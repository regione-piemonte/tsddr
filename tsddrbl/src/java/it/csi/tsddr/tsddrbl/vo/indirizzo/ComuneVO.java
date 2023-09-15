/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.indirizzo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class ComuneVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idComune;
	private String cap;
	private String codCatasto;
	private String comune;
	private Long idComuneIstat;
	private ProvinciaVO provincia;

	/**
	 * Gets the id comune.
	 *
	 * @return the id comune
	 */
	public Long getIdComune() {
		return idComune;
	}

	/**
	 * Sets the id comune.
	 *
	 * @param idComune the new id comune
	 */
	public void setIdComune(Long idComune) {
		this.idComune = idComune;
	}

	/**
	 * Gets the cap.
	 *
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * Sets the cap.
	 *
	 * @param cap the new cap
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Gets the cod catasto.
	 *
	 * @return the cod catasto
	 */
	public String getCodCatasto() {
		return codCatasto;
	}

	/**
	 * Sets the cod catasto.
	 *
	 * @param codCatasto the new cod catasto
	 */
	public void setCodCatasto(String codCatasto) {
		this.codCatasto = codCatasto;
	}

	/**
	 * Gets the comune.
	 *
	 * @return the comune
	 */
	public String getComune() {
		return comune;
	}

	/**
	 * Sets the comune.
	 *
	 * @param comune the new comune
	 */
	public void setComune(String comune) {
		this.comune = comune;
	}

	/**
	 * Gets the id comune istat.
	 *
	 * @return the id comune istat
	 */
	public Long getIdComuneIstat() {
		return idComuneIstat;
	}

	/**
	 * Sets the id comune istat.
	 *
	 * @param idComuneIstat the new id comune istat
	 */
	public void setIdComuneIstat(Long idComuneIstat) {
		this.idComuneIstat = idComuneIstat;
	}

	/**
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public ProvinciaVO getProvincia() {
		return provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia the new provincia
	 */
	public void setProvincia(ProvinciaVO provincia) {
		this.provincia = provincia;
	}

}
