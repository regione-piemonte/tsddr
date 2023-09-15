import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { DomandaAccreditamentoRoutingModule } from '@pages/domanda-accreditamento/domanda-accreditamento-routing.module';
import { RicercaComponent } from './pages/ricerca/ricerca.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { TranslateModule } from '@eng-ds/translate';
import { FormModule } from '@shared/form';
import { DomandaAccreditamentoGuards } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.guards';
import { ListaComponent } from './pages/lista/lista.component';
import { TableModule } from '@shared/table';
import { ConfirmDeleteComponent } from '@pages/domanda-accreditamento/components/confirm-delete/confirm-delete.component';
import { EditComponent } from '@pages/domanda-accreditamento/pages/edit/edit.component';
import { EditDomandaAccreditamentoGuards } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento-edit.guards';
import { DomandaAccreditamentoResolver } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.resolve';
import { ConfirmDeleteLinkComponent } from '@pages/domanda-accreditamento/components/confirm-delete-link/confirm-delete-link.component';
import { ConfirmExitComponent } from '@pages/domanda-accreditamento/components/confirm-exit/confirm-exit.component';

/*
  Accreditamento per utente BO
  Url di Riferimento: /tsddr/domanda-accreditamento
*/

@NgModule({
  declarations: [
    RicercaComponent,
    ListaComponent,
    ConfirmDeleteComponent,
    ConfirmDeleteLinkComponent,
    ConfirmExitComponent,
    EditComponent
  ],
  providers: [
    DomandaAccreditamentoGuards,
    EditDomandaAccreditamentoGuards,
    DomandaAccreditamentoResolver,
    DatePipe
  ],
  imports: [
    CommonModule,
    DomandaAccreditamentoRoutingModule,
    SharedComponentsModule,
    FormModule,
    TableModule,
    TranslateModule
  ]
})
export class DomandaAccreditamentoModule {}
