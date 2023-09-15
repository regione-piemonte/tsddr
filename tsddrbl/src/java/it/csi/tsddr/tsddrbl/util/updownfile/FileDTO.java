/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.updownfile;

import java.util.Arrays;
import java.util.Objects;

/**
 * The type File dto.
 */
public class FileDTO {

    private String fileName;

    private byte[] body;

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get body byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FileDTO fileDTO = (FileDTO) o;
        return Objects.equals(fileName, fileDTO.fileName) && Arrays.equals(body, fileDTO.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fileName);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileDTO {");
        sb.append("         fileName:'").append(fileName).append("'");
        sb.append(",         body:").append(Arrays.toString(body));
        sb.append("}");
        return sb.toString();
    }
}