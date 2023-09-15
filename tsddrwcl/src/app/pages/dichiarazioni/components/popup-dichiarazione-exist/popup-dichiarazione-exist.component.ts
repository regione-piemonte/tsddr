import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { NotificationService } from '@shared/notification/notification.service';
import { UntilDestroy } from '@ngneat/until-destroy';
import { DichiarazioneService } from '../../services/dichiarazioni.service';

@UntilDestroy()
@Component({
  selector: 'app-popup-dichiarazione-exist.component',
  templateUrl: './popup-dichiarazione-exist.component.html'
})
export class PopupDichiarazioneExistComponent extends ModalComponent implements OnInit {
  @Input() messageConfirm: Record<string, any>;

  constructor(
    private service: DichiarazioneService,
    private notificationService: NotificationService
  ) {
    super();
  }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss() {
    this.modalContainer.dismiss(true);
  }


}
