/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.oauth2;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

import it.csi.tsddr.tsddrbl.util.oauth2.OauthHelper;

import java.io.IOException;


/**
 * The type Bearer authentication.
 */
public class BearerAuthentication implements ClientRequestFilter
{
   private final OauthHelper oauthHelper;

    /**
     * Instantiates a new Bearer authentication.
     *
     * @param oauthHelper the oauth helper
     */
    public BearerAuthentication(final OauthHelper oauthHelper)
   {
	  this.oauthHelper = oauthHelper;
   }

   @Override
   public void filter(ClientRequestContext requestContext) throws IOException
   {
	   StringBuffer buf = new StringBuffer(oauthHelper.getToken());
	   String authHeader = "Bearer " + buf.toString(); 
	      
      requestContext.getHeaders().putSingle(HttpHeaders.AUTHORIZATION, authHeader);
   }
}