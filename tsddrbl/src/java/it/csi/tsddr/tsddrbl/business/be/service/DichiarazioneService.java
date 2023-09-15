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
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeBasicVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichiarazioneParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.AllowDuplicaDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.ExistsDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.SoggettoMrVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface DichiarazioneService.
 */
public interface DichiarazioneService {
	
	/**
	 * Gets the ACL dichiarazioni.
	 *
	 * @param httpSession the http session
	 * @return the ACL dichiarazioni
	 */
	GenericResponse<FunzionalitaProfiloVO> getACLDichiarazioni(HttpSession httpSession);
	
	/**
	 * Gets the combo anni.
	 *
	 * @param httpSession the http session
	 * @return the combo anni
	 */
	GenericResponse<List<Long>> getComboAnni(HttpSession httpSession);
	
	/**
	 * Gets the combo gestore.
	 *
	 * @param httpSession the http session
	 * @return the combo gestore
	 */
	GenericResponse<List<SelectVO>> getComboGestore(HttpSession httpSession);
	
	/**
	 * Gets the combo impianti.
	 *
	 * @param httpSession the http session
	 * @param idGestore the id gestore
	 * @return the combo impianti
	 */
	GenericResponse<List<SelectVO>> getComboImpianti(HttpSession httpSession, Long idGestore);
	
	/**
	 * Gets the parametri filtro applicati.
	 *
	 * @param httpSession the http session
	 * @param parametriRicerca the parametri ricerca
	 * @return the parametri filto applicati
	 */
	GenericResponse<String> getParametriFiltroApplicati(HttpSession httpSession, DichiarazioneParametriRicerca parametriRicerca);

	/**
	 * Gets the lista dichiarazioni.
	 *
	 * @param httpSession the http session
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista dichiarazioni
	 */
	GenericResponse<List<DichAnnualeBasicVO>> getListaDichiarazioni(HttpSession httpSession, DichiarazioneParametriRicerca parametriRicerca);
	
	/**
	 * Gets the dichiarazione.
	 *
	 * @param httpSession the http session
	 * @param idDichAnnuale the id dich annuale
	 * @return the dichiarazione
	 */
	GenericResponse<DichAnnualeVO> getDichiarazione(HttpSession httpSession, Long idDichAnnuale);

	/**
	 * Genera dichiarazione PDF.
	 *
	 * @param httpSession the http session
	 * @param idDichiarazione the id dichiarazione
	 * @return the generic response
	 */
	GenericResponse<String> generaDichiarazionePDF(HttpSession httpSession, Long idDichiarazione);
	
	/**
	 * Download dichiarazione PDF.
	 *
	 * @param httpSession the http session
	 * @param idDichiarazione the id dichiarazione
	 * @return the generic response
	 */
	GenericResponse<DocumentoProtocollatoVO> downloadDichiarazionePDF(HttpSession httpSession, Long idDichiarazione);

	/**
	 * Exists dichiarazione.
	 *
	 * @param httpSession the http session
	 * @param existsDichiarazioneVO the exists dichiarazione VO
	 * @return the generic response
	 */
	GenericResponse<Boolean> existsDichiarazione(HttpSession httpSession, ExistsDichiarazioneVO existsDichiarazioneVO);
	
	/**
	 * Gets the nuova versione dichiarazione.
	 *
	 * @param httpSession the http session
	 * @param idDichAnnuale the id dich annuale
	 * @return the nuova versione dichiarazione
	 */
	GenericResponse<DichAnnualeVO> getNuovaVersioneDichiarazione(HttpSession httpSession, Long idDichAnnuale);

	/**
	 * Removes the dichiarazione soggetto mr.
	 *
	 * @param httpSession the http session
	 * @param idDichAnnuale the id dich annuale
	 * @param idSoggettoMr the id soggetto mr
	 * @return the generic response
	 */
	GenericResponse<SoggettoMrVO> removeDichiarazioneSoggettoMr(HttpSession httpSession, Long idDichAnnuale,
			Long idSoggettoMr);

	/**
	 * Insert bozza dich annuale.
	 *
	 * @param httpSession the http session
	 * @param dichAnnualeVO the dich annuale VO
	 * @return the generic response
	 */
	GenericResponse<DichAnnualeVO> insertBozzaDichAnnuale(HttpSession httpSession, DichAnnualeVO dichAnnualeVO);

	/**
	 * Insert dich annuale.
	 *
	 * @param httpSession the http session
	 * @param dichAnnualeVO the dich annuale VO
	 * @return the generic response
	 */
	GenericResponse<DichAnnualeVO> insertDichAnnuale(HttpSession httpSession, DichAnnualeVO dichAnnualeVO);

    /**
     * Gets the dichiarazione by id gestore id impianto anno.
     *
     * @param httpSession the http session
     * @param idGestore the id gestore
     * @param idImpianto the id impianto
     * @param anno the anno
     * @return the dichiarazione by id gestore id impianto anno
     */
    GenericResponse<DichAnnualeVO> getDichiarazioneByIdGestoreIdImpiantoAnno(HttpSession httpSession, Long idGestore,
            Long idImpianto, Long anno);

	/**
	 * Delete dichiarazione.
	 *
	 * @param session the session
	 * @param idDichAnnuale the id dich annuale
	 * @return the generic response
	 */
	GenericResponse<DichAnnualeVO> deleteDichiarazione(HttpSession session, Long idDichAnnuale);

    /**
     * Checks if is duplica allowed.
     *
     * @param session the session
     * @param allowDuplicaDichiarazioneVO the allow duplica dichiarazione VO
     * @return the generic response
     */
    GenericResponse<Boolean> isDuplicaAllowed(HttpSession session, AllowDuplicaDichiarazioneVO allowDuplicaDichiarazioneVO);

	GenericResponse<ReportVO> downloadReport(HttpSession session, DichiarazioneParametriRicerca parametriRicerca);

	GenericResponse<DichAnnualeVO> deteteConferimenti(HttpSession session, Long idDichAnnuale, Long idRifiutoTariffa);
	
}
