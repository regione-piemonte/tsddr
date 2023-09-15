/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import java.util.Date;
import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class SottoLineaVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idSottoLinea;
	private String descSottoLinea;
	private String codSottoLinea;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	/**
	 * Gets the id sotto linea.
	 *
	 * @return the id sotto linea
	 */
	public Long getIdSottoLinea() {
		return idSottoLinea;
	}

	/**
	 * Sets the id sotto linea.
	 *
	 * @param idSottoLinea the new id sotto linea
	 */
	public void setIdSottoLinea(Long idSottoLinea) {
		this.idSottoLinea = idSottoLinea;
	}

	/**
	 * Gets the desc sotto linea.
	 *
	 * @return the desc sotto linea
	 */
	public String getDescSottoLinea() {
		return descSottoLinea;
	}

	/**
	 * Sets the desc sotto linea.
	 *
	 * @param descSottoLinea the new desc sotto linea
	 */
	public void setDescSottoLinea(String descSottoLinea) {
		this.descSottoLinea = descSottoLinea;
	}
	
    /**
     * Gets the cod sotto linea.
     *
     * @return the cod sotto linea
     */
    public String getCodSottoLinea() {
        return codSottoLinea;
    }

    /**
     * Sets the cod sotto linea.
     *
     * @param codSottoLinea the new cod sotto linea
     */
    public void setCodSottoLinea(String codSottoLinea) {
        this.codSottoLinea = codSottoLinea;
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

}
