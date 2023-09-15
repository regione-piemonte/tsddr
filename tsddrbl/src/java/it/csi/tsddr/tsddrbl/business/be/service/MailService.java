/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.concurrent.CompletableFuture;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDomanda;

/**
 * The Interface MailService.
 */
public interface MailService {
	
	/**
	 * Send email domanda accreditamento valutazione.
	 *
	 * @param domanda the domanda
	 * @return the completable future
	 */
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoValutazione(TsddrTDomanda domanda);
	
	/**
	 * Send email domanda accreditamento registrazione.
	 *
	 * @param domanda the domanda
	 * @return the completable future
	 */
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoRegistrazione(TsddrTDomanda domanda);
	
	/**
	 * Send email domanda accreditamento rifutata.
	 *
	 * @param domanda the domanda
	 * @return the completable future
	 */
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoRifutata(TsddrTDomanda domanda);
	
	/**
	 * Send email domanda accreditamento accettata.
	 *
	 * @param domanda the domanda
	 * @return the completable future
	 */
	public CompletableFuture<Void> sendEmailDomandaAccreditamentoAccettata(TsddrTDomanda domanda);

}
