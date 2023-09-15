/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.userinfo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class UserInfoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idProfilo;
	private String descProfilo;
	private String nome;
	private String cognome;
	private String codiceFiscale;

	/**
	 * Instantiates a new user info VO.
	 */
	public UserInfoVO() {
	}

	/**
	 * Instantiates a new user info VO.
	 *
	 * @param idProfilo the id profilo
	 * @param descProfilo the desc profilo
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param codiceFiscale the codice fiscale
	 */
	public UserInfoVO(Long idProfilo, String descProfilo, String nome, String cognome, String codiceFiscale) {
		this.idProfilo = idProfilo;
		this.descProfilo = descProfilo;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
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

	/**
	 * Gets the desc profilo.
	 *
	 * @return the desc profilo
	 */
	public String getDescProfilo() {
		return descProfilo;
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

}
