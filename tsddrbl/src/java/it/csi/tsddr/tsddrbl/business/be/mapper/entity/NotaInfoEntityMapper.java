/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTNotaInfo;
import it.csi.tsddr.tsddrbl.vo.notainfo.NotaInfoVO;

/**
 * The Interface NotaInfoEntityMapper.
 */
@Mapper
public interface NotaInfoEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the nota info VO
	 */
	public NotaInfoVO mapEntityToVO(TsddrTNotaInfo entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<NotaInfoVO> mapListEntityToListVO(List<TsddrTNotaInfo> listEntity);

}
