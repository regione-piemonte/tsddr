/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.List;

import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;

public class DichAnnualeVO extends DichAnnualeBasicVO {

	private String annotazioni;
	private Double creditoImposta;
	private Long saldoImposta;
	private IndirizzoVO indirizzo;
	private RifiutiConferitiVO rifiutiConferiti;
	private VersamentiVO versamenti;
	private List<SoggettoMrVO> soggettiMr;

	/**
	 * Gets the annotazioni.
	 *
	 * @return the annotazioni
	 */
	public String getAnnotazioni() {
		return annotazioni;
	}

	/**
	 * Sets the annotazioni.
	 *
	 * @param annotazioni the new annotazioni
	 */
	public void setAnnotazioni(String annotazioni) {
		this.annotazioni = annotazioni;
	}

	/**
	 * Gets the credito imposta.
	 *
	 * @return the credito imposta
	 */
	public Double getCreditoImposta() {
		return creditoImposta;
	}

	/**
	 * Sets the credito imposta.
	 *
	 * @param creditoImposta the new credito imposta
	 */
	public void setCreditoImposta(Double creditoImposta) {
		this.creditoImposta = creditoImposta;
	}

	/**
	 * Gets the saldo imposta.
	 *
	 * @return the saldo imposta
	 */
	public Long getSaldoImposta() {
		return saldoImposta;
	}

	/**
	 * Sets the saldo imposta.
	 *
	 * @param saldoImposta the new saldo imposta
	 */
	public void setSaldoImposta(Long saldoImposta) {
		this.saldoImposta = saldoImposta;
	}

	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public IndirizzoVO getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Sets the indirizzo.
	 *
	 * @param indirizzo the new indirizzo
	 */
	public void setIndirizzo(IndirizzoVO indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets the rifiuti conferiti.
	 *
	 * @return the rifiuti conferiti
	 */
	public RifiutiConferitiVO getRifiutiConferiti() {
		return rifiutiConferiti;
	}

	/**
	 * Sets the rifiuti conferiti.
	 *
	 * @param rifiutiConferiti the new rifiuti conferiti
	 */
	public void setRifiutiConferiti(RifiutiConferitiVO rifiutiConferiti) {
		this.rifiutiConferiti = rifiutiConferiti;
	}

	/**
	 * Gets the versamenti.
	 *
	 * @return the versamenti
	 */
	public VersamentiVO getVersamenti() {
		return versamenti;
	}

	/**
	 * Sets the versamenti.
	 *
	 * @param versamenti the new versamenti
	 */
	public void setVersamenti(VersamentiVO versamenti) {
		this.versamenti = versamenti;
	}

	/**
	 * Gets the soggetti mr.
	 *
	 * @return the soggetti mr
	 */
	public List<SoggettoMrVO> getSoggettiMr() {
		return soggettiMr;
	}

	/**
	 * Sets the soggetti mr.
	 *
	 * @param soggettiMr the new soggetti mr
	 */
	public void setSoggettiMr(List<SoggettoMrVO> soggettiMr) {
		this.soggettiMr = soggettiMr;
	}

}