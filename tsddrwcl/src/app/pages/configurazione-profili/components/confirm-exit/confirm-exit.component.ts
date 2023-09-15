import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@shared/modal/models/modal.component';

@Component({
  selector: 'app-confirm-exit',
  templateUrl: './confirm-exit.component.html'
})
export class ConfirmExitComponent extends ModalComponent implements OnInit {
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
