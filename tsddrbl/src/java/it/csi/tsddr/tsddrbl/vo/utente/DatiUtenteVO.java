/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.utente;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class DatiUtenteVO extends AbstractVO {

	private static final long serialVersionUID = 1702327503486654745L;

	private Long idUtente;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String telefono;
	private String telefono2;
	private String mail;
	private String pec;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private Boolean deletable;

	/**
	 * Instantiates a new dati utente VO.
	 */
	public DatiUtenteVO() {
	}

	/**
	 * Instantiates a new dati utente VO.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param codiceFiscale the codice fiscale
	 * @param telefono the telefono
	 * @param telefono2 the telefono 2
	 * @param mail the mail
	 * @param pec the pec
	 * @param dataInizioValidita the data inizio validita
	 * @param dataFineValidita the data fine validita
	 */
	public DatiUtenteVO(String nome, String cognome, String codiceFiscale, String telefono, String telefono2,
			String mail, String pec, Date dataInizioValidita, Date dataFineValidita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.telefono = telefono;
		this.telefono2 = telefono2;
		this.mail = mail;
		this.pec = pec;
		this.dataInizioValidita = dataInizioValidita;
		this.dataFineValidita = dataFineValidita;
	}
	
	/**
	 * Instantiates a new dati utente VO.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param codiceFiscale the codice fiscale
	 * @param telefono the telefono
	 * @param telefono2 the telefono 2
	 * @param mail the mail
	 * @param pec the pec
	 * @param dataInizioValidita the data inizio validita
	 * @param dataFineValidita the data fine validita
	 * @param deletable the deletable
	 */
	public DatiUtenteVO(String nome, String cognome, String codiceFiscale, String telefono, String telefono2,
			String mail, String pec, Date dataInizioValidita, Date dataFineValidita, boolean deletable) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.telefono = telefono;
		this.telefono2 = telefono2;
		this.mail = mail;
		this.pec = pec;
		this.dataInizioValidita = dataInizioValidita;
		this.dataFineValidita = dataFineValidita;
		this.deletable = deletable;
	}

	/**
	 * Gets the id utente.
	 *
	 * @return the id utente
	 */
	public Long getIdUtente() {
		return idUtente;
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
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
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
	 * Gets the cognome.
	 *
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
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
	 * Gets the codice fiscale.
	 *
	 * @return the codice fiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * Sets the codice fiscale.
	 *
	 * @param codiceFiscale the new codice fiscale
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
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
		return telefono2;
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
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the pec.
	 *
	 * @return the pec
	 */
	public String getPec() {
		return pec;
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
	 * Gets the data inizio validita.
	 *
	 * @return the data inizio validita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * Sets the data inizio validita.
	 *
	 * @param dataInizioValidita the new data inizio validita
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * Gets the data fine validita.
	 *
	 * @return the data fine validita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * Sets the data fine validita.
	 *
	 * @param dataFineValidita the new data fine validita
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * Checks if is deletable.
	 *
	 * @return the boolean
	 */
	public Boolean isDeletable() {
		return deletable;
	}

	/**
	 * Sets the deletable.
	 *
	 * @param deletable the new deletable
	 */
	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}

}