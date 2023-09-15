/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface ValidazioneService.
 */
public interface ValidazioneService {
	
	/**
	 * Verifica codice fiscale.
	 *
	 * @param codiceFiscale the codice fiscale
	 * @return the generic response
	 */
	GenericResponse<String> verificaCodiceFiscale(String codiceFiscale);
	
	/**
	 * Verifica email.
	 *
	 * @param email the email
	 * @return the generic response
	 */
	GenericResponse<String> verificaEmail(String email);
	
	/**
	 * Verifica formato codice fiscale.
	 *
	 * @param codiceFiscale the codice fiscale
	 * @return the messaggio VO
	 */
	MessaggioVO verificaFormatoCodiceFiscale(String codiceFiscale);
	
	/**
	 * Verifica formato email.
	 *
	 * @param email the email
	 * @return the messaggio VO
	 */
	MessaggioVO verificaFormatoEmail(String email);

	/**
	 * Verifica validita date.
	 *
	 * @param dataInizioValidita the data inizio validita
	 * @param dataFineValidita the data fine validita
	 * @return the messaggio VO
	 */
	MessaggioVO verificaValiditaDate(Date dataInizioValidita, Date dataFineValidita);

	/**
	 * Verifica id domanda ricerca.
	 *
	 * @param idDomanda the id domanda
	 * @return the messaggio VO
	 */
	MessaggioVO verificaIdDomandaRicerca(String idDomanda);

	/**
	 * Verifica formato pec.
	 *
	 * @param pec the pec
	 * @return the messaggio VO
	 */
	MessaggioVO verificaFormatoPec(String pec);

}
