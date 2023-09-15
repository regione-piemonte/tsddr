import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { ProfiliRoutingModule } from '@pages/profili/profili-routing.module';
import { ProfiliGuards } from '@pages/profili/services/profili.guards';
import { FormModule } from '@shared/form';
import { TranslateModule } from '@eng-ds/translate';
import { TableModule } from '@shared/table';
import { ConfirmDeleteComponent } from './components/confirm-delete/confirm-delete.component';
import { ConfirmExitComponent } from './components/confirm-exit/confirm-exit.component';

@NgModule({
  declarations: [MainComponent, ConfirmDeleteComponent, ConfirmExitComponent],
  providers: [ProfiliGuards],
  imports: [
    CommonModule,
    SharedComponentsModule,
    ProfiliRoutingModule,
    FormModule,
    TranslateModule,
    TableModule
  ]
})
export class ProfiliModule {}
