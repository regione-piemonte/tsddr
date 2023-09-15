/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

public class MailException extends GenericException {

	private static final long serialVersionUID = -4507122682632759911L;

	/**
	 * Instantiates a new mail exception.
	 */
	public MailException() {
	}

	/**
	 * Instantiates a new mail exception.
	 *
	 * @param e the e
	 */
	public MailException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new mail exception.
	 *
	 * @param message the message
	 */
	public MailException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new mail exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public MailException(String message, Exception e) {
		super(message, e);
	}

}
