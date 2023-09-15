import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UtentiGuards } from '@pages/utenti/services/utenti.guards';
import { RicercaComponent } from '@pages/utenti/pages/ricerca/ricerca.component';
import { ListaComponent } from '@pages/utenti/pages/lista/lista.component';
import { InserimentoComponent } from '@pages/utenti/pages/inserimento/inserimento.component';
import { NuovoUtenteGuards } from '@pages/utenti/services/nuovo-utente.guards';
import { EditUtenteGuards } from '@pages/utenti/services/edit-utente.guards';
import { EditComponent } from '@pages/utenti/pages/edit/edit.component';
import { UtenteResolver } from '@pages/utenti/services/utente.resolve';

const routes: Routes = [
  {
    path: '',
    canActivate: [UtentiGuards],
    children: [
      {
        path: 'lista',
        component: ListaComponent,
        data: {
          breadcrumbs: [
            {
              label: 'UTENTI.RICERCA.TITLE',
              href: '/gestione-utenti-e-profili/gestione-utenti'
            },
            {
              label: 'UTENTI.LISTA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'UTENTI.RICERCA.TITLE'
        }
      },
      {
        path: 'inserimento',
        canActivate: [NuovoUtenteGuards],
        component: InserimentoComponent,
        data: {
          breadcrumbs: [
            {
              label: 'UTENTI.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'UTENTI.CREATE.TITLE'
        }
      },
      {
        path: ':id/edit',
        canActivate: [EditUtenteGuards],
        resolve: {
          utente: UtenteResolver
        },
        component: EditComponent,
        data: {
          breadcrumbs: [
            {
              label: 'UTENTI.RICERCA.TITLE',
              href: '/gestione-utenti-e-profili/gestione-utenti'
            },
            {
              label: 'UTENTI.LISTA.TITLE',
              href: '/gestione-utenti-e-profili/gestione-utenti/lista'
            },
            {
              label: 'UTENTI.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'UTENTI.EDIT.TITLE'
        }
      },
      {
        path: '',
        component: RicercaComponent,
        pathMatch: 'full',
        data: {
          breadcrumbs: [
            {
              label: 'UTENTI.RICERCA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'UTENTI.RICERCA.TITLE'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UtentiRoutingModule {}
