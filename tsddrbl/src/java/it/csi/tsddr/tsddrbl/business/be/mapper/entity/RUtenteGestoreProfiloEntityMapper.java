/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfilo;
import it.csi.tsddr.tsddrbl.vo.utentegestoreprofilo.UtenteGestoreProfiloVO;

/**
 * The Interface RUtenteGestoreProfiloEntityMapper.
 */
@Mapper
public interface RUtenteGestoreProfiloEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the utente gestore profilo VO
	 */
	@Mapping(target = "idUtente", source = "id.idUtente")
	@Mapping(target = "idProfilo", source = "id.idProfilo")
	@Mapping(target = "idGestore", source = "id.idGestore")
	@Mapping(target = "dataInizioValidita", source = "dataInizioValidita")
	@Mapping(target = "dataFineValidita", source = "dataFineValidita")
	@Mapping(target = "gestore", source = "gestore")
	public UtenteGestoreProfiloVO mapEntityToVO(TsddrRUtenteGestoreProfilo entity);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<UtenteGestoreProfiloVO> mapListEntityToListVO(List<TsddrRUtenteGestoreProfilo> listEntity);

}
