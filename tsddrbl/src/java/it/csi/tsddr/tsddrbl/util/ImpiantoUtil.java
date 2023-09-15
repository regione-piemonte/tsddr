/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;
import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;

@Component
public class ImpiantoUtil {

    public List<GenericLineaVO> getGenericLineeVOfromLinee(List<TsddrRImpiantoLinea> impiantoLinee) {
        List<GenericLineaVO> genericLineeVO = new ArrayList<>();
        for(TsddrRImpiantoLinea impiantoLinea : impiantoLinee) {
            GenericLineaVO genericLineaVO = new GenericLineaVO();
            if(impiantoLinea.getSottoLinea() == null) {
                genericLineaVO.setIdLinea(impiantoLinea.getLinea().getIdLinea());
                genericLineaVO.setDescLinea(impiantoLinea.getLinea().getDescLinea());
                genericLineaVO.setCodLinea(impiantoLinea.getLinea().getCodLinea());
            } else {
                genericLineaVO.setIdSottoLinea(impiantoLinea.getSottoLinea().getIdSottoLinea());
                genericLineaVO.setDescSottoLinea(impiantoLinea.getSottoLinea().getDescSottoLinea());
                genericLineaVO.setCodSottoLinea(impiantoLinea.getSottoLinea().getCodSottoLinea());
            }
            genericLineaVO.setDataInizioValidita(impiantoLinea.getDataInizioValidita());
            genericLineaVO.setDataFineValidita(impiantoLinea.getDataFineValidita());
            genericLineeVO.add(genericLineaVO);
        }
        return genericLineeVO;
    }
}
