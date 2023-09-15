/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_note_info database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_note_info")
@NamedQuery(name = "TsddrTNotaInfo.findAll", query = "SELECT t FROM TsddrTNotaInfo t")
public class TsddrTNotaInfo extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nota_info", unique = true, nullable = false)
	private Long idNotaInfo;

	@Column(name = "cod_nota_info", nullable = false, length = 20)
	private String codNotaInfo;

	@Column(name = "etichetta_campo", nullable = false, length = 100)
	private String etichettaCampo;

	private byte[] media;

	@Column(name = "nome_form", length = 100)
	private String nomeForm;

	@Column(name = "testo_info", nullable = false, length = 500)
	private String testoInfo;

	@Column(name = "titolo_info", length = 200)
	private String titoloInfo;

	/**
	 * Instantiates a new tsddr T nota info.
	 */
	public TsddrTNotaInfo() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id nota info.
	 *
	 * @return the id nota info
	 */
	public Long getIdNotaInfo() {
		return this.idNotaInfo;
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
		return this.codNotaInfo;
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
		return this.etichettaCampo;
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
		return this.media;
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
		return this.nomeForm;
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
		return this.testoInfo;
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
		return this.titoloInfo;
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