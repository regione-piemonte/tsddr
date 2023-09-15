/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

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
 * The persistent class for the tsddr_r_impianti_linee database table.
 * 
 */
@Entity
@Table(name = "tsddr_r_impianti_linee")
@NamedQuery(name = "TsddrRImpiantoLinea.findAll", query = "SELECT t FROM TsddrRImpiantoLinea t")
public class TsddrRImpiantoLinea extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_impianto_linea", unique = true, nullable = false)
	private Long idImpiantoLinea;

	// bi-directional many-to-one association to TsddrTImpianto
	@ManyToOne
	@JoinColumn(name = "id_impianto", nullable = false)
	private TsddrTImpianto impianto;

	// bi-directional many-to-one association to TsddrTLinea
	@ManyToOne
	@JoinColumn(name = "id_linea", nullable = true)
	private TsddrTLinea linea;

	// bi-directional many-to-one association to TsddrTSottoLinea
	@ManyToOne
	@JoinColumn(name = "id_sotto_linea", nullable = true)
	private TsddrTSottoLinea sottoLinea;
	
	// bi-directional many-to-one association to TsddrRPrevConsLinea
    @OneToMany(mappedBy = "impiantoLinea")
    private List<TsddrRPrevConsLinea> rPrevConsLinee;

	/**
	 * Instantiates a new tsddr R impianto linea.
	 */
	public TsddrRImpiantoLinea() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}
	
	/**
	 * Gets the id impianto linea.
	 *
	 * @return the id impianto linea
	 */
	public Long getIdImpiantoLinea() {
		return idImpiantoLinea;
	}
	
	/**
	 * Sets the id impianto linea.
	 *
	 * @param idImpiantoLinea the new id impianto linea
	 */
	public void setIdImpiantoLinea(Long idImpiantoLinea) {
		this.idImpiantoLinea = idImpiantoLinea;
	}

	/**
	 * Gets the impianto.
	 *
	 * @return the impianto
	 */
	public TsddrTImpianto getImpianto() {
		return this.impianto;
	}

	/**
	 * Sets the impianto.
	 *
	 * @param impianto the new impianto
	 */
	public void setImpianto(TsddrTImpianto impianto) {
		this.impianto = impianto;
	}

	/**
	 * Gets the linea.
	 *
	 * @return the linea
	 */
	public TsddrTLinea getLinea() {
		return this.linea;
	}

	/**
	 * Sets the linea.
	 *
	 * @param linea the new linea
	 */
	public void setLinea(TsddrTLinea linea) {
		this.linea = linea;
	}

	/**
	 * Gets the sotto linea.
	 *
	 * @return the sotto linea
	 */
	public TsddrTSottoLinea getSottoLinea() {
		return this.sottoLinea;
	}

	/**
	 * Sets the sotto linea.
	 *
	 * @param sottoLinea the new sotto linea
	 */
	public void setSottoLinea(TsddrTSottoLinea sottoLinea) {
		this.sottoLinea = sottoLinea;
	}

    /**
     * Gets the r prev cons linea.
     *
     * @return the r prev cons linea
     */
    public List<TsddrRPrevConsLinea> getrPrevConsLinee() {
        return rPrevConsLinee;
    }

    /**
     * Sets the r prev cons linea.
     *
     * @param rPrevConsLinee the new r prev cons linea
     */
    public void setrPrevConsLinee(List<TsddrRPrevConsLinea> rPrevConsLinee) {
        this.rPrevConsLinee = rPrevConsLinee;
    }
    
    /**
     * Adds the R prev cons linea.
     *
     * @param prevConsLinea the prev cons linea
     * @return the tsddr R prev cons linea
     */
    public TsddrRPrevConsLinea addRPrevConsLinea(TsddrRPrevConsLinea prevConsLinea) {
        getrPrevConsLinee().add(prevConsLinea);
        prevConsLinea.setImpiantoLinea(this);

        return prevConsLinea;
    }

    /**
     * Removes the R prev cons linea.
     *
     * @param prevConsLinea the prev cons linea
     * @return the tsddr R prev cons linea
     */
    public TsddrRPrevConsLinea removeRPrevConsLinea(TsddrRPrevConsLinea prevConsLinea) {
        getrPrevConsLinee().remove(prevConsLinea);
        prevConsLinea.setImpiantoLinea(null);

        return prevConsLinea;
    }
	
}