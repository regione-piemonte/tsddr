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
 * The persistent class for the tsddr_d_tipi_provvedimenti database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_tipi_provvedimenti")
@NamedQuery(name = "TsddrDTipoProvvedimento.findAll", query = "SELECT t FROM TsddrDTipoProvvedimento t")
public class TsddrDTipoProvvedimento extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_provvedimento", unique = true, nullable = false)
	private Long idTipoProvvedimento;

	@Column(name = "desc_tipo_provvedimento", nullable = false, length = 100)
	private String descTipoProvvedimento;

	// bi-directional many-to-one association to TsddrTAtto
	@OneToMany(mappedBy = "tipoProvvedimento")
	private List<TsddrTAtto> atti;

	/**
	 * Instantiates a new tsddr D tipo provvedimento.
	 */
	public TsddrDTipoProvvedimento() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id tipo provvedimento.
	 *
	 * @return the id tipo provvedimento
	 */
	public Long getIdTipoProvvedimento() {
		return this.idTipoProvvedimento;
	}

	/**
	 * Sets the id tipo provvedimento.
	 *
	 * @param idTipoProvvedimento the new id tipo provvedimento
	 */
	public void setIdTipoProvvedimento(Long idTipoProvvedimento) {
		this.idTipoProvvedimento = idTipoProvvedimento;
	}

	/**
	 * Gets the desc tipo provvedimento.
	 *
	 * @return the desc tipo provvedimento
	 */
	public String getDescTipoProvvedimento() {
		return this.descTipoProvvedimento;
	}

	/**
	 * Sets the desc tipo provvedimento.
	 *
	 * @param descTipoProvvedimento the new desc tipo provvedimento
	 */
	public void setDescTipoProvvedimento(String descTipoProvvedimento) {
		this.descTipoProvvedimento = descTipoProvvedimento;
	}

	/**
	 * Gets the atti.
	 *
	 * @return the atti
	 */
	public List<TsddrTAtto> getAtti() {
		return this.atti;
	}

	/**
	 * Sets the atti.
	 *
	 * @param atti the new atti
	 */
	public void setAtti(List<TsddrTAtto> atti) {
		this.atti = atti;
	}

	/**
	 * Adds the atti.
	 *
	 * @param atti the atti
	 * @return the tsddr T atto
	 */
	public TsddrTAtto addAtti(TsddrTAtto atti) {
		getAtti().add(atti);
		atti.setTipoProvvedimento(this);

		return atti;
	}

	/**
	 * Removes the atti.
	 *
	 * @param atti the atti
	 * @return the tsddr T atto
	 */
	public TsddrTAtto removeAtti(TsddrTAtto atti) {
		getAtti().remove(atti);
		atti.setTipoProvvedimento(null);

		return atti;
	}

}