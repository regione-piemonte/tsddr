/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.csi.tsddr.tsddrbl.business.be.service.AreaPersonaleService;
import it.csi.tsddr.tsddrbl.business.be.web.AreaPersonaleController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.areapersonale.AreaPersonaleUtenteVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class AreaPersonaleControllerImpl implements AreaPersonaleController {
	
	private static Logger logger = Logger.getLogger(AreaPersonaleControllerImpl.class);

	@Autowired
	private AreaPersonaleService areaPersonaleService;
	
	@Override
	public GenericResponse<AreaPersonaleUtenteVO> getDatiUtenteCorrente(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::getDatiUtenteCorrente] BEGIN");
		GenericResponse<AreaPersonaleUtenteVO> response = areaPersonaleService.getDatiUtenteCorrente(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::getDatiUtenteCorrente] END");
		return response;
	}
	
	@Override
	public GenericResponse<AreaPersonaleUtenteVO> getUtenteCorrenteNomeCognome(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::getUtenteCorrenteNomeCognome] BEGIN");
		GenericResponse<AreaPersonaleUtenteVO> response = areaPersonaleService.getNomeCognomeUtenteCorrente(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::getUtenteCorrenteNomeCognome] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> logLogout(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::logLogout] BEGIN");
		GenericResponse<String> response = areaPersonaleService.logLogout(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::logLogout] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLAreaPersonale(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::getACLAreaPersonale] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = areaPersonaleService.getACLFunzionalitaProfilo(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::getACLAreaPersonale] END");
		return response;
	}

	@Override
	public GenericResponse<String> verificaDatiObbligatoriAreaPersonale(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, AreaPersonaleUtenteVO areaPersonaleUtenteVO) {
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::verificaDatiObbligatoriAreaPersonale] BEGIN");
		GenericResponse<String> response = areaPersonaleService.verificaDatiObbligatoriAreaPersonale(areaPersonaleUtenteVO);
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::verificaDatiObbligatoriAreaPersonale] END");
		return response;
	}

	@Override
	public GenericResponse<DatiSoggVO> updateAreaPersonaleUtenteCorrente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, AreaPersonaleUtenteVO areaPersonaleUtenteVO) {
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::updateAreaPersonaleUtenteCorrente] BEGIN");
		GenericResponse<DatiSoggVO> response = areaPersonaleService.updateAreaPersonaleUtente(httpRequest.getSession(), areaPersonaleUtenteVO);
		LoggerUtil.debug(logger, "[AreaPersonaleControllerImpl::updateAreaPersonaleUtenteCorrente] END");
		return response;
	}

}
