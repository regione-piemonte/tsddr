/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.accreditamento;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.datisogg.DatiSoggVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.profilo.ProfiloVO;

public class DomandaAccreditamentoVO extends AbstractVO {

	private static final long serialVersionUID = 2239869237895134912L;

	private Long idDomanda;
	private GestoreVO gestore;
	private DatiSoggVO richiedente;
	private String notaUtente;
	private String notaLavorazione;
	private StatoDomandaVO stato;
	private Date dataRichiesta;
	private Date dataRisposta;
	private ProfiloVO profilo;

	/**
	 * @return the idDomanda
	 */
	public Long getIdDomanda() {
		return idDomanda;
	}

	/**
	 * @param idDomanda the idDomanda to set
	 */
	public void setIdDomanda(Long idDomanda) {
		this.idDomanda = idDomanda;
	}

	/**
	 * @return the gestore
	 */
	public GestoreVO getGestore() {
		return gestore;
	}

	/**
	 * @param gestore the gestore to set
	 */
	public void setGestore(GestoreVO gestore) {
		this.gestore = gestore;
	}

	/**
	 * @return the richiedente
	 */
	public DatiSoggVO getRichiedente() {
		return richiedente;
	}

	/**
	 * @param richiedente the richiedente to set
	 */
	public void setRichiedente(DatiSoggVO richiedente) {
		this.richiedente = richiedente;
	}

	/**
	 * @return the notaUtente
	 */
	public String getNotaUtente() {
		return notaUtente;
	}

	/**
	 * @param notaUtente the notaUtente to set
	 */
	public void setNotaUtente(String notaUtente) {
		this.notaUtente = notaUtente;
	}

	/**
	 * @return the notaLavorazione
	 */
	public String getNotaLavorazione() {
		return notaLavorazione;
	}

	/**
	 * @param notaLavorazione the notaLavorazione to set
	 */
	public void setNotaLavorazione(String notaLavorazione) {
		this.notaLavorazione = notaLavorazione;
	}

	/**
	 * @return the stato
	 */
	public StatoDomandaVO getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(StatoDomandaVO stato) {
		this.stato = stato;
	}

	/**
	 * @return the dataRichiesta
	 */
	public Date getDataRichiesta() {
		return dataRichiesta;
	}

	/**
	 * @param dataRichiesta the dataRichiesta to set
	 */
	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	/**
	 * @return the dataRisposta
	 */
	public Date getDataRisposta() {
		return dataRisposta;
	}

	/**
	 * @param dataRisposta the dataRisposta to set
	 */
	public void setDataRisposta(Date dataRisposta) {
		this.dataRisposta = dataRisposta;
	}

	/**
	 * Gets the profilo.
	 *
	 * @return the profilo
	 */
	public ProfiloVO getProfilo() {
		return profilo;
	}

	/**
	 * Sets the profilo.
	 *
	 * @param profilo the new profilo
	 */
	public void setProfilo(ProfiloVO profilo) {
		this.profilo = profilo;
	}

}
