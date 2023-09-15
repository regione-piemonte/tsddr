/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface AccreditamentoService.
 */
public interface AccreditamentoService {

	/**
	 * Gets the ACL accreditamento.
	 *
	 * @param httpSession the http session
	 * @return the ACL accreditamento
	 */
	public GenericResponse<FunzionalitaProfiloVO> getACLAccreditamento(HttpSession httpSession);

	/**
	 * Gets the lista domande.
	 *
	 * @param httpSession the http session
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista domande
	 */
	public GenericResponse<List<DomandaAccreditamentoVO>> getListaDomande(HttpSession httpSession, DomandaAccreditamentoParametriRicercaVO parametriRicerca);
	
	/**
	 * Gets the domanda.
	 *
	 * @param httpSession the http session
	 * @param idDomanda the id domanda
	 * @return the domanda
	 */
	public GenericResponse<DomandaAccreditamentoVO> getDomanda(HttpSession httpSession, Long idDomanda);

	/**
	 * Nuova domanda.
	 *
	 * @param httpSession the http session
	 * @param domandaVO the domanda VO
	 * @return the generic response
	 */
	public GenericResponse<DomandaAccreditamentoVO> nuovaDomanda(HttpSession httpSession, DomandaAccreditamentoVO domandaVO);

	/**
	 * Aggiorna domanda.
	 *
	 * @param httpSession the http session
	 * @param idDomanda the id domanda
	 * @param domandaVO the domanda VO
	 * @return the generic response
	 */
	public GenericResponse<DomandaAccreditamentoVO> aggiornaDomanda(HttpSession httpSession, Long idDomanda, DomandaAccreditamentoVO domandaVO);

	/**
	 * Annulla domanda.
	 *
	 * @param httpSession the http session
	 * @param idDomanda the id domanda
	 * @return the generic response
	 */
	public GenericResponse<DomandaAccreditamentoVO> annullaDomanda(HttpSession httpSession, Long idDomanda);

	/**
	 * Verifica dati obbligatori.
	 *
	 * @param httpSession the http session
	 * @param domandaVO the domanda VO
	 * @return the generic response
	 */
	public GenericResponse<String> verificaDatiObbligatori(HttpSession httpSession, DomandaAccreditamentoVO domandaVO);

	/**
	 * Gets the combo gestori.
	 *
	 * @param httpSession the http session
	 * @return the combo gestori
	 */
	public GenericResponse<List<SelectVO>> getComboGestori(HttpSession httpSession);
	
	/**
	 * Gets the combo stati domanda.
	 *
	 * @param httpSession the http session
	 * @return the combo stati domanda
	 */
	public GenericResponse<List<SelectVO>> getComboStatiDomanda(HttpSession httpSession);
	
	/**
	 * Gets the all combo stati domanda.
	 *
	 * @param httpSession the http session
	 * @return the all combo stati domanda
	 */
	public GenericResponse<List<SelectVO>> getAllComboStatiDomanda(HttpSession httpSession);

	/**
	 * Gets the combo richidenti.
	 *
	 * @param session the session
	 * @return the combo richidenti
	 */
	public GenericResponse<List<SelectVO>> getComboRichidenti(HttpSession session);

	/**
	 * Gets the combo profili.
	 *
	 * @param session the session
	 * @param idDomanda the id domanda
	 * @return the combo profili
	 */
	public GenericResponse<List<SelectVO>> getComboProfili(HttpSession session, Long idDomanda);

	/**
	 * Gets the parametri filtro applicati accreditamento.
	 *
	 * @param session the session
	 * @param parametriRicerca the parametri ricerca
	 * @return the parametri filtro applicati accreditamento
	 */
	public GenericResponse<String> getParametriFiltroApplicatiAccreditamento(HttpSession session,
			DomandaAccreditamentoParametriRicercaVO parametriRicerca);

	public GenericResponse<ReportVO> downloadReport(HttpSession session,
			DomandaAccreditamentoParametriRicercaVO parametriRicerca);
	
}
