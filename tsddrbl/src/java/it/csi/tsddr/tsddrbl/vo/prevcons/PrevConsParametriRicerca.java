/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.prevcons;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

public class PrevConsParametriRicerca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@QueryParam("idGestore")
	private Long idGestore;
	@QueryParam("idImpianto")
	private Long idImpianto;
	@QueryParam("annoTributoDal")
	private Long annoTributoDal;
	@QueryParam("annoTributoAl")
	private Long annoTributoAl;
	@QueryParam("idTipoDoc")
	@NotNull
    private Long idTipoDoc;
	@QueryParam("idStatoDichiarazione")
    private Long idStatoDichiarazione;
	@QueryParam("annoTributo")
    private Long annoTributo;

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
     * Gets the anno tributo dal.
     *
     * @return the anno tributo dal
     */
    public Long getAnnoTributoDal() {
        return annoTributoDal;
    }

    /**
     * Sets the anno tributo dal.
     *
     * @param annoTributoDal the new anno tributo dal
     */
    public void setAnnoTributoDal(Long annoTributoDal) {
        this.annoTributoDal = annoTributoDal;
    }

    /**
     * Gets the anno tributo al.
     *
     * @return the anno tributo al
     */
    public Long getAnnoTributoAl() {
        return annoTributoAl;
    }

    /**
     * Sets the anno tributo al.
     *
     * @param annoTributoAl the new anno tributo al
     */
    public void setAnnoTributoAl(Long annoTributoAl) {
        this.annoTributoAl = annoTributoAl;
    }

    /**
     * Gets the id tipo doc.
     *
     * @return the id tipo doc
     */
    public Long getIdTipoDoc() {
        return idTipoDoc;
    }

    /**
     * Sets the id tipo doc.
     *
     * @param idTipoDoc the new id tipo doc
     */
    public void setIdTipoDoc(Long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    /**
     * Gets the id stato dichiarazione.
     *
     * @return the id stato dichiarazione
     */
    public Long getIdStatoDichiarazione() {
        return idStatoDichiarazione;
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
     * Gets the anno tributo.
     *
     * @return the anno tributo
     */
    public Long getAnnoTributo() {
        return annoTributo;
    }

    /**
     * Sets the anno tributo.
     *
     * @param annoTributo the new anno tributo
     */
    public void setAnnoTributo(Long annoTributo) {
        this.annoTributo = annoTributo;
    }
    
}
