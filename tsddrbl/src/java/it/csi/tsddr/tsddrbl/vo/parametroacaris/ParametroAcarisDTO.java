/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.parametroacaris;

import java.util.Date;

public class ParametroAcarisDTO {

    private static ParametroAcarisDTO instance;
    
	private String annotazione;
	private Boolean annotazioneFormale;
	private String aOOProtocollante;
	private String aOOResponsabileMateria;
	private Boolean applicaAnnotazioneClassificazioneCorrente;
	private Boolean applicaAnnotazioneInteroDocumento;
	private String applicativoAlimentante;
	private Boolean archivioCorrente;
	private Boolean autenticato;
	private Boolean autenticatoConFirmaAutentica;
	private Boolean autenticatoCopiaAutentica;
	private Boolean cartaceo;
	private String composizione;
	private Boolean consentiCreazioneFascicoli;
	private Boolean consentiInserimentoDocumenti;
	private Boolean consentiRiclassificazioneDocumentiDossier;
	private Boolean consentiRiclassificazioneFascicoliDossier;
	private String conservazioneCorrente;
	private String conservazioneGenerale;
	private Boolean copiaCartacea;
	private String destinatariInterni;
	private String destinatarioGiuridico;
	private String formaDocumentaria_TipologiaDocumentale;
	private String gradoVitalita;
	private Boolean molteplicitaDellaComposizione;
	private String nodoProtocollante;
	private String nodoResponsabileMateria;
	private Boolean origineInterna;
	private Boolean presenzaFile;
	private String protocollista;
	private Boolean registrazioneRiservata;
	private String rimandaSbustamento;
	private Boolean statoDefinitivo;
	private String statoEfficacia;
	private Boolean statoModificabile;
	private Boolean statoRegistrato;
	private String strutturaProtocollante;
	private String strutturaResponsabileMateria;
	private String tipo;
	private String tipoDocumento;
	private Boolean tipologia;
	private Boolean tipologiaDatiPersonali;
	private Boolean tipologiaDatiRiservati;
	private Boolean tipologiaDatiSensibili;
	private String utenteCreazione;
	private String vitalRecordCode;
	
	// dati dichiarazione
	private String dichiarazioneAnno;
	private String dichiarazioneIdentificativo;
	private String dichiarazioneDenominazioneSocieta;
	private String dichiarazioneDescrizioneDocumento;
	private Date dichiarazioneData;
	
    
    private transient boolean isValorized;
    
    
    private transient boolean isRichiesta = false;
    

    /**
     * Instantiates a new parametro acaris DTO.
     */
    private ParametroAcarisDTO(){}
    
    /**
     * Gets the single instance of ParametroAcarisDTO.
     *
     * @return single instance of ParametroAcarisDTO
     */
    public static synchronized ParametroAcarisDTO getInstance() {
        if (instance == null) {
            instance = new ParametroAcarisDTO();
        }
        return instance;
    }
    
    public String getAnnotazione() {
		return annotazione;
	}

	public void setAnnotazione(String annotazione) {
		this.annotazione = annotazione;
	}

	public Boolean getAnnotazioneFormale() {
		return annotazioneFormale;
	}

	public void setAnnotazioneFormale(Boolean annotazioneFormale) {
		this.annotazioneFormale = annotazioneFormale;
	}

	public String getaOOProtocollante() {
		return aOOProtocollante;
	}

	public void setaOOProtocollante(String aOOProtocollante) {
		this.aOOProtocollante = aOOProtocollante;
	}

	public String getaOOResponsabileMateria() {
		return aOOResponsabileMateria;
	}

	public void setaOOResponsabileMateria(String aOOResponsabileMateria) {
		this.aOOResponsabileMateria = aOOResponsabileMateria;
	}

	public Boolean getApplicaAnnotazioneClassificazioneCorrente() {
		return applicaAnnotazioneClassificazioneCorrente;
	}

	public void setApplicaAnnotazioneClassificazioneCorrente(Boolean applicaAnnotazioneClassificazioneCorrente) {
		this.applicaAnnotazioneClassificazioneCorrente = applicaAnnotazioneClassificazioneCorrente;
	}

	public Boolean getApplicaAnnotazioneInteroDocumento() {
		return applicaAnnotazioneInteroDocumento;
	}

	public void setApplicaAnnotazioneInteroDocumento(Boolean applicaAnnotazioneInteroDocumento) {
		this.applicaAnnotazioneInteroDocumento = applicaAnnotazioneInteroDocumento;
	}

	public String getApplicativoAlimentante() {
		return applicativoAlimentante;
	}

	public void setApplicativoAlimentante(String applicativoAlimentante) {
		this.applicativoAlimentante = applicativoAlimentante;
	}

	public Boolean getArchivioCorrente() {
		return archivioCorrente;
	}

	public void setArchivioCorrente(Boolean archivioCorrente) {
		this.archivioCorrente = archivioCorrente;
	}

	public Boolean getAutenticato() {
		return autenticato;
	}

	public void setAutenticato(Boolean autenticato) {
		this.autenticato = autenticato;
	}

	public Boolean getAutenticatoConFirmaAutentica() {
		return autenticatoConFirmaAutentica;
	}

	public void setAutenticatoConFirmaAutentica(Boolean autenticatoConFirmaAutentica) {
		this.autenticatoConFirmaAutentica = autenticatoConFirmaAutentica;
	}

	public Boolean getAutenticatoCopiaAutentica() {
		return autenticatoCopiaAutentica;
	}

	public void setAutenticatoCopiaAutentica(Boolean autenticatoCopiaAutentica) {
		this.autenticatoCopiaAutentica = autenticatoCopiaAutentica;
	}

	public Boolean getCartaceo() {
		return cartaceo;
	}

	public void setCartaceo(Boolean cartaceo) {
		this.cartaceo = cartaceo;
	}

	public String getComposizione() {
		return composizione;
	}

	public void setComposizione(String composizione) {
		this.composizione = composizione;
	}

	public Boolean getConsentiCreazioneFascicoli() {
		return consentiCreazioneFascicoli;
	}

	public void setConsentiCreazioneFascicoli(Boolean consentiCreazioneFascicoli) {
		this.consentiCreazioneFascicoli = consentiCreazioneFascicoli;
	}

	public Boolean getConsentiInserimentoDocumenti() {
		return consentiInserimentoDocumenti;
	}

	public void setConsentiInserimentoDocumenti(Boolean consentiInserimentoDocumenti) {
		this.consentiInserimentoDocumenti = consentiInserimentoDocumenti;
	}

	public Boolean getConsentiRiclassificazioneDocumentiDossier() {
		return consentiRiclassificazioneDocumentiDossier;
	}

	public void setConsentiRiclassificazioneDocumentiDossier(Boolean consentiRiclassificazioneDocumentiDossier) {
		this.consentiRiclassificazioneDocumentiDossier = consentiRiclassificazioneDocumentiDossier;
	}

	public Boolean getConsentiRiclassificazioneFascicoliDossier() {
		return consentiRiclassificazioneFascicoliDossier;
	}

	public void setConsentiRiclassificazioneFascicoliDossier(Boolean consentiRiclassificazioneFascicoliDossier) {
		this.consentiRiclassificazioneFascicoliDossier = consentiRiclassificazioneFascicoliDossier;
	}

	public String getConservazioneCorrente() {
		return conservazioneCorrente;
	}

	public void setConservazioneCorrente(String conservazioneCorrente) {
		this.conservazioneCorrente = conservazioneCorrente;
	}

	public String getConservazioneGenerale() {
		return conservazioneGenerale;
	}

	public void setConservazioneGenerale(String conservazioneGenerale) {
		this.conservazioneGenerale = conservazioneGenerale;
	}

	public Boolean getCopiaCartacea() {
		return copiaCartacea;
	}

	public void setCopiaCartacea(Boolean copiaCartacea) {
		this.copiaCartacea = copiaCartacea;
	}

	public String getDestinatariInterni() {
		return destinatariInterni;
	}

	public void setDestinatariInterni(String destinatariInterni) {
		this.destinatariInterni = destinatariInterni;
	}

	public String getDestinatarioGiuridico() {
		return destinatarioGiuridico;
	}

	public void setDestinatarioGiuridico(String destinatarioGiuridico) {
		this.destinatarioGiuridico = destinatarioGiuridico;
	}

	public String getFormaDocumentaria_TipologiaDocumentale() {
		return formaDocumentaria_TipologiaDocumentale;
	}

	public void setFormaDocumentaria_TipologiaDocumentale(String formaDocumentaria_TipologiaDocumentale) {
		this.formaDocumentaria_TipologiaDocumentale = formaDocumentaria_TipologiaDocumentale;
	}

	public String getGradoVitalita() {
		return gradoVitalita;
	}

	public void setGradoVitalita(String gradoVitalita) {
		this.gradoVitalita = gradoVitalita;
	}

	public Boolean getMolteplicitaDellaComposizione() {
		return molteplicitaDellaComposizione;
	}

	public void setMolteplicitaDellaComposizione(Boolean molteplicitaDellaComposizione) {
		this.molteplicitaDellaComposizione = molteplicitaDellaComposizione;
	}

	public String getNodoProtocollante() {
		return nodoProtocollante;
	}

	public void setNodoProtocollante(String nodoProtocollante) {
		this.nodoProtocollante = nodoProtocollante;
	}

	public String getNodoResponsabileMateria() {
		return nodoResponsabileMateria;
	}

	public void setNodoResponsabileMateria(String nodoResponsabileMateria) {
		this.nodoResponsabileMateria = nodoResponsabileMateria;
	}

	public Boolean getOrigineInterna() {
		return origineInterna;
	}

	public void setOrigineInterna(Boolean origineInterna) {
		this.origineInterna = origineInterna;
	}

	public Boolean getPresenzaFile() {
		return presenzaFile;
	}

	public void setPresenzaFile(Boolean presenzaFile) {
		this.presenzaFile = presenzaFile;
	}

	public String getProtocollista() {
		return protocollista;
	}

	public void setProtocollista(String protocollista) {
		this.protocollista = protocollista;
	}

	public Boolean getRegistrazioneRiservata() {
		return registrazioneRiservata;
	}

	public void setRegistrazioneRiservata(Boolean registrazioneRiservata) {
		this.registrazioneRiservata = registrazioneRiservata;
	}

	public String getRimandaSbustamento() {
		return rimandaSbustamento;
	}

	public void setRimandaSbustamento(String rimandaSbustamento) {
		this.rimandaSbustamento = rimandaSbustamento;
	}

	public Boolean getStatoDefinitivo() {
		return statoDefinitivo;
	}

	public void setStatoDefinitivo(Boolean statoDefinitivo) {
		this.statoDefinitivo = statoDefinitivo;
	}

	public String getStatoEfficacia() {
		return statoEfficacia;
	}

	public void setStatoEfficacia(String statoEfficacia) {
		this.statoEfficacia = statoEfficacia;
	}

	public Boolean getStatoModificabile() {
		return statoModificabile;
	}

	public void setStatoModificabile(Boolean statoModificabile) {
		this.statoModificabile = statoModificabile;
	}

	public Boolean getStatoRegistrato() {
		return statoRegistrato;
	}

	public void setStatoRegistrato(Boolean statoRegistrato) {
		this.statoRegistrato = statoRegistrato;
	}

	public String getStrutturaProtocollante() {
		return strutturaProtocollante;
	}

	public void setStrutturaProtocollante(String strutturaProtocollante) {
		this.strutturaProtocollante = strutturaProtocollante;
	}

	public String getStrutturaResponsabileMateria() {
		return strutturaResponsabileMateria;
	}

	public void setStrutturaResponsabileMateria(String strutturaResponsabileMateria) {
		this.strutturaResponsabileMateria = strutturaResponsabileMateria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Boolean getTipologia() {
		return tipologia;
	}

	public void setTipologia(Boolean tipologia) {
		this.tipologia = tipologia;
	}

	public Boolean getTipologiaDatiPersonali() {
		return tipologiaDatiPersonali;
	}

	public void setTipologiaDatiPersonali(Boolean tipologiaDatiPersonali) {
		this.tipologiaDatiPersonali = tipologiaDatiPersonali;
	}

	public Boolean getTipologiaDatiRiservati() {
		return tipologiaDatiRiservati;
	}

	public void setTipologiaDatiRiservati(Boolean tipologiaDatiRiservati) {
		this.tipologiaDatiRiservati = tipologiaDatiRiservati;
	}

	public Boolean getTipologiaDatiSensibili() {
		return tipologiaDatiSensibili;
	}

	public void setTipologiaDatiSensibili(Boolean tipologiaDatiSensibili) {
		this.tipologiaDatiSensibili = tipologiaDatiSensibili;
	}

	public String getUtenteCreazione() {
		return utenteCreazione;
	}

	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

	public String getVitalRecordCode() {
		return vitalRecordCode;
	}

	public void setVitalRecordCode(String vitalRecordCode) {
		this.vitalRecordCode = vitalRecordCode;
	}

	public synchronized static void setInstance(ParametroAcarisDTO instance) {
		ParametroAcarisDTO.instance = instance;
	}

	/**
     * Gets the checks if is valorized.
     *
     * @return the checks if is valorized
     */
    public boolean getIsValorized() {
        return isValorized;
    }

    /**
     * Sets the checks if is valorized.
     *
     * @param isValorized the new checks if is valorized
     */
    public void setIsValorized(boolean isValorized) {
        this.isValorized = isValorized;
    }

	public String getDichiarazioneAnno() {
		return dichiarazioneAnno;
	}

	public void setDichiarazioneAnno(String dichiarazioneAnno) {
		this.dichiarazioneAnno = dichiarazioneAnno;
	}

	public String getDichiarazioneIdentificativo() {
		return dichiarazioneIdentificativo;
	}

	public void setDichiarazioneIdentificativo(String dichiarazioneIdentificativo) {
		this.dichiarazioneIdentificativo = dichiarazioneIdentificativo;
	}

	public String getDichiarazioneDenominazioneSocieta() {
		return dichiarazioneDenominazioneSocieta;
	}

	public void setDichiarazioneDenominazioneSocieta(String dichiarazioneDenominazioneSocieta) {
		this.dichiarazioneDenominazioneSocieta = dichiarazioneDenominazioneSocieta;
	}

	public String getDichiarazioneDescrizioneDocumento() {
		return dichiarazioneDescrizioneDocumento;
	}

	public void setDichiarazioneDescrizioneDocumento(String dichiarazioneDescrizioneDocumento) {
		this.dichiarazioneDescrizioneDocumento = dichiarazioneDescrizioneDocumento;
	}

	public Date getDichiarazioneData() {
		return dichiarazioneData;
	}

	public void setDichiarazioneData(Date dichiarazioneData) {
		this.dichiarazioneData = dichiarazioneData;
	}

	public boolean isRichiesta() {
		return isRichiesta;
	}

	public void setRichiesta(boolean isRichiesta) {
		this.isRichiesta = isRichiesta;
	}
    
}
