/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_r_funz_prof database table.
 * 
 */
@Entity
@Table(name = "tsddr_r_funz_prof")
@NamedQuery(name = "TsddrRFunzProf.findAll", query = "SELECT t FROM TsddrRFunzProf t")
public class TsddrRFunzProf extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TsddrRFunzProfPK id;

	@Column(name="delete", nullable = false)
	private Boolean isDelete;

	@Column(name="insert", nullable = false)
	private Boolean isInsert;

	@Column(name="read", nullable = false)
	private Boolean isRead;

	@Column(name="update", nullable = false)
	private Boolean isUpdate;

	// bi-directional many-to-one association to TsddrDFunzione
	@ManyToOne
	@JoinColumn(name = "id_funzione", nullable = false, insertable = false, updatable = false)
	private TsddrDFunzione funzione;

	// bi-directional many-to-one association to TsddrDProfilo
	@ManyToOne
	@JoinColumn(name = "id_profilo", nullable = false, insertable = false, updatable = false)
	private TsddrDProfilo profilo;

	/**
	 * Instantiates a new tsddr R funz prof.
	 */
	public TsddrRFunzProf() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public TsddrRFunzProfPK getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(TsddrRFunzProfPK id) {
		this.id = id;
	}

	/**
	 * Gets the checks if is delete.
	 *
	 * @return the checks if is delete
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}

	/**
	 * Sets the checks if is delete.
	 *
	 * @param isDelete the new checks if is delete
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * Gets the checks if is insert.
	 *
	 * @return the checks if is insert
	 */
	public Boolean getIsInsert() {
		return this.isInsert;
	}

	/**
	 * Sets the checks if is insert.
	 *
	 * @param isInsert the new checks if is insert
	 */
	public void setIsInsert(Boolean isInsert) {
		this.isInsert = isInsert;
	}

	/**
	 * Gets the checks if is read.
	 *
	 * @return the checks if is read
	 */
	public Boolean getIsRead() {
		return this.isRead;
	}

	/**
	 * Sets the checks if is read.
	 *
	 * @param isRead the new checks if is read
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * Gets the checks if is update.
	 *
	 * @return the checks if is update
	 */
	public Boolean getIsUpdate() {
		return this.isUpdate;
	}

	/**
	 * Sets the checks if is update.
	 *
	 * @param isUpdate the new checks if is update
	 */
	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	/**
	 * Gets the funzione.
	 *
	 * @return the funzione
	 */
	public TsddrDFunzione getFunzione() {
		return this.funzione;
	}

	/**
	 * Sets the funzione.
	 *
	 * @param funzione the new funzione
	 */
	public void setFunzione(TsddrDFunzione funzione) {
		this.funzione = funzione;
	}

	/**
	 * Gets the profilo.
	 *
	 * @return the profilo
	 */
	public TsddrDProfilo getProfilo() {
		return this.profilo;
	}

	/**
	 * Sets the profilo.
	 *
	 * @param profilo the new profilo
	 */
	public void setProfilo(TsddrDProfilo profilo) {
		this.profilo = profilo;
	}

}