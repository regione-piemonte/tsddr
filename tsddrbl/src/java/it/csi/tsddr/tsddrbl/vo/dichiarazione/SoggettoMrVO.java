/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class SoggettoMrVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idSoggettiMr;
	private String codFiscPartiva;
	private String ragSociale;
	private String obbRagg;

	/**
	 * Gets the id soggetti mr.
	 *
	 * @return the id soggetti mr
	 */
	public Long getIdSoggettiMr() {
		return idSoggettiMr;
	}

	/**
	 * Sets the id soggetti mr.
	 *
	 * @param idSoggettiMr the new id soggetti mr
	 */
	public void setIdSoggettiMr(Long idSoggettiMr) {
		this.idSoggettiMr = idSoggettiMr;
	}

	/**
	 * Gets the cod fisc partiva.
	 *
	 * @return the cod fisc partiva
	 */
	public String getCodFiscPartiva() {
		return codFiscPartiva;
	}

	/**
	 * Sets the cod fisc partiva.
	 *
	 * @param codFiscPartiva the new cod fisc partiva
	 */
	public void setCodFiscPartiva(String codFiscPartiva) {
		this.codFiscPartiva = codFiscPartiva;
	}

	/**
	 * Gets the rag sociale.
	 *
	 * @return the rag sociale
	 */
	public String getRagSociale() {
		return ragSociale;
	}

	/**
	 * Sets the rag sociale.
	 *
	 * @param ragSociale the new rag sociale
	 */
	public void setRagSociale(String ragSociale) {
		this.ragSociale = ragSociale;
	}

	/**
	 * Gets the obb ragg.
	 *
	 * @return the obb ragg
	 */
	public String getObbRagg() {
		return obbRagg;
	}

	/**
	 * Sets the obb ragg.
	 *
	 * @param obbRagg the new obb ragg
	 */
	public void setObbRagg(String obbRagg) {
		this.obbRagg = obbRagg;
	}

}
