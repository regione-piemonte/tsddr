/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.indirizzo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class NazioneVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idNazione;
	private String descNazione;
	private String idIstatNazione;

	/**
	 * Gets the id nazione.
	 *
	 * @return the id nazione
	 */
	public Long getIdNazione() {
		return idNazione;
	}

	/**
	 * Sets the id nazione.
	 *
	 * @param idNazione the new id nazione
	 */
	public void setIdNazione(Long idNazione) {
		this.idNazione = idNazione;
	}

	/**
	 * Gets the desc nazione.
	 *
	 * @return the desc nazione
	 */
	public String getDescNazione() {
		return descNazione;
	}

	/**
	 * Sets the desc nazione.
	 *
	 * @param descNazione the new desc nazione
	 */
	public void setDescNazione(String descNazione) {
		this.descNazione = descNazione;
	}

	/**
	 * Gets the id istat nazione.
	 *
	 * @return the id istat nazione
	 */
	public String getIdIstatNazione() {
		return idIstatNazione;
	}

	/**
	 * Sets the id istat nazione.
	 *
	 * @param idIstatNazione the new id istat nazione
	 */
	public void setIdIstatNazione(String idIstatNazione) {
		this.idIstatNazione = idIstatNazione;
	}
	
}
