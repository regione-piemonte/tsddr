import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { ConfigurazioneProfiliRoutingModule } from '@pages/configurazione-profili/configurazione-profili-routing.module';
import { ConfigurazioneProfiliGuards } from '@pages/configurazione-profili/services/configurazione-profili.guards';
import { FormModule } from '@shared/form';
import { TranslateModule } from '@eng-ds/translate';
import { TableModule } from '@shared/table';
import { ConfirmDeleteComponent } from './components/confirm-delete/confirm-delete.component';
import { ConfirmExitComponent } from './components/confirm-exit/confirm-exit.component';

@NgModule({
  declarations: [MainComponent, ConfirmDeleteComponent, ConfirmExitComponent],
  providers: [ConfigurazioneProfiliGuards],
  imports: [
    CommonModule,
    SharedComponentsModule,
    ConfigurazioneProfiliRoutingModule,
    FormModule,
    TranslateModule,
    TableModule
  ]
})
export class ConfigurazioneProfiliModule {}
