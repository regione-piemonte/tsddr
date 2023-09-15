/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;

public class PrevConsExtendedVO extends PrevConsVO {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "prevConsLinee")
    private List<PrevConsLineeExtendedVO> prevConsLineeExtended;
    private ImpiantoVO impianto;
    private PrevConsExtendedVO prevConsRichiesta;

    public List<PrevConsLineeExtendedVO> getPrevConsLineeExtended() {
        return prevConsLineeExtended;
    }

    public void setPrevConsLineeExtended(List<PrevConsLineeExtendedVO> prevConsLineeExtended) {
        this.prevConsLineeExtended = prevConsLineeExtended;
    }

    public ImpiantoVO getImpianto() {
        return impianto;
    }

    public void setImpianto(ImpiantoVO impianto) {
        this.impianto = impianto;
    }

    public PrevConsExtendedVO getPrevConsRichiesta() {
        return prevConsRichiesta;
    }

    public void setPrevConsRichiesta(PrevConsExtendedVO prevConsRichiesta) {
        this.prevConsRichiesta = prevConsRichiesta;
    }

}