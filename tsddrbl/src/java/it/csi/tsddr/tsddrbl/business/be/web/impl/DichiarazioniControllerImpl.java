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

import it.csi.tsddr.tsddrbl.business.be.service.DichiarazioneService;
import it.csi.tsddr.tsddrbl.business.be.web.DichiarazioniController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeBasicVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichiarazioneParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.AllowDuplicaDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.ExistsDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.SoggettoMrVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class DichiarazioniControllerImpl implements DichiarazioniController {

	private static Logger logger = Logger.getLogger(DichiarazioniControllerImpl.class);

	@Autowired
	private DichiarazioneService dichiarazioneService;

	public GenericResponse<FunzionalitaProfiloVO> getACLDichiarazioni(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getACLDichiarazioni] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = dichiarazioneService.getACLDichiarazioni(httpRequest.getSession());
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getACLDichiarazioni] END");
		return response;
	}

	public GenericResponse<List<Long>> getComboAnni(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboAnni] BEGIN");
		GenericResponse<List<Long>> response = dichiarazioneService.getComboAnni(httpRequest.getSession());
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getComboAnni] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getComboGestore] BEGIN");
		GenericResponse<List<SelectVO>> response = dichiarazioneService.getComboGestore(httpRequest.getSession());
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getComboGestore] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboImpianti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idGestore) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getComboImpianti] BEGIN");
		GenericResponse<List<SelectVO>> response = dichiarazioneService.getComboImpianti(httpRequest.getSession(),
				idGestore);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getComboImpianti] END");
		return response;
	}
	
	public GenericResponse<String> getParametriFiltroApplicati(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, DichiarazioneParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getParametriFiltroApplicati] BEGIN");
        GenericResponse<String> response = dichiarazioneService.getParametriFiltroApplicati(httpRequest.getSession(),
                parametriRicerca);
        LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getParametriFiltroApplicati] END");
        return response;
    }

	@Override
	public GenericResponse<List<DichAnnualeBasicVO>> getListaDichiarazioni(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, DichiarazioneParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getListaDichiarazioni] BEGIN");
		GenericResponse<List<DichAnnualeBasicVO>> response = dichiarazioneService.getListaDichiarazioni(httpRequest.getSession(), parametriRicerca);
		LoggerUtil.debug(logger, "[ImpiantiControllerImpl::getListaDichiarazioni] END");
		return response;
	}

	public GenericResponse<DocumentoProtocollatoVO> downloadDichiarazioneAnnuale(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idDichiarazione) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::downloadDichiarazioneAnnuale] BEGIN");
		GenericResponse<DocumentoProtocollatoVO> response = dichiarazioneService.downloadDichiarazionePDF(httpRequest.getSession(), idDichiarazione);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::downloadDichiarazioneAnnuale] END");
		return response;
	}
	
	public GenericResponse<String> generaDichiarazioneAnnuale(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idDichiarazione) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::generaDichiarazioneAnnuale] BEGIN");
		GenericResponse<String> response = dichiarazioneService.generaDichiarazionePDF(httpRequest.getSession(), idDichiarazione);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::generaDichiarazioneAnnuale] END");
		return response;
	}

	@Override
	public GenericResponse<Boolean> existsDichiarazione(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, ExistsDichiarazioneVO existsDichiarazioneVO) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::generaDichiarazioneAnnuale] BEGIN");
		GenericResponse<Boolean> response = dichiarazioneService.existsDichiarazione(httpRequest.getSession(), existsDichiarazioneVO);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::generaDichiarazioneAnnuale] END");
		return response;
	}

	@Override
	public GenericResponse<DichAnnualeVO> getNuovaVersioneDichiarazione(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getNuovaVersioneDichiarazione] BEGIN");
		GenericResponse<DichAnnualeVO> response = dichiarazioneService.getNuovaVersioneDichiarazione(httpRequest.getSession(), idDichAnnuale);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getNuovaVersioneDichiarazione] END");
		return response;
	}

	@Override
	public GenericResponse<SoggettoMrVO> removeDichiarazioneSoggettoMr(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idDichAnnuale, Long idSoggettoMr) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::removeDichiarazioneSoggettoMr] BEGIN");
		GenericResponse<SoggettoMrVO> response = dichiarazioneService.removeDichiarazioneSoggettoMr(httpRequest.getSession(), idDichAnnuale, idSoggettoMr);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::removeDichiarazioneSoggettoMr] END");
		return response;
	}
	
	@Override
	public GenericResponse<DichAnnualeVO> insertBozzaDichAnnuale(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, DichAnnualeVO dichAnnualeVO) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::insertBozzaDichAnnuale] BEGIN");
		GenericResponse<DichAnnualeVO> response = dichiarazioneService.insertBozzaDichAnnuale(httpRequest.getSession(), dichAnnualeVO);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::insertBozzaDichAnnuale] END");
		return response;
	}

	@Override
	public GenericResponse<DichAnnualeVO> insertDichAnnuale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, DichAnnualeVO dichAnnualeVO) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::insertDichAnnuale] BEGIN");
		GenericResponse<DichAnnualeVO> response = dichiarazioneService.insertDichAnnuale(httpRequest.getSession(), dichAnnualeVO);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::insertDichAnnuale] END");
		return response;
	}
	
	@Override
	public GenericResponse<DichAnnualeVO> getDichiarazioneByIdDichAnnuale(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getDichiarazioneByIdDichAnnuale] BEGIN");
		GenericResponse<DichAnnualeVO> response = dichiarazioneService.getDichiarazione(httpRequest.getSession(), idDichAnnuale);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getDichiarazioneByIdDichAnnuale] END");
		return response;
	}

	@Override
	public GenericResponse<DichAnnualeVO> deleteDichiarazione(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::deleteDichiarazione] BEGIN");
		GenericResponse<DichAnnualeVO> response = dichiarazioneService.deleteDichiarazione(httpRequest.getSession(), idDichAnnuale);
		LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::deleteDichiarazione] END");
		return response;
	}
	
	@Override
    public GenericResponse<DichAnnualeVO> getDichiarazioneByIdGestoreIdImpiantoAnno(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest, 
            Long idGestore, Long idImpianto, Long anno) {
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getDichiarazioneByIdGestoreIdImpiantoAnno] BEGIN");
        GenericResponse<DichAnnualeVO> response = dichiarazioneService.getDichiarazioneByIdGestoreIdImpiantoAnno(httpRequest.getSession(), idGestore, idImpianto, anno);
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::getDichiarazioneByIdGestoreIdImpiantoAnno] END");
        return response;
    }

    @Override
    public GenericResponse<Boolean> allowDuplica(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, AllowDuplicaDichiarazioneVO allowDuplicaDichiarazioneVO) {
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::allowDuplica] BEGIN");
        GenericResponse<Boolean> response = dichiarazioneService.isDuplicaAllowed(httpRequest.getSession(), allowDuplicaDichiarazioneVO);
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::allowDuplica] END");
        return response;
    }

	@Override
	public GenericResponse<ReportVO> downloadReport(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, DichiarazioneParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::downloadReport] BEGIN");
        GenericResponse<ReportVO> response = dichiarazioneService.downloadReport(httpRequest.getSession(), parametriRicerca);
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::downloadReport] END");
        return response;
	}

	@Override
	public GenericResponse<DichAnnualeVO> deteteConferimenti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, Long idDichAnnuale, Long idRifiutoTariffa) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::deteteConferimenti] BEGIN");
        GenericResponse<DichAnnualeVO> response = dichiarazioneService.deteteConferimenti(httpRequest.getSession(), idDichAnnuale, idRifiutoTariffa);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::deteteConferimenti] END");
		return response;
	}
}
