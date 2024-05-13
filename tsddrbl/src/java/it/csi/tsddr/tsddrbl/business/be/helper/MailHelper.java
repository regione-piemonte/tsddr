/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrEmailDT;
import it.csi.tsddr.tsddrbl.business.be.exception.MailException;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrEmailDTRepository;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;

@Component
public class MailHelper {

	private static Logger logger = Logger.getLogger(MailHelper.class);
	
	private static final String EMAIL_ERROR_MSG = "Errore durante la creazione dell'e-mail";

	@Value("${tsddrbl.mail.sendMaxRetry:1}")
	private int maxRetry;
	
	@Value("${tsddrbl.mail.sendRetryInterval:3}")
	private int retryInterval;
	
	@Autowired
	private TsddrEmailDTRepository emailDTRepository;
	
	private JavaMailSenderImpl sender;
	
	@PostConstruct
	private void initMailSender()  {
		LoggerUtil.debug(logger, "[MailHelper::initMailSender] BEGIN");
		
		maxRetry = maxRetry > 0 ? maxRetry : 1;
		
		Optional<TsddrEmailDT> emailDTOpt = emailDTRepository.findByIdCasella(1L);
		
		if (emailDTOpt.isEmpty()) {
			LoggerUtil.error(logger, String.format("[MailHelper::initMailSender] Dati tecnici casella non trovati con idCasella = [%d]. Impossibile inizializzare il MailSender.", 1));
			throw new MailException("Dati tecnici casella non trovati");
		}
		
		TsddrEmailDT emailDT = emailDTOpt.get();
		
		// aruba
//		emailDT.setAutenticazione("no-reply@chromaitaly.com:R3plyN0t!");
//		emailDT.setPorta("465");
//		emailDT.setNomeServer("smtps.aruba.it");

		sender = new JavaMailSenderImpl();

		sender.setHost(emailDT.getNomeServer());
		int port = Integer.parseInt(emailDT.getPorta());
		sender.setPort(port);
		
		boolean auth = false;		
		if (!StringUtils.equalsIgnoreCase(emailDT.getAutenticazione(), "Nessuna")) {
			auth = true;
			// TODO da verificare quale è il formato dei dati di autenticazione
			String[] userPwd = StringUtils.split(emailDT.getAutenticazione(), ":");
			sender.setUsername(userPwd[0]);
			sender.setPassword(userPwd[1]);
		}
		
//		String protocol = "smtp";
		boolean startTLS = false, ssl = false;
		
		if (StringUtils.equalsIgnoreCase(emailDT.getSicurezzaConn(), "STARTTLS")) {
			startTLS = true;
//			protocol = "smtps";
		} else if (List.of("SSL", "TLS", "SSL/TLS").contains(emailDT.getSicurezzaConn())) {
			ssl = true;
		}
		
//		sender.setProtocol(protocol);

//		boolean isSecure = StringUtils.equalsIgnoreCase(protocol, "smtps");

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.host", emailDT.getNomeServer());
	    properties.put("mail.smtp.port", port);
//		properties.put("mail.transport.protocol", protocol);
		properties.put("mail.smtp.starttls.enable", startTLS);
		properties.put("mail.smtp.ssl.trust", emailDT.getNomeServer());
		if (ssl) {
			properties.put("mail.smtp.ssl.enable", ssl);
			properties.put("mail.smtp.socketFactory.port", port);
			properties.put("mail.smtp.ssl.checkserveridentity", true);
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", false);
		}
		
		sender.setJavaMailProperties(properties);
		
		LoggerUtil.debug(logger, "[MailHelper::initMailSender] END");
	}

	public void send(String from, String to, String subject, String body) {
		send(from, new String[] { to }, subject, body);
	}

	public void send(String from, String to, String subject, String body, boolean hideRecipient) {
		send(from, new String[] { to }, subject, body, hideRecipient);
	}

	public void send(String from, List<String> tos, String subject, String body) {
		send(from, tos.toArray(new String[tos.size()]), subject, body);
	}

	public void send(String from, String[] tos, String subject, String body) {
		send(from, tos, subject, body, false);
	}

	public void send(String from, List<String> tos, String subject, String body, boolean hideRecipients) {
		send(from, tos.toArray(new String[tos.size()]), subject, body, hideRecipients);
	}

	public void send(String from, String[] tos, String subject, String body, boolean hideRecipients) {
		MimeMessage message = createMimeMessage(from, tos, subject, body, hideRecipients);
		// invio mail
		send(message);
	}

	public MimeMessage createMimeMessage(String from, String[] tos, String subject, String body, boolean hideRecipients) {
		return createMimeMessage(from, tos, subject, body, hideRecipients, Map.of());
	}

	public MimeMessage createMimeMessage(String from, String[] tos, String subject, String body, boolean hideRecipients, File... files) {
		LoggerUtil.debug(logger, "[MailHelper::createMimeMessage] BEGIN");
		Map<String, InputStream> attachments = null;

		// inserimento allegati
		if (files != null) {
			attachments = new HashMap<String, InputStream>();

			for (File file : files) {
				try {
					attachments.put(file.getName(), new FileInputStream(file));
				} catch (FileNotFoundException e) {
					LoggerUtil.error(logger, String.format("[MailHelper::createMimeMessage] Errore nella lettura dell'allegato [%s]", file.getAbsolutePath()));
					throw new MailException(EMAIL_ERROR_MSG, e);
				}
			}
		}

		return createMimeMessage(from, tos, subject, body, hideRecipients, attachments);
	}

	public MimeMessage createMimeMessage(String from, String[] tos, String subject, String body, boolean hideRecipients, Map<String, InputStream> attachments) {
		LoggerUtil.debug(logger, "[MailHelper::createMimeMessage] BEGIN");
		
		try {
			MimeMessage message = sender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "ISO-8859-1");
	
			helper.setSentDate(new Date());
			helper.setFrom(new InternetAddress(from));
	
			if (hideRecipients) {
				helper.setBcc(tos);
	
			} else {
				helper.setTo(tos);
			}
	
			helper.setSubject(subject);
			helper.setText(body, true);
	
			// inserimento allegati
			if (MapUtils.isNotEmpty(attachments)) {
				for (Entry<String, InputStream> entry : attachments.entrySet()) {
					try {
						helper.addAttachment(entry.getKey(), new ByteArrayResource(IOUtils.toByteArray(entry.getValue())));
					} catch (IOException e) {
						LoggerUtil.error(logger, String.format("[MailHelper::createMimeMessage] Errore nell'inserimento dell'allegato [%s] nell'e-mail", entry.getKey()));
						throw new MailException(EMAIL_ERROR_MSG, e);
					}
				}
			}
	
			LoggerUtil.debug(logger, "[MailHelper::createMimeMessage] END");
			
			return message;
			
		} catch (Exception e) {
			LoggerUtil.error(logger, "[MailHelper::createMimeMessage] Errore durante la creazione dell'e-mail");
			throw new MailException(EMAIL_ERROR_MSG, e);
		}
	}

	private void send(MimeMessage message) {
		LoggerUtil.debug(logger, "[MailHelper::send] BEGIN");
		
		for (int i = 1; i <= maxRetry; i++) {
			try {
				LoggerUtil.debug(logger, String.format("[MailHelper::send] Tentativo %d/%d di invio e-mail in corso...", i, maxRetry));
				sender.send(message);
				LoggerUtil.debug(logger, "[MailHelper::send] E-mail inviata correttamente");
				break;

			} catch (Exception e) {
				if (i < maxRetry) {
					LoggerUtil.warn(logger, "[MailHelper::send] Errore nell'invio dell'e-mail. Riprovo...");
					try {
						Thread.sleep(retryInterval * 1000L);
						
					} catch (InterruptedException e1) {
						LoggerUtil.warn(logger, "[MailHelper::send] Errore durante il tentativo di invio e-mail", e1);
					    // Restore interrupted state...
					    Thread.currentThread().interrupt();
					}

				} else {
					LoggerUtil.error(logger, "[MailHelper::send] Impossibile inviare l'e-mail.", e);
					throw new MailException("Impossibile inviare l'e-mail.", e);
				}
			}
		}
		
		LoggerUtil.debug(logger, "[MailHelper::send] END");
	}
}