import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { ImpiantiACL } from '@pages/impianti/models/acl.model';
import { AsyncValidatorFn, FormGroup, ValidatorFn } from '@angular/forms';
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
import { SelectOption } from '@app/shared/form';
import { HttpParams } from '@angular/common/http';
import { Impianto, ImpiantoDeleteResponse, ImpiantoUpdateResponse } from '../models/impianto.model';
import { Linea, LineaExtended, Sottolinea } from '../models/linea.model';
import { Atto } from '../models/atto.model';
import { LoadingService } from '@app/theme/layouts/loading.services';

@Injectable({
  providedIn: 'root'
})
export class ImpiantiService {
  private currentAcl$: BehaviorSubject<ImpiantiACL> =
    new BehaviorSubject<ImpiantiACL>(null);
  private currentComuni:any;

  constructor(private apiClient: ApiClient, private loadingService: LoadingService) {}

  getImpiantiACL(): Observable<ImpiantiACL> {
    return this.apiClient
      .request('getImpiantiACL')
      .pipe(tap((response: ImpiantiACL) => this.currentAcl$.next(response)));
  }

  getStoredAcl(): Observable<ImpiantiACL> {
    return this.currentAcl$.asObservable();
  }

  getComboProfiliUtente(idUtente: number) {
    return this.apiClient.request('getComboProfiliUtente', null, { idUtente });
  }

  getComboProvince() {
    return this.apiClient.request('getComboProvince');
  }

 checkDeleteImpianti(idImpianto: number) {
    return this.apiClient.request('checkDeleteImpianto',null,null,{idImpianto})

  }

  getComboComuni(idProvincia: number) {
    return this.apiClient.request('getComboComuni',null,{idProvincia:idProvincia})
    .pipe(tap((response: any) => {this.currentComuni=response;}));
  }

  getCap(idComune: number) {
    if(this.currentComuni && this.currentComuni.content &&
       this.currentComuni.content.find(i=>i.id==idComune)){
      return this.currentComuni.content.find(i=>i.id==idComune).additionalValue;
    }
    return '';
  }

  getComboImpiantiTipi() {
    return this.apiClient.request('getComboImpiantiTipi');
  }

  getComboImpiantiStati() {
    return this.apiClient.request('getComboImpiantiStati');
  }

  getComboSedimi() {
    return this.apiClient.request('getComboSedimi');
  }

  getComboLinee():Observable<SelectOption<string, string>[]> {
    return this.apiClient.request<SelectOption<string, string>[]>('getComboLinee').pipe(
      map((response: any) => {
        let selectOptions=[];
        response.content.forEach(element => {
          selectOptions.push(
            {
              id: element.id+((element.additionalValue)?'_'+element.additionalValue:''),
              name: element.value,
              value: element.value
            } as SelectOption<string, string>
          )
        });
        response.content = selectOptions;
        return response;
      })
    );
  }
  getComboLineeImpianto(idImpianto:number):Observable<SelectOption<string, string>[]> {
    return this.apiClient.request<SelectOption<string, string>[]>('getComboImpiantiLinee',null,null,{ idImpianto: idImpianto }).pipe(
      map((response: any) => {
        let selectOptions=[];
        response.content.forEach(element => {
          selectOptions.push(
            {
              id: element.id+((element.additionalValue)?'_'+element.additionalValue:''),
              name: element.value,
              value: element.value
            } as SelectOption<string, string>
          )
        });
        response.content = selectOptions;
        return response;
      })
    );
  }

  getComboAtti():Observable<SelectOption<string, string>[]>{
    return this.apiClient.request<SelectOption<string, string>[]>('getComboImpiantiAtti');
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

  getImpianti(search: any) {
    return this.apiClient.request('getImpianti', null , this._searchToHttpParams(search)).pipe(
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
  createImpianto(impianto: any): Observable<UtenteUpdateResponse> {
    return this.apiClient.request('createImpianto', impianto);
  }
  createLineaImpianto(lineaExtended:LineaExtended,idImpianto:number){
    let linea:Linea = {
      dataInizioValidita : lineaExtended.dataInizioValidita,
      dataFineValidita : lineaExtended.dataFineValidita
    }
    return this.apiClient.request('createImpiantoLinea', linea, null,{ idImpianto: idImpianto, idLinea:lineaExtended.idLinea });
  }
  createSottoLineaImpianto(lineaExtended:LineaExtended,idImpianto:number){
    let sottolinea:Sottolinea = {
      dataInizioValidita : lineaExtended.dataInizioValidita,
      dataFineValidita : lineaExtended.dataFineValidita
    }
    return this.apiClient.request('createImpiantoSottolinea', sottolinea, null,{ idImpianto: idImpianto , idLinea:lineaExtended.idLineaFiglia});
  }

  getImpianto(id: string): Observable<ImpiantoUpdateResponse> {
    return this.apiClient.request('getImpianto', null, null,{ idImpianto: id });
  }

  getImpiantoLinee(id: string): Observable<LineaExtended[]>{
    return this.apiClient.request('getImpiantoLinee', null, null,{ idImpianto: id }).pipe(map((response: any) => response.content));
  }

  getImpiantoAtti(id: string): Observable<LineaExtended[]>{
    return this.apiClient.request('getImpiantoAtti', null, null,{ idImpianto: id }).pipe(map((response: any) => response.content));
  }

  createImpiantoAtto(atto:Atto,idImpianto:number){
    return this.apiClient.request('createImpiantoAtto', atto, null,{ idImpianto: idImpianto});
  }
  editImpiantoAtto(atto:Atto,idImpianto:number){
    return this.apiClient.request('editImpiantoAtto', atto, null,{ idImpianto: idImpianto, idAtto:atto.idAtto });
  }
  deleteImpiantoAtto(idImpianto:number,idAtto:number){
    return this.apiClient.request('deleteImpiantoAtto', null, null,{ idImpianto: idImpianto, idAtto:idAtto });
  }

  getParametriFiltro(search: any) {
    return this.apiClient.request('getImpiantiParametri', null, this._searchToHttpParams(search)).pipe(
      csiCatchError(),
      map((response: any) => response.content)
    );
  }

  editImpianto(impianto: Impianto): Observable<ImpiantoUpdateResponse> {
    return this.apiClient.request('editImpianto', impianto, null , { idImpianto: impianto.idImpianto });
  }

  deleteImpianto(impianto: Impianto): Observable<ImpiantoDeleteResponse> {
    return this.apiClient.request('deleteImpianto', null, null, {
      idImpianto: impianto.idImpianto
    });
  }
  deleteImpiantoLinea(idImpianto:number,idLinea: number): Observable<ImpiantoDeleteResponse> {
    return this.apiClient.request('deleteImpiantoLinea', null, null, {
      idImpianto: idImpianto,
      idLinea: idLinea
    });
  }
  deleteImpiantoSottolinea(idImpianto:number,idLinea: number): Observable<ImpiantoDeleteResponse> {
    return this.apiClient.request('deleteImpiantoSottolinea', null, null, {
      idImpianto: idImpianto,
      idLinea: idLinea
    });
  }
  editImpiantoLinea(idImpianto:number,linea: LineaExtended): Observable<ImpiantoDeleteResponse> {
    return this.apiClient.request('editImpiantoLinea', linea, null, {
      idImpianto: idImpianto,
      idLinea: linea.idLinea
    });
  }
  editImpiantoSottolinea(idImpianto:number,linea: LineaExtended): Observable<ImpiantoDeleteResponse> {
    return this.apiClient.request('editImpiantoSottolinea', linea, null, {
      idImpianto: idImpianto,
      idLinea: linea.idSottoLinea
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
  downloadReportImpianti(filter?:any){
    const params = new HttpParams({});
    this.loadingService.show()
    return this.apiClient
      .request<any>(
        'downloadReportImpianti',
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

  isEmpyForm(formgroup: FormGroup): boolean{
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
      return true;
    }
    return false;
  }


  dateValidator(start:string='dataInizioValidita',end:string='dataFineValidita'): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const d1 = formgroup.controls[start].value as string;
      const d2 = formgroup.controls[end].value as string;
      if(d1 && d2 && ('' + d1).localeCompare(
        ''+d2
      )>0){
        return { dataFineValidita: true };
      }
      return null;
    };
  }

}
