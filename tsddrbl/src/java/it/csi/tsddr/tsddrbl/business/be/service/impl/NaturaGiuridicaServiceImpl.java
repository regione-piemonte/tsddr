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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNaturaGiuridica;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.NaturaGiuridicaEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDNaturaGiuridicaRepository;
import it.csi.tsddr.tsddrbl.business.be.service.NaturaGiuridicaService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class NaturaGiuridicaServiceImpl implements NaturaGiuridicaService {
	
	private static Logger logger = Logger.getLogger(NaturaGiuridicaServiceImpl.class);
	
	@Autowired
	private TsddrDNaturaGiuridicaRepository naturaGiuridicaRepository;
	
	@Autowired
	private NaturaGiuridicaEntityMapper naturaGiuridicaEntityMapper;

	@Override
	public GenericResponse<List<SelectVO>> getComboNatura() {
		LoggerUtil.debug(logger, "[NaturaGiuridicaServiceImpl::getComboNatura] BEGIN");
		List<TsddrDNaturaGiuridica> nature = naturaGiuridicaRepository.findNatureGiuridiche(new Date());
		GenericResponse<List<SelectVO>> response = GenericResponse.build(naturaGiuridicaEntityMapper.mapListEntityToListSelectVO(nature));
		LoggerUtil.debug(logger, "[NaturaGiuridicaServiceImpl::getComboNatura] END");
		return response;
	}

}
