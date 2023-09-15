/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoProfilo;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.TipologiaProfiloVO;

/**
 * The Interface TipoProfiloEntityMapper.
 */
@Mapper
public interface TipoProfiloEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the tipologia profilo VO
	 */
	public TipologiaProfiloVO mapEntityToVO(TsddrDTipoProfilo entity);
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idTipoProfilo")
	@Mapping(target = "descrizione", source = "descTipoProfilo")
	public SelectVO mapEntityToSelectVO(TsddrDTipoProfilo entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<TipologiaProfiloVO> mapListEntityToListVO(List<TsddrDTipoProfilo> listEntity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDTipoProfilo> listEntity);

}
