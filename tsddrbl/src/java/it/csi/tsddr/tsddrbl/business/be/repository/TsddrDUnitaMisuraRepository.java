/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDUnitaMisura;

/**
 * The Interface TsddrDUnitaMisuraRepository.
 */
@Repository
public interface TsddrDUnitaMisuraRepository extends BaseRepository<TsddrDUnitaMisura, Long> {

	/**
	 * Find by id unita misura.
	 *
	 * @param idUnitaMisura the id unita misura
	 * @return the optional
	 */
	@Query("SELECT tdum "
			+ "FROM TsddrDUnitaMisura tdum "
			+ "WHERE tdum.idUnitaMisura = :idUnitaMisura AND "
			+ RepositoryUtil.TDUM_UNITA_MISURA_DELETE_VALIDITY_CHECK)
	Optional<TsddrDUnitaMisura> findByIdUnitaMisura(@Param("idUnitaMisura") Long idUnitaMisura);
	
}
