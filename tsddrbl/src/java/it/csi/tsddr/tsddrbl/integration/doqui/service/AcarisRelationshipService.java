/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;

import java.util.List;

import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;


public interface AcarisRelationshipService 
{	
	// 20200824_LC gestione documento multiplo
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param documentoId
	 * @return
	 * @throws IntegrationException
	 */
	public List<ObjectIdType> recuperaIdContentStream(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType documentoId) throws IntegrationException;
	
	/**
	 * 
	 */
	public void init();
	
}
