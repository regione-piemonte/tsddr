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
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoParametriRicercaVO;
import it.csi.tsddr.tsddrbl.vo.accreditamento.DomandaAccreditamentoVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface AccreditamentoController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/accreditamento")
public interface AccreditamentoController {
	
	/**
	 * Gets the ACL accreditamento.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL accreditamento
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLAccreditamento(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the lista domande.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista domande
	 */
	@GET
	public GenericResponse<List<DomandaAccreditamentoVO>> getListaDomande(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam DomandaAccreditamentoParametriRicercaVO parametriRicerca);
	
	/**
	 * Creates the domanda.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param domandaVO the domanda VO
	 * @return the generic response
	 */
	@POST
	public GenericResponse<DomandaAccreditamentoVO> createDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@NotNull DomandaAccreditamentoVO domandaVO);
	
	/**
	 * Gets the domanda.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDomanda the id domanda
	 * @return the domanda
	 */
	@GET
	@Path("/{idDomanda}")
	public GenericResponse<DomandaAccreditamentoVO> getDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idDomanda") @NotNull Long idDomanda);
	
	/**
	 * Update domanda.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDomanda the id domanda
	 * @param domandaVO the domanda VO
	 * @return the generic response
	 */
	@PUT
	@Path("/{idDomanda}")
	public GenericResponse<DomandaAccreditamentoVO> updateDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idDomanda") @NotNull Long idDomanda, @NotNull DomandaAccreditamentoVO domandaVO);
	
	/**
	 * Removes the domanda.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDomanda the id domanda
	 * @return the generic response
	 */
	@DELETE
	@Path("/{idDomanda}")
	public GenericResponse<DomandaAccreditamentoVO> removeDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idDomanda") @NotNull Long idDomanda);
	
	/**
	 * Verifica dati obbligatori accreditamento.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param domandaVO the domanda VO
	 * @return the generic response
	 */
	@POST
	@Path("/verifica-dati-obbligatori")
	public GenericResponse<String> verificaDatiObbligatoriAccreditamento(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@NotNull DomandaAccreditamentoVO domandaVO);
	
	/**
	 * Gets the combo gestori.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo gestori
	 */
	@GET
	@Path("/gestori/combo")
	public GenericResponse<List<SelectVO>> getComboGestori(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the combo stati domanda.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo stati domanda
	 */
	@GET
	@Path("/stati/combo")
	public GenericResponse<List<SelectVO>> getComboStatiDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the all combo stati domanda.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the all combo stati domanda
	 */
	@GET
	@Path("/stati/combo-all")
	public GenericResponse<List<SelectVO>> getAllComboStatiDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the combo richiedenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo richiedenti
	 */
	@GET
	@Path("/richiedenti/combo")
	public GenericResponse<List<SelectVO>> getComboRichiedenti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the combo profili.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDomanda the id domanda
	 * @return the combo profili
	 */
	@GET
	@Path("/{idDomanda}/profili/combo")
	public GenericResponse<List<SelectVO>> getComboProfili(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @PathParam("idDomanda") @NotNull Long idDomanda);
	
	/**
	 * Gets the parametri filtro applicati accreditamento.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return the parametri filtro applicati accreditamento
	 */
	@GET
	@Path("/parametri-filtro-applicati")
	public GenericResponse<String> getParametriFiltroApplicatiAccreditamento(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@BeanParam DomandaAccreditamentoParametriRicercaVO parametriRicerca);
	
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
	public GenericResponse<ReportVO> downloadReport(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam DomandaAccreditamentoParametriRicercaVO parametriRicerca);
	
	
}
