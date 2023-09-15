/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class VersamentoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idVersamento;
	private Date dataVersamento;
	private Double importoVersato;
	private PeriodoVO periodo;

	/**
	 * Gets the id versamento.
	 *
	 * @return the id versamento
	 */
	public Long getIdVersamento() {
		return idVersamento;
	}

	/**
	 * Sets the id versamento.
	 *
	 * @param idVersamento the new id versamento
	 */
	public void setIdVersamento(Long idVersamento) {
		this.idVersamento = idVersamento;
	}

	/**
	 * Gets the data versamento.
	 *
	 * @return the data versamento
	 */
	public Date getDataVersamento() {
		return dataVersamento;
	}

	/**
	 * Sets the data versamento.
	 *
	 * @param dataVersamento the new data versamento
	 */
	public void setDataVersamento(Date dataVersamento) {
		this.dataVersamento = dataVersamento;
	}

	/**
	 * Gets the importo versato.
	 *
	 * @return the importo versato
	 */
	public Double getImportoVersato() {
		return importoVersato;
	}

	/**
	 * Sets the importo versato.
	 *
	 * @param importoVersato the new importo versato
	 */
	public void setImportoVersato(Double importoVersato) {
		this.importoVersato = importoVersato;
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

}
