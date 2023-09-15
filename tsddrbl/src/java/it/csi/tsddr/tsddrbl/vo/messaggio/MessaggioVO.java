/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.messaggio;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class MessaggioVO extends AbstractVO {
	
	private static final long serialVersionUID = 1L;

	private String codMsg;
	private String titoloMsg;
	private String testoMsg;
	private String descTipoMsg;
	
	private String campo;

	/**
	 * Instantiates a new messaggio VO.
	 */
	public MessaggioVO() {
	}

	/**
	 * Instantiates a new messaggio VO.
	 *
	 * @param codMsg the cod msg
	 * @param titoloMsg the titolo msg
	 * @param testoMsg the testo msg
	 * @param descTipoMsg the desc tipo msg
	 * @param campo the campo
	 */
	public MessaggioVO(String codMsg, String titoloMsg, String testoMsg, String descTipoMsg, String campo) {
		this.codMsg = codMsg;
		this.titoloMsg = titoloMsg;
		this.testoMsg = testoMsg;
		this.descTipoMsg = descTipoMsg;
		this.campo = campo;
	}
	
	/**
	 * For campo.
	 *
	 * @param msg the msg
	 * @param campo the campo
	 * @return the messaggio VO
	 */
	public static MessaggioVO forCampo(MessaggioVO msg, String campo) {
		return new MessaggioVO(msg.getCodMsg(), msg.getTitoloMsg(), msg.getTestoMsg(), msg.getDescTipoMsg(), campo);
	}

	/**
	 * Gets the cod msg.
	 *
	 * @return the cod msg
	 */
	public String getCodMsg() {
		return codMsg;
	}

	/**
	 * Sets the cod msg.
	 *
	 * @param codMsg the new cod msg
	 */
	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}

	/**
	 * Gets the titolo msg.
	 *
	 * @return the titolo msg
	 */
	public String getTitoloMsg() {
		return titoloMsg;
	}

	/**
	 * Sets the titolo msg.
	 *
	 * @param titoloMsg the new titolo msg
	 */
	public void setTitoloMsg(String titoloMsg) {
		this.titoloMsg = titoloMsg;
	}

	/**
	 * Gets the testo msg.
	 *
	 * @return the testo msg
	 */
	public String getTestoMsg() {
		return testoMsg;
	}

	/**
	 * Sets the testo msg.
	 *
	 * @param testoMsg the new testo msg
	 */
	public void setTestoMsg(String testoMsg) {
		this.testoMsg = testoMsg;
	}

	/**
	 * Gets the desc tipo msg.
	 *
	 * @return the desc tipo msg
	 */
	public String getDescTipoMsg() {
		return descTipoMsg;
	}

	/**
	 * Sets the desc tipo msg.
	 *
	 * @param descTipoMsg the new desc tipo msg
	 */
	public void setDescTipoMsg(String descTipoMsg) {
		this.descTipoMsg = descTipoMsg;
	}

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Sets the campo.
	 *
	 * @param campo the new campo
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

}
