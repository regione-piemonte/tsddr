import { Injectable } from '@angular/core';
import { BaseInput, Form } from '../../shared/form';

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractFormHelperService {
  protected _items: Map<string, () => Form> = new Map<string, () => Form>();

  constructor() {
    //This is intentional
  }

  protected abstract _initForms();

  get(
    name: string
  ): Form {
    return this._items.get(name)();
  }

  set<T>(
    form: Form,
    data: Partial<T>
  ): void {
    for (const k in data) {
      if (data.hasOwnProperty(k)) {
        const input: BaseInput = form.get(k);
        if (input) {
          input.setValue(data[k].toString());
        }
      }
    }
  }

}
