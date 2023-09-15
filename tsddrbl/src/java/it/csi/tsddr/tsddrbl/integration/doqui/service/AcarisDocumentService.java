/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;


import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoElettronicoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.document.IdentificatoreDocumento;
import it.doqui.acta.actasrv.dto.acaris.type.document.IdentificazioneTrasformazione;
import it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType;

public interface AcarisDocumentService 
{	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param folderId
	 * @param vitalRecordCodeType
	 * @param idFormaDocumentaria
	 * @param idStatoDiEfficacia
	 * @param documentoActa
	 * @return
	 * @throws IntegrationException
	 */
	public IdentificatoreDocumento creaDocumentoSoloMetadati(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType folderId, VitalRecordCodeType vitalRecordCodeType, Integer idFormaDocumentaria, Integer idStatoDiEfficacia, DocumentoActa documentoActa) throws IntegrationException;
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param documentoElettronicoActa
	 * @param idStatoDiEfficacia
	 * @return
	 * @throws IntegrationException
	 */
	public IdentificazioneTrasformazione[] trasformaDocumentoPlaceholderInDocumentoElettronico(ObjectIdType repositoryId, PrincipalIdType principalId, DocumentoElettronicoActa documentoElettronicoActa, int idStatoDiEfficacia) throws IntegrationException;
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param folderId
	 * @param vitalRecordCodeType
	 * @param idStatoDiEfficacia
	 * @param idFormaDocumentaria
	 * @param numeroProtocolloPadre
	 * @param pkAllegato
	 * @param documentoElettronicoActa
	 * @param isProtocollazioneInUscitaSenzaDocumento
	 * @param metadatiDB
	 * @return
	 * @throws IntegrationException
	 */
	public IdentificatoreDocumento creaDocumentoElettronico(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType folderId, VitalRecordCodeType vitalRecordCodeType, Integer idStatoDiEfficacia, Integer idFormaDocumentaria, String numeroProtocolloPadre, String pkAllegato, DocumentoElettronicoActa documentoElettronicoActa,boolean isProtocollazioneInUscitaSenzaDocumento, ParametroAcarisDTO metadatiDB) throws IntegrationException;
	
	/**
	 * 
	 */
	public void init();
	
}
