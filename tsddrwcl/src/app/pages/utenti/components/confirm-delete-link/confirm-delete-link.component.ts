import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { UtentiService } from '@pages/utenti/services/utenti.service';
import {
  Gestore,
  GestoreLinkDeleteResponse
} from '@pages/utenti/models/gestore.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete-link',
  templateUrl: './confirm-delete-link.component.html'
})
export class ConfirmDeleteLinkComponent
  extends ModalComponent
  implements OnInit
{
  @Input() gestore: Gestore;
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private loadingService: LoadingService,
    private service: UtentiService,
    private notificationService: NotificationService
  ) {
    super();
  }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss() {
    this.modalContainer.close();
  }

  onDelete() {
    this.loadingService.show();
    this.service
      .deleteLinkUtenteProfiloGestore(
        +this.gestore.idUtente,
        +this.gestore.idGestore,
        +this.gestore.idProfilo
      )
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: GestoreLinkDeleteResponse) => {
        this.loadingService.hide();
        // this.notificationService.info({
        //   title: response.message.titoloMsg,
        //   text: response.message.testoMsg
        // });
        this.modalContainer.close();
      });
  }
}
