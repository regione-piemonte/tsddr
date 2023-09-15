import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { IMessageWrapper } from '../models/shared.model';

/*
  Servizio di utility che recupera il messaggi e le note tramite chiamata api
  Trasversale a tutti i moduli
*/

@Injectable()
export class UtilityService {
  filter: any = {};
  constructor(private apiClient: ApiClient) {}

  ///Get and Set form value to restore the searched items
  setFormValue(filter: any) {
    this.filter = filter;
  }
  getFormValue() {
    return this.filter;
  }

  ////
  getMessage(codMsg) {
    return this.apiClient.request<IMessageWrapper>('getMessage', null, null, {
      codMsg
    });
  }

  getNotaInfo(codNotaInfo) {
    return this.apiClient.request<any>('getNotaInfo', null, null, {
      codNotaInfo
    });
  }
}
