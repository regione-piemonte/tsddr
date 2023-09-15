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
import it.csi.tsddr.tsddrbl.vo.messaggio.MessaggioVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.ExistsPrevConsVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsBasicVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsLineeExtendedVO;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsParametriRicerca;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsVO;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface DichiarazioniMrController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/prev-cons")
public interface PrevConsController {
    
    @GET
    @Path("/acl")
    GenericResponse<FunzionalitaProfiloVO> getACLPrevCons(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @QueryParam("idTipoDoc") @NotNull Long idTipoDoc);
    
    @POST
    @Path("/bozza")
    GenericResponse<PrevConsVO> insertBozzaPrevCons(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
            @NotNull PrevConsVO prevConsVO, @QueryParam("idImpianto") @NotNull Long idImpianto, @QueryParam("idGestore") @NotNull Long idGestore);
    
    @POST
    GenericResponse<PrevConsVO> insertPrevCons(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
            @NotNull PrevConsVO prevConsVO, @QueryParam("idImpianto") @NotNull Long idImpianto, @QueryParam("idGestore") @NotNull Long idGestore);
    
    @GET
    @Path("/{idPrevCons}/download")
    GenericResponse<DocumentoProtocollatoVO> downloadPrevConsPDF(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
        @PathParam("idPrevCons") @NotNull Long idPrevCons, @QueryParam("idTipoDoc") @NotNull Long idTipoDoc);
    
    @GET
    @Path("/{idPrevCons}")
    GenericResponse<PrevConsExtendedVO> getPrevCons(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @PathParam("idPrevCons") @NotNull Long idPrevCons, @QueryParam("idTipoDoc") @NotNull Long idTipoDoc);
    
    @DELETE
    @Path("/{idPrevCons}")
    GenericResponse<PrevConsVO> deletePrevCons(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @PathParam("idPrevCons") @NotNull Long idPrevCons, @QueryParam("idTipoDoc") @NotNull Long idTipoDoc);

    @DELETE
    @Path("/linee")
    GenericResponse<MessaggioVO> deleteLinee(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @QueryParam("idPrevConsLinee") @NotNull Long idPrevConsLinee, @QueryParam("idSezione") Long idSezione);
    
    @GET
    @Path("/anni/combo")
    GenericResponse<List<Long>> getComboAnni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @QueryParam("idTipoDoc") @NotNull Long idTipoDoc, @QueryParam("idStatoDichiarazione") Long idStatoDichiarazione);
    
    @GET
    @Path("/gestore/combo")
    GenericResponse<List<SelectVO>> getComboGestore(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
    
    @GET
    @Path("/impianti/combo")
    GenericResponse<List<SelectVO>> getComboImpianti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
        @QueryParam("idGestore") Long idGestore);
    
    @GET
    GenericResponse<List<PrevConsBasicVO>> getListaPrevCons(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam PrevConsParametriRicerca parametriRicerca);

    @GET
    @Path("/parametri-filtro-applicati")
    GenericResponse<String> getParametriFiltroApplicati(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam PrevConsParametriRicerca parametriRicerca);

    @GET
    @Path("/exists")
    GenericResponse<Boolean> existsPrevCons(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
            @BeanParam ExistsPrevConsVO existsPrevConsVO);
    
    @GET
    @Path("/{idPrevCons}/linee-richiesta")
    GenericResponse<List<PrevConsLineeExtendedVO>> getLineeRichiesta(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @PathParam("idPrevCons") @NotNull Long idPrevCons);
    
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
    GenericResponse<ReportVO> downloadReport(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @BeanParam PrevConsParametriRicerca parametriRicerca);

    @GET
    @Path("/isPercRecuperoVisible")
    GenericResponse<Boolean> isPercRecuperoVisible(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @QueryParam("codLinea") @NotNull String codLinea, @QueryParam("anno") @NotNull Long anno);

    @GET
    @Path("/isPercScartoVisible")
    GenericResponse<Boolean> isPercScartoVisible(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, @QueryParam("codLinea") @NotNull String codLinea, @QueryParam("anno") @NotNull Long anno);

        
}
