/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service.impl;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.repository.TsddrCParametroRepository;
import it.csi.tsddr.tsddrbl.integration.doqui.DoquiServiceFactory;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisObjectService;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.XmlSerializer;
import it.csi.tsddr.tsddrbl.util.OptionalUtil;
import it.csi.tsddr.tsddrbl.util.enums.Parametro;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;
import it.doqui.acta.acaris.objectservice.AcarisException;
import it.doqui.acta.acaris.objectservice.ObjectServicePort;
import it.doqui.acta.actasrv.business.util.type.AcarisUtils;
import it.doqui.acta.actasrv.dto.acaris.type.archive.DossierPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumDossierStatoType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumFascicoloRealeStatoType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumFolderObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.FascicoloRealeAnnualePropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.MoveDocumentPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.VolumeSerieTipologicaDocumentiPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.common.AcarisContentStreamType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ChangeTokenType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumPropertyFilter;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumQueryOperator;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumStreamId;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdAOOType;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdNodoType;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdStrutturaType;
import it.doqui.acta.actasrv.dto.acaris.type.common.NavigationConditionInfoType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyFilterType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryConditionType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryNameType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ValueType;
import it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType;

@Service
public class AcarisObjectServiceImpl extends CommonManagementServiceImpl implements AcarisObjectService
{

//	public static final String LOGGER_PREFIX = DoquiConstants.LOGGER_PREFIX + ".integration";
//	private static Logger log = Logger.getLogger(LOGGER_PREFIX);	
	private static Logger log = Logger.getLogger(AcarisObjectServiceImpl.class);
	
	private static final String BEGIN = ".  BEGIN";
	private static final String DOT_EXCEPTION = ". Exception = ";
	private static final String DOT_PROPERTY = ". Property ";
	private static final String REPO_ID = ". repositoryId  = ";
	private static final String PRINCIPAL_ID = ". principalId   = ";
	private static final String PAROLE_CHIAVE = "paroleChiave";
	private static final String ACARIS_EXCEPTION_FAULTINFO_ERRORCODE = ". acEx.getFaultInfo().getErrorCode()     =  ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_PROPNAME = ". acEx.getFaultInfo().getPropertyName()  = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_OBJECTID = ". acEx.getFaultInfo().getObjectId()      = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE = ". acEx.getFaultInfo().getExceptionType() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_CLASSNAME = ". acEx.getFaultInfo().getClassName()     = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_TECHINFO = ". acEx.getFaultInfo().getTechnicalInfo() = ";
	private static final String DOC_SEMPLICE_PROPS_TYPE = "DocumentoSemplicePropertiesType";
	private static final String IMPOSSIBILE_RECUPERARE_UUID = "Impossibile recuperare UUID documento: ";
	private static final String IMPOSSIBILE_CREARE_FASCICOLO_ANNUALE = "Impossibile creare fascicolo annuale ";
	private static final String RESULT_OBJECTS_LENGTH = ". result.getObjectsLength() = ";
	private static final String DB_KEY = "dbKey";
	private static final String PROP_TYPE = ". propertyType[";
	private static final String VALUE_AND_CONTENT = "].getValue().getContent(0) = ";
	private static final String ERRORE_FASE_RECUPERO = ". Si e' verificato un errore in fase di recupero della forma documentaria ";
	private static final String REPOSITORY_ID = ".  repositoryId: ";
	private static final String OGGETTO = "oggetto";
	private static final String IMPOSSIBILE_AGGIORNARE_PROPS = "Impossibile aggiornare le properties del documento ";
	private static final String OBJECT_ID_DOCUMENTO = ".  objectIdDocumento: ";
	private static final String CLASSIFICAZIONE_PROPS_TYPE = "ClassificazionePropertiesType";
	private static final String DOT_ASTERISCHI_DOCUMENTO = ". *************** DOCUMENTO:";
	private static final String ASTERISCHI_DOCUMENTO = "*************** DOCUMENTO:";
	private static final String GET_PROPS_DOCUMENTO = ". ob.getProperties() documento \n ";
	
	private ObjectServicePort objectService;	

	private String pdFile;

	@Autowired
	private TsddrCParametroRepository parametroRepository;
	
	@Autowired
	private DoquiServiceFactory acarisServiceFactory;
	

	private ObjectServicePort getObjectService(boolean forceLoading) throws Exception{
		String method = "getObjectService";
		log.debug(method + ". BEGIN");
		try{
			objectService = acarisServiceFactory.getAcarisService().getObjectServicePort();
			log.info(method + ". AcarisObjectServiceInterface loaded correctly");	
		}
		catch(Exception e){
			log.error(method + ". Exception loading interface " + e);
			throw e;
		}
		return objectService;
	}

	@SuppressWarnings("unused")
	private ObjectServicePort getObjectService() throws Exception{
		return getObjectService(false);
	}

	public String getPdFile() 
	{
		return pdFile;
	}

	public void setPdFile(String pdFile)
	{
		this.pdFile = pdFile;
	}

	public void init(){
		String method = "init";

		try{
			super.init();
			if(log.isDebugEnabled()){
				log.debug(method + ". pdFile= " + pdFile);
			}	

			getObjectService(true);
		}
		catch(Exception e)
		{
			log.error(method + ". Exception " + e);
			throw new RuntimeException();
		}
	} 

	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#getIdentificatoreTramiteParolaChiave(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType, java.lang.String)
	 */
	public ObjectIdType getIdentificatoreTramiteParolaChiave(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String parolaChiave, ObjectIdType parentNodeId) throws IntegrationException  {
		String method = "getIdentificatoreTramiteParolaChiave";
		if(log.isDebugEnabled())
		{
			log.debug(method + REPO_ID + repositoryId.getValue());
			log.debug(method + PRINCIPAL_ID + principalId.getValue());
			log.debug(method + ". target        = " + target.getObject());
			log.debug(method + ". parolaChiave  = " + parolaChiave);
			if (parentNodeId != null)
				log.debug(method + ". parentNodeId  = " + parentNodeId.getValue());	
			else
				log.debug(method + ". parentNodeId  non valorizzato");	
		}
		ObjectIdType identificatore = null;
		PagingResponseType response = null;


		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.NONE);
		QueryConditionType condition = new QueryConditionType();
		condition.setOperator(EnumQueryOperator.EQUALS);
		//condition.setOperator(EnumQueryOperator.LIKE);
		condition.setPropertyName(PAROLE_CHIAVE);
		//condition.setValue("*"+parolaChiave+"*");
		condition.setValue(parolaChiave);
		QueryConditionType []conditions = {condition};

		NavigationConditionInfoType  navigationLimits = null;
		if (parentNodeId != null)
		{
			navigationLimits = new NavigationConditionInfoType();
			navigationLimits.setParentNodeId(parentNodeId);
			navigationLimits.setLimitToChildren(true);
		}
		try {
//			response = objectService.query(repositoryId,principalId, target, filter, conditions, navigationLimits, null, null);
			response = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId,principalId, target, filter, conditions, navigationLimits, null, null);

			
			/*
			if(response == null){
				throw new IntegrationException("Impossibile recuperare identificatore tramite parola chiave: response is null");
			}
			 */
			if(response != null && response.getObjectsLength() > 0){
				identificatore = response.getObjects()[0].getObjectId();
			}
			else{
				log.warn(method + ". parola chiave " + parolaChiave + " non presente");
			}
			/*
			if(identificatore == null){
				throw new IntegrationException("Impossibile recuperare identificatore tramite parola chiave: identificatore is null");
			}
			 */
		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero identificatore tramite parola chiave " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare identificatore per parola chiave: " + acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e);
			throw new IntegrationException("Impossibile recuperare identificatore per parola chiave: " + e, e);
		}
		return identificatore;
	}

	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#getUUIDDocumento(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType, java.lang.String)
	 */
	public String getUUIDDocumento(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String parolaChiave) throws IntegrationException {
		String method = "getUUIDDocumento";
		String propertyName = "uuidDocumento";
		String className = DOC_SEMPLICE_PROPS_TYPE;
		PagingResponseType response = null;
		String UUIDDocumento = null;

		PropertyFilterType filter = new PropertyFilterType();
		QueryNameType queryNameType = new QueryNameType();

		queryNameType.setPropertyName(propertyName);
		queryNameType.setClassName(className);
		QueryNameType[] list = {queryNameType};

		filter.setPropertyList(list);
		filter.setFilterType(EnumPropertyFilter.LIST);
		QueryConditionType condition = new QueryConditionType();
		condition.setOperator(EnumQueryOperator.EQUALS);
		condition.setPropertyName(PAROLE_CHIAVE);

		log.debug(method + ". parolaChiave: " + parolaChiave);

		condition.setValue(parolaChiave);	// 20200713_LC già comprende il PREFIX_PAROLA_CHIAVE
		QueryConditionType []conditions = {condition};

		try {
			// Chiamate tramite WSDL
//			response = objectService.query(repositoryId,principalId, target, filter, conditions, null, null, null);
			response = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId,principalId, target, filter, conditions, null, null, null);
			
			if(response == null)
				throw new IntegrationException("Impossibile recuperare UUID documento: response is null");

			if(response != null && response.getObjectsLength() > 0) {
				it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType[] p = response.getObjects()[0].getProperties();
				for (int i=0; i<p.length; i++){
					log.debug(method + DOT_PROPERTY +p[i].getQueryName().getPropertyName());	
					if (propertyName.equals(p[i].getQueryName().getPropertyName())){
						UUIDDocumento = p[i].getValue().getContent()[0];	
					}
				}
			}
			if(UUIDDocumento == null){
				throw new IntegrationException("Impossibile recuperare UUID documento: UUIDDocumento is null");
			}
			log.debug(method + ". UUIDDocumento == " +UUIDDocumento);

		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero UUID Documento " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_RECUPERARE_UUID + acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_RECUPERARE_UUID + e.getMessage(), e);
		}
		return UUIDDocumento;

	}

	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#creaFascicoloAnnuale(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType, it.csi.stacore.stadoc.business.stadoc.dto.DocumentoActa, it.csi.stacore.stadoc.business.stadoc.dto.UtenteActa)
	 */
	public ObjectIdType creaFascicolo(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentId, ParametroAcarisDTO metadatiDB, UtenteActa utenteActa, VitalRecordCodeType vitalRecordCodeType) throws IntegrationException {
		String method = "creaFascicolo";

		ObjectIdType fascicoloId = null;
		EnumFolderObjectType typeId = EnumFolderObjectType.FASCICOLO_REALE_ANNUALE_PROPERTIES_TYPE;
		FascicoloRealeAnnualePropertiesType fascicoloRealeAnnuale = new FascicoloRealeAnnualePropertiesType();
		
//		fascicoloRealeAnnuale.setIdTipoClasse(value);
		fascicoloRealeAnnuale.setOggetto("Anno tributo " + metadatiDB.getDichiarazioneAnno());
		fascicoloRealeAnnuale.setParoleChiave(metadatiDB.getDichiarazioneAnno());
		fascicoloRealeAnnuale.setConservazioneCorrente(Integer.parseInt(metadatiDB.getConservazioneCorrente()));
		fascicoloRealeAnnuale.setConservazioneGenerale(Integer.parseInt(metadatiDB.getConservazioneGenerale()));
		fascicoloRealeAnnuale.setDatiPersonali(metadatiDB.getTipologiaDatiPersonali());
		fascicoloRealeAnnuale.setDatiRiservati(metadatiDB.getTipologiaDatiRiservati());
		fascicoloRealeAnnuale.setDatiSensibili(metadatiDB.getTipologiaDatiSensibili());
//		fascicoloRealeAnnuale.setDataCreazione(new Date());
//		fascicoloRealeAnnuale.setIdVitalRecordCode();
//		fascicoloRealeAnnuale.setUtenteCreazione(value);
		fascicoloRealeAnnuale.setArchivioCorrente(metadatiDB.getArchivioCorrente());
		fascicoloRealeAnnuale.setIdVitalRecordCode(vitalRecordCodeType.getIdVitalRecordCode());

		IdAOOType idAoo = new IdAOOType();
		idAoo.setValue(utenteActa.getIdAoo().intValue());
		IdStrutturaType idStruttura = new IdStrutturaType();
		idStruttura.setValue(utenteActa.getIdStruttura().intValue());
		IdNodoType idNodo = new IdNodoType();
		idNodo.setValue(utenteActa.getIdNodo().intValue());

		fascicoloRealeAnnuale.setIdAOORespMat(idAoo);
		fascicoloRealeAnnuale.setIdStrutturaRespMat(idStruttura);
		fascicoloRealeAnnuale.setIdNodoRespMat(idNodo);

		fascicoloRealeAnnuale.setStato(EnumFascicoloRealeStatoType.APERTO);
		
		try {
			// Chiamate tramite WSDL
//			fascicoloId = objectService.createFolder(repositoryId, typeId, principalId, fascicoloRealeAnnuale, parentId);
			fascicoloId = acarisServiceFactory.getAcarisService().getObjectServicePort().createFolder(repositoryId, typeId, principalId, fascicoloRealeAnnuale, parentId);
			
			if(fascicoloId == null){
				throw new IntegrationException("Non e' possibile creare fascicolo annuale: fascicoloId is null");
			}

			if(log.isDebugEnabled()){
				log.debug(method + ". creato fascicoloId\n " + XmlSerializer.objectToXml(fascicoloId));
			}

		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di creazione fascicolo annuale " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_CREARE_FASCICOLO_ANNUALE, acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_CREARE_FASCICOLO_ANNUALE, e);
		}
		catch (Throwable e) {
			e.printStackTrace();
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_CREARE_FASCICOLO_ANNUALE);
		}

		return fascicoloId;
	}


	public ObjectIdType creaDossier(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentId
			, ParametroAcarisDTO metadatiDB, UtenteActa utenteActa)
					throws IntegrationException {
		String method = "creaDossier";

		ObjectIdType dossierId = null;
		
		EnumFolderObjectType typeId = EnumFolderObjectType.DOSSIER_PROPERTIES_TYPE;
		DossierPropertiesType dossier = new DossierPropertiesType();

		dossier.setParoleChiave(metadatiDB.getDichiarazioneIdentificativo());
		dossier.setDescrizione(metadatiDB.getDichiarazioneDenominazioneSocieta());
		dossier.setCodice(metadatiDB.getDichiarazioneIdentificativo());
		dossier.setConservazioneCorrente(Integer.parseInt(metadatiDB.getConservazioneCorrente()));
		dossier.setConservazioneGenerale(Integer.parseInt(metadatiDB.getConservazioneGenerale()));
		
		dossier.setDatiPersonali(metadatiDB.getTipologiaDatiPersonali());
		dossier.setDatiRiservati(metadatiDB.getTipologiaDatiRiservati());
		dossier.setDatiSensibili(metadatiDB.getTipologiaDatiSensibili());
		
		
		dossier.setCreazioneFascicoli(metadatiDB.getConsentiCreazioneFascicoli());
		dossier.setStato(EnumDossierStatoType.APERTO);
		dossier.setRiclassificazioneFascicoli(metadatiDB.getConsentiRiclassificazioneFascicoliDossier());
		dossier.setInserimentoDocumenti(metadatiDB.getConsentiInserimentoDocumenti());
//		//TODO Consenti riclassificazione documenti nel dossier non trovata???
//		
//		
//		dossier.setArchivioCorrente(metadatiDB.getArchivioCorrente());
//		dossier.setDataCreazione(new Date());
//		CodiceFiscaleType utenteCreatore = new CodiceFiscaleType();
//		utenteCreatore.setValue("TSDDR");
//		dossier.setUtenteCreazione(utenteCreatore);

		IdAOOType idAoo = new IdAOOType();
		idAoo.setValue(utenteActa.getIdAoo().intValue());
		IdStrutturaType idStruttura = new IdStrutturaType();
		idStruttura.setValue(utenteActa.getIdStruttura().intValue());
		IdNodoType idNodo = new IdNodoType();
		idNodo.setValue(utenteActa.getIdNodo().intValue());

		dossier.setIdAOORespMat(idAoo);
		dossier.setIdStrutturaRespMat(idStruttura);
		dossier.setIdNodoRespMat(idNodo);



		try {
			// Chiamate tramite WSDL
//			dossierId = objectService.createFolder(repositoryId, typeId, principalId, dossier, parentId);
			dossierId = acarisServiceFactory.getAcarisService().getObjectServicePort().createFolder(repositoryId, typeId, principalId, dossier, parentId);
			
			if(dossierId == null){
				throw new IntegrationException("Non e' possibile creare dossier : dossierId is null");
			}

			if(log.isDebugEnabled()){
				log.debug(method + ". creato fascicoloId\n " + XmlSerializer.objectToXml(dossierId));
			}

		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di creazione fascicolo annuale " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_CREARE_FASCICOLO_ANNUALE, acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_CREARE_FASCICOLO_ANNUALE, e);
		}

		return dossierId;
	}



	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#creaVolume(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType, it.csi.stacore.stadoc.business.stadoc.dto.DocumentoActa, it.csi.stacore.stadoc.business.stadoc.dto.UtenteActa)
	 */
	public ObjectIdType creaVolume(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType parentId, ParametroAcarisDTO metadatiDB, UtenteActa utenteActa) throws IntegrationException {

		final String method = "creaVolume";

		ObjectIdType volumeId = null;

		EnumFolderObjectType typeId = EnumFolderObjectType.VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE;

		VolumeSerieTipologicaDocumentiPropertiesType volumeSerieProperties = new VolumeSerieTipologicaDocumentiPropertiesType();

		volumeSerieProperties.setCollocazioneCartaceo("Collocazione cartacea fittizia"); // da valorizzare?
//		volumeSerieProperties.setParoleChiave(documentoActa.getFolder());
//		volumeSerieProperties.setDescrizione(documentoActa.getFolder()); 

		volumeSerieProperties.setConservazioneCorrente(5);
//		volumeSerieProperties.setConservazioneGenerale(documentoActa.getConservazioneGenerale());

		volumeSerieProperties.setDatiPersonali(false);
		volumeSerieProperties.setDatiRiservati(false);
		volumeSerieProperties.setDatiSensibili(false);
		
//		switch (documentoActa.getTipologiaDati()) {
//		case DocumentoActa.TIPOLOGIA_DATI_PERSONALI:
//			volumeSerieProperties.setDatiPersonali(true);
//			break;
//		case DocumentoActa.TIPOLOGIA_DATI_RISERVATI:
//		
//			volumeSerieProperties.setDatiRiservati(true);
//			
//			break;
//		case DocumentoActa.TIPOLOGIA_DATI_SENSIBILI:
//			
//			volumeSerieProperties.setDatiSensibili(true);
//			break;
//		default: 
//			volumeSerieProperties.setDatiPersonali(true);
//			break;    	
//		}
		

		IdAOOType idAoo = new IdAOOType();
		idAoo.setValue(utenteActa.getIdAoo().intValue());
		IdStrutturaType idStruttura = new IdStrutturaType();
		idStruttura.setValue(utenteActa.getIdStruttura().intValue());
		IdNodoType idNodo = new IdNodoType();
		idNodo.setValue(utenteActa.getIdNodo().intValue());

		volumeSerieProperties.setIdAOORespMat(idAoo);
		volumeSerieProperties.setIdStrutturaRespMat(idStruttura);
		volumeSerieProperties.setIdNodoRespMat(idNodo);

		try {
//			volumeId = objectService.createFolder(repositoryId, typeId, principalId, volumeSerieProperties, parentId);
			volumeId = acarisServiceFactory.getAcarisService().getObjectServicePort().createFolder(repositoryId, typeId, principalId, volumeSerieProperties, parentId);
			if(volumeId == null){
				throw new IntegrationException("Non e' possibile creare il volume: volumeId is null");
			}

			if(log.isDebugEnabled()){
				log.debug(method + ". creato volumeId\n " + XmlSerializer.objectToXml(volumeId));
			}

		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di creazione volume " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile creare volume ", acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e);
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile creare volume ", e);
		}

		return volumeId;
	}	


	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#getContentStream(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType)
	 */
	public AcarisContentStreamType[] getContentStream(ObjectIdType repositoryId, ObjectIdType documentoId, PrincipalIdType principalId) throws IntegrationException {
		String method = "getContentStream";
		AcarisContentStreamType[] contentStream = null;
		try {
			// Chiamate tramite WSDL
//			contentStream  = objectService.getContentStream(repositoryId, documentoId, principalId, EnumStreamId.PRIMARY);
			contentStream  = acarisServiceFactory.getAcarisService().getObjectServicePort().getContentStream(repositoryId, documentoId, principalId, EnumStreamId.PRIMARY);
			
			if(contentStream == null){
				throw new IntegrationException("Impossibile recuperare il content stream: content stream is null ");
			}
		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero del content stream " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare il content stream ", acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile recuperare il content stream ", e);
		}
		return contentStream;
	}

	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#getStatoDiEfficacia(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, java.lang.Integer)
	 */
	public Integer getStatoDiEfficacia(ObjectIdType repositoryId, PrincipalIdType principalId, Integer idStatoDiEfficacia) throws IntegrationException{
		String method = "getStatoDiEfficacia";
		QueryableObjectType target = new QueryableObjectType();
		target.setObject("StatoDiEfficaciaDecodifica"); 
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		
		/*
		QueryConditionType[] criteria = new QueryConditionType[1];
		QueryConditionType qctEfficace = new QueryConditionType();
		qctEfficace.setOperator(EnumQueryOperator.EQUALS);
		qctEfficace.setPropertyName("efficace");
		qctEfficace.setValue("S");
		criteria[0] = qctEfficace;
		*/
		
		QueryConditionType[] criteria = null;

		NavigationConditionInfoType navigationLimits = null;
		PagingResponseType result = null;
		Integer maxItems  = null;
		Integer skipCount = new Integer(0);
		Integer tmpIdStatoDiEfficacia = 0;
		Integer resultIdStatoDiEfficacia = 0;

		try {
			// Chiamate tramite WSDL
//			result = objectService.query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			result = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			if(result == null){
				throw new IntegrationException("Impossibile recuperare lo stato di efficacia: result is null ");
			}

			if(result != null && result.getObjectsLength() > 0) {

				log.debug(method + RESULT_OBJECTS_LENGTH + result.getObjectsLength());

				for(int i = 0;i < result.getObjectsLength();i++) {
					log.debug(method + ". result.getObjects(i).getObjectId().getValue() = " + result.getObjects(i).getObjectId().getValue());

					if(result.getObjects(i).getPropertiesLength() > 0)
					{
						it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType[] propertyType = result.getObjects(i).getProperties();

						log.debug(method + ". propertyType.length = " + propertyType.length);

						for (int j=0; j<propertyType.length; j++)
						{
							log.debug(method + ". propertyType.getPropertyName = " + propertyType[j].getQueryName().getPropertyName());

							if(DB_KEY.equals(propertyType[j].getQueryName().getPropertyName())) {
								log.debug(method + PROP_TYPE+j+VALUE_AND_CONTENT + propertyType[j].getValue().getContent(0));
								tmpIdStatoDiEfficacia = Integer.parseInt(propertyType[j].getValue().getContent(0));
								if (idStatoDiEfficacia.equals(tmpIdStatoDiEfficacia))
									resultIdStatoDiEfficacia = tmpIdStatoDiEfficacia;
								break;
							}
							if("efficace".equals(propertyType[j].getQueryName().getPropertyName())){
								log.debug(method + PROP_TYPE+j+VALUE_AND_CONTENT + propertyType[j].getValue().getContent(0));

							}
						}
					}
				}
			}			

			if(idStatoDiEfficacia == null){
				throw new IntegrationException("Impossibile recuperare lo stato di efficacia: idStatoDiEfficacia is null ");
			}
			if(log.isDebugEnabled()){
				log.debug(method + ". idStatoDiEfficacia = " + idStatoDiEfficacia);
			}		
		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero dello stato di efficacia " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare lo stato di efficacia ", acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile recuperare lo stato di efficacia ", e);
		}
		return resultIdStatoDiEfficacia;
	}	

	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#getIdFormaDocumentaria(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, java.lang.String)
	 */
	public Integer getIdFormaDocumentaria(ObjectIdType repositoryId, PrincipalIdType principalId, UtenteActa utenteActa) throws IntegrationException{
		final String method = "getIdFormaDocumentaria";
		log.debug(method + ". BEGIN");
		log.debug(method + ". FORMA DOCUMENTARIA= " + utenteActa.getDescFormaDocumentaria());
		
		if(utenteActa.getDescFormaDocumentaria() == null) {
			log.debug(method + ". FORMA DOCUMENTARIA NON VALORIZZATA: restituiamo un valore nullo ");
			return null;
		}
		
		QueryableObjectType target = new QueryableObjectType();
		target.setObject("FormaDocumentariaDecodifica"); 
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);

		QueryConditionType[] criteria = null;
		if(StringUtils.isEmpty(utenteActa.getDescEnte())){
			criteria = new QueryConditionType[1];
		}
		else{
			criteria = new QueryConditionType[2];
		}
		QueryConditionType qctFormaDocumentaria = new QueryConditionType();
		qctFormaDocumentaria.setOperator(EnumQueryOperator.EQUALS);
		log.debug(method + ". descrizione = " + utenteActa.getDescFormaDocumentaria());
		qctFormaDocumentaria.setPropertyName("descrizione");
		qctFormaDocumentaria.setValue(utenteActa.getDescFormaDocumentaria());
		criteria[0] = qctFormaDocumentaria;
		
		if(StringUtils.isNotEmpty(utenteActa.getDescEnte())){
			qctFormaDocumentaria = new QueryConditionType();
			qctFormaDocumentaria.setOperator(EnumQueryOperator.EQUALS);
			log.debug(method + ". descEnte = " + utenteActa.getDescEnte());
			qctFormaDocumentaria.setPropertyName("descEnte");
			qctFormaDocumentaria.setValue(utenteActa.getDescEnte());
			criteria[1] = qctFormaDocumentaria;
		}

		NavigationConditionInfoType navigationLimits = null;
		PagingResponseType result = null;
		Integer maxItems  = null;
		Integer skipCount = new Integer(0);
		//Integer tmpIdFormaDocumentaria = 0;
		Integer resultIdFormaDocumentaria = null;

		try {
			// Chiamate tramite WSDL
//			result = objectService.query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			result = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			if(result == null){
				throw new IntegrationException("Impossibile recuperare la forma documentaria: result is null ");
			}

			if(result != null && result.getObjectsLength() > 0) {

				log.debug(method + RESULT_OBJECTS_LENGTH + result.getObjectsLength());

				for(int i = 0;i < result.getObjectsLength();i++) {
					log.debug(method + ". result.getObjects(i).getObjectId().getValue() = " + result.getObjects(i).getObjectId().getValue());

					if(result.getObjects(i).getPropertiesLength() > 0)
					{
						it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType[] propertyType = result.getObjects(i).getProperties();

						log.debug(method + ". propertyType.length = " + propertyType.length);

						for (int j=0; j<propertyType.length; j++)
						{
							log.debug(method + ". propertyType.getPropertyName = " + propertyType[j].getQueryName().getPropertyName());

							if(DB_KEY.equals(propertyType[j].getQueryName().getPropertyName())) {
								log.debug(method + PROP_TYPE+j+VALUE_AND_CONTENT + propertyType[j].getValue().getContent(0));
								resultIdFormaDocumentaria = Integer.parseInt(propertyType[j].getValue().getContent(0));
								//								if (idFormaDocumentaria.equals(tmpIdFormaDocumentaria))
								//									resultIdFormaDocumentaria = tmpIdFormaDocumentaria;
								break;
							}
							if("descrizione".equals(propertyType[j].getQueryName().getPropertyName())){
								log.debug(method + PROP_TYPE+j+VALUE_AND_CONTENT + propertyType[j].getValue().getContent(0));

							}
						}
					}
				}
			}			

			if(utenteActa.getDescFormaDocumentaria() == null){
				throw new IntegrationException("Impossibile recuperare la forma documentaria: descrizioneFormaDocumentaria is null ");
			}
			if(log.isDebugEnabled()){
				log.debug(method + ". descrizioneFormaDocumentaria = " + utenteActa.getDescFormaDocumentaria());
			}		
		}
		catch (AcarisException acEx){
			log.error(method + ERRORE_FASE_RECUPERO + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare la forma documentaria ", acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile recuperare la forma documentaria ", e);
		}
		finally {
			log.debug(method + ". END");
		}
		return resultIdFormaDocumentaria;
	}	


	public void updatePropertiesDocumento(ObjectIdType repositoryId, ObjectIdType objectId, PrincipalIdType principalId, String oggetto) throws  IntegrationException{
		String method="updatePropertiesDocumento";
		log.debug(method + BEGIN);
		log.debug(method + REPOSITORY_ID + repositoryId);
		log.debug(method + ".  objectId: " + objectId);
		log.debug(method + PRINCIPAL_ID + principalId);
		log.debug(method + ".  oggetto: " + oggetto);

		ChangeTokenType changeToken = new ChangeTokenType();
		PropertyType[] properties = new PropertyType[1];
		PropertyType property = new PropertyType();

		QueryNameType queryName = new QueryNameType();
		queryName.setClassName(EnumFolderObjectType.DOCUMENTO_FISICO_PROPERTIES_TYPE.value());
		queryName.setPropertyName(OGGETTO);
		property.setQueryName(queryName);

		String[] elementi = new String[1];
		elementi[0] = oggetto;
		ValueType value = new ValueType();
		value.setContent(elementi);
		property.setValue(value);
		properties[0] = property;
		try{
			// Chiamate tramite WSDL
			
//			objectService.updateProperties(repositoryId, objectId, principalId, changeToken, properties);
			acarisServiceFactory.getAcarisService().getObjectServicePort().updateProperties(repositoryId, objectId, principalId, changeToken, properties);
		} catch (AcarisException acEx){
			log.error(method + ERRORE_FASE_RECUPERO + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, e);
		}
	}

	public void updatePropertiesOggettoDocumentoConProtocollo(ObjectIdType repositoryId, ObjectIdType objectIdClassificazione, ObjectIdType objectIdDocumento,PrincipalIdType principalId, String numeroProtocollo) throws  IntegrationException{
		String method="updatePropertiesOggettoDocumentoConProtocollo";
		log.debug(method + BEGIN);
		log.debug(method + REPOSITORY_ID + repositoryId);
		log.debug(method + ".  objectIdClassificazione: " + objectIdClassificazione);
		log.debug(method + OBJECT_ID_DOCUMENTO + objectIdDocumento);
		log.debug(method + PRINCIPAL_ID + principalId);

		try {
			//LEGGO CLASSIFICAZIONE APPENA CREATA (INUTILE)
			PropertyFilterType filter = new PropertyFilterType();
			filter.setFilterType(EnumPropertyFilter.LIST);
			QueryNameType qnt = new QueryNameType();
			qnt.setClassName(CLASSIFICAZIONE_PROPS_TYPE);
			qnt.setPropertyName("codice");
			filter.setPropertyList(new QueryNameType[]{qnt});

			// Chiamate tramite WSDL
//			ObjectResponseType ob = objectService.getProperties(repositoryId, objectIdClassificazione, principalId, filter);
			ObjectResponseType ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdClassificazione, principalId, filter);
			log.debug(method + ". *************** CLASSIFICAZIONE:"+ objectIdClassificazione.getValue());

			//LEGGO DOCUMENTO APPENA CREATO (UTILE PERCHE' CONTIENE IL changeToken)
			
			
			qnt = new QueryNameType();
			qnt.setClassName(DOC_SEMPLICE_PROPS_TYPE);
			qnt.setPropertyName(OGGETTO);
						
			filter.setPropertyList(new QueryNameType[]{qnt});
			
			// Chiamate tramite WSDL
//			ob = objectService.getProperties(repositoryId, objectIdDocumento, principalId, filter);
			ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdDocumento, principalId, filter);
			System.out.println(ASTERISCHI_DOCUMENTO+ objectIdDocumento.getValue());
			log.debug(method + ". ob.getProperties() documento appena creato\n " + XmlSerializer.objectToXml(ob.getProperties()));

			
			//JIRA - Gestione Notifica
			//Aggiornamento non richiesto - non voluto da CSI
			//AGGIORNO IL SOLO OGGETTO
			PropertyType[] properties = ob.getProperties();
			/*
			for (PropertyType propertyType : properties) {
				if(propertyType.getQueryName().getPropertyName().equals(OGGETTO))
					propertyType.getValue().setContent(0, propertyType.getValue().getContent()[0] + numeroProtocollo);
			}
			*/

			// Chiamate tramite WSDL
//			objectService.updateProperties(repositoryId, objectIdDocumento, principalId, null, properties);
			acarisServiceFactory.getAcarisService().getObjectServicePort().updateProperties(repositoryId, objectIdDocumento, principalId, null, properties);

			//RILEGGO DOCUMENTO APPENA MODIFICATO (oggetto e changeToken sono cambiati MODIFICA FATTA!!!)
			ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdDocumento, principalId, filter);
			log.debug(method + DOT_ASTERISCHI_DOCUMENTO+ objectIdDocumento.getValue());
			log.debug(method + ". ob.getProperties() documento appena modificato\n " + XmlSerializer.objectToXml(ob.getProperties()));

		} catch (AcarisException acEx){
			log.error(method + ERRORE_FASE_RECUPERO + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisObjectServiceStadoc#getIdIndiceClassificazione(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType)
	 */
	@SuppressWarnings("unused")
	public String getIdIndiceClassificazione(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectIdClassificazione) throws IntegrationException{
		String method = "getIdIndiceClassificazione";
		QueryableObjectType target = new QueryableObjectType();
		target.setObject(CLASSIFICAZIONE_PROPS_TYPE); 
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);

		QueryConditionType[] criteria = new QueryConditionType[1];
		QueryConditionType qctIndiceClassificazione = new QueryConditionType();
		qctIndiceClassificazione.setOperator(EnumQueryOperator.EQUALS);
		//log(method + ". dbKey = " + AcarisUtils.decodePkDtoFromObjId(objectIdClassificazione));
		//qctIndiceClassificazione.setPropertyName("indiceClassificazione");
		qctIndiceClassificazione.setPropertyName(DB_KEY);
		String resultIndiceClassificazione = null;

		try {
			String dbKey = ((AcarisUtils.decodeClassIdFromObjId(objectIdClassificazione.getValue()))).toString();
			qctIndiceClassificazione.setValue(dbKey);
			criteria[0] = qctIndiceClassificazione;

			NavigationConditionInfoType navigationLimits = null;
			PagingResponseType result = null;
			Integer maxItems  = null;
			Integer skipCount = new Integer(0);
			//Integer tmpIdFormaDocumentaria = 0;

			// Chiamate tramite WSDL
//			result = objectService.query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			result = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			if(result == null){
				throw new IntegrationException("Impossibile recuperare l'ndice di classificazione: result is null ");
			}

			if(result != null && result.getObjectsLength() > 0) {

				log.debug(method + RESULT_OBJECTS_LENGTH + result.getObjectsLength());

				it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType[] p = result.getObjects()[0].getProperties();
				for (int i=0; i<p.length; i++){
					log.debug(method + DOT_PROPERTY +p[i].getQueryName().getPropertyName());	
					if ("indiceClassificazione".equals(p[i].getQueryName().getPropertyName())){
						resultIndiceClassificazione = p[i].getValue().getContent()[0];	

					}
				}

				//				for(int i = 0;i < result.getObjectsLength();i++) {
				//					log(method + ". result.getObjects(i).getObjectId().getValue() = " + result.getObjects(i).getObjectId().getValue());
				//					
				//					if(result.getObjects(i).getPropertiesLength() > 0)
				//					{
				//						it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType[] propertyType = result.getObjects(i).getProperties();
				//				
				//						log.debug(method + ". propertyType.length = " + propertyType.length);
				//						
				//						for (int j=0; j<propertyType.length; j++)
				//						{
				//							log.debug(method + ". propertyType.getPropertyName = " + propertyType[j].getQueryName().getPropertyName());
				//							if(propertyType[j].getValue().getContentLength()>0){
				//								log.debug(method + ". propertyType["+j+"].getValue().getContent(0) = " + propertyType[j].getValue().getContent(0));
				//							}
				//							if("indiceClassificazione".equals(propertyType[j].getQueryName().getPropertyName())) {
				//								log.debug(method + ". propertyType["+j+"].getValue().getContent(0) = " + propertyType[j].getValue().getContent(0));
				//								log.debug(method + ". propertyType["+j+"].getQueryName().getPropertyName() = " + propertyType[j].getQueryName().getPropertyName());
				//								resultIndiceClassificazione = Integer.parseInt(propertyType[j].getValue().getContent(0));
				////								if (idFormaDocumentaria.equals(tmpIdFormaDocumentaria))
				////									resultIdFormaDocumentaria = tmpIdFormaDocumentaria;
				//								break;
				//							}
				//						}
				//					}
				//				}
			}			

			if(log.isDebugEnabled()){
				log.debug(method + ". objectIdClassificazione = " + objectIdClassificazione);
			}		
		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero dell'indice di classificazione " + acEx.getMessage(), acEx);
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare l'indice di classificazione ", acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage(), e);
			throw new IntegrationException("Impossibile recuperare l'indice di classificazione ", e);
		}
		return resultIndiceClassificazione;
	}


	


	// 20200618_LC
	public ObjectIdType moveActaDocument(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType associativeObjectId, ObjectIdType sourceFolderId, ObjectIdType destinationFolderId, boolean isRichiestaOffline) throws IntegrationException {
		String method = "spostaDocumento";

		// id nuova classificazione
		ObjectIdType classificazioneId = null;

		
		
		//	properties	
		// -----------------------------------------------------------------------------------------------------
		
		// spostamento normale
		MoveDocumentPropertiesType associativeProperties = null;									
		
		// spostamento documento con allegati o documento protocolalto e smistato - interpretare bene la documentazione, il booleano al momento è sempre TRUE
		if (isRichiestaOffline) {
		associativeProperties = new MoveDocumentPropertiesType();		
		associativeProperties.setOfflineMoveRequest(true);		// TRUE se si richiede l'esecuzione asincrona via batch, quando ci sono più allegati di quelli pervisti (= 20)
		//associativeProperties.setIdSmistamentoType(null);		// idSmistamentoType  (identificatore dello smistamento da utilizzare), quando il documento da spostare è legato ad uno smistamento (cosa e?)
		}				
		
		// -----------------------------------------------------------------------------------------------------
		
		
		
		try {
			
			// chiamata tramite WS
			classificazioneId = acarisServiceFactory.getAcarisService().getObjectServicePort().moveDocument(repositoryId, principalId, associativeObjectId, sourceFolderId, destinationFolderId, associativeProperties);
			
			
			if(classificazioneId == null){
				throw new IntegrationException("Non e' stato possibile spostare il documento, idNuovaClassificazione is null");
			}
			
			if(log.isDebugEnabled()){
				log.debug(method + ". spostato documento, nuova classificazione Id\n " + XmlSerializer.objectToXml(classificazioneId));
			}
			
		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di spostamento del documento " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile spostare il documento ", acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile spostare il documento ", e);
		}
		
		return classificazioneId;
	}

	public PagingResponseType getDocumentiTramiteProtocollo(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String numProtocollo, ObjectIdType parentNodeId) throws IntegrationException  {
		String method = "getDocumentiTramiteProtocollo";
		if(log.isDebugEnabled())
		{
			log.debug(method + REPO_ID + repositoryId.getValue());
			log.debug(method + PRINCIPAL_ID + principalId.getValue());
			log.debug(method + ". target        = " + target.getObject());
			log.debug(method + ". protocollo    = " + numProtocollo);
			if (parentNodeId != null)
				log.debug(method + ". parentNodeId  = " + parentNodeId.getValue());	
			else
				log.debug(method + ". parentNodeId  non valorizzato");	
		}
//		ObjectIdType identificatore = null;
		PagingResponseType response = null;

		
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.NONE);
		QueryConditionType[] criteria = new QueryConditionType[3];  //dichiariamo tre condizioni, in AND
		QueryConditionType c0 = new QueryConditionType();
		c0.setOperator(EnumQueryOperator.EQUALS);
		c0.setPropertyName("codice");
		c0.setValue(numProtocollo.substring(0, numProtocollo.indexOf("/")));
//		condition.setPropertyName(PAROLE_CHIAVE);
//		condition.setValue("174");
		criteria[0] = c0;
		
		QueryConditionType c1 = new QueryConditionType();
        c1.setOperator(EnumQueryOperator.EQUALS);
        c1.setPropertyName("anno");  // anno della reg
        //c1.setValue("2014");
        c1.setValue(numProtocollo.substring(numProtocollo.indexOf("/")+1));        
		log.debug(method + ". annoRegistrazione " + numProtocollo.substring(numProtocollo.indexOf("/")+1));        
        criteria[1] = c1;

        QueryConditionType c2 = new QueryConditionType();
        c2.setOperator(EnumQueryOperator.EQUALS);
        
        //c2.setPropertyName("idAooProtocollante");  c2.setValue("231");	// (_OLD_)	
        c2.setPropertyName("idAooProtocollante");  
        c2.setValue(OptionalUtil.getContent(parametroRepository.findByNomeParametro(Parametro.ACTA_ID_AOO.getNome())).getValoreParametroS());
        
		log.debug(method + ". idAoo.getValue() " + OptionalUtil.getContent(parametroRepository.findByNomeParametro(Parametro.ACTA_ID_AOO.getNome())).getValoreParametroS());        
        criteria[2] = c2;

		NavigationConditionInfoType  navigationLimits = null;
		if (parentNodeId != null)
		{
			navigationLimits = new NavigationConditionInfoType();
			navigationLimits.setParentNodeId(parentNodeId);
			navigationLimits.setLimitToChildren(true);
		}
		try {
//			response = objectService.query(repositoryId,principalId, target, filter, conditions, navigationLimits, null, null);
			response = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId,principalId, target, filter, criteria, navigationLimits, null, null);

			
			/*
			if(response == null){
				throw new IntegrationException("Impossibile recuperare identificatore tramite protocollo: response is null");
			}
			 */
			if(response != null && response.getObjectsLength() > 0){
				log.debug(method + ". trovati  = " + response.getObjectsLength() +" documenti associati al protocollo: " + numProtocollo);
			}
			else{
				log.warn(method + ". protocollo " + numProtocollo + " non presente");
			}
			/*
			if(identificatore == null){
				throw new IntegrationException("Impossibile recuperare identificatore tramite protocollo: identificatore is null");
			}
			 */
		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero documenti tramite protocollo " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare documenti per protocollo: " + acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e);
			throw new IntegrationException("Impossibile recuperare documenti per protocollo: " + e, e);
		}
		return response;
	}
	
	public String getIndiceClassificazioneEstesa(ObjectIdType repositoryId, PrincipalIdType principalId, String objectIdClassificazione) throws IntegrationException  {
		String method = "getIndiceClassificazioneEstesa";
		if(log.isDebugEnabled())
		{
			log.debug(method + REPO_ID + repositoryId.getValue());
			log.debug(method + PRINCIPAL_ID + principalId.getValue());
			log.debug(method + ". objectIdClassificazione    = " + objectIdClassificazione);
			
		}

		QueryableObjectType target = new QueryableObjectType();
		target.setObject(CLASSIFICAZIONE_PROPS_TYPE); 
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		
		QueryConditionType[] criteria = new QueryConditionType[1];
		QueryConditionType qctIndiceClassificazione = new QueryConditionType();
		qctIndiceClassificazione.setOperator(EnumQueryOperator.EQUALS);
		qctIndiceClassificazione.setPropertyName("objectId");
		qctIndiceClassificazione.setValue(objectIdClassificazione);
		criteria[0] = qctIndiceClassificazione;
		
		String resultIndiceClassificazioneEstesa = null;

		try {

			NavigationConditionInfoType navigationLimits = null;
			PagingResponseType result = null;

			// Chiamate tramite WSDL
//			result = objectService.query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			result = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId, principalId, target, filter, criteria, navigationLimits, null, null);
			if(result == null){
				throw new IntegrationException("Impossibile recuperare l'indice di classificazione estesa: result is null ");
			}

			if(result != null && result.getObjectsLength() > 0) {

				log.debug(method + RESULT_OBJECTS_LENGTH + result.getObjectsLength());
				it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType[] p = result.getObjects()[0].getProperties();
				for (int i=0; i<p.length; i++){
					log.debug(method + DOT_PROPERTY +p[i].getQueryName().getPropertyName());	
					if ("indiceClassificazioneEstesa".equals(p[i].getQueryName().getPropertyName())){
						resultIndiceClassificazioneEstesa = p[i].getValue().getContent()[0];	
						break;
					}
				}
			}
		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero dell'indice di classificazione estesa " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare la classificazione estesa: " + acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e);
			throw new IntegrationException("Impossibile recuperare la classificazione estesa: " + e, e);
		}
		return resultIndiceClassificazioneEstesa;
	}

	
	// 20200708_LC
	public void updatePropertiesParolaChiaveDocumento(ObjectIdType repositoryId, ObjectIdType objectIdDocumento,PrincipalIdType principalId, String newParolaChiave) throws  IntegrationException{
		String method="updatePropertiesParolaChiaveDocumento";
		log.debug(method + BEGIN);
		log.debug(method + REPOSITORY_ID + repositoryId);
		log.debug(method + OBJECT_ID_DOCUMENTO + objectIdDocumento);
		log.debug(method + PRINCIPAL_ID + principalId);

		try {

			//LEGGO DOCUMENTO APPENA CREATO
			PropertyFilterType filter = new PropertyFilterType();
			filter.setFilterType(EnumPropertyFilter.LIST);
			QueryNameType qnt = new QueryNameType();
			qnt = new QueryNameType();
			qnt.setClassName(DOC_SEMPLICE_PROPS_TYPE);
			qnt.setPropertyName(PAROLE_CHIAVE);
			filter.setPropertyList(new QueryNameType[]{qnt});
			
			// Chiamate tramite WSDL;
			ObjectResponseType ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdDocumento, principalId, filter);
			System.out.println(ASTERISCHI_DOCUMENTO+ objectIdDocumento.getValue());
			log.debug(method + GET_PROPS_DOCUMENTO + XmlSerializer.objectToXml(ob.getProperties()));
			// aggiorna oggetto e parola chiave
			PropertyType[] properties = ob.getProperties();
			for (PropertyType propertyType : properties) {
				if(propertyType.getQueryName().getPropertyName().equals(PAROLE_CHIAVE))
					propertyType.getValue().setContent(0, newParolaChiave);
			}

			// Chiamate tramite WSDL
//			objectService.updateProperties(repositoryId, objectIdDocumento, principalId, null, properties);
			acarisServiceFactory.getAcarisService().getObjectServicePort().updateProperties(repositoryId, objectIdDocumento, principalId, null, properties);

			//RILEGGO DOCUMENTO APPENA MODIFICATO
			ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdDocumento, principalId, filter);
			
			log.debug(method + DOT_ASTERISCHI_DOCUMENTO+ objectIdDocumento.getValue());
			log.debug(method + ". parola chiave appena modificata\n " + XmlSerializer.objectToXml(ob.getProperties()));

		} catch (AcarisException acEx){
			log.error(method + ERRORE_FASE_RECUPERO + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, e);
		}
	}

	
	// 20200721_LC
	public void updatePropertiesOggettoDocumento(ObjectIdType repositoryId, ObjectIdType objectIdDocumento,PrincipalIdType principalId, String newOggetto) throws  IntegrationException{
		String method="updatePropertiesOggettoDocumento";
		log.debug(method + BEGIN);
		log.debug(method + REPOSITORY_ID + repositoryId);
		log.debug(method + OBJECT_ID_DOCUMENTO + objectIdDocumento);
		log.debug(method + PRINCIPAL_ID + principalId);

		try {

			//LEGGO DOCUMENTO APPENA CREATO (UTILE PERCHE' CONTIENE IL changeToken)
			PropertyFilterType filter = new PropertyFilterType();
			filter.setFilterType(EnumPropertyFilter.LIST);
			QueryNameType qnt = new QueryNameType();
			qnt = new QueryNameType();
			qnt.setClassName(DOC_SEMPLICE_PROPS_TYPE);
			qnt.setPropertyName(OGGETTO);
			filter.setPropertyList(new QueryNameType[]{qnt});
			
			// Chiamate tramite WSDL;
			ObjectResponseType ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdDocumento, principalId, filter);
			System.out.println(ASTERISCHI_DOCUMENTO+ objectIdDocumento.getValue());
			log.debug(method + GET_PROPS_DOCUMENTO + XmlSerializer.objectToXml(ob.getProperties()));
			// aggiorna oggetto e parola chiave
			PropertyType[] properties = ob.getProperties();
			for (PropertyType propertyType : properties) {
				if(propertyType.getQueryName().getPropertyName().equals(OGGETTO))
					propertyType.getValue().setContent(0, newOggetto);
			}

			// Chiamate tramite WSDL
//			objectService.updateProperties(repositoryId, objectIdDocumento, principalId, null, properties);
			acarisServiceFactory.getAcarisService().getObjectServicePort().updateProperties(repositoryId, objectIdDocumento, principalId, null, properties);

			//RILEGGO DOCUMENTO APPENA MODIFICATO
			ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdDocumento, principalId, filter);
			
			log.debug(method + DOT_ASTERISCHI_DOCUMENTO+ objectIdDocumento.getValue());
			log.debug(method + ". oggetto appena modificato\n " + XmlSerializer.objectToXml(ob.getProperties()));

		} catch (AcarisException acEx){
			log.error(method + ERRORE_FASE_RECUPERO + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_AGGIORNARE_PROPS, e);
		}
	}

	
	
	// 20200711_LC
	public String getParolaChiaveDocumento(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectIdDocumento) throws  IntegrationException{
		String method="getParolaChiaveDocumento";
		log.debug(method + BEGIN);
		log.debug(method + REPOSITORY_ID + repositoryId);
		log.debug(method + OBJECT_ID_DOCUMENTO + objectIdDocumento);
		log.debug(method + PRINCIPAL_ID + principalId);
		String parolaChiave = null;
		try {

			//LEGGO DOCUMENTO APPENA CREATO (UTILE PERCHE' CONTIENE IL changeToken)
			PropertyFilterType filter = new PropertyFilterType();
			filter.setFilterType(EnumPropertyFilter.LIST);
			QueryNameType qnt = new QueryNameType();
			qnt = new QueryNameType();
			qnt.setClassName(DOC_SEMPLICE_PROPS_TYPE);
			qnt.setPropertyName(PAROLE_CHIAVE);
			filter.setPropertyList(new QueryNameType[]{qnt});
			
			// Chiamate tramite WSDL;
			ObjectResponseType ob = acarisServiceFactory.getAcarisService().getObjectServicePort().getProperties(repositoryId, objectIdDocumento, principalId, filter);
			if(ob == null){
				throw new IntegrationException("Impossibile recuperare la parola chiave: result is null ");
			}
			
			System.out.println(ASTERISCHI_DOCUMENTO+ objectIdDocumento.getValue());
			log.debug(method + GET_PROPS_DOCUMENTO + XmlSerializer.objectToXml(ob.getProperties()));
			// aggiorna oggetto e parola chiave
			PropertyType[] properties = ob.getProperties();
			for (PropertyType propertyType : properties) {
				if(propertyType.getQueryName().getPropertyName().equals(PAROLE_CHIAVE))
					parolaChiave = propertyType.getValue().getContent()[0];
			}

			log.debug(method + ". parola chiave recuperata: " + parolaChiave + "\n " + XmlSerializer.objectToXml(ob.getProperties()));

		} catch (AcarisException acEx){
			log.error(method + ERRORE_FASE_RECUPERO + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare la parola chiave del documento ", acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile recuperare la parola chiave le properties del documento ", e);
		}
		return parolaChiave;
	}
	
	
	
	// 20200722_LC
	public String getUUIDDocumentoByObjectIdDocumento(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String objectIdDocumento) throws IntegrationException {
		String method = "getUUIDDocumentByObjectIdDocumento";
		String propertyName = "uuidDocumento";
		String className = DOC_SEMPLICE_PROPS_TYPE;
		PagingResponseType response = null;
		String UUIDDocumento = null;

		PropertyFilterType filter = new PropertyFilterType();
		QueryNameType queryNameType = new QueryNameType();

		queryNameType.setPropertyName(propertyName);
		queryNameType.setClassName(className);
		QueryNameType[] list = {queryNameType};

		filter.setPropertyList(list);
		filter.setFilterType(EnumPropertyFilter.LIST);
		QueryConditionType condition = new QueryConditionType();
		condition.setOperator(EnumQueryOperator.EQUALS);
		condition.setPropertyName("objectId");

		log.debug(method + ". objectIdDocumento: " + objectIdDocumento);

		condition.setValue(objectIdDocumento);	// 20200713_LC già comprende il PREFIX_PAROLA_CHIAVE
		QueryConditionType []conditions = {condition};

		try {
			// Chiamate tramite WSDL
//			response = objectService.query(repositoryId,principalId, target, filter, conditions, null, null, null);
			response = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId,principalId, target, filter, conditions, null, null, null);
			
			if(response == null)
				throw new IntegrationException("Impossibile recuperare UUID documento: response is null");

			if(response != null && response.getObjectsLength() > 0) {
				it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType[] p = response.getObjects()[0].getProperties();
				for (int i=0; i<p.length; i++){
					log.debug(method + DOT_PROPERTY +p[i].getQueryName().getPropertyName());	
					if (propertyName.equals(p[i].getQueryName().getPropertyName())){
						UUIDDocumento = p[i].getValue().getContent()[0];	
					}
				}
			}
			if(UUIDDocumento == null){
				throw new IntegrationException("Impossibile recuperare UUID documento: UUIDDocumento is null");
			}
			log.debug(method + ". UUIDDocumento == " +UUIDDocumento);

		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero UUID Documento " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException(IMPOSSIBILE_RECUPERARE_UUID + acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_RECUPERARE_UUID + e.getMessage(), e);
		}
		return UUIDDocumento;

	}

	
	// 20211019_LC Jira CONAM-152	-	numero allegati effettivo
	public Integer getNumeroAllegatiPresenti(ObjectIdType repositoryId, PrincipalIdType principalId, String objectIdClassificazione) throws IntegrationException {
		String method = "getNumeroAllegatiPresenti";
		
		//String propertyName1 = "dbKeyClassificazionePrincipale";
		String propertyName1 = "objectIdClassificazionePrincipale";
		String propertyName2 = "stato";
		PagingResponseType response = null;
		Integer numAllegatiPresenti = null;
		
		QueryableObjectType target = new QueryableObjectType();
		target.setObject("ElencoAllegatiAClassificazionePrincipaleView");


		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		
		QueryConditionType condition1 = new QueryConditionType();
		condition1.setOperator(EnumQueryOperator.EQUALS);
		condition1.setPropertyName(propertyName1);
		condition1.setValue(objectIdClassificazione);	
		log.debug(method + ". condition dbKeyClassificazionePrincipale: " + objectIdClassificazione);

		QueryConditionType condition2 = new QueryConditionType();
		condition2.setOperator(EnumQueryOperator.EQUALS);
		condition2.setPropertyName(propertyName2);
		condition2.setValue("2");	
		log.debug(method + ". condition dbKeyClassificazionePrincipale: " + "2");
		
		QueryConditionType []conditions = {condition1, condition2};

		try {
			// Chiamate tramite WSDL
			response = acarisServiceFactory.getAcarisService().getObjectServicePort().query(repositoryId, principalId, target, filter, conditions, null, null, null);
			
			if(response == null)
				throw new IntegrationException("Impossibile recuperare numero allegati presenti: response is null");

			numAllegatiPresenti = response.getObjectsLength();
			
			log.debug(method + ". numero allegati presenti == " + numAllegatiPresenti);

		}
		catch (AcarisException acEx){
			log.error(method + ". Si e' verificato un errore in fase di recupero numero allegati presenti " + acEx.getMessage());
			if(acEx.getFaultInfo() != null) {
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID + acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO + acEx.getFaultInfo().getTechnicalInfo());	
			}
			throw new IntegrationException("Impossibile recuperare numero allegati presenti: " + acEx.getMessage(), acEx);
		}
		catch (Exception e) {
			log.error(method + DOT_EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile recuperare numero allegati presenti: " + e.getMessage(), e);
		}
		return numAllegatiPresenti;

	}
	
	
	
}
