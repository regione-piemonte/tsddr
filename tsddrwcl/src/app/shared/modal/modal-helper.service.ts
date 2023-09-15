import { EventEmitter, Injectable } from '@angular/core';
import { ModalOptions } from './models/modal.options';
import { ModalComponent } from './models/modal.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ContainerModalComponent } from './components/container-modal/container-modal.component';
import { ModalService } from './modal.service';
import { AreaPersonaleModalComponent } from '@app/pages/components/area-personale-modal/area-personale-modal.component';
import { Subscription } from 'rxjs';
import { BroadcastEventService } from '@app/core/services/broadcast-event.service';
import { I18nService } from '@eng-ds/translate';


@Injectable({
  providedIn: 'root'
})
export class ModalHelperService {
  private dataApiSubscription: Subscription = new Subscription;

  constructor(private modalService: ModalService,
              private broadcastEventService: BroadcastEventService,
              private i18n: I18nService,
    ) {
   
  }

  public init() {
    this.dataApiSubscription = this.broadcastEventService
    .getStream()
    .subscribe((packet) => {
      console.log(packet);
      switch (packet.key) {
        case "open-area-personale":
          this._openDialog();
          break;
        case "security-change-profile":
          this._closeDialog();
          break;
      }
    });
  }

  private _openDialog(){
    this.modalService.openDialog(AreaPersonaleModalComponent, {
      header: this.i18n.translate('DIALOG.AREAPERSONALE.TITLE'),
      showCloseButton: true,
      context: { booking: {} }
    });
  }
  private _closeDialog(){
    this.modalService.dismissAll('security-change-profile');
  }
 
 
}
