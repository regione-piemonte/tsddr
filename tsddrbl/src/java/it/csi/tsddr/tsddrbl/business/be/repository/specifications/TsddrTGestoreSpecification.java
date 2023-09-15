/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDComune;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDNaturaGiuridica;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDProvincia;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTIndirizzo;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreParametriRicerca;

/**
 *
 */
public class TsddrTGestoreSpecification extends BaseSpecification {

    /**
     * Users associated to profile {@code idProfilo}
     * 
     * @param idProfilo
     * @param targetDate
     * @return
     */
    public static Specification<TsddrTGestore> searchByParams(GestoreParametriRicerca parametriRicerca,
            Date targetDate) {
        return new Specification<TsddrTGestore>() {
            @Override
            public Predicate toPredicate(Root<TsddrTGestore> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Join<TsddrTGestore, TsddrTIndirizzo> sedeLegale = root.join("sedeLegale");

                Predicate predicate = builder.and(isNotDeleted(root, builder, targetDate));

                if (parametriRicerca != null) {
                    predicate = addIdGestore(predicate, builder, root, parametriRicerca);
                    predicate = addIdNaturaGiuridica(predicate, builder, root, parametriRicerca);
                    predicate = addCodFiscPartita(predicate, builder, root, parametriRicerca);
                    predicate = addIdProvincia(predicate, builder, sedeLegale, parametriRicerca);
                    predicate = addIdComune(predicate, builder, sedeLegale, parametriRicerca);
                }
                query.orderBy(builder.asc( // Descending order
                                root.get("codFiscPartiva"))); // Column name
                return predicate;
            }
        };
    }
    
    private static Predicate addIdGestore(Predicate predicate, CriteriaBuilder builder, Root<TsddrTGestore> root, GestoreParametriRicerca parametriRicerca) {
        if (parametriRicerca.getIdGestore() != null) {
            return builder.and(predicate,
                    builder.equal(root.get("idGestore"), parametriRicerca.getIdGestore()));
        }
        return predicate;
    }

    private static Predicate addIdNaturaGiuridica(Predicate predicate, CriteriaBuilder builder,
            Root<TsddrTGestore> root, GestoreParametriRicerca parametriRicerca) {
        if (parametriRicerca.getIdNaturaGiuridica() != null) {
            Join<TsddrTGestore, TsddrDNaturaGiuridica> naturaGiuridica = root.join("naturaGiuridica");
            return builder.and(predicate, builder.equal(naturaGiuridica.get("idNaturaGiuridica"),
                    parametriRicerca.getIdNaturaGiuridica()));
        }
        return predicate;
    }

    private static Predicate addCodFiscPartita(Predicate predicate, CriteriaBuilder builder,
            Root<TsddrTGestore> root, GestoreParametriRicerca parametriRicerca) {
        if (StringUtils.isNotBlank(parametriRicerca.getCodFiscPartiva())) {
            return builder.and(predicate,
                    builder.equal(root.get("codFiscPartiva"), parametriRicerca.getCodFiscPartiva()));
        }
        return predicate;
    }

    private static Predicate addIdProvincia(Predicate predicate, CriteriaBuilder builder,
            Join<TsddrTGestore, TsddrTIndirizzo> sedeLegale, GestoreParametriRicerca parametriRicerca) {
        if (parametriRicerca.getIdProvincia() != null) {
            Join<TsddrTIndirizzo, TsddrDComune> comune = sedeLegale.join("comune");
            Join<TsddrDComune, TsddrDProvincia> provincia = comune.join("provincia");
            return builder.and(predicate,
                    builder.equal(provincia.get("idProvincia"), parametriRicerca.getIdProvincia()));
        }
        return predicate;
    }

    private static Predicate addIdComune(Predicate predicate, CriteriaBuilder builder,
            Join<TsddrTGestore, TsddrTIndirizzo> sedeLegale, GestoreParametriRicerca parametriRicerca) {
        if (parametriRicerca.getIdComune() != null) {
            Join<TsddrTIndirizzo, TsddrDComune> comune = sedeLegale.join("comune");
            return builder.and(predicate,
                    builder.equal(comune.get("idComune"), parametriRicerca.getIdComune()));
        }
        return predicate;
    }

}
