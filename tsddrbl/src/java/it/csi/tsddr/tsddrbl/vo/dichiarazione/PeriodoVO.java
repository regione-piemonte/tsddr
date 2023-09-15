/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.Objects;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class PeriodoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idPeriodo;
	private String descPeriodo;

	/**
	 * Gets the id periodo.
	 *
	 * @return the id periodo
	 */
	public Long getIdPeriodo() {
		return idPeriodo;
	}

	/**
	 * Sets the id periodo.
	 *
	 * @param idPeriodo the new id periodo
	 */
	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	/**
	 * Gets the desc periodo.
	 *
	 * @return the desc periodo
	 */
	public String getDescPeriodo() {
		return descPeriodo;
	}

	/**
	 * Sets the desc periodo.
	 *
	 * @param descPeriodo the new desc periodo
	 */
	public void setDescPeriodo(String descPeriodo) {
		this.descPeriodo = descPeriodo;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idPeriodo);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoVO other = (PeriodoVO) obj;
		return Objects.equals(idPeriodo, other.idPeriodo);
	}

}
