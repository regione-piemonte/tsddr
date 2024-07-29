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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDAnnoPregresso;

/**
 * The Interface TsddrDAnnoPregressoRepository.
 */
@Repository
public interface TsddrDAnnoPregressoRepository extends JpaRepository<TsddrDAnnoPregresso, Long> {
	
	/**
	 * Find anno pregresso by id.
	 *
	 * @param idAnnoPregresso the id tipo pregresso
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdaPregresso "
			+ "FROM TsddrDAnnoPregresso tdaPregresso "
			+ "WHERE tdaPregresso.idAnnoPregresso = :idAnnoPregresso AND "
			+ RepositoryUtil.TDAPREGRESSO_VALIDITY_CHECK)
	public Optional<TsddrDAnnoPregresso> findAnnoPregressoById(@Param("idAnnoPregresso") Long idAnnoPregresso, @Param("currentDate") Date currentDate);
	
	/**
	 * Find anni pregresso.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdaPregresso "
			+ "FROM TsddrDAnnoPregresso tdaPregresso "
			+ "WHERE "
			+ RepositoryUtil.TDAPREGRESSO_VALIDITY_CHECK
			+ "ORDER BY tdaPregresso.descAnnoPregresso ASC")
	public List<TsddrDAnnoPregresso> findAnniPregresso(@Param("currentDate") Date currentDate);

}
