import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { TranslateModule } from '@eng-ds/translate';
import {
  ClickStopPropagationDirective,
  ColorStatusDirective
} from './directives';
import { FormModule } from './form';
import {
  CapitalizePipe,
  DeepObjectPipe,
  FileDimensionPipe,
  TimingPipe
} from './pipes';
import { DateConstructPipe } from './pipes/date.pipe';
import { TableModule } from './table';
import { SharedComponentsModule } from './components/shared-components.module';
import { NotificationModule } from './notification/notification.module';
import { AlertModule } from './alert/alert.module';
import { ModalModule } from './modal/modal.module';
import { RicercaComponent } from './prev-cons/ricerca/ricerca.component';
import { ListaComponent } from './prev-cons/lista/lista.component';
import { TabsComponent } from './prev-cons/tabs/tabs.component';
import { TabProcessoComponent } from './prev-cons/tabs/tab-processo/tab-processo.component';
import { TabRichiestaComponent } from './prev-cons/tabs/tab-richiesta/tab-richiesta.component';
import { TabSedeComponent } from './prev-cons/tabs/tab-sede/tab-sede.component';
import { GestoriModule } from '@app/pages/gestori/gestori.module';
import { ImpiantiModule } from '@app/pages/impianti/impianti.module';
import { ThemeModule } from '@app/theme/theme.module';
import { MrGuard } from './prev-cons/services/mr.guard';
import { DeleteMrComponent } from './prev-cons/modals/delete-mr/delete-mr.component';
import { PopupMrExistComponent } from './prev-cons/modals/popup-mr-exist/popup-mr-exist.component';
import { ConfirmMrExitComponent } from './prev-cons/modals/confirm-mr-exit/confirm-mr-exit.component';
import { TabRifiutiComponent } from './prev-cons/tabs/sotto-tabs/tab-rifiuti/tab-rifiuti.component';
import { TabDescrizioneComponent } from './prev-cons/tabs/sotto-tabs/tab-descrizione/tab-descrizione.component';
import { PrevConsEditComponent } from './prev-cons/prev-cons-edit/prev-cons-edit.component';
import { ConfirmLineaDeleteComponent } from './prev-cons/modals/confirm-linea-delete/confirm-linea-delete.component';
import { EditGuard } from './prev-cons/services/edit.guard';
import { NuovaMrGuard } from './prev-cons/services/nuovaMr.guard';
import { PrevConsDettDeleteComponent } from './prev-cons/modals/prev-cons-dett-delete/prev-cons-dett-delete.component';

const MODULES = [
  FormModule,
  TableModule,
  NotificationModule,
  AlertModule,
  ModalModule,
  SharedComponentsModule,
  GestoriModule,
  ImpiantiModule,
  ThemeModule
];

const DIRECTIVES = [ColorStatusDirective, ClickStopPropagationDirective];
const PIPES = [
  CapitalizePipe,
  TimingPipe,
  DateConstructPipe,
  DeepObjectPipe,
  FileDimensionPipe
];

const PREVCONS_SECTION = [
  RicercaComponent,
  ListaComponent,
  PrevConsEditComponent,
  TabsComponent,
  TabProcessoComponent,
  TabRichiestaComponent,
  TabSedeComponent,
  DeleteMrComponent,
  PopupMrExistComponent,
  ConfirmMrExitComponent,
  TabRifiutiComponent,
  TabDescrizioneComponent,
  ConfirmLineaDeleteComponent,
  PrevConsDettDeleteComponent
];

@NgModule({
  imports: [CommonModule, TranslateModule.forChild(), ...MODULES],
  exports: [CommonModule, TranslateModule, ...MODULES, ...DIRECTIVES, ...PIPES, ...PREVCONS_SECTION],
  providers: [MrGuard, EditGuard, NuovaMrGuard],
  declarations: [...DIRECTIVES, ...PIPES, ...PREVCONS_SECTION ],
  entryComponents: []
})
export class SharedModule {}
