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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoImpianto;

/**
 * The Interface TsddrDTipoImpiantoRepository.
 */
@Repository
public interface TsddrDTipoImpiantoRepository extends JpaRepository<TsddrDTipoImpianto, Long> {

	/**
	 * Find tipo impianto.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdti "
			+ "FROM TsddrDTipoImpianto tdti "
			+ "WHERE "
			+ RepositoryUtil.TDTI_TIPO_IMPIANTO_VALIDITY_CHECK
			+ "ORDER BY tdti.descTipoImpianto ASC")
	public List<TsddrDTipoImpianto> findTipoImpianto(@Param("currentDate") Date currentDate);
	
	/**
	 * Find tipo impianto by id.
	 *
	 * @param idTipoImpianto the id tipo impianto
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdti "
			+ "FROM TsddrDTipoImpianto tdti "
			+ "WHERE tdti.idTipoImpianto = :idTipoImpianto AND "
			+ RepositoryUtil.TDTI_TIPO_IMPIANTO_VALIDITY_CHECK)
	public Optional<TsddrDTipoImpianto> findTipoImpiantoById(@Param("idTipoImpianto") Long idTipoImpianto, @Param("currentDate") Date currentDate);
	
}
