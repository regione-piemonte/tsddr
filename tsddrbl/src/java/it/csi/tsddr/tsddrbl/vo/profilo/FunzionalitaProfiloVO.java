/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.profilo;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRFunzProf;
import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class FunzionalitaProfiloVO extends AbstractVO {

	private static final long serialVersionUID = -7955704098786425878L;
	
	private Long idProfilo;
	private Long idFunzione;
	private String descFunzione;
	private Boolean delete;
	private Boolean insert;
	private Boolean read;
	private Boolean update;
	
	/**
	 * Instantiates a new funzionalita profilo VO.
	 */
	public FunzionalitaProfiloVO() {
	}

	/**
	 * Instantiates a new funzionalita profilo VO.
	 *
	 * @param idProfilo the id profilo
	 * @param idFunzione the id funzione
	 * @param descFunzione the desc funzione
	 * @param delete the delete
	 * @param insert the insert
	 * @param read the read
	 * @param update the update
	 */
	public FunzionalitaProfiloVO(Long idProfilo, Long idFunzione, String descFunzione, Boolean delete,
			Boolean insert, Boolean read, Boolean update) {
		this.idProfilo = idProfilo;
		this.idFunzione = idFunzione;
		this.descFunzione = descFunzione;
		this.delete = delete;
		this.insert = insert;
		this.read = read;
		this.update = update;
	}
	
	/**
	 * Instantiates a new funzionalita profilo VO.
	 *
	 * @param rfp the rfp
	 */
	public FunzionalitaProfiloVO(TsddrRFunzProf rfp) {
		this(rfp.getId().getIdProfilo(), rfp.getId().getIdFunzione(), rfp.getFunzione().getDescFunzione(),
				rfp.getIsDelete(), rfp.getIsInsert(), rfp.getIsRead(), rfp.getIsUpdate());
	}

	/**
	 * Gets the id profilo.
	 *
	 * @return the id profilo
	 */
	public Long getIdProfilo() {
		return idProfilo;
	}

	/**
	 * Sets the id profilo.
	 *
	 * @param idProfilo the new id profilo
	 */
	public void setIdProfilo(Long idProfilo) {
		this.idProfilo = idProfilo;
	}

	/**
	 * Gets the id funzione.
	 *
	 * @return the id funzione
	 */
	public Long getIdFunzione() {
		return idFunzione;
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
	 * Gets the desc funzione.
	 *
	 * @return the desc funzione
	 */
	public String getDescFunzione() {
		return descFunzione;
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
	 * Gets the delete.
	 *
	 * @return the delete
	 */
	public Boolean getDelete() {
		return delete;
	}

	/**
	 * Sets the delete.
	 *
	 * @param delete the new delete
	 */
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	/**
	 * Gets the insert.
	 *
	 * @return the insert
	 */
	public Boolean getInsert() {
		return insert;
	}

	/**
	 * Sets the insert.
	 *
	 * @param insert the new insert
	 */
	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	/**
	 * Gets the read.
	 *
	 * @return the read
	 */
	public Boolean getRead() {
		return read;
	}

	/**
	 * Sets the read.
	 *
	 * @param read the new read
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}

	/**
	 * Gets the update.
	 *
	 * @return the update
	 */
	public Boolean getUpdate() {
		return update;
	}

	/**
	 * Sets the update.
	 *
	 * @param update the new update
	 */
	public void setUpdate(Boolean update) {
		this.update = update;
	}

}
