/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

import java.util.stream.Stream;

public enum TipoIndirizzo {

	SEDE_LEGALE_GESTORE(1), SITO_DELL_IMPIANTO(2), SEDE_CONSERVAZIONE_DOCUMENTI(3);
	
	private long id;
	
	TipoIndirizzo(long id) {
		this.id = id;
	}

	public static TipoIndirizzo byId(int id) {
		return Stream.of(values()).filter(t -> t.getId() == id).findFirst().orElse(null);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
