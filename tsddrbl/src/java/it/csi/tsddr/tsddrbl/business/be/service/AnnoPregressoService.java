/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface AnnoPregresso.
 */
public interface AnnoPregressoService {
    
    /**
     * Gets the combo AnnoPregresso.
     *
     * @param httpSession the http session
     * @return the combo AnnoPregresso
     */
    GenericResponse<List<SelectVO>> getComboAnnoPregresso(HttpSession httpSession);

}
