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
 * The persistent class for the tsddr_t_report database table.
 * 
 */
@Entity
@Table(name="tsddr_t_report")
@NamedQuery(name="TsddrTReport.findAll", query="SELECT t FROM TsddrTReport t")
public class TsddrTReport extends AbstractValidableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_report", unique=true, nullable=false)
	private Long idReport;

	@Column(name="desc_report", nullable=false, length=50)
	private String descReport;

	@Column(name="xml_report", nullable=false)
	private String xmlReport;

	//bi-directional many-to-one association to TsddrTReportDett
	@OneToMany(mappedBy="report")
	private List<TsddrTReportDett> reportDett;

	/**
	 * Instantiates a new tsddr T report.
	 */
	public TsddrTReport() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id report.
	 *
	 * @return the id report
	 */
	public Long getIdReport() {
		return this.idReport;
	}

	/**
	 * Sets the id report.
	 *
	 * @param idReport the new id report
	 */
	public void setIdReport(Long idReport) {
		this.idReport = idReport;
	}

	/**
	 * Gets the desc report.
	 *
	 * @return the desc report
	 */
	public String getDescReport() {
		return this.descReport;
	}

	/**
	 * Sets the desc report.
	 *
	 * @param descReport the new desc report
	 */
	public void setDescReport(String descReport) {
		this.descReport = descReport;
	}

	/**
	 * Gets the xml report.
	 *
	 * @return the xml report
	 */
	public String getXmlReport() {
		return this.xmlReport;
	}

	/**
	 * Sets the xml report.
	 *
	 * @param xmlReport the new xml report
	 */
	public void setXmlReport(String xmlReport) {
		this.xmlReport = xmlReport;
	}

	/**
	 * Gets the report dett.
	 *
	 * @return the report dett
	 */
	public List<TsddrTReportDett> getReportDett() {
		return this.reportDett;
	}

	/**
	 * Sets the report dett.
	 *
	 * @param reportDett the new report dett
	 */
	public void setReportDett(List<TsddrTReportDett> reportDett) {
		this.reportDett = reportDett;
	}

	/**
	 * Adds the tsddr T report dett.
	 *
	 * @param reportDett the report dett
	 * @return the tsddr T report dett
	 */
	public TsddrTReportDett addTsddrTReportDett(TsddrTReportDett reportDett) {
		getReportDett().add(reportDett);
		reportDett.setReport(this);

		return reportDett;
	}

	/**
	 * Removes the tsddr T report dett.
	 *
	 * @param reportDett the report dett
	 * @return the tsddr T report dett
	 */
	public TsddrTReportDett removeTsddrTReportDett(TsddrTReportDett reportDett) {
		getReportDett().remove(reportDett);
		reportDett.setReport(null);

		return reportDett;
	}

}