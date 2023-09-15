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
import { GestoriService } from '@pages/gestori/services/gestori.service';

@Injectable()
export class GestoreResolver implements Resolve<any> {
  constructor(
    private service: GestoriService,
    private notificationService: NotificationService,
    private i18n: I18nService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> | Promise<any> | any {
    return this.service.getGestore(route.params.id).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError((err: HttpErrorResponse) => {
        this.router.navigate(['/gestori/lista']);

        return of(null);
      })
    );
  }
}
