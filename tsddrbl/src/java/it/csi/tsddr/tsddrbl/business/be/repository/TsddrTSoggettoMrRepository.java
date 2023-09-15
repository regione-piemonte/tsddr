/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSoggettoMr;

/**
 * The Interface TsddrTSoggettoMrRepository.
 */
@Repository
public interface TsddrTSoggettoMrRepository extends JpaRepository<TsddrTSoggettoMr, Long> {

	/**
	 * Find by id soggetto mr and id dich annuale.
	 *
	 * @param idSoggettoMr the id soggetto mr
	 * @param idDichAnnuale the id dich annuale
	 * @return the optional
	 */
	@Query("SELECT ttsm "
			+ "FROM TsddrTSoggettoMr ttsm "
			+ "INNER JOIN ttsm.dichAnnuale ttda "
			+ "WHERE ttsm.idSoggettiMr = :idSoggettoMr AND "
			+ "ttda.idDichAnnuale = :idDichAnnuale AND "
			+ RepositoryUtil.TTSM_SOGGETTO_MR_DELETE_VALIDITY_CHECK)
	Optional<TsddrTSoggettoMr> findByIdSoggettoMrAndIdDichAnnuale(@Param("idDichAnnuale")Long idDichAnnuale, @Param("idSoggettoMr") Long idSoggettoMr);
			
}
