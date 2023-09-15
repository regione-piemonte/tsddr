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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLinea;

/**
 * The Interface TsddrTLineaRepository.
 */
@Repository
public interface TsddrTLineaRepository extends JpaRepository<TsddrTLinea, Long> {

	/**
	 * Find by id linea.
	 *
	 * @param idLinea the id linea
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT ttl "
			+ "FROM TsddrTLinea ttl "
			+ "WHERE ttl.idLinea = :idLinea AND "
			+ RepositoryUtil.TTL_LINEA_VALIDITY_CHECK)
	Optional<TsddrTLinea> findByIdLinea(@Param("idLinea") Long idLinea, @Param("currentDate") Date currentDate);
	
	/**
	 * Find linee nuova struttura impianto.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttl "
			+ "FROM TsddrTLinea ttl "
			+ "WHERE "
			+ RepositoryUtil.TTL_LINEA_VALIDITY_CHECK)
	List<TsddrTLinea> findLineeNuovaStrutturaImpianto(@Param("currentDate") Date currentDate);
	
	/**
	 * Find linee nuova struttura impianto.
	 *
	 * @param idLinee the id linee
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttl "
			+ "FROM TsddrTLinea ttl "
			+ "WHERE ttl.idLinea NOT IN (:idLinee) AND "
			+ RepositoryUtil.TTL_LINEA_VALIDITY_CHECK)
	List<TsddrTLinea> findLineeNuovaStrutturaImpianto(@Param("idLinee") List<Long> idLinee, @Param("currentDate") Date currentDate);
	
}
