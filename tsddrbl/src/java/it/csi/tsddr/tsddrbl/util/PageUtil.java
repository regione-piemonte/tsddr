/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * The type Page util.
 */
public class PageUtil {

    /**
     * Gets pageable.
     *
     * @param page    the page
     * @param size    the size
     * @param sortExp the sort exp
     * @return the pageable
     */
    public static Pageable getPageable(int page, int size, String sortExp) {
		if(StringUtils.isEmpty(sortExp)) {
			return getPageable(page, size);
		}else {
			List <Order> orders = new ArrayList <Order>();
			try {
				String[] sortList = sortExp.split(",");
				for(String sort : sortList) {
					sort = sort.trim();
					if(sort.startsWith("+")) {
						orders.add(new Order(Direction.ASC, sort.substring(sort.indexOf("+")+1)));
					}else if(sort.startsWith("-")){
						orders.add(new Order(Direction.DESC, sort.substring(sort.indexOf("-")+1)));
					}
				}
				if(orders.size()>0) {
					return new PageRequest(page, size, new Sort(orders));
				}else {
					return getPageable(page, size);
				}
			}catch(Throwable t) {
				return getPageable(page, size);
			}
		}
	}
	
	private static Pageable getPageable(int page, int size) {
		return new PageRequest(page, size);
	}
}