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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoImpianto;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.TipoImpiantoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.service.TipoImpiantoService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class TipoImpiantoServiceImpl implements TipoImpiantoService {
	
	private static Logger logger = Logger.getLogger(TipoImpiantoServiceImpl.class);
	
	@Autowired
	private TsddrDTipoImpiantoRepository tsddrDTipoImpiantoRepository;
	
	@Autowired
	private TipoImpiantoEntityMapper tipoImpiantoEntityMapper;

	@Override
	public GenericResponse<List<SelectVO>> getComboTipoImpianto() {
		LoggerUtil.debug(logger, "[TipoImpiantoServiceImpl::getComboTipoImpianto] BEGIN");
		List<TsddrDTipoImpianto> tipiImpianto = tsddrDTipoImpiantoRepository.findTipoImpianto(new Date());
		List<SelectVO> selectVO = tipoImpiantoEntityMapper.mapListEntityToListSelectVO(tipiImpianto);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[TipoImpiantoServiceImpl::getComboTipoImpianto] END");
		return response;
	}

}
