/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.oauth2;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import it.csi.tsddr.tsddrbl.util.Constants;
import it.csi.tsddr.tsddrbl.util.oauth2.GetTokenResponse;
import it.csi.tsddr.tsddrbl.util.oauth2.Util;

/**
 * Gestione token oauth2
 * <p>
 * <p>
 * Accede al servizio /api/token dell'API Manager.
 * mantiene in cache locale l'utimo token
 * <p>
 * L'implemetazione attuale si basa su Resteasy per
 * l'accesso al servizio REST
 * <p>
 * Per la configurazione usare un costruttore opportuno; i parametri da usare sono:
 * <ul>
 * <li>oauthURL l'endpoint del servizio REST /api/token</li>
 * <li>@see <a href="https://docs.wso2.com/display/AM191/Token+API">consumerKey</a>
 * <li>@see <a href="https://docs.wso2.com/display/AM191/Token+API">consumerSecret</a>
 * <li>timeout       timeout in millisecondi della connessione verso /api/token</li>
 * </ul>
 * <p>
 * Esempio:
 * <pre>
 *         OauthHelper oauth = new OauthHelper("https://tst-sw-eng.csi.it:443/wso108/api/token",
 *                                             "660PznSzJu706tpmfHaPsMHT5coa",
 *                                             "hhimfC5jF3Y0eonBL2PECvMJDGIa",
 *                                             10000);
 *
 * </pre>
 *
 * @author CSI PIEMONTE
 */
public class OauthHelper {

    /**
     * The constant logger.
     */
    protected static Logger logger = Logger.getLogger(Constants.COMPONENT_NAME);

    /**
     * The constant ACCESS_TOKEN.
     */
    public static final String ACCESS_TOKEN = "access_token";
    /**
     * The constant OAUTH2_GRANT_TYPE.
     */
    public static final String OAUTH2_GRANT_TYPE = "grantType";
    /**
     * The constant OAUTH2_GRANT_TYPE_CLIENT_CREDENTIALS.
     */
    public static final String OAUTH2_GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

	private String oauthURL;
	private String consumerKey;
	private String consumerSecret;
	private int timeout;

	private String token;

	private Long expires;

	private static volatile long hashCount; // # accessi token in cache
	private static volatile long counter;   // # richieste token

	private Date expirationDate;

	/**
	 * Instantiates a new Oauth helper.
	 *
	 * @param oauthURL       l'endpoint del servizio REST /api/token
	 * @param consumerKey    consumerKey @see <a href="https://docs.wso2.com/display/AM191/Token+API">consumerKey</a>
	 * @param consumerSecret consumerSecret @see <a href="https://docs.wso2.com/display/AM191/Token+API">consumerSecret</a>
	 * @param timeout        timeout in millisecondi della connessione verso /api/token
	 */
	public OauthHelper(String oauthURL, String consumerKey, String consumerSecret, int timeout) {
		this.oauthURL = (oauthURL != null) ? oauthURL.trim() : oauthURL;
		this.consumerKey = (consumerKey != null) ? consumerKey.trim() : consumerKey;
		this.consumerSecret = (consumerSecret != null) ? consumerSecret.trim() : consumerSecret;
		this.timeout = timeout;
	}

	/**
	 * Instantiates a new Oauth helper.
	 *
	 * @param oauthURL       l'endpoint del servizio REST /api/token
	 * @param consumerKey    consumerKey @see <a href="https://docs.wso2.com/display/AM191/Token+API">consumerKey</a>
	 * @param consumerSecret consumerSecret @see <a href="https://docs.wso2.com/display/AM191/Token+API">consumerSecret</a>
	 */
	public OauthHelper(String oauthURL, String consumerKey, String consumerSecret) {
		this(oauthURL, consumerKey, consumerSecret, 30000);
	}


    /**
     * Gets expires.
     *
     * @return the expires
     */
    public long getExpires() {
		if (expires != null)
		  return expires.longValue();
		return 0;
	}

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {

		if (token != null && expirationDate.compareTo(new Date()) > 0) {
//			hashCount++;
			String out = "Token di accesso apiman[" + counter + "," + hashCount + "] " + Util.maskForLog(token) +
					" expires in " + expires + " at " + expirationDate;

			logger.info(out);
			return token; // use cache if exist
		}

		logger.info(expirationDate);
		return getTokenInternal();
    }

    /**
     * Gets new token.
     *
     * @return the new token
     */
    public String getNewToken() {
    	token = null;                  // invalidate cache
    	return getTokenInternal();
    }

    private String getTokenInternal() {
    	
		logger.info("[OauthHelper.getTokenInternal]\n" + 
			        "oauthURL ......: " + oauthURL + "\n" +
			        "consumerKey ...: " + consumerKey + "\n" +
			        "consumerSecret : " + Util.maskForLog(consumerSecret) + "\n" +
			        "timeout .......: " + timeout);

		ResteasyClient client = new ResteasyClientBuilder()
				.connectTimeout(timeout, TimeUnit.MILLISECONDS)
				.readTimeout(timeout, TimeUnit.MILLISECONDS).build();
        ResteasyWebTarget target = client.target(this.oauthURL);
        Form tokenForm = new Form();
        tokenForm
        .param("grant_type", OAUTH2_GRANT_TYPE_CLIENT_CREDENTIALS)
        .param("client_id", consumerKey)
        .param("client_secret", consumerSecret);
        
        Response tokenResponse = target.request()
        	.header("Accept","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2")
        	.header("Content-type","application/x-www-form-urlencoded")
        	.post(Entity.form(tokenForm));
		int statusCode = tokenResponse.getStatus();
        if (statusCode != HttpStatus.SC_OK) {
        	logger.error("wrong HTTP status code: " + statusCode + "\nStatusLine:" + tokenResponse.getStatusInfo());
        	token = null;
        	expires = null;
        	logger.warn("OAUT2 token set to null");
        	return token;
        }

        // Read the response body.
       GetTokenResponse responseBody = tokenResponse.readEntity(GetTokenResponse.class);
        
       token = responseBody.getAccess_token();
       expires = responseBody.getExpires_in();
       
		if (token == null || expires == null) {
        	logger.error("unexpected reply: ");
        	token = null;
        	expires = null;
        	logger.info("OAUT2 token set to null");
        	return token;				
		}

		expirationDate = addSeconds(new Date(), (int) (expires - 5));

    	String out = "token di accesso apiman[" + counter + "," + hashCount +"] " + Util.maskForLog(token) +
    			     " expires in " +  expires;

		logger.info(out);
	    return token;
    }

	private static Date addSeconds(Date date, Integer seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

}