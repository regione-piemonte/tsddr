import { Atto } from "./atto.model";
import { Gestore } from "./gestore.model";
import { LineaExtended } from "./linea.model";

export interface Impianto {
    dataFineValidita?: string;
    dataInizioValidita?: string;
    deletable?: boolean;
    denominazione?: string;
    gestore?: Gestore;
    idImpianto?:number;
    indirizzo?: Indirizzo;
    statoImpianto?: StatoImpianto;
    tipoImpianto?: TipoImpianto;
    readOnly?: boolean;
    linee?: LineaExtended[];
    atti?: Atto[]
}

export interface StatoImpianto {
    descStatoImpianto?: string;
    idStatoImpianto?: number;
}
export interface TipoImpianto {
    descTipoImpianto?: string;
    idTipoImpianto?: number;
}

export interface Indirizzo {
    cap?: string;
    idIndirizzo?: number;
    indirizzo?: string;
    comune?: Comune;
    nazione?: Nazione;
    sedime?: Sedime;
    versione?: string;
    hasBeenUpdated?: boolean;
}
export interface IndirizzoExtended {
    idIndirizzo?: number;
    indirizzo?: string;
    versione?: string;
    comune?: ComuneExtended;
    nazione?: Nazione;
    sedime?: Sedime;
    tipoIndirizzo?: TipoIndirizzo;
}
export interface Comune{
    idComune?: number;
    cap?: string;
    provincia?: Provincia;
}
export interface ComuneExtended extends Comune{
    codCatasto?: string;
    comune?: string;
    idComuneIstat?: number;
}
export interface Nazione{
    idNazione?: number;
    idIstatNazione?: string;
}
export interface Provincia{
    idProvincia?: number;
}
export interface Sedime{
    idSedime?: number;
    descSedime?: string;
}
export interface TipoIndirizzo {
    idTipoIndirizzo: number;
    descTipoIndirizzo: string;
}

export interface ImpiantoUpdateResponse {
    message: {
      codMsg: string;
      titoloMsg: string;
      testoMsg: string;
      descTipoMsg: string;
    };
    content: Impianto
}


export interface ImpiantoDeleteResponse {
    message: {
        codMsg: string;
        titoloMsg: string;
        testoMsg: string;
        descTipoMsg: string;
    };
    content: Impianto
}
