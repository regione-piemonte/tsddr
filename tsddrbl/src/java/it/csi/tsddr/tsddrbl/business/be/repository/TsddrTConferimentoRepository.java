/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTConferimento;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTRifiutoTariffa;

/**
 * The Interface TsddrTConferimentoRepository.
 */
@Repository
public interface TsddrTConferimentoRepository extends JpaRepository<TsddrTConferimento, Long> {

    @Modifying
    @Query("DELETE FROM TsddrTConferimento ttc WHERE "
			+ "ttc.dichAnnuale = :dichAnnuale AND "
            + "ttc.rifiutoTariffa = :rifiutoTariffa")
	void deleteByDichAnnualeAndRifiutoTariffa(@Param("dichAnnuale") TsddrTDichAnnuale dichAnnuale,@Param("rifiutoTariffa")  TsddrTRifiutoTariffa rifiutoTariffa);

}
