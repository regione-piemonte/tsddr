/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo;

import java.util.Arrays;

public class DocumentoProtocollatoVO extends AbstractVO {

	private static final long serialVersionUID = -7714298531166937914L;

	private byte[] file;
	private String filename;
	private String classificazione;
	private String classificazioneId;
	private String numProtocollo;
	private String dataOraProtocollo;
	private String idActa;
	private String objectIdDocumento;
	private String registrazioneId;
	private String folderId;
	private String documentoUUID;
	
	
	/**
	 * Instantiates a new documento protocollato VO.
	 *
	 * @param fileName the file name
	 * @param file the file
	 * @param numProtocollo the num protocollo
	 */
	public DocumentoProtocollatoVO(String fileName, byte[] file, String numProtocollo) {
		this.file = file;
		this.filename = fileName;
		this.numProtocollo = numProtocollo;
		
	}
	
	/**
	 * Instantiates a new documento protocollato VO.
	 */
	public DocumentoProtocollatoVO() {}

	/**
	 * Gets the classificazione.
	 *
	 * @return the classificazione
	 */
	public String getClassificazione() {
		return classificazione;
	}

	/**
	 * Sets the classificazione.
	 *
	 * @param classificazione the new classificazione
	 */
	public void setClassificazione(String classificazione) {
		this.classificazione = classificazione;
	}
	
	/**
	 * Gets the data ora protocollo.
	 *
	 * @return the data ora protocollo
	 */
	public String getDataOraProtocollo() {
		return dataOraProtocollo;
	}

	/**
	 * Sets the data ora protocollo.
	 *
	 * @param dataOraProtocollo the new data ora protocollo
	 */
	public void setDataOraProtocollo(String dataOraProtocollo) {
		this.dataOraProtocollo = dataOraProtocollo;
	}
	
	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
	 * Gets the id acta.
	 *
	 * @return the id acta
	 */
	public String getIdActa() {
		return idActa;
	}

	/**
	 * Sets the id acta.
	 *
	 * @param idActa the new id acta
	 */
	public void setIdActa(String idActa) {
		this.idActa = idActa;
	}
	
	/**
	 * Gets the classificazione id.
	 *
	 * @return the classificazione id
	 */
	public String getClassificazioneId() {
		return classificazioneId;
	}

	/**
	 * Sets the classificazione id.
	 *
	 * @param classificazioneId the new classificazione id
	 */
	public void setClassificazioneId(String classificazioneId) {
		this.classificazioneId = classificazioneId;
	}

	/**
	 * Gets the object id documento.
	 *
	 * @return the object id documento
	 */
	public String getObjectIdDocumento() {
		return objectIdDocumento;
	}

	/**
	 * Sets the object id documento.
	 *
	 * @param objectIdDocumento the new object id documento
	 */
	public void setObjectIdDocumento(String objectIdDocumento) {
		this.objectIdDocumento = objectIdDocumento;
	}

	/**
	 * Gets the registrazione id.
	 *
	 * @return the registrazione id
	 */
	public String getRegistrazioneId() {
		return registrazioneId;
	}

	/**
	 * Sets the registrazione id.
	 *
	 * @param registrazioneId the new registrazione id
	 */
	public void setRegistrazioneId(String registrazioneId) {
		this.registrazioneId = registrazioneId;
	}

	/**
	 * Gets the folder id.
	 *
	 * @return the folder id
	 */
	public String getFolderId() {
		return folderId;
	}

	/**
	 * Sets the folder id.
	 *
	 * @param folderId the new folder id
	 */
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	
	/**
	 * Gets the documento UUID.
	 *
	 * @return the documento UUID
	 */
	public String getDocumentoUUID() {
		return documentoUUID;
	}

	/**
	 * Sets the documento UUID.
	 *
	 * @param documentoUUID the new documento UUID
	 */
	public void setDocumentoUUID(String documentoUUID) {
		this.documentoUUID = documentoUUID;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentoProtocollatoVO [file=");
		builder.append(Arrays.toString(file));
		builder.append(", filename=");
		builder.append(filename);
		builder.append(", classificazione=");
		builder.append(classificazione);
		builder.append(", classificazioneId=");
		builder.append(classificazioneId);
		builder.append(", numProtocollo=");
		builder.append(numProtocollo);
		builder.append(", dataOraProtocollo=");
		builder.append(dataOraProtocollo);
		builder.append(", idActa=");
		builder.append(idActa);
		builder.append(", objectIdDocumento=");
		builder.append(objectIdDocumento);
		builder.append(", registrazioneId=");
		builder.append(registrazioneId);
		builder.append(", folderId=");
		builder.append(folderId);
		builder.append(", documentoUUID=");
		builder.append(documentoUUID);
		builder.append("]");
		return builder.toString();
	}





	
	
	
	


	
	
	
	

	


	
	


}
