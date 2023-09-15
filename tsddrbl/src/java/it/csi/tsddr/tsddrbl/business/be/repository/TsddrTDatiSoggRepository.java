/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;

/**
 * The Interface TsddrTDatiSoggRepository.
 */
@Repository
public interface TsddrTDatiSoggRepository extends BaseRepository<TsddrTDatiSogg, Long> {

	/**
	 * Find by cod fiscale.
	 *
	 * @param codiceFiscale the codice fiscale
	 * @return the optional
	 */
	@Query("SELECT ttds "
			+ "FROM TsddrTDatiSogg ttds "
			+ "WHERE UPPER(ttds.codFiscale) = UPPER(:codiceFiscale) AND "
			+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK)
	Optional<TsddrTDatiSogg> findByCodFiscale(@Param("codiceFiscale") String codiceFiscale);
	
	/**
	 * Find by id.
	 *
	 * @param idDatiSogg the id dati sogg
	 * @return the list
	 */
	@Query("SELECT ttds "
			+ "FROM TsddrTDatiSogg ttds "
			+ "WHERE ttds.idDatiSogg IN ( :idDatiSogg ) AND "
			+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttds.cognome , ttds.nome")
	List<TsddrTDatiSogg> findById(@Param("idDatiSogg") List<Long> idDatiSogg);
	
	/**
	 * Find by id.
	 *
	 * @param idDatiSogg the id dati sogg
	 * @return the optional
	 */
	@Query("SELECT ttds "
			+ "FROM TsddrTDatiSogg ttds "
			+ "WHERE ttds.idDatiSogg = :idDatiSogg AND "
			+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK)
	Optional<TsddrTDatiSogg> findById(@Param("idDatiSogg") Long idDatiSogg);
	
	/**
	 * Find richiedenti accreditamento.
	 *
	 * @return the list
	 */
	@Query("SELECT DISTINCT ttds "
			+ "FROM TsddrTDatiSogg ttds "
			+ "INNER JOIN ttds.domande ttd "
			+ "WHERE "
			+ RepositoryUtil.TTDS_DATISOGG_DELETE_VALIDITY_CHECK
			+ "ORDER BY ttds.cognome , ttds.nome")
	List<TsddrTDatiSogg> findRichiedentiAccreditamento();
	
}
