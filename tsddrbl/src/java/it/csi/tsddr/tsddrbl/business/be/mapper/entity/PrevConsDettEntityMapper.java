/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevConsDett;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsDettVO;

@Mapper(uses = { EerEntityMapper.class, SezioneEntityMapper.class, UnitaMisuraEntityMapper.class })
public interface PrevConsDettEntityMapper {

    public PrevConsDettVO mapEntityToVO(TsddrTPrevConsDett entity);

    public TsddrTPrevConsDett mapVOToEntity(PrevConsDettVO vo);

    public List<PrevConsDettVO> mapListEntityToListVO(List<TsddrTPrevConsDett> listEntity);

    public List<TsddrTPrevConsDett> mapListVOToListEntity(List<PrevConsDettVO> listVO);

}
