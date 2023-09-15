/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrRUtenteProf;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDatiSogg;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTUtente;

/**
 * The type Tsddr t user specification.
 */
public class TsddrTUtenteSpecification extends BaseSpecification {

	/**
	 * Users associated to profile {@code idProfilo}
	 * 
	 * @param idProfilo
	 * @param targetDate
	 * @return
	 */
	public static Specification<TsddrTUtente> hasIdProfilo(Long idProfilo, Date targetDate) {
		return new Specification<TsddrTUtente>() {
			@Override
			public Predicate toPredicate(Root<TsddrTUtente> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Join<TsddrTUtente, TsddrRUtenteProf> rUtentiProf = root.join("rUtentiProf");
				Join<TsddrTUtente, TsddrTDatiSogg> datiSogg = root.join("datiSogg");

				return builder.and(builder.equal(rUtentiProf.get("id.idProfilo"), idProfilo),
						isValid(rUtentiProf, builder, targetDate), isValid(root, builder, targetDate),
						isNotDeleted(datiSogg, builder, targetDate));
			}
		};
	}

}