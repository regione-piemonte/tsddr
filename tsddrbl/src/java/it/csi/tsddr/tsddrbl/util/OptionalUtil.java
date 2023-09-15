/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.Optional;


public class OptionalUtil {
	public static <T> T getContent (Optional<T> optional) {
		return optional.isPresent()?optional.get():null;
	} 
}
