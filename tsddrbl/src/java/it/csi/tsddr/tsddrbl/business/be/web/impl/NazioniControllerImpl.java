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

import it.csi.tsddr.tsddrbl.business.be.service.NazioneService;
import it.csi.tsddr.tsddrbl.business.be.web.NazioniController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class NazioniControllerImpl implements NazioniController {
	
	private static Logger logger = Logger.getLogger(NazioniControllerImpl.class);
	
	@Autowired
	private NazioneService nazioniService;

	@Override
	public GenericResponse<List<SelectVO>> getComboNazioni(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, boolean nazioniEstere) {
		LoggerUtil.debug(logger, "[NazioniControllerImpl::getComboNazioni] BEGIN");
		GenericResponse<List<SelectVO>> response = nazioniService.getComboNazioni(nazioniEstere);
		LoggerUtil.debug(logger, "[NazioniControllerImpl::getComboNazioni] END");
		return response;
	}

}
