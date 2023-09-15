/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;

import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoElettronicoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoProtocollatoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.KeyDocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;

public interface ActaManagementService {
	
	
	/**
	 * 
	 */
	public void init();
	
	/**
	 * @param documentoElettronicoActa
	 * @param utenteActa
	 * @param isProtocollazioneInUscitaSenzaDocumento
	 * @param metadatiDB
	 * @return
	 * @throws IntegrationException
	 */
	public KeyDocumentoActa protocollaDocumentoFisico(DocumentoElettronicoActa documentoElettronicoActa, UtenteActa utenteActa,boolean isProtocollazioneInUscitaSenzaDocumento, ParametroAcarisDTO metadatiDB) throws IntegrationException;

	/**
	 * @param numProtocollo
	 * @param utenteActa
	 * @return
	 * @throws IntegrationException
	 */
	public DocumentoProtocollatoActa ricercaDocumentoProtocollato(String numProtocollo, UtenteActa utenteActa) throws IntegrationException;
	
} 
