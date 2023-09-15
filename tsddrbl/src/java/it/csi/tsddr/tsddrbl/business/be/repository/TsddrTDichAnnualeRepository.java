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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;

/**
 * The Interface TsddrTDichAnnualeRepository.
 */
@Repository
public interface TsddrTDichAnnualeRepository
		extends JpaRepository<TsddrTDichAnnuale, Long>, JpaSpecificationExecutor<TsddrTDichAnnuale> {

	/**
	 * Find distinct anni dich annuale.
	 *
	 * @return the list
	 */
	@Query("SELECT distinct(ttda.anno) " 
			+ "FROM TsddrTDichAnnuale ttda " 
			+ "WHERE "
			+ RepositoryUtil.TTDA_DICH_ANNUALE_DELETE_VALIDITY_CHECK
			+ " order by ttda.anno" )
	List<Long> findDistinctAnniDichAnnuale();

	/**
	 * Find by id impianto and anno and id gestore and id stato dichichiarazione.
	 *
	 * @param idImpianto the id impianto
	 * @param anno the anno
	 * @param idGestore the id gestore
	 * @param idStatoDichiarazione the id stato dichiarazione
	 * @return the optional
	 */
	@Query("SELECT ttda "
			+ "FROM TsddrTDichAnnuale ttda "
			+ "INNER JOIN ttda.impianto ttimp "
			+ "INNER JOIN ttimp.gestore ttg "
			+ "INNER JOIN ttda.statoDichiarazione tdsdich "
			+ "WHERE ttda.anno = :anno AND "
			+ "ttda.versione = (SELECT MAX(ttda1.versione) FROM TsddrTDichAnnuale ttda1 WHERE ttda1.impianto.idImpianto = :idImpianto AND ttda1.anno = :anno) AND "
			+ "ttimp.idImpianto = :idImpianto AND "
			+ "ttg.idGestore = :idGestore AND "
			+ "tdsdich.idStatoDichiarazione = :idStatoDichiarazione AND "
            + RepositoryUtil.TTDA_DICH_ANNUALE_DELETE_VALIDITY_CHECK)
	Optional<TsddrTDichAnnuale> findByIdImpiantoAndAnnoAndIdGestoreAndIdStatoDichichiarazione(@Param("idImpianto")Long idImpianto, @Param("anno")Long anno, 
			@Param("idGestore")Long idGestore, @Param("idStatoDichiarazione")Long idStatoDichiarazione);
	
	@Query("SELECT ttda "
            + "FROM TsddrTDichAnnuale ttda "
            + "INNER JOIN ttda.impianto ttimp "
            + "INNER JOIN ttimp.gestore ttg "
            + "WHERE ttda.anno = :anno AND "
            + "ttimp.idImpianto = :idImpianto AND "
            + "ttg.idGestore = :idGestore")
    List<TsddrTDichAnnuale> findByIdImpiantoAndAnnoAndIdGestore(@Param("idImpianto")Long idImpianto, @Param("anno")Long anno, 
            @Param("idGestore")Long idGestore);

	/**
	 * Find by id dich annuale.
	 *
	 * @param idDichAnnuale the id dich annuale
	 * @return the optional
	 */
	@Query("SELECT ttda " 
			+ "FROM TsddrTDichAnnuale ttda " 
			+ "WHERE ttda.idDichAnnuale = :idDichAnnuale")
	Optional<TsddrTDichAnnuale> findByIdDichAnnuale(@Param("idDichAnnuale") Long idDichAnnuale);
	
	/**
	 * Find by id impianto and anno.
	 *
	 * @param idImpianto the id impianto
	 * @param anno the anno
	 * @return the optional
	 */
	@Query("SELECT ttda " 
			+ "FROM TsddrTDichAnnuale ttda " 
			+ "WHERE ttda.impianto.idImpianto = :idImpianto "
			+ "AND ttda.anno = :anno "
			+ "AND ttda.versione = "
			+ "(SELECT MAX(ttda1.versione) "
			+ "FROM TsddrTDichAnnuale ttda1 "
			+ "WHERE ttda1.impianto.idImpianto = :idImpianto "
			+ "AND ttda1.anno = :anno)")
	Optional<TsddrTDichAnnuale> findByIdImpiantoAndAnno(@Param("idImpianto") Long idImpianto, @Param("anno") Long anno);

	/**
	 * Find by id dich annuale and id stato dich annuale.
	 *
	 * @param idDichAnnuale the id dich annuale
	 * @param idStatoDichiarazione the id stato dichiarazione
	 * @return the optional
	 */
	@Query("SELECT ttda " 
			+ "FROM TsddrTDichAnnuale ttda " 
			+ "INNER JOIN ttda.statoDichiarazione tdsdich "
			+ "WHERE ttda.idDichAnnuale = :idDichAnnuale AND "
			+ "tdsdich.idStatoDichiarazione = :idStatoDichiarazione AND "
			+ RepositoryUtil.TTDA_DICH_ANNUALE_DELETE_VALIDITY_CHECK)
	Optional<TsddrTDichAnnuale> findByIdDichAnnualeAndIdStatoDichAnnuale(@Param("idDichAnnuale") Long idDichAnnuale, @Param("idStatoDichiarazione") Long idStatoDichiarazione);

    /**
     * Find by id impianto and anno and id gestore and versione.
     *
     * @param idImpianto the id impianto
     * @param anno the anno
     * @param idGestore the id gestore
     * @param versione the versione
     * @return the optional
     */
	@Query("SELECT ttda "
            + "FROM TsddrTDichAnnuale ttda "
            + "INNER JOIN ttda.statoDichiarazione tdsdich "
            + "INNER JOIN ttda.impianto ttimp "
            + "INNER JOIN ttimp.gestore ttg "
            + "WHERE ttda.anno = :anno AND "
            + "ttda.versione = :versione AND "
            + "ttimp.idImpianto = :idImpianto AND "
            + "ttg.idGestore = :idGestore AND "
            + "tdsdich.idStatoDichiarazione = :idStatoDichiarazione")
    Optional<TsddrTDichAnnuale> findByIdImpiantoAndAnnoAndIdGestoreAndVersione(@Param("idImpianto") Long idImpianto, @Param("anno") Long anno,
            @Param("idGestore") Long idGestore, @Param("versione") Long versione, @Param("idStatoDichiarazione") Long idStatoDichiarazione);

   /**
    * Find by id impianto where anno and id gestore and versione.
    *
    * @param idImpianto the id impianto
    * @return the optional
    */
	@Query("SELECT ttda "
			+ "FROM TsddrTDichAnnuale ttda "
            + "INNER JOIN ttda.statoDichiarazione tdsdich "
            + "INNER JOIN ttda.impianto ttimp "
			+ "WHERE ttimp.idImpianto = :idImpianto AND "
			+ "ttda.dataDelete is NULL AND "
			+ "ttda.idUserDelete is NULL AND "
			+ "tdsdich.idStatoDichiarazione != 3 ")
	List<TsddrTDichAnnuale> findByIdImpianto(@Param("idImpianto") Long idImpianto);
	
}