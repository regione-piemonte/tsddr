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
 * The persistent class for the tsddr_d_sezioni database table.
 * 
 */
@Entity
@Table(name="tsddr_d_sezioni")
@NamedQuery(name="TsddrDSezione.findAll", query="SELECT t FROM TsddrDSezione t")
public class TsddrDSezione extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sezione", unique=true, nullable=false)
	private Long idSezione;

	@Column(name="desc_sezione", nullable=false, length=500)
	private String descSezione;

	//bi-directional many-to-one association to TsddrTPrevConsDett
	@OneToMany(mappedBy="sezione")
	private List<TsddrTPrevConsDett> prevConsDett;

	/**
	 * Instantiates a new tsddr D sezione.
	 */
	public TsddrDSezione() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id sezione.
	 *
	 * @return the id sezione
	 */
	public Long getIdSezione() {
		return this.idSezione;
	}

	/**
	 * Sets the id sezione.
	 *
	 * @param idSezione the new id sezione
	 */
	public void setIdSezione(Long idSezione) {
		this.idSezione = idSezione;
	}

	/**
	 * Gets the desc sezione.
	 *
	 * @return the desc sezione
	 */
	public String getDescSezione() {
		return this.descSezione;
	}

	/**
	 * Sets the desc sezione.
	 *
	 * @param descSezione the new desc sezione
	 */
	public void setDescSezione(String descSezione) {
		this.descSezione = descSezione;
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
		prevConsDett.setSezione(this);

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
		prevConsDett.setSezione(null);

		return prevConsDett;
	}

}