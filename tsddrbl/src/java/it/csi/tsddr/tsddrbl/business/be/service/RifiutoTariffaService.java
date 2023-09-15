/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoTariffaVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface RifiutoTariffaService.
 */
public interface RifiutoTariffaService {
	
	/**
	 * Gets the combo rifiuto tariffa.
	 *
	 * @param httpSession the http session
	 * @param idDichAnnuale the id dich annuale
	 * @return the combo rifiuto tariffa
	 */
	GenericResponse<List<SelectVO>> getComboRifiutoTariffa(HttpSession httpSession, Long idDichAnnuale);

    /**
     * Gets the rifiuto tariffa.
     *
     * @param session the session
     * @param idDichAnnuale the id dich annuale
     * @return the rifiuto tariffa
     */
    GenericResponse<List<RifiutoTariffaVO>> getRifiutoTariffa(HttpSession session, Long idDichAnnuale);

}
