/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface GestoriController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/gestori")
public interface GestoriController {
	
	/**
	 * Restituisce i permessi (ACL) dell'utente corrente  sulle funzionalità della sezione Gestori.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL gestori
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLGestori(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Resituisce la combo dei gestori esistenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo gestore
	 */
	@GET
	@Path("/combo")
	public GenericResponse<List<SelectVO>> getComboGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Restituisce la lista dei filtri di ricerca applicati, sottoforma di stringa.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return the parametri filto applicati
	 */
	@GET
	@Path("/parametri-filtro-applicati")
	public GenericResponse<String> getParametriFiltoApplicati(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@BeanParam GestoreParametriRicerca parametriRicerca);
	
	/**
	 * Restituisce la lista dei gestori esistenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista gestori
	 */
	@GET
	public GenericResponse<List<GestoreVO>> getListaGestori(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@BeanParam GestoreParametriRicerca parametriRicerca);
	
	/**
	 * Restituisce il gestore specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idGestore the id gestore
	 * @return the gestore
	 */
	@GET
	@Path("/{idGestore}")
	public GenericResponse<GestoreVO> getGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("idGestore") @NotNull Long idGestore);
	
	/**
	 * Crea un nuovo gestore.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param gestoreVO the gestore VO
	 * @return the generic response
	 */
	@POST
	public GenericResponse<GestoreVO> createGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull GestoreVO gestoreVO);
	
	/**
	 * Aggiorna il gestore specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idGestore the id gestore
	 * @param gestoreVO the gestore VO
	 * @return the generic response
	 */
	@PUT
	@Path("/{idGestore}")
	public GenericResponse<GestoreVO> updatGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("idGestore") @NotNull Long idGestore, @NotNull GestoreVO gestoreVO);
	
	/**
	 * Rimuove il gestore specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idGestore the id gestore
	 * @return the generic response
	 */
	@DELETE
	@Path("/{idGestore}")
	public GenericResponse<GestoreVO> removeGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("idGestore") @NotNull Long idGestore);
	
	/**
	 * Verifica se esistono domande di accreditamento associate al gestore specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idGestore the id gestore
	 * @return the generic response
	 */
	@GET
	@Path("/{idGestore}/accreditamento/exists")
	public GenericResponse<Boolean> existsDomandeAccreditamentoGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("idGestore") @NotNull Long idGestore);
	
	/**
	 * Verifica se esistono impianti associati al gestore specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idGestore the id gestore
	 * @return the generic response
	 */
	@GET
	@Path("/{idGestore}/impianti/exists")
	public GenericResponse<Boolean> existsImpiantiGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("idGestore") @NotNull Long idGestore);
	
	/**
	 * Restituisce il rappresentante legale corrispondente al codice fiscale/p.iva specificato
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param codFiscPartiva the cod fisc partiva
	 * @return the rappresentante legale
	 */
	@GET
	@Path("/rappresentante-legale/{codFiscPartiva}")
	public GenericResponse<DatiSoggVO> getRappresentanteLegale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("codFiscPartiva") @NotNull String codFiscPartiva);
	
	/**
	 * Verifica se esiste già un gestore con il codice fiscale/p.iva specificto
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param codFiscPartiva the cod fisc partiva
	 * @return the generic response
	 */
	@GET
	@Path("/exists/{codFiscPartiva}")
	public GenericResponse<Boolean> existsGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("codFiscPartiva") @NotNull String codFiscPartiva);
	

	/**
	 * download report lista domande.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return excel con lista domande
	 */
	 @POST
	 @Path("/downloadReport")
	 GenericResponse<ReportVO> downloadReport(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
				@BeanParam GestoreParametriRicerca parametriRicerca);
}

