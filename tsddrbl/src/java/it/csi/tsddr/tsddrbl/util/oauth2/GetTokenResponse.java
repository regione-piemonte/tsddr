/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.oauth2;

/**
 * The type Get token response.
 */
public class GetTokenResponse {
	private String scope;
	private String token_type;
	private String bearer;
	private Long expires_in;
	private String access_token;

    /**
     * Gets scope.
     *
     * @return the scope
     */
    public String getScope() {
		return scope;
	}

    /**
     * Sets scope.
     *
     * @param scope the scope
     */
    public void setScope(String scope) {
		this.scope = scope;
	}

    /**
     * Gets token type.
     *
     * @return the token type
     */
    public String getToken_type() {
		return token_type;
	}

    /**
     * Sets token type.
     *
     * @param token_type the token type
     */
    public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

    /**
     * Gets bearer.
     *
     * @return the bearer
     */
    public String getBearer() {
		return bearer;
	}

    /**
     * Sets bearer.
     *
     * @param bearer the bearer
     */
    public void setBearer(String bearer) {
		this.bearer = bearer;
	}

    /**
     * Gets expires in.
     *
     * @return the expires in
     */
    public Long getExpires_in() {
		return expires_in;
	}

    /**
     * Sets expires in.
     *
     * @param expires_in the expires in
     */
    public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public String getAccess_token() {
		return access_token;
	}

    /**
     * Sets access token.
     *
     * @param access_token the access token
     */
    public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}