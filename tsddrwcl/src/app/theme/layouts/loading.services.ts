import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class LoadingService {
  private _spinner = new BehaviorSubject<boolean>(true);
  spinner$ = this._spinner.asObservable();

  hide(delay: number = 0): void {
    this._toggle(false, delay);
  }

  show(): void {
    this._toggle(true);
  }

  private _toggle(value: boolean, delay: number = 0): void {
    setTimeout(() => {
      this._spinner.next(value);
    }, delay);
  }
}
