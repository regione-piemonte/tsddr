/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDEer;

/**
 * The Interface TsddrDEerRepository.
 */
@Repository
public interface TsddrDEerRepository extends JpaRepository<TsddrDEer, Long> {
    
    /**
     * Find eer.
     *
     * @param currentDate the current date
     * @return the list
     */
    @Query("SELECT tde "
            + "FROM TsddrDEer tde "
            + "WHERE "
            + RepositoryUtil.TDE_EER_VALIDITY_CHECK
            + "ORDER BY tde.codiceEer ASC")
    List<TsddrDEer> findEer(@Param("currentDate") Date currentDate);
    
    /**
     * Find by id eer.
     *
     * @param idEer the id eer
     * @param currentDate the current date
     * @return the optional
     */
    @Query("SELECT tde "
            + "FROM TsddrDEer tde "
            + "WHERE tde.idEer = :idEer AND "
            + RepositoryUtil.TDE_EER_VALIDITY_CHECK)
    Optional<TsddrDEer> findByIdEer(@Param("idEer")Long idEer, @Param("currentDate") Date currentDate);

}
