export interface Gestore {
  codFiscPartiva?: string;
  email?: string;
  idUtente?: number;
  idGestore: any;
  idProfilo?: number;
  idIscrizioneAlbo?: string;
  pec?: string;
  ragSociale?: string;
  telefono?: string;
  dataInizioValidita?: string;
  dataFineValidita?: string;
}

export interface GestoreLinkUpdateResponse {
  content: any;
  message: any;
}

export interface GestoreLinkDeleteResponse {
  content: any;
  message: any;
}
