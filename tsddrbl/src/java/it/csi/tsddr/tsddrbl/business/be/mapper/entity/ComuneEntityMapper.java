/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.ComuneVO;

/**
 * The Interface ComuneEntityMapper.
 */
@Mapper(uses = ProvinciaEntityMapper.class)
public interface ComuneEntityMapper {

	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idComune")
	@Mapping(target = "descrizione", source = "comune")
	@Mapping(target = "descrizioneAggiuntiva", source = "cap")
	public SelectVO mapEntityToSelectVO(TsddrDComune entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the comune VO
	 */
	public ComuneVO mapEntityToVO(TsddrDComune entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDComune> listEntity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<ComuneVO> mapListEntityToListVO(List<TsddrDComune> listEntity);
	
}
