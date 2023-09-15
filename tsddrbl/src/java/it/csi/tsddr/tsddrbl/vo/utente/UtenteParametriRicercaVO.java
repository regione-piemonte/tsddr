/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.utente;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class UtenteParametriRicercaVO extends AbstractVO {

	private static final long serialVersionUID = 1L;
	
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private Long idGestore;
	private Long idProfilo;

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
	 * Gets the id gestore.
	 *
	 * @return the id gestore
	 */
	public Long getIdGestore() {
		return idGestore;
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
	 * Gets the id profilo.
	 *
	 * @return the id profilo
	 */
	public Long getIdProfilo() {
		return idProfilo;
	}

	/**
	 * Sets the id profilo.
	 *
	 * @param idProfilo the new id profilo
	 */
	public void setIdProfilo(Long idProfilo) {
		this.idProfilo = idProfilo;
	}

}
