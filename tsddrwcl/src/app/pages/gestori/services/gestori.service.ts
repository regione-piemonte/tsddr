import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, forkJoin, Observable, of } from 'rxjs';
import { map, take, tap } from 'rxjs/operators';
import { UtentiACL } from '@pages/utenti/models/acl.model';
import { AsyncValidatorFn, FormGroup, ValidatorFn } from '@angular/forms';
import {
  csiCatchError,
  csiCatchErrorForValidators
} from '@core/operators/catch-error.operator';
import { FunzionalitaProfiliResponse } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import { Gestore } from '@pages/utenti/models/gestore.model';
import { GestoriACL } from '@pages/gestori/models/acl.model';
import { SelectOption } from '@shared/form';
import { UtilityService } from '@core/services';
import { NotificationService } from '@shared/notification/notification.service';
import { HttpParams } from '@angular/common/http';
import { LoadingService } from '@app/theme/layouts/loading.services';

@Injectable({
  providedIn: 'root'
})
export class GestoriService {
  private currentAcl$: BehaviorSubject<GestoriACL> =
    new BehaviorSubject<GestoriACL>(null);

  constructor(
    private apiClient: ApiClient,
    private notification: NotificationService,
    private utility: UtilityService, private loadingService: LoadingService
  ) {}

  getGestoriACL(): Observable<GestoriACL> {
    return this.apiClient
      .request('getGestoriACL')
      .pipe(tap((response: GestoriACL) => this.currentAcl$.next(response)));
  }

  getStoredAcl(): Observable<UtentiACL> {
    return this.currentAcl$.asObservable();
  }

  formSedeLegaleValidator(): ValidatorFn {
    return (formgroup: FormGroup): { [key: string]: any } | null => {
      const idNazione = formgroup.value['idNazione'] as string;
      const idSedime = formgroup.value['idSedime'] as string;
      const indirizzo = formgroup.value['indirizzo'] as string;
      const idProvincia = formgroup.value['idProvincia'] as string;
      const comune = formgroup.value['idComune'] as string;
      const cap = formgroup.value['cap'] as string;
      if(!idNazione
        && !idSedime
        ){
        return { formSedeLegaleSedime: true };
      }
      if(!idNazione
        && !indirizzo
        ){
        return { formSedeLegaleIndirizzo: true };
      }
      if(!idNazione
        && !idProvincia
        ){
        return { formSedeLegaleProvincia: true };
      }
      if(!idNazione
        && !comune
        ){
        return { formSedeLegaleComune: true };
      }
      if(!idNazione
        && !cap
        ){
        return { formSedeLegaleCap: true };
      }
      return null;
    };
  }

  dateValidator(): ValidatorFn {
    return (formgroup: FormGroup): { [key: string]: any } | null => {
      const d1 = formgroup.value['dataInizioValidita'] as string;
      const d2 = formgroup.value['dataFineValidita'] as string;
      if (d1 && d2 && ('' + d1).localeCompare('' + d2) > 0) {
        return { dataFineValidita: true };
      }
      return null;
    };
  }

  existValidator(): AsyncValidatorFn {
    return (
      formgroup: FormGroup
    ): Observable<{ [key: string]: any } | null> => {
      const input = {};
      const keys = Object.keys(formgroup.value);

      keys.forEach((k) => {
        if (formgroup.value[k]) {
          input[k] = formgroup.value[k];
        }
      });

      if (Object.keys(input).length === 0 && formgroup.untouched) {
        return of({ unfilled: true });
      }

      const obs = [
        this.apiClient.request('existGestore', null, null, {
          cf: formgroup.value['codFiscPartiva']
        }),
        this.utility.getMessage('E007').pipe(take(1))
      ];

      return forkJoin(obs).pipe(
        map((result: any[]) => {
          if (result[0].content) {
            this.notification.error({
              title: result[1].content.titoloMsg,
              text: result[1].content.testoMsg
            });
            return { cf: result[1] };
          }
        })
      );
    };
  }

  cfValidator(): AsyncValidatorFn {
    return (form: FormGroup): Observable<{ [key: string]: any } | null> => {
      const keys = Object.keys(form.value);
      if (
        keys.indexOf('codFiscPartiva') < 0 ||
        !form.value['codFiscPartiva'] ||
        form.value['codFiscPartiva'] === ''
      ) {
        return of({});
      }
      return this.apiClient
        .request('validateCF', null, {
          codiceFiscale: form.value['codFiscPartiva']
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

      if (Object.keys(searchInput).length === 0) {
        return of({ unfilled: true });
      }

      return of(null);
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
        keys.indexOf('email') < 0 ||
        !form.value['email'] ||
        form.value['email'] === ''
      ) {
        return of({});
      }
      return this.apiClient
        .request('emailValidator', null, {
          email: form.value['email']
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

  pecValidator(): AsyncValidatorFn {
    return (form: FormGroup): Observable<{ [key: string]: any } | null> => {
      const keys = Object.keys(form.value);
      if (
        keys.indexOf('pec') < 0 ||
        !form.value['pec'] ||
        form.value['pec'] === ''
      ) {
        return of({});
      }
      return this.apiClient
        .request('emailValidator', null, {
          email: form.value['pec']
        })
        .pipe(
          csiCatchErrorForValidators(),
          map((result) => {
            if (Array.isArray(result)) {
              return { pec: result };
            }
          })
        );
    };
  }

  getComboSedime(): Observable<SelectOption[]> {
    return this.apiClient.request('getComboSedime');
  }

  getComboNazioni(nazioniEstere='true'): Observable<SelectOption[]> {
    return this.apiClient.request('getComboNazioni',null,{nazioniEstere:nazioniEstere});
  }

  getComboNaturaGiuridica(): Observable<SelectOption[]> {
    return this.apiClient.request('getComboNaturaGiuridica');
  }

  getComboComune(idProvincia: string): Observable<SelectOption[]> {
    return this.apiClient.request('getComboComune', null, { idProvincia });
  }

  getComboProvincia(): Observable<SelectOption[]> {
    return this.apiClient.request('getComboProvincia');
  }

  getComboGestori(): Observable<SelectOption[]> {
    return this.apiClient.request('getComboGestori');
  }

  getGestori(search: Record<string, string>) {
    Object.keys(search).forEach((k) => {
      if (!search[k]) {
        delete search[k];
      }
    });
    return this.apiClient.request('getGestori', null, search).pipe(
      csiCatchError(),
      map((response: FunzionalitaProfiliResponse) => response.content)
    );
  }

  createGestore(gestore: Gestore): Observable<any> {
    return this.apiClient.request('createGestore', gestore);
  }

  getGestore(id: string): Observable<any> {
    return this.apiClient.request('getGestore', null, null, { id });
  }

  getRappresentanteLegale(cf: string): Observable<any> {
    return this.apiClient.request('getRappresentanteLegale', null, null, {
      cf
    });
  }

  getParametriFiltro(search: Record<string, string>) {
    const filtri = {};
    Object.keys(search).forEach((k) => {
      if (search[k]) {
        filtri[k] = search[k];
      }
    });

    return this.apiClient
      .request('getParametriFiltroGestori', null, filtri)
      .pipe(
        csiCatchError(),
        map((response: any) => response.content)
      );
  }

  editGestore(gestore: Gestore): Observable<any> {
    return this.apiClient.request('editGestore', gestore, null, {
      id: gestore.idGestore
    });
  }

  hasDomande(gestore: Gestore): Observable<any> {
    return this.apiClient.request('hasDomande', null, null, {
      id: gestore.idGestore
    });
  }

  hasImpianti(gestore: Gestore): Observable<any> {
    return this.apiClient.request('hasImpianti', null, null, {
      id: gestore.idGestore
    });
  }

  deleteGestore(gestore: Gestore): Observable<any> {
    return this.apiClient.request('deleteGestore', null, null, {
      id: gestore.idGestore
    });
  }

  downloadReportGestori(filter?:any){
    const params = new HttpParams({});
    this.loadingService.show()
    return this.apiClient
      .request<any>(
        'downloadReportGestori',
        {
          responseType: 'blob',
          observe: 'response'
        },
        filter,
       null
      )
      .pipe(csiCatchError())
      .subscribe((response:any) => {

       let type = response.content.filename.slice(
        response.content.filename.lastIndexOf('.') + 1
      );
      this.loadingService.hide()
      const blob = this.dataURItoBlob(response.content.file, type);
      const a = document.createElement('a');
      document.body.appendChild(a);
      const url = URL.createObjectURL(blob);
      a.href = url;
      a.download = response.content.filename;
      a.click();
      window.URL.revokeObjectURL(url);
      document.body.removeChild(a);
      });
  }
  dataURItoBlob(dataURI, type) {
    const byteString = window.atob(dataURI);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    console.log(byteString);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: `application/${{ type }}` });
    return blob;
  }
}
