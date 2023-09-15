/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_r_utenti_gestori_profili database table.
 * 
 */
@Entity
@Table(name = "tsddr_r_utenti_gestori_profili")
@NamedQuery(name = "TsddrRUtenteGestoreProfilo.findAll", query = "SELECT t FROM TsddrRUtenteGestoreProfilo t")
public class TsddrRUtenteGestoreProfilo extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TsddrRUtenteGestoreProfiloPK id;

	// bi-directional many-to-one association to TsddrDProfilo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profilo", nullable = false, insertable = false, updatable = false)
	private TsddrDProfilo profilo;

	// bi-directional many-to-one association to TsddrRUtenteProf
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "id_profilo", referencedColumnName = "id_profilo", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "id_utente", referencedColumnName = "id_utente", nullable = false, insertable = false, updatable = false) })
	private TsddrRUtenteProf rUtenteProf;

	// bi-directional many-to-one association to TsddrTGestore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gestore", nullable = false, insertable = false, updatable = false)
	private TsddrTGestore gestore;

	// bi-directional many-to-one association to TsddrTUtente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente", nullable = false, insertable = false, updatable = false)
	private TsddrTUtente utente;

	/**
	 * Instantiates a new tsddr R utente gestore profilo.
	 */
	public TsddrRUtenteGestoreProfilo() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public TsddrRUtenteGestoreProfiloPK getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(TsddrRUtenteGestoreProfiloPK id) {
		this.id = id;
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

	/**
	 * Gets the r utente prof.
	 *
	 * @return the r utente prof
	 */
	public TsddrRUtenteProf getRUtenteProf() {
		return this.rUtenteProf;
	}

	/**
	 * Sets the r utente prof.
	 *
	 * @param rUtenteProf the new r utente prof
	 */
	public void setRUtenteProf(TsddrRUtenteProf rUtenteProf) {
		this.rUtenteProf = rUtenteProf;
	}

	/**
	 * Gets the gestore.
	 *
	 * @return the gestore
	 */
	public TsddrTGestore getGestore() {
		return this.gestore;
	}

	/**
	 * Sets the gestore.
	 *
	 * @param gestore the new gestore
	 */
	public void setGestore(TsddrTGestore gestore) {
		this.gestore = gestore;
	}

	/**
	 * Gets the utente.
	 *
	 * @return the utente
	 */
	public TsddrTUtente getUtente() {
		return this.utente;
	}

	/**
	 * Sets the utente.
	 *
	 * @param utente the new utente
	 */
	public void setUtente(TsddrTUtente utente) {
		this.utente = utente;
	}

}