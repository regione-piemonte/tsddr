/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoDomanda;
import it.csi.tsddr.tsddrbl.vo.accreditamento.StatoDomandaVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;

/**
 * The Interface StatoDomandaEntityMapper.
 */
@Mapper
public interface StatoDomandaEntityMapper {

	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the stato domanda VO
	 */
	@Mapping(target = "id", source = "idStatoDomanda")
	@Mapping(target = "desc", source = "descStatoDomanda")
	public StatoDomandaVO mapEntityToVO(TsddrDStatoDomanda entity);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr D stato domanda
	 */
	@Mapping(target = "idStatoDomanda", source = "id")
	@Mapping(target = "descStatoDomanda", source = "desc")
	public TsddrDStatoDomanda mapVOToEntity(StatoDomandaVO vo);
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idStatoDomanda")
	@Mapping(target = "descrizione", source = "descStatoDomanda")
	public SelectVO mapEntityToSelectVO(TsddrDStatoDomanda entity);

	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<StatoDomandaVO> mapListEntityToListVO(List<TsddrDStatoDomanda> listEntity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDStatoDomanda> listEntity);
}
