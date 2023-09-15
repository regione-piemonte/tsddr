import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  Router,
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';
import { MrService } from './mr.service';
import {
  IPrevCons,
  IPrevConsDettEntity,
  IPrevConsResponse
} from '../interfaces/prev-cons.interface';
import { SEZIONE } from '../models/constants';

/**
 * Prende la richiesta da associare alla nuova dichiarazione e prepara la base per la nuova dichiarazione
 */
@Injectable({
  providedIn: 'root'
})
export class RmrForDmrResolver implements Resolve<IPrevCons> {
  idCount = 0;

  constructor(private mrService: MrService, private router: Router) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<IPrevCons> {
    const idTipoDoc: number = +route.params.idTipoDoc;

    return this.mrService.getLineeRichiesta(+route.params.id).pipe(
      switchMap((lineeFromRichiesta) => {
        return this.mrService
          .getDichiarazione(+route.params.id, idTipoDoc)
          .pipe(
            map(({ content }: IPrevConsResponse) => {
              content.prevConsLinee = lineeFromRichiesta;
              content.prevConsLinee.forEach((linea, i, linee) => {
                let { prevConsDett = [] } = linea;
                linee[i].idPrevConsLinee = --this.idCount;
                linee[i].dettRii =
                  prevConsDett.filter(
                    (det) => det.sezione.idSezione === SEZIONE.ID_TAB_RII
                  ) ?? [];
                linee[i].dettMat =
                  prevConsDett.filter(
                    (det) => det.sezione.idSezione === SEZIONE.ID_TAB_MAT
                  ) ?? [];
                linee[i].dettRru =
                  prevConsDett.filter(
                    (det) => det.sezione.idSezione === SEZIONE.ID_TAB_RRU
                  ) ?? [];
                linee[i].dettRu =
                  prevConsDett.filter(
                    (det) => det.sezione.idSezione === SEZIONE.ID_TAB_RU
                  ) ?? [];
                linee[i].prevConsDett = [];
                //preparazione delle prevConsdett. ci devo aggiungere id, isRam e la proprietà quantita non valorizzata
               linee= this.prepPrevCons(linea,linee,i)
                // imposto i totali dei rifiuti a zero, perchè non devo considerare le quantità già inserite nella richiesta
                /*  linee[i].totRii = 0;
                        linee[i].totMat = 0;
                        linee[i].totRru = 0;
                        linee[i].totRu = 0;*/
                linee[i].isLegameRmr = true;
              });
              return content;
            })
          );
      }),
      catchError((err: HttpErrorResponse) => {
        this.router.navigate(['/richieste-mr', 'lista']);
        return of(null);
      })
    );
  }
  prepPrevCons(linea, linee, i) {
    if (linea.dettRii.length) {
      linee[i].dettRii = this._preparePrevConsDett(linea.dettRii);
    }
    if (linea.dettMat.length) {
      linee[i].dettMat = this._preparePrevConsDett(linea.dettMat);
    }
    if (linea.dettRru.length) {
      linee[i].dettRru = this._preparePrevConsDett(linea.dettRru);
    }
    if (linea.dettRu.length) {
      linee[i].dettRu = this._preparePrevConsDett(linea.dettRu);
    }
    return linee
  }
  private _preparePrevConsDett(
    dett: IPrevConsDettEntity[]
  ): IPrevConsDettEntity[] {
    let count = 0;
    return dett.map((dett) => ({
      ...dett,
      idPrevConsDett: ++count,
      isRam: true,
      quantita: dett.quantitaRichiesta ?? null
    }));
  }
}
