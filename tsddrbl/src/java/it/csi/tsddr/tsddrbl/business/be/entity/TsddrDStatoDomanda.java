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
 * The persistent class for the tsddr_d_stati_domande database table.
 * 
 */
@Entity
@Table(name = "tsddr_d_stati_domande")
@NamedQuery(name = "TsddrDStatoDomanda.findAll", query = "SELECT t FROM TsddrDStatoDomanda t")
public class TsddrDStatoDomanda extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stato_domanda", unique = true, nullable = false)
	private Long idStatoDomanda;

	@Column(name = "desc_stato_domanda", nullable = false, length = 255)
	private String descStatoDomanda;

	@Column(nullable = false)
	private Long step;

	// bi-directional many-to-one association to TsddrTDomanda
	@OneToMany(mappedBy = "statoDomanda")
	private List<TsddrTDomanda> domande;

	/**
	 * Instantiates a new tsddr D stato domanda.
	 */
	public TsddrDStatoDomanda() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id stato domanda.
	 *
	 * @return the id stato domanda
	 */
	public Long getIdStatoDomanda() {
		return this.idStatoDomanda;
	}

	/**
	 * Sets the id stato domanda.
	 *
	 * @param idStatoDomanda the new id stato domanda
	 */
	public void setIdStatoDomanda(Long idStatoDomanda) {
		this.idStatoDomanda = idStatoDomanda;
	}

	/**
	 * Gets the desc stato domanda.
	 *
	 * @return the desc stato domanda
	 */
	public String getDescStatoDomanda() {
		return this.descStatoDomanda;
	}

	/**
	 * Sets the desc stato domanda.
	 *
	 * @param descStatoDomanda the new desc stato domanda
	 */
	public void setDescStatoDomanda(String descStatoDomanda) {
		this.descStatoDomanda = descStatoDomanda;
	}

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public Long getStep() {
		return this.step;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(Long step) {
		this.step = step;
	}

	/**
	 * Gets the domande.
	 *
	 * @return the domande
	 */
	public List<TsddrTDomanda> getDomande() {
		return this.domande;
	}

	/**
	 * Sets the domande.
	 *
	 * @param domande the new domande
	 */
	public void setDomande(List<TsddrTDomanda> domande) {
		this.domande = domande;
	}

	/**
	 * Adds the domande.
	 *
	 * @param domande the domande
	 * @return the tsddr T domanda
	 */
	public TsddrTDomanda addDomande(TsddrTDomanda domande) {
		getDomande().add(domande);
		domande.setStatoDomanda(this);

		return domande;
	}

	/**
	 * Removes the domande.
	 *
	 * @param domande the domande
	 * @return the tsddr T domanda
	 */
	public TsddrTDomanda removeDomande(TsddrTDomanda domande) {
		getDomande().remove(domande);
		domande.setStatoDomanda(null);

		return domande;
	}

}