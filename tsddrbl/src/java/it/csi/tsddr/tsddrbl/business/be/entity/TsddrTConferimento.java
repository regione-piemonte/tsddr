/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_conferimenti database table.
 * 
 */
@Entity
@Table(name="tsddr_t_conferimenti")
@NamedQuery(name="TsddrTConferimento.findAll", query="SELECT t FROM TsddrTConferimento t")
public class TsddrTConferimento extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_conferimento", unique=true, nullable=false)
	private Long idConferimento;

	@Column(nullable=false)
	private Long anno;

	@Column(nullable=false, precision=12, scale=2)
	private BigDecimal importo;

	@Column(nullable=false, precision=14, scale=4)
	private BigDecimal quantita;

	//bi-directional many-to-one association to TsddrDPeriodo
	@ManyToOne
	@JoinColumn(name="id_periodo", nullable=false)
	private TsddrDPeriodo periodo;

	//bi-directional many-to-one association to TsddrDUnitaMisura
	@ManyToOne
	@JoinColumn(name="id_unita_misura", nullable=false)
	private TsddrDUnitaMisura unitaMisura;

	//bi-directional many-to-one association to TsddrTDichAnnuale
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dich_annuale", nullable=false)
	private TsddrTDichAnnuale dichAnnuale;

	//bi-directional many-to-one association to TsddrTRifiutoTariffa
	@ManyToOne
	@JoinColumn(name="id_rifiuto_tariffa", nullable=false)
	private TsddrTRifiutoTariffa rifiutoTariffa;

	/**
	 * Instantiates a new tsddr T conferimento.
	 */
	public TsddrTConferimento() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id conferimento.
	 *
	 * @return the id conferimento
	 */
	public Long getIdConferimento() {
		return this.idConferimento;
	}

	/**
	 * Sets the id conferimento.
	 *
	 * @param idConferimento the new id conferimento
	 */
	public void setIdConferimento(Long idConferimento) {
		this.idConferimento = idConferimento;
	}

	/**
	 * Gets the anno.
	 *
	 * @return the anno
	 */
	public Long getAnno() {
		return this.anno;
	}

	/**
	 * Sets the anno.
	 *
	 * @param anno the new anno
	 */
	public void setAnno(Long anno) {
		this.anno = anno;
	}

	/**
	 * Gets the importo.
	 *
	 * @return the importo
	 */
	public BigDecimal getImporto() {
		return this.importo;
	}

	/**
	 * Sets the importo.
	 *
	 * @param importo the new importo
	 */
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	/**
	 * Gets the quantita.
	 *
	 * @return the quantita
	 */
	public BigDecimal getQuantita() {
		return this.quantita;
	}

	/**
	 * Sets the quantita.
	 *
	 * @param quantita the new quantita
	 */
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public TsddrDPeriodo getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(TsddrDPeriodo periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the unita misura.
	 *
	 * @return the unita misura
	 */
	public TsddrDUnitaMisura getUnitaMisura() {
		return unitaMisura;
	}

	/**
	 * Sets the unita misura.
	 *
	 * @param unitaMisura the new unita misura
	 */
	public void setUnitaMisura(TsddrDUnitaMisura unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	/**
	 * Gets the dich annuale.
	 *
	 * @return the dich annuale
	 */
	public TsddrTDichAnnuale getDichAnnuale() {
		return dichAnnuale;
	}

	/**
	 * Sets the dich annuale.
	 *
	 * @param dichAnnuale the new dich annuale
	 */
	public void setDichAnnuale(TsddrTDichAnnuale dichAnnuale) {
		this.dichAnnuale = dichAnnuale;
	}

	/**
	 * Gets the rifiuto tariffa.
	 *
	 * @return the rifiuto tariffa
	 */
	public TsddrTRifiutoTariffa getRifiutoTariffa() {
		return rifiutoTariffa;
	}

	/**
	 * Sets the rifiuto tariffa.
	 *
	 * @param rifiutoTariffa the new rifiuto tariffa
	 */
	public void setRifiutoTariffa(TsddrTRifiutoTariffa rifiutoTariffa) {
		this.rifiutoTariffa = rifiutoTariffa;
	}

}