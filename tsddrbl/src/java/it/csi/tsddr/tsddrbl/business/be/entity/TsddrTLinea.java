/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_linee database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_linee")
@NamedQuery(name = "TsddrTLinea.findAll", query = "SELECT t FROM TsddrTLinea t")
public class TsddrTLinea extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_linea", unique = true, nullable = false)
	private Long idLinea;

	@Column(name = "desc_linea", nullable = false, length = 300)
	private String descLinea;

	@Column(name = "flag_sotto_linea", nullable = false)
	private Boolean flagSottoLinea;
	
	@Column(name = "cod_linea", length = 5)
    private String codLinea;

	// bi-directional many-to-one association to TsddrRImpiantoLinea
	@OneToMany(mappedBy = "linea")
	private List<TsddrRImpiantoLinea> rImpiantiLinee;

	// bi-directional many-to-one association to TsddrTLineaSottoLineaPerc
	@OneToMany(mappedBy = "linea")
	private List<TsddrTLineaSottoLineaPerc> lineaSottoLineaPercs;

	// bi-directional many-to-one association to TsddrTSottoLinea
	@OneToMany(mappedBy = "linea")
	private List<TsddrTSottoLinea> sottoLinee;

	// bi-directional many-to-many association to TsddrTImpianto
	@ManyToMany(mappedBy = "linee")
	private List<TsddrTImpianto> impianti;

	/**
	 * Instantiates a new tsddr T linea.
	 */
	public TsddrTLinea() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id linea.
	 *
	 * @return the id linea
	 */
	public Long getIdLinea() {
		return this.idLinea;
	}

	/**
	 * Sets the id linea.
	 *
	 * @param idLinea the new id linea
	 */
	public void setIdLinea(Long idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * Gets the desc linea.
	 *
	 * @return the desc linea
	 */
	public String getDescLinea() {
		return this.descLinea;
	}

	/**
	 * Sets the desc linea.
	 *
	 * @param descLinea the new desc linea
	 */
	public void setDescLinea(String descLinea) {
		this.descLinea = descLinea;
	}

	
	/**
	 * Gets the cod linea.
	 *
	 * @return the cod linea
	 */
	public String getCodLinea() {
        return codLinea;
    }

    /**
     * Sets the cod linea.
     *
     * @param codLinea the new cod linea
     */
    public void setCodLinea(String codLinea) {
        this.codLinea = codLinea;
    }

    /**
	 * Gets the flag sotto linea.
	 *
	 * @return the flag sotto linea
	 */
	public Boolean getFlagSottoLinea() {
		return this.flagSottoLinea;
	}

	/**
	 * Sets the flag sotto linea.
	 *
	 * @param flagSottoLinea the new flag sotto linea
	 */
	public void setFlagSottoLinea(Boolean flagSottoLinea) {
		this.flagSottoLinea = flagSottoLinea;
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
		rImpiantiLinee.setLinea(this);

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
		rImpiantiLinee.setLinea(null);

		return rImpiantiLinee;
	}

	/**
	 * Gets the linea sotto linea percs.
	 *
	 * @return the linea sotto linea percs
	 */
	public List<TsddrTLineaSottoLineaPerc> getLineaSottoLineaPercs() {
		return this.lineaSottoLineaPercs;
	}

	/**
	 * Sets the linea sotto linea percs.
	 *
	 * @param lineaSottoLineaPercs the new linea sotto linea percs
	 */
	public void setLineaSottoLineaPercs(List<TsddrTLineaSottoLineaPerc> lineaSottoLineaPercs) {
		this.lineaSottoLineaPercs = lineaSottoLineaPercs;
	}

	/**
	 * Adds the linea sotto linea perc.
	 *
	 * @param lineaSottoLineaPerc the linea sotto linea perc
	 * @return the tsddr T linea sotto linea perc
	 */
	public TsddrTLineaSottoLineaPerc addLineaSottoLineaPerc(TsddrTLineaSottoLineaPerc lineaSottoLineaPerc) {
		getLineaSottoLineaPercs().add(lineaSottoLineaPerc);
		lineaSottoLineaPerc.setLinea(this);

		return lineaSottoLineaPerc;
	}

	/**
	 * Removes the linea sotto linea perc.
	 *
	 * @param lineaSottoLineaPerc the linea sotto linea perc
	 * @return the tsddr T linea sotto linea perc
	 */
	public TsddrTLineaSottoLineaPerc removeLineaSottoLineaPerc(TsddrTLineaSottoLineaPerc lineaSottoLineaPerc) {
		getLineaSottoLineaPercs().remove(lineaSottoLineaPerc);
		lineaSottoLineaPerc.setLinea(null);

		return lineaSottoLineaPerc;
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
	 * Adds the sotto linee.
	 *
	 * @param sottoLinee the sotto linee
	 * @return the tsddr T sotto linea
	 */
	public TsddrTSottoLinea addSottoLinee(TsddrTSottoLinea sottoLinee) {
		getSottoLinee().add(sottoLinee);
		sottoLinee.setLinea(this);

		return sottoLinee;
	}

	/**
	 * Removes the sotto linee.
	 *
	 * @param sottoLinee the sotto linee
	 * @return the tsddr T sotto linea
	 */
	public TsddrTSottoLinea removeSottoLinee(TsddrTSottoLinea sottoLinee) {
		getSottoLinee().remove(sottoLinee);
		sottoLinee.setLinea(null);

		return sottoLinee;
	}

	/**
	 * Gets the impianti.
	 *
	 * @return the impianti
	 */
	public List<TsddrTImpianto> getImpianti() {
		return this.impianti;
	}

	/**
	 * Sets the impianti.
	 *
	 * @param impianti the new impianti
	 */
	public void setImpianti(List<TsddrTImpianto> impianti) {
		this.impianti = impianti;
	}

}