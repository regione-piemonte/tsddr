import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UtilsService {

  constructor() {
     //This is intentional
  }

  isDefined(variable: any): boolean {
    return variable !== undefined;
  }

  isNotNull(variable: any): boolean {
    return variable !== null;
  }

  isNotEmpty(s: string): boolean {
    return s !== '';
  }

  stringIsNotEmpty(s: string): boolean {
    return this.isDefined(s) && this.isNotNull(s) && this.isNotEmpty(s);
  }

}
