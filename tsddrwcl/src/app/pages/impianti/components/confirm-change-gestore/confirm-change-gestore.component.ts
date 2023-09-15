import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { UtentiService } from '@pages/utenti/services/utenti.service';


@UntilDestroy()
@Component({
  selector: 'app-confirm-change-gestore',
  templateUrl: './confirm-change-gestore.component.html'
})
export class ConfirmChangeGestoreComponent
  extends ModalComponent
  implements OnInit
{
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private loadingService: LoadingService,
  ) {
    super();
  }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss() {
    this.modalContainer.close(false);
  }

  onConfirm() {
    //this.loadingService.show();
    this.modalContainer.close(true);
  }
}
