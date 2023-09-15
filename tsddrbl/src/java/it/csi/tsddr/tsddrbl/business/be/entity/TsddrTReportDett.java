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
 * The persistent class for the tsddr_t_report_dett database table.
 * 
 */
@Entity
@Table(name="tsddr_t_report_dett")
@NamedQuery(name="TsddrTReportDett.findAll", query="SELECT t FROM TsddrTReportDett t")
public class TsddrTReportDett extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dettaglio")
	private Long idDettaglio;

	@Column(name="cod_campo", length=50, nullable=false)
	private String codCampo;

	@Column(name="firma")
	private byte[] firma;

	@Column(name="logo")
	private byte[] logo;

	@Column(name="testo", length=1500)
	private String testo;

	//bi-directional many-to-one association to TsddrDTipoCampo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_campo", nullable=false)
	private TsddrDTipoCampo tipoCampo;

	//bi-directional many-to-one association to TsddrTReport
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_report", nullable=false)
	private TsddrTReport report;

	/**
	 * Instantiates a new tsddr T report dett.
	 */
	public TsddrTReportDett() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id dettaglio.
	 *
	 * @return the id dettaglio
	 */
	public Long getIdDettaglio() {
		return this.idDettaglio;
	}

	/**
	 * Sets the id dettaglio.
	 *
	 * @param idDettaglio the new id dettaglio
	 */
	public void setIdDettaglio(Long idDettaglio) {
		this.idDettaglio = idDettaglio;
	}

	/**
	 * Gets the cod campo.
	 *
	 * @return the cod campo
	 */
	public String getCodCampo() {
		return codCampo;
	}

	/**
	 * Sets the cod campo.
	 *
	 * @param codCampo the new cod campo
	 */
	public void setCodCampo(String codCampo) {
		this.codCampo = codCampo;
	}

	/**
	 * Gets the firma.
	 *
	 * @return the firma
	 */
	public byte[] getFirma() {
		return this.firma;
	}

	/**
	 * Sets the firma.
	 *
	 * @param firma the new firma
	 */
	public void setFirma(byte[] firma) {
		this.firma = firma;
	}

	/**
	 * Gets the logo.
	 *
	 * @return the logo
	 */
	public byte[] getLogo() {
		return this.logo;
	}

	/**
	 * Sets the logo.
	 *
	 * @param logo the new logo
	 */
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	/**
	 * Gets the testo.
	 *
	 * @return the testo
	 */
	public String getTesto() {
		return this.testo;
	}

	/**
	 * Sets the testo.
	 *
	 * @param testo the new testo
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}

	/**
	 * Gets the tipo campo.
	 *
	 * @return the tipo campo
	 */
	public TsddrDTipoCampo getTipoCampo() {
		return this.tipoCampo;
	}

	/**
	 * Sets the tipo campo.
	 *
	 * @param tsddrDTipiCampi the new tipo campo
	 */
	public void setTipoCampo(TsddrDTipoCampo tsddrDTipiCampi) {
		this.tipoCampo = tsddrDTipiCampi;
	}

	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	public TsddrTReport getReport() {
		return this.report;
	}

	/**
	 * Sets the report.
	 *
	 * @param tsddrTReport the new report
	 */
	public void setReport(TsddrTReport tsddrTReport) {
		this.report = tsddrTReport;
	}

}