/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_r_prev_cons_linee database table.
 * 
 */
@Entity
@Table(name = "tsddr_r_prev_cons_linee")
@NamedQuery(name = "TsddrRPrevConsLinea.findAll", query = "SELECT t FROM TsddrRPrevConsLinea t")
public class TsddrRPrevConsLinea extends AbstractEntity {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prev_cons_linee", unique = true, nullable = false)
    private Long idPrevConsLinee;
    
    @Column(name = "desc_sommaria", length = 1000)
    private String descSommaria;
    
    @Column(name = "perc_recupero", precision=10, scale=6)
    private BigDecimal percRecupero;
    
    @Column(name = "perc_scarto", precision=10, scale=6)
    private BigDecimal percScarto;
    
    // bi-directional many-to-one association to TsddrTPrevCons
    @ManyToOne
    @JoinColumn(name = "id_prev_cons", nullable = false)
    private TsddrTPrevCons prevCons;

    // bi-directional many-to-one association to TsddrDProfilo
    @ManyToOne
    @JoinColumn(name = "id_impianto_linee", nullable = false)
    private TsddrRImpiantoLinea impiantoLinea;
    
    // bi-directional many-to-one association to TsddrTPrevConsDett
    @OneToMany(mappedBy = "prevConsLinea", cascade = CascadeType.ALL)
    private List<TsddrTPrevConsDett> prevConsDett;

    /**
     * Gets the id prev cons linee.
     *
     * @return the id prev cons linee
     */
    public Long getIdPrevConsLinee() {
        return idPrevConsLinee;
    }

    /**
     * Sets the id prev cons linee.
     *
     * @param idPrevConsLinee the new id prev cons linee
     */
    public void setIdPrevConsLinee(Long idPrevConsLinee) {
        this.idPrevConsLinee = idPrevConsLinee;
    }

    /**
     * Gets the desc sommaria.
     *
     * @return the desc sommaria
     */
    public String getDescSommaria() {
        return descSommaria;
    }

    /**
     * Sets the desc sommaria.
     *
     * @param descSommaria the new desc sommaria
     */
    public void setDescSommaria(String descSommaria) {
        this.descSommaria = descSommaria;
    }

    /**
     * Gets the perc recupero.
     *
     * @return the perc recupero
     */
    public BigDecimal getPercRecupero() {
        return percRecupero;
    }

    /**
     * Sets the perc recupero.
     *
     * @param percRecupero the new perc recupero
     */
    public void setPercRecupero(BigDecimal percRecupero) {
        this.percRecupero = percRecupero;
    }

    /**
     * Gets the perc scarto.
     *
     * @return the perc scarto
     */
    public BigDecimal getPercScarto() {
        return percScarto;
    }

    /**
     * Sets the perc scarto.
     *
     * @param percScarto the new perc scarto
     */
    public void setPercScarto(BigDecimal percScarto) {
        this.percScarto = percScarto;
    }

    /**
     * Gets the prev cons.
     *
     * @return the prev cons
     */
    public TsddrTPrevCons getPrevCons() {
        return prevCons;
    }

    /**
     * Sets the prev cons.
     *
     * @param prevCons the new prev cons
     */
    public void setPrevCons(TsddrTPrevCons prevCons) {
        this.prevCons = prevCons;
    }

    /**
     * Gets the impianto linea.
     *
     * @return the impianto linea
     */
    public TsddrRImpiantoLinea getImpiantoLinea() {
        return impiantoLinea;
    }

    /**
     * Sets the impianto linea.
     *
     * @param impiantoLinea the new impianto linea
     */
    public void setImpiantoLinea(TsddrRImpiantoLinea impiantoLinea) {
        this.impiantoLinea = impiantoLinea;
    }

    /**
     * Gets the prev cons dett.
     *
     * @return the prev cons dett
     */
    public List<TsddrTPrevConsDett> getPrevConsDett() {
        return prevConsDett;
    }

    /**
     * Sets the prev cons dett.
     *
     * @param prevConsDett the new prev cons dett
     */
    public void setPrevConsDett(List<TsddrTPrevConsDett> prevConsDett) {
        this.prevConsDett = prevConsDett;
    }
    
    /**
     * Adds the R impianti linee.
     *
     * @param prevConsDett the prev cons dett
     * @return the tsddr T prev cons dett
     */
    public TsddrTPrevConsDett addRImpiantiLinee(TsddrTPrevConsDett prevConsDett) {
        getPrevConsDett().add(prevConsDett);
        prevConsDett.setPrevConsLinea(this);

        return prevConsDett;
    }

    /**
     * Removes the R impianti linee.
     *
     * @param prevConsDett the prev cons dett
     * @return the tsddr T prev cons dett
     */
    public TsddrTPrevConsDett removeRImpiantiLinee(TsddrTPrevConsDett prevConsDett) {
        getPrevConsDett().remove(prevConsDett);
        prevConsDett.setPrevConsLinea(null);

        return prevConsDett;
    }
    
}
