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
 * The persistent class for the tsddr_t_atti database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_atti")
@NamedQuery(name = "TsddrTAtto.findAll", query = "SELECT t FROM TsddrTAtto t")
public class TsddrTAtto extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atto", unique = true, nullable = false)
	private Long idAtto;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_provvedimento")
	private Date dataProvvedimento;

	@Column(name = "num_provvedimento", nullable = false, length = 50)
	private String numProvvedimento;

	// bi-directional many-to-one association to TsddrDTipoProvvedimento
	@ManyToOne
	@JoinColumn(name = "id_tipo_provvedimento")
	private TsddrDTipoProvvedimento tipoProvvedimento;

	// bi-directional many-to-one association to TsddrTImpianto
	@ManyToOne
	@JoinColumn(name = "id_impianto")
	private TsddrTImpianto impianto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INIZIO_AUTORIZZAZIONE", nullable = false)
	private Date dataInizioAutorizzazione;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_FINE_AUTORIZZAZIONE")
	private Date dataFineAutorizzazione;

	/**
	 * Instantiates a new tsddr T atto.
	 */
	public TsddrTAtto() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id atto.
	 *
	 * @return the id atto
	 */
	public Long getIdAtto() {
		return this.idAtto;
	}

	/**
	 * Sets the id atto.
	 *
	 * @param idAtto the new id atto
	 */
	public void setIdAtto(Long idAtto) {
		this.idAtto = idAtto;
	}

	/**
	 * Gets the data provvedimento.
	 *
	 * @return the data provvedimento
	 */
	public Date getDataProvvedimento() {
		return this.dataProvvedimento;
	}

	/**
	 * Sets the data provvedimento.
	 *
	 * @param dataProvvedimento the new data provvedimento
	 */
	public void setDataProvvedimento(Date dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}

	/**
	 * Gets the num provvedimento.
	 *
	 * @return the num provvedimento
	 */
	public String getNumProvvedimento() {
		return this.numProvvedimento;
	}

	/**
	 * Sets the num provvedimento.
	 *
	 * @param numProvvedimento the new num provvedimento
	 */
	public void setNumProvvedimento(String numProvvedimento) {
		this.numProvvedimento = numProvvedimento;
	}

	/**
	 * Gets the tipo provvedimento.
	 *
	 * @return the tipo provvedimento
	 */
	public TsddrDTipoProvvedimento getTipoProvvedimento() {
		return this.tipoProvvedimento;
	}

	/**
	 * Sets the tipo provvedimento.
	 *
	 * @param tipoProvvedimento the new tipo provvedimento
	 */
	public void setTipoProvvedimento(TsddrDTipoProvvedimento tipoProvvedimento) {
		this.tipoProvvedimento = tipoProvvedimento;
	}

	/**
	 * Gets the impianto.
	 *
	 * @return the impianto
	 */
	public TsddrTImpianto getImpianto() {
		return impianto;
	}

	/**
	 * Sets the impianto.
	 *
	 * @param impianto the impianto to set
	 */
	public void setImpianto(TsddrTImpianto impianto) {
		this.impianto = impianto;
	}

	/**
	 * Gets the data inizio autorizzazione.
	 *
	 * @return the data inizio autorizzazione
	 */
	public Date getDataInizioAutorizzazione() {
		return dataInizioAutorizzazione;
	}

	/**
	 * Sets the data inizio autorizzazione.
	 *
	 * @param dataInizioAutorizzazione the new data inizio autorizzazione
	 */
	public void setDataInizioAutorizzazione(Date dataInizioAutorizzazione) {
		this.dataInizioAutorizzazione = dataInizioAutorizzazione;
	}

	/**
	 * Gets the data fine autorizzazione.
	 *
	 * @return the data fine autorizzazione
	 */
	public Date getDataFineAutorizzazione() {
		return dataFineAutorizzazione;
	}

	/**
	 * Sets the data fine autorizzazione.
	 *
	 * @param dataFineAutorizzazione the new data fine autorizzazione
	 */
	public void setDataFineAutorizzazione(Date dataFineAutorizzazione) {
		this.dataFineAutorizzazione = dataFineAutorizzazione;
	}

}