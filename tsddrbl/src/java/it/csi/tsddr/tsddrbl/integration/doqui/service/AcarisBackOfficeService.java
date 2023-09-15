/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;


import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType;

public interface AcarisBackOfficeService {
	
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param target
	 * @param dbKey
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType getIdentificatore(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String dbKey) throws IntegrationException;
	
	/**
	 * @param utenteActa
	 * @param repositoryId
	 * @return
	 * @throws IntegrationException
	 */
	public PrincipalIdType recuperaPrincipalId(UtenteActa utenteActa, ObjectIdType repositoryId) throws IntegrationException;
	
	/**
	 * @param idAoo
	 * @param repositoryId
	 * @param principalId
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType recuperaIdAOO(Integer idAoo, ObjectIdType repositoryId, PrincipalIdType  principalId) throws IntegrationException;
	
	/**
	 * @param idNodo
	 * @param repositoryId
	 * @param principalId
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType recuperaIdNodo(Integer idNodo, ObjectIdType repositoryId, PrincipalIdType  principalId) throws IntegrationException;
	
	/**
	 * @param idAoo
	 * @param repositoryId
	 * @param principalId
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType recuperaIdStruttura(Integer idAoo, ObjectIdType repositoryId, PrincipalIdType  principalId) throws IntegrationException;
	
	/**
	 * 
	 */
	public void init();
	
}
