/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.accreditamento;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class StatoDomandaVO extends AbstractVO {

	private static final long serialVersionUID = -7583866648322960216L;

	private Long id;
	private String desc;
	private Long step;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the step
	 */
	public Long getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(Long step) {
		this.step = step;
	}

}
