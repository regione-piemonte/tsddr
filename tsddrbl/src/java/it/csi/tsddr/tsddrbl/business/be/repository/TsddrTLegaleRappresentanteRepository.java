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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLegaleRappresentante;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLegaleRappresentantePK;

/**
 * The Interface TsddrTLegaleRappresentanteRepository.
 */
@Repository
public interface TsddrTLegaleRappresentanteRepository extends BaseRepository<TsddrTLegaleRappresentante, TsddrTLegaleRappresentantePK> {
	
	/**
	 * Find by id gestore.
	 *
	 * @param idGestore the id gestore
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT ttlr "
			+ "FROM TsddrTLegaleRappresentante ttlr "
			+ "WHERE ttlr.id.idGestore = :idGestore AND "
			+ RepositoryUtil.TTLR_LEGALE_RAPP_VALIDITY_CHECK)
	Optional<TsddrTLegaleRappresentante> findByIdGestore(@Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);
	

}
