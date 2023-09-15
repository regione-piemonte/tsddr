/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.repository.specifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDStatoDichiarazione;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDTipoDoc;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTPrevCons;
import it.csi.tsddr.tsddrbl.util.enums.StatoDichiarazione;
import it.csi.tsddr.tsddrbl.vo.prevcons.PrevConsParametriRicerca;

public class TsddrTPrevConsSpecification extends BaseSpecification {

    private static final String ANNO_TRIBUTO = "annoTributo";
    private static final String CF_PIVA = "codFiscPartiva";
    private static final String DENOM = "denominazione";
    private static final String ID_PREV_CONS = "idPrevCons";

    public static Specification<TsddrTPrevCons> searchByParams(PrevConsParametriRicerca parametriRicerca,
            Date targetDate, boolean isProfiloBO, List<TsddrTGestore> gestoriUtente, boolean isReport) {
        return new Specification<TsddrTPrevCons>() {
            @Override
            public Predicate toPredicate(Root<TsddrTPrevCons> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                boolean isEnableAllGestori = false;
                if (isProfiloBO && gestoriUtente.isEmpty()) {
                    isEnableAllGestori = true;
                }

                Join<TsddrTPrevCons, TsddrDStatoDichiarazione> statoDichiarazione = root.join("statoDichiarazione");
                Join<TsddrTPrevCons, TsddrDTipoDoc> tipoDocumento = root.join("tipoDoc");
                Join<TsddrTPrevCons, TsddrTImpianto> impianto = root.join("impianto");
                Join<TsddrTImpianto, TsddrTGestore> gestore = impianto.join("gestore");
                
                Predicate predicate = null;
                if(!isReport) {
                	predicate = builder.and(
                        hasValidDate(statoDichiarazione, builder, targetDate),
                        hasValidDate(impianto, builder, targetDate),
                        hasValidDate(gestore, builder, targetDate));
                }else {
                	predicate = builder.and(
                        hasValidDate(statoDichiarazione, builder, targetDate));
                }

                predicate = addAnnoTributoDal(predicate, builder, root, parametriRicerca);
                predicate = addAnnoTributoAl(predicate, builder, root, parametriRicerca);
                predicate = addAnnoTributo(predicate, builder, root, parametriRicerca);
                predicate = addProfiloBO(isProfiloBO, statoDichiarazione, predicate, builder, parametriRicerca);
                predicate = addImpiantoFilter(predicate, builder, impianto, parametriRicerca);
                predicate = addGestoreFilter(predicate, builder, gestore, isEnableAllGestori, parametriRicerca, gestoriUtente);
                predicate = addTipoDocFilter(predicate, builder, tipoDocumento, parametriRicerca);
                predicate = addStatoDichiarazioneFilter(predicate, builder, statoDichiarazione, parametriRicerca);

                List<Order> orderList = new ArrayList<>();
                if(!isReport) {
                	orderList.add(builder.desc(root.get(ANNO_TRIBUTO)));
                    query.distinct(true);
                }else {
                	orderList.add(builder.asc(gestore.get(CF_PIVA)));
                	orderList.add(builder.asc(impianto.get(DENOM)));
                	orderList.add(builder.desc(root.get(ANNO_TRIBUTO)));
                	orderList.add(builder.desc(root.get(ID_PREV_CONS)));
                }
                query.orderBy(orderList);
                return predicate;
            }
        };

    }
    
    private static Predicate addAnnoTributoDal(Predicate predicate, CriteriaBuilder builder,
            Root<TsddrTPrevCons> root, PrevConsParametriRicerca parametriRicerca) {
        if (parametriRicerca.getAnnoTributoDal() != null) {
            predicate = builder.and(predicate,
                    builder.greaterThanOrEqualTo(root.get(ANNO_TRIBUTO), parametriRicerca.getAnnoTributoDal()));
        }
        return predicate;
    }

    private static Predicate addAnnoTributoAl(Predicate predicate, CriteriaBuilder builder,
            Root<TsddrTPrevCons> root, PrevConsParametriRicerca parametriRicerca) {
        if (parametriRicerca.getAnnoTributoAl() != null) {
            predicate = builder.and(predicate,
                    builder.lessThanOrEqualTo(root.get(ANNO_TRIBUTO), parametriRicerca.getAnnoTributoAl()));
        }
        return predicate;
    }

    private static Predicate addAnnoTributo(Predicate predicate, CriteriaBuilder builder, Root<TsddrTPrevCons> root, PrevConsParametriRicerca parametriRicerca) {
        if (parametriRicerca.getAnnoTributo() != null) {
            predicate = builder.and(predicate,
                    builder.equal(root.get(ANNO_TRIBUTO), parametriRicerca.getAnnoTributo()));
        }
        return predicate;
    }

    private static Predicate addProfiloBO(boolean isProfiloBO,
            Join<TsddrTPrevCons, TsddrDStatoDichiarazione> statoDichiarazione, Predicate predicate,
            CriteriaBuilder builder, PrevConsParametriRicerca parametriRicerca) {
        if (isProfiloBO) {
            predicate = builder.and(predicate, builder.equal(statoDichiarazione.get("idStatoDichiarazione"),
                    StatoDichiarazione.INVIATA_PROTOCOLLATA.getId()));
        }
        return predicate;
    }

    private static Predicate addImpiantoFilter(Predicate predicate, CriteriaBuilder builder,
            Join<TsddrTPrevCons, TsddrTImpianto> impianto, PrevConsParametriRicerca parametriRicerca) {
        if (parametriRicerca.getIdImpianto() != null) {
            predicate = builder.and(predicate,
                    builder.equal(impianto.get("idImpianto"), parametriRicerca.getIdImpianto()));
        }
        return predicate;
    }

    private static Predicate addGestoreFilter(Predicate predicate, CriteriaBuilder builder,
            Join<TsddrTImpianto, TsddrTGestore> gestore, boolean isEnableAllGestori, PrevConsParametriRicerca parametriRicerca, List<TsddrTGestore> gestoriUtente) {
        if (!isEnableAllGestori) {
            List<Long> idGestori = new ArrayList<>();
            idGestori.add(0L);
            for (TsddrTGestore gestoreUtente : gestoriUtente) {
                idGestori.add(gestoreUtente.getIdGestore());
            }
            predicate = builder.and(predicate, gestore.get("idGestore").in(idGestori));
        }

        if (parametriRicerca.getIdGestore() != null) {
            predicate = builder.and(predicate,
                    builder.equal(gestore.get("idGestore"), parametriRicerca.getIdGestore()));
        }
        return predicate;
    }

    private static Predicate addTipoDocFilter(Predicate predicate, CriteriaBuilder builder,
            Join<TsddrTPrevCons, TsddrDTipoDoc> tipoDocumento, PrevConsParametriRicerca parametriRicerca) {
        if (parametriRicerca.getIdTipoDoc() != null) {
            predicate = builder.and(predicate,
                    builder.equal(tipoDocumento.get("idTipoDoc"), parametriRicerca.getIdTipoDoc()));
        }
        return predicate;
    }

    private static Predicate addStatoDichiarazioneFilter(Predicate predicate, CriteriaBuilder builder,
            Join<TsddrTPrevCons, TsddrDStatoDichiarazione> statoDichiarazione, PrevConsParametriRicerca parametriRicerca) {
        if (parametriRicerca.getIdStatoDichiarazione() != null) {
            predicate = builder.and(predicate, builder.equal(statoDichiarazione.get("idStatoDichiarazione"),
                    parametriRicerca.getIdStatoDichiarazione()));
        }
        return predicate;

    }
}
