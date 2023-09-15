/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper;

import org.mapstruct.Named;

public interface BaseMapper {
	
	/**
	 * Increment long.
	 *
	 * @param number the number
	 * @return the long
	 */
	@Named("incrementLong")
	default Long incrementLong(Long number) {
		return number != null ? number++ : 1;
	}

}
