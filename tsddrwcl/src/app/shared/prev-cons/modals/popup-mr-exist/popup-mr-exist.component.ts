import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@app/shared/modal/models/modal.component';
import { UntilDestroy } from '@ngneat/until-destroy';
@UntilDestroy()
@Component({
  selector: 'app-popup-mr-exist',
  templateUrl: './popup-mr-exist.component.html',
})
export class PopupMrExistComponent extends ModalComponent implements OnInit {
  @Input() messageConfirm: Record<string, any>;

  constructor() {
    super();
   }

  ngOnInit(): void {
     //This is intentional
  }

  onDismiss() {
    this.modalContainer.dismiss(true);
  }

}
