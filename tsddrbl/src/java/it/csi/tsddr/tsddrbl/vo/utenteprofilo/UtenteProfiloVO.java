/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.utenteprofilo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class UtenteProfiloVO extends AbstractVO {
    
    private static final long serialVersionUID = 1L;

	private Long idUtente;
	private String codiceFiscale;
	private String cognome;
	private String nome;
	
	/**
	 * Instantiates a new utente profilo VO.
	 */
	public UtenteProfiloVO() {
	}
	
	/**
	 * Instantiates a new utente profilo VO.
	 *
	 * @param idUtente the id utente
	 * @param codiceFiscale the codice fiscale
	 * @param cognome the cognome
	 * @param nome the nome
	 */
	public UtenteProfiloVO(Long idUtente, String codiceFiscale, String cognome, String nome) {
		this.idUtente = idUtente;
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.nome = nome;
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

}
