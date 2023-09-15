/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.accreditamento;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.QueryParam;

public final class DomandaAccreditamentoParametriRicercaVO implements Serializable {

	private static final long serialVersionUID = 1861874138662329330L;
	
	@QueryParam("idDomanda")
	private String idDomanda;
	@QueryParam("idStatoDomanda")
	private List<Long> idStatiDomanda;
	@QueryParam("idGestore")
	private List<Long> idGestori;
	/**
	 * TSDDR_T_DATI_SOGG.ID_DATI_SOGG
	 */
	@QueryParam("idRichiedente")
	private List<Long> idRichiedenti;
	@QueryParam("dataDomandaDal")
	private String dataDomandaDal;
	@QueryParam("dataDomandaAl")
	private String dataDomandaAl;
	
	/**
	 * Gets the id domanda.
	 *
	 * @return the id domanda
	 */
	public String getIdDomanda() {
		return idDomanda;
	}

	/**
	 * Sets the id domanda.
	 *
	 * @param idDomanda the new id domanda
	 */
	public void setIdDomanda(String idDomanda) {
		this.idDomanda = idDomanda;
	}

	/**
	 * Gets the id stati domanda.
	 *
	 * @return the id stati domanda
	 */
	public List<Long> getIdStatiDomanda() {
		return idStatiDomanda;
	}

	/**
	 * Sets the id stati domanda.
	 *
	 * @param idStatiDomanda the new id stati domanda
	 */
	public void setIdStatiDomanda(List<Long> idStatiDomanda) {
		this.idStatiDomanda = idStatiDomanda;
	}

	/**
	 * Gets the id gestori.
	 *
	 * @return the id gestori
	 */
	public List<Long> getIdGestori() {
		return idGestori;
	}

	/**
	 * Sets the id gestori.
	 *
	 * @param idGestori the new id gestori
	 */
	public void setIdGestori(List<Long> idGestori) {
		this.idGestori = idGestori;
	}

	/**
	 * Gets the id richiedenti.
	 *
	 * @return the id richiedenti
	 */
	public List<Long> getIdRichiedenti() {
		return idRichiedenti;
	}

	/**
	 * Sets the id richiedenti.
	 *
	 * @param idRichiedenti the new id richiedenti
	 */
	public void setIdRichiedenti(List<Long> idRichiedenti) {
		this.idRichiedenti = idRichiedenti;
	}

	/**
	 * Gets the data domanda dal.
	 *
	 * @return the data domanda dal
	 */
	public String getDataDomandaDal() {
		return dataDomandaDal;
	}

	/**
	 * Sets the data domanda dal.
	 *
	 * @param dataDomandaDal the new data domanda dal
	 */
	public void setDataDomandaDal(String dataDomandaDal) {
		this.dataDomandaDal = dataDomandaDal;
	}

	/**
	 * Gets the data domanda al.
	 *
	 * @return the data domanda al
	 */
	public String getDataDomandaAl() {
		return dataDomandaAl;
	}

	/**
	 * Sets the data domanda al.
	 *
	 * @param dataDomandaAl the new data domanda al
	 */
	public void setDataDomandaAl(String dataDomandaAl) {
		this.dataDomandaAl = dataDomandaAl;
	}
	
}
