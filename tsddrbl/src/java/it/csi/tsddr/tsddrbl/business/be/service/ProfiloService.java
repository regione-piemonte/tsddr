/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface ProfiloService.
 */
public interface ProfiloService {
	
	/**
	 * Gets the combo profilo.
	 *
	 * @param showAll the show all
	 * @return the combo profilo
	 */
	GenericResponse<List<SelectVO>> getComboProfilo(boolean showAll);

	/**
	 * Gets the tabella profili.
	 *
	 * @param httpSession the http session
	 * @return the tabella profili
	 */
	GenericResponse<List<ProfiloVO>> getTabellaProfili(HttpSession httpSession);
	
	/**
	 * Gets the ACL profili.
	 *
	 * @param httpSession the http session
	 * @return the ACL profili
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLProfili(HttpSession httpSession);
	
	/**
	 * Verifica dati profilo.
	 *
	 * @param profiloVO the profilo VO
	 * @return the generic response
	 */
	GenericResponse<String> verificaDatiProfilo(ProfiloVO profiloVO);
	
	/**
	 * Update profilo.
	 *
	 * @param httpSession the http session
	 * @param profiloVO the profilo VO
	 * @return the generic response
	 */
	GenericResponse<ProfiloVO> updateProfilo(HttpSession httpSession, ProfiloVO profiloVO);
	
	/**
	 * Creates the profilo.
	 *
	 * @param httpSession the http session
	 * @param profiloVO the profilo VO
	 * @return the generic response
	 */
	GenericResponse<ProfiloVO> createProfilo(HttpSession httpSession, ProfiloVO profiloVO);
	
	/**
	 * Removes the profilo.
	 *
	 * @param httpSession the http session
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	GenericResponse<ProfiloVO> removeProfilo(HttpSession httpSession, Long idProfilo);
	
	/**
	 * Gets the id profili by codice fiscale.
	 *
	 * @param codiceFiscale the codice fiscale
	 * @return the id profili by codice fiscale
	 */
	List<Long> getIdProfiliByCodiceFiscale(String codiceFiscale);
	
	
	/**
	 * Checks if is profilo BO.
	 *
	 * @param idProfilo the id profilo
	 * @return true, if is profilo BO
	 */
	boolean isProfiloBO(Long idProfilo);

	/**
	 * Checks if is profilo Pregresso.
	 *
	 * @param idProfilo the id profilo
	 * @return true, if is profilo BO
	 */
	boolean isProfiloPregresso(Long idProfilo);
	
	/**
     * Checks if is profilo BO.
     *
     * @param idProfilo the id profilo
     * @return true, if is profilo BO
     */
	GenericResponse<Boolean> isProfiloBO(HttpSession httpSession, Long idProfilo);

}
