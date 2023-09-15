/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.LineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.SottoLineaVO;

@Mapper
public interface GenericLineaMapper {
	
	/**
	 * Map generic linea VO to linea VO.
	 *
	 * @param entity the entity
	 * @return the linea VO
	 */
	@Mapping(target = "idLinea", source = "idLinea")
	@Mapping(target = "descLinea", source = "descLinea")
	@Mapping(target = "codLinea", source = "codLinea")
	@Mapping(target = "dataInizioValidita", source = "dataInizioValidita")
	@Mapping(target = "dataFineValidita", source = "dataFineValidita")
	public LineaVO mapGenericLineaVOToLineaVO(GenericLineaVO entity);
	
	/**
	 * Map generic linea VO to sotto linea VO.
	 *
	 * @param entity the entity
	 * @return the sotto linea VO
	 */
	@Mapping(target = "idSottoLinea", source = "idSottoLinea")
	@Mapping(target = "descSottoLinea", source = "descSottoLinea")
	@Mapping(target = "codSottoLinea", source = "codSottoLinea")
	@Mapping(target = "dataInizioValidita", source = "dataInizioValidita")
	@Mapping(target = "dataFineValidita", source = "dataFineValidita")
	public SottoLineaVO mapGenericLineaVOToSottoLineaVO(GenericLineaVO entity);

}
