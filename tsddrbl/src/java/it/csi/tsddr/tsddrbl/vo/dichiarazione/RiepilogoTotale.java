/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class RiepilogoTotale extends AbstractVO {

	private static final long serialVersionUID = -1542457100847481390L;

	private Double quantita;

	private Double importo;
	
	/**
	 * Instantiates a new riepilogo totale.
	 */
	public RiepilogoTotale() {
	}

	/**
	 * Instantiates a new riepilogo totale.
	 *
	 * @param quantita the quantita
	 * @param importo the importo
	 */
	public RiepilogoTotale(Double quantita, Double importo) {
		this.quantita = quantita;
		this.importo = importo;
	}

	/**
	 * Gets the quantita.
	 *
	 * @return the quantita
	 */
	public Double getQuantita() {
	    return BigDecimal.valueOf(this.quantita)
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
	}

	/**
	 * Sets the quantita.
	 *
	 * @param quantita the new quantita
	 */
	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	/**
     * Gets the importo.
     *
     * @return the importo
     */
    public Double getImporto() {
        return BigDecimal.valueOf(this.importo)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

	/**
	 * Sets the importo.
	 *
	 * @param importo the new importo
	 */
	public void setImporto(Double importo) {
		this.importo = importo;
	}
	
	/**
	 * Adds the quantita.
	 *
	 * @param quantita the quantita
	 */
	public void addQuantita(Double quantita) {
		this.quantita += (quantita == null ? Double.valueOf(0) : quantita);
	}
	
	/**
	 * Adds the importo.
	 *
	 * @param importo the importo
	 */
	public void addImporto(Double importo) {
		this.importo += (importo == null ? Double.valueOf(0) : importo);
	}
	
	/**
	 * Adds the from conferimento.
	 *
	 * @param conferimento the conferimento
	 * @return the riepilogo totale
	 */
	public RiepilogoTotale addFromConferimento(ConferimentoVO conferimento) {
		addQuantita(conferimento.getQuantita());
		addImporto(conferimento.getImporto());
		return this;
	}
	
	/**
	 * Sum.
	 *
	 * @param tot1 the tot 1
	 * @param tot2 the tot 2
	 * @return the riepilogo totale
	 */
	public static RiepilogoTotale sum(RiepilogoTotale tot1, RiepilogoTotale tot2) {
		return new RiepilogoTotale(Double.sum(tot1.getQuantita(), tot2.getQuantita()),
				Double.sum(tot1.getImporto(), tot2.getImporto()));
	}

}
