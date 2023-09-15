/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.ExistsPrevConsVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsBasicVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface PrevConsService.
 */
public interface PrevConsService {
    
    /**
     * Gets the combo gestore.
     *
     * @param httpSession the http session
     * @return the combo gestore
     */
    GenericResponse<List<SelectVO>> getComboGestore(HttpSession httpSession);
    
    /**
     * Gets the combo anni.
     *
     * @param httpSession the http session
     * @param idTipoDoc the id tipo doc
     * @param idStatoDichiarazione the id stato dichiarazione
     * @return the combo anni
     */
    GenericResponse<List<Long>> getComboAnni(HttpSession httpSession, Long idTipoDoc, Long idStatoDichiarazione);
    
    /**
     * Gets the combo impianti.
     *
     * @param httpSession the http session
     * @param idGestore the id gestore
     * @return the combo impianti
     */
    GenericResponse<List<SelectVO>> getComboImpianti(HttpSession httpSession, Long idGestore);

    /**
     * Gets the ACL dichiarazioni.
     *
     * @param httpSession the http session
     * @param idTipoDoc the id tipo doc
     * @return the ACL dichiarazioni
     */
    GenericResponse<FunzionalitaProfiloVO> getACLPrevCons(HttpSession httpSession, Long idTipoDoc);

    /**
     * Exists prev cons.
     *
     * @param session the session
     * @param existsPrevConsVO the exists prev cons VO
     * @return the generic response
     */
    GenericResponse<Boolean> existsPrevCons(HttpSession session, ExistsPrevConsVO existsPrevConsVO);
    
    /**
     * Insert bozza prev cons.
     *
     * @param httpSession the http session
     * @param prevConsVO the prev cons VO
     * @param idImpianto the id impianto
     * @param idGestore the id gestore
     * @return the generic response
     */
    GenericResponse<PrevConsVO> insertBozzaPrevCons(HttpSession httpSession, PrevConsVO prevConsVO, Long idImpianto, Long idGestore);

    /**
     * Insert prev cons.
     *
     * @param session the session
     * @param prevConsVO the prev cons VO
     * @param idImpianto the id impianto
     * @param idGestore the id gestore
     * @return the generic response
     */
    GenericResponse<PrevConsVO> insertPrevCons(HttpSession session, PrevConsVO prevConsVO, Long idImpianto,
            Long idGestore);

    /**
     * Delete linee.
     *
     * @param session the session
     * @param idPrevConsLinee the id prev cons linee
     * @param idSezione the id sezione
     * @return the generic response
     */
    GenericResponse<MessaggioVO> deleteLinee(HttpSession session, Long idPrevConsLinee, Long idSezione);
    
    /**
     * Delete prev cons.
     *
     * @param session the session
     * @param idPrevCons the id prev cons
     * @param idTipoDoc the id tipo doc
     * @return the generic response
     */
    GenericResponse<PrevConsVO> deletePrevCons(HttpSession session, Long idPrevCons, Long idTipoDoc);

    /**
     * Gets the lista prev cons.
     *
     * @param session the session
     * @param parametriRicerca the parametri ricerca
     * @return the lista prev cons
     */
    GenericResponse<List<PrevConsBasicVO>> getListaPrevCons(HttpSession session, PrevConsParametriRicerca parametriRicerca);

    /**
     * Gets the parametri filtro applicati.
     *
     * @param session the session
     * @param parametriRicerca the parametri ricerca
     * @return the parametri filto applicati
     */
    GenericResponse<String> getParametriFiltroApplicati(HttpSession session, PrevConsParametriRicerca parametriRicerca);

    /**
     * Gets the prev cons.
     *
     * @param session the session
     * @param idPrevCons the id prev cons
     * @param idTipoDoc the id tipo doc
     * @return the prev cons
     */
    GenericResponse<PrevConsExtendedVO> getPrevCons(HttpSession session, Long idPrevCons, Long idTipoDoc);

    /**
     * Download prev cons PDF.
     *
     * @param session the session
     * @param idPrevCons the id prev cons
     * @param idTipoDoc the id tipo doc
     * @return the generic response
     */
    GenericResponse<DocumentoProtocollatoVO> downloadPrevConsPDF(HttpSession session, Long idPrevCons, Long idTipoDoc);

    /**
     * Gets the linee richiesta.
     *
     * @param session the session
     * @param idPrevCons the id prev cons
     * @return the linee richiesta
     */
    GenericResponse<List<PrevConsLineeExtendedVO>> getLineeRichiesta(HttpSession session, Long idPrevCons);

	GenericResponse<ReportVO> downloadReport(HttpSession httpSession, PrevConsParametriRicerca parametriRicerca);

	GenericResponse<Boolean> isPercRecuperoVisible(HttpSession session, String codLinea, Long anno);

	GenericResponse<Boolean> isPercScartoVisible(HttpSession session, String codLinea, Long anno);

	public Boolean isPercRecuperoVisible(String codLinea, Long anno);
	
	public Boolean isPercScartoVisible(String codLinea, Long anno);
    
}