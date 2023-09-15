/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tsddr_r_utenti_prof database table.
 * 
 */
@Embeddable
public class TsddrRUtenteProfPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "id_profilo", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idProfilo;

	@Column(name = "id_utente", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idUtente;

	/**
	 * Instantiates a new tsddr R utente prof PK.
	 */
	public TsddrRUtenteProfPK() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
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
	 * Gets the id utente.
	 *
	 * @return the id utente
	 */
	public Long getIdUtente() {
		return this.idUtente;
	}

	/**
	 * Sets the id utente.
	 *
	 * @param idUtente the new id utente
	 */
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
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
		if (!(other instanceof TsddrRUtenteProfPK)) {
			return false;
		}
		TsddrRUtenteProfPK castOther = (TsddrRUtenteProfPK) other;
		return this.idProfilo.equals(castOther.idProfilo) && this.idUtente.equals(castOther.idUtente);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idProfilo.hashCode();
		hash = hash * prime + this.idUtente.hashCode();

		return hash;
	}
}