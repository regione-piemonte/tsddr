import { HttpHeaders } from '@angular/common/http';

import { RequestMethods } from './request-method.model';

export class ApiConfig {
  name: string;
  url: string;
  method: string;
  headers?:
    | HttpHeaders
    | {
        [header: string]: string | string[];
      };
  timeout?: number;

  constructor(api: ApiConfig) {
    this.name = api.name || '';
    this.url = api.url;
    this.method = (api.method || RequestMethods.GET).toUpperCase();
    this.headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=UTF-8'
    );
    if (api.headers) {
      for (const key in api.headers) {
        if (api.headers[key]) {
          this.headers = this.headers.set(key, api.headers[key].toString());
        }
      }
    }
    this.timeout = api.timeout || 30000;
  }
}
