import { Component, Input } from '@angular/core';
import { DichiarazioneDeleteResponse, RifiutoConferito } from '../../models/dichiarazione.model';
import { DichiarazioneService } from '../../services/dichiarazioni.service';
import { NotificationService } from '@app/shared/notification/notification.service';
import { ModalComponent } from '@app/shared/modal/models/modal.component';
import { csiCatchError } from '@app/core/operators/catch-error.operator';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { IMessage } from '@app/core/models/shared.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete-rifiuto-conferito',
  templateUrl: './confirm-delete-rifiuto-conferito.component.html'
})
export class ConfirmDeleteRifiutoConferitoComponent extends ModalComponent {
  @Input() rifiuto: RifiutoConferito;
  @Input() idDichAnnuale: number;
  @Input() messageConfirm: IMessage;

  constructor(
    private service: DichiarazioneService,
    private notificationService: NotificationService
  ) {
    super();
  }

  onDismiss() {
    this.modalContainer.dismiss(false);
  }

  onDelete() {
    // se il rifiuto non è presente a DB chiudo la modale. Nel tab verrà eliminato dalla dichiarazione
    if(this.rifiuto.isRam){
      this.modalContainer.close(true);
    }else if(this.idDichAnnuale){
      this.service
      .deleteRifiutoConferito(this.idDichAnnuale, this.rifiuto.rifiutoTariffa.idRifiutoTariffa)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: DichiarazioneDeleteResponse) => {
        if(response.message){
          this.notificationService.info({
             title: response.message.titoloMsg,
             text: response.message.testoMsg
          });
        }
        this.modalContainer.close();
      });
    }else{
      this.modalContainer.close(true);
    }
  }
}
