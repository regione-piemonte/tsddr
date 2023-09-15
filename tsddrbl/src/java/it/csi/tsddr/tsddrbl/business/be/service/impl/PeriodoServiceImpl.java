/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.mapper.entity.PeriodoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDPeriodoRepository;
import it.csi.tsddr.tsddrbl.business.be.service.PeriodoService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.PeriodoVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class PeriodoServiceImpl implements PeriodoService{
	
	private static Logger logger = Logger.getLogger(PeriodoServiceImpl.class);
	
	@Autowired
	private PeriodoEntityMapper periodoMapper;
	
	@Autowired
	private TsddrDPeriodoRepository periodoRepository;

	@Override
	public GenericResponse<List<PeriodoVO>> getListaPeriodi() {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getListaPeriodi] BEGIN");
		GenericResponse<List<PeriodoVO>> response = GenericResponse
				.build(periodoMapper.mapListEntityToListVO(periodoRepository.findAll()));
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getListaPeriodi] END");
		return response;
	}
}
