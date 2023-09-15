import { Component, Input, OnInit } from '@angular/core';
import { ModalComponent } from '@app/shared/modal/models/modal.component';
import { UntilDestroy } from '@ngneat/until-destroy';
@Component({
  selector: 'app-info-impianto',
  templateUrl: './info-impianto.component.html',
  styleUrls: ['./info-impianto.component.scss']
})
export class InfoImpiantoComponent  extends ModalComponent implements OnInit {
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
