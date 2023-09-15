import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { DichiarazioniACL } from '@pages/dichiarazioni/models/acl.model';
import { AsyncValidatorFn, FormGroup, ValidatorFn } from '@angular/forms';
import {
  csiCatchError,
  csiCatchErrorForValidators
} from '@core/operators/catch-error.operator';
import { UtenteUpdateResponse } from '@pages/utenti/models/utente.model';
import { FunzionalitaProfiliResponse } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import {
  Gestore,
  GestoreLinkUpdateResponse
} from '@pages/utenti/models/gestore.model';
import { SelectOption } from '@app/shared/form';
import { HttpParams } from '@angular/common/http';
import { Dichiarazione, DichiarazioneDeleteResponse, DichiarazioneUpdateResponse } from '../models/dichiarazione.model';
import { Linea, LineaExtended, Sottolinea } from '../models/linea.model';
import { Atto } from '../models/atto.model';
import { ICombo } from '@app/shared/prev-cons/interfaces/api-mr.model';
import { LoadingService } from '@app/theme/layouts/loading.services';

@Injectable({
  providedIn: 'root'
})
export class DichiarazioneService {
  private currentAcl$: BehaviorSubject<DichiarazioniACL> =
    new BehaviorSubject<DichiarazioniACL>(null);
  private currentComuni:any;

  constructor(private apiClient: ApiClient, private loadingService: LoadingService) {}

  getDichiarazioniACL(): Observable<DichiarazioniACL> {
    return this.apiClient
      .request('getDichiarazioniACL')
      .pipe(tap((response: DichiarazioniACL) => this.currentAcl$.next(response)));
  }

  getStoredAcl(): Observable<DichiarazioniACL> {
    return this.currentAcl$.asObservable();
  }

  getComboDichiarazioneGestori(){
    return this.apiClient.request('getComboDichiarazioneGestori');
  }

  getComboProvince() {
    return this.apiClient.request('getComboProvince');
  }

  getGestore(id: string): Observable<any> {
    return this.apiClient.request('getGestore', null, null, { id });
  }

  getImpianto(id: string): Observable<any> {
    return this.apiClient.request('getImpianto', null, null,{ idImpianto: id });
  }

  getComboComuni(idProvincia: number=null) {
    if(idProvincia){
      return this.apiClient.request('getComboComuni',null,{idProvincia:idProvincia})
      .pipe(tap((response: any) => {this.currentComuni=response;}));
    }else{
      return this.apiClient.request('getComboComuni')
      .pipe(tap((response: any) => {this.currentComuni=response;}));
    }
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

  editImpianto(dichiarazione: Dichiarazione): Observable<DichiarazioneUpdateResponse> {
    return this.apiClient.request('editImpianto', dichiarazione, null , { idImpianto: dichiarazione.idDichAnnuale });
  }

  deleteDichiarazione(dichiarazione: Dichiarazione): Observable<DichiarazioneDeleteResponse> {
    return this.apiClient.request('deleteDichiarazione', null, null, {
      idDichAnnuale: dichiarazione.idDichAnnuale
    });
  }
  deleteImpiantoLinea(idImpianto:number,idLinea: number): Observable<DichiarazioneDeleteResponse> {
    return this.apiClient.request('deleteImpiantoLinea', null, null, {
      idImpianto: idImpianto,
      idLinea: idLinea
    });
  }
  deleteImpiantoSottolinea(idImpianto:number,idLinea: number): Observable<DichiarazioneDeleteResponse> {
    return this.apiClient.request('deleteImpiantoSottolinea', null, null, {
      idImpianto: idImpianto,
      idLinea: idLinea
    });
  }
  editImpiantoLinea(idImpianto:number,linea: LineaExtended): Observable<DichiarazioneUpdateResponse> {
    return this.apiClient.request('editImpiantoLinea', linea, null, {
      idImpianto: idImpianto,
      idLinea: linea.idLinea
    });
  }
  editImpiantoSottolinea(idImpianto:number,linea: LineaExtended): Observable<DichiarazioneUpdateResponse> {
    return this.apiClient.request('editImpiantoSottolinea', linea, null, {
      idImpianto: idImpianto,
      idLinea: linea.idSottoLinea
    });
  }

  createLinkUtenteProfiloGestore(data: Partial<Gestore>): Observable<any> {
    return this.apiClient.request('createLinkUtenteProfiloGestore', data);
  }

  getComboRifiutiTariffa(idDichAnnuale:string=null) {
    if(idDichAnnuale){
      return this.apiClient.request('getComboRifiutiTariffa',null,{idDichAnnuale:idDichAnnuale});
    }else{
      return this.apiClient.request('getComboRifiutiTariffa');
    }
  }
  getRifiutiTariffa(idDichAnnuale:string=null) {
    if(idDichAnnuale){
      return this.apiClient.request('getRifiutiTariffa',null,{idDichAnnuale:idDichAnnuale});
    }else{
      return this.apiClient.request('getRifiutiTariffa');
    }
  }
  getPeriodi() {
    return this.apiClient.request('getPeriodi');
  }

  insertDichiarazioneBozza(data: Partial<Dichiarazione>){
    return this.apiClient.request('insertDichiarazioneBozza', data);
  }

  insertDichiarazione(data: Partial<Dichiarazione>){
    return this.apiClient.request('insertDichiarazione', data);
  }

  duplicaDichiarazione(idDichAnnuale:number){
    return this.apiClient.request('duplicaDichiarazione', null, null,{ idDichAnnuale: idDichAnnuale });
  }

  getDichiarazione(idDichAnnuale: number): Observable<DichiarazioneUpdateResponse> {
    return this.apiClient.request('getDichiarazione', null, null,{ idDichAnnuale: idDichAnnuale });
  }

  getDichiarazioneDownload(idDichAnnuale: number): Observable<any> {
    return this.apiClient.request('getDichiarazioneDownload', null, null,{ idDichAnnuale: idDichAnnuale });
  }

  deleteDichiarazioneSoggetto(idDichAnnuale:number,idSoggettiMr:number){
    return this.apiClient.request('deleteDichiarazioneSoggetto', null, null,{ idDichAnnuale: idDichAnnuale, idSoggettiMr:idSoggettiMr });
  }

  deleteRifiutoConferito(idDichAnnuale: number, idRifiutoTariffa: number): Observable<DichiarazioneDeleteResponse> {
    return this.apiClient.request<DichiarazioneDeleteResponse>('deleteRifiutoConferito', null, null,{ idDichAnnuale , idRifiutoTariffa });

  }

  getParametriFiltro(search: any) {
    return this.apiClient.request('getDichiarazioniParametri', null, this._searchToHttpParams(search)).pipe(
      csiCatchError(),
      map((response: any) => response.content)
    );
  }

  getDichiarazioni(search: any) {
    return this.apiClient.request('getDichiarazioni', null , this._searchToHttpParams(search)).pipe(
      csiCatchError(),
      map((response: FunzionalitaProfiliResponse) => response.content)
    );
  }

  getProfilo(idProfilo){
    return this.apiClient.request('getProfileBo', null, { idProfilo });

  }

  getDichiarazioniSingleResult(search: any) {
    return this.apiClient.request('getDichiarazioniSingleResult', null , this._searchToHttpParams(search)).pipe(
      csiCatchError(),
      map((response: FunzionalitaProfiliResponse) => response)
    );
  }

  existDichiarazione(search:any): Observable<{content:boolean}>{
    return this.apiClient.request('existDichiarazione', null, this._searchToHttpParams(search));
  }

  allowDuplicaDichiarazione(search:any): Observable<{content:boolean,message?:any}>{
    return this.apiClient.request('allowDuplicaDichiarazione', null, this._searchToHttpParams(search));
  }

  getComboDichiarazioneImpianti(idGestore:number=null): Observable<ICombo> {
    return this.apiClient.request('getComboDichiarazioneImpianti',null,idGestore?{idGestore:idGestore}:{})
  }

  getComboDichiarazioniAnni() {
    return this.apiClient.request('getComboDichiarazioniAnni');
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


  dateValidator(): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const d1 = formgroup.controls['annoDal'].value as string;
      const d2 = formgroup.controls['annoAl'].value as string;
      if(d1 && d2 && ('' + d1).localeCompare(
        ''+d2
      )>0){
        return { annoAl: true };
      }
      return null;
    };
  }


  versamentiValidator(): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const dataVersamento_0 = formgroup.controls['dataVersamento_0'].value as string;
      const dataVersamento_1 = formgroup.controls['dataVersamento_1'].value as string;
      const dataVersamento_2 = formgroup.controls['dataVersamento_2'].value as string;
      const dataVersamento_3 = formgroup.controls['dataVersamento_3'].value as string;
      const dataVersamento_4 = formgroup.controls['dataVersamento_4'].value as string;

      const importoVersato_0 = formgroup.controls['importoVersato_0'].value as number;
      const importoVersato_1 = formgroup.controls['importoVersato_1'].value as number;
      const importoVersato_2 = formgroup.controls['importoVersato_2'].value as number;
      const importoVersato_3 = formgroup.controls['importoVersato_3'].value as number;
      const importoVersato_4 = formgroup.controls['importoVersato_4'].value as number;

      if(dataVersamento_0 && importoVersato_0>0){
        return null;
      }
      if(dataVersamento_1 && importoVersato_1>0){
        return null;
      }
      if(dataVersamento_2 && importoVersato_2>0){
        return null;
      }
      if(dataVersamento_3 && importoVersato_3>0){
        return null;
      }
      //il conguaglio può essere negativo
      if(dataVersamento_4 && importoVersato_4!=0){
        return null;
      }
      return { nessunVersamento: true };
    };
  }

  conguaglioValidator(): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const importoConguaglio = +formgroup.get('importoVersato_4').value;
      const importoDebito = +formgroup.get('importoDebito').value;
      if(importoConguaglio!=importoDebito){
        return { conguaglio: true };
      }
      return null;
    };
  }

  rifiutoConferitoValidator(): ValidatorFn {
    return (
      formgroup: FormGroup
    ): { [key: string]: any } | null => {
      const rifiutoTariffa = formgroup.controls['rifiutoTariffa'].value as number;
      const quantita_0 = formgroup.controls['quantita_0'].value as number;
      const quantita_1 = formgroup.controls['quantita_1'].value as number;
      const quantita_2 = formgroup.controls['quantita_2'].value as number;
      const quantita_3 = formgroup.controls['quantita_3'].value as number;
      if(rifiutoTariffa &&

        (quantita_0>0 || quantita_1>0 || quantita_2>0 || quantita_3>0)

        ){
        return null;
      }
      return { nessunVersamento: true };
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

  downloadReportDichiarazioni(filter?:any){
    const params = new HttpParams({});
    this.loadingService.show()
    return this.apiClient
      .request<any>(
        'downloadReportDichiarazioni',
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
}
