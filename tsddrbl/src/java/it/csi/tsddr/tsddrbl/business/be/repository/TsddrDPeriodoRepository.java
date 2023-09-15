/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDPeriodo;

/**
 * The Interface TsddrDPeriodoRepository.
 */
@Repository
public interface TsddrDPeriodoRepository extends BaseRepository<TsddrDPeriodo, Long> {
	
	/**
	 * Find by id periodo.
	 *
	 * @param idPeriodo the id periodo
	 * @return the optional
	 */
	@Query("SELECT tdper "
			+ "FROM TsddrDPeriodo tdper "
			+ "WHERE tdper.idPeriodo = :idPeriodo AND "
			+ RepositoryUtil.TDPER_PERIODO_DELETE_VALIDITY_CHECK)
	Optional<TsddrDPeriodo> findByIdPeriodo(@Param("idPeriodo") Long idPeriodo);

}
