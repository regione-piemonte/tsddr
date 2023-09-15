/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDPeriodo;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.PeriodoVO;

/**
 * The Interface PeriodoEntityMapper.
 */
@Mapper
public interface PeriodoEntityMapper {

	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the periodo VO
	 */
	public PeriodoVO mapEntityToVO(TsddrDPeriodo entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<PeriodoVO> mapListEntityToListVO(List<TsddrDPeriodo> listEntity);
	
}