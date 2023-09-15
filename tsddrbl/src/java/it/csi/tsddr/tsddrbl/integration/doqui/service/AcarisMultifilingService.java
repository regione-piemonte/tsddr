/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;

import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;

public interface AcarisMultifilingService 
{	
	
	/**
	 * 
	 */
	public void init();
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param sourceClassificazioneId
	 * @param destinationFolderId
	 * @param isOfflineRequest
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType aggiungiClassificazione(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType sourceClassificazioneId, ObjectIdType destinationFolderId, boolean isOfflineRequest) throws IntegrationException;

}
