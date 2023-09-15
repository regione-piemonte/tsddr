/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import it.csi.tsddr.tsddrbl.vo.dichiarazione.PeriodoVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface PeriodoService.
 */
public interface PeriodoService {
	
	/**
	 * Gets the lista periodi.
	 *
	 * @return the lista periodi
	 */
	GenericResponse<List<PeriodoVO>> getListaPeriodi();
}
