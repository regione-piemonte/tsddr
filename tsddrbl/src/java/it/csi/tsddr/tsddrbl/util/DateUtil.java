/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String ddMMyyyy = "dd/MM/yyyy";
	
	public static Date getStringAsDate(String dateString, String dateFormat) throws ParseException {
		Date date = new SimpleDateFormat(dateFormat).parse(dateString);  
		return date;
	}
	
}
