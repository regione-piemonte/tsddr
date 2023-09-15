import { NgModule } from '@angular/core';
import { TranslateModule } from '@eng-ds/translate';
import { ThemeModule } from '../theme/theme.module';
import { PagesMenuService } from './pages-menu.service';
import { PagesComponent } from './pages.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { SharedModule } from '../shared/shared.module';
import { NotAuthorizedComponent } from '@pages/not-authorized/not-authorized.component';
import { PagesRoutingModule } from './pages-routing.module';
import { AreaPersonaleModalComponent } from './components/area-personale-modal/area-personale-modal.component';
import { AreaPersonaleFormHelperService } from './components/helper/area-personale-form-helper.service';


/*
  Tutti i moduli contenuti in questa parte di pages 
  presentano una struttura analoga con:
  - model 
    modelli dei dati
  - components
    componenti che non sono delle pagine a se, per esempio popup o pezzi riutilizzare in diverse pagine
  - pages 
    componenti che sono delle pagine a se tipicamente
    lista,ricerca,inserimento,edit la parte di edit e' utilizzata anche per la view del dettaglio
  - services
    A) servizi che contengono chiamate api e validatori
    B) guardie per accesso alle diverse pagine
    C) store delle acl (chiamata BE presente in tutte le seziono)
    D) Resolver per recuro singola entita' dal BE

*/


@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    SharedModule,
    TranslateModule.forChild()
  ],
  declarations: [PagesComponent, NotFoundComponent, NotAuthorizedComponent, AreaPersonaleModalComponent],
  providers: [PagesMenuService,AreaPersonaleFormHelperService]
})
export class PagesModule {
}