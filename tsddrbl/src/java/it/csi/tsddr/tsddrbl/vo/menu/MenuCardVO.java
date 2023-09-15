/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.menu;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class MenuCardVO extends AbstractVO {
    
    private static final long serialVersionUID = 1L;

	private Long idMenu;
	private String descrizioneVoceMenu;
	private Long livello;
	private Long idPadre;
	private Long idFunzione;
	private String descFunzione;
	private String descCard;

	/**
	 * Instantiates a new menu card VO.
	 */
	public MenuCardVO() {
	}

	/**
	 * Instantiates a new menu card VO.
	 *
	 * @param idMenu the id menu
	 * @param descrizioneVoceMenu the descrizione voce menu
	 * @param livello the livello
	 * @param idPadre the id padre
	 * @param idFunzione the id funzione
	 * @param descFunzione the desc funzione
	 * @param descCard the desc card
	 */
	public MenuCardVO(Long idMenu, String descrizioneVoceMenu, Long livello, Long idPadre, Long idFunzione,	String descFunzione, String descCard) {
		this.idMenu = idMenu;
		this.descrizioneVoceMenu = descrizioneVoceMenu;
		this.livello = livello;
		this.idPadre = idPadre;
		this.idFunzione = idFunzione;
		this.descFunzione = descFunzione;
		this.descCard = descCard;
	}

	/**
	 * Gets the id menu.
	 *
	 * @return the id menu
	 */
	public Long getIdMenu() {
		return idMenu;
	}

	/**
	 * Sets the id menu.
	 *
	 * @param idMenu the new id menu
	 */
	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	/**
	 * Gets the descrizione voce menu.
	 *
	 * @return the descrizione voce menu
	 */
	public String getDescrizioneVoceMenu() {
		return descrizioneVoceMenu;
	}

	/**
	 * Sets the descrizione voce menu.
	 *
	 * @param descrizioneVoceMenu the new descrizione voce menu
	 */
	public void setDescrizioneVoceMenu(String descrizioneVoceMenu) {
		this.descrizioneVoceMenu = descrizioneVoceMenu;
	}

	/**
	 * Gets the livello.
	 *
	 * @return the livello
	 */
	public Long getLivello() {
		return livello;
	}

	/**
	 * Sets the livello.
	 *
	 * @param livello the new livello
	 */
	public void setLivello(Long livello) {
		this.livello = livello;
	}

	/**
	 * Gets the id padre.
	 *
	 * @return the id padre
	 */
	public Long getIdPadre() {
		return idPadre;
	}

	/**
	 * Sets the id padre.
	 *
	 * @param idPadre the new id padre
	 */
	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	/**
	 * Gets the id funzione.
	 *
	 * @return the id funzione
	 */
	public Long getIdFunzione() {
		return idFunzione;
	}

	/**
	 * Sets the id funzione.
	 *
	 * @param idFunzione the new id funzione
	 */
	public void setIdFunzione(Long idFunzione) {
		this.idFunzione = idFunzione;
	}

	/**
	 * Gets the desc funzione.
	 *
	 * @return the desc funzione
	 */
	public String getDescFunzione() {
		return descFunzione;
	}

	/**
	 * Sets the desc funzione.
	 *
	 * @param descFunzione the new desc funzione
	 */
	public void setDescFunzione(String descFunzione) {
		this.descFunzione = descFunzione;
	}

	/**
	 * Gets the desc card.
	 *
	 * @return the desc card
	 */
	public String getDescCard() {
		return descCard;
	}

	/**
	 * Sets the desc card.
	 *
	 * @param descCard the new desc card
	 */
	public void setDescCard(String descCard) {
		this.descCard = descCard;
	}

}
