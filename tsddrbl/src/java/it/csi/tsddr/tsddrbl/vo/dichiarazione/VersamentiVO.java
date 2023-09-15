/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.List;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class VersamentiVO extends AbstractVO {

	private static final long serialVersionUID = -2917837500158899916L;

	private List<VersamentoVO> versamenti;

	private Double totale;

	/**
	 * Credito dichiarazione anno precedente
	 */
	private Double creditoAP;

	private Double debito;
	
	private Double credito;
	
	/**
	 * Instantiates a new versamenti VO.
	 */
	public VersamentiVO() {
	}

	/**
	 * Instantiates a new versamenti VO.
	 *
	 * @param versamenti the versamenti
	 * @param totale the totale
	 * @param creditoAP the credito AP
	 * @param debito the debito
	 * @param credito the credito
	 */
	public VersamentiVO(List<VersamentoVO> versamenti, Double totale, Double creditoAP, Double debito, Double credito) {
		this.versamenti = versamenti;
		this.totale = totale;
		this.creditoAP = creditoAP;
		this.debito = debito;
		this.credito = credito;
	}

	/**
	 * Gets the versamenti.
	 *
	 * @return the versamenti
	 */
	public List<VersamentoVO> getVersamenti() {
		return versamenti;
	}

	/**
	 * Sets the versamenti.
	 *
	 * @param versamenti the new versamenti
	 */
	public void setVersamenti(List<VersamentoVO> versamenti) {
		this.versamenti = versamenti;
	}

	/**
	 * Gets the totale.
	 *
	 * @return the totale
	 */
	public Double getTotale() {
		return totale;
	}

	/**
	 * Sets the totale.
	 *
	 * @param totale the new totale
	 */
	public void setTotale(Double totale) {
		this.totale = totale;
	}

	/**
	 * Gets the credito AP.
	 *
	 * @return the credito AP
	 */
	public Double getCreditoAP() {
		return creditoAP;
	}

	/**
	 * Sets the credito AP.
	 *
	 * @param creditoAP the new credito AP
	 */
	public void setCreditoAP(Double creditoAP) {
		this.creditoAP = creditoAP;
	}

	/**
	 * Gets the debito.
	 *
	 * @return the debito
	 */
	public Double getDebito() {
		return debito;
	}

	/**
	 * Sets the debito.
	 *
	 * @param debito the new debito
	 */
	public void setDebito(Double debito) {
		this.debito = debito;
	}

	/**
	 * Gets the credito.
	 *
	 * @return the credito
	 */
	public Double getCredito() {
		return credito;
	}

	/**
	 * Sets the credito.
	 *
	 * @param credito the new credito
	 */
	public void setCredito(Double credito) {
		this.credito = credito;
	}

}
