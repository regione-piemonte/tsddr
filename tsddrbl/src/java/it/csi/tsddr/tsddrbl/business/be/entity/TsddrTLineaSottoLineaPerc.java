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
 * The persistent class for the tsddr_t_linea_sotto_linea_perc database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_linea_sotto_linea_perc")
@NamedQuery(name = "TsddrTLineaSottoLineaPerc.findAll", query = "SELECT t FROM TsddrTLineaSottoLineaPerc t")
public class TsddrTLineaSottoLineaPerc extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_linea_sotto_linea_perc", unique = true, nullable = false)
	private Long idLineaSottoLineaPerc;

	@Column(name = "per_max_scarto", nullable = false)
	private Integer perMaxScarto;

	@Column(name = "per_min_recupero", nullable = false)
	private Integer perMinRecupero;

	// bi-directional many-to-one association to TsddrTLinea
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_linea")
	private TsddrTLinea linea;

	// bi-directional many-to-one association to TsddrTSottoLinea
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sotto_linea")
	private TsddrTSottoLinea sottoLinee;

	/**
	 * Instantiates a new tsddr T linea sotto linea perc.
	 */
	public TsddrTLineaSottoLineaPerc() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id linea sotto linea perc.
	 *
	 * @return the id linea sotto linea perc
	 */
	public Long getIdLineaSottoLineaPerc() {
		return this.idLineaSottoLineaPerc;
	}

	/**
	 * Sets the id linea sotto linea perc.
	 *
	 * @param idLineaSottoLineaPerc the new id linea sotto linea perc
	 */
	public void setIdLineaSottoLineaPerc(Long idLineaSottoLineaPerc) {
		this.idLineaSottoLineaPerc = idLineaSottoLineaPerc;
	}

	/**
	 * Gets the per max scarto.
	 *
	 * @return the per max scarto
	 */
	public Integer getPerMaxScarto() {
		return this.perMaxScarto;
	}

	/**
	 * Sets the per max scarto.
	 *
	 * @param perMaxScarto the new per max scarto
	 */
	public void setPerMaxScarto(Integer perMaxScarto) {
		this.perMaxScarto = perMaxScarto;
	}

	/**
	 * Gets the per min recupero.
	 *
	 * @return the per min recupero
	 */
	public Integer getPerMinRecupero() {
		return this.perMinRecupero;
	}

	/**
	 * Sets the per min recupero.
	 *
	 * @param perMinRecupero the new per min recupero
	 */
	public void setPerMinRecupero(Integer perMinRecupero) {
		this.perMinRecupero = perMinRecupero;
	}

	/**
	 * Gets the linea.
	 *
	 * @return the linea
	 */
	public TsddrTLinea getLinea() {
		return this.linea;
	}

	/**
	 * Sets the linea.
	 *
	 * @param linea the new linea
	 */
	public void setLinea(TsddrTLinea linea) {
		this.linea = linea;
	}

	/**
	 * Gets the sotto linee.
	 *
	 * @return the sotto linee
	 */
	public TsddrTSottoLinea getSottoLinee() {
		return this.sottoLinee;
	}

	/**
	 * Sets the sotto linee.
	 *
	 * @param sottoLinee the new sotto linee
	 */
	public void setSottoLinee(TsddrTSottoLinea sottoLinee) {
		this.sottoLinee = sottoLinee;
	}

}