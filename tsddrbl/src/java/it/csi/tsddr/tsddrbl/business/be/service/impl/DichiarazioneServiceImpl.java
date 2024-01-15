/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSedime;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoDichiarazione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDUnitaMisura;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTAtto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTConferimento;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTRifiutoTariffa;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSoggettoMr;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTVersamento;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.AttoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.DichAnnualeEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.GestoreEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ImpiantoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.SoggettoMrEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDComuneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDPeriodoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProvinciaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDSedimeRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDStatoDichiarazioneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoIndirizzoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDUnitaMisuraRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRImpiantoLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTAttoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTConferimentoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDichAnnualeRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTGestoreRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTRifiutoTariffaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTSoggettoMrRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTUtenteRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.specifications.TsddrTDichAnnualeSpecification;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.DichiarazioneService;
import it.csi.tsddr.tsddrbl.business.be.service.ProfiloService;
import it.csi.tsddr.tsddrbl.business.facade.DoquiServiceFacade;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.ResponseProtocollaDocumento;
import it.csi.tsddr.tsddrbl.util.DateUtil;
import it.csi.tsddr.tsddrbl.util.EntityUtil;
import it.csi.tsddr.tsddrbl.util.ImpiantoUtil;
import it.csi.tsddr.tsddrbl.util.IndirizzoUtil;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.OptionalUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.enums.Periodo;
import it.csi.tsddr.tsddrbl.util.enums.StatoDichiarazione;
import it.csi.tsddr.tsddrbl.util.enums.TipoIndirizzo;
import it.csi.tsddr.tsddrbl.util.enums.UnitaMisura;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.report.DichiarazioneReport;
import it.csi.tsddr.tsddrbl.util.report.ReportUtilImpl;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelDataUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelSheet;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.AllowDuplicaDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.ConferimentoVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeBasicVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichiarazioneParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.ExistsDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoConferitoVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.SoggettoMrVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.VersamentoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.AttoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Transactional
public class DichiarazioneServiceImpl implements DichiarazioneService {

	private static Logger logger = Logger.getLogger(DichiarazioneServiceImpl.class);

	private static Long REPORT_ID = 7L;	

	@Autowired
	private TsddrTReportRepository tsddrTReportRepository;
	
	@Autowired
	private AclUtil aclUtil;
	
	@Autowired
	private MessaggioServiceImpl messaggioService;
	
	@Autowired
	private ProfiloService profiloService;
	
	@Autowired
	private CsiLogAuditService csiLogAuditService;

	@Autowired
	private TsddrTDichAnnualeRepository dichAnnualeRepository;
	
	@Autowired
	private TsddrTSoggettoMrRepository soggettoMrRepository;
	
	@Autowired
	private TsddrDStatoDichiarazioneRepository statoDichiarazioneRepository;
	
	@Autowired
	private TsddrTGestoreRepository gestoreRepository;
	
	@Autowired
	private TsddrTImpiantoRepository impiantoRepository;
	
	@Autowired
	private TsddrRImpiantoLineaRepository impiantoLineaRepository;
	
	@Autowired
	private DichAnnualeEntityMapper dichAnnualeEntityMapper;

	@Autowired
	private GestoreEntityMapper gestoreEntityMapper;
	
	@Autowired
	private ImpiantoEntityMapper impiantoEntityMapper;
	
	@Autowired
	private ReportUtilImpl utilsReportImpl;

	@Autowired
	private TsddrTUtenteRepository tsddrTUtenteRepository;
	
	@Autowired
	private TsddrDTipoIndirizzoRepository tipoIndirizzoRepository;
	
	@Autowired
	private TsddrDPeriodoRepository periodoRepository;
	
	@Autowired
	private TsddrDSedimeRepository sedimeRepository;
	
	@Autowired
	private TsddrDComuneRepository comuneRepository;
	
	@Autowired
	private TsddrDUnitaMisuraRepository unitaMisuraRepository;
	
	@Autowired
	private TsddrTRifiutoTariffaRepository rifiutoTariffaRepository;
	
	@Autowired
	private TsddrDProvinciaRepository provinciaRepository;
	
	@Autowired
    private TsddrTAttoRepository attoRepository;
	
	@Autowired
	private SoggettoMrEntityMapper soggettoMrEntityMapper;
	
	@Autowired
	private DoquiServiceFacade doquiServiceFacade;
	
	@Autowired
	private DichiarazioneReport dichiarazioneReport;
	
	@Autowired
    private AttoEntityMapper attoEntityMapper;
	
	@Autowired
	private ImpiantoUtil impiantoUtil;
	
	@Autowired
	private IndirizzoUtil indirizzoUtil;

	@Value("${acta.enabled:false}")
	private boolean actaEnabled;
	
    @Autowired
    private TsddrTDichAnnualeRepository tsddrTDichAnnualeRepository;

    @Autowired
    private TsddrTRifiutoTariffaRepository tsddrTRifiutoTariffaRepository;
    
    @Autowired
    private TsddrTConferimentoRepository tsddrTConferimentoRepository;

    
	@Override
	public GenericResponse<FunzionalitaProfiloVO> getACLDichiarazioni(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getACLDichiarazioni] BEGIN");
		GenericResponse<FunzionalitaProfiloVO> response = GenericResponse
				.build(aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_02));
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getACLDichiarazioni] END");
		return response;
	}

	public GenericResponse<List<Long>> getComboAnni(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getComboAnni] BEGIN");
		List<Long> anni = dichAnnualeRepository.findDistinctAnniDichAnnuale();
		GenericResponse<List<Long>> response = GenericResponse.build(anni);
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getComboAnni] END");
		return response;
	}

	@Override
	public GenericResponse<List<SelectVO>> getComboGestore(HttpSession httpSession) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getComboGestore] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		
		Date currentDate = new Date();
		List<TsddrTGestore> gestori = gestoreRepository.findGestoriByIdUtenteIdProfilo(idUtente, idProfilo, currentDate);
		
		if (CollectionUtils.isEmpty(gestori) && profiloService.isProfiloBO(idProfilo)) {
			gestori = gestoreRepository.findGestori(currentDate);
		}
		
		GenericResponse<List<SelectVO>> response = GenericResponse.build(gestoreEntityMapper.mapListEntityToListSelectVO(gestori));
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getComboGestore] END");
		return response;
	}
	
	@Override
	public GenericResponse<List<SelectVO>> getComboImpianti(HttpSession httpSession, Long idGestore) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getComboImpianti] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		
		Date currentDate = new Date();
		List<TsddrTImpianto> impianti = idGestore != null
				? impiantoRepository.findByIdUtenteAndIdProfiloAndIdGestore(idUtente, idProfilo, idGestore, currentDate)
				: impiantoRepository.findByIdUtenteAndIdProfilo(idUtente, idProfilo, currentDate);
		
		if (CollectionUtils.isEmpty(impianti) && profiloService.isProfiloBO(idProfilo)) {
		    impianti = idGestore != null
			? impiantoRepository.findImpiantiByIdGestore(idGestore, currentDate)
		    : impiantoRepository.findImpianti(currentDate);
		}
		
		GenericResponse<List<SelectVO>> response = GenericResponse.build(impiantoEntityMapper.mapListEntityToListSelectVO(impianti));
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getComboImpianti] END");
		return response;
	}

	public GenericResponse<String> getParametriFiltroApplicati(HttpSession httpSession,
			DichiarazioneParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getParametriFiltroApplicati] BEGIN");

		if (!areParametriRicercaValid(parametriRicerca)) {
			throw new BadRequestException("Nessun parametro valorizzato");
		}
		Date currentDate = new Date();
		StringBuilder parametriFiltroBuilder = new StringBuilder();

		if(parametriRicerca.getIdGestore() != null) {
			Optional<TsddrTGestore> gestoreOpt = gestoreRepository.findByIdGestore(parametriRicerca.getIdGestore(),
					currentDate);
			if (gestoreOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrTGestore trovato con idGestore = [%d]",
						parametriRicerca.getIdGestore()));
			}
			parametriFiltroBuilder.append("Gestore = \"" + gestoreOpt.get().getRagSociale() + "\" ");
		}
		
		if(parametriRicerca.getIdImpianto() != null) {
			Optional<TsddrTImpianto> impiantoOpt = impiantoRepository.findByIdImpianto(parametriRicerca.getIdImpianto());
			if (impiantoOpt.isEmpty()) {
				throw new BadRequestException(String.format("Nessun TsddrTImpianto trovato con idImpianto = [%d]",
						parametriRicerca.getIdImpianto()));
			}
			parametriFiltroBuilder.append("Denominazione = \"" + impiantoOpt.get().getDenominazione() + "\" ");
		}
		
		if(parametriRicerca.getAnnoDal() != null) {
			parametriFiltroBuilder.append("Dall'anno = \"" + parametriRicerca.getAnnoDal() + "\" ");
		}
		
		if(parametriRicerca.getAnnoAl() != null) {
			parametriFiltroBuilder.append("All'anno = \"" + parametriRicerca.getAnnoAl() + "\" ");
		}

		GenericResponse<String> response = GenericResponse.build(parametriFiltroBuilder.toString());
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getParametriFiltroApplicati] END");
		return response;
	}

	public GenericResponse<List<DichAnnualeBasicVO>> getListaDichiarazioni(HttpSession httpSession, DichiarazioneParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getListaDichiarazioni] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		
		if (!areParametriRicercaValid(parametriRicerca)) {
			throw new BadRequestException("Nessun parametro valorizzato");
		}
		
		Date currentDate = new Date();
		
		boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
		List<TsddrTGestore> gestoriUtente = gestoreRepository.findGestoriByIdUtenteIdProfilo(idUtente, idProfilo, currentDate);
		List<TsddrTDichAnnuale> dichAnnuali = dichAnnualeRepository.findAll(TsddrTDichAnnualeSpecification.searchByParams(parametriRicerca, currentDate, isProfiloBO, gestoriUtente));
		
		if(dichAnnuali.isEmpty()) {
			MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.A002.name());
			throw new FunctionalException(String.format("Nessuna dichiarazione annuale trovato con i parametri di ricerca inseriti"), messaggioVO);
		}
		
		List<DichAnnualeBasicVO> dichAnnualiVO = dichAnnualeEntityMapper.mapListEntityToListBasicVO(dichAnnuali,
				aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_02), isProfiloBO);
		
		String operazioneLog = isProfiloBO ? LogConstants.TSDDR_RICERCA_DICHIARAZIONE_ANNUALE_BO : LogConstants.TSDDR_RICERCA_DICHIARAZIONE_ANNUALE_FO;
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
				LogConstants.TSDDR_T_DICH_ANNUALI,
				dichAnnualiVO.stream().map(g -> String.valueOf(g.getIdDichAnnuale())).collect(Collectors.joining(",")));
		
		GenericResponse<List<DichAnnualeBasicVO>> response = GenericResponse.build(dichAnnualiVO);
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getListaDichiarazioni] END");
		return response;
	}

	public GenericResponse<String> generaDichiarazionePDF(HttpSession httpSession, Long idDichiarazione) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::generaDichiarazionePDF] BEGIN");
		Optional<TsddrTUtente> user = tsddrTUtenteRepository.findByIdUtente(8L);
		TsddrTDichAnnuale dichAnnuale = dichAnnualeRepository.findOne(idDichiarazione);
		ResponseProtocollaDocumento responseProtocollaDocumento = null;
		MessaggioVO message = new MessaggioVO();

		try {
			responseProtocollaDocumento = doquiServiceFacade.protocollaDocumentoFisico(dichAnnuale, generaReportPDFToByte(dichAnnuale), this.generaPdfFilename(dichAnnuale), user.isPresent()?user.get():null);
		} catch (Exception e) {
			LoggerUtil.error(logger, "[DichiarazioneServiceImpl::generaDichiarazionePDF] Errore nella generazione del PDF", e);
			message = new MessaggioVO();
		}

		GenericResponse<String> response = GenericResponse.build(message, responseProtocollaDocumento!=null?responseProtocollaDocumento.getProtocollo():"");
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::generaDichiarazionePDF] END");
		return response;
	}

	
	public GenericResponse<DocumentoProtocollatoVO> downloadDichiarazionePDF(HttpSession httpSession, Long idDichiarazione) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::downloadDichiarazionePDF] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
        boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
        
		TsddrTDichAnnuale dichAnnuale = dichAnnualeRepository.findOne(idDichiarazione);
		String numProtocollo = dichAnnuale.getNumProtocollo();
		DocumentoProtocollatoVO downloadFileVO = null;

		try {			
			if(actaEnabled) {
				downloadFileVO = doquiServiceFacade.ricercaProtocolloSuACTA(numProtocollo);
			}else {
				downloadFileVO = new DocumentoProtocollatoVO("dichiarazione.pdf", generaReportPDFToByte(dichAnnuale), numProtocollo);
			}
			
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		String operazioneLog = isProfiloBO ? LogConstants.TSDDR_SCARICO_MODELLO_DICHIARAZIONE_ANNUALE_BO : LogConstants.TSDDR_SCARICO_MODELLO_DICHIARAZIONE_ANNUALE_FO;
        csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog, LogConstants.TSDDR_T_DICH_ANNUALI, dichAnnuale.getIdDichAnnuale());
        
		GenericResponse<DocumentoProtocollatoVO> response = GenericResponse.build(downloadFileVO);
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::downloadDichiarazionePDF] END");
		return response;
	}

	public byte[] generaReportPDFToByte(TsddrTDichAnnuale dichAnnuale) throws Exception {
		Map<String, Object> jasperParam = new HashMap<>();
		dichiarazioneReport.setJasperParam(dichAnnuale, jasperParam);
		JasperPrint jasperPrint = utilsReportImpl.printJasper(jasperParam, dichiarazioneReport);
		byte[] b =JasperExportManager.exportReportToPdf(jasperPrint);
		//TODO - da commentare
//		saveDevFile(b);
		return b;
	}

	public void saveDevFile(byte[] b) {
		try {
			FileUtils.writeByteArrayToFile(new File("c:/dichiarazione"+System.currentTimeMillis()+".pdf"), b);
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private boolean areParametriRicercaValid(DichiarazioneParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::verificaParametriRicerca] BEGIN");
		boolean areParametriValid = parametriRicerca.getIdGestore() != null || parametriRicerca.getIdImpianto() != null
				|| parametriRicerca.getAnnoAl() != null || parametriRicerca.getAnnoDal() != null;
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::verificaParametriRicerca] END");
		return areParametriValid;
	}

	@Override
	public GenericResponse<Boolean> existsDichiarazione(HttpSession httpSession,
			ExistsDichiarazioneVO existsDichiarazioneVO) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::existsDichiarazione] BEGIN");
		Optional<List<TsddrTDichAnnuale>> dichAnnuali = dichAnnualeRepository.findByIdImpiantoAndAnnoAndIdGestoreAndIdStatoDichichiarazione(existsDichiarazioneVO.getIdImpianto(), existsDichiarazioneVO.getAnno(), existsDichiarazioneVO.getIdGestore(), StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
		GenericResponse<Boolean> response = GenericResponse.build(!dichAnnuali.isEmpty());
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::existsDichiarazione] END");
		return response;
	}

	@Override
	public GenericResponse<SoggettoMrVO> removeDichiarazioneSoggettoMr(HttpSession httpSession, Long idDichAnnuale,
			Long idSoggettoMr) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::existsDichiarazione] BEGIN");
		Long idUtenteSessione = SessionUtil.getIdUtente(httpSession);
		
		Optional<TsddrTSoggettoMr> soggettoMrOpt = soggettoMrRepository.findByIdSoggettoMrAndIdDichAnnuale(idDichAnnuale, idSoggettoMr);
		
		if(!soggettoMrOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTDichAnnuale non trovata con idDichAnnuale = [%d] e idSoggettoMr = [%d]", idDichAnnuale, idSoggettoMr));
		}
		
		TsddrTSoggettoMr soggettoMr = soggettoMrOpt.get();
		soggettoMr = soggettoMrRepository.save(EntityUtil.setDeleted(soggettoMr, idUtenteSessione, new Date()));
		GenericResponse<SoggettoMrVO> response = GenericResponse.build(soggettoMrEntityMapper.mapEntityToVO(soggettoMr));
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::existsDichiarazione] END");
		return response;
	}
	
	@Override
	public GenericResponse<DichAnnualeVO> insertBozzaDichAnnuale(HttpSession httpSession, DichAnnualeVO dichAnnualeVO) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::insertBozzaDichAnnuale] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatoriBasic(dichAnnualeVO);
		if(!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		
		Optional<List<TsddrTDichAnnuale>> dichAnnualeOpt = this.findDichAnnualiByIdImpiantoAndAnnoAndIdGestoreAndIdStatoDichiarazione(dichAnnualeVO.getImpianto().getIdImpianto(), dichAnnualeVO.getAnno(), dichAnnualeVO.getImpianto().getGestore().getIdGestore(), StatoDichiarazione.BOZZA.getId());
		
		TsddrTDichAnnuale dichAnnuale = null;
		boolean newBozza = true;
		if(dichAnnualeOpt.isEmpty()) {
			// INSERT BOZZA
			dichAnnuale = createBozza(dichAnnualeVO, 1L, idUtente);
			this.logSalvaBozzaDichAnnuale(httpSession, idDatiSogg, dichAnnuale.getIdDichAnnuale());
		} else {
			// UPDATE BOZZA
			newBozza = false;
			dichAnnuale = updateBozza(httpSession, dichAnnualeOpt.get().get(0), dichAnnualeVO, idUtente);
			this.logSalvaBozzaDichAnnuale(httpSession, idDatiSogg, dichAnnuale.getIdDichAnnuale());
		}

		dichAnnualeVO = this.ordinaDichAnnualeVO(this.getDichiarazioneAnnualeVO(dichAnnuale));
		
		GenericResponse<DichAnnualeVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(newBozza ? CodiceMessaggio.P015.name() : CodiceMessaggio.P013.name()),
		        dichAnnualeVO);
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::insertBozzaDichAnnuale] END");
		return response;
	}
	
	@Override
	public GenericResponse<DichAnnualeVO> insertDichAnnuale(HttpSession httpSession, DichAnnualeVO dichAnnualeVO) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::insertDichAnnuale] BEGIN");
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatoriBasic(dichAnnualeVO);
		messaggiVO.addAll(this.verificaDatiObbligatori(dichAnnualeVO));
		if(!CollectionUtils.isEmpty(messaggiVO)) {
			throw new FunctionalException(messaggiVO);
		}
		Optional<List<TsddrTDichAnnuale>> dichAnnualeOpt = this.findDichAnnualiByIdImpiantoAndAnnoAndIdGestoreAndIdStatoDichiarazione(dichAnnualeVO.getImpianto().getIdImpianto(), dichAnnualeVO.getAnno(), dichAnnualeVO.getImpianto().getGestore().getIdGestore(), StatoDichiarazione.BOZZA.getId());
		TsddrTDichAnnuale dichAnnuale = null;
		boolean newBozza = false;
		
		if(dichAnnualeOpt.isEmpty()) {
			// INSERT BOZZA
			newBozza = true;
			dichAnnuale = createBozza(dichAnnualeVO, 1L, idUtente);
			this.logSalvaBozzaDichAnnuale(httpSession, idDatiSogg, dichAnnuale.getIdDichAnnuale());
		} else {
			dichAnnuale = dichAnnualeOpt.get().get(0);
		}
		
		// UPDATE BOZZA
		dichAnnuale = updateBozza(httpSession, dichAnnuale, dichAnnualeVO, idUtente);
		this.logSalvaBozzaDichAnnuale(httpSession, idDatiSogg, dichAnnuale.getIdDichAnnuale());
		
		
		if(!actaEnabled) {
            dichAnnuale.setNumProtocollo("9999");
            dichAnnuale.setDataProtocollo(new Date());
            Optional<TsddrDStatoDichiarazione> stato = statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
            if(stato.isPresent()) dichAnnuale.setStatoDichiarazione(stato.get());            
            EntityUtil.setUpdated(dichAnnuale, idUtente, new Date());
            dichAnnuale = dichAnnualeRepository.save(dichAnnuale);
            this.logSalvaDichAnnuale(httpSession, idDatiSogg, dichAnnuale.getIdDichAnnuale());
        } else { 
        	
    		Optional<TsddrTUtente> user = tsddrTUtenteRepository.findByIdUtente(idUtente);
        	ResponseProtocollaDocumento responseProtocollaDocumento = null;
    		MessaggioVO message = new MessaggioVO();

    		try {
    			responseProtocollaDocumento = doquiServiceFacade.protocollaDocumentoFisico(dichAnnuale, generaReportPDFToByte(dichAnnuale), this.generaPdfFilename(dichAnnuale), user.isPresent()? user.get():null);
            	dichAnnuale.setNumProtocollo(responseProtocollaDocumento.getProtocollo());
    		} catch (Exception e) {
    			LoggerUtil.error(logger, "[DichiarazioneServiceImpl::generaDichiarazionePDF] Errore nella generazione del PDF", e);
    			// TODO impostare messagio
    			message = new MessaggioVO();
    			GenericResponse<DichAnnualeVO> response = GenericResponse.build(message,
    					this.getDichiarazioneAnnualeVO(dichAnnuale));
    			LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::insertDichAnnuale] END");
    			return response;
    		}
        	
            dichAnnuale.setDataProtocollo(new Date());
//            dichAnnuale.setStatoDichiarazione(statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.INVIATA_PROTOCOLLATA.getId()).get());
            Optional<TsddrDStatoDichiarazione> stato = statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
            if(stato.isPresent()) dichAnnuale.setStatoDichiarazione(stato.get());   
            EntityUtil.setUpdated(dichAnnuale, idUtente, new Date());
            dichAnnuale = dichAnnualeRepository.save(dichAnnuale);
            this.logSalvaDichAnnuale(httpSession, idDatiSogg, dichAnnuale.getIdDichAnnuale());
        }

		dichAnnualeVO = this.ordinaDichAnnualeVO(this.getDichiarazioneAnnualeVO(dichAnnuale));
		GenericResponse<DichAnnualeVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(newBozza ? CodiceMessaggio.P016.name() : CodiceMessaggio.P014.name()),
		        dichAnnualeVO);
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::insertDichAnnuale] END");
		return response;
	}
	
	private TsddrTDichAnnuale createBozza(DichAnnualeVO dichAnnualeVO, Long versione, Long idUtente) {
		Date currentDate = new Date();
		
		// DICH ANNUALE
		TsddrTDichAnnuale dichAnnuale = new TsddrTDichAnnuale();
		Optional<TsddrTImpianto> impianto = impiantoRepository.findByIdImpianto(dichAnnualeVO.getImpianto().getIdImpianto());
		dichAnnuale.setImpianto(impianto.isPresent()?impianto.get():null);
		dichAnnuale.setAnno(dichAnnualeVO.getAnno());
		dichAnnuale.setVersione(versione);
		dichAnnuale.setDataDichiarazione(dichAnnualeVO.getDataDichiarazione());
		dichAnnuale.setAnnotazioni(dichAnnualeVO.getAnnotazioni());
//		dichAnnuale.setStatoDichiarazione(statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.BOZZA.getId()).get());
        Optional<TsddrDStatoDichiarazione> stato = statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.BOZZA.getId());
        if(stato.isPresent()) dichAnnuale.setStatoDichiarazione(stato.get());   
		
		// CONFERIMENTI
		List<TsddrTConferimento> conferimenti = new ArrayList<>();
		for(RifiutoConferitoVO rifiutoConferitoVO : dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti()) {
			List<TsddrTConferimento> conferimentiTmp = this.creaConferimenti(OptionalUtil.getContent(unitaMisuraRepository.findByIdUnitaMisura(UnitaMisura.EKG.getId())), OptionalUtil.getContent(rifiutoTariffaRepository.findByIdRifiutoTariffa(rifiutoConferitoVO.getRifiutoTariffa().getIdRifiutoTariffa())), dichAnnuale, dichAnnualeVO.getAnno());
			this.valorizzaConferimenti(conferimentiTmp, rifiutoConferitoVO.getConferimenti(), idUtente, currentDate);
			conferimenti.addAll(conferimentiTmp);
		}
		dichAnnuale.setConferimenti(conferimenti);
		
		// VERSAMENTI
		List<TsddrTVersamento> versamenti = this.creaVersamenti(dichAnnuale);
		if(dichAnnualeVO.getVersamenti() != null) {
		    this.valorizzaVersamenti(versamenti, dichAnnualeVO.getVersamenti().getVersamenti(), idUtente, currentDate);
		}
		dichAnnuale.setVersamenti(versamenti);
		
		// SOGGETTI MR
		List<TsddrTSoggettoMr> soggettiMr = new ArrayList<>();
		valorizzaSoggettiMr(soggettiMr, dichAnnualeVO.getSoggettiMr(), dichAnnuale, idUtente, currentDate);
		dichAnnuale.setSoggettiMr(soggettiMr);
		
		// INDIRIZZO
		if(dichAnnualeVO.getIndirizzo() != null) {
			TsddrTIndirizzo indirizzo = new TsddrTIndirizzo();
			indirizzo.setVersione(1L);
			EntityUtil.setInserted(indirizzo, idUtente, currentDate);
			indirizzoUtil.verificaEValorizzaIndirizzo(indirizzo, dichAnnualeVO.getIndirizzo(), OptionalUtil.getContent(tipoIndirizzoRepository.findByIdTipoIndirizzo(TipoIndirizzo.SEDE_CONSERVAZIONE_DOCUMENTI.getId(), currentDate)));
			dichAnnuale.setIndirizzo(indirizzo);
		}
		
		dichAnnuale = this.valorizzaCreditoSaldoImposta(dichAnnuale);
		EntityUtil.setInserted(dichAnnuale, idUtente, currentDate);
		
		dichAnnuale = dichAnnualeRepository.save(dichAnnuale);
		return dichAnnuale;
	}
	
	private TsddrTDichAnnuale updateBozza(HttpSession httpSession, TsddrTDichAnnuale dichAnnuale, DichAnnualeVO dichAnnualeVO, Long idUtente) {
		Date currentDate = new Date();
		
		// CONFERIMENTI
		updateConferimenti(dichAnnuale, dichAnnualeVO, idUtente, currentDate);
		
		// VERSAMENTI
		updateVersamenti(dichAnnuale, dichAnnualeVO, idUtente, currentDate);
		
		
		// SOGGETTI MR
		// delete fisica per idDichAnnuali, reinserimento dati
		List<TsddrTSoggettoMr> soggettiMr = dichAnnuale.getSoggettiMr();
		soggettoMrRepository.delete(soggettiMr);
		List<TsddrTSoggettoMr> nuoviSoggettiMr = new ArrayList<>(); 
		if(dichAnnualeVO.getSoggettiMr() != null) {
		    this.valorizzaSoggettiMr(nuoviSoggettiMr, dichAnnualeVO.getSoggettiMr(), dichAnnuale, idUtente, currentDate);
		}
		dichAnnuale.setSoggettiMr(nuoviSoggettiMr);
		
		// INDIRIZZO
		if(dichAnnualeVO.getIndirizzo() != null) {
			TsddrTIndirizzo indirizzo = dichAnnuale.getIndirizzo();
			if(dichAnnuale.getIndirizzo() == null) {
				indirizzo = new TsddrTIndirizzo();
				indirizzo.setVersione(1L);
				EntityUtil.setInserted(indirizzo, idUtente, currentDate);
			} else {
				indirizzo.setVersione(indirizzo.getVersione() + 1);
				indirizzo.setOriginalId(dichAnnuale.getIndirizzo().getIdIndirizzo());
				EntityUtil.setUpdated(indirizzo, idUtente, currentDate);
			}
			this.verificaEValorizzaIndirizzo(indirizzo, dichAnnualeVO.getIndirizzo(), OptionalUtil.getContent(tipoIndirizzoRepository.findByIdTipoIndirizzo(TipoIndirizzo.SEDE_CONSERVAZIONE_DOCUMENTI.getId(), currentDate)));
			dichAnnuale.setIndirizzo(indirizzo);
		}
		
		dichAnnuale.setAnnotazioni(dichAnnualeVO.getAnnotazioni());
		dichAnnuale = this.valorizzaCreditoSaldoImposta(dichAnnuale);
		EntityUtil.setUpdated(dichAnnuale, idUtente, currentDate);
		dichAnnuale = dichAnnualeRepository.save(dichAnnuale);
		return dichAnnuale;
	}
	
	private void updateConferimenti(TsddrTDichAnnuale dichAnnuale, DichAnnualeVO dichAnnualeVO, Long idUtente, Date currentDate) {
        if(dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti() != null) {
            List<TsddrTConferimento> nuoviConferimenti = new ArrayList<>();
            for(RifiutoConferitoVO rifiutoConferitoVO : dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti()) {
                for(ConferimentoVO conferimentoVO : rifiutoConferitoVO.getConferimenti()) {
                    if(conferimentoVO.getIdConferimento() != null) { // UPDATE
                        TsddrTConferimento conferimento = OptionalUtil.getContent(dichAnnuale.getConferimenti().stream().filter(c -> conferimentoVO.getIdConferimento().equals(c.getIdConferimento())).findFirst());
                        conferimento.setRifiutoTariffa(OptionalUtil.getContent(rifiutoTariffaRepository.findByIdRifiutoTariffa(conferimentoVO.getRifiutoTariffa().getIdRifiutoTariffa())));
                        conferimento.setQuantita(BigDecimal.valueOf(conferimentoVO.getQuantita()));
                        conferimento.setImporto(conferimento.getQuantita().multiply(conferimento.getRifiutoTariffa().getImporto()));
                        EntityUtil.setUpdated(conferimento, idUtente, currentDate);
                    } else if(!conferimentoVO.isJustBeenInsert()) {// INSERT
                        List<TsddrTConferimento> conferimentiTmp = this.creaConferimenti(unitaMisuraRepository.findByIdUnitaMisura(UnitaMisura.EKG.getId()).orElseThrow(), rifiutoTariffaRepository.findByIdRifiutoTariffa(conferimentoVO.getRifiutoTariffa().getIdRifiutoTariffa()).orElseThrow(), dichAnnuale, dichAnnualeVO.getAnno());
                        this.valorizzaConferimenti(conferimentiTmp, rifiutoConferitoVO.getConferimenti(), idUtente, currentDate);
                        nuoviConferimenti.addAll(conferimentiTmp);
                        break;
                    }
                }
            }
            dichAnnuale.getConferimenti().addAll(nuoviConferimenti);
        }
    }
	
	private void updateVersamenti(TsddrTDichAnnuale dichAnnuale, DichAnnualeVO dichAnnualeVO, Long idUtente, Date currentDate) {
        for(TsddrTVersamento versamento : dichAnnuale.getVersamenti()) {
            for(VersamentoVO versamentoVO : dichAnnualeVO.getVersamenti().getVersamenti()) {
                if(versamento.getPeriodo().getIdPeriodo().longValue() == versamentoVO.getPeriodo().getIdPeriodo().longValue()) {
                    versamento.setImportoVersato(BigDecimal.valueOf(versamentoVO.getImportoVersato()));
                    if(versamentoVO.getImportoVersato()!=0) {
                        versamento.setDataVersamento(versamentoVO.getDataVersamento());
                    }else {
                        versamento.setDataVersamento(null);
                    }
                    EntityUtil.setUpdated(versamento, idUtente, currentDate);
                }
            }
        }
    }
	
	private void valorizzaSoggettiMr(List<TsddrTSoggettoMr> soggettiMr, List<SoggettoMrVO> soggettiMrVO, TsddrTDichAnnuale dichAnnuale, Long idUtente, Date targetDate) {
		for(SoggettoMrVO soggettoMrVO : soggettiMrVO) {
			TsddrTSoggettoMr soggettoMr = new TsddrTSoggettoMr();
			soggettoMr.setCodFiscPartiva(soggettoMrVO.getCodFiscPartiva());
			soggettoMr.setRagSociale(soggettoMrVO.getRagSociale());
			soggettoMr.setObbRagg(soggettoMrVO.getObbRagg());
			soggettoMr.setDichAnnuale(dichAnnuale);
			EntityUtil.setInserted(soggettoMr, idUtente, targetDate);
			soggettiMr.add(soggettoMr);
		}
	}
	
	private void verificaEValorizzaIndirizzo(TsddrTIndirizzo indirizzo, IndirizzoVO indirizzoVO, TsddrDTipoIndirizzo tipoIndirizzo) {
		Date currentDate = new Date();
		indirizzo.setTipoIndirizzo(tipoIndirizzo);
		indirizzo.setIndirizzo(indirizzoVO.getIndirizzo());
		indirizzo.setCap(indirizzoVO.getCap());
		
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
		
		if(indirizzoVO.getComune().getProvincia() != null) {
    		Optional<TsddrDProvincia> provinciaOpt = provinciaRepository.findProvinciaById(indirizzoVO.getComune().getProvincia().getIdProvincia(), currentDate);
    		if(provinciaOpt.isEmpty()) {
    			throw new BadRequestException(String.format("Nessun TsddrDProvincia trovato con idProvincia = [%d]", indirizzoVO.getComune().getProvincia().getIdProvincia()));
    		}
    		
    		if(!provinciaOpt.get().getComuni().stream().anyMatch(p -> p.getIdComune().longValue() == comuneOpt.get().getIdComune().longValue())) {
    			throw new BadRequestException(String.format("La Provincia idProvincia = [%d] non contiene il Comune idComune = [%d]", indirizzoVO.getComune().getProvincia().getIdProvincia(), indirizzoVO.getComune().getIdComune()));	
    		}
    		indirizzo.getComune().setProvincia(provinciaOpt.get());
	    }
	}
	
	private List<TsddrTConferimento> creaConferimenti(TsddrDUnitaMisura unitaMisura, TsddrTRifiutoTariffa rifiutoTariffa, TsddrTDichAnnuale dichAnnuale, Long anno) {
		List<TsddrTConferimento> conferimenti = new ArrayList<>();
		for(int i=1; i<=4; i++) {
			TsddrTConferimento conferimento = new TsddrTConferimento();
			conferimento.setQuantita(new BigDecimal(0));
			conferimento.setImporto(new BigDecimal(0));
			conferimento.setUnitaMisura(unitaMisura);
			conferimento.setRifiutoTariffa(rifiutoTariffa);
			conferimento.setAnno(anno);
			conferimento.setDichAnnuale(dichAnnuale);
			switch(i) {
				case 1:
					conferimento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.I_TRIMESTRE.getId())));
					break;
				case 2:
					conferimento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.II_TRIMESTRE.getId())));
					break;
				case 3:
					conferimento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.III_TRIMESTRE.getId())));
					break;
				case 4:
					conferimento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.IV_TRIMESTRE.getId())));
					break;
				default:
					break;
				
			}
			conferimenti.add(conferimento);
		}
		return conferimenti;
	}
	
	private List<TsddrTVersamento> creaVersamenti(TsddrTDichAnnuale dichAnnuale) {
		List<TsddrTVersamento> versamenti = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			TsddrTVersamento versamento = new TsddrTVersamento();
			versamento.setImportoVersato(new BigDecimal(0));
			versamento.setDichAnnuale(dichAnnuale);
			switch(i) {
				case 1:
					versamento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.I_TRIMESTRE.getId())));
					break;
				case 2:
					versamento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.II_TRIMESTRE.getId())));
					break;
				case 3:
					versamento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.III_TRIMESTRE.getId())));
					break;
				case 4:
					versamento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.IV_TRIMESTRE.getId())));
					break;
				case 5:
					versamento.setPeriodo(OptionalUtil.getContent(periodoRepository.findByIdPeriodo(Periodo.CONGUAGLIO.getId())));
					break;
				default:
					break;
				
			}
			versamenti.add(versamento);
		}
		return versamenti;
	}
	
	private void valorizzaConferimenti(List<TsddrTConferimento> conferimenti, List<ConferimentoVO> conferimentiVO, Long idUtente, Date currentDate) {
		for(TsddrTConferimento conferimento : conferimenti) {
			for(ConferimentoVO conferimentoVO : conferimentiVO) {
				if(conferimento.getPeriodo().getIdPeriodo().longValue() == conferimentoVO.getPeriodo().getIdPeriodo().longValue()) {
					conferimento.setQuantita(BigDecimal.valueOf(conferimentoVO.getQuantita()));
					conferimento.setImporto(conferimento.getQuantita().multiply(
							OptionalUtil.getContent(rifiutoTariffaRepository.findByIdRifiutoTariffa(conferimento.getRifiutoTariffa().getIdRifiutoTariffa())).getImporto()));
					EntityUtil.setInserted(conferimento, idUtente, currentDate);
					conferimentoVO.setJustBeenInsert(true);
				}
			}
		}
	}
	
	private void valorizzaVersamenti(List<TsddrTVersamento> versamenti, List<VersamentoVO> versamentiVO, Long idUtente, Date currentDate) {
		for(TsddrTVersamento versamento : versamenti) {
			for(VersamentoVO versamentoVO : versamentiVO) {
				if(versamento.getPeriodo().getIdPeriodo().longValue() == versamentoVO.getPeriodo().getIdPeriodo().longValue()) {
					versamento.setImportoVersato(BigDecimal.valueOf(versamentoVO.getImportoVersato()));
					if(versamentoVO.getImportoVersato()!=0) {
						versamento.setDataVersamento(versamentoVO.getDataVersamento());
					}else {
						versamento.setDataVersamento(null);
					}
					EntityUtil.setInserted(versamento, idUtente, currentDate);
				}
			}
		}
	}
	
	private void logSalvaBozzaDichAnnuale(HttpSession httpSession, Long idDatiSogg, Long idDichAnnuale) {
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_SALVA_IN_BOZZA_NUOVA_DICHIARAZIONE_ANNUALE,	LogConstants.TSDDR_T_DICH_ANNUALI, idDichAnnuale);
	}
	
	private void logSalvaDichAnnuale(HttpSession httpSession, Long idDatiSogg, Long idDichAnnuale) {
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_INVIO_NUOVA_DICHIARAZIONE_ANNUALE, LogConstants.TSDDR_T_DICH_ANNUALI, idDichAnnuale);
	}
	
	private List<MessaggioVO> verificaDatiObbligatoriBasic(DichAnnualeVO dichAnnualeVO) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::verificaDatiObbligatoriBasic] BEGIN");
		List<MessaggioVO> messaggiVO = new ArrayList<>();
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
		
		if (dichAnnualeVO.getAnno() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "anno"));
		}
		
		if (dichAnnualeVO.getDataDichiarazione() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "data"));
		}
		
		if (dichAnnualeVO.getImpianto() == null || 
				dichAnnualeVO.getImpianto().getIdImpianto() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "impianto"));
		}
		
		if (dichAnnualeVO.getImpianto() == null || 
				dichAnnualeVO.getImpianto().getGestore() == null ||
				dichAnnualeVO.getImpianto().getGestore().getIdGestore() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "impianto.gestore"));
		}
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::verificaDatiObbligatoriBasic] END");
		return messaggiVO;
	}
	
	private List<MessaggioVO> verificaDatiObbligatori(DichAnnualeVO dichAnnualeVO) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::verificaDatiObbligatori] BEGIN");
		List<MessaggioVO> messaggiVO = this.verificaDatiObbligatoriBasic(dichAnnualeVO);
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
	
		// dati afferenti ad un versamento per almeno un trimestre o conguaglio		
		if (!areVersamentiValid(dichAnnualeVO)) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "versamenti"));
		}
		
		// almeno 1 rifiuto (riga) inserita
		if (!areRifiutiConferitiValid(dichAnnualeVO)) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "rifiutiConferiti"));
		}
		
		// Sedime, Indirizzo e Comune 
		if (dichAnnualeVO.getIndirizzo() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo"));
		}
		
		if (dichAnnualeVO.getIndirizzo() == null ||
				dichAnnualeVO.getIndirizzo().getIndirizzo() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.indirizzo"));
		}
		
		if (dichAnnualeVO.getIndirizzo() == null ||
				dichAnnualeVO.getIndirizzo().getSedime() == null ||
				dichAnnualeVO.getIndirizzo().getSedime().getIdSedime() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.sedime.idSedime"));
		}
		
		if (dichAnnualeVO.getIndirizzo() == null ||
				dichAnnualeVO.getIndirizzo().getComune() == null ||
				dichAnnualeVO.getIndirizzo().getComune().getIdComune() == null) {
			messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.comune.idComune"));
		}
		
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::verificaDatiObbligatori] END");
		return messaggiVO;
	}
	
	private Optional<List<TsddrTDichAnnuale>> findDichAnnualiByIdImpiantoAndAnnoAndIdGestoreAndIdStatoDichiarazione(Long idImpianto, Long anno, Long idGestore, long idStatoDichiarazione) {
		return dichAnnualeRepository.findByIdImpiantoAndAnnoAndIdGestoreAndIdStatoDichichiarazione(idImpianto, anno, idGestore, idStatoDichiarazione);
	}
	
	/* Versamenti
	* Data ed importo del versamento relativo ad almeno uno dei 4 trimestri
	*/
	private boolean areVersamentiValid(DichAnnualeVO dichAnnualeVO) {
		boolean areVersamentiValid = false;
		if (dichAnnualeVO.getVersamenti() != null ||
				dichAnnualeVO.getVersamenti().getVersamenti() != null ||
				!dichAnnualeVO.getVersamenti().getVersamenti().isEmpty()) {
			for(VersamentoVO versamentoVO : dichAnnualeVO.getVersamenti().getVersamenti()) {
				if(versamentoVO.getDataVersamento() != null &&
						(versamentoVO.getImportoVersato() != null && versamentoVO.getImportoVersato() != 0) ) {
					areVersamentiValid = true;
					break;
				}
			}
		}
		return areVersamentiValid;
	}
	
	/* Rifiuti Conferiti 
	* La selezione di almeno una tipologia di rifiuto conferito in discarica e la valorizzazione di almeno un quantitativo relativo ad un trimestre.
	*/
	private boolean areRifiutiConferitiValid(DichAnnualeVO dichAnnualeVO) {
		boolean areRifiutiConferitiValid = false;
		if (dichAnnualeVO.getRifiutiConferiti() != null ||
				dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti() != null ||
				!dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti().isEmpty()) {
			for(RifiutoConferitoVO rifiutoConferitoVO : dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti()) {
				for(ConferimentoVO conferimentoVO : rifiutoConferitoVO.getConferimenti()) {
					if(rifiutoConferitoVO.getRifiutoTariffa() != null &&
					        rifiutoConferitoVO.getRifiutoTariffa().getIdRifiutoTariffa() != null &&
							conferimentoVO.getQuantita() != null) {
						areRifiutiConferitiValid = true;
						break;
					}
				}
			}
		}
		return areRifiutiConferitiValid;
	}

	private DichAnnualeVO getDichiarazioneVO(Long idDichAnnuale) {		
		Optional<TsddrTDichAnnuale> dichAnnualeOpt = dichAnnualeRepository.findByIdDichAnnuale(idDichAnnuale);
		if(!dichAnnualeOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTDichAnnuale non trovato con idDichAnnuale = [%d]", idDichAnnuale));
		}
		
		TsddrTDichAnnuale dichAnnuale = dichAnnualeOpt.get();
		
		// recupero linee e sottolinee valide
		List<TsddrRImpiantoLinea> lineeImpianto = impiantoLineaRepository.findByIdImpianto(dichAnnuale.getImpianto().getIdImpianto());
        List<GenericLineaVO> genericLineeVO = impiantoUtil.getGenericLineeVOfromLinee(lineeImpianto);
		
		// recupero atti
		List<TsddrTAtto> atti = attoRepository.findAttiByIdImpianto(dichAnnuale.getImpianto().getIdImpianto(), new Date());
		List<AttoVO> attiVO = attoEntityMapper.mapListEntityToListVO(atti);
				
		Optional<TsddrTDichAnnuale> dichAnnualeAnnoPrecedenteOpt = dichAnnualeRepository.findByIdImpiantoAndAnnoAndStato(dichAnnuale.getImpianto().getIdImpianto(), (dichAnnuale.getAnno() - 1), StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
		BigDecimal creditoImposta = dichAnnualeAnnoPrecedenteOpt.map(TsddrTDichAnnuale::getCreditoImposta).orElse(null);
		
		DichAnnualeVO dichAnnualeVO = dichAnnualeEntityMapper.mapEntityToVO(dichAnnuale, creditoImposta != null ? creditoImposta.doubleValue() : null);
		
		// setto gestore 
        dichAnnualeVO.getImpianto().setGestore(gestoreEntityMapper.mapEntityToVO(dichAnnuale.getImpianto().getGestore()));
        // setto impianto.attiVO
        dichAnnualeVO.getImpianto().setAtti(attiVO);
        // setto impianto.lineeVO
        dichAnnualeVO.getImpianto().setLinee(genericLineeVO);
        
        dichAnnualeVO = this.ordinaDichAnnualeVO(dichAnnualeVO);
        		
		return dichAnnualeVO;
	}
	
	@Override
	public GenericResponse<DichAnnualeVO> getDichiarazione(HttpSession httpSession, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getDichiarazione] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);

		// dichiarazione anno precedente
		boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
		
		DichAnnualeVO dichAnnualeVO = getDichiarazioneVO(idDichAnnuale);
		        
		GenericResponse<DichAnnualeVO> response = GenericResponse.build(dichAnnualeVO);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
				isProfiloBO ? LogConstants.TSDDR_VISUALIZZAZIONE_DICHIARAZIONE_ANNUALE_BO : LogConstants.TSDDR_VISUALIZZAZIONE_DICHIARAZIONE_ANNUALE_FO,
				LogConstants.TSDDR_T_DICH_ANNUALI, 
				isProfiloBO ? String.valueOf(dichAnnualeVO.getIdDichAnnuale()) : String.format("%d (%d)", dichAnnualeVO.getIdDichAnnuale(), dichAnnualeVO.getVersione()));
		
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getDichiarazione] END");
		return response;
	}
	
	@Override
	public GenericResponse<DichAnnualeVO> getNuovaVersioneDichiarazione(HttpSession httpSession, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getNuovaVersioneDichiarazione] BEGIN");
		
		Optional<TsddrTDichAnnuale> dichAnnualeOpt = dichAnnualeRepository.findByIdDichAnnuale(idDichAnnuale);
		if(!dichAnnualeOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTDichAnnuale non trovato con idDichAnnuale = [%d]", idDichAnnuale));
		}
		
		TsddrTDichAnnuale dichAnnuale = dichAnnualeOpt.get();

		if (!List.of(StatoDichiarazione.INVIATA_PROTOCOLLATA.getId(), StatoDichiarazione.ELIMINATA.getId())
				.contains(dichAnnuale.getStatoDichiarazione().getIdStatoDichiarazione())) {
			throw new FunctionalException("è possibile duplicare solamente le dichiarazioni INVIATE o ANNULLATE");
		}
		
		if(!this.isDuplicaAllowed(dichAnnuale.getImpianto().getIdImpianto(), dichAnnuale.getAnno(), dichAnnuale.getImpianto().getGestore().getIdGestore(), (dichAnnuale.getVersione() + 1))) {
		    throw new FunctionalException(String.format("La dichiarazione annuale con idImpianto = [%d], anno = [%d], idGestore = [%d], versione= [%d] e' gia' stata duplicata", 
		            dichAnnuale.getImpianto().getIdImpianto(), dichAnnuale.getAnno(), dichAnnuale.getImpianto().getGestore().getIdGestore(), dichAnnuale.getVersione()));
		}
		
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Date currentDate = new Date();
		
		Optional<TsddrTDichAnnuale> dichAnnualeAnnoPrecedenteOpt = dichAnnualeRepository
				.findByIdImpiantoAndAnnoAndStato(dichAnnuale.getImpianto().getIdImpianto(), (dichAnnuale.getAnno() - 1), StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
		
		BigDecimal creditoImposta = dichAnnualeAnnoPrecedenteOpt.map(TsddrTDichAnnuale::getCreditoImposta).orElse(null);

		DichAnnualeVO dichAnnualeVO = dichAnnualeEntityMapper.mapEntityToVO(dichAnnuale,
				creditoImposta != null ? creditoImposta.doubleValue() : null);

		// issue 147 - calcolare la versione verificando per lo stesso anno-gestore-impianto il valore maggiore e non partendo dalla dichiarazione visualizzata
		Optional<List<TsddrTDichAnnuale>> dichAnnualeMaxVersione = dichAnnualeRepository
				.findByIdImpiantoAndAnno(dichAnnuale.getImpianto().getIdImpianto(), dichAnnuale.getAnno());
		
		TsddrTDichAnnuale newDichAnnuale = this.createBozza(dichAnnualeVO, dichAnnualeMaxVersione.isPresent()?(dichAnnualeMaxVersione.get().get(0).getVersione() + 1):1, idUtente);
		newDichAnnuale.setIdUserInsert(idUtente);
		newDichAnnuale.setDataInsert(currentDate);
		
		TsddrTDichAnnuale lastDich = dichAnnualeRepository.save(newDichAnnuale);
		
		GenericResponse<DichAnnualeVO> response = GenericResponse.build(dichAnnualeEntityMapper.mapEntityToVO(lastDich,
				creditoImposta != null ? creditoImposta.doubleValue() : null));
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
				LogConstants.TSDDR_NUOVA_VERSIONE_DICHIARAZIONE_ANNUALE, LogConstants.TSDDR_T_DICH_ANNUALI,
				String.format("%d (%d)", dichAnnuale.getIdDichAnnuale(), dichAnnuale.getVersione()));
		
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getNuovaVersioneDichiarazione] END");
		return response;
	}
	
	private DichAnnualeVO getDichiarazioneAnnualeVO(TsddrTDichAnnuale dichAnnuale) {
		Optional<TsddrTDichAnnuale> dichAnnualeAnnoPrecedenteOpt = dichAnnualeRepository.findByIdImpiantoAndAnnoAndStato(dichAnnuale.getImpianto().getIdImpianto(), (dichAnnuale.getAnno() - 1),StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
		BigDecimal creditoImposta = dichAnnualeAnnoPrecedenteOpt.map(TsddrTDichAnnuale::getCreditoImposta).orElse(null);
		return dichAnnualeEntityMapper.mapEntityToVO(dichAnnuale, creditoImposta != null ? creditoImposta.doubleValue() : null);
	}

	@Override
	public GenericResponse<DichAnnualeVO> deleteDichiarazione(HttpSession httpSession, Long idDichAnnuale) {
		LoggerUtil.debug(logger, "[GestoreServiceImpl::deleteDichiarazione] BEGIN");
		
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		
		Date currentDate = new Date();
		
		Optional<TsddrTDichAnnuale> dichAnnualeOpt = dichAnnualeRepository.findByIdDichAnnualeAndIdStatoDichAnnuale(idDichAnnuale, StatoDichiarazione.BOZZA.getId());
		if(!dichAnnualeOpt.isPresent()) {
			throw new RecordNotFoundException(String.format("TsddrTDichAnnuale non trovata con idDichAnnuale = [%d] e statoDichAnnuale = [%s]", idDichAnnuale, StatoDichiarazione.BOZZA.toString()));
		}
		
		TsddrTDichAnnuale dichAnnuale = dichAnnualeOpt.get();
		dichAnnuale.setStatoDichiarazione(OptionalUtil.getContent(statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.ELIMINATA.getId())));
		EntityUtil.setDeleted(dichAnnuale, idUtente, currentDate);
		dichAnnualeRepository.save(dichAnnuale);
		
		csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
				LogConstants.TSDDR_ELIMINAZIONE_DIC_ANNUALE, LogConstants.TSDDR_T_DICH_ANNUALI,
				String.format("%d, %d, %d", dichAnnuale.getIdDichAnnuale(), dichAnnuale.getAnno() , dichAnnuale.getVersione()));
		
		GenericResponse<DichAnnualeVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P012.name()),
				this.getDichiarazioneAnnualeVO(dichAnnuale));
		
		LoggerUtil.debug(logger, "[GestoreServiceImpl::deleteDichiarazione] END");
		return response;
	}

    @Override
    public GenericResponse<DichAnnualeVO> getDichiarazioneByIdGestoreIdImpiantoAnno(HttpSession httpSession,
            Long idGestore, Long idImpianto, Long anno) {
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getDichiarazioneByIdGestoreIdImpiantoAnno] BEGIN");
        
        Optional<TsddrTDichAnnuale> dichAnnualeOpt = dichAnnualeRepository.findByIdImpiantoAndAnnoAndStato(idImpianto, anno,StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
        if(!dichAnnualeOpt.isPresent()) {
            LoggerUtil.warn(logger, String.format("TsddrTDichAnnuale non trovato con idImpianto = [%d], anno = [%d]", idImpianto, anno));
            MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.A002.name());
            return  GenericResponse.build(messaggioVO);
        }
        
        TsddrTDichAnnuale dichAnnuale = dichAnnualeOpt.get();
        BigDecimal creditoImposta = dichAnnualeOpt.map(TsddrTDichAnnuale::getCreditoImposta).orElse(null);
        
        GenericResponse<DichAnnualeVO> response = GenericResponse.build(dichAnnualeEntityMapper.mapEntityToVO(dichAnnuale, creditoImposta != null ? creditoImposta.doubleValue() : null));
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getDichiarazioneByIdGestoreIdImpiantoAnno] END");
        return response;
    }

    @Override
    public GenericResponse<Boolean> isDuplicaAllowed(HttpSession session,
            AllowDuplicaDichiarazioneVO allowDuplicaDichiarazioneVO) {
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::isDuplicaAllowed] BEGIN");
        GenericResponse<Boolean> response;
        if(this.isDuplicaAllowed(allowDuplicaDichiarazioneVO.getIdImpianto(), allowDuplicaDichiarazioneVO.getAnno(), allowDuplicaDichiarazioneVO.getIdGestore(), (allowDuplicaDichiarazioneVO.getVersione() + 1))) {
            response = GenericResponse.build(true);
        } else {
            response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E014.name()), false);
        }
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::isDuplicaAllowed] END");
        return response;
    }
    
    private boolean isDuplicaAllowed(Long idImpianto, Long anno, Long idGestore, Long versione) {
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::isDuplicaAllowed] BEGIN");
        boolean isDuplicaAllowed;
//        Optional<TsddrTDichAnnuale> dichAnnuale = dichAnnualeRepository.findByIdImpiantoAndAnnoAndIdGestoreAndVersione(idImpianto, anno, idGestore, versione, StatoDichiarazione.BOZZA.getId());
//        Optional<TsddrTDichAnnuale> dichAnnuale = dichAnnualeRepository.findByIdImpiantoAndAnnoAndIdGestoreAndVersione(idImpianto, anno, idGestore, versione, StatoDichiarazione.BOZZA.getId());
        Optional<List<TsddrTDichAnnuale>> dichAnnuale = dichAnnualeRepository.findByIdImpiantoAndAnnoAndIdGestoreAndIdStatoDichichiarazione(idImpianto, anno, idGestore, StatoDichiarazione.BOZZA.getId());
        if(dichAnnuale.isPresent()) {
            isDuplicaAllowed = false;
        } else {
            isDuplicaAllowed = true;
        }
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::isDuplicaAllowed] END");
        return isDuplicaAllowed;
    }
    
    private TsddrTDichAnnuale valorizzaCreditoSaldoImposta(TsddrTDichAnnuale dichAnnuale) {
        DichAnnualeVO tmpVO = this.getDichiarazioneAnnualeVO(dichAnnuale);
        dichAnnuale.setCreditoImposta(BigDecimal.valueOf(tmpVO.getVersamenti().getCredito() != null ? tmpVO.getVersamenti().getCredito() : 0));
        dichAnnuale.setSaldoImposta(BigDecimal.valueOf(tmpVO.getVersamenti().getDebito() != null ? tmpVO.getVersamenti().getDebito() : 0));
        return dichAnnuale;
    }
    
    private String generaPdfFilename(TsddrTDichAnnuale dichAnnuale) {
        var sb = new StringBuilder();
        sb.append("A1103A-TS-")
        .append(dichAnnuale.getAnno()).append("-")
        .append(dichAnnuale.getVersione()).append("-")
        .append(dichAnnuale.getImpianto().getIdImpianto()).append("-")
        .append(dichAnnuale.getImpianto().getGestore().getCodFiscPartiva())
        .append(".pdf");
        return sb.toString();
    }
    
    private DichAnnualeVO ordinaDichAnnualeVO(DichAnnualeVO dichAnnualeVO) {
      LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::ordinaDichAnnualeVO] BEGIN");
      // ordino i versamenti per fe
      if(dichAnnualeVO.getVersamenti() != null && 
              dichAnnualeVO.getVersamenti().getVersamenti() != null) {
          List<VersamentoVO> versamentiVO = dichAnnualeVO.getVersamenti().getVersamenti()
              .stream()
              .sorted((o1, o2) -> o1.getPeriodo().getIdPeriodo().compareTo(o2.getPeriodo().getIdPeriodo()))
              .collect(Collectors.toList());
          dichAnnualeVO.getVersamenti().setVersamenti(versamentiVO);
      }
      
      // ordino i rifiuti conferiti per fe
      if(dichAnnualeVO.getRifiutiConferiti() != null && 
              dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti() != null) {
          for(RifiutoConferitoVO rifiutoConferitoVO : dichAnnualeVO.getRifiutiConferiti().getRifiutiConferiti()) {
              List<ConferimentoVO> conferimentiVO = rifiutoConferitoVO.getConferimenti()
                  .stream()
                  .sorted((o1, o2) -> o1.getPeriodo().getIdPeriodo().compareTo(o2.getPeriodo().getIdPeriodo()))
                  .collect(Collectors.toList());
              rifiutoConferitoVO.setConferimenti(conferimentiVO);
          }
      }
      LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::ordinaDichAnnualeVO] END");
      return dichAnnualeVO;
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
			DichiarazioneParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::downloadReport] BEGIN");

		//metodo per riportare i filtri su nome file
		String filtriSuffix = getFilterByRequest(parametriRicerca);
		
		GenericResponse<List<DichAnnualeBasicVO>> listaGestiori = getListaDichiarazioniReport(session, parametriRicerca, filtriSuffix);
				
        ExcelDataUtil excel = new ExcelDataUtil(getTsddrTReport().getDescReport(), filtriSuffix);
        ExcelSheet sheet = excel.addSheet(getTsddrTReport().getDescReport());
        
//      1	Colonna A	Gestore CF / PI - Denominazione
        sheet.addColumn("Gestore CF / PI - Denominazione");
//      2	Colonna B	Impianto Denominazione - Dati
        sheet.addColumn("Impianto Denominazione - Dati");
//    	3	Colonna C 	Anno Dichiarazione
        sheet.addColumn("Anno Dichiarazione");
//    	4	Colonna D	ID Dichiarazione
        sheet.addColumn("ID Dichiarazione");
//    	5	Colonna E	Data Dic
        sheet.addColumn("Data Dic.");
//      6	Colonna F	Versione
        sheet.addColumn("Versione");
//      7	Colonna G	Stato Dic.
        sheet.addColumn("Stato Dic.");
//      8	Colonna H	Protocollo
        sheet.addColumn("Protocollo");
//      9	Colonna I 	Importo Dovuto
        sheet.addColumn("Importo Dovuto");
//      10	Colonna j	Importo Versato
        sheet.addColumn("Importo Versato");
//      11	Colonna K 	Importo a Credito
        sheet.addColumn("Importo a Credito");
//      12	Colonna L 	Annotazioni
        sheet.addColumn("Annotazioni");

        addContent(sheet, listaGestiori.getContent());
        
        ReportVO report = null;
		try {
			report = new ReportVO(excel.getFileName(), excel.getFile());
		} catch (IOException e) {
			LoggerUtil.warn(logger, "[DichiarazioneServiceImpl::downloadReport] problemi in generazione report");
		}
        
		GenericResponse<ReportVO> response = new GenericResponse<ReportVO>(report);
		
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::downloadReport] END");
		return response;
	}

	private String getFilterByRequest(DichiarazioneParametriRicerca parametriRicerca) {
		StringBuffer sb = new StringBuffer();
		// Imposto i filtri nel nome file
		if(parametriRicerca.getIdGestore()!=null) {
			TsddrTGestore gestore = gestoreRepository.getOne(parametriRicerca.getIdGestore());
			sb.append("_Gestore_").append(gestore.getRagSociale());
		}
		if(parametriRicerca.getIdImpianto()!=null) {
			TsddrTImpianto impianto = impiantoRepository.getOne(parametriRicerca.getIdImpianto());
			sb.append("_Impianto_").append(impianto.getDenominazione());
		}
		if(parametriRicerca.getAnnoDal()!=null) {
			sb.append("_Anno dal_").append(parametriRicerca.getAnnoDal());
		}
		if(parametriRicerca.getAnnoAl()!=null) {
			sb.append("_Anno al_").append(parametriRicerca.getAnnoAl());
		}
		if(sb.toString().length()>0) {
			sb.append("_");
		}
		
		return sb.toString();
	}

	private void addContent(ExcelSheet sheet, List<DichAnnualeBasicVO> list) {
		
		for(DichAnnualeBasicVO dichVO : list){
//			1	Colonna A	Gestore CF / PI - Denominazione
//			2	Colonna B	Impianto Denominazione - Dati
//			3	Colonna C 	Anno Dichiarazione
//			4	Colonna D	ID Dichiarazione
//			5	Colonna E	Data Dic.
//			6	Colonna F	Versione
//			7	Colonna G	Stato Dic.
//			8	Colonna H	Protocollo
//			9	Colonna I 	Importo Dovuto
//			10	Colonna j	Importo Versato
//			11	Colonna K 	Importo a Credito
//			12	Colonna L 	Annotazioni
			DichAnnualeVO dichAnnualeVO = getDichiarazioneVO(dichVO.getIdDichAnnuale());
				sheet.addDataToBody(
						dichVO.getImpianto().getGestore().getCodFiscPartiva() + " - " + dichVO.getImpianto().getGestore().getRagSociale(),
						dichVO.getImpianto().getDenominazione()  + " - " 
							+  dichVO.getImpianto().getIndirizzo().getSedime().getDescSedime()
							+ " " + dichVO.getImpianto().getIndirizzo().getIndirizzo()
							+ " - " + dichVO.getImpianto().getIndirizzo().getComune().getComune()
							+ " (" + (dichVO.getImpianto().getIndirizzo().getCap()!=null?dichVO.getImpianto().getIndirizzo().getCap():"") + ")"
							+ " " + dichVO.getImpianto().getIndirizzo().getComune().getProvincia().getSiglaProv(),
						dichVO.getAnno(),
						dichVO.getIdDichAnnuale(),
						new SimpleDateFormat(DateUtil.ddMMyyyy).format(dichVO.getDataDichiarazione()),
						dichVO.getVersione(),
						dichAnnualeVO.getStatoDichiarazione().getDescrStatoDichiarazione(),
						dichVO.getNumProtocollo(),
						dichAnnualeVO.getRifiutiConferiti().getTotali().getTotale().getImporto(),
						dichAnnualeVO.getVersamenti().getTotale(),
						dichAnnualeVO.getVersamenti().getCredito(),
						dichAnnualeVO.getAnnotazioni());
		}
	}

	private GenericResponse<List<DichAnnualeBasicVO>> getListaDichiarazioniReport(HttpSession httpSession, DichiarazioneParametriRicerca parametriRicerca, String filtriSuffix) {
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getListaDichiarazioniReport] BEGIN");
		Long idProfilo = SessionUtil.getIdProfilo(httpSession);
		Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
		Long idUtente = SessionUtil.getIdUtente(httpSession);
		
		Date currentDate = new Date();
		
		boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
		List<TsddrTGestore> gestoriUtente = gestoreRepository.findGestoriByIdUtenteIdProfilo(idUtente, idProfilo, currentDate);
		List<TsddrTDichAnnuale> dichAnnuali = dichAnnualeRepository.findAll(TsddrTDichAnnualeSpecification.searchByParams(parametriRicerca, currentDate, isProfiloBO, gestoriUtente));
		
		if(dichAnnuali.isEmpty()) {
			MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.A002.name());
			throw new FunctionalException(String.format("Nessuna dichiarazione annuale trovato con i parametri di ricerca inseriti"), messaggioVO);
		}
		
		List<DichAnnualeBasicVO> dichAnnualiVO = dichAnnualeEntityMapper.mapListEntityToListBasicVO(dichAnnuali,
				aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_02), isProfiloBO);
		
		if(filtriSuffix != null && filtriSuffix.length()>0) {
			String operazioneLog = isProfiloBO ? LogConstants.TSDDR_BO_REPORT_DA_FILTRATO : LogConstants.TSDDR_FO_REPORT_DA_FILTRATO;
			csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
					LogConstants.TSDDR_T_DICH_ANNUALI,
					dichAnnualiVO.stream().map(g -> String.valueOf(g.getIdDichAnnuale())).collect(Collectors.joining(",")));
		}else {
			String operazioneLog = isProfiloBO ? LogConstants.TSDDR_BO_REPORT_DA_COMPLETO : LogConstants.TSDDR_FO_REPORT_DA_COMPLETO;
			csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
					LogConstants.TSDDR_T_DICH_ANNUALI,
					"");			
		}
		
		GenericResponse<List<DichAnnualeBasicVO>> response = GenericResponse.build(dichAnnualiVO);
		LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::getListaDichiarazioniReport] END");
		return response;
	}
	

	@Override
	public GenericResponse<DichAnnualeVO> deteteConferimenti(HttpSession session, Long idDichAnnuale, Long idRifiutoTariffa) {
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::deteteConferimenti] BEGIN");
        
        TsddrTDichAnnuale tsddrTDichAnnuale = tsddrTDichAnnualeRepository.findOne(idDichAnnuale);
		TsddrTRifiutoTariffa tsddrTRifiutoTariffa = tsddrTRifiutoTariffaRepository.findOne(idRifiutoTariffa);
		// elimino i conferimenti
        tsddrTConferimentoRepository.deleteByDichAnnualeAndRifiutoTariffa(tsddrTDichAnnuale, tsddrTRifiutoTariffa);
        
        // recupero la dichannuale aggiornata
        DichAnnualeVO dichAnnuale = getDichiarazioneVO(idDichAnnuale);
        
        GenericResponse<DichAnnualeVO> response = GenericResponse.build(dichAnnuale);
       
        LoggerUtil.debug(logger, "[DichiarazioneServiceImpl::deteteConferimenti] END");
		return response;
	}

    
}
