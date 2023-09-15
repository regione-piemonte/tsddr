import { Gestore } from '@pages/utenti/models/gestore.model';

export interface DomandaAccreditamento {
  idDomanda?: number;
  gestore?: Gestore;
  richiedente?: {
    idDatiSogg?: number;
    codFiscale?: string;
    cognome?: string;
    email?: string;
    telefono?: string;
    nome?: string;
    pec?: string;
  };
  notaUtente?: string;
  notaLavorazione?: string;
  stato?: {
    id?: number;
    desc?: string;
    step?: number;
  };
  dataRichiesta?: string;
}

export interface DomandaAccreditamentoResponse {
  message: {
    codMsg: string;
    titoloMsg: string;
    testoMsg: string;
    descTipoMsg: string;
  };
  content: DomandaAccreditamento;
}

export interface ListaDomandeAccreditamento {
  content: DomandaAccreditamento[];
}
