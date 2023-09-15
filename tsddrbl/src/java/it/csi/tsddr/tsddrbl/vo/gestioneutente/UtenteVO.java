/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.gestioneutente;

import java.util.Date;
import java.util.List;

import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;

public class UtenteVO {

    private Long idUtente;
    private String codiceFiscale;
    private String cognome;
    private String nome;
    private List<GestoreVO> gestori;
    private ProfiloVO profilo;
    private Date dataInizioValidita;
    private Date dataFineValidita;

    /**
     * @return the idUtente
     */
    public Long getIdUtente() {
        return idUtente;
    }

    /**
     * @param idUtente the idUtente to set
     */
    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * @return the codiceFiscale
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * @param codiceFiscale the codiceFiscale to set
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the gestori
     */
    public List<GestoreVO> getGestori() {
        return gestori;
    }

    /**
     * @param gestori the gestori to set
     */
    public void setGestori(List<GestoreVO> gestori) {
        this.gestori = gestori;
    }

    /**
     * @return the profilo
     */
    public ProfiloVO getProfilo() {
        return profilo;
    }

    /**
     * @param profilo the profilo to set
     */
    public void setProfilo(ProfiloVO profilo) {
        this.profilo = profilo;
    }

    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita(Date dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita(Date dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

}
