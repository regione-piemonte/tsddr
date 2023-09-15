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

import it.csi.tsddr.tsddrbl.business.be.service.AccreditamentoService;
import it.csi.tsddr.tsddrbl.business.be.web.AccreditamentoController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class AccreditamentoControllerImpl implements AccreditamentoController {
	
	private static Logger logger = Logger.getLogger(AccreditamentoControllerImpl.class);
	
	@Autowired
	private AccreditamentoService accreditamentoService;

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLAccreditamento(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getACLAccreditamento] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = accreditamentoService.getACLAccreditamento(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getACLAccreditamento] END");
		return response;
	}

	@Override
	public GenericResponse<List<DomandaAccreditamentoVO>> getListaDomande(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, DomandaAccreditamentoParametriRicercaVO parametriRicerca) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getListaDomande] BEGIN");
		GenericResponse<List<DomandaAccreditamentoVO>> response = accreditamentoService.getListaDomande(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getListaDomande] END");
		return response;
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> getDomanda(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idDomanda) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getDomanda] BEGIN");
		GenericResponse<DomandaAccreditamentoVO> response = accreditamentoService.getDomanda(httpRequest.getSession(), idDomanda);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getDomanda] END");
		return response;
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> createDomanda(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, DomandaAccreditamentoVO domandaVO) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::nuovaDomanda] BEGIN");
		GenericResponse<DomandaAccreditamentoVO> response = accreditamentoService.nuovaDomanda(httpRequest.getSession(), domandaVO);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::nuovaDomanda] END");
		return response;
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> updateDomanda(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idDomanda, DomandaAccreditamentoVO domandaVO) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::aggiornaDomanda] BEGIN");
		GenericResponse<DomandaAccreditamentoVO> response = accreditamentoService.aggiornaDomanda(httpRequest.getSession(), idDomanda, domandaVO);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::aggiornaDomanda] END");
		return response;
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> removeDomanda(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idDomanda) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::annullaDomanda] BEGIN");
		GenericResponse<DomandaAccreditamentoVO> response = accreditamentoService.annullaDomanda(httpRequest.getSession(), idDomanda);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::annullaDomanda] END");
		return response;
	}

	@Override
	public GenericResponse<String> verificaDatiObbligatoriAccreditamento(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, DomandaAccreditamentoVO domandaVO) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::verificaDatiObbligatoriAccreditamento] BEGIN");
		GenericResponse<String> response = accreditamentoService.verificaDatiObbligatori(httpRequest.getSession(), domandaVO);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::verificaDatiObbligatoriAccreditamento] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getComboGestori(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboGestori] BEGIN");
		GenericResponse<List<SelectVO>> response = accreditamentoService.getComboGestori(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboGestori] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboStatiDomanda(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboStatiDomanda] BEGIN");
		GenericResponse<List<SelectVO>> response = accreditamentoService.getComboStatiDomanda(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboStatiDomanda] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getAllComboStatiDomanda(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getAllComboStatiDomanda] BEGIN");
		GenericResponse<List<SelectVO>> response = accreditamentoService.getAllComboStatiDomanda(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getAllComboStatiDomanda] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboRichiedenti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboRichiedenti] BEGIN");
		GenericResponse<List<SelectVO>> response = accreditamentoService.getComboRichidenti(httpRequest.getSession());
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboRichiedenti] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboProfili(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idDomanda) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboRichiedente] BEGIN");
		GenericResponse<List<SelectVO>> response = accreditamentoService.getComboProfili(httpRequest.getSession(), idDomanda);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboRichiedente] END");
		return response;
	}

	@Override
	public GenericResponse<String> getParametriFiltroApplicatiAccreditamento(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, DomandaAccreditamentoParametriRicercaVO parametriRicerca) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboRichiedente] BEGIN");
		GenericResponse<String> response = accreditamentoService.getParametriFiltroApplicatiAccreditamento(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::getComboRichiedente] END");
		return response;
	}

	@Override
	public GenericResponse<ReportVO> downloadReport(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest,
			DomandaAccreditamentoParametriRicercaVO parametriRicerca) {
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::downloadReport] BEGIN");
		GenericResponse<ReportVO> response = accreditamentoService.downloadReport(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger, "[AccreditamentoControllerImpl::downloadReport] END");
		return response;
	}

}
