/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrEmailTesti;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDomanda;
import it.csi.tsddr.tsddrbl.business.be.exception.MailException;
import it.csi.tsddr.tsddrbl.business.be.helper.MailHelper;
import it.csi.tsddr.tsddrbl.business.be.helper.TemplateHelper;
import it.csi.tsddr.tsddrbl.business.be.service.EmailTestiService;
import it.csi.tsddr.tsddrbl.business.be.service.MailService;
import it.csi.tsddr.tsddrbl.util.DbConstants;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;

@Service
public class MailServiceImpl implements MailService {

	private static Logger logger = Logger.getLogger(MailServiceImpl.class);

	@Autowired
	private EmailTestiService emailTestiService;

	@Autowired
	private MailHelper mailHelper;

	@Autowired
	private TemplateHelper templateHelper;

	@Override
	@Async
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoValutazione(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoValutazione] BEGIN");
		
		try {
			// invio email a operatori BO
			TsddrEmailTesti datiEmail = emailTestiService
					.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_VALUTAZIONE);
			mailHelper.send(datiEmail.getMittente(), StringUtils.split(datiEmail.getDestinatari(), ","),
					datiEmail.getOggetto(), templateHelper.generaTestoValutazioneDomandaAccreditamento(domanda));

		} catch (MailException e) {
			LoggerUtil.warn(logger,
					"[MailServiceImpl::sendEmailDomandaAccreditamentoValutazione] Impossibile inviare l'email di valutazione domanda di accreditamento");
			throw e;
		}

		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoValutazione] END");
		return CompletableFuture.completedFuture(null);
	}

	@Override
	@Async
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoRegistrazione(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoRegistrazione] BEGIN");
		
		try {
			// invio email a utente FO
			TsddrEmailTesti datiEmail = emailTestiService
					.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_REGISTRAZIONE);
			mailHelper.send(datiEmail.getMittente(), domanda.getDatiSogg().getEmail(), datiEmail.getOggetto(),
					templateHelper.generaTestoRegistrazioneDomandaAccreditamento(domanda));

		} catch (MailException e) {
			LoggerUtil.warn(logger,
					"[MailServiceImpl::sendEmailDomandaAccreditamentoRegistrazione] Impossibile inviare l'email di registrazione domanda di accreditamento");
			throw e;
		}

		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoRegistrazione] END");
		return CompletableFuture.completedFuture(null);
	}

	@Override
	@Async
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoRifutata(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoRifutata] BEGIN");
		
		try {
			TsddrEmailTesti datiEmail = emailTestiService
					.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_RIFIUTATA);
			mailHelper.send(datiEmail.getMittente(), domanda.getDatiSogg().getEmail(), datiEmail.getOggetto(),
					templateHelper.generaTestoDomandaAccreditamentoRifiutata(domanda));

		} catch (MailException e) {
			LoggerUtil.warn(logger,
					"[MailServiceImpl::sendEmailDomandaAccreditamentoRifutata] Impossibile inviare l'email di rifiuto domanda di accreditamento");
			throw e;
		}

		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoRifutata] END");
		return CompletableFuture.completedFuture(null);
	}

	@Override
	@Async
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoAccettata(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoAccettata] BEGIN");
		
		try {
			TsddrEmailTesti datiEmail = emailTestiService
					.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_ACCETTATA);
			mailHelper.send(datiEmail.getMittente(), domanda.getDatiSogg().getEmail(), datiEmail.getOggetto(),
					templateHelper.generaTestoDomandaAccreditamentoAccettata(domanda));

		} catch (MailException e) {
			LoggerUtil.warn(logger,
					"[MailServiceImpl::sendEmailDomandaAccreditamentoAccettata] Impossibile inviare l'email di accettazione domanda di accreditamento");
			throw e;
		}

		LoggerUtil.debug(logger, "[MailServiceImpl::sendEmailDomandaAccreditamentoAccettata] END");
		return CompletableFuture.completedFuture(null);
	}

}
