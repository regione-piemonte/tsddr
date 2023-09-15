/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.math.BigDecimal;

public class PrevConsLineeExtendedVO extends PrevConsLineeVO {

    private BigDecimal totRii;
    private BigDecimal totMat;
    private BigDecimal totRru;
    private BigDecimal totRu;

    private String descLinea;
    private String codLinea;

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

    public String getDescLinea() {
        return descLinea;
    }

    public void setDescLinea(String descLinea) {
        this.descLinea = descLinea;
    }

    public String getCodLinea() {
        return codLinea;
    }

    public void setCodLinea(String codLinea) {
        this.codLinea = codLinea;
    }

}
