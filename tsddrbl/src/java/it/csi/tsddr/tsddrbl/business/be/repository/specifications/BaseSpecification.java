/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import it.csi.tsddr.tsddrbl.business.be.entity.AbstractEntity;
import it.csi.tsddr.tsddrbl.business.be.entity.AbstractValidableEntity;

public class BaseSpecification {
	
	protected static final String DATA_INIZIO_VALIDITA = "dataInizioValidita";
	protected static final String DATA_FINE_VALIDITA = "dataFineValidita";
	protected static final String DATA_DELETE = "dataDelete";
	protected static final String ID_USER_DELETE = "idUserDelete";
	
	
	
	protected static Predicate isValid(From<? extends AbstractEntity, ? extends AbstractValidableEntity> root,
			CriteriaBuilder builder, Date targetDate) {
		return builder.and(hasValidDate(root, builder, targetDate), isNotDeleted(root, builder, targetDate));
	}
	
	protected static Predicate hasValidDate(From<? extends AbstractEntity, ? extends AbstractValidableEntity> root,
			CriteriaBuilder builder, Date targetDate) {
		return builder.and(builder.lessThanOrEqualTo(root.get(DATA_INIZIO_VALIDITA), targetDate),
				builder.or(builder.isNull(root.get(DATA_FINE_VALIDITA)),
						builder.greaterThanOrEqualTo(root.get(DATA_FINE_VALIDITA), targetDate)));
	}

	protected static Predicate isNotDeleted(From<? extends AbstractEntity, ? extends AbstractEntity> root,
			CriteriaBuilder builder, Date targetDate) {
		return builder.and(builder.isNull(root.get(DATA_DELETE)), builder.isNull(root.get(ID_USER_DELETE)));
	}

}