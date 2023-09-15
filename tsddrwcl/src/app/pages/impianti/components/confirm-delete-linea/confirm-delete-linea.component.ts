import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { ImpiantiService } from '../../services/impianti.service';
import { LineaExtended } from '../../models/linea.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-delete-linea',
  templateUrl: './confirm-delete-linea.component.html'
})
export class ConfirmDeleteLineaComponent extends ModalComponent implements OnInit {
  @Input() linea: LineaExtended;
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
    if(this.linea.idLinea){
      this.service
      .deleteImpiantoLinea(this.idImpianto,this.linea.idLinea)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: any) => {
        this.loadingService.hide();
        this.notificationService.info({
           title: response.message.titoloMsg,
           text: response.message.testoMsg
        });
        this.modalContainer.close();
      })
    }else if(this.linea.idSottoLinea){
      this.service
      .deleteImpiantoSottolinea(this.idImpianto,this.linea.idSottoLinea)
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response: any) => {
        this.loadingService.hide();
        this.notificationService.info({
          title: response.message.titoloMsg,
          text: response.message.testoMsg
        });
        this.modalContainer.close();
      })
    }
  }
}
