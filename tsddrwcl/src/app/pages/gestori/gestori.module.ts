import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { GestoriGuards } from '@pages/gestori/services/gestori.guards';
import { GestoriRoutingModule } from '@pages/gestori/gestori-routing.module';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { FormModule } from '@shared/form';
import { TableModule } from '@shared/table';
import { TranslateModule } from '@eng-ds/translate';
import { RicercaComponent } from './pages/ricerca/ricerca.component';
import { ListaComponent } from './pages/lista/lista.component';
import { InserimentoComponent } from './pages/inserimento/inserimento.component';
import { NuovoGestoreGuards } from '@pages/gestori/services/nuovo-gestore.guards';
import { EditGestoreGuards } from '@pages/gestori/services/edit-gestore.guards';
import { EditGestoreComponent } from './pages/edit-gestore/edit-gestore.component';
import { ConfirmExitComponent } from '@pages/gestori/components/confirm-exit/confirm-exit.component';
import { GestoreResolver } from '@pages/gestori/services/gestore.resolve';
import { ConfirmEditComponent } from '@pages/gestori/components/confirm-edit/confirm-edit.component';

@NgModule({
  declarations: [
    RicercaComponent,
    ListaComponent,
    InserimentoComponent,
    EditGestoreComponent,
    ConfirmExitComponent,
    ConfirmEditComponent
  ],
  providers: [
    GestoriGuards,
    GestoreResolver,
    NuovoGestoreGuards,
    EditGestoreGuards,
    DatePipe
  ],
  imports: [
    CommonModule,
    SharedComponentsModule,
    FormModule,
    TableModule,
    TranslateModule,
    GestoriRoutingModule
  ],
  exports:[
    EditGestoreComponent
  ]
})
export class GestoriModule {}
