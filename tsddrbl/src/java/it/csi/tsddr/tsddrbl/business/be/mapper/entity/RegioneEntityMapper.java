/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDRegione;
import it.csi.tsddr.tsddrbl.vo.indirizzo.RegioneVO;

/**
 * The Interface RegioneEntityMapper.
 */
@Mapper
public interface RegioneEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the regione VO
	 */
	public RegioneVO mapEntityToVO(TsddrDRegione entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<RegioneVO> mapListEntityToListVO(List<TsddrDRegione> listEntity);

}
