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

import it.csi.tsddr.tsddrbl.business.be.service.FunzionalitaProfiloService;
import it.csi.tsddr.tsddrbl.business.be.web.FunzionalitaProfiloController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class FunzionalitaProfiloControllerImpl implements FunzionalitaProfiloController {

	private static Logger logger = Logger.getLogger(FunzionalitaProfiloControllerImpl.class);
	
	@Autowired
	private FunzionalitaProfiloService funzionalitaProfiloService;
	
	@Override
	public GenericResponse<List<SelectVO>> getComboFunzionalita(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getComboFunzionalita] BEGIN");
		GenericResponse<List<SelectVO>> response = funzionalitaProfiloService.getComboFunzionalitaPerProfilo(idProfilo);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getComboFunzionalita] END");
		return response;
	}

	@Override
	public GenericResponse<List<FunzionalitaProfiloVO>> getListaFunzionalitaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo, Long idFunzionalita) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getTabellaProfiliFunzionalita] BEGIN");
		GenericResponse<List<FunzionalitaProfiloVO>> response = funzionalitaProfiloService.getListaFunzionalitaProfilo(idProfilo, idFunzionalita);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getTabellaProfiliFunzionalita] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLFunzionalitaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getFunzionalitaProfiloGestioneProfiloUtente] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = funzionalitaProfiloService.getACLFunzionalitaProfilo(httpRequest.getSession());
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getFunzionalitaProfiloGestioneProfiloUtente] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboNuovaFunzionalitaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getComboNuovaFunzionalitaProfilo] BEGIN");
		GenericResponse<List<SelectVO>> response = funzionalitaProfiloService.getComboNuovaFunzionalita(idProfilo);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::getComboNuovaFunzionalitaProfilo] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> logConfFunzionalitaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo, Long idFunzionalita) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::logConfProfiloFunzionalita] BEGIN");
		GenericResponse<String> response = funzionalitaProfiloService.logConfProfiloFunzionalita(httpRequest.getSession(), idProfilo, idFunzionalita);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::logConfProfiloFunzionalita] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> updateFunzionalitaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, FunzionalitaProfiloVO FunzionalitaProfiloVO) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::updateFunzionalitaProfilo] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = funzionalitaProfiloService.updateFunzionalitaProfilo(httpRequest.getSession(), FunzionalitaProfiloVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::updateFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> createFunzionalitaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, FunzionalitaProfiloVO FunzionalitaProfiloVO) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::createFunzionalitaProfilo] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = funzionalitaProfiloService.createFunzionalitaProfilo(httpRequest.getSession(), FunzionalitaProfiloVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::createFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> removeFunzionalitaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo, Long idFunzionalita) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::removeFunzionalitaProfilo] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = funzionalitaProfiloService.removeFunzionalitaProfilo(httpRequest.getSession(), idProfilo, idFunzionalita);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloControllerImpl::removeFunzionalitaProfilo] END");
		return response;
	}

}
