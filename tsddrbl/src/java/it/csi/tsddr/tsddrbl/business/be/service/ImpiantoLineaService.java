/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import it.csi.tsddr.tsddrbl.vo.impiantolinee.ImpiantoLineeVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

public interface ImpiantoLineaService {

    GenericResponse<List<ImpiantoLineeVO>> getImpiantiLinee(Long idImpianto, Long idPrevConsRmr);

}
