/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class EerVO extends AbstractVO {
    
    private static final long serialVersionUID = 1L;

    private Long idEer;
    private String codiceEer;
    private String descrizione;

    public Long getIdEer() {
        return idEer;
    }

    public void setIdEer(Long idEer) {
        this.idEer = idEer;
    }

    public String getCodiceEer() {
        return codiceEer;
    }

    public void setCodiceEer(String codiceEer) {
        this.codiceEer = codiceEer;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
