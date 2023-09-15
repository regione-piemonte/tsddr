/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class StatoImpiantoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idStatoImpianto;
	private String descStatoImpianto;

	/**
	 * Gets the id stato impianto.
	 *
	 * @return the id stato impianto
	 */
	public Long getIdStatoImpianto() {
		return idStatoImpianto;
	}

	/**
	 * Sets the id stato impianto.
	 *
	 * @param idStatoImpianto the new id stato impianto
	 */
	public void setIdStatoImpianto(Long idStatoImpianto) {
		this.idStatoImpianto = idStatoImpianto;
	}

	/**
	 * Gets the desc stato impianto.
	 *
	 * @return the desc stato impianto
	 */
	public String getDescStatoImpianto() {
		return descStatoImpianto;
	}

	/**
	 * Sets the desc stato impianto.
	 *
	 * @param descStatoImpianto the new desc stato impianto
	 */
	public void setDescStatoImpianto(String descStatoImpianto) {
		this.descStatoImpianto = descStatoImpianto;
	}

}
