import { Atto } from "@pages/impianti/models/atto.model";
import { IndirizzoExtended, StatoImpianto, TipoImpianto } from "@pages/impianti/models/impianto.model";
import { LineaExtended } from "@pages/impianti/models/linea.model";
import { IMessage } from "@core/models/shared.model";
import { Gestore } from "@app/pages/gestori/models/gestore.model";

export interface IComboAnni {
  content?: (number)[] | null;
  message: IMessage;
}

export interface ICombo {
  content?: (IComboContentEntity)[] | null;
  message?: IMessage;
}

export interface IComboContentEntity {
  additionalValue?: string;
  id?: string;
  value?: string;
}

export interface IexistsPrevCons {
  content: boolean;
  message: IMessage;
}

export interface IParamFiltroApplicati {
  content: string;
  message: IMessage;
}

export interface IDichMrDownload {
  content: IMrDownloadContent;
  message: IMessage;
}

export interface IMrDownloadContent {
  classificazione: string;
  classificazioneId: string;
  dataOraProtocollo: string;
  documentoUUID: string;
  file?: (string)[] | null;
  filename: string;
  folderId: string;
  idActa: string;
  numProtocollo: string;
  objectIdDocumento: string;
  registrazioneId: string;
}
/* differenze con interfaccia impianto già esistente:
gestore e indirizzo */
export interface ImpiantoMr {
  dataFineValidita?: string;
  dataInizioValidita?: string;
  deletable?: boolean;
  denominazione?: string;
  gestore?: Gestore;
  idImpianto?:number;
  indirizzo?: IndirizzoExtended;
  statoImpianto?: StatoImpianto;
  tipoImpianto?: TipoImpianto;
  readOnly?: boolean;
  linee?: LineaExtended[];
  atti?: Atto[]
}
