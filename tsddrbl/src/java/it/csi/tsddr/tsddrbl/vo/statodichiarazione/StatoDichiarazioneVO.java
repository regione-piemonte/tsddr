/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.statodichiarazione;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class StatoDichiarazioneVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idStatoDichiarazione;
	private String descrStatoDichiarazione;

	/**
	 * Gets the id stato dichiarazione.
	 *
	 * @return the id stato dichiarazione
	 */
	public Long getIdStatoDichiarazione() {
		return idStatoDichiarazione;
	}

	/**
	 * Sets the id stato dichiarazione.
	 *
	 * @param idStatoDichiarazione the new id stato dichiarazione
	 */
	public void setIdStatoDichiarazione(Long idStatoDichiarazione) {
		this.idStatoDichiarazione = idStatoDichiarazione;
	}

	/**
	 * Gets the descr stato dichiarazione.
	 *
	 * @return the descr stato dichiarazione
	 */
	public String getDescrStatoDichiarazione() {
		return descrStatoDichiarazione;
	}

	/**
	 * Sets the descr stato dichiarazione.
	 *
	 * @param descrStatoDichiarazione the new descr stato dichiarazione
	 */
	public void setDescrStatoDichiarazione(String descrStatoDichiarazione) {
		this.descrStatoDichiarazione = descrStatoDichiarazione;
	}

}
