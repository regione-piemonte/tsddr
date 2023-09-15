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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoProfilo;

/**
 * The Interface TsddrDTipoProfiloRepository.
 */
@Repository
public interface TsddrDTipoProfiloRepository extends JpaRepository<TsddrDTipoProfilo, Long> {
	
	/**
	 * Find by id tipo profilo.
	 *
	 * @param idTipoProfilo the id tipo profilo
	 * @return the optional
	 */
	Optional<TsddrDTipoProfilo> findByIdTipoProfilo(Long idTipoProfilo);
	
	/**
	 * Gets the combo tipo profilo.
	 *
	 * @param currentDate the current date
	 * @return the combo tipo profilo
	 */
	@Query("SELECT tdtp "
			+ "FROM TsddrDTipoProfilo tdtp "
			+ "WHERE "
			+ RepositoryUtil.TDTP_TIPO_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdtp.descTipoProfilo ASC")
	public List<TsddrDTipoProfilo> getComboTipoProfilo(@Param("currentDate") Date currentDate);
	
}
