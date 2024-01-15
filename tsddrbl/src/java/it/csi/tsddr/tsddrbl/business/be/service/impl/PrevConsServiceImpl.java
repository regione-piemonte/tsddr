/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoDichiarazione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoDoc;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRPrevConsLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTAtto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLineaSottoLineaPerc;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevConsDett;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSottoLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.AttoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.GestoreEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ImpiantoEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.PrevConsDettEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.PrevConsEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDEerRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDSezioneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDStatoDichiarazioneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoDocRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDTipoIndirizzoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDUnitaMisuraRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRImpiantoLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrRPrevConsLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTAttoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTGestoreRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTImpiantoRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTLineaSottoLineaPercRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTPrevConsDettRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTPrevConsRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTSottoLineaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTUtenteRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.specifications.TsddrTLineaSottoLineaPercSpecification;
import it.csi.tsddr.tsddrbl.business.be.repository.specifications.TsddrTPrevConsSpecification;
import it.csi.tsddr.tsddrbl.business.be.service.CsiLogAuditService;
import it.csi.tsddr.tsddrbl.business.be.service.PrevConsService;
import it.csi.tsddr.tsddrbl.business.be.service.ProfiloService;
import it.csi.tsddr.tsddrbl.business.facade.DoquiServiceFacade;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.ResponseProtocollaDocumento;
import it.csi.tsddr.tsddrbl.util.DateUtil;
import it.csi.tsddr.tsddrbl.util.EntityUtil;
import it.csi.tsddr.tsddrbl.util.ImpiantoUtil;
import it.csi.tsddr.tsddrbl.util.IndirizzoUtil;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.OptionalUtil;
import it.csi.tsddr.tsddrbl.util.PrevConsUtility;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.enums.Sezione;
import it.csi.tsddr.tsddrbl.util.enums.StatoDichiarazione;
import it.csi.tsddr.tsddrbl.util.enums.TipoDoc;
import it.csi.tsddr.tsddrbl.util.enums.TipoIndirizzo;
import it.csi.tsddr.tsddrbl.util.log.LogConstants;
import it.csi.tsddr.tsddrbl.util.report.DichiarazioneMRReport;
import it.csi.tsddr.tsddrbl.util.report.ReportUtilImpl;
import it.csi.tsddr.tsddrbl.util.report.RichiestaMRReport;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelDataUtil;
import it.csi.tsddr.tsddrbl.util.xls.ExcelSheet;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.impianto.AttoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.ExistsPrevConsVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsBasicVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsDettVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.TotaliPrevConsLinee;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Transactional
public class PrevConsServiceImpl implements PrevConsService {
    
    private static Logger logger = Logger.getLogger(PrevConsServiceImpl.class);
    
    private static final String TIPODOC_NOT_FOUND = "TsddrDTipoDoc non trovato con idTipoDoc = [%d]";
    private static final String PREVCONS_NOT_FOUND = "TsddrTPrevCons non trovato con idPrevCons = [%d]";

	private static Long REPORT_ID_RMR = 9L;	
	private static Long REPORT_ID_DMR = 8L;	

	@Autowired
	private TsddrTReportRepository tsddrTReportRepository;
    @Autowired
    private AclUtil aclUtil;
    
    @Autowired
    private ImpiantoUtil impiantoUtil;
    
    @Autowired
    private CsiLogAuditService csiLogAuditService;
    
    @Autowired
    private MessaggioServiceImpl messaggioService;
    
    @Autowired
    private TsddrTGestoreRepository gestoreRepository;
    
    @Autowired
    private TsddrTPrevConsRepository prevConsRepository;
    
    @Autowired
    private TsddrTPrevConsDettRepository prevConsDettRepository;
    
    @Autowired
    private TsddrTImpiantoRepository impiantoRepository;
    
    @Autowired
    private TsddrDTipoDocRepository tipoDocRepository;
    
    @Autowired
    private TsddrDStatoDichiarazioneRepository statoDichiarazioneRepository;
    
    @Autowired
    private TsddrRImpiantoLineaRepository impiantoLineaRepository;
    
    @Autowired
    private TsddrDUnitaMisuraRepository unitaMisuraRepository;
    
    @Autowired
    private TsddrDSezioneRepository sezioneRepository;
    
    @Autowired
    private TsddrDEerRepository eerRepository;
    
    @Autowired
    private TsddrRPrevConsLineaRepository prevConsLineaRepository;
    
    @Autowired
    private TsddrTAttoRepository attoRepository;
    
    @Autowired
    private TsddrDTipoIndirizzoRepository tipoIndirizzoRepository;
    
    @Autowired
    private AttoEntityMapper attoEntityMapper;
    
    @Autowired
    private ProfiloService profiloService;
    
    @Autowired
    private GestoreEntityMapper gestoreEntityMapper;
    
    @Autowired
    private ImpiantoEntityMapper impiantoEntityMapper;
    
    @Autowired
    private PrevConsEntityMapper prevConsEntityMapper;
    
    @Autowired
    private PrevConsDettEntityMapper prevConsDettEntityMapper;
    
    @Autowired
    private DichiarazioneMRReport dichiarazioneMRReport;
    
    @Autowired
    private RichiestaMRReport richiestaMRReport;
    
    @Autowired
    private ReportUtilImpl utilsReportImpl;

	@Autowired
	private DoquiServiceFacade doquiServiceFacade;

	@Autowired
	private TsddrTUtenteRepository tsddrTUtenteRepository;
	
	@Autowired
	private IndirizzoUtil indirizzoUtil;
	
	@Autowired
	private TsddrTLineaSottoLineaPercRepository tsddrTLineaSottoLineaPercRepository;
	
	@Autowired
	private TsddrTSottoLineaRepository tsddrTSottoLineaRepository;
	
    @Override
    public GenericResponse<List<SelectVO>> getComboGestore(HttpSession httpSession) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getComboGestore] BEGIN");
        Long idProfilo = SessionUtil.getIdProfilo(httpSession);
        Long idUtente = SessionUtil.getIdUtente(httpSession);
        
        Date currentDate = new Date();
        List<TsddrTGestore> gestori = gestoreRepository.findGestoriByIdUtenteIdProfilo(idUtente, idProfilo, currentDate);
        
        if (CollectionUtils.isEmpty(gestori) && profiloService.isProfiloBO(idProfilo)) {
            gestori = gestoreRepository.findGestori(currentDate);
        }
        
        GenericResponse<List<SelectVO>> response = GenericResponse.build(gestoreEntityMapper.mapListEntityToListSelectVO(gestori));
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getComboGestore] END");
        return response;
    }

    @Override
    public GenericResponse<List<Long>> getComboAnni(HttpSession httpSession, Long idTipoDoc, Long idStatoDichiarazione) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getComboAnni] BEGIN");
        List<Long> anni = new ArrayList<>();
        if(idStatoDichiarazione != null) {
            anni = prevConsRepository.findDistinctAnniPrevConsByIdTipoDocAndIdStatoDichiarazione(idTipoDoc, idStatoDichiarazione);
        } else {
            anni = prevConsRepository.findDistinctAnniPrevConsByIdTipoDoc(idTipoDoc);
        }
        GenericResponse<List<Long>> response = GenericResponse.build(anni);
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getComboAnni] END");
        return response;
    }
    
    @Override
    public GenericResponse<List<SelectVO>> getComboImpianti(HttpSession httpSession, Long idGestore) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getComboImpianti] BEGIN");
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
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getComboImpianti] END");
        return response;
    }

    @Override
    public GenericResponse<FunzionalitaProfiloVO> getACLPrevCons(HttpSession httpSession, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getACLPrevCons] BEGIN");
        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(idTipoDoc, new Date()).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, idTipoDoc)));
        FunzionalitaProfiloVO funzionalitaProfiloVO = null;
        if(idTipoDoc.compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
            funzionalitaProfiloVO = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_03);
        } else if(idTipoDoc.compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
            funzionalitaProfiloVO = aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_04);
        }
        GenericResponse<FunzionalitaProfiloVO> response = GenericResponse.build(funzionalitaProfiloVO);
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getACLPrevCons] END");
        return response;
    }

    @Override
    public GenericResponse<Boolean> existsPrevCons(HttpSession session, ExistsPrevConsVO existsPrevConsVO) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::existsPrevCons] BEGIN");
        Optional<TsddrTPrevCons> prevConsOpt = this.getPrevCons(existsPrevConsVO.getIdImpianto(), existsPrevConsVO.getIdGestore(), existsPrevConsVO.getAnnoTributo(), existsPrevConsVO.getIdTipoDoc());
        GenericResponse<Boolean> response = GenericResponse.build(prevConsOpt.isPresent());
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::existsPrevCons] END");
        return response;
    }

    @Override
    public GenericResponse<PrevConsVO> insertBozzaPrevCons(HttpSession httpSession, PrevConsVO prevConsVO, Long idImpianto, Long idGestore) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::insertBozzaPrevCons] BEGIN");
        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
        Long idUtente = SessionUtil.getIdUtente(httpSession);
        Date currentDate = new Date();
        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(prevConsVO.getTipoDoc().getIdTipoDoc(), currentDate).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, prevConsVO.getTipoDoc().getIdTipoDoc())));
        
        Optional<TsddrTPrevCons> prevConsOpt = this.getPrevCons(idImpianto, idGestore, prevConsVO.getAnnoTributo(), tipoDoc.getIdTipoDoc());
        TsddrTPrevCons prevCons = null;
        if(prevConsOpt.isEmpty()) {
            // INSERT BOZZA
            prevCons = this.createBozza(prevConsVO, idImpianto, idUtente, tipoDoc);
        } else {
            // UPDATE BOZZA
            prevCons = this.updateBozza(prevConsOpt.get(), prevConsVO, idUtente, tipoDoc);
        }
        
        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
            this.logSalvaBozzaRichiestaPrevCons(httpSession, idDatiSogg, prevCons.getIdPrevCons());
        } else if (tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
            this.logSalvaBozzaDichiarazionePrevCons(httpSession, idDatiSogg, prevCons.getIdPrevCons());
        }
        
        GenericResponse<PrevConsVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P013.name()), prevConsEntityMapper.mapEntityToVO(prevCons));
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::insertBozzaPrevCons] END");
        return response;
    }
    
    private TsddrTPrevCons createBozza(PrevConsVO prevConsVO, Long idImpianto, Long idUtente, TsddrDTipoDoc tipoDoc) {
        Date currentDate = new Date();
        
        // PREV CONS
        TsddrTPrevCons prevCons = new TsddrTPrevCons();
        prevCons.setTipoDoc(tipoDoc);
        prevCons.setImpianto(impiantoRepository.findByIdImpianto(idImpianto).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrTImpianto non trovato con idImpianto = [%d]", idImpianto))));
        
        prevCons = this.valorizzaPrevCons(prevCons, prevConsVO, currentDate);
        if(prevConsVO.getTipoDoc().getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0 && prevConsVO.getIndirizzo() != null) { // Se PrevCons Richiesta valorizzo anche l'indirizzo, se presente
            TsddrTIndirizzo indirizzo = new TsddrTIndirizzo();
            indirizzo.setVersione(1L);
            EntityUtil.setInserted(indirizzo, idUtente, currentDate);
            Optional<TsddrDTipoIndirizzo> tipoIndirizzoOpt = tipoIndirizzoRepository.findByIdTipoIndirizzo(TipoIndirizzo.SEDE_CONSERVAZIONE_DOCUMENTI.getId(), currentDate);
            TsddrDTipoIndirizzo tipoIndirizzo = null;
            if(tipoIndirizzoOpt.isPresent()) {
                tipoIndirizzo = tipoIndirizzoOpt.get();
            }
            indirizzoUtil.verificaEValorizzaIndirizzo(indirizzo, prevConsVO.getIndirizzo(), tipoIndirizzo);
            prevCons.setIndirizzo(indirizzo);
        }
        EntityUtil.setInserted(prevCons, idUtente, currentDate);
        
        // PREV CONS LINEE
        List<TsddrRPrevConsLinea> prevConsLinee = this.insertPrevConsLinee(prevConsVO, prevCons, idUtente, currentDate);
        
        prevCons.setPrevConsLinee(prevConsLinee);
        prevCons = prevConsRepository.save(prevCons);
        return prevCons;
    }
    
    private TsddrTPrevCons updateBozza(TsddrTPrevCons prevCons, PrevConsVO prevConsVO, Long idUtente, TsddrDTipoDoc tipoDoc) {
        Date currentDate = new Date();
        // PREV CONS
        if(prevConsVO.getRichiestaAmmissioneHasBeenUpdated() != null && prevConsVO.getRichiestaAmmissioneHasBeenUpdated()) {
            prevCons.setModalita(prevConsVO.getModalita());
            prevCons.setDataInvioDoc(prevConsVO.getDataInvioDoc());
            if(prevConsVO.getNumProtDoc()!=null)
            	prevCons.setNumProtDoc(prevConsVO.getNumProtDoc());
            EntityUtil.setUpdated(prevCons, idUtente, currentDate);
        }
        
        // PREV CONS LINEE
        List<PrevConsLineeVO> prevConsLineeVO = prevConsVO.getPrevConsLinee();
        updatePrevConsLinee(prevCons, prevConsLineeVO, idUtente, currentDate);
        
        // INDIRIZZO
        updateIndirizzo(prevCons, prevConsVO, idUtente, currentDate);
       
        return prevConsRepository.save(prevCons);
    }
    
    private void updatePrevConsLinee(TsddrTPrevCons prevCons, List<PrevConsLineeVO> prevConsLineeVO, Long idUtente, Date currentDate) {
        for(PrevConsLineeVO prevConsLineaVO : prevConsLineeVO) {
            TsddrRPrevConsLinea prevConsLinea = new TsddrRPrevConsLinea();;
            if(prevConsLineaVO.getIdPrevConsLinee() != null || (prevConsLineaVO.getDescSommaria() != null || (prevConsLineaVO.getPrevConsDett() != null && !prevConsLineaVO.getPrevConsDett().isEmpty()))) {
                if(prevConsLineaVO.getIdPrevConsLinee() == null && (prevConsLineaVO.getDescSommaria() != null || (prevConsLineaVO.getPrevConsDett() != null && !prevConsLineaVO.getPrevConsDett().isEmpty()))) { // INSERT PREV CONS LINEE
                    prevConsLinea = new TsddrRPrevConsLinea();
                    prevConsLinea.setPrevCons(prevCons);
                    prevConsLinea.setDescSommaria(prevConsLineaVO.getDescSommaria());
                    prevConsLinea.setImpiantoLinea(impiantoLineaRepository.findByIdImpiantoLinea(prevConsLineaVO.getIdImpiantoLinea()).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrRImpiantoLinea non trovato con id = [%d]", prevConsLineaVO.getIdImpiantoLinea()))));
                    prevCons.addRPrevConsLinea(prevConsLinea);
                    EntityUtil.setInserted(prevConsLinea, idUtente, currentDate);
                    prevConsLineaRepository.save(prevConsLinea);
                } else if(prevConsLineaVO.getIdPrevConsLinee() != null) { // UPDATE PREV CONS LINEE
                    prevConsLinea = OptionalUtil.getContent(prevCons.getPrevConsLinee().stream().filter(x -> x.getIdPrevConsLinee().compareTo(prevConsLineaVO.getIdPrevConsLinee()) == 0).findFirst());
                    checkDescSommaria(prevConsLineaVO, prevConsLinea, idUtente, currentDate);
                }
                updatePrevConsLineeDett(prevConsLineaVO, prevConsLinea, idUtente, currentDate);
                TotaliPrevConsLinee totaliPrevConsLinee = this.getTotaliPrevCons(prevConsLineaVO);
                prevConsLinea.setPercRecupero(totaliPrevConsLinee.getPercRecupero());
                prevConsLinea.setPercScarto(totaliPrevConsLinee.getPercScarto());
            }
        }
    }
    
    private void checkDescSommaria(PrevConsLineeVO prevConsLineaVO, TsddrRPrevConsLinea prevConsLinea, Long idUtente, Date currentDate) {
        if(prevConsLineaVO.getHasBeenUpdated() != null && prevConsLineaVO.getHasBeenUpdated()) {
            prevConsLinea.setDescSommaria(prevConsLineaVO.getDescSommaria());
            EntityUtil.setUpdated(prevConsLinea, idUtente, currentDate);
        }
    }
    
    private void updatePrevConsLineeDett(PrevConsLineeVO prevConsLineaVO, TsddrRPrevConsLinea prevConsLinea, Long idUtente, Date currentDate) {
        for(PrevConsDettVO prevConsDettVO : prevConsLineaVO.getPrevConsDett()) { // PREV CONS DETT
            if(prevConsDettVO.getIdPrevConsDett() == null && prevConsDettVO.getQuantita() != null) { // INSERT
                TsddrTPrevConsDett prevConsDett = new TsddrTPrevConsDett();
                prevConsDett.setPrevConsLinea(prevConsLinea);
                prevConsDett = this.valorizzaPrevConsDett(prevConsDett, prevConsDettVO, currentDate);
                EntityUtil.setUpdated(prevConsDett, idUtente, currentDate);
                if(prevConsLinea.getPrevConsDett() == null) {
                    List<TsddrTPrevConsDett> prevConsDettList = new ArrayList<>();
                    prevConsLinea.setPrevConsDett(prevConsDettList);
                }
                prevConsLinea.getPrevConsDett().add(prevConsDett);
            } else if(prevConsDettVO.getQuantita() != null && prevConsDettVO.getHasBeenUpdated() != null && prevConsDettVO.getHasBeenUpdated()) { // UPDATE
                TsddrTPrevConsDett prevConsDett = prevConsLinea.getPrevConsDett().stream().filter(x -> x.getIdPrevConsDett().compareTo(prevConsDettVO.getIdPrevConsDett()) == 0).findAny().orElseThrow();
                prevConsDett = this.valorizzaPrevConsDett(prevConsDett, prevConsDettVO, currentDate);
                EntityUtil.setUpdated(prevConsDett, idUtente, currentDate);
            }
        }
    }
    
    private void updateIndirizzo(TsddrTPrevCons prevCons, PrevConsVO prevConsVO, Long idUtente, Date currentDate) {
        if(prevConsVO.getTipoDoc().getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0 && prevConsVO.getIndirizzo() != null) {
            TsddrTIndirizzo indirizzo = prevCons.getIndirizzo();
            Optional<TsddrDTipoIndirizzo> tipoIndirizzoOpt = tipoIndirizzoRepository.findByIdTipoIndirizzo(TipoIndirizzo.SEDE_CONSERVAZIONE_DOCUMENTI.getId(), currentDate);
            TsddrDTipoIndirizzo tipoIndirizzo = null;
            if(tipoIndirizzoOpt.isPresent()) {
                tipoIndirizzo = tipoIndirizzoOpt.get();
            }
            
            if(indirizzo == null) { // INSERT
                indirizzo = new TsddrTIndirizzo();
                indirizzo.setVersione(1L);
                EntityUtil.setInserted(indirizzo, idUtente, currentDate);
                indirizzoUtil.verificaEValorizzaIndirizzo(indirizzo, prevConsVO.getIndirizzo(), tipoIndirizzo);
                prevCons.setIndirizzo(indirizzo);
            } else {
                if(prevConsVO.getIndirizzo().getHasBeenUpdated() != null && prevConsVO.getIndirizzo().getHasBeenUpdated()) { // UPDATE
                    TsddrTIndirizzo indirizzoOld = prevCons.getIndirizzo();
                    indirizzo = indirizzoUtil.verificaEValorizzaIndirizzo(prevCons.getIndirizzo(), prevConsVO.getIndirizzo(), tipoIndirizzo);
                    indirizzo.setVersione(indirizzoOld.getVersione() + 1);
                    indirizzo.setOriginalId(prevCons.getIndirizzo().getIdIndirizzo());
                    EntityUtil.setInserted(indirizzo, idUtente, currentDate);
                    prevCons.setIndirizzo(indirizzo);
                }
            }
            prevCons.setIndirizzo(indirizzo);
        }
    }
    
    private Optional<TsddrTPrevCons> getPrevCons(Long idImpianto, Long idGestore, Long annoTributo, Long idTipoDoc) {
        List<Long> idStatiDich = List.of(StatoDichiarazione.BOZZA.getId(), StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
        Optional<TsddrTPrevCons> prevCons = prevConsRepository.findByIdImpiantoAndIdGestoreAndAnnoTributoAndIdTipoDocAndIdStatiDich(idImpianto, idGestore, annoTributo, idTipoDoc, idStatiDich);
        return prevCons;
    }
    
    private TotaliPrevConsLinee getTotaliPrevCons(PrevConsLineeVO prevConsLineeVO) {
        TotaliPrevConsLinee totaliPrevConsLinee = new TotaliPrevConsLinee();
        for(PrevConsDettVO prevConsDettVO : prevConsLineeVO.getPrevConsDett()) {
            if(prevConsDettVO.getQuantita() != null) {
                if(prevConsDettVO.getSezione().getIdSezione().equals(Sezione.RII.getId() )) {
                    totaliPrevConsLinee.setTotRii(totaliPrevConsLinee.getTotRii().add(prevConsDettVO.getQuantita()));
                } else if(prevConsDettVO.getSezione().getIdSezione().equals(Sezione.MAT.getId())) {
                    totaliPrevConsLinee.setTotMat(totaliPrevConsLinee.getTotMat().add(prevConsDettVO.getQuantita()));
                } else if(prevConsDettVO.getSezione().getIdSezione().equals(Sezione.RRU.getId())) {
                    totaliPrevConsLinee.setTotRru(totaliPrevConsLinee.getTotRru().add(prevConsDettVO.getQuantita()));
                } else if(prevConsDettVO.getSezione().getIdSezione().equals(Sezione.RU.getId())) {
                    totaliPrevConsLinee.setTotRu(totaliPrevConsLinee.getTotRu().add(prevConsDettVO.getQuantita()));
                } 
            }
        }
        if(totaliPrevConsLinee.getTotRii().compareTo(BigDecimal.ZERO) != 0) {
            totaliPrevConsLinee.setPercRecupero(totaliPrevConsLinee.getTotMat().add(totaliPrevConsLinee.getTotRru()).divide(totaliPrevConsLinee.getTotRii(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
            totaliPrevConsLinee.setPercScarto(totaliPrevConsLinee.getTotRu().divide(totaliPrevConsLinee.getTotRii(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
        } else {
            totaliPrevConsLinee.setPercRecupero(BigDecimal.ZERO);
            totaliPrevConsLinee.setPercScarto(BigDecimal.ZERO);
        }
        return totaliPrevConsLinee;
    }

    @Override
    public GenericResponse<PrevConsVO> insertPrevCons(HttpSession httpSession, PrevConsVO prevConsVO, Long idImpianto,
            Long idGestore) {
        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
        Long idUtente = SessionUtil.getIdUtente(httpSession);
        Date currentDate = new Date();
        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(prevConsVO.getTipoDoc().getIdTipoDoc(), currentDate).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, prevConsVO.getTipoDoc().getIdTipoDoc())));
       
        List<MessaggioVO> messaggiVO = this.verificaDatiObbligatoriBasic(prevConsVO, idImpianto, idGestore);
        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
            messaggiVO.addAll(this.verificaDatiObbligatoriRichiesta(prevConsVO));
        } else if (tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
            messaggiVO.addAll(this.verificaDatiObbligatoriDichiarazione(prevConsVO));
        }
        
        if(!CollectionUtils.isEmpty(messaggiVO)) {
            throw new FunctionalException(messaggiVO);
        }
        
        Optional<TsddrTPrevCons> prevConsOpt = this.getPrevCons(idImpianto, idGestore, prevConsVO.getAnnoTributo(), tipoDoc.getIdTipoDoc());
        TsddrTPrevCons prevCons = null;
        if(prevConsOpt.isEmpty()) {
            // INSERT BOZZA
            prevCons = this.createBozza(prevConsVO, idImpianto, idUtente, tipoDoc);
        } else {
            // UPDATE BOZZA
            prevCons = this.updateBozza(prevConsOpt.get(), prevConsVO, idUtente, tipoDoc);
        }
        this.logSalvaBozzaDichiarazionePrevCons(httpSession, idDatiSogg, prevCons.getIdPrevCons());
		Optional<TsddrTUtente> userOpt = tsddrTUtenteRepository.findByIdUtente(idUtente);
		TsddrTUtente user = null;
		if(userOpt.isPresent()) {
		    user = userOpt.get();
		}
        ResponseProtocollaDocumento responseProtocollaDocumento = null;
        try {         	
            if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
                responseProtocollaDocumento = doquiServiceFacade.protocollaDocumentoFisicoRichiesta(prevCons, richiestaGeneraReportPDFToByte(prevCons), this.richiestaGeneraPdfFilename(prevCons), user);
                prevCons.setNumProtocollo(responseProtocollaDocumento.getProtocollo());
                prevCons.setDataProtocollo(new Date());
                Optional<TsddrDStatoDichiarazione> statoDichOpt = statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
                if(statoDichOpt.isPresent()) {
                    prevCons.setStatoDichiarazione(statoDichOpt.get());
                }
                this.logSalvaRichiestaPrevCons(httpSession, idDatiSogg, prevCons.getIdPrevCons());
            } else if (tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
                responseProtocollaDocumento = doquiServiceFacade.protocollaDocumentoFisicoDichiarazione(prevCons, dichiarazioneGeneraReportPDFToByte(prevCons), this.dichiarazioneGeneraPdfFilename(prevCons), user);
                prevCons.setNumProtocollo(responseProtocollaDocumento.getProtocollo());
                prevCons.setDataProtocollo(new Date());
                Optional<TsddrDStatoDichiarazione> statoDichOpt = statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
                if(statoDichOpt.isPresent()) {
                    prevCons.setStatoDichiarazione(statoDichOpt.get());
                }
                this.logSalvaDichiarazionePrevCons(httpSession, idDatiSogg, prevCons.getIdPrevCons());
            }
            
            prevConsRepository.save(prevCons);
        } catch(Exception e) {
            LoggerUtil.error(logger, "[PrevConsServiceImpl::insertPrevCons] Errore nella generazione del PDF", e);
        }
        GenericResponse<PrevConsVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P014.name()), prevConsEntityMapper.mapEntityToVO(prevCons));
        return response;
    }

    private String dichiarazioneGeneraPdfFilename(TsddrTPrevCons prevCons) {
		TsddrTImpianto tsddrTImpianto = prevCons.getImpianto();
    	var sb = new StringBuilder();
        sb.append("A1103A-D-MR-")
        .append(prevCons.getAnnoTributo()).append("-")
        .append(tsddrTImpianto.getIdImpianto()).append("-")
        .append(tsddrTImpianto.getGestore().getCodFiscPartiva())
        .append(".pdf");
        return sb.toString();
	}
    
    private String richiestaGeneraPdfFilename(TsddrTPrevCons prevCons) {
        TsddrTImpianto tsddrTImpianto = prevCons.getImpianto();
        var sb = new StringBuilder();
        sb.append("A1103A-R-MR-")
        .append(prevCons.getAnnoTributo()).append("-")
        .append(tsddrTImpianto.getIdImpianto()).append("-")
        .append(tsddrTImpianto.getGestore().getCodFiscPartiva())
        .append(".pdf");
        return sb.toString();
    }

	private TsddrTPrevCons valorizzaPrevCons(TsddrTPrevCons prevCons, PrevConsVO prevConsVO, Date currentDate) {
        prevCons.setAnnoTributo(prevConsVO.getAnnoTributo());
        prevCons.setDataDoc(prevConsVO.getDataDoc());
        prevCons.setStatoDichiarazione(OptionalUtil.getContent(statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.BOZZA.getId())));
        prevCons.setPercRecupero(null);
        prevCons.setPercScarto(null);
        prevCons.setNumProtocollo(null);
        prevCons.setDataProtocollo(null);
        prevCons.setIdPrevConsRMr(prevConsVO.getIdPrevConsRMr()); // Se selezionata R-MR, valorizzare con ID_PREV_CONS della Richiesta selezionata
        prevCons.setModalita(prevConsVO.getModalita());
        prevCons.setDataInvioDoc(prevConsVO.getDataInvioDoc()); // Valorizzato nel form “Richiesta Ammissione”, se non legata a R-MR presene a sistema
        if(prevConsVO.getNumProtDoc()!=null)
        	prevCons.setNumProtDoc(prevConsVO.getNumProtDoc()); // Valorizzato nel form “Richiesta Ammissione”, se non legata a R-MR presene a sistema
        return prevCons;
    }
    
    private TsddrTPrevConsDett valorizzaPrevConsDett(TsddrTPrevConsDett prevConsDett, PrevConsDettVO prevConsDettVO, Date currentDate) {
        prevConsDett.setDescMatUscita(prevConsDettVO.getDescMatUscita());
        prevConsDett.setSezione(sezioneRepository.findByIdSezione(prevConsDettVO.getSezione().getIdSezione(), currentDate).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrDSezione non trovato con id = [%d]", prevConsDettVO.getSezione().getIdSezione()))));
        prevConsDett.setUnitaMisura(unitaMisuraRepository.findByIdUnitaMisura(prevConsDettVO.getUnitaMisura().getIdUnitaMisura()).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrDUnitaMisura non trovato con id = [%d]", prevConsDettVO.getUnitaMisura().getIdUnitaMisura()))));
        if(prevConsDettVO.getEer() != null) {
            prevConsDett.setEer(eerRepository.findByIdEer(prevConsDettVO.getEer().getIdEer(), currentDate).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrDEer non trovato con id = [%d]", prevConsDettVO.getEer().getIdEer()))));
        }
        prevConsDett.setQuantita(prevConsDettVO.getQuantita());
        prevConsDett.setDestinazione(prevConsDettVO.getDestinazione());
        prevConsDett.setIdPrevConsDettRmr(prevConsDettVO.getIdPrevConsDettRmr());
        if(prevConsDettVO.getIdPrevConsDettRmr() != null) {
            TsddrTPrevConsDett prevConsDettRichiesta = prevConsDettRepository.findByIdPrevConsDett(prevConsDettVO.getIdPrevConsDettRmr()).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrTReportDett non trovato con id = [%d]", prevConsDettVO.getIdPrevConsDettRmr())));
            prevConsDett.setIdPrevConsDettRmr(prevConsDettVO.getIdPrevConsDettRmr());
        }
        return prevConsDett;
    }
    
    private List<MessaggioVO> verificaDatiObbligatoriBasic(PrevConsVO prevConsVO, Long idImpianto, Long idGestore) { 
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaDatiObbligatoriBasic] BEGIN");
        List<MessaggioVO> messaggiVO = new ArrayList<>();
        MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
        if (idGestore == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "gestore"));
        }
        
        if (idImpianto == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "impianto"));
        }
        
        if (prevConsVO.getAnnoTributo() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "annoTributo"));
        }
        
        if (prevConsVO.getDataDoc() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "dataDoc"));
        }
        
        if(!this.arePrevConsLineeValid(prevConsVO.getPrevConsLinee())) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "processoImpiantistico.quantita"));
        }
        
        if (prevConsVO.getDataDoc() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "dataDoc"));
        }
        
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaDatiObbligatoriBasic] END");
        return messaggiVO;
    }
    
    private List<MessaggioVO> verificaDatiObbligatoriRichiesta(PrevConsVO prevConsVO) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaDatiObbligatoriRichiesta] BEGIN");
        List<MessaggioVO> messaggiVO = new ArrayList<>();
        MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
    
        // Sedime, Indirizzo e Comune 
        if (prevConsVO.getIndirizzo() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo"));
        }
        
        if (prevConsVO.getIndirizzo() == null ||
                prevConsVO.getIndirizzo().getIndirizzo() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.indirizzo"));
        }
        
        if (prevConsVO.getIndirizzo() == null ||
                prevConsVO.getIndirizzo().getSedime() == null ||
                        prevConsVO.getIndirizzo().getSedime().getIdSedime() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.sedime.idSedime"));
        }
        
        if (prevConsVO.getIndirizzo() == null ||
                prevConsVO.getIndirizzo().getComune() == null ||
                        prevConsVO.getIndirizzo().getComune().getIdComune() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.comune.idComune"));
        }
        
        if (prevConsVO.getIndirizzo() == null ||
                prevConsVO.getIndirizzo().getComune() == null ||
                        prevConsVO.getIndirizzo().getComune().getProvincia() == null || 
                        prevConsVO.getIndirizzo().getComune().getProvincia().getIdProvincia() == null) {
            messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "indirizzo.comune.provincia.idProvincia"));
        }
        
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaDatiObbligatoriRichiesta] END");
        return messaggiVO;
    }
    
    private List<MessaggioVO> verificaDatiObbligatoriDichiarazione(PrevConsVO prevConsVO) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaDatiObbligatoriDichiarazione] BEGIN");
        List<MessaggioVO> messaggiVO = new ArrayList<>();
        MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E010.name());
    
        if(prevConsVO.getIdPrevConsRMr() != null) {
            TsddrTPrevCons prevConsPrec = prevConsRepository.findByIdPrevCons(prevConsVO.getIdPrevConsRMr()).orElseThrow(() -> new RecordNotFoundException(String.format(PREVCONS_NOT_FOUND, prevConsVO.getIdPrevConsRMr())));
            if (  prevConsPrec.getAnnoTributo() == null && 
                    prevConsPrec.getDataDoc() == null && 
                    prevConsPrec.getNumProtocollo() == null) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "PrevConsRMr.annoTributo, PrevConsRMr.dataDoc, PrevConsRMr.numProtocollo"));
            }
        } else {
            if (  prevConsVO.getDataInvioDoc() == null && 
                    prevConsVO.getModalita() == null) {
                messaggiVO.add(MessaggioVO.forCampo(messaggioVO, "PrevCons.dataInvioDoc, PrevCons.modalita"));
            }
        }        
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaDatiObbligatoriDichiarazione] END");
        return messaggiVO;
    }
    
    @Override
    public GenericResponse<MessaggioVO> deleteLinee(HttpSession session, Long idPrevConsLinee, Long idSezione) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::deleteLinee] BEGIN");
        TsddrRPrevConsLinea prevConsLinea = prevConsLineaRepository.findByIdPrevConsLinee(idPrevConsLinee).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrRPrevConsLinea non trovato con id = [%d]", idPrevConsLinee)));
        MessaggioVO messaggioVO = null;
        if(idSezione == null) { // ELIMINAZIONE INTERA LINEA
            prevConsLineaRepository.delete(prevConsLinea);
            messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P017.name());
        } else { // ELIMINAZIONE LINEA PER ID_SEZIONE
            for(TsddrTPrevConsDett prevConsDett : prevConsLinea.getPrevConsDett()) {
                if(prevConsDett.getSezione().getIdSezione().equals(idSezione)) {
                    prevConsDettRepository.deleteById(prevConsDett.getIdPrevConsDett());
                }
            }
            messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P007.name());
        }
        
        GenericResponse<MessaggioVO> response = GenericResponse.build(messaggioVO);
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::deleteLinee] END");
        return response;
    }
    
    @Override
    public GenericResponse<PrevConsVO> deletePrevCons(HttpSession httpSession, Long idPrevCons, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::deletePrevCons] BEGIN");
        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
        Long idUtente = SessionUtil.getIdUtente(httpSession);
        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(idTipoDoc, new Date()).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, idTipoDoc)));
        TsddrTPrevCons prevCons = prevConsRepository.findByIdPrevCons(idPrevCons).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrTPrevCons non trovato con id = [%d]", idPrevCons)));
        prevCons.setStatoDichiarazione(OptionalUtil.getContent(statoDichiarazioneRepository.findStatoDichiarazioneById(StatoDichiarazione.ELIMINATA.getId())));
        EntityUtil.setDeleted(prevCons, idUtente, new Date());
        prevCons = prevConsRepository.save(prevCons);
        
        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_ELIMINAZIONE_RICHIESTA_MR, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
        } else if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_ELIMINAZIONE_DIC_MR, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
        }
        
        GenericResponse<PrevConsVO> response = GenericResponse.build(messaggioService.getMessaggioByCodMsg(CodiceMessaggio.P012.name()), prevConsEntityMapper.mapEntityToVO(prevCons));
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::deletePrevCons] END");
        return response;
    }
    
    @Override
    public GenericResponse<List<PrevConsBasicVO>> getListaPrevCons(HttpSession httpSession,
            PrevConsParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getListaPrevCons] BEGIN");
        Long idProfilo = SessionUtil.getIdProfilo(httpSession);
        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
        Long idUtente = SessionUtil.getIdUtente(httpSession);
        boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
        Date currentDate = new Date();
        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(parametriRicerca.getIdTipoDoc(), currentDate).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, parametriRicerca.getIdTipoDoc())));
        
        if (!this.areParametriRicercaValid(parametriRicerca)) {
            throw new BadRequestException("Nessun parametro valorizzato");
        }
        
        List<TsddrTGestore> gestoriUtente = gestoreRepository.findGestoriByIdUtenteIdProfilo(idUtente, idProfilo, currentDate);
        
        List<TsddrTPrevCons> prevCons = prevConsRepository.findAll(TsddrTPrevConsSpecification.searchByParams(parametriRicerca, currentDate, isProfiloBO, gestoriUtente, false));
        if(prevCons.isEmpty()) {
            MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.A002.name());
            throw new FunctionalException(String.format("Nessuna dichiarazione annuale trovato con i parametri di ricerca inseriti"), messaggioVO);
        }
        
        List<PrevConsBasicVO> prevConsVO = PrevConsUtility.mapListEntityToListBasicVO(prevCons,
                aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_04), isProfiloBO);
        
        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_RICERCA_RICHIESTA_MR_BO : LogConstants.TSDDR_RICERCA_RICHIESTA_MR_FO;
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
                    LogConstants.TSDDR_T_PREV_CONS,
                    prevConsVO.stream().map(g -> String.valueOf(g.getIdPrevCons())).collect(Collectors.joining(",")));
        } else if (tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_RICERCA_DICHIARAZIONE_MR_BO : LogConstants.TSDDR_RICERCA_DICHIARAZIONE_MR_FO;
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
                    LogConstants.TSDDR_T_PREV_CONS,
                    prevConsVO.stream().map(g -> String.valueOf(g.getIdPrevCons())).collect(Collectors.joining(",")));
        }
        
        GenericResponse<List<PrevConsBasicVO>> response = GenericResponse.build(prevConsVO);
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getListaPrevCons] END");
        return response;
    }
    
    @Override
    public GenericResponse<String> getParametriFiltroApplicati(HttpSession session,
            PrevConsParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getParametriFiltroApplicati] BEGIN");
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
        
        if(parametriRicerca.getAnnoTributoDal() != null) {
            parametriFiltroBuilder.append("Dall'anno = \"" + parametriRicerca.getAnnoTributoDal() + "\" ");
        }
        
        if(parametriRicerca.getAnnoTributoAl() != null) {
            parametriFiltroBuilder.append("All'anno = \"" + parametriRicerca.getAnnoTributoAl() + "\" ");
        }

        GenericResponse<String> response = GenericResponse.build(parametriFiltroBuilder.toString());
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getParametriFiltroApplicati] END");
        return response;
    }

    @Override
    public GenericResponse<PrevConsExtendedVO> getPrevCons(HttpSession httpSession, Long idPrevCons, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getPrevCons] BEGIN");
        Long idProfilo = SessionUtil.getIdProfilo(httpSession);
        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
        boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(idTipoDoc, new Date()).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, idTipoDoc)));
        
        TsddrTPrevCons prevCons = prevConsRepository.findByIdPrevCons(idPrevCons).orElseThrow(() -> new RecordNotFoundException(String.format(PREVCONS_NOT_FOUND, idPrevCons)));
        PrevConsExtendedVO prevConsVO = this.valorizePrevCons(prevCons);
        GenericResponse<PrevConsExtendedVO> response = GenericResponse.build(prevConsVO);
        
        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
                    isProfiloBO ? LogConstants.TSDDR_VISUALIZZAZIONE_RICHIESTA_MR_BO : LogConstants.TSDDR_VISUALIZZAZIONE_RICHIESTA_MR_FO,
                    LogConstants.TSDDR_T_PREV_CONS, prevCons.getIdPrevCons());
        } else if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg,
                    isProfiloBO ? LogConstants.TSDDR_VISUALIZZAZIONE_DICHIARAZIONE_MR_BO : LogConstants.TSDDR_VISUALIZZAZIONE_DICHIARAZIONE_MR_FO,
                    LogConstants.TSDDR_T_PREV_CONS, prevCons.getIdPrevCons());
        }
        
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getPrevCons] END");
        return response;
    }
    
    private PrevConsExtendedVO valorizePrevCons(TsddrTPrevCons prevCons) {
        PrevConsExtendedVO prevConsVO = prevConsEntityMapper.mapEntityToExtendedVO(prevCons);
        TsddrTImpianto impianto = prevCons.getImpianto();
        ImpiantoVO impiantoVO = impiantoEntityMapper.mapEntityToVO(impianto);
        prevConsVO.setImpianto(impiantoVO);
        
        // recupero linee e sottolinee valide 
        List<TsddrRImpiantoLinea> lineeImpianto = impiantoLineaRepository.findByIdImpianto(impianto.getIdImpianto());
        
        List<GenericLineaVO> genericLineeVO = impiantoUtil.getGenericLineeVOfromLinee(lineeImpianto);
        
        // recupero atti
        List<TsddrTAtto> atti = attoRepository.findAttiByIdImpianto(impianto.getIdImpianto(), new Date());
        List<AttoVO> attiVO = attoEntityMapper.mapListEntityToListVO(atti);
        
        // setto gestore 
        prevConsVO.getImpianto().setGestore(gestoreEntityMapper.mapEntityToVO(impianto.getGestore()));
        // setto impianto.attiVO
        prevConsVO.getImpianto().setAtti(attiVO);
        // setto impianto.lineeVO
        prevConsVO.getImpianto().setLinee(genericLineeVO);
        
        valorizePrevConsDettRmr(prevConsVO, lineeImpianto);
                
        if(prevConsVO.getIdPrevConsRMr() != null) {
            TsddrTPrevCons prevConsRichiesta = prevConsRepository.findByIdPrevCons(prevConsVO.getIdPrevConsRMr()).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrTPrevCons richiesta non trovato con idPrevCons = [%d]", prevConsVO.getIdPrevConsRMr())));
            PrevConsExtendedVO prevConsExtendedVORichiesta = this.valorizePrevCons(prevConsRichiesta);
            prevConsVO.setPrevConsRichiesta(prevConsExtendedVORichiesta);
            
            // Gestione di TSDDR_R_PREV_CONS_LINEE e TSDDR_T_PREV_CONS_DETT per una DICHIARAZIONE legata a una richiesta
            var lineeRichiestaExtended = prevConsExtendedVORichiesta.getPrevConsLineeExtended();
            var lineeDichiarazioneExtended = prevConsVO.getPrevConsLineeExtended();
            
            makeDiff(lineeDichiarazioneExtended, lineeRichiestaExtended);
                        
            // DIFF tra linee dichiarazione - linee richiesta
            List<PrevConsLineeExtendedVO> lineeRichiestaExtendedDIFF = lineeRichiestaExtended.stream()
                    .filter(lr -> !lineeDichiarazioneExtended.stream()
                        .anyMatch(ld -> ld.getIdImpiantoLinea().compareTo(lr.getIdImpiantoLinea()) == 0))
                    .collect(Collectors.toList());
            for(var lineaRichiestaExtendedDIFF : lineeRichiestaExtendedDIFF ) {
                // per linea richiesta della DIFF, svuoto descSommaria e idPrevConsLinee
                lineaRichiestaExtendedDIFF.setDescSommaria(null);
                lineaRichiestaExtendedDIFF.setIdPrevConsLinee(null);
                for(var prevConsDett : lineaRichiestaExtendedDIFF.getPrevConsDett()) { 
                    // per i dett della linea richiesta della DIFF, svuoto descSommaria e idPrevConsLinee
                    prevConsDett.setIdPrevConsDettRmr(prevConsDett.getIdPrevConsDett());
                    prevConsDett.setIdPrevConsDett(null);
                    prevConsDett.setQuantitaRichiesta(prevConsDett.getQuantita());
                    prevConsDett.setQuantita(null);
                }
            }
            prevConsVO.getPrevConsLineeExtended().addAll(lineeRichiestaExtendedDIFF); // aggiungo le linee della richiesta non presenti nelle linee della dichiarazione 
            
            
        }
        return prevConsVO;
    }
    
    private void valorizePrevConsDettRmr(PrevConsExtendedVO prevConsVO, List<TsddrRImpiantoLinea> lineeImpianto) {
    	for(PrevConsLineeExtendedVO prevConsLineeExtendedVO : prevConsVO.getPrevConsLineeExtended()) {
            TotaliPrevConsLinee totaliPrevConsLinee = this.getTotaliPrevCons(prevConsLineeExtendedVO);
            prevConsLineeExtendedVO.setTotMat(totaliPrevConsLinee.getTotMat());
            prevConsLineeExtendedVO.setTotRii(totaliPrevConsLinee.getTotRii());
            prevConsLineeExtendedVO.setTotRru(totaliPrevConsLinee.getTotRru());
            prevConsLineeExtendedVO.setTotRu(totaliPrevConsLinee.getTotRu());
            
            // valorizzo descLinea
            for(TsddrRImpiantoLinea lineaImpianto : lineeImpianto) {
                if(lineaImpianto.getIdImpiantoLinea().doubleValue() == prevConsLineeExtendedVO.getIdImpiantoLinea().doubleValue()) {
                    if(lineaImpianto.getLinea() != null) {
                        prevConsLineeExtendedVO.setDescLinea(lineaImpianto.getLinea().getDescLinea());
                        prevConsLineeExtendedVO.setCodLinea(lineaImpianto.getLinea().getCodLinea());
                    } else if(lineaImpianto.getSottoLinea() != null) {
                        prevConsLineeExtendedVO.setDescLinea(lineaImpianto.getSottoLinea().getDescSottoLinea());
                        prevConsLineeExtendedVO.setCodLinea(lineaImpianto.getSottoLinea().getCodSottoLinea());
                    }
                    break;
                }
            }
            valorizePrevConsDettRmr(prevConsLineeExtendedVO);            
        }
		
	}

	private void valorizePrevConsDettRmr(PrevConsLineeExtendedVO prevConsLineeExtendedVO) {
		// valorizzo prevConsDettRmr
        for(PrevConsDettVO prevConsDettVO : prevConsLineeExtendedVO.getPrevConsDett()) {
            if(prevConsDettVO.getIdPrevConsDettRmr() != null) {
                TsddrTPrevConsDett prevConsDettRichiesta = prevConsDettRepository.findByIdPrevConsDett(prevConsDettVO.getIdPrevConsDettRmr()).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrTReportDett non trovato con id = [%d]", prevConsDettVO.getIdPrevConsDettRmr())));
                prevConsDettVO.setQuantitaRichiesta(prevConsDettRichiesta.getQuantita());
                prevConsDettVO.setPrevConsDettRichiesta(prevConsDettEntityMapper.mapEntityToVO(prevConsDettRichiesta));
            }
        }
		
	}

	private void makeDiff(List<PrevConsLineeExtendedVO> lineeDichiarazioneExtended, List<PrevConsLineeExtendedVO> lineeRichiestaExtended) {

        // DIFF tra dett linea dichiarazione linea richiesta
        for(var lineaDichiarazioneExtended : lineeDichiarazioneExtended) {
            for(var lineaRichiestaExtended : lineeRichiestaExtended) {
                if(lineaDichiarazioneExtended.getIdImpiantoLinea().compareTo(lineaRichiestaExtended.getIdImpiantoLinea()) == 0) { 
                    // E' la linea dichiarazione con la corrispondente linea richiesta 
                    var dettRichiesta = lineaRichiestaExtended.getPrevConsDett();
                    var dettDichiarazioneDIFF = lineaDichiarazioneExtended.getPrevConsDett();
                    
                    List<PrevConsDettVO> dettRichiestaDIFF = dettRichiesta.stream()
                            .filter(richiesta -> dettDichiarazioneDIFF.stream()
                                .noneMatch(dichiarazione -> 
                                dichiarazione.getIdPrevConsDettRmr() != null && richiesta.getIdPrevConsDett().compareTo(dichiarazione.getIdPrevConsDettRmr()) == 0))
                            .collect(Collectors.toList());
                    
                    for(var prevConsDett : dettRichiestaDIFF) { 
                        // per i dett della linea richiesta della DIFF, svuoto descSommaria e idPrevConsLinee
                        prevConsDett.setIdPrevConsDettRmr(prevConsDett.getIdPrevConsDett());
                        prevConsDett.setIdPrevConsDett(null);
                        prevConsDett.setQuantitaRichiesta(prevConsDett.getQuantita());
                        prevConsDett.setQuantita(null);
                    }
                    
                    lineaDichiarazioneExtended.getPrevConsDett().addAll(dettRichiestaDIFF);
                }
            }
        }
        
	}

	private boolean areParametriRicercaValid(PrevConsParametriRicerca parametriRicerca) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaParametriRicerca] BEGIN");
        boolean areParametriValid = parametriRicerca.getIdGestore() != null || parametriRicerca.getIdImpianto() != null
                || parametriRicerca.getAnnoTributoAl() != null || parametriRicerca.getAnnoTributoDal() != null || parametriRicerca.getIdTipoDoc() != null;
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::verificaParametriRicerca] END");
        return areParametriValid;
    }
    
    private boolean arePrevConsLineeValid(List<PrevConsLineeVO> prevConsLineeVO) {
        boolean arePrevConsLineeValid = false;
        prevConsLineeVOLoop:
        for(PrevConsLineeVO prevConsLineaVO : prevConsLineeVO) {
            for(PrevConsDettVO prevConsDettVO : prevConsLineaVO.getPrevConsDett()) {
                if(prevConsDettVO.getQuantita() != null) {
                    arePrevConsLineeValid = true;
                    break prevConsLineeVOLoop;
                }
            }
        }
        return arePrevConsLineeValid;
    }
    
    public byte[] dichiarazioneGeneraReportPDFToByte(TsddrTPrevCons prevCons) throws Exception {
        Map<String, Object> jasperParam = new HashMap<>();
        PrevConsExtendedVO prevConsExtendedVO = this.valorizePrevCons(prevCons);
        dichiarazioneMRReport.setJasperParam(prevConsExtendedVO, jasperParam);
        JasperPrint jasperPrint = utilsReportImpl.printJasper(jasperParam, dichiarazioneMRReport);
        byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
        // salva il report in locale
//         this.saveDevFile(b);
        return b;
    }
    
    public byte[] richiestaGeneraReportPDFToByte(TsddrTPrevCons prevCons) throws Exception {
        Map<String, Object> jasperParam = new HashMap<>();
        PrevConsExtendedVO prevConsExtendedVO = this.valorizePrevCons(prevCons);
        richiestaMRReport.setJasperParam(prevConsExtendedVO, jasperParam);
        JasperPrint jasperPrint = utilsReportImpl.printJasper(jasperParam, richiestaMRReport);
        byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
        // salva il report in locale
//        this.saveDevFile(b);
        return b;
    }
    
    private void saveDevFile(byte[] b) {
        try {
            FileUtils.writeByteArrayToFile(new File("c:/mr"+System.currentTimeMillis()+".pdf"), b);
              System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
    }

    @Override
    public GenericResponse<DocumentoProtocollatoVO> downloadPrevConsPDF(HttpSession httpSession, Long idPrevCons, Long idTipoDoc) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::downloadPrevConsPDF] BEGIN");
        Long idProfilo = SessionUtil.getIdProfilo(httpSession);
        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
        boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(idTipoDoc, new Date()).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, idTipoDoc)));
        
        TsddrTPrevCons prevCons = prevConsRepository.findByIdPrevCons(idPrevCons).orElseThrow(() -> new RecordNotFoundException(String.format(PREVCONS_NOT_FOUND, idPrevCons)));
        String numProtocollo = prevCons.getNumProtocollo();
        DocumentoProtocollatoVO downloadFileVO = null;

        try {           
            downloadFileVO = doquiServiceFacade.ricercaProtocolloSuACTA(numProtocollo);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_SCARICO_MODELLO_RICHIESTA_MR_BO : LogConstants.TSDDR_SCARICO_MODELLO_RICHIESTA_MR_FO;
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
        } else if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_SCARICO_MODELLO_DICHIARAZIONE_MR_BO : LogConstants.TSDDR_SCARICO_MODELLO_DICHIARAZIONE_MR_FO;
            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
        }
        
        GenericResponse<DocumentoProtocollatoVO> response = GenericResponse.build(downloadFileVO);
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::downloadPrevConsPDF] END");
        return response;
    }
    
    // INSERT PREV CONS LINEE
    private List<TsddrRPrevConsLinea> insertPrevConsLinee(PrevConsVO prevConsVO, TsddrTPrevCons prevCons, Long idUtente, Date currentDate) { 
        List<TsddrRPrevConsLinea> prevConsLinee = new ArrayList<>();
        for(PrevConsLineeVO prevConsLineeVO : prevConsVO.getPrevConsLinee()) {
            if(prevConsLineeVO.getDescSommaria() != null || (prevConsLineeVO.getPrevConsDett() != null && !prevConsLineeVO.getPrevConsDett().isEmpty() && isQuantitaPrevConsDettPresent(prevConsLineeVO.getPrevConsDett()))) {
                TsddrRPrevConsLinea prevConsLinea = new TsddrRPrevConsLinea();
                prevConsLinea.setPrevCons(prevCons);
                prevConsLinea.setDescSommaria(prevConsLineeVO.getDescSommaria());
                prevConsLinea.setImpiantoLinea(impiantoLineaRepository.findByIdImpiantoLinea(prevConsLineeVO.getIdImpiantoLinea()).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrRImpiantoLinea non trovato con id = [%d]", prevConsLineeVO.getIdImpiantoLinea()))));
                EntityUtil.setInserted(prevConsLinea, idUtente, currentDate);
                List<TsddrTPrevConsDett> prevConsDettList = new ArrayList<>();
                for(PrevConsDettVO prevConsDettVO : prevConsLineeVO.getPrevConsDett()) { // PREV CONS DETT
                    if(prevConsDettVO.getQuantita() != null) {
                        TsddrTPrevConsDett prevConsDett = new TsddrTPrevConsDett();
                        prevConsDett = this.valorizzaPrevConsDett(prevConsDett, prevConsDettVO, currentDate);
                        prevConsDett.setPrevConsLinea(prevConsLinea);
                        EntityUtil.setInserted(prevConsDett, idUtente, currentDate);
                        prevConsDettList.add(prevConsDett);  
                    }
                   
                }
                prevConsLinea.setPrevConsDett(prevConsDettList);
                TotaliPrevConsLinee totaliPrevConsLinee = this.getTotaliPrevCons(prevConsLineeVO);
                prevConsLinea.setPercRecupero(totaliPrevConsLinee.getPercRecupero());
                prevConsLinea.setPercScarto(totaliPrevConsLinee.getPercScarto());
                prevConsLinee.add(prevConsLinea);
            }
        }
        return prevConsLinee;
    }
    
    private boolean isQuantitaPrevConsDettPresent(List<PrevConsDettVO> prevConsDettVO) {
       return prevConsDettVO.stream()
        .map(PrevConsDettVO::getQuantita)
        .anyMatch(quantita -> quantita != null);
    }
    
    @Override
    public GenericResponse<List<PrevConsLineeExtendedVO>> getLineeRichiesta(HttpSession session, Long idPrevCons) {
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getLineeRichiesta] BEGIN");
        TsddrTPrevCons prevConsRichiesta = prevConsRepository.findByIdPrevCons(idPrevCons).orElseThrow(() -> new RecordNotFoundException(String.format("TsddrTPrevCons richiesta non trovato con idPrevCons = [%d]", idPrevCons)));
        PrevConsExtendedVO prevConsExtendedVORichiesta = this.valorizePrevCons(prevConsRichiesta);
        var lineeExtended = prevConsExtendedVORichiesta.getPrevConsLineeExtended();
        for(var lineaExtended : lineeExtended) {
            lineaExtended.setIdPrevConsLinee(null);
            lineaExtended.setDescSommaria(null);
            for(var dettExtended : lineaExtended.getPrevConsDett()) {
                dettExtended.setIdPrevConsDettRmr(dettExtended.getIdPrevConsDett());
                dettExtended.setIdPrevConsDett(null);
                dettExtended.setQuantitaRichiesta(dettExtended.getQuantita());
                dettExtended.setQuantita(null);
            }
        }
        GenericResponse<List<PrevConsLineeExtendedVO>> response = GenericResponse.build(lineeExtended);
        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getLineeRichiesta] END");
        return response;
    }
    
    private void logSalvaBozzaRichiestaPrevCons(HttpSession httpSession, Long idDatiSogg, Long idPrevCons) {
        csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_SALVA_IN_BOZZA_NUOVA_RICHIESTA_MR, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
    }
    
    private void logSalvaBozzaDichiarazionePrevCons(HttpSession httpSession, Long idDatiSogg, Long idPrevCons) {
        csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_SALVA_IN_BOZZA_NUOVA_DICHIARAZIONE_MR, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
    }
    
    private void logSalvaRichiestaPrevCons(HttpSession httpSession, Long idDatiSogg, Long idPrevCons) {
        csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_INVIO_NUOVA_RICHIESTA_MR, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
    }
    
    private void logSalvaDichiarazionePrevCons(HttpSession httpSession, Long idDatiSogg, Long idPrevCons) {
        csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, LogConstants.TSDDR_INVIO_NUOVA_DICHIARAZIONE_MR, LogConstants.TSDDR_T_PREV_CONS, idPrevCons);
    }
    

	TsddrTReport tsddrTReportRMR = null;
	TsddrTReport tsddrTReportDMR = null;

	public TsddrTReport getTsddrTReport(Long tipoDoc){
		
		if(tipoDoc == 1L) {
			
			if(tsddrTReportRMR == null ) {
				tsddrTReportRMR = tsddrTReportRepository.findOne(REPORT_ID_RMR);
			}
			return tsddrTReportRMR;
			
		}else {
			
			if(tsddrTReportDMR == null ) {
				tsddrTReportDMR = tsddrTReportRepository.findOne(REPORT_ID_DMR);
			}
			return tsddrTReportDMR;
			
		}
	}

	@Override
	public GenericResponse<ReportVO> downloadReport(HttpSession httpSession, PrevConsParametriRicerca parametriRicerca) {
		LoggerUtil.debug(logger, "[PrevConsServiceImpl::downloadReport] BEGIN");

		//metodo per riportare i filtri su nome file
		String filtriSuffix = getFilterByRequest(parametriRicerca);
		
		GenericResponse<List<PrevConsBasicVO>> listaGestiori = getListaPrevConsReport(httpSession, parametriRicerca, filtriSuffix);
				
        ExcelDataUtil excel = new ExcelDataUtil(getTsddrTReport(parametriRicerca.getIdTipoDoc()).getDescReport(), filtriSuffix);
        ExcelSheet sheet = excel.addSheet(getTsddrTReport(parametriRicerca.getIdTipoDoc()).getDescReport());
        
        if(parametriRicerca.getIdTipoDoc() == 1L) {
//        	1	Colonna A	Gestore CF / PI - Denominazione
    		sheet.addColumn("Gestore CF / PI - Denominazione");
//        	2	Colonna B	Impianto Denominazione - Dati
            sheet.addColumn("Impianto Denominazione - Dati");
//        	3	Colonna C 	Anno Richiesta
            sheet.addColumn("Anno Richiesta");
//        	4	Colonna D	ID Richiesta
            sheet.addColumn("ID Richiesta");
//        	5	Colonna E	Data Richiesta
            sheet.addColumn("Data Richiesta");
//        	6	Colonna F	Stato
            sheet.addColumn("Stato");
//        	7	Colonna G	Protocollo
            sheet.addColumn("Protocollo");
//        	8	Colonna H	N° Dichiarazioni collegate inviate
            // CR OB 258-259-260
            sheet.addColumn("N° Dichiarazioni collegate inviate");
        
        }else {
//          1	Colonna A	Gestore CF / PI - Denominazione
      		sheet.addColumn("Gestore CF / PI - Denominazione");
//        	2	Colonna B	Impianto Denominazione - Dati
            sheet.addColumn("Impianto Denominazione - Dati");
//        	3	Colonna C 	Anno Dichiarazione
            sheet.addColumn("Anno Dichiarazione");
//        	4	Colonna D	ID Dichiarazione
            sheet.addColumn("ID Dichiarazione");
//        	5	Colonna E	Data Dichiarazione
            sheet.addColumn("Data Dichiarazione");
//        	6	Colonna F	Stato
            sheet.addColumn("Stato");
//        	7	Colonna G	Protocollo
            sheet.addColumn("Protocollo");
//        	8	Colonna H	Richiesta R-MR Riferimento
            sheet.addColumn("Richiesta R-MR Riferimento");   
        	
        }
        addContent(sheet, listaGestiori.getContent(), parametriRicerca.getIdTipoDoc());
        
        ReportVO report = null;
		try {
			report = new ReportVO(excel.getFileName(), excel.getFile());
		} catch (IOException e) {
			LoggerUtil.warn(logger, "[PrevConsServiceImpl::downloadReport] problemi in generazione report");
		}
        
		GenericResponse<ReportVO> response = new GenericResponse<ReportVO>(report);
		
		LoggerUtil.debug(logger, "[PrevConsServiceImpl::downloadReport] END");
		return response;
	}

	private String getFilterByRequest(PrevConsParametriRicerca parametriRicerca) {
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
		if(parametriRicerca.getAnnoTributoDal()!=null) {
			sb.append("_Anno dal_").append(parametriRicerca.getAnnoTributoDal());
		}
		if(parametriRicerca.getAnnoTributoAl()!=null) {
			sb.append("_Anno al_").append(parametriRicerca.getAnnoTributoAl());
		}
		if(sb.toString().length()>0) {
			sb.append("_");
		}
		
		return sb.toString();
	}

	private void addContent(ExcelSheet sheet, List<PrevConsBasicVO> list, Long tipoDoc) {
		
		for(PrevConsBasicVO prevCons : list){
			PrevConsExtendedVO prevConsExtVO = getPrevConsInternal(prevCons.getIdPrevCons());
			if(tipoDoc == 1L) {
				sheet.addDataToBody(
						prevConsExtVO.getImpianto().getGestore().getCodFiscPartiva() + " - " + prevConsExtVO.getImpianto().getGestore().getRagSociale(),
						prevConsExtVO.getImpianto().getDenominazione()  + " - " 	
						+ prevConsExtVO.getImpianto().getIndirizzo().getSedime().getDescSedime()
						+ " " + prevConsExtVO.getImpianto().getIndirizzo().getIndirizzo()
						+ ", " + prevConsExtVO.getImpianto().getIndirizzo().getComune().getComune()
						+ " (" + (prevConsExtVO.getImpianto().getIndirizzo().getCap()!=null?prevConsExtVO.getImpianto().getIndirizzo().getCap():"") + ")"
						+ ", " + prevConsExtVO.getImpianto().getIndirizzo().getComune().getProvincia().getSiglaProv(),
						prevConsExtVO.getAnnoTributo(),
						prevConsExtVO.getIdPrevCons(),
						new SimpleDateFormat(DateUtil.ddMMyyyy).format(prevConsExtVO.getDataDoc()),
						prevConsExtVO.getStatoDichiarazione().getDescrStatoDichiarazione(),
						prevConsExtVO.getNumProtocollo(),
						getNumeroCollegate(prevConsExtVO));				
			}else {
				sheet.addDataToBody(
						prevConsExtVO.getImpianto().getGestore().getCodFiscPartiva() + " - " + prevConsExtVO.getImpianto().getGestore().getRagSociale(),
						prevConsExtVO.getImpianto().getDenominazione()  + " - " +  prevConsExtVO.getImpianto().getIndirizzo().getSedime().getDescSedime()
						+ " " + prevConsExtVO.getImpianto().getIndirizzo().getIndirizzo()
						+ ", " + prevConsExtVO.getImpianto().getIndirizzo().getComune().getComune()
						+ " (" + (prevConsExtVO.getImpianto().getIndirizzo().getCap()!=null?prevConsExtVO.getImpianto().getIndirizzo().getCap():"") + ")"
						+ ", " + prevConsExtVO.getImpianto().getIndirizzo().getComune().getProvincia().getSiglaProv(),
						prevConsExtVO.getAnnoTributo(),
						prevConsExtVO.getIdPrevCons(),
						new SimpleDateFormat(DateUtil.ddMMyyyy).format(prevConsExtVO.getDataDoc()),
						prevConsExtVO.getStatoDichiarazione().getDescrStatoDichiarazione(),
						prevConsExtVO.getNumProtocollo(),
						getInfoRichiesta(prevConsExtVO));				
			}
		}
	}
		
    private String getInfoRichiesta(PrevConsExtendedVO prevConsExtVO) {
		if(prevConsExtVO.getPrevConsRichiesta() == null) {
            // CR OB 258-259-260
            return (prevConsExtVO.getModalita() != null ? prevConsExtVO.getModalita() : "")
                    + " - "
                    + (prevConsExtVO.getDataInvioDoc() != null ? new SimpleDateFormat(DateUtil.ddMMyyyy).format(prevConsExtVO.getDataInvioDoc()) : "")
                    + " - "
                    + (prevConsExtVO.getNumProtDoc() != null ? prevConsExtVO.getNumProtDoc() : "");
        }else {
			return  prevConsExtVO.getPrevConsRichiesta().getNumProtocollo() + " - " + new SimpleDateFormat(DateUtil.ddMMyyyy).format(prevConsExtVO.getPrevConsRichiesta().getDataProtocollo());
		}
	}

	private long getNumeroCollegate(PrevConsExtendedVO prevConsExtVO) {
        // CR OB 258-259-260
        return prevConsRepository.countByIdTipoDocAndIdStatoDichiarazioneAndIdPrevConsRMr(
                2L, 2L, prevConsExtVO.getIdPrevCons()
        );
	}

	private PrevConsExtendedVO getPrevConsInternal(Long idPrevCons) {
        TsddrTPrevCons prevCons = prevConsRepository.findByIdPrevCons(idPrevCons).orElseThrow(() -> new RecordNotFoundException(String.format(PREVCONS_NOT_FOUND, idPrevCons)));
        PrevConsExtendedVO prevConsVO = this.valorizePrevCons(prevCons);               
        return prevConsVO;
    }
	
	 public GenericResponse<List<PrevConsBasicVO>> getListaPrevConsReport(HttpSession httpSession,
	            PrevConsParametriRicerca parametriRicerca, String filtriSuffix) {
	        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getListaPrevConsReport] BEGIN");
	        Long idProfilo = SessionUtil.getIdProfilo(httpSession);
	        Long idDatiSogg = SessionUtil.getIdDatiSoggetto(httpSession);
	        Long idUtente = SessionUtil.getIdUtente(httpSession);
	        boolean isProfiloBO = profiloService.isProfiloBO(idProfilo);
	        Date currentDate = new Date();
	        TsddrDTipoDoc tipoDoc = tipoDocRepository.findTipoDocById(parametriRicerca.getIdTipoDoc(), currentDate).orElseThrow(() -> new RecordNotFoundException(String.format(TIPODOC_NOT_FOUND, parametriRicerca.getIdTipoDoc())));
	        
	        List<TsddrTGestore> gestoriUtente = gestoreRepository.findGestoriByIdUtenteIdProfilo(idUtente, idProfilo, currentDate);
	        
	        List<TsddrTPrevCons> prevCons = prevConsRepository.findAll(TsddrTPrevConsSpecification.searchByParams(parametriRicerca, currentDate, isProfiloBO, gestoriUtente, true));
	        if(prevCons.isEmpty()) {
	            MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.A002.name());
	            throw new FunctionalException(String.format("Nessuna dichiarazione annuale trovato con i parametri di ricerca inseriti"), messaggioVO);
	        }
	        
	        List<PrevConsBasicVO> prevConsVO = PrevConsUtility.mapListEntityToListBasicVO(prevCons,
	                aclUtil.getACLPerProfilo(httpSession, CodiceFunzione.ALL_04), isProfiloBO);
	        
	        if(filtriSuffix!=null && filtriSuffix.length()>0) {
		        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
		            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_BO_REPORT_RMR_FILTRATO : LogConstants.TSDDR_FO_REPORT_RMR_FILTRATO;
		            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
		                    LogConstants.TSDDR_T_PREV_CONS,
		                    prevConsVO.stream().map(g -> String.valueOf(g.getIdPrevCons())).collect(Collectors.joining(",")));
		        } else if (tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
		            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_BO_REPORT_DMR_FILTRATO : LogConstants.TSDDR_FO_REPORT_DMR_FILTRATO;
		            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
		                    LogConstants.TSDDR_T_PREV_CONS,
		                    prevConsVO.stream().map(g -> String.valueOf(g.getIdPrevCons())).collect(Collectors.joining(",")));
		        }
	        }else {
		        if(tipoDoc.getIdTipoDoc().compareTo(TipoDoc.RICHIESTA.getId()) == 0) {
		            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_BO_REPORT_RMR_COMPLETO : LogConstants.TSDDR_FO_REPORT_RMR_COMPLETO;
		            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
		                    LogConstants.TSDDR_T_PREV_CONS,
		                    "");
		        } else if (tipoDoc.getIdTipoDoc().compareTo(TipoDoc.DICHIARAZIONE.getId()) == 0) {
		            String operazioneLog = isProfiloBO ? LogConstants.TSDDR_BO_REPORT_DMR_COMPLETO : LogConstants.TSDDR_FO_REPORT_DMR_COMPLETO;
		            csiLogAuditService.traceCsiLogAudit(httpSession, idDatiSogg, operazioneLog,
		                    LogConstants.TSDDR_T_PREV_CONS,
		                    "");
		        }	        	
	        }
	        GenericResponse<List<PrevConsBasicVO>> response = GenericResponse.build(prevConsVO);
	        LoggerUtil.debug(logger, "[PrevConsServiceImpl::getListaPrevConsReport] END");
	        return response;
	    }


	 @Override
	 public Boolean isPercRecuperoVisible(String codLinea, Long anno){			
		return isPercRecuperoVisible(null, codLinea, anno).getContent();		 
	 }
	 
	 @Override
	 public Boolean isPercScartoVisible(String codLinea, Long anno){	
		return isPercScartoVisible(null, codLinea, anno).getContent();		 
	 }
	 	 
	 
	@Override
	public GenericResponse<Boolean> isPercRecuperoVisible(HttpSession session, String codLinea,
			Long anno) {
		Boolean isPercRecuperoVisible = false; 
		LoggerUtil.debug(logger, "[PrevConsServiceImpl::isPercRecuperoVisible] BEGIN");
		Long idLinea = null;
		Long idSottoLinea = null;
		if(codLinea == null || codLinea.equalsIgnoreCase("undefined")) {
			return GenericResponse.build(false);
		}
		if(codLinea.contains(".")) {
			Optional<TsddrTSottoLinea> sottoLinea = tsddrTSottoLineaRepository.findByCodSottoLinea(codLinea);
			idLinea = sottoLinea.isPresent()?sottoLinea.get().getLinea().getIdLinea():null;
			idSottoLinea = sottoLinea.isPresent()?sottoLinea.get().getIdSottoLinea():null;
		}else {
			idLinea = Long.parseLong(codLinea);
		}
		 
		try {
			Date annoDich = new SimpleDateFormat("yyyy").parse(String.valueOf(anno));
			List<TsddrTLineaSottoLineaPerc> tsddrTLineaSottoLineaPerc = tsddrTLineaSottoLineaPercRepository.findAll(TsddrTLineaSottoLineaPercSpecification.searchByParams(idLinea, idSottoLinea, annoDich, false, true));
			if(tsddrTLineaSottoLineaPerc!=null && tsddrTLineaSottoLineaPerc.size()>0) {
				isPercRecuperoVisible = true;
			}
		} catch (ParseException e) {
			LoggerUtil.error(logger, "[PrevConsServiceImpl::isPercRecuperoVisible] Error parsing anno ["+anno+"]", e);
		}
        GenericResponse<Boolean> response = GenericResponse.build(isPercRecuperoVisible);
		LoggerUtil.debug(logger, "[PrevConsServiceImpl::isPercRecuperoVisible] END");
		return response;
	}

	@Override
	public GenericResponse<Boolean> isPercScartoVisible(HttpSession session, String codLinea,
			Long anno) {
		Boolean isPercScartoVisible = false; 
		LoggerUtil.debug(logger, "[PrevConsServiceImpl::isPercScartoVisible] BEGIN");
		Long idLinea = null;
		Long idSottoLinea = null;
		if(codLinea == null || codLinea.equalsIgnoreCase("undefined")) {
			return GenericResponse.build(false);
		}
		if(codLinea.contains(".")) {
			Optional<TsddrTSottoLinea> sottoLinea = tsddrTSottoLineaRepository.findByCodSottoLinea(codLinea);
			idLinea = sottoLinea.isPresent()?sottoLinea.get().getLinea().getIdLinea():null;
			idSottoLinea = sottoLinea.isPresent()?sottoLinea.get().getIdSottoLinea():null;
		}else {
			idLinea = Long.parseLong(codLinea);
		}
		
		try {
			Date annoDich = new SimpleDateFormat("yyyy").parse(String.valueOf(anno));
			List<TsddrTLineaSottoLineaPerc> tsddrTLineaSottoLineaPerc = tsddrTLineaSottoLineaPercRepository.findAll(TsddrTLineaSottoLineaPercSpecification.searchByParams(idLinea, idSottoLinea, annoDich, true, false));
			if(tsddrTLineaSottoLineaPerc!=null && tsddrTLineaSottoLineaPerc.size()>0) {
				isPercScartoVisible = true;
			}
		} catch (ParseException e) {
			LoggerUtil.error(logger, "[PrevConsServiceImpl::isPercScartoVisible] Error parsing anno ["+anno+"]", e);
		}
		
        GenericResponse<Boolean> response = GenericResponse.build(isPercScartoVisible);
		LoggerUtil.debug(logger, "[PrevConsServiceImpl::isPercScartoVisible] END");
		return response;
	}

}
