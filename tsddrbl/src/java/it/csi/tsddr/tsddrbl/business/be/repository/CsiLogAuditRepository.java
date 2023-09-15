/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.CsiLogAudit;

/**
 * The interface Csi log audit repository.
 */
@Repository
public interface CsiLogAuditRepository extends BaseRepository<CsiLogAudit, Date> {

}