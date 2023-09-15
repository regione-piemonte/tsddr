/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.vo.utente.DatiObbligatoriUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.DatiUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.InsertUtenteVO;

@Mapper
public interface DatiUtenteMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the dati utente VO
	 */
	@Mapping(target = "idUtente", source = "idUtente")
	@Mapping(target = "nome", source = "datiSogg.nome")
	@Mapping(target = "cognome", source = "datiSogg.cognome")
	@Mapping(target = "codiceFiscale", source = "datiSogg.codFiscale")
	@Mapping(target = "telefono", source = "datiSogg.telefono")
	@Mapping(target = "telefono2", source = "datiSogg.telefono2")
	@Mapping(target = "mail", source = "datiSogg.email")
	@Mapping(target = "pec", source = "datiSogg.pec")
	public DatiUtenteVO mapEntityToVO(TsddrTUtente entity);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr T utente
	 */
	public TsddrTUtente mapVOToEntity(DatiUtenteVO vo);
	
	/**
	 * Map VO to dati obbligatori VO.
	 *
	 * @param vo the vo
	 * @return the dati obbligatori utente VO
	 */
	public DatiObbligatoriUtenteVO mapVOToDatiObbligatoriVO(DatiUtenteVO vo);
	
	/**
	 * Map VO to dati obbligatori VO.
	 *
	 * @param vo the vo
	 * @return the dati obbligatori utente VO
	 */
	public DatiObbligatoriUtenteVO mapVOToDatiObbligatoriVO(InsertUtenteVO vo);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<DatiUtenteVO> mapListEntityToListVO(List<TsddrTUtente> listEntity);
	
}