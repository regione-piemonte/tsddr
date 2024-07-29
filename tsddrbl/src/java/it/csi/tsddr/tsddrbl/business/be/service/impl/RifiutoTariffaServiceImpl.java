/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTRifiutoTariffa;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.RifiutoTariffaEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTRifiutoTariffaRepository;
import it.csi.tsddr.tsddrbl.business.be.service.RifiutoTariffaService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoTariffaVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class RifiutoTariffaServiceImpl implements RifiutoTariffaService {
	
	private static Logger logger = Logger.getLogger(RifiutoTariffaServiceImpl.class);
	
	@Autowired
	private TsddrTRifiutoTariffaRepository rifiutoTariffaRepository;
	
	@Autowired
	private RifiutoTariffaEntityMapper rifiutoTariffaEntityMapper;

	@Override
	public GenericResponse<List<SelectVO>> getComboRifiutoTariffa(HttpSession httpSession, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[RifiutoTariffaServiceImpl::getComboConferimenti] BEGIN");
		List<TsddrTRifiutoTariffa> rifiutiTariffe = null;
		if(idDichAnnuale != null) {
			rifiutiTariffe = rifiutoTariffaRepository.findRifiutiTariffeByIdDichAnnuale(idDichAnnuale, new Date());
		} else {
			rifiutiTariffe = rifiutoTariffaRepository.findRifiutiTariffe(new Date());
		}
		List<SelectVO> selectVO = rifiutoTariffaEntityMapper.mapListEntityToSelectVO(rifiutiTariffe);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[RifiutoTariffaServiceImpl::getComboConferimenti] END");
		return response;
	}
    @Override
    public GenericResponse<List<RifiutoTariffaVO>> getRifiutoTariffa(HttpSession session, Long idDichAnnuale) {
        LoggerUtil.debug(logger, "[RifiutoTariffaServiceImpl::getRifiutoTariffa] BEGIN");
        List<TsddrTRifiutoTariffa> rifiutiTariffe = null;
        if(idDichAnnuale != null) {
            rifiutiTariffe = rifiutoTariffaRepository.findRifiutiTariffeByIdDichAnnuale(idDichAnnuale, new Date());
        } else {
            rifiutiTariffe = rifiutoTariffaRepository.findRifiutiTariffe(new Date());
        }
        GenericResponse<List<RifiutoTariffaVO>> response = GenericResponse.build(rifiutoTariffaEntityMapper.mapListEntityToListVO(rifiutiTariffe));
        LoggerUtil.debug(logger, "[RifiutoTariffaServiceImpl::getRifiutoTariffa] END");
        return response;
    }

	
    @Override
    public GenericResponse<List<RifiutoTariffaVO>> getRifiutoTariffaByDate(HttpSession session, Long idDichAnnuale, String year) {
        LoggerUtil.debug(logger, "[RifiutoTariffaServiceImpl::getRifiutoTariffa] BEGIN");
        System.out.println(year);
        List<TsddrTRifiutoTariffa> rifiutiTariffe = null;
        if(idDichAnnuale != null) {
            rifiutiTariffe = rifiutoTariffaRepository.findRifiutiTariffeByIdDichAnnuale(idDichAnnuale, year);
        } else {
            rifiutiTariffe = rifiutoTariffaRepository.findRifiutiTariffe(year);
        }
        GenericResponse<List<RifiutoTariffaVO>> response = GenericResponse.build(rifiutoTariffaEntityMapper.mapListEntityToListVO(rifiutiTariffe));
        LoggerUtil.debug(logger, "[RifiutoTariffaServiceImpl::getRifiutoTariffa] END");
        return response;
    }
	
}