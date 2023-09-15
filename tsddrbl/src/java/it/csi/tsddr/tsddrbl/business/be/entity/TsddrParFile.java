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
 * The persistent class for the tsddr_par_file database table.
 * 
 */
@Entity
@Table(name="tsddr_par_file")
@NamedQuery(name="TsddrParFile.findAll", query="SELECT t FROM TsddrParFile t")
public class TsddrParFile extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_par_file", unique=true, nullable=false)
	private Long idParFile;

	@Column(name="file", nullable=false, length=50)
	private String file;

	@Column(name="formato", length=50)
	private String formato;

	@Column(name="lunghezza", nullable=false)
	private Long lunghezza;

	@Column(name="nome_campo", nullable=false, length=50)
	private String nomeCampo;

	@Column(name="null_value", length=50)
	private String nullValue;

	@Column(name="pos_a", nullable=false)
	private Long posA;

	@Column(name="pos_da", nullable=false)
	private Long posDa;

	/**
	 * Instantiates a new tsddr par file.
	 */
	public TsddrParFile() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id par file.
	 *
	 * @return the id par file
	 */
	public Long getIdParFile() {
		return this.idParFile;
	}

	/**
	 * Sets the id par file.
	 *
	 * @param idParFile the new id par file
	 */
	public void setIdParFile(Long idParFile) {
		this.idParFile = idParFile;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public String getFile() {
		return this.file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * Gets the formato.
	 *
	 * @return the formato
	 */
	public String getFormato() {
		return this.formato;
	}

	/**
	 * Sets the formato.
	 *
	 * @param formato the new formato
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}

	/**
	 * Gets the lunghezza.
	 *
	 * @return the lunghezza
	 */
	public Long getLunghezza() {
		return this.lunghezza;
	}

	/**
	 * Sets the lunghezza.
	 *
	 * @param lunghezza the new lunghezza
	 */
	public void setLunghezza(Long lunghezza) {
		this.lunghezza = lunghezza;
	}

	/**
	 * Gets the nome campo.
	 *
	 * @return the nome campo
	 */
	public String getNomeCampo() {
		return this.nomeCampo;
	}

	/**
	 * Sets the nome campo.
	 *
	 * @param nomeCampo the new nome campo
	 */
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	/**
	 * Gets the null value.
	 *
	 * @return the null value
	 */
	public String getNullValue() {
		return this.nullValue;
	}

	/**
	 * Sets the null value.
	 *
	 * @param nullValue the new null value
	 */
	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}

	/**
	 * Gets the pos A.
	 *
	 * @return the pos A
	 */
	public Long getPosA() {
		return this.posA;
	}

	/**
	 * Sets the pos A.
	 *
	 * @param posA the new pos A
	 */
	public void setPosA(Long posA) {
		this.posA = posA;
	}

	/**
	 * Gets the pos da.
	 *
	 * @return the pos da
	 */
	public Long getPosDa() {
		return this.posDa;
	}

	/**
	 * Sets the pos da.
	 *
	 * @param posDa the new pos da
	 */
	public void setPosDa(Long posDa) {
		this.posDa = posDa;
	}

}