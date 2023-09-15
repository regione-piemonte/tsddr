/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSottoLinea;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.impianto.SottoLineaVO;

/**
 * The Interface SottoLineaEntityMapper.
 */
@Mapper
public interface SottoLineaEntityMapper {

	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idSottoLinea")
	@Mapping(target = "descrizione", source = "descSottoLinea")
	@Mapping(target = "descrizioneAggiuntiva", source = "linea.idLinea")
	public SelectVO mapEntityToSelectVO(TsddrTSottoLinea entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the sotto linea VO
	 */
	public SottoLineaVO mapEntityToVO(TsddrTSottoLinea entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrTSottoLinea> listEntity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SottoLineaVO> mapListEntityToListVO(List<TsddrTSottoLinea> listEntity);
	
}
