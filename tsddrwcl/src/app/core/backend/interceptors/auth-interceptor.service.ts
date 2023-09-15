import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';


/*
  HttpInterceptor che viene sfruttato a livello di sviluppo
  per fornire al BE header di autenticazione fake
  non serve in test/prod in quanto il FE viene erogato in
  un unico ear con il BE
*/


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor() {
    //This is intentional
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // && req.url.includes('secure')
    if (environment.fakeAuth.enabled) {
      req = req.clone({
        setHeaders: {
          [environment.fakeAuth.header.key]: environment.fakeAuth.header.value
        }
      });
    }

    return next.handle(req);
  }
}
