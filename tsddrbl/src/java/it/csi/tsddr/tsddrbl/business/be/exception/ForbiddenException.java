/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

public class ForbiddenException extends GenericException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new forbidden exception.
	 */
	public ForbiddenException() {
		super();
	}
	
	/**
	 * Instantiates a new forbidden exception.
	 *
	 * @param e the e
	 */
	public ForbiddenException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new forbidden exception.
	 *
	 * @param message the message
	 */
	public ForbiddenException(String message) {
		super(message);
	}
	
}
