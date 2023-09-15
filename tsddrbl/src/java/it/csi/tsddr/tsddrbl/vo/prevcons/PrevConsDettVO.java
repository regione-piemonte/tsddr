/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.math.BigDecimal;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.unitamisura.UnitaMisuraVO;

public class PrevConsDettVO extends AbstractVO {

    private static final long serialVersionUID = 1L;

    private Long idPrevConsDett;
    private String destinazione;
    private BigDecimal quantita;
    private EerVO eer;
    private SezioneVO sezione;
    private UnitaMisuraVO unitaMisura;
    private String descMatUscita;
    private Long idPrevConsDettRmr;

    private BigDecimal quantitaRichiesta;
    private PrevConsDettVO prevConsDettRichiesta;
    private Boolean hasBeenUpdated;

    /**
     * Gets the id prev cons dett.
     *
     * @return the id prev cons dett
     */
    public Long getIdPrevConsDett() {
        return idPrevConsDett;
    }

    /**
     * Sets the id prev cons dett.
     *
     * @param idPrevConsDett the new id prev cons dett
     */
    public void setIdPrevConsDett(Long idPrevConsDett) {
        this.idPrevConsDett = idPrevConsDett;
    }

    /**
     * Gets the destinazione.
     *
     * @return the destinazione
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     * Sets the destinazione.
     *
     * @param destinazione the new destinazione
     */
    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    /**
     * Gets the quantita.
     *
     * @return the quantita
     */
    public BigDecimal getQuantita() {
        return quantita;
    }

    /**
     * Sets the quantita.
     *
     * @param quantita the new quantita
     */
    public void setQuantita(BigDecimal quantita) {
        this.quantita = quantita;
    }

    /**
     * Gets the eer.
     *
     * @return the eer
     */
    public EerVO getEer() {
        return eer;
    }

    /**
     * Sets the eer.
     *
     * @param eer the new eer
     */
    public void setEer(EerVO eer) {
        this.eer = eer;
    }

    /**
     * Gets the sezione.
     *
     * @return the sezione
     */
    public SezioneVO getSezione() {
        return sezione;
    }

    /**
     * Sets the sezione.
     *
     * @param sezione the new sezione
     */
    public void setSezione(SezioneVO sezione) {
        this.sezione = sezione;
    }

    /**
     * Gets the unita misura.
     *
     * @return the unita misura
     */
    public UnitaMisuraVO getUnitaMisura() {
        return unitaMisura;
    }

    /**
     * Sets the unita misura.
     *
     * @param unitaMisura the new unita misura
     */
    public void setUnitaMisura(UnitaMisuraVO unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    /**
     * Gets the desc mat uscita.
     *
     * @return the desc mat uscita
     */
    public String getDescMatUscita() {
        return descMatUscita;
    }

    /**
     * Sets the desc mat uscita.
     *
     * @param descMatUscita the new desc mat uscita
     */
    public void setDescMatUscita(String descMatUscita) {
        this.descMatUscita = descMatUscita;
    }

    /**
     * Gets the id prev cons dett rmr.
     *
     * @return the id prev cons dett rmr
     */
    public Long getIdPrevConsDettRmr() {
        return idPrevConsDettRmr;
    }

    /**
     * Sets the id prev cons dett rmr.
     *
     * @param idPrevConsDettRmr the new id prev cons dett rmr
     */
    public void setIdPrevConsDettRmr(Long idPrevConsDettRmr) {
        this.idPrevConsDettRmr = idPrevConsDettRmr;
    }
    
    /**
     * Gets the quantita richiesta.
     *
     * @return the quantita richiesta
     */
    public BigDecimal getQuantitaRichiesta() {
        return quantitaRichiesta;
    }

    /**
     * Sets the quantita richiesta.
     *
     * @param quantitaRichiesta the new quantita richiesta
     */
    public void setQuantitaRichiesta(BigDecimal quantitaRichiesta) {
        this.quantitaRichiesta = quantitaRichiesta;
    }

    /**
     * Gets the prev cons dett richiesta.
     *
     * @return the prev cons dett richiesta
     */
    public PrevConsDettVO getPrevConsDettRichiesta() {
        return prevConsDettRichiesta;
    }

    /**
     * Sets the prev cons dett richiesta.
     *
     * @param prevConsDettRichiesta the new prev cons dett richiesta
     */
    public void setPrevConsDettRichiesta(PrevConsDettVO prevConsDettRichiesta) {
        this.prevConsDettRichiesta = prevConsDettRichiesta;
    }

    /**
     * Gets the checks for been updated.
     *
     * @return the checks for been updated
     */
    public Boolean getHasBeenUpdated() {
        return hasBeenUpdated;
    }

    /**
     * Sets the checks for been updated.
     *
     * @param hasBeenUpdated the new checks for been updated
     */
    public void setHasBeenUpdated(Boolean hasBeenUpdated) {
        this.hasBeenUpdated = hasBeenUpdated;
    }

}
