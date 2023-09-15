/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class TipoImpiantoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;
	
	private Long idTipoImpianto;
	private String descTipoImpianto;

	/**
	 * Gets the id tipo impianto.
	 *
	 * @return the id tipo impianto
	 */
	public Long getIdTipoImpianto() {
		return idTipoImpianto;
	}

	/**
	 * Sets the id tipo impianto.
	 *
	 * @param idTipoImpianto the new id tipo impianto
	 */
	public void setIdTipoImpianto(Long idTipoImpianto) {
		this.idTipoImpianto = idTipoImpianto;
	}

	/**
	 * Gets the desc tipo impianto.
	 *
	 * @return the desc tipo impianto
	 */
	public String getDescTipoImpianto() {
		return descTipoImpianto;
	}

	/**
	 * Sets the desc tipo impianto.
	 *
	 * @param descTipoImpianto the new desc tipo impianto
	 */
	public void setDescTipoImpianto(String descTipoImpianto) {
		this.descTipoImpianto = descTipoImpianto;
	}

}
