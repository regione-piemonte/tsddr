/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.helper.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.cfg.beanvalidation.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.tsddr.tsddrbl.integration.doqui.bean.DocumentoElettronicoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.Fruitore;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.KeyDocumentoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.MetadatiActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.RequestProtocollaDocumentoFisico;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.ResponseProtocollaDocumento;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.Soggetto;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.SoggettoActa;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.UtenteActa;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.ProtocollaDocumentoException;
import it.csi.tsddr.tsddrbl.integration.doqui.helper.ManageProtocollaDocumentoHelper;
import it.csi.tsddr.tsddrbl.integration.doqui.service.ActaManagementService;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.XmlSerializer;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;
import it.doqui.acta.actasrv.dto.acaris.type.archive.IdStatoDiEfficaciaType;
import it.doqui.acta.actasrv.dto.acaris.type.archive.StatoDiEfficaciaType;
import it.doqui.acta.actasrv.dto.acaris.type.common.EnumMimeTypeType;

@Service
public class ManageProtocollaDocumentoHelperImpl extends CommonDocumentoHelperImpl implements ManageProtocollaDocumentoHelper
{

//	private static final String LOGGER_PREFIX = DoquiConstants.LOGGER_PREFIX + ".helper";
//	private static final Logger log = Logger.getLogger(LOGGER_PREFIX);
	private static Logger log = Logger.getLogger(ManageProtocollaDocumentoHelperImpl.class);	

	@Autowired
	private ActaManagementService	actaManagementService;
	

	private boolean isCognomeNomeODenominazioneValidata(Soggetto soggetto)
	{
		boolean result = false;
		if ((soggetto.getCognome() != null) && (soggetto.getNome() != null))
			result = true;
		if (soggetto.getDenominazione() != null)
			result = true;

		return result;
	}

	private void requestValidation(RequestProtocollaDocumentoFisico request) throws ProtocollaDocumentoException {
	    if(request == null) throw new ProtocollaDocumentoException("Request non valorizzata");
        if(request.getSoggetto() == null) throw new ProtocollaDocumentoException("Soggetto non valorizzato");
        if(request.getSoggetto().getTipologia() == null) throw new ProtocollaDocumentoException("Tipologia soggetto non valorizzato");
        if(request.getMimeType() == null) throw new ProtocollaDocumentoException("MimeType non valorizzato");

        try
        {
            EnumMimeTypeType.fromValue(request.getMimeType());

        }
        catch (IllegalArgumentException e) 
        {
            throw new ProtocollaDocumentoException("MimeType non corretto");
        }

        if (!isCognomeNomeODenominazioneValidata(request.getSoggetto()))
            throw new ProtocollaDocumentoException("Cognome, Nome o Denominazione soggetto non valorizzato");

        if(StringUtils.isBlank(request.getCodiceFruitore())) throw new ProtocollaDocumentoException("Codice fruitore non presente");
        if(StringUtils.isBlank(request.getTipoDocumento())) throw new ProtocollaDocumentoException("Tipo documento non presente");
        if(StringUtils.isBlank(request.getFolder())) throw new ProtocollaDocumentoException("Folder non presente");
	}
	
	private void check20210421_LC(RequestProtocollaDocumentoFisico request, DocumentoElettronicoActa documentoElettronicoActa, String soggettoActaFruitore) {
	    if(StringUtils.isNotEmpty(soggettoActaFruitore))
            documentoElettronicoActa.setFruitore(soggettoActaFruitore);
        else
            documentoElettronicoActa.setFruitore(fruitore.getDescrFruitore());
        if(request.getApplicativoAlimentante() != null)
            documentoElettronicoActa.setApplicativoAlimentante(request.getApplicativoAlimentante());
        else
            documentoElettronicoActa.setApplicativoAlimentante(fruitore.getDescrFruitore());

	}

	@SuppressWarnings("unused")
	@Transactional(propagation=Propagation.REQUIRED)	
	public ResponseProtocollaDocumento protocollaDocumentoFisico(RequestProtocollaDocumentoFisico request, ParametroAcarisDTO metadatiDB/*, String rootActa, String soggettoActaFruitore, boolean isProtocollazioneInUscitaSenzaDocumento*/) throws ProtocollaDocumentoException
	{
		String method = "protocollaDocumentoFisico";
		log.debug(method + ". BEGIN");

		ResponseProtocollaDocumento response =  new ResponseProtocollaDocumento();
		boolean containsError = false;


		String rootActa = request.getRootActa();
		String soggettoActaFruitore = request.getSoggettoActa();
		String parolaChiaveFolderTemp = request.getParolaChiaveFolderTemp();

		try
		{
			// validazioni 	
		    requestValidation(request);

			request.setTipoDocumento(metadatiDB.getTipoDocumento()); //parametri_acaris.tipo_documento
			
			
			// recupero fruitore
			Fruitore fruitore = getFruitore();
				

			//POJO
			UtenteActa utenteActa = new UtenteActa();
			utenteActa.setCodiceFiscale(fruitore.getCfActa());
			utenteActa.setIdAoo(new Integer(fruitore.getIdAooActa()));
			utenteActa.setIdNodo(new Integer(fruitore.getIdNodoActa()));
			utenteActa.setIdStruttura(new Integer(fruitore.getIdStrutturaActa()));
			utenteActa.setApplicationKeyActa(fruitore.getAppKey());
			utenteActa.setRepositoryName(fruitore.getRepoName());

			if(StringUtils.isNotEmpty(rootActa))
				utenteActa.setRootActa(rootActa);
			else
				utenteActa.setRootActa(null);//cnmDTipoDocumento.getRoot());

			utenteActa.setIdvitalrecordcodetype(0); // TODO //parametri_acaris.Vital record code
			utenteActa.setIdStatoDiEfficacia(8); // PERFETTO ED EFFICACE MA NON FIRMATO
			
			//POJO
			DocumentoElettronicoActa documentoElettronicoActa = new DocumentoElettronicoActa();
			documentoElettronicoActa.setIdDocumento("<TBD>");//cnmTDocumento.getIdentificativoArchiviazione());
			documentoElettronicoActa.setFolder(request.getFolder());
			documentoElettronicoActa.setDocumentoCartaceo(metadatiDB.getCartaceo());
			
			// 20200731_LC
			documentoElettronicoActa.setCollocazioneCartacea(request.getCollocazioneCartacea());  //parametri_acaris.collocazione_cartacea

			// 20210421_LC
			check20210421_LC(request, documentoElettronicoActa, soggettoActaFruitore);

			documentoElettronicoActa.setStream(request.getDocumento().getFile());
			documentoElettronicoActa.setNomeFile(request.getDocumento().getNomeFile());
			documentoElettronicoActa.setFileXSL(request.getDocumento().getFileXSL());
			documentoElettronicoActa.setNomeFileXSL(request.getDocumento().getNomeFileXSL());
			documentoElettronicoActa.setDescrizione(metadatiDB.getTipoDocumento());//cnmDTipoDocumento.getDescrTipoDocumento());
			documentoElettronicoActa.setMimeType(request.getMimeType());
			documentoElettronicoActa.setNumeroAllegati(request.getDocumento().getNumeroAllegati());

			//documentoElettronicoActa.setTipoStrutturaRoot(cnmDTipoDocumento.getIdStrutturaActaRoot());
			//documentoElettronicoActa.setTipoStrutturaFolder(cnmDTipoDocumento.getIdStrutturaActaFolder());
			documentoElettronicoActa.setTipoStrutturaRoot(3); //"<TBD>");//cnmDTipoDocumento.getCnmDStrutturaActaRoot().getIdStrutturaActa());		// 20200630_LC
			documentoElettronicoActa.setTipoStrutturaFolder(1); //"<TBD>");//cnmDTipoDocumento.getCnmDStrutturaActaFolder().getIdStrutturaActa());	// 20200630_LC


			if(request.getMetadati() != null)
			{
				MetadatiActa metadatiActa = new MetadatiActa();
				metadatiActa.setCodiceFiscale(request.getMetadati().getCodiceFiscale());
				metadatiActa.setIdEntitaFruitore(request.getMetadati().getIdEntitaFruitore());
				metadatiActa.setTarga(request.getMetadati().getTarga());
				metadatiActa.setNumeroRegistrazionePrecedente(request.getNumeroRegistrazionePrecedente());
				metadatiActa.setAnnoRegistrazionePrecedente(request.getAnnoRegistrazionePrecedente());
				metadatiActa.setScrittore(request.getScrittore());
				metadatiActa.setDestinatarioFisico(request.getDestinatarioFisico());
				metadatiActa.setDestinatarioGiuridico(request.getDestinatarioGiuridico());
				metadatiActa.setDescrizioneTipoLettera(request.getDescrizioneTipoLettera());
				documentoElettronicoActa.setMetadatiActa(metadatiActa);
			}


			SoggettoActa soggettoActa = new SoggettoActa();
			soggettoActa.setMittente(true);
			documentoElettronicoActa.setAutore(request.getSoggetto().getCognome());
			log.debug(method + ". getDescFormadocEntrata(): " + metadatiDB.getFormaDocumentaria_TipologiaDocumentale());
			utenteActa.setDescFormaDocumentaria(metadatiDB.getFormaDocumentaria_TipologiaDocumentale());
			utenteActa.setDescEnte(fruitore.getCodeEnte());
			soggettoActa.setCognome(request.getSoggetto().getCognome());
			soggettoActa.setNome(request.getSoggetto().getNome());
			if (request.getSoggetto().getDenominazione() != null)
				soggettoActa.setDenominazione(request.getSoggetto().getDenominazione());

			documentoElettronicoActa.setSoggettoActa(soggettoActa);

			documentoElettronicoActa.setAutoreFisico(request.getAutoreFisico());
			documentoElettronicoActa.setAutoreGiuridico(metadatiDB.getDichiarazioneDenominazioneSocieta());
			documentoElettronicoActa.setDestinatarioGiuridico(request.getDestinatarioGiuridico());
			documentoElettronicoActa.setMittentiEsterni(metadatiDB.getDichiarazioneDenominazioneSocieta());
			documentoElettronicoActa.setOriginatore(request.getOriginatore());
			documentoElettronicoActa.setDestinatarioFisico(request.getDestinatarioFisico());
			

	        if (request.getDataCronica() != null) documentoElettronicoActa.setDataCronica(request.getDataCronica());
	        if (request.getDataTopica() != null) documentoElettronicoActa.setDataTopica(request.getDataTopica());

			KeyDocumentoActa keyDocumentoActa = actaManagementService.protocollaDocumentoFisico(documentoElettronicoActa, utenteActa, true, metadatiDB);


			if (keyDocumentoActa != null)
			{
				response.setProtocollo(keyDocumentoActa.getNumeroProtocollo());
				response.setObjectIdDocumento(String.valueOf(keyDocumentoActa.getObjectIdDocumento()));
				response.setIdFolder(keyDocumentoActa.getIdFolderCreated());
			}else
				containsError = true;

			return response;

		}
		catch (IntegrationException e) 
		{
			containsError = true;
			log.error(method + ". IntegrationException: " + e);
			throw new ProtocollaDocumentoException(e.getMessage());
		}
		catch (Exception e) 
		{
			containsError = true;
			log.error(method + ". Exception: " + e);
			throw new ProtocollaDocumentoException(e.getMessage());
		}
		finally
		{
			log.info(method + ". END");
		}
	}
	

}
