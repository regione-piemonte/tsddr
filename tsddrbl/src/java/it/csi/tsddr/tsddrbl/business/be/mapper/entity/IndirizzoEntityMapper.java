/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;

/**
 * The Interface IndirizzoEntityMapper.
 */
@Mapper
public interface IndirizzoEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the indirizzo VO
	 */
	public IndirizzoVO mapEntityToVO(TsddrTIndirizzo entity);

}
