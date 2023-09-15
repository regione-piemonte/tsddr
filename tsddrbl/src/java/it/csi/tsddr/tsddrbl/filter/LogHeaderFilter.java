/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import it.csi.tsddr.tsddrbl.util.Constants;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;


/**
 * The type Log header filter.
 */
@Component
public class LogHeaderFilter extends GenericFilterBean {

    private static final Logger log = Logger.getLogger(LogHeaderFilter.class);
    
	@Override
	public void destroy() {
		// nothing to do
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
	    
		HttpServletRequest hreq = (HttpServletRequest) req;
		manageLogSession(hreq);
		
		chain.doFilter(req, resp);
	}

	private void manageLogSession(HttpServletRequest hreq) {
		try {
//		    logRequestInfo(hreq);
			String userCf = hreq .getHeader(Constants.HEADER_USER_CF);
			String XRequestId = hreq .getHeader(Constants.X_REQUEST_ID);
			String XForwardedFor = hreq .getHeader(Constants.X_FORWARDED_FOR);
			
			if(XRequestId == null || XRequestId.length() == 0) {
				XRequestId = UUID.randomUUID().toString();
			}
			
			String header = "USER["+userCf+"]";
			header += " XRequestId["+XRequestId+"]";
			header += XForwardedFor==null?"":" XForwardedFor["+XForwardedFor+"]";
			
			MDC.put(Constants.LOG_HEADER_NAME, header);
			
			//per logAudit
			MDC.put(Constants.X_FORWARDED_FOR, XForwardedFor);
		}catch(Throwable t) {
			
		}
		
	}
	
	private void logRequestInfo(HttpServletRequest hreq) {
        try {
           var sb = new StringBuilder();
           sb.append("VERB= [" + hreq.getMethod()+ "] ");
           sb.append("URI= [" + hreq.getRequestURI()+ "] ");
           Enumeration<String> headerNames = hreq.getHeaderNames();
           sb.append("HEADERS= [");
           if (headerNames != null) {
                   while (headerNames.hasMoreElements()) {
                       String headerName = headerNames.nextElement();
                       sb.append("[")
                       .append("Name: ")
                       .append(headerName)
                       .append(" - ")
                       .append("Value: ")
                       .append(hreq.getHeader(headerName))
                       .append("]");
                   }
           }
           sb.append("]");
           LoggerUtil.info(log, String.format("[LogHeaderFilter::logRequestInfo] REQUEST DATA [%s]", sb));
        }catch(Throwable t) {
            
        }
        
    }

}