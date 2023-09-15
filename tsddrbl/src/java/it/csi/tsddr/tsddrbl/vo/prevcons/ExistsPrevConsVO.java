/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

public class ExistsPrevConsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @QueryParam("idImpianto")
    @NotNull
    private Long idImpianto;
    @QueryParam("idGestore")
    @NotNull
    private Long idGestore;
    @QueryParam("annoTributo")
    @NotNull
    private Long annoTributo;
    @QueryParam("idTipoDoc")
    @NotNull
    private Long idTipoDoc;

    public Long getIdImpianto() {
        return idImpianto;
    }

    public void setIdImpianto(Long idImpianto) {
        this.idImpianto = idImpianto;
    }

    public Long getIdGestore() {
        return idGestore;
    }

    public void setIdGestore(Long idGestore) {
        this.idGestore = idGestore;
    }

    public Long getAnnoTributo() {
        return annoTributo;
    }

    public void setAnnoTributo(Long annoTributo) {
        this.annoTributo = annoTributo;
    }

    public Long getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(Long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

}
