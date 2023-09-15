import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { ImpiantiService } from '../../services/impianti.service';
import { Atto } from '../../models/atto.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete-atto',
  templateUrl: './confirm-delete-atto.component.html'
})
export class ConfirmDeleteAttoComponent extends ModalComponent implements OnInit {
  @Input() atto: Atto;
  @Input() idImpianto: number;
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
    this.service
    .deleteImpiantoAtto(this.idImpianto,this.atto.idAtto)
    .pipe(csiCatchError(), untilDestroyed(this))
    .subscribe((response: any) => {
      this.loadingService.hide();
      this.notificationService.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
      });
      this.modalContainer.close();
    });
  }
}
