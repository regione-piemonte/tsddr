/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;

/**
 * The Interface TsddrTGestoreRepository.
 */
@Repository
public interface TsddrTGestoreRepository extends JpaRepository<TsddrTGestore, Long>, JpaSpecificationExecutor<TsddrTGestore> {
	

	/**
	 * Find by id gestore.
	 *
	 * @param idGestore the id gestore
	 * @return the optional
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE ttg.idGestore = :idGestore")
	Optional<TsddrTGestore> findByIdGestore(@Param("idGestore") Long idGestore);
	
	/**
	 * Find by id gestore.
	 *
	 * @param idGestore the id gestore
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE ttg.idGestore = :idGestore AND "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK)
	Optional<TsddrTGestore> findByIdGestore(@Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);
	
	/**
	 * Exists by cod fisc partiva and id gestore not.
	 *
	 * @param codFiscPartiva the cod fisc partiva
	 * @param idGestore the id gestore
	 * @param currentDate the current date
	 * @return true, if successful
	 */
	@Query("SELECT case when count(ttg)> 0 then true else false end "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE UPPER(ttg.codFiscPartiva) = UPPER(:codFiscPartiva) "
			+ "AND ttg.idGestore != :idGestore AND "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK)
	boolean existsByCodFiscPartivaAndIdGestoreNot(@Param("codFiscPartiva") String codFiscPartiva, @Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by cod fisc partiva.
	 *
	 * @param codFiscPartiva the cod fisc partiva
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE UPPER(ttg.codFiscPartiva) = UPPER(:codFiscPartiva) AND "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK)
	Optional<TsddrTGestore> findByCodFiscPartiva(@Param("codFiscPartiva") String codFiscPartiva, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id gestori.
	 *
	 * @param idGestori the id gestori
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE ttg.idGestore IN ( :idGestori ) AND "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> findByIdGestori(@Param("idGestori") List<Long> idGestori, @Param("currentDate") Date currentDate);
	
	/**
	 * Find gestori.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> findGestori(@Param("currentDate") Date currentDate);

	/**
	 * Find gestori by id utente id profilo.
	 *
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrRUtenteGestoreProfilo trugp "
			+ "INNER JOIN trugp.gestore ttg "
			+ "WHERE trugp.id.idUtente = :idUtente AND "
			+ "trugp.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> findGestoriByIdUtenteIdProfilo(@Param("idUtente") Long idUtente, @Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Find gestori by id utente.
	 *
	 * @param idUtente the id utente
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrRUtenteGestoreProfilo trugp "
			+ "INNER JOIN trugp.gestore ttg "
			+ "WHERE trugp.id.idUtente = :idUtente AND "
			+ "trugp.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> findGestoriByIdUtenteAndIdProfilo(@Param("idUtente") Long idUtente, @Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Gets the combo griglia gestore.
	 *
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the combo griglia gestore
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE ttg.idGestore NOT IN ( "
				+ "SELECT trugp.gestore.idGestore "
				+ "FROM TsddrRUtenteGestoreProfilo trugp "
				+ "INNER JOIN trugp.profilo tdp "
				+ "INNER JOIN trugp.utente ttu "
				+ "WHERE tdp.idProfilo = :idProfilo AND "
				+ "ttu.idUtente = :idUtente AND "
				+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK
			+ ") AND "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> getComboGrigliaGestore(@Param("idUtente") Long idUtente, @Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);

	/**
	 * Find gestori per nuova domanda.
	 *
	 * @param idDatiSogg the id dati sogg
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT ttg "
			+ "FROM TsddrTGestore ttg "
			+ "WHERE ttg.idGestore NOT IN ( "
				+ "SELECT ttd.gestore.idGestore "
				+ "FROM TsddrTDomanda ttd "
				+ "INNER JOIN ttd.datiSogg ttds "
				+ "INNER JOIN ttd.statoDomanda tdsd "
				+ "WHERE ttds.idDatiSogg = :idDatiSogg AND "
				+ "tdsd.step = 1 AND "
				+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK
			+ ") AND "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> findGestoriPerNuovaDomanda(@Param("idDatiSogg") Long idDatiSogg, @Param("currentDate") Date currentDate);

	/**
	 * Find gestori with domande accreditamento.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT DISTINCT(ttg) "
			+ "FROM TsddrTGestore ttg "
			+ "INNER JOIN ttg.domande ttd "
			+ "WHERE "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> findGestoriWithDomandeAccreditamento(@Param("currentDate") Date currentDate);

	/**
	 * Find gestori with domande accreditamento and id gestori.
	 *
	 * @param idGestori the id gestori
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT DISTINCT(ttg) "
			+ "FROM TsddrTGestore ttg "
			+ "INNER JOIN ttg.domande ttd "
			+ "WHERE ttg.idGestore IN ( :idGestori ) AND "
			+ RepositoryUtil.TTG_GESTORE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttg.ragSociale ASC")
	List<TsddrTGestore> findGestoriWithDomandeAccreditamentoAndIdGestori(@Param("idGestori")List<Long> idGestori, @Param("currentDate") Date currentDate);

}