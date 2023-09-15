/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.gestore;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;

public class LegaleRappresentanteVO extends AbstractVO {

	private static final long serialVersionUID = 319174418825312645L;

	private Long idLegaleRapp;
	private String qualifica;
	private DatiSoggVO datiSogg;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	/**
	 * @return the idLegaleRapp
	 */
	public Long getIdLegaleRapp() {
		return idLegaleRapp;
	}

	/**
	 * @param idLegaleRapp the idLegaleRapp to set
	 */
	public void setIdLegaleRapp(Long idLegaleRapp) {
		this.idLegaleRapp = idLegaleRapp;
	}

	/**
	 * @return the qualifica
	 */
	public String getQualifica() {
		return qualifica;
	}

	/**
	 * @param qualifica the qualifica to set
	 */
	public void setQualifica(String qualifica) {
		this.qualifica = qualifica;
	}

	/**
	 * @return the datiSogg
	 */
	public DatiSoggVO getDatiSogg() {
		return datiSogg;
	}

	/**
	 * @param datiSogg the datiSogg to set
	 */
	public void setDatiSogg(DatiSoggVO datiSogg) {
		this.datiSogg = datiSogg;
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
