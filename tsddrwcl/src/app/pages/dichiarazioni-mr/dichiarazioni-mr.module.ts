import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedComponentsModule } from '@shared/components/shared-components.module';
import { ThemeModule } from '@theme/theme.module';
import { FormModule } from '@shared/form';
import { TableModule } from '@shared/table';
import { TranslateModule } from '@core/translate/public-api';

import { DichiarazioniMrRoutingModule } from './dichiarazioni-mr-routing.module';
import { GestoriModule } from '../gestori/gestori.module';
import { ImpiantiModule } from '../impianti/impianti.module';



@NgModule({
  declarations: [],
  providers: [],
  imports: [
    CommonModule,
    DichiarazioniMrRoutingModule,
    SharedComponentsModule,
    GestoriModule,
    ImpiantiModule,
    ThemeModule,
    FormModule,
    TableModule,
    TranslateModule.forChild()
  ]
})
export class DichiarazioniMrModule { }
