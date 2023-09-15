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

import it.csi.tsddr.tsddrbl.business.be.service.GestoreService;
import it.csi.tsddr.tsddrbl.business.be.web.GestoriController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class GestoriControllerImpl implements GestoriController {
	
	private static Logger logger = Logger.getLogger(GestoriControllerImpl.class);
	
	@Autowired
	private GestoreService gestoreService;
	
	@Override
	public GenericResponse<List<SelectVO>> getComboGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getComboGestore] BEGIN");
		GenericResponse<List<SelectVO>> response = gestoreService.getComboGestore();
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getComboGestore] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLGestori(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getACLGestori] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = gestoreService.getACLGestori(httpRequest.getSession());
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getACLGestori] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> getParametriFiltoApplicati(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, GestoreParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getParametriFiltoApplicati] BEGIN");
		GenericResponse<String> response = gestoreService.getParametriFiltoApplicati(parametriRicerca);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getParametriFiltoApplicati] END");
		return response;
	}

	@Override
	public GenericResponse<List<GestoreVO>> getListaGestori(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, GestoreParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getListaGestori] BEGIN");
		GenericResponse<List<GestoreVO>> response = gestoreService.getListaGestori(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getListaGestori] END");
		return response;
	}

	@Override
	public GenericResponse<GestoreVO> getGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idGestore) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getGestore] BEGIN");
		GenericResponse<GestoreVO> response = gestoreService.getGestore(httpRequest.getSession(), idGestore);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getGestore] END");
		return response;
	}

	@Override
	public GenericResponse<GestoreVO> createGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, GestoreVO gestoreVO) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::createGestore] BEGIN");
		GenericResponse<GestoreVO> response = gestoreService.createGestore(httpRequest.getSession(), gestoreVO);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::createGestore] END");
		return response;
	}

	@Override
	public GenericResponse<GestoreVO> updatGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idGestore, GestoreVO gestoreVO) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::updatGestore] BEGIN");
		GenericResponse<GestoreVO> response = gestoreService.updatGestore(httpRequest.getSession(), idGestore, gestoreVO);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::updatGestore] END");
		return response;
	}

	@Override
	public GenericResponse<GestoreVO> removeGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idGestore) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::removeGestore] BEGIN");
		GenericResponse<GestoreVO> response = gestoreService.removeGestore(httpRequest.getSession(), idGestore);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::removeGestore] END");
		return response;
	}
	
	@Override
	public GenericResponse<Boolean> existsDomandeAccreditamentoGestore(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idGestore) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::existsDomandeAccreditamentoGestore] BEGIN");
		GenericResponse<Boolean> response = gestoreService.hasDomandeAccreditamento(idGestore);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::existsDomandeAccreditamentoGestore] END");
		return response;
	}
	
	@Override
	public GenericResponse<Boolean> existsImpiantiGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idGestore) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::existsImpiantiGestore] BEGIN");
		GenericResponse<Boolean> response = gestoreService.hasImpianti(idGestore);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::existsImpiantiGestore] END");
		return response;
	}	

	@Override
	public GenericResponse<DatiSoggVO> getRappresentanteLegale(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, String codFiscPartiva) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getRappresentanteLegale] BEGIN");
		GenericResponse<DatiSoggVO> response = gestoreService.getRappresentanteLegale(codFiscPartiva);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::getRappresentanteLegale] END");
		return response;
	}

	@Override
	public GenericResponse<Boolean> existsGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String codFiscPartiva) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::existsGestore] BEGIN");
		GenericResponse<Boolean> response = gestoreService.existsGestore(codFiscPartiva);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::existsGestore] END");
		return response;
	}

	@Override
	public GenericResponse<ReportVO> downloadReport(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, GestoreParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[GestoriControllerImpl::downloadReport] BEGIN");
		GenericResponse<ReportVO> response = gestoreService.downloadReport(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger, "[GestoriControllerImpl::downloadReport] END");
		return response;
	}

}
