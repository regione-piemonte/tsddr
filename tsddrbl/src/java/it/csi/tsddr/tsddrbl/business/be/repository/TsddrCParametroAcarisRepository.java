/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametroAcaris;

/**
 * The Interface TsddrCParametroAcarisRepository.
 */
@Repository
public interface TsddrCParametroAcarisRepository extends BaseRepository<TsddrCParametroAcaris, Long> {
	
	/**
	 * Find parametro acaris.
	 *
	 * @return the list
	 */
	@Query("SELECT tcpa "
			+ "FROM TsddrCParametroAcaris tcpa "
			+ "WHERE "
			+ RepositoryUtil.TCPA_PARAMETRO_ACARIS_DELETE_VALIDITY_CHECK)
	List<TsddrCParametroAcaris> findParametroAcaris();
	
}
