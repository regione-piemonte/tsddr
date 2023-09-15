/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;

/**
 * The Interface TsddrTIndirizzoRepository.
 */
@Repository
public interface TsddrTIndirizzoRepository extends BaseRepository<TsddrTIndirizzo, Long> {
	
	/**
	 * Find by original id and max versione.
	 *
	 * @param originalId the original id
	 * @return the optional
	 */
	@Query("SELECT tti "
			+ "FROM TsddrTIndirizzo tti "
			+ "WHERE tti.originalId = :originalId "
			+ "AND versione = (SELECT MAX(tti1.versione) "
			+ "FROM TsddrTIndirizzo tti1 "
			+ "WHERE tti1.originalId = :originalId "
			+ "GROUP BY tti1.originalId)")
	Optional<TsddrTIndirizzo> findByOriginalIdAndMaxVersione(@Param("originalId") Long originalId);
	
	/**
	 * Find by id indirizzo.
	 *
	 * @param idIndirizzo the id indirizzo
	 * @return the optional
	 */
	Optional<TsddrTIndirizzo> findByIdIndirizzo(Long idIndirizzo);
	
	/**
	 * Find by original id.
	 *
	 * @param originalId the original id
	 * @return the list
	 */
	List<TsddrTIndirizzo> findByOriginalId(Long originalId);

}
