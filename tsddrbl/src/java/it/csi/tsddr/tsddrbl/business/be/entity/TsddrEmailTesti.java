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
 * The persistent class for the tsddr_email_testi database table.
 * 
 */
@Entity
@Table(name = "tsddr_email_testi")
@NamedQuery(name = "TsddrEmailTesti.findAll", query = "SELECT t FROM TsddrEmailTesti t")
public class TsddrEmailTesti extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_email", unique = true, nullable = false)
	private Long idEmail;

	@Column(nullable = false, length = 1000)
	private String corpo;

	@Column(name = "desc_email", nullable = false, length = 100)
	private String descEmail;

	@Column(length = 1000)
	private String destinatari;

	@Column(name = "fondo_email")
	private byte[] fondoEmail;

	@Column(nullable = false, length = 100)
	private String mittente;

	@Column(nullable = false, length = 200)
	private String oggetto;

	/**
	 * Instantiates a new tsddr email testi.
	 */
	public TsddrEmailTesti() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id email.
	 *
	 * @return the id email
	 */
	public Long getIdEmail() {
		return this.idEmail;
	}

	/**
	 * Sets the id email.
	 *
	 * @param idEmail the new id email
	 */
	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	/**
	 * Gets the corpo.
	 *
	 * @return the corpo
	 */
	public String getCorpo() {
		return this.corpo;
	}

	/**
	 * Sets the corpo.
	 *
	 * @param corpo the new corpo
	 */
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	/**
	 * Gets the desc email.
	 *
	 * @return the desc email
	 */
	public String getDescEmail() {
		return this.descEmail;
	}

	/**
	 * Sets the desc email.
	 *
	 * @param descEmail the new desc email
	 */
	public void setDescEmail(String descEmail) {
		this.descEmail = descEmail;
	}

	/**
	 * Gets the destinatari.
	 *
	 * @return the destinatari
	 */
	public String getDestinatari() {
		return this.destinatari;
	}

	/**
	 * Sets the destinatari.
	 *
	 * @param destinatari the new destinatari
	 */
	public void setDestinatari(String destinatari) {
		this.destinatari = destinatari;
	}

	/**
	 * Gets the fondo email.
	 *
	 * @return the fondo email
	 */
	public byte[] getFondoEmail() {
		return this.fondoEmail;
	}

	/**
	 * Sets the fondo email.
	 *
	 * @param fondoEmail the new fondo email
	 */
	public void setFondoEmail(byte[] fondoEmail) {
		this.fondoEmail = fondoEmail;
	}

	/**
	 * Gets the mittente.
	 *
	 * @return the mittente
	 */
	public String getMittente() {
		return this.mittente;
	}

	/**
	 * Sets the mittente.
	 *
	 * @param mittente the new mittente
	 */
	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	/**
	 * Gets the oggetto.
	 *
	 * @return the oggetto
	 */
	public String getOggetto() {
		return this.oggetto;
	}

	/**
	 * Sets the oggetto.
	 *
	 * @param oggetto the new oggetto
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

}