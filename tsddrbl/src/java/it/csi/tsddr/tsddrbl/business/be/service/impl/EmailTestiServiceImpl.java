/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrEmailTesti;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrEmailTestiRepository;
import it.csi.tsddr.tsddrbl.business.be.service.EmailTestiService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;

@Service
public class EmailTestiServiceImpl implements EmailTestiService {
	
	private static Logger logger = Logger.getLogger(EmailTestiServiceImpl.class);
	
	@Autowired
	private TsddrEmailTestiRepository emailTestiRepository;

	@Override
	public TsddrEmailTesti getEmailTestiById(Long idEmail) {
		LoggerUtil.debug(logger, "[EmailTestiServiceImpl::getEmailTestiById] BEGIN");
		
		Optional<TsddrEmailTesti> datiEmailOpt = emailTestiRepository.findByIdEmailAndDataDeleteIsNullAndIdUserDeleteIsNull(idEmail);
		if (datiEmailOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format("TsddrEmailTesti non trovato con idEmail = [%d]", idEmail));
		}
		
		LoggerUtil.debug(logger, "[EmailTestiServiceImpl::getEmailTestiById] BEGIN");
		return datiEmailOpt.get();
	}

}
