/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface TipoImpiantoService.
 */
public interface TipoImpiantoService {
	
	/**
	 * Gets the combo tipo impianto.
	 *
	 * @return the combo tipo impianto
	 */
	GenericResponse<List<SelectVO>> getComboTipoImpianto(); 

}
