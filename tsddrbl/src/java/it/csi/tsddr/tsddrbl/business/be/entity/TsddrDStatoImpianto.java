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
 * The persistent class for the tsddr_d_stati_impianti database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_stati_impianti")
@NamedQuery(name = "TsddrDStatoImpianto.findAll", query = "SELECT t FROM TsddrDStatoImpianto t")
public class TsddrDStatoImpianto extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stato_impianto", unique = true, nullable = false)
	private Long idStatoImpianto;

	@Column(name = "desc_stato_impianto", nullable = false, length = 100)
	private String descStatoImpianto;

	// bi-directional many-to-one association to TsddrTImpianto
	@OneToMany(mappedBy = "statoImpianto")
	private List<TsddrTImpianto> impianti;

	/**
	 * Instantiates a new tsddr D stato impianto.
	 */
	public TsddrDStatoImpianto() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id stato impianto.
	 *
	 * @return the id stato impianto
	 */
	public Long getIdStatoImpianto() {
		return this.idStatoImpianto;
	}

	/**
	 * Sets the id stato impianto.
	 *
	 * @param idStatoImpianto the new id stato impianto
	 */
	public void setIdStatoImpianto(Long idStatoImpianto) {
		this.idStatoImpianto = idStatoImpianto;
	}

	/**
	 * Gets the desc stato impianto.
	 *
	 * @return the desc stato impianto
	 */
	public String getDescStatoImpianto() {
		return this.descStatoImpianto;
	}

	/**
	 * Sets the desc stato impianto.
	 *
	 * @param descStatoImpianto the new desc stato impianto
	 */
	public void setDescStatoImpianto(String descStatoImpianto) {
		this.descStatoImpianto = descStatoImpianto;
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
		impianti.setStatoImpianto(this);

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
		impianti.setStatoImpianto(null);

		return impianti;
	}

}