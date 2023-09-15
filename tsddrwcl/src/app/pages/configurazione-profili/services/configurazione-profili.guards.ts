import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  RouterStateSnapshot
} from '@angular/router';
import { map, take } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { NotificationService } from '@app/shared/notification/notification.service';
import { I18nService } from '@eng-ds/translate';
import { ConfigurazioneProfiliService } from '@pages/configurazione-profili/services/configurazione-profili.service';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';

@Injectable()
export class ConfigurazioneProfiliGuards
  implements CanActivate, CanActivateChild
{
  constructor(
    private service: ConfigurazioneProfiliService,
    private notificationService: NotificationService,
    private router: Router,
    private i18n: I18nService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this._checkActivation(route, state);
  }

  canActivateChild(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this._checkActivation(route, state);
  }

  private _checkActivation(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.service.getFunzionalitaProfiliACL().pipe(
      map((acl: FunzionalitaProfiliACL) => {
        if (!acl.content.read) {
          this.notificationService.warning({
            title: this.i18n.translate('NOT_AUTHORIZED.NOTIFICATION.TITLE'),
            text: this.i18n.translate('NOT_AUTHORIZED.NOTIFICATION.TEXT')
          });

          this.router.navigate(['home']);
        }
        return acl.content.read;
      }),
      take(1)
    );
  }
}
