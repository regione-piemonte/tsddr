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
 * The persistent class for the tsddr_d_tipi_profili database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_tipi_profili")
@NamedQuery(name = "TsddrDTipoProfilo.findAll", query = "SELECT t FROM TsddrDTipoProfilo t")
public class TsddrDTipoProfilo extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_profilo", unique = true, nullable = false)
	private Long idTipoProfilo;

	@Column(name = "desc_tipo_profilo", nullable = false, length = 100)
	private String descTipoProfilo;

	// bi-directional many-to-one association to TsddrDProfilo
	@OneToMany(mappedBy = "tipoProfilo")
	private List<TsddrDProfilo> profili;

	/**
	 * Instantiates a new tsddr D tipo profilo.
	 */
	public TsddrDTipoProfilo() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id tipo profilo.
	 *
	 * @return the id tipo profilo
	 */
	public Long getIdTipoProfilo() {
		return this.idTipoProfilo;
	}

	/**
	 * Sets the id tipo profilo.
	 *
	 * @param idTipoProfilo the new id tipo profilo
	 */
	public void setIdTipoProfilo(Long idTipoProfilo) {
		this.idTipoProfilo = idTipoProfilo;
	}

	/**
	 * Gets the desc tipo profilo.
	 *
	 * @return the desc tipo profilo
	 */
	public String getDescTipoProfilo() {
		return this.descTipoProfilo;
	}

	/**
	 * Sets the desc tipo profilo.
	 *
	 * @param descTipoProfilo the new desc tipo profilo
	 */
	public void setDescTipoProfilo(String descTipoProfilo) {
		this.descTipoProfilo = descTipoProfilo;
	}

	/**
	 * Gets the profili.
	 *
	 * @return the profili
	 */
	public List<TsddrDProfilo> getProfili() {
		return this.profili;
	}

	/**
	 * Sets the profili.
	 *
	 * @param profili the new profili
	 */
	public void setProfili(List<TsddrDProfilo> profili) {
		this.profili = profili;
	}

	/**
	 * Adds the profili.
	 *
	 * @param profili the profili
	 * @return the tsddr D profilo
	 */
	public TsddrDProfilo addProfili(TsddrDProfilo profili) {
		getProfili().add(profili);
		profili.setTipoProfilo(this);

		return profili;
	}

	/**
	 * Removes the profili.
	 *
	 * @param profili the profili
	 * @return the tsddr D profilo
	 */
	public TsddrDProfilo removeProfili(TsddrDProfilo profili) {
		getProfili().remove(profili);
		profili.setTipoProfilo(null);

		return profili;
	}

}