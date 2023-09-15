import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@eng-ds/translate';
import { ModalService } from './modal.service';
import { ModalComponent } from './models/modal.component';
import { SharedComponentsModule } from '../components/shared-components.module';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { ContainerModalComponent } from './components/container-modal/container-modal.component';
import { ModalHelperService } from './modal-helper.service';

@NgModule({
  declarations: [ContainerModalComponent, ModalComponent],
  imports: [
    CommonModule,
    SharedComponentsModule,
    NgbModalModule,
    TranslateModule
  ],
  exports: [
    ModalComponent, NgbModalModule
  ],
  providers: [
    ModalService, ModalHelperService
  ]
})
export class ModalModule {
}
