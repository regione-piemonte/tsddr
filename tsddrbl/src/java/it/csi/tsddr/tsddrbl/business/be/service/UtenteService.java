/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.gestioneutente.UtenteVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.utente.DatiUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.InsertUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.UtenteParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.utentegestoreprofilo.UtenteGestoreProfiloVO;

/**
 * The Interface UtenteService.
 */
public interface UtenteService {
	
	/**
	 * Verifica parametri ricerca.
	 *
	 * @param utenteParametriRicercaVO the utente parametri ricerca VO
	 * @return the generic response
	 */
	GenericResponse<String> verificaParametriRicerca(UtenteParametriRicercaVO utenteParametriRicercaVO);
	
	/**
	 * Gets the griglia utenti.
	 *
	 * @param httpSession the http session
	 * @param utenteParametriRicercaVO the utente parametri ricerca VO
	 * @return the griglia utenti
	 */
	GenericResponse<List<DatiUtenteVO>> getGrigliaUtenti(HttpSession httpSession, UtenteParametriRicercaVO utenteParametriRicercaVO);
	
	/**
	 * Gets the parametri filtro applicati.
	 *
	 * @param utenteParametriRicercaVO the utente parametri ricerca VO
	 * @return the parametri filtro applicati
	 */
	GenericResponse<String> getParametriFiltroApplicati(UtenteParametriRicercaVO utenteParametriRicercaVO);

	/**
	 * Gets the ACL utente.
	 *
	 * @param httpSession the http session
	 * @return the ACL utente
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLUtente(HttpSession httpSession);
	
	/**
	 * Gets the dati utente.
	 *
	 * @param httpSession the http session
	 * @param idUtente the id utente
	 * @return the dati utente
	 */
	GenericResponse<DatiUtenteVO> getDatiUtente(HttpSession httpSession, Long idUtente);
	
	/**
	 * Gets the dati utente gestori.
	 *
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the dati utente gestori
	 */
	GenericResponse<List<UtenteGestoreProfiloVO>> getDatiUtenteGestori(Long idUtente, Long idProfilo);
	
	/**
	 * Gets the combo profili utente.
	 *
	 * @param idUtente the id utente
	 * @return the combo profili utente
	 */
	GenericResponse<List<SelectVO>> getComboProfiliUtente(Long idUtente);
	
	/**
	 * Update utente.
	 *
	 * @param httpSession the http session
	 * @param datiUtenteVO the dati utente VO
	 * @return the generic response
	 */
	GenericResponse<DatiUtenteVO> updateUtente(HttpSession httpSession, DatiUtenteVO datiUtenteVO);
	
	/**
	 * Removes the utente.
	 *
	 * @param httpSession the http session
	 * @param idUtente the id utente
	 * @return the generic response
	 */
	GenericResponse<String> removeUtente(HttpSession httpSession, Long idUtente);
	
	/**
	 * Verifica utente.
	 *
	 * @param codiceFiscale the codice fiscale
	 * @return the generic response
	 */
	GenericResponse<String> verificaUtente(String codiceFiscale);
	
	/**
	 * Creates the utente.
	 *
	 * @param httpSession the http session
	 * @param insertUtenteVO the insert utente VO
	 * @return the generic response
	 */
	GenericResponse<UtenteVO> createUtente(HttpSession httpSession, InsertUtenteVO insertUtenteVO);
	
	/**
	 * Gets the combo griglia gestori.
	 *
	 * @param httpSession the http session
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the combo griglia gestori
	 */
	GenericResponse<List<SelectVO>> getComboGrigliaGestori(HttpSession httpSession, Long idUtente, Long idProfilo);

	/**
	 * Creates the utente gestore profilo.
	 *
	 * @param httpSession the http session
	 * @param utenteGestoreProfiloVO the utente gestore profilo VO
	 * @return the generic response
	 */
	GenericResponse<UtenteGestoreProfiloVO> createUtenteGestoreProfilo(HttpSession httpSession, UtenteGestoreProfiloVO utenteGestoreProfiloVO);

	/**
	 * Update utente gestore profilo.
	 *
	 * @param httpSession the http session
	 * @param utenteGestoreProfiloVO the utente gestore profilo VO
	 * @return the generic response
	 */
	GenericResponse<UtenteGestoreProfiloVO> updateUtenteGestoreProfilo(HttpSession httpSession,
			UtenteGestoreProfiloVO utenteGestoreProfiloVO);
	
	/**
	 * Removes the utente gestore profilo.
	 *
	 * @param httpSession the http session
	 * @param idUtente the id utente
	 * @param idGestore the id gestore
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	GenericResponse<String> removeUtenteGestoreProfilo(HttpSession httpSession, Long idUtente, Long idGestore,
			Long idProfilo);

}
