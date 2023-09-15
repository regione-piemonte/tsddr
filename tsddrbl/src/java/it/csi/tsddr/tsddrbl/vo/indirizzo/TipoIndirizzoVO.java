/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.indirizzo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class TipoIndirizzoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idTipoIndirizzo;
	private String descTipoIndirizzo;

	/**
	 * Gets the id tipo indirizzo.
	 *
	 * @return the id tipo indirizzo
	 */
	public Long getIdTipoIndirizzo() {
		return idTipoIndirizzo;
	}

	/**
	 * Sets the id tipo indirizzo.
	 *
	 * @param idTipoIndirizzo the new id tipo indirizzo
	 */
	public void setIdTipoIndirizzo(Long idTipoIndirizzo) {
		this.idTipoIndirizzo = idTipoIndirizzo;
	}

	/**
	 * Gets the desc tipo indirizzo.
	 *
	 * @return the desc tipo indirizzo
	 */
	public String getDescTipoIndirizzo() {
		return descTipoIndirizzo;
	}

	/**
	 * Sets the desc tipo indirizzo.
	 *
	 * @param descTipoIndirizzo the new desc tipo indirizzo
	 */
	public void setDescTipoIndirizzo(String descTipoIndirizzo) {
		this.descTipoIndirizzo = descTipoIndirizzo;
	}

}
