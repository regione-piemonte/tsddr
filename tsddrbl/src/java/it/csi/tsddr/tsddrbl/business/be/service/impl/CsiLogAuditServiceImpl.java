/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.CsiLogAudit;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametro;
import it.csi.tsddr.tsddrbl.business.be.repository.CsiLogAuditRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrCParametroRepository;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.Parametro;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;

@Service
public class CsiLogAuditServiceImpl implements CsiLogAuditService {
	
	private static Logger logger = Logger.getLogger(CsiLogAuditServiceImpl.class);
	
	@Autowired
	private CsiLogAuditRepository csiLogAuditRepository;
	
	@Autowired
	private TsddrCParametroRepository parametroRepository;
	
	private String ID_APP;
	
	@PostConstruct
	public void init() {
		this.ID_APP = createIdApp();
	}
	
	@Override
	public void traceCsiLogAudit(HttpSession httpSession, String utente, String operazione, String oggOper, String keyOper) {
		LoggerUtil.debug(logger, "[CsiLogAuditServiceImpl::traceCsiLogAudit] BEGIN");
		CsiLogAudit csiLogAudit = new CsiLogAudit();
		csiLogAudit.setDataOra(new Date());
		csiLogAudit.setIdApp(ID_APP);
		csiLogAudit.setUtente(utente);
		csiLogAudit.setOperazione(operazione);
		csiLogAudit.setOggOper(oggOper);
		csiLogAudit.setKeyOper(keyOper);
		csiLogAudit.setIpAddress(SessionUtil.getIpAddressUtente(httpSession));
		csiLogAuditRepository.save(csiLogAudit);
		LoggerUtil.debug(logger, "[CsiLogAuditServiceImpl::traceCsiLogAudit] END");
	}
	
	private String createIdApp() {
		String EMPTY_STRING = "";
		String codiceProdotto = parametroRepository.findByNomeParametro(Parametro.CODICE_PRODOTTO.getNome()).map(TsddrCParametro::getValoreParametroS).orElse(EMPTY_STRING);
		String codiceLineaCliente = parametroRepository.findByNomeParametro(Parametro.CODICE_LINEA_CLIENTE.getNome()).map(TsddrCParametro::getValoreParametroS).orElse(EMPTY_STRING);
		String codiceAmbiente = parametroRepository.findByNomeParametro(Parametro.CODICE_AMBIENTE.getNome()).map(TsddrCParametro::getValoreParametroS).orElse(EMPTY_STRING);
		String codiceUnitaInstallazione = parametroRepository.findByNomeParametro(Parametro.CODICE_UNITA_INSTALLAZIONE.getNome()).map(TsddrCParametro::getValoreParametroS).orElse(EMPTY_STRING);
		
		return String.format("%s_%s_%s_%s", codiceProdotto, codiceLineaCliente, codiceAmbiente, codiceUnitaInstallazione);	
	}
	
	@Override
	public void traceCsiLogAudit(HttpSession httpSession, String utente, String operazione, String oggOper, Long keyOper) {
		traceCsiLogAudit(httpSession, utente, operazione, oggOper, String.valueOf(keyOper));
	}

	@Override
	public void traceCsiLogAudit(HttpSession httpSession, Long utente, String operazione, String oggOper, String keyOper) {
		traceCsiLogAudit(httpSession, String.valueOf(utente), operazione, oggOper, keyOper);
	}

	@Override
	public void traceCsiLogAudit(HttpSession httpSession, Long utente, String operazione, String oggOper, Long keyOper) {
		traceCsiLogAudit(httpSession, String.valueOf(utente), operazione, oggOper, String.valueOf(keyOper));
	}

}
