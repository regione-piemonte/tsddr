/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTPrevConsDettRepository;
import it.csi.tsddr.tsddrbl.business.be.service.PrevConsDettService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class PrevConsDettServiceImpl implements PrevConsDettService {
    
    private static Logger logger = Logger.getLogger(PrevConsDettServiceImpl.class);
    
    @Autowired
    private MessaggioServiceImpl messaggioService;
    
    @Autowired
    private TsddrTPrevConsDettRepository prevConsDettRepository;
    
    @Override
    public GenericResponse<MessaggioVO> deletePrevConsDett(HttpSession session, Long idPrevConsDett) {
        LoggerUtil.debug(logger, "[PrevConsDettServiceImpl::deletePrevConsDett] BEGIN");
        if (prevConsDettRepository.findByIdPrevConsDett(idPrevConsDett).isEmpty()){
        	throw new RecordNotFoundException(String.format("TsddrTPrevConsDett non trovato con id = [%d]", idPrevConsDett));
        }
        prevConsDettRepository.deleteById(idPrevConsDett);
        GenericResponse<MessaggioVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P007.name()));
        LoggerUtil.debug(logger, "[PrevConsDettServiceImpl::deletePrevConsDett] END");
        return response;
    }

}
