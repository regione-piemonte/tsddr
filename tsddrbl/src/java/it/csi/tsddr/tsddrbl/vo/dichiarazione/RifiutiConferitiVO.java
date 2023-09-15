/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.List;

public class RifiutiConferitiVO {

	private List<RifiutoConferitoVO> rifiutiConferiti;

	private TotaliRifiutiConferiti totali;
	
	/**
	 * Instantiates a new rifiuti conferiti VO.
	 */
	public RifiutiConferitiVO() {
	}

	/**
	 * Instantiates a new rifiuti conferiti VO.
	 *
	 * @param rifiutiConferiti the rifiuti conferiti
	 * @param totali the totali
	 */
	public RifiutiConferitiVO(List<RifiutoConferitoVO> rifiutiConferiti, TotaliRifiutiConferiti totali) {
		this.rifiutiConferiti = rifiutiConferiti;
		this.totali = totali;
	}

	/**
	 * Gets the rifiuti conferiti.
	 *
	 * @return the rifiuti conferiti
	 */
	public List<RifiutoConferitoVO> getRifiutiConferiti() {
		return rifiutiConferiti;
	}

	/**
	 * Sets the rifiuti conferiti.
	 *
	 * @param rifiutiConferiti the new rifiuti conferiti
	 */
	public void setRifiutiConferiti(List<RifiutoConferitoVO> rifiutiConferiti) {
		this.rifiutiConferiti = rifiutiConferiti;
	}

	/**
	 * Gets the totali.
	 *
	 * @return the totali
	 */
	public TotaliRifiutiConferiti getTotali() {
		return totali;
	}

	/**
	 * Sets the totali.
	 *
	 * @param totali the new totali
	 */
	public void setTotali(TotaliRifiutiConferiti totali) {
		this.totali = totali;
	}

}
