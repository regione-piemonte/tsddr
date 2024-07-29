/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Optional;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;

/**
 * The Interface TsddrTImpiantoRepository.
 */
@Repository
public interface TsddrTImpiantoRepository extends BaseRepository<TsddrTImpianto, Long> {

	/**
	 * Find by id impianto.
	 *
	 * @param idImpianto the id impianto
	 * @return the optional
	 */
	@Query("SELECT ttimp "
			+ "FROM TsddrTImpianto ttimp "
			+ "WHERE ttimp.idImpianto = :idImpianto AND "
			+ RepositoryUtil.TTIMP_IMPIANTO_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttimp.denominazione ASC")
	Optional<TsddrTImpianto> findByIdImpianto(@Param("idImpianto") Long idImpianto);
	
	/**
	 * Find by id gestore.
	 *
	 * @param idGestore the id gestore
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttimp "
			+ "FROM TsddrTImpianto ttimp "
			+ "INNER JOIN ttimp.gestore ttg "
			+ "WHERE ttg.idGestore = :idGestore AND "
			+ RepositoryUtil.TTIMP_IMPIANTO_VALIDITY_CHECK
			+ "ORDER BY ttimp.denominazione ASC")
	List<TsddrTImpianto> findByIdGestore(@Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);
	
	/**
	 * Exists by id gestore.
	 *
	 * @param idGestore the id gestore
	 * @param currentDate the current date
	 * @return true, if successful
	 */
	@Query("SELECT case when count(ttimp)> 0 then true else false end "
			+ "FROM TsddrTImpianto ttimp "
			+ "INNER JOIN ttimp.gestore ttg "
			+ "WHERE ttg.idGestore = :idGestore AND "
			+ RepositoryUtil.TTIMP_IMPIANTO_VALIDITY_CHECK)
	boolean existsByIdGestore(@Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);
	
	/**
	 * Find impianti.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttimp "
			+ "FROM TsddrTImpianto ttimp "
			+ "WHERE "
			+ RepositoryUtil.TTIMP_IMPIANTO_VALIDITY_CHECK
			+ "ORDER BY ttimp.denominazione ASC")
	List<TsddrTImpianto> findImpianti(@Param("currentDate") Date currentDate);
	
	/**
     * Find impianti.
     *
     * @param currentDate the current date
     * @return the list
     */
    @Query("SELECT ttimp "
            + "FROM TsddrTImpianto ttimp "
            + "INNER JOIN ttimp.gestore ttg "
            + "WHERE ttg.idGestore = :idGestore AND "
            + RepositoryUtil.TTIMP_IMPIANTO_VALIDITY_CHECK
			+ "ORDER BY ttimp.denominazione ASC")
    List<TsddrTImpianto> findImpiantiByIdGestore(@Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id utente and id profilo.
	 *
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttimp "
			+ "FROM TsddrRUtenteGestoreProfilo trugp "
			+ "INNER JOIN trugp.gestore ttg "
			+ "INNER JOIN ttg.impianti ttimp "
			+ "WHERE trugp.id.idUtente = :idUtente AND "
			+ "trugp.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTIMP_IMPIANTO_VALIDITY_CHECK
			+ "ORDER BY ttimp.denominazione ASC")
	List<TsddrTImpianto> findByIdUtenteAndIdProfilo(@Param("idUtente") Long idUtente, @Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id utente and id profilo and id gestore.
	 *
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @param idGestore the id gestore
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttimp "
			+ "FROM TsddrRUtenteGestoreProfilo trugp "
			+ "INNER JOIN trugp.gestore ttg "
			+ "INNER JOIN ttg.impianti ttimp "
			+ "WHERE trugp.id.idUtente = :idUtente AND "
			+ "trugp.id.idProfilo = :idProfilo AND "
			+ "ttg.idGestore = :idGestore AND "
			+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTIMP_IMPIANTO_VALIDITY_CHECK
			+ "ORDER BY ttimp.denominazione ASC")
	List<TsddrTImpianto> findByIdUtenteAndIdProfiloAndIdGestore(@Param("idUtente") Long idUtente, @Param("idProfilo") Long idProfilo, @Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);
}
