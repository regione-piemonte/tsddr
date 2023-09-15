/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfiloPK;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProfPK;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDomanda;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.GenericException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.DatiUtenteMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.GestoreEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ProfiloEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.RUtenteGestoreProfiloEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.UtenteEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.RepositoryUtil;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProfiliRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRUtenteGestoreProfiloRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRUtenteProfRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDatiSoggRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDomandaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTGestoreRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTUtenteRepository;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.business.be.service.UtenteService;
import it.csi.tsddr.tsddrbl.business.be.service.ValidazioneService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.enums.TipoProfilo;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.gestioneutente.UtenteVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.utente.DatiObbligatoriUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.DatiUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.InsertUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.UtenteParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.utentegestoreprofilo.UtenteGestoreProfiloVO;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {
	
	private static Logger logger = Logger.getLogger(UtenteServiceImpl.class);
	
	private static final String UTENTE_NOT_FOUND = "TsddrTUtente non trovato con idUtente = [%d]";

	@Autowired
	private GestoreEntityMapper gestoreEntityMapper;
	
	@Autowired
	private AclUtil aclUtil;
	
	@Autowired
	private TsddrDProfiliRepository tsddrDProfiliRepository;
	
	@Autowired
	private TsddrTUtenteRepository tsddrTUtenteRepository;
	
	@Autowired
	private TsddrTDatiSoggRepository tsddrTDatiSoggRepository;
	
	@Autowired
	private TsddrTGestoreRepository tsddrTGestoreRepository;
	
	@Autowired
	private TsddrTDomandaRepository tsddrTDomandaRepository;
	
	@Autowired
	private ProfiloEntityMapper profiloEntityMapper;
	
	@Autowired
	private UtenteEntityMapper utenteEntityMapper;
	
	@Autowired
	private RUtenteGestoreProfiloEntityMapper rutenteGestoreProfiloEntityMapper;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@Autowired
	private CsiLogAuditService csiLogAuditService;
	
	@Autowired
	private ValidazioneService validazioneService; 
	
	@Autowired
	private DatiUtenteMapper datiUtenteMapper;
	
	@Autowired
	private TsddrRUtenteGestoreProfiloRepository tsddrRUtenteGestoreProfiloRepository;
	
	@Autowired
	private TsddrRUtenteProfRepository tsddrRUtenteProfRepository;
	
	@PersistenceContext 
    private EntityManager entityManager;
	
	@Override
	public GenericResponse<String> verificaParametriRicerca(UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaParametriRicerca] BEGIN");
		if(!areParametriRicercaValid(utenteParametriRicercaVO)) {
			throw new BadRequestException("Nessun parametro valorizzato");
		}
		
		if(utenteParametriRicercaVO.getCodiceFiscale() != null && !utenteParametriRicercaVO.getCodiceFiscale().isBlank() && !utenteParametriRicercaVO.getCodiceFiscale().isEmpty()) {
			MessaggioVO messaggioVO = validazioneService.verificaFormatoCodiceFiscale(utenteParametriRicercaVO.getCodiceFiscale());
			if(messaggioVO != null) {
				throw new FunctionalException(String.format("Codice fiscale = [%s] non valido", utenteParametriRicercaVO.getCodiceFiscale()), messaggioVO);
			}
		}
		
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaParametriRicerca] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<DatiUtenteVO>> getGrigliaUtenti(HttpSession httpSession, UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getGrigliaUtenti] BEGIN");
		this.verificaParametriRicerca(utenteParametriRicercaVO);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		List<TsddrTUtente> utenti = this.getUtenti(utenteParametriRicercaVO);
		if(utenti.isEmpty()) {
			MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.A002.name());
			throw new FunctionalException(String.format("Nessun utente trovato con i parametri di ricerca inseriti"), messaggioVO);
		}
		
		List<DatiUtenteVO> datiUtentiVO = datiUtenteMapper.mapListEntityToListVO(utenti);
		Date currentDate = new Date();
		for(DatiUtenteVO datiUtenteVO : datiUtentiVO) {
			if(datiUtenteVO.getDataInizioValidita().compareTo(currentDate) > 0  || 
					(datiUtenteVO.getDataFineValidita() != null && datiUtenteVO.getDataFineValidita().compareTo(currentDate) < 0)) {
				datiUtenteVO.setDeletable(true);
			}
		}
		List<Long> iUtenteVisualizzati = utenti
											.stream()
											.map(TsddrTUtente::getIdUtente)
											.collect(Collectors.toList());
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_RICERCA_UTENTE, LogConstants.TSDDR_T_UTENTI, StringUtils.join(iUtenteVisualizzati, ","));
		GenericResponse<List<DatiUtenteVO>> response = GenericResponse.build(datiUtentiVO);
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getGrigliaUtenti] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> getParametriFiltroApplicati(UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getParametriFiltroApplicati] BEGIN");
		this.verificaParametriRicerca(utenteParametriRicercaVO);
		StringBuilder parametriFiltroBuilder = new StringBuilder();
		if(utenteParametriRicercaVO.getCodiceFiscale() != null && !utenteParametriRicercaVO.getCodiceFiscale().isBlank() && !utenteParametriRicercaVO.getCodiceFiscale().isEmpty()) {
			parametriFiltroBuilder.append("CodiceFiscale = \"" + utenteParametriRicercaVO.getCodiceFiscale() + "\" ");
		}
		if(utenteParametriRicercaVO.getCognome() != null && !utenteParametriRicercaVO.getCognome().isBlank() && !utenteParametriRicercaVO.getCognome().isEmpty()) {
			parametriFiltroBuilder.append("Cognome = \"" + utenteParametriRicercaVO.getCognome() + "\" ");
		}
		
		if(utenteParametriRicercaVO.getNome() != null && !utenteParametriRicercaVO.getNome().isBlank() && !utenteParametriRicercaVO.getNome().isEmpty()) {
			parametriFiltroBuilder.append("Nome = \"" + utenteParametriRicercaVO.getNome() + "\" ");
		}
		
		if(utenteParametriRicercaVO.getIdProfilo() != null) {
			Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(utenteParametriRicercaVO.getIdProfilo(), new Date());
			if(profiloOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDProfilo trovato con idProfilo = [%d]", utenteParametriRicercaVO.getIdProfilo()));
			}
			parametriFiltroBuilder.append("Profilo = \"" + profiloOpt.get().getDescProfilo() + "\" ");
		}
		
		if(utenteParametriRicercaVO.getIdGestore() != null) {
			Optional<TsddrTGestore> gestoreOpt = tsddrTGestoreRepository.findByIdGestore(utenteParametriRicercaVO.getIdGestore(), new Date());
			if(gestoreOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrTGestore trovato con idGestore = [%d]", utenteParametriRicercaVO.getIdGestore()));
			}
			parametriFiltroBuilder.append("Gestore = \"" + gestoreOpt.get().getRagSociale() + "\" ");
		}
		GenericResponse<String> response = GenericResponse.build(parametriFiltroBuilder.toString());
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getParametriFiltroApplicati] END");
		return response;
	}
	
	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLUtente(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getACLUtente] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.AM_002));
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getACLUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<DatiUtenteVO> getDatiUtente(HttpSession httpSession, Long idUtente) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getDatiUtente] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrTUtente> utenteOpt = tsddrTUtenteRepository.findByIdUtente(idUtente);
		if(!utenteOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(UTENTE_NOT_FOUND, idUtente));
		}
		DatiUtenteVO datiUtenteVO = datiUtenteMapper.mapEntityToVO(utenteOpt.get());
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_RICERCA_UTENTE, LogConstants.TSDDR_T_UTENTI, String.valueOf(idUtente));
		GenericResponse<DatiUtenteVO> response = GenericResponse.build(datiUtenteVO);
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getDatiUtente] END");
		return response;
	}

	@Override
	public GenericResponse<List<UtenteGestoreProfiloVO>> getDatiUtenteGestori(Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getDatiUtenteGestori] BEGIN");
		Date currentDate = new Date();
		if(idProfilo == null) {
			List<TsddrDProfilo> profili = tsddrDProfiliRepository.findProfiliByIdUtente(idUtente, currentDate);
			if(profili.isEmpty()) {
				throw new RecordNotFoundException(String.format("Nessun TsddrDProfilo associato a idUtente = [%d]", idUtente));
			}
			idProfilo = profili.get(0).getIdProfilo();
		}
		
		List<Long> userProfilesId = tsddrDProfiliRepository.findIdProfiliByIdUtente(idUtente, currentDate);
		if(!userProfilesId.contains(idProfilo)) {
			throw new RecordNotFoundException(String.format("TsddrDProfilo idProfilo = [%d] non trovato per l'idUtente = [%d]", idProfilo, idUtente));
		}
		
		List<TsddrRUtenteGestoreProfilo> utentiGestoriProfili = tsddrRUtenteGestoreProfiloRepository.findByIdUtenteIdProfilo(idUtente, idProfilo, currentDate);
		List<UtenteGestoreProfiloVO> utentiGestoriProfiliVO = rutenteGestoreProfiloEntityMapper.mapListEntityToListVO(utentiGestoriProfili);
		GenericResponse<List<UtenteGestoreProfiloVO>> response = GenericResponse.build(utentiGestoriProfiliVO);
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getDatiUtenteGestori] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboProfiliUtente(Long idUtente) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getComboProfiliUtente] BEGIN");
		List<TsddrDProfilo> profili = tsddrDProfiliRepository.findProfiliByIdUtente(idUtente, new Date());
		GenericResponse<List<SelectVO>> response = GenericResponse.build(profiloEntityMapper.mapListEntityToListSelectVO(profili));
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getComboProfiliUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<DatiUtenteVO> updateUtente(HttpSession httpSession, DatiUtenteVO datiUtenteVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::updateUtente] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		MessaggioVO errorMsg = null;
		
		// verifica dati obbligatori
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatori(datiUtenteMapper.mapVOToDatiObbligatoriVO(datiUtenteVO), true, false);
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}

		Optional<TsddrTUtente> utenteOpt = tsddrTUtenteRepository.findByIdUtente(datiUtenteVO.getIdUtente());
		
		if(!utenteOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(UTENTE_NOT_FOUND, datiUtenteVO.getIdUtente()));
		}
		
		if(datiUtenteVO.getDataInizioValidita() != null && datiUtenteVO.getDataFineValidita() != null) {
			// verifica date
			errorMsg = validazioneService.verificaValiditaDate(datiUtenteVO.getDataInizioValidita(), datiUtenteVO.getDataFineValidita());
			
			if (errorMsg != null) {
				throw new FunctionalException(errorMsg);
			}
		}
		
		TsddrTUtente utente = utenteOpt.get();
		TsddrTDatiSogg datiSogg = utente.getDatiSogg();
		
		List<TsddrDProfilo> profiliUtente = tsddrDProfiliRepository.findProfiliByIdUtente(utente.getIdUtente(), new Date());
		if(profiliUtente.stream().anyMatch(p -> p.getTipoProfilo().getIdTipoProfilo() == TipoProfilo.BACK_OFFICE.getId())) {
			datiSogg.setCognome(datiUtenteVO.getCognome());
			datiSogg.setNome(datiUtenteVO.getNome());
			
			// verifica cf
			validazioneService.verificaCodiceFiscale(datiUtenteVO.getCodiceFiscale());
			datiSogg.setCodFiscale(datiUtenteVO.getCodiceFiscale().toUpperCase());
		}
		
		datiSogg.setTelefono(datiUtenteVO.getTelefono());
		
		// verifica email
		validazioneService.verificaEmail(datiUtenteVO.getMail());
		datiSogg.setEmail(datiUtenteVO.getMail());
		
		datiSogg.setDataUpdate(new Date());
		datiSogg.setIdUserUpdate(idUtenteSessione);
		
		if (datiUtenteVO.getDataInizioValidita() != null) {
			utente.setDataInizioValidita(datiUtenteVO.getDataInizioValidita());
		} else {
			utente.setDataInizioValidita(new Date());
		}

		if (datiUtenteVO.getDataFineValidita() != null) {
			utente.setDataFineValidita(datiUtenteVO.getDataFineValidita());
		} else {
			utente.setDataFineValidita(null);
		}
		
		datiSogg = tsddrTDatiSoggRepository.save(datiSogg);
		utente.setDatiSogg(datiSogg);
		utente = tsddrTUtenteRepository.save(utente);
		DatiUtenteVO datiUtenteVOResponse = datiUtenteMapper.mapEntityToVO(utente);
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_MODIFICA_DATI_UTENTE, LogConstants.TSDDR_T_UTENTI, String.valueOf(datiUtenteVO.getIdUtente()));
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<DatiUtenteVO> response = GenericResponse.build(messaggioVO, datiUtenteVOResponse);
		LoggerUtil.debug(logger, "[UtenteServiceImpl::updateUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> removeUtente(HttpSession httpSession, Long idUtente) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::removeUtente] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Date currentDate = new Date();
		Optional<TsddrTUtente> utenteOpt = tsddrTUtenteRepository.findByIdUtente(idUtente, currentDate);
		if(!utenteOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(UTENTE_NOT_FOUND, idUtente));
		}
		List<TsddrRUtenteGestoreProfilo> utentiGestoriProfili = tsddrRUtenteGestoreProfiloRepository.findByIdUtente(idUtente, currentDate);
		List<TsddrRUtenteProf> utentiProf = tsddrRUtenteProfRepository.findByIdUtente(idUtente, currentDate);
		
		TsddrTUtente utente = utenteOpt.get();
		
		TsddrTDatiSogg datiSogg = utente.getDatiSogg();
		datiSogg.setDataDelete(currentDate);
		datiSogg.setIdUserDelete(idUtenteSessione);
		datiSogg = tsddrTDatiSoggRepository.save(datiSogg);
		
		utente.setDatiSogg(datiSogg);
		utente.setDataFineValidita(currentDate);
		utente.setDataDelete(currentDate);
		utente.setIdUserDelete(idUtenteSessione);
		tsddrTUtenteRepository.save(utente);
		
		for(TsddrRUtenteGestoreProfilo utenteGestoreProfilo : utentiGestoriProfili) {
			utenteGestoreProfilo.setDataFineValidita(currentDate);
			utenteGestoreProfilo.setDataDelete(currentDate);
			utenteGestoreProfilo.setIdUserDelete(idUtenteSessione);
			tsddrRUtenteGestoreProfiloRepository.save(utenteGestoreProfilo);
		}
							
		for(TsddrRUtenteProf utenteProf : utentiProf) {
			utenteProf.setDataFineValidita(currentDate);
			utenteProf.setDataDelete(currentDate);
			utenteProf.setIdUserDelete(idUtenteSessione);
			tsddrRUtenteProfRepository.save(utenteProf);
		}
		
		GenericResponse<String> response = GenericResponse.ok();
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_ELIMINATO_UN_UTENTE, LogConstants.TSDDR_T_UTENTI, String.valueOf(idUtente));
		LoggerUtil.debug(logger, "[UtenteServiceImpl::removeUtente] END");
		return response;
	}

	@Override
	public GenericResponse<String> verificaUtente(String codiceFiscale) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaUtente] BEGIN");
		MessaggioVO messaggioVO = this.verificaUtenteByCodiceFiscale(codiceFiscale);
		if(messaggioVO != null) {
			throw new FunctionalException(String.format("Utente con codiceFiscale = [%s] gia' presente", codiceFiscale), messaggioVO);
		}
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<UtenteVO> createUtente(HttpSession httpSession, InsertUtenteVO insertUtenteVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::createUtente] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		
		// verifica dati obbligatori
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatori(datiUtenteMapper.mapVOToDatiObbligatoriVO(insertUtenteVO), false, true);
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		
		// verifica email
		validazioneService.verificaEmail(insertUtenteVO.getMail());
		// verifica cf
		validazioneService.verificaCodiceFiscale(insertUtenteVO.getCodiceFiscale());
		// verifica utente già esisente
		this.verificaUtente(insertUtenteVO.getCodiceFiscale());
		
		Date currentDate = new Date();
		
		TsddrTDatiSogg datiSogg = new TsddrTDatiSogg();
		datiSogg.setCodFiscale(insertUtenteVO.getCodiceFiscale().toUpperCase());
		datiSogg.setCognome(insertUtenteVO.getCognome());
		datiSogg.setNome(insertUtenteVO.getNome());
		datiSogg.setTelefono(insertUtenteVO.getTelefono());
		datiSogg.setEmail(insertUtenteVO.getMail());
		datiSogg.setDataInsert(currentDate);
		datiSogg.setIdUserInsert(idUtenteSessione);
		datiSogg = tsddrTDatiSoggRepository.save(datiSogg);
		
		TsddrTUtente utente = new TsddrTUtente();
		utente.setDatiSogg(datiSogg);
		utente.setDataInizioValidita(currentDate);
		utente.setDataFineValidita(null);
		utente.setDataInsert(currentDate);
		utente.setIdUserInsert(idUtenteSessione);
		utente = tsddrTUtenteRepository.save(utente);
		
		TsddrRUtenteProf rUtenteProf = new TsddrRUtenteProf();
		Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(insertUtenteVO.getIdProfilo(), currentDate);
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrDProfilo non trovato con idProfilo = [%d]", insertUtenteVO.getIdProfilo()));
		}
		
		TsddrRUtenteProfPK rUtenteProfPK = new TsddrRUtenteProfPK();
		rUtenteProfPK.setIdProfilo(profiloOpt.get().getIdProfilo());
		rUtenteProfPK.setIdUtente(utente.getIdUtente());
		
		rUtenteProf.setId(rUtenteProfPK);
		rUtenteProf.setDataInizioValidita(currentDate);
		rUtenteProf.setDataFineValidita(null);
		rUtenteProf.setDataInsert(currentDate);
		rUtenteProf.setIdUserInsert(idUtenteSessione);
		rUtenteProf = tsddrRUtenteProfRepository.save(rUtenteProf);
		
		if (insertUtenteVO.getIdGestore() != null) {
			Optional<TsddrTGestore> gestoreOpt = tsddrTGestoreRepository.findByIdGestore(insertUtenteVO.getIdGestore(), currentDate);
			if(!gestoreOpt.isPresent()) {
				throw new RecordNotFoundException(String.format("TsddrTGestore non trovato con idGestore = [%d]", insertUtenteVO.getIdGestore()));
			}
			
			TsddrRUtenteGestoreProfiloPK rUtenteGestoreProfiloPK = new TsddrRUtenteGestoreProfiloPK();
			rUtenteGestoreProfiloPK.setIdUtente(utente.getIdUtente());
			rUtenteGestoreProfiloPK.setIdGestore(gestoreOpt.get().getIdGestore());
			rUtenteGestoreProfiloPK.setIdProfilo(profiloOpt.get().getIdProfilo());
			
			TsddrRUtenteGestoreProfilo rUtenteGestoreProfilo = new TsddrRUtenteGestoreProfilo();
			rUtenteGestoreProfilo.setId(rUtenteGestoreProfiloPK);
			rUtenteGestoreProfilo.setUtente(utente);
			rUtenteGestoreProfilo.setGestore(gestoreOpt.get());
			rUtenteGestoreProfilo.setProfilo(profiloOpt.get());
			rUtenteGestoreProfilo.setDataInizioValidita(currentDate);
			rUtenteGestoreProfilo.setDataFineValidita(null);
			rUtenteGestoreProfilo.setDataInsert(currentDate);
			rUtenteGestoreProfilo.setIdUserInsert(idUtenteSessione);
			rUtenteGestoreProfilo = tsddrRUtenteGestoreProfiloRepository.save(rUtenteGestoreProfilo);
		}
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_INSERIMENTO_UTENTE, LogConstants.TSDDR_T_UTENTI, String.valueOf(utente.getIdUtente()));
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		GenericResponse<UtenteVO> response = GenericResponse.build(messaggioVO, utenteEntityMapper.mapEntityToVO(utente));
		LoggerUtil.debug(logger, "[UtenteServiceImpl::createUtente] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboGrigliaGestori(HttpSession httpSession, Long idUtente, Long idProfilo) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getComboGrigliaGestori] BEGIN");
		List<TsddrTGestore> gestori = tsddrTGestoreRepository.getComboGrigliaGestore(idUtente, idProfilo, new Date());
		List<SelectVO> gestoriSelectVO = gestoreEntityMapper.mapListEntityToListSelectVO(gestori);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(gestoriSelectVO);
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getComboGrigliaGestori] END");
		return response;
	}

	@Override
	public GenericResponse<UtenteGestoreProfiloVO> createUtenteGestoreProfilo(
			HttpSession httpSession, UtenteGestoreProfiloVO utenteGestoreProfiloVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::createUtenteGestoreProfilo] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Date currentDate = new Date();
		
		if(utenteGestoreProfiloVO.getDataInizioValidita() == null) {
			utenteGestoreProfiloVO.setDataInizioValidita(currentDate);
		}
		if(utenteGestoreProfiloVO.getDataFineValidita() != null) {
			validazioneService.verificaValiditaDate(utenteGestoreProfiloVO.getDataInizioValidita(), utenteGestoreProfiloVO.getDataFineValidita());
		}
		
		Optional<TsddrTUtente> utenteOpt = tsddrTUtenteRepository.findByIdUtente(utenteGestoreProfiloVO.getIdUtente());
		if(!utenteOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(UTENTE_NOT_FOUND, utenteGestoreProfiloVO.getIdUtente()));
		}
		
		List<TsddrDProfilo> profili = tsddrDProfiliRepository.findProfiliByIdUtente(utenteGestoreProfiloVO.getIdUtente(), currentDate);
		if (profili.isEmpty()) {
			throw new RecordNotFoundException(String.format("Nessun TsddrDProfilo associato a idUtente = [%d]", utenteGestoreProfiloVO.getIdUtente()));
		}
		
		Optional<TsddrTGestore> gestoreOpt = tsddrTGestoreRepository.findByIdGestore(utenteGestoreProfiloVO.getIdGestore(), currentDate);
		if(!gestoreOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTGestore non trovato con idGestore = [%d]", utenteGestoreProfiloVO.getIdGestore()));
		}
		
		Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(utenteGestoreProfiloVO.getIdProfilo(),  currentDate);
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrDProfilo non trovato con idProfilo = [%d]", utenteGestoreProfiloVO.getIdProfilo()));
		}
		
		TsddrRUtenteGestoreProfiloPK tsddrRUtenteGestoreProfiloPK = new TsddrRUtenteGestoreProfiloPK();
		tsddrRUtenteGestoreProfiloPK.setIdUtente(utenteGestoreProfiloVO.getIdUtente());
		tsddrRUtenteGestoreProfiloPK.setIdGestore(utenteGestoreProfiloVO.getIdGestore());
		tsddrRUtenteGestoreProfiloPK.setIdProfilo(utenteGestoreProfiloVO.getIdProfilo());
		
		TsddrRUtenteGestoreProfilo rUtenteGestoreProfilo = new TsddrRUtenteGestoreProfilo();
		rUtenteGestoreProfilo.setId(tsddrRUtenteGestoreProfiloPK);
		rUtenteGestoreProfilo.setUtente(utenteOpt.get());
		rUtenteGestoreProfilo.setGestore(gestoreOpt.get());
		rUtenteGestoreProfilo.setProfilo(profiloOpt.get());
		rUtenteGestoreProfilo.setDataInizioValidita(utenteGestoreProfiloVO.getDataInizioValidita());
		rUtenteGestoreProfilo.setDataFineValidita(utenteGestoreProfiloVO.getDataFineValidita());
		rUtenteGestoreProfilo.setDataInsert(currentDate);
		rUtenteGestoreProfilo.setIdUserInsert(idUtenteSessione);
		rUtenteGestoreProfilo = tsddrRUtenteGestoreProfiloRepository.save(rUtenteGestoreProfilo);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_INSERISCI_LEGAME_GESTORE_UTENTE, LogConstants.TSDDR_T_UTENTI_TSDDR_T_GESTORI, ("" + utenteGestoreProfiloVO.getIdUtente() + " ," + utenteGestoreProfiloVO.getIdGestore()));
		
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		GenericResponse<UtenteGestoreProfiloVO> response = GenericResponse.build(messaggioVO, rutenteGestoreProfiloEntityMapper.mapEntityToVO(rUtenteGestoreProfilo));
		LoggerUtil.debug(logger, "[UtenteServiceImpl::createUtenteGestoreProfilo] END");		
		return response;
	}
	
	@Override
	public GenericResponse<UtenteGestoreProfiloVO> updateUtenteGestoreProfilo(HttpSession httpSession,
			UtenteGestoreProfiloVO utenteGestoreProfiloVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::updateUtenteGestoreProfilo] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Date currentDate = new Date();
		Optional<TsddrRUtenteGestoreProfilo> tsddrRUtenteGestoreProfiloOpt = tsddrRUtenteGestoreProfiloRepository.findByIdIdUtenteAndIdIdGestoreAndIdIdProfilo(utenteGestoreProfiloVO.getIdUtente(), utenteGestoreProfiloVO.getIdGestore(), utenteGestoreProfiloVO.getIdProfilo());
		if(!tsddrRUtenteGestoreProfiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRUtenteGestoreProfilo non trovato con idUtente = [%d], idGestore = [%d], idProfilo = [%d]", utenteGestoreProfiloVO.getIdUtente(), utenteGestoreProfiloVO.getIdGestore(), utenteGestoreProfiloVO.getIdProfilo()));
		}
		
		TsddrRUtenteGestoreProfilo rUtenteGestoreProfilo = tsddrRUtenteGestoreProfiloOpt.get();
		boolean isUtenteFO = false;
		List<TsddrDProfilo> profiliUtente = tsddrDProfiliRepository.findProfiliByIdUtente(rUtenteGestoreProfilo.getUtente().getIdUtente(), new Date());
		if(profiliUtente.stream().anyMatch(p -> p.getTipoProfilo().getIdTipoProfilo() == TipoProfilo.FRONT_OFFICE.getId())) {
			isUtenteFO = true;
		}
		
		List<TsddrTDomanda> domandeUtente = tsddrTDomandaRepository.findActiveByCodiceFiscaleAndGestore(rUtenteGestoreProfilo.getUtente().getDatiSogg().getCodFiscale(), utenteGestoreProfiloVO.getIdGestore());
		if(!domandeUtente.isEmpty() && isUtenteFO) {
			throw new BadRequestException(String.format("Impossibile eseguire l'update: l'utente con idUtente = [%d] ha il profilo FO e almeno una Domanda di Accreditamento", utenteGestoreProfiloVO.getIdUtente()));
		}
		
		if(utenteGestoreProfiloVO.getDataInizioValidita() == null) {
			utenteGestoreProfiloVO.setDataInizioValidita(currentDate);
		}
		if(utenteGestoreProfiloVO.getDataFineValidita() != null) {
			validazioneService.verificaValiditaDate(utenteGestoreProfiloVO.getDataInizioValidita(), utenteGestoreProfiloVO.getDataFineValidita());
		}
		
		
		rUtenteGestoreProfilo.setDataInizioValidita(utenteGestoreProfiloVO.getDataInizioValidita());
		rUtenteGestoreProfilo.setDataFineValidita(utenteGestoreProfiloVO.getDataFineValidita());
		rUtenteGestoreProfilo.setDataUpdate(currentDate);
		rUtenteGestoreProfilo.setIdUserUpdate(idUtenteSessione);
		rUtenteGestoreProfilo = tsddrRUtenteGestoreProfiloRepository.save(rUtenteGestoreProfilo);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_MODIFICA_LEGAME_GESTORE_UTENTE, LogConstants.TSDDR_T_UTENTI_TSDDR_T_GESTORI, ("" + utenteGestoreProfiloVO.getIdUtente() + ", " + utenteGestoreProfiloVO.getIdGestore()));
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<UtenteGestoreProfiloVO> response = GenericResponse.build(messaggioVO, rutenteGestoreProfiloEntityMapper.mapEntityToVO(rUtenteGestoreProfilo));
		LoggerUtil.debug(logger, "[UtenteServiceImpl::updateUtenteGestoreProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<String> removeUtenteGestoreProfilo(HttpSession httpSession, Long idUtente, Long idGestore,
			Long idProfilo) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::removeUtenteGestoreProfilo] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		
		Optional<TsddrRUtenteGestoreProfilo> tsddrRUtenteGestoreProfiloOpt = tsddrRUtenteGestoreProfiloRepository.findByIdIdUtenteAndIdIdGestoreAndIdIdProfilo(idUtente, idGestore, idProfilo);
		if(!tsddrRUtenteGestoreProfiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRUtenteGestoreProfilo non trovato con idUtente = [%d], idGestore = [%d], idProfilo = [%d]", idUtente, idGestore, idProfilo));
		}
		
		Date currentDate = new Date();
		
		TsddrRUtenteGestoreProfilo rUtenteGestoreProfilo = tsddrRUtenteGestoreProfiloOpt.get();
		rUtenteGestoreProfilo.setDataFineValidita(currentDate);
		rUtenteGestoreProfilo.setDataDelete(currentDate);
		rUtenteGestoreProfilo.setIdUserDelete(idUtenteSessione);
		rUtenteGestoreProfilo = tsddrRUtenteGestoreProfiloRepository.save(rUtenteGestoreProfilo);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_CANCELLA_LEGAME_GESTORE_UTENTE, LogConstants.TSDDR_T_UTENTI_TSDDR_T_GESTORI, ("" + idUtente + ", " + idGestore));
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P003.name());
		GenericResponse<String> response = GenericResponse.build(messaggioVO);
		LoggerUtil.debug(logger, "[UtenteServiceImpl::removeUtenteGestoreProfilo] END");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	private List<TsddrTUtente> getUtenti(UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getUtenti] BEGIN");
		List<TsddrTUtente> utenti = new ArrayList<>();
		try {
			boolean isCodFiscalePresent = false;
			boolean isCognomePresent = false;
			boolean isNomePresent = false;
			boolean isIdGestorePresent = false;
			boolean isIdProfiloPresent = false;
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ttu FROM TsddrTUtente ttu INNER JOIN ttu.datiSogg ttds ");
			if(utenteParametriRicercaVO.getIdGestore() != null) {
				queryBuilder.append("INNER JOIN ttu.rUtentiGestoriProfili trugp ");
				isIdGestorePresent = true;
			}
			if(utenteParametriRicercaVO.getIdProfilo() != null) {
				queryBuilder.append("INNER JOIN ttu.rUtentiProf trup ");
				isIdProfiloPresent = true;
			}
			queryBuilder.append("WHERE ");
			queryBuilder.append(RepositoryUtil.TTU_UTENTE_DELETE_VALIDITY_CHECK);
			queryBuilder.append("AND ");
			queryBuilder.append(RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK);
			
			if(utenteParametriRicercaVO.getCodiceFiscale() != null && !utenteParametriRicercaVO.getCodiceFiscale().isBlank() && !utenteParametriRicercaVO.getCodiceFiscale().isEmpty()) {
				queryBuilder.append("AND UPPER(ttds.codFiscale) = UPPER(:codiceFiscale) ");
				isCodFiscalePresent = true;
			}
			
			if(utenteParametriRicercaVO.getCognome() != null && !utenteParametriRicercaVO.getCognome().isBlank() && !utenteParametriRicercaVO.getCognome().isEmpty()) {
				queryBuilder.append("AND UPPER(ttds.cognome) = UPPER(:cognome) ");
				isCognomePresent = true;
			}
			
			if(utenteParametriRicercaVO.getNome() != null && !utenteParametriRicercaVO.getNome().isBlank() && !utenteParametriRicercaVO.getNome().isEmpty()) {
				queryBuilder.append("AND UPPER(ttds.nome) = UPPER(:nome) ");
				isNomePresent = true;
			}
			
			if(isIdGestorePresent) {
				queryBuilder.append("AND trugp.gestore.idGestore = :idGestore ");
				queryBuilder.append("AND ");
				queryBuilder.append(RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK);
			}
			
			if(isIdProfiloPresent) {
				queryBuilder.append("AND trup.profilo.idProfilo = :idProfilo ");
				queryBuilder.append("AND ");
				queryBuilder.append(RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK);
			}
			
			Query jpaQuery = entityManager.createQuery(queryBuilder.toString());
			valorizeJpaParameters(
			        isCodFiscalePresent,
			        isCognomePresent,
			        isNomePresent,
			        isIdGestorePresent,
			        isIdProfiloPresent,
			        jpaQuery,
			        utenteParametriRicercaVO);
			
			utenti = jpaQuery.getResultList();
			
		} catch (Exception e) {
			throw new GenericException(e);
		}
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getUtenti] END");
		return utenti;
	}
	
	private void valorizeJpaParameters(
	        boolean isCodFiscalePresent,
	        boolean isCognomePresent,
	        boolean isNomePresent,
	        boolean isIdGestorePresent,
	        boolean isIdProfiloPresent,
	        Query jpaQuery,
	        UtenteParametriRicercaVO utenteParametriRicercaVO) {
	    if (isCodFiscalePresent) {
            jpaQuery.setParameter("codiceFiscale", utenteParametriRicercaVO.getCodiceFiscale());
        }
        if (isCognomePresent) {
            jpaQuery.setParameter("cognome", utenteParametriRicercaVO.getCognome());
        }
        if (isNomePresent) {
            jpaQuery.setParameter("nome", utenteParametriRicercaVO.getNome());
        }
        
        if (isIdGestorePresent || isIdProfiloPresent) {
            jpaQuery.setParameter("currentDate", new Date());
        }
        
        if (isIdGestorePresent) {
            jpaQuery.setParameter("idGestore", utenteParametriRicercaVO.getIdGestore());
        }
        
        if (isIdProfiloPresent) {
            jpaQuery.setParameter("idProfilo", utenteParametriRicercaVO.getIdProfilo());
        }
	}
	
	private MessaggioVO verificaUtenteByCodiceFiscale(String codiceFiscale) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaUtenteByCodiceFiscale] BEGIN");
		MessaggioVO messaggioVO = null;
		Optional<TsddrTDatiSogg> datiSoggOpt = tsddrTDatiSoggRepository.findByCodFiscale(codiceFiscale);
		if(datiSoggOpt.isPresent()) {
			messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E007.name());
		}
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaUtenteByCodiceFiscale] END");
		return messaggioVO;
	}
	
	private List<MessaggioVO> verificaDatiObbligatori(DatiObbligatoriUtenteVO datiObbligatoriUtenteVO, boolean skipProfilo, boolean skipDataInizioValidita) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaDatiObbligatori] BEGIN");
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());

		if (StringUtils.isBlank(datiObbligatoriUtenteVO.getCodiceFiscale())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "codiceFiscale"));
		}

		if (StringUtils.isBlank(datiObbligatoriUtenteVO.getCognome())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "cognome"));
		}

		if (StringUtils.isBlank(datiObbligatoriUtenteVO.getNome())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "nome"));
		}
		
		checkProfilo(datiObbligatoriUtenteVO, skipProfilo, messaggiVO, messaggioVO);
		
		if (StringUtils.isBlank(datiObbligatoriUtenteVO.getMail())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "mail"));
		}

		if (!skipDataInizioValidita) {
			if (datiObbligatoriUtenteVO.getDataInizioValidita() == null) {
				messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "dataInizioValidita"));
			}
		}
		LoggerUtil.debug(logger, "[UtenteServiceImpl::verificaDatiObbligatori] END");
		return messaggiVO;
	}
	
	private void checkProfilo(DatiObbligatoriUtenteVO datiObbligatoriUtenteVO, boolean skipProfilo, List<MessaggioVO> messaggiVO, MessaggioVO messaggioVO) {
	    if (!skipProfilo) {
            if (datiObbligatoriUtenteVO.getIdProfilo() == null) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "profilo"));

            } else {
                Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(datiObbligatoriUtenteVO.getIdProfilo());
                
                if (profiloOpt.isPresent()) {
                    if (datiObbligatoriUtenteVO.getIdGestore() == null && profiloOpt.get().getTipoProfilo().getIdTipoProfilo() != TipoProfilo.BACK_OFFICE.getId()) {
                        messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "gestore"));
                    }
                }
            }
        }
	}
	
	private boolean areParametriRicercaValid(UtenteParametriRicercaVO utenteParametriRicercaVO) {
		LoggerUtil.debug(logger, "[UtenteServiceImpl::areParametriRicercaValid] BEGIN");
		boolean areParametriRicercaValid = StringUtils.isNotBlank(utenteParametriRicercaVO.getCodiceFiscale())
				|| StringUtils.isNotBlank(utenteParametriRicercaVO.getCognome())
				|| StringUtils.isNotBlank(utenteParametriRicercaVO.getNome())
				|| utenteParametriRicercaVO.getIdGestore() != null || utenteParametriRicercaVO.getIdProfilo() != null;
		LoggerUtil.debug(logger, "[UtenteServiceImpl::areParametriRicercaValid] END");
		return areParametriRicercaValid;
	}

}
