/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.web;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import it.csi.tsddr.tsddrbl.business.be.exception.SessionException;
import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;

public class SessionUtil {
	
	private static Logger logger = Logger.getLogger(SessionUtil.class);
	
	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";
	public static final String IDDATISOGG_SESSIONATTR = "idDatiSoggcurrentUser";
	public static final String IDUTENTE_SESSIONATTR = "idUtentecurrentUser"; 
	public static final String IDPROFILO_SESSIONATTR = "idProfilocurrentUser";
	public static final String IRIDE_ID_SESSIONATTR = "iride2_id";
	public static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";
	
	public static <T> void setSessionAttribute(HttpSession httpSession, String attributeName, T attributeValue) {
		LoggerUtil.debug(logger, "[SessionHandler::setSessionAttribute] BEGIN");
		try {
			if(attributeValue == null) {
				throw new SessionException(String.format("Session attribute %s value is null", attributeName));
			}
			httpSession.setAttribute(attributeName, attributeValue);
		} catch(Exception e) {
			throw new SessionException(e.getMessage());
		}
		LoggerUtil.debug(logger, "[SessionHandler::setSessionAttribute] END");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttibute(HttpSession httpSession, String attributeName, Class<T> returnType) {
		LoggerUtil.debug(logger, "[SessionHandler::getSessionAttibute] BEGIN");
		T attributeValue = null;
		try {
			attributeValue = (T) (httpSession.getAttribute(attributeName));
			if(attributeValue == null) {
				throw new SessionException(String.format("Session attribute %s value is null", attributeName));
			}
		} catch(SessionException e) {
			throw e;
		} catch (Exception e) {
			throw new SessionException(e.getMessage());
		}
		LoggerUtil.debug(logger, "[SessionHandler::getSessionAttibute] END");
		return attributeValue;
	}
	
	public static <T> T getSessionAttibuteIgnoreNull(HttpSession httpSession, String attributeName, Class<T> returnType) {
		LoggerUtil.debug(logger, "[SessionHandler::getSessionAttibuteIgnoreNull] BEGIN");
		T attributeValue = null;
		try {
			attributeValue = httpSession.getAttribute(attributeName) != null ? getSessionAttibute(httpSession, attributeName, returnType) : null;	
		
		} catch (Exception e) {
			throw new SessionException(e.getMessage());
		}
		LoggerUtil.debug(logger, "[SessionHandler::getSessionAttibuteIgnoreNull] END");
		return attributeValue;
	}
	
	public static UserInfo getUserInfo(HttpSession httpSession) {
		return getSessionAttibute(httpSession, USERINFO_SESSIONATTR, UserInfo.class);
	}
	
	public static Long getIdProfilo(HttpSession httpSession) {
		return getSessionAttibute(httpSession, IDPROFILO_SESSIONATTR, Long.class);
	}
	
	public static Long getIdUtente(HttpSession httpSession) {
		return getUserInfo(httpSession).getIdUtente();
	}
	
	public static Long getIdDatiSoggetto(HttpSession httpSession) {
		return getUserInfo(httpSession).getIdDatiSogg();
	}
	
	public static String getCodiceFiscaleUtente(HttpSession httpSession) {
		return getUserInfo(httpSession).getCodFisc();
	}
	
	public static String getIpAddressUtente(HttpSession httpSession) {
		return getUserInfo(httpSession).getIpAddress();
	}

}
