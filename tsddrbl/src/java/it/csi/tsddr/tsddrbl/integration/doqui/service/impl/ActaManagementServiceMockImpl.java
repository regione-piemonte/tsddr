/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoElettronicoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.KeyDocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.DateFormat;

public class ActaManagementServiceMockImpl extends ActaManagementServiceImpl {

	@Transactional(propagation=Propagation.REQUIRED)	
	public KeyDocumentoActa archiviaDocumentoFisico(DocumentoElettronicoActa documentoActa, UtenteActa utenteActa) throws IntegrationException {
		final String method = "archiviaDocumentoFisico";
		log.debug(method + ". BEGIN");

		try {

			log.info(method + ". MOCK CLASS");


			KeyDocumentoActa keyDocumentoActa = new KeyDocumentoActa(documentoActa.getIdDocumento());

			keyDocumentoActa.setNumeroProtocollo("mock-" + RandomStringUtils.randomNumeric(7)+"/"  + DateFormat.getCurrentYear());

			keyDocumentoActa.setUUIDDocumento("mock-"+ RandomStringUtils.randomAlphabetic(31));





			return keyDocumentoActa; 
		}
		finally{
			log.debug(method + ". END");
		}
	}





}
