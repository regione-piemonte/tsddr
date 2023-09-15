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

import it.csi.tsddr.tsddrbl.business.be.service.PrevConsService;
import it.csi.tsddr.tsddrbl.business.be.web.PrevConsController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.ExistsPrevConsVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsBasicVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class PrevConsControllerImpl implements PrevConsController {
    
    private static Logger logger = Logger.getLogger(PrevConsControllerImpl.class);
    
    @Autowired
    private PrevConsService prevConsService;

    @Override
    public GenericResponse<FunzionalitaProfiloVO> getACLPrevCons(SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getACLPrevCons] BEGIN");
        GenericResponse<FunzionalitaProfiloVO> response = prevConsService.getACLPrevCons(httpRequest.getSession(), idTipoDoc);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getACLPrevCons] END");
        return response;
    }
    
    @Override
    public GenericResponse<List<Long>> getComboAnni(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idTipoDoc, Long idStatoDichiarazione) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getComboAnni] BEGIN");
        GenericResponse<List<Long>> response = prevConsService.getComboAnni(httpRequest.getSession(), idTipoDoc, idStatoDichiarazione);
        return response;
    }
    
    @Override
    public GenericResponse<List<SelectVO>> getComboGestore(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getComboGestore] BEGIN");
        GenericResponse<List<SelectVO>> response = prevConsService.getComboGestore(httpRequest.getSession());
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getComboGestore] END");
        return response;
    }

    @Override
    public GenericResponse<List<SelectVO>> getComboImpianti(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idGestore) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getComboImpianti] BEGIN");
        GenericResponse<List<SelectVO>> response = prevConsService.getComboImpianti(httpRequest.getSession(),
                idGestore);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getComboImpianti] END");
        return response;
    }
    
    @Override
    public GenericResponse<Boolean> existsPrevCons(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, ExistsPrevConsVO existsPrevConsVO) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::existsPrevCons] BEGIN");
        GenericResponse<Boolean> response = prevConsService.existsPrevCons(httpRequest.getSession(), existsPrevConsVO);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::existsPrevCons] END");
        return response;
    }

    @Override
    public GenericResponse<PrevConsVO> insertBozzaPrevCons(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, PrevConsVO prevConsVO, Long idImpianto, Long idGestore) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::insertBozzaPrevCons] BEGIN");
        GenericResponse<PrevConsVO> response = prevConsService.insertBozzaPrevCons(httpRequest.getSession(), prevConsVO, idImpianto, idGestore);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::insertBozzaPrevCons] END");
        return response;
    }
    
    @Override
    public GenericResponse<PrevConsVO> insertPrevCons(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, PrevConsVO prevConsVO, Long idImpianto, Long idGestore) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::insertPrevCons] BEGIN");
        GenericResponse<PrevConsVO> response = prevConsService.insertPrevCons(httpRequest.getSession(), prevConsVO, idImpianto, idGestore);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::insertPrevCons] END");
        return response;
    }
    
    @Override
    public GenericResponse<MessaggioVO> deleteLinee(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idPrevConsLinee, Long idSezione) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::deleteLinee] BEGIN");
        GenericResponse<MessaggioVO> response = prevConsService.deleteLinee(httpRequest.getSession(), idPrevConsLinee, idSezione);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::deleteLinee] END");
        return response;
    }

    @Override
    public GenericResponse<DocumentoProtocollatoVO> downloadPrevConsPDF(SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idPrevCons, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::downloadPrevConsPDF] BEGIN");
        GenericResponse<DocumentoProtocollatoVO> response = prevConsService.downloadPrevConsPDF(httpRequest.getSession(), idPrevCons, idTipoDoc);
        LoggerUtil.debug(logger, "[DichiarazioniControllerImpl::downloadPrevCons] END");
        return response;
    }

    @Override
    public GenericResponse<PrevConsExtendedVO> getPrevCons(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idPrevCons, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getPrevCons] BEGIN");
        GenericResponse<PrevConsExtendedVO> response = prevConsService.getPrevCons(httpRequest.getSession(), idPrevCons, idTipoDoc);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getPrevCons] END");
        return response;
    }

    @Override
    public GenericResponse<PrevConsVO> deletePrevCons(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idPrevCons, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::deleteLinee] BEGIN");
        GenericResponse<PrevConsVO> response = prevConsService.deletePrevCons(httpRequest.getSession(), idPrevCons, idTipoDoc);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::deleteLinee] END");
        return response;
    }
   
    @Override
    public GenericResponse<List<PrevConsBasicVO>> getListaPrevCons(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, PrevConsParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getListaPrevCons] BEGIN");
        GenericResponse<List<PrevConsBasicVO>> response = prevConsService.getListaPrevCons(httpRequest.getSession(), parametriRicerca);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getListaPrevCons] END");
        return response;
    }

    @Override
    public GenericResponse<String> getParametriFiltroApplicati(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, PrevConsParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getParametriFiltroApplicati] BEGIN");
        GenericResponse<String> response = prevConsService.getParametriFiltroApplicati(httpRequest.getSession(),
                parametriRicerca);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getParametriFiltroApplicati] END");
        return response;
    }

    @Override
    public GenericResponse<List<PrevConsLineeExtendedVO>> getLineeRichiesta(SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest, Long idPrevCons) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getLineeRichiesta] BEGIN");
        GenericResponse<List<PrevConsLineeExtendedVO>> response = prevConsService.getLineeRichiesta(httpRequest.getSession(), idPrevCons);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::getLineeRichiesta] END");
        return response;
    }

	@Override
	public GenericResponse<ReportVO> downloadReport(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, PrevConsParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::downloadReport] BEGIN");
        GenericResponse<ReportVO> response = prevConsService.downloadReport(httpRequest.getSession(), parametriRicerca);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::downloadReport] END");
		return response;
	}

	@Override
	public GenericResponse<Boolean> isPercRecuperoVisible(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String codLinea, Long anno) {
		LoggerUtil.debug(logger, "[PrevConsControllerImpl::isPercRecuperoVisible] BEGIN");
        GenericResponse<Boolean> response = prevConsService.isPercRecuperoVisible(httpRequest.getSession(), codLinea, anno);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::isPercRecuperoVisible] END");
		return response;
	}

	@Override
	public GenericResponse<Boolean> isPercScartoVisible(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String codLinea, Long anno) {
		LoggerUtil.debug(logger, "[PrevConsControllerImpl::isPercScartoVisible] BEGIN");
        GenericResponse<Boolean> response = prevConsService.isPercScartoVisible(httpRequest.getSession(), codLinea, anno);
        LoggerUtil.debug(logger, "[PrevConsControllerImpl::isPercScartoVisible] END");
		return response;
	}


}
