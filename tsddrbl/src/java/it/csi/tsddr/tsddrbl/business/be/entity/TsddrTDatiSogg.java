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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_dati_sogg database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_dati_sogg")
@NamedQuery(name = "TsddrTDatiSogg.findAll", query = "SELECT t FROM TsddrTDatiSogg t")
public class TsddrTDatiSogg extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dati_sogg", unique = true, nullable = false)
	private Long idDatiSogg;

	@Column(name = "cod_fiscale", nullable = false, length = 255)
	private String codFiscale;

	@Column(nullable = false, length = 255)
	private String cognome;

	@Column(length = 255)
	private String email;

	@Column(nullable = false, length = 255)
	private String nome;

	@Column(length = 255)
	private String pec;

	@Column(length = 255)
	private String telefono;

	@Column(name = "telefono_2", length = 255)
	private String telefono2;

	// bi-directional one-to-one association to TsddrTUtente
	@OneToOne(mappedBy = "datiSogg")
	private TsddrTUtente utente;

	// bi-directional many-to-one association to TsddrTDomanda
	@OneToMany(mappedBy = "datiSogg")
	private List<TsddrTDomanda> domande;

	// bi-directional many-to-one association to TsddrTLegaleRappresentante
	@OneToMany(mappedBy = "datiSogg")
	private List<TsddrTLegaleRappresentante> legaliRappresentanti;

	/**
	 * Instantiates a new tsddr T dati sogg.
	 */
	public TsddrTDatiSogg() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id dati sogg.
	 *
	 * @return the id dati sogg
	 */
	public Long getIdDatiSogg() {
		return this.idDatiSogg;
	}

	/**
	 * Sets the id dati sogg.
	 *
	 * @param idDatiSogg the new id dati sogg
	 */
	public void setIdDatiSogg(Long idDatiSogg) {
		this.idDatiSogg = idDatiSogg;
	}

	/**
	 * Gets the cod fiscale.
	 *
	 * @return the cod fiscale
	 */
	public String getCodFiscale() {
		return this.codFiscale;
	}

	/**
	 * Sets the cod fiscale.
	 *
	 * @param codFiscale the new cod fiscale
	 */
	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	/**
	 * Gets the cognome.
	 *
	 * @return the cognome
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * Sets the cognome.
	 *
	 * @param cognome the new cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
	 * Gets the telefono 2.
	 *
	 * @return the telefono 2
	 */
	public String getTelefono2() {
		return this.telefono2;
	}

	/**
	 * Sets the telefono 2.
	 *
	 * @param telefono2 the new telefono 2
	 */
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
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
		domande.setDatiSogg(this);

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
		domande.setDatiSogg(null);

		return domande;
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
		legaliRappresentanti.setDatiSogg(this);

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
		legaliRappresentanti.setDatiSogg(null);

		return legaliRappresentanti;
	}

}