import { Injectable } from '@angular/core';
import { ApiClient } from '@eng-ds/api-client';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ApiPageableResponse, UserInfo } from '@app/core';
import { BroadcastEventService } from './broadcast-event.service';
import { environment } from '@env/environment';

/*
  Servizio di sicurezza che lavora a livello di
  - recupero dati utente
  - logout dal sistema
  - recupero delle cards che sono in hp e nel menu di sinistra e ti da elenco di funzionalita' disponibili per utente profilo corrente
  - cambio profilo corrente (storato a FE a livello di localStorage)
*/


@Injectable()
export class SecurityService {
  private _user$: BehaviorSubject<UserInfo> = new BehaviorSubject<UserInfo>(
    null
  );
  private _cards$: BehaviorSubject<any> = new BehaviorSubject<any>(
    null
  );
  private _userDescription$: BehaviorSubject<string> = new BehaviorSubject<string>(
    null
  );
  profiles:{id:string,value:string,additionalValue:string}[]=[];

  constructor(private apiClient: ApiClient, private broadcastEventService: BroadcastEventService) { }

  getUserProfile(): Observable<UserInfo> {
    return this.apiClient.request('idProfilo').pipe(
      catchError((err) => {
        return throwError(err);
      }),
      map((response: any) => {
        localStorage.setItem('idProfilo', response.content.idProfilo);
        this.initUserCards(response.content.idProfilo);
        this.getComboProfiles().subscribe(
          (response) => this.profiles=response
        );
        return response.content;
      })
    );
  }

  public getIdProfilo(): string {
    return localStorage.getItem('idProfilo');
  }

  public getSsoLogoutUrl(): string {
    if(this.isProfileBo())
      return environment['auth'].ssoLogoutBO;
    else
      return environment['auth'].ssoLogoutFO;
  }

  public isProfileBo(): boolean {
    if(!this.profiles || this.profiles.length===0){
      return false;
    }
    // 1 BO 2 FO
    return this.profiles.find(i=>i.id === this.getIdProfilo()).additionalValue==="1";
  }

  public getProfileDesc(idProfilo:string): string {
    if(!this.profiles || this.profiles.length===0){
      return '';
    }
    return this.profiles.find(i=>i.id === idProfilo).value;
  }

  public isEditEnabledPersonalArea(): boolean {
    return !environment.backend.profilesEditAreaPersonalDisabled.includes(+this.getIdProfilo());
  }

  initUserInfo() {
    return this.resolvePromise();
  }

  resolvePromise() {
    let promiseResolve: any;
    const userPromise = this.getUserProfile().toPromise();
    const promise = new Promise((resolve) => {
      promiseResolve = resolve;
    });
    userPromise
      .then((response) => {this._user$.next(response);this._userDescription$.next(response.descProfilo)})
      .catch()
      .finally(() => promiseResolve());
    return promise;
  }

  getUser(): Observable<UserInfo> {
    return this._user$.asObservable();
  }

  getUserDescription(): Observable<string> {
    return this._userDescription$.asObservable();
  }

  onLogout():Observable<boolean> {
    // log logout
    return this.logLogout().pipe(
      catchError((err) => {
        return throwError(err);
      }),
      map((response: any) => {
        this._user$.next(null);
        //localStorage.removeItem('idProfilo');
        localStorage.clear();
        sessionStorage.clear();
        console.log('logout OK');
        return true;
      })
    );
    //this.resolvePromiseLogLogout();
  }

  getUserCards(): Observable<any> {
    return this._cards$.asObservable();
  }

  getStrutturaMenueCardList(idProfile: number): Observable<ApiPageableResponse<any>> {
    return this.apiClient.request('strutturaMenueCard', null, { idProfilo: idProfile }).pipe(
      catchError((err) => {
        return throwError(err);
      }),
      map((response: any) => {
        return response.content;
      })
    );
  }

  initUserCards(idProfile: number) {
    return this.resolvePromiseCards(idProfile);
  }
  resolvePromiseCards(idProfile: number) {
    let promiseResolve: any;
    const cardPromise = this.getStrutturaMenueCardList(idProfile).toPromise();
    const promise = new Promise((resolve) => {
      promiseResolve = resolve;
    });
    cardPromise
      .then((response) => this._cards$.next(response))
      .catch()
      .finally(() => promiseResolve());
    return promise;
  }


  getComboProfiles(): Observable<{id:string,value:string,additionalValue:string}[]> {
    return this.apiClient.request('comboProfilo').pipe(
      catchError((err) => {
        return throwError(err);
      }),
      map((response: any) => {
        return response.content;
      })
    );
  }

  changeProfile(idProfile: string) {
    localStorage.setItem('idProfilo', idProfile);
    this._userDescription$.next(this.getProfileDesc(idProfile.toString()));
    this.initUserCards(Number(idProfile));
    this.resolvePromiseLogCambioProfilo(Number(idProfile));
    this.broadcastEventService.sendResult<any>({
      key: 'security-change-profile'
    });
  }

  resolvePromiseLogCambioProfilo(idProfile: number) {
    let promiseResolve: any;
    const logPromise = this.logCambioProfilo(idProfile).toPromise();
    const promise = new Promise((resolve) => {
      promiseResolve = resolve;
    });
    logPromise
      .then((response) => {
        //This is intentional
       })
      .catch()
      .finally(() => promiseResolve());
    return promise;
  }
  logCambioProfilo(idProfile: number): Observable<ApiPageableResponse<any>> {
    return this.apiClient.request('logCambioProfilo', null, { idProfilo: idProfile }).pipe(
      catchError((err) => {
        return throwError(err);
      }),
      map((response: any) => {
        return response.content;
      })
    );
  }

  resolvePromiseLogLogout() {
    let promiseResolve: any;
    const logoutPromise = this.logLogout().toPromise();
    const promise = new Promise((resolve) => {
      promiseResolve = resolve;
    });
    logoutPromise
      .then((response) => {
        //This is intentional
       })
      .catch()
      .finally(() => promiseResolve());
    return promise;
  }
  logLogout() {
    return this.apiClient.request('logLogout').pipe(
      catchError((err) => {
        return throwError(err);
      }),
      map((response: any) => {
        return response.content;
      })
    );
  }
}
