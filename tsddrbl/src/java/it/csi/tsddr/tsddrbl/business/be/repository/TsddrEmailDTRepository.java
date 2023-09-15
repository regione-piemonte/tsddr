/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrEmailDT;

/**
 * The Interface TsddrEmailDTRepository.
 */
@Repository
public interface TsddrEmailDTRepository extends JpaRepository<TsddrEmailDT, Long> {
	
	/**
	 * Find by id casella.
	 *
	 * @param idCasella the id casella
	 * @return the optional
	 */
	@Query("SELECT tedt "
			+ "FROM TsddrEmailDT tedt "
			+ "WHERE tedt.idCasella = :idCasella AND "
			+ RepositoryUtil.TEDT_CASELLA_DELETE_VALIDITY_CHECK)
	Optional<TsddrEmailDT> findByIdCasella(@Param("idCasella") Long idCasella);
	
}
