/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.List;
import java.util.stream.Collectors;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRImpiantoLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRPrevConsLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;
import it.csi.tsddr.tsddrbl.util.enums.StatoDichiarazione;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsBasicVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;

public class PrevConsUtility {
    
    public static PrevConsBasicVO mapEntityToBasicVO(TsddrTPrevCons entity, FunzionalitaProfiloVO acl, boolean ignoreDescrStatoDichiarazione) {
        TsddrTImpianto impianto = entity.getImpianto();
        TsddrTGestore gestore = impianto.getGestore();
        PrevConsBasicVO vo = new PrevConsBasicVO();
        vo.setIdPrevCons(entity.getIdPrevCons());
        vo.setAnnoTributo(entity.getAnnoTributo());
        vo.setDenominazione(impianto.getDenominazione());
        vo.setRagSociale(gestore.getRagSociale());
        vo.setDescrStatoDichiarazione(entity.getStatoDichiarazione().getDescrStatoDichiarazione());
        vo.setPrintable(StatoDichiarazione.INVIATA_PROTOCOLLATA.getId() == entity.getStatoDichiarazione().getIdStatoDichiarazione() && !entity.getPregresso());
//        if (ignoreDescrStatoDichiarazione) {
//            vo.setDescrStatoDichiarazione(null);
//        }
        StringBuilder sb = new StringBuilder();
        for(TsddrRPrevConsLinea prevConsLinea : entity.getPrevConsLinee()) {
            TsddrRImpiantoLinea impiantoLinea = prevConsLinea.getImpiantoLinea();
            if(impiantoLinea.getLinea() != null) {
                sb.append(impiantoLinea.getLinea().getCodLinea());
                sb.append("; ");
            } else if(impiantoLinea.getSottoLinea() != null) {
                sb.append(impiantoLinea.getSottoLinea().getCodSottoLinea());
                sb.append("; ");
            }
        }
        String lineeRichiesta = sb.toString();
        vo.setLineeRichiesta(lineeRichiesta != null ? lineeRichiesta.trim() : null);
        vo.setDataDoc(entity.getDataDoc());
        vo.setNumProtocollo(entity.getNumProtocollo());
        vo.setPregresso(entity.getPregresso());
        vo.setAnnullable((acl.getDelete() == Boolean.TRUE) && StatoDichiarazione.BOZZA.getDesc().toString().equals(vo.getDescrStatoDichiarazione()));
        return vo;
    }
   
    public static List<PrevConsBasicVO> mapListEntityToListBasicVO(List<TsddrTPrevCons> listEntity,
            FunzionalitaProfiloVO acl, boolean ignoreDescrStatoDichiarazione) {
        if (listEntity == null) {
            return null;
        }
        return listEntity.stream().map(i -> mapEntityToBasicVO(i, acl, ignoreDescrStatoDichiarazione))
                .collect(Collectors.toList());
    }
    
}
