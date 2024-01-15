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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.impianto.AttoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.GenericLineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.CheckImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.impianto.LineaVO;
import it.csi.tsddr.tsddrbl.vo.impianto.SottoLineaVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface ImpiantiController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/impianti")
public interface ImpiantiController {
	
	/**
	 * Restituisce i permessi (ACL) dell'utente corrente sulle funzionalità della sezione Impianti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL impianti
	 */
	@GET
	@Path("/acl")
	public GenericResponse<FunzionalitaProfiloVO> getACLImpianti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Restituisce la compo dei tipi impianto esistenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo tipi impianto
	 */
	@GET
	@Path("/tipi/combo")
	public GenericResponse<List<SelectVO>> getComboTipiImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Resituisce la combo degli stati impianto esistenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo stati impianto
	 */
	@GET
	@Path("/stati/combo")
	public GenericResponse<List<SelectVO>> getComboStatiImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Restituisce la combo di tutte le linee e sottolinee esistenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo linee
	 */
	@GET
	@Path("/linee/combo")
	public GenericResponse<List<SelectVO>> getComboLinee(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Restituisce la combo delle linee e sottolinee associabili all'impianto specificato (ovvero quelle non già associate all'impianto).
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @return the combo linee impianto
	 */
	@GET
	@Path("/{idImpianto}/linee/combo")
	public GenericResponse<List<SelectVO>> getComboLineeImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@PathParam("idImpianto") @NotNull Long idImpianto);
	
	/**
	 * Restituisce la combo dei tipi di provvedimento esistenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo tipo provvedimento
	 */
	@GET
	@Path("/atti/combo") 
	public GenericResponse<List<SelectVO>> getComboTipoProvvedimento(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
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
			@BeanParam ImpiantoParametriRicerca parametriRicerca);
	
	/**
	 * Restituisce la lista degli impianti esistenti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista impianti
	 */
	@GET
	public GenericResponse<List<ImpiantoVO>> getListaImpianti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@BeanParam ImpiantoParametriRicerca parametriRicerca);
	
	/**
	 * Crea un nuovo impianto.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param impiantoVO the impianto VO
	 * @return the generic response
	 */
	@POST
	public GenericResponse<ImpiantoVO> addImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@NotNull ImpiantoVO impiantoVO);
	
	/**
	 * Restituisce l'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @return the impianto
	 */
	@GET
	@Path("/{idImpianto}")
	public GenericResponse<ImpiantoVO> getImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto);
	
	/**
	 * Aggiorna l'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param impiantoVO the impianto VO
	 * @return the generic response
	 */
	@PUT
	@Path("/{idImpianto}")
	public GenericResponse<ImpiantoVO> updateImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, @NotNull ImpiantoVO impiantoVO);
	
	/**
	 * Rimuove l'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @return the generic response
	 */
	@DELETE
	@Path("/{idImpianto}")
	public GenericResponse<ImpiantoVO> removeImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto);
	
	/**
	 * Resituisce la lista delle linee e sottolinee associate all'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @return the linee impianto
	 */
	@GET
	@Path("/{idImpianto}/linee")
	public GenericResponse<List<GenericLineaVO>> getLineeImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, @QueryParam("idPrevCons") String idPrevCons);
	
	/**
	 * Aggiunge la linea specificata all'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idLinea the id linea
	 * @param lineaVO the linea VO
	 * @return the generic response
	 */
	@POST
	@Path("/{idImpianto}/linee/{idLinea}")
	public GenericResponse<LineaVO> addLineaImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, 
			@PathParam("idLinea") @NotNull Long idLinea,
			@NotNull LineaVO lineaVO);
	
	/**
	 * Aggiorna la linea specificata dell'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idLinea the id linea
	 * @param lineaVO the linea VO
	 * @return the generic response
	 */
	@PUT
	@Path("/{idImpianto}/linee/{idLinea}")
	public GenericResponse<LineaVO> updateLineaImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, 
			@PathParam("idLinea") @NotNull Long idLinea, 
			@NotNull LineaVO lineaVO);

	/**
	 * Rimuove la linea specificata dall'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idLinea the id linea
	 * @return the generic response
	 */
	@DELETE
	@Path("/{idImpianto}/linee/{idLinea}")
	public GenericResponse<LineaVO> removeLineaImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto,
			@PathParam("idLinea") @NotNull Long idLinea);
	
	/**
	 * Aggiunge la sottolinea specificata all'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idSottoLinea the id sotto linea
	 * @param sottolineaVO the sottolinea VO
	 * @return the generic response
	 */
	@POST
	@Path("/{idImpianto}/sottolinee/{idSottoLinea}")
	public GenericResponse<SottoLineaVO> addSottoLineaImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, 
			@PathParam("idSottoLinea") @NotNull Long idSottoLinea,
			@NotNull SottoLineaVO sottolineaVO);
	
	/**
	 * Aggiorna la sottolinea specificata dell'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idSottoLinea the id sotto linea
	 * @param sottoLineaVO the sotto linea VO
	 * @return the generic response
	 */
	@PUT
	@Path("/{idImpianto}/sottolinee/{idSottoLinea}")
	public GenericResponse<SottoLineaVO> updateSottoLineaImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, 
			@PathParam("idSottoLinea") @NotNull Long idSottoLinea, 
			@NotNull SottoLineaVO sottoLineaVO);

	/**
	 * Rimuove la sottolinea specificata dall'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idSottoLinea the id sotto linea
	 * @return the generic response
	 */
	@DELETE
	@Path("/{idImpianto}/sottolinee/{idSottoLinea}")
	public GenericResponse<SottoLineaVO> removeSottoLineaImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, @PathParam("idSottoLinea") @NotNull Long idSottoLinea);
	
	/**
	 * Restituisce la lista degli atti associati all'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @return the atti impianto
	 */
	@GET
	@Path("/{idImpianto}/atti")
	public GenericResponse<List<AttoVO>> getAttiImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto);
	
	/**
	 * Aggiunge un nuovo atto all'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param attoVO the atto VO
	 * @return the generic response
	 */
	@POST
	@Path("/{idImpianto}/atti")
	public GenericResponse<AttoVO> addAttoImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto,
			@NotNull AttoVO attoVO);
	
	/**
	 * Aggiorna l'atto specificato dell'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idAtto the id atto
	 * @param attoVO the atto VO
	 * @return the generic response
	 */
	@PUT
	@Path("/{idImpianto}/atti/{idAtto}")
	public GenericResponse<AttoVO> updateAttoImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, 
			@PathParam("idAtto") @NotNull Long idAtto,
			@NotNull AttoVO attoVO);

	/**
	 * Rimuove l'atto specificato dall'impianto specificato.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idImpianto the id impianto
	 * @param idAtto the id atto
	 * @return the generic response
	 */
	@DELETE
	@Path("/{idImpianto}/atti/{idAtto}")
	public GenericResponse<AttoVO> removeAttoImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idImpianto") @NotNull Long idImpianto, @PathParam("idAtto") @NotNull Long idAtto);

	/**
	 * download report lista domande.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return excel con lista impianti
	 */
	 @POST
	 @Path("/downloadReport")
	 GenericResponse<ReportVO> downloadReport(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
				@BeanParam ImpiantoParametriRicerca parametriRicerca);
	 
	/**
     * Controlla se l'impianto specificato è eliminabile.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @param idImpianto the id impianto
     * @return the generic response
     */
	 @GET
	 @Path("/checkDelete/{idImpianto}")
	 public GenericResponse<CheckImpiantoVO> checkDeleteImpianto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			 @PathParam("idImpianto") @NotNull Long idImpianto);
}
