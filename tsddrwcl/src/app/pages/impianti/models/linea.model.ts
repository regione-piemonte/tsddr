
export interface Linea {
    dataFineValidita?: string;
    dataInizioValidita?: string;
    idLineaImpianto?:number;
    idLinea?: number;
}

export interface Sottolinea {
    dataFineValidita?: string;
    dataInizioValidita?: string;
    idLineaImpianto?:number;
    idLineaPadre?: number;
    idLineaFiglia?: number;
    idSottoLinea?: number;
}

export interface LineaExtended extends Linea,Sottolinea{
    isSottolinea?: boolean;
    idFormUpdate?:string;
    descrizione?: string;
    descLinea?: string;
} 

export interface LineaUpdateResponse {
    message: {
      codMsg: string;
      titoloMsg: string;
      testoMsg: string;
      descTipoMsg: string;
    };
    content: Linea
}
 

export interface LineaDeleteResponse {
    message: {
        codMsg: string;
        titoloMsg: string;
        testoMsg: string;
        descTipoMsg: string;
    };
    content: Linea
}