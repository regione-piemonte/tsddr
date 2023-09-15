/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDomanda;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoVO;

/**
 * The Interface DomandaEntityMapper.
 */
@Mapper(uses = {DatiSoggEntityMapper.class, GestoreEntityMapper.class, StatoDomandaEntityMapper.class})
public interface DomandaEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the domanda accreditamento VO
	 */
	@Mapping(target = "richiedente", source = "datiSogg")
	@Mapping(target = "stato", source = "statoDomanda")
	public DomandaAccreditamentoVO mapEntityToVO(TsddrTDomanda entity);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr T domanda
	 */
	@Mapping(target = "statoDomanda", source = "stato")
	public TsddrTDomanda mapVOToEntity(DomandaAccreditamentoVO vo);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<DomandaAccreditamentoVO> mapListEntityToListVO(List<TsddrTDomanda> listEntity);

}
