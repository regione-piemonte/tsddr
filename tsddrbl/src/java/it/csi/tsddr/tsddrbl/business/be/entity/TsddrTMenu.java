/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tsddr_t_menu database table.
 * 
 */
@Entity
@Table(name = "tsddr_t_menu")
@NamedQuery(name = "TsddrTMenu.findAll", query = "SELECT t FROM TsddrTMenu t")
public class TsddrTMenu extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_menu", unique = true, nullable = false)
	private Long idMenu;

	@Column(name = "desc_voce_menu", nullable = false, length = 100)
	private String descVoceMenu;

	@Column(name = "descrizione_card", length = 100)
	private String descrizioneCard;

	@Column(nullable = false)
	private Long livello;

	// bi-directional many-to-one association to TsddrDFunzione
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funzione", nullable = false)
	private TsddrDFunzione funzione;

	@Column(name = "id_padre", nullable = false, columnDefinition = "bigint DEFAULT 0")
	private Long idPadre;

	/**
	 * Instantiates a new tsddr T menu.
	 */
	public TsddrTMenu() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	/**
	 * Gets the id menu.
	 *
	 * @return the id menu
	 */
	public Long getIdMenu() {
		return this.idMenu;
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
	 * Gets the desc voce menu.
	 *
	 * @return the desc voce menu
	 */
	public String getDescVoceMenu() {
		return this.descVoceMenu;
	}

	/**
	 * Sets the desc voce menu.
	 *
	 * @param descVoceMenu the new desc voce menu
	 */
	public void setDescVoceMenu(String descVoceMenu) {
		this.descVoceMenu = descVoceMenu;
	}

	/**
	 * Gets the descrizione card.
	 *
	 * @return the descrizione card
	 */
	public String getDescrizioneCard() {
		return this.descrizioneCard;
	}

	/**
	 * Sets the descrizione card.
	 *
	 * @param descrizioneCard the new descrizione card
	 */
	public void setDescrizioneCard(String descrizioneCard) {
		this.descrizioneCard = descrizioneCard;
	}

	/**
	 * Gets the livello.
	 *
	 * @return the livello
	 */
	public Long getLivello() {
		return this.livello;
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
	 * Gets the funzione.
	 *
	 * @return the funzione
	 */
	public TsddrDFunzione getFunzione() {
		return this.funzione;
	}

	/**
	 * Sets the funzione.
	 *
	 * @param funzione the new funzione
	 */
	public void setFunzione(TsddrDFunzione funzione) {
		this.funzione = funzione;
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

}
