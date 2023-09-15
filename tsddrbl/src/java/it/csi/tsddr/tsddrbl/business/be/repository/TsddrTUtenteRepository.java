/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;

/**
 * The interface Tsddr t user repository.
 */
@Repository
public interface TsddrTUtenteRepository extends JpaRepository<TsddrTUtente, Long>, JpaSpecificationExecutor<TsddrTUtente> {

    /**
     * Find id by cf.
     *
     * @param cf the cf
     * @return the long
     */
    @Query("SELECT o.idUtente FROM TsddrTUtente o WHERE o.datiSogg.codFiscale = :cf")
    Long findIdByCf(@Param("cf") String cf);
    
    /**
     * Find by id utente.
     *
     * @param idUtente the id utente
     * @return the optional
     */
    @Query("SELECT ttu "
    		+ "FROM TsddrTUtente ttu "
    		+ "INNER JOIN ttu.datiSogg ttds "
    		+ "WHERE ttu.idUtente = :idUtente AND "
    		+ RepositoryUtil.TTU_UTENTE_DELETE_VALIDITY_CHECK 
    		+ "AND "
    		+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK)
    Optional<TsddrTUtente> findByIdUtente(@Param("idUtente") Long idUtente);
    
    /**
     * Find by id dati sogg.
     *
     * @param idDatiSogg the id dati sogg
     * @return the optional
     */
    @Query("SELECT ttu "
    		+ "FROM TsddrTUtente ttu "
    		+ "INNER JOIN ttu.datiSogg ttds "
    		+ "WHERE ttds.idDatiSogg = :idDatiSogg AND "
    		+ RepositoryUtil.TTU_UTENTE_DELETE_VALIDITY_CHECK 
    		+ "AND "
    		+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK)
    Optional<TsddrTUtente> findByIdDatiSogg(@Param("idDatiSogg") Long idDatiSogg);
    
    /**
     * Find by id utente.
     *
     * @param idUtente the id utente
     * @param currentDate the current date
     * @return the optional
     */
    @Query("SELECT ttu "
    		+ "FROM TsddrTUtente ttu "
    		+ "INNER JOIN ttu.datiSogg ttds "
    		+ "WHERE ttu.idUtente = :idUtente AND "
    		+ RepositoryUtil.TTU_UTENTE_VALIDITY_CHECK
    		+ "AND "
    		+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK)
    Optional<TsddrTUtente> findByIdUtente(@Param("idUtente") Long idUtente, @Param("currentDate") Date currentDate);

}