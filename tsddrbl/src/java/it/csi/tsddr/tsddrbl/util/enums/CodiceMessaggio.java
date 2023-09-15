/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

/**
 * 
 */
public enum CodiceMessaggio {
	
	A001(TipoMessaggio.ATTENZIONE),
	A002(TipoMessaggio.ATTENZIONE),
	A003(TipoMessaggio.ATTENZIONE),
	A004(TipoMessaggio.ERRORE),
	A005(TipoMessaggio.ATTENZIONE),
	A006(TipoMessaggio.ATTENZIONE),
	A007(TipoMessaggio.ATTENZIONE),
	A008(TipoMessaggio.ATTENZIONE),
	A009(TipoMessaggio.ATTENZIONE),
	A010(TipoMessaggio.ATTENZIONE),
	A011(TipoMessaggio.ATTENZIONE),
	A012(TipoMessaggio.ATTENZIONE),
	A013(TipoMessaggio.ATTENZIONE),
	A014(TipoMessaggio.ATTENZIONE),
	A015(TipoMessaggio.ATTENZIONE),
	A016(TipoMessaggio.ATTENZIONE),
	A017(TipoMessaggio.ATTENZIONE),
	A018(TipoMessaggio.ATTENZIONE),
	A019(TipoMessaggio.ATTENZIONE),
	A020(TipoMessaggio.ATTENZIONE),
	A021(TipoMessaggio.ATTENZIONE),
	A022(TipoMessaggio.ATTENZIONE),
	A023(TipoMessaggio.ATTENZIONE),
	A14(TipoMessaggio.ATTENZIONE),
	A15(TipoMessaggio.ATTENZIONE),
	E001(TipoMessaggio.ERRORE),
	E002(TipoMessaggio.ERRORE),
	E003(TipoMessaggio.ERRORE),
	E004(TipoMessaggio.ERRORE),
	E005(TipoMessaggio.ERRORE),
	E006(TipoMessaggio.ERRORE),
	E007(TipoMessaggio.ERRORE),
	E008(TipoMessaggio.ERRORE),
	E009(TipoMessaggio.ERRORE),
	E010(TipoMessaggio.ERRORE),
	E11(TipoMessaggio.ERRORE),
	E012(TipoMessaggio.ERRORE),
	E013(TipoMessaggio.ERRORE),
	E014(TipoMessaggio.ERRORE),
	I001(TipoMessaggio.INFORMATIVO),
	I002(TipoMessaggio.INFORMATIVO),
	I003(TipoMessaggio.INFORMATIVO),
	I004(TipoMessaggio.ATTENZIONE),
	P001(TipoMessaggio.POSITIVO),
	P002(TipoMessaggio.POSITIVO),
	P003(TipoMessaggio.POSITIVO),
	P004(TipoMessaggio.POSITIVO),
	P005(TipoMessaggio.POSITIVO),
	P006(TipoMessaggio.POSITIVO),
	P007(TipoMessaggio.POSITIVO),
	P008(TipoMessaggio.POSITIVO),
	P017(TipoMessaggio.POSITIVO),
	P009(TipoMessaggio.POSITIVO),
	P010(TipoMessaggio.POSITIVO),
	P011(TipoMessaggio.POSITIVO),
	P012(TipoMessaggio.POSITIVO),
	P013(TipoMessaggio.POSITIVO),
	P014(TipoMessaggio.POSITIVO),
	P015(TipoMessaggio.POSITIVO),
	P016(TipoMessaggio.POSITIVO),
	P018(TipoMessaggio.POSITIVO),
	P019(TipoMessaggio.POSITIVO),
	P020(TipoMessaggio.POSITIVO);
	
	private TipoMessaggio tipoMessaggio;

	CodiceMessaggio(TipoMessaggio tipoMessaggio) {
		this.tipoMessaggio = tipoMessaggio;
	}

	/**
	 * @return the tipoMessaggio
	 */
	public TipoMessaggio getTipoMessaggio() {
		return tipoMessaggio;
	}

	/**
	 * @param tipoMessaggio the tipoMessaggio to set
	 */
	public void setTipoMessaggio(TipoMessaggio tipoMessaggio) {
		this.tipoMessaggio = tipoMessaggio;
	}


}
