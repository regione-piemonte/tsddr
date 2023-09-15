/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.Objects;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class RifiutoTariffaVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idRifiutoTariffa;
	private String descrizione;
	private Boolean flagRiduzione;
	private String idTipoRifiuto;
	private String idTipologia2;
	private String idTipologia3;
	private Double importo;
	
	private transient String stringRiduzione;
	private transient String descrizioneCompleta;

	/**
	 * Gets the id rifiuto tariffa.
	 *
	 * @return the id rifiuto tariffa
	 */
	public Long getIdRifiutoTariffa() {
		return idRifiutoTariffa;
	}

	/**
	 * Sets the id rifiuto tariffa.
	 *
	 * @param idRifiutoTariffa the new id rifiuto tariffa
	 */
	public void setIdRifiutoTariffa(Long idRifiutoTariffa) {
		this.idRifiutoTariffa = idRifiutoTariffa;
	}

	/**
	 * Gets the descrizione.
	 *
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Sets the descrizione.
	 *
	 * @param descrizione the new descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Gets the flag riduzione.
	 *
	 * @return the flag riduzione
	 */
	public Boolean getFlagRiduzione() {
		return flagRiduzione;
	}

	/**
	 * Sets the flag riduzione.
	 *
	 * @param flagRiduzione the new flag riduzione
	 */
	public void setFlagRiduzione(Boolean flagRiduzione) {
		this.flagRiduzione = flagRiduzione;
	}

	/**
	 * Gets the id tipo rifiuto.
	 *
	 * @return the id tipo rifiuto
	 */
	public String getIdTipoRifiuto() {
		return idTipoRifiuto;
	}

	/**
	 * Sets the id tipo rifiuto.
	 *
	 * @param idTipoRifiuto the new id tipo rifiuto
	 */
	public void setIdTipoRifiuto(String idTipoRifiuto) {
		this.idTipoRifiuto = idTipoRifiuto;
	}

	/**
	 * Gets the id tipologia 2.
	 *
	 * @return the id tipologia 2
	 */
	public String getIdTipologia2() {
		return idTipologia2;
	}

	/**
	 * Sets the id tipologia 2.
	 *
	 * @param idTipologia2 the new id tipologia 2
	 */
	public void setIdTipologia2(String idTipologia2) {
		this.idTipologia2 = idTipologia2;
	}

	/**
	 * Gets the id tipologia 3.
	 *
	 * @return the id tipologia 3
	 */
	public String getIdTipologia3() {
		return idTipologia3;
	}

	/**
	 * Sets the id tipologia 3.
	 *
	 * @param idTipologia3 the new id tipologia 3
	 */
	public void setIdTipologia3(String idTipologia3) {
		this.idTipologia3 = idTipologia3;
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
	 * @return
	 */
	public String getStringRiduzione() {
        return stringRiduzione;
    }

    /**
     * @param stringRiduzione
     */
    public void setStringRiduzione(String stringRiduzione) {
        this.stringRiduzione = stringRiduzione;
    }
    
    /**
     * Gets the string descrizione completa.
     *
     * @return the string descrizione completa
     */
    public String getDescrizioneCompleta() {
        return descrizioneCompleta;
    }

    /**
     * Sets the string descrizione completa.
     *
     * @param stringDescrizioneCompleta the new string descrizione completa
     */
    public void setDescrizioneCompleta(String descrizioneCompleta) {
        this.descrizioneCompleta = descrizioneCompleta;
    }

    /**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idRifiutoTariffa);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RifiutoTariffaVO other = (RifiutoTariffaVO) obj;
		return Objects.equals(idRifiutoTariffa, other.idRifiutoTariffa);
	}
}
