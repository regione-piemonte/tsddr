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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_d_stati_dichiarazioni database table.
 * 
 */
@Entity
@Table(name="tsddr_d_stati_dichiarazioni")
@NamedQuery(name="TsddrDStatoDichiarazione.findAll", query="SELECT t FROM TsddrDStatoDichiarazione t")
public class TsddrDStatoDichiarazione extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_stato_dichiarazione", unique=true, nullable=false)
	private Long idStatoDichiarazione;

	@Column(name="descr_stato_dichiarazione", nullable=false, length=500)
	private String descrStatoDichiarazione;

	//bi-directional many-to-one association to TsddrTDichAnnuale
	@OneToMany(mappedBy="statoDichiarazione")
	private List<TsddrTDichAnnuale> dichAnnuali;
	
	//bi-directional many-to-one association to TsddrTDichAnnuale
    @OneToMany(mappedBy="statoDichiarazione")
    private List<TsddrTPrevCons> prevCons;

	/**
	 * Instantiates a new tsddr D stato dichiarazione.
	 */
	public TsddrDStatoDichiarazione() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id stato dichiarazione.
	 *
	 * @return the id stato dichiarazione
	 */
	public Long getIdStatoDichiarazione() {
		return this.idStatoDichiarazione;
	}

	/**
	 * Sets the id stato dichiarazione.
	 *
	 * @param idStatoDichiarazione the new id stato dichiarazione
	 */
	public void setIdStatoDichiarazione(Long idStatoDichiarazione) {
		this.idStatoDichiarazione = idStatoDichiarazione;
	}

	/**
	 * Gets the descr stato dichiarazione.
	 *
	 * @return the descr stato dichiarazione
	 */
	public String getDescrStatoDichiarazione() {
		return this.descrStatoDichiarazione;
	}

	/**
	 * Sets the descr stato dichiarazione.
	 *
	 * @param descrStatoDichiarazione the new descr stato dichiarazione
	 */
	public void setDescrStatoDichiarazione(String descrStatoDichiarazione) {
		this.descrStatoDichiarazione = descrStatoDichiarazione;
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
	 * Adds the dich annuale.
	 *
	 * @param dichAnnuale the dich annuale
	 * @return the tsddr T dich annuale
	 */
	public TsddrTDichAnnuale addDichAnnuale(TsddrTDichAnnuale dichAnnuale) {
		getDichAnnuali().add(dichAnnuale);
		dichAnnuale.setStatoDichiarazione(this);

		return dichAnnuale;
	}

	/**
	 * Removes the dich annuale.
	 *
	 * @param dichAnnuale the dich annuale
	 * @return the tsddr T dich annuale
	 */
	public TsddrTDichAnnuale removeDichAnnuale(TsddrTDichAnnuale dichAnnuale) {
		getDichAnnuali().remove(dichAnnuale);
		dichAnnuale.setStatoDichiarazione(null);

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
     * Adds the prev cons.
     *
     * @param prevCons the prev cons
     * @return the tsddr T prev cons
     */
    public TsddrTPrevCons addPrevCons(TsddrTPrevCons prevCons) {
        getPrevCons().add(prevCons);
        prevCons.setStatoDichiarazione(this);

        return prevCons;
    }

    /**
     * Removes the prev cons.
     *
     * @param prevCons the prev Cons
     * @return the tsddr T prev cons
     */
    public TsddrTPrevCons removePrevCons(TsddrTPrevCons prevCons) {
        getPrevCons().remove(prevCons);
        prevCons.setStatoDichiarazione(null);

        return prevCons;
    }
}