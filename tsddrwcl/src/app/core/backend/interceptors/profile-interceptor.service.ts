import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { SecurityService } from '@app/core/services';


/*
  HttpInterceptor che aggiunge header "idProfilocurrentUser"
  che contiene il profilo corrente(recuperato dal servizio di security) dell'utente alle chiamate BE
*/


@Injectable()
export class ProfileInterceptor implements HttpInterceptor {
  constructor(private securityService:SecurityService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
   // SecurityService
    // getIdProfilo
    if(this.securityService.getIdProfilo()){
      req = req.clone({
        setHeaders: {
          ['idProfilocurrentUser']: this.securityService.getIdProfilo()
        }
      });
    }
    
    return next.handle(req);
  }
}
