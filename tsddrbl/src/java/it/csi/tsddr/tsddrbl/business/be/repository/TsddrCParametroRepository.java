/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametro;

/**
 * The Interface TsddrCParametroRepository.
 */
@Repository
public interface TsddrCParametroRepository extends BaseRepository<TsddrCParametro, Long> {
	
	/**
	 * Find by nome parametro.
	 *
	 * @param nomeParametro the nome parametro
	 * @return the optional
	 */
	@Query("SELECT tcp "
			+ "FROM TsddrCParametro tcp "
			+ "WHERE tcp.nomeParametro = :nomeParametro AND "
			+ RepositoryUtil.TCP_PARAMETRO_DELETE_VALIDITY_CHECK)
	Optional<TsddrCParametro> findByNomeParametro(@Param("nomeParametro") String nomeParametro);
	
	
}
