import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from '@pages/configurazione-profili/main/main.component';
import { ConfigurazioneProfiliGuards } from '@pages/configurazione-profili/services/configurazione-profili.guards';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [ConfigurazioneProfiliGuards],
    data: {
      breadcrumbs: [
        {
          label: 'CONFIGURAZIONE_PROFILI.TITLE',
          href: undefined
        }
      ],
      headerLabel: 'CONFIGURAZIONE_PROFILI.TITLE'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfigurazioneProfiliRoutingModule {}
