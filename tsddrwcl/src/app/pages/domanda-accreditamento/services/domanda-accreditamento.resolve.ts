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
import { DomandaAccreditamentoService } from '@app/pages/domanda-accreditamento/services/domanda-accreditamento.service';

@Injectable()
export class DomandaAccreditamentoResolver implements Resolve<any> {
  constructor(
    private service: DomandaAccreditamentoService,
    private notificationService: NotificationService,
    private i18n: I18nService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> | Promise<any> | any {
    return this.service.getDomanda(route.params.id).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError((err: HttpErrorResponse) => {
        this.router.navigate([
          '/domanda-accreditamento/lista'
        ]);

        return of(null);
      })
    );
  }
}
