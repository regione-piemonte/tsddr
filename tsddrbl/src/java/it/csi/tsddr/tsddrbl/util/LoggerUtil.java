/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import org.apache.log4j.MDC;

/**
 * The type Logger util.
 */
public class LoggerUtil {

    /**
     * Debug.
     *
     * @param logger  the logger
     * @param message the message
     */
    public static void debug(org.apache.log4j.Logger logger, String message) {
		
		String header = (String) MDC.get(Constants.LOG_HEADER_NAME);
		logger.debug(header +" - " + message);
	};


    /**
     * Info.
     *
     * @param logger  the logger
     * @param message the message
     */
    public static void info(org.apache.log4j.Logger logger, String message) {

		String header = (String) MDC.get(Constants.LOG_HEADER_NAME);
		logger.info(header +" - " + message);
	}

    /**
     * Warn.
     *
     * @param logger  the logger
     * @param message the message
     */
    public static void warn(org.apache.log4j.Logger logger, String message) {

		String header = (String) MDC.get(Constants.LOG_HEADER_NAME);
		logger.warn(header +" - " + message);
	}

    /**
     * Warn.
     *
     * @param logger  the logger
     * @param message the message
     * @param t       the t
     */
    public static void warn(org.apache.log4j.Logger logger, String message, Throwable t) {

		String header = (String) MDC.get(Constants.LOG_HEADER_NAME);
		logger.warn(header +" - " + message, t);
	}

    /**
     * Error.
     *
     * @param logger  the logger
     * @param message the message
     */
    public static void error(org.apache.log4j.Logger logger, String message) {

		String header = (String) MDC.get(Constants.LOG_HEADER_NAME);
		logger.error(header +" - " + message);
	}

    /**
     * Error.
     *
     * @param logger  the logger
     * @param message the message
     * @param t       the t
     */
    public static void error(org.apache.log4j.Logger logger, String message, Throwable t) {

		String header = (String) MDC.get(Constants.LOG_HEADER_NAME);
		logger.error(header +" - " + message, t);
	}
}