/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import it.csi.tsddr.tsddrbl.vo.notainfo.NotaInfoVO;

/**
 * The Interface NotaInfoService.
 */
public interface NotaInfoService {
	
	/**
	 * Gets the nota info by cod nota info.
	 *
	 * @param codNotaInfo the cod nota info
	 * @return the nota info by cod nota info
	 */
	NotaInfoVO getNotaInfoByCodNotaInfo(String codNotaInfo);

}
