/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Parent vo.
 */
public abstract class AbstractVO implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -2238681230786404845L;

}