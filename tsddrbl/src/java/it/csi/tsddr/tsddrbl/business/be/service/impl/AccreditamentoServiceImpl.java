/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNaturaGiuridica;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoDomanda;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfiloPK;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProfPK;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDomanda;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.GenericException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.DatiSoggEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.DomandaEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.GestoreEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ProfiloEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.StatoDomandaEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.RepositoryUtil;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProfiliRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDStatoDomandaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRUtenteGestoreProfiloRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRUtenteProfRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDatiSoggRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDomandaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTGestoreRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTUtenteRepository;
import it.csi.tsddr.tsddrbl.business.be.service.AccreditamentoService;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.MailService;
import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.business.be.service.ProfiloService;
import it.csi.tsddr.tsddrbl.business.be.service.ValidazioneService;
import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.util.DateUtil;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.enums.Profilo;
import it.csi.tsddr.tsddrbl.util.enums.StatoDomanda;
import it.csi.tsddr.tsddrbl.util.enums.TipoProfilo;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelDataUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelSheet;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class AccreditamentoServiceImpl implements AccreditamentoService {
	
	private static Logger logger = Logger.getLogger(AccreditamentoServiceImpl.class);
	
	public SimpleDateFormat formatDate = new SimpleDateFormat(DateUtil.ddMMyyyy);
	
	private static final String DOMANDA_NOT_FOUND = "TsddrTDomanda non trovata con idDomanda = [%d]";

	private static Long REPORT_ID = 6L;	

	@Autowired
	private TsddrTReportRepository tsddrTReportRepository;
	@Autowired
	private AclUtil aclUtil;
	
	@Autowired
	private MessaggioService messaggioService;
	
	@Autowired
	private ValidazioneService validazioneService;
	
	@Autowired
	private CsiLogAuditService csiLogAuditService;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private ProfiloService profiloService;
	
	@Autowired
	private TsddrTDatiSoggRepository datiSoggRepository;
	
	@Autowired
	private TsddrTDomandaRepository domandaRepository;
	
	@Autowired
	private TsddrDStatoDomandaRepository statoDomandaRepository;
	
	@Autowired
	private TsddrTGestoreRepository gestoreRepository;
	
	@Autowired
	private TsddrDProfiliRepository tsddrDProfiliRepository;
	
	@Autowired
	private TsddrTUtenteRepository utenteRepository;
	
	@Autowired
	private TsddrRUtenteGestoreProfiloRepository utenteGestoreProfiloRepository;
	
	@Autowired
	private TsddrRUtenteProfRepository utenteProfRepository;
	
	@Autowired
	private DomandaEntityMapper domandaEntityMapper;
	
	@Autowired
	private StatoDomandaEntityMapper statoDomandaEntityMapper;
	
	@Autowired
	private GestoreEntityMapper gestoreEntityMapper;
	
	@Autowired
	private DatiSoggEntityMapper datiSoggEntityMapper;
	
	@Autowired
	private ProfiloEntityMapper profiloEntityMapper;
	
	@PersistenceContext 
    private EntityManager entityManager;

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLAccreditamento(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getACLAccreditamento] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		FunzionalitaProfiloVO aclAccreditamento = null;
		if(profiloService.isProfiloBO(idProfilo)) {
			aclAccreditamento = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.BO_001);
		} else {
			aclAccreditamento = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.FO_001);
			FunzionalitaProfiloVO aclNuovaDomanda = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.FO_002);
			FunzionalitaProfiloVO aclGestioneDomande = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.FO_003);
			aclAccreditamento.setInsert(aclNuovaDomanda.getInsert());
			aclAccreditamento.setUpdate(aclGestioneDomande.getUpdate());
			aclAccreditamento.setDelete(aclGestioneDomande.getDelete());
		}
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(aclAccreditamento);
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getACLAccreditamento] END");
		return response;
	}

	@Override
	public GenericResponse<List<DomandaAccreditamentoVO>> getListaDomande(HttpSession httpSession,
			DomandaAccreditamentoParametriRicercaVO parametriRicerca) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getListaDomande] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		String codFisc = SessionUtil.getCodiceFiscaleUtente(httpSession);
		Optional<TsddrTDatiSogg> datiSoggOpt = datiSoggRepository.findByCodFiscale(codFisc);
		String operazione;
		
		List<TsddrTDomanda> domande;
		if(profiloService.isProfiloBO(idProfilo)) {
			domande = this.getListaDomandeBO(httpSession, parametriRicerca, idProfilo, false);
			operazione = LogConstants.TSDDR_BO_RICERCA_RICHIESTE_ACCREDITAMENTO;
		} else {
			// FO
			domande = this.getListaDomandeFO(httpSession);
			operazione = LogConstants.TSDDR_FO_RICERCA_DOMANDE_ACCREDITAMENTO;
		}
		csiLogAuditService.traceCsiLogAudit(httpSession, datiSoggOpt.isPresent() ? String.valueOf(datiSoggOpt.get().getIdDatiSogg()) : codFisc, operazione,
				LogConstants.TSDDR_T_DOMANDE, domande.stream().map(d -> String.valueOf(d.getIdDomanda())).collect(Collectors.joining(", ")));
		GenericResponse<List<DomandaAccreditamentoVO>> response = GenericResponse.build(domandaEntityMapper.mapListEntityToListVO(domande));
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getListaDomande] END");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	private List<TsddrTDomanda> getListaDomandeBO(HttpSession httpSession, DomandaAccreditamentoParametriRicercaVO parametriRicerca, Long idProfilo, boolean bypassVerifica) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getListaDomandeBO] BEGIN");
		List<MessaggioVO> messaggiVO = this.verificaParametriRicerca(parametriRicerca, bypassVerifica);
		if(!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		List<TsddrTDomanda> domande = new ArrayList<>();
		
		try {
			boolean isIdDomandaPresent = false;
			boolean isIdStatiDomandaPresent = false;
			boolean isIdGestoriPresent = false;
			boolean isIdRichiedentiPresent = false;
			boolean isDataDomandaDalPresent = false;
			boolean isDataDomandaAlPresent = false;
			boolean isEnableAllGestori = false;
			
			Long idUtente = SessionUtil.getIdUtente(httpSession);
			List<TsddrTGestore> gestoriUtente = gestoreRepository.findGestoriByIdUtenteAndIdProfilo(idUtente, idProfilo, new Date());
			
			if(gestoriUtente.isEmpty()) {
				isEnableAllGestori = true;
			}
			
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ttd FROM TsddrTDomanda ttd ");
			
			if(!parametriRicerca.getIdStatiDomanda().isEmpty()) {
				queryBuilder.append("INNER JOIN ttd.statoDomanda tdsd ");
				isIdStatiDomandaPresent = true;
			}
			
			queryBuilder.append("INNER JOIN ttd.gestore ttg ");
			
			if(parametriRicerca.getIdGestori() != null && parametriRicerca.getIdGestori().size()>0) {//.isEmpty()) {// || !isEnableAllGestori) {
				isIdGestoriPresent = true;
			}
			
			if(!parametriRicerca.getIdRichiedenti().isEmpty()) {
				queryBuilder.append("INNER JOIN ttd.datiSogg ttds ");
				isIdRichiedentiPresent = true;
			}
			
			queryBuilder.append("WHERE ");
			queryBuilder.append(RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK);
			
			if(StringUtils.isNotBlank(parametriRicerca.getIdDomanda())) {
				queryBuilder.append("AND ttd.idDomanda = :idDomanda ");
				isIdDomandaPresent = true;
			}
			
			if(isIdStatiDomandaPresent) {
				queryBuilder.append("AND tdsd.idStatoDomanda IN ( :idStatiDomanda ) ");
				queryBuilder.append("AND ");
				queryBuilder.append(RepositoryUtil.TDSD_STATO_DOMANDA_VALIDITY_CHECK);
			}
			
			if(isIdGestoriPresent || !isEnableAllGestori) {
				queryBuilder.append("AND ttg.idGestore IN ( :idGestori ) ");
				queryBuilder.append("AND ");
				queryBuilder.append(RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK);
				
			}
			
			if(isIdRichiedentiPresent) {
				queryBuilder.append("AND ttds.idDatiSogg IN ( :idDatiSogg ) ");
				queryBuilder.append("AND ");
				queryBuilder.append(RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK);
			}
			
			if(parametriRicerca.getDataDomandaDal() != null) {
				queryBuilder.append("AND ttd.dataRichiesta >= :dataDomandaDal ");
				isDataDomandaDalPresent = true;
			}
			
			if(parametriRicerca.getDataDomandaAl() != null) {
				queryBuilder.append("AND ttd.dataRichiesta <= :dataDomandaAl ");
				isDataDomandaAlPresent = true;
			}
			if(bypassVerifica)
				queryBuilder.append("ORDER BY EXTRACT(YEAR FROM ttd.dataRichiesta) DESC, ttg.codFiscPartiva ASC, ttd.idDomanda DESC");
			else
				queryBuilder.append("ORDER BY ttd.idDomanda DESC");
			
			Query jpaQuery = entityManager.createQuery(queryBuilder.toString());
			
			setJpaQuery(jpaQuery, parametriRicerca, 
			        isIdDomandaPresent, 
			        isIdStatiDomandaPresent,
			        isIdRichiedentiPresent,
			        isDataDomandaDalPresent,
			        isDataDomandaAlPresent,
			        isIdGestoriPresent,
			        isEnableAllGestori,
			        gestoriUtente);
			domande = jpaQuery.getResultList();
			
		} catch (Exception e) {
			throw new GenericException(e);
		}
		
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getListaDomandeBO] END");
		return domande;
	}
	
	
	private void setJpaQuery(Query jpaQuery, DomandaAccreditamentoParametriRicercaVO parametriRicerca, 
	        boolean isIdDomandaPresent,
	        boolean isIdStatiDomandaPresent,
	        boolean isIdRichiedentiPresent,
	        boolean isDataDomandaDalPresent,
	        boolean isDataDomandaAlPresent,
	        boolean isIdGestoriPresent,
	        boolean isEnableAllGestori,
	        List<TsddrTGestore> gestoriUtente
	        ) {
	    if (isIdDomandaPresent) {
            Long idDomanda = Long.parseLong(parametriRicerca.getIdDomanda());
            jpaQuery.setParameter("idDomanda", idDomanda);
        }
        
        if (isIdStatiDomandaPresent) {
            jpaQuery.setParameter("idStatiDomanda", parametriRicerca.getIdStatiDomanda());
        }
        
        if (isIdRichiedentiPresent) {
            jpaQuery.setParameter("idDatiSogg", parametriRicerca.getIdRichiedenti());
        }
        
        setJpaDataDomandaDal(isDataDomandaDalPresent, parametriRicerca, jpaQuery);
        
        if (isDataDomandaAlPresent) {
            Date dataAl = null;
            try {
                dataAl = DateUtil.getStringAsDate(parametriRicerca.getDataDomandaAl(), DateUtil.YYYY_MM_DD);
            } catch(ParseException e) {
                throw new BadRequestException(String.format("Conversione fallita per dataAl = [%s]", dataAl));
            }
            jpaQuery.setParameter("dataDomandaAl", dataAl);
        }
        
        Set<Long> idGestori = new HashSet<>();
        if (isIdGestoriPresent) {
            idGestori.addAll(parametriRicerca.getIdGestori());
        }else if(!isEnableAllGestori) {
            for(TsddrTGestore gestoreUtente : gestoriUtente) {
                idGestori.add(gestoreUtente.getIdGestore());
            }
        }
        
        if(isIdGestoriPresent || !isEnableAllGestori) {
            jpaQuery.setParameter("idGestori", idGestori);
        }
        
        if(isIdGestoriPresent || !isEnableAllGestori || isIdStatiDomandaPresent) {
            jpaQuery.setParameter("currentDate", new Date());
        }
	}
	
	private void setJpaDataDomandaDal(boolean isDataDomandaDalPresent, DomandaAccreditamentoParametriRicercaVO parametriRicerca, Query jpaQuery) {
	    if (isDataDomandaDalPresent) {
            Date dataDal = null;
            try {
                dataDal = DateUtil.getStringAsDate(parametriRicerca.getDataDomandaDal(), DateUtil.YYYY_MM_DD);
            } catch(ParseException e) {
                throw new BadRequestException(String.format("Conversione fallita per dataDal = [%s]", parametriRicerca.getDataDomandaDal()));
            }
            jpaQuery.setParameter("dataDomandaDal", dataDal);
        }
	}
	
	private List<TsddrTDomanda> getListaDomandeFO(HttpSession httpSession) {
		String codFisc = SessionUtil.getCodiceFiscaleUtente(httpSession);
		List<TsddrTDomanda> domande = domandaRepository.findByCodiceFiscale(codFisc);
		return domande;
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> getDomanda(HttpSession httpSession, Long idDomanda) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getDomanda] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		String codFisc = SessionUtil.getCodiceFiscaleUtente(httpSession);
		String operazione;
		TsddrTDomanda domanda;
		if (profiloService.isProfiloBO(idProfilo)) {
			domanda = this.getDomandaBO(idDomanda);
			operazione = LogConstants.TSDDR_BO_CONSULTA_RICHIESTA_ACCREDITAMENTO;
		} else {
			// FO
			domanda = this.getDomandaFO(codFisc, idDomanda);
			operazione = LogConstants.TSDDR_FO_CONSULTA_DOMANDE_ACCREDITAMENTO;
		}
		DomandaAccreditamentoVO domandaAccreditamentoVO = domandaEntityMapper.mapEntityToVO(domanda);
		
		if(domanda.getStatoDomanda().getIdStatoDomanda() == StatoDomanda.ACCETTATA.getId()) {
		   Optional<TsddrRUtenteGestoreProfilo> tsddrRUtenteGestoreProfiloOpt = domandaRepository.findByIdDomandaAndIdUtenteAndIdGestoreAndIdProfiloAndIdTipoProfilo(domanda.getIdDomanda(), domanda.getDatiSogg().getUtente().getIdUtente(), domanda.getGestore().getIdGestore(), Profilo.UTENTE_FRONT_OFFICE.getId(), TipoProfilo.FRONT_OFFICE.getId(), new Date());
		   if(tsddrRUtenteGestoreProfiloOpt.isPresent()) {
		       ProfiloVO profiloVO = new ProfiloVO();
		       profiloVO.setIdProfilo(tsddrRUtenteGestoreProfiloOpt.get().getProfilo().getIdProfilo());
		       profiloVO.setDescProfilo(tsddrRUtenteGestoreProfiloOpt.get().getProfilo().getDescProfilo());
		       domandaAccreditamentoVO.setProfilo(profiloVO);
		   }
		}
		
		// log audit
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazione,
				LogConstants.TSDDR_T_DOMANDE, String.valueOf(domanda.getIdDomanda()));
		
		GenericResponse<DomandaAccreditamentoVO> response = GenericResponse.build(domandaAccreditamentoVO);
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getDomanda] END");
		return response;
	}
	
	private TsddrTDomanda getDomandaBO(Long idDomanda) {
		Optional<TsddrTDomanda> domandaOpt = domandaRepository.findByIdDomanda(idDomanda);
		if (domandaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format(DOMANDA_NOT_FOUND, idDomanda));
		}
		TsddrTDomanda domanda = domandaOpt.get();
		return domanda;
	}
	
	private TsddrTDomanda getDomandaFO(String codFisc, Long idDomanda) {
		Optional<TsddrTDomanda> domandaOpt = domandaRepository.findByCodiceFiscaleAndIdDomanda(codFisc, idDomanda);
		if (domandaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format("TsddrTDomanda non trovata con codiceFiscale = [%s] e idDomanda = [%d]", codFisc, idDomanda));
		}
		TsddrTDomanda domanda = domandaOpt.get();
		return domanda;
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> nuovaDomanda(HttpSession httpSession,
			DomandaAccreditamentoVO domandaVO) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::nuovaDomanda] BEGIN");
		// verifica dati domanda
		this.verificaDatiDomanda(httpSession, domandaVO);
		
		Date currentDate = new Date();
		UserInfo userInfo = SessionUtil.getUserInfo(httpSession);
		String codFisc = userInfo.getCodFisc();
		
		Long idGestore = domandaVO.getGestore().getIdGestore();
		
		Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(idGestore, currentDate);
		if (gestoreOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format("TsddrTGestore non trovato con idGestore = [%d]", idGestore));
		}

		List<TsddrTDomanda> domandeAttive = domandaRepository.findActiveByCodiceFiscaleAndGestore(codFisc, idGestore);
		if (CollectionUtils.isNotEmpty(domandeAttive)) {
			throw new BadRequestException(String.format(
					"Esiste gia' una domanda di accreditamento in lavorazione o accettata per l'utente codFisc = [%s] presso il gestore con idGestore = [%d]",
					codFisc, idGestore));
		}
		
		Optional<TsddrTDatiSogg> datiSoggOpt = datiSoggRepository.findByCodFiscale(codFisc);
		
		Long idUserInsert = null;
		TsddrTDatiSogg datiSogg;
		if (datiSoggOpt.isPresent()) {
			datiSogg = datiSoggOpt.get();
			
			idUserInsert = datiSogg.getUtente() != null ? datiSogg.getUtente().getIdUtente() : datiSogg.getIdDatiSogg();
			
			datiSogg.setEmail(domandaVO.getRichiedente().getEmail());
			datiSogg.setTelefono(domandaVO.getRichiedente().getTelefono());
			datiSogg.setIdUserUpdate(idUserInsert);
			datiSogg.setDataUpdate(currentDate);
			datiSogg = datiSoggRepository.save(datiSogg);
			
		} else {
			// prima domanda
			datiSogg = new TsddrTDatiSogg();
			datiSogg.setCodFiscale(codFisc);
			datiSogg.setCognome(userInfo.getCognome());
			datiSogg.setNome(userInfo.getNome());
			datiSogg.setEmail(domandaVO.getRichiedente().getEmail());
			datiSogg.setTelefono(domandaVO.getRichiedente().getTelefono());
			datiSogg = datiSoggRepository.save(datiSogg);
			
			idUserInsert = datiSogg.getIdDatiSogg();
			
			// aggiorno id_user_insert
			datiSogg.setIdUserInsert(idUserInsert);
			datiSogg.setDataInsert(currentDate);
			datiSogg = datiSoggRepository.save(datiSogg);
		}
		
		TsddrTDomanda domanda = domandaEntityMapper.mapVOToEntity(domandaVO);
		domanda.setDatiSogg(datiSogg);
		domanda.setGestore(gestoreOpt.get());
		domanda.setDataRichiesta(currentDate);
		domanda.setStatoDomanda(this.findStatoDomanda(StatoDomanda.IN_LAVORAZIONE, currentDate));
		domanda.setIdUserInsert(idUserInsert);
		domanda.setDataInsert(currentDate);
		// salvataggio domanda
		domanda = domandaRepository.save(domanda);
		
		String idDatiSogg = String.valueOf(datiSogg.getIdDatiSogg());
		String idDomanda = String.valueOf(domanda.getIdDomanda());
		
		// log audit
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_FO_INSERIMENTO_DOMANDA_ACCREDITAMENTO, LogConstants.TSDDR_T_DOMANDE, String.valueOf(domanda.getIdDomanda()));
		
		// invio email a operatori BO e FO
		CompletableFuture.allOf(mailService.sendEmailDomandaAccreditamentoValutazione(domanda),
				mailService.sendEmailDomandaAccreditamentoRegistrazione(domanda)).thenRunAsync(() -> {
					// log audit
					csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
							LogConstants.TSDDR_INVIO_EMAIL_DOMANDA_ACCREDITAMENTO, LogConstants.TSDDR_T_DOMANDE,
							idDomanda);
				});
				
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		GenericResponse<DomandaAccreditamentoVO> response = GenericResponse.build(messaggio, domandaEntityMapper.mapEntityToVO(domanda));
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::nuovaDomanda] END");
		return response;
	}
	
	private TsddrDStatoDomanda findStatoDomanda(StatoDomanda statoDomandaEnum, Date dataValidita) {
		Optional<TsddrDStatoDomanda> domandaOpt = statoDomandaRepository.findById(statoDomandaEnum.getId(), dataValidita);
		if (domandaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format("TsddrDStatoDomanda non trovato con idStatoDomanda = [%d]",
					statoDomandaEnum.getId()));
		}

		return domandaOpt.get();
	}
	
	private void verificaDatiDomanda(HttpSession httpSession, DomandaAccreditamentoVO domandaVO) {
		// verifica dati obbligatori
		this.verificaDatiObbligatori(httpSession, domandaVO);
		// verifica email
		validazioneService.verificaEmail(domandaVO.getRichiedente().getEmail());
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> aggiornaDomanda(HttpSession httpSession, Long idDomanda,
			DomandaAccreditamentoVO domandaVO) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::aggiornaDomanda] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		String operazione;
		String keyOperator;
		
		TsddrTDomanda domanda;
		if (profiloService.isProfiloBO(idProfilo)) {
			domanda = this.aggiornaDomandaBO(httpSession, idDomanda, domandaVO);
			operazione = LogConstants.TSDDR_BO_MODIFICA_RICHIESTA_ACCREDITAMENTO;
			keyOperator = String.valueOf(domanda.getStatoDomanda().getIdStatoDomanda());
		} else {
			// FO
			domanda = this.aggiornaDomandaFO(httpSession, idDomanda, domandaVO);
			operazione = LogConstants.TSDDR_FO_MODIFICA_DOMANDA_ACCREDITAMENTO;
			keyOperator = String.valueOf(domanda.getIdDomanda());
		}
		
		// log audit
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazione, LogConstants.TSDDR_T_DOMANDE, keyOperator);
		GenericResponse<DomandaAccreditamentoVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name()), domandaEntityMapper.mapEntityToVO(domanda));
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::aggiornaDomanda] BEGIN");
		return response;
	}
	
	private TsddrTDomanda aggiornaDomandaBO(HttpSession httpSession, Long idDomanda, DomandaAccreditamentoVO domandaVO) {
		Long idUser = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrTDomanda> domandaOpt = domandaRepository.findByIdDomanda(idDomanda);
		if (domandaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format(DOMANDA_NOT_FOUND, idDomanda));
		}
		
		TsddrTDomanda domanda = domandaOpt.get();
		if(domanda.getStatoDomanda().getDescStatoDomanda().equals(StatoDomanda.ACCETTATA.toString()) ||
				domanda.getStatoDomanda().getDescStatoDomanda().equals(StatoDomanda.RIFIUTATA.toString())) {
			domanda.setNotaLavorazione(domandaVO.getNotaLavorazione());
			domanda.setIdUserUpdate(idUser);
			domanda.setDataUpdate(new Date());
			domanda = domandaRepository.save(domanda);
		} else if(domanda.getStatoDomanda().getDescStatoDomanda().equals(StatoDomanda.IN_LAVORAZIONE.toString())) {
			Optional<TsddrDStatoDomanda> statoDomandaOpt = statoDomandaRepository.findById(domandaVO.getStato().getId(), new Date());
			if (statoDomandaOpt.isEmpty()) {
				throw new RecordNotFoundException(String.format("TsddrDStatoDomanda non trovato con idStatoDomanda = [%d]", domandaVO.getStato().getId()));
			}
			TsddrDStatoDomanda statoDomanda = statoDomandaOpt.get();
			if(statoDomanda.getDescStatoDomanda().equals(StatoDomanda.RIFIUTATA.toString())) {
				domanda.setStatoDomanda(statoDomandaOpt.get());
				domanda.setNotaLavorazione(domandaVO.getNotaLavorazione());
				domanda.setIdUserUpdate(idUser);
				domanda.setDataUpdate(new Date());
				domanda = domandaRepository.save(domanda);
				
				// invio email a FO
				mailService.sendEmailDomandaAccreditamentoRifutata(domanda).thenRunAsync(() -> {
					// log audit
					csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
							LogConstants.TSDDR_INVIO_EMAIL_LAVORAZIONE_DOMANDA, LogConstants.TSDDR_T_DOMANDE,
							idDomanda);
				});
					
			} else if(statoDomanda.getDescStatoDomanda().equals(StatoDomanda.ACCETTATA.toString())) {
				domanda.setStatoDomanda(statoDomandaOpt.get());
				domanda.setNotaLavorazione(domandaVO.getNotaLavorazione());
				domanda.setIdUserUpdate(idUser);
				domanda.setDataUpdate(new Date());
				domanda = domandaRepository.save(domanda);
				
				Optional<TsddrTUtente> utenteOpt = utenteRepository.findByIdDatiSogg(domanda.getDatiSogg().getIdDatiSogg());
				
				Optional<TsddrTDatiSogg> datiSoggOpt = datiSoggRepository.findById(domanda.getDatiSogg().getIdDatiSogg());
				checkDatiSoggOpt(domanda, datiSoggOpt);
				
                Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(domanda.getGestore().getIdGestore(), new Date());
                checkGestoreOpt(domanda, gestoreOpt);
                
                Optional<TsddrDProfilo> profiloOpt = tsddrDProfiliRepository.findByIdProfilo(domandaVO.getProfilo().getIdProfilo(), new Date());
                checkProfiloOpt(domandaVO, profiloOpt);
                
                TsddrTDatiSogg datiSogg = datiSoggOpt.get();
                TsddrTGestore gestore = gestoreOpt.get();
                TsddrDProfilo profilo = profiloOpt.get();
                TsddrTUtente utente = utenteOpt.isPresent() ? utenteOpt.get() : null;
                utente = checkUtenteOpt(utenteOpt, domanda, utente, profilo, idUser, datiSogg);
				
				this.saveUtenteGestoreProfilo(gestore, profilo, utente, idUser);
				
				// invio email a FO
				mailService.sendEmailDomandaAccreditamentoAccettata(domanda).thenRunAsync(() -> {
					// log audit
					csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
							LogConstants.TSDDR_INVIO_EMAIL_LAVORAZIONE_DOMANDA, LogConstants.TSDDR_T_DOMANDE,
							idDomanda);
				});

			}
		} else if(domanda.getStatoDomanda().getDescStatoDomanda().equals(StatoDomanda.ANNULLATA.toString())) {
			throw new BadRequestException("Impossibile impostare lo statoDomanda ad ANNULLATA");
		}
		
		return domanda;
	}
	
	private TsddrTUtente checkUtenteOpt(Optional<TsddrTUtente> utenteOpt, TsddrTDomanda domanda, TsddrTUtente tsddrTutente, TsddrDProfilo profilo, Long idUser, TsddrTDatiSogg datiSogg) {
	    if(!utenteOpt.isPresent()) {
            List<TsddrDProfilo> profiliAbilitati = this.getProfiliOperatoreInserimentoDomanda(domanda);
            if (!profiliAbilitati.stream().anyMatch(p -> p.getIdProfilo().longValue() == profilo.getIdProfilo().longValue())) {
                throw new BadRequestException(String.format("Il profilo con idProfilo = [%d] non è abilitato per l'utente", profilo.getIdProfilo()));   
            }
            
            if(tsddrTutente == null)
            	tsddrTutente = new TsddrTUtente();
            
            tsddrTutente.setDatiSogg(datiSogg);
            tsddrTutente.setDataInizioValidita(new Date());
            tsddrTutente.setDataFineValidita(null);
            tsddrTutente.setIdUserInsert(idUser);
            tsddrTutente.setDataInsert(new Date());
            tsddrTutente = utenteRepository.save(tsddrTutente);
            
            TsddrRUtenteProfPK utenteProfPK = new TsddrRUtenteProfPK();
            utenteProfPK.setIdProfilo(profilo.getIdProfilo());
            utenteProfPK.setIdUtente(tsddrTutente.getIdUtente());
            
            TsddrRUtenteProf utenteProf = new TsddrRUtenteProf();
            utenteProf.setId(utenteProfPK);
            utenteProf.setProfilo(profilo);
            utenteProf.setUtente(tsddrTutente);
            utenteProf.setDataInizioValidita(new Date());
            utenteProf.setDataFineValidita(null);
            utenteProf.setIdUserInsert(idUser);
            utenteProf.setDataInsert(new Date());
            utenteProfRepository.save(utenteProf);
            
        }
	    return tsddrTutente;
	}
	
	private void checkDatiSoggOpt(TsddrTDomanda domanda, Optional<TsddrTDatiSogg> datiSoggOpt) {
	    if (datiSoggOpt.isEmpty()) {
            throw new RecordNotFoundException(String.format("TsddrTDatiSogg non trovato con idDatiSogg = [%d]", domanda.getDatiSogg().getIdDatiSogg()));
        }
	}
	
	private void checkGestoreOpt(TsddrTDomanda domanda, Optional<TsddrTGestore> gestoreOpt) {
	    if (gestoreOpt.isEmpty()) {
            throw new RecordNotFoundException(String.format("TsddrTGestore non trovato con idGestore = [%d]", domanda.getGestore().getIdGestore()));
        }
	}
	
	private void checkProfiloOpt( DomandaAccreditamentoVO domandaVO, Optional<TsddrDProfilo> profiloOpt) {
	    if (profiloOpt.isEmpty()) {
            throw new RecordNotFoundException(String.format("TsddrDProfilo non trovato con idProfilo = [%d]", domandaVO.getProfilo().getIdProfilo()));
        }
    }
	
	private TsddrTDomanda aggiornaDomandaFO(HttpSession httpSession, Long idDomanda, DomandaAccreditamentoVO domandaVO) {
		// verifica email
		validazioneService.verificaEmail(domandaVO.getRichiedente().getEmail());
		
		String codFisc = SessionUtil.getCodiceFiscaleUtente(httpSession);
		
		Optional<TsddrTDomanda> domandaOpt = domandaRepository.findByCodiceFiscaleAndIdDomanda(codFisc, idDomanda);
		if (domandaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format(DOMANDA_NOT_FOUND, idDomanda));
		}
		
		TsddrTDomanda domanda = domandaOpt.get();
		
		if (domanda.getStatoDomanda().getIdStatoDomanda() != StatoDomanda.IN_LAVORAZIONE.getId()) {
			LoggerUtil.warn(logger, String.format("[AccreditamentoServiceImpl::aggiornaDomandaFO] Domanda idDomanda = [%d] non ha stato IN_LAVORAZIONE. Impossibile modificare.", idDomanda));
			throw new BadRequestException("è possibile modificare solamente le domande IN LAVORAZIONE");	
		}
		
		Date currentDate = new Date();
		Long idUserUpdate = domanda.getDatiSogg().getUtente() != null ? domanda.getDatiSogg().getUtente().getIdUtente() : domanda.getDatiSogg().getIdDatiSogg();
		
		domanda.getDatiSogg().setEmail(domandaVO.getRichiedente().getEmail());
		domanda.getDatiSogg().setTelefono(domandaVO.getRichiedente().getTelefono());
		domanda.setNotaUtente(domandaVO.getNotaUtente());
		domanda.setIdUserUpdate(idUserUpdate);
		domanda.setDataUpdate(currentDate);
		
		// salvataggio domanda
		domanda = domandaRepository.save(domanda);
		return domanda;
	}

	@Override
	public GenericResponse<DomandaAccreditamentoVO> annullaDomanda(HttpSession httpSession, Long idDomanda) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::annullaDomanda] BEGIN");
		String codFisc = SessionUtil.getCodiceFiscaleUtente(httpSession);
		
		Optional<TsddrTDomanda> domandaOpt = domandaRepository.findByCodiceFiscaleAndIdDomanda(codFisc, idDomanda);
		if (domandaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format(DOMANDA_NOT_FOUND, idDomanda));
		}
		
		TsddrTDomanda domanda = domandaOpt.get();
		
		if (domanda.getStatoDomanda().getIdStatoDomanda() != StatoDomanda.IN_LAVORAZIONE.getId()) {
			LoggerUtil.warn(logger, String.format("[AccreditamentoServiceImpl::annullaDomanda] Domanda idDomanda = [%d] non ha stato IN_LAVORAZIONE. Impossibile annullare.", idDomanda));
			throw new BadRequestException("è possibile annullare solamente le domande IN LAVORAZIONE");	
		}
		
		Date currentDate = new Date();
		Long idUserUpdate = domanda.getDatiSogg().getUtente() != null ? domanda.getDatiSogg().getUtente().getIdUtente() : domanda.getDatiSogg().getIdDatiSogg();
		
		domanda.setStatoDomanda(this.findStatoDomanda(StatoDomanda.ANNULLATA, currentDate));
		domanda.setIdUserUpdate(idUserUpdate);
		domanda.setDataUpdate(currentDate);
		
		// salvataggio domanda
		domanda = domandaRepository.save(domanda);
		
		// log audit
		csiLogAuditService.traceCsiLogAudit(httpSession, domanda.getDatiSogg().getIdDatiSogg(),
				LogConstants.TSDDR_FO_ANNULLA_DOMANDA_ACCREDITAMENTO, LogConstants.TSDDR_T_DOMANDE,
				domanda.getIdDomanda());
		GenericResponse<DomandaAccreditamentoVO> response = GenericResponse.build(domandaEntityMapper.mapEntityToVO(domanda));
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::annullaDomanda] END");
		return response;
	}

	@Override
	public GenericResponse<String> verificaDatiObbligatori(HttpSession httpSession, DomandaAccreditamentoVO domandaVO) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::verificaDatiObbligatori] BEGIN");
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatori(domandaVO);
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::verificaDatiObbligatori] END");
		return response;
	}

	private List<MessaggioVO> verificaDatiObbligatori(DomandaAccreditamentoVO domanda) {
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());

		if (StringUtils.isBlank(domanda.getRichiedente().getEmail())) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "richiedente.email"));
		}

		if (domanda.getGestore().getIdGestore() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "gestore.idGestore"));
		}

		return messaggiVO;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboGestori(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboGestori] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		List<TsddrTGestore> gestori = null;
		if (profiloService.isProfiloBO(idProfilo)) {
			LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboGestori] Profilo BO");
			Long idUtente = SessionUtil.getIdUtente(httpSession);
			List<TsddrTGestore> gestoriUtente = gestoreRepository.findGestoriByIdUtenteAndIdProfilo(idUtente, idProfilo, new Date());
			if(gestoriUtente.isEmpty()) {
				gestori = gestoreRepository.findGestoriWithDomandeAccreditamento(new Date());
			} else { 
				List<Long> idGestori = gestoriUtente.stream().mapToLong(TsddrTGestore::getIdGestore).boxed().collect(Collectors.toList());
				gestori = gestoreRepository.findGestoriWithDomandeAccreditamentoAndIdGestori(idGestori, new Date());
			}
		} else {
			LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboGestori] Profilo FO");
			Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
			gestori = this.getGestoriFO(idDatiSogg);
		}
		GenericResponse<List<SelectVO>> response = GenericResponse.build(gestoreEntityMapper.mapListEntityToListSelectVO(gestori));
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboGestori] END");
		return response;
	}
	
	private List<TsddrTGestore> getGestoriFO(Long idDatiSogg) {
		return gestoreRepository.findGestoriPerNuovaDomanda(idDatiSogg, new Date());
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getComboStatiDomanda(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboStatiDomanda] BEGIN");
		List<TsddrDStatoDomanda> statiDomanda = statoDomandaRepository.findStatoDomandaNot(StatoDomanda.ANNULLATA.getId(), new Date());
		List<SelectVO> selectVO = statoDomandaEntityMapper.mapListEntityToListSelectVO(statiDomanda);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboStatiDomanda] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getAllComboStatiDomanda(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getAllComboStatiDomanda] BEGIN");
		List<TsddrDStatoDomanda> statiDomanda = statoDomandaRepository.findAllStatoDomanda(new Date());
		List<SelectVO> selectVO = statoDomandaEntityMapper.mapListEntityToListSelectVO(statiDomanda);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getAllComboStatiDomanda] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboRichidenti(HttpSession session) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboRichidenti] BEGIN");
		List<TsddrTDatiSogg> richiedentiAccreditamento = datiSoggRepository.findRichiedentiAccreditamento();
		List<SelectVO> selectVO = datiSoggEntityMapper.mapListEntityRichiedenteToSelectVO(richiedentiAccreditamento);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboRichidenti] END");
		return response;
	}
	
	private List<MessaggioVO> verificaParametriRicerca(DomandaAccreditamentoParametriRicercaVO domandaAccreditamentoParametriRicercaVO, boolean bypassVerifica) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::verificaParametriRicerca] BEGIN");
		if(!bypassVerifica && !areParametriRicercaValid(domandaAccreditamentoParametriRicercaVO)) {
			throw new BadRequestException("Nessun parametro valorizzato");
		}
		List<MessaggioVO> messaggiVO = new ArrayList<>();

		if(StringUtils.isNotBlank(domandaAccreditamentoParametriRicercaVO.getIdDomanda())) {
			MessaggioVO messaggioVO = validazioneService.verificaIdDomandaRicerca(domandaAccreditamentoParametriRicercaVO.getIdDomanda());
			if(messaggioVO != null ) {
				messaggioVO.setCampo("Identificativo Domanda");
				messaggiVO.add(messaggioVO);
			}
		}
		
		if(domandaAccreditamentoParametriRicercaVO.getDataDomandaDal() != null && domandaAccreditamentoParametriRicercaVO.getDataDomandaAl() != null) {
			try {
				Date dataDal = DateUtil.getStringAsDate(domandaAccreditamentoParametriRicercaVO.getDataDomandaDal(), DateUtil.YYYY_MM_DD);
				Date dataAl = DateUtil.getStringAsDate(domandaAccreditamentoParametriRicercaVO.getDataDomandaAl(), DateUtil.YYYY_MM_DD);
				MessaggioVO messaggioVO = validazioneService.verificaValiditaDate(dataDal, dataAl);
				if(messaggioVO != null ) {
					messaggioVO.setCampo("Data domanda al");
					messaggiVO.add(messaggioVO);
				}
			} catch(ParseException e) {
				throw new BadRequestException(String.format("Conversione fallita per dataDal = [%s] dataAl = [%s] ", domandaAccreditamentoParametriRicercaVO.getDataDomandaDal(), domandaAccreditamentoParametriRicercaVO.getDataDomandaAl()));
			}
		}
		
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::verificaParametriRicerca] END");
		return messaggiVO;
	}

	private boolean areParametriRicercaValid(
			DomandaAccreditamentoParametriRicercaVO parametriRicerca) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::areParametriRicercaValid] BEGIN");
		boolean areParametriRicercaValid = StringUtils.isNotBlank(parametriRicerca.getIdDomanda()) 
				|| !parametriRicerca.getIdStatiDomanda().isEmpty()
				|| !parametriRicerca.getIdGestori().isEmpty()
				|| !parametriRicerca.getIdRichiedenti().isEmpty()
				|| parametriRicerca.getDataDomandaDal() != null
				|| parametriRicerca.getDataDomandaAl() != null;
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::areParametriRicercaValid] END");
		return areParametriRicercaValid;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboProfili(HttpSession session, Long idDomanda) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboProfili] BEGIN");
		List<SelectVO> selectVO = null;
		Optional<TsddrTDomanda> domandaOpt = domandaRepository.findByIdDomanda(idDomanda);
		if(domandaOpt.isEmpty()) {
			throw new RecordNotFoundException(String.format(DOMANDA_NOT_FOUND, idDomanda));
		}
		
		TsddrTDomanda domanda = domandaOpt.get();
		if(!domanda.getStatoDomanda().getDescStatoDomanda().equals(StatoDomanda.IN_LAVORAZIONE.toString())) { 
			throw new BadRequestException(String.format("La domanda con idDomanda = [%d] non è IN LAVORAZIONE", idDomanda));
		}
		
		List<TsddrDProfilo> profili = getProfiliOperatoreInserimentoDomanda(domanda);
		selectVO = profiloEntityMapper.mapListEntityToListSelectVO(profili);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getComboProfili] END");
		return response;
	}
	
	private List<TsddrDProfilo> getProfiliOperatoreInserimentoDomanda(TsddrTDomanda domanda) {
		List<TsddrDProfilo> profili = null;
		if(domanda.getDatiSogg().getUtente() != null) {
			profili = tsddrDProfiliRepository.findProfiliByIdUtente(domanda.getDatiSogg().getUtente().getIdUtente(), new Date());
		} else {
			profili = tsddrDProfiliRepository.findProfiliByIdTipoProfilo(Profilo.UTENTE_FRONT_OFFICE.getId(), TipoProfilo.FRONT_OFFICE.getId(), new Date()); 
		}
		return profili;
	}
	
	@Override
	public GenericResponse<String> getParametriFiltroApplicatiAccreditamento(HttpSession session,
			DomandaAccreditamentoParametriRicercaVO parametriRicerca) {
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getParametriFiltroApplicatiAccreditamento] BEGIN");
		this.verificaParametriRicerca(parametriRicerca, false);
		List<MessaggioVO> messaggiVO = this.verificaParametriRicerca(parametriRicerca, false);
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		
		StringBuilder parametriFiltroBuilder = new StringBuilder();
		
		if(StringUtils.isNotBlank(parametriRicerca.getIdDomanda())) {
			parametriFiltroBuilder.append("Identificativo Domanda = \"" + parametriRicerca.getIdDomanda() + "\" ");
		}
		
		if(!parametriRicerca.getIdStatiDomanda().isEmpty()) {
			List<TsddrDStatoDomanda> statiDomanda = statoDomandaRepository.findById(parametriRicerca.getIdStatiDomanda(), new Date());
			List<String> descStatiDomanda = statiDomanda.stream().map(TsddrDStatoDomanda::getDescStatoDomanda).collect(Collectors.toList());
			parametriFiltroBuilder.append("Stato Domanda = " + descStatiDomanda.stream().collect(Collectors.joining(",", "\"", "\"")));
		}
		
		if(!parametriRicerca.getIdGestori().isEmpty()) {
			if(parametriRicerca.getIdGestori().size() == 1) {
				parametriFiltroBuilder.append("Gestore = ");
			} else {
				parametriFiltroBuilder.append("Gestori = ");
			}
			
			List<TsddrTGestore> gestori = gestoreRepository.findByIdGestori(parametriRicerca.getIdGestori(), new Date());
			List<String> ragSocialeGestori = gestori.stream().map(TsddrTGestore::getRagSociale).collect(Collectors.toList());
			parametriFiltroBuilder.append(ragSocialeGestori.stream().collect(Collectors.joining(",", "\"", "\"")));
		}
		
		checkParametriRichiedenti(parametriRicerca, parametriFiltroBuilder);
		
		if(parametriRicerca.getDataDomandaDal() != null) {
			try {
				DateUtil.getStringAsDate(parametriRicerca.getDataDomandaDal(), DateUtil.YYYY_MM_DD);
				parametriFiltroBuilder.append("Data domanda dal = \"" + parametriRicerca.getDataDomandaDal() + "\"");
			} catch(ParseException e) {
				throw new BadRequestException(String.format("Conversione fallita per dataDal = [%s]", parametriRicerca.getDataDomandaDal()));
			}
		}
		
		if(parametriRicerca.getDataDomandaAl() != null) {
			try {
				DateUtil.getStringAsDate(parametriRicerca.getDataDomandaAl(), DateUtil.YYYY_MM_DD);
				parametriFiltroBuilder.append("Data domanda al = \"" + parametriRicerca.getDataDomandaAl() + "\"");
			} catch(ParseException e) {
				throw new BadRequestException(String.format("Conversione fallita per dataAl = [%s]", parametriRicerca.getDataDomandaAl()));
			}
		}
		
		GenericResponse<String> response = GenericResponse.build(parametriFiltroBuilder.toString());
		LoggerUtil.debug(logger, "[AreaPersonaleServiceImpl::getParametriFiltroApplicatiAccreditamento] END");
		return response;
	}
	
	private void checkParametriRichiedenti(DomandaAccreditamentoParametriRicercaVO parametriRicerca, StringBuilder parametriFiltroBuilder) {
	    if(!parametriRicerca.getIdRichiedenti().isEmpty()) {
            if(parametriRicerca.getIdRichiedenti().size() == 1) {
                parametriFiltroBuilder.append("Richiedente = ");
            } else {
                parametriFiltroBuilder.append("Richiedenti = ");
            }
            
            List<TsddrTDatiSogg> richiedenti = datiSoggRepository.findById(parametriRicerca.getIdRichiedenti());
            List<String> nomeCognomeRichiedenti = richiedenti.stream().map(richiedente -> richiedente.getCognome() + " " + richiedente.getNome()).collect(Collectors.toList());
            parametriFiltroBuilder.append(nomeCognomeRichiedenti.stream().collect(Collectors.joining(",", "\"", "\"")));
        }
	}
	
	private void saveUtenteGestoreProfilo(TsddrTGestore gestore, TsddrDProfilo profilo, TsddrTUtente utente, Long idUserInsert) {
	    TsddrRUtenteGestoreProfiloPK utenteGestoreProfiloPK = new TsddrRUtenteGestoreProfiloPK();
        utenteGestoreProfiloPK.setIdGestore(gestore.getIdGestore());
        utenteGestoreProfiloPK.setIdProfilo(profilo.getIdProfilo());
        utenteGestoreProfiloPK.setIdUtente(utente.getIdUtente());
        
        TsddrRUtenteGestoreProfilo utenteGestoreProfilo = new TsddrRUtenteGestoreProfilo();
        utenteGestoreProfilo.setId(utenteGestoreProfiloPK);
        utenteGestoreProfilo.setUtente(utente);
        utenteGestoreProfilo.setProfilo(profilo);
        utenteGestoreProfilo.setGestore(gestore);
        utenteGestoreProfilo.setDataInizioValidita(new Date());
        utenteGestoreProfilo.setDataFineValidita(null);
        utenteGestoreProfilo.setIdUserInsert(idUserInsert);
        utenteGestoreProfilo.setDataInsert(new Date());
        utenteGestoreProfiloRepository.save(utenteGestoreProfilo);
	}

	TsddrTReport tsddrTReport = null;

	public TsddrTReport getTsddrTReport(){
		if(tsddrTReport == null ) {
			tsddrTReport = tsddrTReportRepository.findOne(REPORT_ID);
		}
		return tsddrTReport;
	}
	
	@Override
	public GenericResponse<ReportVO> downloadReport(HttpSession session,
			DomandaAccreditamentoParametriRicercaVO parametriRicerca) {

		//metodo per riportare i filtri su nome file
		String filtriSuffix = getFilterByRequest(parametriRicerca);
		
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::downloadReport] BEGIN");
		GenericResponse<List<DomandaAccreditamentoVO>> listaGestiori = getListaDomandeReport(session, parametriRicerca, filtriSuffix);
		
		
        ExcelDataUtil excel = new ExcelDataUtil(getTsddrTReport().getDescReport(), filtriSuffix);
        ExcelSheet sheet = excel.addSheet(getTsddrTReport().getDescReport());
		sheet.addColumn("Anno Domanda");
        sheet.addColumn("Data Pres. Domanda");
        sheet.addColumn("ID Domanda");
        sheet.addColumn("Stato Domanda");
        sheet.addColumn("Gestore CF / PI - Denominazione");
        sheet.addColumn("Richiedente CF - Cognome Nome");
        sheet.addColumn("Telefono");
        sheet.addColumn("Email");
        sheet.addColumn("Nota Domanda");
        sheet.addColumn("Nota Lavorazione");
        
        addContent(sheet, listaGestiori.getContent());
        
        ReportVO report = null;
		try {
			report = new ReportVO(excel.getFileName(), excel.getFile());
		} catch (IOException e) {
			LoggerUtil.warn(logger, "[AccreditamentoServiceImpl::downloadReport] problemi in generazione report");
		}
        
		GenericResponse<ReportVO> response = new GenericResponse<ReportVO>(report);
		
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::downloadReport] END");
		return response;
	}

	private String getFilterByRequest(DomandaAccreditamentoParametriRicercaVO parametriRicerca) {
		// Verifico i filtri per inserirli nel nome file
		StringBuffer sb = new StringBuffer();
		if(parametriRicerca.getIdGestori()!=null && parametriRicerca.getIdGestori().size() >0) {
			TsddrTGestore gestore = gestoreRepository.getOne(parametriRicerca.getIdGestori().get(0));
			sb.append("_Gestore_").append(gestore.getRagSociale());
		}
		if(parametriRicerca.getIdDomanda()!=null) {
			sb.append("_ID_").append(parametriRicerca.getIdDomanda());
		}
		if(parametriRicerca.getIdStatiDomanda()!=null && parametriRicerca.getIdStatiDomanda().size()>0) {
			TsddrDStatoDomanda stato = statoDomandaRepository.getOne(parametriRicerca.getIdStatiDomanda().get(0));
			sb.append("_Stato_").append(stato.getDescStatoDomanda());
		}
		if(parametriRicerca.getIdRichiedenti()!=null && parametriRicerca.getIdRichiedenti().size()>0) {
			TsddrTDatiSogg richiedente = datiSoggRepository.getOne(parametriRicerca.getIdRichiedenti().get(0));
			sb.append("_Richiedente_").append(richiedente.getNome() + " " + richiedente.getCognome());
		}
		if(parametriRicerca.getDataDomandaDal()!=null) {
			sb.append("_Data dal_").append(parametriRicerca.getDataDomandaDal());
		}
		if(parametriRicerca.getDataDomandaAl()!=null) {
			sb.append("_Data al_").append(parametriRicerca.getDataDomandaAl());
		}
		if(sb.toString().length()>0) {
			sb.append("_");
		}
		
		return sb.toString();
	}

	private void addContent(ExcelSheet sheet, List<DomandaAccreditamentoVO> list) {
		// imposto il contenuto
		for(DomandaAccreditamentoVO domanda : list){
			sheet.addDataToBody(
					new SimpleDateFormat("yyyy").format(domanda.getDataRichiesta()),
					formatDate.format(domanda.getDataRichiesta()),
					domanda.getIdDomanda(),
					domanda.getStato().getDesc(),
					domanda.getGestore().getCodFiscPartiva() + " - " + domanda.getGestore().getRagSociale(),
					domanda.getRichiedente().getCodFiscale() + " - " + domanda.getRichiedente().getCognome() + " " + domanda.getRichiedente().getNome(),
					domanda.getRichiedente().getTelefono(),
					domanda.getRichiedente().getEmail(),
					domanda.getNotaUtente(),
					domanda.getNotaLavorazione());
		}
	}

	private GenericResponse<List<DomandaAccreditamentoVO>> getListaDomandeReport(HttpSession httpSession,
			DomandaAccreditamentoParametriRicercaVO parametriRicerca, String filtriSuffix) {
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getListaDomandeReport] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		String codFisc = SessionUtil.getCodiceFiscaleUtente(httpSession);
		Optional<TsddrTDatiSogg> datiSoggOpt = datiSoggRepository.findByCodFiscale(codFisc);
		String operazione;
		
		List<TsddrTDomanda> domande;
		String ids = "";
		if(profiloService.isProfiloBO(idProfilo)) {
			// BO
			domande = this.getListaDomandeBO(httpSession, parametriRicerca, idProfilo, true);
			if(filtriSuffix != null && filtriSuffix.length()>0) {
				operazione = LogConstants.TSDDR_BO_REPORT_RICHIESTE_ACC_FILTRATO;
				ids = domande.stream().map(d -> String.valueOf(d.getIdDomanda())).collect(Collectors.joining(", "));
			}else {
				operazione = LogConstants.TSDDR_BO_REPORT_RICHIESTE_ACC_COMPLETO;				
			}
		} else {
			// FO
			domande = this.getListaDomandeFO(httpSession);
			operazione = LogConstants.TSDDR_FO_REPORT_RICHIESTE_ACC;
		}
		csiLogAuditService.traceCsiLogAudit(httpSession, datiSoggOpt.isPresent() ? String.valueOf(datiSoggOpt.get().getIdDatiSogg()) : codFisc, operazione,
				LogConstants.TSDDR_T_DOMANDE, ids);
		
		GenericResponse<List<DomandaAccreditamentoVO>> response = GenericResponse.build(domandaEntityMapper.mapListEntityToListVO(domande));
		LoggerUtil.debug(logger, "[AccreditamentoServiceImpl::getListaDomandeReport] END");
		return response;
	}

}
