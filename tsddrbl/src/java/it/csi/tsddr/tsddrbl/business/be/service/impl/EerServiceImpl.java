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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDEer;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.EerEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDEerRepository;
import it.csi.tsddr.tsddrbl.business.be.service.EerService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class EerServiceImpl implements EerService {
    
    private static Logger logger = Logger.getLogger(EerServiceImpl.class);
    
    @Autowired
    private TsddrDEerRepository eerRepository;
    
    @Autowired
    private EerEntityMapper eerEntityMapper;

    @Override
    public GenericResponse<List<SelectVO>> getComboEer(HttpSession httpSession) {
        LoggerUtil.debug(logger, "[EerServiceImpl::getComboEer] BEGIN");
        List<TsddrDEer> eers = eerRepository.findEer(new Date());
        GenericResponse<List<SelectVO>> response = GenericResponse.build(eerEntityMapper.mapListEntityToListSelectVO(eers));
        LoggerUtil.debug(logger, "[EerServiceImpl::getComboEer] END");
        return response;
    }

}
