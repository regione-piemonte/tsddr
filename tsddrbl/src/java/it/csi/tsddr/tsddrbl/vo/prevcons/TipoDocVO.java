/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class TipoDocVO extends AbstractVO {

    private Long idTipoDoc;
    private String descTipoDoc;

    public Long getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(Long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public String getDescTipoDoc() {
        return descTipoDoc;
    }

    public void setDescTipoDoc(String descTipoDoc) {
        this.descTipoDoc = descTipoDoc;
    }

}
