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
 * The persistent class for the tsddr_d_periodi database table.
 * 
 */
@Entity
@Table(name="tsddr_d_periodi")
@NamedQuery(name="TsddrDPeriodo.findAll", query="SELECT t FROM TsddrDPeriodo t")
public class TsddrDPeriodo extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_periodo", unique=true, nullable=false)
	private Long idPeriodo;

	@Column(name="desc_periodo", nullable=false, length=100)
	private String descPeriodo;

	//bi-directional many-to-one association to TsddrTConferimento
	@OneToMany(mappedBy="periodo")
	private List<TsddrTConferimento> conferimenti;

	//bi-directional many-to-one association to TsddrTVersamento
	@OneToMany(mappedBy="periodo")
	private List<TsddrTVersamento> versamenti;

	/**
	 * Instantiates a new tsddr D periodo.
	 */
	public TsddrDPeriodo() {
	 // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id periodo.
	 *
	 * @return the id periodo
	 */
	public Long getIdPeriodo() {
		return this.idPeriodo;
	}

	/**
	 * Sets the id periodo.
	 *
	 * @param idPeriodo the new id periodo
	 */
	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	/**
	 * Gets the desc periodo.
	 *
	 * @return the desc periodo
	 */
	public String getDescPeriodo() {
		return this.descPeriodo;
	}

	/**
	 * Sets the desc periodo.
	 *
	 * @param descPeriodo the new desc periodo
	 */
	public void setDescPeriodo(String descPeriodo) {
		this.descPeriodo = descPeriodo;
	}

	/**
	 * Gets the conferimenti.
	 *
	 * @return the conferimenti
	 */
	public List<TsddrTConferimento> getConferimenti() {
		return conferimenti;
	}

	/**
	 * Sets the conferimenti.
	 *
	 * @param conferimenti the new conferimenti
	 */
	public void setConferimenti(List<TsddrTConferimento> conferimenti) {
		this.conferimenti = conferimenti;
	}
	
	/**
	 * Adds the tsddr T conferimenti.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T conferimento
	 */
	public TsddrTConferimento addTsddrTConferimenti(TsddrTConferimento conferimento) {
		getConferimenti().add(conferimento);
		conferimento.setPeriodo(this);

		return conferimento;
	}

	/**
	 * Removes the tsddr T conferimenti.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T conferimento
	 */
	public TsddrTConferimento removeTsddrTConferimenti(TsddrTConferimento conferimento) {
		getConferimenti().remove(conferimento);
		conferimento.setPeriodo(null);

		return conferimento;
	}

	/**
	 * Gets the versamenti.
	 *
	 * @return the versamenti
	 */
	public List<TsddrTVersamento> getVersamenti() {
		return versamenti;
	}

	/**
	 * Sets the versamenti.
	 *
	 * @param versamenti the new versamenti
	 */
	public void setVersamenti(List<TsddrTVersamento> versamenti) {
		this.versamenti = versamenti;
	}

	/**
	 * Adds the tsddr T versamenti.
	 *
	 * @param versamento the versamento
	 * @return the tsddr T versamento
	 */
	public TsddrTVersamento addTsddrTVersamenti(TsddrTVersamento versamento) {
		getVersamenti().add(versamento);
		versamento.setPeriodo(this);

		return versamento;
	}

	/**
	 * Removes the tsddr T versamenti.
	 *
	 * @param versamento the versamento
	 * @return the tsddr T versamento
	 */
	public TsddrTVersamento removeTsddrTVersamenti(TsddrTVersamento versamento) {
		getVersamenti().remove(versamento);
		versamento.setPeriodo(null);

		return versamento;
	}

}