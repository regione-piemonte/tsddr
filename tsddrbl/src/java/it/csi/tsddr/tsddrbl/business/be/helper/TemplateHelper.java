/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.helper;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametro;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrEmailTesti;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDomanda;
import it.csi.tsddr.tsddrbl.business.be.exception.RecordNotFoundException;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrCParametroRepository;
import it.csi.tsddr.tsddrbl.business.be.service.EmailTestiService;
import it.csi.tsddr.tsddrbl.util.DbConstants;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.Parametro;

@Component
public class TemplateHelper {
	
	private static Logger logger = Logger.getLogger(TemplateHelper.class);
	
	private static final String PARAMETRO_NOT_FOUND = "TsddrCParametro non trovato con nome_parametro = [%s]";
	private static final String ID_DOMANDA = "ID_DOMANDA";
	private static final String COGNOME = "Cognome";
	
	@Autowired
	private EmailTestiService emailTestiService;
	
	@Autowired
	private TsddrCParametroRepository parametroRepository;
	
	public String generaTestoValutazioneDomandaAccreditamento(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoValutazioneDomandaAccreditamento] BEGIN");
		
		TsddrEmailTesti datiEmail = emailTestiService.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_VALUTAZIONE);
		
		Optional<TsddrCParametro> parametro = parametroRepository.findByNomeParametro(Parametro.LINK_PRIVACY.getNome());
		if (parametro.isEmpty()) {
			throw new RecordNotFoundException(String.format(PARAMETRO_NOT_FOUND, Parametro.LINK_PRIVACY.getNome()));
		}
		
		TsddrTDatiSogg datiSogg = domanda.getDatiSogg();
		
		Map<String, String> params = Map.of("NOME", datiSogg.getNome(), "COGNOME", datiSogg.getCognome(),
				"CF_UTENTE_FO", datiSogg.getCodFiscale(), ID_DOMANDA, String.valueOf(domanda.getIdDomanda()),
				"RAG_SOCIALE", domanda.getGestore().getRagSociale(), "link", parametro.get().getValoreParametroS());
		
		String testo = StrSubstitutor.replace(datiEmail.getCorpo(), params, "<<", ">>");
		
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoValutazioneDomandaAccreditamento] END");
		
		return testo;
	}
	
	public String generaTestoRegistrazioneDomandaAccreditamento(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoRegistrazioneDomandaAccreditamento] BEGIN");
		
		TsddrEmailTesti datiEmail = emailTestiService.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_REGISTRAZIONE);
		
		Optional<TsddrCParametro> parametro = parametroRepository.findByNomeParametro(Parametro.LINK_PRIVACY.getNome());
		if (parametro.isEmpty()) {
			throw new RecordNotFoundException(String.format(PARAMETRO_NOT_FOUND, Parametro.LINK_PRIVACY.getNome()));
		}
		
		TsddrTDatiSogg datiSogg = domanda.getDatiSogg();
		
		Map<String, String> params = Map.of("Nome", datiSogg.getNome(), COGNOME, datiSogg.getCognome(), ID_DOMANDA,
				String.valueOf(domanda.getIdDomanda()), "link", parametro.get().getValoreParametroS());
		
		String testo = StrSubstitutor.replace(datiEmail.getCorpo(), params, "<<", ">>");
		
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoRegistrazioneDomandaAccreditamento] END");
		
		return testo;
	}
	
	public String generaTestoDomandaAccreditamentoAccettata(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoDomandaAccreditamentoAccettata] BEGIN");
		TsddrEmailTesti datiEmail = emailTestiService.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_ACCETTATA);
		Optional<TsddrCParametro> parametro = parametroRepository.findByNomeParametro(Parametro.LINK_PRIVACY.getNome());
		if (parametro.isEmpty()) {
			throw new RecordNotFoundException(String.format(PARAMETRO_NOT_FOUND, Parametro.LINK_PRIVACY.getNome()));
		}
		TsddrTDatiSogg datiSogg = domanda.getDatiSogg();
		Map<String, String> params = Map.of(
				"Nome", datiSogg.getNome(), 
				COGNOME, datiSogg.getCognome(), 
				ID_DOMANDA, String.valueOf(domanda.getIdDomanda()),
				"Note", domanda.getNotaLavorazione()!=null?domanda.getNotaLavorazione():"",
				"link", parametro.get().getValoreParametroS());
		String testo = StrSubstitutor.replace(datiEmail.getCorpo(), params, "<<", ">>");
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoDomandaAccreditamentoAccettata] END");
		return testo;
	}

	public String generaTestoDomandaAccreditamentoRifiutata(TsddrTDomanda domanda) {
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoDomandaAccreditamentoRifiutata] BEGIN");
		TsddrEmailTesti datiEmail = emailTestiService.getEmailTestiById(DbConstants.ID_EMAIL_DOMANDA_ACCREDITAMENTO_RIFIUTATA);
		Optional<TsddrCParametro> parametro = parametroRepository.findByNomeParametro(Parametro.LINK_PRIVACY.getNome());
		if (parametro.isEmpty()) {
			throw new RecordNotFoundException(String.format(PARAMETRO_NOT_FOUND, Parametro.LINK_PRIVACY.getNome()));
		}
		TsddrTDatiSogg datiSogg = domanda.getDatiSogg();
		Map<String, String> params = Map.of(
				"Nome", datiSogg.getNome(), 
				COGNOME, datiSogg.getCognome(), 
				ID_DOMANDA, String.valueOf(domanda.getIdDomanda()),
				"Note", domanda.getNotaLavorazione()!=null?domanda.getNotaLavorazione():"",
				"link", parametro.get().getValoreParametroS());
		String testo = StrSubstitutor.replace(datiEmail.getCorpo(), params, "<<", ">>");
		LoggerUtil.debug(logger, "[TemplateHelper::generaTestoDomandaAccreditamentoRifiutata] END");
		return testo;
	}

}
