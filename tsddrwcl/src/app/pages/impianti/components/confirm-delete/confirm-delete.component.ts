import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { Impianto, ImpiantoDeleteResponse } from '../../models/impianto.model';
import { ImpiantiService } from '../../services/impianti.service';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html'
})
export class ConfirmDeleteComponent extends ModalComponent implements OnInit {
  @Input() impianto: Impianto;
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private loadingService: LoadingService,
    private service: ImpiantiService,
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
    console.log(this.impianto);
    this.service
      .deleteImpianto(this.impianto)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: ImpiantoDeleteResponse) => {
        this.loadingService.hide();
        // this.notificationService.info({
        //   title: response.message.titoloMsg,
        //   text: response.message.testoMsg
        // });
        this.modalContainer.close();
      });
  }
}
