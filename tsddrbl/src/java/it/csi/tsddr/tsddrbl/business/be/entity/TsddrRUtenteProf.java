/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_r_utenti_prof database table.
 * 
 */
@Entity
@Table(name = "tsddr_r_utenti_prof")
@NamedQuery(name = "TsddrRUtenteProf.findAll", query = "SELECT t FROM TsddrRUtenteProf t")
public class TsddrRUtenteProf extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TsddrRUtenteProfPK id;

	// bi-directional many-to-one association to TsddrRUtenteGestoreProfilo
	@OneToMany(mappedBy = "rUtenteProf")
	private List<TsddrRUtenteGestoreProfilo> rUtentiGestoriProfili;

	// bi-directional many-to-one association to TsddrDProfilo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profilo", nullable = false, insertable = false, updatable = false)
	private TsddrDProfilo profilo;

	// bi-directional many-to-one association to TsddrTUtente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente", nullable = false, insertable = false, updatable = false)
	private TsddrTUtente utente;

	// bi-directional many-to-many association to TsddrTGestore
	@ManyToMany
	@JoinTable(name = "tsddr_r_utenti_gestori_profili", joinColumns = {
			@JoinColumn(name = "id_profilo", referencedColumnName = "id_profilo", nullable = false),
			@JoinColumn(name = "id_utente", referencedColumnName = "id_utente", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_gestore", nullable = false) })
	private List<TsddrTGestore> gestori;

	/**
	 * Instantiates a new tsddr R utente prof.
	 */
	public TsddrRUtenteProf() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public TsddrRUtenteProfPK getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(TsddrRUtenteProfPK id) {
		this.id = id;
	}

	/**
	 * Gets the r utenti gestori profili.
	 *
	 * @return the r utenti gestori profili
	 */
	public List<TsddrRUtenteGestoreProfilo> getRUtentiGestoriProfili() {
		return this.rUtentiGestoriProfili;
	}

	/**
	 * Sets the r utenti gestori profili.
	 *
	 * @param rUtentiGestoriProfili the new r utenti gestori profili
	 */
	public void setRUtentiGestoriProfili(List<TsddrRUtenteGestoreProfilo> rUtentiGestoriProfili) {
		this.rUtentiGestoriProfili = rUtentiGestoriProfili;
	}

	/**
	 * Adds the R utenti gestori profili.
	 *
	 * @param rUtentiGestoriProfili the r utenti gestori profili
	 * @return the tsddr R utente gestore profilo
	 */
	public TsddrRUtenteGestoreProfilo addRUtentiGestoriProfili(TsddrRUtenteGestoreProfilo rUtentiGestoriProfili) {
		getRUtentiGestoriProfili().add(rUtentiGestoriProfili);
		rUtentiGestoriProfili.setRUtenteProf(this);

		return rUtentiGestoriProfili;
	}

	/**
	 * Removes the R utenti gestori profili.
	 *
	 * @param rUtentiGestoriProfili the r utenti gestori profili
	 * @return the tsddr R utente gestore profilo
	 */
	public TsddrRUtenteGestoreProfilo removeRUtentiGestoriProfili(TsddrRUtenteGestoreProfilo rUtentiGestoriProfili) {
		getRUtentiGestoriProfili().remove(rUtentiGestoriProfili);
		rUtentiGestoriProfili.setRUtenteProf(null);

		return rUtentiGestoriProfili;
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

	/**
	 * Gets the gestori.
	 *
	 * @return the gestori
	 */
	public List<TsddrTGestore> getGestori() {
		return this.gestori;
	}

	/**
	 * Sets the gestori.
	 *
	 * @param gestori the new gestori
	 */
	public void setGestori(List<TsddrTGestore> gestori) {
		this.gestori = gestori;
	}

}