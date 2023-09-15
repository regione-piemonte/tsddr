/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.gestore;

import java.util.Date;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;
import it.csi.tsddr.tsddrbl.vo.indirizzo.IndirizzoVO;

public class GestoreVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private Long idGestore;
	private String codFiscPartiva;
	private String email;
	private String idIscrizioneAlbo;
	private String pec;
	private String ragSociale;
	private String telefono;
	private IndirizzoVO sedeLegale;
	private LegaleRappresentanteVO legaleRappresentante;
	private NaturaGiuridicaVO naturaGiuridica;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	/**
	 * @return the idGestore
	 */
	public Long getIdGestore() {
		return idGestore;
	}

	/**
	 * @param idGestore the idGestore to set
	 */
	public void setIdGestore(Long idGestore) {
		this.idGestore = idGestore;
	}

	/**
	 * @return the codFiscPartiva
	 */
	public String getCodFiscPartiva() {
		return codFiscPartiva;
	}

	/**
	 * @param codFiscPartiva the codFiscPartiva to set
	 */
	public void setCodFiscPartiva(String codFiscPartiva) {
		this.codFiscPartiva = codFiscPartiva;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the idIscrizioneAlbo
	 */
	public String getIdIscrizioneAlbo() {
		return idIscrizioneAlbo;
	}

	/**
	 * @param idIscrizioneAlbo the idIscrizioneAlbo to set
	 */
	public void setIdIscrizioneAlbo(String idIscrizioneAlbo) {
		this.idIscrizioneAlbo = idIscrizioneAlbo;
	}

	/**
	 * @return the pec
	 */
	public String getPec() {
		return pec;
	}

	/**
	 * @param pec the pec to set
	 */
	public void setPec(String pec) {
		this.pec = pec;
	}

	/**
	 * @return the ragSociale
	 */
	public String getRagSociale() {
		return ragSociale;
	}

	/**
	 * @param ragSociale the ragSociale to set
	 */
	public void setRagSociale(String ragSociale) {
		this.ragSociale = ragSociale;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the sedeLegale
	 */
	public IndirizzoVO getSedeLegale() {
		return sedeLegale;
	}

	/**
	 * @param sedeLegale the sedeLegale to set
	 */
	public void setSedeLegale(IndirizzoVO sedeLegale) {
		this.sedeLegale = sedeLegale;
	}

	/**
	 * @return the legaleRappresentante
	 */
	public LegaleRappresentanteVO getLegaleRappresentante() {
		return legaleRappresentante;
	}

	/**
	 * @param legaleRappresentante the legaleRappresentante to set
	 */
	public void setLegaleRappresentante(LegaleRappresentanteVO legaleRappresentante) {
		this.legaleRappresentante = legaleRappresentante;
	}

	/**
	 * @return the naturaGiuridica
	 */
	public NaturaGiuridicaVO getNaturaGiuridica() {
		return naturaGiuridica;
	}

	/**
	 * @param naturaGiuridica the naturaGiuridica to set
	 */
	public void setNaturaGiuridica(NaturaGiuridicaVO naturaGiuridica) {
		this.naturaGiuridica = naturaGiuridica;
	}

	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

}
