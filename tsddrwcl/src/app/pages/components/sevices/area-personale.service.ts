import { Injectable } from '@angular/core';
import { I18nService } from '@eng-ds/translate';
import { Observable, of, Subject} from 'rxjs';
import { SelectOption } from '@app/shared/form';
import { ApiClient } from '@eng-ds/api-client';
import { SecurityService } from '@app/core';
import { catchError, map } from 'rxjs/operators';
import { NotificationService } from '@app/shared/notification/notification.service';
import { Router } from '@angular/router';

/*
  AreaPersonaleService che lavora in
  particolare con la modale di area personale
*/

@Injectable({
  providedIn: 'root'
})
export class AreaPersonaleService {
  public stream: Subject<any> = new Subject<any>();

  constructor(private i18n: I18nService,
    private apiClient: ApiClient,
    private securityService: SecurityService,
    private notificationService: NotificationService,
    private router: Router
  ) {
  }

  getStream(): Observable<any> {
    return this.stream.asObservable();
  }

  sendResult<T>(packet: T) {
    this.stream.next(packet);
  }

  getAreaPersonaleACL(): Observable<any> {
    return this.apiClient
      .request('getAreaPersonaleACL');
  }

  getProfiles(): Observable<{ content: SelectOption<number, string>[] }> {
    return this.apiClient.request('comboProfilo');
  }
  changeProfile(idProfile: string) {
    this.securityService.changeProfile(idProfile);
    // se cambi il profilo vai alla home 
    this.router.navigate(['home']);
  }
  getUtenteCorrente(): Observable<{ content: any }> {
    return this.apiClient.request('utenteCorrente');
  }
  setUtenteCorrente(data): Observable<{ content: any }> {
    return this.apiClient.request('salvaUtenteCorrente', data);
  }
  verificaCampiObbligatori(data) {
    return this.apiClient.request('verificaDatiObbligatori', data);
  }
  validatorVerificaCampiObbligatori(key, data) {
    let body = new Object();
    body[key] = data;
    return this.apiClient.request('verificaDatiObbligatori', body).pipe(
      catchError((err) => {
        this.sendResult({
          dataTag: 'validatorVerificaCampiObbligatoriErrors',
          data: err.error.errors
        });
        console.log(err.error.errors);
        console.log(err.error.errors[0]);
        this.notificationService.error({
          title: err.error.errors[0].titoloMsg,
          text: err.error.errors[0].testoMsg,
        });
        return of([false]);
      }),
      map((result: any) => {
        if (result.content) {
          this.sendResult({
            dataTag: 'validatorVerificaCampiObbligatoriErrors',
            data: null
          });
        }
        return result.content ? true : false
      }
      )
    );
  }
}
