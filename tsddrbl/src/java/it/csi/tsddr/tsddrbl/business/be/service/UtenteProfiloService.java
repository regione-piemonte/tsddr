/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.utenteprofilo.UtenteProfiloVO;

/**
 * The Interface UtenteProfiloService.
 */
public interface UtenteProfiloService {
	
	/**
	 * Gets the ACL utente profilo.
	 *
	 * @param httpSession the http session
	 * @return the ACL utente profilo
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLUtenteProfilo(HttpSession httpSession);
	
	/**
	 * Gets the lista utenti profilo.
	 *
	 * @param httpSession the http session
	 * @param idProfilo the id profilo
	 * @return the lista utenti profilo
	 */
	GenericResponse<List<UtenteProfiloVO>> getListaUtentiProfilo(HttpSession httpSession, Long idProfilo);
	
	/**
	 * Creates the utente profilo.
	 *
	 * @param httpSession the http session
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	GenericResponse<UtenteProfiloVO> createUtenteProfilo(HttpSession httpSession, Long idUtente, Long idProfilo);
	
	/**
	 * Delete utente profilo.
	 *
	 * @param httpSession the http session
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	GenericResponse<String> deleteUtenteProfilo(HttpSession httpSession, Long idUtente, Long idProfilo);
	
	/**
	 * Gets the combo nuovo utente.
	 *
	 * @param idProfilo the id profilo
	 * @return the combo nuovo utente
	 */
	GenericResponse<List<SelectVO>> getComboNuovoUtente(Long idProfilo);

}
