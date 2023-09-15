/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.csi.tsddr.tsddrbl.business.be.service.UtenteService;
import it.csi.tsddr.tsddrbl.business.be.web.UtentiController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.gestioneutente.UtenteVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.utente.DatiUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.InsertUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.UtenteParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.utentegestoreprofilo.UtenteGestoreProfiloVO;

@RestController
public class UtentiControllerImpl implements UtentiController {
	
	private static Logger logger = Logger.getLogger(UtentiControllerImpl.class);

	@Autowired
	private UtenteService utenteService;

	@Override
	public GenericResponse<String> verificaParametriRicerca(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::verificaParametriRicerca] BEGIN");
		GenericResponse<String> response = utenteService.verificaParametriRicerca(utenteParametriRicercaVO);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::verificaParametriRicerca] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<DatiUtenteVO>> getGrigliaUtenti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getGrigliaUtenti] BEGIN");
		GenericResponse<List<DatiUtenteVO>> response = utenteService.getGrigliaUtenti(httpRequest.getSession(), utenteParametriRicercaVO);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getGrigliaUtenti] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> getParametriFiltroApplicati(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getGrigliaUtenti] BEGIN");
		GenericResponse<String> response = utenteService.getParametriFiltroApplicati(utenteParametriRicercaVO);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getGrigliaUtenti] END");
		return response;
	}
	
	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLUtente(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getACLUtente] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = utenteService.getACLUtente(httpRequest.getSession());
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getACLUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<DatiUtenteVO> getDatiUtente(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idUtente) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getDatiUtente] BEGIN");
		GenericResponse<DatiUtenteVO> response = utenteService.getDatiUtente(httpRequest.getSession(), idUtente);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getDatiUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<UtenteGestoreProfiloVO>> getDatiUtenteGestori(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getDatiUtenteGestori] BEGIN");
		GenericResponse<List<UtenteGestoreProfiloVO>> response = utenteService.getDatiUtenteGestori(idUtente, idProfilo);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getDatiUtenteGestori] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getProfiliUtente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idUtente) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getProfiliUtente] BEGIN");
		GenericResponse<List<SelectVO>> response = utenteService.getComboProfiliUtente(idUtente);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::getProfiliUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<DatiUtenteVO> updateUtente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, DatiUtenteVO datiUtenteVO) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::updateUtente] BEGIN");
		GenericResponse<DatiUtenteVO> response = utenteService.updateUtente(httpRequest.getSession(), datiUtenteVO);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::updateUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> removeUtente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idUtente) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::verificaFormatoCodiceFiscale] BEGIN");
		GenericResponse<String> response = utenteService.removeUtente(httpRequest.getSession(), idUtente);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::verificaFormatoCodiceFiscale] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> verificaUtente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String codiceFiscale) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::verificaUtente] BEGIN");
		GenericResponse<String> response = utenteService.verificaUtente(codiceFiscale);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::verificaUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<UtenteVO> createUtente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, InsertUtenteVO insertUtenteVO) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::createUtente] BEGIN");
		GenericResponse<UtenteVO> response = utenteService.createUtente(httpRequest.getSession(), insertUtenteVO);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::createUtente] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboGrigliaGestori(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::updateUtente] BEGIN");
		GenericResponse<List<SelectVO>> response = utenteService.getComboGrigliaGestori(httpRequest.getSession(), idUtente, idProfilo);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::updateUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<UtenteGestoreProfiloVO> createUtenteGestoreProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, UtenteGestoreProfiloVO utenteGestoreProfiloVO) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::createUtenteGestoreProfilo] BEGIN");
		GenericResponse<UtenteGestoreProfiloVO> response = utenteService.createUtenteGestoreProfilo(httpRequest.getSession(), utenteGestoreProfiloVO);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::createUtenteGestoreProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<UtenteGestoreProfiloVO> updateUtenteGestoreProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, UtenteGestoreProfiloVO utenteGestoreProfiloVO) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::updateUtenteGestoreProfilo] BEGIN");
		GenericResponse<UtenteGestoreProfiloVO> response = utenteService.updateUtenteGestoreProfilo(httpRequest.getSession(), utenteGestoreProfiloVO);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::updateUtenteGestoreProfilo] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> removeUtenteGestoreProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idUtente, Long idGestore, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtentiControllerImpl::removeUtenteGestoreProfilo] BEGIN");
		GenericResponse<String> response = utenteService.removeUtenteGestoreProfilo(httpRequest.getSession(), idUtente, idGestore, idProfilo);
		LoggerUtil.debug(logger, "[UtentiControllerImpl::removeUtenteGestoreProfilo] END");
		return response;
	}

}
