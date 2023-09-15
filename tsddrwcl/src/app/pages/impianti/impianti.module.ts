import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ImpiantiRoutingModule } from '@pages/impianti/impianti-routing.module';
import { RicercaComponent } from './pages/ricerca/ricerca.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { TranslateModule } from '@eng-ds/translate';
import { FormModule } from '@shared/form';
import { ImpiantiGuards } from '@pages/impianti/services/impianti.guards';
import { ListaComponent } from './pages/lista/lista.component';
import { TableModule } from '@shared/table';
import { InserimentoComponent } from './pages/inserimento/inserimento.component';
import { ConfirmDeleteComponent } from '@pages/impianti/components/confirm-delete/confirm-delete.component';
import { NuovoImpiantoGuards } from '@pages/impianti/services/nuovo-impianto.guards';
import { EditImpiantoComponent } from '@app/pages/impianti/pages/edit-impianto/edit-impianto.component';
import { EditImpiantoGuards } from '@pages/impianti/services/edit-impianto.guards';
import { ImpiantoResolver } from '@pages/impianti/services/impianto.resolve';
import { ConfirmChangeGestoreComponent } from '@app/pages/impianti/components/confirm-change-gestore/confirm-change-gestore.component';
import { ConfirmExitComponent } from '@pages/impianti/components/confirm-exit/confirm-exit.component';
import { ConfirmDeleteLineaComponent } from '@pages/impianti/components/confirm-delete-linea/confirm-delete-linea.component';
import { ConfirmDeleteAttoComponent } from '@pages/impianti/components/confirm-delete-atto/confirm-delete-atto.component';
import { ConfirmEditComponent } from './components/confirm-edit/confirm-edit.component';
import { InfoImpiantoComponent } from './components/info-impianto/info-impianto.component';

@NgModule({
  declarations: [
    RicercaComponent,
    ListaComponent,
    InserimentoComponent,
    ConfirmDeleteComponent,
    ConfirmChangeGestoreComponent,
    ConfirmDeleteLineaComponent,
    ConfirmDeleteAttoComponent,
    ConfirmExitComponent,
    ConfirmEditComponent,
    EditImpiantoComponent,
    InfoImpiantoComponent
  ],
  providers: [
    ImpiantiGuards,
    NuovoImpiantoGuards,
    EditImpiantoGuards,
    ImpiantoResolver,
    DatePipe
  ],
  imports: [
    CommonModule,
    ImpiantiRoutingModule,
    SharedComponentsModule,
    FormModule,
    TableModule,
    TranslateModule
  ],
  exports:[
    EditImpiantoComponent
  ]
})
export class ImpiantiModule {}
