export interface Gestore {
  codFiscPartiva?: string;
  email?: string;
  idGestore: any;
  idProfilo?: number;
  idIscrizioneAlbo?: string;
  pec?: string;
  ragSociale?: string;
  telefono?: string;
  dataInizioValidita?: string;
  dataFineValidita?: string;
  sedeLegale?: {
    nazione?: { 
      idNazione: string;
      descNazione?: string;
      idIstatNazione?: string;
    };
    idIndirizzo?: number;
    originalId?: number;
    versione?: number;
    indirizzo?: string;
    cap?: string;
    comune?: {
      idComune?: number;
      codCatasto?: string;
      comune?: string;
      cap?: string;
      idComuneIstat?: number;
      provincia?: {
        idProvincia?: number;
        descProvincia?: string;
        idProvinciaIstat?: number;
        siglaProv?: string;
        regione?: {
          idRegione?: number;
          descRegione?: string;
          idIstatRegione?: number;
        };
      };
    };
    sedime?: {
      idSedime?: number;
      descSedime?: string;
    };
    tipoIndirizzo?: {
      idTipoIndirizzo?: number;
      descTipoIndirizzo?: string;
    };
  };
  legaleRappresentante: {
    datiSogg?: {
      nome?: string;
      cognome?: string;
      codFiscale?: string;
    };
    qualifica: string
  };
  naturaGiuridica?: {
    descNaturaGiuridica?: string;
    idNaturaGiuridica?: number;
    dataInizioValidita?: string;
  };
}

export interface GestoreLinkUpdateResponse {
  content: any;
  message: any;
}

export interface GestoreLinkDeleteResponse {
  content: any;
  message: any;
}
