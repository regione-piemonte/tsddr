import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from '@pages/profili/main/main.component';
import { ProfiliGuards } from '@pages/profili/services/profili.guards';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [ProfiliGuards],
    data: {
      breadcrumbs: [
        {
          label: 'PROFILI.TITLE',
          href: undefined
        }
      ],
      headerLabel: 'PROFILI.TITLE'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfiliRoutingModule {}
