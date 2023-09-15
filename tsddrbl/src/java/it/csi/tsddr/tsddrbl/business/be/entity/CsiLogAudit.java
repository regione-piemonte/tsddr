/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the csi_log_audit database table.
 * 
 */
@Entity
@Table(name = "csi_log_audit")
@NamedQuery(name = "CsiLogAudit.findAll", query = "SELECT c FROM CsiLogAudit c")
public class CsiLogAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_csi_log_audit", unique = true, nullable = false)
	private Long idCsiLogAudit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ora", unique = true, nullable = false)
	private Date dataOra;

	@Column(name = "id_app", nullable = false, length = 100)
	private String idApp;
	
	@Column(name = "utente", nullable = false, length = 40)
	private String utente;
	
	@Column(name = "operazione", nullable = false, length = 100)
	private String operazione;
	
	@Column(name = "ip_address", length = 40)
	private String ipAddress;
	
	@Column(name = "ogg_oper", nullable = false, length = 500)
	private String oggOper;

	@Column(name = "key_oper", length = 500)
	private String keyOper;

	/**
	 * Gets the id csi log audit.
	 *
	 * @return the id csi log audit
	 */
	public Long getIdCsiLogAudit() {
		return idCsiLogAudit;
	}

	/**
	 * Sets the id csi log audit.
	 *
	 * @param idCsiLogAudit the new id csi log audit
	 */
	public void setIdCsiLogAudit(Long idCsiLogAudit) {
		this.idCsiLogAudit = idCsiLogAudit;
	}

	/**
	 * Gets the data ora.
	 *
	 * @return the data ora
	 */
	public Date getDataOra() {
		return dataOra;
	}

	/**
	 * Sets the data ora.
	 *
	 * @param dataOra the new data ora
	 */
	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	/**
	 * Gets the id app.
	 *
	 * @return the id app
	 */
	public String getIdApp() {
		return idApp;
	}

	/**
	 * Sets the id app.
	 *
	 * @param idApp the new id app
	 */
	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	/**
	 * Gets the utente.
	 *
	 * @return the utente
	 */
	public String getUtente() {
		return utente;
	}

	/**
	 * Sets the utente.
	 *
	 * @param utente the new utente
	 */
	public void setUtente(String utente) {
		this.utente = utente;
	}

	/**
	 * Gets the operazione.
	 *
	 * @return the operazione
	 */
	public String getOperazione() {
		return operazione;
	}

	/**
	 * Sets the operazione.
	 *
	 * @param operazione the new operazione
	 */
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress the new ip address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Gets the ogg oper.
	 *
	 * @return the ogg oper
	 */
	public String getOggOper() {
		return oggOper;
	}

	/**
	 * Sets the ogg oper.
	 *
	 * @param oggOper the new ogg oper
	 */
	public void setOggOper(String oggOper) {
		this.oggOper = oggOper;
	}

	/**
	 * Gets the key oper.
	 *
	 * @return the key oper
	 */
	public String getKeyOper() {
		return keyOper;
	}

	/**
	 * Sets the key oper.
	 *
	 * @param keyOper the new key oper
	 */
	public void setKeyOper(String keyOper) {
		this.keyOper = keyOper;
	}

}