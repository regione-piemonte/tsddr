/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.indirizzo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class RegioneVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idRegione;
	private String descRegione;
	private Long idIstatRegione;

	/**
	 * Gets the id regione.
	 *
	 * @return the id regione
	 */
	public Long getIdRegione() {
		return idRegione;
	}

	/**
	 * Sets the id regione.
	 *
	 * @param idRegione the new id regione
	 */
	public void setIdRegione(Long idRegione) {
		this.idRegione = idRegione;
	}

	/**
	 * Gets the desc regione.
	 *
	 * @return the desc regione
	 */
	public String getDescRegione() {
		return descRegione;
	}

	/**
	 * Sets the desc regione.
	 *
	 * @param descRegione the new desc regione
	 */
	public void setDescRegione(String descRegione) {
		this.descRegione = descRegione;
	}

	/**
	 * Gets the id istat regione.
	 *
	 * @return the id istat regione
	 */
	public Long getIdIstatRegione() {
		return idIstatRegione;
	}

	/**
	 * Sets the id istat regione.
	 *
	 * @param idIstatRegione the new id istat regione
	 */
	public void setIdIstatRegione(Long idIstatRegione) {
		this.idIstatRegione = idIstatRegione;
	}

}
