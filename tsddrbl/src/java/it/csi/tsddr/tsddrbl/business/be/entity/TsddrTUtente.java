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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_utenti database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_utenti")
@NamedQuery(name = "TsddrTUtente.findAll", query = "SELECT t FROM TsddrTUtente t")
public class TsddrTUtente extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utente", unique = true, nullable = false)
	private Long idUtente;

	// bi-directional many-to-one association to TsddrRUtenteGestoreProfilo
	@OneToMany(mappedBy = "utente")
	private List<TsddrRUtenteGestoreProfilo> rUtentiGestoriProfili;

	// bi-directional many-to-one association to TsddrRUtenteProf
	@OneToMany(mappedBy = "utente")
	private List<TsddrRUtenteProf> rUtentiProf;

	// bi-directional one-to-one association to TsddrTDatiSogg
	@OneToOne
	@JoinColumn(name = "id_dati_sogg")
	private TsddrTDatiSogg datiSogg;

	// bi-directional many-to-many association to TsddrDProfilo
	@ManyToMany(mappedBy = "utenti")
	private List<TsddrDProfilo> profili;

	/**
	 * Instantiates a new tsddr T utente.
	 */
	public TsddrTUtente() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
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
		rUtentiGestoriProfili.setUtente(this);

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
		rUtentiGestoriProfili.setUtente(null);

		return rUtentiGestoriProfili;
	}

	/**
	 * Gets the r utenti prof.
	 *
	 * @return the r utenti prof
	 */
	public List<TsddrRUtenteProf> getRUtentiProf() {
		return this.rUtentiProf;
	}

	/**
	 * Sets the r utenti prof.
	 *
	 * @param rUtentiProf the new r utenti prof
	 */
	public void setRUtentiProf(List<TsddrRUtenteProf> rUtentiProf) {
		this.rUtentiProf = rUtentiProf;
	}

	/**
	 * Adds the R utenti prof.
	 *
	 * @param rUtentiProf the r utenti prof
	 * @return the tsddr R utente prof
	 */
	public TsddrRUtenteProf addRUtentiProf(TsddrRUtenteProf rUtentiProf) {
		getRUtentiProf().add(rUtentiProf);
		rUtentiProf.setUtente(this);

		return rUtentiProf;
	}

	/**
	 * Removes the R utenti prof.
	 *
	 * @param rUtentiProf the r utenti prof
	 * @return the tsddr R utente prof
	 */
	public TsddrRUtenteProf removeRUtentiProf(TsddrRUtenteProf rUtentiProf) {
		getRUtentiProf().remove(rUtentiProf);
		rUtentiProf.setUtente(null);

		return rUtentiProf;
	}

	/**
	 * Gets the dati sogg.
	 *
	 * @return the dati sogg
	 */
	public TsddrTDatiSogg getDatiSogg() {
		return this.datiSogg;
	}

	/**
	 * Sets the dati sogg.
	 *
	 * @param datiSogg the new dati sogg
	 */
	public void setDatiSogg(TsddrTDatiSogg datiSogg) {
		this.datiSogg = datiSogg;
	}

	/**
	 * Gets the profili.
	 *
	 * @return the profili
	 */
	public List<TsddrDProfilo> getProfili() {
		return this.profili;
	}

	/**
	 * Sets the profili.
	 *
	 * @param profili the new profili
	 */
	public void setProfili(List<TsddrDProfilo> profili) {
		this.profili = profili;
	}

}
