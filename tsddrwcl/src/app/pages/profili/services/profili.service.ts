import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { ProfiliACL } from '@pages/profili/models/acl.model';
import {
  FunzionalitaProfili,
  FunzionalitaProfiliDeleteResponse,
  FunzionalitaProfiliResponse,
  FunzionalitaProfiliUpdateResponse
} from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { catchError, map, tap } from 'rxjs/operators';
import { Profilo, ProfiloResponse, ProfiloUpdateResponse, VerificaProfiloResponse } from '../models/profili.model';
import { SelectOption } from '@app/shared/form';

@Injectable({
  providedIn: 'root'
})
export class ProfiliService {
  private currentAcl$: BehaviorSubject<ProfiliACL> =
    new BehaviorSubject<ProfiliACL>(null);
  private comboTipiProfilo$: BehaviorSubject<SelectOption<number, string>[]> =
    new BehaviorSubject<SelectOption<number, string>[]>(null);

  constructor(private apiClient: ApiClient) {}

  getProfiliACL(): Observable<ProfiliACL> {
    return this.apiClient
      .request('getProfiliACL')
      .pipe(
        tap((response: ProfiliACL) =>
          this.currentAcl$.next(response)
        )
      );
  }

  getStoredAcl(): Observable<ProfiliACL> {
    return this.currentAcl$.asObservable();
  }

  getProfili(
  ): Observable<Profilo[]> {
    return this.apiClient
      .request('getProfili')
      .pipe(map((response: ProfiloResponse) => response.content));
  }

  getComboTipiProfilo():Observable<SelectOption<number, string>[]> {
    if(this.comboTipiProfilo$.value){
      return this.comboTipiProfilo$.asObservable();
    }
    return this.apiClient.request<SelectOption<number, string>[]>('getComboTipiProfilo').pipe(
      tap(response=>{this.comboTipiProfilo$.next(response)})
    );
  }

  createProfilo(
    profilo: Profilo
  ): Observable<ProfiloUpdateResponse> {
    return this.apiClient.request('createProfilo', profilo);
  }

  updateProfilo(
    profilo: Profilo
  ): Observable<ProfiloUpdateResponse> {
    return this.apiClient.request('updateProfilo', profilo);
  }

  deleteProfilo(
    profilo: Profilo
  ): Observable<FunzionalitaProfiliDeleteResponse> {
    return this.apiClient.request('deleteProfilo', null, {
      idProfilo: profilo.idProfilo
    });
  }
  verifyProfilo(
    profilo: Profilo
  ): Observable<VerificaProfiloResponse> {
    return this.apiClient.request('verifyProfilo', profilo).pipe(
      catchError((err) => {
        
        return of({errors:err.error.errors});
      }),
      map((result: any) => {
        return result; 
      }
      )
    );;
  }
}
