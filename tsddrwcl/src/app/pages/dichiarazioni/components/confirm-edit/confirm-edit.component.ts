import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';

@Component({
  selector: 'app-confirm-edit',
  templateUrl: './confirm-edit.component.html'
})
export class ConfirmEditComponent extends ModalComponent implements OnInit {
  @Input() messageConfirm: Record<string, any>;

  constructor() {
    super();
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
