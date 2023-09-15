/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.bean;

import java.io.Serializable;

public class Fruitore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long idFruitore;

	private String codFruitore;

	private String descrFruitore;

	private String cfActa;

	private String idAooActa;

	private String idStrutturaActa;

	private String idNodoActa;

	private String appKey;

	private String repoName;

	private String codeEnte;
		
	
	public Fruitore(long idFruitore, String codFruitore, String descrFruitore, String cfActa, String idAooActa,
			String idStrutturaActa, String idNodoActa, String appKey, String repoName, String codeEnte) {
		super();
		this.idFruitore = idFruitore;
		this.codFruitore = codFruitore;
		this.descrFruitore = descrFruitore;
		this.cfActa = cfActa;
		this.idAooActa = idAooActa;
		this.idStrutturaActa = idStrutturaActa;
		this.idNodoActa = idNodoActa;
		this.appKey = appKey;
		this.repoName = repoName;
		this.codeEnte = codeEnte;
	}

	public long getIdFruitore() {
		return idFruitore;
	}

	public void setIdFruitore(long idFruitore) {
		this.idFruitore = idFruitore;
	}

	public String getCodFruitore() {
		return codFruitore;
	}

	public void setCodFruitore(String codFruitore) {
		this.codFruitore = codFruitore;
	}

	public String getDescrFruitore() {
		return descrFruitore;
	}

	public void setDescrFruitore(String descrFruitore) {
		this.descrFruitore = descrFruitore;
	}

	public String getCfActa() {
		return cfActa;
	}

	public void setCfActa(String cfActa) {
		this.cfActa = cfActa;
	}

	public String getIdAooActa() {
		return idAooActa;
	}

	public void setIdAooActa(String idAooActa) {
		this.idAooActa = idAooActa;
	}

	public String getIdStrutturaActa() {
		return idStrutturaActa;
	}

	public void setIdStrutturaActa(String idStrutturaActa) {
		this.idStrutturaActa = idStrutturaActa;
	}

	public String getIdNodoActa() {
		return idNodoActa;
	}

	public void setIdNodoActa(String idNodoActa) {
		this.idNodoActa = idNodoActa;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getCodeEnte() {
		return codeEnte;
	}

	public void setCodeEnte(String codeEnte) {
		this.codeEnte = codeEnte;
	}

}
