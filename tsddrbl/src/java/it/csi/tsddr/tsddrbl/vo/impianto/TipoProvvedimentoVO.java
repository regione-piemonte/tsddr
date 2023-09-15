/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class TipoProvvedimentoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;
	
	private Long idTipoProvvedimento;
	private String descTipoProvvedimento;

	/**
	 * Gets the id tipo provvedimento.
	 *
	 * @return the id tipo provvedimento
	 */
	public Long getIdTipoProvvedimento() {
		return idTipoProvvedimento;
	}

	/**
	 * Sets the id tipo provvedimento.
	 *
	 * @param idTipoProvvedimento the new id tipo provvedimento
	 */
	public void setIdTipoProvvedimento(Long idTipoProvvedimento) {
		this.idTipoProvvedimento = idTipoProvvedimento;
	}

	/**
	 * Gets the desc tipo provvedimento.
	 *
	 * @return the desc tipo provvedimento
	 */
	public String getDescTipoProvvedimento() {
		return descTipoProvvedimento;
	}

	/**
	 * Sets the desc tipo provvedimento.
	 *
	 * @param descTipoProvvedimento the new desc tipo provvedimento
	 */
	public void setDescTipoProvvedimento(String descTipoProvvedimento) {
		this.descTipoProvvedimento = descTipoProvvedimento;
	}

}
