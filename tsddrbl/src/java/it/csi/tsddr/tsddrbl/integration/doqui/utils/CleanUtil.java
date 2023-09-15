/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.utils;

import org.apache.commons.lang.StringUtils;

import it.csi.tsddr.tsddrbl.integration.doqui.DoquiConstants;


public class CleanUtil {

	
	public static String cleanNullValue(String value){
		return (StringUtils.equalsIgnoreCase(value, DoquiConstants.NULL_VALUE) ?  null : value);
	}
	

}
