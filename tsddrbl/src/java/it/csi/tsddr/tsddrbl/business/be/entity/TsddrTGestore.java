/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_gestori database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_gestori")
@NamedQuery(name = "TsddrTGestore.findAll", query = "SELECT t FROM TsddrTGestore t")
public class TsddrTGestore extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gestore", unique = true, nullable = false)
	private Long idGestore;

	@Column(name = "cod_fisc_partiva", nullable = false, length = 16)
	private String codFiscPartiva;

	@Column(length = 100)
	private String email;

	@Column(name = "id_iscrizione_albo", length = 50)
	private String idIscrizioneAlbo;

	@Column(length = 100)
	private String pec;

	@Column(name = "rag_sociale", nullable = false, length = 200)
	private String ragSociale;

	@Column(length = 50)
	private String telefono;

	// bi-directional many-to-one association to TsddrRUtenteGestoreProfilo
	@OneToMany(mappedBy = "gestore")
	private List<TsddrRUtenteGestoreProfilo> rUtentiGestoriProfili;

	// bi-directional many-to-one association to TsddrTDomanda
	@OneToMany(mappedBy = "gestore")
	private List<TsddrTDomanda> domande;

	// bi-directional many-to-one association to TsddrDNaturaGiuridica
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_natura_giuridica")
	private TsddrDNaturaGiuridica naturaGiuridica;

	// bi-directional many-to-one association to TsddrTImpianto
	@OneToMany(mappedBy = "gestore")
	private List<TsddrTImpianto> impianti;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_sede_legale")
	private TsddrTIndirizzo sedeLegale;

	// bi-directional many-to-one association to TsddrTLegaleRappresentante
	@OneToMany(mappedBy = "gestore")
	private List<TsddrTLegaleRappresentante> legaliRappresentanti;

	// bi-directional many-to-many association to TsddrRUtenteProf
	@ManyToMany(mappedBy = "gestori")
	private List<TsddrRUtenteProf> rUtentiProf;

	/**
	 * Instantiates a new tsddr T gestore.
	 */
	public TsddrTGestore() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
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
	 * Gets the cod fisc partiva.
	 *
	 * @return the cod fisc partiva
	 */
	public String getCodFiscPartiva() {
		return this.codFiscPartiva;
	}

	/**
	 * Sets the cod fisc partiva.
	 *
	 * @param codFiscPartiva the new cod fisc partiva
	 */
	public void setCodFiscPartiva(String codFiscPartiva) {
		this.codFiscPartiva = codFiscPartiva;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the id iscrizione albo.
	 *
	 * @return the id iscrizione albo
	 */
	public String getIdIscrizioneAlbo() {
		return this.idIscrizioneAlbo;
	}

	/**
	 * Sets the id iscrizione albo.
	 *
	 * @param idIscrizioneAlbo the new id iscrizione albo
	 */
	public void setIdIscrizioneAlbo(String idIscrizioneAlbo) {
		this.idIscrizioneAlbo = idIscrizioneAlbo;
	}

	/**
	 * Gets the pec.
	 *
	 * @return the pec
	 */
	public String getPec() {
		return this.pec;
	}

	/**
	 * Sets the pec.
	 *
	 * @param pec the new pec
	 */
	public void setPec(String pec) {
		this.pec = pec;
	}

	/**
	 * Gets the rag sociale.
	 *
	 * @return the rag sociale
	 */
	public String getRagSociale() {
		return this.ragSociale;
	}

	/**
	 * Sets the rag sociale.
	 *
	 * @param ragSociale the new rag sociale
	 */
	public void setRagSociale(String ragSociale) {
		this.ragSociale = ragSociale;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono the new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		rUtentiGestoriProfili.setGestore(this);

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
		rUtentiGestoriProfili.setGestore(null);

		return rUtentiGestoriProfili;
	}

	/**
	 * Gets the domande.
	 *
	 * @return the domande
	 */
	public List<TsddrTDomanda> getDomande() {
		return this.domande;
	}

	/**
	 * Sets the domande.
	 *
	 * @param domande the new domande
	 */
	public void setDomande(List<TsddrTDomanda> domande) {
		this.domande = domande;
	}

	/**
	 * Adds the domande.
	 *
	 * @param domande the domande
	 * @return the tsddr T domanda
	 */
	public TsddrTDomanda addDomande(TsddrTDomanda domande) {
		getDomande().add(domande);
		domande.setGestore(this);

		return domande;
	}

	/**
	 * Removes the domande.
	 *
	 * @param domande the domande
	 * @return the tsddr T domanda
	 */
	public TsddrTDomanda removeDomande(TsddrTDomanda domande) {
		getDomande().remove(domande);
		domande.setGestore(null);

		return domande;
	}

	/**
	 * Gets the natura giuridica.
	 *
	 * @return the natura giuridica
	 */
	public TsddrDNaturaGiuridica getNaturaGiuridica() {
		return this.naturaGiuridica;
	}

	/**
	 * Sets the natura giuridica.
	 *
	 * @param naturaGiuridica the new natura giuridica
	 */
	public void setNaturaGiuridica(TsddrDNaturaGiuridica naturaGiuridica) {
		this.naturaGiuridica = naturaGiuridica;
	}

	/**
	 * Gets the impianti.
	 *
	 * @return the impianti
	 */
	public List<TsddrTImpianto> getImpianti() {
		return this.impianti;
	}

	/**
	 * Sets the impianti.
	 *
	 * @param impianti the new impianti
	 */
	public void setImpianti(List<TsddrTImpianto> impianti) {
		this.impianti = impianti;
	}

	/**
	 * Adds the impianti.
	 *
	 * @param impianti the impianti
	 * @return the tsddr T impianto
	 */
	public TsddrTImpianto addImpianti(TsddrTImpianto impianti) {
		getImpianti().add(impianti);
		impianti.setGestore(this);

		return impianti;
	}

	/**
	 * Removes the impianti.
	 *
	 * @param impianti the impianti
	 * @return the tsddr T impianto
	 */
	public TsddrTImpianto removeImpianti(TsddrTImpianto impianti) {
		getImpianti().remove(impianti);
		impianti.setGestore(null);

		return impianti;
	}

	/**
	 * Gets the sede legale.
	 *
	 * @return the sede legale
	 */
	public TsddrTIndirizzo getSedeLegale() {
		return sedeLegale;
	}
	
	/**
	 * Sets the sede legale.
	 *
	 * @param sedeLegale the new sede legale
	 */
	public void setSedeLegale(TsddrTIndirizzo sedeLegale) {
		this.sedeLegale = sedeLegale;
	}

	/**
	 * Gets the legali rappresentanti.
	 *
	 * @return the legali rappresentanti
	 */
	public List<TsddrTLegaleRappresentante> getLegaliRappresentanti() {
		return this.legaliRappresentanti;
	}

	/**
	 * Sets the legali rappresentanti.
	 *
	 * @param legaliRappresentanti the new legali rappresentanti
	 */
	public void setLegaliRappresentanti(List<TsddrTLegaleRappresentante> legaliRappresentanti) {
		this.legaliRappresentanti = legaliRappresentanti;
	}

	/**
	 * Adds the legali rappresentanti.
	 *
	 * @param legaliRappresentanti the legali rappresentanti
	 * @return the tsddr T legale rappresentante
	 */
	public TsddrTLegaleRappresentante addLegaliRappresentanti(TsddrTLegaleRappresentante legaliRappresentanti) {
		getLegaliRappresentanti().add(legaliRappresentanti);
		legaliRappresentanti.setGestore(this);

		return legaliRappresentanti;
	}

	/**
	 * Removes the legali rappresentanti.
	 *
	 * @param legaliRappresentanti the legali rappresentanti
	 * @return the tsddr T legale rappresentante
	 */
	public TsddrTLegaleRappresentante removeLegaliRappresentanti(TsddrTLegaleRappresentante legaliRappresentanti) {
		getLegaliRappresentanti().remove(legaliRappresentanti);
		legaliRappresentanti.setGestore(null);

		return legaliRappresentanti;
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

}