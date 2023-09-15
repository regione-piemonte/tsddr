/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

public class SessionException extends GenericException {

	private static final long serialVersionUID = -2631969419398877860L;

	/**
	 * Instantiates a new session exception.
	 */
	public SessionException() {}
	
	/**
	 * Instantiates a new session exception.
	 *
	 * @param e the e
	 */
	public SessionException(Exception e) {
		super(e);
	}
	
	/**
	 * Instantiates a new session exception.
	 *
	 * @param message the message
	 */
	public SessionException(String message) {
		super(message);
	}

}
