/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;

import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;
import it.doqui.acta.actasrv.dto.acaris.type.common.AcarisContentStreamType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType;



public interface AcarisObjectService {	
	
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param target
	 * @param parolaChiave
	 * @param parentNodeId
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType getIdentificatoreTramiteParolaChiave(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String parolaChiave, ObjectIdType parentNodeId) throws IntegrationException;
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param parentId
	 * @param metadatiDB
	 * @param utenteActa
	 * @param vitalRecordCodeType
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType creaFascicolo(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentId, ParametroAcarisDTO metadatiDB, UtenteActa utenteActa, VitalRecordCodeType vitalRecordCodeType) throws IntegrationException;
	
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param parentId
	 * @param metadatiDB
	 * @param utenteActa
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType creaVolume(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentId, ParametroAcarisDTO metadatiDB, UtenteActa utenteActa) throws IntegrationException;
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param parentId
	 * @param metadatiDB
	 * @param utenteActa
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType creaDossier(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentId, ParametroAcarisDTO metadatiDB, UtenteActa utenteActa) throws IntegrationException;
	
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param target
	 * @param parolaChiave
	 * @return
	 * @throws IntegrationException
	 */
	public String getUUIDDocumento(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String parolaChiave) throws IntegrationException; 
	/**
	 * @param repositoryId
	 * @param documentoId
	 * @param principalId
	 * @return
	 * @throws IntegrationException
	 */
	public AcarisContentStreamType[] getContentStream(ObjectIdType repositoryId, ObjectIdType documentoId, PrincipalIdType principalId) throws IntegrationException;
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param idStatoDiEfficacia
	 * @return
	 * @throws IntegrationException
	 */
	public Integer getStatoDiEfficacia(ObjectIdType repositoryId, PrincipalIdType principalId, Integer idStatoDiEfficacia) throws IntegrationException;
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param utenteActa
	 * @return
	 * @throws IntegrationException
	 */
	public Integer getIdFormaDocumentaria(ObjectIdType repositoryId, PrincipalIdType principalId, UtenteActa utenteActa) throws IntegrationException;
	/**
	 * @param repositoryId
	 * @param objectId
	 * @param principalId
	 * @param oggetto
	 * @throws IntegrationException
	 */
	public void updatePropertiesDocumento(ObjectIdType repositoryId, ObjectIdType objectId, PrincipalIdType principalId, String oggetto) throws  IntegrationException;
	/**
	 * @param repositoryId
	 * @param objectIdClassificazione
	 * @param objectIdDocumento
	 * @param principalId
	 * @param numeroProtocollo
	 * @throws IntegrationException
	 */
	public void updatePropertiesOggettoDocumentoConProtocollo(ObjectIdType repositoryId, ObjectIdType objectIdClassificazione, ObjectIdType objectIdDocumento,PrincipalIdType principalId, String numeroProtocollo) throws  IntegrationException;	// 20200721_LC added "ConProtocollo"
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param objectIdClassificazione
	 * @return
	 * @throws IntegrationException
	 */
	public String getIdIndiceClassificazione(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectIdClassificazione) throws IntegrationException;
	
	/**
	 * 
	 */
	public void init();
	
	
	
	
	// 20200618_LC
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param associativeObjectId
	 * @param sourceFolderId
	 * @param destinationFolderId
	 * @param isConAllegatiOrProtocollato
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType moveActaDocument(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType associativeObjectId, ObjectIdType sourceFolderId, ObjectIdType destinationFolderId, boolean isConAllegatiOrProtocollato) throws IntegrationException;
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param target
	 * @param numProtocollo
	 * @param parentNodeId
	 * @return
	 * @throws IntegrationException
	 */
	public PagingResponseType getDocumentiTramiteProtocollo(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String numProtocollo, ObjectIdType parentNodeId) throws IntegrationException;
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param objectIdClassificazione
	 * @return
	 * @throws IntegrationException
	 */
	public String getIndiceClassificazioneEstesa(ObjectIdType repositoryId, PrincipalIdType principalId, String objectIdClassificazione) throws IntegrationException;
	
	// 20200708_LC
	/**
	 * @param repositoryId
	 * @param objectIdDocumento
	 * @param principalId
	 * @param newParolaChiave
	 * @throws IntegrationException
	 */
	public void updatePropertiesParolaChiaveDocumento(ObjectIdType repositoryId, ObjectIdType objectIdDocumento,PrincipalIdType principalId, String newParolaChiave) throws  IntegrationException;

	// 20200721_LC
	/**
	 * @param repositoryId
	 * @param objectIdDocumento
	 * @param principalId
	 * @param newOggetto
	 * @throws IntegrationException
	 */
	public void updatePropertiesOggettoDocumento(ObjectIdType repositoryId, ObjectIdType objectIdDocumento,PrincipalIdType principalId, String newOggetto) throws  IntegrationException;

	// 20200711_LC
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param objectIdDocumento
	 * @return
	 * @throws IntegrationException
	 */
	public String getParolaChiaveDocumento(ObjectIdType repositoryId, PrincipalIdType principalId , ObjectIdType objectIdDocumento) throws  IntegrationException;
	
	// 20200722_LC
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param target
	 * @param objectIdDocumento
	 * @return
	 * @throws IntegrationException
	 */
	public String getUUIDDocumentoByObjectIdDocumento(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String objectIdDocumento) throws IntegrationException;  

	// 20211019_LC
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param objectIdClassificazione
	 * @return
	 * @throws IntegrationException
	 */
	public Integer getNumeroAllegatiPresenti(ObjectIdType repositoryId, PrincipalIdType principalId, String objectIdClassificazione) throws  IntegrationException;
	
}
