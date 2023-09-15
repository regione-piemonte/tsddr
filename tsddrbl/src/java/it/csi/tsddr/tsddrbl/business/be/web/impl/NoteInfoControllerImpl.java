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

import it.csi.tsddr.tsddrbl.business.be.service.NotaInfoService;
import it.csi.tsddr.tsddrbl.business.be.web.NoteInfoController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.notainfo.NotaInfoVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class NoteInfoControllerImpl implements NoteInfoController {

	private static Logger logger = Logger.getLogger(NoteInfoControllerImpl.class);
	
	@Autowired
	private NotaInfoService notaInfoService;
	
	@Override
	public GenericResponse<NotaInfoVO> getNotaInfoByCodNotaInfo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String codNotaInfo) {
		LoggerUtil.debug(logger, "[NoteInfoControllerImpl::getNotaInfoByCodNotaInfo] BEGIN");
		NotaInfoVO notaInfoVO = notaInfoService.getNotaInfoByCodNotaInfo(codNotaInfo);
		LoggerUtil.debug(logger, "[NoteInfoControllerImpl::getNotaInfoByCodNotaInfo] END");
		return GenericResponse.build(notaInfoVO);
	}

}
