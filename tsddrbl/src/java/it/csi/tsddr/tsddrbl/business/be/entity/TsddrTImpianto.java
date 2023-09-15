/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_impianti database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_impianti")
@NamedQuery(name = "TsddrTImpianto.findAll", query = "SELECT t FROM TsddrTImpianto t")
public class TsddrTImpianto extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_impianto", unique = true, nullable = false)
	private Long idImpianto;

	@Column(nullable = false, length = 200)
	private String denominazione;

	// bi-directional many-to-one association to TsddrRImpiantoLinea
	@OneToMany(mappedBy = "impianto")
	private List<TsddrRImpiantoLinea> rImpiantiLinee;

	// bi-directional many-to-one association to TsddrDStatoImpianto
	@ManyToOne
	@JoinColumn(name = "id_stato")
	private TsddrDStatoImpianto statoImpianto;

	// bi-directional many-to-one association to TsddrDTipoImpianto
	@ManyToOne
	@JoinColumn(name = "id_tipo_impianto")
	private TsddrDTipoImpianto tipoImpianto;

	// bi-directional many-to-one association to TsddrTGestore
	@ManyToOne
	@JoinColumn(name = "id_gestore")
	private TsddrTGestore gestore;

	// bi-directional many-to-one association to TsddrTIndirizzo
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_indirizzo")
	private TsddrTIndirizzo indirizzo;

	// bi-directional many-to-many association to TsddrTLinea
	@ManyToMany
	@JoinTable(name = "tsddr_r_impianti_linee", joinColumns = {
			@JoinColumn(name = "id_impianto", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_linea", nullable = false) })
	private List<TsddrTLinea> linee;

	// bi-directional many-to-many association to TsddrTSottoLinea
	@ManyToMany
	@JoinTable(name = "tsddr_r_impianti_linee", joinColumns = {
			@JoinColumn(name = "id_impianto", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_sotto_linea", nullable = false) })
	private List<TsddrTSottoLinea> sottoLinee;

	// bi-directional many-to-one association to TsddrTAtto
	@OneToMany(mappedBy = "impianto")
	private List<TsddrTAtto> atti;
	
	//bi-directional many-to-one association to TsddrTDichAnnuale
	@OneToMany(mappedBy="impianto")
	private List<TsddrTDichAnnuale> dichAnnuali;
	
	//bi-directional many-to-one association to TsddrTPrevCons
    @OneToMany(mappedBy="impianto")
    private List<TsddrTPrevCons> prevCons;

	/**
	 * Instantiates a new tsddr T impianto.
	 */
	public TsddrTImpianto() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id impianto.
	 *
	 * @return the id impianto
	 */
	public Long getIdImpianto() {
		return this.idImpianto;
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
		return this.denominazione;
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
	 * Gets the r impianti linee.
	 *
	 * @return the r impianti linee
	 */
	public List<TsddrRImpiantoLinea> getRImpiantiLinee() {
		return this.rImpiantiLinee;
	}

	/**
	 * Sets the r impianti linee.
	 *
	 * @param rImpiantiLinee the new r impianti linee
	 */
	public void setRImpiantiLinee(List<TsddrRImpiantoLinea> rImpiantiLinee) {
		this.rImpiantiLinee = rImpiantiLinee;
	}

	/**
	 * Adds the R impianti linee.
	 *
	 * @param rImpiantiLinee the r impianti linee
	 * @return the tsddr R impianto linea
	 */
	public TsddrRImpiantoLinea addRImpiantiLinee(TsddrRImpiantoLinea rImpiantiLinee) {
		getRImpiantiLinee().add(rImpiantiLinee);
		rImpiantiLinee.setImpianto(this);

		return rImpiantiLinee;
	}

	/**
	 * Removes the R impianti linee.
	 *
	 * @param rImpiantiLinee the r impianti linee
	 * @return the tsddr R impianto linea
	 */
	public TsddrRImpiantoLinea removeRImpiantiLinee(TsddrRImpiantoLinea rImpiantiLinee) {
		getRImpiantiLinee().remove(rImpiantiLinee);
		rImpiantiLinee.setImpianto(null);

		return rImpiantiLinee;
	}

	/**
	 * Gets the stato impianto.
	 *
	 * @return the stato impianto
	 */
	public TsddrDStatoImpianto getStatoImpianto() {
		return this.statoImpianto;
	}

	/**
	 * Sets the stato impianto.
	 *
	 * @param statoImpianto the new stato impianto
	 */
	public void setStatoImpianto(TsddrDStatoImpianto statoImpianto) {
		this.statoImpianto = statoImpianto;
	}

	/**
	 * Gets the tipo impianto.
	 *
	 * @return the tipo impianto
	 */
	public TsddrDTipoImpianto getTipoImpianto() {
		return this.tipoImpianto;
	}

	/**
	 * Sets the tipo impianto.
	 *
	 * @param tipoImpianto the new tipo impianto
	 */
	public void setTipoImpianto(TsddrDTipoImpianto tipoImpianto) {
		this.tipoImpianto = tipoImpianto;
	}

	/**
	 * Gets the gestore.
	 *
	 * @return the gestore
	 */
	public TsddrTGestore getGestore() {
		return this.gestore;
	}

	/**
	 * Sets the gestore.
	 *
	 * @param gestore the new gestore
	 */
	public void setGestore(TsddrTGestore gestore) {
		this.gestore = gestore;
	}

	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public TsddrTIndirizzo getIndirizzo() {
		return this.indirizzo;
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
	 * Gets the linee.
	 *
	 * @return the linee
	 */
	public List<TsddrTLinea> getLinee() {
		return this.linee;
	}

	/**
	 * Sets the linee.
	 *
	 * @param linee the new linee
	 */
	public void setLinee(List<TsddrTLinea> linee) {
		this.linee = linee;
	}

	/**
	 * Gets the sotto linee.
	 *
	 * @return the sotto linee
	 */
	public List<TsddrTSottoLinea> getSottoLinee() {
		return this.sottoLinee;
	}

	/**
	 * Sets the sotto linee.
	 *
	 * @param sottoLinee the new sotto linee
	 */
	public void setSottoLinee(List<TsddrTSottoLinea> sottoLinee) {
		this.sottoLinee = sottoLinee;
	}

	/**
	 * Gets the atti.
	 *
	 * @return the atti
	 */
	public List<TsddrTAtto> getAtti() {
		return atti;
	}

	/**
	 * Sets the atti.
	 *
	 * @param atti the atti to set
	 */
	public void setAtti(List<TsddrTAtto> atti) {
		this.atti = atti;
	}
	
	/**
	 * Adds the atti.
	 *
	 * @param atto the atto
	 * @return the tsddr T atto
	 */
	public TsddrTAtto addAtti(TsddrTAtto atto) {
		getAtti().add(atto);
		atto.setImpianto(this);

		return atto;
	}

	/**
	 * Removes the atti.
	 *
	 * @param atto the atto
	 * @return the tsddr T atto
	 */
	public TsddrTAtto removeAtti(TsddrTAtto atto) {
		getAtti().remove(atto);
		atto.setImpianto(null);

		return atto;
	}

	/**
	 * Gets the dich annuali.
	 *
	 * @return the dich annuali
	 */
	public List<TsddrTDichAnnuale> getDichAnnuali() {
		return dichAnnuali;
	}

	/**
	 * Sets the dich annuali.
	 *
	 * @param dichAnnuali the new dich annuali
	 */
	public void setDichAnnuali(List<TsddrTDichAnnuale> dichAnnuali) {
		this.dichAnnuali = dichAnnuali;
	}
	
	/**
	 * Adds the tsddr T dich annuale.
	 *
	 * @param dichAnnuale the dich annuale
	 * @return the tsddr T dich annuale
	 */
	public TsddrTDichAnnuale addTsddrTDichAnnuale(TsddrTDichAnnuale dichAnnuale) {
		getDichAnnuali().add(dichAnnuale);
		dichAnnuale.setImpianto(this);

		return dichAnnuale;
	}

	/**
	 * Removes the tsddr T dich annuale.
	 *
	 * @param dichAnnuale the dich annuale
	 * @return the tsddr T dich annuale
	 */
	public TsddrTDichAnnuale removeTsddrTDichAnnuale(TsddrTDichAnnuale dichAnnuale) {
		getDichAnnuali().remove(dichAnnuale);
		dichAnnuale.setImpianto(null);

		return dichAnnuale;
	}

}