import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { UtentiACL } from '@pages/utenti/models/acl.model';
import { AsyncValidatorFn, FormGroup } from '@angular/forms';
import {
  csiCatchError,
  csiCatchErrorForValidators
} from '@core/operators/catch-error.operator';
import {
  Utente,
  UtenteDeleteResponse,
  UtenteUpdateResponse
} from '@pages/utenti/models/utente.model';
import { FunzionalitaProfiliResponse } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import {
  Gestore,
  GestoreLinkUpdateResponse
} from '@pages/utenti/models/gestore.model';

@Injectable({
  providedIn: 'root'
})
export class UtentiService {
  private currentAcl$: BehaviorSubject<UtentiACL> =
    new BehaviorSubject<UtentiACL>(null);

  constructor(private apiClient: ApiClient) {}

  getUtentiACL(): Observable<UtentiACL> {
    return this.apiClient
      .request('getUtentiACL')
      .pipe(tap((response: UtentiACL) => this.currentAcl$.next(response)));
  }

  getStoredAcl(): Observable<UtentiACL> {
    return this.currentAcl$.asObservable();
  }

  cfValidator(): AsyncValidatorFn {
    return (form: FormGroup): Observable<{ [key: string]: any } | null> => {
      const keys = Object.keys(form.value);
      if (
        keys.indexOf('codiceFiscale') < 0 ||
        !form.value['codiceFiscale'] ||
        form.value['codiceFiscale'] === ''
      ) {
        return of({});
      }
      return this.apiClient
        .request('validateCF', null, {
          codiceFiscale: form.value['codiceFiscale']
        })
        .pipe(
          csiCatchErrorForValidators(),
          map((result) => {
            if (Array.isArray(result)) {
              return { cf: result };
            }
          })
        );
    };
  }

  searchValidator(): AsyncValidatorFn {
    return (
      formgroup: FormGroup
    ): Observable<{ [key: string]: any } | null> => {
      const searchInput = {};
      const keys = Object.keys(formgroup.value);

      keys.forEach((k) => {
        if (formgroup.value[k]) {
          searchInput[k] = formgroup.value[k];
        }
      });

      if (Object.keys(searchInput).length === 0 && formgroup.untouched) {
        return of({ unfilled: true });
      }

      //if the request is empty
      if(JSON.stringify(searchInput)==='{}'){

        return of({ unfilled: true });
      }

      return this.apiClient.request('validateSearchUtenti', searchInput).pipe(
        csiCatchErrorForValidators(),
        map((result) => {
          if (Array.isArray(result)) {
            return result;
          }
        })
      );
    };
  }

  validateInsert(): AsyncValidatorFn {
    return (
      formgroup: FormGroup
    ): Observable<{ [key: string]: any } | null> => {
      const searchInput = {};
      const keys = Object.keys(formgroup.value);

      keys.forEach((k) => {
        if (formgroup.value[k]) {
          searchInput[k] = formgroup.value[k];
        }
      });

      if (Object.keys(searchInput).length === 0 && formgroup.untouched) {
        return of({ unfilled: true });
      }

      return this.apiClient.request('validateInsert', searchInput).pipe(
        csiCatchErrorForValidators(),
        map((result) => {
          if (Array.isArray(result)) {
            return result;
          }
        })
      );
    };
  }

  emailValidator(): AsyncValidatorFn {
    return (form: FormGroup): Observable<{ [key: string]: any } | null> => {
      const keys = Object.keys(form.value);
      if (
        keys.indexOf('mail') < 0 ||
        !form.value['mail'] ||
        form.value['mail'] === ''
      ) {
        return of({});
      }
      return this.apiClient
        .request('emailValidator', null, {
          email: form.value['mail']
        })
        .pipe(
          csiCatchErrorForValidators(),
          map((result) => {
            if (Array.isArray(result)) {
              return { email: result };
            }
          })
        );
    };
  }

  validateUser(): AsyncValidatorFn {
    return (
      formgroup: FormGroup
    ): Observable<{ [key: string]: any } | null> => {
      const searchInput = {};
      const keys = Object.keys(formgroup.value);

      keys.forEach((k) => {
        if (formgroup.value[k]) {
          searchInput[k] = formgroup.value[k];
        }
      });

      if (Object.keys(searchInput).length === 0 && formgroup.untouched) {
        return of({ unfilled: true });
      }

      return this.apiClient
        .request('validateUser', null, {
          codiceFiscale: formgroup.value['codiceFiscale']
        })
        .pipe(
          csiCatchErrorForValidators(),
          map((result) => {
            if (Array.isArray(result)) {
              return result;
            }
          })
        );
    };
  }

  getComboProfiliUtente(idUtente: number) {
    return this.apiClient.request('getComboProfiliUtente', null, { idUtente });
  }

  getGestoriUtente(idProfilo: number, idUtente: number) {
    return this.apiClient
      .request('getGestoriUtente', null, {
        idUtente,
        idProfilo
      })
      .pipe(
        csiCatchError(),
        map((response: FunzionalitaProfiliResponse) => response.content)
      );
  }

  getComboGestoriUtente(idProfilo: number, idUtente: number) {
    return this.apiClient.request('getComboGestoriUtente', null, {
      idUtente,
      idProfilo
    });
  }

  getComboProfili() {
    return this.apiClient.request('getComboProfili');
  }

  getComboGestori() {
    return this.apiClient.request('getComboGestori');
  }

  getUtenti(search: Record<string, string>) {
    return this.apiClient.request('getUtenti', search).pipe(
      csiCatchError(),
      map((response: FunzionalitaProfiliResponse) => response.content)
    );
  }

  //
  // getComboNuovaFunzionalita(idProfilo: number) {
  //   return this.apiClient.request('getComboNuovaFunzionalita', null, {
  //     idProfilo
  //   });
  // }
  //
  createUtente(utente: Utente): Observable<UtenteUpdateResponse> {
    return this.apiClient.request('createUtente', utente);
  }

  getUtente(id: string): Observable<UtenteUpdateResponse> {
    return this.apiClient.request('getUtente', null, { idUtente: id });
  }

  getParametriFiltro(search: Record<string, string>) {
    return this.apiClient.request('getParametriFiltro', search).pipe(
      csiCatchError(),
      map((response: any) => response.content)
    );
  }

  editUtente(utente: Utente): Observable<UtenteUpdateResponse> {
    return this.apiClient.request('editUtente', utente);
  }

  deleteUtente(utente: Utente): Observable<UtenteDeleteResponse> {
    return this.apiClient.request('deleteUtente', null, {
      idUtente: utente.idUtente
    });
  }

  createLinkUtenteProfiloGestore(data: Partial<Gestore>): Observable<any> {
    return this.apiClient.request('createLinkUtenteProfiloGestore', data);
  }

  deleteLinkUtenteProfiloGestore(
    idUtente: number,
    idGestore: number,
    idProfilo: number
  ): Observable<any> {
    return this.apiClient.request('deleteLinkUtenteProfiloGestore', null, {
      idUtente,
      idGestore,
      idProfilo
    });
  }

  updateLinkUtenteProfiloGestore(
    data: Partial<Gestore>
  ): Observable<GestoreLinkUpdateResponse> {
    return this.apiClient.request('updateLinkUtenteProfiloGestore', data);
  }
}
