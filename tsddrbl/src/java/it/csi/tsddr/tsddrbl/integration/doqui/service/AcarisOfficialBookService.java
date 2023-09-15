/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service;


import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.IdentificazioneRegistrazione;

public interface AcarisOfficialBookService 
{	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param classificazionePartenza
	 * @param idStruttura
	 * @param idNodo
	 * @param idAOO
	 * @param documentoElettronicoActa
	 * @return
	 * @throws IntegrationException
	 */
	public IdentificazioneRegistrazione creaRegistrazioneInPartenzaDaDocumentoElettronicoEsistente(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType classificazionePartenza, ObjectIdType idStruttura, ObjectIdType idNodo, ObjectIdType idAOO, DocumentoActa documentoElettronicoActa) throws IntegrationException;
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param classificazionePartenza
	 * @param idStruttura
	 * @param idNodo
	 * @param idAOO
	 * @param documentoElettronicoActa
	 * @return
	 * @throws IntegrationException
	 */
	public IdentificazioneRegistrazione creaRegistrazioneInArrivoDaDocumentoElettronicoEsistente(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType classificazionePartenza, ObjectIdType idStruttura, ObjectIdType idNodo, ObjectIdType idAOO, DocumentoActa documentoElettronicoActa) throws IntegrationException;
	
	/**
	 * @param repositoryId
	 * @param principalId
	 * @param codiceRegistrazione
	 * @param annoRegistrazione
	 * @param idAoo
	 * @return
	 * @throws IntegrationException
	 */
	public ObjectIdType recuperaIdFascicoloProtocollazioneInEntrataAssociata(ObjectIdType repositoryId, PrincipalIdType principalId,String codiceRegistrazione, String annoRegistrazione, ObjectIdType idAoo) throws IntegrationException;
	
	/**
	 * 
	 */
	public void init();
}
