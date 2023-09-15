import { Injectable } from '@angular/core';
import { Indirizzo } from '@app/pages/impianti/models/impianto.model';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Dichiarazione, DichiarazioneEditingStore, reportsDichiarazioneEditingStore, RifiutiConferiti, RifiutoConferito, SoggettoMr, SoggettoMrExtended, statusFormEditingStore, Versamenti } from '../models/dichiarazione.model';


@Injectable({
  providedIn: 'root'
})
export class DichiarazioneEditingStoreService{
  private currentItemsSoggetti$: BehaviorSubject<SoggettoMrExtended[]> =
    new BehaviorSubject<SoggettoMrExtended[]>(null);

  private currentDichiarazioneStore$: BehaviorSubject<DichiarazioneEditingStore> =
    new BehaviorSubject<DichiarazioneEditingStore>(null);
  currentDichiarazioneStore:DichiarazioneEditingStore;

  private currentReport$: BehaviorSubject<reportsDichiarazioneEditingStore> =
  new BehaviorSubject<reportsDichiarazioneEditingStore>(null);

  private currentStatus$: BehaviorSubject<statusFormEditingStore> =
  new BehaviorSubject<statusFormEditingStore>(null);

  currentStatus:statusFormEditingStore;
  currentReport:reportsDichiarazioneEditingStore;
  currentItemsSoggetti:SoggettoMrExtended[];
  currentIndirizzo:Indirizzo;
  currentAnnotazioni:string;
  currentDichiarazione:Dichiarazione;
  rifiutiConferiti:RifiutiConferiti;
  versamenti:Versamenti;

  constructor() {
    //This is intentional
  }

  // in caso di edit setto in memoria
  setDichiarazione(dichiarazioneEditingStore:DichiarazioneEditingStore,key:string='new'){
    this.currentDichiarazione = dichiarazioneEditingStore.dichiarazione;
    this.rifiutiConferiti = dichiarazioneEditingStore.dichiarazione.rifiutiConferiti;
    this.currentItemsSoggetti = dichiarazioneEditingStore.dichiarazione.soggettiMr;
    this.currentAnnotazioni = dichiarazioneEditingStore.dichiarazione.annotazioni;
    this.currentIndirizzo = dichiarazioneEditingStore.dichiarazione.indirizzo;
    this.versamenti = dichiarazioneEditingStore.dichiarazione.versamenti;
    this.currentDichiarazioneStore = dichiarazioneEditingStore;
    this.currentStatus = {...dichiarazioneEditingStore.status};
    this.currentStatus$.next(this.currentStatus);
    this.currentItemsSoggetti$.next(this.currentItemsSoggetti);
    this._dichiarazioneNext(key);
  }

  getStoreReport(key:string='new'): Observable<reportsDichiarazioneEditingStore> {
    return this.currentReport$.asObservable();
  }

  getStoredDichiarazione(key:string='new'): Observable<DichiarazioneEditingStore> {
    return this.currentDichiarazioneStore$.asObservable();
  }

  getStoredSoggetti(key:string='new'): Observable<SoggettoMr[]> {
    return this.currentItemsSoggetti$.asObservable();
  }

  getStoreStatus(key:string='new'): Observable<statusFormEditingStore> {
    return this.currentStatus$.asObservable();
  }

  setStatus(stato:string,statovalue:boolean,key:string='new'): void {
     this.currentStatus[stato]=statovalue;
     this.currentStatus$.next(this.currentStatus);
  }

  addSoggetto(current:SoggettoMr,key:string='new'): void {
    this.currentItemsSoggetti.push(current);
    this.currentItemsSoggetti$.next(this.currentItemsSoggetti);
    this._dichiarazioneNext(key);
  }

  removeSoggetto(current:SoggettoMr,key:string='new'): void {
    this.currentItemsSoggetti.forEach((item, index, object)=>{
      if(JSON.stringify(item) === JSON.stringify(current)){
        object.splice(index, 1);
      }
    });
    this.currentItemsSoggetti$.next(this.currentItemsSoggetti);
    this._dichiarazioneNext(key);
  }

  setIndirizzo(current:Indirizzo, sedeValid:boolean,key:string='new'): void {
    this.currentDichiarazioneStore.sedeValid = sedeValid;
    this.currentIndirizzo = current;
    this._dichiarazioneNext(key);
  }

  setAnnotazioni(current:string,key:string='new'): void {
    this.currentAnnotazioni = current;
    this._dichiarazioneNext(key);
  }

  setRifiutiConferiti(current:RifiutiConferiti,key:string='new'): void {
    this.rifiutiConferiti = current;
    this._dichiarazioneNext(key);
  }

  setVersamenti(current:Versamenti,key:string='new'): void {
    this.versamenti = current;
    this._dichiarazioneNext(key);
  }

  setReport(totaleImporto:number,key:string='new'): void {
    this.currentReport = {
      totaleImporto:totaleImporto
    };
    this.currentReport$.next(this.currentReport);
  }

  // generiche

  _dichiarazioneNext(key:string='new'){
    this.currentDichiarazione ={
      ...this.currentDichiarazione,
      soggettiMr:this.currentItemsSoggetti,
      annotazioni:this.currentAnnotazioni,
      indirizzo:this.currentIndirizzo,
      rifiutiConferiti:this.rifiutiConferiti,
      versamenti:this.versamenti
    };
    this.currentDichiarazioneStore.dichiarazione = this.currentDichiarazione;
    this.currentDichiarazioneStore$.next(this.currentDichiarazioneStore);
  }

  reset(key:string='new'){
    // TODO
    this.currentItemsSoggetti = [];
    this.currentIndirizzo = null;
    this.currentAnnotazioni = null;
    this.rifiutiConferiti = {rifiutiConferiti:[]};
    this.currentDichiarazione = {rifiutiConferiti:this.rifiutiConferiti};
    this.currentDichiarazioneStore = null;
    this.currentReport = null;
    this.currentStatus = null;
    this.currentStatus$.next(this.currentStatus);
    this.currentItemsSoggetti$.next(this.currentItemsSoggetti);
    this.currentReport$.next(this.currentReport);
    this.currentDichiarazioneStore$.next(this.currentDichiarazioneStore);
  }
}
