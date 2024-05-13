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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;

/**
 * The Interface TsddrRImpiantoLineaRepository.
 */
@Repository
public interface TsddrRImpiantoLineaRepository extends JpaRepository<TsddrRImpiantoLinea, Long> {
	
	/**
	 * Find by id impianto.
	 *
	 * @param idImpianto the id impianto
	 * @return the list
	 */
	@Query("SELECT tril "
			+ "FROM TsddrRImpiantoLinea tril "
			+ "INNER JOIN tril.impianto tti "
			+ "WHERE tti.idImpianto = :idImpianto AND "
			+ RepositoryUtil.TRIL_RIMPIANTOLINEA_DELETE_VALIDITY_CHECK
			+ "ORDER BY tril.linea.idLinea ASC, tril.sottoLinea.idSottoLinea")
	List<TsddrRImpiantoLinea> findByIdImpianto(@Param("idImpianto") Long idImpianto);
	

	/**
	 * Find by id impianto.
	 *
	 * @param idImpianto the id impianto
	 * @return the list
	 */
	@Query("SELECT tril "
			+ "FROM TsddrRImpiantoLinea tril "
			+ "INNER JOIN tril.impianto tti "
			+ "WHERE tti.idImpianto = :idImpianto AND "
			+ RepositoryUtil.TRIL_RIMPIANTOLINEA_VALIDITY_CHECK
			+ "ORDER BY tril.linea.idLinea ASC, tril.sottoLinea.idSottoLinea")
	List<TsddrRImpiantoLinea> findByIdImpiantoAll(@Param("idImpianto") Long idImpianto);
	

	/**
	 * Find by id impianto.
	 *
	 * @param idImpianto the id impianto
	 * @return the list
	 */
	@Query("SELECT tril "
			+ "FROM TsddrRImpiantoLinea tril "
			+ "INNER JOIN tril.impianto tti "
			+ "WHERE tti.idImpianto = :idImpianto AND "
			+ RepositoryUtil.TRIL_RIMPIANTOLINEA_VALIDITY_CHECK
			+ "ORDER BY tril.linea.idLinea ASC, tril.sottoLinea.idSottoLinea")
	List<TsddrRImpiantoLinea> findByIdImpiantoAllValid(@Param("idImpianto") Long idImpianto, @Param("currentDate") Date currentDate);
	
	/**
	 * Find by id impianto and id prev cons rmr.
	 *
	 * @param idImpianto the id impianto
	 * @param IdPrevConsRmr the id prev cons rmr
	 * @return the list
	 */
	@Query("SELECT tril "
            + "FROM TsddrRImpiantoLinea tril "
            + "INNER JOIN tril.impianto tti "
            + "INNER JOIN tril.rPrevConsLinee trpcl "
            + "INNER JOIN trpcl.prevCons ttpc "
            + "WHERE tti.idImpianto = :idImpianto AND "
            + "ttpc.idPrevCons = :idPrevCons AND "
            + RepositoryUtil.TRIL_RIMPIANTOLINEA_DELETE_VALIDITY_CHECK
            + "ORDER BY tril.linea.idLinea ASC, tril.sottoLinea.idSottoLinea")
	List<TsddrRImpiantoLinea>findByIdImpiantoAndIdPrevCons(@Param("idImpianto") Long idImpianto, @Param("idPrevCons") Long idPrevCons);
	
	/**
	 * Find by id impianto and id linea.
	 *
	 * @param idImpianto the id impianto
	 * @param idLinea the id linea
	 * @return the optional
	 */
	@Query("SELECT tril "
			+ "FROM TsddrRImpiantoLinea tril "
			+ "INNER JOIN tril.impianto tti "
			+ "WHERE tti.idImpianto = :idImpianto AND "
			+ "tril.linea.idLinea = :idLinea AND "
			+ RepositoryUtil.TRIL_RIMPIANTOLINEA_DELETE_VALIDITY_CHECK)
	Optional<TsddrRImpiantoLinea> findByIdImpiantoAndIdLinea(@Param("idImpianto") Long idImpianto, @Param("idLinea") Long idLinea);
	
	/**
	 * Find by id impianto and id sotto linea.
	 *
	 * @param idImpianto the id impianto
	 * @param idSottoLinea the id sotto linea
	 * @return the optional
	 */
	@Query("SELECT tril "
			+ "FROM TsddrRImpiantoLinea tril "
			+ "INNER JOIN tril.impianto tti "
			+ "WHERE tti.idImpianto = :idImpianto AND "
			+ "tril.sottoLinea.idSottoLinea = :idSottoLinea AND "
			+ RepositoryUtil.TRIL_RIMPIANTOLINEA_DELETE_VALIDITY_CHECK)
	Optional<TsddrRImpiantoLinea> findByIdImpiantoAndIdSottoLinea(@Param("idImpianto") Long idImpianto, @Param("idSottoLinea") Long idSottoLinea);
	
    /**
     * Find by id impianto linea.
     *
     * @param idImpiantoLinea the id impianto linea
     * @return the optional
     */
    @Query("SELECT tril "
            + "FROM TsddrRImpiantoLinea tril "
            + "WHERE tril.idImpiantoLinea = :idImpiantoLinea AND "
            + RepositoryUtil.TRIL_RIMPIANTOLINEA_DELETE_VALIDITY_CHECK)
    Optional<TsddrRImpiantoLinea> findByIdImpiantoLinea(@Param("idImpiantoLinea") Long idImpiantoLinea);

}
