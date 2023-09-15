/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.facade.impl;

import java.io.File;
import java.nio.file.Path;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;
import it.csi.tsddr.tsddrbl.business.facade.DoquiServiceFacade;
import it.csi.tsddr.tsddrbl.integration.doqui.DoquiConstants;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.Documento;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.Metadati;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.RequestProtocollaDocumentoFisico;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.ResponseProtocollaDocumento;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.Soggetto;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.ProtocollaDocumentoException;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.RicercaDocumentoException;
import it.csi.tsddr.tsddrbl.integration.doqui.helper.ManageProtocollaDocumentoHelper;
import it.csi.tsddr.tsddrbl.integration.doqui.helper.ManageRicercaDocumentoHelper;
import it.csi.tsddr.tsddrbl.util.ParametroAcarisUtil;
import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;
import it.csi.tsddr.tsddrbl.vo.parametroacaris.ParametroAcarisDTO;

@Service
public class DoquiServiceFacadeImpl implements DoquiServiceFacade, InitializingBean {

	private static Logger logger = Logger.getLogger(DoquiServiceFacadeImpl.class);

	@Autowired
	private ManageProtocollaDocumentoHelper manageProtocollaDocumentoHelper;

	@Autowired
	private ManageRicercaDocumentoHelper manageRicercaDocumentoHelper;
	
	private String rootActa;

	private String soggettoActa;

	private String idEntitaFruitore;

	public static final String TOPOLOGIA_SOGGETTO_MITTENTE = "MITTENTE";
	public static final String TOPOLOGIA_SOGGETTO_DESTINATARIO = "DESTINATARIO";
	private static final String AUTORE_REGIONE_PIEMONTE = "REGIONE PIEMONTE";
	private static final String DICH_NON_VALIDO = "dichiarazione non valido";
	private static final String DOC_NON_VALIDO = "document non valido";
	private static final String NOMEFILE_NON_VALIDO = "nomeFile non valido";
	private static final String USER_NON_VALIDO = "user non valido";
	private static final String NOMEFILE_LOG = "[protocollaDocumentoFisico] -> nomeFile :: ";
	private static final String TSDDR = "TSDDR";
	private static final String NULL_VALUE = "NULL_VALUE";
	private static final String REQUEST_LOG = " - REQUEST :: ";
	private static final String ARROW_LOG = "[protocollaDocumentoFisico] -> ";
	private static final String TIPO_DOCUMENTO_LOG = "tipoDocumento :: ";
	private static final String PROTOCOLLA_DOC_EXCEPTION = "Protocolla Documento Exception:";
	private static final String EXCEPTION = "Exception:";
	
	@Autowired
	private ParametroAcarisUtil parametroAcarisUtil;

	@Override
	public ResponseProtocollaDocumento protocollaDocumentoFisico(TsddrTDichAnnuale dichiarazione, byte[] document, String nomeFile, TsddrTUtente user) throws ProtocollaDocumentoException {
		if (dichiarazione == null)
			throw new IllegalArgumentException(DICH_NON_VALIDO);
		if (document == null)
			throw new IllegalArgumentException(DOC_NON_VALIDO);
		if (nomeFile == null)
			throw new IllegalArgumentException(NOMEFILE_NON_VALIDO);
		if (user == null)
			throw new IllegalArgumentException(USER_NON_VALIDO);

		logger.info(NOMEFILE_LOG + nomeFile);

		ParametroAcarisDTO metadatiDB = parametroAcarisUtil.getParametroAcarisDTO();
		metadatiDB.setDichiarazioneAnno(dichiarazione.getAnno()+"");
		metadatiDB.setDichiarazioneData(dichiarazione.getDataDichiarazione());
		metadatiDB.setDichiarazioneDenominazioneSocieta(dichiarazione.getImpianto().getDenominazione());
		metadatiDB.setDichiarazioneIdentificativo(dichiarazione.getImpianto().getGestore().getCodFiscPartiva());
		metadatiDB.setDichiarazioneDescrizioneDocumento("Dichiarazione annuale deposito in discarica - " + metadatiDB.getDichiarazioneAnno() + " - " + metadatiDB.getDichiarazioneIdentificativo());
		
		RequestProtocollaDocumentoFisico request = new RequestProtocollaDocumentoFisico();

		request.setCodiceFruitore(DoquiConstants.CODICE_FRUITORE);
		request.setTipoDocumento(metadatiDB.getTipoDocumento());

		// fascicolo
		request.setFolder(getFolder(dichiarazione));

		// **********SOGGETTO
		Soggetto soggetto = new Soggetto();
		// **********METADATI
		Metadati metadati = new Metadati();

		soggetto.setCognome(TSDDR);
		soggetto.setNome(TSDDR);
		soggetto.setDenominazione(dichiarazione.getImpianto().getDenominazione());
		request.setSoggetto(soggetto);
		String mineType = getMimeType(nomeFile);
		request.setMimeType(mineType);

		// dossier
		request.setRootActa(rootActa); // TODO
		// soggetto
		request.setSoggettoActa(soggettoActa);// TODO

		// metadati
		metadati.setIdEntitaFruitore(idEntitaFruitore);
		request.setMetadati(metadati);

		// **********DOCUMENTO
		Documento documento = new Documento();
		if (document != null) {
			documento.setNomeFile(nomeFile);
			documento.setFile(document);
		} 
		request.setDocumento(documento);

		// autore fisico
		request.setAutoreFisico(NULL_VALUE); // si fa così per forzare a null

		// destinatario giuridico
		request.setDestinatarioGiuridico(AUTORE_REGIONE_PIEMONTE);

		// applicativo alimentante
		request.setApplicativoAlimentante(TSDDR);

		request.setProtocollazioneInUscitaSenzaDocumento(false);
		request.setAutoreFisico(user.getDatiSogg().getNome() + " " + user.getDatiSogg().getCognome());
		request.setDestinatarioFisico(null);
		request.setDestinatarioGiuridico(null);
		request.setMittentiEsterni(null);
		request.getSoggetto().setTipologia(TOPOLOGIA_SOGGETTO_DESTINATARIO);
//		request.getSoggetto().setCognome(null);
//		request.getSoggetto().setNome(null);
//		request.getSoggetto().setDenominazione(null);
		request.setAutoreGiuridico(AUTORE_REGIONE_PIEMONTE);
		request.setOriginatore(user.getDatiSogg().getNome() + " " + user.getDatiSogg().getCognome()); // utente
//		request.setCollocazioneCartacea(DoquiConstants.COLLOCAZIONE_CARTACEA);
		request.setParolaChiaveFolderTemp(null);	
//    	request.setDataTopica(DoquiConstants.LUOGO);
//    	request.setDataCronica(new Date()); // 
        
		logger.info(ARROW_LOG + TIPO_DOCUMENTO_LOG + metadatiDB.getTipoDocumento() + REQUEST_LOG + request);

		try {
			return manageProtocollaDocumentoHelper.protocollaDocumentoFisico(request, metadatiDB);
		} catch (ProtocollaDocumentoException e) {
			logger.error(PROTOCOLLA_DOC_EXCEPTION, e);
			throw e;
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
			throw e;
		}
	}

	private String getFolder(TsddrTDichAnnuale dichiarazione) {
		String folder = "TRISPE-W/" + dichiarazione.getImpianto().getGestore().getCodFiscPartiva() + " / " + dichiarazione.getAnno();
		return StringUtils.abbreviate(folder, 100);
	}
	
	private String getFolder(TsddrTPrevCons prevCons) {
		TsddrTImpianto tsddrTImpianto = prevCons.getImpianto();
		String folder = "TRISPE-W/" + tsddrTImpianto.getGestore().getCodFiscPartiva() + " / " + prevCons.getAnnoTributo();
		return StringUtils.abbreviate(folder, 100);
	}

	@Override
	public DocumentoProtocollatoVO ricercaProtocolloSuACTA(String numProtocollo) throws RicercaDocumentoException{
		if (StringUtils.isBlank(numProtocollo))
			throw new IllegalArgumentException("numProtocollo non valido");

		try {
			return manageRicercaDocumentoHelper.ricercaDocumentoProtocollato(numProtocollo);
		} catch (RicercaDocumentoException e) {
			logger.error("RicercaDocumento Exception:", e);
			throw e;
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
			throw e;
		}

	}

	@Override
	public ResponseProtocollaDocumento protocollaDocumentoFisicoDichiarazione(TsddrTPrevCons prevCons, byte[] document, String nomeFile, TsddrTUtente user) throws ProtocollaDocumentoException {
		if (prevCons == null)
			throw new IllegalArgumentException(DICH_NON_VALIDO);
		if (document == null)
			throw new IllegalArgumentException(DOC_NON_VALIDO);
		if (nomeFile == null)
			throw new IllegalArgumentException(NOMEFILE_NON_VALIDO);
		if (user == null)
			throw new IllegalArgumentException(USER_NON_VALIDO);

		logger.info(NOMEFILE_LOG + nomeFile);
		
		TsddrTImpianto tsddrTImpianto = prevCons.getImpianto();

		ParametroAcarisDTO metadatiDB = parametroAcarisUtil.getParametroAcarisDTO();
		metadatiDB.setDichiarazioneAnno(prevCons.getAnnoTributo()+"");
//		metadatiDB.setDichiarazioneData(prevCons.getDataDichiarazione());
		metadatiDB.setDichiarazioneDenominazioneSocieta(tsddrTImpianto.getDenominazione());
		metadatiDB.setDichiarazioneIdentificativo(tsddrTImpianto.getGestore().getCodFiscPartiva());
		metadatiDB.setDichiarazioneDescrizioneDocumento("Dichiarazione di conseguimento degli obiettivi - " + metadatiDB.getDichiarazioneAnno() + " - " + metadatiDB.getDichiarazioneIdentificativo());
		
		RequestProtocollaDocumentoFisico request = new RequestProtocollaDocumentoFisico();

		request.setCodiceFruitore(DoquiConstants.CODICE_FRUITORE);
		request.setTipoDocumento(metadatiDB.getTipoDocumento());

		// fascicolo
		request.setFolder(getFolder(prevCons));

		// **********SOGGETTO
		Soggetto soggetto = new Soggetto();
		// **********METADATI
		Metadati metadati = new Metadati();

		soggetto.setCognome(TSDDR);
		soggetto.setNome(TSDDR);
		soggetto.setDenominazione(tsddrTImpianto.getDenominazione());
		request.setSoggetto(soggetto);
		String mineType = getMimeType(nomeFile);
		request.setMimeType(mineType);

		// dossier
		request.setRootActa(rootActa); // TODO
		// soggetto
		request.setSoggettoActa(soggettoActa);// TODO

		// metadati
		metadati.setIdEntitaFruitore(idEntitaFruitore);
		request.setMetadati(metadati);

		// **********DOCUMENTO
		Documento documento = new Documento();
		if (document != null) {
			documento.setNomeFile(nomeFile);
			documento.setFile(document);
		} 
		request.setDocumento(documento);

		// autore fisico
		request.setAutoreFisico(NULL_VALUE); // si fa così per forzare a null

		// destinatario giuridico
		request.setDestinatarioGiuridico(AUTORE_REGIONE_PIEMONTE);

		// applicativo alimentante
		request.setApplicativoAlimentante(TSDDR);

		request.setProtocollazioneInUscitaSenzaDocumento(false);
		request.setAutoreFisico(user.getDatiSogg().getNome() + " " + user.getDatiSogg().getCognome());
		request.setDestinatarioFisico(null);
		request.setDestinatarioGiuridico(null);
		request.setMittentiEsterni(null);
		request.getSoggetto().setTipologia(TOPOLOGIA_SOGGETTO_DESTINATARIO);
//		request.getSoggetto().setCognome(null);
//		request.getSoggetto().setNome(null);
//		request.getSoggetto().setDenominazione(null);
		request.setAutoreGiuridico(AUTORE_REGIONE_PIEMONTE);
		request.setOriginatore(user.getDatiSogg().getNome() + " " + user.getDatiSogg().getCognome()); // utente
//		request.setCollocazioneCartacea(DoquiConstants.COLLOCAZIONE_CARTACEA);
		request.setParolaChiaveFolderTemp(null);	
//    	request.setDataTopica(DoquiConstants.LUOGO);
//    	request.setDataCronica(new Date()); // 
        
		logger.info(ARROW_LOG + TIPO_DOCUMENTO_LOG + metadatiDB.getTipoDocumento() + REQUEST_LOG + request);

		try {
			return manageProtocollaDocumentoHelper.protocollaDocumentoFisico(request, metadatiDB);
		} catch (ProtocollaDocumentoException e) {
			logger.error(PROTOCOLLA_DOC_EXCEPTION, e);
			throw e;
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
			throw e;
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public String getMimeType(String fileName) {
		Path path = new File(fileName).toPath();
		if(path.toString().endsWith(".PDF") || path.toString().endsWith(".pdf")) {
			return "application/pdf";
		}
		return null;
	}

    @Override
    public ResponseProtocollaDocumento protocollaDocumentoFisicoRichiesta(TsddrTPrevCons prevCons, byte[] document,
            String nomeFile, TsddrTUtente user) throws ProtocollaDocumentoException {
    	if (prevCons == null) {
    	    throw new IllegalArgumentException(DICH_NON_VALIDO);
    	}
		if (document == null) {
		    throw new IllegalArgumentException(DOC_NON_VALIDO);
		}
		if (nomeFile == null) {
		    throw new IllegalArgumentException(NOMEFILE_NON_VALIDO);
		}
		if (user == null) {
		    throw new IllegalArgumentException(USER_NON_VALIDO);
		}

		logger.info(NOMEFILE_LOG + nomeFile);
		
		TsddrTImpianto tsddrTImpianto = prevCons.getImpianto();

		ParametroAcarisDTO metadatiDB = parametroAcarisUtil.getParametroAcarisDTO();
		metadatiDB.setRichiesta(true);
		metadatiDB.setDichiarazioneAnno(prevCons.getAnnoTributo()+"");
//		metadatiDB.setDichiarazioneData(prevCons.getDataDichiarazione());
		metadatiDB.setDichiarazioneDenominazioneSocieta(tsddrTImpianto.getDenominazione());
		metadatiDB.setDichiarazioneIdentificativo(tsddrTImpianto.getGestore().getCodFiscPartiva());
		metadatiDB.setDichiarazioneDescrizioneDocumento("Richiesta di ammissione al pagamento in misura ridotta - " + metadatiDB.getDichiarazioneIdentificativo());
		
		RequestProtocollaDocumentoFisico request = new RequestProtocollaDocumentoFisico();

		request.setCodiceFruitore(DoquiConstants.CODICE_FRUITORE);
		request.setTipoDocumento(metadatiDB.getTipoDocumento());

		// fascicolo
		request.setFolder(getFolder(prevCons));

		// **********SOGGETTO
		Soggetto soggetto = new Soggetto();
		// **********METADATI
		Metadati metadati = new Metadati();

		soggetto.setCognome(TSDDR);
		soggetto.setNome(TSDDR);
		soggetto.setDenominazione(tsddrTImpianto.getDenominazione());
		request.setSoggetto(soggetto);
		String mineType = getMimeType(nomeFile);
		request.setMimeType(mineType);

		// dossier
		request.setRootActa(rootActa); // TODO
		// soggetto
		request.setSoggettoActa(soggettoActa);// TODO

		// metadati
		metadati.setIdEntitaFruitore(idEntitaFruitore);
		request.setMetadati(metadati);

		// **********DOCUMENTO
		Documento documento = new Documento();
		if (document != null) {
			documento.setNomeFile(nomeFile);
			documento.setFile(document);
		} 
		request.setDocumento(documento);

		// autore fisico
		request.setAutoreFisico(NULL_VALUE); // si fa così per forzare a null

		// destinatario giuridico
		request.setDestinatarioGiuridico(AUTORE_REGIONE_PIEMONTE);

		// applicativo alimentante
		request.setApplicativoAlimentante(TSDDR);

		request.setProtocollazioneInUscitaSenzaDocumento(false);
		request.setAutoreFisico(user.getDatiSogg().getNome() + " " + user.getDatiSogg().getCognome());
		request.setDestinatarioFisico(null);
		request.setDestinatarioGiuridico(null);
		request.setMittentiEsterni(null);
		request.getSoggetto().setTipologia(TOPOLOGIA_SOGGETTO_DESTINATARIO);
//		request.getSoggetto().setCognome(null);
//		request.getSoggetto().setNome(null);
//		request.getSoggetto().setDenominazione(null);
		request.setAutoreGiuridico(AUTORE_REGIONE_PIEMONTE);
		request.setOriginatore(user.getDatiSogg().getNome() + " " + user.getDatiSogg().getCognome()); // utente
//		request.setCollocazioneCartacea(DoquiConstants.COLLOCAZIONE_CARTACEA);
		request.setParolaChiaveFolderTemp(null);	
//    	request.setDataTopica(DoquiConstants.LUOGO);
//    	request.setDataCronica(new Date()); // 
        
		logger.info(ARROW_LOG + TIPO_DOCUMENTO_LOG + metadatiDB.getTipoDocumento() + REQUEST_LOG + request);

		try {
			return manageProtocollaDocumentoHelper.protocollaDocumentoFisico(request, metadatiDB);
		} catch (ProtocollaDocumentoException e) {
			logger.error(PROTOCOLLA_DOC_EXCEPTION, e);
			throw e;
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
			throw e;
		}
    }

	
}
