import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import {
  FunzionalitaProfili,
  FunzionalitaProfiliDeleteResponse
} from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { I18nService } from '@eng-ds/translate';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { UtilityService } from '@core/services';
import { ProfiliService } from '../../services/profili.service';
import { Profilo } from '../../models/profili.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html'
})
export class ConfirmDeleteComponent extends ModalComponent implements OnInit {
  @Input() profilo: Profilo;
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private loadingService: LoadingService,
    private service: ProfiliService,
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
      .deleteProfilo(this.profilo)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: FunzionalitaProfiliDeleteResponse) => {
        this.loadingService.hide();
        this.notificationService.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.modalContainer.close();
      });
  }
}
