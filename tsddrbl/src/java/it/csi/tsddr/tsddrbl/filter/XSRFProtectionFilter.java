/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.filter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import it.csi.tsddr.tsddrbl.util.Constants;

/**
 * The type Xsrf protection filter.
 */
@Component
public class XSRFProtectionFilter extends GenericFilterBean {

    /**
     * The constant LOG.
     */
    final static Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");
    
    @Value("${xsrffilter.disabled:false}")
    private boolean isDisabled;

	private static final String XSRF_HEADER_NAME = "X-XSRF-TOKEN";

	/*
	 * nome del cookie XSRF
	 */
	private static final String XSRF_COOKIE_NAME = "XSRF-TOKEN";

	private static final String XSRF_INTERNAL_TOKEN_NAME = "XSRF_SESSION_TOKEN";

	@Override
	public void destroy() {
		// nothing to do
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;
		// get current session or create a new one
		HttpSession session = hreq.getSession();
		
		if (!isDisabled) {
			LOG.debug("[XSRFProtectionFilter::dofilter() - START]");
			if (!isRestRequest(hreq)) {
				LOG.debug("[XSRFProtectionFilter::dofilter() - request is NOT a REST request");
				if (!isXSRFSessionAlive(session)) {
					LOG.debug(
							"[XSRFProtectionFilter::dofilter() - XSRF token NOT already initialized: creating new one]");
					try {
						String newToken = createNewXSRFToken(session);
						addXSRFCookie(hresp, newToken);
						LOG.debug(
								"[XSRFProtectionFilter::dofilter() - new XSRF token created and added to session and response]");
					} catch (NoSuchAlgorithmException e) {
						throw new ServletException(e);
					}
				} else {
					LOG.debug("[XSRFProtectionFilter::dofilter() - XSRF token already initialized: nothing to do]");
				}
			} else { // rest request: token must be valid
			    checkTokenValidity(session, hreq);
				
			}
		}
		setNoCache(hresp);
		chain.doFilter(req, resp);
	}
	
	private void checkTokenValidity(HttpSession session, HttpServletRequest hreq) throws ServletException {
	    LOG.debug("[XSRFProtectionFilter::dofilter() - request is a REST request]");
        if (isXSRFSessionAlive(session)) {
            LOG.debug(
                    "[XSRFProtectionFilter::dofilter() - XSRF token already initialized => must check request]");
            if (validXSRFCookieAndHeader(hreq, session)) {
                LOG.debug("[XSRFProtectionFilter::dofilter() - XSRF cookie&header matches in request => OK]");
            } else {
                LOG.error("[XSRFProtectionFilter::dofilter() - Invalid XSRF Header in request]");
                throw new ServletException("Invalid XSRF HEADER");
            }
        } else {
            LOG.error("[XSRFProtectionFilter::dofilter() - XSRF token not already initialized]");
            throw new ServletException("XSRF TOKEN not already initialized");
        }
	}

	private void setNoCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
	}

	private boolean isRestRequest(HttpServletRequest hreq) {
		return hreq.getRequestURI() != null && hreq.getRequestURI().contains("restfacade");
	}

	private void addXSRFCookie(HttpServletResponse hresp, String token) {

		Cookie c = new Cookie(XSRF_COOKIE_NAME, token);
		c.setPath("/");

		hresp.addCookie(c);

	}

	private String createNewXSRFToken(HttpSession session) throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		String newToken = "" + random.nextLong() + "" + random.nextLong();
		session.setAttribute(XSRF_INTERNAL_TOKEN_NAME, newToken);
		return newToken;
	}

	private String getActualActiveXSRFToken(HttpSession session) {
		return (String) session.getAttribute(XSRF_INTERNAL_TOKEN_NAME);
	}

	private boolean validXSRFCookieAndHeader(HttpServletRequest hreq, HttpSession session) {
		String actualActiveToken = getActualActiveXSRFToken(session);
		String actualRequestHeader = getActualXSRFHeader(hreq);
		String actualRequestCookie = getActualXSRFCookie(hreq);
		return actualRequestHeader != null && actualActiveToken != null && actualActiveToken.equals(actualRequestHeader)
				&& actualActiveToken.equals(actualRequestCookie);
	}

	private String getActualXSRFHeader(HttpServletRequest hreq) {
		return (String) hreq.getHeader(XSRF_HEADER_NAME);
	}

	private String getActualXSRFCookie(HttpServletRequest hreq) {
		Cookie[] cookies = hreq.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().contentEquals(XSRF_COOKIE_NAME)) {
				return cookies[i].getValue();
			}
		}
		// if not found...
		return "";
	}

	private boolean isXSRFSessionAlive(HttpSession session) {
		return getActualActiveXSRFToken(session) != null;
	}

}