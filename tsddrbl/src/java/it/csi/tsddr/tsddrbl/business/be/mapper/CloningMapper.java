/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.control.DeepClone;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTConferimento;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSoggettoMr;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTVersamento;

@Mapper(mappingControl = DeepClone.class)
public interface CloningMapper extends BaseMapper {
	
	/**
	 * Clone.
	 *
	 * @param dichAnnuale the dich annuale
	 * @return the tsddr T dich annuale
	 */
	@Mapping(target = "idDichAnnuale", ignore = true)
	@Mapping(target = "conferimenti", ignore = true)
	@Mapping(target = "soggettiMr", ignore = true)
	@Mapping(target = "versamenti", ignore = true)
	@Mapping(target = "impianto.dichAnnuali", ignore = true)
	@Mapping(target = "indirizzo.dichAnnuali", ignore = true)
	@Mapping(target = "statoDichiarazione.dichAnnuali", ignore = true)
	@Mapping(target = "idUserInsert", ignore = true)
	@Mapping(target = "idUserUpdate", ignore = true)
	@Mapping(target = "idUserDelete", ignore = true)
	@Mapping(target = "dataInsert", ignore = true)
	@Mapping(target = "dataUpdate", ignore = true)
	@Mapping(target = "dataDelete", ignore = true)
	@Mapping(target = "versione", source = "versione", qualifiedByName = "incrementLong")
	public TsddrTDichAnnuale clone(TsddrTDichAnnuale dichAnnuale);
	
	/**
	 * Clone.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T conferimento
	 */
	@Mapping(target = "idConferimento", ignore = true)
	@Mapping(target = "dichAnnuale", ignore = true)
	@Mapping(target = "rifiutoTariffa.conferimenti", ignore = true)
	@Mapping(target = "periodo.conferimenti", ignore = true)
	@Mapping(target = "periodo.versamenti", ignore = true)
	@Mapping(target = "idUserInsert", ignore = true)
	@Mapping(target = "idUserUpdate", ignore = true)
	@Mapping(target = "idUserDelete", ignore = true)
	@Mapping(target = "dataInsert", ignore = true)
	@Mapping(target = "dataUpdate", ignore = true)
	@Mapping(target = "dataDelete", ignore = true)
	public TsddrTConferimento clone(TsddrTConferimento conferimento);
	
	/**
	 * Clone conferimenti.
	 *
	 * @param conferimenti the conferimenti
	 * @return the list
	 */
	public List<TsddrTConferimento> cloneConferimenti(List<TsddrTConferimento> conferimenti);
	
	/**
	 * Clone.
	 *
	 * @param soggettoMr the soggetto mr
	 * @return the tsddr T soggetto mr
	 */
	@Mapping(target = "idSoggettiMr", ignore = true)
	@Mapping(target = "dichAnnuale", ignore = true)
	@Mapping(target = "idUserInsert", ignore = true)
	@Mapping(target = "idUserUpdate", ignore = true)
	@Mapping(target = "idUserDelete", ignore = true)
	@Mapping(target = "dataInsert", ignore = true)
	@Mapping(target = "dataUpdate", ignore = true)
	@Mapping(target = "dataDelete", ignore = true)
	public TsddrTSoggettoMr clone(TsddrTSoggettoMr soggettoMr);
	
	/**
	 * Clone soggetti mr.
	 *
	 * @param soggettiMr the soggetti mr
	 * @return the list
	 */
	public List<TsddrTSoggettoMr> cloneSoggettiMr(List<TsddrTSoggettoMr> soggettiMr);
	
	/**
	 * Clone.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T versamento
	 */
	@Mapping(target = "idVersamento", ignore = true)
	@Mapping(target = "dichAnnuale", ignore = true)
	@Mapping(target = "periodo.conferimenti", ignore = true)
	@Mapping(target = "periodo.versamenti", ignore = true)
	@Mapping(target = "idUserInsert", ignore = true)
	@Mapping(target = "idUserUpdate", ignore = true)
	@Mapping(target = "idUserDelete", ignore = true)
	@Mapping(target = "dataInsert", ignore = true)
	@Mapping(target = "dataUpdate", ignore = true)
	@Mapping(target = "dataDelete", ignore = true)
	public TsddrTVersamento clone(TsddrTVersamento conferimento);
	
	/**
	 * Clone versamenti.
	 *
	 * @param versamenti the versamenti
	 * @return the list
	 */
	public List<TsddrTVersamento> cloneVersamenti(List<TsddrTVersamento> versamenti);
	
	/**
	 * Clone.
	 *
	 * @param gestore the gestore
	 * @return the tsddr T gestore
	 */
	public TsddrTGestore clone(TsddrTGestore gestore);

}
