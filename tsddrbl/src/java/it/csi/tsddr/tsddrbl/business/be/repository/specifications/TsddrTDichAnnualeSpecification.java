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
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTDichAnnuale;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTGestore;
import it.csi.tsddr.tsddrbl.business.be.entity.TsddrTImpianto;
import it.csi.tsddr.tsddrbl.util.enums.StatoDichiarazione;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.DichiarazioneParametriRicerca;

public class TsddrTDichAnnualeSpecification extends BaseSpecification {

    public static Specification<TsddrTDichAnnuale> searchByParams(DichiarazioneParametriRicerca parametriRicerca,
            Date targetDate, boolean isProfiloBO, boolean isProfiloPregresso, List<TsddrTGestore> gestoriUtente) {
        return new Specification<TsddrTDichAnnuale>() {
            @Override
            public Predicate toPredicate(Root<TsddrTDichAnnuale> root, CriteriaQuery<?> query,
                    CriteriaBuilder builder) {
                boolean isEnableAllGestori = false;
                if ((isProfiloBO || isProfiloPregresso)&& gestoriUtente.isEmpty()) {
                    isEnableAllGestori = true;
                }
                Join<TsddrTDichAnnuale, TsddrDStatoDichiarazione> statoDichiarazione = root.join("statoDichiarazione");
                Join<TsddrTDichAnnuale, TsddrTImpianto> impianto = root.join("impianto");
                Join<TsddrTImpianto, TsddrTGestore> gestore = impianto.join("gestore");

                Predicate predicate = builder.and(
                        hasValidDate(statoDichiarazione, builder, targetDate),
                        hasValidDate(impianto, builder, targetDate),
                        hasValidDate(gestore, builder, targetDate));

                predicate = addAnnoDal(parametriRicerca, predicate, builder, root);
                predicate = addAnnoAl(parametriRicerca, predicate, builder, root);
                predicate = addStatoDichiarazione(isProfiloBO, predicate, builder, statoDichiarazione);
                predicate = addIdImpianto(parametriRicerca, predicate, builder, impianto);
                predicate = addGestori(isEnableAllGestori, gestoriUtente, predicate, builder, gestore);
                predicate = addIdGestore(parametriRicerca, predicate, builder, gestore);

                if(isProfiloPregresso){
                    predicate = builder.and(predicate, builder.equal(root.get("pregresso"), true));
                } else if (!isProfiloBO){
                    // si tratta di un profilo FO, che deve prendere tutte le dich non pregresse, oppure quelle pregresse in stato CONSOLIDATO (4)
                    predicate = builder.and(predicate, builder.or(  builder.equal(root.get("pregresso"), false), 
                                                                    builder.and(    builder.equal(root.get("pregresso"), true), 
                                                                                    builder.equal(statoDichiarazione.get("idStatoDichiarazione"),
                                                                                                    StatoDichiarazione.PREGRESSO_CONSOLIDATO.getId()))));
                }

                
                List<Order> orderList = new ArrayList<>();
                //  Issue 121
                orderList.add(builder.asc(gestore.get("codFiscPartiva")));
                orderList.add(builder.asc(impianto.get("denominazione")));
                orderList.add(builder.desc(root.get("anno")));
                orderList.add(builder.desc(root.get("versione")));
                orderList.add(builder.asc(root.get("idDichAnnuale")));
                query.orderBy(orderList);

                return predicate;
            }

        };
    }

    private static Predicate addAnnoDal(DichiarazioneParametriRicerca parametriRicerca, Predicate predicate,
            CriteriaBuilder builder, Root<TsddrTDichAnnuale> root) {
        if (parametriRicerca.getAnnoDal() != null) {
            return builder.and(predicate,
                    builder.greaterThanOrEqualTo(root.get("anno"), parametriRicerca.getAnnoDal()));
        }
        return predicate;
    }

    private static Predicate addAnnoAl(DichiarazioneParametriRicerca parametriRicerca, Predicate predicate,
            CriteriaBuilder builder, Root<TsddrTDichAnnuale> root) {
        if (parametriRicerca.getAnnoAl() != null) {
            return builder.and(predicate, builder.lessThanOrEqualTo(root.get("anno"), parametriRicerca.getAnnoAl()));
        }
        return predicate;
    }

    private static Predicate addStatoDichiarazione(boolean isProfiloBO, Predicate predicate, CriteriaBuilder builder,
            Join<TsddrTDichAnnuale, TsddrDStatoDichiarazione> statoDichiarazione) {
        if (isProfiloBO) {
            return builder.and(builder.or(  builder.equal(statoDichiarazione.get("idStatoDichiarazione"),
                                                StatoDichiarazione.PREGRESSO_CONSOLIDATO.getId()), 
                                            builder.equal(statoDichiarazione.get("idStatoDichiarazione"),
                                                StatoDichiarazione.INVIATA_PROTOCOLLATA.getId())));
        }
        return predicate;
    }

    private static Predicate addIdImpianto(DichiarazioneParametriRicerca parametriRicerca, Predicate predicate,
            CriteriaBuilder builder, Join<TsddrTDichAnnuale, TsddrTImpianto> impianto) {
        if (parametriRicerca.getIdImpianto() != null) {
            return builder.and(predicate, builder.equal(impianto.get("idImpianto"), parametriRicerca.getIdImpianto()));
        }
        return predicate;
    }

    private static Predicate addGestori(boolean isEnableAllGestori, List<TsddrTGestore> gestoriUtente, Predicate predicate,
            CriteriaBuilder builder, Join<TsddrTImpianto, TsddrTGestore> gestore) {
        if (!isEnableAllGestori) {
            List<Long> idGestori = new ArrayList<>();
            for (TsddrTGestore gestoreUtente : gestoriUtente) {
                idGestori.add(gestoreUtente.getIdGestore());
            }
            return builder.and(predicate, gestore.get("idGestore").in(idGestori));
        }
        return predicate;
    }

    private static Predicate addIdGestore(DichiarazioneParametriRicerca parametriRicerca, Predicate predicate,
            CriteriaBuilder builder, Join<TsddrTImpianto, TsddrTGestore> gestore) {
        if (parametriRicerca.getIdGestore() != null) {
            return builder.and(predicate, builder.equal(gestore.get("idGestore"), parametriRicerca.getIdGestore()));
        }
        return predicate;
    }
}
