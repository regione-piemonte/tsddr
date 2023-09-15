/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSedime;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.SedimeVO;

/**
 * The Interface SedimeEntityMapper.
 */
@Mapper
public interface SedimeEntityMapper {

	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idSedime")
	@Mapping(target = "descrizione", source = "descSedime")
	public SelectVO mapEntityToSelectVO(TsddrDSedime entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the sedime VO
	 */
	public SedimeVO mapEntityToVO(TsddrDSedime entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDSedime> listEntity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SedimeVO> mapListEntityToListVO(List<TsddrDSedime> listEntity);
	
}
