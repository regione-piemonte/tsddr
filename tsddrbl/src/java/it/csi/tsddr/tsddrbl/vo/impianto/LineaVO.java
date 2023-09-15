/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class LineaVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idLinea;
	private String descLinea;
	private String codLinea;
	private Boolean flagSottoLinea;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	/**
	 * Gets the id linea.
	 *
	 * @return the id linea
	 */
	public Long getIdLinea() {
		return idLinea;
	}

	/**
	 * Sets the id linea.
	 *
	 * @param idLinea the new id linea
	 */
	public void setIdLinea(Long idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * Gets the desc linea.
	 *
	 * @return the desc linea
	 */
	public String getDescLinea() {
		return descLinea;
	}

	/**
	 * Sets the desc linea.
	 *
	 * @param descLinea the new desc linea
	 */
	public void setDescLinea(String descLinea) {
		this.descLinea = descLinea;
	}

	
	/**
	 * Gets the cod linea.
	 *
	 * @return the cod linea
	 */
	public String getCodLinea() {
        return codLinea;
    }

    /**
     * Sets the cod linea.
     *
     * @param codLinea the new cod linea
     */
    public void setCodLinea(String codLinea) {
        this.codLinea = codLinea;
    }

    /**
	 * Gets the flag sotto linea.
	 *
	 * @return the flag sotto linea
	 */
	public Boolean getFlagSottoLinea() {
		return flagSottoLinea;
	}

	/**
	 * Sets the flag sotto linea.
	 *
	 * @param flagSottoLinea the new flag sotto linea
	 */
	public void setFlagSottoLinea(Boolean flagSottoLinea) {
		this.flagSottoLinea = flagSottoLinea;
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
