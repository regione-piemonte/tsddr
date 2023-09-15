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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoProfilo;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.TipoProfiloEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoProfiloRepository;
import it.csi.tsddr.tsddrbl.business.be.service.TipoProfiloService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class TipoProfiloServiceImpl implements TipoProfiloService {
	
	private static Logger logger = Logger.getLogger(TipoProfiloServiceImpl.class);
	
	@Autowired
	private TipoProfiloEntityMapper tipoProfiloEntityMapper;
	
	@Autowired
	private TsddrDTipoProfiloRepository tsddrDTipoProfiloRepository;

	@Override
	public GenericResponse<List<SelectVO>> getComboTipoProfilo() {
		LoggerUtil.debug(logger, "[TipoProfiloServiceImpl::getComboTipoProfilo] BEGIN");
		List<TsddrDTipoProfilo> tipiProfilo = tsddrDTipoProfiloRepository.getComboTipoProfilo(new Date());
		List<SelectVO> selectVO = tipoProfiloEntityMapper.mapListEntityToListSelectVO(tipiProfilo);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[TipoProfiloServiceImpl::getComboTipoProfilo] END");
		return response;
	}

}
