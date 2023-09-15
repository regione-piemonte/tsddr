/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.notainfo.NotaInfoVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface NoteInfoController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/note-info")
public interface NoteInfoController {

	/**
	 * Gets the nota info by cod nota info.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param codNotaInfo the cod nota info
	 * @return the nota info by cod nota info
	 */
	@GET
	@Path("/{cod-nota-info}")
	public GenericResponse<NotaInfoVO> getNotaInfoByCodNotaInfo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("cod-nota-info") String codNotaInfo);
	
}