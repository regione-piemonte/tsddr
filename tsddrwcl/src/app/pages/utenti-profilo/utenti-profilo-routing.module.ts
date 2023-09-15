import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from '@pages/utenti-profilo/main/main.component';
import { UtentiProfiloGuards } from '@pages/utenti-profilo/services/utenti-profilo.guards';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [UtentiProfiloGuards],
    data: {
      breadcrumbs: [
        {
          label: 'UTENTI_PROFILO.TITLE',
          href: undefined
        }
      ],
      headerLabel: 'UTENTI_PROFILO.TITLE'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UtentiProfiloRoutingModule {}
