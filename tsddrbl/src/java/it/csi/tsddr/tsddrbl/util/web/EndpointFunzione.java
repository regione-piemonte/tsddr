/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.web;

import org.springframework.http.HttpMethod;

public class EndpointFunzione {

	public enum FunzType {
		READ, UPDATE, DELETE, INSERT
	};

	private String endpoint;

	private HttpMethod method;

	private FunzType funzType;

	public static EndpointFunzione create(String endpoint, HttpMethod method, FunzType funzType) {
		return new EndpointFunzione(endpoint, method, funzType);
	}

	public EndpointFunzione(String endpoint, HttpMethod method, FunzType funzType) {
		this.endpoint = endpoint;
		this.method = method;
		this.funzType = funzType;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the method
	 */
	public HttpMethod getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	/**
	 * @return the funzType
	 */
	public FunzType getFunzType() {
		return funzType;
	}

	/**
	 * @param funzType the funzType to set
	 */
	public void setFunzType(FunzType funzType) {
		this.funzType = funzType;
	}

}