/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNazione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSedime;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;
import it.csi.tsddr.tsddrbl.business.be.exception.BadRequestException;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDComuneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDNazioneRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDProvinciaRepository;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrDSedimeRepository;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;

@Component
public class IndirizzoUtil {
    
    @SuppressWarnings("el-syntax")
    @Value("${id.istat.nazione.corrente:" + Constants.ID_ISTAT_NAZIONE_CORRENTE_DEFAULT + "}")
    private String idIstatNazioneCorrente;
    
    @Autowired
    private TsddrDSedimeRepository sedimeRepository;
    
    @Autowired
    private TsddrDComuneRepository comuneRepository;
    
    @Autowired
    private TsddrDProvinciaRepository provinciaRepository;
    
    @Autowired
    private TsddrDNazioneRepository nazioneRepository;
    
    public TsddrTIndirizzo verificaEValorizzaIndirizzo(TsddrTIndirizzo indirizzo, IndirizzoVO indirizzoVO, TsddrDTipoIndirizzo tipoIndirizzo) {
        Date currentDate = new Date();
        indirizzo.setTipoIndirizzo(tipoIndirizzo);
        indirizzo.setIndirizzo(indirizzoVO.getIndirizzo());
        indirizzo.setCap(indirizzoVO.getCap());
        
        checkSedime(indirizzo, indirizzoVO, currentDate);
        
        if(indirizzoVO.getComune() != null && indirizzoVO.getComune().getIdComune() != null) {
            Optional<TsddrDComune> comuneOpt = comuneRepository.findByIdComune(indirizzoVO.getComune().getIdComune(), currentDate);
            if(comuneOpt.isEmpty()) {
                throw new BadRequestException(String.format("Nessun TsddrDComune trovato con idComune = [%d]", indirizzoVO.getComune().getIdComune()));
            }
            indirizzo.setComune(comuneOpt.get());
            
            if(indirizzoVO.getComune().getProvincia() != null && indirizzoVO.getComune().getProvincia().getIdProvincia() != null) {
                Optional<TsddrDProvincia> provinciaOpt = provinciaRepository.findProvinciaById(indirizzoVO.getComune().getProvincia().getIdProvincia(), currentDate);
                if(provinciaOpt.isEmpty()) {
                    throw new BadRequestException(String.format("Nessun TsddrDProvincia trovato con idProvincia = [%d]", indirizzoVO.getComune().getProvincia().getIdProvincia()));
                }
                
                if(!provinciaOpt.get().getComuni().stream().anyMatch(p -> p.getIdComune().compareTo(comuneOpt.get().getIdComune()) == 0 )) {
                    throw new BadRequestException(String.format("La Provincia idProvincia = [%d] non contiene il Comune idComune = [%d]", indirizzoVO.getComune().getProvincia().getIdProvincia(), indirizzoVO.getComune().getIdComune())); 
                }
                indirizzo.getComune().setProvincia(provinciaOpt.get());
            } else {
                indirizzo.getComune().setProvincia(null);
            }
            
        } else {
            indirizzo.setComune(null);
        }
        // nazione è impostata di default
        checkNazione(indirizzo, currentDate);
        
        return indirizzo;
    }
    
    private void checkSedime(TsddrTIndirizzo indirizzo, IndirizzoVO indirizzoVO, Date currentDate) {
        if(indirizzoVO.getSedime() != null && indirizzoVO.getSedime().getIdSedime() != null) {
            Optional<TsddrDSedime> sedimeOpt = sedimeRepository.findByIdSedime(indirizzoVO.getSedime().getIdSedime(), currentDate);
            if(sedimeOpt.isEmpty()) {
                throw new BadRequestException(String.format("Nessun TsddrDSedime trovato con idSedime = [%d]", indirizzoVO.getSedime().getIdSedime()));
            }
            indirizzo.setSedime(sedimeOpt.get());
        } else {
            indirizzo.setSedime(null);
        }
    }
    
    private void checkNazione(TsddrTIndirizzo indirizzo, Date currentDate) {
        Optional<TsddrDNazione> nazioneOpt = nazioneRepository.findNazioneByIdIstatNazione(idIstatNazioneCorrente, currentDate);
        if(nazioneOpt.isEmpty()) {
            throw new BadRequestException(String.format("Nessuna TsddrDNazione trovata con idIstatNazioneCorrente = [%s]", idIstatNazioneCorrente));
        }
        indirizzo.setNazione(nazioneOpt.get());
    }
    
}
