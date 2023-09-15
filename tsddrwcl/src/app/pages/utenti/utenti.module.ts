import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { UtentiRoutingModule } from '@pages/utenti/utenti-routing.module';
import { RicercaComponent } from './pages/ricerca/ricerca.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { TranslateModule } from '@eng-ds/translate';
import { FormModule } from '@shared/form';
import { UtentiGuards } from '@pages/utenti/services/utenti.guards';
import { ListaComponent } from './pages/lista/lista.component';
import { TableModule } from '@shared/table';
import { InserimentoComponent } from './pages/inserimento/inserimento.component';
import { ConfirmDeleteComponent } from '@pages/utenti/components/confirm-delete/confirm-delete.component';
import { NuovoUtenteGuards } from '@pages/utenti/services/nuovo-utente.guards';
import { EditComponent } from '@pages/utenti/pages/edit/edit.component';
import { EditUtenteGuards } from '@pages/utenti/services/edit-utente.guards';
import { UtenteResolver } from '@pages/utenti/services/utente.resolve';
import { AssociazioneProfiloUtenteGestoreComponent } from './components/associazione-profilo-utente-gestore/associazione-profilo-utente-gestore.component';
import { ConfirmDeleteLinkComponent } from '@pages/utenti/components/confirm-delete-link/confirm-delete-link.component';
import { ConfirmExitComponent } from '@pages/utenti/components/confirm-exit/confirm-exit.component';

@NgModule({
  declarations: [
    RicercaComponent,
    ListaComponent,
    InserimentoComponent,
    ConfirmDeleteComponent,
    ConfirmDeleteLinkComponent,
    ConfirmExitComponent,
    EditComponent,
    AssociazioneProfiloUtenteGestoreComponent
  ],
  providers: [
    UtentiGuards,
    NuovoUtenteGuards,
    EditUtenteGuards,
    UtenteResolver,
    DatePipe
  ],
  imports: [
    CommonModule,
    UtentiRoutingModule,
    SharedComponentsModule,
    FormModule,
    TableModule,
    TranslateModule
  ]
})
export class UtentiModule {}
