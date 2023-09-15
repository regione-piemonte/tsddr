/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTAtto;
import it.csi.tsddr.tsddrbl.vo.impianto.AttoVO;

// TODO: Auto-generated Javadoc
@Mapper(uses = {TipoProvvedimentoEntityMapper.class, ImpiantoEntityMapper.class})
public interface AttoEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the atto VO
	 */
	public AttoVO mapEntityToVO(TsddrTAtto entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<AttoVO> mapListEntityToListVO(List<TsddrTAtto> listEntity);

}
