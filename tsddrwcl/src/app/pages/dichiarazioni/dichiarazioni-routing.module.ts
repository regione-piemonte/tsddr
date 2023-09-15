import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RicercaComponent } from '@pages/dichiarazioni/pages/ricerca/ricerca.component';
import { ListaComponent } from '@pages/dichiarazioni/pages/lista/lista.component';
import { InserimentoComponent } from '@pages/dichiarazioni/pages/inserimento/inserimento.component';
import { EditComponent } from '@pages/dichiarazioni/pages/edit/edit.component';
import { ImpiantiGuards } from './services/dichiarazioni.guards';
import { NuovaDichiarazoneGuards } from './services/nuova-dichiarazione.guards';
import { EditDichiarazioneGuards } from './services/edit-dichiarazione.guards';
import { DichiarazioneResolver } from './services/dichiarazione.resolve';

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
              label: 'DICHIARAZIONI.RICERCA.TITLE',
              href: '/dichiarazioni-annuali'
            },
            {
              label: 'DICHIARAZIONI.LISTA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI.RICERCA.TITLE'
        }
      },
      {
        path: 'inserimento',
        canActivate: [NuovaDichiarazoneGuards],
        component: InserimentoComponent,
        data: {
          isAtti: false,
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI.RICERCA.TITLE',
              href: '/dichiarazioni-annuali'
            },
            {
              label: 'DICHIARAZIONI.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI.CREATE.TITLE'
        }
      },
      {
        path: 'inserimento/:tab',
        canActivate: [NuovaDichiarazoneGuards],
        component: InserimentoComponent,
        data: {
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI.RICERCA.TITLE',
              href: '/dichiarazioni-annuali'
            },
            {
              label: 'DICHIARAZIONI.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI.CREATE.TITLE'
        }
      },
      {
        path: ':id/edit',
        canActivate: [EditDichiarazioneGuards],
        resolve: {
          dichiarazione: DichiarazioneResolver
        },
        component: EditComponent,
        data: {
          isAtti: false,
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI.RICERCA.TITLE',
              href: '/dichiarazioni-annuali'
            },
            {
              label: 'DICHIARAZIONI.LISTA.TITLE',
              href: '/dichiarazioni-annuali/lista'
            },
            {
              label: 'DICHIARAZIONI.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI.EDIT.TITLE'
        }
      },
      {
        path: ':id/edit/:tab',
        canActivate: [EditDichiarazioneGuards],
        resolve: {
          dichiarazione: DichiarazioneResolver
        },
        component: EditComponent,
        data: {
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI.RICERCA.TITLE',
              href: '/dichiarazioni-annuali'
            },
            {
              label: 'DICHIARAZIONI.LISTA.TITLE',
              href: '/dichiarazioni-annuali/lista'
            },
            {
              label: 'DICHIARAZIONI.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI.EDIT.TITLE'
        }
      },
      {
        path: '',
        component: RicercaComponent,
        pathMatch: 'full',
        data: {
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI.RICERCA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI.RICERCA.TITLE'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DichiarazioniRoutingModule {}
