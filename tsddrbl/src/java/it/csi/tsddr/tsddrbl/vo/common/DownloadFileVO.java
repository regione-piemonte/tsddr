/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.vo.common;

import it.csi.tsddr.tsddrbl.vo.AbstractVO;

public class DownloadFileVO extends AbstractVO {

	private static final long serialVersionUID = 1004497900218242299L;

	private String fileName;
	private byte[] file;
	private String contentType;

	/**
	 * Instantiates a new download file VO.
	 *
	 * @param fileName the file name
	 * @param file the file
	 */
	public DownloadFileVO(String fileName, byte[] file) {
		this.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		this.fileName = fileName;
		this.file = file;
	}

	/**
	 * Instantiates a new download file VO.
	 *
	 * @param fileName the file name
	 * @param file the file
	 * @param contentType the content type
	 */
	public DownloadFileVO(String fileName, byte[] file, String contentType) {
		this(fileName, file);
		this.contentType = contentType;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public byte[] getFile() {
		return this.file;
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
	 * Gets the content type.
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
