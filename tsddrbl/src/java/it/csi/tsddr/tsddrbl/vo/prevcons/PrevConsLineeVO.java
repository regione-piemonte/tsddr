/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.math.BigDecimal;
import java.util.List;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class PrevConsLineeVO extends AbstractVO {

    private Long idPrevConsLinee;
    private String descSommaria;
    private BigDecimal percRecupero;
    private BigDecimal percScarto;
    private Long idPrevCons;
    private Long idImpiantoLinea;
    private List<PrevConsDettVO> prevConsDett;

    private Boolean hasBeenUpdated;

    public Long getIdPrevConsLinee() {
        return idPrevConsLinee;
    }

    public void setIdPrevConsLinee(Long idPrevConsLinee) {
        this.idPrevConsLinee = idPrevConsLinee;
    }

    public String getDescSommaria() {
        return descSommaria;
    }

    public void setDescSommaria(String descSommaria) {
        this.descSommaria = descSommaria;
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

    public Long getIdPrevCons() {
        return idPrevCons;
    }

    public void setIdPrevCons(Long idPrevCons) {
        this.idPrevCons = idPrevCons;
    }

    public Long getIdImpiantoLinea() {
        return idImpiantoLinea;
    }

    public void setIdImpiantoLinea(Long idImpiantoLinea) {
        this.idImpiantoLinea = idImpiantoLinea;
    }

    public List<PrevConsDettVO> getPrevConsDett() {
        return prevConsDett;
    }

    public void setPrevConsDett(List<PrevConsDettVO> prevConsDett) {
        this.prevConsDett = prevConsDett;
    }

    public Boolean getHasBeenUpdated() {
        return hasBeenUpdated;
    }

    public void setHasBeenUpdated(Boolean hasBeenUpdated) {
        this.hasBeenUpdated = hasBeenUpdated;
    }

}
