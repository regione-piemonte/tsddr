/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impiantolinee;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class ImpiantoLineeVO extends AbstractVO {

    private static final long serialVersionUID = 1L;

    private Long idImpiantoLinea;
    private Long idLinea;
    private Long idSottoLinea;
    private Long idImpianto;
    private String codLinea;
    private String codSottoLinea;
    private Date dataInizioValidita;
    private Date dataFineValidita;

    public Long getIdImpiantoLinea() {
        return idImpiantoLinea;
    }

    public void setIdImpiantoLinea(Long idImpiantoLinea) {
        this.idImpiantoLinea = idImpiantoLinea;
    }

    public Long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Long idLinea) {
        this.idLinea = idLinea;
    }

    public Long getIdSottoLinea() {
        return idSottoLinea;
    }

    public void setIdSottoLinea(Long idSottoLinea) {
        this.idSottoLinea = idSottoLinea;
    }

    public Long getIdImpianto() {
        return idImpianto;
    }

    public void setIdImpianto(Long idImpianto) {
        this.idImpianto = idImpianto;
    }

    public String getCodLinea() {
        return codLinea;
    }

    public void setCodLinea(String codLinea) {
        this.codLinea = codLinea;
    }

    public String getCodSottoLinea() {
        return codSottoLinea;
    }

    public void setCodSottoLinea(String codSottoLinea) {
        this.codSottoLinea = codSottoLinea;
    }

    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }

    public void setDataInizioValidita(Date dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    public void setDataFineValidita(Date dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

}
