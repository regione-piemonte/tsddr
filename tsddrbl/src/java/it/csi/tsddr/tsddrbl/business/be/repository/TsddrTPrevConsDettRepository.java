/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevConsDett;

/**
 * The Interface TsddrTPrevConsDettRepository.
 */
@Repository
public interface TsddrTPrevConsDettRepository extends JpaRepository<TsddrTPrevConsDett, Long> {
    
    @Modifying
    @Query("DELETE FROM TsddrTPrevConsDett ttpcd WHERE "
            + "ttpcd.idPrevConsDett = :idPrevConsDett")
    void deleteById(@Param("idPrevConsDett")Long idPrevConsDett);
    
    @Modifying
    @Query("DELETE FROM TsddrTPrevConsDett ttpcd WHERE "
            + "ttpcd.idPrevConsDett IN (:idPrevConsDett)")
    void deleteById(@Param("idPrevConsDett")List<Long> idPrevConsDett);
    
    @Query("SELECT ttpcd " 
            + "FROM TsddrTPrevConsDett ttpcd "
            + "WHERE ttpcd.idPrevConsDett = :idPrevConsDett")
    Optional<TsddrTPrevConsDett> findByIdPrevConsDett(@Param("idPrevConsDett")Long idPrevConsDett);
    
}
