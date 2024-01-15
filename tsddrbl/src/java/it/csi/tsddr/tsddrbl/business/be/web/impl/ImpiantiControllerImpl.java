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

import it.csi.tsddr.tsddrbl.business.be.service.ImpiantoService;
import it.csi.tsddr.tsddrbl.business.be.service.StatoImpiantoService;
import it.csi.tsddr.tsddrbl.business.be.service.TipoImpiantoService;
import it.csi.tsddr.tsddrbl.business.be.web.ImpiantiController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.impianto.AttoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.CheckImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.LineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.SottoLineaVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class ImpiantiControllerImpl implements ImpiantiController {
	
	private static Logger logger = Logger.getLogger(ImpiantiControllerImpl.class);
	
	@Autowired
	private StatoImpiantoService statoImpiantoService;
	
	@Autowired
	private TipoImpiantoService tipoImpiantoService;
	
	@Autowired
	private ImpiantoService impiantoService;

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLImpianti(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getACLImpianti] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = impiantoService.getACLImpianti(httpRequest.getSession());
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getACLImpianti] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboTipiImpianto(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboTipiImpianto] BEGIN");
		GenericResponse<List<SelectVO>> response = tipoImpiantoService.getComboTipoImpianto();
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboTipiImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboStatiImpianto(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboStatiImpianto] BEGIN");
		GenericResponse<List<SelectVO>> response = statoImpiantoService.getComboStatiImpianto();
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboStatiImpianto] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getComboLinee(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboLinee] BEGIN");
		GenericResponse<List<SelectVO>> response = impiantoService.getComboLinee();
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboLinee] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboLineeImpianto(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboLineeImpianto] BEGIN");
		GenericResponse<List<SelectVO>> response = impiantoService.getComboLineeImpianto(idImpianto);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboLineeImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboTipoProvvedimento(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboStatiImpianto] BEGIN");
		GenericResponse<List<SelectVO>> response = impiantoService.getComboTipoProvvedimento();
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboStatiImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<String> getParametriFiltoApplicati(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getParametriFiltoApplicati] BEGIN");
		GenericResponse<String> response = impiantoService.getParametriFiltoApplicati(parametriRicerca);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getParametriFiltoApplicati] END");
		return response;
	}

	@Override
	public GenericResponse<List<ImpiantoVO>> getListaImpianti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getListaImpianti] BEGIN");
		GenericResponse<List<ImpiantoVO>> response = impiantoService.getListaImpianti(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getListaImpianti] END");
		return response;
	}

	@Override
	public GenericResponse<ImpiantoVO> addImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ImpiantoVO impiantoVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addImpianto] BEGIN");
		GenericResponse<ImpiantoVO> response = impiantoService.addImpianto(httpRequest.getSession(), impiantoVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<ImpiantoVO> getImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getImpianto] BEGIN");
		GenericResponse<ImpiantoVO> response = impiantoService.getImpianto(httpRequest.getSession(), idImpianto);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<ImpiantoVO> updateImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, ImpiantoVO impiantoVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addImpianto] BEGIN");
		GenericResponse<ImpiantoVO> response = impiantoService.updateImpianto(httpRequest.getSession(), idImpianto, impiantoVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<ImpiantoVO> removeImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeImpianto] BEGIN");
		GenericResponse<ImpiantoVO> response = impiantoService.removeImpianto(httpRequest.getSession(), idImpianto);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeImpianto] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<GenericLineaVO>> getLineeImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, String idPrevCons) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getLineeImpianto] BEGIN");
		GenericResponse<List<GenericLineaVO>> response = impiantoService.getLineeImpianto(idImpianto, idPrevCons);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getLineeImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<LineaVO> addLineaImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idLinea, LineaVO lineaVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addLineaImpianto] BEGIN");
		GenericResponse<LineaVO> response = impiantoService.addLineaImpianto(httpRequest.getSession(), idImpianto, idLinea, lineaVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<LineaVO> updateLineaImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idLinea, LineaVO lineaVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::updateLineaImpianto] BEGIN");
		GenericResponse<LineaVO> response = impiantoService.updateLineaImpianto(httpRequest.getSession(), idImpianto, idLinea, lineaVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::updateLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<LineaVO> removeLineaImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idLinea) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeLineaImpianto] BEGIN");
		GenericResponse<LineaVO> response = impiantoService.removeLineaImpianto(httpRequest.getSession(), idImpianto, idLinea);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<SottoLineaVO> addSottoLineaImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idSottoLinea, SottoLineaVO sottoLineaVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addSottoLineaImpianto] BEGIN");
		GenericResponse<SottoLineaVO> response = impiantoService.addSottoLineaImpianto(httpRequest.getSession(), idImpianto, idSottoLinea, sottoLineaVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addSottoLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<SottoLineaVO> updateSottoLineaImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idSottoLinea, SottoLineaVO sottoLineaVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::updateSottoLineaImpianto] BEGIN");
		GenericResponse<SottoLineaVO> response = impiantoService.updateSottoLineaImpianto(httpRequest.getSession(), idImpianto, idSottoLinea, sottoLineaVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::updateSottoLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<SottoLineaVO> removeSottoLineaImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idSottoLinea) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeSottoLineaImpianto] BEGIN");
		GenericResponse<SottoLineaVO> response = impiantoService.removeSottoLineaImpianto(httpRequest.getSession(), idImpianto, idSottoLinea);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeSottoLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<List<AttoVO>> getAttiImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getAttiImpianto] BEGIN");
		GenericResponse<List<AttoVO>> response = impiantoService.getAttiImpianto(httpRequest.getSession(), idImpianto);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getAttiImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<AttoVO> addAttoImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, AttoVO attoVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addAttoImpianto] BEGIN");
		GenericResponse<AttoVO> response = impiantoService.addAttoImpianto(httpRequest.getSession(), idImpianto, attoVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::addAttoImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<AttoVO> updateAttoImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idAtto, AttoVO attoVO) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::updateAttoImpianto] BEGIN");
		GenericResponse<AttoVO> response = impiantoService.updateAttoImpianto(httpRequest.getSession(), idImpianto, idAtto, attoVO);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::updateAttoImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<AttoVO> removeAttoImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto, Long idAtto) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeAttoImpianto] BEGIN");
		GenericResponse<AttoVO> response = impiantoService.removeAttoImpianto(httpRequest.getSession(), idImpianto, idAtto);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::removeAttoImpianto] END");
		return response;
	}
	
	@Override
	public GenericResponse<ReportVO> downloadReport(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger,"[ImpiantiControllerImpl::downloadReport] BEGIN");
		GenericResponse<ReportVO> response = impiantoService.downloadReport(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger,"[ImpiantiControllerImpl::downloadReport] END");
		return response;
	}
	
	@Override
	public GenericResponse<CheckImpiantoVO> checkDeleteImpianto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idImpianto) {
		LoggerUtil.debug(logger,"[ImpiantiControllerImpl::checkDeleteImpianto] BEGIN");
		GenericResponse<CheckImpiantoVO> response = impiantoService.checkDeleteImpianto(idImpianto);
		LoggerUtil.debug(logger,"[ImpiantiControllerImpl::checkDeleteImpianto] END");
		return response;
	}
}
