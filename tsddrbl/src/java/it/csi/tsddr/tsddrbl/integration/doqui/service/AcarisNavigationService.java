/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;

import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;


public interface AcarisNavigationService 
{	
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param idFascicolo
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType recuperaFascicoloProtocollazione(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType idFascicolo) throws IntegrationException;
	/**
	 * 
	 */
	public void init();
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param objectId
	 * @param isGruppoAllegati
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectResponseType recuperaChildren(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectId, boolean isGruppoAllegati) throws IntegrationException;
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param objectId
	 * @param depth
	 * @return
	 * @throws IntegrationException
	 */
	public PagingResponseType recuperaDescendants(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectId, Integer depth) throws IntegrationException;			// 20200707_LC
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param objectId
	 * @return
	 * @throws IntegrationException
	 */
	public PagingResponseType recuperaAllChildrens(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectId) throws IntegrationException;							// 20200708_LC
}
