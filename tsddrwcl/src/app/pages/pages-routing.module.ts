import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotAuthorizedComponent } from '@pages/not-authorized/not-authorized.component';
import { PagesComponent } from '@pages/pages.component';
import { NotFoundComponent } from './not-found/not-found.component';


// intero sito e' gestito a livello di routing di primo livello tramite queste Routes


const routes: Routes = [
  {
    path: '',
    component: PagesComponent,
    children: [
      {
        path: 'home',
        loadChildren: () =>
          import('./home/home.module').then((m) => m.HomeModule)
      },
      {
        path: 'gestione-utenti-e-profili/configurazione-profili',
        loadChildren: () =>
          import('./configurazione-profili/configurazione-profili.module').then(
            (m) => m.ConfigurazioneProfiliModule
          )
      },
      {
        path: 'gestione-utenti-e-profili/gestione-utenti',
        loadChildren: () =>
          import('./utenti/utenti.module').then((m) => m.UtentiModule)
      },
      {
        path: 'gestione-utenti-e-profili/gestione-profili',
        loadChildren: () =>
          import('./profili/profili.module').then((m) => m.ProfiliModule)
      },
      {
        path: 'gestione-utenti-e-profili/associa-utenti-a-profili',
        loadChildren: () =>
          import('./utenti-profilo/utenti-profili.module').then(
            (m) => m.UtentiProfiloModule
          )
      },
      {
        path: 'impianti',
        loadChildren: () =>
          import('./impianti/impianti.module').then(
            (m) => m.ImpiantiModule
          )
      },
      {
        path: 'accreditamento',
        loadChildren: () =>
          import('./accreditamento-fo/accreditamento-fo.module').then(
            (m) => m.AccreditamentoFoModule
          )
      },
      {
        path: 'gestori',
        loadChildren: () =>
          import('./gestori/gestori.module').then((m) => m.GestoriModule)
      },
      {
        path: 'domanda-accreditamento',
        loadChildren: () =>
          import('./domanda-accreditamento/domanda-accreditamento.module').then(
            (m) => m.DomandaAccreditamentoModule
          )
      },
      {
        path: 'dichiarazioni-annuali',
        loadChildren: () =>
          import('./dichiarazioni/dichiarazioni.module').then(
            (m) => m.DichiarazioniModule
          )
      },
      { 
        path: 'dichiarazioni-mr',
        loadChildren: () => 
          import('./dichiarazioni-mr/dichiarazioni-mr.module').then(
            (m) => m.DichiarazioniMrModule
          )
      },
      { 
        path: 'richieste-mr',
        loadChildren: () => 
          import('./richiesta-mr/richiesta-mr.module').then(
            (m) => m.RichiestaMrModule
          )
      },
      {
        path: 'not-authorized',
        component: NotAuthorizedComponent
      },
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      {
        path: '**',
        component: NotFoundComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule {}
