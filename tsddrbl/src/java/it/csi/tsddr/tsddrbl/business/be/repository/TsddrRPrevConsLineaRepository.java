/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRPrevConsLinea;

/**
 * The Interface TsddrRPrevConsLineaRepository.
 */
@Repository
public interface TsddrRPrevConsLineaRepository extends JpaRepository<TsddrRPrevConsLinea, Long> {

    @Query("SELECT trpcl "
            + "FROM TsddrRPrevConsLinea trpcl "
            + "WHERE trpcl.idPrevConsLinee = :idPrevConsLinee")
    Optional<TsddrRPrevConsLinea> findByIdPrevConsLinee(@Param("idPrevConsLinee") Long idPrevConsLinee);
    
}
