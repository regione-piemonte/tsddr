/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.datisogg;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class DatiSoggVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idDatiSogg;
	private String codFiscale;
	private String cognome;
	private String email;
	private String nome;
	private String pec;
	private String telefono;
	private String telefono2;

	/**
	 * Gets the id dati sogg.
	 *
	 * @return the id dati sogg
	 */
	public Long getIdDatiSogg() {
		return idDatiSogg;
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
		return codFiscale;
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

}
