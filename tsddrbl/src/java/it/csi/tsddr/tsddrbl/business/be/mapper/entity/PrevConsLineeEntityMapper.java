package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRPrevConsLinea;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeVO;

@Mapper(uses = { PrevConsDettEntityMapper.class })
public interface PrevConsLineeEntityMapper {

    @Mapping(target = "idImpiantoLinea", source = "impiantoLinea.idImpiantoLinea")
    @Named("mapEntityToVO")
    public PrevConsLineeVO mapEntityToVO(TsddrRPrevConsLinea entity);

    public PrevConsLineeVO mapVOToEntity(PrevConsLineeVO vo);
    
    @IterableMapping(qualifiedByName = "mapEntityToVO")
    public List<PrevConsLineeVO> mapListEntityToListVO(List<TsddrRPrevConsLinea> listEntity);
    
    public List<TsddrRPrevConsLinea> mapListVOToListEntity(List<PrevConsLineeVO> listVO);
    
    @Named("mapEntityToExtendedVO")
    @Mapping(target = "idImpiantoLinea", source = "impiantoLinea.idImpiantoLinea")
    public PrevConsLineeExtendedVO mapEntityToExtendedVO(TsddrRPrevConsLinea entity);
    
    @IterableMapping(qualifiedByName = "mapEntityToExtendedVO")
    @Named("mapListEntityToListExtendedVO")
    public List<PrevConsLineeExtendedVO> mapListEntityToListExtendedVO(List<TsddrRPrevConsLinea> listEntity);

}
