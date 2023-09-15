/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProfilo;

/**
 * The Interface TsddrDProfiliRepository.
 */
@Repository
public interface TsddrDProfiliRepository extends JpaRepository<TsddrDProfilo, Long> {
	
	/**
	 * Find by id profilo.
	 *
	 * @param idProfilo the id profilo
	 * @return the optional
	 */
	Optional<TsddrDProfilo> findByIdProfilo(Long idProfilo);
	
	/**
	 * Find by id profilo.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdp "
			+ "FROM TsddrDProfilo tdp "
			+ "WHERE tdp.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK)
	Optional<TsddrDProfilo> findByIdProfilo(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Find profili by codice fiscale.
	 *
	 * @param codiceFiscale the codice fiscale
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdp "
			+ "FROM TsddrTDatiSogg ttds "
			+ "INNER JOIN ttds.utente ttu "
			+ "INNER JOIN ttu.rUtentiProf trup "
			+ "INNER JOIN trup.profilo tdp "
			+ "INNER JOIN tdp.tipoProfilo tdtp "
			+ "WHERE ttds.codFiscale = :codiceFiscale AND " 
			+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TTU_UTENTE_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK
			+ "AND " 
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdtp.idTipoProfilo ASC, tdp.idProfilo ASC")
	List<TsddrDProfilo> findProfiliByCodiceFiscale(@Param("codiceFiscale") String codiceFiscale, @Param("currentDate") Date currentDate);
	
	/**
	 * Find id profili by codice fiscale.
	 *
	 * @param codiceFiscale the codice fiscale
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdp.idProfilo "
			+ "FROM TsddrTDatiSogg ttds "
			+ "INNER JOIN ttds.utente ttu "
			+ "INNER JOIN ttu.rUtentiProf trup "
			+ "INNER JOIN trup.profilo tdp "
			+ "INNER JOIN tdp.tipoProfilo tdtp "
			+ "WHERE ttds.codFiscale = :codiceFiscale AND "
			+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TTU_UTENTE_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK
			+ "AND " 
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdtp.idTipoProfilo ASC")
	List<Long> findIdProfiliByCodiceFiscale(@Param("codiceFiscale") String codiceFiscale, @Param("currentDate") Date currentDate);
	
	/**
	 * Find profili by id utente.
	 *
	 * @param idUtente the id utente
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdp "
			+ "FROM TsddrRUtenteProf trup "
			+ "INNER JOIN trup.profilo as tdp "
			+ "INNER JOIN trup.utente as ttu "
			+ "WHERE ttu.idUtente = :idUtente AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdp.descProfilo ASC")
	List<TsddrDProfilo> findProfiliByIdUtente(@Param("idUtente") Long idUtente, @Param("currentDate") Date currentDate);
	
	/**
	 * Find id profili by id utente.
	 *
	 * @param idUtente the id utente
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdp.idProfilo "
			+ "FROM TsddrRUtenteProf trup "
			+ "INNER JOIN trup.profilo as tdp "
			+ "INNER JOIN trup.utente as ttu "
			+ "WHERE ttu.idUtente = :idUtente AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdp.descProfilo ASC")
	List<Long> findIdProfiliByIdUtente(@Param("idUtente") Long idUtente, @Param("currentDate") Date currentDate);
	
	/**
	 * Find profili.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdp "
			+ "FROM TsddrDProfilo tdp "
			+ "WHERE "
			+ RepositoryUtil.TDP_PROFILO_ID_CHECK 
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdp.descProfilo ASC")
	List<TsddrDProfilo> findProfili(@Param("currentDate") Date currentDate);
	
	/**
	 * Find all profili.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdp "
			+ "FROM TsddrDProfilo tdp "
			+ "WHERE "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdp.descProfilo ASC")
	List<TsddrDProfilo> findAllProfili(@Param("currentDate") Date currentDate);
	
	/**
	 * Find profili data and id user check.
	 *
	 * @return the list
	 */
	@Query("SELECT tdp "
			+ "FROM TsddrDProfilo tdp "
			+ "WHERE "
			+ RepositoryUtil.TDP_PROFILO_DATA_DELETE_ID_USER_VALIDITY_CHECK
			+ "ORDER BY tdp.idProfilo ASC")
	List<TsddrDProfilo> findProfiliDataAndIdUserCheck();
	
	/**
	 * Find profili by id tipo profilo.
	 *
	 * @param idTipoProfilo the id tipo profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdp "
			+ "FROM TsddrDProfilo tdp "
			+ "INNER JOIN tdp.tipoProfilo tdtp "
			+ "WHERE tdtp.idTipoProfilo = :idTipoProfilo AND "
			+ "tdp.idProfilo = :idProfilo AND " 
			+ RepositoryUtil.TDTP_TIPO_PROFILO_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdp.descProfilo ASC")
	List<TsddrDProfilo> findProfiliByIdTipoProfilo(@Param("idProfilo") Long idProfilo, @Param("idTipoProfilo") Long idTipoProfilo, @Param("currentDate") Date currentDate);
	
}
