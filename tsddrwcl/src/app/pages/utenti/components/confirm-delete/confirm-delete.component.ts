import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import {
  Utente,
  UtenteDeleteResponse
} from '@pages/utenti/models/utente.model';
import { UtentiService } from '@pages/utenti/services/utenti.service';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html'
})
export class ConfirmDeleteComponent extends ModalComponent implements OnInit {
  @Input() utente: Utente;
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
      .deleteUtente(this.utente)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: UtenteDeleteResponse) => {
        this.loadingService.hide();
        // this.notificationService.info({
        //   title: response.message.titoloMsg,
        //   text: response.message.testoMsg
        // });
        this.modalContainer.close();
      });
  }
}
