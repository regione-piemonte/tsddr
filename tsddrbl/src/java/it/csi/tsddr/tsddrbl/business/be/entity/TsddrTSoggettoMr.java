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
 * The persistent class for the tsddr_t_soggetti_mr database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_soggetti_mr")
@NamedQuery(name = "TsddrTSoggettoMr.findAll", query = "SELECT t FROM TsddrTSoggettoMr t")
public class TsddrTSoggettoMr extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_soggetti_mr", unique = true, nullable = false)
	private Long idSoggettiMr;

	@Column(name = "cod_fisc_partiva", nullable = false, length = 16)
	private String codFiscPartiva;

	@Column(name = "rag_sociale", nullable = false, length = 100)
	private String ragSociale;

	@Column(name = "obb_ragg", nullable = false, length = 2)
	private String obbRagg;

	// bi-directional many-to-one association to TsddrTDichAnnuale
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dich_annuale", nullable = false)
	private TsddrTDichAnnuale dichAnnuale;

	/**
	 * Instantiates a new tsddr T soggetto mr.
	 */
	public TsddrTSoggettoMr() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id soggetti mr.
	 *
	 * @return the id soggetti mr
	 */
	public Long getIdSoggettiMr() {
		return this.idSoggettiMr;
	}

	/**
	 * Sets the id soggetti mr.
	 *
	 * @param idSoggettiMr the new id soggetti mr
	 */
	public void setIdSoggettiMr(Long idSoggettiMr) {
		this.idSoggettiMr = idSoggettiMr;
	}

	/**
	 * Gets the cod fisc partiva.
	 *
	 * @return the cod fisc partiva
	 */
	public String getCodFiscPartiva() {
		return this.codFiscPartiva;
	}

	/**
	 * Sets the cod fisc partiva.
	 *
	 * @param codFiscPartiva the new cod fisc partiva
	 */
	public void setCodFiscPartiva(String codFiscPartiva) {
		this.codFiscPartiva = codFiscPartiva;
	}

	/**
	 * Gets the rag sociale.
	 *
	 * @return the rag sociale
	 */
	public String getRagSociale() {
		return this.ragSociale;
	}

	/**
	 * Sets the rag sociale.
	 *
	 * @param ragSociale the new rag sociale
	 */
	public void setRagSociale(String ragSociale) {
		this.ragSociale = ragSociale;
	}

	/**
	 * Gets the obb ragg.
	 *
	 * @return the obb ragg
	 */
	public String getObbRagg() {
		return obbRagg;
	}

	/**
	 * Sets the obb ragg.
	 *
	 * @param obbRagg the new obb ragg
	 */
	public void setObbRagg(String obbRagg) {
		this.obbRagg = obbRagg;
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