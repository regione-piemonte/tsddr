/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_USER_INSERT")
	private Long idUserInsert;

	@Column(name = "ID_USER_UPDATE")
	private Long idUserUpdate;

	@Column(name = "ID_USER_DELETE")
	private Long idUserDelete;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INSERT")
	private Date dataInsert;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_UPDATE")
	private Date dataUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_DELETE")
	private Date dataDelete;

	/**
	 * @return the idUserInsert
	 */
	public Long getIdUserInsert() {
		return idUserInsert;
	}

	/**
	 * @param idUserInsert the idUserInsert to set
	 */
	public void setIdUserInsert(Long idUserInsert) {
		this.idUserInsert = idUserInsert;
	}

	/**
	 * @return the idUserUpdate
	 */
	public Long getIdUserUpdate() {
		return idUserUpdate;
	}

	/**
	 * @param idUserUpdate the idUserUpdate to set
	 */
	public void setIdUserUpdate(Long idUserUpdate) {
		this.idUserUpdate = idUserUpdate;
	}

	/**
	 * @return the idUserDelete
	 */
	public Long getIdUserDelete() {
		return idUserDelete;
	}

	/**
	 * @param idUserDelete the idUserDelete to set
	 */
	public void setIdUserDelete(Long idUserDelete) {
		this.idUserDelete = idUserDelete;
	}

	/**
	 * @return the dataInsert
	 */
	public Date getDataInsert() {
		return dataInsert;
	}

	/**
	 * @param dataInsert the dataInsert to set
	 */
	public void setDataInsert(Date dataInsert) {
		this.dataInsert = dataInsert;
	}

	/**
	 * @return the dataUpdate
	 */
	public Date getDataUpdate() {
		return dataUpdate;
	}

	/**
	 * @param dataUpdate the dataUpdate to set
	 */
	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	/**
	 * @return the dataDelete
	 */
	public Date getDataDelete() {
		return dataDelete;
	}

	/**
	 * @param dataDelete the dataDelete to set
	 */
	public void setDataDelete(Date dataDelete) {
		this.dataDelete = dataDelete;
	}

}
