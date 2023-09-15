/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

public class ExistsDichiarazioneVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@QueryParam("idImpianto")
	@NotNull
	private Long idImpianto;
	@QueryParam("idGestore")
	@NotNull
	private Long idGestore;
	@QueryParam("anno")
	@NotNull
	private Long anno;

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
	 * Gets the anno.
	 *
	 * @return the anno
	 */
	public Long getAnno() {
		return anno;
	}

	/**
	 * Sets the anno.
	 *
	 * @param anno the new anno
	 */
	public void setAnno(Long anno) {
		this.anno = anno;
	}

}
