/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;
import it.csi.tsddr.tsddrbl.vo.impiantolinee.ImpiantoLineeVO;

@Mapper
public interface ImpiantoLineeEntityMapper {

    @Mapping(target = "idLinea", source = "linea.idLinea")
    @Mapping(target = "idSottoLinea", source = "sottoLinea.idSottoLinea")
    @Mapping(target = "codLinea", source = "linea.codLinea")
    @Mapping(target = "codSottoLinea", source = "sottoLinea.codSottoLinea")
    @Mapping(target = "idImpianto", source = "impianto.idImpianto")
    public ImpiantoLineeVO mapEntityToVO(TsddrRImpiantoLinea entity);
    
    public List<ImpiantoLineeVO> mapListEntityToListVO(List<TsddrRImpiantoLinea> listEntity);
    
}
