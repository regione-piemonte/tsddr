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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoDomanda;

/**
 * The Interface TsddrDStatoDomandaRepository.
 */
@Repository
public interface TsddrDStatoDomandaRepository extends JpaRepository<TsddrDStatoDomanda, Long> {
	
	/**
	 * Find by id.
	 *
	 * @param idStatoDomanda the id stato domanda
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdsd "
			+ "FROM TsddrDStatoDomanda tdsd "
			+ "WHERE tdsd.idStatoDomanda = :idStatoDomanda AND "
			+ RepositoryUtil.TDSD_STATO_DOMANDA_VALIDITY_CHECK)
	Optional<TsddrDStatoDomanda> findById(@Param("idStatoDomanda") Long idStatoDomanda, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id.
	 *
	 * @param idStatiDomanda the id stati domanda
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdsd "
			+ "FROM TsddrDStatoDomanda tdsd "
			+ "WHERE tdsd.idStatoDomanda IN ( :idStatiDomanda ) AND "
			+ RepositoryUtil.TDSD_STATO_DOMANDA_VALIDITY_CHECK
			+ "ORDER BY tdsd.descStatoDomanda ASC")
	List<TsddrDStatoDomanda> findById(@Param("idStatiDomanda") List<Long> idStatiDomanda, @Param("currentDate") Date currentDate);
	
	/**
	 * Find all stato domanda.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdsd "
			+ "FROM TsddrDStatoDomanda tdsd "
			+ "WHERE "
			+ RepositoryUtil.TDSD_STATO_DOMANDA_VALIDITY_CHECK
			+ "ORDER BY tdsd.descStatoDomanda ASC")
	List<TsddrDStatoDomanda> findAllStatoDomanda(@Param("currentDate") Date currentDate);

	/**
	 * Find stato domanda not.
	 *
	 * @param idStatoDomanda the id stato domanda
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdsd "
			+ "FROM TsddrDStatoDomanda tdsd "
			+ "WHERE tdsd.idStatoDomanda <> :idStatoDomanda AND "
			+ RepositoryUtil.TDSD_STATO_DOMANDA_VALIDITY_CHECK)
	List<TsddrDStatoDomanda> findStatoDomandaNot(@Param("idStatoDomanda") Long idStatoDomanda, @Param("currentDate") Date currentDate);

}
