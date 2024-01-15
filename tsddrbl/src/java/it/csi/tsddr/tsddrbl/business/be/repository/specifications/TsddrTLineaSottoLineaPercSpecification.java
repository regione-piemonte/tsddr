/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLinea;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTLineaSottoLineaPerc;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTSottoLinea;

public class TsddrTLineaSottoLineaPercSpecification extends BaseSpecification {

    public static Specification<TsddrTLineaSottoLineaPerc> searchByParams(Long idLinea, Long idSottoLinea,
            Date anno, Boolean checkPercScartoVisible, Boolean checkPercRecuperoVisible) {
        return new Specification<TsddrTLineaSottoLineaPerc>() {
            @Override
            public Predicate toPredicate(Root<TsddrTLineaSottoLineaPerc> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
            	
                Join<TsddrTLineaSottoLineaPerc, TsddrTLinea> linea = root.join("linea");

                Predicate predicate = builder.and(
                        hasValidDate(root, builder, anno),
                        builder.equal(linea.get("idLinea"), idLinea));
                Join<TsddrTLineaSottoLineaPerc, TsddrTSottoLinea> sottoLinee = root.join("sottoLinee", JoinType.LEFT);
                if(idSottoLinea != null) {                    
                	predicate = builder.and(predicate, builder.equal(sottoLinee.get("idSottoLinea"), idSottoLinea));
                }else {
                	predicate = builder.and(predicate, builder.isNull(sottoLinee.get("idSottoLinea")));
                }
                
                if(checkPercScartoVisible)
                	predicate = builder.and(predicate, builder.greaterThan(root.get("perMaxScarto"), 0));
                
                if(checkPercRecuperoVisible)
                	predicate = builder.and(predicate, builder.greaterThan(root.get("perMinRecupero"), 0));
                
                return predicate;
            }

        };
    }
    
}
