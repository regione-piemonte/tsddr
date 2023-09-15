/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * The type Cors filter.
 */
@Component
public class CorsFilter extends GenericFilterBean {
	
	@Value("${corsfilter.enablecors:false}")
	private boolean enableCors;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		if (enableCors) {
			HttpServletResponse res = (HttpServletResponse) servletResponse;
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			res.setHeader("Access-Control-Expose-Headers", "X-Page, X-Page-Size, X-Total-Elements, X-Total-Pages");
			res.setHeader("content-type", "application/json");
//			res.setHeader("Transfer-Encoding", "identity");
//			res.setHeader("Access-Control-Allow-Headers", "*");
//			res.setHeader("Access-Control-Expose-Headers", "X-Page, X-Page-Size, X-Total-Elements, X-Total-Pages, Transfer-Encoding");
		}
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
		if ( request.getMethod().equals("OPTIONS") ) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
		
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
	 // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

}