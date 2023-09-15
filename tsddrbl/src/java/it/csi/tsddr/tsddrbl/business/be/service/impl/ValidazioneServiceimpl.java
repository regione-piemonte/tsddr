/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametro;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrCParametroRepository;
import it.csi.tsddr.tsddrbl.business.be.service.ValidazioneService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.OptionalUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.util.enums.Parametro;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class ValidazioneServiceimpl implements ValidazioneService {
	
	private static Logger logger = Logger.getLogger(ValidazioneServiceimpl.class);
	
	@Autowired
	private TsddrCParametroRepository tsddrCParametroRepository;
	
	@Autowired
	private MessaggioServiceImpl messaggiService;
	
//	private static final String eMailRegex = "^[\\w-\\.]+@([\\w-]"
//			+ "+\\.)+[\\w-]{2,4}$";

	@Override
	public GenericResponse<String> verificaCodiceFiscale(String codiceFiscale) {
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoCodiceFiscale] BEGIN");
		MessaggioVO messaggioVO = this.verificaFormatoCodiceFiscale(codiceFiscale);
		if(messaggioVO != null) {
			throw new FunctionalException(String.format("Codice fiscale = [%s] non valido", codiceFiscale), messaggioVO);
		}
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoCodiceFiscale] END");
		return response;
	}

	@Override
	public GenericResponse<String> verificaEmail(String email) {
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaUtenteFormatoEmail] BEGIN");
		MessaggioVO messaggioVO = this.verificaFormatoEmail(email);
		if(messaggioVO != null) {
			throw new FunctionalException(String.format("Email = [%s] non valida", email), messaggioVO);
		}
		GenericResponse<String> response = GenericResponse.ok();
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaUtenteFormatoEmail] END");
		return response;
	}
	
	@Override
	public MessaggioVO verificaFormatoCodiceFiscale(String codiceFiscale) {
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoCodiceFiscale] BEGIN");
		MessaggioVO messaggioVO = null;
		String patternToMatch = "AAAAAA##A##A###A";
		boolean isCodiceFiscaleValid = true;
		if(codiceFiscale.matches("[0-9]+")) {
			if(codiceFiscale.length() != 11) {
				isCodiceFiscaleValid = false;
			}
		} else if(patternToMatch.length() != codiceFiscale.length()) {
			isCodiceFiscaleValid = false;
		} else {
			isCodiceFiscaleValid = codiceFiscale.toUpperCase().matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
		}
		if(!isCodiceFiscaleValid) {
			messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E005.name());
		}
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoCodiceFiscale] END");
		return messaggioVO;
	}

	@Override
	public MessaggioVO verificaFormatoEmail(String email) {
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoEmail] BEGIN");
		MessaggioVO messaggioVO = null;
//		String emailRegex = eMailRegex;
		Optional<TsddrCParametro> tsddrCParametroOpt = tsddrCParametroRepository.findByNomeParametro(Parametro.VALORI_EMAIL_NON_VALIDI.getNome());
		TsddrCParametro tsddrCParametro = OptionalUtil.getContent(tsddrCParametroOpt);
		List<String> invalidWords = Arrays.asList(tsddrCParametro.getValoreParametroS().split(","));
		if (email==null || !EmailValidator.getInstance().isValid(email) || invalidWords.stream().anyMatch(s -> email.contains(s))) {
			messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E003.name());
		} 
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoEmail] END");
		return messaggioVO;
	}
	
	@Override
	public MessaggioVO verificaFormatoPec(String pec) {
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoPec] BEGIN");
		MessaggioVO messaggioVO = null;
//		String emailRegex = eMailRegex;
		Optional<TsddrCParametro> tsddrCParametroOpt = tsddrCParametroRepository.findByNomeParametro(Parametro.VALORI_EMAIL_NON_VALIDI.getNome());
		TsddrCParametro tsddrCParametro = OptionalUtil.getContent(tsddrCParametroOpt);
		List<String> invalidWords = Arrays.asList(tsddrCParametro.getValoreParametroS().split(","));
		if (!EmailValidator.getInstance().isValid(pec) || invalidWords.stream().noneMatch(s -> pec.contains(s))) {
			messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E009.name());
		} 
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaFormatoPec] END");
		return messaggioVO;
	}
	
	@Override
	public MessaggioVO verificaValiditaDate(Date dataInizioValidita, Date dataFineValidita) {
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaValiditaDate] BEGIN");
		MessaggioVO messaggioVO = null;
		if(dataFineValidita.before(dataInizioValidita)) {
			messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E002.name());
		}
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaValiditaDate] END");
		return messaggioVO;
	}
	
	@Override
	public MessaggioVO verificaIdDomandaRicerca(String idDomanda) {
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaIdDomandaRicerca] BEGIN");
		MessaggioVO messaggioVO = null;
		try { 
			Long.parseLong(idDomanda);
	    } catch(NumberFormatException e) {
	    	messaggioVO = messaggiService.getMessaggioByCodMsg(CodiceMessaggio.E001.name());
	    }
		LoggerUtil.debug(logger, "[ValidationServiceimpl::verificaIdDomandaRicerca] END");
		return messaggioVO;
	}

}
