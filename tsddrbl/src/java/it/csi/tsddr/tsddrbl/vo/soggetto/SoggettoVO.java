/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.soggetto;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class SoggettoVO extends AbstractVO {
    
    private static final long serialVersionUID = 1L;

	private Integer id;
	private String codiceFiscalePartitaIva;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the codice fiscale partita iva.
	 *
	 * @return the codice fiscale partita iva
	 */
	public String getCodiceFiscalePartitaIva() {
		return codiceFiscalePartitaIva;
	}

	/**
	 * Sets the codice fiscale partita iva.
	 *
	 * @param codiceFiscalePartitaIva the new codice fiscale partita iva
	 */
	public void setCodiceFiscalePartitaIva(String codiceFiscalePartitaIva) {
		this.codiceFiscalePartitaIva = codiceFiscalePartitaIva;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
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

}
