/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.dichiarazione;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.impianto.ImpiantoVO;
import it.csi.tsddr.tsddrbl.vo.statodichiarazione.StatoDichiarazioneVO;

public class DichAnnualeBasicVO {

	private Long idDichAnnuale;
	private Long anno;
	private Date dataDichiarazione;
	private Long versione;
	private StatoDichiarazioneVO statoDichiarazione;
	private ImpiantoVO impianto;
	private String numProtocollo;
	private Date dataProtocollo;

	private Boolean annullable;
	private Boolean printable;

	/**
	 * Gets the id dich annuale.
	 *
	 * @return the id dich annuale
	 */
	public Long getIdDichAnnuale() {
		return idDichAnnuale;
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
		return anno;
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
	 * Gets the data dichiarazione.
	 *
	 * @return the data dichiarazione
	 */
	public Date getDataDichiarazione() {
		return dataDichiarazione;
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
	 * Gets the stato dichiarazione.
	 *
	 * @return the stato dichiarazione
	 */
	public StatoDichiarazioneVO getStatoDichiarazione() {
		return statoDichiarazione;
	}

	/**
	 * Sets the stato dichiarazione.
	 *
	 * @param statoDichiarazione the new stato dichiarazione
	 */
	public void setStatoDichiarazione(StatoDichiarazioneVO statoDichiarazione) {
		this.statoDichiarazione = statoDichiarazione;
	}

	/**
	 * Gets the impianto.
	 *
	 * @return the impianto
	 */
	public ImpiantoVO getImpianto() {
		return impianto;
	}

	/**
	 * Sets the impianto.
	 *
	 * @param impianto the new impianto
	 */
	public void setImpianto(ImpiantoVO impianto) {
		this.impianto = impianto;
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
	 * Gets the annullable.
	 *
	 * @return the annullable
	 */
	public Boolean getAnnullable() {
		return annullable;
	}

	/**
	 * Sets the annullable.
	 *
	 * @param annullable the new annullable
	 */
	public void setAnnullable(Boolean annullable) {
		this.annullable = annullable;
	}

	/**
	 * Gets the printable.
	 *
	 * @return the printable
	 */
	public Boolean getPrintable() {
		return printable;
	}

	/**
	 * Sets the printable.
	 *
	 * @param printable the new printable
	 */
	public void setPrintable(Boolean printable) {
		this.printable = printable;
	}

}