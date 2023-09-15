/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.areapersonale.AreaPersonaleUtenteVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface AreaPersonaleController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/area-personale")
public interface AreaPersonaleController {
 
	/**
	 * Gets the dati utente corrente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the dati utente corrente
	 */
	@GET
	@Path("/utente-corrente")
	public GenericResponse<AreaPersonaleUtenteVO> getDatiUtenteCorrente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the utente corrente nome cognome.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the utente corrente nome cognome
	 */
	@GET
	@Path("/utente-corrente/nome-cognome")
	public GenericResponse<AreaPersonaleUtenteVO> getUtenteCorrenteNomeCognome(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Log logout.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the generic response
	 */
	@POST
	@Path("/logout")
	public GenericResponse<String> logLogout(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the ACL area personale.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL area personale
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLAreaPersonale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Verifica dati obbligatori area personale.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param areaPersonaleUtenteVO the area personale utente VO
	 * @return the generic response
	 */
	@POST
	@Path("/verifica-dati-obbligatori")
	public GenericResponse<String> verificaDatiObbligatoriAreaPersonale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull AreaPersonaleUtenteVO areaPersonaleUtenteVO);
	
	/**
	 * Update area personale utente corrente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param areaPersonaleUtenteVO the area personale utente VO
	 * @return the generic response
	 */
	@PUT
	@Path("/utente-corrente")
	public GenericResponse<DatiSoggVO> updateAreaPersonaleUtenteCorrente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull AreaPersonaleUtenteVO areaPersonaleUtenteVO);
	
}