import { Component, OnDestroy, OnInit } from '@angular/core';
import { AutoUnsubscribe } from '@core/components';
import { SecurityService } from '@core/services';
import { I18nService } from '@eng-ds/translate';
import { UserInfo } from '@core/backend/model';
import { DropDownItem } from '@shared/models';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { MenuService } from '@app/core';
import { environment } from '@env/environment';
import { AreaPersonaleService } from '@app/pages/components/sevices/area-personale.service';
import { BroadcastEventService } from '@app/core/services/broadcast-event.service';

/*
  Header del sito che
  - chiama la logout
  - apre area peronale

*/

@UntilDestroy()
@Component({
  selector: 'app-header',
  styleUrls: ['./header.component.scss'],
  templateUrl: './header.component.html'
})
export class HeaderComponent
  extends AutoUnsubscribe
  implements OnInit, OnDestroy {
  dropdownMenu: DropDownItem[] = [];
  avatarContent = 'AP';
  user: UserInfo;
  descProfilo: string;

  constructor(
    private i18n: I18nService,
    private securityService: SecurityService,
    private menuService: MenuService,
    private broadcastEventService: BroadcastEventService
  ) {
    super();
  }

  ngOnInit(): void {
    this.securityService
    .getUserDescription()
    .pipe(untilDestroyed(this))
    .subscribe((_userDescription: string) => {
      this.descProfilo = _userDescription;
    });

    this.securityService
      .getUser()
      .pipe(untilDestroyed(this))
      .subscribe((user: UserInfo) => {
        if (this.securityService.isEditEnabledPersonalArea() && !!user) {
          this.dropdownMenu = [
            {
              label: ' ',
              onClick: this.onProfile.bind(this)
            },
            {
              icon: {
                name: 'it-plug',
                type: 'it',
                cssClass: 'custom-icon cursor-pointer mx-2 mt-1',
                fill: 'black',
                size: 'small'
              },
              label: this.i18n.translate('HEADER.DROPDOWN.LOGOUT'),
              onClick: this.onLogout.bind(this)
            }
          ];
          this.user = user;
          this.dropdownMenu[0].label = this.user.nome + ' ' + this.user.cognome;
        }else{
          this.dropdownMenu = [
            {
              icon: {
                name: 'it-plug',
                type: 'it',
                cssClass: 'custom-icon cursor-pointer mx-2 mt-1',
                fill: 'black',
                size: 'small'
              },
              label: this.i18n.translate('HEADER.DROPDOWN.LOGOUT'),
              onClick: this.onLogout.bind(this)
            }
          ];
        }

      });

    if (window.innerWidth <= 1024) {
      this.menuService.close();
    }
  }

  openMenu(): void {
    this.menuService.open();
  }

  onResize(e) {
    if (window.innerWidth <= 1024) {
      this.menuService.close();
    }
  }

  onLogout() {
    const ssoLogout = this.securityService.getSsoLogoutUrl();
    this.securityService.onLogout().subscribe(r=>{
      window.location =  ssoLogout as (string | Location) &
        Location;
    });
  }

  onProfile(){
    this.broadcastEventService.sendResult<any>({
      key:'open-area-personale'
    });
  }

  onLogin() {
    window.location = `${environment.backend.baseUrl}/auth` as (
      | string
      | Location
    ) &
      Location;
  }
}
