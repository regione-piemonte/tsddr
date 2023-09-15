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
 * The persistent class for the tsddr_c_parametri_acaris database table.
 * 
 */
@Entity
@Table(name="tsddr_c_parametri_acaris")
@NamedQuery(name="TsddrCParametroAcaris.findAll", query="SELECT t FROM TsddrCParametroAcaris t")
public class TsddrCParametroAcaris extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_parametro_acaris", unique=true, nullable=false)
	private Long idParametroAcaris;

	@Column(name="nome_param_acaris", nullable=false, length=100)
	private String nomeParamAcaris;

	@Column(name="valore_param_acaris_b")
	private Boolean valoreParamAcarisB;

	@Column(name="valore_param_acaris_n")
	private Long valoreParamAcarisN;

	@Column(name="valore_param_acaris_s", length=200)
	private String valoreParamAcarisS;

	/**
	 * Instantiates a new tsddr C parametro acaris.
	 */
	public TsddrCParametroAcaris() {
	 // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id parametro acaris.
	 *
	 * @return the id parametro acaris
	 */
	public Long getIdParametroAcaris() {
		return this.idParametroAcaris;
	}

	/**
	 * Sets the id parametro acaris.
	 *
	 * @param idParametroAcaris the new id parametro acaris
	 */
	public void setIdParametroAcaris(Long idParametroAcaris) {
		this.idParametroAcaris = idParametroAcaris;
	}

	/**
	 * Gets the nome param acaris.
	 *
	 * @return the nome param acaris
	 */
	public String getNomeParamAcaris() {
		return this.nomeParamAcaris;
	}

	/**
	 * Sets the nome param acaris.
	 *
	 * @param nomeParamAcaris the new nome param acaris
	 */
	public void setNomeParamAcaris(String nomeParamAcaris) {
		this.nomeParamAcaris = nomeParamAcaris;
	}

	/**
	 * Gets the valore param acaris B.
	 *
	 * @return the valore param acaris B
	 */
	public Boolean getValoreParamAcarisB() {
		return this.valoreParamAcarisB;
	}

	/**
	 * Sets the valore param acaris B.
	 *
	 * @param valoreParamAcarisB the new valore param acaris B
	 */
	public void setValoreParamAcarisB(Boolean valoreParamAcarisB) {
		this.valoreParamAcarisB = valoreParamAcarisB;
	}

	/**
	 * Gets the valore param acaris N.
	 *
	 * @return the valore param acaris N
	 */
	public Long getValoreParamAcarisN() {
		return this.valoreParamAcarisN;
	}

	/**
	 * Sets the valore param acaris N.
	 *
	 * @param valoreParamAcarisN the new valore param acaris N
	 */
	public void setValoreParamAcarisN(Long valoreParamAcarisN) {
		this.valoreParamAcarisN = valoreParamAcarisN;
	}

	/**
	 * Gets the valore param acaris S.
	 *
	 * @return the valore param acaris S
	 */
	public String getValoreParamAcarisS() {
		return this.valoreParamAcarisS;
	}

	/**
	 * Sets the valore param acaris S.
	 *
	 * @param valoreParamAcarisS the new valore param acaris S
	 */
	public void setValoreParamAcarisS(String valoreParamAcarisS) {
		this.valoreParamAcarisS = valoreParamAcarisS;
	}

}