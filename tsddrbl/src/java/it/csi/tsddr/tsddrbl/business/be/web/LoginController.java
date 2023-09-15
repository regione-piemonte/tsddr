/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.web.bind.annotation.RequestHeader;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.menu.MenuCardVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.userinfo.UserInfoVO;

/**
 * The Interface LoginController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/login")
public interface LoginController {

	/**
	 * Gets the id profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the id profilo
	 */
	@GET
	@Path("/id-profilo")
	public GenericResponse<UserInfoVO> getIdProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @RequestHeader("X-Forwarded-For") String xForwardedFor);
	
	/**
	 * Gets the struttura menue card.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the struttura menue card
	 */
	@GET
	@Path("/struttura-menue-card")
	public GenericResponse<List<MenuCardVO>> getStrutturaMenueCard(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the combo profilo login.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo profilo login
	 */
	@GET
	@Path("/combo-profilo")
	public GenericResponse<List<SelectVO>> getComboProfiloLogin(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Log cambio profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	@POST
	@Path("/log-cambio-profilo")
	public GenericResponse<String> logCambioProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
}
