/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTMenu;
import it.csi.tsddr.tsddrbl.vo.menu.MenuCardVO;

/**
 * The Interface TsddrTMenuRepository.
 */
@Repository
public interface TsddrTMenuRepository extends JpaRepository<TsddrTMenu, Long> {

	/**
	 * Struttura menue card by id profilo.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT new it.csi.tsddr.tsddrbl.vo.menu.MenuCardVO( ttm.idMenu, ttm.descVoceMenu, ttm.livello, ttm.idPadre, tdf.idFunzione, tdf.descFunzione, ttm.descrizioneCard ) "
			+ "FROM TsddrRFunzProf trfp "
			+ "INNER JOIN trfp.profilo tdp "
			+ "INNER JOIN trfp.funzione tdf "
			+ "INNER JOIN tdf.menus ttm "
			+ "WHERE tdp.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRFP_RFUNZPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDF_FUNZIONE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTM_MENU_VALIDITY_CHECK
			+ "ORDER BY ttm.idMenu ASC")
	List<MenuCardVO> strutturaMenueCardByIdProfilo(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);

}
