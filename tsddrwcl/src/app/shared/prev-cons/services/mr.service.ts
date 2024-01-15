import { IMessage } from './../../../core/models/shared.model';
import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { FormGroup, ValidatorFn } from '@angular/forms';

import { BehaviorSubject, forkJoin, Observable, of } from 'rxjs';
import { map, tap, switchMap } from 'rxjs/operators';

import { ApiClient } from '@core/api-client/public-api';
import { IProfiloACL } from '@core/models/acl.model';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { SelectOption } from '@app/shared/form';
import {
  IPrevCons,
  IPrevConsLineeEntity,
  IPrevConsResponse
} from '../interfaces/prev-cons.interface';
import {
  ICombo,
  IComboAnni,
  IDichMrDownload,
  IParamFiltroApplicati
} from '../interfaces/api-mr.model';
import { SEZIONE } from '../models/constants';
import { LoadingService } from '@app/theme/layouts/loading.services';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Injectable({
  providedIn: 'root'
})
export class MrService {
  private currentAcl$: BehaviorSubject<IProfiloACL> =
    new BehaviorSubject<IProfiloACL>(null);
  private currentComuni: any;

  constructor(
    private apiClient: ApiClient,
    private loadingService: LoadingService
  ) {}

  getPrevConsACL(idTipoDoc: number): Observable<IProfiloACL> {
    return this.apiClient
      .request('getPrevConsACL', null, { idTipoDoc })
      .pipe(tap((response: IProfiloACL) => this.currentAcl$.next(response)));
  }

  getStoredAcl(): Observable<IProfiloACL> {
    return this.currentAcl$.asObservable();
  }

  getGestore(id: string): Observable<any> {
    return this.apiClient.request('getGestore', null, null, { id });
  }

  getImpianto(id: string): Observable<any> {
    return this.apiClient.request('getImpianto', null, null, {
      idImpianto: id
    });
  }

  getComboProvince() {
    return this.apiClient.request('getComboProvince');
  }

  getPercRecuperoVisible(codLinea: string, anno: number) {
    return this.apiClient.request('isPercRecuperoVisible', null, {codLinea, anno});
  }

  getPercScartoVisible(codLinea: string, anno: number){
    return this.apiClient.request('isPercScartoVisible', null, {codLinea, anno});
  }

  getComboComuni(idProvincia: number = null) {
    if (idProvincia) {
      return this.apiClient
        .request('getComboComuni', null, { idProvincia: idProvincia })
        .pipe(
          tap((response: any) => {
            this.currentComuni = response;
          })
        );
    } else {
      return this.apiClient.request('getComboComuni').pipe(
        tap((response: any) => {
          this.currentComuni = response;
        })
      );
    }
  }

  getCap(idComune: number) {
    if (
      this.currentComuni &&
      this.currentComuni.content &&
      this.currentComuni.content.find((i) => i.id == idComune)
    ) {
      return this.currentComuni.content.find((i) => i.id == idComune)
        .additionalValue;
    }
    return '';
  }

  getComboDichiarazioneGestori(): Observable<ICombo> {
    return this.apiClient.request('getComboDichiarazioneMrGestori');
  }

  getComboDichiarazioneImpianti(idGestore: number = null): Observable<ICombo> {
    return this.apiClient.request(
      'getComboDichiarazioneMrImpianti',
      null,
      idGestore ? { idGestore } : {}
    );
  }

  getComboSedimi() {
    return this.apiClient.request('getComboSedimi');
  }

  getComboDichiarazioniAnni(
    idTipoDoc: number,
    idStatoDichiarazione?: number
  ): Observable<IComboAnni> {
    let params: { idTipoDoc: number; idStatoDichiarazione?: number } = {
      idTipoDoc
    };
    if (idStatoDichiarazione) {
      params = {
        ...params,
        idStatoDichiarazione
      };
    }
    return this.apiClient.request<IComboAnni>(
      'getComboDichiarazioniMrAnni',
      null,
      params
    );
  }

  getComboEer() {
    return this.apiClient.request('getComboEer');
  }

  getDichiarazione(
    idPrevCons: number,
    idTipoDoc: number
  ): Observable<IPrevConsResponse> {
    // aggiungo al payload l'idTipoDoc per avere dichiarazioni o richieste
    return this.apiClient.request(
      'getDichiarazioneMrById',
      null,
      { idTipoDoc },
      { idPrevCons }
    );
  }

  getDichiarazioneDownload(
    idPrevCons: number,
    idTipoDoc: number
  ): Observable<IDichMrDownload> {
    return this.apiClient
      .request(
        'getDichiarazioneMrDownload',
        null,
        { idTipoDoc },
        { idPrevCons }
      )
      .pipe(csiCatchError());
  }

  /**
   *
   * @param search nel caso della ricerca è un oggetto tipo:
   * {
   *   idGestore: 11
   *   annoTributoDal: 2021
   * }
   * @param idTipoDoc discriminante per sapere se sto prendendo delle dichiarazioni (2) o richieste (1)
   * @returns
   */
  getDichiarazioni(search: any, idTipoDoc: number): Observable<IPrevCons> {
    // aggiungo al payload l'idTipoDoc per avere dichiarazioni o richieste
    search.idTipoDoc = idTipoDoc;
    return this.apiClient
      .request('getDichiarazioniMr', null, this._searchToHttpParams(search))
      .pipe(
        csiCatchError(),
        map((response: IPrevConsResponse) => response.content)
      );
  }

  getPrevConsResolver(
    idPrevCons: number,
    idTipoDoc: number,
    message?: IMessage
  ): Observable<IPrevCons> {
    return this.getDichiarazione(idPrevCons, idTipoDoc).pipe(
      map(({ content }: IPrevConsResponse) => {
        let { prevConsLinee = [] } = content;
        prevConsLinee.forEach((linea, i, linee) => {
          let { prevConsDett = [] } = linea;
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
        });
        return { ...content, prevConsLinee, message };
      }),
      switchMap((response) => {
        if (response.impianto.idImpianto && !response.prevConsRichiesta) {
          return this.getImpiantiLinee({
            idImpianto: response.impianto.idImpianto
          }).pipe(map((lineeImpianto) => ({ ...response, lineeImpianto })));
        }
        return of(response);
      })
    );
  }

  getParametriFiltro(search: any): Observable<string> {
    return this.apiClient
      .request(
        'getDichiarazioniMrParametri',
        null,
        this._searchToHttpParams(search)
      )
      .pipe(
        csiCatchError(),
        map((response: IParamFiltroApplicati) => response.content)
      );
  }

  deleteDichiarazione(
    idPrevCons: number,
    idTipoDoc: number
  ): Observable<IPrevConsResponse> {
    return this.apiClient.request(
      'deleteDichiarazioneMr',
      null,
      { idTipoDoc },
      { idPrevCons }
    );
  }

  //eliminazione linea processo-impiantistico
  deleteDichiarazioneMrPrevConsLinee(idPrevConsLinee: number) {
    return this.apiClient.request('deleteDichiarazioneMrLineePrevCons', null, {
      idPrevConsLinee: idPrevConsLinee
    });
  }

  //eliminazione prevConsDett sotto tab processo-impiantistico
  deleteDichiarazioneMrPrevConsDett(idPrevCons: number, idPrevConsDett: number) {
    return this.apiClient.request(
      'deleteDichiarazioneMrPrevConsDett',
      null,
      null,
      { idPrevConsDett: idPrevConsDett,
        idPrevCons: idPrevCons }
    );
  }

  getProfilo(idProfilo): Observable<any> {
    return this.apiClient.request('getProfileBo', null, { idProfilo });
  }

  getImpiantiLinee(search: any): Observable<any[]> {
    return this.apiClient
      .request<{ content: any[] }>(
        'getImpiantiLinee',
        null,
        this._searchToHttpParams(search)
      )
      .pipe(map(({ content }) => content));
  }

  /**
   * @param idImpianto
   * @returns lista di linee e sottolinee con descrizioni
   */
  getImpiantoLinee(
    idImpianto: string,
    checkIdPrevCons?: any
  ): Observable<any[]> {
    let request = {
      idPrevCons: checkIdPrevCons
    };
    return this.apiClient
      .request<{ content: any[] }>(
        'getImpiantoLinee',
        null,
        { ...request },
        { idImpianto }
      )
      .pipe(map(({ content }) => content));
  }

  /**
   * @description in caso di inserimento D-MR legata a R-MR mi torna le linee della R-MR
   * pronte per essere utilizzate nella D-MR
   * @param idPrevCons della richiesta da legare alla dichiarazione
   * @returns lista di linee di una richiesta
   */
  getLineeRichiesta(idPrevCons: number): Observable<IPrevConsLineeEntity[]> {
    return this.apiClient
      .request('getLineeRichiesta', null, null, { idPrevCons })
      .pipe(map(({ content }) => content));
  }

  getLineeObject(
    idImpianto: string,
    linee?: IPrevConsLineeEntity[],
    checkIdPrevCons?: number
  ): Observable<SelectOption<string, string>[]> {
    if (!checkIdPrevCons) {
      checkIdPrevCons = null;
    }

    //se mi arrivano le linee ho già lì le info da prendere.

    if (linee?.length) {
      return of(
        linee.map((lineaItem) => {
          if ('codLinea' in lineaItem) {
            return {
              id: lineaItem.idImpiantoLinea as any,
              value: lineaItem.descLinea
            };
          } else {
            // ho idSottoLinea -> nell'array descrizione faccio il find idSottoLinea -> prendo descSottoLinea
            return {
              id: lineaItem.idImpiantoLinea as any,
              value: lineaItem.descSottoLinea
            };
          }
        })
      );
    }

    return forkJoin([
      this.getImpiantiLinee({ idImpianto }),
      this.getImpiantoLinee(idImpianto, checkIdPrevCons)
    ]).pipe(
      map(([linea, descrizione]) => {
        return linea.map((lineaItem) => {
          if ('idLinea' in lineaItem) {
            return {
              id: lineaItem.idImpiantoLinea,
              value: descrizione.find(
                (item) => item.idLinea == lineaItem.idLinea
              )?.descLinea
            };
          } else {
            // ho idSottoLinea -> nell'array descrizione faccio il find idSottoLinea -> prendo descSottoLinea
            return {
              id: lineaItem.idImpiantoLinea,
              value: descrizione.find(
                (item) => item.idSottoLinea == lineaItem.idSottoLinea
              )?.descSottoLinea
            };
          }
        });
      })
    );
  }

  existPrevCons(search: any): Observable<{ content: boolean }> {
    return this.apiClient.request(
      'existsPrevCons',
      null,
      this._searchToHttpParams(search)
    );
  }

  // Verifica che almeno un campo del form sia valorizzato
  hasValueValidator(): ValidatorFn {
    return (formgroup: FormGroup): { [key: string]: any } | null => {
      const { controls } = formgroup;
      const keys = Object.keys(formgroup.value);
      return keys.some((key) => controls[key] && !!controls[key].value)
        ? null
        : { hasValue: true };
    };
  }

  // verifica che annoTributoDal sia minore di annoTributoAl
  dateValidator(): ValidatorFn {
    return (formgroup: FormGroup): { [key: string]: any } | null => {
      const annoTributoDal = formgroup.controls['annoTributoDal'] as any;
      const annoTributoAl = formgroup.controls['annoTributoAl'] as any;
      if (
        +annoTributoDal?.value &&
        +annoTributoAl?.value &&
        +annoTributoDal?.value > +annoTributoAl?.value
      ) {
        return { annoTributoAl: true };
      }
      // il form è valido, settaggi per eliminare i messaggi di errore
      this._removeErrorsFromControl(annoTributoDal);
      this._removeErrorsFromControl(annoTributoAl);
      return null;
    };
  }

  // Verifica che siano valorizzati entrambi i campi
  notBothValuedValidator(): ValidatorFn {
    return (formgroup: FormGroup): { [key: string]: boolean } | null => {
      const dataInvioDoc = formgroup.controls['_richiesta_dataInvioDoc'] as any;
      const modalita = formgroup.controls['_richiesta_modalita'] as any;
     const numProtocollo = formgroup.controls['_richiesta_numProtDoc'] as any;
     console.log(dataInvioDoc.value, modalita.value, numProtocollo.value)

      if (
        (dataInvioDoc?.value?.toString() &&
          modalita?.value?.toString() == '') ||
        (modalita?.value?.toString() && dataInvioDoc?.value?.toString() == '')
      ) {
        return { notBothValued: true };
      }
else if(
    ( numProtocollo?.value?.toString() != null  && (dataInvioDoc?.value?.toString() == null
    || modalita?.value?.toString() === '') && (modalita?.value?.toString() == undefined ||
    modalita?.value?.toString() == null ||
    modalita?.value?.toString() == '')

    )
      ){
        return { notBothValued: true };
      }
      // il form è valido, settaggi per eliminare i messaggi di errore
      this._removeErrorsFromControl(dataInvioDoc);
      this._removeErrorsFromControl(modalita);
      //this._removeErrorsFromControl(numProtocollo);

      return null;
    };
  }

  //Verifica che sia valorizzato codiceEer se ho quantità != 0
  notCodiceEerAndQuantitaValuedValidator(): ValidatorFn {
    return (formgroup: FormGroup): { [key: string]: boolean } | null => {
      const codiceEer = formgroup.controls['codiceEer'].value as string;
      const quantita = formgroup.controls['quantita'].value as number;
      if (!!quantita && !codiceEer) {
        return { notCodiceValued: true };
      }
      this._removeErrorsFromControl(formgroup.controls['codiceEer'] as any);
      this._removeErrorsFromControl(formgroup.controls['quantita'] as any);
      return null;
    };
  }

  //Verifica che sia valorizzato descrizioneMat se ho quantità != 0
  notDescMatUscitaAndQuantitaValuedValidator(): ValidatorFn {
    return (formgroup: FormGroup): { [key: string]: boolean } | null => {
      const descMatUscita = formgroup.controls['descMatUscita'].value as string;
      const quantita = formgroup.controls['quantita'].value as number;
      if (!!quantita && !descMatUscita) {
        return { notDescMatUscitaValued: true };
      }
      this._removeErrorsFromControl(formgroup.controls['descMatUscita'] as any);
      this._removeErrorsFromControl(formgroup.controls['quantita'] as any);
      return null;
    };
  }

  /**
   * Quando devo fare una validazione su 2 o più campi collegati fra loro, possono rimanere dei messaggi di errore
   * Con questa funzzione rimuovo manualmente il messaggio d'errore (statusMessage)
   * e ripristino a "basic" il bordo del campo, che altrimenti rimarrebbe rosso ("danger")
   *
   * @param control
   * @returns oggetto controls di tipo any che non mostra più messaggi di errore
   */
  private _removeErrorsFromControl(control: any): void {
    control.statusMessage = null;
    control.colorStatus = 'basic';
  }

  insertDichiarazioneBozza(
    data: Partial<IPrevCons>,
    search: any
  ): Observable<IPrevConsResponse> {
    return this.apiClient.request(
      'insertDichiarazioneMrBozza',
      data,
      this._searchToHttpParams(search)
    );
  }

  insertDichiarazioneMr(data: Partial<IPrevCons>, search: any) {
    return this.apiClient.request(
      'insertDichiarazioneMr',
      data,
      this._searchToHttpParams(search)
    );
  }
  downloadReportPrevCons(idTipoDoc: number, filter: any) {
    let param;
    if (filter === null) {
      param = { idTipoDoc };
    } else {
      filter.idTipoDoc = idTipoDoc;
      param = this._searchToHttpParams(filter);
    }
    const params = new HttpParams({});
    this.loadingService.show();
    return this.apiClient
      .request<any>('downloadReportPrevCons', null, param, null)
      .pipe(csiCatchError())
      .subscribe((response: any) => {
        this.loadingService.hide();
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
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: `application/${{ type }}` });
    return blob;
  }
  private _searchToHttpParams(search: any): HttpParams {
    let httpParams = new HttpParams();
    for (let key in search) {
      if (Array.isArray(search[key])) {
        search[key].forEach(
          (i) => (httpParams = httpParams.append(key, i.toString()))
        );
      } else if (search[key]) {
        httpParams = httpParams.append(key, search[key].toString());
      }
    }
    return httpParams;
  }
}
