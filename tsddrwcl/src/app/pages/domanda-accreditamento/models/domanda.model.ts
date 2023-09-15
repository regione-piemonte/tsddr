import { Profilo } from "@app/pages/profili/models/profili.model";
import { DatiSogg } from "./dati-sogg.model";
import { Gestore } from "./gestore.model";
import { StatoDomanda } from "./stato-domanda.model";

export interface Domanda {
  idDomanda?: number;
  notaUtente?: string;
  notaLavorazione?: string;
  dataRichiesta?: string;
  dataRisposta?: string;
  gestore:Gestore;
  richiedente:DatiSogg;
  stato:StatoDomanda;
  profilo:Profilo
}

export interface DomandaUpdateResponse {
  message: {
    codMsg: string;
    titoloMsg: string;
    testoMsg: string;
    descTipoMsg: string;
  };
  content: Domanda;
}

export interface DomandaDeleteResponse {
  message: {
    codMsg: string;
    titoloMsg: string;
    testoMsg: string;
    descTipoMsg: string;
  };
  content: Domanda;
}
