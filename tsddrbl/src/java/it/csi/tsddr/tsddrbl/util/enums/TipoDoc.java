/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

import java.util.stream.Stream;

public enum TipoDoc {
    
    RICHIESTA(1, "Richiesta di ammissione al pagamento in misura ridotta"), DICHIARAZIONE(2, "Dichiarazione di raggiungimento degli obiettivi");

    private long id;
    private String desc;

    TipoDoc(long id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static TipoDoc byId(int id) {
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
