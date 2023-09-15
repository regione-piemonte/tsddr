import { Utente } from "@app/pages/utenti/models/utente.model";

export interface UtentiProfiloResponse {
  message: any;
  content: Utente[];
}

export interface UtentiProfilo {
  idProfilo?: number;
  idUtente?: number;
}

export interface UtentiProfiloDeleteResponse {
  content: UtentiProfilo;
  message: {
    campo: string;
    codMsg: string;
    descTipoMsg: string;
    testoMsg: string;
    titoloMsg: string;
  };
}
