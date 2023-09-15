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

import it.csi.tsddr.tsddrbl.business.be.service.UtenteProfiloService;
import it.csi.tsddr.tsddrbl.business.be.web.UtentiProfiloController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.utenteprofilo.UtenteProfiloVO;

@RestController
public class UtentiProfiloControllerImpl implements UtentiProfiloController {
	
	private static Logger logger = Logger.getLogger(UtentiProfiloControllerImpl.class);
	
	@Autowired
	private UtenteProfiloService utenteProfiloService;

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLUtenteProfilo(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::getACLUtenteProfilo] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = utenteProfiloService.getACLUtenteProfilo(httpRequest.getSession());
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::getACLUtenteProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<UtenteProfiloVO>> getListaUtentiProfilo(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::getListaUtentiProfilo] BEGIN");
		GenericResponse<List<UtenteProfiloVO>> response = utenteProfiloService.getListaUtentiProfilo(httpRequest.getSession(), idProfilo);
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::getListaUtentiProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<UtenteProfiloVO> createUtenteProfilo(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::createUtenteProfilo] BEGIN");
		GenericResponse<UtenteProfiloVO> response = utenteProfiloService.createUtenteProfilo(httpRequest.getSession(), idUtente, idProfilo);
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::createUtenteProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<String> deleteUtenteProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::deleteUtenteProfilo] BEGIN");
		GenericResponse<String> response = utenteProfiloService.deleteUtenteProfilo(httpRequest.getSession(), idUtente, idProfilo);
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::deleteUtenteProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboNuovoUtente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::getComboNuovoUtente] BEGIN");
		GenericResponse<List<SelectVO>> response = utenteProfiloService.getComboNuovoUtente(idProfilo);
		LoggerUtil.debug(logger, "[UtentiProfiloControllerImpl::getComboNuovoUtente] END");
		return response;
	}



}
