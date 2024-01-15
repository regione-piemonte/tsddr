/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.impianto.AttoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.CheckImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.LineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.SottoLineaVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface ImpiantoService.
 */
public interface ImpiantoService {

	/**
	 * Gets the ACL impianti.
	 *
	 * @param httpSession the http session
	 * @return the ACL impianti
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLImpianti(HttpSession httpSession);

	/**
	 * Gets the combo linee.
	 *
	 * @return the combo linee
	 */
	GenericResponse<List<SelectVO>> getComboLinee();
	
	/**
	 * Gets the combo linee impianto.
	 *
	 * @param idImpianto the id impianto
	 * @return the combo linee impianto
	 */
	GenericResponse<List<SelectVO>> getComboLineeImpianto(Long idImpianto);

	/**
	 * Gets the combo tipo provvedimento.
	 *
	 * @return the combo tipo provvedimento
	 */
	GenericResponse<List<SelectVO>> getComboTipoProvvedimento();

	/**
	 * Gets the parametri filto applicati.
	 *
	 * @param parametriRicerca the parametri ricerca
	 * @return the parametri filto applicati
	 */
	GenericResponse<String> getParametriFiltoApplicati(ImpiantoParametriRicerca parametriRicerca);

	/**
	 * Gets the lista impianti.
	 *
	 * @param httpSession the http session
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista impianti
	 */
	GenericResponse<List<ImpiantoVO>> getListaImpianti(HttpSession httpSession, ImpiantoParametriRicerca parametriRicerca);

	/**
	 * Adds the impianto.
	 *
	 * @param httpSession the http session
	 * @param impiantoVO the impianto VO
	 * @return the generic response
	 */
	GenericResponse<ImpiantoVO> addImpianto(HttpSession httpSession, ImpiantoVO impiantoVO);

	/**
	 * Gets the impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @return the impianto
	 */
	GenericResponse<ImpiantoVO> getImpianto(HttpSession httpSession, Long idImpianto);

	/**
	 * Update impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param impiantoVO the impianto VO
	 * @return the generic response
	 */
	GenericResponse<ImpiantoVO> updateImpianto(HttpSession httpSession, Long idImpianto, ImpiantoVO impiantoVO);

	/**
	 * Removes the impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @return the generic response
	 */
	GenericResponse<ImpiantoVO> removeImpianto(HttpSession httpSession, Long idImpianto);
	
	/**
	 * Gets the linee impianto.
	 *
	 * @param idImpianto the id impianto
	 * @return the linee impianto
	 */
	GenericResponse<List<GenericLineaVO>> getLineeImpianto(Long idImpianto, String idPrevCons);

	/**
	 * Adds the linea impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idLinea the id linea
	 * @param lineaVO the linea VO
	 * @return the generic response
	 */
	GenericResponse<LineaVO> addLineaImpianto(HttpSession httpSession, Long idImpianto, Long idLinea, LineaVO lineaVO);

	/**
	 * Update linea impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idLinea the id linea
	 * @param lineaVO the linea VO
	 * @return the generic response
	 */
	GenericResponse<LineaVO> updateLineaImpianto(HttpSession httpSession, Long idImpianto, Long idLinea, LineaVO lineaVO);

	/**
	 * Removes the linea impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idLinea the id linea
	 * @return the generic response
	 */
	GenericResponse<LineaVO> removeLineaImpianto(HttpSession httpSession, Long idImpianto, Long idLinea);

	/**
	 * Adds the sotto linea impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idSottoLinea the id sotto linea
	 * @param sottoLineaVO the sotto linea VO
	 * @return the generic response
	 */
	GenericResponse<SottoLineaVO> addSottoLineaImpianto(HttpSession httpSession, Long idImpianto, Long idSottoLinea, SottoLineaVO sottoLineaVO);

	/**
	 * Update sotto linea impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idSottoLinea the id sotto linea
	 * @param sottoLineaVO the sotto linea VO
	 * @return the generic response
	 */
	GenericResponse<SottoLineaVO> updateSottoLineaImpianto(HttpSession httpSession, Long idImpianto, Long idSottoLinea, SottoLineaVO sottoLineaVO);

	/**
	 * Removes the sotto linea impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idSottoLinea the id sotto linea
	 * @return the generic response
	 */
	GenericResponse<SottoLineaVO> removeSottoLineaImpianto(HttpSession httpSession, Long idImpianto, Long idSottoLinea);

	/**
	 * Gets the atti impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @return the atti impianto
	 */
	GenericResponse<List<AttoVO>> getAttiImpianto(HttpSession httpSession, Long idImpianto);

	/**
	 * Adds the atto impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param attoVO the atto VO
	 * @return the generic response
	 */
	GenericResponse<AttoVO> addAttoImpianto(HttpSession httpSession, Long idImpianto, AttoVO attoVO);

	/**
	 * Update atto impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idAtto the id atto
	 * @param attoVO the atto VO
	 * @return the generic response
	 */
	GenericResponse<AttoVO> updateAttoImpianto(HttpSession httpSession, Long idImpianto, Long idAtto, AttoVO attoVO);

	/**
	 * Removes the atto impianto.
	 *
	 * @param httpSession the http session
	 * @param idImpianto the id impianto
	 * @param idAtto the id atto
	 * @return the generic response
	 */
	GenericResponse<AttoVO> removeAttoImpianto(HttpSession httpSession, Long idImpianto, Long idAtto);
	
	/**
	 * Verifica parametri.
	 *
	 * @param parametriRicerca the parametri ricerca
	 * @return the generic response
	 */
	GenericResponse<String> verificaParametri(ImpiantoParametriRicerca parametriRicerca);

	
    /**
	 * Gets the xls report.
	 *
	 * @param httpSession the http session
	 * @param parametriRicerca the parametri ricerca
	 * @return the xls report
	 */
	GenericResponse<ReportVO> downloadReport(HttpSession HttpSession, ImpiantoParametriRicerca parametriRicerca);

	
	/**
     * Controlla se l'impianto specificato è eliminabile.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @param idImpianto the id impianto
     * @return the generic response
     */
	GenericResponse<CheckImpiantoVO> checkDeleteImpianto(Long idImpianto);
	
}
