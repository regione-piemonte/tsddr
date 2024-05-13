import {
  ActivatedRouteSnapshot,
  Resolve,
  Router,
  RouterStateSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Injectable } from '@angular/core';
import { catchError, map } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from '@shared/notification/notification.service';
import { I18nService } from '@eng-ds/translate';
import { UtentiService } from '@pages/utenti/services/utenti.service';

@Injectable()
export class UtenteResolver implements Resolve<any> {
  constructor(
    private service: UtentiService,
    private notificationService: NotificationService,
    private i18n: I18nService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> | Promise<any> | any {
    return this.service.getUtente(route.params.id).pipe(
      map((res: any) => {
        console.log(res)
        return res || {};
      }),
      catchError((err: HttpErrorResponse) => {
        this.router.navigate([
          '/gestione-utenti-e-profili/gestione-utenti/lista'
        ]);

        return of(null);
      })
    );
  }
}
