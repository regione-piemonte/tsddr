/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.integration.doqui.DoquiServiceFactory;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisOfficialBookService;
import it.doqui.acta.acaris.officialbookservice.OfficialBookServicePort;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumPropertyFilter;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumQueryOperator;
import it.doqui.acta.actasrv.dto.acaris.type.common.NavigationConditionInfoType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PagingResponseType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyFilterType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PropertyType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryConditionType;
import it.doqui.acta.actasrv.dto.acaris.type.common.QueryableObjectType;
import it.doqui.acta.actasrv.dto.acaris.type.common.ValueType;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.DestinatarioEsterno;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.DestinatarioInterno;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.EnumTipoAPI;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.EnumTipoRegistrazioneDaCreare;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.EnumTipologiaSoggettoAssociato;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.IdentificazioneProtocollante;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.IdentificazioneRegistrazione;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.InfoCreazioneCorrispondente;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.InfoCreazioneRegistrazione;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.MittenteEsterno;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.MittenteInterno;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.ProtocollazioneDocumentoEsistente;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.RegistrazioneArrivo;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.RegistrazionePartenza;
import it.doqui.acta.actasrv.dto.acaris.type.officialbook.RiferimentoSoggettoEsistente;

@Service
public class AcarisOfficialBookServiceImpl extends CommonManagementServiceImpl implements AcarisOfficialBookService 
{
	
//	public static final String LOGGER_PREFIX = DoquiConstants.LOGGER_PREFIX + ".integration";	    
//	private static Logger log = Logger.getLogger(LOGGER_PREFIX);	
	private static Logger log = Logger.getLogger(AcarisOfficialBookServiceImpl.class);	
	
	private static final String BEGIN = ". BEGIN";
	private static final String END = ". END";
	private static final String DOT_ARROW = ". -> ";
	private static final String EXCEPTION = "Exception ";
	private static final String DOT_EXCEPTION = ". Exception";
	private static final String ACARIS_EXCEPTION = "AcarisException ";
	private static final String DOT_ACARIS_EXCEPTION = ". AcarisException =";
	private static final String GET_MESSAGE = ". e.getMessage() = ";
	private static final String AC_GETCAUSE = " acEx.getCause(): ";
	private static final String ACARIS_EXCEPTION_MSG = ". AcarisException.getMessage() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO = ". AcarisException.getFaultInfo() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_ERRORCODE = ". AcarisException.getFaultInfo().getErrorCode() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_PROPNAME = ". AcarisException.getFaultInfo().getPropertyName() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_OBJECTID = ". AcarisException.getFaultInfo().getObjectId() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_EXCEPTIONTYPE = ". AcarisException.getFaultInfo().getExceptionType() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_CLASSNAME = ". AcarisException.getFaultInfo().getClassName() = ";
	private static final String ACARIS_EXCEPTION_FAULTINFO_TECHINFO = ". AcarisException.getFaultInfo().getTechnicalInfo = ";
	
	
	private OfficialBookServicePort officialBookService;
	
	@Autowired
	private DoquiServiceFactory acarisServiceFactory;
	
	private String pdFile;
		
	private OfficialBookServicePort getOfficialBookService(boolean forceLoading) throws Exception{
		String method = "getOfficialBookService";
		log.debug(method + BEGIN);
		try{
			officialBookService = acarisServiceFactory.getAcarisService().getOfficialBookServicePort();
			log.info(method + ". AcarisOfficialBookServiceInterface loaded correctly");	
		}
		catch(Exception e){
			log.error(method + ". Exception loading interface " + e);
			throw e;
		}
		return officialBookService;
	}
	
	private OfficialBookServicePort getOfficialBookService() throws Exception{
		return getOfficialBookService(false);
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
			getOfficialBookService(true);
		}
		catch(Exception e)
		{
			log.error(method + ". Exception " + e);
			throw new RuntimeException();
		}
		
	}
	

	/*
	 * Registrazione Fisica in Arrivo
	 * Il soggetto � un mittente quindi la registrazione � in arrivo di tipo fisica ovvero con documento associato
	 */
	public IdentificazioneRegistrazione creaRegistrazioneInArrivoDaDocumentoElettronicoEsistente(ObjectIdType repositoryId, 
																								 PrincipalIdType principalId, 
	           																					 ObjectIdType classificazionePartenza, 
	           																					 ObjectIdType idStruttura,
	           																					 ObjectIdType idNodo, ObjectIdType idAOO,
	           																					 DocumentoActa documentoElettronicoActa) throws IntegrationException 
	{
		String method = "creaRegistrazioneInArrivoDaDocumentoElettronicoEsistente";
		log.debug(method + BEGIN);
		
		IdentificazioneRegistrazione identificazioneRegistrazione = null;
		
		EnumTipoRegistrazioneDaCreare tipologiaCreazione = EnumTipoRegistrazioneDaCreare.PROTOCOLLAZIONE_DOCUMENTO_ESISTENTE;
		
		//******************************
		//IdentificazioneProtocollante
		//******************************	
		IdentificazioneProtocollante identificazioneProtocollante = new IdentificazioneProtocollante();
		identificazioneProtocollante.setNodoId(idNodo);
		identificazioneProtocollante.setStrutturaId(idStruttura);
		
		//******************************
		//InfoCreazioneRegistrazione
		//******************************	
		InfoCreazioneRegistrazione infoCreazioneRegistrazione = new InfoCreazioneRegistrazione();
		infoCreazioneRegistrazione.setProtocollante(identificazioneProtocollante);
		infoCreazioneRegistrazione.setDocumentoRiservato(false);
		infoCreazioneRegistrazione.setOggetto(documentoElettronicoActa.getIdDocumento());
		infoCreazioneRegistrazione.setRegistrazioneRiservata(false);	
		infoCreazioneRegistrazione.setForzareSePresenzaInviti(true);
		infoCreazioneRegistrazione.setForzareSeRegistrazioneSimile(true);
		
		//******************************
		//InfoCreazioneCorrispondente - Destinatario (Regione)
		//******************************		
		DestinatarioInterno[]  elencoDestinatarioInterno = new DestinatarioInterno[1];
		DestinatarioInterno destinatarioInterno = new DestinatarioInterno();
		destinatarioInterno.setIdRuoloCorrispondente(1);

		 ObjectIdType idNodoInternoDestinatario = new ObjectIdType();	
		 idNodoInternoDestinatario.setValue(idNodo.getValue());		

		RiferimentoSoggettoEsistente infoSoggetto = new RiferimentoSoggettoEsistente();
		//infoSoggetto.setSoggettoId(idNodo); // idNodo � il nodo interno
		infoSoggetto.setSoggettoId(idNodoInternoDestinatario); // idNodoInternoDestinatario � il nodo interno	    
		infoSoggetto.setTipologia(EnumTipologiaSoggettoAssociato.NODO);

		InfoCreazioneCorrispondente infoCreazioneCorrispondenteDestinatario = new InfoCreazioneCorrispondente();
		infoCreazioneCorrispondenteDestinatario.setDenominazione("");
		infoCreazioneCorrispondenteDestinatario.setInfoSoggettoAssociato(infoSoggetto);
		
		destinatarioInterno.setCorrispondente(infoCreazioneCorrispondenteDestinatario);
		
		elencoDestinatarioInterno[0] = destinatarioInterno;
		infoCreazioneRegistrazione.setDestinatarioInterno(elencoDestinatarioInterno);

		//******************************
		//InfoCreazioneCorrispondente - Mittente (Utente)
		//******************************	
    	MittenteEsterno[] elencoMittenteEsterno = new MittenteEsterno[1];
    	MittenteEsterno mittenteEsterno = new MittenteEsterno();
    	
    	InfoCreazioneCorrispondente infoCreazioneCorrispondenteMittente = new InfoCreazioneCorrispondente();
    	
    	// gestione mittenti esterni
    	if(StringUtils.isNotBlank(documentoElettronicoActa.getMittentiEsterni())) {
    		infoCreazioneCorrispondenteMittente.setDenominazione(documentoElettronicoActa.getMittentiEsterni());	
    	}
    	else {
    		infoCreazioneCorrispondenteMittente.setDenominazione(documentoElettronicoActa.getSoggettoActa().getDenominazione());
        	infoCreazioneCorrispondenteMittente.setCognome(documentoElettronicoActa.getSoggettoActa().getCognome());
        	infoCreazioneCorrispondenteMittente.setNome(documentoElettronicoActa.getSoggettoActa().getNome());
    	}
    	
    	mittenteEsterno.setCorrispondente(infoCreazioneCorrispondenteMittente);
    	elencoMittenteEsterno[0] = mittenteEsterno;
    	
		//******************************
		//RegistrazioneArrivo
		//******************************
		RegistrazioneArrivo registrazioneArrivo = new RegistrazioneArrivo();
		registrazioneArrivo.setTipoRegistrazione(EnumTipoAPI.ARRIVO);
		registrazioneArrivo.setInfoCreazione(infoCreazioneRegistrazione);
    	registrazioneArrivo.setMittenteEsterno(elencoMittenteEsterno);

		//******************************
		//ProtocollazioneDocumentoEsistente
		//******************************
		ProtocollazioneDocumentoEsistente protocollazioneDocumentoEsistente = new ProtocollazioneDocumentoEsistente();
		protocollazioneDocumentoEsistente.setAooProtocollanteId(idAOO);
		protocollazioneDocumentoEsistente.setRegistrazioneAPI(registrazioneArrivo);
		protocollazioneDocumentoEsistente.setSenzaCreazioneSoggettiEsterni(true);		
		protocollazioneDocumentoEsistente.setClassificazioneId(classificazionePartenza);
		
		try 
		{
			identificazioneRegistrazione = getOfficialBookService().creaRegistrazione(repositoryId, principalId, tipologiaCreazione, protocollazioneDocumentoEsistente);
		}
		catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) 
		{
			log.error(method + ". ################### AcarisException on " + method + " ###################");
			log.error(method + DOT_ACARIS_EXCEPTION + acEx + AC_GETCAUSE + acEx.getCause());		
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
			}
			throw new IntegrationException(ACARIS_EXCEPTION, acEx);
		}
		catch (Exception e) 
		{
			log.error(method + ". ################### Exception on " + method + " ###################");
			log.error(method + DOT_EXCEPTION + e);
			log.error(method + GET_MESSAGE + e.getMessage());
			throw new IntegrationException(EXCEPTION, e);
		}
		finally
		{
		log.debug(method + END);
		}
		return identificazioneRegistrazione;
		}
	
	/*
	 * Registrazione Fisica in Partenza - Utente Regionale
	 * Il soggetto � un destinatario quindi la registrazione � in uscita di tipo fisica ovvero con documento associato
	 */
	public IdentificazioneRegistrazione creaRegistrazioneInPartenzaDaDocumentoElettronicoEsistente(ObjectIdType repositoryId, PrincipalIdType principalId, 
																						           ObjectIdType classificazionePartenza, 
																						           ObjectIdType idStruttura,
																								   ObjectIdType idNodo, ObjectIdType idAOO,
																								   DocumentoActa documentoElettronicoActa) throws IntegrationException {
		
		String method = "creaRegistrazioneInPartenzaDaDocumentoElettronicoEsistente";
		log.debug(method + BEGIN);
		
		IdentificazioneRegistrazione identificazioneRegistrazione = null;
		EnumTipoRegistrazioneDaCreare tipologiaCreazione = EnumTipoRegistrazioneDaCreare.PROTOCOLLAZIONE_DOCUMENTO_ESISTENTE;
       		
		//******************************
		//IdentificazioneProtocollante
		//******************************		
		log.debug(method + ". Identificazione Protocollante .... ");
		IdentificazioneProtocollante identificazioneProtocollante = new IdentificazioneProtocollante();
		identificazioneProtocollante.setNodoId(idNodo);
		identificazioneProtocollante.setStrutturaId(idStruttura);
	
		//******************************
		//InfoCreazioneRegistrazione
		//******************************		
		log.debug(method + ". InfoCreazioneRegistrazione ....");
		InfoCreazioneRegistrazione infoCreazioneRegistrazione = new InfoCreazioneRegistrazione();
		infoCreazioneRegistrazione.setProtocollante(identificazioneProtocollante);
		infoCreazioneRegistrazione.setDocumentoRiservato(false);
		infoCreazioneRegistrazione.setOggetto(documentoElettronicoActa.getIdDocumento());
		infoCreazioneRegistrazione.setRegistrazioneRiservata(false);	
		infoCreazioneRegistrazione.setForzareSePresenzaInviti(true);
		infoCreazioneRegistrazione.setForzareSeRegistrazioneSimile(true);
		
		//******************************
		//InfoCreazioneCorrispondente - Destinatario (Utente)
		//******************************		
		log.debug(method + ". InfoCreazioneCorrispondente Destinatario ....");
		DestinatarioEsterno[]  elencoDestinatarioEsterno = new DestinatarioEsterno[1];
		DestinatarioEsterno destinatarioEsterno = new DestinatarioEsterno();
		destinatarioEsterno.setIdRuoloCorrispondente(1);
		
		InfoCreazioneCorrispondente infoCreazioneCorrispondenteDestinatario = new InfoCreazioneCorrispondente();		
		infoCreazioneCorrispondenteDestinatario.setDenominazione(documentoElettronicoActa.getSoggettoActa().getDenominazione());
		infoCreazioneCorrispondenteDestinatario.setCognome(documentoElettronicoActa.getSoggettoActa().getCognome());
		infoCreazioneCorrispondenteDestinatario.setNome(documentoElettronicoActa.getSoggettoActa().getNome());
			
		destinatarioEsterno.setCorrispondente(infoCreazioneCorrispondenteDestinatario);
		elencoDestinatarioEsterno[0] = destinatarioEsterno;
		infoCreazioneRegistrazione.setDestinatarioEsterno(elencoDestinatarioEsterno);
		
		//******************************
		//InfoCreazioneCorrispondente - Mittente (Regione)
		//******************************				
		log.debug(method + ". InfoCreazioneCorrispondente Mittente ....");
		MittenteInterno[] elencoMittenteInterno = new MittenteInterno[1];
        MittenteInterno mittenteInterno = new MittenteInterno();
             
		 ObjectIdType idNodoInternoDestinatario = new ObjectIdType();	
		 idNodoInternoDestinatario.setValue(idNodo.getValue());
		 
		RiferimentoSoggettoEsistente infoSoggetto = new RiferimentoSoggettoEsistente();
		//infoSoggetto.setSoggettoId(idNodo); // idNodo � il nodo interno
		infoSoggetto.setSoggettoId(idNodoInternoDestinatario); // idNodoInternoDestinatario � il nodo interno	    
		infoSoggetto.setTipologia(EnumTipologiaSoggettoAssociato.NODO);
		
		log.debug(method + ". InfoSoggettoAssociato ....");
		InfoCreazioneCorrispondente infoCreazioneCorrispondenteMittente = new InfoCreazioneCorrispondente();
		infoCreazioneCorrispondenteMittente.setDenominazione(""); //<------------ � obbligatorio (altrimenti ACTA rialza una properties non presente)
		infoCreazioneCorrispondenteMittente.setInfoSoggettoAssociato(infoSoggetto);
		//infoCreazioneCorrispondenteMittente.setInfoSoggettoAssociato(null); //<------------------------------------------con il set dell'infoSoggettoAssociato a null ricadiamo nella vecchia configurazione di stadoc (nonsense) 
		//infoCreazioneCorrispondenteMittente.setDenominazione(documentoElettronicoActa.getFolder()); //<------------ necessario se infoSoggetto = null
		
		infoCreazioneRegistrazione.setNumeroRegistrazionePrecedente(documentoElettronicoActa.getMetadatiActa().getNumeroRegistrazionePrecedente()); 
        infoCreazioneRegistrazione.setAnnoRegistrazionePrecedente(documentoElettronicoActa.getMetadatiActa().getAnnoRegistrazionePrecedente());
		log.debug(method + ". InfoCreazioneCorrispondente NumeroRegistrazionePrecedente = " + documentoElettronicoActa.getMetadatiActa().getNumeroRegistrazionePrecedente());
		log.debug(method + ". InfoCreazioneCorrispondente AnnoRegistrazionePrecedente   = " + documentoElettronicoActa.getMetadatiActa().getAnnoRegistrazionePrecedente());
		
        mittenteInterno.setCorrispondente(infoCreazioneCorrispondenteMittente);
        elencoMittenteInterno[0] = mittenteInterno;
		infoCreazioneRegistrazione.setMittenteInterno(elencoMittenteInterno);
	
		//******************************
		//ProtocollazioneDocumentoEsistente
		//******************************	
		log.debug(method + ". ProtocollazioneDocumentoEsistente ....");
		ProtocollazioneDocumentoEsistente protocollazioneDocumentoEsistente = new ProtocollazioneDocumentoEsistente();
		protocollazioneDocumentoEsistente.setAooProtocollanteId(idAOO);
		//protocollazioneDocumentoEsistente.setAooProtocollanteId(idAOOInternoDestinatario);		
    	
		//******************************
		//RegistrazionePartenza
		//******************************	
    	RegistrazionePartenza registrazionePartenza = new RegistrazionePartenza();
    	registrazionePartenza.setTipoRegistrazione(EnumTipoAPI.PARTENZA);
    	registrazionePartenza.setInfoCreazione(infoCreazioneRegistrazione);

    	protocollazioneDocumentoEsistente.setRegistrazioneAPI(registrazionePartenza);
    	protocollazioneDocumentoEsistente.setSenzaCreazioneSoggettiEsterni(true);		
    	protocollazioneDocumentoEsistente.setClassificazioneId(classificazionePartenza);
    			
    	try 
		{
    		identificazioneRegistrazione = getOfficialBookService().creaRegistrazione(repositoryId, principalId, tipologiaCreazione, protocollazioneDocumentoEsistente);
		}
		catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) 
		{
			
			log.error(method + DOT_ACARIS_EXCEPTION + acEx + AC_GETCAUSE + acEx.getCause());		
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
		return identificazioneRegistrazione;
	}

	@SuppressWarnings("unused")
	public ObjectIdType recuperaIdFascicoloProtocollazioneInEntrataAssociata(ObjectIdType repositoryId, PrincipalIdType principalId,String codiceRegistrazione, String annoRegistrazione, ObjectIdType idAoo) throws IntegrationException {
		String method = "recuperaIdFascicoloProtocollazioneInEntrataAssociata";
		log.debug(method + BEGIN);
		
		//target= ClassificazioniProtocollateView
		 //1) utilizzare il metodo query dell'interfaccia officialBook,  per ricavare la classificazione della registrazione in Arrivo, utilizzando i parametri codice anno e idAooProtocollante .
		 //2) � su AcarisNavigationServiceStadocImpl utilizzare il metodo getFolderParent dell'interfaccia navigationService passando in input la classificazione ottenuta al punto 1 e ottenendo in output l'aggregazione(nel vs caso il fascicolo) in cui la classificazione � contenuta. 
		
		//1)
		QueryableObjectType target = new QueryableObjectType();
		target.setObject("ClassificazioniProtocollateView");
		
        PropertyFilterType filter = new PropertyFilterType() ;
        filter.setFilterType(EnumPropertyFilter.ALL);
        
        QueryConditionType[] criteria = new QueryConditionType[3];  //dichiariamo tre condizioni, in AND
        QueryConditionType c0 = new QueryConditionType();
        c0.setOperator(EnumQueryOperator.EQUALS);
        c0.setPropertyName("codice");  // codice della reg 
        //c0.setValue("00000001");
        log.debug(method + ". codiceRegistrazione " + codiceRegistrazione);
        c0.setValue(codiceRegistrazione);        
        criteria[0] = c0;
        
        QueryConditionType c1 = new QueryConditionType();
        c1.setOperator(EnumQueryOperator.EQUALS);
        c1.setPropertyName("anno");  // anno della reg
        //c1.setValue("2014");
        c1.setValue(annoRegistrazione);        
		log.debug(method + ". annoRegistrazione " + annoRegistrazione);        
        criteria[1] = c1;

        QueryConditionType c2 = new QueryConditionType();
        c2.setOperator(EnumQueryOperator.EQUALS);
        c2.setPropertyName("objectIdAooProtocollante");  // objectId dell'aoo protocollante (passare il cifrato)
        //c1.setValue("07cf32c326de27cb2fc33ac430fa27c525cf27de3ccf26fe2cda30f56799648a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a758a");
        c2.setValue(idAoo.getValue());
		log.debug(method + ". idAoo.getValue() " + idAoo.getValue());        
        criteria[2] = c2;

        NavigationConditionInfoType navigationLimits = new NavigationConditionInfoType();
        Integer maxItems = 10;
        Integer skipCount = 0;
				
        PagingResponseType pagingResponseType = null;
        ObjectIdType classId = new ObjectIdType();
        
		try {
			pagingResponseType = getOfficialBookService().query(repositoryId, principalId, target, filter, criteria, navigationLimits, maxItems, skipCount);
			
			PropertyType[] prop = pagingResponseType.getObjects()[0].getProperties();
			for (int i = 0; i < prop.length; i++) {
				PropertyType propertyType = prop[i];
				log.debug(method + DOT_ARROW + propertyType.getQueryName().getClassName());
				log.debug(method + DOT_ARROW + propertyType.getQueryName().getPropertyName());	
				log.debug(method + DOT_ARROW + propertyType.getValue());	
				if(propertyType.getQueryName().getPropertyName().equalsIgnoreCase("objectIdClassificazione")){
					ValueType vt = propertyType.getValue();
					classId.setValue(vt.getContent()[0]);
				}
			}
			
        }catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) 
		{
			
			log.error(method + DOT_ACARIS_EXCEPTION + acEx + AC_GETCAUSE + acEx.getCause());		
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
        
        log.debug(method + END);		        
        return classId;
	}
	
}
