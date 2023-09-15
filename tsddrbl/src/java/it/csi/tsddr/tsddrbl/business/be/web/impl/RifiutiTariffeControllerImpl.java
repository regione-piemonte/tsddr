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

import it.csi.tsddr.tsddrbl.business.be.service.RifiutoTariffaService;
import it.csi.tsddr.tsddrbl.business.be.web.RifiutiTariffeController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoTariffaVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class RifiutiTariffeControllerImpl implements RifiutiTariffeController {
	
	private static Logger logger = Logger.getLogger(RifiutiTariffeControllerImpl.class);
	
	@Autowired
	private RifiutoTariffaService rifiutoTariffaService;

	@Override
	public GenericResponse<List<SelectVO>> getComboRifiutoTariffa(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[RifiutiTariffeControllerImpl::getComboRifiutoTariffa] BEGIN");
		GenericResponse<List<SelectVO>> response = rifiutoTariffaService.getComboRifiutoTariffa(httpRequest.getSession(), idDichAnnuale);
		LoggerUtil.debug(logger, "[RifiutiTariffeControllerImpl::getComboRifiutoTariffa] END");
		return response;
	}

    @Override
    public GenericResponse<List<RifiutoTariffaVO>> getRifiutoTariffa(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idDichAnnuale) {
        LoggerUtil.debug(logger, "[RifiutiTariffeControllerImpl::getRifiutoTariffa] BEGIN");
        GenericResponse<List<RifiutoTariffaVO>> response = rifiutoTariffaService.getRifiutoTariffa(httpRequest.getSession(), idDichAnnuale);
        LoggerUtil.debug(logger, "[RifiutiTariffeControllerImpl::getRifiutoTariffa] END");
        return response;
    }

}
