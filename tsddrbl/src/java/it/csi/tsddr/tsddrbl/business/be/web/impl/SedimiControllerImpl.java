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

import it.csi.tsddr.tsddrbl.business.be.service.SedimeService;
import it.csi.tsddr.tsddrbl.business.be.web.SedimiController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class SedimiControllerImpl implements SedimiController {

	private static Logger logger = Logger.getLogger(SedimiControllerImpl.class);

	@Autowired
	private SedimeService sedimeService;

	@Override
	public GenericResponse<List<SelectVO>> getComboSedime(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[SedimeControllerImpl::getComboSedime] BEGIN");
		GenericResponse<List<SelectVO>> response = sedimeService.getComboSedime();
		LoggerUtil.debug(logger, "[SedimeControllerImpl::getComboSedime] END");
		return response;
	}

}
