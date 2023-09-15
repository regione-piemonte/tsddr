import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RicercaComponent } from '@shared/prev-cons/ricerca/ricerca.component';
import { ListaComponent } from '@shared/prev-cons/lista/lista.component';
import { MrGuard } from '@shared/prev-cons/services/mr.guard';
import { PrevConsEditComponent } from '@app/shared/prev-cons/prev-cons-edit/prev-cons-edit.component';
import { PrevConsResolver } from '@app/shared/prev-cons/services/prev-cons.resolver';
import { EditGuard } from '@app/shared/prev-cons/services/edit.guard';
import { NuovaMrGuard } from '@app/shared/prev-cons/services/nuovaMr.guard';
import { RmrForDmrResolver } from '@app/shared/prev-cons/services/rmrForDmr.resolver';
import { ID_TIPO_DOC } from '@app/shared/prev-cons/models/constants';

const routes: Routes = [
  {
    path: '',
    data: {
      idTipoDoc: ID_TIPO_DOC.DICHIARAZIONE
    },
    canActivate: [MrGuard],
    children: [
      {
        path: '',
        component: RicercaComponent,
        pathMatch: 'full',
        data: {
          isAtti: false,
          page: 'dichiarazioni-mr',
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI_MR.RICERCA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI_MR.RICERCA.TITLE'
        }
      },
      {
        path: 'lista',
        component: ListaComponent,
        data: {
          page: 'dichiarazioni-mr',
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI_MR.RICERCA.TITLE',
              href: '/dichiarazioni-mr'
            },
            {
              label: 'DICHIARAZIONI_MR.LISTA.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI_MR.RICERCA.TITLE'
        }
      },
      {
        path: 'pre-inserimento',
        component: ListaComponent,
        data: {
          isAtti: false,
          page: 'pre-inserimento',
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI_MR.RICERCA.TITLE',
              href: '/dichiarazioni-mr'
            },
            {
              label: 'DICHIARAZIONI_MR.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI_MR.CREATE.TITLE'
        },
      },
      {
        path: 'inserimento',
        canActivate: [NuovaMrGuard],
        component: PrevConsEditComponent,
        data: {
          isAtti: false,
          page: 'dichiarazioni-mr/inserimento',
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI_MR.RICERCA.TITLE',
              href: '/dichiarazioni-mr'
            },
            {
              label: 'DICHIARAZIONI_MR.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI_MR.CREATE.TITLE'
        },
      },
      {
        path: 'inserimento/:id/:idTipoDoc',
        canActivate: [NuovaMrGuard],
        resolve: {
          richiestaToDichiarazione: RmrForDmrResolver
        },
        component: PrevConsEditComponent,
        data: {
          isAtti: false,
          page: 'dichiarazioni-mr/inserimento/id',
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI_MR.RICERCA.TITLE',
              href: '/dichiarazioni-mr'
            },
            {
              label: 'DICHIARAZIONI_MR.CREATE.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI_MR.CREATE.TITLE'
        },
      },
      {
        path: ':id/edit/:idTipoDoc',
        component: PrevConsEditComponent,
        canActivate: [EditGuard],
        resolve: {
          prevCons: PrevConsResolver,
        },
        data: {
          page: 'dichiarazioni-mr/edit',
          breadcrumbs: [
            {
              label: 'DICHIARAZIONI_MR.RICERCA.TITLE',
              href: '/dichiarazioni-mr'
            },
            {
              label: 'DICHIARAZIONI_MR.LISTA.TITLE',
              href: '/dichiarazioni-mr/lista'
            },
            {
              label: 'DICHIARAZIONI_MR.EDIT.TITLE',
              href: undefined
            }
          ],
          headerLabel: 'DICHIARAZIONI_MR.EDIT.TITLE'
        }
      }
    ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DichiarazioniMrRoutingModule { }
