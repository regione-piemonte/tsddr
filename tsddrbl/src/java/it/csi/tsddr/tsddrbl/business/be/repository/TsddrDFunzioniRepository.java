/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDFunzione;

/**
 * The Interface TsddrDFunzioniRepository.
 */
@Repository
public interface TsddrDFunzioniRepository extends JpaRepository<TsddrDFunzione, Long> {

	/**
	 * Find by id funzione.
	 *
	 * @param idFunzione the id funzione
	 * @return the optional
	 */
	Optional<TsddrDFunzione> findByIdFunzione(Long idFunzione);
	
}
