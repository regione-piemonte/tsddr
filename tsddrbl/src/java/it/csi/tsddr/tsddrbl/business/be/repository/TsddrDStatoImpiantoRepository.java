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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoImpianto;

/**
 * The Interface TsddrDStatoImpiantoRepository.
 */
@Repository
public interface TsddrDStatoImpiantoRepository extends JpaRepository<TsddrDStatoImpianto, Long> {
	
	/**
	 * Find stati impianto.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdsi "
			+ "FROM TsddrDStatoImpianto tdsi "
			+ "WHERE "
			+ RepositoryUtil.TDSI_STATO_IMPIANTO_VALIDITY_CHECK
			+ "ORDER BY tdsi.descStatoImpianto ASC")
	public List<TsddrDStatoImpianto> findStatiImpianto(@Param("currentDate") Date currentDate);
	
	/**
	 * Find stato impianto by id.
	 *
	 * @param idStatoImpianto the id stato impianto
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdsi "
			+ "FROM TsddrDStatoImpianto tdsi "
			+ "WHERE tdsi.idStatoImpianto = :idStatoImpianto AND "
			+ RepositoryUtil.TDSI_STATO_IMPIANTO_VALIDITY_CHECK)
	public Optional<TsddrDStatoImpianto> findStatoImpiantoById(@Param("idStatoImpianto") Long idStatoImpianto, @Param("currentDate") Date currentDate);

}
