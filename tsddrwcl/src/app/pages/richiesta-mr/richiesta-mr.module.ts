import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { ThemeModule } from '@theme/theme.module';
import { FormModule } from '@shared/form';
import { TableModule } from '@shared/table';
import { TranslateModule } from '@core/translate/public-api';
import { GestoriModule } from '../gestori/gestori.module';
import { ImpiantiModule } from '../impianti/impianti.module';

import { RichiestaMrRoutingModule } from './richiesta-mr-routing.module';




@NgModule({
  declarations: [],
  providers: [
    DatePipe,
    //DichMRGuard,
    //NuovaDichiarazoneMrGuards,
  ],
  imports: [
    CommonModule,
    RichiestaMrRoutingModule,
    SharedComponentsModule,
    GestoriModule,
    ImpiantiModule,
    ThemeModule,
    FormModule,
    TableModule,
    TranslateModule.forChild()
  ]
})
export class RichiestaMrModule { }
