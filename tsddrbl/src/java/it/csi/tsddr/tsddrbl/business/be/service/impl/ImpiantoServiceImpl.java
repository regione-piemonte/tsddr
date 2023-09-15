/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import static java.util.Comparator.comparing;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNazione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSedime;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoProvvedimento;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTAtto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSottoLinea;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.GenericException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.GenericLineaMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.AttoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ImpiantoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.LineaEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.SottoLineaEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.TipoProvvedimentoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.RepositoryUtil;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDComuneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDNazioneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProvinciaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDSedimeRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDStatoImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoIndirizzoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoProvvedimentoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRImpiantoLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTAttoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDichAnnualeRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTGestoreRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTIndirizzoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTPrevConsRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTSottoLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.ImpiantoService;
import it.csi.tsddr.tsddrbl.util.Constants;
import it.csi.tsddr.tsddrbl.util.DateUtil;
import it.csi.tsddr.tsddrbl.util.EntityUtil;
import it.csi.tsddr.tsddrbl.util.ImpiantoUtil;
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
import it.csi.tsddr.tsddrbl.vo.impianto.AttoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.CheckImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.LineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.SottoLineaVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

@Service
@Transactional
public class ImpiantoServiceImpl implements ImpiantoService {
	
	private static Logger logger = Logger.getLogger(GestoreServiceImpl.class);
	
	public SimpleDateFormat formatDate = new SimpleDateFormat(DateUtil.ddMMyyyy);
	private static Long REPORT_ID = 5L;	
	
	private static final String TRIPLE_S_FORMAT = "%s, %s, %s"; 
	private static final String DOUBLE_S_FORMAT = "%s, %s"; 
	private static final String TRIPLE_D_FORMAT = "%d, %d, %d"; 
	private static final String DOUBLE_D_FORMAT = "%d, %d"; 
	private static final String DOUBLE_D_FORMAT_WITH_COMMA = "%d, %d, ";
	private static final String IMPIANTO_NOT_FOUND = "TsddrTImpianto non trovato con idImpianto = [%d]";
	
	
	@SuppressWarnings("el-syntax")
	@Value("${id.istat.nazione.corrente:" + Constants.ID_ISTAT_NAZIONE_CORRENTE_DEFAULT + "}")
	private String idIstatNazioneCorrente;
	
	@Autowired
	private TsddrTReportRepository tsddrTReportRepository;
	
	@Autowired
	private AclUtil aclUtil;
	
	@PersistenceContext 
    private EntityManager entityManager;
	
	@Autowired
	private TsddrTImpiantoRepository impiantoRepository;
	
	@Autowired
	private TsddrRImpiantoLineaRepository impiantoLineaRepository;
	
	@Autowired
	private TsddrTAttoRepository attoRepository;
	
	@Autowired
	private TsddrDTipoProvvedimentoRepository tipoProvvedimentoRepository;
	
	@Autowired
	private TsddrTGestoreRepository gestoreRepository;

	@Autowired
	private TsddrTDichAnnualeRepository tsddrTDichAnnualeRepository;
	
	@Autowired
	private TsddrTPrevConsRepository tsddrTPrevConsRepository;
	
	@Autowired
	private TsddrDComuneRepository comuneRepository;
	
	@Autowired
	private TsddrDProvinciaRepository provinciaRepository;
	
	@Autowired
	private TsddrDTipoImpiantoRepository tipoImpiantoRepository;
	
	@Autowired
	private TsddrDStatoImpiantoRepository statoImpiantoRepository;
	
	@Autowired
	private TsddrTLineaRepository lineaRepository;
	
	@Autowired
	private TsddrTSottoLineaRepository sottolineaRepository;
	
	@Autowired
	private TsddrDSedimeRepository sedimeRepository;
	
	@Autowired
	private TsddrDTipoIndirizzoRepository tipoIndirizzoRepository;
	
	@Autowired
	private TsddrTIndirizzoRepository indirizzoRepository;
	
	@Autowired
	private TsddrDNazioneRepository nazioneRepository;
	
	@Autowired
	private TipoProvvedimentoEntityMapper tipoProvvedimentoEntityMapper;
	
	@Autowired
	private AttoEntityMapper attoEntityMapper;
	
	@Autowired
	private ImpiantoEntityMapper impiantoEntityMapper;
	
	@Autowired
	private LineaEntityMapper lineaEntityMapper;
	
	@Autowired
	private SottoLineaEntityMapper sottoLineaEntityMapper;
	
	@Autowired
	private GenericLineaMapper genericLineaMapper;
	
	@Autowired
	private MessaggioServiceImpl messaggioService;
	
	@Autowired
	private CsiLogAuditService csiLogAuditService;
	
	@Autowired
	private ImpiantoUtil impiantoUtil;

	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLImpianti(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getACLUtente] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.AM_007));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getACLUtente] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getComboLinee() {
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboLinee] BEGIN");
		List<SelectVO> selectVO = new ArrayList<>();
		
		List<TsddrTLinea> linee = lineaRepository.findLineeNuovaStrutturaImpianto(new Date());
		List<TsddrTSottoLinea> sottoLinee = sottolineaRepository.findSottoLineeNuovaStrutturaImpianto(new Date());
		
		selectVO.addAll(lineaEntityMapper.mapListEntityToListSelectVO(linee));
		selectVO.addAll(sottoLineaEntityMapper.mapListEntityToListSelectVO(sottoLinee));
		
		Collections.sort(selectVO, comparing(SelectVO::getDescrizione));
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboLinee] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboLineeImpianto(Long idImpianto) {
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboLineeImpianto] BEGIN");
		List<SelectVO> selectVO = new ArrayList<>();
		List<TsddrRImpiantoLinea> impiantoLinee = impiantoLineaRepository.findByIdImpianto(idImpianto);
		List<Long> idLineeAssegnate = new ArrayList<>();
		List<Long> idSottoLineeAssegnate = new ArrayList<>();
		
		for(TsddrRImpiantoLinea impiantoLinea : impiantoLinee) {
			if(impiantoLinea.getLinea() != null) {
				idLineeAssegnate.add(impiantoLinea.getLinea().getIdLinea());
			} else if(impiantoLinea.getSottoLinea() != null) {
				idSottoLineeAssegnate.add(impiantoLinea.getSottoLinea().getIdSottoLinea());
			}
		}
		List<TsddrTLinea> linee = new ArrayList<>();
		List<TsddrTSottoLinea> sottoLinee = new ArrayList<>();
		
		if(!idLineeAssegnate.isEmpty()) {
			linee = lineaRepository.findLineeNuovaStrutturaImpianto(idLineeAssegnate, new Date());
		} else {
			linee = lineaRepository.findLineeNuovaStrutturaImpianto(new Date());
		}
		
		if(!idSottoLineeAssegnate.isEmpty()) {
			sottoLinee = sottolineaRepository.findSottoLineeNuovaStrutturaImpianto(idSottoLineeAssegnate, new Date());
		} else {
			sottoLinee = sottolineaRepository.findSottoLineeNuovaStrutturaImpianto(new Date());
		}
		selectVO.addAll(lineaEntityMapper.mapListEntityToListSelectVO(linee));
		selectVO.addAll(sottoLineaEntityMapper.mapListEntityToListSelectVO(sottoLinee));
		Collections.sort(selectVO, comparing(SelectVO::getDescrizione));
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboLineeImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboTipoProvvedimento() {
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboTipoProvvedimento] BEGIN");
		List<TsddrDTipoProvvedimento> tipiProvvedimento = tipoProvvedimentoRepository.findTipiProvvedimento(new Date());
		List<SelectVO> selectVO = tipoProvvedimentoEntityMapper.mapListEntityToListSelectVO(tipiProvvedimento);
		GenericResponse<List<SelectVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getComboTipoProvvedimento] END");
		return response;
	}

	@Override
	public GenericResponse<String> getParametriFiltoApplicati(ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[StatoImpiantoServiceImpl::getParametriFiltoApplicati] BEGIN");
		this.verificaParametri(parametriRicerca);
		StringBuilder parametriFiltroBuilder = new StringBuilder();
		
		checkGestoreFiltro(parametriRicerca, parametriFiltroBuilder);
		
		
		if(StringUtils.isNotBlank(parametriRicerca.getDenominazione())) {
			parametriFiltroBuilder.append("Denominazione = \"" + parametriRicerca.getDenominazione() + "\" ");
		}
		if(parametriRicerca.getIdComune() != null) {
			Optional<TsddrDComune> comuneOpt = comuneRepository.findByIdComune(parametriRicerca.getIdComune(), new Date());
			if(comuneOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDComune trovato con idComune = [%d]", parametriRicerca.getIdComune()));
			}
			parametriFiltroBuilder.append("Comune = \"" + comuneOpt.get().getComune() + "\" ");
		}
		if(parametriRicerca.getIdProvincia() != null) {
			Optional<TsddrDProvincia> provinciaOpt = provinciaRepository.findProvinciaById(parametriRicerca.getIdProvincia(), new Date());
			if(provinciaOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDProvincia trovato con idProvincia = [%d]", parametriRicerca.getIdProvincia()));
			}
			parametriFiltroBuilder.append("Provincia/CM = \"" + provinciaOpt.get().getDescProvincia() + "\" ");
		}
		if(parametriRicerca.getIdTipoImpianto() != null) {
			Optional<TsddrDTipoImpianto> tipoImpiantoOpt = tipoImpiantoRepository.findTipoImpiantoById(parametriRicerca.getIdTipoImpianto(), new Date());
			if(tipoImpiantoOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDTipoImpianto trovato con idTipoImpianto = [%d]", parametriRicerca.getIdTipoImpianto()));
			}
			parametriFiltroBuilder.append("Tipo Impianto = \"" + tipoImpiantoOpt.get().getDescTipoImpianto() + "\" ");
		}
		if(parametriRicerca.getIdStatoImpianto() != null) {
			Optional<TsddrDStatoImpianto> statoImpiantoOpt = statoImpiantoRepository.findStatoImpiantoById(parametriRicerca.getIdStatoImpianto(), new Date());
			if(statoImpiantoOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrDStatoImpianto trovato con idStatoImpianto = [%d]", parametriRicerca.getIdStatoImpianto()));
			}
			parametriFiltroBuilder.append("Stato Impianto = \"" + statoImpiantoOpt.get().getDescStatoImpianto() + "\" ");
		}
		GenericResponse<String> response = GenericResponse.build(parametriFiltroBuilder.toString());
		LoggerUtil.debug(logger, "[UtenteServiceImpl::getParametriFiltroApplicati] END");
		return response;
	}
	
	private void checkGestoreFiltro(ImpiantoParametriRicerca parametriRicerca, StringBuilder parametriFiltroBuilder) {
	    if(parametriRicerca.getIdGestore() != null) {
            Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(parametriRicerca.getIdGestore(), new Date());
            if(gestoreOpt.isEmpty()) {
                throw new BadRequestException(String.format("Nessun TsddrTGestore trovato con idGestore = [%d]", parametriRicerca.getIdGestore()));
            }
            parametriFiltroBuilder.append("Gestore = \"" + gestoreOpt.get().getRagSociale() + "\" ");
        }
	}

	@Override
	public GenericResponse<List<ImpiantoVO>> getListaImpianti(HttpSession httpSession, ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getListaImpianti] BEGIN");
		this.verificaParametri(parametriRicerca);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		List<TsddrTImpianto> impianti = getImpianti(parametriRicerca);
		
		if(impianti.isEmpty()) {
			MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.A002.name());
			throw new FunctionalException(String.format("Nessun utente trovato con i parametri di ricerca inseriti"), messaggioVO);
		}
		
		List<ImpiantoVO> impiantiVO = impiantoEntityMapper.mapListEntityToListVOIgnoreFields(impianti);
		for(ImpiantoVO impiantoVO : impiantiVO) {
			impiantoVO.setDeletable(this.isImpiantoDeletable(impiantoVO));
		}
		
		List<Long> idImpiantiVisualizzati = impianti
				.stream()
				.map(TsddrTImpianto::getIdImpianto)
				.collect(Collectors.toList());
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_RICERCA_IMPIANTI, LogConstants.TSDDR_T_IMPIANTI, StringUtils.join(idImpiantiVisualizzati, ","));
		
		GenericResponse<List<ImpiantoVO>> response = GenericResponse.build(impiantiVO);
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getListaImpianti] END");
		return response;
	}
	
	private List<TsddrTImpianto> getImpianti(ImpiantoParametriRicerca parametriRicerca) {
		List<TsddrTImpianto> impianti = new ArrayList<>();
		try {
			boolean isIdGestorePresent = false;
			boolean isDenominazionePresent = false;
			boolean isIdProvinciaPresent = false;
			boolean isIdComunePresent = false;
			boolean isIdTipoImpiantoPresent = false;
			boolean isIdStatoImpiantoPresent = false;
			
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ttimp FROM TsddrTImpianto ttimp ");
			queryBuilder.append("INNER JOIN ttimp.gestore ttg ");
			
			if(parametriRicerca.getIdGestore() != null) {
				isIdGestorePresent = true;
			}
			
			if(parametriRicerca.getIdComune() != null || parametriRicerca.getIdProvincia() != null) {
				queryBuilder.append("INNER JOIN ttimp.indirizzo ttindr ");
			}
			
			if(parametriRicerca.getIdComune() != null) {
				queryBuilder.append("INNER JOIN ttindr.comune tdc ");
				isIdComunePresent = true;
			}
			
			if(isIdComunePresent && parametriRicerca.getIdProvincia() != null) {
				queryBuilder.append("INNER JOIN tdc.provincia tdprv ");
				isIdProvinciaPresent = true;
			} else if (parametriRicerca.getIdProvincia() != null) {
				queryBuilder.append("INNER JOIN ttindr.comune tdc ");
				queryBuilder.append("INNER JOIN tdc.provincia tdprv ");
				isIdProvinciaPresent = true;
			}
			
			if(parametriRicerca.getIdTipoImpianto() != null) {
				queryBuilder.append("INNER JOIN ttimp.tipoImpianto tdti ");
				isIdTipoImpiantoPresent = true;
			}
			
			if(parametriRicerca.getIdStatoImpianto() != null) {
				queryBuilder.append("INNER JOIN ttimp.statoImpianto tdsi ");
				isIdStatoImpiantoPresent = true;
			}
			
			queryBuilder.append("WHERE ");
			queryBuilder.append(RepositoryUtil.TTIMP_IMPIANTO_DELETE_VALIDITY_CHECK);
			
			if(StringUtils.isNotBlank(parametriRicerca.getDenominazione())) {
				queryBuilder.append("AND UPPER(ttimp.denominazione) = UPPER(:denominazione) ");
				isDenominazionePresent = true;
			}

			valorizeQueryBuilder(
			        isIdGestorePresent, 
			        isIdComunePresent,
			        isIdProvinciaPresent,
			        isIdTipoImpiantoPresent,
			        isIdStatoImpiantoPresent,
			        queryBuilder);
			
			queryBuilder.append("ORDER BY ttg.codFiscPartiva, ttimp.denominazione ASC");
			
			Query jpaQuery = entityManager.createQuery(queryBuilder.toString());
			valorizeJpaParameters(
			        isIdGestorePresent, 
			        isDenominazionePresent,
			        isIdProvinciaPresent,
			        isIdComunePresent,
			        isIdTipoImpiantoPresent,
			        isIdStatoImpiantoPresent,
			        jpaQuery,
			        parametriRicerca);
			impianti = jpaQuery.getResultList();
		} catch(Exception e) {
			throw new GenericException(e);
		}
		return impianti;
	}
	
	private void valorizeQueryBuilder(
	        boolean isIdGestorePresent, 
	        boolean isIdComunePresent,
	        boolean isIdProvinciaPresent,
	        boolean isIdTipoImpiantoPresent,
	        boolean isIdStatoImpiantoPresent,
	        StringBuilder queryBuilder) {
	    if(isIdGestorePresent) {
            queryBuilder.append("AND ttg.idGestore = :idGestore ");
            queryBuilder.append("AND ");
            queryBuilder.append(RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK);
        }
        
        if(isIdComunePresent) {
            queryBuilder.append("AND tdc.idComune = :idComune ");
            queryBuilder.append("AND ");
            queryBuilder.append(RepositoryUtil.TDC_COMUNE_VALIDITY_CHECK);
        }
        
        if(isIdProvinciaPresent) {
            queryBuilder.append("AND tdprv.idProvincia = :idProvincia ");
            queryBuilder.append("AND ");
            queryBuilder.append(RepositoryUtil.TDPRV_PROVINCIA_VALIDITY_CHECK);
        }
        
        if(isIdTipoImpiantoPresent) {
            queryBuilder.append("AND tdti.idTipoImpianto = :idTipoImpianto ");
            queryBuilder.append("AND ");
            queryBuilder.append(RepositoryUtil.TDTI_TIPO_IMPIANTO_VALIDITY_CHECK);
        }
        
        if(isIdStatoImpiantoPresent) {
            queryBuilder.append("AND tdsi.idStatoImpianto = :idStatoImpianto ");
            queryBuilder.append("AND ");
            queryBuilder.append(RepositoryUtil.TDSI_STATO_IMPIANTO_VALIDITY_CHECK);
        }
        
        if(isIdComunePresent || isIdProvinciaPresent) {
            queryBuilder.append("AND ");
            queryBuilder.append(RepositoryUtil.TTINDR_INDIRIZZO_DELETE_VALIDITY_CHECK);
        }

	}
	
	private void valorizeJpaParameters(
	        boolean isIdGestorePresent, 
	        boolean isDenominazionePresent,
	        boolean isIdProvinciaPresent,
	        boolean isIdComunePresent,
	        boolean isIdTipoImpiantoPresent,
	        boolean isIdStatoImpiantoPresent,
	        Query jpaQuery,
	        ImpiantoParametriRicerca parametriRicerca) {
	    if(isDenominazionePresent) {
            jpaQuery.setParameter("denominazione", parametriRicerca.getDenominazione());
        }
        
        if(isIdGestorePresent) {
            jpaQuery.setParameter("idGestore", parametriRicerca.getIdGestore());
        }
        
        if(isIdComunePresent) {
            jpaQuery.setParameter("idComune", parametriRicerca.getIdComune());
        }
        
        if(isIdProvinciaPresent) {
            jpaQuery.setParameter("idProvincia", parametriRicerca.getIdProvincia());
        }
        
        if(isIdTipoImpiantoPresent) {
            jpaQuery.setParameter("idTipoImpianto", parametriRicerca.getIdTipoImpianto());
        }
        
        if(isIdStatoImpiantoPresent) {
            jpaQuery.setParameter("idStatoImpianto", parametriRicerca.getIdStatoImpianto());
        }
        
        if(isIdTipoImpiantoPresent || isIdStatoImpiantoPresent || isIdComunePresent || isIdProvinciaPresent) {
            jpaQuery.setParameter("currentDate", new Date());
        }
	}

	@Override
	public GenericResponse<ImpiantoVO> addImpianto(HttpSession httpSession, ImpiantoVO impiantoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		messaggiVO.addAll(this.verificaDatiObbligatoriImpianto(impiantoVO));
		messaggiVO.addAll(this.verificaDatiObbligatoriAddImpianto(impiantoVO));
		messaggiVO.addAll(this.verificaDatiObbligatoriIndirizzo(impiantoVO.getIndirizzo()));
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		
		// Creo indirizzo
		TsddrTIndirizzo indirizzo = new TsddrTIndirizzo();
		indirizzo.setVersione(1L);
		Optional<TsddrDTipoIndirizzo> tipoIndirizzoOpt = tipoIndirizzoRepository.findByIdTipoIndirizzo(TipoIndirizzo.SITO_DELL_IMPIANTO.getId(), new Date());
		if(tipoIndirizzoOpt.isEmpty()) {
			throw new BadRequestException(String.format("Nessun TsddrDTipoIndirizzo trovato con idTipoIndirizzo = [%d]", TipoIndirizzo.SITO_DELL_IMPIANTO.getId()));
		}
		indirizzo.setTipoIndirizzo(tipoIndirizzoOpt.get());
		indirizzo.setIndirizzo(impiantoVO.getIndirizzo().getIndirizzo());
		indirizzo.setCap(impiantoVO.getIndirizzo().getCap());
		this.verificaEPopolaIndirizzoImpianto(indirizzo, impiantoVO.getIndirizzo());
		
		// Creo impianto
		TsddrTImpianto impianto = new TsddrTImpianto();
		impianto.setIndirizzo(indirizzo);
		this.verificaEPopolaImpianto(impianto, impiantoVO);
		impianto.setDenominazione(impiantoVO.getDenominazione());
		impianto.setIdUserInsert(idUtenteSessione);
		impianto.setDataInsert(new Date());
		impianto = impiantoRepository.save(impianto);
		
		// add linee
		for(GenericLineaVO genericLineaVO : impiantoVO.getLinee()) {
			if(genericLineaVO.getIdLinea() != null) {
				LineaVO lineaVO = genericLineaMapper.mapGenericLineaVOToLineaVO(genericLineaVO);
				this.addLineaImpianto(httpSession, impianto.getIdImpianto(), lineaVO.getIdLinea(), lineaVO);
			} else if(genericLineaVO.getIdSottoLinea() != null) {
				SottoLineaVO sottoLineaVO = genericLineaMapper.mapGenericLineaVOToSottoLineaVO(genericLineaVO);
				this.addSottoLineaImpianto(httpSession, impianto.getIdImpianto(), sottoLineaVO.getIdSottoLinea(), sottoLineaVO);
			}
		}
		
		// add atti
		for(AttoVO attoVO : impiantoVO.getAtti()) {
			this.addAttoImpianto(httpSession, impianto.getIdImpianto(), attoVO);
		}
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_INSERIMENTO_NUOVO_IMPIANTO, LogConstants.TSDDR_T_IMPIANTI, impianto.getIdImpianto());
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		GenericResponse<ImpiantoVO> response = GenericResponse.build(messaggio, impiantoEntityMapper.mapEntityToVOIgnoreFields(impianto));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<ImpiantoVO> getImpianto(HttpSession httpSession, Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getImpianto] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(idImpianto);
		if(!impiantoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(IMPIANTO_NOT_FOUND, idImpianto));
		}
		ImpiantoVO impiantoVO = impiantoEntityMapper.mapEntityToVOIgnoreFields(impiantoOpt.get());
		impiantoVO.setReadOnly(this.isImpiantoReadOnly(impiantoVO));
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_VISUALIZZA_DATI_IMPIANTO, LogConstants.TSDDR_T_IMPIANTI, impiantoVO.getIdImpianto());
		
		GenericResponse<ImpiantoVO> response = GenericResponse.build(impiantoVO);
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<ImpiantoVO> updateImpianto(HttpSession httpSession, Long idImpianto, ImpiantoVO impiantoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateImpianto] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		
		Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(idImpianto);
		if(!impiantoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(IMPIANTO_NOT_FOUND, idImpianto));
		}
		
		messaggiVO.addAll(this.verificaDatiObbligatoriImpianto(impiantoVO));
		if(impiantoVO.getIndirizzo() != null) {
			messaggiVO.addAll(this.verificaDatiObbligatoriIndirizzo(impiantoVO.getIndirizzo()));
		}
		if (!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		
		if(this.isImpiantoReadOnly(impiantoEntityMapper.mapEntityToVOIgnoreFields(impiantoOpt.get()))) {
			throw new BadRequestException(String.format("TsddrTImpianto con idImpianto = [%d] non modificabile", idImpianto));
		}
		
		TsddrTImpianto impianto = impiantoOpt.get();
		impianto.setDenominazione(impiantoVO.getDenominazione());
		this.verificaEPopolaImpianto(impianto, impiantoVO);
		
		if(impiantoVO.getIndirizzo() != null) {
			Optional<TsddrTIndirizzo> indirizzoOpt = indirizzoRepository.findByIdIndirizzo(impiantoVO.getIndirizzo().getIdIndirizzo());
			if(!indirizzoOpt.isPresent()) {
				throw new RecordNotFoundException(String.format("TsddrTIndirizzo non trovato con idIndirizzo = [%d]", impiantoVO.getIndirizzo().getIdIndirizzo()));
			}
			TsddrTIndirizzo indirizzo = indirizzoOpt.get();
			if(indirizzo.getIdIndirizzo().longValue() != impianto.getIndirizzo().getIdIndirizzo().longValue()) {
				throw new BadRequestException(String.format("TsddrTIndirizzo con idIndirizzo = [%d] non assegnato a TsddrTImpianto con idImpianto = [%d]", impiantoVO.getIndirizzo().getIdIndirizzo(), impianto.getIdImpianto()));
			}
			
			Long versioneIndirizzo = indirizzo.getVersione();
			indirizzo.setVersione(versioneIndirizzo++);
			indirizzo.setIndirizzo(impiantoVO.getIndirizzo().getIndirizzo());
			indirizzo.setCap(impiantoVO.getIndirizzo().getCap());
			this.verificaEPopolaIndirizzoImpianto(indirizzo, impiantoVO.getIndirizzo());
			indirizzo.setIdUserInsert(idUtenteSessione);
			indirizzo.setDataInsert(new Date());
			impianto.setIndirizzo(indirizzo);
		}
		
		impianto.setIdUserUpdate(idUtenteSessione);
		impianto.setDataUpdate(new Date());
		impianto = impiantoRepository.save(impianto);
		
		impiantoRepository.save(impianto);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_MODIFICA_DATI_IMPIANTO,
				LogConstants.TSDDR_T_IMPIANTI, impiantoVO.getIdImpianto());
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<ImpiantoVO> response = GenericResponse.build(messaggio, impiantoEntityMapper.mapEntityToVOIgnoreFields(impianto));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<ImpiantoVO> removeImpianto(HttpSession httpSession, Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::removeImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Date currentDate = new Date();
		
		Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(idImpianto);
		if(!impiantoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(IMPIANTO_NOT_FOUND, idImpianto));
		}
		
		if(!this.isImpiantoDeletable(impiantoEntityMapper.mapEntityToVOIgnoreFields(impiantoOpt.get()))) {
			throw new BadRequestException(String.format("TsddrTImpianto con idImpianto = [%d] non eliminabile", idImpianto));
		}
		
		List<TsddrRImpiantoLinea> impiantoLinee = impiantoLineaRepository.findByIdImpianto(idImpianto);
		
		TsddrTImpianto impianto = impiantoOpt.get();
		impianto.setIdUserDelete(idUtenteSessione);
		impianto.setDataDelete(currentDate);
		impiantoRepository.save(impianto);
		
		for(TsddrRImpiantoLinea impiantoLinea : impiantoLinee) {
			impiantoLinea.setIdUserDelete(idUtenteSessione);
			impiantoLinea.setDataDelete(currentDate);
			impiantoLineaRepository.save(impiantoLinea);
		}
		
		ImpiantoVO impiantoVO = impiantoEntityMapper.mapEntityToVOIgnoreFields(impianto);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_ELIMINZIONE_IMPIANTO,
				LogConstants.TSDDR_T_IMPIANTI, impiantoVO.getIdImpianto());
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P006.name());
		GenericResponse<ImpiantoVO> response = GenericResponse.build(messaggio, impiantoVO);
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::removeImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<LineaVO> addLineaImpianto(HttpSession httpSession, Long idImpianto, Long idLinea, LineaVO lineaVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addLineaImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrRImpiantoLinea> impiantoLineaOpt = impiantoLineaRepository.findByIdImpiantoAndIdLinea(idImpianto, idLinea);
		if(impiantoLineaOpt.isPresent()) {
			throw new BadRequestException(String.format("TsddrRImpiantoLinea gia' presente con idImpianto = [%d] e idLinea = [%d]", idImpianto, idLinea));
		}
		
		Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(idImpianto);
		if(!impiantoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(IMPIANTO_NOT_FOUND, idImpianto));
		}
		
		Optional<TsddrTLinea> lineaOpt = lineaRepository.findByIdLinea(idLinea, new Date());
		if(!lineaOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTLinea non trovato con idLinea = [%d]", idLinea));
		}
		
		TsddrRImpiantoLinea impiantoLinea = new TsddrRImpiantoLinea();
		impiantoLinea.setImpianto(impiantoOpt.get());
		impiantoLinea.setLinea(lineaOpt.get());
		if(lineaVO.getDataInizioValidita() != null) {
			impiantoLinea.setDataInizioValidita(lineaVO.getDataInizioValidita());
		} else {
			impiantoLinea.setDataInizioValidita(new Date());
		}
		impiantoLinea.setDataFineValidita(lineaVO.getDataFineValidita());
		EntityUtil.setInserted(impiantoLinea, idUtenteSessione, new Date());
		impiantoLinea = impiantoLineaRepository.save(impiantoLinea);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, 
				idDatiSogg,
				LogConstants.TSDDR_IMPIANTI_INSERIMENTO_NUOVA_LINEA_A_IMPIANTO,
				String.format(TRIPLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_LINEE,
						LogConstants.TSDDR_T_SOTTO_LINEE),
				String.format(DOUBLE_D_FORMAT_WITH_COMMA, impiantoLinea.getImpianto().getIdImpianto(),
						impiantoLinea.getLinea().getIdLinea()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P008.name());
		GenericResponse<LineaVO> response = GenericResponse.build(messaggio, lineaEntityMapper.mapEntityToVO(impiantoLinea.getLinea()));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<LineaVO> updateLineaImpianto(HttpSession httpSession, Long idImpianto, Long idLinea,
			LineaVO lineaVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateLineaImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrRImpiantoLinea> impiantoLineaOpt = impiantoLineaRepository.findByIdImpiantoAndIdLinea(idImpianto, idLinea);
		if(!impiantoLineaOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRImpiantoLinea non trovato con idImpianto = [%d] e idLinea = [%d]", idImpianto, idLinea));
		}
		
		TsddrRImpiantoLinea impiantoLinea = impiantoLineaOpt.get();
		if(lineaVO.getDataInizioValidita() != null) {
			impiantoLinea.setDataInizioValidita(lineaVO.getDataInizioValidita());
		} else {
			impiantoLinea.setDataInizioValidita(new Date());
		}
		impiantoLinea.setDataFineValidita(lineaVO.getDataFineValidita());
		EntityUtil.setUpdated(impiantoLinea, idUtenteSessione, new Date());
		impiantoLinea = impiantoLineaRepository.save(impiantoLinea);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_MODIFICA_LINEA_IMPIANTO,
				String.format(TRIPLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_LINEE, LogConstants.TSDDR_T_SOTTO_LINEE),
				String.format(DOUBLE_D_FORMAT_WITH_COMMA, impiantoLinea.getImpianto().getIdImpianto(), impiantoLinea.getLinea().getIdLinea()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<LineaVO> response = GenericResponse.build(messaggio, lineaEntityMapper.mapEntityToVO(impiantoLinea.getLinea()));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateLineaImpianto] END");
		return response;
	}
	
	@Override
	public GenericResponse<LineaVO> removeLineaImpianto(HttpSession httpSession, Long idImpianto, Long idLinea) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::removeLineaImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrRImpiantoLinea> impiantoLineaOpt = impiantoLineaRepository.findByIdImpiantoAndIdLinea(idImpianto, idLinea);
		
		if(!impiantoLineaOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRImpiantoLinea non trovato con idImpianto = [%d] e idLinea = [%d]", idImpianto, idLinea));
		}
		
		TsddrRImpiantoLinea impiantoLinea = impiantoLineaOpt.get();
		EntityUtil.setDeletedWithValidity(impiantoLinea, idUtenteSessione, new Date());
		impiantoLinea = impiantoLineaRepository.save(impiantoLinea);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_ELIMINZIONE_LINEA_A_IMPIANTO,
				String.format(TRIPLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_LINEE, LogConstants.TSDDR_T_SOTTO_LINEE),
				String.format(DOUBLE_D_FORMAT_WITH_COMMA, impiantoLinea.getImpianto().getIdImpianto(), impiantoLinea.getLinea().getIdLinea()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P007.name());
		GenericResponse<LineaVO> response = GenericResponse.build(messaggio, lineaEntityMapper.mapEntityToVO(impiantoLinea.getLinea()));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::removeLineaImpianto] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<GenericLineaVO>> getLineeImpianto(Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getLineeImpianto] BEGIN");
		List<TsddrRImpiantoLinea> impiantoLinee = impiantoLineaRepository.findByIdImpianto(idImpianto);
		List<GenericLineaVO> genericLineeVO = impiantoUtil.getGenericLineeVOfromLinee(impiantoLinee);
		GenericResponse<List<GenericLineaVO>> response = GenericResponse.build(genericLineeVO);
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getLineeImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<SottoLineaVO> addSottoLineaImpianto(HttpSession httpSession, Long idImpianto, Long idSottoLinea, SottoLineaVO sottoLineaVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addSottoLineaImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrRImpiantoLinea> impiantoLineaOpt = impiantoLineaRepository.findByIdImpiantoAndIdSottoLinea(idImpianto, idSottoLinea);
		if(impiantoLineaOpt.isPresent()) {
			throw new BadRequestException(String.format("TsddrRImpiantoLinea gia' presente con idImpianto = [%d] e idSottoLinea = [%d]", idImpianto, idSottoLinea));
		}
		
		Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(idImpianto);
		if(!impiantoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(IMPIANTO_NOT_FOUND, idImpianto));
		}
		
		Optional<TsddrTSottoLinea> sottoLineaOpt = sottolineaRepository.findByIdSottoLinea(idSottoLinea, new Date());
		if(!sottoLineaOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTSottoLinea non trovato con idSottoLinea = [%d]", idSottoLinea));
		}
		
		TsddrRImpiantoLinea impiantoLinea = new TsddrRImpiantoLinea();
		impiantoLinea.setImpianto(impiantoOpt.get());
		impiantoLinea.setSottoLinea(sottoLineaOpt.get());
		if(sottoLineaVO.getDataInizioValidita() != null) {
			impiantoLinea.setDataInizioValidita(sottoLineaVO.getDataInizioValidita());
		} else {
			impiantoLinea.setDataInizioValidita(new Date());
		}
		impiantoLinea.setDataFineValidita(sottoLineaVO.getDataFineValidita());
		EntityUtil.setInserted(impiantoLinea, idUtenteSessione, new Date());
		impiantoLinea = impiantoLineaRepository.save(impiantoLinea);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_INSERIMENTO_NUOVA_LINEA_A_IMPIANTO,
				String.format(TRIPLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_LINEE, LogConstants.TSDDR_T_SOTTO_LINEE),
				String.format(TRIPLE_D_FORMAT, impiantoLinea.getImpianto().getIdImpianto(), sottoLineaOpt.get().getLinea().getIdLinea(), impiantoLinea.getSottoLinea().getIdSottoLinea()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P008.name());
		
		GenericResponse<SottoLineaVO> response = GenericResponse.build(messaggio, sottoLineaEntityMapper.mapEntityToVO(impiantoLinea.getSottoLinea()));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addSottoLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<SottoLineaVO> updateSottoLineaImpianto(HttpSession httpSession, Long idImpianto,
			Long idSottoLinea, SottoLineaVO sottoLineaVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateSottoLineaImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrRImpiantoLinea> impiantoLineaOpt = impiantoLineaRepository.findByIdImpiantoAndIdSottoLinea(idImpianto, idSottoLinea);
		if(!impiantoLineaOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRImpiantoLinea non trovato con idImpianto = [%d] e idSottoLinea = [%d]", idImpianto, idSottoLinea));
		}
		
		TsddrRImpiantoLinea impiantoLinea = impiantoLineaOpt.get();
		if(sottoLineaVO.getDataInizioValidita() != null) {
			impiantoLinea.setDataInizioValidita(sottoLineaVO.getDataInizioValidita());
		} else {
			impiantoLinea.setDataInizioValidita(new Date());
		}
		impiantoLinea.setDataFineValidita(sottoLineaVO.getDataFineValidita());
		EntityUtil.setUpdated(impiantoLinea, idUtenteSessione, new Date());
		impiantoLinea = impiantoLineaRepository.save(impiantoLinea);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_MODIFICA_LINEA_IMPIANTO, 
				String.format(TRIPLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_LINEE, LogConstants.TSDDR_T_SOTTO_LINEE),
				String.format(TRIPLE_D_FORMAT, impiantoLinea.getImpianto().getIdImpianto(), impiantoLinea.getSottoLinea().getLinea().getIdLinea(), impiantoLinea.getSottoLinea().getIdSottoLinea()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<SottoLineaVO> response = GenericResponse.build(messaggio, sottoLineaEntityMapper.mapEntityToVO(impiantoLinea.getSottoLinea()));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateSottoLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<SottoLineaVO> removeSottoLineaImpianto(HttpSession httpSession, Long idImpianto, Long idSottoLinea) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::removeSottoLineaImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Optional<TsddrRImpiantoLinea> impiantoLineaOpt = impiantoLineaRepository.findByIdImpiantoAndIdSottoLinea(idImpianto, idSottoLinea);
		
		if(!impiantoLineaOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrRImpiantoLinea non trovato con idImpianto = [%d] e idSottoLinea = [%d]", idImpianto, idSottoLinea));
		}
		
		TsddrRImpiantoLinea impiantoLinea = impiantoLineaOpt.get();
		EntityUtil.setDeletedWithValidity(impiantoLinea, idUtenteSessione, new Date());
		impiantoLinea = impiantoLineaRepository.save(impiantoLinea);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_ELIMINZIONE_LINEA_A_IMPIANTO, 
				String.format(TRIPLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_LINEE, LogConstants.TSDDR_T_SOTTO_LINEE), 
				String.format(TRIPLE_D_FORMAT, impiantoLinea.getImpianto().getIdImpianto(), impiantoLinea.getSottoLinea().getLinea().getIdLinea(), impiantoLinea.getSottoLinea().getIdSottoLinea()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P007.name());
		GenericResponse<SottoLineaVO> response = GenericResponse.build(messaggio, sottoLineaEntityMapper.mapEntityToVO(impiantoLinea.getSottoLinea()));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::removeSottoLineaImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<List<AttoVO>> getAttiImpianto(HttpSession httpSession, Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getAttiImpianto] BEGIN");
		List<TsddrTAtto> atti = attoRepository.findAttiByIdImpianto(idImpianto, new Date());
		GenericResponse<List<AttoVO>> response = GenericResponse.build(attoEntityMapper.mapListEntityToListVO(atti));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getAttiImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<AttoVO> addAttoImpianto(HttpSession httpSession, Long idImpianto, AttoVO attoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addAttoImpianto] BEGIN");
		
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(idImpianto);
		if(!impiantoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(IMPIANTO_NOT_FOUND, idImpianto));
		}
		Optional<TsddrDTipoProvvedimento> tipoProvvedimentoOpt = tipoProvvedimentoRepository.findTipoProvvedimentoById(attoVO.getTipoProvvedimento().getIdTipoProvvedimento(), new Date());
		if(!tipoProvvedimentoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrDTipoProvvedimento non trovato con idTipoProvvedimento = [%d]", attoVO.getTipoProvvedimento().getIdTipoProvvedimento()));
		}
		
		TsddrTAtto atto = new TsddrTAtto();
		atto.setImpianto(impiantoOpt.get());
		atto.setNumProvvedimento(attoVO.getNumProvvedimento());
		atto.setDataProvvedimento(attoVO.getDataProvvedimento());
		atto.setTipoProvvedimento(tipoProvvedimentoOpt.get());
		atto.setDataInizioAutorizzazione(attoVO.getDataInizioAutorizzazione());
		atto.setDataFineAutorizzazione(attoVO.getDataFineAutorizzazione());
		
		atto.setDataInsert(new Date());
		
		atto.setIdUserInsert(idUtenteSessione);
		
		atto = attoRepository.save(atto);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_INSERIMENTO_NUOVA_ATTO_A_IMPIANTO, 
				String.format(DOUBLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_ATTI),
				String.format(DOUBLE_D_FORMAT, impiantoOpt.get().getIdImpianto(), atto.getIdAtto()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P002.name());
		GenericResponse<AttoVO> response = GenericResponse.build(messaggio, attoEntityMapper.mapEntityToVO(atto));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::addAttoImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<AttoVO> updateAttoImpianto(HttpSession httpSession, Long idImpianto, Long idAtto, AttoVO attoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateAttoImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrTAtto> attoOpt = attoRepository.findAttoByIdAtto(idAtto);
		if(!attoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTAtto non trovato con idAtto = [%d]", idAtto));
		}
		Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(idImpianto);
		if(!impiantoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format(IMPIANTO_NOT_FOUND, idImpianto));
		}
		Optional<TsddrDTipoProvvedimento> tipoProvvedimentoOpt = tipoProvvedimentoRepository.findTipoProvvedimentoById(attoVO.getTipoProvvedimento().getIdTipoProvvedimento(), new Date());
		if(!tipoProvvedimentoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrDTipoProvvedimento non trovato con idTipoProvvedimento = [%d]", attoVO.getTipoProvvedimento().getIdTipoProvvedimento()));
		}
		
		TsddrTAtto atto = attoOpt.get();
		atto.setImpianto(impiantoOpt.get());
		atto.setNumProvvedimento(attoVO.getNumProvvedimento());
		atto.setDataProvvedimento(attoVO.getDataProvvedimento());
		atto.setTipoProvvedimento(tipoProvvedimentoOpt.get());
		atto.setDataInizioAutorizzazione(attoVO.getDataInizioAutorizzazione());
		atto.setDataFineAutorizzazione(attoVO.getDataFineAutorizzazione());
		
		atto.setDataUpdate(new Date());
		atto.setIdUserUpdate(idUtenteSessione);
		
		atto = attoRepository.save(atto);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_MODIFICA_ATTO_A_IMPIANTO, 
				String.format(DOUBLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_ATTI), 
				String.format(DOUBLE_D_FORMAT, impiantoOpt.get().getIdImpianto(), atto.getIdAtto()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P001.name());
		GenericResponse<AttoVO> response = GenericResponse.build(messaggio, attoEntityMapper.mapEntityToVO(atto));
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateAttoImpianto] END");
		return response;
	}

	@Override
	public GenericResponse<AttoVO> removeAttoImpianto(HttpSession httpSession, Long idImpianto, Long idAtto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::updateAttoImpianto] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Optional<TsddrTAtto> attoOpt = attoRepository.findAttoByIdAtto(idAtto);
		if(!attoOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTAtto non trovato con idAtto = [%d]", idAtto));
		}
		
		TsddrTAtto atto = attoOpt.get();
		atto.setDataDelete(new Date());
		atto.setIdUserDelete(idUtenteSessione);
		atto = attoRepository.save(atto);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_IMPIANTI_ELIMINZIONE_ATTO_A_IMPIANTO, 
				String.format(DOUBLE_S_FORMAT, LogConstants.TSDDR_T_IMPIANTI, LogConstants.TSDDR_T_ATTI),
				String.format(DOUBLE_D_FORMAT, atto.getImpianto().getIdImpianto(), atto.getIdAtto()));
		
		MessaggioVO messaggio = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P017.name());
		GenericResponse<AttoVO> response = GenericResponse.build(messaggio, attoEntityMapper.mapEntityToVO(atto));
		return response;
	}

	@Override
	public GenericResponse<String> verificaParametri(ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaParametriRicerca] BEGIN");
		if(!areParametriRicercaValid(parametriRicerca)) {
			throw new BadRequestException("Nessun parametro valorizzato");
		}
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaParametriRicerca] END");
		return response;
	}
	
	private boolean areParametriRicercaValid(ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::areParametriRicercaValid] BEGIN");
		boolean areParametriRicercaValid = StringUtils.isNotBlank(parametriRicerca.getDenominazione())
				|| parametriRicerca.getIdComune() != null
				|| parametriRicerca.getIdGestore() != null
				|| parametriRicerca.getIdProvincia() != null
				|| parametriRicerca.getIdStatoImpianto() != null
				||parametriRicerca.getIdTipoImpianto() != null;
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::areParametriRicercaValid] END");
		return areParametriRicercaValid;
	}
	
	private List<MessaggioVO> verificaDatiObbligatoriAddImpianto(ImpiantoVO impianto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaDatiObbligatoriImpianto] BEGIN");
		
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
		
		if (impianto.getLinee() == null || impianto.getLinee().isEmpty()) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "impianto.linee"));
		}
		
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaDatiObbligatoriImpianto] END");
		return messaggiVO;
	}
	
	private List<MessaggioVO> verificaDatiObbligatoriImpianto(ImpiantoVO impianto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaDatiObbligatoriImpianto] BEGIN");
		
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());

		if (impianto.getGestore() == null || impianto.getGestore().getIdGestore() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "gestore.idGestore"));
		}
		
		if (impianto.getTipoImpianto() == null || impianto.getTipoImpianto().getIdTipoImpianto() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "tipoImpianto.idTipoImpianto"));
		}
		
		if (impianto.getStatoImpianto() == null || impianto.getStatoImpianto().getIdStatoImpianto() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "statoImpianto.idStatoImpianto"));
		}

		if (impianto.getDataInizioValidita() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "dataInizioValidita"));
		}
		
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaDatiObbligatoriImpianto] END");
		return messaggiVO;
	}
	
	private List<MessaggioVO> verificaDatiObbligatoriIndirizzo(IndirizzoVO indirizzo) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaDatiObbligatoriIndirizzo] BEGIN");
		
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());

		if (indirizzo.getIndirizzo() == null || 
				indirizzo.getSedime() == null || 
						indirizzo.getSedime().getIdSedime() == null) {
				messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.sedime.idSedime"));
			}
			
			if (indirizzo == null ||
				indirizzo.getIndirizzo() == null) {
				messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.indirizzo"));
			}
			
			if (indirizzo == null || 
				indirizzo.getComune() == null ||
				indirizzo.getComune().getIdComune() == null) {
				messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.comune.idComune"));
			}
			
			if (indirizzo == null ||
				indirizzo.getComune() == null ||
				indirizzo.getComune().getProvincia() == null ||
				indirizzo.getComune().getProvincia().getIdProvincia() == null) {
				messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.comune.provincia.idProvincia"));
			}
		
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaDatiObbligatoriIndirizzo] END");
		return messaggiVO;
	}
	
	private void verificaEPopolaImpianto(TsddrTImpianto impianto, ImpiantoVO impiantoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaEPopolaImpianto] BEGIN");
		Date currentDate = new Date();
		
		// check su impianto: gestore, tipoImpianto, statoImpianto
		Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(impiantoVO.getGestore().getIdGestore(), currentDate);
		if(!gestoreOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTGestore non trovato con idGestore = [%d]", impiantoVO.getGestore().getIdGestore()));
		}
		impianto.setGestore(gestoreOpt.get());
		
		Optional<TsddrDTipoImpianto> tipoImpiantoOpt = tipoImpiantoRepository.findTipoImpiantoById(impiantoVO.getTipoImpianto().getIdTipoImpianto(), currentDate);
		if(tipoImpiantoOpt.isEmpty()) {
			throw new BadRequestException(String.format("Nessun TsddrDTipoImpianto trovato con idTipoImpianto = [%d]", impiantoVO.getTipoImpianto().getIdTipoImpianto()));
		}
		impianto.setTipoImpianto(tipoImpiantoOpt.get());
		
		Optional<TsddrDStatoImpianto> statoImpiantoOpt = statoImpiantoRepository.findStatoImpiantoById(impiantoVO.getStatoImpianto().getIdStatoImpianto(), currentDate);
		if(statoImpiantoOpt.isEmpty()) {
			throw new BadRequestException(String.format("Nessun TsddrDStatoImpianto trovato con idStatoImpianto = [%d]", impiantoVO.getStatoImpianto().getIdStatoImpianto()));
		}
		impianto.setStatoImpianto(statoImpiantoOpt.get());
		
		if(impiantoVO.getDataInizioValidita() != null) {
			impianto.setDataInizioValidita(impiantoVO.getDataInizioValidita());
		} else {
			impianto.setDataInizioValidita(new Date());
		}
		if(impiantoVO.getDataFineValidita() != null) {
			impianto.setDataFineValidita(impiantoVO.getDataFineValidita());
		} else {
			impianto.setDataFineValidita(null);
		}
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaEPopolaImpianto] END");
	}
		
	private void verificaEPopolaIndirizzoImpianto(TsddrTIndirizzo indirizzo, IndirizzoVO indirizzoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaEPopolaIndirizzoImpianto] BEGIN");
		Date currentDate = new Date();
		// check su impianto.indirizzo: sedime, comune, provincia, nazione
		Optional<TsddrDSedime> sedimeOpt = sedimeRepository.findByIdSedime(indirizzoVO.getSedime().getIdSedime(), currentDate);
		if(sedimeOpt.isEmpty()) {
			throw new BadRequestException(String.format("Nessun TsddrDSedime trovato con idSedime = [%d]", indirizzoVO.getSedime().getIdSedime()));
		}
		indirizzo.setSedime(sedimeOpt.get());
		
		Optional<TsddrDComune> comuneOpt = comuneRepository.findByIdComune(indirizzoVO.getComune().getIdComune(), currentDate);
		if(comuneOpt.isEmpty()) {
			throw new BadRequestException(String.format("Nessun TsddrDComune trovato con idComune = [%d]", indirizzoVO.getComune().getIdComune()));
		}
		indirizzo.setComune(comuneOpt.get());
		
		Optional<TsddrDProvincia> provinciaOpt = provinciaRepository.findProvinciaById(indirizzoVO.getComune().getProvincia().getIdProvincia(), currentDate);
		if(provinciaOpt.isEmpty()) {
			throw new BadRequestException(String.format("Nessun TsddrDProvincia trovato con idProvincia = [%d]", indirizzoVO.getComune().getProvincia().getIdProvincia()));
		}
		
		if(!provinciaOpt.get().getComuni().stream().anyMatch(p -> p.getIdComune().longValue() == comuneOpt.get().getIdComune().longValue())) {
			throw new BadRequestException(String.format("La Provincia idProvincia = [%d] non contiene il Comune idComune = [%d]", indirizzoVO.getComune().getProvincia().getIdProvincia(), indirizzoVO.getComune().getIdComune()));	
		}
		indirizzo.getComune().setProvincia(provinciaOpt.get());
		
		// nazione è impostata di default
		Optional<TsddrDNazione> nazioneOpt = nazioneRepository.findNazioneByIdIstatNazione(idIstatNazioneCorrente, currentDate);
		if(nazioneOpt.isEmpty()) {
			throw new BadRequestException(String.format("Nessuna TsddrDNazione trovata con idIstatNazioneCorrente = [%s]", idIstatNazioneCorrente));
		}
		indirizzo.setNazione(nazioneOpt.get());
		
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::verificaEPopolaIndirizzoImpianto] END");
	}
	
	private boolean isImpiantoDeletable(ImpiantoVO impiantoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::isImpiantoDeletable] BEGIN");
		Date currentDate = new Date();
		boolean isDeletable = false;
		if(		impiantoVO.getDataInizioValidita().compareTo(currentDate) > 0  || 
				impiantoVO.getGestore().getDataInizioValidita().compareTo(currentDate) > 0 ||
				(impiantoVO.getDataFineValidita() != null && impiantoVO.getDataFineValidita().compareTo(currentDate) < 0) ||
				(impiantoVO.getGestore().getDataFineValidita() != null && impiantoVO.getGestore().getDataFineValidita().compareTo(currentDate) < 0)) {
			isDeletable = false;
		} else {
			isDeletable = true;
		}
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::isImpiantoDeletable] END");
		return isDeletable;
	}
	
	private boolean isImpiantoReadOnly(ImpiantoVO impiantoVO) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::isImpiantoReadOnly] BEGIN");
		Date currentDate = new Date();
		boolean isReadOnly = true;
		if(impiantoVO.getDataFineValidita() != null ) {
			if(		impiantoVO.getDataInizioValidita().compareTo(currentDate) <= 0 &&
					impiantoVO.getDataFineValidita().compareTo(currentDate) >= 0 	) {
				isReadOnly = false;
			}
		} else {
			if(impiantoVO.getDataInizioValidita().compareTo(currentDate) <= 0) {
				isReadOnly = false;	
			}
		}
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::isImpiantoReadOnly] END");
		return isReadOnly;
	}
	
	TsddrTReport tsddrTReport = null;

	public TsddrTReport getTsddrTReport(){
		if(tsddrTReport == null ) {
			tsddrTReport = tsddrTReportRepository.findOne(REPORT_ID);
		}
		return tsddrTReport;
	}
	
	@Override
	public GenericResponse<ReportVO> downloadReport(HttpSession httpSession, ImpiantoParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getListaImpianti] BEGIN");
		
		String filtriSuffix = getFilterByRequest(parametriRicerca);
		GenericResponse<List<ImpiantoVO>> listaImpianti = getListaImpiantiReport(httpSession, parametriRicerca, filtriSuffix);
		
		ExcelDataUtil excel = new ExcelDataUtil(getTsddrTReport().getDescReport(), filtriSuffix);
        ExcelSheet sheet = excel.addSheet(getTsddrTReport().getDescReport()); 
    	sheet.addColumn("Gestore CF / PI Denominazione");
        sheet.addColumn("Denominazione Impianto");
        sheet.addColumn("Tipo Impianto");
        sheet.addColumn("Stato Impianto");
        sheet.addColumn("DATA Inizio Validità");
        sheet.addColumn("DATA Fine Validità");
        sheet.addColumn("Indirizzo Impianto");
        sheet.addColumn("Comune");
        sheet.addColumn("CAP");      
        sheet.addColumn("Sigla Prov.");
        sheet.addColumn("Telefono Gestore");
        sheet.addColumn("Email Gestore");
        sheet.addColumn("PEC Gestore");
        
        addContent(sheet, listaImpianti.getContent());
        
		ReportVO report = null;
		try {
			report = new ReportVO(excel.getFileName(), excel.getFile());
		} catch(IOException e) {
			LoggerUtil.warn(logger, "[ImpiantoServiceImpl::downloadReport] problemi in generazione report");
		}
		
		GenericResponse<ReportVO> response = new GenericResponse<ReportVO>(report);
		
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::downloadReport] END");
		return response;
	}
	
	private String getFilterByRequest(ImpiantoParametriRicerca parametriRicerca) {
		StringBuffer sb = new StringBuffer();
		
		if(parametriRicerca.getIdGestore()!=null) {
			Optional<TsddrTGestore> gestore = gestoreRepository.findByIdGestore(parametriRicerca.getIdGestore());
			sb.append("_Gestore_").append(gestore.isPresent()?gestore.get().getRagSociale():"");
		}
		if(parametriRicerca.getDenominazione()!=null) {
			sb.append("_Denominazione_").append(parametriRicerca.getDenominazione());
		}
		if(parametriRicerca.getIdProvincia()!=null) {
			Optional<TsddrDProvincia> provincia = provinciaRepository.findProvinciaById(parametriRicerca.getIdProvincia(), new Date());
			sb.append("_Prov_").append(provincia.isPresent()? provincia.get().getSiglaProv():"");
		}
		if(parametriRicerca.getIdComune()!=null) {
			Optional<TsddrDComune> comune = comuneRepository.findByIdComune(parametriRicerca.getIdComune(), new Date());
			sb.append("_Comune_").append(comune.isPresent()? comune.get().getComune():"");
		}
		if(parametriRicerca.getIdStatoImpianto()!=null) {
			Optional<TsddrDStatoImpianto> stato = statoImpiantoRepository.findStatoImpiantoById(parametriRicerca.getIdStatoImpianto(), new Date());
			sb.append("_Stato_").append(stato.isPresent()? stato.get().getDescStatoImpianto():"");
		}
		if(parametriRicerca.getIdTipoImpianto()!=null) {
			Optional<TsddrDTipoImpianto> tipo = tipoImpiantoRepository.findTipoImpiantoById(parametriRicerca.getIdTipoImpianto(), new Date());
			sb.append("_Tipo_").append(tipo.isPresent()? tipo.get().getDescTipoImpianto():"");
		}			
		
		if(sb.toString().length()>0) {
			sb.append("_");
		}
		
		return sb.toString();
	}
	
	private void addContent(ExcelSheet sheet, List<ImpiantoVO> content) {
		
		for(ImpiantoVO impianto : content){
			sheet.addDataToBody(
					impianto.getGestore().getCodFiscPartiva() + " / " +	impianto.getGestore().getRagSociale(),
					impianto.getDenominazione()!=null?impianto.getDenominazione():"",
					impianto.getTipoImpianto().getDescTipoImpianto()!=null?impianto.getTipoImpianto().getDescTipoImpianto():"",
					impianto.getStatoImpianto().getDescStatoImpianto() !=null?impianto.getStatoImpianto().getDescStatoImpianto():"",
					impianto.getDataInizioValidita()!=null?formatDate.format(impianto.getDataInizioValidita()):"",
					impianto.getDataFineValidita()!=null?formatDate.format(impianto.getDataFineValidita()):"",
					impianto.getIndirizzo().getIndirizzo()!=null?impianto.getIndirizzo().getSedime().getDescSedime() + " " + impianto.getIndirizzo().getIndirizzo():"",
					impianto.getIndirizzo().getComune().getComune()!=null?impianto.getIndirizzo().getComune().getComune():"",
					impianto.getIndirizzo().getCap()!=null?impianto.getIndirizzo().getCap():"",	 						
					impianto.getIndirizzo().getComune().getProvincia().getSiglaProv()!=null?impianto.getIndirizzo().getComune().getProvincia().getSiglaProv():"",
					impianto.getGestore().getTelefono()!=null?impianto.getGestore().getTelefono():"",
					impianto.getGestore().getEmail()!=null?impianto.getGestore().getEmail():"",
					impianto.getGestore().getPec()!=null?impianto.getGestore().getPec():""
					);
		}
	}
	
	public GenericResponse<List<ImpiantoVO>> getListaImpiantiReport(HttpSession httpSession, ImpiantoParametriRicerca parametriRicerca, String filtriSuffix) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getListaImpiantiReport] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		List<TsddrTImpianto> impianti = getImpianti(parametriRicerca);
		
		List<ImpiantoVO> selectVO = impiantoEntityMapper.mapListEntityToListVO(impianti);
		
		String operation = LogConstants.TSDDR_REPORT_IMPIANTI_COMPLETO;
		String ids = "";
		if(filtriSuffix != null && filtriSuffix.length()>0) {
			ids = selectVO.stream().map(g -> String.valueOf(g.getIdImpianto())).collect(Collectors.joining(","));
			operation = LogConstants.TSDDR_REPORT_IMPIANTI_FILTRATO;
		}
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operation,
				LogConstants.TSDDR_T_IMPIANTI,
				ids);
		GenericResponse<List<ImpiantoVO>> response = GenericResponse.build(selectVO);
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::getListaImpiantiReport] END");
		
		return response;
	}
	
	@Override
	public GenericResponse<CheckImpiantoVO> checkDeleteImpianto(Long idImpianto) {
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::checkDeleteImpianto] BEGIN");
		
		CheckImpiantoVO check = new CheckImpiantoVO();

		Boolean statusBoolean = null;
		
		List<TsddrTDichAnnuale> dichiarazioni = tsddrTDichAnnualeRepository.findByIdImpianto(idImpianto);
		List<TsddrTPrevCons> prevCons = tsddrTPrevConsRepository.findByIdImpianto(idImpianto);
		
		if (	dichiarazioni != null &&
				dichiarazioni.size() > 0) {
			statusBoolean = false;
		} else if (
				prevCons != null &&
				prevCons.size() > 0 ) {
			statusBoolean = false;
		} else {
			statusBoolean = true;
		}

		check.setResult(statusBoolean);

		GenericResponse<CheckImpiantoVO> response = new GenericResponse<CheckImpiantoVO>(check);
		LoggerUtil.debug(logger, "[ImpiantoServiceImpl::checkDeleteImpianto] END");

		return response;
	}
}
