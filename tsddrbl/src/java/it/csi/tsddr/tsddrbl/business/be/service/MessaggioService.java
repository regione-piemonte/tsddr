/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;

/**
 * The Interface MessaggioService.
 */
public interface MessaggioService {
	
	/**
	 * Gets the messaggio by cod msg.
	 *
	 * @param codMsg the cod msg
	 * @return the messaggio by cod msg
	 */
	MessaggioVO getMessaggioByCodMsg(String codMsg);

}
