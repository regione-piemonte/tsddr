import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationComponent } from './components/notification/notification.component';
import { SharedComponentsModule } from '../components/shared-components.module';
import { NotificationService } from './notification.service';
import { TranslateModule } from '@eng-ds/translate';
import { NotificationContainerComponent } from './components/notification-container/notification-container.component';
import { NgbToastModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [NotificationComponent, NotificationContainerComponent],
  imports: [
    CommonModule,
    SharedComponentsModule,
    TranslateModule,
    NgbToastModule
  ],
  exports: [NotificationContainerComponent]
})
export class NotificationModule {}
