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
 * The persistent class for the tsddr_d_tipi_campi database table.
 * 
 */
@Entity
@Table(name="tsddr_d_tipi_campi")
@NamedQuery(name="TsddrDTipoCampo.findAll", query="SELECT t FROM TsddrDTipoCampo t")
public class TsddrDTipoCampo extends AbstractValidableEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_campo", unique=true, nullable=false)
	private Long idTipoCampo;

	@Column(name="desc_tipo_campo", nullable=false, length=50)
	private String descTipoCampo;

	//bi-directional many-to-one association to TsddrTReportDett
	@OneToMany(mappedBy="tipoCampo")
	private List<TsddrTReportDett> reportDett;

	/**
	 * Instantiates a new tsddr D tipo campo.
	 */
	public TsddrDTipoCampo() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id tipo campo.
	 *
	 * @return the id tipo campo
	 */
	public Long getIdTipoCampo() {
		return this.idTipoCampo;
	}

	/**
	 * Sets the id tipo campo.
	 *
	 * @param idTipoCampo the new id tipo campo
	 */
	public void setIdTipoCampo(Long idTipoCampo) {
		this.idTipoCampo = idTipoCampo;
	}

	/**
	 * Gets the desc tipo campo.
	 *
	 * @return the desc tipo campo
	 */
	public String getDescTipoCampo() {
		return this.descTipoCampo;
	}

	/**
	 * Sets the desc tipo campo.
	 *
	 * @param descTipoCampo the new desc tipo campo
	 */
	public void setDescTipoCampo(String descTipoCampo) {
		this.descTipoCampo = descTipoCampo;
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
	 * @param tsddrTReportDett the tsddr T report dett
	 * @return the tsddr T report dett
	 */
	public TsddrTReportDett addTsddrTReportDett(TsddrTReportDett tsddrTReportDett) {
		getReportDett().add(tsddrTReportDett);
		tsddrTReportDett.setTipoCampo(this);

		return tsddrTReportDett;
	}

	/**
	 * Removes the tsddr T report dett.
	 *
	 * @param tsddrTReportDett the tsddr T report dett
	 * @return the tsddr T report dett
	 */
	public TsddrTReportDett removeTsddrTReportDett(TsddrTReportDett tsddrTReportDett) {
		getReportDett().remove(tsddrTReportDett);
		tsddrTReportDett.setTipoCampo(null);

		return tsddrTReportDett;
	}

}