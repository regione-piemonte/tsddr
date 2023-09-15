/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametroAcaris;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisVO;

/**
 * The Interface ParametroAcarisEntityMapper.
 */
@Mapper
public interface ParametroAcarisEntityMapper {
    
    /**
     * Map entity to VO.
     *
     * @param entity the entity
     * @return the parametro acaris VO
     */
    public ParametroAcarisVO mapEntityToVO(TsddrCParametroAcaris entity);
    
    /**
     * Map list entity to list VO.
     *
     * @param listEntity the list entity
     * @return the list
     */
    public List<ParametroAcarisVO> mapListEntityToListVO(List<TsddrCParametroAcaris> listEntity);

}
