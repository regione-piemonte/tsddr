/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametroAcaris;
import it.csi.tsddr.tsddrbl.business.be.mapper.entity.ParametroAcarisEntityMapper;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrCParametroAcarisRepository;
import it.csi.tsddr.tsddrbl.util.enums.ParametroAcaris;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisVO;

@Component
public class ParametroAcarisUtil {
    
    @Autowired
    private TsddrCParametroAcarisRepository tsddrCParametroAcarisRepository;
    
    @Autowired
    private ParametroAcarisEntityMapper parametroAcarisEntityMapper;
    
    private static Logger logger = Logger.getLogger(ParametroAcarisUtil.class);
    
    public ParametroAcarisDTO getParametroAcarisDTO() {
        ParametroAcarisDTO parametroAcarisDTO = ParametroAcarisDTO.getInstance();
        
        if(!parametroAcarisDTO.getIsValorized()) {
            List<TsddrCParametroAcaris> parametriAcaris = tsddrCParametroAcarisRepository.findParametroAcaris();
            List<ParametroAcarisVO> parametriAcarisVO = parametroAcarisEntityMapper.mapListEntityToListVO(parametriAcaris);
            
            for(ParametroAcarisVO parametroAcarisVO : parametriAcarisVO) {
                if(ParametroAcaris.ANNOTAZIONE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setAnnotazione(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.ANNOTAZIONE_FORMALE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                        parametroAcarisDTO.setAnnotazioneFormale(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.AOO_PROTOCOLLANTE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setaOOProtocollante(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.AOO_RESPONSABILE_DELLA_MATERIA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setaOOResponsabileMateria(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.APPLICA_ANNOTAZIONE_A_CLASSIFICAZIONE_CORRENTE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setApplicaAnnotazioneClassificazioneCorrente(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.APPLICA_ANNOTAZIONE_A_INTERO_DOCUMENTO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setApplicaAnnotazioneInteroDocumento(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.APPLICATIVO_ALIMENTANTE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setApplicativoAlimentante(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.ARCHIVIO_CORRENTE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setArchivioCorrente(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.AUTENTICATO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setAutenticato(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.AUTENTICATO_CON_FIRMA_AUTENTICA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setAutenticatoConFirmaAutentica(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.AUTENTICATO_COPIA_AUTENTICA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setAutenticatoCopiaAutentica(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.CARTACEO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setCartaceo(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.COMPOSIZIONE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setComposizione(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.CONSENTI_CREAZIONE_FASCICOLI.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setConsentiCreazioneFascicoli(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.CONSENTI_INSERIMENTO_DOCUMENTI.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setConsentiInserimentoDocumenti(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.CONSENTI_RICLASSIFICAZIONE_DOCUMENTI_NEL_DOSSIER.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setConsentiRiclassificazioneDocumentiDossier(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.CONSENTI_RICLASSIFICAZIONE_FASCICOLI_NEL_DOSSIER.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setConsentiRiclassificazioneFascicoliDossier(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.CONSERVAZIONE_CORRENTE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setConservazioneCorrente(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.CONSERVAZIONE_GENERALE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setConservazioneGenerale(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.COPIA_CARTACEA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setCopiaCartacea(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.DESTINATARI_INTERNI.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setDestinatariInterni(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.DESTINATARIO_GIURIDICO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setDestinatarioGiuridico(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.FORMA_DOCUMENTARIA_TIPOLOGIA_DOCUMENTALE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setFormaDocumentaria_TipologiaDocumentale(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.GRADO_DI_VITALITA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setGradoVitalita(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.MOLTEPLICITA_DELLA_COMPOSIZIONE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setMolteplicitaDellaComposizione(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.NODO_PROTOCOLLANTE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setNodoProtocollante(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.NODO_RESPONSABILE_DELLA_MATERIA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setNodoResponsabileMateria(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.ORIGINE_INTERNA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setOrigineInterna(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.PRESENZA_FILE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setPresenzaFile(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.PROTOCOLLISTA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setProtocollista(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.REGISTRAZIONE_RISERVATA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setRegistrazioneRiservata(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.RIMANDA_SBUSTAMENTO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setRimandaSbustamento(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.STATO_DEFINITIVO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setStatoDefinitivo(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.STATO_DI_EFFICACIA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setStatoEfficacia(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.STATO_MODIFICABILE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setStatoModificabile(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.STATO_REGISTRATO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setStatoRegistrato(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.STRUTTURA_PROTOCOLLANTE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setStrutturaProtocollante(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.STRUTTURA_RESPONSABILE_DELLA_MATERIA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setStrutturaResponsabileMateria(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.TIPO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setTipo(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.TIPO_DOCUMENTO.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setTipoDocumento(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.TIPOLOGIA.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setTipologia(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.TIPOLOGIA_DATI_PERSONALI.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setTipologiaDatiPersonali(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.TIPOLOGIA_DATI_RISERVATI.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setTipologiaDatiRiservati(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.TIPOLOGIA_DATI_SENSIBILI.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setTipologiaDatiSensibili(parametroAcarisVO.getValoreParamAcarisB());
                }else if(ParametroAcaris.UTENTE_DI_CREAZIONE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setUtenteCreazione(parametroAcarisVO.getValoreParamAcarisS());
                }else if(ParametroAcaris.VITAL_RECORD_CODE.getId().longValue()== parametroAcarisVO.getIdParametroAcaris().longValue()) {
                    parametroAcarisDTO.setVitalRecordCode(parametroAcarisVO.getValoreParamAcarisS());
                } else {  
                    logger.warn(String.format("Nessuna corrispondenza trovata per [%s]", parametroAcarisVO.getNomeParamAcaris())); 
                }
            }
            parametroAcarisDTO.setIsValorized(true);
        }
        return parametroAcarisDTO;
    }

}