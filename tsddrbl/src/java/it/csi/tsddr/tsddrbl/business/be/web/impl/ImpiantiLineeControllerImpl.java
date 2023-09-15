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

import it.csi.tsddr.tsddrbl.business.be.service.ImpiantoLineaService;
import it.csi.tsddr.tsddrbl.business.be.web.ImpiantiLineeController;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.impiantolinee.ImpiantoLineeVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@RestController
public class ImpiantiLineeControllerImpl implements ImpiantiLineeController {

    private static Logger logger = Logger.getLogger(ImpiantiLineeControllerImpl.class);
    
    @Autowired
    private ImpiantoLineaService impiantoLineaService;
    
    @Override
    public GenericResponse<List<ImpiantoLineeVO>> getImpiantoLinee(SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest, Long idImpianto, Long idPrevCons) {
        LoggerUtil.debug(logger, "[ImpiantiLineeControllerImpl::getImpiantoLinee] BEGIN");
        GenericResponse<List<ImpiantoLineeVO>> response = impiantoLineaService.getImpiantiLinee(idImpianto, idPrevCons);
        LoggerUtil.debug(logger, "[ImpiantiLineeControllerImpl::getImpiantoLinee] END");
        return response;
    }

}
