/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.response;

import java.io.Serializable;
import java.util.List;

import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<MessaggioVO> errors;

	/**
	 * Instantiates a new error response.
	 *
	 * @param errors the errors
	 */
	public ErrorResponse(List<MessaggioVO> errors) {
		this.errors = errors;
	}
	
	/**
	 * Builds the.
	 *
	 * @param errors the errors
	 * @return the error response
	 */
	public static ErrorResponse build(MessaggioVO... errors) {
		return new ErrorResponse(List.of(errors));
	}
	
	/**
	 * Builds the.
	 *
	 * @param errors the errors
	 * @return the error response
	 */
	public static ErrorResponse build(List<MessaggioVO> errors) {
		return new ErrorResponse(errors);
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<MessaggioVO> getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors the new errors
	 */
	public void setErrors(List<MessaggioVO> errors) {
		this.errors = errors;
	}
	

}
