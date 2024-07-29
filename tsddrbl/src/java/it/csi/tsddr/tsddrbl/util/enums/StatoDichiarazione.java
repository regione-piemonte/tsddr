/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

import java.util.stream.Stream;

public enum StatoDichiarazione {

    BOZZA(1, "Bozza"), INVIATA_PROTOCOLLATA(2, "Inviata (Protocollata)"), ELIMINATA(3, "Eliminata"), PREGRESSO_CONSOLIDATO(4, "Pregresso consolidato");

    private long id;
    private String desc;

    StatoDichiarazione(long id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static StatoDichiarazione byId(int id) {
        return Stream.of(values()).filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}