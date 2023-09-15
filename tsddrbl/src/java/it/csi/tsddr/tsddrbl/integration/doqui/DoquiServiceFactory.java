/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrCParametro;
import it.csi.tsddr.tsddrbl.business.be.repository.TsddrCParametroRepository;
import it.csi.tsddr.tsddrbl.util.OptionalUtil;
import it.csi.tsddr.tsddrbl.util.enums.Parametro;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;

@Service
public class DoquiServiceFactory {

	private DoquiService acarisService;

	@Autowired
	private TsddrCParametroRepository parametroRepository;
	
	public DoquiService getAcarisService() {
		if(acarisService == null) {

			Optional<TsddrCParametro>  actaServer = 			parametroRepository.findByNomeParametro(Parametro.ACTA_SERVER.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  actaContext = 			parametroRepository.findByNomeParametro(Parametro.ACTA_CONTEXT.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  actaPort = 				parametroRepository.findByNomeParametro(Parametro.ACTA_PORT.getNome());// <TBD> TODO reperire dal db - 
			Optional<TsddrCParametro>  apiManagerUser = 		parametroRepository.findByNomeParametro(Parametro.APIMANAGER_CONSUMERKEY.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  apiManagerPassword =		parametroRepository.findByNomeParametro(Parametro.APIMANAGER_CONSUMERSECRET.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  apiManagerOauthUrl =		parametroRepository.findByNomeParametro(Parametro.APIMANAGER_OAUTHURL.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  apiManagerRootUrl = 		parametroRepository.findByNomeParametro(Parametro.APIMANAGER_URL.getNome());//TODO reperire dal db - 
			Optional<TsddrCParametro>  apiManagerRootUrlEnd = 	parametroRepository.findByNomeParametro(Parametro.APIMANAGER_URL_END.getNome());//TODO reperire dal db - 
			
			OauthHelper apiManagerOauth = new OauthHelper(OptionalUtil.getContent(apiManagerOauthUrl).getValoreParametroS(),
					OptionalUtil.getContent(apiManagerUser).getValoreParametroS(),
					OptionalUtil.getContent(apiManagerPassword).getValoreParametroS()
					);
			
			boolean actaIsWS = true;
			boolean isApiManagerEnabled= true;
						
			acarisService = new DoquiService(OptionalUtil.getContent(actaServer).getValoreParametroS(), 
					OptionalUtil.getContent(actaContext).getValoreParametroS(), 
					OptionalUtil.getContent(actaPort).getValoreParametroN().intValue(), 
					actaIsWS, 
					apiManagerOauth, 
					OptionalUtil.getContent(apiManagerUser).getValoreParametroS(), 
					OptionalUtil.getContent(apiManagerPassword).getValoreParametroS(), 
					isApiManagerEnabled, 
					OptionalUtil.getContent(apiManagerRootUrl).getValoreParametroS(), 
					OptionalUtil.getContent(apiManagerRootUrlEnd).getValoreParametroS());
//			acarisService = new DoquiService("tst-applogic.reteunitaria.piemonte.it", "/actasrv/", 80, true);
		}
		return acarisService;
		
	}
		
		
}
