import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@app/shared/modal/models/modal.component';

@Component({
  selector: 'app-confirm-mr-exit',
  templateUrl: './confirm-mr-exit.component.html'
})
export class ConfirmMrExitComponent extends ModalComponent implements OnInit {
  @Input() messageConfirm: Record<string, any>;

  constructor() {
    super()
   }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss() {
    this.modalContainer.dismiss(false);
  }

  onConfirm() {
    this.modalContainer.close(true);
  }

}
