/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.impianto;

import java.util.Date;
import java.util.List;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.gestore.GestoreVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;

public class ImpiantoVO extends AbstractVO {

	private static final long serialVersionUID = 1311001080560799178L;

	private Long idImpianto;
	private String denominazione;
	private StatoImpiantoVO statoImpianto;
	private TipoImpiantoVO tipoImpianto;
	private GestoreVO gestore;
	private IndirizzoVO indirizzo;
	private List<GenericLineaVO> linee;
	private List<AttoVO> atti;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	private Boolean deletable;
	private Boolean readOnly;

	/**
	 * Gets the id impianto.
	 *
	 * @return the id impianto
	 */
	public Long getIdImpianto() {
		return idImpianto;
	}

	/**
	 * Sets the id impianto.
	 *
	 * @param idImpianto the new id impianto
	 */
	public void setIdImpianto(Long idImpianto) {
		this.idImpianto = idImpianto;
	}

	/**
	 * Gets the denominazione.
	 *
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * Sets the denominazione.
	 *
	 * @param denominazione the new denominazione
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	/**
	 * Gets the stato impianto.
	 *
	 * @return the stato impianto
	 */
	public StatoImpiantoVO getStatoImpianto() {
		return statoImpianto;
	}

	/**
	 * Sets the stato impianto.
	 *
	 * @param statoImpianto the new stato impianto
	 */
	public void setStatoImpianto(StatoImpiantoVO statoImpianto) {
		this.statoImpianto = statoImpianto;
	}

	/**
	 * Gets the tipo impianto.
	 *
	 * @return the tipo impianto
	 */
	public TipoImpiantoVO getTipoImpianto() {
		return tipoImpianto;
	}

	/**
	 * Sets the tipo impianto.
	 *
	 * @param tipoImpianto the new tipo impianto
	 */
	public void setTipoImpianto(TipoImpiantoVO tipoImpianto) {
		this.tipoImpianto = tipoImpianto;
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
	 * Gets the linee.
	 *
	 * @return the linee
	 */
	public List<GenericLineaVO> getLinee() {
		return linee;
	}

	/**
	 * Sets the linee.
	 *
	 * @param linee the new linee
	 */
	public void setLinee(List<GenericLineaVO> linee) {
		this.linee = linee;
	}

	/**
	 * Gets the atti.
	 *
	 * @return the atti
	 */
	public List<AttoVO> getAtti() {
		return atti;
	}

	/**
	 * Sets the atti.
	 *
	 * @param atti the new atti
	 */
	public void setAtti(List<AttoVO> atti) {
		this.atti = atti;
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
	 * Gets the deletable.
	 *
	 * @return the deletable
	 */
	public Boolean getDeletable() {
		return deletable;
	}

	/**
	 * Sets the deletable.
	 *
	 * @param deletable the new deletable
	 */
	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}

	/**
	 * Gets the read only.
	 *
	 * @return the read only
	 */
	public Boolean getReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the read only.
	 *
	 * @param readOnly the new read only
	 */
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	
}
