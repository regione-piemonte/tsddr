import { IProfiloACL } from "@app/core/models/acl.model";
import { IMessage } from "@app/core/models/shared.model";
import { Indirizzo } from "@app/pages/impianti/models/impianto.model";
import { IPrevCons, IStatusMrFormEditingStore } from "../../interfaces/prev-cons.interface";
import { MrStoreService } from "../../services/mr-store.service";
import { MrService } from "../../services/mr.service";
import { PrevConsEditAbstract, IPath } from "../abstract-factory/prev-cons-edit-abstract";
import { PrevConsClass } from "../classes/prev-cons.class";
import { ID_TIPO_DOC } from "../constants";

export class RichiestaInserimento extends PrevConsEditAbstract {
  public isEditMode: boolean = true;
  public helperTitle: string;
  public profiloACL: IProfiloACL;
  public path: IPath = 'richieste-mr';
  public idTipoDoc: number = ID_TIPO_DOC.RICHIESTA;
  //Bottone inserimento
  public btnNuovaMr: string = 'RICHIESTA_MR.NUOVA_RICHIESTA';
  public ariaLabelNuovaMr: string = 'RICHIESTA_MR.CREATE.FORM.LABEL';
  //Bottone invio
  public btnInserisciMr: string = 'RICHIESTA_MR.CREATE.FORM.BUTTON';
  public ariaLabelInserisci: string = 'RICHIESTA_MR.CREATE.FORM.LABEL';

  public msgModal: IMessage;
  public titleModal: string;
  public prevCons: PrevConsClass;

  public status: IStatusMrFormEditingStore;

  constructor(
    readonly dichiarazioneMrService: MrService,
    readonly storeService: MrStoreService
  ) {
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
    //implementare in caso di pop-up da visualizzare prima dell'invio di una richiesta,
    //non presente sulla documentazione
    return false;
  }

  /**
   * @descrizione mi restituisce la classe prevCons
   */
  public createPrevCons(): PrevConsClass {
    this.prevCons = new PrevConsClass(this.idTipoDoc);
    return this.prevCons;
  }

  /**
   * @descrizione
   */
  public initTabSede(): Indirizzo {
    // passare quello che serve al tab sede
    return this.prevCons.indirizzo;
  }

  /**
   * @description verifica se ci sono modifiche non salvate prima di premere pulsante indietro
   */
  public isEditing(): boolean {
    this.status = this.storeService.getStatus();
    if (this.status.sedeChanged || this.status.processoChanged) {
      return true;
    }
    return false;
  }

  /**
   * @description controlla lo stato di validità delle sezioni di una prevCons per abilitare il pulsante di invio
   */
  public sendEnabled(): boolean {
    this.status = this.storeService.getStatus();
    console.log('this.status', this.status);
    if (this.status.sedeValid && this.status.processoValid){
        return true;
      } else {
        return false;
      }
  }

  public setModalPros({titoloMsg, ...msg}: IMessage): void {
    this.titleModal = titoloMsg;
    this.msgModal = {...msg, titoloMsg};
  }

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
