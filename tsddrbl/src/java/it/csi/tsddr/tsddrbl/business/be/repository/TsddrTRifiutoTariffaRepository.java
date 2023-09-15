/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTRifiutoTariffa;

/**
 * The Interface TsddrTRifiutoTariffaRepository.
 */
@Repository
public interface TsddrTRifiutoTariffaRepository extends BaseRepository<TsddrTRifiutoTariffa, Long> {
	
	/**
	 * Find rifiuti tariffe.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttrt "
			+ "FROM TsddrTRifiutoTariffa ttrt "
			+ "WHERE "
			+ RepositoryUtil.TTRT_RIFIUTO_TARIFFA_VALIDITY_CHECK
			+ "ORDER BY ttrt.idRifiutoTariffa")
	List<TsddrTRifiutoTariffa> findRifiutiTariffe(@Param("currentDate") Date currentDate);
	
	/**
	 * Find rifiuti tariffe by id dich annuale.
	 *
	 * @param idDichAnnuale the id dich annuale
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttrt "
			+ "FROM TsddrTRifiutoTariffa ttrt "
			+ "WHERE ttrt.idRifiutoTariffa NOT IN ( "
			+ 	"SELECT ttrt.idRifiutoTariffa "
			+ 	"FROM TsddrTRifiutoTariffa ttrt "
			+ 	"INNER JOIN ttrt.conferimenti ttc "
			+ 	"INNER JOIN ttc.dichAnnuale ttda "
			+ 	"WHERE ttda.idDichAnnuale = :idDichAnnuale AND "
			+ 	RepositoryUtil.TTDA_DICH_ANNUALE_DELETE_VALIDITY_CHECK
			+ 	") AND "
			+ RepositoryUtil.TTRT_RIFIUTO_TARIFFA_VALIDITY_CHECK
			+ "ORDER BY ttrt.descrizione")
	List<TsddrTRifiutoTariffa> findRifiutiTariffeByIdDichAnnuale(@Param("idDichAnnuale") Long idDichAnnuale, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id rifiuto tariffa.
	 *
	 * @param idRifiutoTariffa the id rifiuto tariffa
	 * @return the optional
	 */
	@Query("SELECT ttrt "
			+ "FROM TsddrTRifiutoTariffa ttrt "
			+ "WHERE ttrt.idRifiutoTariffa = :idRifiutoTariffa AND "
			+ RepositoryUtil.TTRT_RIFIUTO_TARIFFA_DELETE_VALIDITY_CHECK)
	Optional<TsddrTRifiutoTariffa> findByIdRifiutoTariffa(@Param("idRifiutoTariffa") Long idRifiutoTariffa);

}
