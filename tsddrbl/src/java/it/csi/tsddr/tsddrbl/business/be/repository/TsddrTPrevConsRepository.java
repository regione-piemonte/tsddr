/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;

/**
 * The Interface TsddrTPrevConsRepository.
 */
@Repository
public interface TsddrTPrevConsRepository extends JpaRepository<TsddrTPrevCons, Long>, JpaSpecificationExecutor<TsddrTPrevCons> {

    @Query("SELECT distinct(ttpc.annoTributo) " 
            + "FROM TsddrTPrevCons ttpc "
            + "INNER JOIN ttpc.tipoDoc tdtd " 
            + "WHERE tdtd.idTipoDoc = :idTipoDoc "
            + "order by ttpc.annoTributo DESC")
    List<Long> findDistinctAnniPrevConsByIdTipoDoc(@Param("idTipoDoc")Long idTipoDoc);
    
    @Query("SELECT distinct(ttpc.annoTributo) " 
            + "FROM TsddrTPrevCons ttpc "
            + "INNER JOIN ttpc.tipoDoc tdtd "
            + "INNER JOIN ttpc.statoDichiarazione tdsd " 
            + "WHERE tdtd.idTipoDoc = :idTipoDoc AND "
            + "tdsd.idStatoDichiarazione = :idStatoDichiarazione "
            + "ORDER BY ttpc.annoTributo DESC")
    List<Long> findDistinctAnniPrevConsByIdTipoDocAndIdStatoDichiarazione(@Param("idTipoDoc")Long idTipoDoc, @Param("idStatoDichiarazione")Long idStatoDichiarazione);

    @Query("SELECT ttpc "
            + "FROM TsddrTPrevCons ttpc "
            + "INNER JOIN ttpc.tipoDoc tdtd "
            + "INNER JOIN ttpc.statoDichiarazione tdsd "
            + "INNER JOIN ttpc.impianto tti "
            + "INNER JOIN tti.gestore ttg "
            + "WHERE ttpc.annoTributo = :annoTributo AND "
            + "tdtd.idTipoDoc = :idTipoDoc AND "
            + "tdsd.idStatoDichiarazione IN (:idStatiDich) AND "
            + "tti.idImpianto = :idImpianto AND "
            + "ttg.idGestore = :idGestore")
    Optional<TsddrTPrevCons> findByIdImpiantoAndIdGestoreAndAnnoTributoAndIdTipoDocAndIdStatiDich(@Param("idImpianto")Long idImpianto, @Param("idGestore")Long idGestore, @Param("annoTributo")Long annoTributo, @Param("idTipoDoc")Long idTipoDoc,
            @Param("idStatiDich")List<Long> idStatiDich);
    
    @Query("SELECT ttpc " 
            + "FROM TsddrTPrevCons ttpc "
            + "WHERE ttpc.idPrevCons = :idPrevCons")
    Optional<TsddrTPrevCons> findByIdPrevCons(@Param("idPrevCons")Long idPrevCons);

    // CR OB 258-259-260
    @Query("SELECT COUNT (ttpc.idPrevCons) "
            + "FROM TsddrTPrevCons ttpc "
            + "INNER JOIN ttpc.tipoDoc tdtd "
            + "INNER JOIN ttpc.statoDichiarazione tdsd "
            + "WHERE tdtd.idTipoDoc = :idTipoDoc AND "
            + "tdsd.idStatoDichiarazione = :idStatoDichiarazione AND "
            + "ttpc.idPrevConsRMr = :idPrevConsRMr"
    )
    long countByIdTipoDocAndIdStatoDichiarazioneAndIdPrevConsRMr(
            @Param("idTipoDoc")Long idTipoDoc,
            @Param("idStatoDichiarazione")Long idStatoDichiarazione,
            @Param("idPrevConsRMr")Long idPrevConsRMr
    );

    @Query("SELECT ttpc "
    		+ "FROM TsddrTPrevCons ttpc "
    		+ "INNER JOIN ttpc.statoDichiarazione tdsdich "
    		+ "INNER JOIN ttpc.impianto ttimp "
    		+ "WHERE ttimp.idImpianto = :impianto AND "
    		+ "ttpc.dataDelete is NULL AND "
    		+ "ttpc.idUserDelete is NULL AND "
    		+ "ttpc.statoDichiarazione != 3")
    List<TsddrTPrevCons> findByIdImpianto(@Param("impianto")Long impianto);
}
