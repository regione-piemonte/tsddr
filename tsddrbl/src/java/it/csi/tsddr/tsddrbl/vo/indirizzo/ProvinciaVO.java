/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.indirizzo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class ProvinciaVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idProvincia;
	private String descProvincia;
	private Long idProvinciaIstat;
	private String siglaProv;
	private RegioneVO regione;

	/**
	 * Gets the id provincia.
	 *
	 * @return the id provincia
	 */
	public Long getIdProvincia() {
		return idProvincia;
	}

	/**
	 * Sets the id provincia.
	 *
	 * @param idProvincia the new id provincia
	 */
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * Gets the desc provincia.
	 *
	 * @return the desc provincia
	 */
	public String getDescProvincia() {
		return descProvincia;
	}

	/**
	 * Sets the desc provincia.
	 *
	 * @param descProvincia the new desc provincia
	 */
	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	/**
	 * Gets the id provincia istat.
	 *
	 * @return the id provincia istat
	 */
	public Long getIdProvinciaIstat() {
		return idProvinciaIstat;
	}

	/**
	 * Sets the id provincia istat.
	 *
	 * @param idProvinciaIstat the new id provincia istat
	 */
	public void setIdProvinciaIstat(Long idProvinciaIstat) {
		this.idProvinciaIstat = idProvinciaIstat;
	}

	/**
	 * Gets the sigla prov.
	 *
	 * @return the sigla prov
	 */
	public String getSiglaProv() {
		return siglaProv;
	}

	/**
	 * Sets the sigla prov.
	 *
	 * @param siglaProv the new sigla prov
	 */
	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

	/**
	 * Gets the regione.
	 *
	 * @return the regione
	 */
	public RegioneVO getRegione() {
		return regione;
	}

	/**
	 * Sets the regione.
	 *
	 * @param regione the new regione
	 */
	public void setRegione(RegioneVO regione) {
		this.regione = regione;
	}

}
