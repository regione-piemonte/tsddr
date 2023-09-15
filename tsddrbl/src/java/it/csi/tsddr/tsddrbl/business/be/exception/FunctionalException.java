/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

import java.util.Arrays;
import java.util.List;

import it.csi.tsddr.tsddrbl.util.enums.TipoMessaggio;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;

/**
 * The type FunctionalException exception.
 */
public class FunctionalException extends RuntimeException {

	private static final long serialVersionUID = -2631969419398877860L;

	private List<MessaggioVO> messaggiVO;
	
	/**
	 * Instantiates a new functional exception.
	 */
	public FunctionalException() {
	}

	/**
	 * Instantiates a new functional exception.
	 *
	 * @param message the message
	 */
	public FunctionalException(String message) {
		super(message);
		this.messaggiVO = Arrays
				.asList(new MessaggioVO(null, null, message, TipoMessaggio.INFORMATIVO.toString(), null));
	}
	
	/**
	 * Instantiates a new functional exception.
	 *
	 * @param e the e
	 */
	public FunctionalException(Exception e) {
		super(e);
	}
	
	/**
	 * Instantiates a new functional exception.
	 *
	 * @param message the message
	 * @param messaggioVO the messaggio VO
	 */
	public FunctionalException(String message, MessaggioVO messaggioVO) {
		super(message);
		this.messaggiVO = Arrays.asList(messaggioVO);
	}
	
	/**
	 * Instantiates a new functional exception.
	 *
	 * @param message the message
	 * @param messaggiVO the messaggi VO
	 */
	public FunctionalException(String message, List<MessaggioVO> messaggiVO) {
		super(message);
		this.messaggiVO = messaggiVO;
	}

	/**
	 * Instantiates a new functional exception.
	 *
	 * @param messaggioVO the messaggio VO
	 */
	public FunctionalException(MessaggioVO messaggioVO) {
		super();
		this.messaggiVO = Arrays.asList(messaggioVO);
	}
	
	/**
	 * Instantiates a new functional exception.
	 *
	 * @param messaggiVO the messaggi VO
	 */
	public FunctionalException(List<MessaggioVO> messaggiVO) {
		super();
		this.messaggiVO = messaggiVO;
	}

	/**
	 * Gets the messaggi VO.
	 *
	 * @return the messaggi VO
	 */
	public List<MessaggioVO> getMessaggiVO() {
		return messaggiVO;
	}

	/**
	 * Sets the messaggi VO.
	 *
	 * @param messaggiVO the new messaggi VO
	 */
	public void setMessaggiVO(List<MessaggioVO> messaggiVO) {
		this.messaggiVO = messaggiVO;
	}

}