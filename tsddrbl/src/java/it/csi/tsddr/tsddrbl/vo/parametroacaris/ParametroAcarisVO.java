/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.parametroacaris;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class ParametroAcarisVO extends AbstractVO {

    private static final long serialVersionUID = 1L;

    private Long idParametroAcaris;
    private String nomeParamAcaris;
    private Boolean valoreParamAcarisB;
    private Long valoreParamAcarisN;
    private String valoreParamAcarisS;

    /**
     * Gets the id parametro acaris.
     *
     * @return the id parametro acaris
     */
    public Long getIdParametroAcaris() {
        return idParametroAcaris;
    }

    /**
     * Sets the id parametro acaris.
     *
     * @param idParametroAcaris the new id parametro acaris
     */
    public void setIdParametroAcaris(Long idParametroAcaris) {
        this.idParametroAcaris = idParametroAcaris;
    }

    /**
     * Gets the nome param acaris.
     *
     * @return the nome param acaris
     */
    public String getNomeParamAcaris() {
        return nomeParamAcaris;
    }

    /**
     * Sets the nome param acaris.
     *
     * @param nomeParamAcaris the new nome param acaris
     */
    public void setNomeParamAcaris(String nomeParamAcaris) {
        this.nomeParamAcaris = nomeParamAcaris;
    }

    /**
     * Gets the valore param acaris B.
     *
     * @return the valore param acaris B
     */
    public Boolean getValoreParamAcarisB() {
        return valoreParamAcarisB;
    }

    /**
     * Sets the valore param acaris B.
     *
     * @param valoreParamAcarisB the new valore param acaris B
     */
    public void setValoreParamAcarisB(Boolean valoreParamAcarisB) {
        this.valoreParamAcarisB = valoreParamAcarisB;
    }

    /**
     * Gets the valore param acaris N.
     *
     * @return the valore param acaris N
     */
    public Long getValoreParamAcarisN() {
        return valoreParamAcarisN;
    }

    /**
     * Sets the valore param acaris N.
     *
     * @param valoreParamAcarisN the new valore param acaris N
     */
    public void setValoreParamAcarisN(Long valoreParamAcarisN) {
        this.valoreParamAcarisN = valoreParamAcarisN;
    }

    /**
     * Gets the valore param acaris S.
     *
     * @return the valore param acaris S
     */
    public String getValoreParamAcarisS() {
        return valoreParamAcarisS;
    }

    /**
     * Sets the valore param acaris S.
     *
     * @param valoreParamAcarisS the new valore param acaris S
     */
    public void setValoreParamAcarisS(String valoreParamAcarisS) {
        this.valoreParamAcarisS = valoreParamAcarisS;
    }

}
