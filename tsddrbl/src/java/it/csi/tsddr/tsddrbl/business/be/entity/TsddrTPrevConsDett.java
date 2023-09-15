/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_prev_cons_dett database table.
 * 
 */
@Entity
@Table(name="tsddr_t_prev_cons_dett")
@NamedQuery(name="TsddrTPrevConsDett.findAll", query="SELECT t FROM TsddrTPrevConsDett t")
public class TsddrTPrevConsDett extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prev_cons_dett", unique=true, nullable=false)
	private Long idPrevConsDett;

	@Column(length=200)
	private String destinazione;

	@Column(nullable=false, precision=10, scale=6)
	private BigDecimal quantita;

	//bi-directional many-to-one association to TsddrDEer
	@ManyToOne
	@JoinColumn(name="id_eer")
	private TsddrDEer eer;

	//bi-directional many-to-one association to TsddrDSezione
	@ManyToOne
	@JoinColumn(name="id_sezione", nullable=false)
	private TsddrDSezione sezione;

	//bi-directional many-to-one association to TsddrDUnitaMisura
	@ManyToOne
	@JoinColumn(name="id_unita_misura", nullable=false)
	private TsddrDUnitaMisura unitaMisura;
	
	@Column(name="desc_mat_uscita", length=200)
    private String descMatUscita;
	
	 // bi-directional many-to-one association to TsddrRPrevConsLinea
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_prev_cons_linee", nullable = false)
    private TsddrRPrevConsLinea prevConsLinea;
    
    @Column(name="id_prev_cons_dett_r_mr")
    private Long idPrevConsDettRmr;

	/**
	 * Instantiates a new tsddr T prev cons dett.
	 */
	public TsddrTPrevConsDett() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id prev cons dett.
	 *
	 * @return the id prev cons dett
	 */
	public Long getIdPrevConsDett() {
		return this.idPrevConsDett;
	}

	/**
	 * Sets the id prev cons dett.
	 *
	 * @param idPrevConsDett the new id prev cons dett
	 */
	public void setIdPrevConsDett(Long idPrevConsDett) {
		this.idPrevConsDett = idPrevConsDett;
	}

	/**
	 * Gets the destinazione.
	 *
	 * @return the destinazione
	 */
	public String getDestinazione() {
		return this.destinazione;
	}

	/**
	 * Sets the destinazione.
	 *
	 * @param destinazione the new destinazione
	 */
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	/**
	 * Gets the quantita.
	 *
	 * @return the quantita
	 */
	public BigDecimal getQuantita() {
		return this.quantita;
	}

	/**
	 * Sets the quantita.
	 *
	 * @param quantita the new quantita
	 */
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

	/**
	 * Gets the eer.
	 *
	 * @return the eer
	 */
	public TsddrDEer getEer() {
		return eer;
	}

	/**
	 * Sets the eer.
	 *
	 * @param eer the new eer
	 */
	public void setEer(TsddrDEer eer) {
		this.eer = eer;
	}

	/**
	 * Gets the sezione.
	 *
	 * @return the sezione
	 */
	public TsddrDSezione getSezione() {
		return sezione;
	}

	/**
	 * Sets the sezione.
	 *
	 * @param sezione the new sezione
	 */
	public void setSezione(TsddrDSezione sezione) {
		this.sezione = sezione;
	}

	/**
	 * Gets the unita misura.
	 *
	 * @return the unita misura
	 */
	public TsddrDUnitaMisura getUnitaMisura() {
		return unitaMisura;
	}

	/**
	 * Sets the unita misura.
	 *
	 * @param unitaMisura the new unita misura
	 */
	public void setUnitaMisura(TsddrDUnitaMisura unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

    /**
     * Gets the desc mat uscita.
     *
     * @return the desc mat uscita
     */
    public String getDescMatUscita() {
        return descMatUscita;
    }

    /**
     * Sets the desc mat uscita.
     *
     * @param descMatUscita the new desc mat uscita
     */
    public void setDescMatUscita(String descMatUscita) {
        this.descMatUscita = descMatUscita;
    }

    /**
     * Gets the prev cons linea.
     *
     * @return the prev cons linea
     */
    public TsddrRPrevConsLinea getPrevConsLinea() {
        return prevConsLinea;
    }

    /**
     * Sets the prev cons linea.
     *
     * @param prevConsLinea the new prev cons linea
     */
    public void setPrevConsLinea(TsddrRPrevConsLinea prevConsLinea) {
        this.prevConsLinea = prevConsLinea;
    }

    /**
     * Gets the id prev cons dett R mr.
     *
     * @return the id prev cons dett R mr
     */
    public Long getIdPrevConsDettRmr() {
        return idPrevConsDettRmr;
    }

    /**
     * Sets the id prev cons dett R mr.
     *
     * @param idPrevConsDettRMr the new id prev cons dett R mr
     */
    public void setIdPrevConsDettRmr(Long idPrevConsDettRmr) {
        this.idPrevConsDettRmr = idPrevConsDettRmr;
    }

}