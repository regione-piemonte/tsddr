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
 * The persistent class for the tsddr_d_nature_giuridiche database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_nature_giuridiche")
@NamedQuery(name = "TsddrDNaturaGiuridica.findAll", query = "SELECT t FROM TsddrDNaturaGiuridica t")
public class TsddrDNaturaGiuridica extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_natura_giuridica", unique = true, nullable = false)
	private Long idNaturaGiuridica;

	@Column(name = "desc_natura_giuridica", nullable = false, length = 100)
	private String descNaturaGiuridica;

	// bi-directional many-to-one association to TsddrTGestore
	@OneToMany(mappedBy = "naturaGiuridica")
	private List<TsddrTGestore> gestori;

	/**
	 * Instantiates a new tsddr D natura giuridica.
	 */
	public TsddrDNaturaGiuridica() {
	 // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id natura giuridica.
	 *
	 * @return the id natura giuridica
	 */
	public Long getIdNaturaGiuridica() {
		return this.idNaturaGiuridica;
	}

	/**
	 * Sets the id natura giuridica.
	 *
	 * @param idNaturaGiuridica the new id natura giuridica
	 */
	public void setIdNaturaGiuridica(Long idNaturaGiuridica) {
		this.idNaturaGiuridica = idNaturaGiuridica;
	}

	/**
	 * Gets the desc natura giuridica.
	 *
	 * @return the desc natura giuridica
	 */
	public String getDescNaturaGiuridica() {
		return this.descNaturaGiuridica;
	}

	/**
	 * Sets the desc natura giuridica.
	 *
	 * @param descNaturaGiuridica the new desc natura giuridica
	 */
	public void setDescNaturaGiuridica(String descNaturaGiuridica) {
		this.descNaturaGiuridica = descNaturaGiuridica;
	}

	/**
	 * Gets the gestori.
	 *
	 * @return the gestori
	 */
	public List<TsddrTGestore> getGestori() {
		return this.gestori;
	}

	/**
	 * Sets the gestori.
	 *
	 * @param gestori the new gestori
	 */
	public void setGestori(List<TsddrTGestore> gestori) {
		this.gestori = gestori;
	}

	/**
	 * Adds the gestori.
	 *
	 * @param gestori the gestori
	 * @return the tsddr T gestore
	 */
	public TsddrTGestore addGestori(TsddrTGestore gestori) {
		getGestori().add(gestori);
		gestori.setNaturaGiuridica(this);

		return gestori;
	}

	/**
	 * Removes the gestori.
	 *
	 * @param gestori the gestori
	 * @return the tsddr T gestore
	 */
	public TsddrTGestore removeGestori(TsddrTGestore gestori) {
		getGestori().remove(gestori);
		gestori.setNaturaGiuridica(null);

		return gestori;
	}

}