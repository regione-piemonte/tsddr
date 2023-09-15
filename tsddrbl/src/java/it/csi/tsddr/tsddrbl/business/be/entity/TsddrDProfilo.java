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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_d_profili database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_profili")
@NamedQuery(name = "TsddrDProfilo.findAll", query = "SELECT t FROM TsddrDProfilo t")
public class TsddrDProfilo extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_profilo", unique = true, nullable = false)
	private Long idProfilo;

	@Column(name = "desc_profilo", length = 100)
	private String descProfilo;

	// bi-directional many-to-one association to TsddrDTipoProfilo
	@ManyToOne
	@JoinColumn(name = "id_tipo_profilo")
	private TsddrDTipoProfilo tipoProfilo;

	// bi-directional many-to-one association to TsddrRFunzProf
	@OneToMany(mappedBy = "profilo")
	private List<TsddrRFunzProf> rFunzProf;

	// bi-directional many-to-one association to TsddrRUtenteGestoreProfilo
	@OneToMany(mappedBy = "profilo")
	private List<TsddrRUtenteGestoreProfilo> rUtentiGestoriProfili;

	// bi-directional many-to-one association to TsddrRUtenteProf
	@OneToMany(mappedBy = "profilo")
	private List<TsddrRUtenteProf> rUtentiProf;

	// bi-directional many-to-many association to TsddrDFunzione
	@ManyToMany(mappedBy = "profili")
	private List<TsddrDFunzione> funzioni;

	// bi-directional many-to-many association to TsddrTUtente
	@ManyToMany
	@JoinTable(name = "tsddr_r_utenti_prof", joinColumns = {
			@JoinColumn(name = "id_profilo", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_utente", nullable = false) })
	private List<TsddrTUtente> utenti;

	/**
	 * Instantiates a new tsddr D profilo.
	 */
	public TsddrDProfilo() {
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
	 * Gets the desc profilo.
	 *
	 * @return the desc profilo
	 */
	public String getDescProfilo() {
		return this.descProfilo;
	}

	/**
	 * Sets the desc profilo.
	 *
	 * @param descProfilo the new desc profilo
	 */
	public void setDescProfilo(String descProfilo) {
		this.descProfilo = descProfilo;
	}

	/**
	 * Gets the tipo profilo.
	 *
	 * @return the tipo profilo
	 */
	public TsddrDTipoProfilo getTipoProfilo() {
		return tipoProfilo;
	}

	/**
	 * Sets the tipo profilo.
	 *
	 * @param tipoProfilo the new tipo profilo
	 */
	public void setTipoProfilo(TsddrDTipoProfilo tipoProfilo) {
		this.tipoProfilo = tipoProfilo;
	}

	/**
	 * Gets the r funz prof.
	 *
	 * @return the r funz prof
	 */
	public List<TsddrRFunzProf> getRFunzProf() {
		return this.rFunzProf;
	}

	/**
	 * Sets the r funz profs.
	 *
	 * @param rFunzProf the new r funz profs
	 */
	public void setRFunzProfs(List<TsddrRFunzProf> rFunzProf) {
		this.rFunzProf = rFunzProf;
	}

	/**
	 * Adds the R funz prof.
	 *
	 * @param rFunzProf the r funz prof
	 * @return the tsddr R funz prof
	 */
	public TsddrRFunzProf addRFunzProf(TsddrRFunzProf rFunzProf) {
		getRFunzProf().add(rFunzProf);
		rFunzProf.setProfilo(this);

		return rFunzProf;
	}

	/**
	 * Removes the R funz prof.
	 *
	 * @param rFunzProf the r funz prof
	 * @return the tsddr R funz prof
	 */
	public TsddrRFunzProf removeRFunzProf(TsddrRFunzProf rFunzProf) {
		getRFunzProf().remove(rFunzProf);
		rFunzProf.setProfilo(null);

		return rFunzProf;
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
		rUtentiGestoriProfili.setProfilo(this);

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
		rUtentiGestoriProfili.setProfilo(null);

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
		rUtentiProf.setProfilo(this);

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
		rUtentiProf.setProfilo(null);

		return rUtentiProf;
	}

	/**
	 * Gets the funzioni.
	 *
	 * @return the funzioni
	 */
	public List<TsddrDFunzione> getFunzioni() {
		return this.funzioni;
	}

	/**
	 * Sets the funzioni.
	 *
	 * @param funzioni the new funzioni
	 */
	public void setFunzioni(List<TsddrDFunzione> funzioni) {
		this.funzioni = funzioni;
	}

	/**
	 * Gets the utenti.
	 *
	 * @return the utenti
	 */
	public List<TsddrTUtente> getUtenti() {
		return this.utenti;
	}

	/**
	 * Sets the utenti.
	 *
	 * @param utenti the new utenti
	 */
	public void setUtenti(List<TsddrTUtente> utenti) {
		this.utenti = utenti;
	}

}