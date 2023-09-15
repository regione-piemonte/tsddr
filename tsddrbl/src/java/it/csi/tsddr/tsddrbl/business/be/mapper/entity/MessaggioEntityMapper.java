/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTMessaggio;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;

/**
 * The Interface MessaggioEntityMapper.
 */
@Mapper
public interface MessaggioEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the messaggio VO
	 */
	@Mapping(target = "descTipoMsg", source = "tipoMsg.descTipoMsg")
	public MessaggioVO mapEntityToVO(TsddrTMessaggio entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<MessaggioVO> mapListEntityToListVO(List<TsddrTMessaggio> listEntity);
	
}
