/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;

import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;


public interface AcarisRepositoryService 
{	
	/**
	 * @param repositoryName
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType recuperaIdRepository(String repositoryName) throws IntegrationException;
	
	/**
	 * 
	 */
	public void init();
	
}
