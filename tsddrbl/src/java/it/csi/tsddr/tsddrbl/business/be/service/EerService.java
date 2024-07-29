/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface EerService.
 */
public interface EerService {
    
    /**
     * Gets the combo eer.
     *
     * @param httpSession the http session
     * @return the combo eer
     */
    GenericResponse<List<SelectVO>> getComboEer(HttpSession httpSession);

    /**
     * Gets the combo eer.
     *
     * @param httpSession the http session
     * @param date the date
     * @return the combo eer
     */
    GenericResponse<List<SelectVO>> getComboEerByDate(HttpSession httpSession, String year);

}
