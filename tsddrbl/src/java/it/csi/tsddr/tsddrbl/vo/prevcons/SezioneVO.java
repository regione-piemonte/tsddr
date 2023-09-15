/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class SezioneVO extends AbstractVO {

    private static final long serialVersionUID = 1L;

    private Long idSezione;
    private String descSezione;

    public Long getIdSezione() {
        return idSezione;
    }

    public void setIdSezione(Long idSezione) {
        this.idSezione = idSezione;
    }

    public String getDescSezione() {
        return descSezione;
    }

    public void setDescSezione(String descSezione) {
        this.descSezione = descSezione;
    }

}
