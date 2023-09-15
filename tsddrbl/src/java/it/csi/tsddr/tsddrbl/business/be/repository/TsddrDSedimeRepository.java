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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSedime;

/**
 * The Interface TsddrDSedimeRepository.
 */
@Repository
public interface TsddrDSedimeRepository extends BaseRepository<TsddrDSedime, Long> {
	
	/**
	 * Find sedimi.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tds "
			+ "FROM TsddrDSedime tds "
			+ "WHERE "
			+ RepositoryUtil.TDS_SEDIME_VALIDITY_CHECK
			+ "ORDER BY tds.descSedime ASC")
	List<TsddrDSedime> findSedimi(@Param("currentDate") Date currentDate);
	
	/**
	 * Find by id sedime.
	 *
	 * @param idSedime the id sedime
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tds "
			+ "FROM TsddrDSedime tds "
			+ "WHERE tds.idSedime = :idSedime AND "
			+ RepositoryUtil.TDS_SEDIME_VALIDITY_CHECK)
	Optional<TsddrDSedime> findByIdSedime(@Param("idSedime")Long idSedime, @Param("currentDate") Date currentDate);

}
