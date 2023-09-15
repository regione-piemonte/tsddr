/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_d_regioni database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_regioni")
@NamedQuery(name = "TsddrDRegione.findAll", query = "SELECT t FROM TsddrDRegione t")
public class TsddrDRegione extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_regione", unique = true, nullable = false)
	private Long idRegione;

	@Column(name = "desc_regione", nullable = false, length = 100)
	private String descRegione;

	@Column(name = "id_istat_regione", nullable = false)
	private Long idIstatRegione;

	// bi-directional many-to-one association to TsddrDProvincia
	@OneToMany(mappedBy = "regione")
	private List<TsddrDProvincia> province;

	// bi-directional many-to-one association to TsddrDNazione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nazione")
	private TsddrDNazione nazione;

	/**
	 * Instantiates a new tsddr D regione.
	 */
	public TsddrDRegione() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id regione.
	 *
	 * @return the id regione
	 */
	public Long getIdRegione() {
		return this.idRegione;
	}

	/**
	 * Sets the id regione.
	 *
	 * @param idRegione the new id regione
	 */
	public void setIdRegione(Long idRegione) {
		this.idRegione = idRegione;
	}

	/**
	 * Gets the desc regione.
	 *
	 * @return the desc regione
	 */
	public String getDescRegione() {
		return this.descRegione;
	}

	/**
	 * Sets the desc regione.
	 *
	 * @param descRegione the new desc regione
	 */
	public void setDescRegione(String descRegione) {
		this.descRegione = descRegione;
	}

	/**
	 * Gets the id istat regione.
	 *
	 * @return the id istat regione
	 */
	public Long getIdIstatRegione() {
		return this.idIstatRegione;
	}

	/**
	 * Sets the id istat regione.
	 *
	 * @param idIstatRegione the new id istat regione
	 */
	public void setIdIstatRegione(Long idIstatRegione) {
		this.idIstatRegione = idIstatRegione;
	}

	/**
	 * Gets the province.
	 *
	 * @return the province
	 */
	public List<TsddrDProvincia> getProvince() {
		return this.province;
	}

	/**
	 * Sets the province.
	 *
	 * @param province the new province
	 */
	public void setProvince(List<TsddrDProvincia> province) {
		this.province = province;
	}

	/**
	 * Adds the province.
	 *
	 * @param province the province
	 * @return the tsddr D provincia
	 */
	public TsddrDProvincia addProvince(TsddrDProvincia province) {
		getProvince().add(province);
		province.setRegione(this);

		return province;
	}

	/**
	 * Removes the province.
	 *
	 * @param province the province
	 * @return the tsddr D provincia
	 */
	public TsddrDProvincia removeProvince(TsddrDProvincia province) {
		getProvince().remove(province);
		province.setRegione(null);

		return province;
	}

	/**
	 * Gets the nazione.
	 *
	 * @return the nazione
	 */
	public TsddrDNazione getNazione() {
		return this.nazione;
	}

	/**
	 * Sets the nazione.
	 *
	 * @param nazione the new nazione
	 */
	public void setNazione(TsddrDNazione nazione) {
		this.nazione = nazione;
	}

}