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
 * The persistent class for the tsddr_d_nazioni database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_nazioni")
@NamedQuery(name = "TsddrDNazione.findAll", query = "SELECT t FROM TsddrDNazione t")
public class TsddrDNazione extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nazione", unique = true, nullable = false)
	private Long idNazione;

	@Column(name = "desc_nazione", nullable = false, length = 100)
	private String descNazione;
	
	@Column(name = "id_istat_nazione", nullable = false, length = 3)
	private String idIstatNazione;

	// bi-directional many-to-one association to TsddrDRegione
	@OneToMany(mappedBy = "nazione")
	private List<TsddrDRegione> regioni;

	// bi-directional many-to-one association to TsddrTIndirizzo
	@OneToMany(mappedBy = "nazione")
	private List<TsddrTIndirizzo> indirizzi;

	/**
	 * Instantiates a new tsddr D nazione.
	 */
	public TsddrDNazione() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id nazione.
	 *
	 * @return the id nazione
	 */
	public Long getIdNazione() {
		return this.idNazione;
	}

	/**
	 * Sets the id nazione.
	 *
	 * @param idNazione the new id nazione
	 */
	public void setIdNazione(Long idNazione) {
		this.idNazione = idNazione;
	}

	/**
	 * Gets the desc nazione.
	 *
	 * @return the desc nazione
	 */
	public String getDescNazione() {
		return this.descNazione;
	}

	/**
	 * Sets the desc nazione.
	 *
	 * @param descNazione the new desc nazione
	 */
	public void setDescNazione(String descNazione) {
		this.descNazione = descNazione;
	}

	/**
	 * Gets the id istat nazione.
	 *
	 * @return the id istat nazione
	 */
	public String getIdIstatNazione() {
		return idIstatNazione;
	}

	/**
	 * Sets the id istat nazione.
	 *
	 * @param idIstatNazione the new id istat nazione
	 */
	public void setIdIstatNazione(String idIstatNazione) {
		this.idIstatNazione = idIstatNazione;
	}

	/**
	 * Gets the regioni.
	 *
	 * @return the regioni
	 */
	public List<TsddrDRegione> getRegioni() {
		return this.regioni;
	}

	/**
	 * Sets the regioni.
	 *
	 * @param regioni the new regioni
	 */
	public void setRegioni(List<TsddrDRegione> regioni) {
		this.regioni = regioni;
	}

	/**
	 * Adds the regioni.
	 *
	 * @param regioni the regioni
	 * @return the tsddr D regione
	 */
	public TsddrDRegione addRegioni(TsddrDRegione regioni) {
		getRegioni().add(regioni);
		regioni.setNazione(this);

		return regioni;
	}

	/**
	 * Removes the regioni.
	 *
	 * @param regioni the regioni
	 * @return the tsddr D regione
	 */
	public TsddrDRegione removeRegioni(TsddrDRegione regioni) {
		getRegioni().remove(regioni);
		regioni.setNazione(null);

		return regioni;
	}

	/**
	 * Gets the indirizzi.
	 *
	 * @return the indirizzi
	 */
	public List<TsddrTIndirizzo> getIndirizzi() {
		return this.indirizzi;
	}

	/**
	 * Sets the indirizzi.
	 *
	 * @param indirizzi the new indirizzi
	 */
	public void setIndirizzi(List<TsddrTIndirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}

	/**
	 * Adds the indirizzi.
	 *
	 * @param indirizzi the indirizzi
	 * @return the tsddr T indirizzo
	 */
	public TsddrTIndirizzo addIndirizzi(TsddrTIndirizzo indirizzi) {
		getIndirizzi().add(indirizzi);
		indirizzi.setNazione(this);

		return indirizzi;
	}

	/**
	 * Removes the indirizzi.
	 *
	 * @param indirizzi the indirizzi
	 * @return the tsddr T indirizzo
	 */
	public TsddrTIndirizzo removeIndirizzi(TsddrTIndirizzo indirizzi) {
		getIndirizzi().remove(indirizzi);
		indirizzi.setNazione(null);

		return indirizzi;
	}

}