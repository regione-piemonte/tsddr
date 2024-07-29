import { Indirizzo } from "@app/pages/impianti/models/impianto.model";
import { IMessage } from "@core/models/shared.model";
import { ImpiantoMr } from "./api-mr.model";

export interface IPrevConsResponse {
  content: IPrevCons;
  message?: IMessage;
}

export interface IGetImpiantoLinee {
  idImpiantoLinea: number;
  idLinea: number;
  idImpianto: number;
  dataInizioValidita: string;
  idSottoLinea?: number;
  codLinea?: string;
  codSottoLinea?: string;
}

export interface IStatusMrFormEditingStore {
  richiestaValid?: boolean;
  richiestaChanged?: boolean;
  processoValid?: boolean;
  processoChanged?: boolean;
  sedeValid?: boolean;
  sedeChanged?: boolean;
}

export interface IPrevCons {
  idPrevCons?: number;
  idPrevConsRMr?: number;
  prevConsRichiesta?: IPrevCons;
  annoTributo?: number;
  dataDoc?: string;
  dataInvioDoc?: string;
  dataProtocollo?: string;
  modalita?: string;
  richiestaAmmissioneHasBeenUpdated?: boolean;
  numProtocollo?: string;
  percRecupero?: number;
  numProtDoc?: any;
  percScarto?: number;
  prevConsLinee?: IPrevConsLineeEntity[] | null;
  statoDichiarazione?: IStatoPrevCons;
  tipoDoc?: ITipoDoc;
  impianto?: ImpiantoMr;
  indirizzo?: Indirizzo;
  lineeImpianto?: IGetImpiantoLinee[];
  message?: IMessage;
  pregresso?: boolean;
}

export interface IPrevConsLineeEntity {
  idImpiantoLinea?: number;
  codLinea?: string;
  codSottoLinea?: string;
  descLinea?: string;
  descSottoLinea?: string;
  descSommaria?: string;
  hasBeenUpdated?: boolean;
  idPrevCons?: number;
  idPrevConsLinee?: number;
  percRecupero?: number;
  percScarto?: number;
  prevConsDett?: (IPrevConsDettEntity)[] | null;
  dettRii?: IPrevConsDettEntity[];
  dettMat?: IPrevConsDettEntity[];
  dettRru?: IPrevConsDettEntity[];
  dettRu?: IPrevConsDettEntity[];
  totRii?: number;
  totMat?: number;
  totRru?: number;
  totRu?: number;
  isLegameRmr?: boolean;
}

export interface IPrevConsDettEntity {
  idPrevConsDett?: number;
  descMatUscita?: string;
  destinazione?: string;
  eer?: IEer;
  hasBeenUpdated?: boolean;
  quantita?: number;
  quantitaRichiesta?: number;
  sezione?: ISezione;
  unitaMisura?: IUnitaMisura;
  idPrevConsDettRmr?: number | null;
  prevConsDettRichiesta?: IPrevConsDettEntity;
  isRam?: boolean;
  isPrevConsDettRmr?: boolean;
}

export interface IEer {
  idEer?: number;
  codiceEer?: string;
  descrizione?: string;
}

export interface ISezione {
  idSezione?: number;
  descSezione?: string;
}

export interface IUnitaMisura {
  idUnitaMisura?: number;
  descUnitaMisura?: string;
}

export interface IStatoPrevCons {
  descrStatoDichiarazione?: string;
  idStatoDichiarazione?: number;
}

export interface ITipoDoc {
  descTipoDoc?: string;
  idTipoDoc?: number;
}

export interface IRifiutiObject {
  totValue?: number;
  prevConsDett?: IPrevConsDettEntity[];
}
