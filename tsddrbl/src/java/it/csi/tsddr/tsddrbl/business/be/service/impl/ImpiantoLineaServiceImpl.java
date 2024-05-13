/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ImpiantoLineeEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRImpiantoLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.service.ImpiantoLineaService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.impiantolinee.ImpiantoLineeVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class ImpiantoLineaServiceImpl implements ImpiantoLineaService {
    
    private static Logger logger = Logger.getLogger(ImpiantoLineaServiceImpl.class);
    
    @Autowired
    private TsddrRImpiantoLineaRepository impiantoLineaRepository;
    
    @Autowired
    private ImpiantoLineeEntityMapper impiantoLineeEntityMapper;

    @Override
    public GenericResponse<List<ImpiantoLineeVO>> getImpiantiLinee(Long idImpianto, Long idPrevCons) {
        LoggerUtil.debug(logger, "[ImpiantoLineaServiceImpl::getImpiantiLinee] BEGIN");
        List<TsddrRImpiantoLinea> impiantiLinee = new ArrayList<>();
        if(idPrevCons != null) {
            impiantiLinee = impiantoLineaRepository.findByIdImpiantoAndIdPrevCons(idImpianto, idPrevCons);
        } else {
//            impiantiLinee = impiantoLineaRepository.findByIdImpiantoAll(idImpianto);
            impiantiLinee = impiantoLineaRepository.findByIdImpiantoAllValid(idImpianto, new Date());
        }
        
        GenericResponse<List<ImpiantoLineeVO>> response = GenericResponse.build(impiantoLineeEntityMapper.mapListEntityToListVO(impiantiLinee));
        LoggerUtil.debug(logger, "[ImpiantoLineaServiceImpl::getListaImpianti] END");
        return response;
    }
    

}
