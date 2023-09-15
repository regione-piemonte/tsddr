/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoIndirizzo;

/**
 * The Interface TsddrDTipoIndirizzoRepository.
 */
@Repository
public interface TsddrDTipoIndirizzoRepository extends BaseRepository<TsddrDTipoIndirizzo, Long> {
	
	/**
	 * Find by id tipo indirizzo.
	 *
	 * @param idTipoIndirizzo the id tipo indirizzo
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdtind "
			+ "FROM TsddrDTipoIndirizzo tdtind "
			+ "WHERE tdtind.idTipoIndirizzo = :idTipoIndirizzo AND "
			+ RepositoryUtil.TDTIND_TIPO_INDIRIZZO_VALIDITY_CHECK)
	Optional<TsddrDTipoIndirizzo> findByIdTipoIndirizzo(@Param("idTipoIndirizzo")Long idTipoIndirizzo, @Param("currentDate") Date currentDate);

}
