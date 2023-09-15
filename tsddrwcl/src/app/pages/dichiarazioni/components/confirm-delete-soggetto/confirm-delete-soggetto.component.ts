import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { DichiarazioneService } from '../../services/dichiarazioni.service';
import { DichiarazioneDeleteResponse, SoggettoMr, SoggettoMrExtended } from '../../models/dichiarazione.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete-soggetto',
  templateUrl: './confirm-delete-soggetto.component.html'
})
export class ConfirmDeleteSoggettoComponent extends ModalComponent implements OnInit {
  @Input() soggetto: SoggettoMrExtended;
  @Input() idDichAnnuale: number;
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private service: DichiarazioneService,
    private notificationService: NotificationService
  ) {
    super();
  }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss() {
    this.modalContainer.dismiss(false);
  }

  onDelete() {
    if(this.soggetto.isRam){
      this.modalContainer.close(true);
    }else if(this.idDichAnnuale){
      this.service
      .deleteDichiarazioneSoggetto(this.idDichAnnuale,this.soggetto.idSoggettiMr)
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
