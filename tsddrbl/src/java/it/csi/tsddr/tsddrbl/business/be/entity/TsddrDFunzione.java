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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_d_funzioni database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_funzioni")
@NamedQuery(name = "TsddrDFunzione.findAll", query = "SELECT t FROM TsddrDFunzione t")
public class TsddrDFunzione extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funzione", unique = true, nullable = false)
	private Long idFunzione;

	@Column(name = "cod_funzione", nullable = false, length = 10)
	private String codFunzione;

	@Column(name = "desc_funzione", nullable = false, length = 100)
	private String descFunzione;

	// bi-directional many-to-one association to TsddrRFunzProf
	@OneToMany(mappedBy = "funzione")
	private List<TsddrRFunzProf> rFunzProf;

	// bi-directional many-to-one association to TsddrTMenu
	@OneToMany(mappedBy = "funzione")
	private List<TsddrTMenu> menus;

	// bi-directional many-to-many association to TsddrDProfilo
	@ManyToMany
	@JoinTable(name = "tsddr_r_funz_prof", joinColumns = {
			@JoinColumn(name = "id_funzione", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_profilo", nullable = false) })
	private List<TsddrDProfilo> profili;

	/**
	 * Instantiates a new tsddr D funzione.
	 */
	public TsddrDFunzione() {
	 // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id funzione.
	 *
	 * @return the id funzione
	 */
	public Long getIdFunzione() {
		return this.idFunzione;
	}

	/**
	 * Sets the id funzione.
	 *
	 * @param idFunzione the new id funzione
	 */
	public void setIdFunzione(Long idFunzione) {
		this.idFunzione = idFunzione;
	}

	/**
	 * Gets the cod funzione.
	 *
	 * @return the cod funzione
	 */
	public String getCodFunzione() {
		return this.codFunzione;
	}

	/**
	 * Sets the cod funzione.
	 *
	 * @param codFunzione the new cod funzione
	 */
	public void setCodFunzione(String codFunzione) {
		this.codFunzione = codFunzione;
	}

	/**
	 * Gets the desc funzione.
	 *
	 * @return the desc funzione
	 */
	public String getDescFunzione() {
		return this.descFunzione;
	}

	/**
	 * Sets the desc funzione.
	 *
	 * @param descFunzione the new desc funzione
	 */
	public void setDescFunzione(String descFunzione) {
		this.descFunzione = descFunzione;
	}

	/**
	 * Gets the r funz prof.
	 *
	 * @return the r funz prof
	 */
	public List<TsddrRFunzProf> getRFunzProf() {
		return this.rFunzProf;
	}

	/**
	 * Sets the r funz prof.
	 *
	 * @param rFunzProf the new r funz prof
	 */
	public void setRFunzProf(List<TsddrRFunzProf> rFunzProf) {
		this.rFunzProf = rFunzProf;
	}

	/**
	 * Adds the R funz prof.
	 *
	 * @param rFunzProf the r funz prof
	 * @return the tsddr R funz prof
	 */
	public TsddrRFunzProf addRFunzProf(TsddrRFunzProf rFunzProf) {
		getRFunzProf().add(rFunzProf);
		rFunzProf.setFunzione(this);

		return rFunzProf;
	}

	/**
	 * Removes the R funz prof.
	 *
	 * @param rFunzProf the r funz prof
	 * @return the tsddr R funz prof
	 */
	public TsddrRFunzProf removeRFunzProf(TsddrRFunzProf rFunzProf) {
		getRFunzProf().remove(rFunzProf);
		rFunzProf.setFunzione(null);

		return rFunzProf;
	}

	/**
	 * Gets the menus.
	 *
	 * @return the menus
	 */
	public List<TsddrTMenu> getMenus() {
		return this.menus;
	}

	/**
	 * Sets the menus.
	 *
	 * @param menus the new menus
	 */
	public void setMenus(List<TsddrTMenu> menus) {
		this.menus = menus;
	}

	/**
	 * Adds the menus.
	 *
	 * @param menus the menus
	 * @return the tsddr T menu
	 */
	public TsddrTMenu addMenus(TsddrTMenu menus) {
		getMenus().add(menus);
		menus.setFunzione(this);

		return menus;
	}

	/**
	 * Removes the menus.
	 *
	 * @param menus the menus
	 * @return the tsddr T menu
	 */
	public TsddrTMenu removeMenus(TsddrTMenu menus) {
		getMenus().remove(menus);
		menus.setFunzione(null);

		return menus;
	}

	/**
	 * Gets the profili.
	 *
	 * @return the profili
	 */
	public List<TsddrDProfilo> getProfili() {
		return this.profili;
	}

	/**
	 * Sets the profili.
	 *
	 * @param profili the new profili
	 */
	public void setProfili(List<TsddrDProfilo> profili) {
		this.profili = profili;
	}

}