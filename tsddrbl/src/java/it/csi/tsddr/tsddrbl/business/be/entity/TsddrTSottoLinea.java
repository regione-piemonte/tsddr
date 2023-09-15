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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_sotto_linee database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_sotto_linee")
@NamedQuery(name = "TsddrTSottoLinea.findAll", query = "SELECT t FROM TsddrTSottoLinea t")
public class TsddrTSottoLinea extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sotto_linea", unique = true, nullable = false)
	private Long idSottoLinea;

	@Column(name = "desc_sotto_linea", nullable = false, length = 300)
	private String descSottoLinea;
	
	@Column(name = "cod_sotto_linea", length = 5)
    private String codSottoLinea;

	// bi-directional many-to-one association to TsddrRImpiantoLinea
	@OneToMany(mappedBy = "sottoLinea")
	private List<TsddrRImpiantoLinea> rImpiantiLinee;

	// bi-directional many-to-one association to TsddrTLineaSottoLineaPerc
	@OneToMany(mappedBy = "sottoLinee")
	private List<TsddrTLineaSottoLineaPerc> lineaSottoLineaPercs;

	// bi-directional many-to-one association to TsddrTLinea
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_linea", nullable = false)
	private TsddrTLinea linea;

	// bi-directional many-to-many association to TsddrTImpianto
	@ManyToMany(mappedBy = "sottoLinee")
	private List<TsddrTImpianto> impianti;

	/**
	 * Instantiates a new tsddr T sotto linea.
	 */
	public TsddrTSottoLinea() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id sotto linea.
	 *
	 * @return the id sotto linea
	 */
	public Long getIdSottoLinea() {
		return this.idSottoLinea;
	}

	/**
	 * Sets the id sotto linea.
	 *
	 * @param idSottoLinea the new id sotto linea
	 */
	public void setIdSottoLinea(Long idSottoLinea) {
		this.idSottoLinea = idSottoLinea;
	}

	/**
	 * Gets the desc sotto linea.
	 *
	 * @return the desc sotto linea
	 */
	public String getDescSottoLinea() {
		return this.descSottoLinea;
	}

	/**
	 * Sets the desc sotto linea.
	 *
	 * @param descSottoLinea the new desc sotto linea
	 */
	public void setDescSottoLinea(String descSottoLinea) {
		this.descSottoLinea = descSottoLinea;
	}
	
    /**
     * Gets the cod sotto linea.
     *
     * @return the cod sotto linea
     */
    public String getCodSottoLinea() {
        return codSottoLinea;
    }

    /**
     * Sets the cod sotto linea.
     *
     * @param codSottoLinea the new cod sotto linea
     */
    public void setCodSottoLinea(String codSottoLinea) {
        this.codSottoLinea = codSottoLinea;
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
		rImpiantiLinee.setSottoLinea(this);

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
		rImpiantiLinee.setSottoLinea(null);

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
		lineaSottoLineaPerc.setSottoLinee(this);

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
		lineaSottoLineaPerc.setSottoLinee(null);

		return lineaSottoLineaPerc;
	}

	/**
	 * Gets the linea.
	 *
	 * @return the linea
	 */
	public TsddrTLinea getLinea() {
		return this.linea;
	}

	/**
	 * Sets the linea.
	 *
	 * @param linea the new linea
	 */
	public void setLinea(TsddrTLinea linea) {
		this.linea = linea;
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