/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.indirizzo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class IndirizzoVO extends AbstractVO {

	private static final long serialVersionUID = 7914928215577647041L;

	private Long idIndirizzo;
	private Long originalId;
	private Long versione;
	private String indirizzo;
	private String cap;
	private ComuneVO comune;
	private NazioneVO nazione;
	private SedimeVO sedime;
	private TipoIndirizzoVO tipoIndirizzo;
	
	private Boolean hasBeenUpdated;

	/**
	 * @return the idIndirizzo
	 */
	public Long getIdIndirizzo() {
		return idIndirizzo;
	}

	/**
	 * @param idIndirizzo the idIndirizzo to set
	 */
	public void setIdIndirizzo(Long idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	/**
	 * Gets the original id.
	 *
	 * @return the original id
	 */
	public Long getOriginalId() {
		return originalId;
	}

	/**
	 * Sets the original id.
	 *
	 * @param originalId the new original id
	 */
	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	/**
	 * @return the versione
	 */
	public Long getVersione() {
		return versione;
	}

	/**
	 * @param versione the versione to set
	 */
	public void setVersione(Long versione) {
		this.versione = versione;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the comune
	 */
	public ComuneVO getComune() {
		return comune;
	}

	/**
	 * @param comune the comune to set
	 */
	public void setComune(ComuneVO comune) {
		this.comune = comune;
	}

	/**
	 * @return the nazione
	 */
	public NazioneVO getNazione() {
		return nazione;
	}

	/**
	 * @param nazione the nazione to set
	 */
	public void setNazione(NazioneVO nazione) {
		this.nazione = nazione;
	}

	/**
	 * @return the sedime
	 */
	public SedimeVO getSedime() {
		return sedime;
	}

	/**
	 * @param sedime the sedime to set
	 */
	public void setSedime(SedimeVO sedime) {
		this.sedime = sedime;
	}

	/**
	 * @return the tipoIndirizzo
	 */
	public TipoIndirizzoVO getTipoIndirizzo() {
		return tipoIndirizzo;
	}

	/**
	 * @param tipoIndirizzo the tipoIndirizzo to set
	 */
	public void setTipoIndirizzo(TipoIndirizzoVO tipoIndirizzo) {
		this.tipoIndirizzo = tipoIndirizzo;
	}

    /**
     * Gets the checks for been updated.
     *
     * @return the checks for been updated
     */
    public Boolean getHasBeenUpdated() {
        return hasBeenUpdated;
    }

    /**
     * Sets the checks for been updated.
     *
     * @param hasBeenUpdated the new checks for been updated
     */
    public void setHasBeenUpdated(Boolean hasBeenUpdated) {
        this.hasBeenUpdated = hasBeenUpdated;
    }
	
}
