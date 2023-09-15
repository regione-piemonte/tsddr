/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

public enum Parametro {
	
	CODICE_PRODOTTO("codiceProdotto"),
	CODICE_LINEA_CLIENTE("codiceLineaCliente"),
	CODICE_AMBIENTE("codiceAmbiente"),
	CODICE_UNITA_INSTALLAZIONE("codiceUnitaInstallazione"),
	LINK_PRIVACY("LinkPrivacy"),
	IMPORTO_MINIMO("ImportoMinimo"),
	VALORI_EMAIL_NON_VALIDI("Valori email non validi"),
	ACTA_SERVER("ACTA_SERVER"),
	ACTA_CONTEXT("ACTA_CONTEXT"),
	ACTA_PORT("ACTA_PORT"),
	APIMANAGER_OAUTHURL("APIMANAGER_OAUTHURL"),
	APIMANAGER_CONSUMERKEY("APIMANAGER_CONSUMERKEY"),
	APIMANAGER_CONSUMERSECRET("APIMANAGER_CONSUMERSECRET"),
	APIMANAGER_URL("APIMANAGER_URL"),
	APIMANAGER_URL_END("APIMANAGER_URL_END"),	
	ACTA_ID_AOO("ACTA_ID_AOO"),
	ACTA_ID_STRUTTURA("ACTA_ID_STRUTTURA"),
	ACTA_ID_NODO("ACTA_ID_NODO"),
	ACTA_CODE_FRUITORE("ACTA_CODE_FRUITORE"),
	ACTA_CF("ACTA_CF"),
	ACTA_APP_KEY_FRUITORE("ACTA_APP_KEY_FRUITORE"),
	ACTA_REPO_NAME_FRUITORE("ACTA_REPO_NAME_FRUITORE"),
	ACTA_CODE_ENTE_FRUITORE("ACTA_CODE_ENTE_FRUITORE");
	
	private String nome;

	private Parametro(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
