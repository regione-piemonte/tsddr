/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoImpianto;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.impianto.StatoImpiantoVO;

/**
 * The Interface StatoImpiantoEntityMapper.
 */
@Mapper
public interface StatoImpiantoEntityMapper {
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idStatoImpianto")
	@Mapping(target = "descrizione", source = "descStatoImpianto")
	public SelectVO mapEntityToSelectVO(TsddrDStatoImpianto entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the stato impianto VO
	 */
	public StatoImpiantoVO mapEntityToVO(TsddrDStatoImpianto entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDStatoImpianto> listEntity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<StatoImpiantoVO> mapListEntityToListVO(List<TsddrDStatoImpianto> listEntity);

}
