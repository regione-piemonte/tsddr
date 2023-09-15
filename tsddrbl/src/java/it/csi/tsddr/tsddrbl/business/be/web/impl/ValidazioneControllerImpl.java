/**
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

import it.csi.tsddr.tsddrbl.business.be.service.ValidazioneService;
import it.csi.tsddr.tsddrbl.business.be.service.impl.UtenteServiceImpl;
import it.csi.tsddr.tsddrbl.business.be.web.ValidazioneController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class ValidazioneControllerImpl implements ValidazioneController {
	
	private static Logger logger = Logger.getLogger(UtenteServiceImpl.class);
	
	@Autowired
	private ValidazioneService validazioneService;

	@Override
	public GenericResponse<String> verificaFormatoCodiceFiscale(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, String codiceFiscale) {
		LoggerUtil.debug(logger, "[ValidazioneControllerImpl::verificaFormatoCodiceFiscale] BEGIN");
		GenericResponse<String> response = validazioneService.verificaCodiceFiscale(codiceFiscale);
		LoggerUtil.debug(logger, "[ValidazioneControllerImpl::verificaFormatoCodiceFiscale] END");
		return response;
	}


	@Override
	public GenericResponse<String> verificaFormatoEmail(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String email) {
		LoggerUtil.debug(logger, "[ValidazioneControllerImpl::verificaFormatoCodiceFiscale] BEGIN");
		GenericResponse<String> response = validazioneService.verificaEmail(email);
		LoggerUtil.debug(logger, "[ValidazioneControllerImpl::verificaFormatoCodiceFiscale] END");
		return response;
	}


	@Override
	public GenericResponse<String> verificaFormatoPec(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String pec) {
		// TODO Auto-generated method stub
		return null;
	}

}
