/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import javax.servlet.http.HttpSession;

/**
 * The Interface CsiLogAuditService.
 */
public interface CsiLogAuditService {

	/**
	 * Trace csi log audit.
	 *
	 * @param httpSession the http session
	 * @param utente the utente
	 * @param operazione the operazione
	 * @param oggOper the ogg oper
	 * @param keyOper the key oper
	 */
	void traceCsiLogAudit(HttpSession httpSession, String utente, String operazione, String oggOper, String keyOper);
	
	/**
	 * Trace csi log audit.
	 *
	 * @param httpSession the http session
	 * @param utente the utente
	 * @param operazione the operazione
	 * @param oggOper the ogg oper
	 * @param keyOper the key oper
	 */
	void traceCsiLogAudit(HttpSession httpSession, String utente, String operazione, String oggOper, Long keyOper);
	
	/**
	 * Trace csi log audit.
	 *
	 * @param httpSession the http session
	 * @param utente the utente
	 * @param operazione the operazione
	 * @param oggOper the ogg oper
	 * @param keyOper the key oper
	 */
	void traceCsiLogAudit(HttpSession httpSession, Long utente, String operazione, String oggOper, String keyOper);
	
	/**
	 * Trace csi log audit.
	 *
	 * @param httpSession the http session
	 * @param utente the utente
	 * @param operazione the operazione
	 * @param oggOper the ogg oper
	 * @param keyOper the key oper
	 */
	void traceCsiLogAudit(HttpSession httpSession, Long utente, String operazione, String oggOper, Long keyOper);
	
}
