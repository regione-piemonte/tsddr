/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.mapper.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeBasicVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichAnnualeVO;

/**
 * A factory for creating DichAnnualeVO objects.
 */
@Component
public class DichAnnualeVOFactory {

	/**
	 * Creates a new DichAnnualeVO object.
	 *
	 * @return the dich annuale basic VO
	 */
	public DichAnnualeBasicVO createDichAnnualeBasic() {
		return new DichAnnualeBasicVO();
	}

	/**
	 * Creates a new DichAnnualeVO object.
	 *
	 * @return the dich annuale VO
	 */
	public DichAnnualeVO createDichAnnuale() {
		return new DichAnnualeVO();
	}
	
	/**
	 * Creates a new DichAnnualeVO object.
	 *
	 * @return the list< dich annuale basic V o>
	 */
	public List<DichAnnualeBasicVO> createDichAnnualeBasicList() {
		return new ArrayList<>();
	}
	
	/**
	 * Creates a new DichAnnualeVO object.
	 *
	 * @return the list< dich annuale V o>
	 */
	public List<DichAnnualeVO> createDichAnnualeList() {
		return new ArrayList<>();
	}

}
