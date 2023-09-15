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
 * The persistent class for the tsddr_d_tipi_indirizzi database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_tipi_indirizzi")
@NamedQuery(name = "TsddrDTipoIndirizzo.findAll", query = "SELECT t FROM TsddrDTipoIndirizzo t")
public class TsddrDTipoIndirizzo extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_indirizzo", unique = true, nullable = false)
	private Long idTipoIndirizzo;

	@Column(name = "desc_tipo_indirizzo", nullable = false, length = 100)
	private String descTipoIndirizzo;

	// bi-directional many-to-one association to TsddrTIndirizzo
	@OneToMany(mappedBy = "tipoIndirizzo")
	private List<TsddrTIndirizzo> indirizzi;

	/**
	 * Instantiates a new tsddr D tipo indirizzo.
	 */
	public TsddrDTipoIndirizzo() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id tipo indirizzo.
	 *
	 * @return the id tipo indirizzo
	 */
	public Long getIdTipoIndirizzo() {
		return this.idTipoIndirizzo;
	}

	/**
	 * Sets the id tipo indirizzo.
	 *
	 * @param idTipoIndirizzo the new id tipo indirizzo
	 */
	public void setIdTipoIndirizzo(Long idTipoIndirizzo) {
		this.idTipoIndirizzo = idTipoIndirizzo;
	}

	/**
	 * Gets the desc tipo indirizzo.
	 *
	 * @return the desc tipo indirizzo
	 */
	public String getDescTipoIndirizzo() {
		return this.descTipoIndirizzo;
	}

	/**
	 * Sets the desc tipo indirizzo.
	 *
	 * @param descTipoIndirizzo the new desc tipo indirizzo
	 */
	public void setDescTipoIndirizzo(String descTipoIndirizzo) {
		this.descTipoIndirizzo = descTipoIndirizzo;
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
		indirizzi.setTipoIndirizzo(this);

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
		indirizzi.setTipoIndirizzo(null);

		return indirizzi;
	}

}