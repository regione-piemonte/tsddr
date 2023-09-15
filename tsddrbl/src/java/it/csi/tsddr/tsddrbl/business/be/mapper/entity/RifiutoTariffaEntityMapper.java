/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTRifiutoTariffa;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoTariffaVO;

/**
 * The Interface RifiutoTariffaEntityMapper.
 */
@Mapper
public interface RifiutoTariffaEntityMapper {
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idRifiutoTariffa")
	@Mapping(target = "descrizione", expression = "java(entity.getIdTipoRifiuto() + \", \" + entity.getIdTipologia2() + \", \" + entity.getIdTipologia3() + \", \" + entity.getDescrizione())")
	public SelectVO mapEntityToSelectVO(TsddrTRifiutoTariffa entity);
	
	/**
	 * Map list entity to select VO.
	 *
	 * @param entity the entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToSelectVO(List<TsddrTRifiutoTariffa> entity);
	
    /**
     * Map entity to VO.
     *
     * @param entity the entity
     * @return the rifiuto tariffa VO
     */
    public RifiutoTariffaVO mapEntityToVO(TsddrTRifiutoTariffa entity);
    
    /**
     * Map list entity to list VO.
     *
     * @param listEntity the list entity
     * @return the list
     */
    public List<RifiutoTariffaVO> mapListEntityToListVO(List<TsddrTRifiutoTariffa> listEntity);

}
