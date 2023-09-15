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
import { DichiarazioneService } from '@app/pages/dichiarazioni/services/dichiarazioni.service';

@Injectable()
export class DichiarazioneResolver implements Resolve<any> {
  constructor(
    private service: DichiarazioneService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> | Promise<any> | any {
    return this.service.getDichiarazione(+route.params.id).pipe(
      map((res: any) => {
        return res || {};
      }),
      catchError((err: HttpErrorResponse) => {
        this.router.navigate([
          '/dichiarazioni-annuali/lista'
        ]);
        return of(null);
      })
    );
  }
}
