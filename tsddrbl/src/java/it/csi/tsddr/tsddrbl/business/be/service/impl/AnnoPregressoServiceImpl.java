/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDAnnoPregresso;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.AnnoPregressoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDAnnoPregressoRepository;
import it.csi.tsddr.tsddrbl.business.be.service.AnnoPregressoService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class AnnoPregressoServiceImpl implements AnnoPregressoService {
    
    private static Logger logger = Logger.getLogger(AnnoPregressoServiceImpl.class);
    
    @Autowired
    private TsddrDAnnoPregressoRepository annoPregressoRepository;
    
    @Autowired
    private AnnoPregressoEntityMapper annoPregressoEntityMapper;

    @Override
    public GenericResponse<List<SelectVO>> getComboAnnoPregresso(HttpSession httpSession) {
        LoggerUtil.debug(logger, "[AnnoPregressoServiceImpl::getComboAnnoPregresso] BEGIN");
        List<TsddrDAnnoPregresso> annoPregressos = annoPregressoRepository.findAnniPregresso(new Date());
        GenericResponse<List<SelectVO>> response = GenericResponse.build(annoPregressoEntityMapper.mapListEntityToListSelectVO(annoPregressos));
        LoggerUtil.debug(logger, "[AnnoPregressoServiceImpl::getComboAnnoPregresso] END");
        return response;
    }

}
