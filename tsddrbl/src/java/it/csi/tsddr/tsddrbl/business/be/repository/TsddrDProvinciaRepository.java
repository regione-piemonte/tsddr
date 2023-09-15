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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;

/**
 * The Interface TsddrDProvinciaRepository.
 */
@Repository
public interface TsddrDProvinciaRepository extends BaseRepository<TsddrDProvincia, Long> {
	
	/**
	 * Find province.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdprv "
			+ "FROM TsddrDProvincia tdprv "
			+ "WHERE "
			+ RepositoryUtil.TDPRV_PROVINCIA_VALIDITY_CHECK
			+ "ORDER BY tdprv.descProvincia ASC")
	public List<TsddrDProvincia> findProvince(@Param("currentDate") Date currentDate);
	
	/**
	 * Find provincia by id.
	 *
	 * @param idProvincia the id provincia
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdprv "
			+ "FROM TsddrDProvincia tdprv "
			+ "WHERE tdprv.idProvincia = :idProvincia AND "
			+ RepositoryUtil.TDPRV_PROVINCIA_VALIDITY_CHECK)
	public Optional<TsddrDProvincia> findProvinciaById(@Param("idProvincia") Long idProvincia, @Param("currentDate") Date currentDate);

}
