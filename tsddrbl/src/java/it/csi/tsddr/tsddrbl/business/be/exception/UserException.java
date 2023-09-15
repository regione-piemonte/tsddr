/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

public class UserException extends CSIException {
    
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new user exception.
	 *
	 * @param msg the msg
	 */
	public UserException(final String msg) {
        super(msg);
    }
    
    /**
     * Instantiates a new user exception.
     *
     * @param msg the msg
     * @param nestedClass the nested class
     * @param nestedMsg the nested msg
     */
    public UserException(final String msg, final String nestedClass, final String nestedMsg) {
        super(msg, nestedClass, nestedMsg);
    }
    
    /**
     * Instantiates a new user exception.
     *
     * @param msg the msg
     * @param nestedClass the nested class
     * @param nestedMsg the nested msg
     * @param stackTrace the stack trace
     */
    public UserException(final String msg, final String nestedClass, final String nestedMsg, final String stackTrace) {
        super(msg, nestedClass, nestedMsg, stackTrace);
    }
    
    /**
     * Instantiates a new user exception.
     *
     * @param msg the msg
     * @param nested the nested
     */
    public UserException(final String msg, final Throwable nested) {
        super(msg, nested);
    }
}