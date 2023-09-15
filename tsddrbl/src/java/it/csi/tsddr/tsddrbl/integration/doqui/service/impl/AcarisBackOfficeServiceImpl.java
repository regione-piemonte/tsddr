/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.integration.doqui.DoquiServiceFactory;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisBackOfficeService;
import it.doqui.acta.acaris.backofficeservice.AcarisException;
import it.doqui.acta.acaris.backofficeservice.BackOfficeServicePort;
import it.doqui.acta.actasrv.dto.acaris.type.backoffice.ClientApplicationInfo;
import it.doqui.acta.actasrv.dto.acaris.type.backoffice.EnumBackOfficeObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.backoffice.PrincipalExtResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.CodiceFiscaleType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumPropertyFilter;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumQueryOperator;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdAOOType;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdNodoType;
import it.doqui.acta.actasrv.dto.acaris.type.common.IdStrutturaType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ItemType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyFilterType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryConditionType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.common.VarargsType;

/**
 * @author A152350
 *
 */
/**
 * @author A152350
 *
 */
@Service
public class AcarisBackOfficeServiceImpl extends CommonManagementServiceImpl implements AcarisBackOfficeService
{

//	public static final String LOGGER_PREFIX = DoquiConstants.LOGGER_PREFIX + ".integration";
//	private static Logger log = Logger.getLogger(LOGGER_PREFIX);	
	private static Logger log = Logger.getLogger(AcarisBackOfficeServiceImpl.class);
	
	private static final String IMPOSSIBILE_RECUPERARE_IDENTIFICATORE = "Impossibile recuperare l'identificatore ";

	private BackOfficeServicePort backOfficeService;
	@Autowired
	private DoquiServiceFactory acarisServiceFactory;

	private String pdFile;

	private BackOfficeServicePort getBackOfficeService(boolean forceLoading) throws Exception{
		String method = "getBackOfficeService";
		log.debug(method + ". BEGIN");
		try{
			backOfficeService = acarisServiceFactory.getAcarisService().getBackOfficeServicePort();
			log.info(method + ". backOfficeService loaded correctly");	
		}
		catch(Exception e){
			log.error(method + ". Exception loading interface " + e);
			throw e;
		}
		return backOfficeService;
	}

	private BackOfficeServicePort getBackOfficeService() throws Exception{
		return getBackOfficeService(false);
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
				log.debug(method + ". pdFile= " + getPdFile());
			}	
			getBackOfficeService(true);

		}
		catch(Exception e)
		{
			log.error(method + ". Exception " + e);
			throw new RuntimeException();
		}

	}

	
	public PrincipalIdType recuperaPrincipalId(UtenteActa utenteActa, ObjectIdType repositoryId) throws IntegrationException  {
		String method = "recuperaPrincipalId";
		PrincipalIdType principalId = null;

		try {

			CodiceFiscaleType codiceFiscale = new CodiceFiscaleType();
			codiceFiscale.setValue(utenteActa.getCodiceFiscale());
			IdAOOType idAoo = new IdAOOType();
			idAoo.setValue(utenteActa.getIdAoo().intValue());
			IdStrutturaType idStruttura = new IdStrutturaType();
			idStruttura.setValue(utenteActa.getIdStruttura().intValue());
			IdNodoType idNodo = new IdNodoType();
			idNodo.setValue(utenteActa.getIdNodo().intValue());
			String applicationKeyActa = utenteActa.getApplicationKeyActa();
			ClientApplicationInfo cli = new ClientApplicationInfo();
			cli.setAppKey(applicationKeyActa);			
			cli.setInfo(getInfoParamActa());		

			PrincipalExtResponseType[] arrPrincipalExt =  getBackOfficeService().getPrincipalExt(repositoryId, codiceFiscale, idAoo, idStruttura, idNodo, cli);
			if(arrPrincipalExt == null ){
				throw new IntegrationException("Impossibile recuperare il principalId: PrincipalExtResponseType is null");
			}
			if(arrPrincipalExt !=null && arrPrincipalExt.length >0) {
				PrincipalExtResponseType response = arrPrincipalExt[0];
				if(response != null) {
					principalId = response.getPrincipalId();
				}	
			}			
			if (principalId == null){
				throw new IntegrationException("Impossibile recuperare il principalId: principalId is null");
			}
			if(log.isDebugEnabled()){
				log.debug(method + ". principalId = " + principalId.getValue());
			}

		} 
		catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) {

			log.error(method + ". acEx.getMessage() = " + acEx.getMessage());
			log.error(method + ". acEx.getFaultInfo().getErrorCode() =  " + acEx.getFaultInfo().getErrorCode());
			log.error(method + ". acEx.getFaultInfo().getPropertyName() = " + acEx.getFaultInfo().getPropertyName());
			log.error(method + ". acEx.getFaultInfo().getObjectId() = " + acEx.getFaultInfo().getObjectId());
			log.error(method + ". acEx.getFaultInfo().getExceptionType() = " + acEx.getFaultInfo().getExceptionType());
			log.error(method + ". acEx.getFaultInfo().getClassName() = " + acEx.getFaultInfo().getClassName());
			log.error(method + ". acEx.getFaultInfo().getTechnicalInfo = " + acEx.getFaultInfo().getTechnicalInfo());

			throw new IntegrationException("Impossibile recuperare repositoryId ", acEx);
		} 
		catch (Exception e) {
			log.error(method + ". Exception = " + e.getMessage());
			throw new IntegrationException("Si e' verificato un errore in fase di recupero principalId ", e);
		}
		return principalId;
	}

	private VarargsType getInfoParamActa()
	{
		VarargsType info = new VarargsType();
		ItemType[] props = new ItemType[0];
		info.setItems(props);

		return info;	
	}

	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisBackOfficeServiceStadoc#recuperaIdAOO(java.lang.Integer, it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType)
	 */
	public ObjectIdType recuperaIdAOO(Integer idAoo, ObjectIdType repositoryId, PrincipalIdType  principalId) throws IntegrationException {
		String method = "recuperaIdAOO";
		ObjectIdType result = null;
		result = getIdentificatore(repositoryId, principalId, getTarget(EnumBackOfficeObjectType.AOO_PROPERTIES_TYPE.value()), idAoo.toString());
		if(log.isDebugEnabled()){
			log.debug(method + ". idAOO = " + result.getValue());
		}
		return result;
	}
	
	public ObjectIdType recuperaIdNodo(Integer idNodo, ObjectIdType repositoryId, PrincipalIdType  principalId) throws IntegrationException {
		String method = "recuperaIdNodo";
		ObjectIdType result = null;
		result = getIdentificatore(repositoryId, principalId, getTarget(EnumBackOfficeObjectType.NODO_PROPERTIES_TYPE.value()), idNodo.toString());
		if(log.isDebugEnabled()){
			log.debug(method + ". idNodo = " + result.getValue());
		}
		return result;
	}

	
	public ObjectIdType recuperaIdStruttura(Integer idStruttura, ObjectIdType repositoryId, PrincipalIdType  principalId) throws IntegrationException{
		String method = "recuperaIdStruttura";
		ObjectIdType result = null;
		
		result = this.getIdentificatore(repositoryId, principalId, getTarget(EnumBackOfficeObjectType.STRUTTURA_PROPERTIES_TYPE.value()), idStruttura.toString());
		if(log.isDebugEnabled()){
			log.debug(method + ". IdStruttura = " + result.getValue());
		}
		return result;
	}

	private QueryableObjectType getTarget(String val) {
		QueryableObjectType target = new QueryableObjectType();
		target.setObject(val);
		return target;
	}


	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisBackOfficeServiceStadoc#getIdentificatore(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType, java.lang.String)
	 */
	public ObjectIdType getIdentificatore(ObjectIdType repositoryId, PrincipalIdType principalId, QueryableObjectType target, String dbKey) throws IntegrationException {
		
		String method = "getIdentificatore";
		
		ObjectIdType identificatore = null;
		PagingResponseType response = null;

		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.NONE);
		QueryConditionType condition = new QueryConditionType();
		condition.setOperator(EnumQueryOperator.EQUALS);
		condition.setPropertyName("dbKey");
		condition.setValue(dbKey);
		QueryConditionType []conditions = {condition};
		try {
			// Chiamate tramite WSDL
//			response = backOfficeService.query(repositoryId,principalId, target, filter, conditions, null, null, null);
			response = acarisServiceFactory.getAcarisService().getBackOfficeServicePort().query(repositoryId,principalId, target, filter, conditions, null, null, null);
			if(response == null){
				throw new IntegrationException(IMPOSSIBILE_RECUPERARE_IDENTIFICATORE, new NullPointerException("response is null"));
			}
			
			if(response != null && response.getObjectsLength() > 0) {
				identificatore = response.getObjects()[0].getObjectId();
			}
			
			if(identificatore == null){
				throw new IntegrationException(IMPOSSIBILE_RECUPERARE_IDENTIFICATORE, new NullPointerException("identificatore is null"));
			}
		}
		catch (AcarisException acEx) {
			log.error(method + ". Impossibile recuperare l'identificatore: " + acEx.getMessage());
			log.error(method + ". acEx.getFaultInfo().getErrorCode() =  " + acEx.getFaultInfo().getErrorCode());
			log.error(method + ". acEx.getFaultInfo().getPropertyName() = " + acEx.getFaultInfo().getPropertyName());
			log.error(method + ". acEx.getFaultInfo().getObjectId() = " + acEx.getFaultInfo().getObjectId());
			log.error(method + ". acEx.getFaultInfo().getExceptionType() = " + acEx.getFaultInfo().getExceptionType());
			log.error(method + ". acEx.getFaultInfo().getClassName() = " + acEx.getFaultInfo().getClassName());
			log.error(method + ". acEx.getFaultInfo().getTechnicalInfo = " + acEx.getFaultInfo().getTechnicalInfo());
			throw new IntegrationException("AcarisException ", acEx);
		} 
		catch (Exception e) {
			log.error(method + ". Exception = " + e.getMessage());
			throw new IntegrationException(IMPOSSIBILE_RECUPERARE_IDENTIFICATORE, e);
		}	
		return identificatore;
	}
	
}

