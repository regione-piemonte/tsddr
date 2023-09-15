/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.gestore;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class NaturaGiuridicaVO extends AbstractVO {

	private static final long serialVersionUID = -8427274517182298826L;

	private Long idNaturaGiuridica;
	private String descNaturaGiuridica;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	/**
	 * @return the idNaturaGiuridica
	 */
	public Long getIdNaturaGiuridica() {
		return idNaturaGiuridica;
	}

	/**
	 * @param idNaturaGiuridica the idNaturaGiuridica to set
	 */
	public void setIdNaturaGiuridica(Long idNaturaGiuridica) {
		this.idNaturaGiuridica = idNaturaGiuridica;
	}

	/**
	 * @return the descNaturaGiuridica
	 */
	public String getDescNaturaGiuridica() {
		return descNaturaGiuridica;
	}

	/**
	 * @param descNaturaGiuridica the descNaturaGiuridica to set
	 */
	public void setDescNaturaGiuridica(String descNaturaGiuridica) {
		this.descNaturaGiuridica = descNaturaGiuridica;
	}

	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

}