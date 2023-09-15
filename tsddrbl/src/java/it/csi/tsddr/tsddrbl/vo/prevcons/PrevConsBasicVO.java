/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class PrevConsBasicVO extends AbstractVO {

    private Long idPrevCons;
    private Long annoTributo;
    private String ragSociale;
    private String denominazione;
    private String descrStatoDichiarazione;
    private String numProtocollo;
    private String lineeRichiesta;
    private Date dataDoc;

    private Boolean annullable;
    private Boolean printable;

    /**
     * Gets the id prev cons.
     *
     * @return the id prev cons
     */
    public Long getIdPrevCons() {
        return idPrevCons;
    }

    /**
     * Sets the id prev cons.
     *
     * @param idPrevCons the new id prev cons
     */
    public void setIdPrevCons(Long idPrevCons) {
        this.idPrevCons = idPrevCons;
    }

    /**
     * Gets the anno tributo.
     *
     * @return the anno tributo
     */
    public Long getAnnoTributo() {
        return annoTributo;
    }

    /**
     * Sets the anno tributo.
     *
     * @param annoTributo the new anno tributo
     */
    public void setAnnoTributo(Long annoTributo) {
        this.annoTributo = annoTributo;
    }

    /**
     * Gets the rag sociale.
     *
     * @return the rag sociale
     */
    public String getRagSociale() {
        return ragSociale;
    }

    /**
     * Sets the rag sociale.
     *
     * @param ragSociale the new rag sociale
     */
    public void setRagSociale(String ragSociale) {
        this.ragSociale = ragSociale;
    }

    /**
     * Gets the denominazione.
     *
     * @return the denominazione
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Sets the denominazione.
     *
     * @param denominazione the new denominazione
     */
    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    /**
     * Gets the descr stato dichiarazione.
     *
     * @return the descr stato dichiarazione
     */
    public String getDescrStatoDichiarazione() {
        return descrStatoDichiarazione;
    }

    /**
     * Sets the descr stato dichiarazione.
     *
     * @param descrStatoDichiarazione the new descr stato dichiarazione
     */
    public void setDescrStatoDichiarazione(String descrStatoDichiarazione) {
        this.descrStatoDichiarazione = descrStatoDichiarazione;
    }

    /**
     * Gets the num protocollo.
     *
     * @return the num protocollo
     */
    public String getNumProtocollo() {
        return numProtocollo;
    }

    /**
     * Sets the num protocollo.
     *
     * @param numProtocollo the new num protocollo
     */
    public void setNumProtocollo(String numProtocollo) {
        this.numProtocollo = numProtocollo;
    }
    
    /**
     * Gets the linee richiesta.
     *
     * @return the linee richiesta
     */
    public String getLineeRichiesta() {
        return lineeRichiesta;
    }

    /**
     * Sets the linee richiesta.
     *
     * @param lineeRichiesta the new linee richiesta
     */
    public void setLineeRichiesta(String lineeRichiesta) {
        this.lineeRichiesta = lineeRichiesta;
    }
    
    /**
     * Gets the data invio doc.
     *
     * @return the data invio doc
     */
    public Date getDataDoc() {
        return dataDoc;
    }

    /**
     * Sets the data invio doc.
     *
     * @param dataInvioDoc the new data invio doc
     */
    public void setDataDoc(Date dataDoc) {
        this.dataDoc = dataDoc;
    }

    /**
     * Gets the annullable.
     *
     * @return the annullable
     */
    public Boolean getAnnullable() {
        return annullable;
    }

    /**
     * Sets the annullable.
     *
     * @param annullable the new annullable
     */
    public void setAnnullable(Boolean annullable) {
        this.annullable = annullable;
    }

    /**
     * Gets the printable.
     *
     * @return the printable
     */
    public Boolean getPrintable() {
        return printable;
    }

    /**
     * Sets the printable.
     *
     * @param printable the new printable
     */
    public void setPrintable(Boolean printable) {
        this.printable = printable;
    }

}
