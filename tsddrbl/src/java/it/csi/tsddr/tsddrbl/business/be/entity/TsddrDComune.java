/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_d_comuni database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_comuni")
@NamedQuery(name = "TsddrDComune.findAll", query = "SELECT t FROM TsddrDComune t")
public class TsddrDComune extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comune", unique = true, nullable = false)
	private Long idComune;

	@Column(nullable = false, length = 5)
	private String cap;

	@Column(name = "cod_catasto", nullable = false, length = 255)
	private String codCatasto;

	@Column(nullable = false, length = 100)
	private String comune;

	@Column(name = "id_comune_istat", nullable = false)
	private Long idComuneIstat;

	// bi-directional many-to-one association to TsddrDProvincia
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_provincia")
	private TsddrDProvincia provincia;

	// bi-directional many-to-one association to TsddrTIndirizzo
	@OneToMany(mappedBy = "comune")
	private List<TsddrTIndirizzo> indirizzi;

	/**
	 * Instantiates a new tsddr D comune.
	 */
	public TsddrDComune() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id comune.
	 *
	 * @return the id comune
	 */
	public Long getIdComune() {
		return this.idComune;
	}

	/**
	 * Sets the id comune.
	 *
	 * @param idComune the new id comune
	 */
	public void setIdComune(Long idComune) {
		this.idComune = idComune;
	}

	/**
	 * Gets the cap.
	 *
	 * @return the cap
	 */
	public String getCap() {
		return this.cap;
	}

	/**
	 * Sets the cap.
	 *
	 * @param cap the new cap
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Gets the cod catasto.
	 *
	 * @return the cod catasto
	 */
	public String getCodCatasto() {
		return this.codCatasto;
	}

	/**
	 * Sets the cod catasto.
	 *
	 * @param codCatasto the new cod catasto
	 */
	public void setCodCatasto(String codCatasto) {
		this.codCatasto = codCatasto;
	}

	/**
	 * Gets the comune.
	 *
	 * @return the comune
	 */
	public String getComune() {
		return this.comune;
	}

	/**
	 * Sets the comune.
	 *
	 * @param comune the new comune
	 */
	public void setComune(String comune) {
		this.comune = comune;
	}

	/**
	 * Gets the id comune istat.
	 *
	 * @return the id comune istat
	 */
	public Long getIdComuneIstat() {
		return this.idComuneIstat;
	}

	/**
	 * Sets the id comune istat.
	 *
	 * @param idComuneIstat the new id comune istat
	 */
	public void setIdComuneIstat(Long idComuneIstat) {
		this.idComuneIstat = idComuneIstat;
	}

	/**
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public TsddrDProvincia getProvincia() {
		return this.provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia the new provincia
	 */
	public void setProvincia(TsddrDProvincia provincia) {
		this.provincia = provincia;
	}

	/**
	 * Gets the indirizzi.
	 *
	 * @return the indirizzi
	 */
	public List<TsddrTIndirizzo> getIndirizzi() {
		return this.indirizzi;
	}

	/**
	 * Sets the indirizzi.
	 *
	 * @param indirizzi the new indirizzi
	 */
	public void setIndirizzi(List<TsddrTIndirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}

	/**
	 * Adds the indirizzi.
	 *
	 * @param indirizzi the indirizzi
	 * @return the tsddr T indirizzo
	 */
	public TsddrTIndirizzo addIndirizzi(TsddrTIndirizzo indirizzi) {
		getIndirizzi().add(indirizzi);
		indirizzi.setComune(this);

		return indirizzi;
	}

	/**
	 * Removes the indirizzi.
	 *
	 * @param indirizzi the indirizzi
	 * @return the tsddr T indirizzo
	 */
	public TsddrTIndirizzo removeIndirizzi(TsddrTIndirizzo indirizzi) {
		getIndirizzi().remove(indirizzi);
		indirizzi.setComune(null);

		return indirizzi;
	}

}