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

import it.csi.tsddr.tsddrbl.business.be.service.PeriodoService;
import it.csi.tsddr.tsddrbl.business.be.web.PeriodiController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.PeriodoVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class PeriodiControllerImpl implements PeriodiController {
	
	private static Logger logger = Logger.getLogger(PeriodiControllerImpl.class);
	
	@Autowired
	private PeriodoService periodoService;

	@Override
	public GenericResponse<List<PeriodoVO>> getListaPeriodi(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ComuniControllerImpl::getListaPeriodi] BEGIN");
		GenericResponse<List<PeriodoVO>> response = periodoService.getListaPeriodi();
		LoggerUtil.debug(logger, "[ComuniControllerImpl::getListaPeriodi] END");
		return response;
	}

}