import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { IProfiloACL } from '@app/core/models/acl.model';
import { NotificationService } from '@app/shared/notification/notification.service';
import { MrService } from '@app/shared/prev-cons/services/mr.service';
import { I18nService } from '@eng-ds/translate';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { ID_TIPO_DOC } from '../models/constants';

@Injectable({
  providedIn: 'root'
})
export class NuovaMrGuard implements CanActivate {
  constructor(
    private service: MrService,
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

  private _checkActivation(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    const path: string = route.data.page.toString().includes('richiesta-mr') ? 'richieste-mr' : 'dichiarazioni-mr';
    const idTipoDoc: number = path === 'richieste-mr' ? ID_TIPO_DOC.RICHIESTA : ID_TIPO_DOC.DICHIARAZIONE;
    return this.service.getPrevConsACL(idTipoDoc).pipe(
      map((acl: IProfiloACL) => {
        if (!acl.content.insert) {
          this.notificationService.warning({
            title: this.i18n.translate('NOT_AUTHORIZED.NOTIFICATION.TITLE'),
            text: this.i18n.translate('NOT_AUTHORIZED.NOTIFICATION.TEXT')
          });

          this.router.navigate([path]);
        }
        return acl.content.insert;
      }),
      take(1)
    );
  }
}
