/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

public class RecordNotFoundException extends GenericException {
	
	private static final long serialVersionUID = -2631969419398877860L;

	/**
	 * Instantiates a new record not found exception.
	 */
	public RecordNotFoundException() {}
	
	/**
	 * Instantiates a new record not found exception.
	 *
	 * @param e the e
	 */
	public RecordNotFoundException(Exception e) {
		super(e);
	}
	
	/**
	 * Instantiates a new record not found exception.
	 *
	 * @param message the message
	 */
	public RecordNotFoundException(String message) {
		super(message);
	}
	
}
