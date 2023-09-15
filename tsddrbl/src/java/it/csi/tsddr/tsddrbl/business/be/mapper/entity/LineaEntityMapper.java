/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLinea;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.impianto.LineaVO;

/**
 * The Interface LineaEntityMapper.
 */
@Mapper
public interface LineaEntityMapper {
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idLinea")
	@Mapping(target = "descrizione", source = "descLinea")
	public SelectVO mapEntityToSelectVO(TsddrTLinea entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the linea VO
	 */
	public LineaVO mapEntityToVO(TsddrTLinea entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<LineaVO> mapListEntityToListVO(List<TsddrTLinea> listEntity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrTLinea> listEntity);

}
