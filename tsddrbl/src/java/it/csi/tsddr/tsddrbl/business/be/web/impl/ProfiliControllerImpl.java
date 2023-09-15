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

import it.csi.tsddr.tsddrbl.business.be.service.ProfiloService;
import it.csi.tsddr.tsddrbl.business.be.web.ProfiliController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class ProfiliControllerImpl implements ProfiliController {

	private static Logger logger = Logger.getLogger(ProfiliControllerImpl.class);
	
	@Autowired
	private ProfiloService profiloService;
	
	@Override
	public GenericResponse<List<SelectVO>> getComboProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getComboProfilo] BEGIN");
		GenericResponse<List<SelectVO>> response = profiloService.getComboProfilo(false);
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getComboProfilo] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getComboProfiloAll(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getComboProfiloAll] BEGIN");
		GenericResponse<List<SelectVO>> response = profiloService.getComboProfilo(true);
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getComboProfiloAll] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<ProfiloVO>> getListaProfili(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getListaProfili] BEGIN");
		GenericResponse<List<ProfiloVO>> response = profiloService.getTabellaProfili(httpRequest.getSession());
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getListaProfili] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLProfili(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getProfilazioneGestioneProfiloUtente] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = profiloService.getACLProfili(httpRequest.getSession());
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::getProfilazioneGestioneProfiloUtente] END");
		return response;
	}

	@Override
	public GenericResponse<String> verificaProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ProfiloVO profiloVO) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::verificaProfilo] BEGIN");
		GenericResponse<String> defaultResponse = profiloService.verificaDatiProfilo(profiloVO);
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::verificaProfilo] END");
		return defaultResponse;
	}

	@Override
	public GenericResponse<ProfiloVO> updateProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ProfiloVO profiloVO) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::updateProfilo] BEGIN");
		GenericResponse<ProfiloVO> response = profiloService.updateProfilo(httpRequest.getSession(), profiloVO);
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::updateProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<ProfiloVO> createProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ProfiloVO profiloVO) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::createProfilo] BEGIN");
		GenericResponse<ProfiloVO> response = profiloService.createProfilo(httpRequest.getSession(), profiloVO);
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::createProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<ProfiloVO> removeProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo) {
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::removeProfilo] BEGIN");
		GenericResponse<ProfiloVO> response = profiloService.removeProfilo(httpRequest.getSession(), idProfilo);
		LoggerUtil.debug(logger, "[ProfiliControllerImpl::removeProfilo] END");
		return response;
	}

    @Override
    public GenericResponse<Boolean> isProfiloBO(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idProfilo) {
        LoggerUtil.debug(logger, "[ProfiliControllerImpl::isProfiloBO] BEGIN");
        GenericResponse<Boolean> response = profiloService.isProfiloBO(httpRequest.getSession(), idProfilo);
        LoggerUtil.debug(logger, "[ProfiliControllerImpl::isProfiloBO] END");
        return response;
    }

}