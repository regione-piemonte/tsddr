import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';

import { map, take } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { NotificationService } from '@shared/notification/notification.service';
import { I18nService } from '@eng-ds/translate';
import { IProfiloACL } from '@core/models/acl.model';
import { MrService } from './mr.service';
import { ID_TIPO_DOC } from '../models/constants';

@Injectable()
export class MrGuard implements CanActivate {
  constructor(
    private mrService: MrService,
    private notificationService: NotificationService,
    private router: Router,
    private i18n: I18nService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
  ): Observable<boolean> {
    return this._checkActivation(route);
  }

  private _checkActivation(route: ActivatedRouteSnapshot): Observable<boolean> {
    const idTipoDoc : number = +route.data.idTipoDoc;

    return this.mrService.getPrevConsACL(idTipoDoc).pipe(
      map((acl: IProfiloACL) => {
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
