import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditGestoreComponent } from '@app/pages/gestori/pages/edit-gestore/edit-gestore.component';
import { GestoriGuards } from '@pages/gestori/services/gestori.guards';
import { RicercaComponent } from '@pages/gestori/pages/ricerca/ricerca.component';
import { ListaComponent } from '@pages/gestori/pages/lista/lista.component';
import { NuovoGestoreGuards } from '@pages/gestori/services/nuovo-gestore.guards';
import { InserimentoComponent } from '@pages/gestori/pages/inserimento/inserimento.component';
import { EditGestoreGuards } from '@pages/gestori/services/edit-gestore.guards';
import { GestoreResolver } from '@pages/gestori/services/gestore.resolve';

const routes: Routes = [
  {
    path: '',
    canActivate: [GestoriGuards],
    children: [
      {
        path: 'lista',
        component: ListaComponent,
        data: {
          breadcrumbs: [
            {
              label: 'GESTORI.RICERCA.TITLE',
              href: '/gestori'
            },
            {
              label: 'GESTORI.LISTA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'GESTORI.RICERCA.TITLE'
        }
      },
      {
        path: 'inserimento',
        canActivate: [NuovoGestoreGuards],
        component: InserimentoComponent,
        data: {
          breadcrumbs: [
            {
              label: 'GESTORI.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'GESTORI.CREATE.TITLE'
        }
      },
      {
        path: ':id/edit',
        canActivate: [EditGestoreGuards],
        resolve: {
          gestore: GestoreResolver
        },
        component: EditGestoreComponent,
        data: {
          breadcrumbs: [
            {
              label: 'GESTORI.RICERCA.TITLE',
              href: '/gestori'
            },
            {
              label: 'GESTORI.LISTA.TITLE',
              href: '/gestori/lista'
            },
            {
              label: 'GESTORI.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'GESTORI.EDIT.TITLE'
        }
      },
      {
        path: '',
        component: RicercaComponent,
        pathMatch: 'full',
        data: {
          breadcrumbs: [
            {
              label: 'GESTORI.RICERCA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'GESTORI.RICERCA.TITLE'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestoriRoutingModule {}
