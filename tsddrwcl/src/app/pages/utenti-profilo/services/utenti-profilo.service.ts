import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable } from 'rxjs';
import { UtentiProfiloACL } from '@pages/utenti-profilo/models/acl.model';
import {
  UtentiProfilo,
  UtentiProfiloDeleteResponse,
  UtentiProfiloResponse,
} from '@pages/utenti-profilo/models/utenti-profilo.model';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UtentiProfiloService {
  private currentAcl$: BehaviorSubject<UtentiProfiloACL> =
    new BehaviorSubject<UtentiProfiloACL>(null);

  constructor(private apiClient: ApiClient) {}

  getFunzionalitaProfiliACL(): Observable<UtentiProfiloACL> {
    return this.apiClient
      .request('getUtentiProfiloACL')
      .pipe(
        tap((response: UtentiProfiloACL) =>
          this.currentAcl$.next(response)
        )
      );
  }

  getStoredAcl(): Observable<UtentiProfiloACL> {
    return this.currentAcl$.asObservable();
  }

  getUtentiProfilo(
    idProfilo: string,
  ): Observable<UtentiProfilo[]> {
    const query = {
      idProfilo
    };
    return this.apiClient
      .request('getUtentiProfilo', null, query)
      .pipe(map((response: UtentiProfiloResponse) => response.content));
  }

  getComboProfili() {
    return this.apiClient.request('getComboProfili');
  }

  getComboUtentiProfiloNuovoUtente(idProfilo: number) {
    return this.apiClient.request('getComboUtentiProfiloNuovoUtente', null, {
      idProfilo
    });
  }

  associaUtenteProfilo(
    utentiProfilo: UtentiProfilo
  ): Observable<UtentiProfiloResponse> {
    return this.apiClient.request('associaUtenteProfilo', null, {
      idProfilo: utentiProfilo.idProfilo,
      idUtente: utentiProfilo.idUtente
    });
  }
 
  rimuoviUtenteProfilo(
    utentiProfilo: UtentiProfilo
  ): Observable<UtentiProfiloDeleteResponse> {
    return this.apiClient.request('rimuoviUtenteProfilo', null, {
      idProfilo: utentiProfilo.idProfilo,
      idUtente: utentiProfilo.idUtente
    });
  }
}
