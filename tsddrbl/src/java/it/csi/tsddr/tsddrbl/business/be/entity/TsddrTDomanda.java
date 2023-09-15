/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tsddr_t_domande database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_domande")
@NamedQuery(name = "TsddrTDomanda.findAll", query = "SELECT t FROM TsddrTDomanda t")
public class TsddrTDomanda extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_domanda", unique = true, nullable = false)
	private Long idDomanda;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_richiesta", nullable = false)
	private Date dataRichiesta;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_risposta")
	private Date dataRisposta;

	@Column(name = "nota_lavorazione", length = 2000)
	private String notaLavorazione;

	@Column(name = "nota_utente", length = 1000)
	private String notaUtente;

	// bi-directional many-to-one association to TsddrDStatoDomanda
	@ManyToOne
	@JoinColumn(name = "id_stato_domanda")
	private TsddrDStatoDomanda statoDomanda;

	// bi-directional many-to-one association to TsddrTDatiSogg
	@ManyToOne
	@JoinColumn(name = "id_dati_sogg")
	private TsddrTDatiSogg datiSogg;

	// bi-directional many-to-one association to TsddrTGestore
	@ManyToOne
	@JoinColumn(name = "id_gestore")
	private TsddrTGestore gestore;

	/**
	 * Instantiates a new tsddr T domanda.
	 */
	public TsddrTDomanda() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id domanda.
	 *
	 * @return the id domanda
	 */
	public Long getIdDomanda() {
		return this.idDomanda;
	}

	/**
	 * Sets the id domanda.
	 *
	 * @param idDomanda the new id domanda
	 */
	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}

	/**
	 * Gets the data richiesta.
	 *
	 * @return the data richiesta
	 */
	public Date getDataRichiesta() {
		return this.dataRichiesta;
	}

	/**
	 * Sets the data richiesta.
	 *
	 * @param dataRichiesta the new data richiesta
	 */
	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	/**
	 * Gets the data risposta.
	 *
	 * @return the data risposta
	 */
	public Date getDataRisposta() {
		return this.dataRisposta;
	}

	/**
	 * Sets the data risposta.
	 *
	 * @param dataRisposta the new data risposta
	 */
	public void setDataRisposta(Date dataRisposta) {
		this.dataRisposta = dataRisposta;
	}

	/**
	 * Gets the nota lavorazione.
	 *
	 * @return the nota lavorazione
	 */
	public String getNotaLavorazione() {
		return this.notaLavorazione;
	}

	/**
	 * Sets the nota lavorazione.
	 *
	 * @param notaLavorazione the new nota lavorazione
	 */
	public void setNotaLavorazione(String notaLavorazione) {
		this.notaLavorazione = notaLavorazione;
	}

	/**
	 * Gets the nota utente.
	 *
	 * @return the nota utente
	 */
	public String getNotaUtente() {
		return this.notaUtente;
	}

	/**
	 * Sets the nota utente.
	 *
	 * @param notaUtente the new nota utente
	 */
	public void setNotaUtente(String notaUtente) {
		this.notaUtente = notaUtente;
	}

	/**
	 * Gets the stato domanda.
	 *
	 * @return the stato domanda
	 */
	public TsddrDStatoDomanda getStatoDomanda() {
		return this.statoDomanda;
	}

	/**
	 * Sets the stato domanda.
	 *
	 * @param statoDomanda the new stato domanda
	 */
	public void setStatoDomanda(TsddrDStatoDomanda statoDomanda) {
		this.statoDomanda = statoDomanda;
	}

	/**
	 * Gets the dati sogg.
	 *
	 * @return the dati sogg
	 */
	public TsddrTDatiSogg getDatiSogg() {
		return this.datiSogg;
	}

	/**
	 * Sets the dati sogg.
	 *
	 * @param datiSogg the new dati sogg
	 */
	public void setDatiSogg(TsddrTDatiSogg datiSogg) {
		this.datiSogg = datiSogg;
	}

	/**
	 * Gets the gestore.
	 *
	 * @return the gestore
	 */
	public TsddrTGestore getGestore() {
		return this.gestore;
	}

	/**
	 * Sets the gestore.
	 *
	 * @param gestore the new gestore
	 */
	public void setGestore(TsddrTGestore gestore) {
		this.gestore = gestore;
	}

}