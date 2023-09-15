import { Component, Input, OnInit } from '@angular/core';
import { csiCatchError } from '@app/core/operators/catch-error.operator';
import { ModalComponent } from '@app/shared/modal/models/modal.component';
import { NotificationService } from '@app/shared/notification/notification.service';
import { LoadingService } from '@app/theme/layouts/loading.services';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { IPrevConsResponse } from '../../interfaces/prev-cons.interface';
import { MrService } from '../../services/mr.service';

@UntilDestroy()
@Component({
  selector: 'app-delete-mr',
  templateUrl: './delete-mr.component.html'
})
export class DeleteMrComponent extends ModalComponent {
  @Input() idPrevCons: number;
  @Input() idTipoDoc: number;
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private mrService: MrService,
    private loadingService: LoadingService,
    private notificationService: NotificationService
  ) {
    super();
  }

  onDismiss(): void {
    this.modalContainer.dismiss(false);
  }

  onDelete(): void {
    this.loadingService.show();
    this.mrService
      .deleteDichiarazione(this.idPrevCons, this.idTipoDoc)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: IPrevConsResponse) => {
        this.loadingService.hide();
        if(response.message){
          this.notificationService.info({
             title: response.message.titoloMsg,
             text: response.message.testoMsg
          });
        }
        this.modalContainer.close();
      });
  }

}
