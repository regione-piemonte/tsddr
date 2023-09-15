/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDEer;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.EerVO;

/**
 * The Interface EerEntityMapper.
 */
@Mapper
public interface EerEntityMapper {
    
    /**
     * Map entity to select VO.
     *
     * @param entity the entity
     * @return the select VO
     */
    @Mapping(target = "id", source = "idEer")
    @Mapping(target = "descrizione", source = "codiceEer")
    @Mapping(target = "descrizioneAggiuntiva", source = "descrizione")
    public SelectVO mapEntityToSelectVO(TsddrDEer entity);
    
    /**
     * Map entity to VO.
     *
     * @param entity the entity
     * @return the eer VO
     */
    public EerVO mapEntityToVO(TsddrDEer entity);
    
    /**
     * Map VO to entity.
     *
     * @param vo the vo
     * @return the tsddr D eer
     */
    public TsddrDEer mapVOToEntity(EerVO vo);
    
    /**
     * Map list entity to list select VO.
     *
     * @param listEntity the list entity
     * @return the list
     */
    public List<SelectVO> mapListEntityToListSelectVO(List<TsddrDEer> listEntity);

}
