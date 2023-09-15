import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NuovaDomandaComponent } from '@pages/accreditamento-fo/pages/nuova-domanda/nuova-domanda.component';
import { GestioneComponent } from '@pages/accreditamento-fo/pages/gestione/gestione.component';
import { DomandaAccreditamentoResolve } from '@pages/accreditamento-fo/services/domanda-accreditamento.resolve';
import { EditDomandaComponent } from '@pages/accreditamento-fo/pages/edit-domanda/edit-domanda.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'nuova-domanda',
        component: NuovaDomandaComponent,
        data: {
          breadcrumbs: [
            {
              label: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'ACCREDITAMENTO_FO.NUOVA_DOMANDA.TITLE'
        }
      },
      {
        path: 'gestione-domande',
        component: GestioneComponent,
        data: {
          breadcrumbs: [
            {
              label: 'ACCREDITAMENTO_FO.GESTIONE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'ACCREDITAMENTO_FO.GESTIONE.TITLE'
        }
      },
      {
        path: 'gestione-domande/:id',
        resolve: {
          domanda: DomandaAccreditamentoResolve
        },
        component: EditDomandaComponent,
        data: {
          breadcrumbs: [
            {
              label: 'ACCREDITAMENTO_FO.GESTIONE.TITLE',
              href: '/accreditamento/gestione-domande'
            },
            {
              label: 'ACCREDITAMENTO_FO.GESTIONE.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'ACCREDITAMENTO_FO.GESTIONE.TITLE'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccreditamentoFoRoutingModule {}
