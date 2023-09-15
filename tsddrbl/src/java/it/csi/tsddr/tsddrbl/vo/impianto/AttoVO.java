/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class AttoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idAtto;
	private Date dataProvvedimento;
	private String numProvvedimento;
	private TipoProvvedimentoVO tipoProvvedimento;
	private Date dataInizioAutorizzazione;
	private Date dataFineAutorizzazione;

	/**
	 * Gets the id atto.
	 *
	 * @return the id atto
	 */
	public Long getIdAtto() {
		return idAtto;
	}

	/**
	 * Sets the id atto.
	 *
	 * @param idAtto the new id atto
	 */
	public void setIdAtto(Long idAtto) {
		this.idAtto = idAtto;
	}

	/**
	 * Gets the data provvedimento.
	 *
	 * @return the data provvedimento
	 */
	public Date getDataProvvedimento() {
		return dataProvvedimento;
	}

	/**
	 * Sets the data provvedimento.
	 *
	 * @param dataProvvedimento the new data provvedimento
	 */
	public void setDataProvvedimento(Date dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}

	/**
	 * Gets the num provvedimento.
	 *
	 * @return the num provvedimento
	 */
	public String getNumProvvedimento() {
		return numProvvedimento;
	}

	/**
	 * Sets the num provvedimento.
	 *
	 * @param numProvvedimento the new num provvedimento
	 */
	public void setNumProvvedimento(String numProvvedimento) {
		this.numProvvedimento = numProvvedimento;
	}

	/**
	 * Gets the tipo provvedimento.
	 *
	 * @return the tipo provvedimento
	 */
	public TipoProvvedimentoVO getTipoProvvedimento() {
		return tipoProvvedimento;
	}

	/**
	 * Sets the tipo provvedimento.
	 *
	 * @param tipoProvvedimento the new tipo provvedimento
	 */
	public void setTipoProvvedimento(TipoProvvedimentoVO tipoProvvedimento) {
		this.tipoProvvedimento = tipoProvvedimento;
	}

	/**
	 * Gets the data inizio autorizzazione.
	 *
	 * @return the data inizio autorizzazione
	 */
	public Date getDataInizioAutorizzazione() {
		return dataInizioAutorizzazione;
	}

	/**
	 * Sets the data inizio autorizzazione.
	 *
	 * @param dataInizioAutorizzazione the new data inizio autorizzazione
	 */
	public void setDataInizioAutorizzazione(Date dataInizioAutorizzazione) {
		this.dataInizioAutorizzazione = dataInizioAutorizzazione;
	}

	/**
	 * Gets the data fine autorizzazione.
	 *
	 * @return the data fine autorizzazione
	 */
	public Date getDataFineAutorizzazione() {
		return dataFineAutorizzazione;
	}

	/**
	 * Sets the data fine autorizzazione.
	 *
	 * @param dataFineAutorizzazione the new data fine autorizzazione
	 */
	public void setDataFineAutorizzazione(Date dataFineAutorizzazione) {
		this.dataFineAutorizzazione = dataFineAutorizzazione;
	}

}
