/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDFunzione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRFunzProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRFunzProfPK;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.FunzioneEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.RFunzProfEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDFunzioniRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProfiliRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRFunzProfRepository;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.FunzionalitaProfiloService;
import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class FunzionalitaProfiloServiceImpl implements FunzionalitaProfiloService {

	private static Logger logger = Logger.getLogger(FunzionalitaProfiloServiceImpl.class);
	
	@Autowired
	private CsiLogAuditService logService;
	
	@Autowired
	private FunzioneEntityMapper funzioneEntityMapper;
	
	@Autowired
	private RFunzProfEntityMapper rFunzProfEntityMapper;
	
	@Autowired
	private TsddrDProfiliRepository tsddrDProfiliRepository;
	
	@Autowired
	private TsddrRFunzProfRepository tsddrRFunzProfRepository;
	
	@Autowired
	private TsddrDFunzioniRepository tsddrDFunzioniRepository;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@Override
	public GenericResponse<List<SelectVO>> getComboFunzionalitaPerProfilo(Long idProfilo) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getComboFunzionalitaPerProfilo] BEGIN");
		List<TsddrDFunzione> funzioni  = tsddrRFunzProfRepository.findFunzioniByIdProfilo(idProfilo, new Date());
		List<SelectVO> selectVO = funzioneEntityMapper.mapListEntityToListSelectVO(funzioni);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getComboFunzionalitaPerProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<FunzionalitaProfiloVO>> getListaFunzionalitaProfilo(Long idProfilo, Long idFunzionalita) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getListaFunzionalitaProfilo] BEGIN");
		List<FunzionalitaProfiloVO> FunzionalitaProfiloVO;
		if(idFunzionalita != null) {
			FunzionalitaProfiloVO = List.of(tsddrRFunzProfRepository.findProfiloFunzionalitaByIdProfiloAndIdFunzione(idProfilo, idFunzionalita, new Date()));
		} else {
			FunzionalitaProfiloVO = tsddrRFunzProfRepository.findProfiloFunzionalitaByIdProfilo(idProfilo, new Date());
		}
		GenericResponse<List<FunzionalitaProfiloVO>> response = GenericResponse.build(FunzionalitaProfiloVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getListaFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLFunzionalitaProfilo(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getACLFunzionalitaProfilo] BEGIN");
		FunzionalitaProfiloVO gestioneProfiloUtenteVO = getFunzionalitaProfiloByCodFunzione(SessionUtil.getIdProfilo(httpSession), CodiceFunzione.AM_004);
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(gestioneProfiloUtenteVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getACLFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboNuovaFunzionalita(Long idProfilo) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getComboNuovaFunzionalita] BEGIN");
		List<TsddrDFunzione> funzioni = tsddrRFunzProfRepository.findFunzioniByIdProfiloNot(idProfilo, new Date());
		List<SelectVO> selectVO = funzioneEntityMapper.mapListEntityToListSelectVO(funzioni);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getComboNuovaFunzionalita] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> logConfProfiloFunzionalita(HttpSession httpSession, Long idProfilo, Long idFunzionalita) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::logConfProfiloFunzionalita] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		List<TsddrDProfilo> profili = tsddrDProfiliRepository.findProfili(new Date());
		if(profili.stream()
				.filter(profilo -> idProfilo.equals(profilo.getIdProfilo()))
				.findFirst()
				.isEmpty()) {
			throw new BadRequestException(String.format("idProfilo = [%s] non presente in TsddrDProfili", idProfilo));
		}
		List<FunzionalitaProfiloVO> profiliFunzionalitaVO = getListaFunzionalitaProfilo(idProfilo, idFunzionalita).getContent();
		List<Long> idFunzioni = new ArrayList<Long>();
		for(FunzionalitaProfiloVO FunzionalitaProfiloVO : profiliFunzionalitaVO) {
			idFunzioni.add(FunzionalitaProfiloVO.getIdFunzione());
		}
		logService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_ADMIN_CONFIGURAZIONE_PROFILI, LogConstants.TSDDR_D_PROFILI_TSDDR_R_FUNZ_PROF, StringUtils.join(idFunzioni, ","));
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::logConfProfiloFunzionalita] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> updateFunzionalitaProfilo(HttpSession httpSession, FunzionalitaProfiloVO FunzionalitaProfiloVO) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::updateFunzionalitaProfilo] BEGIN");
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrRFunzProf> rFunzProfOpt = tsddrRFunzProfRepository.findByIdIdProfiloAndIdIdFunzione(FunzionalitaProfiloVO.getIdProfilo(), FunzionalitaProfiloVO.getIdFunzione());
		if(!rFunzProfOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRFunzProf non trovato con idProfilo = [%d] e idFunzione = [%d]", FunzionalitaProfiloVO.getIdProfilo(), FunzionalitaProfiloVO.getIdFunzione()));
		}
		TsddrRFunzProf rFunzProf = rFunzProfOpt.get();
		rFunzProf.setIsDelete(FunzionalitaProfiloVO.getDelete());
		rFunzProf.setIsInsert(FunzionalitaProfiloVO.getInsert());
		rFunzProf.setIsRead(FunzionalitaProfiloVO.getRead());
		rFunzProf.setIsUpdate(FunzionalitaProfiloVO.getUpdate());
		rFunzProf.setDataInizioValidita(new Date());
		rFunzProf.setDataFineValidita(null);
		rFunzProf.setIdUserUpdate(idUtente);
		rFunzProf.setDataUpdate(new Date());
		rFunzProf = tsddrRFunzProfRepository.save(rFunzProf);
		logService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_ADMIN_MODIFICA_CONFIGURAZIONE_PROFILI, LogConstants.TSDDR_D_PROFILI_TSDDR_R_FUNZ_PROF, FunzionalitaProfiloVO.getIdProfilo() + " || " + FunzionalitaProfiloVO.getIdFunzione());
		FunzionalitaProfiloVO rFunzProfVO = rFunzProfEntityMapper.mapEntityToVO(rFunzProf);
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(messaggio, rFunzProfVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::updateFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> createFunzionalitaProfilo(HttpSession httpSession, FunzionalitaProfiloVO FunzionalitaProfiloVO) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::createFunzionalitaProfilo] BEGIN");
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(FunzionalitaProfiloVO.getIdProfilo());
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrDProfilo non trovato con idProfilo = [%d]", FunzionalitaProfiloVO.getIdProfilo()));
		} 
		
		Optional<TsddrDFunzione> funzioneOpt = tsddrDFunzioniRepository.findByIdFunzione(FunzionalitaProfiloVO.getIdFunzione());
		if (!funzioneOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrDFunzione non trovata con idFunzione = [%d]", FunzionalitaProfiloVO.getIdFunzione()));
		}
		TsddrRFunzProf rFunzProf = new TsddrRFunzProf();
		TsddrRFunzProfPK rFunzProfPK = new TsddrRFunzProfPK();
		rFunzProfPK.setIdFunzione(funzioneOpt.get().getIdFunzione());
		rFunzProfPK.setIdProfilo(profiloOpt.get().getIdProfilo());
		rFunzProf.setId(rFunzProfPK);
		rFunzProf.setIsDelete(FunzionalitaProfiloVO.getDelete());
		rFunzProf.setIsInsert(FunzionalitaProfiloVO.getInsert());
		rFunzProf.setIsRead(FunzionalitaProfiloVO.getRead());
		rFunzProf.setIsUpdate(FunzionalitaProfiloVO.getUpdate());
		rFunzProf.setDataInizioValidita(new Date());
		rFunzProf.setDataFineValidita(null);
		rFunzProf.setIdUserInsert(idUtente);
		rFunzProf.setDataInsert(new Date());
		rFunzProf = tsddrRFunzProfRepository.save(rFunzProf);
		logService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_ADMIN_INSERIMENTO_FUNZIONALITA_PROFILO, LogConstants.TSDDR_D_PROFILI_TSDDR_R_FUNZ_PROF, FunzionalitaProfiloVO.getIdProfilo() + " || " + FunzionalitaProfiloVO.getIdFunzione());
		FunzionalitaProfiloVO rFunzProfVO = rFunzProfEntityMapper.mapEntityToVO(rFunzProf);
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(messaggio, rFunzProfVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::createFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> removeFunzionalitaProfilo(HttpSession httpSession, Long idProfilo, Long idFunzionalita) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::removeFunzionalitaProfilo] BEGIN");
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrRFunzProf> rFunzProfOpt = tsddrRFunzProfRepository.findByIdIdProfiloAndIdIdFunzione(idProfilo, idFunzionalita);
		if(!rFunzProfOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRFunzProf non trovato con idProfilo = [%d] e idFunzione = [%d]", idProfilo, idFunzionalita));
		}
		TsddrRFunzProf rFunzProf = rFunzProfOpt.get();
		rFunzProf.setIdUserDelete(idUtente);
		rFunzProf.setDataDelete(new Date());
		rFunzProf.setDataFineValidita(new Date());
		rFunzProf = tsddrRFunzProfRepository.save(rFunzProf);
		logService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_ADMIN_ELIMINAZIONE_FUNZIONALITA_PROFILO, LogConstants.TSDDR_D_PROFILI_TSDDR_R_FUNZ_PROF, idProfilo + " || " + idFunzionalita);
		FunzionalitaProfiloVO rFunzProfVO = rFunzProfEntityMapper.mapEntityToVO(rFunzProf);
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P003.name());
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(messaggio, rFunzProfVO);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::removeFunzionalitaProfilo] END");
		return response;
	}

	@Override
	public FunzionalitaProfiloVO getFunzionalitaProfiloByCodFunzione(Long idProfilo, CodiceFunzione codFunzione) {
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getFunzionalitaProfiloByCodFunzione] BEGIN");
		FunzionalitaProfiloVO funzionalitaProfiloVO = tsddrRFunzProfRepository.findProfiloFunzionalitaByIdProfiloAndCodFunzione(idProfilo, codFunzione.name(), new Date());
		funzionalitaProfiloVO = funzionalitaProfiloVO != null ? funzionalitaProfiloVO : new FunzionalitaProfiloVO(idProfilo, codFunzione.getIdFunzione(), codFunzione.getDescFunzione(), false, false, false, false);
		LoggerUtil.debug(logger, "[FunzionalitaProfiloServiceImpl::getFunzionalitaProfiloByCodFunzione] END");
		return funzionalitaProfiloVO;
	}

}
