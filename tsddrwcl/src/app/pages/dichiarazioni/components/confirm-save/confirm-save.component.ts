import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';

@Component({
  selector: 'app-confirm-save',
  templateUrl: './confirm-save.component.html'
})
export class ConfirmSaveComponent extends ModalComponent implements OnInit {
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
