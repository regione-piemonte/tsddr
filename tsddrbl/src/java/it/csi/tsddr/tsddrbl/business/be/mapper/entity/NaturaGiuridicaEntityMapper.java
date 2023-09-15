/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNaturaGiuridica;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.gestore.NaturaGiuridicaVO;

/**
 * The Interface NaturaGiuridicaEntityMapper.
 */
@Mapper
public interface NaturaGiuridicaEntityMapper {

	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idNaturaGiuridica")
	@Mapping(target = "descrizione", source = "descNaturaGiuridica")
	public SelectVO mapEntityToSelectVO(TsddrDNaturaGiuridica entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the natura giuridica VO
	 */
	public NaturaGiuridicaVO mapEntityToVO(TsddrDNaturaGiuridica entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDNaturaGiuridica> listEntity);
}
