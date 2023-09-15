/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProfPK;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.UtenteProfiloMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProfiliRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRUtenteProfRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTUtenteRepository;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.business.be.service.UtenteProfiloService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.utenteprofilo.UtenteProfiloVO;

@Service
@Transactional
public class UtenteProfiloServiceImpl implements UtenteProfiloService {
	
	private static Logger logger = Logger.getLogger(UtenteProfiloServiceImpl.class);
	
	private static final String PROFILO_NOT_FOUND = "TsddrDProfilo non trovato con idProfilo = [%d]";
	
	private static final String DOUBLE_S_FORMAT = "%s, %s";
	
	@Autowired
	private AclUtil aclUtil;
	
	@Autowired
	private TsddrRUtenteProfRepository rUtenteProfRepository;
	
	@Autowired
	private TsddrTUtenteRepository tUtenteRepository;
	
	@Autowired
	private TsddrDProfiliRepository dProfiliRepository;
	
	@Autowired
	private UtenteProfiloMapper utenteProfiloMapper;
	
	@Autowired
	private CsiLogAuditService logService;
	
	@Autowired
	private MessaggioService messaggioService;

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLUtenteProfilo(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::getACLUtenteProfilo] BEGIN");
		FunzionalitaProfiloVO gestioneProfiloUtenteVO = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.AM_005);
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(gestioneProfiloUtenteVO);
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::getACLUtenteProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<UtenteProfiloVO>> getListaUtentiProfilo(HttpSession httpSession, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::getListaUtentiProfilo] BEGIN");
		Date now = new Date();
		Optional<TsddrDProfilo> profiloOpt = dProfiliRepository.findByIdProfilo(idProfilo, now);
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(PROFILO_NOT_FOUND, idProfilo));
		}
		List<UtenteProfiloVO> utenteProfiloVOList = rUtenteProfRepository.findUtentiByIdProfilo(idProfilo, now);
		
		logService.traceCsiLogAudit(httpSession, SessionUtil.getIdDatiSoggetto(httpSession), LogConstants.TSDDR_ADMIN_UTENTI_PROFILO,
				LogConstants.TSDDR_D_PROFILI_TSDDR_T_UTENTI, String.format(DOUBLE_S_FORMAT, idProfilo, utenteProfiloVOList.stream().map(vo -> String.valueOf(vo.getIdUtente())).collect(Collectors.joining(","))));
		
		GenericResponse<List<UtenteProfiloVO>> response = GenericResponse.build(utenteProfiloVOList);
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::getListaUtentiProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<UtenteProfiloVO> createUtenteProfilo(HttpSession httpSession, Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::createUtenteProfilo] BEGIN");
		Date now = new Date();
		Optional<TsddrTUtente> utenteOpt = tUtenteRepository.findByIdUtente(idUtente, now);
		if(!utenteOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTUtente non trovato con idUtente = [%d]", idUtente));
		}
		
		Optional<TsddrDProfilo> profiloOpt = dProfiliRepository.findByIdProfilo(idProfilo, now);
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(PROFILO_NOT_FOUND, idProfilo));
		}
		
		TsddrRUtenteProf rUtenteProf = new TsddrRUtenteProf();
		TsddrRUtenteProfPK rupId = new TsddrRUtenteProfPK();
		rupId.setIdProfilo(idProfilo);
		rupId.setIdUtente(idUtente);
		rUtenteProf.setId(rupId);
		rUtenteProf.setDataInizioValidita(now);
		rUtenteProf.setDataFineValidita(null);
		rUtenteProf.setIdUserInsert(idUtente);
		rUtenteProf.setDataInsert(now);
		rUtenteProf = rUtenteProfRepository.save(rUtenteProf);
		
		logService.traceCsiLogAudit(httpSession, SessionUtil.getIdDatiSoggetto(httpSession), LogConstants.TSDDR_ADMIN_NUOVO_UTENTE_PROFILO,
				LogConstants.TSDDR_D_PROFILI_TSDDR_T_UTENTI, String.format(DOUBLE_S_FORMAT, idProfilo, idUtente));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		GenericResponse<UtenteProfiloVO> response = GenericResponse.build(messaggio, utenteProfiloMapper.mapEntityToVO(rUtenteProf));
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::createUtenteProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<String> deleteUtenteProfilo(HttpSession httpSession, Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::deleteUtenteProfilo] BEGIN");
		Date now = new Date();
		Optional<TsddrRUtenteProf> rUtenteProfOpt = rUtenteProfRepository.findByIdUtenteAndIdProfilo(idUtente, idProfilo, now);
		if(!rUtenteProfOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRUtenteProf non trovato con idUtente = [%d] e idProfilo = [%d]", idUtente, idProfilo));
		}
		
		TsddrRUtenteProf rUtenteProf = rUtenteProfOpt.get();
		rUtenteProf.setDataFineValidita(now);
		rUtenteProf.setDataDelete(now);
		rUtenteProf.setIdUserDelete(SessionUtil.getIdUtente(httpSession));
		rUtenteProf = rUtenteProfRepository.save(rUtenteProf);
		
		logService.traceCsiLogAudit(httpSession, SessionUtil.getIdDatiSoggetto(httpSession), LogConstants.TSDDR_ADMIN_ELIMINATO_UTENTE_PROFILO,
				LogConstants.TSDDR_D_PROFILI_TSDDR_T_UTENTI, String.format(DOUBLE_S_FORMAT, idProfilo, idUtente));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P003.name());
		GenericResponse<String> response = GenericResponse.build(messaggio, HttpStatus.OK.toString());
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::deleteUtenteProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboNuovoUtente(Long idProfilo) {
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::getComboNuovoUtente] BEGIN");
		Date now = new Date();
		Optional<TsddrDProfilo> profiloOpt = dProfiliRepository.findByIdProfilo(idProfilo, now);
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(PROFILO_NOT_FOUND, idProfilo));
		}
		List<TsddrTUtente> utentiNonAssociati = rUtenteProfRepository.findUtentiByIdProfiloNot(idProfilo, now);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(utenteProfiloMapper.mapListEntityToListSelectVO(utentiNonAssociati));
		LoggerUtil.debug(logger, "[UtenteProfiloServiceImpl::getComboNuovoUtente] END");
		return response;
	}

}
