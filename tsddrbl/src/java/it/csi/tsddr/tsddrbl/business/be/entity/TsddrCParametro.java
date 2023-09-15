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
 * The persistent class for the tsddr_c_parametri database table.
 * 
 */
@Entity
@Table(name = "tsddr_c_parametri")
@NamedQuery(name = "TsddrCParametro.findAll", query = "SELECT t FROM TsddrCParametro t")
public class TsddrCParametro extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_parametro", unique = true, nullable = false)
	private Long idParametro;

	@Column(name = "nome_parametro", nullable = false, length = 100)
	private String nomeParametro;

	@Column(name = "valore_parametro_b")
	private Boolean valoreParametroB;

	@Column(name = "valore_parametro_n")
	private Long valoreParametroN;

	@Column(name = "valore_parametro_s", length = 100)
	private String valoreParametroS;

	/**
	 * 
	 */
	public TsddrCParametro() {
	 // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * @return
	 */
	public Long getIdParametro() {
		return this.idParametro;
	}

	/**
	 * @param idParametro
	 */
	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	/**
	 * @return
	 */
	public String getNomeParametro() {
		return this.nomeParametro;
	}

	/**
	 * @param nomeParametro
	 */
	public void setNomeParametro(String nomeParametro) {
		this.nomeParametro = nomeParametro;
	}

	/**
	 * @return
	 */
	public Boolean getValoreParametroB() {
		return this.valoreParametroB;
	}

	/**
	 * @param valoreParametroB
	 */
	public void setValoreParametroB(Boolean valoreParametroB) {
		this.valoreParametroB = valoreParametroB;
	}

	/**
	 * @return
	 */
	public Long getValoreParametroN() {
		return this.valoreParametroN;
	}

	/**
	 * @param valoreParametroN
	 */
	public void setValoreParametroN(Long valoreParametroN) {
		this.valoreParametroN = valoreParametroN;
	}

	/**
	 * @return
	 */
	public String getValoreParametroS() {
		return this.valoreParametroS;
	}

	/**
	 * @param valoreParametroS
	 */
	public void setValoreParametroS(String valoreParametroS) {
		this.valoreParametroS = valoreParametroS;
	}

}