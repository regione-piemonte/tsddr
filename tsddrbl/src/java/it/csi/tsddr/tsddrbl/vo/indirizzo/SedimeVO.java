/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.indirizzo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class SedimeVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idSedime;
	private String descSedime;

	/**
	 * Gets the id sedime.
	 *
	 * @return the id sedime
	 */
	public Long getIdSedime() {
		return idSedime;
	}

	/**
	 * Sets the id sedime.
	 *
	 * @param idSedime the new id sedime
	 */
	public void setIdSedime(Long idSedime) {
		this.idSedime = idSedime;
	}

	/**
	 * Gets the desc sedime.
	 *
	 * @return the desc sedime
	 */
	public String getDescSedime() {
		return descSedime;
	}

	/**
	 * Sets the desc sedime.
	 *
	 * @param descSedime the new desc sedime
	 */
	public void setDescSedime(String descSedime) {
		this.descSedime = descSedime;
	}

}
