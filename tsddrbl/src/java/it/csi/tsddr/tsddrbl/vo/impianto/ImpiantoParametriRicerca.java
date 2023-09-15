/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

public class ImpiantoParametriRicerca implements Serializable {

	private static final long serialVersionUID = 3116229810730762959L;

	@QueryParam("idGestore")
	private Long idGestore;
	@QueryParam("denominazione")
	private String denominazione;
	@QueryParam("idProvincia")
	private Long idProvincia;
	@QueryParam("idComune")
	private Long idComune;
	@QueryParam("idTipoImpianto")
	private Long idTipoImpianto;
	@QueryParam("idStatoImpianto")
	private Long idStatoImpianto;

	/**
	 * @return the idGestore
	 */
	public Long getIdGestore() {
		return idGestore;
	}

	/**
	 * @param idGestore the idGestore to set
	 */
	public void setIdGestore(Long idGestore) {
		this.idGestore = idGestore;
	}

	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	/**
	 * @return the idProvincia
	 */
	public Long getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the idComune
	 */
	public Long getIdComune() {
		return idComune;
	}

	/**
	 * @param idComune the idComune to set
	 */
	public void setIdComune(Long idComune) {
		this.idComune = idComune;
	}

	/**
	 * @return the idTipoImpianto
	 */
	public Long getIdTipoImpianto() {
		return idTipoImpianto;
	}

	/**
	 * @param idTipoImpianto the idTipoImpianto to set
	 */
	public void setIdTipoImpianto(Long idTipoImpianto) {
		this.idTipoImpianto = idTipoImpianto;
	}

	/**
	 * @return the idStatoImpianto
	 */
	public Long getIdStatoImpianto() {
		return idStatoImpianto;
	}

	/**
	 * @param idStatoImpianto the idStatoImpianto to set
	 */
	public void setIdStatoImpianto(Long idStatoImpianto) {
		this.idStatoImpianto = idStatoImpianto;
	}

}
