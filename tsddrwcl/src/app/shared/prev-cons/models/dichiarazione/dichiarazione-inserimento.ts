
import { IProfiloACL } from "@app/core/models/acl.model";
import { IMessage } from "@app/core/models/shared.model";
import { PrevConsEditAbstract, IPath } from "../abstract-factory/prev-cons-edit-abstract";
import { MrService } from "../../services/mr.service";
import { IPrevCons, IStatusMrFormEditingStore } from "../../interfaces/prev-cons.interface";
import { PrevConsClass } from "../classes/prev-cons.class";
import { MrStoreService } from "../../services/mr-store.service";
import { ID_TIPO_DOC } from "../constants";

export class DichiarazioneInserimento extends PrevConsEditAbstract {
  public isEditMode: boolean = true;
  public helperTitle: string;
  public path: IPath = 'dichiarazioni-mr';
  public idTipoDoc = ID_TIPO_DOC.DICHIARAZIONE;
  public profiloACL: IProfiloACL;
  //Bottone inserimento
  public btnNuovaMr: string = 'DICHIARAZIONI_MR.RICERCA.NUOVA_DICHIARAZIONE';
  public ariaLabelNuovaMr: string = 'DICHIARAZIONI_MR.RICERCA.NUOVA_DICHIARAZIONE';
  //Bottone invio
  public btnInserisciMr: string = 'DICHIARAZIONI.CREATE.FORM.BUTTON';
  public ariaLabelInserisci: string = 'DICHIARAZIONI_MR.CREATE.FORM.BUTTON.LABEL';

  public msgModal: IMessage;
  public titleModal: string;
  public prevCons: PrevConsClass;

  public status: IStatusMrFormEditingStore;

  constructor(
    readonly dichiarazioneMrService: MrService,
    readonly storeService: MrStoreService
  ){
    super();
  }

  public setHelperTitle(value: string): void {
    this.helperTitle = value;
  }
  public setProfiloACL(profilo: IProfiloACL): void {
    this.profiloACL = profilo;
  }

  /**
  * @description effettuo i dovuti controlli prima di inviare una dichiarazione
  */
  public isOpenModalCreate(prev?: IPrevCons): boolean {
    //controllo che 'Richiesta-ammissione' sia compilato
    return !prev.idPrevConsRMr && (!prev.modalita || !prev.dataInvioDoc);
  }

  public setModalPros({titoloMsg, ...msg}: IMessage): void {
    this.titleModal = titoloMsg;
    this.msgModal = {...msg, titoloMsg};
  }

  /**
   * @descrizione mi restituisce la classe prevCons
   */
  public createPrevCons(): PrevConsClass {
    this.prevCons = new PrevConsClass(this.idTipoDoc);
    return this.prevCons;
  }

  /**
   * @description verifica se ci sono modifiche non salvate prima di premere pulsante indietro
   */
  public isEditing(): boolean {
    const status = this.storeService.getStatus();
    if (status.richiestaChanged || status.processoChanged) {
      return true;
    }
    return false;
  }

  /**
   * @description controlla lo stato di validità delle sezioni di una prevCons per abilitare il pulsante di invio
   */
  public sendEnabled(): boolean {

    this.status = this.storeService.getStatus();
    if (this.status.richiestaValid && this.status.processoValid){
        return true;
      } else {
        return false;
      }
  }

  /**
   * @description controlla lo stato di validità delle sezioni di una prevCons per abilitare il pulsante di invio
   */
  public showNewPrevConsBtn(onVerificaPresenzaMr: boolean): boolean {
    return this.profiloACL.content.insert && !onVerificaPresenzaMr;
  }

  public showTabs(onVerificaPresenzaMr: boolean): boolean {
    return this.profiloACL.content.insert && onVerificaPresenzaMr;
  }

  public showDraftCreateBtn(onVerificaPresenzaMr: boolean): boolean {
    return this.profiloACL.content.insert && onVerificaPresenzaMr;
  }

}
