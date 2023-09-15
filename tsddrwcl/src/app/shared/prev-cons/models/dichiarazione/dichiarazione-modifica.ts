import { IProfiloACL } from "@app/core/models/acl.model";
import { IMessage } from "@app/core/models/shared.model";
import { IPrevCons, IStatusMrFormEditingStore } from "../../interfaces/prev-cons.interface";
import { PrevConsEditAbstract, IPath } from "../abstract-factory/prev-cons-edit-abstract";
import { PrevConsClass } from "../classes/prev-cons.class";
import { MrService } from "../../services/mr.service";
import { MrStoreService } from "../../services/mr-store.service";
import { ID_TIPO_DOC } from "../constants";

export class DichiarazioneModifica extends PrevConsEditAbstract {

    public isEditMode: boolean = true;
    public helperTitle: string;
    public profiloACL: IProfiloACL;
    public path: IPath = 'dichiarazioni-mr/lista';
    public idTipoDoc: number = ID_TIPO_DOC.DICHIARAZIONE;
    public msgModal: IMessage;
    public titleModal: string;
    public prevCons: PrevConsClass;
    public status: IStatusMrFormEditingStore;
    //Bottone invio
    public btnInserisciMr: string = 'DICHIARAZIONI.CREATE.FORM.BUTTON';
    public ariaLabelInserisci: string = 'Invia nuova dichiarazione MR';

    constructor(
        readonly dichiarazioneMrService: MrService,
        readonly storeService: MrStoreService
    ) {
        super();
    }

    public setModalPros(content: IMessage): void {
        // serve per settare titolo e messaggio di alcune modali che in richiesta non vengono utilizzate
        // ad esempio in dichiarazione -> modale che ti ricorda di compilare i campi nel tab richiesta
    }
    public setHelperTitle(value: string): void {
      //da documentazione, non è richiesto l'helperTitle per la modifica
      this.helperTitle = null;
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

    public createPrevCons(): PrevConsClass {

      this.prevCons = new PrevConsClass(this.idTipoDoc);
      return this.prevCons;
    }

    /**
    * @description controlla lo stato di validità delle sezioni di una prevCons per abilitare il pulsante di invio
    */
    public sendEnabled(): boolean {
    
      // metodo non utilizzato nella visualizzazione
      if (!this.isEditMode) {
        return false;
      }
      this.status = this.storeService.getStatus();
      if (this.status.richiestaValid && this.status.processoValid){
          return true;
        } else {
          return false;
        }
    }

    /**
    * @description verifica se ci sono modifiche non salvate prima di premere pulsante indietro
    */
    public isEditing(): boolean {
      // metodo non utilizzato nella visualizzazione
      if (!this.isEditMode) {
        return false;
      }
      this.status = this.storeService.getStatus();
      if (this.status.richiestaChanged || this.status.processoChanged) {
        return true;
      }
      return false;
    }

    public showNewPrevConsBtn(): boolean {
        // questo pulsante si visualizza solo in inserimento
        return false;
    }
    public showTabs(currentDichiarazione: boolean): boolean {
        return currentDichiarazione;
    }
    public showDraftCreateBtn(currentDichiarazione: boolean): boolean {
        return this.profiloACL.content.update && currentDichiarazione && this.isEditMode;
    }

}
