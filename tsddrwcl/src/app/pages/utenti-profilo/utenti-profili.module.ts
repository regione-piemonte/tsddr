import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { UtentiProfiloRoutingModule } from '@pages/utenti-profilo/utenti-profilo-routing.module';
import { UtentiProfiloGuards } from '@pages/utenti-profilo/services/utenti-profilo.guards';
import { FormModule } from '@shared/form';
import { TranslateModule } from '@eng-ds/translate';
import { TableModule } from '@shared/table';
import { ConfirmDeleteComponent } from './components/confirm-delete/confirm-delete.component';
import { ConfirmExitComponent } from './components/confirm-exit/confirm-exit.component';

@NgModule({
  declarations: [MainComponent, ConfirmDeleteComponent, ConfirmExitComponent],
  providers: [UtentiProfiloGuards],
  imports: [
    CommonModule,
    SharedComponentsModule,
    UtentiProfiloRoutingModule,
    FormModule,
    TranslateModule,
    TableModule
  ]
})
export class UtentiProfiloModule {}
