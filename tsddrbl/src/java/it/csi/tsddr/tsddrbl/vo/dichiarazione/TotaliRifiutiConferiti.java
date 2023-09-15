/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.List;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class TotaliRifiutiConferiti extends AbstractVO {

	private static final long serialVersionUID = 1223004470398166691L;

	private List<TotalePeriodo> totaliPeriodi;

	private RiepilogoTotale totale;
	
	/**
	 * Instantiates a new totali rifiuti conferiti.
	 */
	public TotaliRifiutiConferiti() {
	}

	/**
	 * Instantiates a new totali rifiuti conferiti.
	 *
	 * @param totaliPeriodi the totali periodi
	 * @param totale the totale
	 */
	public TotaliRifiutiConferiti(List<TotalePeriodo> totaliPeriodi, RiepilogoTotale totale) {
		this.totaliPeriodi = totaliPeriodi;
		this.totale = totale;
	}

	/**
	 * Gets the totali periodi.
	 *
	 * @return the totali periodi
	 */
	public List<TotalePeriodo> getTotaliPeriodi() {
		return totaliPeriodi;
	}

	/**
	 * Sets the totali periodi.
	 *
	 * @param totaliPeriodi the new totali periodi
	 */
	public void setTotaliPeriodi(List<TotalePeriodo> totaliPeriodi) {
		this.totaliPeriodi = totaliPeriodi;
	}

	/**
	 * Gets the totale.
	 *
	 * @return the totale
	 */
	public RiepilogoTotale getTotale() {
		return totale;
	}

	/**
	 * Sets the totale.
	 *
	 * @param totale the new totale
	 */
	public void setTotale(RiepilogoTotale totale) {
		this.totale = totale;
	}

}
