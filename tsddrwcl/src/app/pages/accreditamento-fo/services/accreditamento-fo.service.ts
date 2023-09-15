import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable } from 'rxjs';
import {
  DomandaAccreditamento,
  DomandaAccreditamentoResponse
} from '@pages/accreditamento-fo/models/domanda-accreditamento.model';
import { map, tap } from 'rxjs/operators';
import { AccreditamentoACL } from '@pages/accreditamento-fo/models/acl.model';
import { csiCatchError } from '@core/operators/catch-error.operator';
import { FunzionalitaProfiliResponse } from '@pages/configurazione-profili/models/funzionalita-profilo.model';
import {
  Utente,
  UtenteDeleteResponse
} from '@pages/utenti/models/utente.model';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { HttpParams } from '@angular/common/http';
import { LoadingService } from '@app/theme/layouts/loading.services';
@UntilDestroy()
@Injectable({
  providedIn: 'root'
})
export class AccreditamentoFoService {
  private currentAcl$: BehaviorSubject<AccreditamentoACL> =
    new BehaviorSubject<AccreditamentoACL>(null);

  constructor(private apiClient: ApiClient, private loadingService: LoadingService) {}

  getAccreditamentoACL(): Observable<AccreditamentoACL> {
    return this.apiClient
      .request('getAccreditamentoACL')
      .pipe(
        tap((response: AccreditamentoACL) => this.currentAcl$.next(response))
      );
  }

  getStoredAcl(): Observable<AccreditamentoACL> {
    return this.currentAcl$.asObservable();
  }

  getComboGestoriAccreditamento() {
    return this.apiClient.request('getComboGestoriAccreditamento');
  }

  createDomandaAccreditamento(
    domanda: DomandaAccreditamento
  ): Observable<DomandaAccreditamentoResponse> {
    return this.apiClient.request('createDomandaAccreditamento', domanda);
  }

  editDomandaAccreditamento(
    domanda: DomandaAccreditamento
  ): Observable<DomandaAccreditamentoResponse> {
    return this.apiClient.request('editDomandaAccreditamento', domanda, null, {
      id: domanda.idDomanda
    });
  }

  getDomandeAccreditamento() {
    return this.apiClient.request('getDomandeAccreditamento').pipe(
      csiCatchError(),
      map((response: FunzionalitaProfiliResponse) => response.content)
    );
  }

  dowloadReportAccreditamento(){
    const params = new HttpParams({});
this.loadingService.show()
    return this.apiClient
      .request<any>(
        'downloadReportAccreditamento',
      null,
        null,
       null
      )
      .pipe(csiCatchError(), untilDestroyed(this))
      .subscribe((response:any) => {
        this.loadingService.hide()
       // this._saveData(response);
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
  getDomandaAccreditamento(
    idDomanda: string
  ): Observable<DomandaAccreditamento> {
    return this.apiClient.request('getDomandaAccreditamento', null, null, {
      idDomanda
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
  deleteDomandaAccreditamento(
    domanda: DomandaAccreditamento
  ): Observable<DomandaAccreditamento> {
    return this.apiClient.request('deleteDomandaAccreditamento', null, null, {
      idDomanda: domanda.idDomanda
    });
  }
}

