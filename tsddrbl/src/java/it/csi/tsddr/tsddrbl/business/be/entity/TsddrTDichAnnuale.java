/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_dich_annuale database table.
 * 
 */
@Entity
@Table(name="tsddr_t_dich_annuale")
@NamedQuery(name="TsddrTDichAnnuale.findAll", query="SELECT t FROM TsddrTDichAnnuale t")
public class TsddrTDichAnnuale extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dich_annuale", unique=true, nullable=false)
	private Long idDichAnnuale;

	@Column(nullable=false)
	private Long anno;

	@Column(length=500)
	private String annotazioni;

	@Column(name="credito_imposta")
	private BigDecimal creditoImposta;

	@Column(name="data_dichiarazione")
	private Date dataDichiarazione;

	@Column(name="saldo_imposta")
	private BigDecimal saldoImposta;

	@Column(nullable=false)
	private Long versione;
	
	@Column(name="num_protocollo")
	private String numProtocollo;
	
	@Column(name="data_protocollo")
	private Date dataProtocollo;

	//bi-directional many-to-one association to TsddrTConferimento
	@OneToMany(mappedBy="dichAnnuale", cascade = CascadeType.PERSIST)
	private List<TsddrTConferimento> conferimenti;

	//bi-directional many-to-one association to TsddrDStatoDichiarazione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_stato_dichiarazione", nullable=false)
	private TsddrDStatoDichiarazione statoDichiarazione;

	//bi-directional many-to-one association to TsddrTImpianti
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_impianto", nullable=false)
	private TsddrTImpianto impianto;

	//bi-directional many-to-one association to TsddrTIndirizzi
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_ind_dep_provv")
	private TsddrTIndirizzo indirizzo;

	//bi-directional many-to-one association to TsddrTSoggettoMr
	@OneToMany(mappedBy="dichAnnuale", cascade = CascadeType.PERSIST)
	private List<TsddrTSoggettoMr> soggettiMr;

	//bi-directional many-to-one association to TsddrTVersamento
	@OneToMany(mappedBy="dichAnnuale", cascade = CascadeType.PERSIST)
	private List<TsddrTVersamento> versamenti;

	/**
	 * Instantiates a new tsddr T dich annuale.
	 */
	public TsddrTDichAnnuale() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id dich annuale.
	 *
	 * @return the id dich annuale
	 */
	public Long getIdDichAnnuale() {
		return this.idDichAnnuale;
	}

	/**
	 * Sets the id dich annuale.
	 *
	 * @param idDichAnnuale the new id dich annuale
	 */
	public void setIdDichAnnuale(Long idDichAnnuale) {
		this.idDichAnnuale = idDichAnnuale;
	}

	/**
	 * Gets the anno.
	 *
	 * @return the anno
	 */
	public Long getAnno() {
		return this.anno;
	}

	/**
	 * Sets the anno.
	 *
	 * @param anno the new anno
	 */
	public void setAnno(Long anno) {
		this.anno = anno;
	}

	/**
	 * Gets the annotazioni.
	 *
	 * @return the annotazioni
	 */
	public String getAnnotazioni() {
		return this.annotazioni;
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
	public BigDecimal getCreditoImposta() {
		return this.creditoImposta;
	}

	/**
	 * Sets the credito imposta.
	 *
	 * @param creditoImposta the new credito imposta
	 */
	public void setCreditoImposta(BigDecimal creditoImposta) {
		this.creditoImposta = creditoImposta;
	}

	/**
	 * Gets the data dichiarazione.
	 *
	 * @return the data dichiarazione
	 */
	public Date getDataDichiarazione() {
		return this.dataDichiarazione;
	}

	/**
	 * Sets the data dichiarazione.
	 *
	 * @param dataDichiarazione the new data dichiarazione
	 */
	public void setDataDichiarazione(Date dataDichiarazione) {
		this.dataDichiarazione = dataDichiarazione;
	}

	/**
	 * Gets the saldo imposta.
	 *
	 * @return the saldo imposta
	 */
	public BigDecimal getSaldoImposta() {
		return this.saldoImposta;
	}

	/**
	 * Sets the saldo imposta.
	 *
	 * @param saldoImposta the new saldo imposta
	 */
	public void setSaldoImposta(BigDecimal saldoImposta) {
		this.saldoImposta = saldoImposta;
	}

	/**
	 * Gets the versione.
	 *
	 * @return the versione
	 */
	public Long getVersione() {
		return this.versione;
	}

	/**
	 * Sets the versione.
	 *
	 * @param versione the new versione
	 */
	public void setVersione(Long versione) {
		this.versione = versione;
	}

	/**
	 * Gets the conferimenti.
	 *
	 * @return the conferimenti
	 */
	public List<TsddrTConferimento> getConferimenti() {
		return conferimenti;
	}

	/**
	 * Sets the conferimenti.
	 *
	 * @param conferimenti the new conferimenti
	 */
	public void setConferimenti(List<TsddrTConferimento> conferimenti) {
		this.conferimenti = conferimenti;
	}
	
	/**
	 * Adds the conferimento.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T conferimento
	 */
	public TsddrTConferimento addConferimento(TsddrTConferimento conferimento) {
		getConferimenti().add(conferimento);
		conferimento.setDichAnnuale(this);

		return conferimento;
	}

	/**
	 * Removes the conferimento.
	 *
	 * @param conferimento the conferimento
	 * @return the tsddr T conferimento
	 */
	public TsddrTConferimento removeConferimento(TsddrTConferimento conferimento) {
		getConferimenti().remove(conferimento);
		conferimento.setDichAnnuale(null);

		return conferimento;
	}

	/**
	 * Gets the stato dichiarazione.
	 *
	 * @return the stato dichiarazione
	 */
	public TsddrDStatoDichiarazione getStatoDichiarazione() {
		return statoDichiarazione;
	}

	/**
	 * Sets the stato dichiarazione.
	 *
	 * @param statoDichiarazione the new stato dichiarazione
	 */
	public void setStatoDichiarazione(TsddrDStatoDichiarazione statoDichiarazione) {
		this.statoDichiarazione = statoDichiarazione;
	}

	/**
	 * Gets the impianto.
	 *
	 * @return the impianto
	 */
	public TsddrTImpianto getImpianto() {
		return impianto;
	}

	/**
	 * Sets the impianto.
	 *
	 * @param impianto the new impianto
	 */
	public void setImpianto(TsddrTImpianto impianto) {
		this.impianto = impianto;
	}

	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public TsddrTIndirizzo getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Sets the indirizzo.
	 *
	 * @param indirizzo the new indirizzo
	 */
	public void setIndirizzo(TsddrTIndirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets the soggetti mr.
	 *
	 * @return the soggetti mr
	 */
	public List<TsddrTSoggettoMr> getSoggettiMr() {
		return soggettiMr;
	}

	/**
	 * Sets the soggetti mr.
	 *
	 * @param soggettiMr the new soggetti mr
	 */
	public void setSoggettiMr(List<TsddrTSoggettoMr> soggettiMr) {
		this.soggettiMr = soggettiMr;
	}
	
	/**
	 * Adds the soggetto mr.
	 *
	 * @param soggettoMr the soggetto mr
	 * @return the tsddr T soggetto mr
	 */
	public TsddrTSoggettoMr addSoggettoMr(TsddrTSoggettoMr soggettoMr) {
		getSoggettiMr().add(soggettoMr);
		soggettoMr.setDichAnnuale(this);

		return soggettoMr;
	}

	/**
	 * Removes the soggetto mr.
	 *
	 * @param soggettoMr the soggetto mr
	 * @return the tsddr T soggetto mr
	 */
	public TsddrTSoggettoMr removeSoggettoMr(TsddrTSoggettoMr soggettoMr) {
		getSoggettiMr().remove(soggettoMr);
		soggettoMr.setDichAnnuale(null);

		return soggettoMr;
	}

	/**
	 * Gets the versamenti.
	 *
	 * @return the versamenti
	 */
	public List<TsddrTVersamento> getVersamenti() {
		return versamenti;
	}

	/**
	 * Sets the versamenti.
	 *
	 * @param versamenti the new versamenti
	 */
	public void setVersamenti(List<TsddrTVersamento> versamenti) {
		this.versamenti = versamenti;
	}
	
	/**
	 * Adds the versamento.
	 *
	 * @param versamento the versamento
	 * @return the tsddr T versamento
	 */
	public TsddrTVersamento addVersamento(TsddrTVersamento versamento) {
		getVersamenti().add(versamento);
		versamento.setDichAnnuale(this);

		return versamento;
	}

	/**
	 * Removes the versamento.
	 *
	 * @param versamento the versamento
	 * @return the tsddr T versamento
	 */
	public TsddrTVersamento removeVersamento(TsddrTVersamento versamento) {
		getVersamenti().remove(versamento);
		versamento.setDichAnnuale(null);

		return versamento;
	}

	/**
	 * Gets the num protocollo.
	 *
	 * @return the num protocollo
	 */
	public String getNumProtocollo() {
		return numProtocollo;
	}

	/**
	 * Sets the num protocollo.
	 *
	 * @param numProtocollo the new num protocollo
	 */
	public void setNumProtocollo(String numProtocollo) {
		this.numProtocollo = numProtocollo;
	}

	/**
	 * Gets the data protocollo.
	 *
	 * @return the data protocollo
	 */
	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	/**
	 * Sets the data protocollo.
	 *
	 * @param dataProtocollo the new data protocollo
	 */
	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

}