/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.helper.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoProtocollatoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.Fruitore;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.FruitoreException;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.RicercaDocumentoException;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.RicercaDocumentoNoDocElettronicoException;
import it.csi.tsddr.tsddrbl.integration.doqui.helper.ManageRicercaDocumentoHelper;
import it.csi.tsddr.tsddrbl.integration.doqui.service.ActaManagementService;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.XmlSerializer;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;

@Service
public class ManageRicercaDocumentoHelperImpl extends CommonDocumentoHelperImpl implements ManageRicercaDocumentoHelper
{

	private static Logger log = Logger.getLogger(ManageRicercaDocumentoHelperImpl.class);	

	@Autowired
	private ActaManagementService	actaManagementService;


	@SuppressWarnings("unused")
	@Transactional(propagation=Propagation.REQUIRED)	
	public DocumentoProtocollatoVO ricercaDocumentoProtocollato(String numProtocollo) throws RicercaDocumentoException 
	{
		String method = "ricercaDocumentoProtocollato";
		log.debug(method + ". BEGIN");
		DocumentoProtocollatoVO response = null;
		boolean containsError = false;
		if(log.isDebugEnabled())
		{	
			log.debug(method + ". numProtocollo  = " + numProtocollo);
		}
		
		try
		{
			// validazioni 	
			if(StringUtils.isBlank(numProtocollo)) throw new RicercaDocumentoException("numProtocollo non presente");
		
			
			// recupero fruitore
			Fruitore docTFruitore = getFruitore();
			
			//POJO
			UtenteActa utenteActa = new UtenteActa();
			utenteActa.setCodiceFiscale(docTFruitore.getCfActa());
			utenteActa.setIdAoo(new Integer(docTFruitore.getIdAooActa()));
			utenteActa.setIdNodo(new Integer(docTFruitore.getIdNodoActa()));
			utenteActa.setIdStruttura(new Integer(docTFruitore.getIdStrutturaActa()));
			utenteActa.setApplicationKeyActa(docTFruitore.getAppKey());
			utenteActa.setRepositoryName(docTFruitore.getRepoName());
			

			if(log.isDebugEnabled())
			{
				log.debug(method + ". UtenteActa =\n " + XmlSerializer.objectToXml(utenteActa));
			}
			
			DocumentoProtocollatoActa documentoProtocollatoActa = actaManagementService.ricercaDocumentoProtocollato(numProtocollo, utenteActa);
			
			if(documentoProtocollatoActa!=null) {
				DocumentoProtocollatoVO docProtocollato = new DocumentoProtocollatoVO();
				docProtocollato.setFile(documentoProtocollatoActa.getStream());
				docProtocollato.setFilename(documentoProtocollatoActa.getNomeFile());
				docProtocollato.setObjectIdDocumento(documentoProtocollatoActa.getIdDocumento());
				docProtocollato.setClassificazione(documentoProtocollatoActa.getClassificazioneEstesa());
				docProtocollato.setClassificazioneId(documentoProtocollatoActa.getClassificazioneId());
				docProtocollato.setRegistrazioneId(documentoProtocollatoActa.getRegistrazioneId());	
				docProtocollato.setFolderId(documentoProtocollatoActa.getFolderId());	
				docProtocollato.setDataOraProtocollo(documentoProtocollatoActa.getDataProtocollo());
				
				if (documentoProtocollatoActa.getRegistrazioneId()!=null) {
					docProtocollato.setNumProtocollo(numProtocollo);
				}							
				response = docProtocollato;
			}
			
			return response;
			
		}
		catch (FruitoreException e)
		{
			containsError = true;
			log.error(method + ". FruitoreException: " + e);
			throw new RicercaDocumentoException(e.getMessage());
		} catch (IntegrationException e) 
		{
			containsError = true;
			log.error(method + ". IntegrationException: " + e);
			if(e.getItsOriginalException()!= null && e.getItsOriginalException() instanceof RicercaDocumentoNoDocElettronicoException) {
				throw new RicercaDocumentoException(e.getMessage(), "RicercaDocumentoNoDocElettronicoException", e.getItsOriginalException().getMessage());
			}
			throw new RicercaDocumentoException(e.getMessage());
		}
		catch (Exception e) 
		{
			containsError = true;
			log.error(method + ". Exception: " + e);
			throw new RicercaDocumentoException(e.getMessage());
		}
		finally
		{
			log.debug(method + ". END");
		}
	}

}


