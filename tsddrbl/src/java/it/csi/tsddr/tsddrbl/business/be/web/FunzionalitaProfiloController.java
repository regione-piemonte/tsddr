/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface FunzionalitaProfiloController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/funzionalita-profilo")
public interface FunzionalitaProfiloController {

	/**
	 * Gets the combo funzionalita.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @return the combo funzionalita
	 */
	@GET
	@Path("/combo")
	public GenericResponse<List<SelectVO>> getComboFunzionalita(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
	/**
	 * Gets the lista funzionalita profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @param idFunzionalita the id funzionalita
	 * @return the lista funzionalita profilo
	 */
	@GET
	public GenericResponse<List<FunzionalitaProfiloVO>> getListaFunzionalitaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo,
			@QueryParam("idFunzionalita") Long idFunzionalita);
	
	/**
	 * Update funzionalita profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param FunzionalitaProfiloVO the funzionalita profilo VO
	 * @return the generic response
	 */
	@PUT
	public GenericResponse<FunzionalitaProfiloVO> updateFunzionalitaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull FunzionalitaProfiloVO FunzionalitaProfiloVO);
	
	/**
	 * Creates the funzionalita profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param FunzionalitaProfiloVO the funzionalita profilo VO
	 * @return the generic response
	 */
	@POST
	public GenericResponse<FunzionalitaProfiloVO> createFunzionalitaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull FunzionalitaProfiloVO FunzionalitaProfiloVO);
	
	/**
	 * Removes the funzionalita profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @param idFunzionalita the id funzionalita
	 * @return the generic response
	 */
	@DELETE
	public GenericResponse<FunzionalitaProfiloVO> removeFunzionalitaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo,
			@QueryParam("idFunzionalita") @NotNull Long idFunzionalita);
	
	/**
	 * Gets the ACL funzionalita profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL funzionalita profilo
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLFunzionalitaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the combo nuova funzionalita profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @return the combo nuova funzionalita profilo
	 */
	@GET
	@Path("/combo-nuova-funzionalita")
	public GenericResponse<List<SelectVO>> getComboNuovaFunzionalitaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
	/**
	 * Log conf funzionalita profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @param idFunzionalita the id funzionalita
	 * @return the generic response
	 */
	@POST
	@Path("/log-conf-funzionalita-profilo")
	public GenericResponse<String> logConfFunzionalitaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo, 
			@QueryParam("idFunzionalita") Long idFunzionalita);
	
}
