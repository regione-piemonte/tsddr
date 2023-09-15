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
 * The persistent class for the tsddr_d_province database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_province")
@NamedQuery(name = "TsddrDProvincia.findAll", query = "SELECT t FROM TsddrDProvincia t")
public class TsddrDProvincia extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_provincia", unique = true, nullable = false)
	private Long idProvincia;

	@Column(name = "desc_provincia", nullable = false, length = 100)
	private String descProvincia;

	@Column(name = "id_provincia_istat", nullable = false)
	private Long idProvinciaIstat;

	@Column(name = "sigla_prov", nullable = false, length = 5)
	private String siglaProv;

	// bi-directional many-to-one association to TsddrDComune
	@OneToMany(mappedBy = "provincia")
	private List<TsddrDComune> comuni;

	// bi-directional many-to-one association to TsddrDRegione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_regione")
	private TsddrDRegione regione;

	/**
	 * Instantiates a new tsddr D provincia.
	 */
	public TsddrDProvincia() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id provincia.
	 *
	 * @return the id provincia
	 */
	public Long getIdProvincia() {
		return this.idProvincia;
	}

	/**
	 * Sets the id provincia.
	 *
	 * @param idProvincia the new id provincia
	 */
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * Gets the desc provincia.
	 *
	 * @return the desc provincia
	 */
	public String getDescProvincia() {
		return this.descProvincia;
	}

	/**
	 * Sets the desc provincia.
	 *
	 * @param descProvincia the new desc provincia
	 */
	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	/**
	 * Gets the id provincia istat.
	 *
	 * @return the id provincia istat
	 */
	public Long getIdProvinciaIstat() {
		return this.idProvinciaIstat;
	}

	/**
	 * Sets the id provincia istat.
	 *
	 * @param idProvinciaIstat the new id provincia istat
	 */
	public void setIdProvinciaIstat(Long idProvinciaIstat) {
		this.idProvinciaIstat = idProvinciaIstat;
	}

	/**
	 * Gets the sigla prov.
	 *
	 * @return the sigla prov
	 */
	public String getSiglaProv() {
		return this.siglaProv;
	}

	/**
	 * Sets the sigla prov.
	 *
	 * @param siglaProv the new sigla prov
	 */
	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

	/**
	 * Gets the comuni.
	 *
	 * @return the comuni
	 */
	public List<TsddrDComune> getComuni() {
		return this.comuni;
	}

	/**
	 * Sets the comuni.
	 *
	 * @param comuni the new comuni
	 */
	public void setComuni(List<TsddrDComune> comuni) {
		this.comuni = comuni;
	}

	/**
	 * Adds the comuni.
	 *
	 * @param comuni the comuni
	 * @return the tsddr D comune
	 */
	public TsddrDComune addComuni(TsddrDComune comuni) {
		getComuni().add(comuni);
		comuni.setProvincia(this);

		return comuni;
	}

	/**
	 * Removes the comuni.
	 *
	 * @param comuni the comuni
	 * @return the tsddr D comune
	 */
	public TsddrDComune removeComuni(TsddrDComune comuni) {
		getComuni().remove(comuni);
		comuni.setProvincia(null);

		return comuni;
	}

	/**
	 * Gets the regione.
	 *
	 * @return the regione
	 */
	public TsddrDRegione getRegione() {
		return this.regione;
	}

	/**
	 * Sets the regione.
	 *
	 * @param regione the new regione
	 */
	public void setRegione(TsddrDRegione regione) {
		this.regione = regione;
	}

}