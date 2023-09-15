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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProfPK;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.vo.utenteprofilo.UtenteProfiloVO;

/**
 * The Interface TsddrRUtenteProfRepository.
 */
@Repository
public interface TsddrRUtenteProfRepository extends JpaRepository<TsddrRUtenteProf, TsddrRUtenteProfPK> {

	/**
	 * Find id profili by id utente.
	 *
	 * @param idUtente the id utente
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT trup.id.idProfilo "
			+ "FROM TsddrRUtenteProf trup "
			+ "INNER JOIN trup.utente ttu "
			+ "WHERE ttu.idUtente = :idUtente AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK)
	List<Long> findIdProfiliByIdUtente(@Param("idUtente") Long idUtente, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id utente.
	 *
	 * @param idUtente the id utente
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT trup "
			+ "FROM TsddrRUtenteProf trup "
			+ "WHERE trup.id.idUtente = :idUtente AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK)
	List<TsddrRUtenteProf> findByIdUtente(@Param("idUtente") Long idUtente, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id utente and id profilo.
	 *
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT trup "
			+ "FROM TsddrRUtenteProf trup "
			+ "WHERE trup.id.idUtente = :idUtente AND "
			+ "trup.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK)
	Optional<TsddrRUtenteProf> findByIdUtenteAndIdProfilo(@Param("idUtente") Long idUtente, @Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Count by id profilo.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the long
	 */
	@Query("SELECT COUNT(trup) "
			+ "FROM TsddrRUtenteProf trup "
			+ "WHERE trup.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK)
	Long countByIdProfilo(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Find utenti by id profilo.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT new it.csi.tsddr.tsddrbl.vo.utenteprofilo.UtenteProfiloVO(ttu.idUtente, ttds.codFiscale, ttds.cognome, ttds.nome)"
			+ "FROM TsddrTUtente ttu "
			+ "INNER JOIN ttu.rUtentiProf trup "
			+ "INNER JOIN ttu.datiSogg ttds "
			+ "WHERE trup.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTU_UTENTE_VALIDITY_CHECK 
    		+ "AND "
    		+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK)
	List<UtenteProfiloVO> findUtentiByIdProfilo(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Find utenti by id profilo not.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT distinct(ttu), ttu.datiSogg "
			+ "FROM TsddrTUtente ttu "
			+ "INNER JOIN ttu.datiSogg ttds "
			+ "WHERE "
			+ RepositoryUtil.TTU_UTENTE_VALIDITY_CHECK 
    		+ "AND "
    		+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK
    		+ "AND "
			+ "(ttu.idUtente NOT IN (SELECT trup.id.idUtente FROM TsddrRUtenteProf trup)"
			+ "OR "
			+ "ttu.idUtente NOT IN ("
			+ "SELECT trup.id.idUtente "
			+ "FROM TsddrRUtenteProf trup "
			+ "WHERE trup.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUP_RUTENTIPROF_VALIDITY_CHECK + "))"
			+ "ORDER BY ttu.datiSogg.cognome, ttu.datiSogg.nome")
	List<TsddrTUtente> findUtentiByIdProfiloNot(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
}
