/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

public class CheckImpiantoVO {

	private boolean result;

	/**
	 * Returns if the implant is erasable
	 *
	 * @return the result of the check
	 */
	public boolean getResult() {
		return result;
	}

	/**
	 * Sets the result of the check
	 *
	 * @param result the result of the check
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
}