/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.helper.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametro;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrCParametroRepository;
import it.csi.tsddr.tsddrbl.integration.doqui.bean.Fruitore;
import it.csi.tsddr.tsddrbl.integration.doqui.exception.FruitoreException;
import it.csi.tsddr.tsddrbl.integration.doqui.utils.XmlSerializer;
import it.csi.tsddr.tsddrbl.util.OptionalUtil;
import it.csi.tsddr.tsddrbl.util.enums.Parametro;

public class CommonDocumentoHelperImpl {


	@Autowired
	private TsddrCParametroRepository parametroRepository;
	
	Fruitore fruitore = null;

	private static Logger log = Logger.getLogger(CommonDocumentoHelperImpl.class);	
	
	public Fruitore getFruitore() throws FruitoreException {
		String method = "getFruitore";
		
		if(fruitore != null) {
			if(log.isDebugEnabled()){
				log.debug(method + ". CnmTFruitore \n " + XmlSerializer.objectToXml(fruitore));
			}
			return fruitore;
		}
		
		try
		{	
			

			Optional<TsddrCParametro>  idAooActa = 			parametroRepository.findByNomeParametro(Parametro.ACTA_ID_AOO.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  idStrutturaActa = 	parametroRepository.findByNomeParametro(Parametro.ACTA_ID_STRUTTURA.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  idNodoActa = 		parametroRepository.findByNomeParametro(Parametro.ACTA_ID_NODO.getNome());// <TBD> TODO reperire dal db - 
			Optional<TsddrCParametro>  codFruitore = 		parametroRepository.findByNomeParametro(Parametro.ACTA_CODE_FRUITORE.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  cfActa =				parametroRepository.findByNomeParametro(Parametro.ACTA_CF.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  applicationKeyActa =	parametroRepository.findByNomeParametro(Parametro.ACTA_APP_KEY_FRUITORE.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  repositoryName = 	parametroRepository.findByNomeParametro(Parametro.ACTA_REPO_NAME_FRUITORE.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  codeEnte = 			parametroRepository.findByNomeParametro(Parametro.ACTA_CODE_ENTE_FRUITORE.getNome());//TODO reperire dal db - 
			
			String descrFruitore = OptionalUtil.getContent(codFruitore).getValoreParametroS();
			
			fruitore = new Fruitore(1, OptionalUtil.getContent(codFruitore).getValoreParametroS(), 
					descrFruitore, 
					OptionalUtil.getContent(cfActa).getValoreParametroS(), 
					OptionalUtil.getContent(idAooActa).getValoreParametroS(), 
					OptionalUtil.getContent(idStrutturaActa).getValoreParametroS(), 
					OptionalUtil.getContent(idNodoActa).getValoreParametroS(), 
					OptionalUtil.getContent(applicationKeyActa).getValoreParametroS(), 
					OptionalUtil.getContent(repositoryName).getValoreParametroS(), 
					OptionalUtil.getContent(codeEnte).getValoreParametroS());
			if(log.isDebugEnabled()){
				log.debug(method + ". CnmTFruitore \n " + XmlSerializer.objectToXml(fruitore));
			}
			
		}
		catch (Exception e)
		{
			log.error(method + ". Accesso al DAO Fallito " + e);
			throw new FruitoreException(e.getMessage());
		}

		return fruitore;
	
	}
}
