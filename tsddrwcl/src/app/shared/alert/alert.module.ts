import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertComponent } from './components/alert/alert.component';
import { SharedComponentsModule } from '../components/shared-components.module';
import { AlertService } from './alert.service';
import { TranslateModule } from '@eng-ds/translate';


@NgModule({
  declarations: [AlertComponent],
  imports: [
    CommonModule,
    SharedComponentsModule,
    TranslateModule
  ],
  providers: [
    AlertService
  ]
})
export class AlertModule {
}
