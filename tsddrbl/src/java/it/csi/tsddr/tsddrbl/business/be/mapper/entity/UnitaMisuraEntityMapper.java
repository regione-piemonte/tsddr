package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import org.mapstruct.Mapper;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDUnitaMisura;
import it.csi.tsddr.tsddrbl.vo.unitamisura.UnitaMisuraVO;

/**
 * The Interface UnitaMisuraEntityMapper.
 */
@Mapper
public interface UnitaMisuraEntityMapper {
    
    /**
     * Map entity to VO.
     *
     * @param entity the entity
     * @return the unita misura VO
     */
    public UnitaMisuraVO mapEntityToVO(TsddrDUnitaMisura entity);
    
    /**
     * Map VO to entity.
     *
     * @param vo the vo
     * @return the tsddr D unita misura
     */
    public TsddrDUnitaMisura mapVOToEntity(UnitaMisuraVO vo);
    
}