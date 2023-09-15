/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.oauth2;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * The type Resteasy oauth wrapper.
 */
public class ResteasyOauthWrapper implements InvocationHandler{
	
	private Class jaxrsIntf;
	
	private OauthHelper oauthHelper;
	
	private ResteasyWebTarget webTarget;

    /**
     * Instantiates a new Resteasy oauth wrapper.
     *
     * @param target      the target
     * @param jaxrsIntf   the jaxrs intf
     * @param oauthHelper the oauth helper
     */
    public ResteasyOauthWrapper(ResteasyWebTarget target, Class jaxrsIntf, OauthHelper oauthHelper) {
		this.jaxrsIntf=jaxrsIntf;
		this.oauthHelper=oauthHelper;
		this.webTarget=target;
		// registrazione di un interceptor che inserisce nelle chiamate l'header di autenticazione oauth2
		webTarget.register(new BearerAuthentication(oauthHelper));
	}
	
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// questo proxy implementa jaxrsintf
		Object jaxrsProxy = getNewJaxrsProxy();
		try {
			Response resp = (Response)method.invoke(jaxrsProxy, args);
			// in caso di errore di autenticazione (401) non viene restituita una eccezione
			// ma una response con codice 401
			if(isOauthError(resp)) {
				// refresh token & retry....
				refreshToken();
				jaxrsProxy = getNewJaxrsProxy();
				return getObject(method, args, jaxrsProxy);
			}
			else {
				return resp;
			}
		}
		catch(InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	private Object getObject(Method method, Object[] args, Object jaxrsProxy) throws InvocationTargetException, IllegalAccessException {
		return method.invoke(jaxrsProxy, args);
	}


	private void refreshToken() {
		oauthHelper.getNewToken();
	}

	private boolean isOauthError(Response resp) {
		return resp.getStatus() == 401;
	}
	

	private Object cachedProxy = null;
	private Object getNewJaxrsProxy() {
		if (cachedProxy ==null) {
			Object newProxy = this.webTarget.proxy(jaxrsIntf);
			cachedProxy = newProxy;
		}
		// 
		return cachedProxy;
	}

}