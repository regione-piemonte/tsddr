/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.handler;

import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import it.csi.tsddr.tsddrbl.business.SpringApplicationContextHelper;
import it.csi.tsddr.tsddrbl.business.be.exception.FunctionalException;
import it.csi.tsddr.tsddrbl.business.be.exception.GenericException;
import it.csi.tsddr.tsddrbl.business.be.service.MessaggioService;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceMessaggio;
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.response.ErrorResponse;

/**
 * The type Exception handler.
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<RuntimeException> {

	private static Logger logger = Logger.getLogger(ExceptionHandler.class);
	
	private MessaggioService messaggioService;

	@Override
	public Response toResponse(RuntimeException e) {
		messaggioService = messaggioService != null ? messaggioService : (MessaggioService) SpringApplicationContextHelper.getBean("messaggioServiceImpl");
		
		if(e instanceof DataAccessException) {
			LoggerUtil.error(logger, "DBMS_OUT: ", e);
			
		} else if (e instanceof FunctionalException) {
			FunctionalException fe = (FunctionalException) e;
			
			LoggerUtil.error(logger, fe.getMessaggiVO() != null ? fe.getMessaggiVO().stream().map(MessaggioVO::getTestoMsg).collect(Collectors.joining(",")) : "", e);
			
		} else {
			LoggerUtil.error(logger, "ERROR: ", e);
		}
			
		if (e instanceof FunctionalException) {
			return Response.status(Status.BAD_REQUEST).entity(ErrorResponse.build(((FunctionalException) e).getMessaggiVO())).build();
		
		} else if (e instanceof GenericException || e instanceof DataAccessException) {
			MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E008.name());
			return Response.status(Status.BAD_REQUEST).entity(ErrorResponse.build(messaggioVO)).build();
		}
		
		MessaggioVO messaggioVO = messaggioService.getMessaggioByCodMsg(CodiceMessaggio.E008.name());
		return Response.serverError().entity(ErrorResponse.build(messaggioVO)).build();
	}
}
