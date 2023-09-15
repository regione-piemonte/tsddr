/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTMessaggio;

/**
 * The Interface TsddrTMessaggioRepository.
 */
@Repository
public interface TsddrTMessaggioRepository extends JpaRepository<TsddrTMessaggio, Long> {
	
	/**
	 * Find by cod msg.
	 *
	 * @param codMsg the cod msg
	 * @return the optional
	 */
	Optional<TsddrTMessaggio> findByCodMsg(String codMsg);

}
