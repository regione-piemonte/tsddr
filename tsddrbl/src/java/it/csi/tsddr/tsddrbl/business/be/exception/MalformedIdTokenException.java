/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

public class MalformedIdTokenException extends UserException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new malformed id token exception.
	 */
	public MalformedIdTokenException() {
        super("");
    }
    
    /**
     * Instantiates a new malformed id token exception.
     *
     * @param msg the msg
     */
    public MalformedIdTokenException(final String msg) {
        super(msg);
    }
}