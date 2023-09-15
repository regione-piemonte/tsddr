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
 * The persistent class for the tsddr_email_d_t database table.
 * 
 */
@Entity
@Table(name = "tsddr_email_d_t")
@NamedQuery(name = "TsddrEmailDT.findAll", query = "SELECT t FROM TsddrEmailDT t")
public class TsddrEmailDT extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_casella", unique = true, nullable = false)
	private Long idCasella;

	@Column(nullable = false, length = 200)
	private String autenticazione;

	@Column(name = "casella_di_posta", nullable = false, length = 200)
	private String casellaDiPosta;

	@Column(name = "nome_server", nullable = false, length = 200)
	private String nomeServer;

	@Column(nullable = false, length = 200)
	private String porta;

	@Column(name = "sicurezza_conn", nullable = false, length = 200)
	private String sicurezzaConn;

	/**
	 * Instantiates a new tsddr email DT.
	 */
	public TsddrEmailDT() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id casella.
	 *
	 * @return the id casella
	 */
	public Long getIdCasella() {
		return this.idCasella;
	}

	/**
	 * Sets the id casella.
	 *
	 * @param idCasella the new id casella
	 */
	public void setIdCasella(Long idCasella) {
		this.idCasella = idCasella;
	}

	/**
	 * Gets the autenticazione.
	 *
	 * @return the autenticazione
	 */
	public String getAutenticazione() {
		return this.autenticazione;
	}

	/**
	 * Sets the autenticazione.
	 *
	 * @param autenticazione the new autenticazione
	 */
	public void setAutenticazione(String autenticazione) {
		this.autenticazione = autenticazione;
	}

	/**
	 * Gets the casella di posta.
	 *
	 * @return the casella di posta
	 */
	public String getCasellaDiPosta() {
		return this.casellaDiPosta;
	}

	/**
	 * Sets the casella di posta.
	 *
	 * @param casellaDiPosta the new casella di posta
	 */
	public void setCasellaDiPosta(String casellaDiPosta) {
		this.casellaDiPosta = casellaDiPosta;
	}

	/**
	 * Gets the nome server.
	 *
	 * @return the nome server
	 */
	public String getNomeServer() {
		return this.nomeServer;
	}

	/**
	 * Sets the nome server.
	 *
	 * @param nomeServer the new nome server
	 */
	public void setNomeServer(String nomeServer) {
		this.nomeServer = nomeServer;
	}

	/**
	 * Gets the porta.
	 *
	 * @return the porta
	 */
	public String getPorta() {
		return this.porta;
	}

	/**
	 * Sets the porta.
	 *
	 * @param porta the new porta
	 */
	public void setPorta(String porta) {
		this.porta = porta;
	}

	/**
	 * Gets the sicurezza conn.
	 *
	 * @return the sicurezza conn
	 */
	public String getSicurezzaConn() {
		return this.sicurezzaConn;
	}

	/**
	 * Sets the sicurezza conn.
	 *
	 * @param sicurezzaConn the new sicurezza conn
	 */
	public void setSicurezzaConn(String sicurezzaConn) {
		this.sicurezzaConn = sicurezzaConn;
	}

}