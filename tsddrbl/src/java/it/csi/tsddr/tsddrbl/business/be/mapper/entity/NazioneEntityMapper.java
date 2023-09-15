/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNazione;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.NazioneVO;

/**
 * The Interface NazioneEntityMapper.
 */
@Mapper
public interface NazioneEntityMapper {

	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idNazione")
	@Mapping(target = "descrizione", source = "descNazione")
	public SelectVO mapEntityToSelectVO(TsddrDNazione entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the nazione VO
	 */
	public NazioneVO mapEntityToVO(TsddrDNazione entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDNazione> listEntity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<NazioneVO> mapListEntityToListVO(List<TsddrDNazione> listEntity);
	
}
