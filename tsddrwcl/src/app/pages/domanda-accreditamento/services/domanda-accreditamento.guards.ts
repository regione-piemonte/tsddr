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
import { DomandaAccreditamentoACL } from '@pages/domanda-accreditamento/models/acl.model';
import { DomandaAccreditamentoService } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.service';

@Injectable()
export class DomandaAccreditamentoGuards implements CanActivate, CanActivateChild {
  constructor(
    private service: DomandaAccreditamentoService,
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
    return this.service.getAccreditamentoACL().pipe(
      map((acl: DomandaAccreditamentoACL) => {
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
