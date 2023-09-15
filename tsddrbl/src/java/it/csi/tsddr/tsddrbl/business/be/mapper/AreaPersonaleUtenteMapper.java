/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.vo.areapersonale.AreaPersonaleUtenteVO;

@Mapper
public interface AreaPersonaleUtenteMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the area personale utente VO
	 */
	@Mapping(target = "codiceFiscale", source = "codFiscale")
	@Mapping(target = "cognome", source = "cognome")
	@Mapping(target = "nome", source = "nome")
	@Mapping(target = "email", source = "email")
	@Mapping(target = "telefono", source = "telefono")
	public AreaPersonaleUtenteVO mapEntityToVO(TsddrTDatiSogg entity);

}
