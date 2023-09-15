/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

public class DichiarazioneParametriRicerca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@QueryParam("idGestore")
	private Long idGestore;
	@QueryParam("idImpianto")
	private Long idImpianto;
	@QueryParam("annoDal")
	private Long annoDal;
	@QueryParam("annoAl")
	private Long annoAl;

	/**
	 * Gets the id gestore.
	 *
	 * @return the id gestore
	 */
	public Long getIdGestore() {
		return idGestore;
	}

	/**
	 * Sets the id gestore.
	 *
	 * @param idGestore the new id gestore
	 */
	public void setIdGestore(Long idGestore) {
		this.idGestore = idGestore;
	}

	/**
	 * Gets the id impianto.
	 *
	 * @return the id impianto
	 */
	public Long getIdImpianto() {
		return idImpianto;
	}

	/**
	 * Sets the id impianto.
	 *
	 * @param idImpianto the new id impianto
	 */
	public void setIdImpianto(Long idImpianto) {
		this.idImpianto = idImpianto;
	}

	/**
	 * Gets the anno dal.
	 *
	 * @return the anno dal
	 */
	public Long getAnnoDal() {
		return annoDal;
	}

	/**
	 * Sets the anno dal.
	 *
	 * @param annoDal the new anno dal
	 */
	public void setAnnoDal(Long annoDal) {
		this.annoDal = annoDal;
	}

	/**
	 * Gets the anno al.
	 *
	 * @return the anno al
	 */
	public Long getAnnoAl() {
		return annoAl;
	}

	/**
	 * Sets the anno al.
	 *
	 * @param annoAl the new anno al
	 */
	public void setAnnoAl(Long annoAl) {
		this.annoAl = annoAl;
	}

}
