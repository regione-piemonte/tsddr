/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;

/**
 * The Interface ImpiantoEntityMapper.
 */
@Mapper(uses = { GestoreEntityMapper.class, IndirizzoEntityMapper.class, StatoImpiantoEntityMapper.class,
		TipoImpiantoEntityMapper.class })
public interface ImpiantoEntityMapper {
	
    /**
     * Map entity to VO.
     *
     * @param entity the entity
     * @return the impianto VO
     */
    @Named("mapEntity")
    public ImpiantoVO mapEntityToVO(TsddrTImpianto entity);
    
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the impianto VO
	 */
    @Named("mapEntityIgnoreFieldsGestore")
	@Mapping(target = "gestore.sedeLegale", ignore = true)
	@Mapping(target = "gestore.codFiscPartiva", ignore = true)
	@Mapping(target = "gestore.email", ignore = true)
	@Mapping(target = "gestore.naturaGiuridica", ignore = true)
	@Mapping(target = "indirizzo.versione", ignore = true)
	@Mapping(target = "indirizzo.comune.codCatasto", ignore = true)
	@Mapping(target = "indirizzo.comune.idComuneIstat", ignore = true)
	@Mapping(target = "indirizzo.comune.provincia.idProvinciaIstat", ignore = true)
	@Mapping(target = "indirizzo.comune.provincia.siglaProv", ignore = true)
	public ImpiantoVO mapEntityToVOIgnoreFields(TsddrTImpianto entity);
	
	/**
	 * Map entity to select VO.
	 *
	 * @param entity the entity
	 * @return the select VO
	 */
	@Mapping(target = "id", source = "idImpianto")
	@Mapping(target = "descrizione", source = "denominazione")
	@Mapping(target = "descrizioneAggiuntiva", source = "gestore.idGestore")
	public SelectVO mapEntityToSelectVO(TsddrTImpianto entity);
	
	/**
	 * Map list entity to list select VO.
	 *
	 * @param listEntity the list entity
	 * @return the list
	 */
	public List<SelectVO> mapListEntityToListSelectVO(List<TsddrTImpianto> listEntity);
	
    /**
     * @param listEntity
     * @return
     */
    public List<ImpiantoVO> mapListEntityToListVO(List<TsddrTImpianto> listEntity);
    
    /**
     * Map list entity to list VO ignore fields.
     *
     * @param listEntity the list entity
     * @return the list
     */
    @IterableMapping(qualifiedByName = "mapEntityIgnoreFieldsGestore")
	public List<ImpiantoVO> mapListEntityToListVOIgnoreFields(List<TsddrTImpianto> listEntity);

}
