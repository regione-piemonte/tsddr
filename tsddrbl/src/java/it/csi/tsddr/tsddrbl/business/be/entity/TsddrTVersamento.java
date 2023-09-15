/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tsddr_t_versamenti database table.
 * 
 */
@Entity
@Table(name="tsddr_t_versamenti")
@NamedQuery(name="TsddrTVersamento.findAll", query="SELECT t FROM TsddrTVersamento t")
public class TsddrTVersamento extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_versamento", unique=true, nullable=false)
	private Long idVersamento;

	@Temporal(TemporalType.DATE)
	@Column(name="data_versamento")
	private Date dataVersamento;

	@Column(name="importo_versato", precision=10, scale=2)
	private BigDecimal importoVersato;

	//bi-directional many-to-one association to TsddrDPeriodo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_periodo", nullable=false)
	private TsddrDPeriodo periodo;

	//bi-directional many-to-one association to TsddrTDichAnnuale
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dich_annuale", nullable=false)
	private TsddrTDichAnnuale dichAnnuale;

	/**
	 * Instantiates a new tsddr T versamento.
	 */
	public TsddrTVersamento() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id versamento.
	 *
	 * @return the id versamento
	 */
	public Long getIdVersamento() {
		return this.idVersamento;
	}

	/**
	 * Sets the id versamento.
	 *
	 * @param idVersamento the new id versamento
	 */
	public void setIdVersamento(Long idVersamento) {
		this.idVersamento = idVersamento;
	}

	/**
	 * Gets the data versamento.
	 *
	 * @return the data versamento
	 */
	public Date getDataVersamento() {
		return this.dataVersamento;
	}

	/**
	 * Sets the data versamento.
	 *
	 * @param dataVersamento the new data versamento
	 */
	public void setDataVersamento(Date dataVersamento) {
		this.dataVersamento = dataVersamento;
	}

	/**
	 * Gets the importo versato.
	 *
	 * @return the importo versato
	 */
	public BigDecimal getImportoVersato() {
		return this.importoVersato;
	}

	/**
	 * Sets the importo versato.
	 *
	 * @param importoVersato the new importo versato
	 */
	public void setImportoVersato(BigDecimal importoVersato) {
		this.importoVersato = importoVersato;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public TsddrDPeriodo getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(TsddrDPeriodo periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the dich annuale.
	 *
	 * @return the dich annuale
	 */
	public TsddrTDichAnnuale getDichAnnuale() {
		return dichAnnuale;
	}

	/**
	 * Sets the dich annuale.
	 *
	 * @param dichAnnuale the new dich annuale
	 */
	public void setDichAnnuale(TsddrTDichAnnuale dichAnnuale) {
		this.dichAnnuale = dichAnnuale;
	}

}