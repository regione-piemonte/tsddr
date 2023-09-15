/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNazione;

/**
 * The Interface TsddrDNazioneRepository.
 */
@Repository
public interface TsddrDNazioneRepository extends BaseRepository<TsddrDNazione, Long> {
	
	/**
	 * Find nazioni.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdn "
			+ "FROM TsddrDNazione tdn "
			+ "WHERE "
			+ RepositoryUtil.TDN_NAZIONE_VALIDITY_CHECK
			+ "ORDER BY tdn.descNazione ASC")
	List<TsddrDNazione> findNazioni(@Param("currentDate") Date currentDate);
	
	/**
	 * Find by id nazione.
	 *
	 * @param idNazione the id nazione
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdn "
			+ "FROM TsddrDNazione tdn "
			+ "WHERE tdn.idNazione = :idNazione AND "
			+ RepositoryUtil.TDN_NAZIONE_VALIDITY_CHECK)
	Optional<TsddrDNazione> findByIdNazione(@Param("idNazione") Long idNazione, @Param("currentDate") Date currentDate);

	/**
	 * Find nazioni not equals desc nazione.
	 *
	 * @param descNazione the desc nazione
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdn "
			+ "FROM TsddrDNazione tdn "
			+ "WHERE UPPER( tdn.descNazione ) <> UPPER( :descNazione ) AND "
			+ RepositoryUtil.TDN_NAZIONE_VALIDITY_CHECK
			+ "ORDER BY tdn.descNazione ASC")
	List<TsddrDNazione> findNazioniNotEqualsDescNazione(@Param("descNazione") String descNazione, @Param("currentDate") Date currentDate);
	
	/**
	 * Find nazione by desc nazione.
	 *
	 * @param descNazione the desc nazione
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdn "
			+ "FROM TsddrDNazione tdn "
			+ "WHERE UPPER( tdn.descNazione ) = UPPER( :descNazione ) AND "
			+ RepositoryUtil.TDN_NAZIONE_VALIDITY_CHECK)
	Optional<TsddrDNazione> findNazioneByDescNazione(@Param("descNazione") String descNazione, @Param("currentDate") Date currentDate);
	
	/**
	 * Find nazioni not equals id istat nazione.
	 *
	 * @param idIstatNazione the id istat nazione
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdn "
			+ "FROM TsddrDNazione tdn "
			+ "WHERE tdn.idIstatNazione <> :idIstatNazione AND "
			+ RepositoryUtil.TDN_NAZIONE_VALIDITY_CHECK
			+ "ORDER BY tdn.descNazione ASC")
	List<TsddrDNazione> findNazioniNotEqualsIdIstatNazione(@Param("idIstatNazione") String idIstatNazione, @Param("currentDate") Date currentDate);
	
	/**
	 * Find nazione by id istat nazione.
	 *
	 * @param idIstatNazione the id istat nazione
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdn "
			+ "FROM TsddrDNazione tdn "
			+ "WHERE tdn.idIstatNazione = :idIstatNazione AND "
			+ RepositoryUtil.TDN_NAZIONE_VALIDITY_CHECK)
	Optional<TsddrDNazione> findNazioneByIdIstatNazione(@Param("idIstatNazione") String idIstatNazione, @Param("currentDate") Date currentDate);

}
