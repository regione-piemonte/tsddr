/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tsddr_r_funz_prof database table.
 * 
 */
@Embeddable
public class TsddrRFunzProfPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "id_funzione", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idFunzione;

	@Column(name = "id_profilo", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idProfilo;

	/**
	 * Instantiates a new tsddr R funz prof PK.
	 */
	public TsddrRFunzProfPK() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id funzione.
	 *
	 * @return the id funzione
	 */
	public Long getIdFunzione() {
		return this.idFunzione;
	}

	/**
	 * Sets the id funzione.
	 *
	 * @param idFunzione the new id funzione
	 */
	public void setIdFunzione(Long idFunzione) {
		this.idFunzione = idFunzione;
	}

	/**
	 * Gets the id profilo.
	 *
	 * @return the id profilo
	 */
	public Long getIdProfilo() {
		return this.idProfilo;
	}

	/**
	 * Sets the id profilo.
	 *
	 * @param idProfilo the new id profilo
	 */
	public void setIdProfilo(Long idProfilo) {
		this.idProfilo = idProfilo;
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
		if (!(other instanceof TsddrRFunzProfPK)) {
			return false;
		}
		TsddrRFunzProfPK castOther = (TsddrRFunzProfPK) other;
		return this.idFunzione.equals(castOther.idFunzione) && this.idProfilo.equals(castOther.idProfilo);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idFunzione.hashCode();
		hash = hash * prime + this.idProfilo.hashCode();

		return hash;
	}
}