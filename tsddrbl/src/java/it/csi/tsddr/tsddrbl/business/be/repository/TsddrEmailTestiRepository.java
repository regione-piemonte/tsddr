/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrEmailTesti;

/**
 * The Interface TsddrEmailTestiRepository.
 */
public interface TsddrEmailTestiRepository extends JpaRepository<TsddrEmailTesti, Long> {
	
	/**
	 * Find by id email and data delete is null and id user delete is null.
	 *
	 * @param idEmail the id email
	 * @return the optional
	 */
	Optional<TsddrEmailTesti> findByIdEmailAndDataDeleteIsNullAndIdUserDeleteIsNull(Long idEmail);

}
