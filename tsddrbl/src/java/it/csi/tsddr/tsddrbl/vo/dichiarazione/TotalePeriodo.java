/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class TotalePeriodo extends AbstractVO {

	private static final long serialVersionUID = -4321106126062395316L;

	private PeriodoVO periodo;

	private RiepilogoTotale totale;
	
	/**
	 * Instantiates a new totale periodo.
	 */
	public TotalePeriodo() {
	}

	/**
	 * Instantiates a new totale periodo.
	 *
	 * @param periodo the periodo
	 * @param totale the totale
	 */
	public TotalePeriodo(PeriodoVO periodo, RiepilogoTotale totale) {
		this.periodo = periodo;
		this.totale = totale;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public PeriodoVO getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(PeriodoVO periodo) {
		this.periodo = periodo;
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
