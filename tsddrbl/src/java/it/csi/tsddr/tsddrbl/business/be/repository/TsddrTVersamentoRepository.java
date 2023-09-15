/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTVersamento;

/**
 * The Interface TsddrTVersamentoRepository.
 */
@Repository
public interface TsddrTVersamentoRepository extends JpaRepository<TsddrTVersamento, Long> {

}
