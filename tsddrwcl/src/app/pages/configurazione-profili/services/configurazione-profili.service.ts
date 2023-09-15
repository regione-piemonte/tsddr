import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable } from 'rxjs';
import { FunzionalitaProfiliACL } from '@pages/configurazione-profili/models/acl.model';
import {
  FunzionalitaProfili,
  FunzionalitaProfiliDeleteResponse,
  FunzionalitaProfiliResponse,
  FunzionalitaProfiliUpdateResponse
} from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ConfigurazioneProfiliService {
  private currentAcl$: BehaviorSubject<FunzionalitaProfiliACL> =
    new BehaviorSubject<FunzionalitaProfiliACL>(null);

  constructor(private apiClient: ApiClient) {}

  getFunzionalitaProfiliACL(): Observable<FunzionalitaProfiliACL> {
    return this.apiClient
      .request('getFunzionalitaProfiliACL')
      .pipe(
        tap((response: FunzionalitaProfiliACL) =>
          this.currentAcl$.next(response)
        )
      );
  }

  getStoredAcl(): Observable<FunzionalitaProfiliACL> {
    return this.currentAcl$.asObservable();
  }

  getFunzionalitaProfili(
    idProfilo: string,
    idFunzionalita?: string
  ): Observable<FunzionalitaProfili[]> {
    const query = {
      idProfilo
    };
    if (idFunzionalita) {
      query['idFunzionalita'] = idFunzionalita;
    }
    return this.apiClient
      .request('getFunzionalitaProfili', null, query)
      .pipe(map((response: FunzionalitaProfiliResponse) => response.content));
  }

  getComboProfili() {
    return this.apiClient.request('getComboProfili');
  }

  getComboFunzionalita(idProfilo: number) {
    return this.apiClient.request('getComboFunzionalita', null, { idProfilo });
  }

  getComboNuovaFunzionalita(idProfilo: number) {
    return this.apiClient.request('getComboNuovaFunzionalita', null, {
      idProfilo
    });
  }

  createFunzionalita(
    funzionalita: FunzionalitaProfili
  ): Observable<FunzionalitaProfiliUpdateResponse> {
    return this.apiClient.request('createFunzionalita', funzionalita);
  }

  updateFunzionalita(
    funzionalita: FunzionalitaProfili
  ): Observable<FunzionalitaProfiliUpdateResponse> {
    return this.apiClient.request('updateFunzionalita', funzionalita);
  }

  deleteFunzionalita(
    funzionalita: FunzionalitaProfili
  ): Observable<FunzionalitaProfiliDeleteResponse> {
    return this.apiClient.request('deleteFunzionalita', null, {
      idProfilo: funzionalita.idProfilo,
      idFunzionalita: funzionalita.idFunzione
    });
  }
}
