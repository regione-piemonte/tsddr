import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { LoadingService } from '@theme/layouts/loading.services';
import { NotificationService } from '@shared/notification/notification.service';
import { I18nService } from '@eng-ds/translate';
import { ModalComponent } from '@shared/modal/models/modal.component';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Form } from '@app/shared/form';
import { ModalService } from '@app/shared/modal/modal.service';

@UntilDestroy()
@Component({
  selector: 'confirm-modal',
  templateUrl: './confirm-modal.component.html'
})
export class ConfirmModalComponent extends ModalComponent implements OnInit {
  @Input() confirmText: String='Sei sicuro di voler procedere?';


  constructor() {
    super();
  }

  ngOnInit(): void {
     //This is intentional
  }

  onDismissClick() {
    this.modalContainer.close(false);
  }

  onSaveClick() {
    this.modalContainer.close(true);
  }
}
