/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;

/**
 * The Interface ProfiloEntityMapper.
 */
@Mapper
public interface ProfiloEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the profilo VO
	 */
	@Mapping(target = "tipologiaProfiloVO", source = "tipoProfilo")
	public ProfiloVO mapEntityToVO(TsddrDProfilo entity);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr D profilo
	 */
	@Mapping(target = "tipoProfilo", source = "tipologiaProfiloVO")
	public TsddrDProfilo mapVOToEntity(ProfiloVO vo);
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idProfilo")
	@Mapping(target = "descrizione", source = "descProfilo")
	@Mapping(target = "descrizioneAggiuntiva", source = "tipoProfilo.idTipoProfilo")
	public SelectVO mapEntityToSelectVO(TsddrDProfilo entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<ProfiloVO> mapListEntityToListVO(List<TsddrDProfilo> listEntity);
	
	/**
	 * Map list VO to list entity.
	 *
	 * @param listVO the list VO
	 * @return the list
	 */
	public List<TsddrDProfilo> mapListVOToListEntity(List<ProfiloVO> listVO);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDProfilo> listEntity);
	
}