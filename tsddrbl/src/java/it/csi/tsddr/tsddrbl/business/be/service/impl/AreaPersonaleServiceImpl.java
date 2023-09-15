/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.AreaPersonaleUtenteMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.DatiSoggEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDatiSoggRepository;
import it.csi.tsddr.tsddrbl.business.be.service.AreaPersonaleService;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.ValidazioneService;
import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.util.EntityUtil;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.vo.areapersonale.AreaPersonaleUtenteVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class AreaPersonaleServiceImpl implements AreaPersonaleService {
	
	private static Logger logger = Logger.getLogger(AreaPersonaleServiceImpl.class);

	@Autowired 
	private TsddrTDatiSoggRepository tsddrTDatiSoggRepository;
	
	@Autowired
	private AreaPersonaleUtenteMapper areaPersonaleUtenteMapper;
	
	@Autowired
	private CsiLogAuditService csiLogAuditService;
	
	@Autowired
	private AclUtil aclUtil;
	
	@Autowired
	private MessaggioServiceImpl messaggiService;
	
	@Autowired
	private ValidazioneService validationService;
	
	@Autowired
	private DatiSoggEntityMapper datiSoggEntityMapper;
	
	@Override
	public GenericResponse<AreaPersonaleUtenteVO> getNomeCognomeUtenteCorrente(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getNomeCognomeUtenteCorrente] BEGIN");
		UserInfo userInfo = SessionUtil.getUserInfo(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		TsddrTDatiSogg datiSogg = this.getDatiSoggCorrente(userInfo);
		GenericResponse<AreaPersonaleUtenteVO> response = GenericResponse.build(areaPersonaleUtenteMapper.mapEntityToVO(datiSogg));
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_AREA_DATI_PERSONALI, LogConstants.TSDDR_T_DATI_SOGG, idDatiSogg);
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getNomeCognomeUtenteCorrente] END");
		return response;
	}
	
	@Override
	public GenericResponse<AreaPersonaleUtenteVO> getDatiUtenteCorrente(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getDatiUtenteCorrente] BEGIN");
		UserInfo userInfo = SessionUtil.getUserInfo(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		TsddrTDatiSogg datiSogg = this.getDatiSoggCorrente(userInfo);
		GenericResponse<AreaPersonaleUtenteVO> response = GenericResponse.build(areaPersonaleUtenteMapper.mapEntityToVO(datiSogg));
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_AREA_DATI_PERSONALI, LogConstants.TSDDR_T_DATI_SOGG, idDatiSogg);
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getDatiUtenteCorrente] END");
		return response;
	}

	@Override
	public GenericResponse<String> logLogout(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::logLogout] BEGIN");
		UserInfo userInfo = SessionUtil.getUserInfo(httpSession);
		Long currentIdProfilo = SessionUtil.getIdProfilo(httpSession);
		csiLogAuditService.traceCsiLogAudit(httpSession, userInfo.getCodFisc(), LogConstants.LOGOUT, LogConstants.TSDDR_D_PROFILI, String.valueOf(currentIdProfilo));
		httpSession.invalidate();
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::logLogout] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLFunzionalitaProfilo(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getACLFunzionalitaProfilo] BEGIN");
		FunzionalitaProfiloVO gestioneProfiloUtenteVO = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_01);
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(gestioneProfiloUtenteVO);
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getACLFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<String> verificaDatiObbligatoriAreaPersonale(AreaPersonaleUtenteVO areaPersonaleUtenteVO) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::verificaDatiObbligatoriAreaPersonale] BEGIN");
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatori(areaPersonaleUtenteVO);
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::verificaDatiObbligatoriAreaPersonale] END");
		return response;
	}
	
	private List<MessaggioVO> verificaDatiObbligatori(AreaPersonaleUtenteVO areaPersonaleUtenteVO) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::verificaDatiObbligatori] BEGIN");
		List<MessaggioVO> messaggiVO = new ArrayList<>();

		if (StringUtils.isBlank(areaPersonaleUtenteVO.getEmail())) {
			MessaggioVO messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
			messaggioVO.setCampo("email");
			messaggiVO.add(messaggioVO);
			
		} else {
			MessaggioVO messaggioVO = validationService.verificaFormatoEmail(areaPersonaleUtenteVO.getEmail());
			if(messaggioVO != null ) {
				messaggioVO.setCampo("email");
				messaggiVO.add(messaggioVO);
			}
		}
		
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::verificaDatiObbligatori] END");
		return messaggiVO;
	}

	@Override
	public GenericResponse<DatiSoggVO> updateAreaPersonaleUtente(HttpSession httpSession, AreaPersonaleUtenteVO areaPersonaleUtenteVO) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::updateAreaPersonaleUtente] BEGIN");
		String codiceFiscale = SessionUtil.getUserInfo(httpSession).getCodFisc();
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Date currentDate = new Date();
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatori(areaPersonaleUtenteVO);
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		Optional<TsddrTDatiSogg> datiSoggOpt = tsddrTDatiSoggRepository.findByCodFiscale(codiceFiscale);
		if(!datiSoggOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTDatiSogg non trovato con codFiscale = [%s]", codiceFiscale));
		}
		TsddrTDatiSogg datiSogg = datiSoggOpt.get();
		datiSogg.setEmail(areaPersonaleUtenteVO.getEmail());
		datiSogg.setTelefono(areaPersonaleUtenteVO.getTelefono());
		EntityUtil.setUpdated(datiSogg, idUtente, currentDate);
		datiSogg = tsddrTDatiSoggRepository.save(datiSogg);
		csiLogAuditService.traceCsiLogAudit(httpSession, String.valueOf(datiSogg.getIdDatiSogg()), LogConstants.TSDDR_MODIFICA_IN_AREA_DATI_PERSONALI, LogConstants.TSDDR_T_DATI_SOGG, String.valueOf(idDatiSogg));
		
		MessaggioVO messaggio = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<DatiSoggVO> response = GenericResponse.build(messaggio, datiSoggEntityMapper.mapEntityToVO(datiSogg));
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::updateAreaPersonaleUtente] END");
		return response;
	}
	
	@Override
	public TsddrTDatiSogg getDatiSoggCorrente(UserInfo userInfo) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getDatiSoggCorrente] BEGIN");
		Optional<TsddrTDatiSogg> datiSoggOpt = tsddrTDatiSoggRepository.findByCodFiscale(userInfo.getCodFisc());
		if(!datiSoggOpt.isPresent()) {			
			TsddrTDatiSogg datiSogg = new TsddrTDatiSogg();
			datiSogg.setCodFiscale(userInfo.getCodFisc());
			datiSogg.setCognome(userInfo.getCognome());
			datiSogg.setNome(userInfo.getNome());
			return datiSogg;
		}
		TsddrTDatiSogg datiSogg = datiSoggOpt.get();
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getDatiSoggCorrente] END");
		return datiSogg;
	}
	
}
