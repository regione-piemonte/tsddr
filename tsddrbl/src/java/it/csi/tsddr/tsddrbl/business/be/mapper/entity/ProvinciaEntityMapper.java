/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.ProvinciaVO;

/**
 * The Interface ProvinciaEntityMapper.
 */
@Mapper(uses = RegioneEntityMapper.class)
public interface ProvinciaEntityMapper {
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idProvincia")
	@Mapping(target = "descrizione", source = "descProvincia")
	public SelectVO mapEntityToSelectVO(TsddrDProvincia entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the provincia VO
	 */
	public ProvinciaVO mapEntityToVO(TsddrDProvincia entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDProvincia> listEntity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<ProvinciaVO> mapListEntityToListVO(List<TsddrDProvincia> listEntity);

}
