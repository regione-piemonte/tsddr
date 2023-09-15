/**
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

import it.csi.tsddr.tsddrbl.business.be.service.ComuneService;
import it.csi.tsddr.tsddrbl.business.be.web.ComuniController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class ComuniControllerImpl implements ComuniController {
	
	private static Logger logger = Logger.getLogger(ComuniControllerImpl.class);
	
	@Autowired
	private ComuneService comuniService;

	@Override
	public GenericResponse<List<SelectVO>> getComboComuni(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idProvincia) {
		LoggerUtil.debug(logger, "[ComuniControllerImpl::getComboComuni] BEGIN");
		GenericResponse<List<SelectVO>> response = comuniService.getComboComuni(idProvincia);
		LoggerUtil.debug(logger, "[ComuniControllerImpl::getComboComuni] END");
		return response;
	}

}
