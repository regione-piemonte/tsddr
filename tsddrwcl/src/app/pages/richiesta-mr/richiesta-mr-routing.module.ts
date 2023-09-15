import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaComponent } from '@shared/prev-cons/lista/lista.component';
import { RicercaComponent } from '@shared/prev-cons/ricerca/ricerca.component';
import { MrGuard } from '@shared/prev-cons/services/mr.guard';
import { EditGuard } from '@app/shared/prev-cons/services/edit.guard';
import { PrevConsEditComponent } from '@app/shared/prev-cons/prev-cons-edit/prev-cons-edit.component';
import { PrevConsResolver } from '@app/shared/prev-cons/services/prev-cons.resolver';
import { NuovaMrGuard } from '@app/shared/prev-cons/services/nuovaMr.guard';
import { ID_TIPO_DOC } from '@app/shared/prev-cons/models/constants';


const routes: Routes = [
    {
      path: '',
      data: {
        idTipoDoc: ID_TIPO_DOC.RICHIESTA
      },
      canActivate: [MrGuard],
      children: [
        {
          path: '',
          component: RicercaComponent,
          pathMatch: 'full',
          data: {
            isAtti: false,
            page: 'richiesta-mr',
            breadcrumbs: [
              {
                label: 'RICHIESTA_MR.RICERCA.TITLE',
                href: undefined
              }
            ],
            headerLabel: 'RICHIESTA_MR.RICERCA.TITLE'
          }
        },
        {
          path: 'lista',
          component: ListaComponent,
          data: {
            page: 'richiesta-mr',
            breadcrumbs: [
              {
                label: 'RICHIESTA_MR.RICERCA.TITLE',
                href: '/richieste-mr'
              },
              {
                label: 'RICHIESTA_MR.LISTA.TITLE',
                href: undefined
              }
            ],
            headerLabel: 'RICHIESTA_MR.RICERCA.TITLE'
          }
        },
        {
          path: 'inserimento',
          canActivate: [NuovaMrGuard],
          component: PrevConsEditComponent,
          data: {
            isAtti: false,
            page: 'richiesta-mr/inserimento',
            breadcrumbs: [
              {
                label: 'RICHIESTA_MR.RICERCA.TITLE',
                href: '/richieste-mr'
              },
              {
                label: 'RICHIESTA_MR.CREATE.TITLE',
                href: undefined
              }
            ],
            headerLabel: 'RICHIESTA_MR.CREATE.TITLE'
          },
        },
        {
          path: ':id/edit/:idTipoDoc',
          component: PrevConsEditComponent,
          canActivate: [EditGuard],
          resolve: {
            prevCons: PrevConsResolver
          },
          data: {
            page: 'richiesta-mr/edit',
            breadcrumbs: [
              {
                label: 'RICHIESTA_MR.RICERCA.TITLE',
                href: '/richieste-mr'
              },
              {
                label: 'RICHIESTA_MR.LISTA.TITLE',
                href: '/richieste-mr/lista'
              },
              {
                label: 'RICHIESTA_MR.EDIT.TITLE',
                href: undefined
              }
            ],
            headerLabel: 'RICHIESTA_MR.EDIT.TITLE'
          }
        }
      ]
     }
  ];

  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class RichiestaMrRoutingModule { }
