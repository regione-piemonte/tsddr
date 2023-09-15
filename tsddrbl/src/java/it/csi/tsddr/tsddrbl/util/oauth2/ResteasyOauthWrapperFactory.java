/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.oauth2;

import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import it.csi.tsddr.tsddrbl.util.Constants;
import it.csi.tsddr.tsddrbl.util.oauth2.OauthHelper;
import it.csi.tsddr.tsddrbl.util.oauth2.ResteasyOauthWrapper;


/**
 * The type Resteasy oauth wrapper factory.
 */
public class ResteasyOauthWrapperFactory /*extends BaseFactoryBean*/ {


    /**
     * The constant logger.
     */
    protected static Logger logger = Logger.getLogger(Constants.COMPONENT_NAME);


    /**
     * The Wrapped interface.
     */
    protected Class wrappedInterface;
    /**
     * The End point.
     */
    protected String endPoint;


    /**
     * Gets end point.
     *
     * @return the end point
     */
    public String getEndPoint() {
		return endPoint;
	}

    /**
     * Sets end point.
     *
     * @param endPoint the end point
     */
    public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

    /**
     * Gets wrapped interface.
     *
     * @return the wrapped interface
     */
    public Class getWrappedInterface() {
		return wrappedInterface;
	}

    /**
     * Sets wrapped interface.
     *
     * @param wrappedInterface the wrapped interface
     */
    public void setWrappedInterface(Class wrappedInterface) {
		this.wrappedInterface = wrappedInterface;
	}

    /**
     * Create object.
     *
     * @return the object
     * @throws ClassNotFoundException the class not found exception
     */
    public Object create() throws ClassNotFoundException {
		logger.info("[GenericWrapperFactoryBean.create]" + 
			        "\nendpoint ......: " + endPoint + 
			        "\nwrappedClass ..: " + wrappedInterface);
		
		
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(endPoint);
        
		ResteasyOauthWrapper wrapper = new ResteasyOauthWrapper(target, wrappedInterface, oauthHelper);
		Object o = Proxy.newProxyInstance(wrappedInterface.getClassLoader(), new Class[] {wrappedInterface}, wrapper);
	
	    if (logger.isDebugEnabled())
	    	logger.debug("[GenericWrapperFactoryBean.create] created " + o.getClass().getName());
        return o;
    }
   
 

    private OauthHelper oauthHelper;

    /**
     * Gets oauth helper.
     *
     * @return the oauth helper
     */
    public OauthHelper getOauthHelper() {
		return oauthHelper;
	}

    /**
     * Sets oauth helper.
     *
     * @param oauthHelper the oauth helper
     */
    public void setOauthHelper(OauthHelper oauthHelper) {
		this.oauthHelper = oauthHelper;
	}

}