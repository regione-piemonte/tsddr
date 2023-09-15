/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.vo.areapersonale.AreaPersonaleUtenteVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface AreaPersonaleService.
 */
public interface AreaPersonaleService {
	
	/**
	 * Gets the nome cognome utente corrente.
	 *
	 * @param httpSession the http session
	 * @return the nome cognome utente corrente
	 */
	GenericResponse<AreaPersonaleUtenteVO> getNomeCognomeUtenteCorrente(HttpSession httpSession);

	/**
	 * Gets the dati utente corrente.
	 *
	 * @param httpSession the http session
	 * @return the dati utente corrente
	 */
	GenericResponse<AreaPersonaleUtenteVO> getDatiUtenteCorrente(HttpSession httpSession);

	/**
	 * Log logout.
	 *
	 * @param httpSession the http session
	 * @return the generic response
	 */
	GenericResponse<String> logLogout(HttpSession httpSession);

	/**
	 * Gets the ACL funzionalita profilo.
	 *
	 * @param httpSession the http session
	 * @return the ACL funzionalita profilo
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLFunzionalitaProfilo(HttpSession httpSession);

	/**
	 * Verifica dati obbligatori area personale.
	 *
	 * @param areaPersonaleUtenteVO the area personale utente VO
	 * @return the generic response
	 */
	GenericResponse<String> verificaDatiObbligatoriAreaPersonale(AreaPersonaleUtenteVO areaPersonaleUtenteVO);

	/**
	 * Update area personale utente.
	 *
	 * @param httpSession the http session
	 * @param areaPersonaleUtenteVO the area personale utente VO
	 * @return the generic response
	 */
	GenericResponse<DatiSoggVO> updateAreaPersonaleUtente(HttpSession httpSession, AreaPersonaleUtenteVO areaPersonaleUtenteVO);

	/**
	 * Gets the dati sogg corrente.
	 *
	 * @param userInfo the user info
	 * @return the dati sogg corrente
	 */
	TsddrTDatiSogg getDatiSoggCorrente(UserInfo userInfo);

}
