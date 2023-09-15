import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';

import { AccreditamentoFoService } from '@pages/accreditamento-fo/services/accreditamento-fo.service';
import { DomandaAccreditamento } from '@pages/accreditamento-fo/models/domanda-accreditamento.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html'
})
export class ConfirmDeleteComponent extends ModalComponent implements OnInit {
  @Input() domanda: DomandaAccreditamento;
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private loadingService: LoadingService,
    private service: AccreditamentoFoService,
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
      .deleteDomandaAccreditamento(this.domanda)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: DomandaAccreditamento) => {
        this.loadingService.hide();
        // this.notificationService.info({
        //   title: response.message.titoloMsg,
        //   text: response.message.testoMsg
        // });
        this.modalContainer.close();
      });
  }
}
