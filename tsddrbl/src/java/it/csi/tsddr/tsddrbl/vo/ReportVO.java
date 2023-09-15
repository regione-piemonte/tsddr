/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo;

import java.util.Arrays;

public class ReportVO extends AbstractVO {

	private static final long serialVersionUID = -7714298531166937914L;

	private byte[] file;
	private String filename;
	
	
	/**
	 * Instantiates a new documento protocollato VO.
	 *
	 * @param fileName the file name
	 * @param file the file
	 */
	public ReportVO(String fileName, byte[] file) {
		this.file = file;
		this.filename = fileName;
		
	}
	
	/**
	 * Instantiates a new documento protocollato VO.
	 */
	public ReportVO() {}

	
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
		builder.append("]");
		return builder.toString();
	}





	
	
	
	


	
	
	
	

	


	
	


}
