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

import it.csi.tsddr.tsddrbl.business.be.service.LoginService;
import it.csi.tsddr.tsddrbl.business.be.web.LoginController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.menu.MenuCardVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.userinfo.UserInfoVO;

@RestController
public class LoginControllerImpl implements LoginController {

	private static Logger logger = Logger.getLogger(LoginControllerImpl.class);
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public GenericResponse<UserInfoVO> getIdProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String xForwardedFor) {
		LoggerUtil.debug(logger, "[LoginControllerImpl::getIdProfilo] BEGIN");
		LoggerUtil.info(logger, String.format("[LoginControllerImpl::getIdProfilo] HEADER xforwardedFor: [%s]", xForwardedFor));
		GenericResponse<UserInfoVO> response = loginService.getIdProfilo(httpRequest.getSession());
		LoggerUtil.debug(logger, "[LoginControllerImpl::getIdProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<MenuCardVO>> getStrutturaMenueCard(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[LoginControllerImpl::getStrutturaMenueCard] BEGIN");
		GenericResponse<List<MenuCardVO>> response = loginService.getStrutturaMenueCard(httpRequest.getSession());
		LoggerUtil.debug(logger, "[LoginControllerImpl::getStrutturaMenueCard] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboProfiloLogin(SecurityContext securityContext, HttpHeaders httpHeaders, 
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[LoginControllerImpl::getComboProfiloLogin] BEGIN");
		GenericResponse<List<SelectVO>> response = loginService.getComboProfiloLogin(httpRequest.getSession());
		LoggerUtil.debug(logger, "[LoginControllerImpl::getComboProfiloLogin] END");
		return response;
	}

	@Override
	public GenericResponse<String> logCambioProfilo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProfilo) {
		LoggerUtil.debug(logger, "[LoginControllerImpl::logCambioProfilo] BEGIN");
		GenericResponse<String> defaultResponse = loginService.logCambioProfilo(httpRequest.getSession(), idProfilo);
		LoggerUtil.debug(logger, "[LoginControllerImpl::logCambioProfilo] END");
		return defaultResponse;
	}

}
