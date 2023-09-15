/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

import java.util.stream.Stream;

public enum Periodo {

	I_TRIMESTRE(1), II_TRIMESTRE(2), III_TRIMESTRE(3), IV_TRIMESTRE(4), CONGUAGLIO(5);
	 
	private long id;
	
	private Periodo(int id) {
		this.id = id;
	}
	
	public static Periodo byId(long id) {
		return Stream.of(values()).filter(t -> t.getId() == id).findFirst().orElse(null);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return the id
	 */
	public int getIdAsInt() {
		return (int) id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
}
