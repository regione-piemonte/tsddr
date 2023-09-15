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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfilo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteGestoreProfiloPK;

/**
 * The Interface TsddrRUtenteGestoreProfiloRepository.
 */
@Repository
public interface TsddrRUtenteGestoreProfiloRepository extends JpaRepository<TsddrRUtenteGestoreProfilo, TsddrRUtenteGestoreProfiloPK> {
	
	/**
	 * Find by id id utente and id id gestore and id id profilo.
	 *
	 * @param idUtene the id utene
	 * @param idGestore the id gestore
	 * @param idProfilo the id profilo
	 * @return the optional
	 */
	Optional<TsddrRUtenteGestoreProfilo> findByIdIdUtenteAndIdIdGestoreAndIdIdProfilo(Long idUtene, Long idGestore, Long idProfilo);
	
	/**
	 * Find by id utente.
	 *
	 * @param idUtente the id utente
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT trugp "
			+ "FROM TsddrRUtenteGestoreProfilo trugp "
			+ "WHERE trugp.id.idUtente = :idUtente AND "
			+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK)
	List<TsddrRUtenteGestoreProfilo> findByIdUtente(@Param("idUtente") Long idUtente, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id gestore.
	 *
	 * @param idGestore the id gestore
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT trugp "
			+ "FROM TsddrRUtenteGestoreProfilo trugp "
			+ "WHERE trugp.id.idGestore = :idGestore AND "
			+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK)
	List<TsddrRUtenteGestoreProfilo> findByIdGestore(@Param("idGestore") Long idGestore, @Param("currentDate") Date currentDate);

	/**
	 * Find by id utente id profilo.
	 *
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT trugp "
			+ "FROM TsddrRUtenteGestoreProfilo trugp "
			+ "INNER JOIN trugp.gestore ttg "
			+ "WHERE trugp.id.idUtente = :idUtente AND "
			+ "trugp.id.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK 
			+ "AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK)
	public List<TsddrRUtenteGestoreProfilo> findByIdUtenteIdProfilo(@Param("idUtente") Long idUtente, @Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	
}
