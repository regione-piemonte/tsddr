/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoElettronicoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoProtocollatoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.KeyDocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.RicercaDocumentoNoDocElettronicoException;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisBackOfficeService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisDocumentService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisManagementService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisMultifilingService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisNavigationService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisObjectService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisOfficialBookService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisRelationshipService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisRepositoryService;
import it.csi.tsddr.tsddrbl.integration.doqui.service.ActaManagementService;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.DateFormat;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;
import it.doqui.acta.actasrv.dto.acaris.type.common.AcarisContentStreamType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ValueType;
import it.doqui.acta.actasrv.dto.acaris.type.document.IdentificatoreDocumento;
import it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.IdentificazioneRegistrazione;

@Service
public class ActaManagementServiceImpl implements ActaManagementService {

	protected static Logger log = Logger.getLogger(ActaManagementServiceImpl.class);
	
	private static final String BEGIN = ". BEGIN";
	private static final String END = ". END";
	private static final String DOT_RESULT = ". result  = ";
	   private static final String DOT_ARROW = ". -> ";
	private static final String TIPO_STRUTTURA_NON_GESTITA = "Tipo struttura acta non gestita";

	@Autowired
	private AcarisRepositoryService 	acarisRepositoryService;
	@Autowired
	private AcarisBackOfficeService 	acarisBackOfficeService;
	@Autowired
	private AcarisObjectService 	  	acarisObjectService;
	@Autowired
	private AcarisManagementService 	acarisManagementService;
	@Autowired
	private AcarisDocumentService   	acarisDocumentService;
	@Autowired
	private AcarisOfficialBookService acarisOfficialBookService;
	@Autowired
	private AcarisRelationshipService acarisRelationshipService;
	@Autowired
	private AcarisNavigationService acarisNavigationService; 
	
	@Autowired
	private AcarisMultifilingService acarisMultifilingService; 



	@Value("${acta.enabled:false}")
	private boolean actaEnabled;

	public AcarisNavigationService getAcarisNavigationService() {
		return acarisNavigationService;
	}

	public void setAcarisNavigationService(
			AcarisNavigationService acarisNavigationService) {
		this.acarisNavigationService = acarisNavigationService;
	}

	public AcarisRelationshipService getAcarisRelationshipService()
	{
		return acarisRelationshipService;
	}

	public void setAcarisRelationshipService(
			AcarisRelationshipService acarisRelationshipService)
	{
		this.acarisRelationshipService = acarisRelationshipService;
	}

	public AcarisOfficialBookService getAcarisOfficialBookService()
	{
		return acarisOfficialBookService;
	}

	public void setAcarisOfficialBookService(AcarisOfficialBookService acarisOfficialBookService)
	{
		this.acarisOfficialBookService = acarisOfficialBookService;
	}

	public AcarisRepositoryService getAcarisRepositoryService() 
	{
		return acarisRepositoryService;
	}

	public void setAcarisRepositoryService(AcarisRepositoryService acarisRepositoryService)
	{
		this.acarisRepositoryService = acarisRepositoryService;
	}

	public AcarisBackOfficeService getAcarisBackOfficeService()
	{
		return acarisBackOfficeService;
	}

	public void setAcarisBackOfficeService(AcarisBackOfficeService acarisBackOfficeService) 
	{
		this.acarisBackOfficeService = acarisBackOfficeService;
	}

	public AcarisObjectService getAcarisObjectService() 
	{
		return acarisObjectService;
	}

	public void setAcarisObjectService(AcarisObjectService acarisObjectService) 
	{
		this.acarisObjectService = acarisObjectService;
	}

	public AcarisManagementService getAcarisManagementService()
	{
		return acarisManagementService;
	}

	public void setAcarisManagementService(AcarisManagementService acarisManagementService)
	{
		this.acarisManagementService = acarisManagementService;
	}

	public AcarisDocumentService getAcarisDocumentService()
	{
		return acarisDocumentService;
	}

	public void setAcarisDocumentService(
			AcarisDocumentService acarisDocumentService)
	{
		this.acarisDocumentService = acarisDocumentService;
	}


	// 20200728_LC
	public AcarisMultifilingService getAcarisMultifilingService()
	{
		return acarisMultifilingService;
	}

	public void setAcarisMultifilingService(
			AcarisMultifilingService acarisMultifilingService)
	{
		this.acarisMultifilingService = acarisMultifilingService;
	}	
	
	@PostConstruct
	public void init() {
		
		if(actaEnabled) {
			try{
				getAcarisRepositoryService().init();
				getAcarisBackOfficeService().init();
				getAcarisObjectService().init();
				getAcarisManagementService().init();
				getAcarisDocumentService().init();
				getAcarisOfficialBookService().init();
				getAcarisRelationshipService().init();
				getAcarisNavigationService().init();
				getAcarisMultifilingService().init();		// 20200728_LC
			}catch(Throwable t) {
				String method = "init";
				log.warn(method + ". Problem creating Acta service!");
			}
		}else {
			String method = "init";
			log.info(method + ". Acta disabled!");
		}
	}
	

	@Transactional(propagation=Propagation.REQUIRED)	
	public void associaDocumentoFisico(DocumentoElettronicoActa documentoElettronicoActa, UtenteActa utenteActa) throws IntegrationException 
	{
		String method = "associaDocumentoFisico";
		log.debug(method + BEGIN);
		if(log.isDebugEnabled())	
		{
			log.debug(method + ". documentoElettronicoActa.getNomeFile = " + documentoElettronicoActa.getNomeFile());

		}
		ObjectIdType repositoryId = null;
		PrincipalIdType  principalId = null;

		try
		{
			log.debug(method + ". Running Servizio recuperaIdRepository...");
			repositoryId = acarisRepositoryService.recuperaIdRepository(utenteActa.getRepositoryName());

			if (repositoryId != null)
				log.debug(method + ". repositoryId = " + repositoryId.hashCode());

			log.debug(method + ". Running Servizio recuperaPrincipalId...");
			principalId = acarisBackOfficeService.recuperaPrincipalId(utenteActa, repositoryId);
			if (principalId != null)
				log.debug(method + ". principalId = " + principalId.hashCode());

			Integer idStatoDiEfficacia = acarisObjectService.getStatoDiEfficacia(repositoryId, principalId, utenteActa.getIdStatoDiEfficacia());

			log.debug(method + ". Running Servizio trasformaDocumentoPlaceholderInDocumentoElettronico...");
			log.debug(method + ". idStatoDiEfficacia = " + idStatoDiEfficacia);

			acarisDocumentService.trasformaDocumentoPlaceholderInDocumentoElettronico(repositoryId, principalId, documentoElettronicoActa, idStatoDiEfficacia.intValue());
			
		}
		finally
		{
			log.info(method + END);
		}
	}
	private VitalRecordCodeType estratiVitalRecordCodeType(VitalRecordCodeType[] elencoVitalRecordCodeType, int idVitalRecordCode)
	{
		String method = "estratiVitalRecordCodeType";
		log.info(method + BEGIN);
		VitalRecordCodeType result = null;
		if(log.isDebugEnabled())	
			log.debug(method + ". idVitalRecordCode  = " + idVitalRecordCode);
		try
		{
			if (elencoVitalRecordCodeType != null)
			{
				for (int i=0; i< elencoVitalRecordCodeType.length; i++)
				{
					VitalRecordCodeType temp = elencoVitalRecordCodeType[i];
					if (temp.getIdVitalRecordCode().getValue() == idVitalRecordCode)
					{
						result = temp;
						break;
					}	
				}
			}
			return result;
		}
		finally
		{
			if(log.isDebugEnabled())	
				if (result != null)
					log.debug(method + DOT_RESULT + result.getIdVitalRecordCode().hashCode());
				else
					log.debug(method + DOT_RESULT + result);
			log.debug(method + END);
		}
	}
	
	
	private VitalRecordCodeType estratiVitalRecordCodeType(VitalRecordCodeType[] elencoVitalRecordCodeType, String desc)
	{
		String method = "estratiVitalRecordCodeType";
		log.info(method + BEGIN);
		VitalRecordCodeType result = null;
		if(log.isDebugEnabled())	
			log.debug(method + ". descrizione  = " + desc);
		try
		{
			if (elencoVitalRecordCodeType != null)
			{
				for (int i=0; i< elencoVitalRecordCodeType.length; i++)
				{
					VitalRecordCodeType temp = elencoVitalRecordCodeType[i];
					if (temp.getDescrizione().equalsIgnoreCase(desc))
					{
						result = temp;
						break;
					}	
				}
			}
			return result;
		}
		finally
		{
			if(log.isDebugEnabled())	
				if (result != null)
					log.debug(method + DOT_RESULT + result.getIdVitalRecordCode().hashCode());
				else
					log.debug(method + DOT_RESULT + result);
			log.debug(method + END);
		}
	}
	
	private KeyDocumentoActa ottengoKeyActa(DocumentoActa documentoActa, String UUIDDocumento, String indiceClassificazione, IdentificazioneRegistrazione identificazioneRegistrazione, IdentificatoreDocumento identificatoreDocumento, String idFolderCreated)
	{
		KeyDocumentoActa keyActa = new KeyDocumentoActa(documentoActa.getIdDocumento());

		if (UUIDDocumento != null){
			keyActa.setUUIDDocumento(UUIDDocumento);
		}
		
		keyActa.setIndiceClassificazione(indiceClassificazione);
		
		
		
		if (identificazioneRegistrazione != null) {
			keyActa.setNumeroProtocollo(identificazioneRegistrazione.getNumero()+"/"+String.valueOf(DateFormat.getCurrentYear()));
			keyActa.setRegistrazioneId(identificazioneRegistrazione.getRegistrazioneId().getValue());
			keyActa.setClassificazioneId(identificazioneRegistrazione.getClassificazioneId().getValue());
		}
		
		if (identificatoreDocumento != null) {
			keyActa.setObjectIdClassificazione(identificatoreDocumento.getObjectIdClassificazione().getValue());
			keyActa.setObjectIdDocumento(identificatoreDocumento.getObjectIdDocumento().getValue());
		}
		
		
		// 20201123_LC
		if (idFolderCreated != null) {
			keyActa.setIdFolderCreated(idFolderCreated);
		}

		return keyActa;
	}
	
	private void checkIntegrationException(DocumentoElettronicoActa documentoElettronicoActa, UtenteActa utenteActa) throws IntegrationException {
	    if(documentoElettronicoActa == null)
            throw new IntegrationException("Documento acta non valorizzato");
        
        if(utenteActa == null)
            throw new IntegrationException("Utente acta non valorizzato");
        
        if(documentoElettronicoActa.getTipoStrutturaFolder() == null)
            throw new IntegrationException("Tipo struttura acta folder non valorizzato");
        
        if(documentoElettronicoActa.getTipoStrutturaRoot() == null)
            throw new IntegrationException("Tipo struttura acta root non valorizzato");
        
	}

	@Transactional(propagation=Propagation.REQUIRED)	
	public KeyDocumentoActa protocollaDocumentoFisico(DocumentoElettronicoActa documentoElettronicoActa, 
			UtenteActa utenteActa,
			boolean isProtocollazioneInUscitaSenzaDocumento, 
			ParametroAcarisDTO metadatiDB) throws IntegrationException 
	{
		String method = "protocollaDocumentoFisico";
		log.debug(method + BEGIN);

		ObjectIdType repositoryId = null;
		PrincipalIdType  principalId = null;
		ObjectIdType rootId = null;
		VitalRecordCodeType[] elencoVitalRecordCodeType = null;
		IdentificatoreDocumento identificatoreDocumentoFisico = null;
		String UUIDDocumento = null;
		ObjectIdType idAOO = null;
		ObjectIdType idNodo = null;
		ObjectIdType idStruttura = null;
		IdentificazioneRegistrazione identificazioneRegistrazione = null;
		String idFolderToTrace = null;		 

		try {
			
		    checkIntegrationException(documentoElettronicoActa, utenteActa);
			
			//repositoryId
			repositoryId = acarisRepositoryService.recuperaIdRepository(utenteActa.getRepositoryName());
			
			//principalId
			principalId = acarisBackOfficeService.recuperaPrincipalId(utenteActa, repositoryId);
			

			//rootId // - TRISPE-W
			log.debug(method + ". recupera root folder Id ...");
			rootId = recuperaRootFolderId("TRISPE-W", repositoryId, principalId, DocumentoActa.TIPO_STRUTTURA_SERIE_DOSSIER);
			
			// TODO - verifico esistenza Dossier (<codice_fiscale> oppure <PIVA>), se non c'è lo creo
			
			ObjectIdType strutturaId = null;
			try {
				log.debug(method + ". Running Servizio recuperaStrutturaFolderId serie dossier...");
				strutturaId = recuperaStrutturaFolderId(metadatiDB.getDichiarazioneIdentificativo(), repositoryId, principalId, rootId, DocumentoActa.TIPO_STRUTTURA_DOSSIER);							
			}catch(IntegrationException ex) {
				ex.printStackTrace();
			}		
			
			if (strutturaId == null){					
				rootId = creaStruttura(repositoryId, principalId, rootId, metadatiDB, DocumentoActa.TIPO_STRUTTURA_DOSSIER, utenteActa, null);								
			}else {
				rootId = strutturaId;
			}
			
			//vitalRecordCodeType
			elencoVitalRecordCodeType = acarisManagementService.recuperaVitalRecordCode(repositoryId);
//			VitalRecordCodeType vitalRecordCodeType = estratiVitalRecordCodeType(elencoVitalRecordCodeType, utenteActa.getIdvitalrecordcodetype());

			//idStatoDiEfficacia
			log.debug(method + ". getStatoDiEfficacia...");
			Integer idStatoDiEfficacia = acarisObjectService.getStatoDiEfficacia(repositoryId, principalId, utenteActa.getIdStatoDiEfficacia());
			
			//idFormaDocumentaria
			//log.debug(method + ". Running Servizio getIdFormaDocumentaria...");
			Integer idFormaDocumentaria = null;
			
			//idAOO
			log.debug(method + ". Running Servizio recuperaIdAOO...");
			idAOO = acarisBackOfficeService.recuperaIdAOO(utenteActa.getIdAoo(), repositoryId, principalId);
			if (idAOO != null)
				log.debug(method + ". idAOO = " + idAOO.hashCode());
					
			
			// verifico se si tratta di una richiesta, che dovra essere salvata nel dossier
			if(metadatiDB.isRichiesta()) {
				strutturaId = rootId;
			}else {
				// verifico esistenza Fascicolo ("Anno tributo " +  <anno_tributo>), se non c'è lo creo
				strutturaId = null;
				log.debug(method + ". Running Servizio recuperaFascicoloAnnualeFolderIdFolderId...");
				strutturaId = recuperaStrutturaFolderId(metadatiDB.getDichiarazioneAnno(), repositoryId, principalId, rootId, documentoElettronicoActa.getTipoStrutturaFolder());							
								
				if (strutturaId == null){					
					strutturaId = creaStruttura(repositoryId, principalId, rootId, metadatiDB, DocumentoActa.TIPO_STRUTTURA_FASCICOLO, utenteActa, estratiVitalRecordCodeType(elencoVitalRecordCodeType, metadatiDB.getGradoVitalita()));			
					idFolderToTrace = strutturaId.getValue();								
				}
			}
			if (strutturaId != null)
				log.debug(method + ". strutturaId = " + strutturaId.hashCode());
			
			idFormaDocumentaria =acarisObjectService.getIdFormaDocumentaria(repositoryId, principalId, utenteActa);
			
			//acarisDocumentServiceStadoc.creaDocumentoElettronico
			// CREA DOCUMENTO ELETTRONICO
			
			identificatoreDocumentoFisico = acarisDocumentService.creaDocumentoElettronico(repositoryId, principalId, strutturaId, estratiVitalRecordCodeType(elencoVitalRecordCodeType, metadatiDB.getGradoVitalita()), idStatoDiEfficacia, idFormaDocumentaria, null, null, documentoElettronicoActa,isProtocollazioneInUscitaSenzaDocumento, metadatiDB);
			if (identificatoreDocumentoFisico != null) {
				log.debug(method + ". identificatoreDocumentoFisico = " + identificatoreDocumentoFisico.getObjectIdDocumento().hashCode());

				//UUIDDocumento
				log.debug(method + ". Running Servizio getUUIDDocumento...");
				
				//UUIDDocumento = getUUIDDocumento(documentoElettronicoActa.getIdDocumento() + (documentoElettronicoActa.getMetadatiActa().getDescrizioneTipoLettera()!=null?" - " + documentoElettronicoActa.getMetadatiActa().getDescrizioneTipoLettera():""), repositoryId, principalId);
	
	//			UUIDDocumento = getUUIDDocumentoByParolaChiave(documentoElettronicoActa.getIdDocumento(), repositoryId, principalId);
				//idNodo
				log.debug(method + ". Running Servizio recuperaIdNodo...");
				idNodo = acarisBackOfficeService.recuperaIdNodo(utenteActa.getIdNodo(), repositoryId, principalId);
				if (idNodo != null)
					log.debug(method + ". idNodo = " + idNodo.hashCode());
	
				//idStruttura
				log.debug(method + ". Running Servizio recuperaIdStruttura...");
				idStruttura = acarisBackOfficeService.recuperaIdStruttura(utenteActa.getIdStruttura(), repositoryId, principalId);
				if (idStruttura != null)
					log.debug(method + ". idStruttura = " + idStruttura.hashCode());
	
				//identificazioneRegistrazione
				log.debug(method + ". isMittente() = " + documentoElettronicoActa.getSoggettoActa().isMittente());
				if (documentoElettronicoActa.getSoggettoActa().isMittente())
				{
					//Arrivo (Mittente)
					identificazioneRegistrazione = acarisOfficialBookService.creaRegistrazioneInArrivoDaDocumentoElettronicoEsistente(repositoryId, principalId, identificatoreDocumentoFisico.getObjectIdClassificazione(), idStruttura, idNodo, idAOO, documentoElettronicoActa);
				}
				else
				{
					//Partenza (Destinatario)
					log.debug(method + ". Running Servizio creaRegistrazioneInPartenzaDaDocumentoElettronicoEsistente...");
					identificazioneRegistrazione = acarisOfficialBookService.creaRegistrazioneInPartenzaDaDocumentoElettronicoEsistente(repositoryId, principalId, identificatoreDocumentoFisico.getObjectIdClassificazione(), idStruttura, idNodo, idAOO, documentoElettronicoActa);
				}
				
				if (identificazioneRegistrazione != null){
					log.debug(method + ". identificazioneRegistrazione Numero = " + identificazioneRegistrazione.getNumero());
				}
				   
				return ottengoKeyActa(documentoElettronicoActa, UUIDDocumento, null, identificazioneRegistrazione, identificatoreDocumentoFisico, idFolderToTrace);
			}
			log.warn(method + "RETURNED NULL");
			return null;
		}
		finally
		{
			log.debug(method + END);
		}
	}
	
	private ObjectIdType recuperaRootFolderId(String parolaChiave, ObjectIdType repositoryId, PrincipalIdType  principalId, Integer tipoStruttura) throws IntegrationException {

		String method = "recuperaRootFolderId";
		log.debug(method + BEGIN);

		ObjectIdType folderId = null;
		QueryableObjectType target = null;
		
		try {
			
			if(tipoStruttura == null)
				throw new IntegrationException("tipo struttura is null");
			
			log.debug(method + ". tipoStruttura= " + tipoStruttura);
			
			if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_VOLUME){
				//q = getTarget(EnumObjectType.SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE.value());
				target = getTarget(EnumObjectType.SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE.value());
				
				//VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE
			}
			else if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_FASCICOLO){
				target = getTarget(EnumObjectType.SERIE_FASCICOLI_PROPERTIES_TYPE.value());
			}
			else if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_DOSSIER){
				//x conam � solo dossier
				target = getTarget(EnumObjectType.DOSSIER_PROPERTIES_TYPE.value());
			}
			else if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_SERIE_DOSSIER){
				//x conam � solo dossier
				target = getTarget(EnumObjectType.SERIE_DOSSIER_PROPERTIES_TYPE.value());
			}
			else{
				throw new IntegrationException(TIPO_STRUTTURA_NON_GESTITA);
			}
			
			folderId = acarisObjectService.getIdentificatoreTramiteParolaChiave(repositoryId, principalId, target, parolaChiave, null);
			
			if(folderId == null){
				throw new IntegrationException("Impossibile recuperare il parametro folderId");
			}
			
			if(log.isDebugEnabled()){
				log.debug(method + ". folderId= " + folderId.getValue());
			}
		}
		catch(Exception e){
			log.error(method + ". Si e' verificato un errore in fase di recuperoRootFolderId: " + e);
			throw new IntegrationException(e.getMessage(), e);
		}
		finally{
			log.debug(method + END);
		}
		return folderId;
	}	

	private ObjectIdType recuperaStrutturaFolderId(String parolaChiave, ObjectIdType repositoryId, PrincipalIdType  principalId, ObjectIdType rootFolderId, Integer tipoStruttura) throws IntegrationException{
		String method = "recuperaStrutturaFolderId";
		log.debug(method + BEGIN);
		ObjectIdType folderId = null;
		try{
			QueryableObjectType q = null;
			
			if(tipoStruttura == null)
				throw new IntegrationException("tipo struttura is null");
			
			log.debug(method + ". tipoStruttura= " + tipoStruttura);
			
			if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_VOLUME){
				q = getTarget(EnumObjectType.VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE.value());
			}
			else if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_FASCICOLO){
				q = getTarget(EnumObjectType.FASCICOLO_REALE_ANNUALE_PROPERTIES_TYPE.value());
			}
			else if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_DOSSIER){
				q = getTarget(EnumObjectType.DOSSIER_PROPERTIES_TYPE.value());
			}
			else{
				throw new IntegrationException(TIPO_STRUTTURA_NON_GESTITA);
			}
			folderId = acarisObjectService.getIdentificatoreTramiteParolaChiave(repositoryId, principalId, q, parolaChiave, rootFolderId);
			log.debug(method + ". FascicoloAnnualeFolderId = " + folderId);
		}
		catch(Exception e){
			log.error(method + ". Si e' verificato un errore in fase di recuperaStrutturaFolderId: " + e);
			throw new IntegrationException(e.getMessage(), e);
		}
		finally{
			log.debug(method + END);
		}
		return folderId;
	}
	

	private ObjectIdType creaStruttura(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentId, ParametroAcarisDTO metadatiDB, Integer tipoStruttura, UtenteActa utenteActa, VitalRecordCodeType vitalRecordCodeType) throws IntegrationException{
		String method = "creaStruttura";
		log.debug(method + BEGIN);
		ObjectIdType strutturaId = null;
		try{
			if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_VOLUME){
				log.debug(method + ". creazione volume");
				strutturaId = acarisObjectService.creaVolume(repositoryId, principalId, parentId, metadatiDB, utenteActa);
			}
			else if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_FASCICOLO){
				log.debug(method + ". creazione fascicolo");
				strutturaId = acarisObjectService.creaFascicolo(repositoryId, principalId, parentId, metadatiDB, utenteActa, vitalRecordCodeType);
			}
			else if(tipoStruttura.intValue() == DocumentoActa.TIPO_STRUTTURA_DOSSIER){
				log.debug(method + ". creazione dossier");
				strutturaId = acarisObjectService.creaDossier(repositoryId, principalId, parentId, metadatiDB, utenteActa);
			}
			else{
				throw new IntegrationException(TIPO_STRUTTURA_NON_GESTITA);
			}
			
		}
		finally{
			log.debug(method + END);
		}
		return strutturaId;
	}
	
	
	private String getUUIDDocumentoByParolaChiave(String parolaChiave, ObjectIdType repositoryId, PrincipalIdType  principalId) throws IntegrationException
	{
		String method = "getUUIDDocumento";
		log.debug(method + BEGIN);
		String UUIDDocumento = null;
		try
		{
			UUIDDocumento = acarisObjectService.getUUIDDocumento(repositoryId, principalId, getTarget(EnumObjectType.DOCUMENTO_SEMPLICE_PROPERTIES_TYPE.value()), parolaChiave);
			log.debug(method + ". getUUIDDocumento = " + UUIDDocumento);	

		}
		finally
		{
			log.debug(method + END);
		}
		return UUIDDocumento;
	}

	private QueryableObjectType getTarget(String val) {
		QueryableObjectType target = new QueryableObjectType();
		target.setObject(val);
		return target;
	}
	
	
	public DocumentoProtocollatoActa ricercaDocumentoProtocollato(String numProtocollo, UtenteActa utenteActa) throws IntegrationException
	{
		String method = "ricercaDocumentoProtocollato";
		log.debug(method + BEGIN);
		ObjectIdType repositoryId = null;
		PrincipalIdType  principalId = null;
		DocumentoProtocollatoActa documentoProtocollatoActa = null;
		ObjectIdType folderId = null;
		
		try
		{
			log.debug(method + ". Running Servizio recuperaIdRepository...");
			repositoryId = acarisRepositoryService.recuperaIdRepository(utenteActa.getRepositoryName());

			if (repositoryId != null)
				log.debug(method + ". repositoryId = " + repositoryId.hashCode());

			log.debug(method + ". Running Servizio recuperaPrincipalId...");
			principalId = acarisBackOfficeService.recuperaPrincipalId(utenteActa, repositoryId);
			if (principalId != null)
				log.debug(method + ". principalId = " + principalId.hashCode());
			
			PagingResponseType response = acarisObjectService.getDocumentiTramiteProtocollo(repositoryId, principalId, getTarget("ClassificazioniProtocollateView"), numProtocollo, null);
			if(response != null && response.getObjectsLength() > 0){
				
				for (int i = 0; i < response.getObjectsLength(); i++) {
					
					documentoProtocollatoActa = new DocumentoProtocollatoActa();
					//String objectIdClassificazione = null;
					String idClassificazione = null;
					String idRegistrazione = null;
					ObjectIdType objectIdClassificazione = new ObjectIdType();
					
					// dalle properties recupero dataProtocollo e objectIdClassificazione che mi serve per recuperare l'indice classificazione estesa
					PropertyType[] prop = response.getObjects()[i].getProperties();
					for (int j = 0; j < prop.length; j++) {
						PropertyType propertyType = prop[j];
						log.debug(method + DOT_ARROW + propertyType.getQueryName().getClassName());
						log.debug(method + DOT_ARROW + propertyType.getQueryName().getPropertyName());	
						log.debug(method + DOT_ARROW + propertyType.getValue());	
						if(propertyType.getQueryName().getPropertyName().equalsIgnoreCase("dataProtocollo")){
							ValueType vt = propertyType.getValue();
							documentoProtocollatoActa.setDataProtocollo(vt.getContent()[0]);
						}else if(propertyType.getQueryName().getPropertyName().equalsIgnoreCase("idClassificazione")) {	// 20200707_LC
							ValueType vt = propertyType.getValue();
							idClassificazione = vt.getContent()[0];
						}else if(propertyType.getQueryName().getPropertyName().equalsIgnoreCase("objectId")) {	// 20200707_LC
							ValueType vt = propertyType.getValue();
							idRegistrazione = vt.getContent()[0];
						}
					}


					//if(StringUtils.isNotBlank(objectIdClassificazione) ) {
					if(StringUtils.isNotBlank(idClassificazione) && StringUtils.isNotBlank(idRegistrazione) ) {
						//log.debug(method + ". objectIdClassificazione = " + objectIdClassificazione);
						log.debug(method + ". idClassificazione = " + idClassificazione);
						log.debug(method + ". idRegistrazione = " + idRegistrazione);
						
						// documentoProtocollatoActa.setClassificazioneId(objectIdClassificazione);
						documentoProtocollatoActa.setClassificazioneId(idClassificazione);		// 20200707_LC è questo quello che ci si aspetta
						documentoProtocollatoActa.setRegistrazioneId(idRegistrazione);		
						
						// String indiceClassificazioneEstesa = acarisObjectService.getIndiceClassificazioneEstesa(repositoryId, principalId, objectIdClassificazione);
						String indiceClassificazioneEstesa = acarisObjectService.getIndiceClassificazioneEstesa(repositoryId, principalId, idClassificazione);	// 20200707_LC
						documentoProtocollatoActa.setClassificazioneEstesa(indiceClassificazioneEstesa);
						log.debug(method + ". indiceClassificazioneEstesa = " + indiceClassificazioneEstesa);
					
						objectIdClassificazione.setValue(idClassificazione);			
						
						ObjectResponseType documentoProtocollato = acarisNavigationService.recuperaChildren(repositoryId, principalId, objectIdClassificazione, false);
											
						// recupera folder
						folderId = acarisNavigationService.recuperaFascicoloProtocollazione(repositoryId, principalId, objectIdClassificazione);
						documentoProtocollatoActa.setFolderId(folderId.getValue());		
						
						if(documentoProtocollato!=null) {
							ObjectIdType docProtocollatoObj = documentoProtocollato.getObjectId();
							if (docProtocollatoObj != null) {
								log.debug(method + ". documentoId Hash Code = " + docProtocollatoObj.hashCode());
								log.debug(method + ". documentoId Value     = " + docProtocollatoObj.getValue());						
					
								documentoProtocollatoActa.setIdDocumento(docProtocollatoObj.getValue());
								log.debug(method + ". Running Servizio recuperaIdContentStream...");
							
								// se 1 elemento allora doc standard, se >1 è doc multiplo
								List<ObjectIdType> contentStreamIdList = null;
								try {
									contentStreamIdList = acarisRelationshipService.recuperaIdContentStream(repositoryId, principalId, docProtocollatoObj);
								}catch(IntegrationException e) {
									log.warn(method + "recupera contentStreamId problem = ", e);
									throw new IntegrationException(e.getMessage(), new RicercaDocumentoNoDocElettronicoException(""));
								}
								if (contentStreamIdList != null)
								{
									log.debug(method + "contentStreamId Hash Code = " + contentStreamIdList.get(0).hashCode());
									log.debug(method + "contentStreamId Value     = " + contentStreamIdList.get(0).getValue());
					
								}

								if(contentStreamIdList != null && contentStreamIdList.size() > 0) {
									
									if (contentStreamIdList.size()==1) {
										// se size 1 c'è un solo doc fisico
										log.debug(method + "Running Servizio getContentStream...");
										AcarisContentStreamType[] contentStream = acarisObjectService.getContentStream(repositoryId, contentStreamIdList.get(0), principalId);	
										documentoProtocollatoActa.setNomeFile(contentStream[0].getFilename());
										try {
											documentoProtocollatoActa.setStream(IOUtils.toByteArray(contentStream[0].getStreamMTOM().getDataSource().getInputStream()));
										}catch(IOException e ) {

											log.debug(method + "Problem getting stream!");
										}
									} 
								}
								
							}
						}
					}
				}
		
			}

			return documentoProtocollatoActa;
		}
		finally
		{
			log.debug(method + END);
		}
	}

	
	
}
