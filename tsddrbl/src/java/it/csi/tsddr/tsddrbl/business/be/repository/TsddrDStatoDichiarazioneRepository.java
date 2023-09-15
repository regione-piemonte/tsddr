/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoDichiarazione;

/**
 * The Interface TsddrDStatoDichiarazioneRepository.
 */
@Repository
public interface TsddrDStatoDichiarazioneRepository extends JpaRepository<TsddrDStatoDichiarazione, Long> {
	
	/**
	 * Find stato dichiarazione by id.
	 *
	 * @param idStatoDichiarazione the id stato dichiarazione
	 * @return the optional
	 */
	@Query("SELECT tdsdich "
			+ "FROM TsddrDStatoDichiarazione tdsdich "
			+ "WHERE tdsdich.idStatoDichiarazione = :idStatoDichiarazione AND "
			+ RepositoryUtil.TDSDICH_STATO_DICHIARAZIONE_DELETE_VALIDITY_CHECK)
	public Optional<TsddrDStatoDichiarazione> findStatoDichiarazioneById(@Param("idStatoDichiarazione") Long idStatoDichiarazione);

}
