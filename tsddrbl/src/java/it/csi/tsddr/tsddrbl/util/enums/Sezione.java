/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.enums;

import java.util.stream.Stream;

/**
 * The Enum Sezione.
 */
public enum Sezione {
	
    DESC_SOMMARIA(1, "4.1 - Descrizione sommaria"), 
    RII(2, "4.2 - Elenco dei rifiuti in ingresso alla linea impiantistica effettivamente sottoposti ad attività di selezione automatica, riciclaggio e compostaggio – r.i.i"),
    MAT(3, "4.3 - Elenco dei materiali in uscita dalla linea impiantistica con indicazione dei quantitativi annui derivanti dai trattamenti sopra indicati – mat."),
    RRU(4, "4.4 - Elenco delle tipologie di rifiuti recuperabili in uscita dalla linea impiantistica derivanti dai trattamenti sopra indicati – r.r.u."),
    RU(5, "4.5 - Elenco dei rifiuti in uscita inviati a smaltimento o recupero, a esclusione dei rifiuti avviati a impianti che effettuano il recupero di materia conclusivo o del percolato – r.u."),
    MR(6, "3 - Richiesta di ammissione al pagamento in misura ridotta");

    private long id;
    private String desc;

    Sezione(long id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static Sezione byId(int id) {
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
