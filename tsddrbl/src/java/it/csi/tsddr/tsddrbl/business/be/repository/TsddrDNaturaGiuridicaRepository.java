/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNaturaGiuridica;

/**
 * The Interface TsddrDNaturaGiuridicaRepository.
 */
@Repository
public interface TsddrDNaturaGiuridicaRepository extends BaseRepository<TsddrDNaturaGiuridica, Long> {
	
	/**
	 * Find nature giuridiche.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdng "
			+ "FROM TsddrDNaturaGiuridica tdng "
			+ "WHERE "
			+ RepositoryUtil.TDNG_NATURA_GIURIDICA_VALIDITY_CHECK
			+ "ORDER BY tdng.descNaturaGiuridica ASC")
	List<TsddrDNaturaGiuridica> findNatureGiuridiche(@Param("currentDate") Date currentDate);
	
	/**
	 * Find by id natura giuridica.
	 *
	 * @param idNaturaGiuridica the id natura giuridica
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdng "
			+ "FROM TsddrDNaturaGiuridica tdng "
			+ "WHERE tdng.idNaturaGiuridica = :idNaturaGiuridica AND "
			+ RepositoryUtil.TDNG_NATURA_GIURIDICA_VALIDITY_CHECK
			+ "ORDER BY tdng.descNaturaGiuridica ASC")
	Optional<TsddrDNaturaGiuridica> findByIdNaturaGiuridica(@Param("idNaturaGiuridica") Long idNaturaGiuridica, @Param("currentDate") Date currentDate);

}
