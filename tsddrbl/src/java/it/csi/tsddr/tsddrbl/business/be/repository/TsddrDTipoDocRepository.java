/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoDoc;

/**
 * The Interface TsddrDTipoDocRepository.
 */
@Repository
public interface TsddrDTipoDocRepository extends JpaRepository<TsddrDTipoDoc, Long> {

    /**
     * Find tipo doc by id.
     *
     * @param idTipoDoc the id tipo doc
     * @param currentDate the current date
     * @return the optional
     */
    @Query("SELECT tdtd "
            + "FROM TsddrDTipoDoc tdtd "
            + "WHERE tdtd.idTipoDoc = :idTipoDoc AND "
            + RepositoryUtil.TDTD_TIPO_DOC_VALIDITY_CHECK)
    public Optional<TsddrDTipoDoc> findTipoDocById(@Param("idTipoDoc") Long idTipoDoc, @Param("currentDate") Date currentDate);

    
}
