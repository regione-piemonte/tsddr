export interface ProfiloResponse {
  message: any;
  content: Profilo[];
}

export interface Profilo {
  idProfilo?: number;
  descProfilo?: string;
  dataInizioValidita?: string;
  dataFineValidita?: string;
  deletable?: boolean;
  tipologiaProfilo?: TipologiaProfilo;
}
export interface TipologiaProfilo {
  idTipoProfilo?: number;
  descTipoProfilo?: string;
}

export interface ProfiloUpdateResponse {
  content: Profilo;
  message: {
    campo: string;
    codMsg: string;
    descTipoMsg: string;
    testoMsg: string;
    titoloMsg: string;
  };
}

export interface ProfiloDeleteResponse {
  content: Profilo;
  message: {
    campo: string;
    codMsg: string;
    descTipoMsg: string;
    testoMsg: string;
    titoloMsg: string;
  };
}

export interface VerificaProfiloResponse {
  errors: Error[]
}

export interface Error {
  codMsg:string;
  titoloMsg:string;
  testoMsg:string;
  descTipoMsg:string;
  campo:string;
}

