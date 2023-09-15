import { Injectable } from '@angular/core';
import { IStatusMrFormEditingStore } from '../interfaces/prev-cons.interface';

@Injectable({
  providedIn: 'root'
})
export class MrStoreService {
  private _status: IStatusMrFormEditingStore = {
    processoChanged: false,
    processoValid: false,
    richiestaChanged: false,
    richiestaValid: false,
    sedeChanged: false,
    sedeValid: false
  }

  constructor() {
     //This is intentional
     }

  /**
   * @description get status mr store
   */
  getStatus(): IStatusMrFormEditingStore {
    return this._status;
  }

  setPropsStatus<T extends IStatusMrFormEditingStore, K extends keyof IStatusMrFormEditingStore>(key: K,value:boolean) {
    this._status[key] = value;
  }

  /**
   * @description resetta i valori dello status
   */
  resetStatus(): IStatusMrFormEditingStore {
    return this._status = {
      processoChanged: false,
      processoValid: false,
      richiestaChanged: false,
      richiestaValid: false,
      sedeChanged: false,
      sedeValid: false
    }
  }

}
