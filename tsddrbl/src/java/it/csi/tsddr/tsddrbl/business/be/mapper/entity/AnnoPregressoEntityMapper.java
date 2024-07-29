/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDAnnoPregresso;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;

/**
 * The Interface AnnoPregressoEntityMapper.
 */
@Mapper
public interface AnnoPregressoEntityMapper {
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idAnnoPregresso")
	@Mapping(target = "descrizione", source = "descAnnoPregresso")
	public SelectVO mapEntityToSelectVO(TsddrDAnnoPregresso entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDAnnoPregresso> listEntity);

}
