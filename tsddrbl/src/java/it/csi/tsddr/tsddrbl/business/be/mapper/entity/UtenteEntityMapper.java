/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.vo.gestioneutente.UtenteVO;

/**
 * The Interface UtenteEntityMapper.
 */
@Mapper(uses = GestoreEntityMapper.class)
public interface UtenteEntityMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the utente VO
	 */
	@Mapping(target = "nome", source = "datiSogg.nome")
	@Mapping(target = "cognome", source = "datiSogg.cognome")
	@Mapping(target = "codiceFiscale", source = "datiSogg.codFiscale")
	@Mapping(target = "gestori", source = "RUtentiGestoriProfili")
	public UtenteVO mapEntityToVO(TsddrTUtente entity);

}
