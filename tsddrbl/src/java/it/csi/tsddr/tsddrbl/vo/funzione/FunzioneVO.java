/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.funzione;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class FunzioneVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idFunzione;

	private String codFunzione;

	private String descFunzione;

	/**
	 * Instantiates a new funzione VO.
	 */
	public FunzioneVO() {
	}

	/**
	 * Instantiates a new funzione VO.
	 *
	 * @param idFunzione the id funzione
	 * @param codFunzione the cod funzione
	 * @param descFunzione the desc funzione
	 */
	public FunzioneVO(Long idFunzione, String codFunzione, String descFunzione) {
		super();
		this.idFunzione = idFunzione;
		this.codFunzione = codFunzione;
		this.descFunzione = descFunzione;
	}

	/**
	 * Gets the id funzione.
	 *
	 * @return the id funzione
	 */
	public Long getIdFunzione() {
		return idFunzione;
	}

	/**
	 * Sets the id funzione.
	 *
	 * @param idFunzione the new id funzione
	 */
	public void setIdFunzione(Long idFunzione) {
		this.idFunzione = idFunzione;
	}

	/**
	 * Gets the cod funzione.
	 *
	 * @return the cod funzione
	 */
	public String getCodFunzione() {
		return codFunzione;
	}

	/**
	 * Sets the cod funzione.
	 *
	 * @param codFunzione the new cod funzione
	 */
	public void setCodFunzione(String codFunzione) {
		this.codFunzione = codFunzione;
	}

	/**
	 * Gets the desc funzione.
	 *
	 * @return the desc funzione
	 */
	public String getDescFunzione() {
		return descFunzione;
	}

	/**
	 * Sets the desc funzione.
	 *
	 * @param descFunzione the new desc funzione
	 */
	public void setDescFunzione(String descFunzione) {
		this.descFunzione = descFunzione;
	}

}
