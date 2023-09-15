/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.unitamisura;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class UnitaMisuraVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idUnitaMisura;
	private String descUnitaMisura;

	/**
	 * Gets the id unita misura.
	 *
	 * @return the id unita misura
	 */
	public Long getIdUnitaMisura() {
		return idUnitaMisura;
	}

	/**
	 * Sets the id unita misura.
	 *
	 * @param idUnitaMisura the new id unita misura
	 */
	public void setIdUnitaMisura(Long idUnitaMisura) {
		this.idUnitaMisura = idUnitaMisura;
	}

	/**
	 * Gets the desc unita misura.
	 *
	 * @return the desc unita misura
	 */
	public String getDescUnitaMisura() {
		return descUnitaMisura;
	}

	/**
	 * Sets the desc unita misura.
	 *
	 * @param descUnitaMisura the new desc unita misura
	 */
	public void setDescUnitaMisura(String descUnitaMisura) {
		this.descUnitaMisura = descUnitaMisura;
	}

}
