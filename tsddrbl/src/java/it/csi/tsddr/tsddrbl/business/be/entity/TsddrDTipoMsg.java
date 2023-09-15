/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_d_tipi_msg database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_tipi_msg")
@NamedQuery(name = "TsddrDTipoMsg.findAll", query = "SELECT t FROM TsddrDTipoMsg t")
public class TsddrDTipoMsg extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_msg", unique = true, nullable = false)
	private Long idTipoMsg;

	@Column(name = "desc_tipo_msg", nullable = false, length = 100)
	private String descTipoMsg;

	// bi-directional many-to-one association to TsddrTMessaggio
	@OneToMany(mappedBy = "tipoMsg")
	private List<TsddrTMessaggio> messaggi;

	/**
	 * Instantiates a new tsddr D tipo msg.
	 */
	public TsddrDTipoMsg() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id tipo msg.
	 *
	 * @return the id tipo msg
	 */
	public Long getIdTipoMsg() {
		return this.idTipoMsg;
	}

	/**
	 * Sets the id tipo msg.
	 *
	 * @param idTipoMsg the new id tipo msg
	 */
	public void setIdTipoMsg(Long idTipoMsg) {
		this.idTipoMsg = idTipoMsg;
	}

	/**
	 * Gets the desc tipo msg.
	 *
	 * @return the desc tipo msg
	 */
	public String getDescTipoMsg() {
		return this.descTipoMsg;
	}

	/**
	 * Sets the desc tipo msg.
	 *
	 * @param descTipoMsg the new desc tipo msg
	 */
	public void setDescTipoMsg(String descTipoMsg) {
		this.descTipoMsg = descTipoMsg;
	}

	/**
	 * Gets the messaggi.
	 *
	 * @return the messaggi
	 */
	public List<TsddrTMessaggio> getMessaggi() {
		return this.messaggi;
	}

	/**
	 * Sets the messaggi.
	 *
	 * @param messaggi the new messaggi
	 */
	public void setMessaggi(List<TsddrTMessaggio> messaggi) {
		this.messaggi = messaggi;
	}

	/**
	 * Adds the messaggi.
	 *
	 * @param messaggi the messaggi
	 * @return the tsddr T messaggio
	 */
	public TsddrTMessaggio addMessaggi(TsddrTMessaggio messaggi) {
		getMessaggi().add(messaggi);
		messaggi.setTipoMsg(this);

		return messaggi;
	}

	/**
	 * Removes the messaggi.
	 *
	 * @param messaggi the messaggi
	 * @return the tsddr T messaggio
	 */
	public TsddrTMessaggio removeMessaggi(TsddrTMessaggio messaggi) {
		getMessaggi().remove(messaggi);
		messaggi.setTipoMsg(null);

		return messaggi;
	}

}