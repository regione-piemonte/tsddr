/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.web;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.tsddr.tsddrbl.business.be.service.FunzionalitaProfiloService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;

@Component
public class AclUtil {
	
	@Autowired
	private FunzionalitaProfiloService funzionalitaProfiloService;
	
	private static Logger logger = Logger.getLogger(AclUtil.class);
	
	public FunzionalitaProfiloVO getACLPerProfilo(HttpSession httpSession, CodiceFunzione codFunzione) {
		LoggerUtil.debug(logger, "[AclUtil::getACLPerProfilo] BEGIN");
		FunzionalitaProfiloVO funzProfAcl = getACLPerProfilo(SessionUtil.getIdProfilo(httpSession), codFunzione);
		LoggerUtil.debug(logger, "[AclUtil::getACLPerProfilo] END");
		return funzProfAcl;
		
	}
	
	public FunzionalitaProfiloVO getACLPerProfilo(Long idProfilo, CodiceFunzione codFunzione) {
		LoggerUtil.debug(logger, "[AclUtil::getACLPerProfilo] BEGIN");
		FunzionalitaProfiloVO funzProfAcl = funzionalitaProfiloService.getFunzionalitaProfiloByCodFunzione(idProfilo, codFunzione);
		LoggerUtil.debug(logger, "[AclUtil::getACLPerProfilo] END");
		return funzProfAcl;
	}
	
}
