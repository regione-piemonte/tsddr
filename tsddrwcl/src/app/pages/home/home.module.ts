import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeRoutingModule } from './home-routing.module';
import { ThemeModule } from '../../theme/theme.module';
import { TranslateModule } from '@eng-ds/translate';
import { HomeComponent } from './home.component';
import { SharedModule } from '../../shared/shared.module';
@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    ThemeModule,
    TranslateModule,
    HomeRoutingModule,
    SharedModule
  ]
})
export class HomeModule {
}
