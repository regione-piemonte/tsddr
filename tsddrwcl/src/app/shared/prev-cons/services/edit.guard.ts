import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { IProfiloACL } from '@app/core/models/acl.model';
import { I18nService } from '@app/core/translate/public-api';
import { NotificationService } from '@app/shared/notification/notification.service';
import { MrService } from '@app/shared/prev-cons/services/mr.service';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { ID_TIPO_DOC } from '../models/constants';

@Injectable({
  providedIn: 'root'
})
export class EditGuard implements CanActivate {
  constructor(
    private mrService: MrService,
    private notificationService: NotificationService,
    private router: Router,
    private i18n: I18nService,
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
  ): Observable<boolean> {
    return this._checkActivation(route);
  }

  private _checkActivation(route: ActivatedRouteSnapshot): Observable<boolean> {
    const path: string = route.data.page.toString().includes('richiesta-mr') ? 'richieste-mr' : 'dichiarazioni-mr';
    const idTipoDoc: number = path === 'richieste-mr' ? ID_TIPO_DOC.RICHIESTA : ID_TIPO_DOC.DICHIARAZIONE;
    return this.mrService.getPrevConsACL(idTipoDoc).pipe(
      map((acl: IProfiloACL) => {
        if (!acl.content.read) {
          this.notificationService.warning({
            title: this.i18n.translate('NOT_AUTHORIZED.NOTIFICATION.TITLE'),
            text: this.i18n.translate('NOT_AUTHORIZED.NOTIFICATION.TEXT')
          });

          this.router.navigate([
            path,
            'lista'
          ]);
        }
        return acl.content.read;
      }),
      take(1)
    );
  }
}
