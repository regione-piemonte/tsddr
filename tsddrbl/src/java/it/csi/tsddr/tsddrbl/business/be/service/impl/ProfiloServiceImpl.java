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

import javax.servlet.http.HttpSession;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoProfilo;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ProfiloEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProfiliRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoProfiloRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRUtenteProfRepository;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.ProfiloService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.enums.TipoProfilo;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class ProfiloServiceImpl implements ProfiloService {

	private static Logger logger = Logger.getLogger(ProfiloServiceImpl.class);
	
	private static final String PROFILO_NOT_FOUND = "TsddrDProfilo non trovato con idProfilo = [%d]";
	
	@Autowired
	private CsiLogAuditService csiLogAuditService;
	
	@Autowired
	private ProfiloEntityMapper profiloEntityMapper;
	
	@Autowired
	private TsddrDTipoProfiloRepository tsddrDTipoProfiloRepository;
	
	@Autowired
	private TsddrDProfiliRepository tsddrDProfiliRepository;
	
	@Autowired
	private TsddrRUtenteProfRepository tsddrRUtenteProfRepository;
	
	@Autowired
	private MessaggioServiceImpl messaggiService;
	
	@Autowired
	private AclUtil aclUtil;	
	
	@Override
	public GenericResponse<List<SelectVO>> getComboProfilo(boolean showAll) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getComboProfilo] BEGIN");
		List<TsddrDProfilo> profili = showAll ? tsddrDProfiliRepository.findAllProfili(new Date())
				: tsddrDProfiliRepository.findProfili(new Date());
		List<SelectVO> selectVO = profiloEntityMapper.mapListEntityToListSelectVO(profili);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getComboProfilo] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<ProfiloVO>> getTabellaProfili(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getTabellaProfili] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		List<TsddrDProfilo> profili = tsddrDProfiliRepository.findProfiliDataAndIdUserCheck();
		List<ProfiloVO> profiliVO = new ArrayList<ProfiloVO>();
		for(TsddrDProfilo profilo : profili) {
			profiliVO.add(profiloEntityMapper.mapEntityToVO(profilo));
		}
		for(ProfiloVO profiloVO : profiliVO) {
			Long rUtenteProfIdProfiloSize = tsddrRUtenteProfRepository.countByIdProfilo(profiloVO.getIdProfilo(), new Date());
			if(rUtenteProfIdProfiloSize == 0) {
				profiloVO.setDeletable(true);
			} 
		}
		List<Long> idProfiliVisualizzati = profili
				.stream()
				.map(TsddrDProfilo::getIdProfilo)
				.collect(Collectors.toList());
		csiLogAuditService.traceCsiLogAudit(httpSession, String.valueOf(idDatiSogg), LogConstants.TSDDR_ADMIN_GESTIONE_PROFILI, LogConstants.TSDDR_D_PROFILI, StringUtils.join(idProfiliVisualizzati, ","));
		GenericResponse<List<ProfiloVO>> response = GenericResponse.build(profiliVO);
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getTabellaProfili] END");
		return response;
	}

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLProfili(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getACLProfili] BEGIN");
		FunzionalitaProfiloVO gestioneProfiloUtenteVO = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.AM_003);
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(gestioneProfiloUtenteVO);
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getACLProfili] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> verificaDatiProfilo(ProfiloVO profiloVO) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::verificaDatiProfilo] BEGIN");

		List<MessaggioVO> messaggiVO = new ArrayList<>();
		
		if (profiloVO.getDescProfilo() == null) {
			MessaggioVO messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
			messaggioVO.setCampo("descProfilo");
			messaggiVO.add(messaggioVO);
		}

		if (profiloVO.getTipologiaProfiloVO() == null) {
			MessaggioVO messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
			messaggioVO.setCampo("tipologiaProfilo");
			messaggiVO.add(messaggioVO);
		}

		if (profiloVO.getDataFineValidita() != null && profiloVO.getDataInizioValidita() != null
				&& profiloVO.getDataFineValidita().before(profiloVO.getDataInizioValidita())) {
			MessaggioVO messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E002.name());
			messaggioVO.setCampo("dataFineValidita");
			messaggiVO.add(messaggioVO);
		}
		
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::verificaDatiProfilo] END");
		return response;
	}

	@Override
	public GenericResponse<ProfiloVO> updateProfilo(HttpSession httpSession, ProfiloVO profiloVO) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::updateProfilo] BEGIN");
		this.verificaDatiProfilo(profiloVO);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(profiloVO.getIdProfilo());
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(PROFILO_NOT_FOUND, profiloVO.getIdProfilo()));
		}
		Optional<TsddrDTipoProfilo> tipoProfiloOpt = tsddrDTipoProfiloRepository.findByIdTipoProfilo(profiloVO.getTipologiaProfiloVO().getIdTipoProfilo());
		if(!tipoProfiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrDTipoProfilo non trovato con idTipoProfilo = [%d]", profiloVO.getTipologiaProfiloVO().getIdTipoProfilo()));
		}
		
		TsddrDProfilo profilo = profiloOpt.get();
		profilo.setDescProfilo(profiloVO.getDescProfilo());
		profilo.setTipoProfilo(tipoProfiloOpt.get());
		if(profiloVO.getDataInizioValidita() != null) {
			profilo.setDataInizioValidita(profiloVO.getDataInizioValidita());
		} else {
			profilo.setDataInizioValidita(new Date());
		}
		profilo.setDataFineValidita(profiloVO.getDataFineValidita());
		profilo.setIdUserUpdate(idUtente);
		profilo.setDataUpdate(new Date());
		profilo = tsddrDProfiliRepository.save(profilo);
		
		profiloVO = profiloEntityMapper.mapEntityToVO(profilo);
		csiLogAuditService.traceCsiLogAudit(httpSession, String.valueOf(idDatiSogg), LogConstants.TSDDR_ADMIN_MODIFICA_DATI_PROFILO, LogConstants.TSDDR_D_PROFILI, String.valueOf(profiloVO.getIdProfilo()));
		MessaggioVO messaggio = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::updateProfilo] END");
		return GenericResponse.build(messaggio, profiloVO);
	}

	@Override
	public GenericResponse<ProfiloVO> removeProfilo(HttpSession httpSession, Long idProfilo) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::removeProfilo] BEGIN");
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(idProfilo);
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(PROFILO_NOT_FOUND, idProfilo));
		}
		
		Long rUtenteProfIdProfiloSize = tsddrRUtenteProfRepository.countByIdProfilo(idProfilo, new Date());
		if(rUtenteProfIdProfiloSize > 0) {
			throw new BadRequestException(String.format("TsddrRUtenteProf ha dei record con idProfilo = [%d]", idProfilo)); 
		}
		
		TsddrDProfilo profilo = profiloOpt.get();
		profilo.setDataFineValidita(new Date());
		profilo.setIdUserDelete(idUtente);
		profilo.setDataDelete(new Date());
		profilo = tsddrDProfiliRepository.save(profilo);
		ProfiloVO profiloVO = profiloEntityMapper.mapEntityToVO(profilo);
		csiLogAuditService.traceCsiLogAudit(httpSession, String.valueOf(idDatiSogg), LogConstants.TSDDR_ADMIN_ELIMINATO_PROFILO, LogConstants.TSDDR_D_PROFILI, String.valueOf(idProfilo));
		MessaggioVO messaggio = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.P003.name());
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::removeProfilo] END");
		return GenericResponse.build(messaggio, profiloVO);
	}

	@Override
	public GenericResponse<ProfiloVO> createProfilo(HttpSession httpSession, ProfiloVO profiloVO) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::createProfilo] BEGIN");
		this.verificaDatiProfilo(profiloVO);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrDTipoProfilo> tipoProfiloOpt = tsddrDTipoProfiloRepository.findByIdTipoProfilo(profiloVO.getTipologiaProfiloVO().getIdTipoProfilo());
		
		if (tipoProfiloOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format("TsddrDTipoProfilo non trovato con idTipoProfilo = [%d]", profiloVO.getTipologiaProfiloVO().getIdTipoProfilo()));
		}
		
		Date now = new Date();
		TsddrDProfilo profilo = profiloEntityMapper.mapVOToEntity(profiloVO);
		profilo.setTipoProfilo(tipoProfiloOpt.get());
		if (profilo.getDataInizioValidita() == null) {
			profilo.setDataInizioValidita(now);
		}
		profilo.setIdUserInsert(idUtente);
		profilo.setDataInsert(now);
		profilo = tsddrDProfiliRepository.save(profilo);
		csiLogAuditService.traceCsiLogAudit(httpSession, String.valueOf(idDatiSogg), LogConstants.TSDDR_ADMIN_INSERIMENTO_NUOVO_PROFILO, LogConstants.TSDDR_D_PROFILI, String.valueOf(profilo.getIdProfilo()));
		profiloVO = profiloEntityMapper.mapEntityToVO(profilo);
		MessaggioVO messaggio = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::createProfilo] END");
		return GenericResponse.build(messaggio, profiloVO);
	}

	@Override
	public List<Long> getIdProfiliByCodiceFiscale(String codiceFiscale) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getIdProfiliByCodiceFiscale] BEGIN");
		List<Long> idProfili = tsddrDProfiliRepository.findIdProfiliByCodiceFiscale(codiceFiscale, new Date());
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::getIdProfiliByCodiceFiscale] END");
		return idProfili;
	}

	@Override
	public boolean isProfiloBO(Long idProfilo) {
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::isProfiloBO] BEGIN");
		boolean isProfiloBO = false;
		Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(idProfilo);
		if(!profiloOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(PROFILO_NOT_FOUND, idProfilo));
		}
		isProfiloBO = profiloOpt.get().getTipoProfilo().getIdTipoProfilo() == TipoProfilo.BACK_OFFICE.getId();
		LoggerUtil.debug(logger, "[ProfiloServiceImpl::isProfiloBO] END");
		return isProfiloBO;
	}

    @Override
    public GenericResponse<Boolean> isProfiloBO(HttpSession httpSession, Long idProfilo) {
        LoggerUtil.debug(logger, "[ProfiloServiceImpl::isProfiloBO] BEGIN");
        GenericResponse<Boolean> response = GenericResponse.build(this.isProfiloBO(idProfilo));
        LoggerUtil.debug(logger, "[ProfiloServiceImpl::isProfiloBO] END");
        return response;
    }

}
