/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web.impl;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.csi.tsddr.tsddrbl.business.be.service.EerService;
import it.csi.tsddr.tsddrbl.business.be.web.EerController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class EerControllerImpl implements EerController {

private static Logger logger = Logger.getLogger(NatureGiuridicheControllerImpl.class);
    
    @Autowired
    private EerService eerService;

    @Override
    public GenericResponse<List<SelectVO>> getComboEer(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, String anno) {
        LoggerUtil.debug(logger, "[EerControllerImpl::getComboEer] BEGIN");
        GenericResponse<List<SelectVO>> response = null;
        if(anno == null){
            response = eerService.getComboEer(httpRequest.getSession());
        }else{
            response = eerService.getComboEerByDate(httpRequest.getSession(), anno);
        }
        LoggerUtil.debug(logger, "[EerControllerImpl::getComboEer] END");
        return response;
    }
    
}
