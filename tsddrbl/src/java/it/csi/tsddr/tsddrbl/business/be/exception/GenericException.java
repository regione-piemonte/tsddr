/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private MessaggioVO messaggioVO;
	
	/**
	 * Instantiates a new generic exception.
	 */
	public GenericException() {}
	
	/**
	 * Instantiates a new generic exception.
	 *
	 * @param messaggioVO the messaggio VO
	 */
	public GenericException(MessaggioVO messaggioVO) {
		this.messaggioVO = messaggioVO;
	}
	
	/**
	 * Instantiates a new generic exception.
	 *
	 * @param message the message
	 */
	public GenericException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new generic exception.
	 *
	 * @param e the e
	 */
	public GenericException(Exception e) {
		super(e);
	}
	
	/**
	 * Instantiates a new generic exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public GenericException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * Gets the messaggio VO.
	 *
	 * @return the messaggio VO
	 */
	public MessaggioVO getMessaggioVO() {
		return messaggioVO;
	}

	/**
	 * Sets the messaggio VO.
	 *
	 * @param messaggioVO the new messaggio VO
	 */
	public void setMessaggioVO(MessaggioVO messaggioVO) {
		this.messaggioVO = messaggioVO;
	}
	
}
