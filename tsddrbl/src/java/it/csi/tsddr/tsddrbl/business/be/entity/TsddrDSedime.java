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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_d_sedime database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_sedime")
@NamedQuery(name = "TsddrDSedime.findAll", query = "SELECT t FROM TsddrDSedime t")
public class TsddrDSedime extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sedime", unique = true, nullable = false)
	private Long idSedime;

	@Column(name = "desc_sedime", nullable = false, length = 100)
	private String descSedime;

	// bi-directional many-to-one association to TsddrTIndirizzo
	@OneToMany(mappedBy = "sedime")
	private List<TsddrTIndirizzo> indirizzi;

	/**
	 * Instantiates a new tsddr D sedime.
	 */
	public TsddrDSedime() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id sedime.
	 *
	 * @return the id sedime
	 */
	public Long getIdSedime() {
		return this.idSedime;
	}

	/**
	 * Sets the id sedime.
	 *
	 * @param idSedime the new id sedime
	 */
	public void setIdSedime(Long idSedime) {
		this.idSedime = idSedime;
	}

	/**
	 * Gets the desc sedime.
	 *
	 * @return the desc sedime
	 */
	public String getDescSedime() {
		return this.descSedime;
	}

	/**
	 * Sets the desc sedime.
	 *
	 * @param descSedime the new desc sedime
	 */
	public void setDescSedime(String descSedime) {
		this.descSedime = descSedime;
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
		indirizzi.setSedime(this);

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
		indirizzi.setSedime(null);

		return indirizzi;
	}

}