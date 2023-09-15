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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.DocumentoProtocollatoVO;
import it.csi.tsddr.tsddrbl.vo.ReportVO;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeBasicVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichiarazioneParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.AllowDuplicaDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.ExistsDichiarazioneVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.SoggettoMrVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface DichiarazioniController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/dichiarazioni")
public interface DichiarazioniController {

	/**
	 * Gets the ACL dichiarazioni.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the ACL dichiarazioni
	 */
	@GET
	@Path("/acl")
	GenericResponse<FunzionalitaProfiloVO> getACLDichiarazioni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the combo anni.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo anni
	 */
	@GET
	@Path("/anni/combo")
	GenericResponse<List<Long>> getComboAnni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the combo gestore.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @return the combo gestore
	 */
	@GET
	@Path("/gestore/combo")
	GenericResponse<List<SelectVO>> getComboGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the combo impianti.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idGestore the id gestore
	 * @return the combo impianti
	 */
	@GET
	@Path("/impianti/combo")
	GenericResponse<List<SelectVO>> getComboImpianti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
		@QueryParam("idGestore") Long idGestore);
	
	/**
	 * Gets the parametri filto applicati.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return the parametri filto applicati
	 */
	@GET
	@Path("/parametri-filtro-applicati")
	GenericResponse<String> getParametriFiltroApplicati(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam DichiarazioneParametriRicerca parametriRicerca);
	
	/**
	 * Gets the lista dichiarazioni.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param parametriRicerca the parametri ricerca
	 * @return the lista dichiarazioni
	 */
	@GET
	GenericResponse<List<DichAnnualeBasicVO>> getListaDichiarazioni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam DichiarazioneParametriRicerca parametriRicerca);

	/**
	 * Download dichiarazione annuale.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDichAnnuale the id dich annuale
	 * @return the generic response
	 */
	@GET
   	@Path("/{idDichAnnuale}/download")
   	GenericResponse<DocumentoProtocollatoVO> downloadDichiarazioneAnnuale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
		@PathParam("idDichAnnuale") @NotNull Long idDichAnnuale);

	/**
	 * Genera dichiarazione annuale.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDichAnnuale the id dich annuale
	 * @return the generic response
	 */
	@POST
   	@Path("/{idDichAnnuale}/genera")
   	GenericResponse<String> generaDichiarazioneAnnuale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
		@PathParam("idDichAnnuale") @NotNull Long idDichAnnuale);

	/**
	 * Exists dichiarazione.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param existsDichiarazioneVO the exists dichiarazione VO
	 * @return the generic response
	 */
	@GET
   	@Path("/exists")
	GenericResponse<Boolean> existsDichiarazione(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@BeanParam ExistsDichiarazioneVO existsDichiarazioneVO);
	
	/**
	 * Removes the dichiarazione soggetto mr.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDichAnnuale the id dich annuale
	 * @param idSoggettoMr the id soggetto mr
	 * @return the generic response
	 */
	@DELETE
   	@Path("/{idDichAnnuale}/soggetti-mr/{idSoggettoMr}")
	GenericResponse<SoggettoMrVO> removeDichiarazioneSoggettoMr(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@PathParam("idDichAnnuale") @NotNull Long idDichAnnuale,
			@PathParam("idSoggettoMr") @NotNull Long idSoggettoMr);
	
	/**
	 * Insert bozza dich annuale.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param dichAnnualeVO the dich annuale VO
	 * @return the generic response
	 */
	@POST
	@Path("/bozza")
	GenericResponse<DichAnnualeVO> insertBozzaDichAnnuale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@NotNull DichAnnualeVO dichAnnualeVO);
	
	/**
	 * Insert dich annuale.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param dichAnnualeVO the dich annuale VO
	 * @return the generic response
	 */
	@POST
	GenericResponse<DichAnnualeVO> insertDichAnnuale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
			@NotNull DichAnnualeVO dichAnnualeVO);
			
	/**
	 * Gets the dichiarazione by id dich annuale.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDichAnnuale the id dich annuale
	 * @return the dichiarazione by id dich annuale
	 */
	@GET
	@Path("/{idDichAnnuale}")
	GenericResponse<DichAnnualeVO> getDichiarazioneByIdDichAnnuale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @PathParam("idDichAnnuale") @NotNull Long idDichAnnuale);
	
	/**
	 * Gets the nuova versione dichiarazione.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDichAnnuale the id dich annuale
	 * @return the nuova versione dichiarazione
	 */
	@GET
	@Path("/{idDichAnnuale}/nuova-versione")
	GenericResponse<DichAnnualeVO> getNuovaVersioneDichiarazione(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @PathParam("idDichAnnuale") @NotNull Long idDichAnnuale);

	/**
	 * Gets the dichiarazione by id gestore id impianto anno.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idGestore the id gestore
	 * @param idImpianto the id impianto
	 * @param anno the anno
	 * @return the dichiarazione by id gestore id impianto anno
	 */
	@GET
	@Path("/single-result")
    	GenericResponse<DichAnnualeVO> getDichiarazioneByIdGestoreIdImpiantoAnno(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
            @QueryParam("idGestore") @NotNull Long idGestore, 
            @QueryParam("idImpianto") @NotNull Long idImpianto,  
            @QueryParam("anno") @NotNull Long anno);
	
    /**
     * Delete dichiarazione.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @param idDichAnnuale the id dich annuale
     * @return the generic response
     */
    @DELETE
	@Path("/{idDichAnnuale}")
	GenericResponse<DichAnnualeVO> deleteDichiarazione(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @PathParam("idDichAnnuale") @NotNull Long idDichAnnuale);

    /**
     * Allow duplica.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @param allowDuplicaDichiarazioneVO the allow duplica dichiarazione VO
     * @return the generic response
     */
    @GET
    @Path("/allow-duplica")
    GenericResponse<Boolean> allowDuplica(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
            @BeanParam AllowDuplicaDichiarazioneVO allowDuplicaDichiarazioneVO);
    
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
	GenericResponse<ReportVO> downloadReport(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam DichiarazioneParametriRicerca parametriRicerca);

    @DELETE
    @Path("/{idDichAnnuale}/conferimenti/{idRifiutoTariffa}")
    GenericResponse<DichAnnualeVO> deteteConferimenti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
    		@PathParam("idDichAnnuale") @NotNull Long idDichAnnuale, @PathParam("idRifiutoTariffa") @NotNull Long idRifiutoTariffa);
    
    
}
