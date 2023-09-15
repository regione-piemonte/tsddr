import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccreditamentoFoRoutingModule } from './accreditamento-fo-routing.module';
import { NuovaDomandaComponent } from './pages/nuova-domanda/nuova-domanda.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { FormModule } from '@shared/form';
import { TranslateModule } from '@eng-ds/translate';
import { ConfirmExitComponent } from '@pages/accreditamento-fo/components/confirm-exit/confirm-exit.component';
import { GestioneComponent } from './pages/gestione/gestione.component';
import { TableModule } from '@shared/table';
import { ConfirmDeleteComponent } from '@pages/accreditamento-fo/components/confirm-delete/confirm-delete.component';
import { DomandaAccreditamentoResolve } from '@pages/accreditamento-fo/services/domanda-accreditamento.resolve';
import { EditDomandaComponent } from './pages/edit-domanda/edit-domanda.component';

/*
  Accreditamento per utente FO
  Url di Riferimento: /tsddr/accreditamento
*/

@NgModule({
  declarations: [
    NuovaDomandaComponent,
    ConfirmExitComponent,
    GestioneComponent,
    ConfirmDeleteComponent,
    EditDomandaComponent
  ],
  providers: [DomandaAccreditamentoResolve],
  imports: [
    CommonModule,
    AccreditamentoFoRoutingModule,
    SharedComponentsModule,
    FormModule,
    TranslateModule,
    TableModule
  ]
})
export class AccreditamentoFoModule {}
