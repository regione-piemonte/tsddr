/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoImpianto;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.StatoImpiantoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDStatoImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.service.StatoImpiantoService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class StatoImpiantoServiceImpl implements StatoImpiantoService {
	
private static Logger logger = Logger.getLogger(StatoImpiantoServiceImpl.class);
	
	@Autowired
	private TsddrDStatoImpiantoRepository tsddrDStatoImpiantoRepository;
	
	@Autowired
	private StatoImpiantoEntityMapper statoImpiantoEntityMapper;

	@Override
	public GenericResponse<List<SelectVO>> getComboStatiImpianto() {
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboStatiImpianto] BEGIN");
		List<TsddrDStatoImpianto> statiImpianto = tsddrDStatoImpiantoRepository.findStatiImpianto(new Date());
		List<SelectVO> selectVO = statoImpiantoEntityMapper.mapListEntityToListSelectVO(statiImpianto);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboStatiImpianto] END");
		return response;
	}

}
