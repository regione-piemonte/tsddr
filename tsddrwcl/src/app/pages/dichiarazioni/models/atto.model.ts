
export interface Atto {
    dataProvvedimento?: string;
    numProvvedimento?: string;
    dataInizioAutorizzazione?: string;
    dataFineAutorizzazione?: string;
    idAtto?: number;
    tipoProvvedimento?: TipoProvvedimento
}

export interface TipoProvvedimento{
    idTipoProvvedimento?: number
    descTipoProvvedimento?: string
}

export interface AttoUpdateResponse {
    message: {
      codMsg: string;
      titoloMsg: string;
      testoMsg: string;
      descTipoMsg: string;
    };
    content: Atto
}

export interface AttoDeleteResponse {
    message: {
        codMsg: string;
        titoloMsg: string;
        testoMsg: string;
        descTipoMsg: string;
    };
    content: Atto
}