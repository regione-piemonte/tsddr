/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;
import it.csi.tsddr.tsddrbl.business.be.mapper.BaseMapper;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsVO;

@Mapper(uses = { PrevConsLineeEntityMapper.class })
public interface PrevConsEntityMapper extends BaseMapper {
    
    public PrevConsVO mapEntityToVO(TsddrTPrevCons entity);
    
    public TsddrTPrevCons mapVOToEntity(PrevConsVO vo);
    
    @Mapping(target = "prevConsLineeExtended", source = "entity.prevConsLinee", qualifiedByName = "mapListEntityToListExtendedVO")
    public PrevConsExtendedVO mapEntityToExtendedVO(TsddrTPrevCons entity);
    
}
