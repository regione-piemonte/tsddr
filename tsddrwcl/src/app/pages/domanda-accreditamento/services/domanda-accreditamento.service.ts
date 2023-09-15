import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { DomandaAccreditamentoACL } from '@pages/domanda-accreditamento/models/acl.model';
import { AsyncValidatorFn, FormGroup, ValidatorFn } from '@angular/forms';
import {
  csiCatchError,
  csiCatchErrorForValidators
} from '@core/operators/catch-error.operator';
import {
  Domanda,
  DomandaDeleteResponse,
  DomandaUpdateResponse
} from '@app/pages/domanda-accreditamento/models/domanda.model';
import { FunzionalitaProfiliResponse } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import {
  Gestore,
  GestoreLinkUpdateResponse
} from '@pages/domanda-accreditamento/models/gestore.model';
import { HttpParams } from '@angular/common/http';
import { untilDestroyed } from '@ngneat/until-destroy';
import { LoadingService } from '@app/theme/layouts/loading.services';

@Injectable({
  providedIn: 'root'
})
export class DomandaAccreditamentoService {
  private currentAcl$: BehaviorSubject<DomandaAccreditamentoACL> =
    new BehaviorSubject<DomandaAccreditamentoACL>(null);

  constructor(private apiClient: ApiClient, private loadingService: LoadingService) {}

  getAccreditamentoACL(): Observable<DomandaAccreditamentoACL> {
    return this.apiClient
      .request('getAccreditamentoACL')
      .pipe(tap((response: DomandaAccreditamentoACL) => this.currentAcl$.next(response)));
  }

  getStoredAcl(): Observable<DomandaAccreditamentoACL> {
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
  editValidator(domanda:Domanda): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {

      if(
        domanda.stato.id!=2 && domanda.stato.id!=3 &&
        domanda.stato.id.toString() == formgroup.controls['statoDomanda'].value ||
        formgroup.controls['statoDomanda'].value==''
      ){
        return { notChangedForm: true };
      }
      if(
        (domanda.stato.id==2 || domanda.stato.id==3) &&
        (
          domanda.notaLavorazione == formgroup.controls['notaLavorazione'].value ||
          !formgroup.controls['notaLavorazione'].dirty ||
          (formgroup.controls['notaLavorazione'].dirty &&  formgroup.controls['notaLavorazione'].value=='')
        )
      ){
        return { notChangedForm: true };
      }

      if(domanda.stato.id.toString()=='1' && formgroup.controls['statoDomanda'].value=="2"){
        if (!formgroup.value['profilo']){
          return { notChangedForm: true };
        }
      }

      return null;
    };
  }

  searchValidator(): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const searchInput = {};
      const keys = Object.keys(formgroup.value);
      keys.forEach((k) => {
        if (formgroup.value[k] && !Array.isArray(formgroup.value[k])) {
          searchInput[k] = formgroup.value[k];
        }
        if (formgroup.value[k] && Array.isArray(formgroup.value[k]) && formgroup.value[k].length>0) {
          searchInput[k] = formgroup.value[k];
        }
      });
      if (Object.keys(searchInput).length === 0) {
        return { empty: true };
      }
      return null;
    };
  }

  idDomandaNumericValidator(): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const v= formgroup.controls['idDomanda'].value as string;
      const NUMERIC_REGEXP = /^[0-9]*$/;
      if(v && v!='' && !NUMERIC_REGEXP.test(v)){
        return { iddomanda: true };
      }
      return null;
    };
  }

  dateValidator(): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const d1 = formgroup.controls['dataDomandaDal'].value as string;
      const d2 = formgroup.controls['dataDomandaAl'].value as string;
      if(d1 && d2 && ('' + d1).localeCompare(
        ''+d2
      )>0){
        return { dataDomandaAl: true };
      }
      return null;
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

  getComboProfiliAccreditamento(idDomanda) {
    return this.apiClient.request('getComboProfiliAccreditamento',null, null, { idDomanda });
  }

  getComboGestori() {
    return this.apiClient.request('getComboGestori');
  }

  getComboGestoriAccreditamento() {
    return this.apiClient.request('getComboGestoriAccreditamento');
  }

  getComboStati() {
    return this.apiClient.request('getComboStatiAccreditamento');
  }

  getComboAllStati() {
    return this.apiClient.request('getComboAllStatiAccreditamento');
  }

  getComboRichiedenti(){
    return this.apiClient.request('getComboRichiedentiAccreditamento');
  }

  getUtenti(search: Record<string, string>) {
    return this.apiClient.request('getUtenti', search).pipe(
      csiCatchError(),
      map((response: FunzionalitaProfiliResponse) => response.content)
    );
  }

  getDomande(search:any) {
    return this.apiClient.request('getDomandeAccreditamento',null, this._searchToHttpParams(search)).pipe(
      csiCatchError(),
      map((response: FunzionalitaProfiliResponse) => response.content)
    );
  }

  getParametriFiltroApplicati(search:any) {
    return this.apiClient.request('getDomandeAccreditamentoParametri',null, this._searchToHttpParams(search)).pipe(
      csiCatchError(),
      map((response: any) => response.content)
    );
  }
downloadReportDomandeAccreditamento(filter?:any){
  const params = new HttpParams({});
  this.loadingService.show()
  return this.apiClient
    .request<any>(
      'downloadReportAccreditamento',
    null,
       this._searchToHttpParams(filter),
     null
    )
    .pipe(csiCatchError())
    .subscribe((response:any) => {
      this.loadingService.hide()
     let type = response.content.filename.slice(
      response.content.filename.lastIndexOf('.') + 1
    );

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
  _searchToHttpParams(search:any):HttpParams{
    let httpParams = new HttpParams();
    for (let key in search) {
      if(Array.isArray(search[key])){
        search[key].forEach(i=>httpParams = httpParams.append(key, i.toString()))
      }else if(search[key]){
        httpParams = httpParams.append(key, search[key].toString());
      }
    };
    return httpParams;
  }
  //
  // getComboNuovaFunzionalita(idProfilo: number) {
  //   return this.apiClient.request('getComboNuovaFunzionalita', null, {
  //     idProfilo
  //   });
  // }
  //


  getDomanda(idDomanda: string): Observable<DomandaUpdateResponse> {
    return this.apiClient.request('getDomandaAccreditamento', null, null, { idDomanda });
  }

  editDomanda(domanda: Partial<Domanda>): Observable<DomandaUpdateResponse> {
    const id=domanda.idDomanda;
    return this.apiClient.request('editDomandaAccreditamento', domanda, null, { id });
  }

  deleteDomanda(domanda: Domanda): Observable<DomandaDeleteResponse> {
    return this.apiClient.request('deleteUtente', null, {
      domanda: domanda.idDomanda
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
