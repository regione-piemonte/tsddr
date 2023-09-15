/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTNotaInfo;

/**
 * The Interface TsddrTNotaInfoRepository.
 */
@Repository
public interface TsddrTNotaInfoRepository extends JpaRepository<TsddrTNotaInfo, Long> {

	/**
	 * Find by cod nota info.
	 *
	 * @param codNotaInfo the cod nota info
	 * @return the optional
	 */
	Optional<TsddrTNotaInfo> findByCodNotaInfo(String codNotaInfo);
	
}
