/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.notainfo;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class NotaInfoVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idNotaInfo;
	private String codNotaInfo;
	private String etichettaCampo;
	private byte[] media;
	private String nomeForm;
	private String testoInfo;
	private String titoloInfo;
	
	/**
	 * Instantiates a new nota info VO.
	 */
	public NotaInfoVO() {
	}

	/**
	 * Instantiates a new nota info VO.
	 *
	 * @param idNotaInfo the id nota info
	 * @param codNotaInfo the cod nota info
	 * @param etichettaCampo the etichetta campo
	 * @param media the media
	 * @param nomeForm the nome form
	 * @param testoInfo the testo info
	 * @param titoloInfo the titolo info
	 */
	public NotaInfoVO(Long idNotaInfo, String codNotaInfo, String etichettaCampo, byte[] media, String nomeForm,
			String testoInfo, String titoloInfo) {
		this.idNotaInfo = idNotaInfo;
		this.codNotaInfo = codNotaInfo;
		this.etichettaCampo = etichettaCampo;
		this.media = media;
		this.nomeForm = nomeForm;
		this.testoInfo = testoInfo;
		this.titoloInfo = titoloInfo;
	}

	/**
	 * Gets the id nota info.
	 *
	 * @return the id nota info
	 */
	public Long getIdNotaInfo() {
		return idNotaInfo;
	}

	/**
	 * Sets the id nota info.
	 *
	 * @param idNotaInfo the new id nota info
	 */
	public void setIdNotaInfo(Long idNotaInfo) {
		this.idNotaInfo = idNotaInfo;
	}

	/**
	 * Gets the cod nota info.
	 *
	 * @return the cod nota info
	 */
	public String getCodNotaInfo() {
		return codNotaInfo;
	}

	/**
	 * Sets the cod nota info.
	 *
	 * @param codNotaInfo the new cod nota info
	 */
	public void setCodNotaInfo(String codNotaInfo) {
		this.codNotaInfo = codNotaInfo;
	}

	/**
	 * Gets the etichetta campo.
	 *
	 * @return the etichetta campo
	 */
	public String getEtichettaCampo() {
		return etichettaCampo;
	}

	/**
	 * Sets the etichetta campo.
	 *
	 * @param etichettaCampo the new etichetta campo
	 */
	public void setEtichettaCampo(String etichettaCampo) {
		this.etichettaCampo = etichettaCampo;
	}

	/**
	 * Gets the media.
	 *
	 * @return the media
	 */
	public byte[] getMedia() {
		return media;
	}

	/**
	 * Sets the media.
	 *
	 * @param media the new media
	 */
	public void setMedia(byte[] media) {
		this.media = media;
	}

	/**
	 * Gets the nome form.
	 *
	 * @return the nome form
	 */
	public String getNomeForm() {
		return nomeForm;
	}

	/**
	 * Sets the nome form.
	 *
	 * @param nomeForm the new nome form
	 */
	public void setNomeForm(String nomeForm) {
		this.nomeForm = nomeForm;
	}

	/**
	 * Gets the testo info.
	 *
	 * @return the testo info
	 */
	public String getTestoInfo() {
		return testoInfo;
	}

	/**
	 * Sets the testo info.
	 *
	 * @param testoInfo the new testo info
	 */
	public void setTestoInfo(String testoInfo) {
		this.testoInfo = testoInfo;
	}

	/**
	 * Gets the titolo info.
	 *
	 * @return the titolo info
	 */
	public String getTitoloInfo() {
		return titoloInfo;
	}

	/**
	 * Sets the titolo info.
	 *
	 * @param titoloInfo the new titolo info
	 */
	public void setTitoloInfo(String titoloInfo) {
		this.titoloInfo = titoloInfo;
	}

}
