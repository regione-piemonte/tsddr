/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.integration.doqui.DoquiServiceFactory;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisNavigationService;
import it.doqui.acta.acaris.navigationservice.NavigationServicePort;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumPropertyFilter;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyFilterType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType;
@Service
public class AcarisNavigationServiceImpl extends
		CommonManagementServiceImpl implements AcarisNavigationService {

//	public static final String LOGGER_PREFIX = DoquiConstants.LOGGER_PREFIX + ".integration";   
//	private static Logger log = Logger.getLogger(LOGGER_PREFIX);	
	private static Logger log = Logger.getLogger(AcarisNavigationServiceImpl.class);
	
	private static final String BEGIN = ". BEGIN";
	private static final String END = ". END";
	private static final String DOT_EXCEPTION = ". Exception";
	private static final String EXCEPTION = "Exception ";
	private static final String GET_MESSAGE = ". e.getMessage() = ";
	private static final String AC_GETCAUSE = " acEx.getCause(): ";
	private static final String ACARIS_EXCEPTION_EQ = ". AcarisException =";
	private static final String ACARIS_EXCEPTION = "AcarisException ";
	private static final String ACARIS_EXCEPTION_MSG = ". AcarisException.getMessage() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO = ". AcarisException.getFaultInfo() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_ERRORCODE = ". AcarisException.getFaultInfo().getErrorCode() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_PROPNAME = ". AcarisException.getFaultInfo().getPropertyName() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_OBJECTID = ". AcarisException.getFaultInfo().getObjectId() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE = ". AcarisException.getFaultInfo().getExceptionType() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_CLASSNAME = ". AcarisException.getFaultInfo().getClassName() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_TECHINFO = ". AcarisException.getFaultInfo().getTechnicalInfo = ";
	
	
	private NavigationServicePort navigationService;

	@Autowired
	private DoquiServiceFactory acarisServiceFactory;
	
	private String pdFile;
	
	public String getPdFile() {
		return pdFile;
	}

	public void setPdFile(String pdFile) {
		this.pdFile = pdFile;
	}

	private NavigationServicePort getNavigationService() throws Exception{
		return getNavigationService(false);
	}
	
	private NavigationServicePort getNavigationService(boolean forceLoading) throws Exception{
		String method = "getNavigationService";
		log.debug(method + BEGIN);
		try{
			navigationService = acarisServiceFactory.getAcarisService().getNavigationServicePort();
			log.info(method + ". AcarisNavigationServiceInterface loaded correctly");	
		}
		catch(Exception e){
			log.error(method + ". Exception loading interface " + e);
			throw e;
		}
		return navigationService;
	}
	
	public ObjectIdType recuperaFascicoloProtocollazione(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType idFascicolo) throws IntegrationException{
		
		String method = "recuperaFascicoloProtocollazione";
		log.debug(method + BEGIN);
		
		ObjectResponseType fascicolo = null;
		
        PropertyFilterType filter = new PropertyFilterType() ;
        filter.setFilterType(EnumPropertyFilter.ALL);
        
        try{
        	fascicolo = getNavigationService().getFolderParent(repositoryId, idFascicolo , principalId, filter); 
        	if (fascicolo!=null){  // ret.getObjectId() � l'objectId dell'aggregazione(fascicolo) che dovrai usare come destinazione del doc della reg in PARTENZA
        		log.debug(method + ". Il fascicolo esiste");
        		// fascicolo contiene anche un array di properies relative al fascicolo (fascicolo.getProperties())  che potresti visualizzare in fase di debug per verifica ad esempio codice, iggetto datacreazione ... 

        		return fascicolo.getObjectId();
        	}
        }catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) 
		{
			
			log.error(method + ACARIS_EXCEPTION_EQ + acEx + AC_GETCAUSE + acEx.getCause());		
			log.error(method + ACARIS_EXCEPTION_MSG + acEx.getMessage());
			log.error(method + ACARIS_EXCEPTION_FAULTINFO + acEx.getFaultInfo());	
			if(acEx.getFaultInfo() != null)
			{
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE+acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME+ acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID+ acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE+ acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME+ acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO+ acEx.getFaultInfo().getTechnicalInfo());
				throw new IntegrationException(ACARIS_EXCEPTION, acEx);
			}			
		}
		catch (Exception e) 
		{
			log.error(method + DOT_EXCEPTION + e);
			log.error(method + GET_MESSAGE + e.getMessage());
			throw new IntegrationException(EXCEPTION, e);
		}
		finally
		{
			log.debug(method + END);			
		}
        
		log.warn(method + ". RETURNED NULL");
		return null;
	}

	public ObjectResponseType recuperaChildren(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectId, boolean isGruppoAllegati) throws IntegrationException{
		
		String method = "recuperaChildren";
		log.debug(method + BEGIN);
		
		PagingResponseType children = null;
		ObjectResponseType response = null;
		
        PropertyFilterType filter = new PropertyFilterType() ;
        filter.setFilterType(EnumPropertyFilter.ALL);
        
        try{
        	children = getNavigationService().getChildren(repositoryId, objectId, principalId, filter, null, null); 
        	if (children!=null){ 
        		log.debug(method + ". Il children esiste");

                // 20211019_LC Jira CONAM-152	-	estrazione corretta del gruppoAllegati
                if (isGruppoAllegati) {
                	// cerca tra tutti gli oggetti uno di tipo GruppoAllegati			
                	for (int i = 0; i < children.getObjectsLength(); i++) {
                		PropertyType[] prop = children.getObjects(i).getProperties();
                		for (int j = 0; j < prop.length; j++) {
                			PropertyType propertyType = prop[j];
                			if (propertyType.getQueryName().getClassName().equalsIgnoreCase("GruppoAllegatiPropertiesType")) {
                				response = children.getObjects(i); 
                			}
                		}
                	}

                } else {
                	// prende il primo in caso di documento (DocumentoSemplicePropertiesType)
                	if (children.getObjectsLength()>0) response = children.getObjects(0);    
                }
                
        	}
        }catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) 
		{
			
			log.error(method + ACARIS_EXCEPTION_EQ + acEx + AC_GETCAUSE + acEx.getCause());		
			log.error(method + ACARIS_EXCEPTION_MSG + acEx.getMessage());
			log.error(method + ACARIS_EXCEPTION_FAULTINFO + acEx.getFaultInfo());	
			if(acEx.getFaultInfo() != null)
			{
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE+acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME+ acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID+ acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE+ acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME+ acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO+ acEx.getFaultInfo().getTechnicalInfo());
				throw new IntegrationException(ACARIS_EXCEPTION, acEx);
			}			
		}
		catch (Exception e) 
		{
			log.error(method + DOT_EXCEPTION + e);
			log.error(method + GET_MESSAGE + e.getMessage());
			throw new IntegrationException(EXCEPTION, e);
		}
		finally
		{
			log.debug(method + END);			
		}
        
         return response;
	}

	public void init(){
		String method = "init";
		
		try{
			super.init();
			if(log.isDebugEnabled()){
				log.debug(method + ". pdFile= " + getPdFile());
			}	
			getNavigationService(true);
		}
		catch(Exception e)
		{
			log.error(method + ". Exception " + e);
			throw new RuntimeException();
		}
		
	}
	
	
	
	// 20200707_LC
	public PagingResponseType recuperaDescendants(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectId, Integer depth) throws IntegrationException{
		
		String method = "recuperaDescendants";
		log.debug(method + BEGIN);
		
		PagingResponseType descendants = null;
		
        PropertyFilterType filter = new PropertyFilterType() ;
        filter.setFilterType(EnumPropertyFilter.ALL);
                
        try{
        	descendants = getNavigationService().getDescendants( repositoryId, objectId, principalId, depth, filter, null, null); 
        	if (descendants!=null){ 
        		log.debug(method + ". Il descendants esiste");
	    }
        }catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) 
		{
			
			log.error(method + ACARIS_EXCEPTION_EQ + acEx + AC_GETCAUSE + acEx.getCause());		
			log.error(method + ACARIS_EXCEPTION_MSG + acEx.getMessage());
			log.error(method + ACARIS_EXCEPTION_FAULTINFO + acEx.getFaultInfo());	
			if(acEx.getFaultInfo() != null)
			{
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE+acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME+ acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID+ acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE+ acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME+ acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO+ acEx.getFaultInfo().getTechnicalInfo());
				throw new IntegrationException(ACARIS_EXCEPTION, acEx);
			}			
		}
		catch (Exception e) 
		{
			log.error(method + DOT_EXCEPTION + e);
			log.error(method + GET_MESSAGE + e.getMessage());
			throw new IntegrationException(EXCEPTION, e);
		}
		finally
		{
			log.debug(method + END);			
		}
        
         return descendants;
	}
	
	
	// 20200708_LC
	public PagingResponseType recuperaAllChildrens(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType objectId) throws IntegrationException{
		
		String method = "recuperaAllChildrens";
		log.debug(method + BEGIN);
		
		PagingResponseType children = null;
		
        PropertyFilterType filter = new PropertyFilterType() ;
        filter.setFilterType(EnumPropertyFilter.ALL);
        
        try{
        	children = getNavigationService().getChildren(repositoryId, objectId, principalId, filter, null, null); 
        	if (children!=null){ 
        		log.debug(method + ". Il children esiste");
	    }
        }catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) 
		{
			
			log.error(method + ACARIS_EXCEPTION_EQ + acEx + AC_GETCAUSE + acEx.getCause());		
			log.error(method + ACARIS_EXCEPTION_MSG + acEx.getMessage());
			log.error(method + ACARIS_EXCEPTION_FAULTINFO + acEx.getFaultInfo());	
			if(acEx.getFaultInfo() != null)
			{
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_ERRORCODE+acEx.getFaultInfo().getErrorCode());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_PROPNAME+ acEx.getFaultInfo().getPropertyName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_OBJECTID+ acEx.getFaultInfo().getObjectId());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE+ acEx.getFaultInfo().getExceptionType());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_CLASSNAME+ acEx.getFaultInfo().getClassName());
				log.error(method + ACARIS_EXCEPTION_FAULTINFO_TECHINFO+ acEx.getFaultInfo().getTechnicalInfo());
				throw new IntegrationException(ACARIS_EXCEPTION, acEx);
			}			
		}
		catch (Exception e) 
		{
			log.error(method + DOT_EXCEPTION + e);
			log.error(method + GET_MESSAGE + e.getMessage());
			throw new IntegrationException(EXCEPTION, e);
		}
		finally
		{
			log.debug(method + END);			
		}
        
         return children;		// 20200707_LC
	}


	
}
