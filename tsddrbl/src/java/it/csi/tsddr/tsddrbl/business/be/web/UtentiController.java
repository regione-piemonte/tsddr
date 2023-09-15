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
import it.csi.tsddr.tsddrbl.vo.gestioneutente.UtenteVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.utente.DatiUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.InsertUtenteVO;
import it.csi.tsddr.tsddrbl.vo.utente.UtenteParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.utentegestoreprofilo.UtenteGestoreProfiloVO;

/**
 * The Interface UtentiController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/utenti")
public interface UtentiController {
	
	/**
	 * Verifica parametri ricerca.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param utenteParametriRicercaVO the utente parametri ricerca VO
	 * @return the generic response
	 */
	@POST
	@Path("/verifica-parametri-ricerca")
	public GenericResponse<String> verificaParametriRicerca(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull UtenteParametriRicercaVO utenteParametriRicercaVO);
	
	/**
	 * Gets the griglia utenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param utenteParametriRicercaVO the utente parametri ricerca VO
	 * @return the griglia utenti
	 */
	@POST
	@Path("/griglia-utenti")
	public GenericResponse<List<DatiUtenteVO>> getGrigliaUtenti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull UtenteParametriRicercaVO utenteParametriRicercaVO);
	
	/**
	 * Gets the parametri filtro applicati.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param utenteParametriRicercaVO the utente parametri ricerca VO
	 * @return the parametri filtro applicati
	 */
	@POST
	@Path("/parametri-filtro-applicati")
	public GenericResponse<String> getParametriFiltroApplicati(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull UtenteParametriRicercaVO utenteParametriRicercaVO);
	
	/**
	 * Gets the ACL utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL utente
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the dati utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @return the dati utente
	 */
	@GET
	@Path("/dati-utente")
	public GenericResponse<DatiUtenteVO> getDatiUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente);
	
	/**
	 * Gets the dati utente gestori.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the dati utente gestori
	 */
	@GET
	@Path("/dati-utente/gestori")
	public GenericResponse<List<UtenteGestoreProfiloVO>> getDatiUtenteGestori(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente,
			@QueryParam("idProfilo") Long idProfilo);
	
	/**
	 * Gets the profili utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @return the profili utente
	 */
	@GET
	@Path("/combo-profili")
	public GenericResponse<List<SelectVO>> getProfiliUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente);
	
	/**
	 * Update utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param datiUtenteVO the dati utente VO
	 * @return the generic response
	 */
	@PUT
	public GenericResponse<DatiUtenteVO> updateUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull DatiUtenteVO datiUtenteVO);
	
	/**
	 * Creates the utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param insertUtenteVO the insert utente VO
	 * @return the generic response
	 */
	@POST
	public GenericResponse<UtenteVO> createUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull InsertUtenteVO insertUtenteVO);
	
	/**
	 * Removes the utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @return the generic response
	 */
	@DELETE
	public GenericResponse<String> removeUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente);
	
	/**
	 * Verifica utente.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param codiceFiscale the codice fiscale
	 * @return the generic response
	 */
	@GET
	@Path("/verifica-utente")
	public GenericResponse<String> verificaUtente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("codiceFiscale") @NotNull String codiceFiscale);
	
	/**
	 * Gets the combo griglia gestori.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @param idProfilo the id profilo
	 * @return the combo griglia gestori
	 */
	@GET
	@Path("/combo-nuovo-gestore")
	public GenericResponse<List<SelectVO>> getComboGrigliaGestori(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
	/**
	 * Creates the utente gestore profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param utenteGestoreProfiloVO the utente gestore profilo VO
	 * @return the generic response
	 */
	@POST
	@Path("/utente-gestore-profilo")
	public GenericResponse<UtenteGestoreProfiloVO> createUtenteGestoreProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull UtenteGestoreProfiloVO utenteGestoreProfiloVO);
	
	/**
	 * Update utente gestore profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param utenteGestoreProfiloVO the utente gestore profilo VO
	 * @return the generic response
	 */
	@PUT
	@Path("/utente-gestore-profilo")
	public GenericResponse<UtenteGestoreProfiloVO> updateUtenteGestoreProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull UtenteGestoreProfiloVO utenteGestoreProfiloVO);
	
	/**
	 * Removes the utente gestore profilo.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idUtente the id utente
	 * @param idGestore the id gestore
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	@DELETE
	@Path("/utente-gestore-profilo")
	public GenericResponse<String> removeUtenteGestoreProfilo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idUtente") @NotNull Long idUtente,
			@QueryParam("idGestore") @NotNull Long idGestore,
			@QueryParam("idProfilo") @NotNull Long idProfilo);
	
}
