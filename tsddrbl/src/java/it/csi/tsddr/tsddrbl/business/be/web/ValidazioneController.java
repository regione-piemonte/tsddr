/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface ValidazioneController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/validazione")
public interface ValidazioneController {
	
	/**
	 * Valida il formato del codice fiscale speificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param codiceFiscale the codice fiscale
	 * @return the generic response
	 */
	@GET
	@Path("/verifica-formato-cf")
	public GenericResponse<String> verificaFormatoCodiceFiscale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("codiceFiscale") @NotNull String codiceFiscale);
	
	/**
	 * Valida il formato dell'indirizzo email specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param email the email
	 * @return the generic response
	 */
	@GET
	@Path("/verifica-formato-email")
	public GenericResponse<String> verificaFormatoEmail(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("email") @NotNull String email);
	
	/**
	 *  Valida il formato dell'indirizzo PEC specificato. 
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param pec the pec
	 * @return the generic response
	 */
	@GET
	@Path("/verifica-formato-pec")
	public GenericResponse<String> verificaFormatoPec(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("pec") @NotNull String pec);

}
