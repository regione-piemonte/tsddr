/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

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
 * The persistent class for the tsddr_t_indirizzi database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_indirizzi")
@NamedQuery(name = "TsddrTIndirizzo.findAll", query = "SELECT t FROM TsddrTIndirizzo t")
public class TsddrTIndirizzo extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_indirizzo", unique = true, nullable = false)
	private Long idIndirizzo;
	
	@Column(name="original_id")
	private Long originalId;
	
	private Long versione;
	
	@Column(nullable = false, length = 100)
	private String indirizzo;
	
	@Column(length = 5)
	private String cap;

	// bi-directional many-to-one association to TsddrDComune
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comune")
	private TsddrDComune comune;

	// bi-directional many-to-one association to TsddrDNazione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nazione")
	private TsddrDNazione nazione;

	// bi-directional many-to-one association to TsddrDSedime
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sedime")
	private TsddrDSedime sedime;

	// bi-directional many-to-one association to TsddrDTipoIndirizzo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_indirizzo")
	private TsddrDTipoIndirizzo tipoIndirizzo;

	// bi-directional many-to-one association to TsddrTGestore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_indirizzo", referencedColumnName = "id_sede_legale", insertable = false, updatable = false)
	private TsddrTGestore gestore;

	// bi-directional many-to-one association to TsddrTImpianto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_indirizzo", insertable = false, updatable = false)
	private TsddrTImpianto impianto;
	
	//bi-directional many-to-one association to TsddrTDichAnnuale
	@OneToMany(mappedBy="impianto")
	private List<TsddrTDichAnnuale> dichAnnuali;
	
	//bi-directional many-to-one association to TsddrTPrevCons
    @OneToMany(mappedBy="indirizzo")
    private List<TsddrTPrevCons> prevCons;

	/**
	 * Instantiates a new tsddr T indirizzo.
	 */
	public TsddrTIndirizzo() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id indirizzo.
	 *
	 * @return the id indirizzo
	 */
	public Long getIdIndirizzo() {
		return idIndirizzo;
	}
	
	/**
	 * Sets the id indirizzo.
	 *
	 * @param idIndirizzo the new id indirizzo
	 */
	public void setIdIndirizzo(Long idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	
	/**
	 * Gets the versione.
	 *
	 * @return the versione
	 */
	public Long getVersione() {
		return versione;
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
	 * Gets the original id.
	 *
	 * @return the original id
	 */
	public Long getOriginalId() {
		return originalId;
	}

	/**
	 * Sets the original id.
	 *
	 * @param originalId the new original id
	 */
	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}
	
	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return this.indirizzo;
	}

	/**
	 * Sets the indirizzo.
	 *
	 * @param indirizzo the new indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets the cap.
	 *
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * Sets the cap.
	 *
	 * @param cap the new cap
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Gets the comune.
	 *
	 * @return the comune
	 */
	public TsddrDComune getComune() {
		return this.comune;
	}

	/**
	 * Sets the comune.
	 *
	 * @param comune the new comune
	 */
	public void setComune(TsddrDComune comune) {
		this.comune = comune;
	}

	/**
	 * Gets the nazione.
	 *
	 * @return the nazione
	 */
	public TsddrDNazione getNazione() {
		return this.nazione;
	}

	/**
	 * Sets the nazione.
	 *
	 * @param nazione the new nazione
	 */
	public void setNazione(TsddrDNazione nazione) {
		this.nazione = nazione;
	}

	/**
	 * Gets the sedime.
	 *
	 * @return the sedime
	 */
	public TsddrDSedime getSedime() {
		return this.sedime;
	}

	/**
	 * Sets the sedime.
	 *
	 * @param sedime the new sedime
	 */
	public void setSedime(TsddrDSedime sedime) {
		this.sedime = sedime;
	}

	/**
	 * Gets the tipo indirizzo.
	 *
	 * @return the tipo indirizzo
	 */
	public TsddrDTipoIndirizzo getTipoIndirizzo() {
		return this.tipoIndirizzo;
	}

	/**
	 * Sets the tipo indirizzo.
	 *
	 * @param tipoIndirizzo the new tipo indirizzo
	 */
	public void setTipoIndirizzo(TsddrDTipoIndirizzo tipoIndirizzo) {
		this.tipoIndirizzo = tipoIndirizzo;
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
	 * Gets the impianto.
	 *
	 * @return the impianto
	 */
	public TsddrTImpianto getImpianto() {
		return this.impianto;
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
		dichAnnuale.setIndirizzo(this);

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
		dichAnnuale.setIndirizzo(null);

		return dichAnnuale;
	}
	
    /**
     * Gets the prev cons.
     *
     * @return the prev cons
     */
    public List<TsddrTPrevCons> getPrevCons() {
        return prevCons;
    }

    /**
     * Sets the prev cons.
     *
     * @param prevCons the new prev cons
     */
    public void setPrevCons(List<TsddrTPrevCons> prevCons) {
        this.prevCons = prevCons;
    }
    
    /**
     * Adds the tsddr T prev cons.
     *
     * @param prevCons the prev cons
     * @return the tsddr T prev cons
     */
    public TsddrTPrevCons addTsddrTPrevCons(TsddrTPrevCons prevCons) {
        getPrevCons().add(prevCons);
        prevCons.setIndirizzo(this);

        return prevCons;
    }

    /**
     * Removes the tsddr T prev cons.
     *
     * @param prevCons the prev cons
     * @return the tsddr T prev cons
     */
    public TsddrTPrevCons removeTsddrTPrevCons(TsddrTPrevCons prevCons) {
        getPrevCons().remove(prevCons);
        prevCons.setIndirizzo(null);

        return prevCons;
    }

}