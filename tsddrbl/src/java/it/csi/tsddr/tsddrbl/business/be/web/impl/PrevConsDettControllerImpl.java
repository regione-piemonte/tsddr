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

import it.csi.tsddr.tsddrbl.business.be.service.PrevConsDettService;
import it.csi.tsddr.tsddrbl.business.be.web.PrevConsDettController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class PrevConsDettControllerImpl implements PrevConsDettController {
    
    private static Logger logger = Logger.getLogger(PrevConsDettControllerImpl.class);
    
    @Autowired
    private PrevConsDettService prevConsDettService;

    @Override
    public GenericResponse<MessaggioVO> deletePrevConsDett(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idPrevConsDett) {
        LoggerUtil.debug(logger, "[PrevConsDettControllerImpl::deletePrevConsDett] BEGIN");
        GenericResponse<MessaggioVO> response = prevConsDettService.deletePrevConsDett(httpRequest.getSession(), idPrevConsDett);
        LoggerUtil.debug(logger, "[PrevConsDettControllerImpl::deletePrevConsDett] END");
        return response;
    }

}
