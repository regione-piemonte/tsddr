/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTMessaggio;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.MessaggioEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTMessaggioRepository;
import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;

@Service
public class MessaggioServiceImpl implements MessaggioService {
	
	private static Logger logger = Logger.getLogger(MessaggioServiceImpl.class);
	
	@Autowired
	private TsddrTMessaggioRepository messaggioRepository;
	
	@Autowired
	private MessaggioEntityMapper entityMapper;

	@Override
	public MessaggioVO getMessaggioByCodMsg(String codMsg) {
		LoggerUtil.debug(logger, "[MessaggioServiceImpl::getMessaggioByCod] BEGIN");
		Optional<TsddrTMessaggio> messaggioOpt = messaggioRepository.findByCodMsg(codMsg);
		if(!messaggioOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTMessaggio non trovato con codMsg = [%s]", codMsg));
		}
		MessaggioVO messaggioVO = entityMapper.mapEntityToVO(messaggioOpt.get()); 
		LoggerUtil.debug(logger, "[MessaggioServiceImpl::getMessaggioByCod] END");
		return messaggioVO;
	}

}
