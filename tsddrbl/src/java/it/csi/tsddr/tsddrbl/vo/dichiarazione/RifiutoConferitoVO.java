/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.List;

import it.csi.tsddr.tsddrbl.vo.unitamisura.UnitaMisuraVO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RifiutoConferitoVO {

	private RifiutoTariffaVO rifiutoTariffa;
	
	private UnitaMisuraVO unitaMisura;

	private List<ConferimentoVO> conferimenti;

	private JRBeanCollectionDataSource conferimentiReport;

	private RiepilogoTotale totale;
	
	/**
	 * Instantiates a new rifiuto conferito VO.
	 */
	public RifiutoConferitoVO() {
	}

	/**
	 * Instantiates a new rifiuto conferito VO.
	 *
	 * @param rifiutoTariffa the rifiuto tariffa
	 * @param unitaMisura the unita misura
	 * @param conferimenti the conferimenti
	 * @param totale the totale
	 */
	public RifiutoConferitoVO(RifiutoTariffaVO rifiutoTariffa, UnitaMisuraVO unitaMisura, List<ConferimentoVO> conferimenti,
			RiepilogoTotale totale) {
		this.rifiutoTariffa = rifiutoTariffa;
		this.unitaMisura = unitaMisura;
		this.conferimenti = conferimenti;
		this.totale = totale;
		this.conferimentiReport = new JRBeanCollectionDataSource(conferimenti);
	}

	/**
	 * Gets the rifiuto tariffa.
	 *
	 * @return the rifiuto tariffa
	 */
	public RifiutoTariffaVO getRifiutoTariffa() {
		return rifiutoTariffa;
	}

	/**
	 * Sets the rifiuto tariffa.
	 *
	 * @param rifiutoTariffa the new rifiuto tariffa
	 */
	public void setRifiutoTariffa(RifiutoTariffaVO rifiutoTariffa) {
		this.rifiutoTariffa = rifiutoTariffa;
	}

	/**
	 * Gets the conferimenti.
	 *
	 * @return the conferimenti
	 */
	public List<ConferimentoVO> getConferimenti() {
		return conferimenti;
	}

	/**
	 * Sets the conferimenti.
	 *
	 * @param conferimenti the new conferimenti
	 */
	public void setConferimenti(List<ConferimentoVO> conferimenti) {
		this.conferimenti = conferimenti;
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

	/**
	 * Gets the unita misura.
	 *
	 * @return the unita misura
	 */
	public UnitaMisuraVO getUnitaMisura() {
		return unitaMisura;
	}

	/**
	 * Sets the unita misura.
	 *
	 * @param unitaMisura the new unita misura
	 */
	public void setUnitaMisura(UnitaMisuraVO unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	/**
	 * Gets the conferimenti report.
	 *
	 * @return the conferimenti report
	 */
	public JRBeanCollectionDataSource getConferimentiReport() {
		return conferimentiReport;
	}

	/**
	 * Sets the conferimenti report.
	 *
	 * @param conferimentiReport the new conferimenti report
	 */
	public void setConferimentiReport(JRBeanCollectionDataSource conferimentiReport) {
		this.conferimentiReport = conferimentiReport;
	}

}
