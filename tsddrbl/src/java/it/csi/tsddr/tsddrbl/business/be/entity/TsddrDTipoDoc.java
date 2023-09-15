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
 * The persistent class for the tsddr_d_tipo_doc database table.
 * 
 */
@Entity
@Table(name="tsddr_d_tipo_doc")
@NamedQuery(name="TsddrDTipoDoc.findAll", query="SELECT t FROM TsddrDTipoDoc t")
public class TsddrDTipoDoc extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_doc", unique=true, nullable=false)
	private Long idTipoDoc;

	@Column(name="desc_tipo_doc", nullable=false, length=100)
	private String descTipoDoc;

	//bi-directional many-to-one association to TsddrTPrevCon
	@OneToMany(mappedBy="tipoDoc")
	private List<TsddrTPrevCons> prevCons;

	/**
	 * Instantiates a new tsddr D tipo doc.
	 */
	public TsddrDTipoDoc() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id tipo doc.
	 *
	 * @return the id tipo doc
	 */
	public Long getIdTipoDoc() {
		return this.idTipoDoc;
	}

	/**
	 * Sets the id tipo doc.
	 *
	 * @param idTipoDoc the new id tipo doc
	 */
	public void setIdTipoDoc(Long idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	/**
	 * Gets the desc tipo doc.
	 *
	 * @return the desc tipo doc
	 */
	public String getDescTipoDoc() {
		return this.descTipoDoc;
	}

	/**
	 * Sets the desc tipo doc.
	 *
	 * @param descTipoDoc the new desc tipo doc
	 */
	public void setDescTipoDoc(String descTipoDoc) {
		this.descTipoDoc = descTipoDoc;
	}

	/**
	 * Gets the prev cons.
	 *
	 * @return the prev cons
	 */
	public List<TsddrTPrevCons> getPrevCons() {
		return prevCons;
	}

	/**
	 * Sets the prev cons.
	 *
	 * @param prevCons the new prev cons
	 */
	public void setPrevCons(List<TsddrTPrevCons> prevCons) {
		this.prevCons = prevCons;
	}

	/**
	 * Adds the prev cons.
	 *
	 * @param prevCons the prev cons
	 * @return the tsddr T prev cons
	 */
	public TsddrTPrevCons addPrevCons(TsddrTPrevCons prevCons) {
		getPrevCons().add(prevCons);
		prevCons.setTipoDoc(this);

		return prevCons;
	}

	/**
	 * Removes the prev cons.
	 *
	 * @param prevCons the prev cons
	 * @return the tsddr T prev cons
	 */
	public TsddrTPrevCons removePrevCons(TsddrTPrevCons prevCons) {
		getPrevCons().remove(prevCons);
		prevCons.setTipoDoc(null);

		return prevCons;
	}
}