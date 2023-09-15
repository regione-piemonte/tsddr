/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.profilo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class ProfiloVO extends AbstractVO {

	private static final long serialVersionUID = 1L;
	
	private Long idProfilo;
	private String descProfilo;
	@JsonProperty(value = "tipologiaProfilo")
	private TipologiaProfiloVO tipologiaProfiloVO;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	@JsonProperty(value = "deletable")
	private boolean isDeletable;

	/**
	 * Instantiates a new profilo VO.
	 */
	public ProfiloVO() {
	}

	/**
	 * Instantiates a new profilo VO.
	 *
	 * @param idProfilo the id profilo
	 * @param descProfilo the desc profilo
	 * @param idTipoProfilo the id tipo profilo
	 * @param descTipoProfilo the desc tipo profilo
	 * @param dataInizioValidita the data inizio validita
	 * @param dataFineValidita the data fine validita
	 */
	public ProfiloVO(Long idProfilo, String descProfilo, Long idTipoProfilo, String descTipoProfilo,
			Date dataInizioValidita, Date dataFineValidita) {
		this.idProfilo = idProfilo;
		this.descProfilo = descProfilo;
		this.tipologiaProfiloVO = new TipologiaProfiloVO(idTipoProfilo, descTipoProfilo);
		this.dataInizioValidita = dataInizioValidita;
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * Gets the id profilo.
	 *
	 * @return the id profilo
	 */
	public Long getIdProfilo() {
		return idProfilo;
	}

	/**
	 * Sets the id profilo.
	 *
	 * @param idProfilo the new id profilo
	 */
	public void setIdProfilo(Long idProfilo) {
		this.idProfilo = idProfilo;
	}

	/**
	 * Gets the desc profilo.
	 *
	 * @return the desc profilo
	 */
	public String getDescProfilo() {
		return descProfilo;
	}

	/**
	 * Sets the desc profilo.
	 *
	 * @param descProfilo the new desc profilo
	 */
	public void setDescProfilo(String descProfilo) {
		this.descProfilo = descProfilo;
	}

	/**
	 * Gets the tipologia profilo VO.
	 *
	 * @return the tipologia profilo VO
	 */
	public TipologiaProfiloVO getTipologiaProfiloVO() {
		return tipologiaProfiloVO;
	}

	/**
	 * Sets the tipologia profilo VO.
	 *
	 * @param tipologiaProfiloVO the new tipologia profilo VO
	 */
	public void setTipologiaProfiloVO(TipologiaProfiloVO tipologiaProfiloVO) {
		this.tipologiaProfiloVO = tipologiaProfiloVO;
	}

	/**
	 * Gets the data inizio validita.
	 *
	 * @return the data inizio validita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * Sets the data inizio validita.
	 *
	 * @param dataInizioValidita the new data inizio validita
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * Gets the data fine validita.
	 *
	 * @return the data fine validita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * Sets the data fine validita.
	 *
	 * @param dataFineValidita the new data fine validita
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * Checks if is deletable.
	 *
	 * @return true, if is deletable
	 */
	public boolean isDeletable() {
		return isDeletable;
	}

	/**
	 * Sets the deletable.
	 *
	 * @param isDeletable the new deletable
	 */
	public void setDeletable(boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

}
