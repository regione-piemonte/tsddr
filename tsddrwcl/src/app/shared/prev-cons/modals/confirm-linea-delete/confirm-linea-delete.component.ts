import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@app/shared/modal/models/modal.component';
import { NotificationService } from '@app/shared/notification/notification.service';
import { MrService } from '../../services/mr.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { csiCatchError } from '@app/core/operators/catch-error.operator';
import { IPrevConsLineeEntity, IPrevConsResponse } from '../../interfaces/prev-cons.interface';
import { IMessage } from '@app/core/models/shared.model';

@UntilDestroy()
@Component({
  selector: 'app-confirm-linea-delete',
  templateUrl: './confirm-linea-delete.component.html'
})
export class ConfirmLineaDeleteComponent extends ModalComponent implements OnInit {
  @Input() messageConfirm: IMessage;
  @Input() selectedLinea: IPrevConsLineeEntity;

  constructor(private notificationService: NotificationService,
              private mrService: MrService) {
    super();
   }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss(){
    this.modalContainer.dismiss(false);
  }

  onDelete(){
    //se sono in modifica
    if(this.selectedLinea.idPrevConsLinee > 0){
        this.mrService.deleteDichiarazioneMrPrevConsLinee(this.selectedLinea.idPrevConsLinee)
        .pipe(csiCatchError(),untilDestroyed(this))
        .subscribe((response: IPrevConsResponse) => {
          if(response.message){
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
