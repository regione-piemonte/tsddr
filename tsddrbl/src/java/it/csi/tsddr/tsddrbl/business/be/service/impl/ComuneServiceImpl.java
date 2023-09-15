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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ComuneEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDComuneRepository;
import it.csi.tsddr.tsddrbl.business.be.service.ComuneService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class ComuneServiceImpl implements ComuneService {
	
	private static Logger logger = Logger.getLogger(ComuneServiceImpl.class);
	
	@Autowired
	private TsddrDComuneRepository comuneRepository;
	
	@Autowired
	private ComuneEntityMapper comuneEntityMapper;

	@Override
	public GenericResponse<List<SelectVO>> getComboComuni(Long idProvincia) {
		LoggerUtil.debug(logger, "[ComuniServiceImpl::getComboComuni] BEGIN");
		List<TsddrDComune> comuni = new ArrayList<>();
		if(idProvincia == null) {
			comuni = comuneRepository.findComuni(new Date());
		} else {
			comuni = comuneRepository.findComuniByIdProvincia(idProvincia, new Date());
		}
		GenericResponse<List<SelectVO>> response = GenericResponse.build(comuneEntityMapper.mapListEntityToListSelectVO(comuni));
		LoggerUtil.debug(logger, "[ComuniServiceImpl::getComboComuni] END");
		return response;
	}

}
