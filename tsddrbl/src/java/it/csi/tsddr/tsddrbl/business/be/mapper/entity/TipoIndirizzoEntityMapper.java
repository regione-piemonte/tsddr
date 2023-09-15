/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoImpianto;
import it.csi.tsddr.tsddrbl.vo.indirizzo.TipoIndirizzoVO;

/**
 * The Interface TipoIndirizzoEntityMapper.
 */
@Mapper
public interface TipoIndirizzoEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the tipo indirizzo VO
	 */
	public TipoIndirizzoVO mapEntityToVO(TsddrDTipoImpianto entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<TipoIndirizzoVO> mapListEntityToListVO(List<TsddrDTipoImpianto> listEntity);

}
