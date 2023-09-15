import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { DichiarazioniRoutingModule } from '@pages/dichiarazioni/dichiarazioni-routing.module';
import { RicercaComponent } from './pages/ricerca/ricerca.component';
import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { TranslateModule } from '@eng-ds/translate';
import { FormModule } from '@shared/form';
import { ImpiantiGuards } from '@app/pages/dichiarazioni/services/dichiarazioni.guards';
import { ListaComponent } from './pages/lista/lista.component';
import { TableModule } from '@shared/table';
import { InserimentoComponent } from './pages/inserimento/inserimento.component';
import { ConfirmDeleteComponent } from '@pages/dichiarazioni/components/confirm-delete/confirm-delete.component';
import { NuovaDichiarazoneGuards } from '@app/pages/dichiarazioni/services/nuova-dichiarazione.guards';
import { EditComponent } from '@pages/dichiarazioni/pages/edit/edit.component';
import { EditDichiarazioneGuards } from '@app/pages/dichiarazioni/services/edit-dichiarazione.guards';
import { DichiarazioneResolver } from '@app/pages/dichiarazioni/services/dichiarazione.resolve';
import { ConfirmExitComponent } from '@pages/dichiarazioni/components/confirm-exit/confirm-exit.component';
import { ConfirmDeleteSoggettoComponent } from '@app/pages/dichiarazioni/components/confirm-delete-soggetto/confirm-delete-soggetto.component';
import { ConfirmEditComponent } from './components/confirm-edit/confirm-edit.component';
import { DichiarazioneTabsComponent, 
         DichiarazioneTabRifiutiComponent, 
         DichiarazioneTabVersamentiComponent,
         DichiarazioneTabSoggettiComponent, 
         DichiarazioneTabSedeComponent,
         DichiarazioneTabAnnotazioniComponent,
         PopupDichiarazioneExistComponent,
         ConfirmSaveComponent
} from './components/';
import { GestoriModule } from '../gestori/gestori.module';
import { ImpiantiModule } from '../impianti/impianti.module';
import { ConfirmDeleteRifiutoConferitoComponent } from './components/confirm-delete-rifiuto-conferito/confirm-delete-rifiuto-conferito.component';


/*
  Modulo per le dichiarazioni annuali
  Le pagine presentano una parte a tabs
  per cui sono state create delle componenti singole
  usate nelle diverse pagine della sezione.
  E' stato creato uno store DichiarazioneEditingStoreService
  che contiene in ram la dichiarazione annuale 
  corrente e lo stato di validita'
  dei diversi tab per abilitare o disabilitare
  i pulsanti di salvataggio 
  Attenzione: utilizza la edit impianto e gestore 
  per mostrare in tab appositi le due entita'
  in modalita' di visualizzazione

*/

@NgModule({
  declarations: [
    RicercaComponent,
    ListaComponent,
    InserimentoComponent,
    ConfirmDeleteComponent,
    ConfirmDeleteSoggettoComponent,
    ConfirmExitComponent,
    ConfirmEditComponent,
    ConfirmSaveComponent,
    EditComponent,
    DichiarazioneTabsComponent,
    DichiarazioneTabRifiutiComponent,
    DichiarazioneTabVersamentiComponent,
    DichiarazioneTabSoggettiComponent,
    DichiarazioneTabSedeComponent,
    DichiarazioneTabAnnotazioniComponent,
    DichiarazioneTabVersamentiComponent,
    PopupDichiarazioneExistComponent,
    ConfirmDeleteRifiutoConferitoComponent
  ],
  providers: [
    ImpiantiGuards,
    NuovaDichiarazoneGuards,
    EditDichiarazioneGuards,
    DichiarazioneResolver,
    DatePipe
  ],
  imports: [
    CommonModule,
    DichiarazioniRoutingModule,
    SharedComponentsModule,
    GestoriModule,
    ImpiantiModule,
    FormModule,
    TableModule,
    TranslateModule
  ]
})
export class DichiarazioniModule {}
