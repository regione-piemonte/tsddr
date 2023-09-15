/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.math.BigDecimal;
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
 * The persistent class for the tsddr_t_rifiuti_tariffe database table.
 * 
 */
@Entity
@Table(name="tsddr_t_rifiuti_tariffe")
@NamedQuery(name="TsddrTRifiutoTariffa.findAll", query="SELECT t FROM TsddrTRifiutoTariffa t")
public class TsddrTRifiutoTariffa extends AbstractValidableEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rifiuto_tariffa", unique=true, nullable=false)
	private Long idRifiutoTariffa;

	@Column(nullable=false, length=500)
	private String descrizione;

	@Column(name="flag_riduzione", nullable=false)
	private Boolean flagRiduzione;

	@Column(name="id_tipo_rifiuto", nullable=false, length=10)
	private String idTipoRifiuto;

	@Column(name="id_tipologia_2", nullable=false, length=10)
	private String idTipologia2;

	@Column(name="id_tipologia_3", length=10)
	private String idTipologia3;

	@Column(nullable=false, precision=10, scale=8)
	private BigDecimal importo;

	//bi-directional many-to-one association to TsddrTConferimento
	@OneToMany(mappedBy="rifiutoTariffa")
	private List<TsddrTConferimento> conferimenti;

	public TsddrTRifiutoTariffa() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}

	public Long getIdRifiutoTariffa() {
		return this.idRifiutoTariffa;
	}

	public void setIdRifiutoTariffa(Long idRifiutoTariffa) {
		this.idRifiutoTariffa = idRifiutoTariffa;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Boolean getFlagRiduzione() {
		return this.flagRiduzione;
	}

	public void setFlagRiduzione(Boolean flagRiduzione) {
		this.flagRiduzione = flagRiduzione;
	}

	public String getIdTipoRifiuto() {
		return this.idTipoRifiuto;
	}

	public void setIdTipoRifiuto(String idTipoRifiuto) {
		this.idTipoRifiuto = idTipoRifiuto;
	}

	public String getIdTipologia2() {
		return this.idTipologia2;
	}

	public void setIdTipologia2(String idTipologia2) {
		this.idTipologia2 = idTipologia2;
	}

	public String getIdTipologia3() {
		return this.idTipologia3;
	}

	public void setIdTipologia3(String idTipologia3) {
		this.idTipologia3 = idTipologia3;
	}

	public BigDecimal getImporto() {
		return this.importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public List<TsddrTConferimento> getConferimenti() {
		return conferimenti;
	}

	public void setConferimenti(List<TsddrTConferimento> conferimenti) {
		this.conferimenti = conferimenti;
	}
	
	public TsddrTConferimento addConferimento(TsddrTConferimento conferimento) {
		getConferimenti().add(conferimento);
		conferimento.setRifiutoTariffa(this);

		return conferimento;
	}

	public TsddrTConferimento removeConferimento(TsddrTConferimento conferimento) {
		getConferimenti().remove(conferimento);
		conferimento.setRifiutoTariffa(null);

		return conferimento;
	}
}