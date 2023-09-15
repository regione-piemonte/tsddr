/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLegaleRappresentante;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.gestore.LegaleRappresentanteVO;

/**
 * The Interface GestoreEntityMapper.
 */
@Mapper(uses = { DatiSoggEntityMapper.class, IndirizzoEntityMapper.class, NaturaGiuridicaEntityMapper.class })
public interface GestoreEntityMapper {
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idGestore")
	@Mapping(target = "descrizione", source = "ragSociale")
	public SelectVO mapEntityToSelectVO(TsddrTGestore entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the gestore VO
	 */
	@Mapping(target = "legaleRappresentante", source = "legaliRappresentanti", qualifiedByName = "activeLegaleRapp")
	public GestoreVO mapEntityToVO(TsddrTGestore entity);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr T gestore
	 */
	public TsddrTGestore mapVOToEntity(GestoreVO vo);
	
	/**
	 * Map RUGP entity to VO.
	 *
	 * @param entity the entity
	 * @return the gestore VO
	 */
	@Mapping(target = ".", source = "gestore")
	public GestoreVO mapRUGPEntityToVO(TsddrRUtenteGestoreProfilo entity);
	
	/**
	 * Map legale rappr entity to VO.
	 *
	 * @param entity the entity
	 * @return the legale rappresentante VO
	 */
	public LegaleRappresentanteVO mapLegaleRapprEntityToVO(TsddrTLegaleRappresentante entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrTGestore> listEntity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<GestoreVO> mapListEntityToListVO(List<TsddrTGestore> listEntity);
	
	/**
	 * Map list RUGP entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<GestoreVO> mapListRUGPEntityToListVO(List<TsddrRUtenteGestoreProfilo> listEntity);
	
	/**
	 * Active legale rapp.
	 *
	 * @param legaliRapp the legali rapp
	 * @return the legale rappresentante VO
	 */
	@Named("activeLegaleRapp")
	default LegaleRappresentanteVO activeLegaleRapp(List<TsddrTLegaleRappresentante> legaliRapp) {
		return legaliRapp == null ? null : legaliRapp.stream()
				.filter(lp -> lp.getDataFineValidita() == null && lp.getIdUserDelete() == null
						&& lp.getDataDelete() == null)
				.findFirst().map(this::mapLegaleRapprEntityToVO).orElse(null);
	}
}
