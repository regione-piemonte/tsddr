/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrEmailTesti;

/**
 * The Interface EmailTestiService.
 */
public interface EmailTestiService {
	
	/**
	 * Gets the email testi by id.
	 *
	 * @param idEmail the id email
	 * @return the email testi by id
	 */
	TsddrEmailTesti getEmailTestiById(Long idEmail);

}
