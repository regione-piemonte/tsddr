/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_messaggi database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_messaggi")
@NamedQuery(name = "TsddrTMessaggio.findAll", query = "SELECT t FROM TsddrTMessaggio t")
public class TsddrTMessaggio extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_messaggio", unique = true, nullable = false)
	private Long idMessaggio;

	@Column(name = "cod_msg", nullable = false, length = 20)
	private String codMsg;

	@Column(name = "testo_msg", nullable = false, length = 500)
	private String testoMsg;

	@Column(name = "titolo_msg", nullable = false, length = 200)
	private String titoloMsg;

	// bi-directional many-to-one association to TsddrDTipoMsg
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_msg")
	private TsddrDTipoMsg tipoMsg;

	/**
	 * Instantiates a new tsddr T messaggio.
	 */
	public TsddrTMessaggio() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id messaggio.
	 *
	 * @return the id messaggio
	 */
	public Long getIdMessaggio() {
		return this.idMessaggio;
	}

	/**
	 * Sets the id messaggio.
	 *
	 * @param idMessaggio the new id messaggio
	 */
	public void setIdMessaggio(Long idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	/**
	 * Gets the cod msg.
	 *
	 * @return the cod msg
	 */
	public String getCodMsg() {
		return this.codMsg;
	}

	/**
	 * Sets the cod msg.
	 *
	 * @param codMsg the new cod msg
	 */
	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}

	/**
	 * Gets the testo msg.
	 *
	 * @return the testo msg
	 */
	public String getTestoMsg() {
		return this.testoMsg;
	}

	/**
	 * Sets the testo msg.
	 *
	 * @param testoMsg the new testo msg
	 */
	public void setTestoMsg(String testoMsg) {
		this.testoMsg = testoMsg;
	}

	/**
	 * Gets the titolo msg.
	 *
	 * @return the titolo msg
	 */
	public String getTitoloMsg() {
		return this.titoloMsg;
	}

	/**
	 * Sets the titolo msg.
	 *
	 * @param titoloMsg the new titolo msg
	 */
	public void setTitoloMsg(String titoloMsg) {
		this.titoloMsg = titoloMsg;
	}

	/**
	 * Gets the tipo msg.
	 *
	 * @return the tipo msg
	 */
	public TsddrDTipoMsg getTipoMsg() {
		return this.tipoMsg;
	}

	/**
	 * Sets the tipo msg.
	 *
	 * @param tipoMsg the new tipo msg
	 */
	public void setTipoMsg(TsddrDTipoMsg tipoMsg) {
		this.tipoMsg = tipoMsg;
	}

}