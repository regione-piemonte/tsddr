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

import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.business.be.web.MessaggiController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class MessaggiControllerImpl implements MessaggiController {
	
	private static Logger logger = Logger.getLogger(MessaggiController.class);
	
	@Autowired
	private MessaggioService messaggioService;

	@Override
	public GenericResponse<MessaggioVO> getMessaggioByCodMsg(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String codMsg) {
		LoggerUtil.debug(logger, "[MessaggiControllerImpl::getMessaggioByCodMsg]");
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(codMsg);
		LoggerUtil.debug(logger, "[MessaggiControllerImpl::getMessaggioByCodMsg]");
		return GenericResponse.build(null, messaggioVO);
	}

}
