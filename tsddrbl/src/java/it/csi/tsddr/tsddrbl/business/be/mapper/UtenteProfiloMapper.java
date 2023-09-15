/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.utenteprofilo.UtenteProfiloVO;

@Mapper
public interface UtenteProfiloMapper {
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the utente profilo VO
	 */
	@Mapping(target = "idUtente", source = "idUtente")
	@Mapping(target = "nome", source = "datiSogg.nome")
	@Mapping(target = "cognome", source = "datiSogg.cognome")
	@Mapping(target = "codiceFiscale", source = "datiSogg.codFiscale")
	public UtenteProfiloVO mapEntityToVO(TsddrTUtente entity);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the utente profilo VO
	 */
	@Mapping(target = "idUtente", source = "utente.idUtente")
	@Mapping(target = "nome", source = "utente.datiSogg.nome")
	@Mapping(target = "cognome", source = "utente.datiSogg.cognome")
	@Mapping(target = "codiceFiscale", source = "utente.datiSogg.codFiscale")
	public UtenteProfiloVO mapEntityToVO(TsddrRUtenteProf entity);
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idUtente")
	@Mapping(target = "descrizione", source = "entity", qualifiedByName = "UserDataToString")
	public SelectVO mapEntityToSelectVO(TsddrTUtente entity);
	
	/**
	 * Map VO to select VO.
	 *
	 * @param vo the vo
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idUtente")
	@Mapping(target = "descrizione", source = "vo", qualifiedByName = "UserDataToString")
	public SelectVO mapVOToSelectVO(UtenteProfiloVO vo);
	
	/**
	 * Map list entity to list VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<UtenteProfiloVO> mapListEntityToListVO(List<TsddrTUtente> listEntity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrTUtente> listEntity);
	
	/**
	 * Map list VO to list select VO.
	 *
	 * @param listVO the list VO
	 * @return the list
	 */
	public List<SelectVO> mapListVOToListSelectVO(List<UtenteProfiloVO> listVO);
	
	/**
	 * User data to string.
	 *
	 * @param vo the vo
	 * @return the string
	 */
	@Named("UserDataToString")
    default String userDataToString(UtenteProfiloVO vo) {
        return String.format("%s %s (%s)", vo.getCognome(), vo.getNome(), vo.getCodiceFiscale());
    }
	
	/**
	 * User data to string.
	 *
	 * @param entity the entity
	 * @return the string
	 */
	@Named("UserDataToString")
    default String userDataToString(TsddrTUtente entity) {
        return String.format("%s %s (%s)", entity.getDatiSogg().getCognome(), entity.getDatiSogg().getNome(), entity.getDatiSogg().getCodFiscale());
    }
	
}