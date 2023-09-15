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

import it.csi.tsddr.tsddrbl.business.be.service.ProvinciaService;
import it.csi.tsddr.tsddrbl.business.be.web.ProvinceController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class ProvinceControllerImpl implements ProvinceController {
	
	private static Logger logger = Logger.getLogger(ProvinceControllerImpl.class);
	
	@Autowired
	private ProvinciaService provinciaService;

	@Override
	public GenericResponse<List<SelectVO>> getComboProvince(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ProvinceControllerImpl::getComboProvince] BEGIN");
		GenericResponse<List<SelectVO>> response = provinciaService.getComboProvince();
		LoggerUtil.debug(logger, "[ProvinceControllerImpl::getComboProvince] END");
		return response;
	}

}
