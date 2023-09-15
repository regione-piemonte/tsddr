import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DomandaAccreditamentoGuards } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.guards';
import { RicercaComponent } from '@pages/domanda-accreditamento/pages/ricerca/ricerca.component';
import { ListaComponent } from '@pages/domanda-accreditamento/pages/lista/lista.component';
import { EditDomandaAccreditamentoGuards } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento-edit.guards';
import { EditComponent } from '@pages/domanda-accreditamento/pages/edit/edit.component';
import { DomandaAccreditamentoResolver } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.resolve';

const routes: Routes = [
  {
    path: '',
    canActivate: [DomandaAccreditamentoGuards],
    children: [
      {
        path: 'lista',
        component: ListaComponent,
        data: {
          breadcrumbs: [
            {
              label: 'ACCREDITAMENTO_BO.RICERCA.TITLE',
              href: '/domanda-accreditamento'
            },
            {
              label: 'ACCREDITAMENTO_BO.LISTA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'ACCREDITAMENTO_BO.RICERCA.TITLE'
        }
      },
      {
        path: ':id/edit',
        canActivate: [EditDomandaAccreditamentoGuards],
        resolve: {
          domanda: DomandaAccreditamentoResolver
        },
        component: EditComponent,
        data: {
          breadcrumbs: [
            {
              label: 'ACCREDITAMENTO_BO.RICERCA.TITLE',
              href: '/domanda-accreditamento'
            },
            {
              label: 'ACCREDITAMENTO_BO.LISTA.TITLE',
              href: '/domanda-accreditamento/lista'
            },
            {
              label: 'ACCREDITAMENTO_BO.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'ACCREDITAMENTO_BO.EDIT.TITLE'
        }
      },
      {
        path: '',
        component: RicercaComponent,
        pathMatch: 'full',
        data: {
          breadcrumbs: [
            {
              label: 'ACCREDITAMENTO_BO.RICERCA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'ACCREDITAMENTO_BO.RICERCA.TITLE'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DomandaAccreditamentoRoutingModule {}
