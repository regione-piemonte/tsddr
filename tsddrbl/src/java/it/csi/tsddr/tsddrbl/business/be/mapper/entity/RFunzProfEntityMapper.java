/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRFunzProf;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;

/**
 * The Interface RFunzProfEntityMapper.
 */
@Mapper
public interface RFunzProfEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the funzionalita profilo VO
	 */
	@Mapping(target = "idFunzione", source = "id.idFunzione")
	@Mapping(target = "idProfilo", source = "id.idProfilo")
	@Mapping(target = "delete", source = "isDelete")
	@Mapping(target = "insert", source = "isInsert")
	@Mapping(target = "read", source = "isRead")
	@Mapping(target = "update", source = "isUpdate")
	public FunzionalitaProfiloVO mapEntityToVO(TsddrRFunzProf entity);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr R funz prof
	 */
	@Mapping(target = "id.idFunzione", source = "idFunzione")
	@Mapping(target = "id.idProfilo", source = "idProfilo")
	@Mapping(target = "isDelete", source = "delete")
	@Mapping(target = "isInsert", source = "insert")
	@Mapping(target = "isRead", source = "read")
	@Mapping(target = "isUpdate", source = "update")
	public TsddrRFunzProf mapVOToEntity(FunzionalitaProfiloVO vo);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<FunzionalitaProfiloVO> mapListEntityToListVO(List<TsddrRFunzProf> listEntity);
	
	/**
	 * Map list VO to list entity.
	 *
	 * @param listVO the list VO
	 * @return the list
	 */
	public List<TsddrRFunzProf> mapListVOToListEntity(List<FunzionalitaProfiloVO> listVO);

}
