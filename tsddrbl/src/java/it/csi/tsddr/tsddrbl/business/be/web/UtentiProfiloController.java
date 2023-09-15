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
import it.csi.tsddr.tsddrbl.vo.utenteprofilo.UtenteProfiloVO;

/**
 * The Interface UtentiProfiloController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/utenti-profilo")
public interface UtentiProfiloController {

	/**
	 * Gets the ACL utente profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL utente profilo
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLUtenteProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the lista utenti profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @return the lista utenti profilo
	 */
	@GET
	public GenericResponse<List<UtenteProfiloVO>> getListaUtentiProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
	/**
	 * Creates the utente profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	@POST
	public GenericResponse<UtenteProfiloVO> createUtenteProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
	/**
	 * Delete utente profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	@DELETE
	public GenericResponse<String> deleteUtenteProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente, 
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
	/**
	 * Gets the combo nuovo utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @return the combo nuovo utente
	 */
	@GET
	@Path("/combo-nuovo-utente")
	public GenericResponse<List<SelectVO>> getComboNuovoUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
}
