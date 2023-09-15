import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@app/shared/modal/models/modal.component';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { MrService } from '../../services/mr.service';
import { csiCatchError } from '@app/core/operators/catch-error.operator';
import { NotificationService } from '@app/shared/notification/notification.service';
import { IPrevConsResponse } from '../../interfaces/prev-cons.interface';
import { IMessage } from '@app/core/models/shared.model';

@UntilDestroy()
@Component({
  selector: 'app-prev-cons-dett-delete',
  templateUrl: './prev-cons-dett-delete.component.html',
})
export class PrevConsDettDeleteComponent extends ModalComponent implements OnInit {

  @Input() idPrevConsDett: number;
  @Input() idPrevCons: number;
  @Input() messageConfirm: IMessage;

  constructor(private mrService: MrService,
              private notificationService: NotificationService) {
    super();
   }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss() {
    this.modalContainer.dismiss(false);
  }

  onDelete() {
      if(this.idPrevCons){
      this.mrService
        .deleteDichiarazioneMrPrevConsDett(this.idPrevConsDett)
        .pipe(csiCatchError(), untilDestroyed(this))
        .subscribe((response: IPrevConsResponse) => {
          if (response.message) {
            this.notificationService.info({
              title: response.message.titoloMsg,
              text: response.message.testoMsg
            });
          }
        });
    }
    this.modalContainer.close(true);
  }

}
