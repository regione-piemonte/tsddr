/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoTariffaVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;

/**
 * The Interface RifiutiTariffeController.
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/rifiuti-tariffe")
public interface RifiutiTariffeController {

	/**
	 * Gets the combo rifiuto tariffa.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDichAnnuale the id dich annuale
	 * @return the combo rifiuto tariffa
	 */
	@GET
	@Path("/combo")
	GenericResponse<List<SelectVO>> getComboRifiutoTariffa(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@QueryParam("idDichAnnuale") Long idDichAnnuale);
	
	/**
	 * Gets the rifiuto tariffa.
	 *
	 * @param securityContext the security context
	 * @param httpHeaders the http headers
	 * @param httpRequest the http request
	 * @param idDichAnnuale the id dich annuale
	 * @param anno the year of dich annuale
	 * @return the rifiuto tariffa
	 */
	@GET
    GenericResponse<List<RifiutoTariffaVO>> getRifiutoTariffa(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
            @QueryParam("idDichAnnuale") Long idDichAnnuale, @QueryParam("anno") String anno);
	
}
