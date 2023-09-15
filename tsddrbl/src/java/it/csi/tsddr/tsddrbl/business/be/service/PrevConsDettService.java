/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface PrevConsService.
 */
public interface PrevConsDettService {

    /**
     * Delete prev cons dett.
     *
     * @param session the session
     * @param idPrevConsDett the id prev cons dett
     * @return the generic response
     */
    GenericResponse<MessaggioVO> deletePrevConsDett(HttpSession session, Long idPrevConsDett);

}
