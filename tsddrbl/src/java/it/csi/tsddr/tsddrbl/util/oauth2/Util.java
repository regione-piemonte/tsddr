/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.oauth2;

/**
 * The type Util.
 */
public class Util {
    /**
     * Mask for log string.
     *
     * @param in the in
     * @return the string
     */
    public static String maskForLog(String in) {
		if (in != null && in.length() > 5) {
			int n = in.length() / 3;
			String pre = in.substring(0, n);
			String end = in.substring(in.length() - n);
			StringBuffer sb = new StringBuffer();
			sb.append(pre);
			for (int i = 0; i < in.length() - 2 * n; i++)
				sb.append("*");
			sb.append(end);
			return sb.toString();
		}
		return "*****";
	}
}