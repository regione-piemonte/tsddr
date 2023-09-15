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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTAtto;

/**
 * The Interface TsddrTAttoRepository.
 */
@Repository
public interface TsddrTAttoRepository extends JpaRepository<TsddrTAtto, Long> {
	
	/**
	 * Find atto by id atto.
	 *
	 * @param idAtto the id atto
	 * @return the optional
	 */
	@Query("SELECT tta "
			+ "FROM TsddrTAtto tta "
			+ "WHERE tta.idAtto = :idAtto AND "
			+ RepositoryUtil.TTA_ATTO_DELETE_VALIDITY_CHECK)
	Optional<TsddrTAtto> findAttoByIdAtto(@Param("idAtto") Long idAtto);

	/**
	 * Find atti by id impianto.
	 *
	 * @param idImpianto the id impianto
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tta "
			+ "FROM TsddrTAtto tta "
			+ "INNER JOIN tta.impianto ttimp "
			+ "INNER JOIN tta.tipoProvvedimento tdtprovv "
			+ "WHERE ttimp.idImpianto = :idImpianto AND "
			+ RepositoryUtil.TTA_ATTO_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTIMP_IMPIANTO_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDTPROVV_TIPO_PROVVEDIMENTO_VALIDITY_CHECK)
	List<TsddrTAtto> findAttiByIdImpianto(@Param("idImpianto") Long idImpianto, @Param("currentDate") Date currentDate);
	
}
