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
import { AccreditamentoFoService } from '@pages/accreditamento-fo/services/accreditamento-fo.service';

@Injectable()
export class DomandaAccreditamentoResolve implements Resolve<any> {
  constructor(
    private service: AccreditamentoFoService,
    private notificationService: NotificationService,
    private i18n: I18nService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> | Promise<any> | any {
    return this.service.getDomandaAccreditamento(route.params.id).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError((err: HttpErrorResponse) => {
        this.router.navigate(['/accreditamento/gestione-domande']);

        return of(null);
      })
    );
  }
}
