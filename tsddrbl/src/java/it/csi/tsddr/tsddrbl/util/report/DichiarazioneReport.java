/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.report;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReportDett;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.DichAnnualeEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.GestoreEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTDichAnnualeRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrTReportRepository;
import it.csi.tsddr.tsddrbl.util.enums.StatoDichiarazione;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.ConferimentoVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutiConferitiVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoConferitoVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoTariffaVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.VersamentoVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class DichiarazioneReport implements TsddrReport {
	
    private static Logger logger = Logger.getLogger(DichiarazioneReport.class);

	private static Long REPORT_ID = 1L;	

	@Autowired
	private TsddrTReportRepository tsddrTReportRepository;
	
	@Autowired
    private TsddrTDichAnnualeRepository dichAnnualeRepository;

	@Autowired
	private DichAnnualeEntityMapper dichAnnualeEntityMapper;

	@Autowired
	private GestoreEntityMapper gestoreEntityMapper;
	
	TsddrTReport tsddrTReport = null;

	public TsddrTReport getTsddrTReport(){
		if(tsddrTReport == null ) {
			tsddrTReport = tsddrTReportRepository.findOne(getTsddrReportId());
		}
		return tsddrTReport;
	}
	
	public String getJrxml() throws Exception {
		return this.getTemplateJrxml(getTsddrTReport().getXmlReport());
	}
	
	public void setJasperParam(TsddrTDichAnnuale dichiarazione, Map<String, Object> jasperParam) throws IOException {
		
	    Optional<TsddrTDichAnnuale> dichAnnualeAnnoPrecedenteOpt = dichAnnualeRepository.findByIdImpiantoAndAnnoAndStato(dichiarazione.getImpianto().getIdImpianto(), (dichiarazione.getAnno() - 1), StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
        BigDecimal creditoImposta = dichAnnualeAnnoPrecedenteOpt.map(TsddrTDichAnnuale::getCreditoImposta).orElse(null);
        
		DichAnnualeVO dichAnnualeVO = dichAnnualeEntityMapper.mapEntityToVO(dichiarazione, creditoImposta != null ? creditoImposta.doubleValue() : null);
		
		GestoreVO gestore = gestoreEntityMapper.mapEntityToVO(dichiarazione.getImpianto().getGestore());
		jasperParam.put("ANNO", dichAnnualeVO.getAnno());
		jasperParam.put("dataDichiarazione", dichAnnualeVO.getDataDichiarazione());
		
		// DICHIARANTE
		putDichiarante_1(jasperParam ,gestore);
		putDichiarante_2(jasperParam ,gestore);
		
		// IMPIANTO
		putImpianto(jasperParam, dichAnnualeVO);
		
		// LUOGO 
		putLuogo(jasperParam, dichAnnualeVO);
		
		// PROVVEDIMENTO sono la lista di atti dell'impianto
    	jasperParam.put("provvedimenti", new JRBeanCollectionDataSource(dichAnnualeVO.getImpianto().getAtti()));
		
		// Conferimenti
		RifiutiConferitiVO rifiutiConferiti = dichAnnualeVO.getRifiutiConferiti();
		
		for(RifiutoConferitoVO rifiutoConferitoVO : rifiutiConferiti.getRifiutiConferiti()) {
		   String stringRiduzione = rifiutoConferitoVO.getRifiutoTariffa().getFlagRiduzione() == true ? "Presente" : "";
		   rifiutoConferitoVO.getRifiutoTariffa().setStringRiduzione(stringRiduzione);
		   
		   String descrizioneCompleta = this.generaDescrizioneCompletaRifiuto(rifiutoConferitoVO.getRifiutoTariffa());
		   rifiutoConferitoVO.getRifiutoTariffa().setDescrizioneCompleta(descrizioneCompleta);
		}
		// ordino i conferimenti
		// ordino i rifiuti conferiti
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
    	jasperParam.put("rifiutiConferiti", new JRBeanCollectionDataSource(rifiutiConferiti.getRifiutiConferiti()));
    	jasperParam.put("totalerifiutiConferiti" , new JRBeanCollectionDataSource(rifiutiConferiti.getTotali().getTotaliPeriodi()));

    	jasperParam.put("totaleQuantita" , rifiutiConferiti.getTotali().getTotale().getQuantita());
    	jasperParam.put("totaleImporto" , rifiutiConferiti.getTotali().getTotale().getImporto());
    	
    	// Versamenti
    	// ordino i versamenti 
    	if(dichAnnualeVO.getVersamenti() != null && 
                dichAnnualeVO.getVersamenti().getVersamenti() != null) {
            List<VersamentoVO> versamentiVO = dichAnnualeVO.getVersamenti().getVersamenti()
                .stream()
                .sorted((o1, o2) -> o1.getPeriodo().getIdPeriodo().compareTo(o2.getPeriodo().getIdPeriodo()))
                .collect(Collectors.toList());
            dichAnnualeVO.getVersamenti().setVersamenti(versamentiVO);
        }
    	jasperParam.put("versamenti", new JRBeanCollectionDataSource(dichAnnualeVO.getVersamenti().getVersamenti()));
    	jasperParam.put("versamentiCredito", dichAnnualeVO.getVersamenti().getCredito());
    	jasperParam.put("versamentiCreditoAP", dichAnnualeVO.getVersamenti().getCreditoAP());
    	jasperParam.put("versamentiDebito", dichAnnualeVO.getVersamenti().getDebito());
    	jasperParam.put("versamentiTotale", dichAnnualeVO.getVersamenti().getTotale());
    	
    	// Soggetti MR
    	jasperParam.put("soggettimr", new JRBeanCollectionDataSource(dichAnnualeVO.getSoggettiMr()));

    	// Annotazioni
    	jasperParam.put("annotazioni", dichAnnualeVO.getAnnotazioni());
    	
    	// Imposto i campi di conf dal db
    	putDbConf(jasperParam);
		
	}
	
	private void putDichiarante_1(Map<String, Object> jasperParam, GestoreVO gestore) {
	    try{jasperParam.put("CF_GESTORE" , gestore.getCodFiscPartiva());}
        catch(Throwable t) {jasperParam.put("CF_GESTORE" , "");}
        try{jasperParam.put("RAGSOG_GESTORE" , gestore.getRagSociale());}
        catch(Throwable t) {jasperParam.put("RAGSOG_GESTORE" , "");}
        try{jasperParam.put("NATGIU_GESTORE" , gestore.getNaturaGiuridica().getDescNaturaGiuridica());}
        catch(Throwable t) {jasperParam.put("NATGIU_GESTORE" , "");}
        try{jasperParam.put("SEDIME_GESTORE" , gestore.getSedeLegale().getSedime().getDescSedime());}
        catch(Throwable t) {jasperParam.put("SEDIME_GESTORE" , "");}
        try{jasperParam.put("IND_GESTORE" , gestore.getSedeLegale().getIndirizzo());}
        catch(Throwable t) {jasperParam.put("IND_GESTORE" , "");}
        try{jasperParam.put("COM_GESTORE" , gestore.getSedeLegale().getComune().getComune());}
        catch(Throwable t) {jasperParam.put("COM_GESTORE" , "");}
        try{jasperParam.put("CAP_GESTORE" , gestore.getSedeLegale().getCap() != null ? gestore.getSedeLegale().getCap() : gestore.getSedeLegale().getComune().getCap());}
        catch(Throwable t) {jasperParam.put("CAP_GESTORE" , "");}
        try{jasperParam.put("PROV_GESTORE" , gestore.getSedeLegale().getComune().getProvincia().getDescProvincia());}
        catch(Throwable t) {jasperParam.put("PROV_GESTORE" , "");}
	}
	
	private void putDichiarante_2(Map<String, Object> jasperParam, GestoreVO gestore) {
	    try{jasperParam.put("TEL_GESTORE" , gestore.getTelefono());}
        catch(Throwable t) {jasperParam.put("TEL_GESTORE" , "");}
        try{jasperParam.put("MAIL_GESTORE" , gestore.getEmail());}
        catch(Throwable t) {jasperParam.put("MAIL_GESTORE" , "");}
        try{jasperParam.put("PEC_GESTORE" , gestore.getPec());}
        catch(Throwable t) {jasperParam.put("PEC_GESTORE" , "");}
        try{jasperParam.put("CF" , gestore.getLegaleRappresentante().getDatiSogg().getCodFiscale());}
        catch(Throwable t) {jasperParam.put("CF" , "");}
        try{jasperParam.put("NOME" , gestore.getLegaleRappresentante().getDatiSogg().getNome());}
        catch(Throwable t) {jasperParam.put("NOME" , "");}
        try{jasperParam.put("COGNOME" , gestore.getLegaleRappresentante().getDatiSogg().getCognome());}
        catch(Throwable t) {jasperParam.put("COGNOME" , "");}
        try{jasperParam.put("QUALIFICA" , gestore.getLegaleRappresentante().getQualifica());}
        catch(Throwable t) {jasperParam.put("QUALIFICA" , "");}
    }
	
	private void putImpianto(Map<String, Object> jasperParam, DichAnnualeVO dichAnnualeVO) {
	    try{jasperParam.put("NOME_IMPIANTO" , dichAnnualeVO.getImpianto().getDenominazione());}
        catch(Throwable t) {jasperParam.put("NOME_IMPIANTO" , "");}
        try{jasperParam.put("TIPO_IMPIANTO" , dichAnnualeVO.getImpianto().getTipoImpianto().getDescTipoImpianto());}
        catch(Throwable t) {jasperParam.put("TIPO_IMPIANTO" , "");}
        try{jasperParam.put("STATO_IMPIANTO" , dichAnnualeVO.getImpianto().getStatoImpianto().getDescStatoImpianto());}
        catch(Throwable t) {jasperParam.put("STATO_IMPIANTO" , "");}
        try{jasperParam.put("SEDIME_IMPIANTO" , dichAnnualeVO.getImpianto().getIndirizzo().getSedime().getDescSedime());}
        catch(Throwable t) {jasperParam.put("SEDIME_IMPIANTO" , "");}
        try{jasperParam.put("INDIRIZZO_IMPIANTO" , dichAnnualeVO.getImpianto().getIndirizzo().getIndirizzo());}
        catch(Throwable t) {jasperParam.put("INDIRIZZO_IMPIANTO" , "");}
        try{jasperParam.put("COMUNE_IMPIANTO" , dichAnnualeVO.getImpianto().getIndirizzo().getComune().getComune());}
        catch(Throwable t) {jasperParam.put("COMUNE_IMPIANTO" , "");}
        try{jasperParam.put("CAP_IMPIANTO" , dichAnnualeVO.getImpianto().getIndirizzo().getCap() != null ? dichAnnualeVO.getImpianto().getIndirizzo().getCap() : dichAnnualeVO.getImpianto().getIndirizzo().getComune().getCap());}
        catch(Throwable t) {jasperParam.put("CAP_IMPIANTO" , "");}
        try{jasperParam.put("PROV_IMPIANTO" , dichAnnualeVO.getImpianto().getIndirizzo().getComune().getProvincia().getDescProvincia());}
        catch(Throwable t) {jasperParam.put("PROV_IMPIANTO" , "");}
	}
	
	private void putLuogo(Map<String, Object> jasperParam, DichAnnualeVO dichAnnualeVO) {
	    try{jasperParam.put("SEDIME_LUOGO" , dichAnnualeVO.getIndirizzo().getSedime().getDescSedime());}
        catch(Throwable t) {jasperParam.put("SEDIME_LUOGO" , "");}
        try{jasperParam.put("INDIRIZZO_LUOGO" , dichAnnualeVO.getIndirizzo().getIndirizzo());}
        catch(Throwable t) {jasperParam.put("INDIRIZZO_LUOGO" , "");}
        try{jasperParam.put("COMUNE_LUOGO" , dichAnnualeVO.getIndirizzo().getComune().getComune());}
        catch(Throwable t) {jasperParam.put("COMUNE_LUOGO" , "");}
        try{jasperParam.put("CAP_LUOGO" , dichAnnualeVO.getIndirizzo().getCap());}
        catch(Throwable t) {jasperParam.put("CAP_LUOGO" , "");}
        try{jasperParam.put("PROV_LUOGO" , dichAnnualeVO.getIndirizzo().getComune().getProvincia().getDescProvincia());}
        catch(Throwable t) {jasperParam.put("PROV_LUOGO" , "");}
	}
	
	private void putDbConf(Map<String, Object> jasperParam) {
	    for(TsddrTReportDett dett : getTsddrTReport().getReportDett()) {
            if(dett.getTipoCampo().getIdTipoCampo() == 1) {
                jasperParam.put(dett.getCodCampo(), dett.getTesto()!=null?dett.getTesto():"");
            }else if(dett.getCodCampo().equalsIgnoreCase("LOGO RP")) {
                try {
                    jasperParam.put("LOGO_RP", getImageFromBase64ByteArray(dett.getLogo()));
                } catch(Exception e) {
                    logger.error("Exception: " + e);
                }
            }
        }
	}

	public Long getTsddrReportId() {
		return REPORT_ID;
	}
	
	private String generaDescrizioneCompletaRifiuto(RifiutoTariffaVO rifiutoTariffaVO) {
	    var sb = new StringBuilder();
	    sb.append(rifiutoTariffaVO.getIdTipoRifiuto()).append(", ");
	    sb.append(rifiutoTariffaVO.getIdTipologia2()).append(", ");
	    if(rifiutoTariffaVO.getIdTipologia3() != null) 
	        sb.append(rifiutoTariffaVO.getIdTipologia3()).append(", ");
	    sb.append(rifiutoTariffaVO.getDescrizione());
	    return sb.toString();
	}

    @Override
    public void setJasperParam(PrevConsExtendedVO prevConsExtendedVO, Map<String, Object> var2) throws IOException {
        // TODO Auto-generated method stub
        
    }
	
}
