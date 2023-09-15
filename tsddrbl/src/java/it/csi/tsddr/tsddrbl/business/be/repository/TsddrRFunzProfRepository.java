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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDFunzione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRFunzProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRFunzProfPK;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;

/**
 * The Interface TsddrRFunzProfRepository.
 */
@Repository
public interface TsddrRFunzProfRepository extends JpaRepository<TsddrRFunzProf, TsddrRFunzProfPK> {

	/**
	 * Find by id id profilo and id id funzione.
	 *
	 * @param idProfilo the id profilo
	 * @param idFunzione the id funzione
	 * @return the optional
	 */
	Optional<TsddrRFunzProf> findByIdIdProfiloAndIdIdFunzione(Long idProfilo, Long idFunzione);
	
	/**
	 * Find by id id profilo in.
	 *
	 * @param idProfili the id profili
	 * @return the list
	 */
	List<TsddrRFunzProf> findByIdIdProfiloIn(List<Long> idProfili);
	
	/**
	 * Find profilo funzionalita by id profilo.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT new it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO(tdp.idProfilo, tdf.idFunzione, tdf.descFunzione, trfp.isDelete, trfp.isInsert, trfp.isRead, trfp.isUpdate) "
			+ "FROM TsddrRFunzProf trfp "
			+ "INNER JOIN trfp.profilo tdp "
			+ "INNER JOIN trfp.funzione tdf "
			+ "WHERE tdp.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRFP_RFUNZPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDF_FUNZIONE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK)
	List<FunzionalitaProfiloVO> findProfiloFunzionalitaByIdProfilo(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Find profilo funzionalita by id profilo and id funzione.
	 *
	 * @param idProfilo the id profilo
	 * @param idFunzione the id funzione
	 * @param currentDate the current date
	 * @return the funzionalita profilo VO
	 */
	@Query("SELECT new it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO(tdp.idProfilo, tdf.idFunzione, tdf.descFunzione, trfp.isDelete, trfp.isInsert, trfp.isRead, trfp.isUpdate) "
			+ "FROM TsddrRFunzProf trfp "
			+ "INNER JOIN trfp.funzione tdf "
			+ "INNER JOIN trfp.profilo tdp "
			+ "WHERE tdp.idProfilo = :idProfilo AND "
			+ "tdf.idFunzione = :idFunzione AND "
			+ RepositoryUtil.TRFP_RFUNZPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDF_FUNZIONE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK)
	FunzionalitaProfiloVO findProfiloFunzionalitaByIdProfiloAndIdFunzione(@Param("idProfilo") Long idProfilo, 
			@Param("idFunzione") Long idFunzione, @Param("currentDate") Date currentDate);
	
	/**
	 * Find profilo funzionalita by id profilo and cod funzione.
	 *
	 * @param idProfilo the id profilo
	 * @param codFunzione the cod funzione
	 * @param currentDate the current date
	 * @return the funzionalita profilo VO
	 */
	@Query("SELECT new it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO(tdp.idProfilo, tdf.idFunzione, tdf.descFunzione, trfp.isDelete, trfp.isInsert, trfp.isRead, trfp.isUpdate) "
			+ "FROM TsddrRFunzProf trfp "
			+ "INNER JOIN trfp.funzione tdf "
			+ "INNER JOIN trfp.profilo tdp "
			+ "WHERE tdp.idProfilo = :idProfilo AND "
			+ "tdf.codFunzione = :codFunzione AND "
			+ RepositoryUtil.TRFP_RFUNZPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDF_FUNZIONE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK)
	FunzionalitaProfiloVO findProfiloFunzionalitaByIdProfiloAndCodFunzione(@Param("idProfilo") Long idProfilo, 
			@Param("codFunzione") String codFunzione, @Param("currentDate") Date currentDate);
	
	/**
	 * Find funzioni by id profilo.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdf "
			+ "FROM TsddrRFunzProf trfp "
			+ "INNER JOIN trfp.funzione tdf "
			+ "INNER JOIN trfp.profilo tdp "
			+ "WHERE tdp.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRFP_RFUNZPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDF_FUNZIONE_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
			+ "ORDER BY tdf.descFunzione ASC")
	List<TsddrDFunzione> findFunzioniByIdProfilo(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate);
	
	/**
	 * Find funzioni by id profilo not.
	 *
	 * @param idProfilo the id profilo
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdf "
		+ "FROM TsddrDFunzione tdf "
		+ "WHERE tdf NOT IN ( "
			+ "SELECT trfp.funzione.idFunzione "
			+ "FROM TsddrRFunzProf trfp "
			+ "INNER JOIN trfp.profilo tdp "
			+ "WHERE tdp.idProfilo = :idProfilo AND "
			+ RepositoryUtil.TRFP_RFUNZPROF_VALIDITY_CHECK
			+ "AND "
			+ RepositoryUtil.TDP_PROFILO_VALIDITY_CHECK
		+ ") AND "
		+ RepositoryUtil.TDF_FUNZIONE_VALIDITY_CHECK
		+ "ORDER BY tdf.descFunzione ASC")
	List<TsddrDFunzione> findFunzioniByIdProfiloNot(@Param("idProfilo") Long idProfilo, @Param("currentDate") Date currentDate); 
	
}
