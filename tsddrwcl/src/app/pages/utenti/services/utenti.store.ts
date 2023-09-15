import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtentiStoreService {
  private searchInput$: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor() {
    //This is intentional
  }

  setSearchInput(search: any) {
    this.searchInput$.next(search);
  }

  getSearchInput(): Observable<any> {
    return this.searchInput$.asObservable();
  }
}
