/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_limiti_amministrativi database table.
 * 
 */
@Entity
@Table(name="tsddr_limiti_amministrativi")
@NamedQuery(name="TsddrLimiteAmministrativo.findAll", query="SELECT t FROM TsddrLimiteAmministrativo t")
public class TsddrLimiteAmministrativo extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="record", nullable=false, length=500)
	private String record;

	/**
	 * Instantiates a new tsddr limite amministrativo.
	 */
	public TsddrLimiteAmministrativo() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the record.
	 *
	 * @return the record
	 */
	public String getRecord() {
		return this.record;
	}

	/**
	 * Sets the record.
	 *
	 * @param record the new record
	 */
	public void setRecord(String record) {
		this.record = record;
	}

}