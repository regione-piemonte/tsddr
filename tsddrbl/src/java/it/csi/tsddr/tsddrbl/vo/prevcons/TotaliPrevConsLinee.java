/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.math.BigDecimal;

public class TotaliPrevConsLinee {

    private BigDecimal totRii;
    private BigDecimal totMat;
    private BigDecimal totRru;
    private BigDecimal totRu;
    private BigDecimal percRecupero;
    private BigDecimal percScarto;
    
    public TotaliPrevConsLinee() {
        this.totRii = BigDecimal.ZERO;
        this.totMat = BigDecimal.ZERO;
        this.totRru = BigDecimal.ZERO;
        this.totRu = BigDecimal.ZERO;
        this.percRecupero = BigDecimal.ZERO;
        this.percScarto = BigDecimal.ZERO;
    }

    public BigDecimal getTotRii() {
        return totRii;
    }

    public void setTotRii(BigDecimal totRii) {
        this.totRii = totRii;
    }

    public BigDecimal getTotMat() {
        return totMat;
    }

    public void setTotMat(BigDecimal totMat) {
        this.totMat = totMat;
    }

    public BigDecimal getTotRru() {
        return totRru;
    }

    public void setTotRru(BigDecimal totRru) {
        this.totRru = totRru;
    }

    public BigDecimal getTotRu() {
        return totRu;
    }

    public void setTotRu(BigDecimal totRu) {
        this.totRu = totRu;
    }

    public BigDecimal getPercRecupero() {
        return percRecupero;
    }

    public void setPercRecupero(BigDecimal percRecupero) {
        this.percRecupero = percRecupero;
    }

    public BigDecimal getPercScarto() {
        return percScarto;
    }

    public void setPercScarto(BigDecimal percScarto) {
        this.percScarto = percScarto;
    }

}
