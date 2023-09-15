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
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface ProfiliController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/profili")
public interface ProfiliController {
	
	/**
	 * Gets the lista profili.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the lista profili
	 */
	@GET
	public GenericResponse<List<ProfiloVO>> getListaProfili(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Update profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param profiloVO the profilo VO
	 * @return the generic response
	 */
	@PUT
	public GenericResponse<ProfiloVO> updateProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull ProfiloVO profiloVO);
	
	/**
	 * Creates the profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param profiloVO the profilo VO
	 * @return the generic response
	 */
	@POST
	public GenericResponse<ProfiloVO> createProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull ProfiloVO profiloVO);

	/**
	 * Removes the profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	@DELETE
	public GenericResponse<ProfiloVO> removeProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
	/**
	 * Gets the combo profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo profilo
	 */
	@GET
	@Path("/combo")
	public GenericResponse<List<SelectVO>> getComboProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the combo profilo all.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo profilo all
	 */
	@GET
	@Path("/combo-all")
	public GenericResponse<List<SelectVO>> getComboProfiloAll(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the ACL profili.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL profili
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLProfili(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Verifica profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param profiloVO the profilo VO
	 * @return the generic response
	 */
	@POST
	@Path("/verifica")
	public GenericResponse<String> verificaProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull ProfiloVO profiloVO);
	
	/**
     * Check if isProfiloBO
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @GET
    @Path("/is-profilo-BO")
    public GenericResponse<Boolean> isProfiloBO(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
            @QueryParam("idProfilo") @NotNull Long idProfilo);
    
}
