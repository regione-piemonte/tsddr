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
 * The persistent class for the tsddr_d_tipi_impianti database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_tipi_impianti")
@NamedQuery(name = "TsddrDTipoImpianto.findAll", query = "SELECT t FROM TsddrDTipoImpianto t")
public class TsddrDTipoImpianto extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_impianto", unique = true, nullable = false)
	private Long idTipoImpianto;

	@Column(name = "desc_tipo_impianto", nullable = false, length = 100)
	private String descTipoImpianto;

	// bi-directional many-to-one association to TsddrTImpianto
	@OneToMany(mappedBy = "tipoImpianto")
	private List<TsddrTImpianto> impianti;

	/**
	 * Instantiates a new tsddr D tipo impianto.
	 */
	public TsddrDTipoImpianto() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id tipo impianto.
	 *
	 * @return the id tipo impianto
	 */
	public Long getIdTipoImpianto() {
		return this.idTipoImpianto;
	}

	/**
	 * Sets the id tipo impianto.
	 *
	 * @param idTipoImpianto the new id tipo impianto
	 */
	public void setIdTipoImpianto(Long idTipoImpianto) {
		this.idTipoImpianto = idTipoImpianto;
	}

	/**
	 * Gets the desc tipo impianto.
	 *
	 * @return the desc tipo impianto
	 */
	public String getDescTipoImpianto() {
		return this.descTipoImpianto;
	}

	/**
	 * Sets the desc tipo impianto.
	 *
	 * @param descTipoImpianto the new desc tipo impianto
	 */
	public void setDescTipoImpianto(String descTipoImpianto) {
		this.descTipoImpianto = descTipoImpianto;
	}

	/**
	 * Gets the impianti.
	 *
	 * @return the impianti
	 */
	public List<TsddrTImpianto> getImpianti() {
		return this.impianti;
	}

	/**
	 * Sets the impianti.
	 *
	 * @param impianti the new impianti
	 */
	public void setImpianti(List<TsddrTImpianto> impianti) {
		this.impianti = impianti;
	}

	/**
	 * Adds the impianti.
	 *
	 * @param impianti the impianti
	 * @return the tsddr T impianto
	 */
	public TsddrTImpianto addImpianti(TsddrTImpianto impianti) {
		getImpianti().add(impianti);
		impianti.setTipoImpianto(this);

		return impianti;
	}

	/**
	 * Removes the impianti.
	 *
	 * @param impianti the impianti
	 * @return the tsddr T impianto
	 */
	public TsddrTImpianto removeImpianti(TsddrTImpianto impianti) {
		getImpianti().remove(impianti);
		impianti.setTipoImpianto(null);

		return impianti;
	}

}