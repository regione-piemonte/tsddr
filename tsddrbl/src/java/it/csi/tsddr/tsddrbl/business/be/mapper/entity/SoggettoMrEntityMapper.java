/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSoggettoMr;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.SoggettoMrVO;

/**
 * The Interface SoggettoMrEntityMapper.
 */
@Mapper
public interface SoggettoMrEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the soggetto mr VO
	 */
	public SoggettoMrVO mapEntityToVO(TsddrTSoggettoMr entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	@Mapping(target = ".", qualifiedByName = "activeSog")
	public List<SoggettoMrVO> mapListEntityToListVO(List<TsddrTSoggettoMr> listEntity);

	/**
	 * Active sog.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	@Named("activeSog")
	default List<SoggettoMrVO> activeSog(List<TsddrTSoggettoMr> listEntity) {
		return listEntity == null ? null : listEntity.stream()
				.filter(lp -> lp.getDataDelete() == null && lp.getIdUserDelete() == null)
				.map(this::mapEntityToVO).collect(Collectors.toList());
	}
}
