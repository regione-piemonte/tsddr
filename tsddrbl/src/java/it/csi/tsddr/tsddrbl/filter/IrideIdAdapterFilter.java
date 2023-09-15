/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package  it.csi.tsddr.tsddrbl.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import it.csi.tsddr.tsddrbl.business.be.exception.MalformedIdTokenException;
import it.csi.tsddr.tsddrbl.business.be.service.LoginService;
import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.filter.iride.entity.Identita;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;

/**
 * Inserisce in sessione:
 * <ul> 
 *  <li>l'identit&agrave; digitale relativa all'utente autenticato.
 *  <li>l'oggetto <code>currentUser</code>
 * </ul>
 * Funge da adapter tra il filter del metodo di autenticaizone previsto e la
 * logica applicativa.
 *
 * @author CSIPiemonte
 */
@Component
public class IrideIdAdapterFilter extends GenericFilterBean {
	
	protected static final Logger LOG = Logger.getLogger(IrideIdAdapterFilter.class);
	
	@Value("${idadapterfilter.devmode:false}")
	private boolean devmode;

	@Autowired
	private LoginService loginService;
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fchn)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpSession session = hreq.getSession();
		if (session.getAttribute(SessionUtil.IRIDE_ID_SESSIONATTR) == null) {
			String marker = getToken(hreq);
//			String marker = "KKNDNG98D28E960U//TEST USER/ACTALIS_EU/20201203143417/16/72Fm0h3s08aROqA0Up6IMg==";
			if (marker != null) {
				try {
					Identita identita = new Identita(normalizeToken(marker));
					LOG.debug("[IrideIdAdapterFilter::doFilter] Inserito in sessione marcatore IRIDE:" + identita);
					session.setAttribute(SessionUtil.IRIDE_ID_SESSIONATTR, identita);
					UserInfo userInfo = loginService.populateUserInfo(identita);
					session.setAttribute(SessionUtil.USERINFO_SESSIONATTR, userInfo);
				} catch (MalformedIdTokenException e) {
					LOG.error("[IrideIdAdapterFilter::doFilter] " + e.toString(), e);
				}
			} else {
				// il marcatore deve sempre essere presente altrimenti e' una 
				// condizione di errore (escluse le pagine home e di servizio)
				if (mustCheckPage(hreq.getRequestURI())) {
					LOG.error(
							"[IrideIdAdapterFilter::doFilter] Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
					throw new ServletException(
							"Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
				}
			}
		}
		
		UserInfo userInfo = SessionUtil.getSessionAttibuteIgnoreNull(session, SessionUtil.USERINFO_SESSIONATTR, UserInfo.class);
		if(!StringUtils.isBlank(hreq.getHeader("X-Forwarded-For"))) {
		    userInfo.setIpAddress(hreq.getHeader("X-Forwarded-For")); 
		} else {
		    userInfo.setIpAddress(hreq.getRemoteAddr());
		}
		session.setAttribute(SessionUtil.USERINFO_SESSIONATTR, userInfo);
		fchn.doFilter(req, resp);
	}

	private boolean mustCheckPage(String requestURI) {
		return true;
	}

	public void destroy() {
		// NOP
	}

	public String getToken(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getHeader(SessionUtil.AUTH_ID_MARKER);
		if (marker == null && devmode) {
			return getTokenDevMode(httpreq);
		} else if(marker == null) {
			return null;
		}else {
			try {
				// gestione dell'encoding
				String decodedMarker = new String(marker.getBytes("ISO-8859-1"), "UTF-8");
				return decodedMarker;
			} catch (java.io.UnsupportedEncodingException e) {
				// se la decodifica non funziona comunque sempre meglio restituire 
				// il marker originale non decodificato
				return marker;
			}
		}
	}

	private String getTokenDevMode(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getParameter(SessionUtil.AUTH_ID_MARKER);
		return marker;
	}

	private String normalizeToken(String token) {
		return token;
	}

}
