/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSedime;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.SedimeEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDSedimeRepository;
import it.csi.tsddr.tsddrbl.business.be.service.SedimeService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class SedimeServiceImpl implements SedimeService {
	
	private static Logger logger = Logger.getLogger(SedimeServiceImpl.class);
	
	@Autowired
	private TsddrDSedimeRepository sedimeRepository;
	
	@Autowired
	private SedimeEntityMapper sedimeEntityMapper;

	@Override
	public GenericResponse<List<SelectVO>> getComboSedime() {
		LoggerUtil.debug(logger, "[SedimeServiceImpl::getComboSedime] BEGIN");
		List<TsddrDSedime> sedimi = sedimeRepository.findSedimi(new Date());
		GenericResponse<List<SelectVO>> response = GenericResponse.build(sedimeEntityMapper.mapListEntityToListSelectVO(sedimi));
		LoggerUtil.debug(logger, "[SedimeServiceImpl::getComboSedime] END");
		return response;
	}

}
