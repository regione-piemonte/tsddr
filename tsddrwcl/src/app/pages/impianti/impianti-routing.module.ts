import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RicercaComponent } from '@pages/impianti/pages/ricerca/ricerca.component';
import { ListaComponent } from '@pages/impianti/pages/lista/lista.component';
import { InserimentoComponent } from '@pages/impianti/pages/inserimento/inserimento.component';
import { EditImpiantoComponent } from '@app/pages/impianti/pages/edit-impianto/edit-impianto.component';
import { ImpiantiGuards } from './services/impianti.guards';
import { NuovoImpiantoGuards } from './services/nuovo-impianto.guards';
import { EditImpiantoGuards } from './services/edit-impianto.guards';
import { ImpiantoResolver } from './services/impianto.resolve';

const routes: Routes = [
  {
    path: '',
    canActivate: [ImpiantiGuards],
    children: [
      {
        path: 'lista',
        component: ListaComponent,
        data: {
          breadcrumbs: [
            {
              label: 'IMPIANTI.RICERCA.TITLE',
              href: '/impianti'
            },
            {
              label: 'IMPIANTI.LISTA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'IMPIANTI.RICERCA.TITLE'
        }
      },
      {
        path: 'inserimento',
        canActivate: [NuovoImpiantoGuards],
        component: InserimentoComponent,
        data: {
          isAtti: false,
          breadcrumbs: [
            {
              label: 'IMPIANTI.RICERCA.TITLE',
              href: '/impianti'
            },
            {
              label: 'IMPIANTI.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'IMPIANTI.CREATE.TITLE'
        }
      },
      {
        path: 'inserimento/atti',
        canActivate: [NuovoImpiantoGuards],
        component: InserimentoComponent,
        data: {
          isAtti: true,
          breadcrumbs: [
            {
              label: 'IMPIANTI.RICERCA.TITLE',
              href: '/impianti'
            },
            {
              label: 'IMPIANTI.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'IMPIANTI.CREATE.TITLE'
        }
      },
      {
        path: ':id/edit',
        canActivate: [EditImpiantoGuards],
        resolve: {
          impianto: ImpiantoResolver
        },
        component: EditImpiantoComponent,
        data: {
          isAtti: false,
          breadcrumbs: [
            {
              label: 'IMPIANTI.RICERCA.TITLE',
              href: '/impianti'
            },
            {
              label: 'IMPIANTI.LISTA.TITLE',
              href: '/impianti/lista'
            },
            {
              label: 'IMPIANTI.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'IMPIANTI.EDIT.TITLE'
        }
      },
      {
        path: ':id/edit/atti',
        canActivate: [EditImpiantoGuards],
        resolve: {
          impianto: ImpiantoResolver
        },
        component: EditImpiantoComponent,
        data: {
          isAtti: true,
          breadcrumbs: [
            {
              label: 'IMPIANTI.RICERCA.TITLE',
              href: '/impianti'
            },
            {
              label: 'IMPIANTI.LISTA.TITLE',
              href: '/impianti/lista'
            },
            {
              label: 'IMPIANTI.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'IMPIANTI.EDIT.TITLE'
        }
      },
      {
        path: '',
        component: RicercaComponent,
        pathMatch: 'full',
        data: {
          breadcrumbs: [
            {
              label: 'IMPIANTI.RICERCA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'IMPIANTI.RICERCA.TITLE'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImpiantiRoutingModule {}
