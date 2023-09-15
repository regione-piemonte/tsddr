import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  Router,
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MrService } from './mr.service';
import { IGetImpiantoLinee, IPrevCons } from '../interfaces/prev-cons.interface';

@Injectable({
  providedIn: 'root'
})
export class PrevConsResolver implements Resolve<IPrevCons & {lineeImpianto?: IGetImpiantoLinee[]}> {
path: string = '';
  constructor(
    private mrService: MrService,
    private router: Router
  ) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<IPrevCons> {
    const idTipoDoc : number = +route.params.idTipoDoc;
    this.path = route.data.page.toString();
    if(this.path.includes('richiesta-mr')) {
      this.path = '/richieste-mr';
    } else {
      this.path = '/dichiarazioni-mr'
    }

    return this.mrService.getPrevConsResolver(+route.params.id, idTipoDoc).pipe(
      catchError((err: HttpErrorResponse) => {
        this.router.navigate([
          this.path,
          'lista'
        ]);
        return of(null);
      })
    );
  }
}
