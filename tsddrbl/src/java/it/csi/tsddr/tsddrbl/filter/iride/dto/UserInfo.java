/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.filter.iride.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo implements Serializable {
	// verra' utilizzata la seguente strategia serializzazione degli attributi:
	// [explicit-as-modeled]

	private static final long serialVersionUID = -1333937672787680834L;
	
	private String nome;
	private String cognome;
	private String codFisc;
	private String ente;
	private String ruolo;
	@JsonProperty("cod_ruolo")
	private String codRuolo;
	private Integer livAuth;
	private String community;
	private String ipAddress;
	private Long idDatiSogg;
	private Long idUtente;

	/**
	 * nome dell&#39;utente
	 **/
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * cognome dell&#39;utente
	 **/
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * codice fiscale dell&#39;utente
	 **/
	public String getCodFisc() {
		return codFisc;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}

	/**
	 * nome dell&#39;ente
	 **/
	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	/**
	 * nome del ruolo dell&#39;utente
	 **/
	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * codice del ruolo dell&#39;utente
	 **/
	public String getCodRuolo() {
		return codRuolo;
	}

	public void setCodRuolo(String codRuolo) {
		this.codRuolo = codRuolo;
	}

	/**
	 * livello di autenticazione utilizzato per accedere
	 **/
	public Integer getLivAuth() {
		return livAuth;
	}

	public void setLivAuth(Integer livAuth) {
		this.livAuth = livAuth;
	}

	/**
	 * community di utenti utilizzata per accedere
	 **/
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public Long getIdDatiSogg() {
		return idDatiSogg;
	}

	public void setIdDatiSogg(Long idDatiSogg) {
		this.idDatiSogg = idDatiSogg;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserInfo userInfo = (UserInfo) o;
		return Objects.equals(nome, userInfo.nome) && Objects.equals(cognome, userInfo.cognome)
				&& Objects.equals(codFisc, userInfo.codFisc) && Objects.equals(ente, userInfo.ente)
				&& Objects.equals(ruolo, userInfo.ruolo) && Objects.equals(codRuolo, userInfo.codRuolo)
				&& Objects.equals(livAuth, userInfo.livAuth) && Objects.equals(community, userInfo.community)
				&& Objects.equals(ipAddress, userInfo.ipAddress) && Objects.equals(idDatiSogg, userInfo.idDatiSogg)
				&& Objects.equals(idUtente, userInfo.idUtente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, cognome, codFisc, ente, ruolo, codRuolo, livAuth, community, ipAddress);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UserInfo {\n");

		sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
		sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
		sb.append("    codFisc: ").append(toIndentedString(codFisc)).append("\n");
		sb.append("    ente: ").append(toIndentedString(ente)).append("\n");
		sb.append("    ruolo: ").append(toIndentedString(ruolo)).append("\n");
		sb.append("    codRuolo: ").append(toIndentedString(codRuolo)).append("\n");
		sb.append("    livAuth: ").append(toIndentedString(livAuth)).append("\n");
		sb.append("    community: ").append(toIndentedString(community)).append("\n");
		sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
		sb.append("    idDatiSogg: ").append(toIndentedString(idDatiSogg)).append("\n");
		sb.append("    idUtente: ").append(toIndentedString(idUtente)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n\t");
	}
}
