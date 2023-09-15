/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import it.csi.tsddr.tsddrbl.vo.dichiarazione.ConferimentoVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RiepilogoTotale;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutiConferitiVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.RifiutoConferitoVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.TotalePeriodo;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.TotaliRifiutiConferiti;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.VersamentiVO;
import it.csi.tsddr.tsddrbl.vo.dichiarazione.VersamentoVO;

public class DichAnnualeUtil {

	public static RifiutiConferitiVO createRifiutiConferitiVO(List<ConferimentoVO> conferimenti) {
		if (conferimenti == null) {
			return null;
		}
		
		List<RifiutoConferitoVO> rifiutiConferiti = conferimenti.parallelStream()
				.collect(Collectors.groupingBy(ConferimentoVO::getRifiutoTariffa)).entrySet().stream()
				.map(e -> new RifiutoConferitoVO(e.getKey(),
						CollectionUtils.isNotEmpty(e.getValue()) ? e.getValue().get(0).getUnitaMisura() : null,
						e.getValue(), createRiepilogo(e.getValue())))
				.collect(Collectors.toList());
		
		return new RifiutiConferitiVO(rifiutiConferiti, createTotali(rifiutiConferiti));
	}
	
	private static RiepilogoTotale createRiepilogo(List<ConferimentoVO> conferimenti) {
		return conferimenti.stream().reduce(new RiepilogoTotale(0.0, 0.0),
				(riepilogo, conferimento) -> riepilogo.addFromConferimento(conferimento), RiepilogoTotale::sum);
	}

	private static TotaliRifiutiConferiti createTotali(List<RifiutoConferitoVO> rifiutiConferiti) {
		List<TotalePeriodo> totaliPeriodi = rifiutiConferiti.stream().map(RifiutoConferitoVO::getConferimenti)
				.flatMap(List::stream).collect(Collectors.groupingBy(ConferimentoVO::getPeriodo)).entrySet().stream()
				.map(e -> new TotalePeriodo(e.getKey(), createRiepilogo(e.getValue()))).collect(Collectors.toList());
		
		return new TotaliRifiutiConferiti(totaliPeriodi, createRiepilogoPeriodi(totaliPeriodi));
	}
	
	private static RiepilogoTotale createRiepilogoPeriodi(List<TotalePeriodo> totaliPeriodi) {
//        return totaliPeriodi.stream().reduce(new RiepilogoTotale(),
//                (riepilogo, totalePeriodo) -> RiepilogoTotale.merge(riepilogo, totalePeriodo.getTotale()), RiepilogoTotale::merge);
		return totaliPeriodi.stream().map(TotalePeriodo::getTotale).reduce(new RiepilogoTotale(0.0, 0.0), RiepilogoTotale::sum);
	}
	
	public static VersamentiVO createVersamentiVO(List<VersamentoVO> versamenti, Double dovuto, Double creditoAP) {
		if (versamenti == null) {
			return null;
		}
		
		Double versato = versamenti.stream().map(VersamentoVO::getImportoVersato).reduce(0.00, Double::sum);
		
		Double saldo = (dovuto != null ? dovuto : 0) - (creditoAP != null ? creditoAP : 0) - versato;
		
		return new VersamentiVO(versamenti, versato != null ? versato : 0, creditoAP, saldo > 0 ? saldo : 0, saldo < 0 ? Math.abs(saldo) : 0);
	}
	
}
