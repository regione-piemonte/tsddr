/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNaturaGiuridica;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNazione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSedime;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLegaleRappresentante;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.DatiSoggEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.GestoreEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.IndirizzoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDComuneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDNaturaGiuridicaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDNazioneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProvinciaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDSedimeRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoIndirizzoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRUtenteGestoreProfiloRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDatiSoggRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDomandaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTGestoreRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTIndirizzoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTLegaleRappresentanteRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.specifications.TsddrTGestoreSpecification;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.GestoreService;
import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.business.be.service.ValidazioneService;
import it.csi.tsddr.tsddrbl.util.Constants;
import it.csi.tsddr.tsddrbl.util.DateUtil;
import it.csi.tsddr.tsddrbl.util.EntityUtil;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.enums.TipoIndirizzo;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelDataUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelSheet;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.gestore.LegaleRappresentanteVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class GestoreServiceImpl implements GestoreService {
	
	private static Logger logger = Logger.getLogger(GestoreServiceImpl.class);

	public SimpleDateFormat formatDate = new SimpleDateFormat(DateUtil.ddMMyyyy);
	private static Long REPORT_ID = 4L;	
	private static final String GESTORE_NOT_FOUND = "TsddrTGestore non trovato con idGestore = [%d]";

	@Autowired
	private TsddrTReportRepository tsddrTReportRepository;
	
	@SuppressWarnings("el-syntax")
	@Value("${id.istat.nazione.corrente:" + Constants.ID_ISTAT_NAZIONE_CORRENTE_DEFAULT + "}")
	private String idIstatNazioneCorrente;
	
	@Autowired
	private GestoreEntityMapper gestoreEntityMapper;
	
	@Autowired
	private DatiSoggEntityMapper datiSoggEntityMapper;
	
	@Autowired
	private IndirizzoEntityMapper indirizzoEntityMapper;
	
	@Autowired
	private TsddrTGestoreRepository gestoreRepository;
	
	@Autowired
	private TsddrTLegaleRappresentanteRepository legaleRappresentanteRepository;
	
	@Autowired
	private TsddrRUtenteGestoreProfiloRepository utenteGestoreProfiloRepository;
	
	@Autowired
	private TsddrTDatiSoggRepository datiSoggRepository;
	
	@Autowired
	private TsddrTDomandaRepository domandaRepository; 
	
	@Autowired
	private TsddrTImpiantoRepository impiantoRepository;
	
	@Autowired
	private TsddrDNaturaGiuridicaRepository naturaGiuridicaRepository;
	
	@Autowired
	private TsddrTIndirizzoRepository indirizzoRepository;
	
	@Autowired
	private TsddrDTipoIndirizzoRepository tipoIndirizzoRepository;
	
	@Autowired
	private TsddrDNazioneRepository nazioneRepository;
	
	@Autowired
	private TsddrDComuneRepository comuneRepository;
	
	@Autowired
	private TsddrDProvinciaRepository provinciaRepository;
	
	@Autowired
	private TsddrDSedimeRepository sedimeRepository; 
	
	@Autowired
	private MessaggioService messaggioService;
	
	@Autowired
	private CsiLogAuditService csiLogAuditService;
	
	@Autowired
	private ValidazioneService validazioneService;

	@Autowired
	private AclUtil aclUtil;
	
	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLGestori(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getACLGestori] BEGIN");
		FunzionalitaProfiloVO gestioneProfiloUtenteVO = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.AM_006);
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(gestioneProfiloUtenteVO);
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getACLGestori] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getComboGestore() {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getComboGestore] BEGIN");
		List<TsddrTGestore> gestori = gestoreRepository.findGestori(new Date());
		List<SelectVO> selectVO = gestoreEntityMapper.mapListEntityToListSelectVO(gestori);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getComboGestore] END");
		return response;
	}
	
	@Override
	public GenericResponse<String> getParametriFiltoApplicati(GestoreParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getParametriFiltoApplicati] BEGIN");
		StringBuilder parametriFiltroBuilder = new StringBuilder();
		
		Date currentDate = new Date();
		
		if(parametriRicerca.getIdGestore() != null) {
			Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(parametriRicerca.getIdGestore(), currentDate);
			if(gestoreOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrTGestore trovato con idGestore = [%d]", parametriRicerca.getIdGestore()));
			}
			parametriFiltroBuilder.append("Gestore = \"" + gestoreOpt.get().getRagSociale() + "\" ");
		}
		if(StringUtils.isNotBlank(parametriRicerca.getCodFiscPartiva())) {
			parametriFiltroBuilder.append("Codice Fiscale/P. IVA = \"" + parametriRicerca.getCodFiscPartiva() + "\" ");
		}
		if(parametriRicerca.getIdComune() != null) {
			Optional<TsddrDComune> comuneOpt = comuneRepository.findByIdComune(parametriRicerca.getIdComune(), currentDate);
			if(comuneOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDComune trovato con idComune = [%d]", parametriRicerca.getIdComune()));
			}
			parametriFiltroBuilder.append("Comune = \"" + comuneOpt.get().getComune() + "\" ");
		}
		if(parametriRicerca.getIdProvincia() != null) {
			Optional<TsddrDProvincia> provinciaOpt = provinciaRepository.findProvinciaById(parametriRicerca.getIdProvincia(), currentDate);
			if(provinciaOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDProvincia trovato con idProvincia = [%d]", parametriRicerca.getIdProvincia()));
			}
			parametriFiltroBuilder.append("Provincia/CM = \"" + provinciaOpt.get().getDescProvincia() + "\" ");
		}
		if(parametriRicerca.getIdNaturaGiuridica() != null) {
			Optional<TsddrDNaturaGiuridica> naturaGiuridicaOpt = naturaGiuridicaRepository.findByIdNaturaGiuridica(parametriRicerca.getIdNaturaGiuridica(), currentDate);
			if(naturaGiuridicaOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDNaturaGiuridica trovato con idNaturaGiuridica = [%d]", parametriRicerca.getIdNaturaGiuridica()));
			}
			parametriFiltroBuilder.append("Natura Giuridica = \"" + naturaGiuridicaOpt.get().getDescNaturaGiuridica() + "\" ");
		}
		GenericResponse<String> response = GenericResponse.build(parametriFiltroBuilder.toString());
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getParametriFiltroApplicati] END");
		return response;
	}

	@Override
	public GenericResponse<List<GestoreVO>> getListaGestori(HttpSession httpSession, GestoreParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getListaGestori] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		List<TsddrTGestore> gestori = gestoreRepository.findAll(TsddrTGestoreSpecification.searchByParams(parametriRicerca, new Date()));
		List<GestoreVO> selectVO = gestoreEntityMapper.mapListEntityToListVO(gestori);
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_RICERCA_GESTORI,
				LogConstants.TSDDR_T_GESTORI,
				selectVO.stream().map(g -> String.valueOf(g.getIdGestore())).collect(Collectors.joining(",")));
		GenericResponse<List<GestoreVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getListaGestori] END");
		return response;
	}

	@Override
	public GenericResponse<GestoreVO> getGestore(HttpSession httpSession, Long idGestore) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getGestore] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		// 20221027 PP - Jira TSDDR-16 reperisco senza data validità per avere il dettaglio
		Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(idGestore);
		if (!gestoreOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(GESTORE_NOT_FOUND, idGestore));
		}
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
				LogConstants.TSDDR_GESTORI_VISUALIZZA_DATI_GESTORE, LogConstants.TSDDR_T_GESTORI, gestoreOpt.get().getIdGestore());
		GenericResponse<GestoreVO> response = GenericResponse.build(gestoreEntityMapper.mapEntityToVO(gestoreOpt.get()));
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getGestore] END");
		return response;
	}

	@Override
	public GenericResponse<GestoreVO> createGestore(HttpSession httpSession, GestoreVO gestoreVO) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::createGestore] BEGIN");
		// validazione dati gestore
		this.validazioneGestore(gestoreVO);
		
		Date currentDate = new Date();
		
		Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByCodFiscPartiva(gestoreVO.getCodFiscPartiva(), new Date());
		if (gestoreOpt.isPresent()) {
			LoggerUtil.error(logger, String.format("Gestore gia' esistente con codFiscPartiva = [%s]", gestoreVO.getCodFiscPartiva()));
			throw new FunctionalException(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E007.name()));
		}
		
		Optional<TsddrDNaturaGiuridica> naturaGiuridicaOpt = naturaGiuridicaRepository.findByIdNaturaGiuridica(gestoreVO.getNaturaGiuridica().getIdNaturaGiuridica(), currentDate);
		if (naturaGiuridicaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format("TsddrDNaturaGiuridica non trovato con idNaturaGiuridica = [%d]", gestoreVO.getNaturaGiuridica().getIdNaturaGiuridica()));
		}
		
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		TsddrTIndirizzo sedeLegale = this.createSedeLegale(gestoreVO.getSedeLegale(), null, 1L, currentDate, idUtenteSessione);
		
		TsddrTGestore gestore = gestoreEntityMapper.mapVOToEntity(gestoreVO);
		gestore.setCodFiscPartiva(StringUtils.upperCase(gestore.getCodFiscPartiva()));
		gestore.setNaturaGiuridica(naturaGiuridicaOpt.get());
		gestore.setSedeLegale(sedeLegale);
		EntityUtil.setInsertedWithValidity(gestore, idUtenteSessione, gestoreVO.getDataInizioValidita() != null ? gestoreVO.getDataInizioValidita() : currentDate);
		gestore.setDataFineValidita(gestoreVO.getDataFineValidita() != null ? gestoreVO.getDataFineValidita() : null);
		gestore = gestoreRepository.save(gestore);
		
		// rappresentante legale
		gestore.setLegaliRappresentanti(List.of(this.createLegaleRapp(gestoreVO.getLegaleRappresentante(), gestore, currentDate, idUtenteSessione)));
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
				LogConstants.TSDDR_GESTORI_INSERIMENTO_NUOVO_GESTORE, LogConstants.TSDDR_T_GESTORI, gestore.getIdGestore());

		GenericResponse<GestoreVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name()),
				gestoreEntityMapper.mapEntityToVO(gestore));
		
		LoggerUtil.debug(logger, "[GestoreServiceImpl::createGestore] END");
		return response;
	}

	@Override
	public GenericResponse<GestoreVO> updatGestore(HttpSession httpSession, Long idGestore, GestoreVO gestoreVO) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::updatGestore] BEGIN");
		// validazione dati gestore
		this.validazioneGestore(gestoreVO);
		
		Date currentDate = new Date();
		
		Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(idGestore, currentDate);
		if (gestoreOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format(GESTORE_NOT_FOUND, idGestore));
		}
		
		Optional<TsddrDNaturaGiuridica> naturaGiuridicaOpt = naturaGiuridicaRepository.findByIdNaturaGiuridica(gestoreVO.getNaturaGiuridica().getIdNaturaGiuridica(), currentDate);
		if (naturaGiuridicaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format("TsddrDNaturaGiuridica non trovato con idNaturaGiuridica = [%d]", gestoreVO.getNaturaGiuridica().getIdNaturaGiuridica()));
		}
		
		if (!StringUtils.equalsIgnoreCase(gestoreVO.getCodFiscPartiva(), gestoreOpt.get().getCodFiscPartiva())) {
			if (gestoreRepository.existsByCodFiscPartivaAndIdGestoreNot(gestoreVO.getCodFiscPartiva(), idGestore, currentDate)) {
				LoggerUtil.error(logger, String.format("Gestore gia' esistente con codFiscPartiva = [%s]", gestoreVO.getCodFiscPartiva()));
				throw new FunctionalException(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E007.name()));
			}
		}
		
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		TsddrTGestore gestore = gestoreOpt.get();
		
		if (gestore.getSedeLegale() == null || this.isAddressModified(indirizzoEntityMapper.mapEntityToVO(gestore.getSedeLegale()), gestoreVO.getSedeLegale())) {
			// sede legale corrente
			Optional<TsddrTIndirizzo> currentSedeLegaleOpt = indirizzoRepository.findByOriginalIdAndMaxVersione(gestore.getSedeLegale().getOriginalId());
			
			Long versione = 1L;
			Long originalId = null;
			if (currentSedeLegaleOpt.isPresent()) {
				indirizzoRepository.save(EntityUtil.setDeleted(currentSedeLegaleOpt.get(), idUtenteSessione, currentDate));
				versione = currentSedeLegaleOpt.get().getVersione() + 1;
				originalId = currentSedeLegaleOpt.get().getOriginalId();
			}
			
			gestore.setSedeLegale(this.createSedeLegale(gestoreVO.getSedeLegale(), originalId, versione, currentDate, idUtenteSessione));
		}
		
		LegaleRappresentanteVO currentLegaleRapprVO = gestoreEntityMapper.activeLegaleRapp(gestore.getLegaliRappresentanti());
		
		if (currentLegaleRapprVO == null || this.isRappLegaleModified(currentLegaleRapprVO, gestoreVO.getLegaleRappresentante())) {
			// eliminazione logica legale rapp corrente
			Optional<TsddrTLegaleRappresentante> currentLegaleRapprOpt = legaleRappresentanteRepository.findByIdGestore(idGestore, currentDate);
			if (currentLegaleRapprOpt.isPresent()) {
				legaleRappresentanteRepository.save(EntityUtil.setDeleted(currentLegaleRapprOpt.get(), idUtenteSessione, currentDate));
			}
			
			gestore.getLegaliRappresentanti().add(this.createLegaleRapp(gestoreVO.getLegaleRappresentante(), gestore, currentDate, idUtenteSessione));
		}
		
		gestore.setNaturaGiuridica(naturaGiuridicaOpt.get());
		gestore.setCodFiscPartiva(StringUtils.upperCase(gestoreVO.getCodFiscPartiva()));
		gestore.setRagSociale(gestoreVO.getRagSociale());
		gestore.setTelefono(gestoreVO.getTelefono());
		gestore.setEmail(gestoreVO.getEmail());
		gestore.setPec(gestoreVO.getPec());
		EntityUtil.setUpdated(gestore, idUtenteSessione, gestoreVO.getDataInizioValidita() != null ? gestoreVO.getDataInizioValidita() : currentDate);
		gestore.setDataFineValidita(gestoreVO.getDataFineValidita() != null ? gestoreVO.getDataFineValidita() : null);
		gestore = gestoreRepository.save(gestore);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
				LogConstants.TSDDR_GESTORI_MODIFICA_DATI_GESTORE, LogConstants.TSDDR_T_GESTORI, gestore.getIdGestore());

		GenericResponse<GestoreVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name()),
				gestoreEntityMapper.mapEntityToVO(gestore));
		
		LoggerUtil.debug(logger, "[GestoreServiceImpl::updatGestore] END");
		return response;
	}
	
	private TsddrTIndirizzo createSedeLegale(IndirizzoVO sedeLegaleVO, Long originalId, Long versione, Date currentDate, Long idUtenteSessione) {
		TsddrTIndirizzo newSedeLegale = new TsddrTIndirizzo();
		newSedeLegale.setVersione(versione != null ? versione : 1L);
		
		if (originalId != null) {
			newSedeLegale.setOriginalId(originalId);
		}
		
		Optional<TsddrDTipoIndirizzo> tipoIndirizzoOpt = tipoIndirizzoRepository.findByIdTipoIndirizzo(TipoIndirizzo.SEDE_LEGALE_GESTORE.getId(), currentDate);
		newSedeLegale.setTipoIndirizzo(tipoIndirizzoOpt.orElse(null));
		
		if (sedeLegaleVO.getNazione() != null && sedeLegaleVO.getNazione().getIdNazione() != null) {
			Optional<TsddrDNazione> nazioneOpt = nazioneRepository.findByIdNazione(sedeLegaleVO.getNazione().getIdNazione(), currentDate);
			if (nazioneOpt.isEmpty()) {
				throw new RecordNotFoundException(String.format("TsddrDNazione non trovata con idNazione = [%d]", sedeLegaleVO.getNazione().getIdNazione()));
			}
			
			if(idIstatNazioneCorrente.equalsIgnoreCase(nazioneOpt.get().getIdIstatNazione())) {
				throw new BadRequestException(String.format("Impossibile settare TsddrDNazione nazione estera con idIstatNazioneCorrente = [%s], è la nazione corrente", nazioneOpt.get().getIdIstatNazione()));
			}
			newSedeLegale.setNazione(nazioneOpt.get());
		
		} else {
			// nazione è impostata di default
			Optional<TsddrDNazione> nazioneOpt = nazioneRepository.findNazioneByIdIstatNazione(idIstatNazioneCorrente, currentDate);
			if(nazioneOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessuna TsddrDNazione trovata con idIstatNazioneCorrente = [%s]", idIstatNazioneCorrente));
			}
			newSedeLegale.setNazione(nazioneOpt.get());
			
			Optional<TsddrDSedime> sedimeOpt = sedimeRepository.findByIdSedime(sedeLegaleVO.getSedime().getIdSedime(), currentDate);
			newSedeLegale.setSedime(sedimeOpt.orElseThrow(
					() -> new RecordNotFoundException(String.format("TsddrDSedime non trovato con idSedime = [%d]",
							sedeLegaleVO.getSedime().getIdSedime()))));
			newSedeLegale.setCap(sedeLegaleVO.getCap());
			newSedeLegale.setIndirizzo(sedeLegaleVO.getIndirizzo());

			Optional<TsddrDComune> comuneOpt = comuneRepository.findByIdComune(sedeLegaleVO.getComune().getIdComune(), currentDate);
			newSedeLegale.setComune(comuneOpt.orElseThrow(
					() -> new RecordNotFoundException(String.format("TsddrDComune non trovato con idComune = [%d]",
							sedeLegaleVO.getComune().getIdComune()))));
		}
		
		newSedeLegale = indirizzoRepository.save(EntityUtil.setInserted(newSedeLegale, idUtenteSessione, currentDate));
		
		if (newSedeLegale.getOriginalId() == null) {
			// XXX attenzione: potrebbe verificarsi una violazione di
			// unique key nel caso di scritture simultanee nella tabella prima
			// dell'inserimento dell'originalId: si avrebbe infatti la sola versione
			// valorizzata, quindi in caso di primo inserimento di un indirizzo, potremmo
			// avere già un record con versione 1 finchè non viene eseguita questa
			// istruzione (o meglio finchè non termina la transazione)
			
			// inserisco originalId
			newSedeLegale.setOriginalId(newSedeLegale.getIdIndirizzo());
			newSedeLegale = indirizzoRepository.save(newSedeLegale);
		}
		
		return newSedeLegale;		
	}
	
	private TsddrTLegaleRappresentante createLegaleRapp(LegaleRappresentanteVO legaleRappVO, TsddrTGestore gestore, Date currentDate, Long idUtenteSessione) {
		// DatiSogg nuovo legale rapp
		Optional<TsddrTDatiSogg> datiSoggOpt = datiSoggRepository.findByCodFiscale(legaleRappVO.getDatiSogg().getCodFiscale());
		// se non esiste già lo creo
		TsddrTDatiSogg datiSogg = datiSoggOpt.isPresent() ? datiSoggOpt.get() : datiSoggRepository.save(EntityUtil.setInserted(
				datiSoggEntityMapper.mapVOToEntity(legaleRappVO.getDatiSogg()), idUtenteSessione, currentDate));
		
		// nuovo legale rappr
		TsddrTLegaleRappresentante newRapprLegale = new TsddrTLegaleRappresentante();
		newRapprLegale.setIdGestore(gestore.getIdGestore());
		newRapprLegale.setGestore(gestore);
		newRapprLegale.setDatiSogg(datiSogg);
		newRapprLegale.setQualifica(legaleRappVO.getQualifica());

		return legaleRappresentanteRepository
				.save(EntityUtil.setInsertedWithValidity(newRapprLegale, idUtenteSessione, currentDate));
	}
	
	private void validazioneGestore(GestoreVO gestoreVO) {
		List<MessaggioVO> errori = this.verificaDatiObbligatori(gestoreVO);
		if (CollectionUtils.isNotEmpty(errori)) {
			LoggerUtil.error(logger,
					String.format("Dati obbligatori mancanti. %s", gestoreVO.getIdGestore() != null
							? String.format("Impossibile aggiornare il gestore con idGestore = [%d]", gestoreVO.getIdGestore())
							: "Impossibile creare il nuovo gestore"));
			throw new FunctionalException(errori);
		}
		
		// verifica dati
		errori = this.verificaDatiGestore(gestoreVO);
		if (CollectionUtils.isNotEmpty(errori)) {
			LoggerUtil.error(logger,
					String.format("Dati gestore non validi. %s", gestoreVO.getIdGestore() != null
							? String.format("Impossibile aggiornare il gestore con idGestore = [%d]", gestoreVO.getIdGestore())
							: "Impossibile creare il nuovo gestore"));
			throw new FunctionalException(errori);
		}
	}
	
	private List<MessaggioVO> verificaDatiObbligatori(GestoreVO gestoreVO) {
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());

		if (StringUtils.isBlank(gestoreVO.getCodFiscPartiva())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "codFiscPartiva"));
		}
		
		if (StringUtils.isBlank(gestoreVO.getRagSociale())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "ragSociale"));
		}
		
		if (gestoreVO.getNaturaGiuridica() == null || gestoreVO.getNaturaGiuridica().getIdNaturaGiuridica() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "naturaGiuridica.idNaturaGiuridica"));
		}
		
		checkSedeLegale(gestoreVO, messaggiVO, messaggioVO);
		checkNazione(gestoreVO, messaggiVO, messaggioVO);
		 
		if (StringUtils.isBlank(gestoreVO.getEmail())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "email"));
		}
		
		if (StringUtils.isBlank(gestoreVO.getPec())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "pec"));
		}
		
		checkDatiSogg(gestoreVO, messaggiVO, messaggioVO);
		
		if (gestoreVO.getDataInizioValidita() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "dataInizioValidita"));
		}
		
		return messaggiVO;
	}
	
	private void checkSedeLegale(GestoreVO gestoreVO, List<MessaggioVO> messaggiVO, MessaggioVO messaggioVO) {
	    if (gestoreVO.getSedeLegale() != null && (gestoreVO.getSedeLegale().getNazione() == null || gestoreVO.getSedeLegale().getNazione().getIdNazione() == null)) {
            if (gestoreVO.getSedeLegale().getSedime() == null || gestoreVO.getSedeLegale().getSedime().getIdSedime() == null) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "sedeLegale.sedime.idSedime"));
            }
            
            if (StringUtils.isBlank(gestoreVO.getSedeLegale().getIndirizzo())) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "sedeLegale.indirizzo"));
            }
            
            if (gestoreVO.getSedeLegale().getComune() == null || gestoreVO.getSedeLegale().getComune().getIdComune() == null) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "sedeLegale.comune.idComune"));
            }
            
            if (gestoreVO.getSedeLegale().getComune() == null
                    || gestoreVO.getSedeLegale().getComune().getProvincia() == null
                    || gestoreVO.getSedeLegale().getComune().getProvincia().getIdProvincia() == null) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "sedeLegale.comune.provincia.idProvincia"));
            }
        }
	}
	
	private void checkNazione(GestoreVO gestoreVO, List<MessaggioVO> messaggiVO, MessaggioVO messaggioVO) {
	    if (gestoreVO.getSedeLegale() != null && (gestoreVO.getSedeLegale().getSedime() == null
                || gestoreVO.getSedeLegale().getSedime().getIdSedime() == null)
                        && StringUtils.isBlank(gestoreVO.getSedeLegale().getIndirizzo())
                && (gestoreVO.getSedeLegale().getComune() == null
                        || gestoreVO.getSedeLegale().getComune().getIdComune() == null)
                && (gestoreVO.getSedeLegale().getComune() != null && (gestoreVO.getSedeLegale().getComune().getProvincia() == null
                        || gestoreVO.getSedeLegale().getComune().getProvincia().getIdProvincia() == null))
                ) {
            if (gestoreVO.getSedeLegale().getNazione() == null || gestoreVO.getSedeLegale().getNazione().getIdNazione() == null) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "sedeLegale.nazione.idNazione"));
            }
        }
	}
	
	private void checkDatiSogg(GestoreVO gestoreVO, List<MessaggioVO> messaggiVO, MessaggioVO messaggioVO) {
	    if (StringUtils.isBlank(gestoreVO.getLegaleRappresentante().getDatiSogg().getCodFiscale())) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "legaleRappresentante.datiSogg.codFiscale"));
        }
        
        if (StringUtils.isBlank(gestoreVO.getLegaleRappresentante().getDatiSogg().getCognome())) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "legaleRappresentante.datiSogg.cognome"));
        }
        
        if (StringUtils.isBlank(gestoreVO.getLegaleRappresentante().getDatiSogg().getNome())) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "legaleRappresentante.datiSogg.nome"));
        }
	}

	
	private List<MessaggioVO> verificaDatiGestore(GestoreVO gestoreVO) {
		List<MessaggioVO> errors = new ArrayList<>();
		
		CollectionUtils.addIgnoreNull(errors, validazioneService.verificaFormatoCodiceFiscale(gestoreVO.getLegaleRappresentante().getDatiSogg().getCodFiscale()));
		CollectionUtils.addIgnoreNull(errors, validazioneService.verificaFormatoEmail(gestoreVO.getEmail()));
		if (gestoreVO.getDataInizioValidita() != null && gestoreVO.getDataFineValidita() != null) {
			CollectionUtils.addIgnoreNull(errors, validazioneService.verificaValiditaDate(gestoreVO.getDataInizioValidita(), gestoreVO.getDataFineValidita()));
		}
		
		return errors;
	}
	
	private boolean isAddressModified(IndirizzoVO corrente, IndirizzoVO nuovo) {
		if (corrente == null) {
			return true;
		}
		
		if (!StringUtils.equalsIgnoreCase(corrente.getIndirizzo(), nuovo.getIndirizzo())) {
			return true;
		}
		
		if (corrente.getComune().getIdComune().longValue() != nuovo.getComune().getIdComune().longValue()) {
			return true;
		}
		
		if (!StringUtils.equalsIgnoreCase(corrente.getCap(), nuovo.getCap())) {
			return true;
		}
		
		if (corrente.getComune().getProvincia().getIdProvincia().longValue() != nuovo.getComune().getProvincia().getIdProvincia().longValue()) {
			return true;
		}
		
		if (corrente.getNazione().getIdNazione().longValue() != nuovo.getNazione().getIdNazione().longValue()) {
			return true;
		}
		
		if (corrente.getSedime().getIdSedime().longValue() != nuovo.getSedime().getIdSedime().longValue()) {
			return true;
		}
		
		return false;
	}
	
	private boolean isRappLegaleModified(LegaleRappresentanteVO corrente, LegaleRappresentanteVO nuovo) {
		if (corrente == null) {
			return true;
		}	
		
		if (!StringUtils.equalsIgnoreCase(corrente.getDatiSogg().getCodFiscale(), nuovo.getDatiSogg().getCodFiscale())) {
			return true;
		}
		
		if (!StringUtils.equalsIgnoreCase(corrente.getDatiSogg().getNome(), nuovo.getDatiSogg().getNome())) {
			return true;
		}
		
		if (!StringUtils.equalsIgnoreCase(corrente.getDatiSogg().getCognome(), nuovo.getDatiSogg().getCognome())) {
			return true;
		}
		
		if (!StringUtils.equalsIgnoreCase(corrente.getQualifica(), nuovo.getQualifica())) {
			return true;
		}
		
		return false;
	}

	@Override
	public GenericResponse<GestoreVO> removeGestore(HttpSession httpSession, Long idGestore) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::removeGestore] BEGIN");
		Date currentDate = new Date();
		
		Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(idGestore, currentDate);
		if(!gestoreOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(GESTORE_NOT_FOUND, idGestore));
		}
		
		// cerco domande accreditamento associate al gestore
		if (domandaRepository.existsNotCanceledByGestore(idGestore)) {
			LoggerUtil.warn(logger, String.format(
					"Il gestore con idGestore = [%d] è collegato a delle domande di accreditamento. Cancellazione non possibile.",
					idGestore));
			throw new FunctionalException(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.I004.name()));
		}
		
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		TsddrTGestore gestore = gestoreOpt.get();
		gestore.setDataFineValidita(currentDate);
		gestore.setDataDelete(currentDate);
		gestore.setIdUserDelete(idUtenteSessione);
		gestoreRepository.save(gestore);
		
		Optional<TsddrTLegaleRappresentante> legaleRappOpt = legaleRappresentanteRepository.findByIdGestore(idGestore, currentDate);
		
		if (legaleRappOpt.isPresent()) {
			TsddrTLegaleRappresentante legaleRapp = legaleRappOpt.get();
			legaleRapp.setDataFineValidita(currentDate);
			legaleRapp.setDataDelete(currentDate);
			legaleRapp.setIdUserDelete(idUtenteSessione);
			legaleRappresentanteRepository.save(legaleRapp);
		}
		
		List<TsddrRUtenteGestoreProfilo> rugpList = utenteGestoreProfiloRepository.findByIdGestore(idGestore, currentDate);
		
		if (CollectionUtils.isNotEmpty(rugpList)) {
			rugpList.forEach(rugp -> {
				rugp.setDataFineValidita(currentDate);
				rugp.setDataDelete(currentDate);
				rugp.setIdUserDelete(idUtenteSessione);
				utenteGestoreProfiloRepository.save(rugp);
			});
		}
		
		List<TsddrTImpianto> impianti = impiantoRepository.findByIdGestore(idGestore, currentDate);
		
		if (CollectionUtils.isNotEmpty(impianti)) {
			impianti.forEach(i -> {
				i.setDataFineValidita(currentDate);
				i.setDataDelete(currentDate);
				i.setIdUserDelete(idUtenteSessione);
				impiantoRepository.save(i);
			});
		}
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_GESTORI_ELIMINAZIONE_GESTORE,
				LogConstants.TSDDR_T_GESTORI, String.format("%d||%s", gestoreOpt.get().getIdGestore(), impianti.stream()
						.map(i -> String.valueOf(i.getIdImpianto())).collect(Collectors.joining(","))));

		GenericResponse<GestoreVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CollectionUtils.isEmpty(impianti) ? CodiceMessaggio.P004.name() : CodiceMessaggio.P005.name()), gestoreEntityMapper.mapEntityToVO(gestoreOpt.get()));
		LoggerUtil.debug(logger, "[GestoreServiceImpl::removeGestore] END");
		return response;
	}
	
	@Override
	public GenericResponse<Boolean> hasDomandeAccreditamento(Long idGestore) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::hasDomandeAccreditamento] BEGIN");
		GenericResponse<Boolean> response = GenericResponse.build(domandaRepository.existsNotCanceledByGestore(idGestore));
		LoggerUtil.debug(logger, "[GestoreServiceImpl::hasDomandeAccreditamento] END");
		return response;
	}
	
	@Override
	public GenericResponse<Boolean> hasImpianti(Long idGestore) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::hasImpianti] BEGIN");
		GenericResponse<Boolean> response = GenericResponse.build(impiantoRepository.existsByIdGestore(idGestore, new Date()));
		LoggerUtil.debug(logger, "[GestoreServiceImpl::hasImpianti] END");
		return response;
	}
	
	@Override
	public GenericResponse<DatiSoggVO> getRappresentanteLegale(String codFiscPartiva) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getRappresentanteLegale] BEGIN");
		Optional<TsddrTDatiSogg> datiSoggOpt = datiSoggRepository.findByCodFiscale(codFiscPartiva);
		if(!datiSoggOpt.isPresent()) {
			throw new FunctionalException(String.format("Rappresentante Legale non trovato con codFiscPartiva = [%s]", codFiscPartiva));
		}
		GenericResponse<DatiSoggVO> response = GenericResponse.build(datiSoggEntityMapper.mapEntityToVO(datiSoggOpt.get()));
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getRappresentanteLegale] END");
		return response;
	}
	
	@Override
	public GenericResponse<Boolean> existsGestore(String codFiscPartiva) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::existsGestore] BEGIN");
		Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByCodFiscPartiva(codFiscPartiva, new Date());
		GenericResponse<Boolean> response = GenericResponse.build(gestoreOpt.isPresent());
		LoggerUtil.debug(logger, "[GestoreServiceImpl::existsGestore] END");
		return response;
	}
	
	TsddrTReport tsddrTReport = null;

	public TsddrTReport getTsddrTReport(){
		if(tsddrTReport == null ) {
			tsddrTReport = tsddrTReportRepository.findOne(REPORT_ID);
		}
		return tsddrTReport;
	}

	@Override
	public GenericResponse<ReportVO> downloadReport(HttpSession httpSession, GestoreParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::downloadReport] BEGIN");
		//metodo per riportare i filtri su nome file
		String filtriSuffix = getFilterByRequest(parametriRicerca);
		
		GenericResponse<List<GestoreVO>> listaGestiori = getListaGestoriReport(httpSession, parametriRicerca, filtriSuffix);
				
        ExcelDataUtil excel = new ExcelDataUtil(getTsddrTReport().getDescReport(), filtriSuffix);
        ExcelSheet sheet = excel.addSheet(getTsddrTReport().getDescReport());
		sheet.addColumn("CF/PI");
        sheet.addColumn("Ragione Sociale");
        sheet.addColumn("Natura Giuridica");
        sheet.addColumn("DATA INIZIO Validità");
        sheet.addColumn("DATA FINE Validità");
        sheet.addColumn("Indirizzo sede legale");
        sheet.addColumn("Comune");
        sheet.addColumn("CAP");
        sheet.addColumn("Prov");
        sheet.addColumn("Telefono");
        sheet.addColumn("Email");
        sheet.addColumn("PEC");
        
        addContent(sheet, listaGestiori.getContent());
        
        ReportVO report = null;
		try {
			report = new ReportVO(excel.getFileName(), excel.getFile());
		} catch (IOException e) {
			LoggerUtil.warn(logger, "[GestoreServiceImpl::downloadReport] problemi in generazione report");
		}
        
		GenericResponse<ReportVO> response = new GenericResponse<ReportVO>(report);
		
		LoggerUtil.debug(logger, "[GestoreServiceImpl::downloadReport] END");
		return response;
	}

	private String getFilterByRequest(GestoreParametriRicerca parametriRicerca) {
		StringBuffer sb = new StringBuffer();
		if(parametriRicerca.getIdGestore()!=null) {
			TsddrTGestore gestore = gestoreRepository.getOne(parametriRicerca.getIdGestore());
			sb.append("_Gestore_").append(gestore.getRagSociale());
		}
		if(parametriRicerca.getCodFiscPartiva()!=null) {
			sb.append("_CF/PI_").append(parametriRicerca.getCodFiscPartiva());
		}
		if(parametriRicerca.getIdComune()!=null) {
			TsddrDComune comune = comuneRepository.getOne(parametriRicerca.getIdComune());
			sb.append("_Comune_").append(comune.getComune());
		}
		if(parametriRicerca.getIdProvincia()!=null) {
			TsddrDProvincia provincia = provinciaRepository.getOne(parametriRicerca.getIdProvincia());
			sb.append("_Provincia_").append(provincia.getSiglaProv());
		}
		if(parametriRicerca.getIdNaturaGiuridica()!=null) {
			TsddrDNaturaGiuridica provincia = naturaGiuridicaRepository.getOne(parametriRicerca.getIdNaturaGiuridica());
			sb.append("_Natura Giuridica_").append(provincia.getDescNaturaGiuridica());
		}
		if(sb.toString().length()>0) {
			sb.append("_");
		}
		
		return sb.toString();
	}

	private void addContent(ExcelSheet sheet, List<GestoreVO> content) {
		
		for(GestoreVO gestore : content){
			sheet.addDataToBody(gestore.getCodFiscPartiva(), 
					gestore.getRagSociale(),
					gestore.getNaturaGiuridica()!=null?gestore.getNaturaGiuridica().getDescNaturaGiuridica():"",
					gestore.getDataInizioValidita()!=null?formatDate.format(gestore.getDataInizioValidita()):"",
					gestore.getDataFineValidita()!=null?formatDate.format(gestore.getDataFineValidita()):"",
					gestore.getSedeLegale()!=null?gestore.getSedeLegale().getIndirizzo()!=null?gestore.getSedeLegale().getSedime()!=null?gestore.getSedeLegale().getSedime().getDescSedime()+" "+gestore.getSedeLegale().getIndirizzo():"":"":"",
					gestore.getSedeLegale()!=null?gestore.getSedeLegale().getComune()!=null?gestore.getSedeLegale().getComune().getComune():"":"",
					gestore.getSedeLegale()!=null?gestore.getSedeLegale().getCap()!=null?gestore.getSedeLegale().getCap():"":"",
					gestore.getSedeLegale()!=null?gestore.getSedeLegale().getComune()!=null?gestore.getSedeLegale().getComune().getProvincia()!=null?gestore.getSedeLegale().getComune().getProvincia().getSiglaProv():"":"":"",
					gestore.getTelefono()!=null?gestore.getTelefono():"",
					gestore.getEmail()!=null?gestore.getEmail():"",
					gestore.getPec()!=null?gestore.getPec():"");
		}
	}
	

	public GenericResponse<List<GestoreVO>> getListaGestoriReport(HttpSession httpSession, GestoreParametriRicerca parametriRicerca, String filtriSuffix) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getListaGestoriReport] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		List<TsddrTGestore> gestori = gestoreRepository.findAll(TsddrTGestoreSpecification.searchByParams(parametriRicerca, new Date()));
		List<GestoreVO> selectVO = gestoreEntityMapper.mapListEntityToListVO(gestori);
		
		String operation = LogConstants.TSDDR_REPORT_GESTORI_COMPLETO;
		String ids = "";
		if(filtriSuffix != null && filtriSuffix.length()>0) {
			ids = selectVO.stream().map(g -> String.valueOf(g.getIdGestore())).collect(Collectors.joining(","));
			operation = LogConstants.TSDDR_REPORT_GESTORI_FILTRATO;
		}
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operation,
				LogConstants.TSDDR_T_GESTORI,
				ids);
		GenericResponse<List<GestoreVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[GestoreServiceImpl::getListaGestoriReport] END");
		return response;
	}

	
}
