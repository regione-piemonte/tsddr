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
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDomanda;

/**
 * The Interface TsddrTDomandaRepository.
 */
@Repository
public interface TsddrTDomandaRepository extends JpaRepository<TsddrTDomanda, Long> {
	
	/**
	 * Find active by codice fiscale and gestore.
	 *
	 * @param codFiscale the cod fiscale
	 * @param idGestore the id gestore
	 * @return the list
	 */
	@Query("SELECT ttd "
			+ "FROM TsddrTDomanda ttd "
			+ "INNER JOIN ttd.datiSogg ttds "
			+ "INNER JOIN ttd.gestore ttg "
			+ "INNER JOIN ttd.statoDomanda tdsd "
			+ "WHERE ttds.codFiscale = :codFiscale AND "
			+ "ttg.idGestore = :idGestore AND "
			+ "tdsd.step = 1 AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK)
	List<TsddrTDomanda> findActiveByCodiceFiscaleAndGestore(@Param("codFiscale") String codFiscale, @Param("idGestore") Long idGestore);
	
	/**
	 * Find not canceled by gestore.
	 *
	 * @param idGestore the id gestore
	 * @return the list
	 */
	@Query("SELECT ttd "
			+ "FROM TsddrTDomanda ttd "
			+ "INNER JOIN ttd.gestore ttg "
			+ "INNER JOIN ttd.statoDomanda tdsd "
			+ "WHERE ttg.idGestore = :idGestore AND "
			+ "tdsd.idStatoDomanda != 4 AND "
			+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK)
	List<TsddrTDomanda> findNotCanceledByGestore(@Param("idGestore") Long idGestore);
	
	/**
	 * Exists not canceled by gestore.
	 *
	 * @param idGestore the id gestore
	 * @return true, if successful
	 */
	@Query("SELECT case when count(ttd)> 0 then true else false end "
			+ "FROM TsddrTDomanda ttd "
			+ "INNER JOIN ttd.gestore ttg "
			+ "INNER JOIN ttd.statoDomanda tdsd "
			+ "WHERE ttg.idGestore = :idGestore AND "
			+ "tdsd.idStatoDomanda != 4 AND "
			+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK)
	boolean existsNotCanceledByGestore(@Param("idGestore") Long idGestore);
	
	/**
	 * Find by codice fiscale.
	 *
	 * @param codFiscale the cod fiscale
	 * @return the list
	 */
	@Query("SELECT ttd "
			+ "FROM TsddrTDomanda ttd "
			+ "INNER JOIN ttd.datiSogg ttds "
			+ "INNER JOIN ttd.gestore ttg "
			+ "WHERE ttds.codFiscale = :codFiscale AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttd.idDomanda DESC")
	List<TsddrTDomanda> findByCodiceFiscale(@Param("codFiscale") String codFiscale);
	
	/**
	 * Find by codice fiscale and id domanda.
	 *
	 * @param codFiscale the cod fiscale
	 * @param idDomanda the id domanda
	 * @return the optional
	 */
	@Query("SELECT ttd "
			+ "FROM TsddrTDomanda ttd "
			+ "INNER JOIN ttd.datiSogg ttds "
			+ "WHERE ttds.codFiscale = :codFiscale AND "
			+ "ttd.idDomanda = :idDomanda")
	Optional<TsddrTDomanda> findByCodiceFiscaleAndIdDomanda(@Param("codFiscale") String codFiscale, @Param("idDomanda") Long idDomanda);
	
	/**
	 * Find by id domanda.
	 *
	 * @param idDomanda the id domanda
	 * @return the optional
	 */
	@Query("SELECT ttd "
			+ "FROM TsddrTDomanda ttd "
			+ "INNER JOIN ttd.datiSogg ttds "
			+ "INNER JOIN ttd.gestore ttg "
			+ "INNER JOIN ttd.statoDomanda tdsd "
			+ "WHERE ttd.idDomanda = :idDomanda AND "
			+ RepositoryUtil.TTD_DOMANDA_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TTG_GESTORE_DELETE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDSD_STATO_DOMANDA_DELETE_VALIDITY_CHECK)
	Optional<TsddrTDomanda> findByIdDomanda(@Param("idDomanda") Long idDomanda);
	
	@Query("SELECT trugp "
	        + "FROM TsddrTDomanda ttd "
            + "INNER JOIN ttd.gestore ttg "
            + "INNER JOIN ttg.rUtentiGestoriProfili trugp "
            + "INNER JOIN trugp.profilo tdp "
            + "INNER JOIN tdp.tipoProfilo tdtp "
            + "WHERE trugp.id.idUtente = :idUtente AND "
            + "trugp.id.idGestore = :idGestore AND "
            + "ttd.idDomanda = :idDomanda AND "
            + "tdtp.idTipoProfilo = :idTipoProfilo AND "
            + "tdp.idProfilo = :idProfilo AND "
            + RepositoryUtil.TRUGP_RUTENTEGESTOREPROFILO_VALIDITY_CHECK)
	Optional<TsddrRUtenteGestoreProfilo> findByIdDomandaAndIdUtenteAndIdGestoreAndIdProfiloAndIdTipoProfilo(@Param("idDomanda") Long idDomanda, @Param("idUtente") Long idUtente, @Param("idGestore") Long idGestore, @Param("idProfilo") Long idProfilo, @Param("idTipoProfilo") Long idTipoProfilo, @Param("currentDate") Date currentDate);
	
}
