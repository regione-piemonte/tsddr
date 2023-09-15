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
 * The persistent class for the tsddr_d_unita_misura database table.
 * 
 */
@Entity
@Table(name="tsddr_d_unita_misura")
@NamedQuery(name="TsddrDUnitaMisura.findAll", query="SELECT t FROM TsddrDUnitaMisura t")
public class TsddrDUnitaMisura extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_unita_misura", unique=true, nullable=false)
	private Long idUnitaMisura;

	@Column(name="desc_unita_misura", nullable=false, length=100)
	private String descUnitaMisura;

	//bi-directional many-to-one association to TsddrTConferimento
	@OneToMany(mappedBy="unitaMisura")
	private List<TsddrTConferimento> conferimenti;

	//bi-directional many-to-one association to TsddrTPrevConsDett
	@OneToMany(mappedBy="unitaMisura")
	private List<TsddrTPrevConsDett> prevConsDett;

	/**
	 * Instantiates a new tsddr D unita misura.
	 */
	public TsddrDUnitaMisura() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id unita misura.
	 *
	 * @return the id unita misura
	 */
	public Long getIdUnitaMisura() {
		return this.idUnitaMisura;
	}

	/**
	 * Sets the id unita misura.
	 *
	 * @param idUnitaMisura the new id unita misura
	 */
	public void setIdUnitaMisura(Long idUnitaMisura) {
		this.idUnitaMisura = idUnitaMisura;
	}

	/**
	 * Gets the desc unita misura.
	 *
	 * @return the desc unita misura
	 */
	public String getDescUnitaMisura() {
		return this.descUnitaMisura;
	}

	/**
	 * Sets the desc unita misura.
	 *
	 * @param descUnitaMisura the new desc unita misura
	 */
	public void setDescUnitaMisura(String descUnitaMisura) {
		this.descUnitaMisura = descUnitaMisura;
	}

	/**
	 * Gets the conferimenti.
	 *
	 * @return the conferimenti
	 */
	public List<TsddrTConferimento> getConferimenti() {
		return conferimenti;
	}

	/**
	 * Sets the conferimenti.
	 *
	 * @param conferimenti the new conferimenti
	 */
	public void setConferimenti(List<TsddrTConferimento> conferimenti) {
		this.conferimenti = conferimenti;
	}
	
	/**
	 * Adds the conferimento.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T conferimento
	 */
	public TsddrTConferimento addConferimento(TsddrTConferimento conferimento) {
		getConferimenti().add(conferimento);
		conferimento.setUnitaMisura(this);

		return conferimento;
	}

	/**
	 * Removes the conferimento.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T conferimento
	 */
	public TsddrTConferimento removeConferimento(TsddrTConferimento conferimento) {
		getConferimenti().remove(conferimento);
		conferimento.setUnitaMisura(null);

		return conferimento;
	}

	/**
	 * Gets the prev cons dett.
	 *
	 * @return the prev cons dett
	 */
	public List<TsddrTPrevConsDett> getPrevConsDett() {
		return prevConsDett;
	}

	/**
	 * Sets the prev cons dett.
	 *
	 * @param prevConsDett the new prev cons dett
	 */
	public void setPrevConsDett(List<TsddrTPrevConsDett> prevConsDett) {
		this.prevConsDett = prevConsDett;
	}
	
	/**
	 * Adds the prev cons dett.
	 *
	 * @param prevConsDett the prev cons dett
	 * @return the tsddr T prev cons dett
	 */
	public TsddrTPrevConsDett addPrevConsDett(TsddrTPrevConsDett prevConsDett) {
		getPrevConsDett().add(prevConsDett);
		prevConsDett.setUnitaMisura(this);

		return prevConsDett;
	}

	/**
	 * Removes the prev cons dett.
	 *
	 * @param prevConsDett the prev cons dett
	 * @return the tsddr T prev cons dett
	 */
	public TsddrTPrevConsDett removePrevConsDett(TsddrTPrevConsDett prevConsDett) {
		getPrevConsDett().remove(prevConsDett);
		prevConsDett.setUnitaMisura(null);

		return prevConsDett;
	}

}