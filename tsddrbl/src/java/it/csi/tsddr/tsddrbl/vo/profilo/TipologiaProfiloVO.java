/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.profilo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class TipologiaProfiloVO extends AbstractVO {

	private static final long serialVersionUID = 1L;
	
	private Long idTipoProfilo;
	private String descTipoProfilo;
	
	/**
	 * Instantiates a new tipologia profilo VO.
	 */
	public TipologiaProfiloVO() {
	}

	/**
	 * Instantiates a new tipologia profilo VO.
	 *
	 * @param idTipoProfilo the id tipo profilo
	 * @param descTipoProfilo the desc tipo profilo
	 */
	public TipologiaProfiloVO(Long idTipoProfilo, String descTipoProfilo) {
		this.idTipoProfilo = idTipoProfilo;
		this.descTipoProfilo = descTipoProfilo;
	}

	/**
	 * Gets the id tipo profilo.
	 *
	 * @return the id tipo profilo
	 */
	public Long getIdTipoProfilo() {
		return idTipoProfilo;
	}

	/**
	 * Sets the id tipo profilo.
	 *
	 * @param idTipoProfilo the new id tipo profilo
	 */
	public void setIdTipoProfilo(Long idTipoProfilo) {
		this.idTipoProfilo = idTipoProfilo;
	}

	/**
	 * Gets the desc tipo profilo.
	 *
	 * @return the desc tipo profilo
	 */
	public String getDescTipoProfilo() {
		return descTipoProfilo;
	}

	/**
	 * Sets the desc tipo profilo.
	 *
	 * @param descTipoProfilo the new desc tipo profilo
	 */
	public void setDescTipoProfilo(String descTipoProfilo) {
		this.descTipoProfilo = descTipoProfilo;
	}

}
