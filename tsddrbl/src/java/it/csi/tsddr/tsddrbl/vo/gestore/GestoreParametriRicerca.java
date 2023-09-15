/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.gestore;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

public class GestoreParametriRicerca implements Serializable {

	private static final long serialVersionUID = 3116229810730762959L;
	
	@QueryParam("codFiscPartiva")
	private String codFiscPartiva;
	@QueryParam("idGestore")
	private Long idGestore;
	@QueryParam("idNaturaGiuridica")
	private Long idNaturaGiuridica;
	@QueryParam("idComune")
	private Long idComune;
	@QueryParam("idProvincia")
	private Long idProvincia;

	/**
	 * @return the codFiscPartiva
	 */
	public String getCodFiscPartiva() {
		return codFiscPartiva;
	}

	/**
	 * @param codFiscPartiva the codFiscPartiva to set
	 */
	public void setCodFiscPartiva(String codFiscPartiva) {
		this.codFiscPartiva = codFiscPartiva;
	}

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
	 * @return the idNaturaGiuridica
	 */
	public Long getIdNaturaGiuridica() {
		return idNaturaGiuridica;
	}

	/**
	 * @param idNaturaGiuridica the idNaturaGiuridica to set
	 */
	public void setIdNaturaGiuridica(Long idNaturaGiuridica) {
		this.idNaturaGiuridica = idNaturaGiuridica;
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

}
