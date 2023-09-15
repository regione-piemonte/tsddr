/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;

/**
 * The Interface DatiSoggEntityMapper.
 */
@Mapper
public interface DatiSoggEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the dati sogg VO
	 */
	public DatiSoggVO mapEntityToVO(TsddrTDatiSogg entity);
	
	/**
	 * Map VO to entity.
	 *
	 * @param entity the entity
	 * @return the tsddr T dati sogg
	 */
	public TsddrTDatiSogg mapVOToEntity(DatiSoggVO entity);
	
	/**
	 * Map entity richiedente to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idDatiSogg")
	@Mapping(target = "descrizione", expression = "java(entity.getCognome() + \" \" + entity.getNome())")
	public SelectVO mapEntityRichiedenteToSelectVO(TsddrTDatiSogg entity);
	
	/**
	 * Map list entity richiedente to select VO.
	 *
	 * @param entity the entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityRichiedenteToSelectVO(List<TsddrTDatiSogg> entity);
	
}
