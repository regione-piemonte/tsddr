import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

/*
  Servizio di appoggio che
  che genericamente manda eventi in
  broadcast per intero applicativo
*/



@Injectable({
  providedIn: 'root'
})
export class BroadcastEventService {
  public stream: Subject<any> = new Subject<any>();

  constructor() {
    ////This is intentional
  }

  getStream(): Observable<any> {
    return this.stream.asObservable();
  }

  sendResult<T>(packet:T) {
    this.stream.next(packet);
  }

}
