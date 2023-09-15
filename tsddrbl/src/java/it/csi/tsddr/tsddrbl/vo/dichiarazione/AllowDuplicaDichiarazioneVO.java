/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

public class AllowDuplicaDichiarazioneVO extends ExistsDichiarazioneVO {

    private static final long serialVersionUID = 1L;

    @QueryParam("versione")
    @NotNull
    private Long versione;

    /**
     * Gets the versione.
     *
     * @return the versione
     */
    public Long getVersione() {
        return versione;
    }

    /**
     * Sets the versione.
     *
     * @param versione the new versione
     */
    public void setVersione(Long versione) {
        this.versione = versione;
    }

}
