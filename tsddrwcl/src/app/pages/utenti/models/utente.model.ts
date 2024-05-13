export interface Utente {
  codiceFiscale?: string;
  cognome?: string;
  nome?: string;
  telefono?: string;
  mail?: string;
  idGestore?: number;
  idProfilo?: number;
  dataInizioValidita?: string;
  dataFineValidita?: string;
  idUtente?: number;
  pec?: string;
}

export interface UtenteUpdateResponse {
  message: {
    codMsg: string;
    titoloMsg: string;
    testoMsg: string;
    descTipoMsg: string;
  };
  content: {
    idUtente: number;
    codiceFiscale: string;
    cognome: string;
    nome: string;
    dataInizioValidita: string;
    dataFineValidita?: string;
  };
}

export interface UtenteDeleteResponse {
  message: {
    codMsg: string;
    titoloMsg: string;
    testoMsg: string;
    descTipoMsg: string;
  };
  content: {
    idUtente: number;
    codiceFiscale: string;
    cognome: string;
    nome: string;
    dataInizioValidita: string;
  };
}
