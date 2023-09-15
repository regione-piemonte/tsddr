/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTNotaInfo;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.NotaInfoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTNotaInfoRepository;
import it.csi.tsddr.tsddrbl.business.be.service.NotaInfoService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.notainfo.NotaInfoVO;

@Service
public class NotaInfoServiceImpl implements NotaInfoService {
	
	private static Logger logger = Logger.getLogger(NotaInfoServiceImpl.class);

	@Autowired
	private TsddrTNotaInfoRepository tsddrTNotaInfoRepository;
	
	@Autowired
	private NotaInfoEntityMapper entityMapper;
	
	@Override
	public NotaInfoVO getNotaInfoByCodNotaInfo(String codNotaInfo) {
		LoggerUtil.debug(logger, "[NotaInfoServiceImpl::getNotaInfoByCodNotaInfo] BEGIN");
		Optional<TsddrTNotaInfo> tsddrTNotaInfoOpt = tsddrTNotaInfoRepository.findByCodNotaInfo(codNotaInfo);
		if(!tsddrTNotaInfoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTNotaInfo non trovata con codNotaInfo = [%s]", codNotaInfo));
		}
		NotaInfoVO notaInfoVO = entityMapper.mapEntityToVO(tsddrTNotaInfoOpt.get());
		LoggerUtil.debug(logger, "[NotaInfoServiceImpl::getNotaInfoByCodNotaInfo] END");
		return notaInfoVO;
	}

}
