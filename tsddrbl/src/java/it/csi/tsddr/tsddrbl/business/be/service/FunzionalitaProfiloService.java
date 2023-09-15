/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface FunzionalitaProfiloService.
 */
public interface FunzionalitaProfiloService {

	/**
	 * Gets the combo funzionalita per profilo.
	 *
	 * @param idProfilo the id profilo
	 * @return the combo funzionalita per profilo
	 */
	GenericResponse<List<SelectVO>> getComboFunzionalitaPerProfilo(Long idProfilo);
	
	/**
	 * Gets the lista funzionalita profilo.
	 *
	 * @param idProfilo the id profilo
	 * @param idFunzionalita the id funzionalita
	 * @return the lista funzionalita profilo
	 */
	GenericResponse<List<FunzionalitaProfiloVO>> getListaFunzionalitaProfilo(Long idProfilo, Long idFunzionalita);
	
	/**
	 * Gets the combo nuova funzionalita.
	 *
	 * @param idProfilo the id profilo
	 * @return the combo nuova funzionalita
	 */
	GenericResponse<List<SelectVO>> getComboNuovaFunzionalita(Long idProfilo);
	
	/**
	 * Update funzionalita profilo.
	 *
	 * @param httpSession the http session
	 * @param FunzionalitaProfiloVO the funzionalita profilo VO
	 * @return the generic response
	 */
	GenericResponse<FunzionalitaProfiloVO> updateFunzionalitaProfilo(HttpSession httpSession, FunzionalitaProfiloVO FunzionalitaProfiloVO);
	
	/**
	 * Creates the funzionalita profilo.
	 *
	 * @param httpSession the http session
	 * @param FunzionalitaProfiloVO the funzionalita profilo VO
	 * @return the generic response
	 */
	GenericResponse<FunzionalitaProfiloVO> createFunzionalitaProfilo(HttpSession httpSession, FunzionalitaProfiloVO FunzionalitaProfiloVO);
	
	/**
	 * Removes the funzionalita profilo.
	 *
	 * @param httpSession the http session
	 * @param idProfilo the id profilo
	 * @param idFunzionalita the id funzionalita
	 * @return the generic response
	 */
	GenericResponse<FunzionalitaProfiloVO> removeFunzionalitaProfilo(HttpSession httpSession, Long idProfilo, Long idFunzionalita);
	
	/**
	 * Gets the funzionalita profilo by cod funzione.
	 *
	 * @param idProfilo the id profilo
	 * @param codFunzione the cod funzione
	 * @return the funzionalita profilo by cod funzione
	 */
	FunzionalitaProfiloVO getFunzionalitaProfiloByCodFunzione(Long idProfilo, CodiceFunzione codFunzione);
	
	/**
	 * Log conf profilo funzionalita.
	 *
	 * @param httpSession the http session
	 * @param idProfilo the id profilo
	 * @param idFunzionalita the id funzionalita
	 * @return the generic response
	 */
	GenericResponse<String> logConfProfiloFunzionalita(HttpSession httpSession, Long idProfilo, Long idFunzionalita);

	/**
	 * Gets the ACL funzionalita profilo.
	 *
	 * @param httpSession the http session
	 * @return the ACL funzionalita profilo
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLFunzionalitaProfilo(HttpSession httpSession);	
}
