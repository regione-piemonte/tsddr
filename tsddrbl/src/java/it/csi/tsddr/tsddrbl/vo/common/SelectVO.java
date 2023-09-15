/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

/**
 * The type Select vo.
 */
public class SelectVO extends AbstractVO {
	
	@JsonIgnore
    private static final long serialVersionUID = -2641198993382229145L;

    private String id;

    @JsonProperty(value = "value")
    private String descrizione;
    @JsonProperty(value = "additionalValue")
    private String descrizioneAggiuntiva;
    
	/**
	 * Instantiates a new select VO.
	 */
	public SelectVO() {
	}

	/**
	 * Instantiates a new select VO.
	 *
	 * @param id the id
	 * @param descrizione the descrizione
	 * @param descrizioneAggiuntiva the descrizione aggiuntiva
	 */
	public SelectVO(String id, String descrizione, String descrizioneAggiuntiva) {
		this.id = id;
		this.descrizione = descrizione;
		this.descrizioneAggiuntiva = descrizioneAggiuntiva;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Gets the descrizione aggiuntiva.
	 *
	 * @return the descrizione aggiuntiva
	 */
	public String getDescrizioneAggiuntiva() {
		return descrizioneAggiuntiva;
	}

	/**
	 * Sets the descrizione aggiuntiva.
	 *
	 * @param descrizioneAggiuntiva the new descrizione aggiuntiva
	 */
	public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
		this.descrizioneAggiuntiva = descrizioneAggiuntiva;
	}

}