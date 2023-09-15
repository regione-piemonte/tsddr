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
 * The persistent class for the tsddr_d_eer database table.
 * 
 */
@Entity
@Table(name="tsddr_d_eer")
@NamedQuery(name="TsddrDEer.findAll", query="SELECT t FROM TsddrDEer t")
public class TsddrDEer extends AbstractValidableEntity {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_eer", unique=true, nullable=false)
	private Long idEer;

	@Column(name="codice_eer", nullable=false, length=20)
	private String codiceEer;

	@Column(nullable=false, length=500)
	private String descrizione;

	//bi-directional many-to-one association to TsddrTPrevConsDett
	@OneToMany(mappedBy="eer")
	private List<TsddrTPrevConsDett> prevConsDett;

	/**
	 * Instantiates a new tsddr D eer.
	 */
	public TsddrDEer() {
	 // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id eer.
	 *
	 * @return the id eer
	 */
	public Long getIdEer() {
		return this.idEer;
	}

	/**
	 * Sets the id eer.
	 *
	 * @param idEer the new id eer
	 */
	public void setIdEer(Long idEer) {
		this.idEer = idEer;
	}

	/**
	 * Gets the codice eer.
	 *
	 * @return the codice eer
	 */
	public String getCodiceEer() {
		return this.codiceEer;
	}

	/**
	 * Sets the codice eer.
	 *
	 * @param codiceEer the new codice eer
	 */
	public void setCodiceEer(String codiceEer) {
		this.codiceEer = codiceEer;
	}

	/**
	 * Gets the descrizione.
	 *
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * Sets the descrizione.
	 *
	 * @param descrizione the new descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
		prevConsDett.setEer(this);

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
		prevConsDett.setEer(null);

		return prevConsDett;
	}
}