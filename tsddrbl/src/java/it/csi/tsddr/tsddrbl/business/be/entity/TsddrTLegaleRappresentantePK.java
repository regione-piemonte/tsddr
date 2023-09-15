/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.io.Serializable;

/**
 * The primary key class for the tsddr_t_legali_rappresentanti database table.
 * 
 */
public class TsddrTLegaleRappresentantePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	private Long idLegaleRapp;

	private Long idGestore;

	/**
	 * Instantiates a new tsddr T legale rappresentante PK.
	 */
	public TsddrTLegaleRappresentantePK() {
	}
	
	/**
	 * Instantiates a new tsddr T legale rappresentante PK.
	 *
	 * @param idLegaleRapp the id legale rapp
	 * @param idGestore the id gestore
	 */
	public TsddrTLegaleRappresentantePK(Long idLegaleRapp, Long idGestore) {
		this.idLegaleRapp = idLegaleRapp;
		this.idGestore = idGestore;
	}

	/**
	 * Gets the id gestore.
	 *
	 * @return the id gestore
	 */
	public Long getIdGestore() {
		return this.idGestore;
	}

	/**
	 * Sets the id gestore.
	 *
	 * @param idGestore the new id gestore
	 */
	public void setIdGestore(Long idGestore) {
		this.idGestore = idGestore;
	}

	/**
	 * Gets the id legale rapp.
	 *
	 * @return the id legale rapp
	 */
	public Long getIdLegaleRapp() {
		return this.idLegaleRapp;
	}

	/**
	 * Sets the id legale rapp.
	 *
	 * @param idLegaleRapp the new id legale rapp
	 */
	public void setIdLegaleRapp(Long idLegaleRapp) {
		this.idLegaleRapp = idLegaleRapp;
	}

	/**
	 * Equals.
	 *
	 * @param other the other
	 * @return true, if successful
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TsddrTLegaleRappresentantePK)) {
			return false;
		}
		TsddrTLegaleRappresentantePK castOther = (TsddrTLegaleRappresentantePK) other;
		return this.idGestore.equals(castOther.idGestore) && this.idLegaleRapp.equals(castOther.idLegaleRapp);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGestore.hashCode();
		hash = hash * prime + this.idLegaleRapp.hashCode();

		return hash;
	}
}