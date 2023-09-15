/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface GestoreService.
 */
public interface GestoreService {

	/**
	 * Gets the ACL gestori.
	 *
	 * @param httpSession the http session
	 * @return the ACL gestori
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLGestori(HttpSession httpSession);

	/**
	 * Gets the combo gestore.
	 *
	 * @return the combo gestore
	 */
	GenericResponse<List<SelectVO>> getComboGestore();
	
	/**
	 * Gets the parametri filto applicati.
	 *
	 * @param parametriRicerca the parametri ricerca
	 * @return the parametri filto applicati
	 */
	GenericResponse<String> getParametriFiltoApplicati(GestoreParametriRicerca parametriRicerca);

	/**
	 * Gets the lista gestori.
	 *
	 * @param httpSession the http session
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista gestori
	 */
	GenericResponse<List<GestoreVO>> getListaGestori(HttpSession httpSession, GestoreParametriRicerca parametriRicerca);

	/**
	 * Gets the gestore.
	 *
	 * @param httpSession the http session
	 * @param idGestore the id gestore
	 * @return the gestore
	 */
	GenericResponse<GestoreVO> getGestore(HttpSession httpSession, Long idGestore);

	/**
	 * Creates the gestore.
	 *
	 * @param httpSession the http session
	 * @param gestoreVO the gestore VO
	 * @return the generic response
	 */
	GenericResponse<GestoreVO> createGestore(HttpSession httpSession, GestoreVO gestoreVO);

	/**
	 * Updat gestore.
	 *
	 * @param HttpSession the http session
	 * @param idGestore the id gestore
	 * @param gestoreVO the gestore VO
	 * @return the generic response
	 */
	GenericResponse<GestoreVO> updatGestore(HttpSession HttpSession, Long idGestore, GestoreVO gestoreVO);

	/**
	 * Removes the gestore.
	 *
	 * @param httpSession the http session
	 * @param idGestore the id gestore
	 * @return the generic response
	 */
	GenericResponse<GestoreVO> removeGestore(HttpSession httpSession, Long idGestore);

	/**
	 * Gets the rappresentante legale.
	 *
	 * @param codFiscPartiva the cod fisc partiva
	 * @return the rappresentante legale
	 */
	GenericResponse<DatiSoggVO> getRappresentanteLegale(String codFiscPartiva);
	
	/**
	 * Checks for domande accreditamento.
	 *
	 * @param idGestore the id gestore
	 * @return the generic response
	 */
	GenericResponse<Boolean> hasDomandeAccreditamento(Long idGestore);
	
	/**
	 * Checks for impianti.
	 *
	 * @param idGestore the id gestore
	 * @return the generic response
	 */
	GenericResponse<Boolean> hasImpianti(Long idGestore);

	/**
	 * Exists gestore.
	 *
	 * @param codFiscPartiva the cod fisc partiva
	 * @return the generic response
	 */
	GenericResponse<Boolean> existsGestore(String codFiscPartiva);
	
	/**
	 * Gets the xls report.
	 *
	 * @param httpSession the http session
	 * @param parametriRicerca the parametri ricerca
	 * @return the xls report
	 */
	GenericResponse<ReportVO> downloadReport(HttpSession HttpSession, GestoreParametriRicerca parametriRicerca);

}
