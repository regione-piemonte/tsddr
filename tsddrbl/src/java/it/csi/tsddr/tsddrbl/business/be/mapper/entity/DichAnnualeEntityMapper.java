/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTConferimento;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTVersamento;
import it.csi.tsddr.tsddrbl.business.be.mapper.BaseMapper;
import it.csi.tsddr.tsddrbl.util.DichAnnualeUtil;
import it.csi.tsddr.tsddrbl.util.enums.StatoDichiarazione;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.ConferimentoVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeBasicVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutiConferitiVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.VersamentoVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;

/**
 * The Interface DichAnnualeEntityMapper.
 */
@Mapper(uses = { ImpiantoEntityMapper.class, IndirizzoEntityMapper.class, SoggettoMrEntityMapper.class})
public interface DichAnnualeEntityMapper extends BaseMapper {

	/**
	 * Map entity to basic VO.
	 *
	 * @param entity the entity
	 * @param acl the acl
	 * @param ignoreDescrStatoDichiarazione the ignore descr stato dichiarazione
	 * @return the dich annuale basic VO
	 */
	public DichAnnualeBasicVO mapEntityToBasicVO(TsddrTDichAnnuale entity, FunzionalitaProfiloVO acl, boolean ignoreDescrStatoDichiarazione);

	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @param creditoImpostaAP the credito imposta AP
	 * @return the dich annuale VO
	 */
	@Mapping(target = "rifiutiConferiti", source = "entity.conferimenti", qualifiedByName = "rifiutiConferiti")
	@Mapping(target = "soggettiMr", source = "entity.soggettiMr", qualifiedByName = "activeSog")
	@Mapping(target = "versamenti", ignore = true)
	public DichAnnualeVO mapEntityToVO(TsddrTDichAnnuale entity, Double creditoImpostaAP);
	
	/**
	 * Clone entity to VO.
	 *
	 * @param entity the entity
	 * @return the dich annuale VO
	 */
	@Mapping(target = "rifiutiConferiti", source = "entity.conferimenti", qualifiedByName = "rifiutiConferiti")
	@Mapping(target = "versamenti", ignore = true)
	@Mapping(target = "versione", source = "versione", qualifiedByName = "incrementLong")
	public DichAnnualeVO cloneEntityToVO(TsddrTDichAnnuale entity);

	/**
	 * Map list entity to list basic VO.
	 *
	 * @param listEntity the list entity
	 * @param acl the acl
	 * @param ignoreDescrStatoDichiarazione the ignore descr stato dichiarazione
	 * @return the list
	 */
	default List<DichAnnualeBasicVO> mapListEntityToListBasicVO(List<TsddrTDichAnnuale> listEntity,
			FunzionalitaProfiloVO acl, boolean ignoreDescrStatoDichiarazione) {
		if (listEntity == null) {
			return null;
		}

		return listEntity.stream().map(i -> mapEntityToBasicVO(i, acl, ignoreDescrStatoDichiarazione))
				.collect(Collectors.toList());
	}

	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the conferimento VO
	 */
	public ConferimentoVO mapEntityToVO(TsddrTConferimento entity);
	
	/**
	 * Map conferimenti list entity to list VO.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	public List<ConferimentoVO> mapConferimentiListEntityToListVO(List<TsddrTConferimento> entityList);
	
	/**
	 * Map entity to VO.
	 *
	 * @param entity the entity
	 * @return the versamento VO
	 */
	public VersamentoVO mapEntityToVO(TsddrTVersamento entity);
	
	/**
	 * Map versamenti list entity to list VO.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	public List<VersamentoVO> mapVersamentiListEntityToListVO(List<TsddrTVersamento> entityList);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr T conferimento
	 */
	public TsddrTConferimento mapVOToEntity(ConferimentoVO vo);
	
	/**
	 * Map conferimenti list vo to list entity.
	 *
	 * @param voList the vo list
	 * @return the list
	 */
	public List<TsddrTConferimento> mapConferimentiListVoToListEntity(List<ConferimentoVO> voList);
	
	/**
	 * Map VO to entity.
	 *
	 * @param vo the vo
	 * @return the tsddr T versamento
	 */
	public TsddrTVersamento mapVOToEntity(VersamentoVO vo);
	
	/**
	 * Map versamenti list vo to list entity.
	 *
	 * @param voList the vo list
	 * @return the list
	 */
	public List<TsddrTVersamento> mapVersamentiListVoToListEntity(List<VersamentoVO> voList);

	/**
	 * Creates the rifiuti conferiti.
	 *
	 * @param conferimenti the conferimenti
	 * @return the rifiuti conferiti VO
	 */
	@Named("rifiutiConferiti")
	default RifiutiConferitiVO createRifiutiConferiti(List<TsddrTConferimento> conferimenti) {
		return DichAnnualeUtil.createRifiutiConferitiVO(mapConferimentiListEntityToListVO(conferimenti));
	}

	/**
	 * Creates the and set versamenti.
	 *
	 * @param entity the entity
	 * @param creditoImpostaAP the credito imposta AP
	 * @param vo the vo
	 */
	@AfterMapping
	default void createAndSetVersamenti(TsddrTDichAnnuale entity, Double creditoImpostaAP, @MappingTarget DichAnnualeVO vo) {
		vo.setVersamenti(DichAnnualeUtil.createVersamentiVO(mapVersamentiListEntityToListVO(entity.getVersamenti()),
				vo.getRifiutiConferiti().getTotali().getTotale().getImporto(),
				creditoImpostaAP != null ? creditoImpostaAP : 0));
	}
	
	/**
	 * Sets the printable and annullable.
	 *
	 * @param acl the acl
	 * @param vo the vo
	 */
	@AfterMapping
	default void setPrintableAndAnnullable(FunzionalitaProfiloVO acl, @MappingTarget DichAnnualeBasicVO vo) {
		vo.setPrintable(vo.getStatoDichiarazione().getIdStatoDichiarazione() == StatoDichiarazione.INVIATA_PROTOCOLLATA.getId());
		vo.setAnnullable((acl.getDelete() == Boolean.TRUE) && vo.getStatoDichiarazione().getIdStatoDichiarazione() == StatoDichiarazione.BOZZA.getId());
	}

	/**
	 * Ignore descr stato dichiarazione.
	 *
	 * @param ignoreDescrStatoDichiarazione the ignore descr stato dichiarazione
	 * @param vo the vo
	 */
	@AfterMapping
	default void ignoreDescrStatoDichiarazione(boolean ignoreDescrStatoDichiarazione, @MappingTarget DichAnnualeBasicVO vo) {
		if (ignoreDescrStatoDichiarazione) {
			vo.getStatoDichiarazione().setDescrStatoDichiarazione(null);
		}
	}
	
}
