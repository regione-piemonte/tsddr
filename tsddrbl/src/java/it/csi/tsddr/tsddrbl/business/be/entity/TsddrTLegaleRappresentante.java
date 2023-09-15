/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_legali_rappresentanti database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_legali_rappresentanti")
@NamedQuery(name = "TsddrTLegaleRappresentante.findAll", query = "SELECT t FROM TsddrTLegaleRappresentante t")
@IdClass(TsddrTLegaleRappresentantePK.class)
public class TsddrTLegaleRappresentante extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "legaliRappSeq", sequenceName = "tsddr_t_legali_rappresentanti_id_legale_rapp_seq", allocationSize = 1)
	@GeneratedValue(generator = "legaliRappSeq")
	@Column(name = "id_legale_rapp", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idLegaleRapp;
	
	@Id
	@Column(name = "id_gestore", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idGestore;

	@Column(length = 50)
	private String qualifica;

	// bi-directional many-to-one association to TsddrTDatiSogg
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dati_sogg")
	private TsddrTDatiSogg datiSogg;

	// bi-directional many-to-one association to TsddrTGestore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gestore", nullable = false, insertable = false, updatable = false)
	private TsddrTGestore gestore;

	/**
	 * Instantiates a new tsddr T legale rappresentante.
	 */
	public TsddrTLegaleRappresentante() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id legale rapp.
	 *
	 * @return the id legale rapp
	 */
	public Long getIdLegaleRapp() {
		return idLegaleRapp;
	}

	/**
	 * Sets the id legale rapp.
	 *
	 * @param idLegaleRapp the new id legale rapp
	 */
	public void setIdLegaleRapp(Long idLegaleRapp) {
		this.idLegaleRapp = idLegaleRapp;
	}

	/**
	 * Gets the id gestore.
	 *
	 * @return the id gestore
	 */
	public Long getIdGestore() {
		return idGestore;
	}

	/**
	 * Sets the id gestore.
	 *
	 * @param idGestore the new id gestore
	 */
	public void setIdGestore(Long idGestore) {
		this.idGestore = idGestore;
	}

	/**
	 * Gets the qualifica.
	 *
	 * @return the qualifica
	 */
	public String getQualifica() {
		return this.qualifica;
	}

	/**
	 * Sets the qualifica.
	 *
	 * @param qualifica the new qualifica
	 */
	public void setQualifica(String qualifica) {
		this.qualifica = qualifica;
	}

	/**
	 * Gets the dati sogg.
	 *
	 * @return the dati sogg
	 */
	public TsddrTDatiSogg getDatiSogg() {
		return this.datiSogg;
	}

	/**
	 * Sets the dati sogg.
	 *
	 * @param datiSogg the new dati sogg
	 */
	public void setDatiSogg(TsddrTDatiSogg datiSogg) {
		this.datiSogg = datiSogg;
	}

	/**
	 * Gets the gestore.
	 *
	 * @return the gestore
	 */
	public TsddrTGestore getGestore() {
		return this.gestore;
	}

	/**
	 * Sets the gestore.
	 *
	 * @param gestore the new gestore
	 */
	public void setGestore(TsddrTGestore gestore) {
		this.gestore = gestore;
	}

}