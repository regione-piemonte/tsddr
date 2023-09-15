/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

import java.util.stream.Stream;

public enum StatoDomanda {
	
	IN_LAVORAZIONE(1), ACCETTATA(2), RIFIUTATA(3), ANNULLATA(4);
	
	private long id;
	
	StatoDomanda(long id) {
		this.id = id;
	}

	public static StatoDomanda byId(int id) {
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
