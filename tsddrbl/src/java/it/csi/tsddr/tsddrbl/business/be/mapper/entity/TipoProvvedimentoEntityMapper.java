/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoProvvedimento;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;

/**
 * The Interface TipoProvvedimentoEntityMapper.
 */
@Mapper
public interface TipoProvvedimentoEntityMapper {
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idTipoProvvedimento")
	@Mapping(target = "descrizione", source = "descTipoProvvedimento")
	public SelectVO mapEntityToSelectVO(TsddrDTipoProvvedimento entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDTipoProvvedimento> listEntity);

}
