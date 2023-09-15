/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.response;

import org.springframework.http.HttpStatus;

import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;

public class GenericResponse<T> {

	private MessaggioVO message;
	
	private T content;
	
	/**
	 * Instantiates a new generic response.
	 *
	 * @param message the message
	 * @param content the content
	 */
	public GenericResponse(MessaggioVO message, T content) {
		this.message = message;
		this.content = content;
	}
	
	/**
	 * Instantiates a new generic response.
	 *
	 * @param message the message
	 */
	public GenericResponse(MessaggioVO message) {
		this.message = message;
	}
	
	/**
	 * Instantiates a new generic response.
	 *
	 * @param content the content
	 */
	public GenericResponse(T content) {
		this.content = content;
	}

	/**
	 * Builds the.
	 *
	 * @param <T> the generic type
	 * @param message the message
	 * @param content the content
	 * @return the generic response
	 */
	public static <T> GenericResponse<T> build(MessaggioVO message, T content) {
		return new GenericResponse<>(message, content); 
	}
	
	/**
	 * Builds the.
	 *
	 * @param <T> the generic type
	 * @param message the message
	 * @return the generic response
	 */
	public static <T> GenericResponse<T> build(MessaggioVO message) {
		return new GenericResponse<>(message); 
	}

	/**
	 * Builds the.
	 *
	 * @param <T> the generic type
	 * @param content the content
	 * @return the generic response
	 */
	public static <T> GenericResponse<T> build(T content) {
		return new GenericResponse<>(content); 
	}
	
	/**
	 * Ok.
	 *
	 * @return the generic response
	 */
	public static GenericResponse<String> ok() {
		return new GenericResponse<>(HttpStatus.OK.toString()); 
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public MessaggioVO getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(MessaggioVO message) {
		this.message = message;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public T getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(T content) {
		this.content = content;
	}
	
}
