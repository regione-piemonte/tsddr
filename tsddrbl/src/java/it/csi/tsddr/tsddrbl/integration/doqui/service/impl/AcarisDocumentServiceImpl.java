/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.service.impl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.integration.doqui.DoquiConstants;
import it.csi.tsddr.tsddrbl.integration.doqui.DoquiServiceFactory;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoElettronicoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.MimeType;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.IntegrationException;
import it.csi.tsddr.tsddrbl.integration.doqui.service.AcarisDocumentService;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.CleanUtil;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.DateFormat;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.XmlSerializer;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;
import it.doqui.acta.acaris.documentservice.DocumentServicePort;
import it.doqui.acta.actasrv.dto.acaris.type.archive.ClassificazionePropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.ContenutoFisicoPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.DocumentoFisicoPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.DocumentoSemplicePropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumDocPrimarioType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.EnumTipoDocumentoType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.GruppoAllegatiPropertiesType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.IdFormaDocumentariaType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.IdStatoDiEfficaciaType;
import it.doqui.acta.actasrv.dto.acaris.type.common.AcarisContentStreamType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumMimeTypeType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumStreamId;
import it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType;
import it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType;
import it.doqui.acta.actasrv.dto.acaris.type.document.ContenutoFisicoIRC;
import it.doqui.acta.actasrv.dto.acaris.type.document.DocumentoArchivisticoIRC;
import it.doqui.acta.actasrv.dto.acaris.type.document.DocumentoFisicoIRC;
import it.doqui.acta.actasrv.dto.acaris.type.document.EnumStepErrorAction;
import it.doqui.acta.actasrv.dto.acaris.type.document.EnumTipoDocumentoArchivistico;
import it.doqui.acta.actasrv.dto.acaris.type.document.EnumTipoOperazione;
import it.doqui.acta.actasrv.dto.acaris.type.document.IdentificatoreDocumento;
import it.doqui.acta.actasrv.dto.acaris.type.document.IdentificazioneTrasformazione;
import it.doqui.acta.actasrv.dto.acaris.type.document.InfoRichiestaTrasformazione;
import it.doqui.acta.actasrv.dto.acaris.type.document.StepErrorAction;
import it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType;
import it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException;


@Service
public class AcarisDocumentServiceImpl extends CommonManagementServiceImpl implements AcarisDocumentService
{

	private static Logger log = Logger.getLogger(AcarisDocumentServiceImpl.class);
	
	private static final String BEGIN = ". BEGIN";
	private static final String END = ". END";
	private static final String FAULT_INFO_ERROR_CODE = ". acEx.getFaultInfo().getErrorCode() =  ";
	private static final String FAULT_INFO_PROPERTY_NAME = ". acEx.getFaultInfo().getPropertyName() = ";
	private static final String FAULT_INFO_OBJECT_ID = ". acEx.getFaultInfo().getObjectId() = ";
	private static final String FAULT_INFO_EXCEPTION_TYPE = ". acEx.getFaultInfo().getExceptionType() = ";
	private static final String FAULT_INFO_CLASSNAME = ". acEx.getFaultInfo().getClassName() = ";
	private static final String FAULT_INFO_TECHNICAL_INFO = ". acEx.getFaultInfo().getTechnicalInfo = ";
	private static final String ACARIS_EXCEPTION = "AcarisException ";
	private static final String EXCEPTION = ". Exception = ";

	private DocumentServicePort documentService;


	@Autowired
	private DoquiServiceFactory acarisServiceFactory;
	
	private String pdFile;
	public void init(){
		String method = "init";
		try{
			super.init();

			if(log.isDebugEnabled()){
				log.debug(method + ". pdFile= " + getPdFile());
			}	
			getDocumentService(true);

		}
		catch(Exception e)
		{
			log.error(method + ". Exception " + e);
			throw new RuntimeException();
		}

	}
	private DocumentServicePort getDocumentService(boolean forceLoading) throws Exception{
		String method = "getDocumentService";
		log.debug(method + BEGIN);
		try{
			documentService = acarisServiceFactory.getAcarisService().getDocumentServicePort();
			log.info(method + ". EcmEngineManagementInterface loaded correctly");	
		}
		catch(Exception e){
			log.error(method + ". Exception loading interface " + e);
			throw e;
		}
		return documentService;
	}
	
	private DocumentServicePort getDocumentService() throws Exception{
		return getDocumentService(false);
	}
	

	public String getPdFile() 
	{
		return pdFile;
	}

	public void setPdFile(String pdFile)
	{
		this.pdFile = pdFile;
	}


	public IdentificatoreDocumento creaDocumentoSoloMetadati(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType folderId, VitalRecordCodeType vitalRecordCodeType, Integer idFormaDocumentaria, Integer idStatoDiEfficacia, DocumentoActa documentoActa) throws IntegrationException {
		String method = "creaDocumentoSoloMetadati";
		IdentificatoreDocumento identificatoreDocumento = null;
		DocumentoArchivisticoIRC datiCreazione = new DocumentoArchivisticoIRC();
		datiCreazione.setParentFolderId(folderId);	
		datiCreazione.setAllegato(false);
		
		// verifica 
		if(documentoActa.getNumeroAllegati() > 0){
			
			/*
			 * Quando si crea la classificazione principale, 
				oltre all'indicazione del gruppo allegati (come indicato in una mail precedente), bisogna indicare ad acta che la classificazione 
				prevede degli allegati valorizzando la property documentoArchivisticoIRC.propertiesDocumento.docConAllegati.
			 * 
			 * 
			 */
			
			log.debug(method + ". il documento contiene allegati: configurazione gruppo allegati");
			GruppoAllegatiPropertiesType gruppoAllegati = new GruppoAllegatiPropertiesType();
		    gruppoAllegati.setNumeroAllegati(documentoActa.getNumeroAllegati());
		    gruppoAllegati.setDataInizio(DateFormat.getCurrentDate());
		    datiCreazione.setGruppoAllegati(gruppoAllegati);
		    
		} else {
			datiCreazione.setGruppoAllegati(null);
		}
			
		datiDocumento(datiCreazione, vitalRecordCodeType, documentoActa, idFormaDocumentaria, idStatoDiEfficacia);
	
		try{
			
			if(log.isDebugEnabled()) {
				log.debug(method + " DocumentoArchivisticoIRC\n " + XmlSerializer.objectToXml(datiCreazione));
			}
			
			identificatoreDocumento = getDocumentService().creaDocumento(repositoryId, principalId, EnumTipoOperazione.SOLO_METADATI, datiCreazione);
		} 
		catch (AcarisException acEx) {
			log.error(method + ". Impossibile creare il documento    : " + acEx.getMessage());
			log.error(method + FAULT_INFO_ERROR_CODE + acEx.getFaultInfo().getErrorCode());
			log.error(method + FAULT_INFO_PROPERTY_NAME + acEx.getFaultInfo().getPropertyName());
			log.error(method + FAULT_INFO_OBJECT_ID + acEx.getFaultInfo().getObjectId());
			log.error(method + FAULT_INFO_EXCEPTION_TYPE + acEx.getFaultInfo().getExceptionType());
			log.error(method + FAULT_INFO_CLASSNAME + acEx.getFaultInfo().getClassName());
			log.error(method + FAULT_INFO_TECHNICAL_INFO + acEx.getFaultInfo().getTechnicalInfo());
			throw new IntegrationException(ACARIS_EXCEPTION, acEx);
		} 
		
		catch (Exception e) {
			log.error(method + ". Exception: " + e.getMessage());
			throw new IntegrationException("Impossibile creare il documento solo metadati ", e);
		}
		
		return identificatoreDocumento;
	}
	
	private void setDocumentoActaProps(DocumentoActa documentoActa, DocumentoSemplicePropertiesType properties) {
	    // originatore
        if(documentoActa.getOriginatore() != null)
            properties.setOriginatore(new String[]{CleanUtil.cleanNullValue( documentoActa.getOriginatore())});
        
        // autore giuridico
        if(documentoActa.getAutoreGiuridico() != null)
            properties.setAutoreGiuridico(new String[]{CleanUtil.cleanNullValue(documentoActa.getAutoreGiuridico())});
        
        // autore fisico
        if(documentoActa.getAutoreFisico() != null)
            properties.setAutoreFisico(new String[]{CleanUtil.cleanNullValue(documentoActa.getAutoreFisico())});
        
        // scrittore
        if(documentoActa.getScrittore() != null)
            properties.setScrittore(new String[]{CleanUtil.cleanNullValue(documentoActa.getScrittore())});
        
        // destinatario giuridico
        if(documentoActa.getDestinatarioGiuridico() != null)
            properties.setDestinatarioGiuridico(new String[]{CleanUtil.cleanNullValue(documentoActa.getDestinatarioGiuridico())});
        else
            properties.setAutoreGiuridico(new String[]{DoquiConstants.ENTE});
        
        // destinatario fisico
        if(documentoActa.getDestinatarioFisico() != null)
            properties.setDestinatarioFisico(new String[]{CleanUtil.cleanNullValue(documentoActa.getDestinatarioFisico())});
        
        // soggetto produttore
        if(documentoActa.getSoggettoProduttore() != null)
            properties.setSoggettoProduttore(new String[]{CleanUtil.cleanNullValue(documentoActa.getSoggettoProduttore())});
        
	}
	
	private void valorizeProps(Integer idFormaDocumentaria, DocumentoSemplicePropertiesType properties, Integer idStatoDiEfficacia) {
	    IdFormaDocumentariaType idFormaDocumentariaType = new IdFormaDocumentariaType();
        if(idFormaDocumentaria != null){
            idFormaDocumentariaType.setValue(idFormaDocumentaria);
            properties.setIdFormaDocumentaria(idFormaDocumentariaType);
        }
        
        //20200708 PP - CDU_18 - se idStatoDiEfficaciaType = "PERFETTO ED EFFICACE" (id = 2), si tratta di un doc firmato, quindi setto TipoDocFisico a FIRMATO
        //new param
        if(idStatoDiEfficacia == 2) {
            properties.setTipoDocFisico(EnumTipoDocumentoType.FIRMATO);
            properties.setFirmaElettronicaDigitale(true);
        }
        else {
            properties.setTipoDocFisico(EnumTipoDocumentoType.SEMPLICE);
            properties.setFirmaElettronicaDigitale(false);
         // properties.setDataCreazione(DateFormat.getCurrentDate());
        }
	}
	
	private void datiDocumento(DocumentoArchivisticoIRC datiCreazione, VitalRecordCodeType vitalRecordCodeType, DocumentoActa documentoActa, Integer idFormaDocumentaria, Integer idStatoDiEfficacia)
	{ 	
		String method= "datiDocumento";
		log.debug(method + BEGIN);
		//DATI PRINCIPALI
    	DocumentoSemplicePropertiesType properties = new DocumentoSemplicePropertiesType();
    	datiCreazione.setTipoDocumento(EnumTipoDocumentoArchivistico.DOCUMENTO_SEMPLICE);
    	
    	//DATI DI CLASSIFICAZIONE
    	ClassificazionePropertiesType classificazionePropertiesType = new ClassificazionePropertiesType();
    	
    	setDocumentoActaProps(documentoActa, properties);
    	
    	properties.setRegistrato(true);
    	properties.setDefinitivo(true);
    	properties.setModificabile(false); 	
    	properties.setAnalogico(true);
    	properties.setDaConservare(false);
    	properties.setProntoPerConservazione(false);
    	properties.setDatiPersonali(true);
    	properties.setDatiRiservati(false);
    	properties.setDatiSensibili(false);
    	
    	//[Raffaella] parte da verificare: parole chiave e oggetto
    	properties.setParoleChiave(documentoActa.getIdDocumento()); 	// 20200713_LC comprende già il PREFIX_PAROLA_CHIAVE
    	//String[] listaAutore = {documentoActa.getAutore()};	
    	//properties.setAutoreFisico(listaAutore);
    	properties.setApplicativoAlimentante(documentoActa.getApplicativoAlimentante());
       	properties.setIdVitalRecordCode(vitalRecordCodeType.getIdVitalRecordCode());
       	
       	IdStatoDiEfficaciaType idStatoDiEfficaciaType = new IdStatoDiEfficaciaType();
       	idStatoDiEfficaciaType.setValue(idStatoDiEfficacia);
       	properties.setIdStatoDiEfficacia(idStatoDiEfficaciaType);
       	
     	
        properties.setRappresentazioneDigitale(false); 
      
        valorizeProps(idFormaDocumentaria, properties, idStatoDiEfficacia);
        
        //[Raffaella] � corretto questo true????
        properties.setDocAllegato(true);
        
        // il documento ha allegati
        if(documentoActa.getNumeroAllegati() > 0 )
        	properties.setDocConAllegati(true);  

        //JIRA - Gestione Notifica
        //properties.setDataDocCronica(documentoActa.getDataCronica() !=null ? documentoActa.getDataCronica() : DateFormat.getCurrentDate());
        //properties.setDataDocTopica(documentoActa.getDataTopica() !=null ? documentoActa.getDataTopica() : null);
        
        String intestazioneOggetto = "";
        String descrizioneTipoLettera = "";
        
        if(documentoActa.getSoggettoActa().isMittente()){
        	properties.setOrigineInterna(false);
        	// SE IL CONTRIBUENTE � UNA PERSONA FISICA
        	
        	/*
        	properties.setAutoreFisico(new String[]{(documentoActa.getSoggettoActa().getNome()!=null ||  documentoActa.getSoggettoActa().getCognome()!=null) ? documentoActa.getSoggettoActa().getNome() + " " + 
        								documentoActa.getSoggettoActa().getCognome() : ""});  
           	*/
        	
            // SE IL CONTRIBUENTE � UNA PERSONA GIURIDICA
            properties.setAutoreGiuridico(new String[]{documentoActa.getSoggettoActa().getDenominazione()!=null ? documentoActa.getSoggettoActa().getDenominazione() : ""}); 
    	    
            //properties.setDestinatarioGiuridico(new String[]{Constants.ENTE});
    	    
    	    properties.setFirmaElettronica(true);
    	    
    	    
    	} else {
    		
    		log.debug(method + ". InfoCreazioneCorrispondente NumeroRegistrazionePrecedente = " + documentoActa.getMetadatiActa().getNumeroRegistrazionePrecedente());
    		log.debug(method + ". InfoCreazioneCorrispondente AnnoRegistrazionePrecedente   = " + documentoActa.getMetadatiActa().getAnnoRegistrazionePrecedente());
    		
			/*
			 * DP 
			 * gestire nuovo scenario per la PEC:
			 * protocollazione in uscita non associata ad una protocollazione in ingresso (numeroRegistrazionePrecedente non valorizzato)
			 * deve creare la struttura con il folder passato come parametro
			 */
    		if(!StringUtils.isEmpty(documentoActa.getMetadatiActa().getNumeroRegistrazionePrecedente()))
    			intestazioneOggetto = "RISPOSTA_AL_PROT_IN_"+documentoActa.getMetadatiActa().getNumeroRegistrazionePrecedente() + "/" +documentoActa.getMetadatiActa().getAnnoRegistrazionePrecedente() +" - ";
    		properties.setOrigineInterna(true);
    		properties.setAutoreFisico(new String[]{DoquiConstants.DIRIGENTE});
    	    
    		//properties.setAutoreGiuridico(new String[]{Constants.ENTE});
    	    
    	    if(documentoActa.getMetadatiActa()!= null){
	    	    //UTENTE CHE STA LAVORANDO (QUELLO LOGGATO A STABOF)
	    	    properties.setScrittore(new String[]{documentoActa.getMetadatiActa().getScrittore()});
	    	    properties.setDestinatarioFisico(new String[]{documentoActa.getMetadatiActa().getDestinatarioFisico()}); // SE IL CONTRIBUENTE � UNA PERSONA FISICA
	    	    properties.setDestinatarioGiuridico(new String[]{documentoActa.getMetadatiActa().getDestinatarioGiuridico()}); // SE IL CONTRIBUENTE � UNA PERSONA GIURIDICA
	    	  //RAFFAELLA RIPRISTINATA LA FUNZIONALITA' DELLA PAROLA CHIAVE COME IDENTIFICATIVO UNIVOCO AGGIUNTA L'INFORMAZIONE SUL TIPO DI LETTERA NELL'OGGETTO COME DA ANALISI ARCHIVISTICA V09
//	    	    String paroleChiave = properties.getParoleChiave() + (documentoActa.getMetadatiActa().getDescrizioneTipoLettera()!=null?" - " + documentoActa.getMetadatiActa().getDescrizioneTipoLettera():"");
//	    	    properties.setParoleChiave(paroleChiave);
	    	    descrizioneTipoLettera = documentoActa.getMetadatiActa().getDescrizioneTipoLettera()!=null?" - " +documentoActa.getMetadatiActa().getDescrizioneTipoLettera():"";
    	    }

    	    //JIRA - Gestione Notifica
    	    //properties.setDataDocTopica(DoquiConstants.LUOGO);    	    

    	    properties.setFirmaElettronica(false);
    	    properties.setComposizione(EnumDocPrimarioType.DOCUMENTO_SINGOLO);
    	   
      	    // 20200731_LC sostituito da nuova logica (fine del metodo):
//    		//new param
//    	    if(documentoActa.getCollocazioneCartacea() != null)
//    	    	classificazionePropertiesType.setCollocazioneCartacea(documentoActa.getCollocazioneCartacea());
//    	    else
//    	    	classificazionePropertiesType.setCollocazioneCartacea(DoquiConstants.COLLOCAZIONE_CARTACEA);
    	}
        
        if (documentoActa.getMetadatiActa() != null) {
    		properties.setOggetto(intestazioneOggetto + documentoActa.getMetadatiActa().toString() + descrizioneTipoLettera);
        } else {
     		properties.setOggetto(documentoActa.getIdDocumento());
        }
            	 
    	classificazionePropertiesType.setCopiaCartacea(false);
    	classificazionePropertiesType.setCartaceo(documentoActa.isDocumentoCartaceo());   
    	
    	// 20200731_LC per tutti i documenti
    	if(documentoActa.getCollocazioneCartacea() != null)
    		classificazionePropertiesType.setCollocazioneCartacea(documentoActa.getCollocazioneCartacea());
    	
    	
    	log.debug(method + ". intestazione oggetto    : " + intestazioneOggetto);
    	log.debug(method + ". Oggetto documento    : " + properties.getOggetto());
    	
        datiCreazione.setPropertiesDocumento(properties);	
    	datiCreazione.setPropertiesClassificazione(classificazionePropertiesType);
    	log.debug(method + END);
    	
	}
	
	private AcarisContentStreamType creaContentStream(byte[] stream, String fileName, String mimeType)
	{
		String method = "creaContentStream";
		log.debug(method + BEGIN);
		
		AcarisContentStreamType contentStream = new AcarisContentStreamType();

		
		//non necessario per implementazione WS
//		contentStream.setStream(stream);
		String estensioneFile = fileName.substring(fileName.lastIndexOf('.')+1);
		contentStream.setFilename(fileName);
		contentStream.setMimeType(EnumMimeTypeType.fromValue(mimeType));
		
//		final InputStream iS = new ByteArrayInputStream(stream);
//		final OutputStream oS = new ByteArrayOutputStream(stream.length);
		
		

		javax.activation.DataSource a = new javax.activation.DataSource() {
		       
	        public OutputStream getOutputStream() throws IOException {
	            return new ByteArrayOutputStream(stream.length);
	        }
	       
	        public String getName() {
	            return fileName;
	        }
	       
	        public InputStream getInputStream() throws IOException {
	            return new ByteArrayInputStream(stream);
	        }
			public String getContentType() {
				return estensioneFile;
			}
		};
				
		// valorizzare StreamMTOM se servizio invocato via WS SOAP
		contentStream.setStreamMTOM(new DataHandler(a));
				
		log.debug(method + END);
		
		return contentStream;
	}
	
	private AcarisContentStreamType creaContentStream(byte[] stream, String fileName, EnumMimeTypeType mimeType)
	{
		String method = "creaContentStream";
		log.debug(method + BEGIN);
		
		AcarisContentStreamType contentStream = new AcarisContentStreamType();
//		contentStream.setStream(stream);
//		contentStream.setFilename(fileName);
//		contentStream.setMimeType(mimeType);
		
		String estensioneFile = fileName.substring(fileName.lastIndexOf('.')+1);
		contentStream.setFilename(fileName);
		contentStream.setMimeType(mimeType);
		
		final InputStream iS = new ByteArrayInputStream(stream);
		final OutputStream oS = new ByteArrayOutputStream(stream.length);
		
		javax.activation.DataSource a = new javax.activation.DataSource() {
			
			public OutputStream getOutputStream() throws IOException {
				return oS;
			}
			
			public String getName() {
				return fileName;
			}
			
			public InputStream getInputStream() throws IOException {
				return iS;
			}
			
			public String getContentType() {
				return estensioneFile;
			}
		};
		
		// valorizzare StreamMTOM se servizio invocato via WS SOAP
		contentStream.setStreamMTOM(new DataHandler(a));
						
		log.debug(method + END);
		
		return contentStream;
	}
	
	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisDocumentServiceStadoc#trasformaDocumentoPlaceholderInDocumentoElettronico(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.csi.stacore.stadoc.business.stadoc.dto.DocumentoElettronicoActa, int)
	 */
	public IdentificazioneTrasformazione[] trasformaDocumentoPlaceholderInDocumentoElettronico(ObjectIdType repositoryId, PrincipalIdType principalId, DocumentoElettronicoActa documentoElettronicoActa, int idStatoDiEfficacia) throws IntegrationException  {
		String method = "trasformaDocumentoPlaceholderInDocumentoElettronico";
		log.debug(method + ".  BEGIN");
		IdentificazioneTrasformazione[] identificazioneTrasformazione = null;
		
		ObjectIdType classificazioneId = new ObjectIdType();
		classificazioneId.setValue(documentoElettronicoActa.getClassificazioneId());
		
		log.debug(method + ". ***************************classificazioneId.getValue(): " + classificazioneId.getValue());
		log.debug(method + ". ***************************documentoElettronicoActa.getClassificazioneId(): " + documentoElettronicoActa.getClassificazioneId());
		
		
		ObjectIdType registrazioneId = new ObjectIdType();
		registrazioneId.setValue(documentoElettronicoActa.getRegistrazioneId());
		
		DocumentoFisicoIRC[] documenti = new DocumentoFisicoIRC[1];
		
		documenti[0] = new DocumentoFisicoIRC();
	   	DocumentoFisicoPropertiesType documentoFisicoProperty = new DocumentoFisicoPropertiesType();
    	
    	documentoFisicoProperty.setDescrizione(documentoElettronicoActa.getDescrizione());
    	documenti[0].setPropertiesDocumentoFisico(documentoFisicoProperty);
    	
    	ContenutoFisicoIRC[] contenuti = new ContenutoFisicoIRC[1];
    	
		contenuti[0] = new ContenutoFisicoIRC();
    	ContenutoFisicoPropertiesType contenutoFisicoPropertiesType = new ContenutoFisicoPropertiesType();
    	contenutoFisicoPropertiesType.setModificheTecniche("-");
    	contenutoFisicoPropertiesType.setSbustamento(false);
    	contenuti[0].setPropertiesContenutoFisico(contenutoFisicoPropertiesType);
    	AcarisContentStreamType contentStream = creaContentStream(documentoElettronicoActa.getStream(), documentoElettronicoActa.getNomeFile(), EnumMimeTypeType.APPLICATION_PDF);
    	contenuti[0].setStream(contentStream);
    	contenuti[0].setTipo(EnumStreamId.PRIMARY);
    	documenti[0].setContenutiFisici(contenuti);
    	

    	// 20200825_PP - 	Inserimento StepErrorAction per specificare se nel caso di fallimento del singolo step il sistema effettua comunque l'inserimento del documento. 
    	//					E' usato per i documenti firmati che possono avere la firma non valida (es. scaduta) 
    	// TODO - inserire lettura proprieta da db;
    	
    	/**
    	
    	stepErrorActionList e' una stringa di flag.
    	indica se lo step corrispondente alla posizione del digit deve:
    	- restare attivo ('0')
    	- essere disabilitato ('1')
    	
    	 */
    	
    	String stepErrorActionList = "1111111";
    	
    	StepErrorAction[] stepErrorAction = new StepErrorAction[7];
    	StepErrorAction errorAction1 = new StepErrorAction();
    	errorAction1.setAction(EnumStepErrorAction.INSERT);
    	errorAction1.setStep(1);
    	StepErrorAction errorAction2 = new StepErrorAction();
    	errorAction2.setAction(EnumStepErrorAction.INSERT);
    	errorAction2.setStep(2);
    	StepErrorAction errorAction3 = new StepErrorAction();
    	errorAction3.setAction(EnumStepErrorAction.INSERT);
    	errorAction3.setStep(3);
    	StepErrorAction errorAction4 = new StepErrorAction();
    	errorAction4.setAction(EnumStepErrorAction.INSERT);
    	errorAction4.setStep(4);
    	StepErrorAction errorAction5 = new StepErrorAction();
    	errorAction5.setAction(EnumStepErrorAction.INSERT);
    	errorAction5.setStep(5);
    	StepErrorAction errorAction6 = new StepErrorAction();
    	errorAction6.setAction(EnumStepErrorAction.INSERT);
    	errorAction6.setStep(6);
    	StepErrorAction errorAction7 = new StepErrorAction();
    	errorAction7.setAction(EnumStepErrorAction.INSERT);
    	errorAction7.setStep(7);

		log.debug(method + ". ***************************stepErrorActionList: " + stepErrorActionList);
		
    	if(stepErrorActionList != null && stepErrorActionList.length() == 7) {
    		    		
    	    valorizeStepErrorAction(stepErrorActionList, stepErrorAction, errorAction1, errorAction2, errorAction3, errorAction4, errorAction5, errorAction6, errorAction7);
	    	
	    	
	    	contenuti[0].setAzioniVerificaFirma(stepErrorAction);
    	}
    	
	    //[Raffaella] TODO  CHIEDERE A RIGANO SE NEL CASO DI QUESTO SERVIZIO  DOBBIAMO METTERE IDFORMA DOCUMENTARIA E DOVE????
		InfoRichiestaTrasformazione infoRichiestaTrasformazione = new InfoRichiestaTrasformazione();
    	infoRichiestaTrasformazione.setDiventaElettronico(true);
    	infoRichiestaTrasformazione.setMultiplo(false);
    	infoRichiestaTrasformazione.setRimandareOperazioneSbustamento(true);
    	infoRichiestaTrasformazione.setStatoDiEfficaciaId(idStatoDiEfficacia);
    	infoRichiestaTrasformazione.setTipoDocFisicoId(2);//Semplice
    	infoRichiestaTrasformazione.setComposizioneId(1);//Documento Singolo 

		try {
			identificazioneTrasformazione = getDocumentService().trasformaDocumentoPlaceholderInDocumentoElettronico(repositoryId, principalId, classificazioneId, registrazioneId, infoRichiestaTrasformazione, documenti);
	    	if(identificazioneTrasformazione == null){
	    		throw new IntegrationException("Impossibile trasformare i placeholder in documento elettronico: identificazioneTrasformazione is null");
	    	}
	
		} 
		catch (it.doqui.acta.actasrv.util.acaris.wrapper.exception.AcarisException acEx) {
			log.error(method + ". Impossibile trasformare i placeholder in documento elettronico: " + acEx.getMessage());
			log.error(method + FAULT_INFO_ERROR_CODE + acEx.getFaultInfo().getErrorCode());
			log.error(method + FAULT_INFO_PROPERTY_NAME + acEx.getFaultInfo().getPropertyName());
			log.error(method + FAULT_INFO_OBJECT_ID + acEx.getFaultInfo().getObjectId());
			log.error(method + FAULT_INFO_EXCEPTION_TYPE + acEx.getFaultInfo().getExceptionType());
			log.error(method + FAULT_INFO_CLASSNAME + acEx.getFaultInfo().getClassName());
			log.error(method + FAULT_INFO_TECHNICAL_INFO + acEx.getFaultInfo().getTechnicalInfo());
			throw new IntegrationException(ACARIS_EXCEPTION, acEx);
		} 
		catch (Exception e) {
			log.error(method + EXCEPTION + e.getMessage());
			throw new IntegrationException("Impossibile trasformare i placeholder in documento elettronico ", e);
		}
		
		log.debug(method + ".  END");
		return identificazioneTrasformazione;
	}
	
	private void valorizeStepErrorAction(String stepErrorActionList, StepErrorAction[] stepErrorAction, StepErrorAction errorAction1, StepErrorAction errorAction2, 
	        StepErrorAction errorAction3, StepErrorAction errorAction4, StepErrorAction errorAction5, StepErrorAction errorAction6, StepErrorAction errorAction7) {
	    if(stepErrorActionList.charAt(0) == '1') {
            stepErrorAction[0] = errorAction1;
        }
        if(stepErrorActionList.charAt(1) == '1') {
            stepErrorAction[1] = errorAction2;
        }
        if(stepErrorActionList.charAt(2) == '1') {
            stepErrorAction[2] = errorAction3;
        }
        if(stepErrorActionList.charAt(3) == '1') {
            stepErrorAction[3] = errorAction4;
        }
        if(stepErrorActionList.charAt(4) == '1') {
            stepErrorAction[4] = errorAction5;
        }
        if(stepErrorActionList.charAt(5) == '1') {
            stepErrorAction[5] = errorAction6;
        }
        if(stepErrorActionList.charAt(6) == '1') {
            stepErrorAction[6] = errorAction7;
        }
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see it.csi.stacore.stadoc.business.stadoc.integration.AcarisDocumentServiceStadoc#creaDocumentoElettronico(it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.common.PrincipalIdType, it.doqui.acta.actasrv.dto.acaris.type.common.ObjectIdType, it.doqui.acta.actasrv.dto.acaris.type.management.VitalRecordCodeType, java.lang.Integer, it.csi.stacore.stadoc.business.stadoc.dto.DocumentoElettronicoActa)
	 */
	public IdentificatoreDocumento creaDocumentoElettronico(ObjectIdType repositoryId, PrincipalIdType principalId, ObjectIdType folderId, VitalRecordCodeType vitalRecordCodeType, Integer idStatoDiEfficacia, Integer idFormaDocumentaria, String numeroProtocolloPadre, String pkAllegato, DocumentoElettronicoActa documentoElettronicoActa,boolean isProtocollazioneInUscitaSenzaDocumento, ParametroAcarisDTO metadatiDB) throws IntegrationException {
		String method = "creaDocumentoElettronico";
		log.debug(method + ".  BEGIN");
		log.debug(method + ".  repositoryId: " + repositoryId.getValue());
		log.debug(method + ".  principalId: " + principalId.getValue());
		log.debug(method + ".  objectIdDocumento: " + folderId.getValue());
		if(vitalRecordCodeType!=null){
			log.debug(method + ".  vitalRecordCodeType.getDescrizione(): " + vitalRecordCodeType.getDescrizione());
			log.debug(method + ".  vitalRecordCodeType.getTempoDiVitalita(): " + vitalRecordCodeType.getTempoDiVitalita());
			log.debug(method + ".  vitalRecordCodeType.getIdVitalRecordCode(): " + vitalRecordCodeType.getIdVitalRecordCode());
		}
		log.debug(method + ".  idFormaDocumentaria: " + idFormaDocumentaria);
		log.debug(method + ".  idStatoDiEfficacia: " + idStatoDiEfficacia);
		if(documentoElettronicoActa != null){
			log.debug(method + ". documentoElettronicoActa\n " + documentoElettronicoActa);//XmlSerializer.objectToXml(documentoElettronicoActa));
		
		
			IdentificatoreDocumento identificatoreDocumento = null;	
			DocumentoArchivisticoIRC datiCreazione = new DocumentoArchivisticoIRC();
				//QUI NEL CASO DEGLI ALLEGATI BISOGNA PASSARE identificatoreDocumento.getObjectIdClassificazione() DEL DOCUMENTO PADRE (PROBABILMENTE)
			if(pkAllegato != null){
				datiCreazione.setAllegato(true);
				datiCreazione.setParentFolderId(null);
				datiCreazione.setClassificazionePrincipale(folderId);
				datiCreazione.setGruppoAllegati(null);
			} else {
				
				datiCreazione.setAllegato(false);
				datiCreazione.setParentFolderId(folderId);		
	
				if(documentoElettronicoActa.getNumeroAllegati() > 0){
					GruppoAllegatiPropertiesType gruppoAllegati = new GruppoAllegatiPropertiesType();
				    gruppoAllegati.setNumeroAllegati(documentoElettronicoActa.getNumeroAllegati());
				    gruppoAllegati.setDataInizio(DateFormat.getCurrentDate());
				    datiCreazione.setGruppoAllegati(gruppoAllegati);
				} else {
					datiCreazione.setGruppoAllegati(null);
				}
				    
			}
			
			datiDocumentoElettronico(datiCreazione, vitalRecordCodeType, idStatoDiEfficacia, idFormaDocumentaria, numeroProtocolloPadre, pkAllegato, documentoElettronicoActa, isProtocollazioneInUscitaSenzaDocumento, metadatiDB);
		
	
			try {
				
				if(datiCreazione != null){
	//				String datiCreazioneXml = XmlSerializer.objectToXml(datiCreazione);
	//				log.debug(method + ". datiCreazione\n " + datiCreazioneXml);
				}
				
				identificatoreDocumento = getDocumentService().creaDocumento(repositoryId, principalId, EnumTipoOperazione.ELETTRONICO, datiCreazione);
				if(identificatoreDocumento == null){
					throw new IntegrationException("Impossibile creare il documento elettronico: identificatoreDocumento is null");
				}	
				if(log.isDebugEnabled()){
					log.debug(method + ". identificatoreDocumento\n " + XmlSerializer.objectToXml(identificatoreDocumento));	
				}
				
				
							
				
			} 
			catch (it.doqui.acta.acaris.documentservice.AcarisException acEx) {
				log.error(method + ". Impossibile creare il documento elettronico: " + acEx.getMessage(), acEx);
				log.error(method + FAULT_INFO_ERROR_CODE + acEx.getFaultInfo().getErrorCode());
				log.error(method + FAULT_INFO_PROPERTY_NAME + acEx.getFaultInfo().getPropertyName());
				log.error(method + FAULT_INFO_OBJECT_ID + acEx.getFaultInfo().getObjectId());
				log.error(method + FAULT_INFO_EXCEPTION_TYPE + acEx.getFaultInfo().getExceptionType());
				log.error(method + FAULT_INFO_CLASSNAME + acEx.getFaultInfo().getClassName());
				log.error(method + FAULT_INFO_TECHNICAL_INFO + acEx.getFaultInfo().getTechnicalInfo());
				throw new IntegrationException(ACARIS_EXCEPTION, acEx);
			} 
			catch (Exception e) {
				log.error(method + EXCEPTION + e.getMessage(), e);
				throw new IntegrationException("Impossibile creare il documento elettronico ", e);
			}
			catch (Throwable e) {
				e.printStackTrace();
				log.error(method + EXCEPTION + e.getMessage(), e);
				throw new IntegrationException("Impossibile creare il documento elettronico ");
			}
			
			log.debug(method + ".  END");
			return identificatoreDocumento;
		}else {
			log.warn(method + ". RETURNED NULL");
			log.debug(method + ".  END");
			return null;
		}
	}
	
	
	private void datiDocumentoElettronico(DocumentoArchivisticoIRC datiCreazione, VitalRecordCodeType vitalRecordCodeType, Integer idStatoDiEfficacia, Integer idFormaDocumentaria, String numeroProtocolloPadre, String pkAllegato, DocumentoElettronicoActa documentoElettronicoActa,boolean isProtocollazioneInUscitaSenzaDocumento, ParametroAcarisDTO metadatiDB) { 	
		String method = "datiDocumentoElettronico";
		
		log.debug(method + BEGIN);
		
    	DocumentoSemplicePropertiesType properties = new DocumentoSemplicePropertiesType();
    	
    	ClassificazionePropertiesType classificazionePropertiesType = new ClassificazionePropertiesType();
    	

    	classificazionePropertiesType.setCopiaCartacea(metadatiDB.getCopiaCartacea());
    	
    	classificazionePropertiesType.setCartaceo(metadatiDB.getCartaceo());
    	
    	classificazionePropertiesType.setCollocazioneCartacea("");

    	EnumTipoDocumentoType tipoDocFisico =  EnumTipoDocumentoType.SEMPLICE;
    	for(EnumTipoDocumentoType type : EnumTipoDocumentoType.values()) {
    		if(type.toString().equalsIgnoreCase(metadatiDB.getTipoDocumento())) {
    			tipoDocFisico = type;
    		}
    	}
    	
    	properties.setTipoDocFisico(tipoDocFisico);
 		properties.setOggetto(metadatiDB.getDichiarazioneDescrizioneDocumento());
 		properties.setOrigineInterna(metadatiDB.getOrigineInterna());
 		// properties.presenza file - non esiste
 		
 		
    	properties.setDatiPersonali(metadatiDB.getTipologiaDatiPersonali());
    	properties.setDatiRiservati(metadatiDB.getTipologiaDatiRiservati());
    	properties.setDatiSensibili(metadatiDB.getTipologiaDatiSensibili());
    	
    	//TODO - non trovati
//    	Annotazione
//    	Annotazione formale
//    	Applica annotazione a intero documento
//    	Applica annotazione a classificazione corrente
    	
    	properties.setRegistrato(metadatiDB.getStatoRegistrato());
    	properties.setModificabile(metadatiDB.getStatoModificabile());
    	properties.setDefinitivo(metadatiDB.getStatoDefinitivo());
    	properties.setAutoreGiuridico(new String[]{metadatiDB.getDichiarazioneDenominazioneSocieta()});
    	properties.setDestinatarioGiuridico(new String[]{metadatiDB.getDestinatarioGiuridico()});
    	
    	//TODO - problema compatibilità actaserv con cxf 3.3.10
//    	properties.setDataDocCronica(metadatiDB.getDichiarazioneData());
    	properties.setDocConAllegati(false);
    	
    	properties.setDocAutenticato(metadatiDB.getAutenticato());
    	properties.setDocAutenticatoFirmaAutenticata(metadatiDB.getAutenticatoConFirmaAutentica());
    	properties.setDocAutenticatoCopiaAutentica(metadatiDB.getAutenticatoCopiaAutentica());
    	
    	properties.setApplicativoAlimentante(metadatiDB.getApplicativoAlimentante());

       	properties.setIdVitalRecordCode(vitalRecordCodeType.getIdVitalRecordCode());
       	IdStatoDiEfficaciaType idStatoDiEfficaciaType = new IdStatoDiEfficaciaType();
       	idStatoDiEfficaciaType.setValue(idStatoDiEfficacia);
       	properties.setIdStatoDiEfficacia(idStatoDiEfficaciaType);
       	
       	IdFormaDocumentariaType idFormaDocumentariaType = new IdFormaDocumentariaType();
       	if(idFormaDocumentaria != null){
       		idFormaDocumentariaType.setValue(idFormaDocumentaria);
       		properties.setIdFormaDocumentaria(idFormaDocumentariaType);
       	}

//       	properties.setTipoDocFisico(EnumTipoDocumentoType.fromValue(metadatiDB.getTipoDocumento()));       	
        properties.setRappresentazioneDigitale(true); 
        
        EnumDocPrimarioType composizione =  EnumDocPrimarioType.DOCUMENTO_SINGOLO;
    	for(EnumDocPrimarioType comp : EnumDocPrimarioType.values()) {
    		if(comp.toString().equalsIgnoreCase(metadatiDB.getComposizione())) {
    			composizione = comp;
    		}
    	}
        properties.setComposizione(composizione); 
    	
        properties.setMultiplo(metadatiDB.getMolteplicitaDellaComposizione());
    	//TODO nno trovato - Rimanda l’operazione di sbustamento in quanto si prevede, a breve, la spedizione del documento (non sbustare)
    	DocumentoFisicoIRC[] documenti = new DocumentoFisicoIRC[1];
 		
 		documenti[0] = new DocumentoFisicoIRC();
 	   	DocumentoFisicoPropertiesType documentoFisicoProperty = new DocumentoFisicoPropertiesType();
 	   	
 	    documentoFisicoProperty.setDescrizione(documentoElettronicoActa.getDescrizione());
 	    documenti[0].setPropertiesDocumentoFisico(documentoFisicoProperty);
               	    

     	if(MimeType.MIME_TYPE_APPLICATION_XML.equalsIgnoreCase(documentoElettronicoActa.getMimeType()) && documentoElettronicoActa.getNomeFileXSL()!=null){
	     	properties.setComposizione(EnumDocPrimarioType.ALLEGATO_XML);
	     	    
	     	log.debug(method + ". Creo i due contenuti fisici");
	     	   
	     	ContenutoFisicoIRC[] contenuti = new ContenutoFisicoIRC[2];
	      	
	     	contenuti[0] = new ContenutoFisicoIRC();
	     	ContenutoFisicoPropertiesType contenutoFisicoPropertiesType = new ContenutoFisicoPropertiesType();
		    contenutoFisicoPropertiesType.setSbustamento(false);
		    contenuti[0].setPropertiesContenutoFisico(contenutoFisicoPropertiesType);
		      	
		    AcarisContentStreamType contentStream = creaContentStream(documentoElettronicoActa.getStream(), documentoElettronicoActa.getNomeFile(), EnumMimeTypeType.APPLICATION_XML);
		    contenuti[0].setStream(contentStream);
		    contenuti[0].setTipo(EnumStreamId.PRIMARY);
		      	
		    contenuti[1] = new ContenutoFisicoIRC();
		    contenuti[1].setPropertiesContenutoFisico(contenutoFisicoPropertiesType);
		       
		    log.debug(method + ". documentoElettronicoActa.getFileXSL(): " + documentoElettronicoActa.getFileXSL());
		    log.debug(method + ". documentoElettronicoActa.getNomeFileXSL(): " + documentoElettronicoActa.getNomeFileXSL());
		    AcarisContentStreamType contentStreamXSL = creaContentStream(documentoElettronicoActa.getFileXSL(), documentoElettronicoActa.getNomeFileXSL(), EnumMimeTypeType.APPLICATION_XSL);
		    if(contentStreamXSL != null){
		    contenuti[1].setStream(contentStreamXSL);
		    } else {
		    	log.debug(method + ". contentStreamXSL NULL");
		    }
		    contenuti[1].setTipo(EnumStreamId.RENDITION_ENGINE);
		      	
		    documenti[0].setContenutiFisici(contenuti);
     	}else {
     		properties.setComposizione(EnumDocPrimarioType.DOCUMENTO_SINGOLO);
       	    
        	ContenutoFisicoIRC[] contenuti = new ContenutoFisicoIRC[1];
          	
      		contenuti[0] = new ContenutoFisicoIRC();
          	ContenutoFisicoPropertiesType contenutoFisicoPropertiesType = new ContenutoFisicoPropertiesType();
          	contenutoFisicoPropertiesType.setSbustamento(false);
          	contenuti[0].setPropertiesContenutoFisico(contenutoFisicoPropertiesType);
          	AcarisContentStreamType contentStream = creaContentStream(documentoElettronicoActa.getStream(), documentoElettronicoActa.getNomeFile(), documentoElettronicoActa.getMimeType());
          	contenuti[0].setStream(contentStream);
          	contenuti[0].setTipo(EnumStreamId.PRIMARY);
          	        	
        	String digitalSignStepToBypass = "0001011";//TODO <TBD>
        	
        	StepErrorAction[] stepErrorAction = new StepErrorAction[7];

    		log.debug(method + ". ***************************stepErrorActionList: " + digitalSignStepToBypass);
    		
        	if(digitalSignStepToBypass != null && digitalSignStepToBypass.length() == 7) {
        		    		
    	    	if(digitalSignStepToBypass.charAt(0) == '1') {
    	        	StepErrorAction errorAction1 = new StepErrorAction();
    	        	errorAction1.setAction(EnumStepErrorAction.INSERT);
    	        	errorAction1.setStep(1);
    	    		stepErrorAction[0] = errorAction1;
    	    	}
    	    	if(digitalSignStepToBypass.charAt(1) == '1') {
    	        	StepErrorAction errorAction2 = new StepErrorAction();
    	        	errorAction2.setAction(EnumStepErrorAction.INSERT);
    	        	errorAction2.setStep(2);
    	    		stepErrorAction[1] = errorAction2;
    	    	}
    	    	if(digitalSignStepToBypass.charAt(2) == '1') {
    	        	StepErrorAction errorAction3 = new StepErrorAction();
    	        	errorAction3.setAction(EnumStepErrorAction.INSERT);
    	        	errorAction3.setStep(3);
    	    		stepErrorAction[2] = errorAction3;
    	    	}
    	    	if(digitalSignStepToBypass.charAt(3) == '1') {
    	        	StepErrorAction errorAction4 = new StepErrorAction();
    	        	errorAction4.setAction(EnumStepErrorAction.INSERT);
    	        	errorAction4.setStep(4);
    	    		stepErrorAction[3] = errorAction4;
    	    	}
    	    	if(digitalSignStepToBypass.charAt(4) == '1') {
    	        	StepErrorAction errorAction5 = new StepErrorAction();
    	        	errorAction5.setAction(EnumStepErrorAction.INSERT);
    	        	errorAction5.setStep(5);
    	    		stepErrorAction[4] = errorAction5;
    	    	}
    	    	if(digitalSignStepToBypass.charAt(5) == '1') {
    	        	StepErrorAction errorAction6 = new StepErrorAction();
    	        	errorAction6.setAction(EnumStepErrorAction.INSERT);
    	        	errorAction6.setStep(6);
    	    		stepErrorAction[5] = errorAction6;
    	    	}
    	    	if(digitalSignStepToBypass.charAt(6) == '1') {
    	        	StepErrorAction errorAction7 = new StepErrorAction();
    	        	errorAction7.setAction(EnumStepErrorAction.INSERT);
    	        	errorAction7.setStep(7);
    	    		stepErrorAction[6] = errorAction7;
    	    	}
    	    	
    	    	contenuti[0].setAzioniVerificaFirma(stepErrorAction);
    	    	documenti[0].setAzioniVerificaFirma(stepErrorAction);
        	}

          	documenti[0].setContenutiFisici(contenuti);
          	
     	}
     	
     	datiCreazione.setDocumentiFisici(documenti);
    	datiCreazione.setTipoDocumento(EnumTipoDocumentoArchivistico.DOCUMENTO_SEMPLICE);
        datiCreazione.setPropertiesDocumento(properties);	
    	datiCreazione.setPropertiesClassificazione(classificazionePropertiesType);
    	
    	log.debug(method + END);
    	

	}	

	
}
