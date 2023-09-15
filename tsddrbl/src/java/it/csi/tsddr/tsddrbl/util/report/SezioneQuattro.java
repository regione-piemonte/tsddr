/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.report;

import java.math.BigDecimal;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SezioneQuattro {
    
    private String sezTimp1;
    private String sezTimp2;

    private String sezPimp1;
    private String sezPimp2;
    private String pimpDes;
    private String descLinea;
    private String descSezione1;
    private String descSommaria;

    private String descSezione2;
    private String riiTesta0;
    private String riiTesta1;
    private String riiTesta2;
    private String riiTesta3;
    private String riiTotDesc;
    private BigDecimal totRii;

    private String descSezione3;
    private String matTesta0;
    private String matTesta1;
    private String matTesta2;
    private String matTotDesc;
    private BigDecimal totMat;

    private String descSezione4;
    private String rruTesta0;
    private String rruTesta1;
    private String rruTesta2;
    private String rruTesta3;
    private String rruTesta4;
    private String rruTotDesc;
    private BigDecimal totRru;

    private String descSezione5;
    private String ruTesta0;
    private String ruTesta1;
    private String ruTesta2;
    private String ruTesta3;
    private String ruTesta4;
    private String ruTotDesc;
    private BigDecimal totRu;

    private String obiett;
    private String obiettRec;
    private BigDecimal percRecupero;
    private String obiettSca;
    private BigDecimal percScarto;
    
    private JRBeanCollectionDataSource sezioneRii; 
    private JRBeanCollectionDataSource sezioneMat; 
    private JRBeanCollectionDataSource sezioneRru; 
    private JRBeanCollectionDataSource sezioneRu; 

    public String getSezTimp1() {
        return sezTimp1;
    }

    public void setSezTimp1(String sezTimp1) {
        this.sezTimp1 = sezTimp1;
    }

    public String getSezTimp2() {
        return sezTimp2;
    }

    public void setSezTimp2(String sezTimp2) {
        this.sezTimp2 = sezTimp2;
    }

    public String getSezPimp1() {
        return sezPimp1;
    }

    public void setSezPimp1(String sezPimp1) {
        this.sezPimp1 = sezPimp1;
    }

    public String getSezPimp2() {
        return sezPimp2;
    }

    public void setSezPimp2(String sezPimp2) {
        this.sezPimp2 = sezPimp2;
    }

    public String getPimpDes() {
        return pimpDes;
    }

    public void setPimpDes(String pimpDes) {
        this.pimpDes = pimpDes;
    }

    public String getDescLinea() {
        return descLinea;
    }

    public void setDescLinea(String descLinea) {
        this.descLinea = descLinea;
    }

    public String getDescSezione1() {
        return descSezione1;
    }

    public void setDescSezione1(String descSezione1) {
        this.descSezione1 = descSezione1;
    }

    public String getDescSommaria() {
        return descSommaria;
    }

    public void setDescSommaria(String descSommaria) {
        this.descSommaria = descSommaria;
    }

    public String getDescSezione2() {
        return descSezione2;
    }

    public void setDescSezione2(String descSezione2) {
        this.descSezione2 = descSezione2;
    }

    public String getRiiTesta0() {
        return riiTesta0;
    }

    public void setRiiTesta0(String riiTesta0) {
        this.riiTesta0 = riiTesta0;
    }

    public String getRiiTesta1() {
        return riiTesta1;
    }

    public void setRiiTesta1(String riiTesta1) {
        this.riiTesta1 = riiTesta1;
    }

    public String getRiiTesta2() {
        return riiTesta2;
    }

    public void setRiiTesta2(String riiTesta2) {
        this.riiTesta2 = riiTesta2;
    }

    public String getRiiTesta3() {
        return riiTesta3;
    }

    public void setRiiTesta3(String riiTesta3) {
        this.riiTesta3 = riiTesta3;
    }

    public String getRiiTotDesc() {
        return riiTotDesc;
    }

    public void setRiiTotDesc(String riiTotDesc) {
        this.riiTotDesc = riiTotDesc;
    }

    public BigDecimal getTotRii() {
        return totRii;
    }

    public void setTotRii(BigDecimal totRii) {
        this.totRii = totRii;
    }

    public String getDescSezione3() {
        return descSezione3;
    }

    public void setDescSezione3(String descSezione3) {
        this.descSezione3 = descSezione3;
    }

    public String getMatTesta0() {
        return matTesta0;
    }

    public void setMatTesta0(String matTesta0) {
        this.matTesta0 = matTesta0;
    }

    public String getMatTesta1() {
        return matTesta1;
    }

    public void setMatTesta1(String matTesta1) {
        this.matTesta1 = matTesta1;
    }

    public String getMatTesta2() {
        return matTesta2;
    }

    public void setMatTesta2(String matTesta2) {
        this.matTesta2 = matTesta2;
    }

    public String getMatTotDesc() {
        return matTotDesc;
    }

    public void setMatTotDesc(String matTotDesc) {
        this.matTotDesc = matTotDesc;
    }

    public BigDecimal getTotMat() {
        return totMat;
    }

    public void setTotMat(BigDecimal totMat) {
        this.totMat = totMat;
    }

    public String getDescSezione4() {
        return descSezione4;
    }

    public void setDescSezione4(String descSezione4) {
        this.descSezione4 = descSezione4;
    }

    public String getRruTesta0() {
        return rruTesta0;
    }

    public void setRruTesta0(String rruTesta0) {
        this.rruTesta0 = rruTesta0;
    }

    public String getRruTesta1() {
        return rruTesta1;
    }

    public void setRruTesta1(String rruTesta1) {
        this.rruTesta1 = rruTesta1;
    }

    public String getRruTesta2() {
        return rruTesta2;
    }

    public void setRruTesta2(String rruTesta2) {
        this.rruTesta2 = rruTesta2;
    }

    public String getRruTesta3() {
        return rruTesta3;
    }

    public void setRruTesta3(String rruTesta3) {
        this.rruTesta3 = rruTesta3;
    }

    public String getRruTesta4() {
        return rruTesta4;
    }

    public void setRruTesta4(String rruTesta4) {
        this.rruTesta4 = rruTesta4;
    }

    public String getRruTotDesc() {
        return rruTotDesc;
    }

    public void setRruTotDesc(String rruTotDesc) {
        this.rruTotDesc = rruTotDesc;
    }

    public BigDecimal getTotRru() {
        return totRru;
    }

    public void setTotRru(BigDecimal totRru) {
        this.totRru = totRru;
    }

    public String getDescSezione5() {
        return descSezione5;
    }

    public void setDescSezione5(String descSezione5) {
        this.descSezione5 = descSezione5;
    }

    public String getRuTesta0() {
        return ruTesta0;
    }

    public void setRuTesta0(String ruTesta0) {
        this.ruTesta0 = ruTesta0;
    }

    public String getRuTesta1() {
        return ruTesta1;
    }

    public void setRuTesta1(String ruTesta1) {
        this.ruTesta1 = ruTesta1;
    }

    public String getRuTesta2() {
        return ruTesta2;
    }

    public void setRuTesta2(String ruTesta2) {
        this.ruTesta2 = ruTesta2;
    }

    public String getRuTesta3() {
        return ruTesta3;
    }

    public void setRuTesta3(String ruTesta3) {
        this.ruTesta3 = ruTesta3;
    }

    public String getRuTesta4() {
        return ruTesta4;
    }

    public void setRuTesta4(String ruTesta4) {
        this.ruTesta4 = ruTesta4;
    }

    public String getRuTotDesc() {
        return ruTotDesc;
    }

    public void setRuTotDesc(String ruTotDesc) {
        this.ruTotDesc = ruTotDesc;
    }

    public BigDecimal getTotRu() {
        return totRu;
    }

    public void setTotRu(BigDecimal totRu) {
        this.totRu = totRu;
    }

    public String getObiett() {
        return obiett;
    }

    public void setObiett(String obiett) {
        this.obiett = obiett;
    }

    public String getObiettRec() {
        return obiettRec;
    }

    public void setObiettRec(String obiettRec) {
        this.obiettRec = obiettRec;
    }

    public BigDecimal getPercRecupero() {
        return percRecupero;
    }

    public void setPercRecupero(BigDecimal percRecupero) {
        this.percRecupero = percRecupero;
    }

    public String getObiettSca() {
        return obiettSca;
    }

    public void setObiettSca(String obiettSca) {
        this.obiettSca = obiettSca;
    }

    public BigDecimal getPercScarto() {
        return percScarto;
    }

    public void setPercScarto(BigDecimal percScarto) {
        this.percScarto = percScarto;
    }

    public JRBeanCollectionDataSource getSezioneRii() {
        return sezioneRii;
    }

    public void setSezioneRii(JRBeanCollectionDataSource sezioneRii) {
        this.sezioneRii = sezioneRii;
    }

    public JRBeanCollectionDataSource getSezioneMat() {
        return sezioneMat;
    }

    public void setSezioneMat(JRBeanCollectionDataSource sezioneMat) {
        this.sezioneMat = sezioneMat;
    }

    public JRBeanCollectionDataSource getSezioneRru() {
        return sezioneRru;
    }

    public void setSezioneRru(JRBeanCollectionDataSource sezioneRru) {
        this.sezioneRru = sezioneRru;
    }

    public JRBeanCollectionDataSource getSezioneRu() {
        return sezioneRu;
    }

    public void setSezioneRu(JRBeanCollectionDataSource sezioneRu) {
        this.sezioneRu = sezioneRu;
    }

}
