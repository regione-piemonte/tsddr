import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Atto } from '../models/atto.model';


@Injectable({
  providedIn: 'root'
})
export class StoreGridMemoryService<T>{
  
  private currentItems$: BehaviorSubject<T[]> =
    new BehaviorSubject<T[]>(null);
  
  private currentItemsAtti$: BehaviorSubject<Atto[]> =
    new BehaviorSubject<Atto[]>(null);
  
  currentItems:T[];
  currentItemsAtti:Atto[];
  
  constructor() {
    this.currentItems = [];
    this.currentItemsAtti = [];
  }

  getStored(): Observable<T[]> {
    return this.currentItems$.asObservable();
  }

  addLinea(current:T): void {
    this.currentItems.push(current);
    this.currentItems$.next(this.currentItems);
  }

  removeLinea(current:T): void {
    this.currentItems.forEach((item, index, object)=>{
      if(JSON.stringify(item) === JSON.stringify(current)){
        object.splice(index, 1);
      }
    });
    this.currentItems$.next(this.currentItems);
  }

  getStoredAtti(): Observable<Atto[]> {
    return this.currentItemsAtti$.asObservable();
  }

  addAtto(current:Atto): void {
    this.currentItemsAtti.push(current);
    this.currentItemsAtti$.next(this.currentItemsAtti);
  }

  removeAtto(current:Atto): void {
    this.currentItemsAtti.forEach((item, index, object)=>{
      if(JSON.stringify(item) === JSON.stringify(current)){
        object.splice(index, 1);
      }
    });
    this.currentItemsAtti$.next(this.currentItemsAtti);
  }

  reset(){
    this.currentItems = [];
    this.currentItemsAtti = [];
    this.currentItems$.next(this.currentItems);
    this.currentItemsAtti$.next(this.currentItemsAtti);
  }
}
