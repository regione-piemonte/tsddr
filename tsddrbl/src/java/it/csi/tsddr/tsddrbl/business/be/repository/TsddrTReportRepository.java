/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTReport;

/**
 * The Interface TsddrTReportRepository.
 */
@Repository
public interface TsddrTReportRepository extends BaseRepository<TsddrTReport, Long> {

}
