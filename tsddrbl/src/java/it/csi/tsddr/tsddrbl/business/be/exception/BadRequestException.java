/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

public class BadRequestException extends GenericException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new bad request exception.
	 */
	public BadRequestException() {}
	
	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param e the e
	 */
	public BadRequestException(Exception e) {
		super(e);
	}
	
	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param message the message
	 */
	public BadRequestException(String message) {
		super(message);
	}

}
