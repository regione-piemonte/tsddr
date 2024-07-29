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
 * The persistent class for the tsddr_d_anni_pregresso database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_anni_pregresso")
@NamedQuery(name = "TsddrDAnnoPregresso.findAll", query = "SELECT t FROM TsddrDAnnoPregresso t")
public class TsddrDAnnoPregresso extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_anno_pregresso", unique = true, nullable = false)
	private Long idAnnoPregresso;

	@Column(name = "desc_anno_pregresso", nullable = false, length = 100)
	private String descAnnoPregresso;

	/**
	 * Instantiates a new tsddr D anno pregresso.
	 */
	public TsddrDAnnoPregresso() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id anno pregresso.
	 *
	 * @return the id anno pregresso
	 */
	public Long getIdAnnoPregresso() {
		return this.idAnnoPregresso;
	}

	/**
	 * Sets the id anno pregresso.
	 *
	 * @param idAnnoPregresso the new id anno pregresso
	 */
	public void setIdAnnoPregresso(Long idAnnoPregresso) {
		this.idAnnoPregresso = idAnnoPregresso;
	}

	/**
	 * Gets the desc anno pregresso.
	 *
	 * @return the desc anno pregresso
	 */
	public String getDescAnnoPregresso() {
		return this.descAnnoPregresso;
	}

	/**
	 * Sets the desc anno pregresso.
	 *
	 * @param descAnnoPregresso the new desc anno pregresso
	 */
	public void setDescAnnoPregresso(String descAnnoPregresso) {
		this.descAnnoPregresso = descAnnoPregresso;
	}

}