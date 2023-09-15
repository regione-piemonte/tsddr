import { Impianto, Indirizzo } from '@app/pages/impianti/models/impianto.model';

export interface DichiarazioneEditingStore {
  dichiarazione: Partial<Dichiarazione>;
  dichiarazionePrecedente: Partial<Dichiarazione>;
  report?: reportsDichiarazioneEditingStore;
  status?: statusFormEditingStore;
  versamentiValid: boolean;
  rifiutiValid: boolean;
  sedeValid: boolean;
  editMode?: boolean;
  viewMode?: boolean;
  key: string;
}

export interface reportsDichiarazioneEditingStore {
  totaleImporto: number;
}

export interface statusFormEditingStore {
  versamentiValid: boolean;
  rifiutiValid: boolean;
  sedeValid: boolean;
  versamentiChanged: boolean;
  rifiutiChanged: boolean;
  sedeChanged: boolean;
  soggettiChanged: boolean;
  annotazioniChanged: boolean;
}

export interface Dichiarazione {
  idDichAnnuale?: number;
  anno?: number;
  dataDichiarazione?: string;
  versione?: number;
  statoDichiarazione?: StatoDichiarazione;
  impianto?: Impianto;
  numProtocollo?: number;
  dataProtocollo?: string;
  annullable?: boolean;
  printable?: boolean;
  annotazioni?: string;
  creditoImposta?: number;
  saldoImposta?: number;
  indirizzo?: Indirizzo;
  rifiutiConferiti?: RifiutiConferiti;
  versamenti?: Versamenti;
  soggettiMr?: SoggettoMrExtended[]; // con flag per capire se sta in ram il soggetto
}

export interface StatoDichiarazione {
  idStatoDichiarazione?: number;
  descrStatoDichiarazione?: string;
}
export interface RifiutiConferiti {
  rifiutiConferiti?: RifiutoConferito[];
  totali?: TotaliRifiutiConferiti;
}
export interface RifiutoConferito {
  idRifiutoConferito?: number;
  unitaMisura?: UnitaMisura;
  rifiutoTariffa?: RifiutoTariffa;
  conferimenti?: Conferimento[];
  conferimentiReport?: { data: any[] };
  totale?: RiepilogoTotale;
  isRam?: boolean;
}
export interface RifiutoTariffa {
  idRifiutoTariffa?: number;
  descrizione?: string;
  flagRiduzione?: boolean;
  idTipoRifiuto?: string;
  idTipologia2?: string;
  idTipologia3?: string;
  importo?: number;
}

export interface Conferimento {
  idConferimento?: number;
  anno?: number;
  importo?: number;
  quantita?: number;
  periodo?: Periodo;
  unitaMisura?: UnitaMisura;
  rifiutoTariffa?: RifiutoTariffa;
}
export interface RiepilogoTotale {
  quantita?: number;
  importo?: number;
}

export interface TotaliRifiutiConferiti {
  totaliPeriodi?: TotalePeriodo[];
  totale?: RiepilogoTotale;
}
export interface UnitaMisura {
  idUnitaMisura?: number;
  descUnitaMisura?: string;
}
export interface Periodo {
  idPeriodo?: number;
  descPeriodo?: string;
}
export interface Versamenti {
  versamenti?: Versamento[];
  totale?: number;
  creditoAP?: number;
}
export interface Versamento {
  idVersamento?: number;
  dataVersamento?: string;
  importoVersato?: number;
  periodo?: Periodo;
}

export interface SoggettoMrExtended extends SoggettoMr {
  isRam?: boolean;
}
export interface SoggettoMr {
  idSoggettiMr?: number;
  codFiscPartiva?: string;
  ragSociale?: string;
  obbRagg?: boolean | string;
}
export interface TotalePeriodo {
  periodo?: Periodo;
  totale?: RiepilogoTotale;
}

export interface DichiarazioneUpdateResponse {
  message: {
    codMsg: string;
    titoloMsg: string;
    testoMsg: string;
    descTipoMsg: string;
  };
  content: Dichiarazione;
}

export interface DichiarazioneDeleteResponse {
  message: {
    codMsg: string;
    titoloMsg: string;
    testoMsg: string;
    descTipoMsg: string;
  };
  content: Dichiarazione;
}
