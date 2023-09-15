
import { IProfiloACL } from "@app/core/models/acl.model";
import { IMessage } from "@app/core/models/shared.model";
import { IPrevCons, IStatusMrFormEditingStore } from "../../interfaces/prev-cons.interface";
import { PrevConsClass } from "../classes/prev-cons.class";

export type IPath = 'dichiarazioni-mr' | 'richieste-mr' | 'dichiarazioni-mr/lista' | 'richieste-mr/lista' ;

export abstract class PrevConsEditAbstract {

  public abstract helperTitle: string;
  public abstract profiloACL: IProfiloACL;
  public abstract path: IPath;
  public abstract idTipoDoc: number;
  public abstract msgModal: IMessage;
  public abstract titleModal: string;
  public abstract prevCons: PrevConsClass;
  public abstract isEditMode: boolean;
  public abstract status: IStatusMrFormEditingStore;
  //Bottone invio
  public abstract btnInserisciMr: string;
  public abstract ariaLabelInserisci: string;

  /**
   * @description imposto il messaggio ed il title da visualizzare nella modale
   */
  public abstract setModalPros(content: IMessage):void;

  /**
   * @description setta il valore messaggio info header
   */
  public abstract setHelperTitle(value: string): void;
  /**
   * @description setta il valore del profilo che ricevo dal service
   */
  public abstract setProfiloACL(profilo: IProfiloACL): void;
  /**
   * @descrizione Controlla apertura modale prima dell'invio della prevCons
   */
  public abstract isOpenModalCreate(prev?: IPrevCons):boolean;
  /**
   * @descrizione mi restituisce la classe prevCons
   */
  public abstract createPrevCons(): PrevConsClass;
  /**
   * @description controlla lo stato di validità delle sezioni di una prevCons per abilitare il pulsante di invio
   */
  public abstract sendEnabled(): boolean;
  /**
   * @description verifica se ci sono modifiche non salvate prima di premere pulsante indietro
   */
  public abstract isEditing(): boolean;
  /**
   * @description controlli per visualizzare il pulsante nuova prevCons
   *
   * @param specificCheck In inserimento è verificaPresenza, in edit è la presenza o meno della prevCons
   */
  public abstract showNewPrevConsBtn(specificCheck: boolean): boolean;
  /**
   * @description controlli per visualizzare i tabs
   *
   * @param specificCheck In inserimento è verificaPresenza, in edit è la presenza o meno della prevCons
   */
  public abstract showTabs(specificCheck: boolean): boolean;
  /**
   * @description controlli per visualizzare i pulsanti salva in bozza e invia prev cons
   *
   * @param specificCheck In inserimento è verificaPresenza, in edit è la presenza o meno della prevCons
   */
  public abstract showDraftCreateBtn(specificCheck: boolean): boolean;

}
