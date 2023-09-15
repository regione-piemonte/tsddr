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

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoProvvedimento;

/**
 * The Interface TsddrDTipoProvvedimentoRepository.
 */
@Repository
public interface TsddrDTipoProvvedimentoRepository extends JpaRepository<TsddrDTipoProvvedimento, Long> {
	
	/**
	 * Find tipo provvedimento by id.
	 *
	 * @param idTipoProvvedimento the id tipo provvedimento
	 * @param currentDate the current date
	 * @return the optional
	 */
	@Query("SELECT tdtprovv "
			+ "FROM TsddrDTipoProvvedimento tdtprovv "
			+ "WHERE tdtprovv.idTipoProvvedimento = :idTipoProvvedimento AND "
			+ RepositoryUtil.TDTPROVV_TIPO_PROVVEDIMENTO_VALIDITY_CHECK)
	public Optional<TsddrDTipoProvvedimento> findTipoProvvedimentoById(@Param("idTipoProvvedimento") Long idTipoProvvedimento, @Param("currentDate") Date currentDate);
	
	/**
	 * Find tipi provvedimento.
	 *
	 * @param currentDate the current date
	 * @return the list
	 */
	@Query("SELECT tdtprovv "
			+ "FROM TsddrDTipoProvvedimento tdtprovv "
			+ "WHERE "
			+ RepositoryUtil.TDTPROVV_TIPO_PROVVEDIMENTO_VALIDITY_CHECK
			+ "ORDER BY tdtprovv.descTipoProvvedimento ASC")
	public List<TsddrDTipoProvvedimento> findTipiProvvedimento(@Param("currentDate") Date currentDate);

}
