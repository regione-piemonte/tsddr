/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The type App servlet context listener.
 */
public class AppServletContextListener implements ServletContextListener {

	private static ServletContext sc;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* Sets the context in a static variable */
		AppServletContextListener.sc = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

    /**
     * Gets servlet context.
     *
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
		return sc;
	}
}