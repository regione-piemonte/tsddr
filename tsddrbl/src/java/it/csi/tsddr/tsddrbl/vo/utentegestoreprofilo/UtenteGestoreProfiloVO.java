/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.utentegestoreprofilo;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;

public class UtenteGestoreProfiloVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idUtente;
	private Long idProfilo;
	private Long idGestore;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private GestoreVO gestore;

	/**
	 * Gets the id utente.
	 *
	 * @return the id utente
	 */
	public Long getIdUtente() {
		return idUtente;
	}

	/**
	 * Sets the id utente.
	 *
	 * @param idUtente the new id utente
	 */
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	/**
	 * Gets the id profilo.
	 *
	 * @return the id profilo
	 */
	public Long getIdProfilo() {
		return idProfilo;
	}

	/**
	 * Sets the id profilo.
	 *
	 * @param idProfilo the new id profilo
	 */
	public void setIdProfilo(Long idProfilo) {
		this.idProfilo = idProfilo;
	}

	/**
	 * Gets the id gestore.
	 *
	 * @return the id gestore
	 */
	public Long getIdGestore() {
		return idGestore;
	}

	/**
	 * Sets the id gestore.
	 *
	 * @param idGestore the new id gestore
	 */
	public void setIdGestore(Long idGestore) {
		this.idGestore = idGestore;
	}

	/**
	 * Gets the data inizio validita.
	 *
	 * @return the data inizio validita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * Sets the data inizio validita.
	 *
	 * @param dataInizioValidita the new data inizio validita
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * Gets the data fine validita.
	 *
	 * @return the data fine validita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * Sets the data fine validita.
	 *
	 * @param dataFineValidita the new data fine validita
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * Gets the gestore.
	 *
	 * @return the gestore
	 */
	public GestoreVO getGestore() {
		return gestore;
	}

	/**
	 * Sets the gestore.
	 *
	 * @param gestore the new gestore
	 */
	public void setGestore(GestoreVO gestore) {
		this.gestore = gestore;
	}

}
