/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.util.JsonUtils;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.ErrorResponse;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	private static Logger log = Logger.getLogger(ExceptionHandlerFilter.class);
	
	@Autowired
	private MessaggioService messaggioService;
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			LoggerUtil.error(log, e.getMessage(), e);
			MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E008.name());
			String responseString = JsonUtils.toJsonString(ErrorResponse.build(messaggioVO));

			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
			response.getWriter().write(responseString);
		}
	}
}
