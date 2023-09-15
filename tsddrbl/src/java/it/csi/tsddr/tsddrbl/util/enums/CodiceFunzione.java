/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

public enum CodiceFunzione {

	ALL_00(0L, "Home"),
	FO_001(1L, "Accreditamento"),
	FO_002(2L, "Nuova Domanda"),
	FO_003(3L, "Gestione Domande"),
	BO_001(4L, "Domande Accreditamento"),
	AM_001(5L, "Gestione Utenti e Profili"),
	AM_002(6L, "Gestione Utenti"),
	AM_003(7L, "Gestione Profili"),
	AM_004(8L, "Configurazione Profili"),
	AM_005(9L, "Associa Utenti a Profili"),
	SYS_001(10L, "Profilazione Utente"),
	ALL_01(11L, "Area Dati Personali"),
	AM_006(12L, "Gestori"),
	AM_007(13L, "Impianti"),
	ALL_02(14L, "Dichiarazione Annuale"),
	ALL_03(15L, "Richiesta Mis. Rid."),
    ALL_04(16L, "Dichiarazione Mis. Rid.");
	
	private Long idFunzione;
	private String descFunzione;

	CodiceFunzione(Long idFunzione, String descFunzione) {
		this.idFunzione = idFunzione;
		this.descFunzione = descFunzione;
	}

	/**
	 * @return the idFunzione
	 */
	public Long getIdFunzione() {
		return idFunzione;
	}

	/**
	 * @param idFunzione the idFunzione to set
	 */
	public void setIdFunzione(Long idFunzione) {
		this.idFunzione = idFunzione;
	}

	/**
	 * @return the descFunzione
	 */
	public String getDescFunzione() {
		return descFunzione;
	}

	/**
	 * @param descFunzione the descFunzione to set
	 */
	public void setDescFunzione(String descFunzione) {
		this.descFunzione = descFunzione;
	}
	
}
