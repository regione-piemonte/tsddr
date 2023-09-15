/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSottoLinea;

/**
 * The Interface TsddrTSottoLineaRepository.
 */
@Repository
public interface TsddrTSottoLineaRepository  extends JpaRepository<TsddrTLinea, Long> {
	
	/**
	 * Find by id sotto linea.
	 *
	 * @param idSottoLinea the id sotto linea
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT ttsl "
			+ "FROM TsddrTSottoLinea ttsl "
			+ "WHERE ttsl.idSottoLinea = :idSottoLinea AND "
			+ RepositoryUtil.TTSL_SOTTO_LINEA_VALIDITY_CHECK)
	Optional<TsddrTSottoLinea> findByIdSottoLinea(@Param("idSottoLinea") Long idSottoLinea, @Param("currentDate") Date currentDate);
	

	@Query("SELECT ttsl "
			+ "FROM TsddrTSottoLinea ttsl "
			+ "WHERE ttsl.idSottoLinea = :idSottoLinea")
	Optional<TsddrTSottoLinea> findByIdSottoLinea(@Param("idSottoLinea") Long idSottoLinea);
	
	
	/**
	 * Find sotto linee nuova struttura impianto.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttsl "
			+ "FROM TsddrTSottoLinea ttsl "
			+ "WHERE "
			+ RepositoryUtil.TTSL_SOTTO_LINEA_VALIDITY_CHECK)
	List<TsddrTSottoLinea> findSottoLineeNuovaStrutturaImpianto(@Param("currentDate") Date currentDate);
	
	/**
	 * Find sotto linee nuova struttura impianto.
	 *
	 * @param idSottoLinee the id sotto linee
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttsl "
			+ "FROM TsddrTSottoLinea ttsl "
			+ "WHERE ttsl.idSottoLinea NOT IN (:idSottoLinee) AND "
			+ RepositoryUtil.TTSL_SOTTO_LINEA_VALIDITY_CHECK)
	List<TsddrTSottoLinea> findSottoLineeNuovaStrutturaImpianto(@Param("idSottoLinee") List<Long> idSottoLinee, @Param("currentDate") Date currentDate);


	@Query("SELECT ttsl "
			+ "FROM TsddrTSottoLinea ttsl "
			+ "WHERE ttsl.codSottoLinea = :codSottoLinea")
	Optional<TsddrTSottoLinea> findByCodSottoLinea(@Param("codSottoLinea") String codSottoLinea);

}
