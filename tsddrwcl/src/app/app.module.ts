import { registerLocaleData } from '@angular/common';
import it from '@angular/common/locales/it';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core';
import { ThemeModule } from './theme/theme.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthComponent } from './auth/auth.component';

registerLocaleData(it);
/*
  Modulo app
  - nel modulo pages sono presenti tutte le pagine del sito
  - nel modulo core si chiama la initUserInfo (chiamata al profilo BE) se non funziona la chiamata il sito non
  viene inizializzato correttamente
  
*/
@NgModule({
  declarations: [AppComponent, AuthComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule.forRoot(),
    ThemeModule.forRoot(),
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
