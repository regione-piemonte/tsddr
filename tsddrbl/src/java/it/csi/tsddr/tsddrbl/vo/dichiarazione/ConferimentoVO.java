/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.unitamisura.UnitaMisuraVO;

public class ConferimentoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idConferimento;
	private Long anno;
	private Double importo;
	private Double quantita;
	private PeriodoVO periodo;
	private UnitaMisuraVO unitaMisura;
	@JsonProperty(access = Access.WRITE_ONLY)
	private RifiutoTariffaVO rifiutoTariffa;
	
	private transient boolean isJustBeenInsert;

	/**
	 * Gets the id conferimento.
	 *
	 * @return the id conferimento
	 */
	public Long getIdConferimento() {
		return idConferimento;
	}

	/**
	 * Sets the id conferimento.
	 *
	 * @param idConferimento the new id conferimento
	 */
	public void setIdConferimento(Long idConferimento) {
		this.idConferimento = idConferimento;
	}

	/**
	 * Gets the anno.
	 *
	 * @return the anno
	 */
	public Long getAnno() {
		return anno;
	}

	/**
	 * Sets the anno.
	 *
	 * @param anno the new anno
	 */
	public void setAnno(Long anno) {
		this.anno = anno;
	}

	/**
	 * Gets the importo.
	 *
	 * @return the importo
	 */
	public Double getImporto() {
		return importo;
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
	 * Gets the quantita.
	 *
	 * @return the quantita
	 */
	public Double getQuantita() {
		return quantita;
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

    public boolean isJustBeenInsert() {
        return isJustBeenInsert;
    }

    public void setJustBeenInsert(boolean isJustBeenInsert) {
        this.isJustBeenInsert = isJustBeenInsert;
    }

}