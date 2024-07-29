/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;
import it.csi.tsddr.tsddrbl.vo.statodichiarazione.StatoDichiarazioneVO;

public class PrevConsVO extends AbstractVO {

    private static final long serialVersionUID = 1L;

    private Long idPrevCons;
    private Long annoTributo;
    private Date dataDoc;
    private String modalita;
    private String numProtDoc;
    private BigDecimal percRecupero;
    private BigDecimal percScarto;
    private TipoDocVO tipoDoc;
    private Date dataInvioDoc;
    private Long idPrevConsRMr;
    private String numProtocollo;
    private Date dataProtocollo;
    private StatoDichiarazioneVO statoDichiarazione;
    private List<PrevConsLineeVO> prevConsLinee;
    private IndirizzoVO indirizzo;
	private Boolean pregresso;
    
    private Boolean richiestaAmmissioneHasBeenUpdated;

    public Long getIdPrevCons() {
        return idPrevCons;
    }

    public void setIdPrevCons(Long idPrevCons) {
        this.idPrevCons = idPrevCons;
    }

    public Long getAnnoTributo() {
        return annoTributo;
    }

    public void setAnnoTributo(Long annoTributo) {
        this.annoTributo = annoTributo;
    }

    public Date getDataDoc() {
        return dataDoc;
    }

    public void setDataDoc(Date dataDoc) {
        this.dataDoc = dataDoc;
    }

    public String getModalita() {
        return modalita;
    }

    public void setModalita(String modalita) {
        this.modalita = modalita;
    }

    public BigDecimal getPercRecupero() {
        return percRecupero;
    }

    public void setPercRecupero(BigDecimal percRecupero) {
        this.percRecupero = percRecupero;
    }

    public BigDecimal getPercScarto() {
        return percScarto;
    }

    public void setPercScarto(BigDecimal percScarto) {
        this.percScarto = percScarto;
    }

    public TipoDocVO getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDocVO tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Date getDataInvioDoc() {
        return dataInvioDoc;
    }

    public void setDataInvioDoc(Date dataInvioDoc) {
        this.dataInvioDoc = dataInvioDoc;
    }

    public Long getIdPrevConsRMr() {
        return idPrevConsRMr;
    }

    public void setIdPrevConsRMr(Long idPrevConsRMr) {
        this.idPrevConsRMr = idPrevConsRMr;
    }

    public String getNumProtocollo() {
        return numProtocollo;
    }

    public void setNumProtocollo(String numProtocollo) {
        this.numProtocollo = numProtocollo;
    }

    public Date getDataProtocollo() {
        return dataProtocollo;
    }

    public void setDataProtocollo(Date dataProtocollo) {
        this.dataProtocollo = dataProtocollo;
    }

    public StatoDichiarazioneVO getStatoDichiarazione() {
        return statoDichiarazione;
    }

    public void setStatoDichiarazione(StatoDichiarazioneVO statoDichiarazione) {
        this.statoDichiarazione = statoDichiarazione;
    }

    public List<PrevConsLineeVO> getPrevConsLinee() {
        return prevConsLinee;
    }

    public void setPrevConsLinee(List<PrevConsLineeVO> prevConsLinee) {
        this.prevConsLinee = prevConsLinee;
    }

    public Boolean getRichiestaAmmissioneHasBeenUpdated() {
        return richiestaAmmissioneHasBeenUpdated;
    }

    public void setRichiestaAmmissioneHasBeenUpdated(Boolean richiestaAmmissioneHasBeenUpdated) {
        this.richiestaAmmissioneHasBeenUpdated = richiestaAmmissioneHasBeenUpdated;
    }

    public IndirizzoVO getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(IndirizzoVO indirizzo) {
        this.indirizzo = indirizzo;
    }

	public String getNumProtDoc() {
		return numProtDoc;
	}

	public void setNumProtDoc(String numProtDoc) {
		this.numProtDoc = numProtDoc;
	}

	public Boolean getPregresso() {
		return pregresso;
	}

	public void setPregresso(Boolean pregresso) {
		this.pregresso = pregresso;
	}
    
}