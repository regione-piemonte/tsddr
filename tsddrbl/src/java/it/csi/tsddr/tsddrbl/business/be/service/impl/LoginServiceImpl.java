/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ProfiloEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProfiliRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDatiSoggRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTMenuRepository;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.LoginService;
import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.filter.iride.entity.Identita;
import it.csi.tsddr.tsddrbl.util.DbConstants;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.menu.MenuCardVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.userinfo.UserInfoVO;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private TsddrTDatiSoggRepository tsddrTDatiSoggRepository;
	
	@Autowired
	private TsddrDProfiliRepository tsddrDProfiliRepository;
	
	@Autowired
	private TsddrTMenuRepository tsddrTMenuRepository;
	
	@Autowired
	private CsiLogAuditService logService;
	
	@Autowired
	private ProfiloEntityMapper profiloEntityMapper;
	
	private static Logger logger = Logger.getLogger(LoginServiceImpl.class);

	public UserInfo populateUserInfo(Identita identita) {
		LoggerUtil.debug(logger, "[LoginServiceImpl::populateUserInfo] BEGIN");
		UserInfo userInfo = new UserInfo();
		userInfo.setNome(identita.getNome());
		userInfo.setCognome(identita.getCognome());
		userInfo.setEnte("--");
		userInfo.setRuolo("--");
		userInfo.setCodFisc(identita.getCodFiscale());
		userInfo.setLivAuth(identita.getLivelloAutenticazione());
		userInfo.setCommunity(identita.getIdProvider());
		
		String codiceFiscale = userInfo.getCodFisc();
		Optional<TsddrTDatiSogg> tsddrTDatiSoggOpt = tsddrTDatiSoggRepository.findByCodFiscale(codiceFiscale);
		
		if(tsddrTDatiSoggOpt.isPresent()) {
			TsddrTDatiSogg datiSogg = tsddrTDatiSoggOpt.get();
			userInfo.setIdDatiSogg(datiSogg.getIdDatiSogg());

			// se utente non ancora accreditato, non è presente il record su tabella utenti
			if (datiSogg.getUtente() != null) {
				userInfo.setIdUtente(datiSogg.getUtente().getIdUtente());
			}
		}
		LoggerUtil.debug(logger, "[LoginServiceImpl::populateUserInfo] END");
		return userInfo;
	}
	
	@Override
	public GenericResponse<UserInfoVO> getIdProfilo(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[LoginServiceImpl::getIdProfilo] BEGIN");
		UserInfo userInfo = SessionUtil.getUserInfo(httpSession);
		String codiceFiscale = SessionUtil.getCodiceFiscaleUtente(httpSession);
		
		List<TsddrDProfilo> profili = tsddrDProfiliRepository.findProfiliByCodiceFiscale(codiceFiscale, new Date());
		
		TsddrDProfilo profilo = CollectionUtils.isNotEmpty(profili) ? profili.get(0)
				: tsddrDProfiliRepository.findByIdProfilo(DbConstants.IDPROFILO_UTENTE_NON_ACCREDITATO)
						.orElseThrow(() -> new RecordNotFoundException(String.format("Profilo di default con idProfilo = [%d] non trovato.", DbConstants.IDPROFILO_UTENTE_NON_ACCREDITATO)));
		
		UserInfoVO userInfoVO = new UserInfoVO(profilo.getIdProfilo(), profilo.getDescProfilo(), userInfo.getNome(), userInfo.getCognome(), userInfo.getCodFisc());
		logService.traceCsiLogAudit(httpSession, codiceFiscale, LogConstants.LOGIN, LogConstants.TSDDR_D_PROFILI, String.valueOf(profilo.getIdProfilo()));
		GenericResponse<UserInfoVO> response = GenericResponse.build(userInfoVO);
		LoggerUtil.debug(logger, "[LoginServiceImpl::getIdProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<MenuCardVO>> getStrutturaMenueCard(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[LoginServiceImpl::getStrutturaMenueCard] BEGIN");
		String codiceFiscale = SessionUtil.getCodiceFiscaleUtente(httpSession);
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		List<MenuCardVO> menuCardVO = tsddrTMenuRepository.strutturaMenueCardByIdProfilo(idProfilo, new Date());
		Optional<TsddrTDatiSogg> tsddrTDatiSoggOpt = tsddrTDatiSoggRepository.findByCodFiscale(codiceFiscale);
		List<Long> idProfili = tsddrDProfiliRepository.findIdProfiliByCodiceFiscale(codiceFiscale, new Date());
		if (idProfili.isEmpty()) {
			idProfili.add(idProfilo);
		}
		logService.traceCsiLogAudit(httpSession, tsddrTDatiSoggOpt.isPresent() ? String.valueOf(tsddrTDatiSoggOpt.get().getIdDatiSogg()) : codiceFiscale, LogConstants.TSDDR_UTENTE_IDENTIFICAZIONE, LogConstants.TSDDR_D_PROFILI, StringUtils.join(idProfili, ","));
		GenericResponse<List<MenuCardVO>> response = GenericResponse.build(menuCardVO);
		LoggerUtil.debug(logger, "[LoginServiceImpl::getStrutturaMenueCard] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboProfiloLogin(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[LoginServiceImpl::getComboProfiloLogin] BEGIN");
		String codiceFiscale = SessionUtil.getCodiceFiscaleUtente(httpSession);
		List<TsddrDProfilo> profili = tsddrDProfiliRepository.findProfiliByCodiceFiscale(codiceFiscale, new Date());
		List<SelectVO> selectVO = profiloEntityMapper.mapListEntityToListSelectVO(profili);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[LoginServiceImpl::getComboProfiloLogin] END");
		return response;
	}

	@Override
	public GenericResponse<String> logCambioProfilo(HttpSession httpSession, Long idProfilo) {
		LoggerUtil.debug(logger, "[LoginServiceImpl::logCambioProfilo] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		UserInfo userInfo = SessionUtil.getUserInfo(httpSession);
		String codiceFiscale = userInfo.getCodFisc();
		List<Long> idProfili = tsddrDProfiliRepository.findIdProfiliByCodiceFiscale(codiceFiscale, new Date());
		if(!idProfili.contains(idProfilo)) {
			throw new BadRequestException(String.format("idProfilo = [%s] non presente in TsddrDProfili dell'utente", idProfilo));
		}
		logService.traceCsiLogAudit(httpSession, String.valueOf(idDatiSogg), LogConstants.TSDDR_UTENTE_CAMBIO_PROFILO, LogConstants.TSDDR_D_PROFILI, String.valueOf(idProfilo));
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[LoginServiceImpl::logCambioProfilo] END");
		return response;
	}

}
