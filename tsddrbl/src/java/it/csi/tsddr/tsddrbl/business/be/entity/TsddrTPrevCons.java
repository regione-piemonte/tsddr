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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tsddr_t_prev_cons database table.
 * 
 */
@Entity
@Table(name="tsddr_t_prev_cons")
@NamedQuery(name="TsddrTPrevCons.findAll", query="SELECT t FROM TsddrTPrevCons t")
public class TsddrTPrevCons extends AbstractEntity {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id prev cons. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prev_cons", unique=true, nullable=false)
	private Long idPrevCons;

	/** The anno tributo. */
	@Column(name="anno_tributo", nullable=false)
	private Long annoTributo;

	/** The data doc. */
	@Temporal(TemporalType.DATE)
	@Column(name="data_doc", nullable=false)
	private Date dataDoc;

	/** The modalita. */
	@Column(length=1000)
	private String modalita;

	/** The perc recupero. */
	@Column(name="perc_recupero", precision=10, scale=6)
	private BigDecimal percRecupero;

	/** The perc scarto. */
	@Column(name="perc_scarto", precision=10, scale=6)
	private BigDecimal percScarto;

	/** The tipo doc. */
	//bi-directional many-to-one association to TsddrDTipoDoc
	@ManyToOne
	@JoinColumn(name="id_tipo_documento", nullable=false)
	private TsddrDTipoDoc tipoDoc;
	
	/** The data invio doc. */
	@Temporal(TemporalType.DATE)
    @Column(name="data_invio_doc")
    private Date dataInvioDoc;
	
	/** The id prev cons R mr. */
	@Column(name="id_prev_cons_r_mr")
    private Long idPrevConsRMr;
	
	/** The num protocollo. */
	@Column(name="num_protocollo", length=50)
    private String numProtocollo;
	
	/** The data procollo. */
	@Temporal(TemporalType.DATE)
    @Column(name="data_protocollo")
    private Date dataProtocollo;
	
	@Column(nullable = false)
	private Boolean pregresso;

	/** The stato dichiarazione. */
	//bi-directional many-to-one association to TsddrDStatoDichiarazione
    @ManyToOne
    @JoinColumn(name="id_stato_dichiarazione", nullable=false)
    private TsddrDStatoDichiarazione statoDichiarazione;
    
    // bi-directional many-to-one association to TsddrRPrevConsLinea
    @OneToMany(mappedBy = "prevCons", cascade = CascadeType.PERSIST)
    private List<TsddrRPrevConsLinea> prevConsLinee;
    
    //bi-directional many-to-one association to TsddrTImpianti
    @ManyToOne
    @JoinColumn(name="id_impianto", nullable=false)
    private TsddrTImpianto impianto;
    
    //bi-directional many-to-one association to TsddrTIndirizzi
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_ind_dep_provv_pc")
    private TsddrTIndirizzo indirizzo;
    
	/** The num protocollo doc r-mr. */
	@Column(name="num_prot_doc", length=50)
    private String numProtDoc;

	/**
	 * Instantiates a new tsddr T prev cons.
	 */
	public TsddrTPrevCons() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id prev cons.
	 *
	 * @return the id prev cons
	 */
	public Long getIdPrevCons() {
		return this.idPrevCons;
	}

	/**
	 * Sets the id prev cons.
	 *
	 * @param idPrevCons the new id prev cons
	 */
	public void setIdPrevCons(Long idPrevCons) {
		this.idPrevCons = idPrevCons;
	}

	/**
	 * Gets the anno tributo.
	 *
	 * @return the anno tributo
	 */
	public Long getAnnoTributo() {
		return this.annoTributo;
	}

	/**
	 * Sets the anno tributo.
	 *
	 * @param annoTributo the new anno tributo
	 */
	public void setAnnoTributo(Long annoTributo) {
		this.annoTributo = annoTributo;
	}

	/**
	 * Gets the data doc.
	 *
	 * @return the data doc
	 */
	public Date getDataDoc() {
		return this.dataDoc;
	}

	/**
	 * Sets the data doc.
	 *
	 * @param dataDoc the new data doc
	 */
	public void setDataDoc(Date dataDoc) {
		this.dataDoc = dataDoc;
	}

	/**
	 * Gets the modalita.
	 *
	 * @return the modalita
	 */
	public String getModalita() {
		return this.modalita;
	}

	/**
	 * Sets the modalita.
	 *
	 * @param modalita the new modalita
	 */
	public void setModalita(String modalita) {
		this.modalita = modalita;
	}

	/**
	 * Gets the perc recupero.
	 *
	 * @return the perc recupero
	 */
	public BigDecimal getPercRecupero() {
		return this.percRecupero;
	}

	/**
	 * Sets the perc recupero.
	 *
	 * @param percRecupero the new perc recupero
	 */
	public void setPercRecupero(BigDecimal percRecupero) {
		this.percRecupero = percRecupero;
	}

	/**
	 * Gets the perc scarto.
	 *
	 * @return the perc scarto
	 */
	public BigDecimal getPercScarto() {
		return this.percScarto;
	}

	/**
	 * Sets the perc scarto.
	 *
	 * @param percScarto the new perc scarto
	 */
	public void setPercScarto(BigDecimal percScarto) {
		this.percScarto = percScarto;
	}

	/**
	 * Gets the tipo doc.
	 *
	 * @return the tipo doc
	 */
	public TsddrDTipoDoc getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * Sets the tipo doc.
	 *
	 * @param tipoDoc the new tipo doc
	 */
	public void setTipoDoc(TsddrDTipoDoc tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

    /**
     * Gets the data invio doc.
     *
     * @return the data invio doc
     */
    public Date getDataInvioDoc() {
        return dataInvioDoc;
    }

    /**
     * Sets the data invio doc.
     *
     * @param dataInvioDoc the new data invio doc
     */
    public void setDataInvioDoc(Date dataInvioDoc) {
        this.dataInvioDoc = dataInvioDoc;
    }

    /**
     * Gets the id prev cons R mr.
     *
     * @return the id prev cons R mr
     */
    public Long getIdPrevConsRMr() {
        return idPrevConsRMr;
    }

    /**
     * Sets the id prev cons R mr.
     *
     * @param idPrevConsRMr the new id prev cons R mr
     */
    public void setIdPrevConsRMr(Long idPrevConsRMr) {
        this.idPrevConsRMr = idPrevConsRMr;
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
     * Gets the prev cons linee.
     *
     * @return the prev cons linee
     */
    public List<TsddrRPrevConsLinea> getPrevConsLinee() {
        return prevConsLinee;
    }

    /**
     * Sets the prev cons linee.
     *
     * @param prevConsLinee the new prev cons linee
     */
    public void setPrevConsLinee(List<TsddrRPrevConsLinea> prevConsLinee) {
        this.prevConsLinee = prevConsLinee;
    }
    
    /**
     * Adds the R prev cons linea.
     *
     * @param prevConsLinea the prev cons linea
     * @return the tsddr R prev cons linea
     */
    public TsddrRPrevConsLinea addRPrevConsLinea(TsddrRPrevConsLinea prevConsLinea) {
        getPrevConsLinee().add(prevConsLinea);
        prevConsLinea.setPrevCons(this);

        return prevConsLinea;
    }

    /**
     * Removes the R impianti linee.
     *
     * @param prevConsLinea the prev cons linea
     * @return the tsddr R prev cons linea
     */
    public TsddrRPrevConsLinea removeRImpiantiLinee(TsddrRPrevConsLinea prevConsLinea) {
        getPrevConsLinee().remove(prevConsLinea);
        prevConsLinea.setPrevCons(null);

        return prevConsLinea;
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

	public String getNumProtDoc() {
		return numProtDoc;
	}

	public void setNumProtDoc(String numProtDoc) {
		this.numProtDoc = numProtDoc;
	}
    
	/**
	 * Gets the data pregresso.
	 *
	 * @return the data pregresso
	 */
	public Boolean getPregresso() {
		return pregresso;
	}

	/**
	 * Sets the num pregresso.
	 *
	 * @param pregresso the new pregresso
	 */
	public void setPregresso(Boolean pregresso) {
		this.pregresso = pregresso;
	}
}