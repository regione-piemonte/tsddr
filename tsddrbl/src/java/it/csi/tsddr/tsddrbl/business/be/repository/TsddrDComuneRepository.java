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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;

/**
 * The Interface TsddrDComuneRepository.
 */
@Repository
public interface TsddrDComuneRepository extends BaseRepository<TsddrDComune, Long> {
	
	/**
	 * Find comuni by id provincia.
	 *
	 * @param idProvincia the id provincia
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdc "
			+ "FROM TsddrDComune tdc "
			+ "WHERE tdc.provincia.idProvincia = :idProvincia "
			+ "AND "
			+ RepositoryUtil.TDC_COMUNE_VALIDITY_CHECK
			+ "ORDER BY tdc.comune ASC")
	List<TsddrDComune> findComuniByIdProvincia(@Param("idProvincia") Long idProvincia, @Param("currentDate") Date currentDate);

	/**
	 * Find comuni.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdc "
			+ "FROM TsddrDComune tdc "
			+ "WHERE "
			+ RepositoryUtil.TDC_COMUNE_VALIDITY_CHECK
			+ "ORDER BY tdc.comune ASC")
	List<TsddrDComune> findComuni(@Param("currentDate") Date currentDate);
	
	/**
	 * Find by id comune.
	 *
	 * @param idComune the id comune
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdc "
			+ "FROM TsddrDComune tdc "
			+ "WHERE tdc.idComune = :idComune AND "
			+ RepositoryUtil.TDC_COMUNE_VALIDITY_CHECK)
	Optional<TsddrDComune> findByIdComune(@Param("idComune") Long idComune, @Param("currentDate") Date currentDate);

}
