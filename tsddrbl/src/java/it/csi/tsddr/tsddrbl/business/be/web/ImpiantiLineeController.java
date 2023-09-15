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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.impiantolinee.ImpiantoLineeVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface ImpiantiLineeController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/impianti-linee")
public interface ImpiantiLineeController {

    /**
     * Gets the impianto linee.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @param idImpianto the id impianto
     * @return the impianto linee
     */
    @GET
    public GenericResponse<List<ImpiantoLineeVO>> getImpiantoLinee(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest, 
            @QueryParam("idImpianto") @NotNull Long idImpianto, @QueryParam("idPrevCons") Long idPrevCons);
    
}
