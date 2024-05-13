/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */

package it.csi.tsddr.tsddrbl.util.report;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSezione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReportDett;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDSezioneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;
import it.csi.tsddr.tsddrbl.business.be.service.PrevConsService;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsDettVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeExtendedVO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class DichiarazioneMRReport implements TsddrReport {
	
    private static Logger logger = Logger.getLogger(DichiarazioneMRReport.class);

	private static Long REPORT_ID = 3L;	

	@Autowired
	private TsddrTReportRepository tsddrTReportRepository;
	
	@Autowired
    private TsddrDSezioneRepository tsddrDSezioneRepository;
	
	@Autowired
	private PrevConsService prevConsService;
	
	TsddrTReport tsddrTReport = null;

	public TsddrTReport getTsddrTReport(){
		if(tsddrTReport == null ) {
			tsddrTReport = tsddrTReportRepository.findOne(getTsddrReportId());
		}
		return tsddrTReport;
	}
	
	List<TsddrDSezione> tsddrDSezioni = null;
	
	public List<TsddrDSezione> getSezioni(){
        if(tsddrDSezioni == null ) {
            tsddrDSezioni = tsddrDSezioneRepository.findAll();
        }
        return tsddrDSezioni;
    }
	
	public String getJrxml() throws Exception {
		return this.getTemplateJrxml(getTsddrTReport().getXmlReport());
	}
	
	public void setJasperParam(PrevConsExtendedVO prevConsExtendedVO, Map<String, Object> jasperParam) throws IOException {
		jasperParam.put("ANNO", prevConsExtendedVO.getAnnoTributo());
		
		// DICHIARANTE
		putDichiarante_1(prevConsExtendedVO, jasperParam);
        putDichiarante_2(prevConsExtendedVO, jasperParam);
		
		// IMPIANTO
		putImpianto(prevConsExtendedVO, jasperParam);
		
		// RICHIESTA AMMISSIONE
		putRichiestaAmmissione(prevConsExtendedVO, jasperParam);
		
		// Lista sezioneQuattro
		
		// Per le linee e i dettagli delle linee, scarto quelli senza idPrevConsLinee e idPrevConsDett, in quanto sono linee/dettagli della richiesta collegata
		
		var lineeExtended = prevConsExtendedVO.getPrevConsLineeExtended();
		lineeExtended = lineeExtended.stream()
		        .filter(obj -> obj.getIdPrevConsLinee() != null)
		        .collect(Collectors.toList());
        
        lineeExtended = lineeExtended.stream()
                    .filter(obj -> (obj.getTotMat().compareTo(BigDecimal.valueOf(0L)) != 0
                                    || obj.getTotRii().compareTo(BigDecimal.valueOf(0L)) != 0
                                    || obj.getTotRru().compareTo(BigDecimal.valueOf(0L)) != 0
                                    || obj.getTotRu().compareTo(BigDecimal.valueOf(0L)) != 0))
                    .collect(Collectors.toList());
                
		lineeExtended = lineeExtended.stream()
		        .map(lineaExtended -> {
		            lineaExtended.setPrevConsDett(lineaExtended.getPrevConsDett().stream()
		                .filter(dett -> dett.getIdPrevConsDett() != null)
		                .collect(Collectors.toList()));
		            return lineaExtended;
		        })
		        .collect(Collectors.toList());
		
		List<SezioneQuattro> sezioniQuattro = new ArrayList<>();
        jasperParam.put("sezioneQuattro", new JRBeanCollectionDataSource(this.valorizeSezioneQuattro(sezioniQuattro, lineeExtended, prevConsExtendedVO.getAnnoTributo())));
		
        // Valorizzo sezione
        for(TsddrDSezione sezione : getSezioni()) {
            if(sezione.getIdSezione().compareTo(6L) == 0) {
                jasperParam.put("SEZ_R_MR", sezione.getDescSezione());
            }
        }
        
		// FOOTER
		try{jasperParam.put("DATA_DOCUMENTO" , prevConsExtendedVO.getDataDoc());}
        catch(Throwable t) {jasperParam.put("DATA_DOCUMENTO" , "");}
        
    	// Imposto i campi di conf dal db
		putDbConf(jasperParam);
	}
	
	private void putDichiarante_1(PrevConsExtendedVO prevConsExtendedVO, Map<String, Object> jasperParam) {
	    try{jasperParam.put("CF_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getCodFiscPartiva());}
        catch(Throwable t) {jasperParam.put("CF_GESTORE" , "");}
        try{jasperParam.put("RAGSOG_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getRagSociale());}
        catch(Throwable t) {jasperParam.put("RAGSOG_GESTORE" , "");}
        try{jasperParam.put("NATGIU_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getNaturaGiuridica().getDescNaturaGiuridica());}
        catch(Throwable t) {jasperParam.put("NATGIU_GESTORE" , "");}
        try{jasperParam.put("SEDIME_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getSedeLegale().getSedime().getDescSedime());}
        catch(Throwable t) {jasperParam.put("SEDIME_GESTORE" , "");}
        try{jasperParam.put("IND_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getSedeLegale().getIndirizzo());}
        catch(Throwable t) {jasperParam.put("IND_GESTORE" , "");}
        try{jasperParam.put("COM_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getSedeLegale().getComune().getComune());}
        catch(Throwable t) {jasperParam.put("COM_GESTORE" , "");}
        try{jasperParam.put("CAP_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getSedeLegale().getCap() != null ? prevConsExtendedVO.getImpianto().getGestore().getSedeLegale().getCap() : prevConsExtendedVO.getImpianto().getGestore().getSedeLegale().getComune().getCap());}
        catch(Throwable t) {jasperParam.put("CAP_GESTORE" , "");}
        try{jasperParam.put("PROV_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getSedeLegale().getComune().getProvincia().getDescProvincia());}
        catch(Throwable t) {jasperParam.put("PROV_GESTORE" , "");}
	}
	
	private void putDichiarante_2(PrevConsExtendedVO prevConsExtendedVO, Map<String, Object> jasperParam) {
	    try{jasperParam.put("TEL_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getTelefono());}
        catch(Throwable t) {jasperParam.put("TEL_GESTORE" , "");}
        try{jasperParam.put("MAIL_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getEmail());}
        catch(Throwable t) {jasperParam.put("MAIL_GESTORE" , "");}
        try{jasperParam.put("PEC_GESTORE" , prevConsExtendedVO.getImpianto().getGestore().getPec());}
        catch(Throwable t) {jasperParam.put("PEC_GESTORE" , "");}
        try{jasperParam.put("CF" , prevConsExtendedVO.getImpianto().getGestore().getLegaleRappresentante().getDatiSogg().getCodFiscale());}
        catch(Throwable t) {jasperParam.put("CF" , "");}
        try{jasperParam.put("NOME" , prevConsExtendedVO.getImpianto().getGestore().getLegaleRappresentante().getDatiSogg().getNome());}
        catch(Throwable t) {jasperParam.put("NOME" , "");}
        try{jasperParam.put("COGNOME" , prevConsExtendedVO.getImpianto().getGestore().getLegaleRappresentante().getDatiSogg().getCognome());}
        catch(Throwable t) {jasperParam.put("COGNOME" , "");}
        try{jasperParam.put("QUALIFICA" , prevConsExtendedVO.getImpianto().getGestore().getLegaleRappresentante().getQualifica());}
        catch(Throwable t) {jasperParam.put("QUALIFICA" , "");}
	}
	
	private void putImpianto(PrevConsExtendedVO prevConsExtendedVO, Map<String, Object> jasperParam) {
	    try{jasperParam.put("NOME_IMPIANTO" , prevConsExtendedVO.getImpianto().getDenominazione());}
        catch(Throwable t) {jasperParam.put("NOME_IMPIANTO" , "");}
        try{jasperParam.put("TIPO_IMPIANTO" , prevConsExtendedVO.getImpianto().getTipoImpianto().getDescTipoImpianto());}
        catch(Throwable t) {jasperParam.put("TIPO_IMPIANTO" , "");}
        try{jasperParam.put("STATO_IMPIANTO" , prevConsExtendedVO.getImpianto().getStatoImpianto().getDescStatoImpianto());}
        catch(Throwable t) {jasperParam.put("STATO_IMPIANTO" , "");}
        try{jasperParam.put("SEDIME_IMPIANTO" , prevConsExtendedVO.getImpianto().getIndirizzo().getSedime().getDescSedime());}
        catch(Throwable t) {jasperParam.put("SEDIME_IMPIANTO" , "");}
        try{jasperParam.put("INDIRIZZO_IMPIANTO" , prevConsExtendedVO.getImpianto().getIndirizzo().getIndirizzo());}
        catch(Throwable t) {jasperParam.put("INDIRIZZO_IMPIANTO" , "");}
        try{jasperParam.put("COMUNE_IMPIANTO" , prevConsExtendedVO.getImpianto().getIndirizzo().getComune().getComune());}
        catch(Throwable t) {jasperParam.put("COMUNE_IMPIANTO" , "");}
        try{jasperParam.put("CAP_IMPIANTO" , prevConsExtendedVO.getImpianto().getIndirizzo().getCap() != null ? prevConsExtendedVO.getImpianto().getIndirizzo().getCap() : prevConsExtendedVO.getImpianto().getIndirizzo().getComune().getCap());}
        catch(Throwable t) {jasperParam.put("CAP_IMPIANTO" , "");}
        try{jasperParam.put("PROV_IMPIANTO" , prevConsExtendedVO.getImpianto().getIndirizzo().getComune().getProvincia().getDescProvincia());}
        catch(Throwable t) {jasperParam.put("PROV_IMPIANTO" , "");}
	}
	
	private void putRichiestaAmmissione(PrevConsExtendedVO prevConsExtendedVO, Map<String, Object> jasperParam) {
	    try{jasperParam.put("RMR_ANNO_TRIBUTO" , prevConsExtendedVO.getPrevConsRichiesta().getAnnoTributo());}
        catch(Throwable t) {jasperParam.put("RMR_ANNO_TRIBUTO" , null);}
        try{jasperParam.put("RMR_DATA_PROTOCOLLO" , prevConsExtendedVO.getPrevConsRichiesta().getDataProtocollo());}
        catch(Throwable t) {jasperParam.put("RMR_DATA_PROTOCOLLO" , null);}
        try{jasperParam.put("RMR_NUM_PROTOCOLLO" , prevConsExtendedVO.getPrevConsRichiesta().getNumProtocollo());}
        catch(Throwable t) {jasperParam.put("RMR_NUM_PROTOCOLLO" , "");}
        
        try{jasperParam.put("DATA_INVIO_DOC" , prevConsExtendedVO.getDataInvioDoc());}
        catch(Throwable t) {jasperParam.put("DATA_INVIO_DOC" , "");}
        try{jasperParam.put("MODALITA" , prevConsExtendedVO.getModalita());}
        catch(Throwable t) {jasperParam.put("MODALITA" , "");}
        try{jasperParam.put("NUM_PROT_DOC" , prevConsExtendedVO.getNumProtDoc());}
        catch(Throwable t) {jasperParam.put("NUM_PROT_DOC" , "");}
	}
	
	private void putDbConf(Map<String, Object> jasperParam) {
	    for(TsddrTReportDett dett : getTsddrTReport().getReportDett()) {
            if(dett.getTipoCampo().getIdTipoCampo() == 1) {
                jasperParam.put(dett.getCodCampo(), dett.getTesto()!=null?dett.getTesto():"");
            }else if(dett.getCodCampo().equalsIgnoreCase("LOGO")) {
                try {
                    jasperParam.put("LOGO_RP", getImageFromBase64ByteArray(dett.getLogo()));
                } catch(Exception e) {
                    logger.error("Exception: " + e);
                }
            } else {
                jasperParam.put(dett.getCodCampo(), dett.getTesto()!=null?dett.getTesto():"");
            }
        }
	}

	private List<SezioneQuattro> valorizeSezioneQuattro(List<SezioneQuattro> sezioniQuattro,
            List<PrevConsLineeExtendedVO> prevConsLineeExtended, Long anno) {
	    for(PrevConsLineeExtendedVO prevConsLineaExtended : prevConsLineeExtended) {
	        SezioneQuattro sezioneQuattro = new SezioneQuattro();
	        sezioneQuattro.setDescLinea(prevConsLineaExtended.getDescLinea());
	        sezioneQuattro.setDescSommaria(prevConsLineaExtended.getDescSommaria());
	        
	        sezioneQuattro.setTotRii(prevConsLineaExtended.getTotRii());
	        sezioneQuattro.setTotMat(prevConsLineaExtended.getTotMat());
	        sezioneQuattro.setTotRru(prevConsLineaExtended.getTotRru());
	        sezioneQuattro.setTotRu(prevConsLineaExtended.getTotRu());

	        //Recupero flag visualizzazione per recupero
	        boolean isPercRecuperoVisible = prevConsService.isPercRecuperoVisible(prevConsLineaExtended.getCodLinea(), anno);
	        //Recupero flag visualizzazione per scarto
	        boolean isPercScartoVisible = prevConsService.isPercScartoVisible(prevConsLineaExtended.getCodLinea(), anno);
	        
	        sezioneQuattro.setPercRecupero(isPercRecuperoVisible?prevConsLineaExtended.getPercRecupero():null);
	        sezioneQuattro.setPercScarto(isPercScartoVisible?prevConsLineaExtended.getPercScarto():null);
	        sezioneQuattro.setDescLinea(prevConsLineaExtended.getDescLinea());
	        
	        
	        // Imposto i campi di conf dal db
	        for(TsddrTReportDett dett : getTsddrTReport().getReportDett()) {
	            putSezioneQuattroConfDb_1(dett, sezioneQuattro);
	            putSezioneQuattroConfDb_2(dett, sezioneQuattro);
	            
	          
	        }
	        
	        // Valorizzo parametri sezioni
	        putParametersSezioni(sezioneQuattro);
	        
	        // Set Sezioni Rii, Mat, Rru, ru
	        List<PrevConsDettVO> sezioneRii = new ArrayList<>();
	        List<PrevConsDettVO> sezioneMat = new ArrayList<>();
	        List<PrevConsDettVO> sezioneRru = new ArrayList<>();
	        List<PrevConsDettVO> sezioneRu = new ArrayList<>();
	        long riiIndex = 1;
	        long matIndex = 1;
	        long rruIndex = 1;
	        long ruIndex = 1;
	        
	        for(PrevConsDettVO prevConsDettVO : prevConsLineaExtended.getPrevConsDett()) {
	            if(prevConsDettVO.getSezione().getIdSezione().compareTo(2L) == 0) {
	            	prevConsDettVO.setIdPrevConsDett(riiIndex);
	                sezioneRii.add(prevConsDettVO);
	                riiIndex++;
	            } else if(prevConsDettVO.getSezione().getIdSezione().compareTo(3L) == 0) {
	            	prevConsDettVO.setIdPrevConsDett(matIndex);
	                sezioneMat.add(prevConsDettVO);
	                matIndex++;
	            } else if(prevConsDettVO.getSezione().getIdSezione().compareTo(4L) == 0) {
	            	prevConsDettVO.setIdPrevConsDett(rruIndex);
	                sezioneRru.add(prevConsDettVO);
	                rruIndex++;
	            } else if(prevConsDettVO.getSezione().getIdSezione().compareTo(5L) == 0) {
	            	prevConsDettVO.setIdPrevConsDett(ruIndex);
	                sezioneRu.add(prevConsDettVO);
	                ruIndex++;
	            }
	        }
	        sezioneQuattro.setSezioneRii(new JRBeanCollectionDataSource(sezioneRii));
	        sezioneQuattro.setSezioneMat(new JRBeanCollectionDataSource(sezioneMat));
	        sezioneQuattro.setSezioneRru(new JRBeanCollectionDataSource(sezioneRru));
	        sezioneQuattro.setSezioneRu(new JRBeanCollectionDataSource(sezioneRu));
	        sezioniQuattro.add(sezioneQuattro);
	    }
        return sezioniQuattro;
    }
	
	private void putParametersSezioni(SezioneQuattro sezioneQuattro) {
	    for(TsddrDSezione sezione : getSezioni()) {
            if(sezione.getIdSezione().compareTo(1L) == 0) {
                sezioneQuattro.setDescSezione1(sezione.getDescSezione());
            } else if(sezione.getIdSezione().compareTo(2L) == 0) {
                sezioneQuattro.setDescSezione2(sezione.getDescSezione());
            } else if(sezione.getIdSezione().compareTo(3L) == 0) {
                sezioneQuattro.setDescSezione3(sezione.getDescSezione());
            } else if(sezione.getIdSezione().compareTo(4L) == 0) {
                sezioneQuattro.setDescSezione4(sezione.getDescSezione());
            } else if(sezione.getIdSezione().compareTo(5L) == 0) {
                sezioneQuattro.setDescSezione5(sezione.getDescSezione());
            }
        }
	}
	
	private void putSezioneQuattroConfDb_1(TsddrTReportDett dett, SezioneQuattro sezioneQuattro) {
	    if(dett.getCodCampo().equals("SEZ_PIMP1")) {
            sezioneQuattro.setSezPimp1(dett.getTesto());
        } else if(dett.getCodCampo().equals("SEZ_PIMP2")) {
            sezioneQuattro.setSezPimp2(dett.getTesto());
        } else if(dett.getCodCampo().equals("PIMP-DES")) {
            sezioneQuattro.setPimpDes(dett.getTesto());
        } else if(dett.getCodCampo().equals("RII-TOT")) {
            sezioneQuattro.setRiiTotDesc(dett.getTesto());
        } else if(dett.getCodCampo().equals("MAT-TOT")) {
            sezioneQuattro.setMatTotDesc(dett.getTesto());
        } else if(dett.getCodCampo().equals("RUU-TOT")) {
            sezioneQuattro.setRruTotDesc(dett.getTesto());
        } else if(dett.getCodCampo().equals("RU-TOT")) {
            sezioneQuattro.setRuTotDesc(dett.getTesto());
        } else if(dett.getCodCampo().equals("OBIETT")) {
            sezioneQuattro.setObiett(dett.getTesto());
        }
	}
	
	private void putSezioneQuattroConfDb_2(TsddrTReportDett dett, SezioneQuattro sezioneQuattro) {
	    if(dett.getCodCampo().equals("OBIETT-REC")) {
            sezioneQuattro.setObiettRec(dett.getTesto());
        }  else if(dett.getCodCampo().equals("OBIETT-SCA")) {
            sezioneQuattro.setObiettSca(dett.getTesto());
        } else if(dett.getCodCampo().equals("RII-TESTA")) {
            String[] elements = dett.getTesto().split("\\|"); 
            sezioneQuattro.setRiiTesta0(elements[0]);
            sezioneQuattro.setRiiTesta1(elements[1]);
            sezioneQuattro.setRiiTesta2(elements[2]);
            sezioneQuattro.setRiiTesta3(elements[3]);
        } else if(dett.getCodCampo().equals("MAT-TESTA")) {
            String[] elements = dett.getTesto().split("\\|"); 
            sezioneQuattro.setMatTesta0(elements[0]);
            sezioneQuattro.setMatTesta1(elements[1]);
            sezioneQuattro.setMatTesta2(elements[2]);
        } else if(dett.getCodCampo().equals("RUU-TESTA")) {
            String[] elements = dett.getTesto().split("\\|"); 
            sezioneQuattro.setRruTesta0(elements[0]);
            sezioneQuattro.setRruTesta1(elements[1]);
            sezioneQuattro.setRruTesta2(elements[2]);
            sezioneQuattro.setRruTesta3(elements[3]);
            sezioneQuattro.setRruTesta4(elements[4]);
        } else if(dett.getCodCampo().equals("RU-TESTA")) {
            String[] elements = dett.getTesto().split("\\|"); 
            sezioneQuattro.setRuTesta0(elements[0]);
            sezioneQuattro.setRuTesta1(elements[1]);
            sezioneQuattro.setRuTesta2(elements[2]);
            sezioneQuattro.setRuTesta3(elements[3]);
            sezioneQuattro.setRuTesta4(elements[4]);
        }
	}
	

    public Long getTsddrReportId() {
		return REPORT_ID;
	}

    @Override
    public void setJasperParam(TsddrTDichAnnuale var1, Map<String, Object> var2) throws IOException {
        // TODO Auto-generated method stub
    }
	
}
