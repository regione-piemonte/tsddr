/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

import java.util.stream.Stream;

/**
 * 
 */
public enum TipoProfilo {
	
	BACK_OFFICE(1), FRONT_OFFICE(2);
	
	private long id;
	
	private TipoProfilo(int id) {
		this.id = id;
	}
	
	public static TipoProfilo byId(long id) {
		return Stream.of(values()).filter(t -> t.getId() == id).findFirst().orElse(null);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
