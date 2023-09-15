/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNazione;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.NazioneEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDNazioneRepository;
import it.csi.tsddr.tsddrbl.business.be.service.NazioneService;
import it.csi.tsddr.tsddrbl.util.Constants;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
public class NazioneServiceImpl implements NazioneService {
	
private static Logger logger = Logger.getLogger(NazioneServiceImpl.class);
	
	@Autowired
	private TsddrDNazioneRepository nazioneRepository;
	
	@Autowired
	private NazioneEntityMapper nazioneEntityMapper;
	
	@SuppressWarnings("el-syntax")
	@Value("${id.istat.nazione.corrente:" + Constants.ID_ISTAT_NAZIONE_CORRENTE_DEFAULT + "}")
	private String idIstatNazioneCorrente;

	@Override
	public GenericResponse<List<SelectVO>> getComboNazioni(boolean nazioniEstere) {
		LoggerUtil.debug(logger, "[NazioneServiceImpl::getComboNazioni] BEGIN");
		List<TsddrDNazione> nazioni = null;
		if(!nazioniEstere) {
			nazioni = nazioneRepository.findNazioni(new Date());
		} else {
			nazioni = nazioneRepository.findNazioniNotEqualsIdIstatNazione(idIstatNazioneCorrente, new Date());
		}
		GenericResponse<List<SelectVO>> response = GenericResponse.build(nazioneEntityMapper.mapListEntityToListSelectVO(nazioni));
		LoggerUtil.debug(logger, "[NazioneServiceImpl::getComboNazioni] END");
		return response;
	}

}
