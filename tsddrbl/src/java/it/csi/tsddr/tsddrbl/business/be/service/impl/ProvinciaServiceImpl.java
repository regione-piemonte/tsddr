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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ProvinciaEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProvinciaRepository;
import it.csi.tsddr.tsddrbl.business.be.service.ProvinciaService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {
	
private static Logger logger = Logger.getLogger(ProvinciaServiceImpl.class);
	
	@Autowired
	private TsddrDProvinciaRepository provinciaRepository;
	
	@Autowired
	private ProvinciaEntityMapper provinciaEntityMapper;

	@Override
	public GenericResponse<List<SelectVO>> getComboProvince() {
		LoggerUtil.debug(logger, "[ProvinciaServiceImpl::getComboProvince] BEGIN");
		List<TsddrDProvincia> province = provinciaRepository.findProvince(new Date());
		GenericResponse<List<SelectVO>> response = GenericResponse.build(provinciaEntityMapper.mapListEntityToListSelectVO(province));
		LoggerUtil.debug(logger, "[ProvinciaServiceImpl::getComboProvince] END");
		return response;
	}

}
